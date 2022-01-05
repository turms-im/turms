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

package im.turms.gateway.access.websocket.factory;

import im.turms.gateway.access.common.function.ConnectionHandler;
import im.turms.gateway.access.common.handler.ServiceAvailabilityHandler;
import im.turms.gateway.constant.MetricsConstant;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.server.common.access.common.resource.LoopResourcesFactory;
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.property.env.gateway.WebSocketProperties;
import im.turms.server.common.service.blocklist.BlocklistService;
import im.turms.server.common.util.SslUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.reactivestreams.Publisher;
import org.springframework.boot.web.server.Ssl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.WebsocketServerSpec;

import java.util.List;
import java.util.function.BiFunction;

import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_MAX_AGE;
import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD;
import static com.google.common.net.HttpHeaders.CONNECTION;
import static com.google.common.net.HttpHeaders.ORIGIN;
import static com.google.common.net.HttpHeaders.SEC_WEBSOCKET_KEY;
import static com.google.common.net.HttpHeaders.UPGRADE;
import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.SO_LINGER;
import static io.netty.channel.ChannelOption.SO_REUSEADDR;
import static io.netty.channel.ChannelOption.TCP_NODELAY;
import static io.netty.handler.codec.http.HttpMethod.OPTIONS;

/**
 * @author James Chen
 * @implNote We don't use spring-reactive-websocket because it always makes
 * the code abstract and the workflow really complex in non-blocking programming.
 * People will realize that it's a disaster for a large application to debug
 * and troubleshoot with spring-reactive-websocket.
 * <p>
 * On the other hand, we can just use tens of code to do what
 * spring-reactive-websocket can do for us with really clear code
 * @see WebSocketFactory#getHttpRequestHandler
 */
public final class WebSocketFactory {

    private WebSocketFactory() {
    }

    public static DisposableServer create(WebSocketProperties webSocketProperties,
                                          BlocklistService blocklistService,
                                          ServerStatusManager serverStatusManager,
                                          SessionService sessionService,
                                          ConnectionHandler handler,
                                          int maxFramePayloadLength) {
        ServiceAvailabilityHandler serviceAvailabilityHandler = new ServiceAvailabilityHandler(blocklistService, serverStatusManager, sessionService);
        WebsocketServerSpec serverSpec = WebsocketServerSpec.builder()
                .maxFramePayloadLength(maxFramePayloadLength)
                .build();
        // Don't set SO_SNDBUF and SO_RCVBUF because of
        // the reasons mentioned in https://developer.aliyun.com/article/724580
        HttpServer server = HttpServer.create()
                .host(webSocketProperties.getHost())
                .port(webSocketProperties.getPort())
                .option(CONNECT_TIMEOUT_MILLIS, webSocketProperties.getConnectionTimeout())
                .option(SO_REUSEADDR, true)
                .option(SO_BACKLOG, webSocketProperties.getBacklog())
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, true)
                .runOn(LoopResourcesFactory.createForServer("gateway-ws"))
                .metrics(true, () -> new TurmsMicrometerChannelMetricsRecorder(MetricsConstant.CLIENT_NETWORK, "websocket"))
                .handle(getHttpRequestHandler(handler, serverSpec))
                .doOnChannelInit((connectionObserver, channel, remoteAddress) ->
                        channel.pipeline().addFirst("serviceAvailabilityHandler", serviceAvailabilityHandler));
        Ssl ssl = webSocketProperties.getSsl();
        if (ssl.isEnabled()) {
            server.secure(spec -> SslUtil.configureSslContextSpec(spec, ssl, true), true);
        }
        try {
            return server
                    .bind()
                    .block();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to bind the WebSocket server", e);
        }
    }

    /**
     * @see ReactorNettyRequestUpgradeStrategy#upgrade
     */
    private static BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> getHttpRequestHandler(
            ConnectionHandler handler,
            WebsocketServerSpec serverSpec) {
        // Return MonoNever to keep the connection alive
        // Return MonoEmpty to close the connection
        return (request, response) -> {
            // 1. Always respond with OK to CORS preflight requests
            if (isPreFlightRequest(request)) {
                return response.status(HttpResponseStatus.OK)
                        .addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                        .addHeader(ACCESS_CONTROL_ALLOW_METHODS, "*")
                        .addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "*")
                        .addHeader(ACCESS_CONTROL_MAX_AGE, "7200")
                        .send()
                        .then(Mono.never());
            }
            // 2. Validate handshake request
            HttpResponseStatus errorStatus = validateHandshakeRequest(request);
            if (errorStatus != null) {
                return response
                        .status(errorStatus)
                        .send()
                        // Close the TCP connection
                        .then();
            }
            // 3. Upgrade to WebSocket
            // reactor.netty.http.server.HttpServer.HttpServerHandle.onStateChange
            int maxFramePayloadLength = serverSpec.maxFramePayloadLength();
            return response.sendWebsocket((in, out) -> {
                Flux<ByteBuf> inbound = in.aggregateFrames(maxFramePayloadLength)
                        .receiveFrames()
                        // Note that:
                        // 1. PingWebSocketFrame will be handled by Netty itself
                        // 2. The flatMap is called by FluxReceive, which will release buffer after "onNext" returns
                        .flatMap(frame -> frame instanceof BinaryWebSocketFrame
                                ? Mono.just(frame.content())
                                : Mono.empty());
                Mono<Void> onClose = in.receiveCloseStatus().then();
                // BinaryWebSocketFrame will be created by reactor.netty.http.server.WebsocketServerOperations.send
                return handler.handle((Connection) in, true, inbound, out, onClose);
            }, serverSpec);
        };
    }

    private static boolean isPreFlightRequest(HttpServerRequest request) {
        if (!OPTIONS.equals(request.method())) {
            return false;
        }
        HttpHeaders headers = request.requestHeaders();
        return headers.contains(ORIGIN) && headers.contains(ACCESS_CONTROL_REQUEST_METHOD);
    }

    /**
     * @see HandshakeWebSocketService#handleRequest
     * @see org.springframework.web.server.handler.ExceptionHandlingWebHandler
     */
    private static HttpResponseStatus validateHandshakeRequest(HttpServerRequest request) {
        HttpMethod method = request.method();
        HttpHeaders headers = request.requestHeaders();
        if (HttpMethod.GET != method) {
            return new HttpResponseStatus(HttpResponseStatus.METHOD_NOT_ALLOWED.code(), "Request method '" + method + "' not supported");
        }
        if (!"WebSocket".equalsIgnoreCase(headers.get(UPGRADE))) {
            return new HttpResponseStatus(HttpResponseStatus.BAD_REQUEST.code(), "Invalid 'Upgrade' header: " + headers);
        }
        List<String> connectionValue = headers.getAll(CONNECTION);
        if (!connectionValue.contains("Upgrade") && !connectionValue.contains("upgrade")) {
            return new HttpResponseStatus(HttpResponseStatus.BAD_REQUEST.code(), "Invalid 'Connection' header: " + headers);
        }
        String key = headers.get(SEC_WEBSOCKET_KEY);
        if (key == null) {
            return new HttpResponseStatus(HttpResponseStatus.BAD_REQUEST.code(), "Missing \"Sec-WebSocket-Key\" header");
        }
        return null;
    }

}