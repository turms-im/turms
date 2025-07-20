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

package im.turms.gateway.access.client.websocket;

import java.net.InetSocketAddress;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.ProxyProtocolSupportType;
import reactor.netty.http.server.WebsocketServerSpec;

import im.turms.gateway.access.client.common.channel.ServiceAvailabilityHandler;
import im.turms.gateway.access.client.common.connection.ConnectionListener;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.metrics.MetricNameConst;
import im.turms.gateway.infra.thread.ThreadNameConst;
import im.turms.server.common.access.common.LoopResourcesFactory;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.infra.net.BindException;
import im.turms.server.common.infra.net.SslContextSpecType;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.constant.RemoteAddressSourceHttpHeaderMode;
import im.turms.server.common.infra.property.constant.RemoteAddressSourceProxyProtocolMode;
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.env.gateway.network.WebSocketProperties;
import im.turms.server.common.infra.property.env.gateway.network.WebSocketRemoteAddressSourceProperties;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.SO_LINGER;
import static io.netty.channel.ChannelOption.SO_REUSEADDR;
import static io.netty.channel.ChannelOption.TCP_NODELAY;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_MAX_AGE;
import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.SEC_WEBSOCKET_KEY;
import static io.netty.handler.codec.http.HttpHeaderNames.UPGRADE;

import static im.turms.server.common.access.admin.web.HttpUtil.isPreFlightRequest;

/**
 * @author James Chen
 */
public final class WebSocketServerFactory {

    private WebSocketServerFactory() {
    }

    public static DisposableServer create(
            WebSocketProperties webSocketProperties,
            BlocklistService blocklistService,
            ServerStatusManager serverStatusManager,
            SessionService sessionService,
            ConnectionListener connectionListener,
            int maxFramePayloadLength) {
        ServiceAvailabilityHandler serviceAvailabilityHandler = new ServiceAvailabilityHandler(
                blocklistService,
                serverStatusManager,
                sessionService);
        WebsocketServerSpec serverSpec = WebsocketServerSpec.builder()
                .maxFramePayloadLength(maxFramePayloadLength)
                .build();
        // Don't set SO_SNDBUF and SO_RCVBUF because of
        // the reasons mentioned in https://developer.aliyun.com/article/724580
        String host = webSocketProperties.getHost();
        int port = webSocketProperties.getPort();
        WebSocketRemoteAddressSourceProperties remoteAddressSourceProperties =
                webSocketProperties.getRemoteAddressSource();
        RemoteAddressSourceProxyProtocolMode proxyProtocolMode =
                remoteAddressSourceProperties.getProxyProtocolMode();
        ProxyProtocolSupportType proxyProtocolSupportType = switch (proxyProtocolMode) {
            case REQUIRED -> ProxyProtocolSupportType.ON;
            case OPTIONAL -> ProxyProtocolSupportType.AUTO;
            case DISABLED -> ProxyProtocolSupportType.OFF;
        };
        HttpServer server = HttpServer.create()
                .host(host)
                .port(port)
                .option(CONNECT_TIMEOUT_MILLIS, webSocketProperties.getConnectTimeoutMillis())
                .option(SO_REUSEADDR, true)
                .option(SO_BACKLOG, webSocketProperties.getBacklog())
                .childOption(SO_REUSEADDR, true)
                .childOption(SO_LINGER, 0)
                .childOption(TCP_NODELAY, true)
                .proxyProtocol(proxyProtocolSupportType)
                .runOn(LoopResourcesFactory.createForServer(ThreadNameConst.GATEWAY_WS_PREFIX))
                .metrics(true,
                        () -> new TurmsMicrometerChannelMetricsRecorder(
                                MetricNameConst.TURMS_GATEWAY_SERVER_WEBSOCKET))
                .handle((request, response) -> handleHttpRequest(blocklistService,
                        request,
                        response,
                        connectionListener,
                        serverSpec))
                .doOnChannelInit((connectionObserver, channel, remoteAddress) -> channel.pipeline()
                        .addFirst("serviceAvailabilityHandler", serviceAvailabilityHandler));
        RemoteAddressSourceHttpHeaderMode remoteAddressSourceHttpHeaderMode =
                remoteAddressSourceProperties.getHttpHeaderMode();
        if (RemoteAddressSourceHttpHeaderMode.REQUIRED == remoteAddressSourceHttpHeaderMode) {
            server = server.forwarded(new HttpForwardedHeaderHandler(true));
        } else if (RemoteAddressSourceHttpHeaderMode.OPTIONAL == remoteAddressSourceHttpHeaderMode) {
            server = server.forwarded(new HttpForwardedHeaderHandler(false));
        }
        SslProperties ssl = webSocketProperties.getSsl();
        if (ssl.isEnabled()) {
            server = server.secure(
                    spec -> SslUtil
                            .configureSslContextSpec(spec, SslContextSpecType.HTTP11, ssl, true),
                    true);
        }
        try {
            return server.bind()
                    .block();
        } catch (Exception e) {
            String message = "Failed to bind the WebSocket server on: "
                    + host
                    + ":"
                    + port;
            throw new BindException(message, e);
        }
    }

