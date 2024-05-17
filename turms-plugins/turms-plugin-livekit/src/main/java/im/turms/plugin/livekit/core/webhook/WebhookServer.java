/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.plugin.livekit.core.webhook;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import com.google.protobuf.util.JsonFormat;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

import im.turms.plugin.livekit.core.metrics.MetricNameConst;
import im.turms.plugin.livekit.core.proto.webhook.WebhookEvent;
import im.turms.plugin.livekit.property.WebhookProperties;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.metrics.TurmsMicrometerChannelMetricsRecorder;

/**
 * @author James Chen
 */
public class WebhookServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookServer.class);
    private static final JsonFormat.Parser PARSER = JsonFormat.parser()
            .ignoringUnknownFields();
    private static final Mono<ByteBuf> ERROR_EMPTY_REQUEST =
            Mono.error(new RuntimeException("Empty request"));

    private final WebhookProperties properties;
    private final Consumer<WebhookEvent> webhookEventHandler;

    private final AtomicReference<Mono<DisposableServer>> httpServer = new AtomicReference<>();

    public WebhookServer(WebhookProperties properties, Consumer<WebhookEvent> webhookEventHandler) {
        this.properties = properties;
        this.webhookEventHandler = webhookEventHandler;
    }

    public Mono<Void> start() {
        Mono<DisposableServer> mono = Mono.defer(() -> HttpServer.create()
                .host(properties.getHost())
                .port(properties.getPort())
                .metrics(true,
                        () -> new TurmsMicrometerChannelMetricsRecorder(
                                MetricNameConst.WEBHOOK,
                                "http"))
                .handle((request, response) -> {
                    handleRequest(request, response);
                    return Mono.never();
                })
                .bind());
        if (httpServer.compareAndSet(null, mono)) {
            return mono.then();
        }
        Mono<DisposableServer> serverMono = httpServer.get();
        if (serverMono == null) {
            return start();
        }
        return serverMono.then();
    }

    public Mono<Void> stop() {
        Mono<DisposableServer> serverMono = httpServer.get();
        if (serverMono == null) {
            return Mono.empty();
        }
        return serverMono.flatMap(server -> {
            if (server == null) {
                return Mono.empty();
            }
            server.dispose();
            return server.onDispose();
        });
    }

    private void handleRequest(HttpServerRequest request, HttpServerResponse response) {
        request.receive()
                .aggregate()
                .switchIfEmpty(ERROR_EMPTY_REQUEST)
                .map(buffer -> {
                    WebhookEvent.Builder builder = WebhookEvent.newBuilder();
                    try (InputStreamReader reader =
                            new InputStreamReader(new ByteBufInputStream(buffer))) {
                        PARSER.merge(reader, builder);
                    } catch (IOException e) {
                        throw new InputOutputException("Failed to parse the webhook event", e);
                    }
                    return builder.build();
                })
                .subscribe(event -> {
                    webhookEventHandler.accept(event);
                    response.status(200);
                    response.send()
                            .subscribe(null,
                                    t -> LOGGER.error("Failed to send the webhook event response",
                                            t));
                }, t -> {
                    LOGGER.error("Caught an error while processing the webhook event", t);
                    response.status(500);
                    response.send()
                            .subscribe(null,
                                    throwable -> LOGGER.error(
                                            "Failed to send the webhook event response",
                                            throwable));
                });
    }

}