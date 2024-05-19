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

package im.turms.plugin.livekit.core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.concurrent.FastThreadLocal;
import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import im.turms.plugin.livekit.core.auth.AccessTokenFactory;
import im.turms.plugin.livekit.core.auth.VideoGrant;
import im.turms.plugin.livekit.core.metrics.MetricNameConst;
import im.turms.plugin.livekit.core.proto.webhook.WebhookEvent;
import im.turms.plugin.livekit.core.service.RoomService;
import im.turms.plugin.livekit.core.webhook.WebhookServer;
import im.turms.plugin.livekit.property.LiveKitProperties;
import im.turms.server.common.access.admin.web.HttpUtil;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.infra.security.jwt.algorithm.HmacAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithmDefinition;

/**
 * @author James Chen
 */
public class LiveKitClient {

    private final String apiKey;
    private final byte[] secretKey;
    private final HmacAlgorithm jwtAlgorithm;
    private final int accessTokenTtlMillis;

    private static final FastThreadLocal<Map<String, Message.Builder>> NAME_TO_BUILDER =
            new FastThreadLocal<>() {
                @Override
                protected Map<String, Message.Builder> initialValue() {
                    return new HashMap<>(16);
                }
            };

    private final HttpClient httpClient;
    @Getter
    private final RoomService roomService;
    private final WebhookServer webhookServer;

    public LiveKitClient(LiveKitProperties properties, Consumer<WebhookEvent> webhookEventHandler) {
        httpClient = HttpClient.create()
                .baseUrl(properties.getUrl())
                .metrics(true,
                        () -> new TurmsMicrometerChannelMetricsRecorder(
                                MetricNameConst.CLIENT,
                                "http"));
        String apiKey = properties.getApiKey();
        String secretKey = properties.getSecretKey();
        if (StringUtil.isBlank(apiKey)) {
            throw new IllegalArgumentException("apiKey cannot be blank");
        }
        if (StringUtil.isBlank(secretKey)) {
            throw new IllegalArgumentException("secretKey cannot be blank");
        }
        this.apiKey = apiKey;
        this.secretKey = secretKey.getBytes(StandardCharsets.UTF_8);
        jwtAlgorithm = new HmacAlgorithm(JwtAlgorithmDefinition.HS256, this.secretKey);
        int ttlSeconds = properties.getAccessToken()
                .getTtlSeconds();
        accessTokenTtlMillis = ttlSeconds * 1000;
        roomService = new RoomService(this);
        webhookServer = new WebhookServer(properties.getWebhook(), webhookEventHandler);
    }

    public Mono<Void> start() {
        return webhookServer.start();
    }

    public Mono<Void> stop() {
        return webhookServer.stop();
    }

    public String generateRoomAccessToken(String roomName) {
        Map<String, Object> videoGrants = Map.of(VideoGrant.RoomName.KEY,
                roomName,
                VideoGrant.RoomJoin.KEY,
                true,
                VideoGrant.CanPublish.KEY,
                true,
                VideoGrant.CanSubscribe.KEY,
                true);
        return AccessTokenFactory.createJwt(jwtAlgorithm,
                apiKey,
                videoGrants,
                System.currentTimeMillis() + accessTokenTtlMillis);
    }

    public <T extends GeneratedMessage> Mono<T> sendHttpRequest(
            String path,
            List<VideoGrant> videoGrants,
            T responseTemplate,
            GeneratedMessage request) {
        return send(path, videoGrants, request).responseSingle((response, bufferMono) -> {
            if (response.status()
                    .code() != 200) {
                return Mono.error(new RuntimeException(
                        "Failed to send http request: "
                                + response.status()));
            }
            return bufferMono.map(buffer -> {
                String name = responseTemplate.getDescriptorForType()
                        .getFullName();
                Message.Builder builder = NAME_TO_BUILDER.get()
                        .computeIfAbsent(name, key -> responseTemplate.newBuilderForType());
                builder.clear();
                try {
                    builder.mergeDelimitedFrom(new ByteBufInputStream(buffer));
                } catch (IOException e) {
                    throw new RuntimeException("Failed to deserialize the response", e);
                }
                return (T) builder.build();
            });
        });
    }

    public Mono<Void> sendHttpRequest(
            String path,
            List<VideoGrant> videoGrants,
            GeneratedMessage request) {
        return send(path, videoGrants, request).response()
                .flatMap(response -> HttpUtil.isSuccess(response.status())
                        ? Mono.empty()
                        : Mono.error(new RuntimeException(
                                "The HTTP response is not successful: "
                                        + response.status())));
    }

    private HttpClient.ResponseReceiver<?> send(
            String path,
            List<VideoGrant> videoGrants,
            GeneratedMessage request) {
        String auth = generateAuthHeader(videoGrants);
        return httpClient.headers(entries -> entries.add("Content-Type", "application/protobuf")
                .add("Authorization", auth))
                .request(HttpMethod.POST)
                .uri(path)
                .send(Mono.fromCallable(() -> {
                    // Don't use "request.getSerializedSize()" as
                    // using a constant size is efficient and enough for most cases.
                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(64);
                    try (ByteBufOutputStream outputStream = new ByteBufOutputStream(buffer)) {
                        request.writeDelimitedTo(outputStream);
                    } catch (Exception e) {
                        buffer.release();
                        throw new InputOutputException(
                                "Failed to serialize the request: "
                                        + request,
                                e);
                    }
                    return buffer;
                }));
    }

    // TODO: improve performance.
    private String generateAuthHeader(List<VideoGrant> videoGrants) {
        Map<String, Object> map = CollectionUtil.newMapWithExpectedSize(videoGrants.size());
        for (VideoGrant videoGrant : videoGrants) {
            map.put(videoGrant.getKey(), videoGrant.getValue());
        }
        String jwt = AccessTokenFactory.createJwt(jwtAlgorithm,
                apiKey,
                map,
                System.currentTimeMillis() + 6 * 60 * 60 * 1000);
        return "Bearer "
                + jwt;
    }

}