    /**
     * @implNote 1. Return MonoNever to keep the connection alive
     *           <p>
     *           2. Return MonoEmpty to close the connection.
     */
    private static Publisher<Void> handleHttpRequest(
            BlocklistService blocklistService,
            HttpServerRequest request,
            HttpServerResponse response,
            ConnectionListener connectionListener,
            WebsocketServerSpec serverSpec) {
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
            return response.status(errorStatus)
                    .send()
                    // Close the TCP connection
                    .then();
        }
        // 3. Get the real client address
        InetSocketAddress remoteAddress = request.remoteAddress();

        // 4. Check if the client address has been blocked
        if (remoteAddress != null
                && blocklistService.isIpBlocked(remoteAddress.getAddress()
                        .getAddress())) {
            return Mono.empty();
        }

        // 5. Upgrade to WebSocket
        // reactor.netty.http.server.HttpServer.HttpServerHandle.onStateChange
        int maxFramePayloadLength = serverSpec.maxFramePayloadLength();
        return response.sendWebsocket((in, out) -> {
            Flux<ByteBuf> inbound = in.aggregateFrames(maxFramePayloadLength)
                    .receiveFrames()
                    // Note that:
                    // 1. PingWebSocketFrame will be handled by Netty itself
                    // 2. The flatMap is called by FluxReceive, which will release buffer after
                    // "onNext" returns.
                    .flatMap(frame -> frame instanceof BinaryWebSocketFrame
                            ? Mono.just(frame.content())
                            : Mono.empty());
            Mono<Void> onClose = in.receiveCloseStatus()
                    .then();
            // BinaryWebSocketFrame will be created by
            // reactor.netty.http.server.WebsocketServerOperations.send
            Connection connection = (Connection) in;
            InetSocketAddress remoteAddr = remoteAddress == null
                    ? (InetSocketAddress) connection.channel()
                            .remoteAddress()
                    : remoteAddress;
            return connectionListener.onAdded(connection, remoteAddr, inbound, out, onClose);
        }, serverSpec);
    }

    @Nullable
    private static HttpResponseStatus validateHandshakeRequest(HttpServerRequest request) {
        HttpMethod method = request.method();
        HttpHeaders headers = request.requestHeaders();
        if (HttpMethod.GET != method) {
            return new HttpResponseStatus(
                    HttpResponseStatus.METHOD_NOT_ALLOWED.code(),
                    "Request method '"
                            + method
                            + "' not supported");
        }
        if (!headers.containsValue(UPGRADE, "websocket", true)) {
            return new HttpResponseStatus(
                    HttpResponseStatus.BAD_REQUEST.code(),
                    "Invalid 'Upgrade' header: "
                            + headers);
        }
        if (!headers.containsValue(CONNECTION, "upgrade", true)) {
            return new HttpResponseStatus(
                    HttpResponseStatus.BAD_REQUEST.code(),
                    "Invalid 'Connection' header: "
                            + headers);
        }
        String key = headers.get(SEC_WEBSOCKET_KEY);
        if (key == null) {
            return new HttpResponseStatus(
                    HttpResponseStatus.BAD_REQUEST.code(),
                    "Missing \"Sec-WebSocket-Key\" header");
        }
        return null;
    }

}