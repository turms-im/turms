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

package im.turms.plugin.push.core.smtp;

import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import javax.annotation.Nullable;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import jakarta.validation.constraints.NotNull;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.internal.shaded.org.jctools.queues.MpscUnboundedArrayQueue;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.NettyInbound;
import reactor.netty.tcp.TcpClient;

import im.turms.plugin.push.core.smtp.codec.MailEncoder;
import im.turms.plugin.push.core.smtp.codec.Utf8SmtpRequestEncoder;
import im.turms.plugin.push.core.smtp.codec.Utf8SmtpResponseDecoder;
import im.turms.plugin.push.core.smtp.exception.SmtpResponseException;
import im.turms.plugin.push.core.smtp.model.EhloResponse;
import im.turms.plugin.push.core.smtp.model.MailAddress;
import im.turms.plugin.push.core.smtp.model.MailMessage;
import im.turms.plugin.push.core.smtp.model.SmtpRequest;
import im.turms.plugin.push.core.smtp.model.SmtpResponse;
import im.turms.plugin.push.core.smtp.model.SmtpResponseGroup;
import im.turms.server.common.infra.codec.Base64Util;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
public class SmtpSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpSession.class);
    private static final AtomicIntegerFieldUpdater<SmtpSession> REQUIRES_RSET =
            AtomicIntegerFieldUpdater.newUpdater(SmtpSession.class, "requiresRset");

    private static final String AUTH_PLAIN_MECHANISM = "PLAIN";
    private static final String AUTH_XOAUTH2_MECHANISM = "XOAUTH2";

    private final String id;
    private final Connection connection;
    private final MpscUnboundedArrayQueue<ResponseCollector<?>> responseCollectorQueue;
    private EhloResponse ehloResponse;
    @Nullable
    private ResponseCollector<?> currentResponseCollector;

    private volatile int requiresRset;

    public SmtpSession(
            Connection connection,
            MpscUnboundedArrayQueue<ResponseCollector<?>> responseCollectorQueue) {
        id = connection.channel()
                .id()
                .asLongText();
        this.connection = connection;
        this.responseCollectorQueue = responseCollectorQueue;
    }

    public static Mono<SmtpSession> connect(
            String ehloHostname,
            String username,
            String password,
            String accessToken,
            boolean startTls,
            InetSocketAddress remoteAddress,
            Duration connectTimeout) {
        Sinks.One<SmtpSession> connectSink = Sinks.one();
        Mono<? extends Connection> connect = TcpClient.newConnection()
                // TODO: add metrics
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) connectTimeout.toMillis())
                .remoteAddress(() -> remoteAddress)
                .doOnChannelInit((connectionObserver, channel, address) -> channel.pipeline()
                        .addLast(new Utf8SmtpRequestEncoder(),
                                new Utf8SmtpResponseDecoder(),
                                new ChunkedWriteHandler()))
                .handle((in, out) -> onConnected(ehloHostname,
                        username,
                        password,
                        accessToken,
                        startTls,
                        connectSink,
                        in))
                .connect()
                .doOnError(connectSink::tryEmitError);
        return connect.then(connectSink.asMono());
    }

    @NotNull
    private static Mono<Void> onConnected(
            String ehloHostname,
            String username,
            String password,
            String accessToken,
            boolean startTls,
            Sinks.One<SmtpSession> connectSink,
            NettyInbound in) {
        // A channel/connection will only work on one thread,
        // so a single producer is enough.
        MpscUnboundedArrayQueue<ResponseCollector<?>> responseCollectorQueue =
                new MpscUnboundedArrayQueue<>(64);
        Connection connection = (Connection) in;
        SmtpSession session = new SmtpSession(connection, responseCollectorQueue);

        SmtpResponseCollector serverReady = ResponseCollector.one(220);
        responseCollectorQueue.add(serverReady);
        serverReady.getSink()
                .asMono()
                .subscribe(null, t -> {
                    if (t instanceof SmtpResponseException) {
                        connection.dispose();
                    }
                });

        in.receiveObject()
                .cast(SmtpResponse.class)
                .subscribe(session::handleResponse,
                        t -> LOGGER.error("Caught an error while receiving responses", t));
        LOGGER.info("Trying to establish the SMTP session: "
                + session.id);
        session.establish(ehloHostname, username, password, accessToken, startTls)
                .subscribe(unused -> {
                    LOGGER.info("Established the SMTP session: "
                            + session.id);
                    connectSink.tryEmitValue(session);
                }, t -> {
                    LOGGER.error("Failed to establish the SMTP session: "
                            + session.id);
                    session.onClose(connectSink, t);
                });
        return connection.onDispose()
                .doOnSuccess(unused -> connectSink.tryEmitEmpty())
                .doOnError(t -> session.onClose(connectSink, t));
    }

    private void onClose(Sinks.One<SmtpSession> connectSink, Throwable t) {
        connectSink.tryEmitError(t);
        responseCollectorQueue.drain(collector -> collector.emitError(t));
        ResponseCollector<?> collector = currentResponseCollector;
        if (collector != null) {
            collector.emitError(t);
        }
    }

    private Mono<Void> establish(
            String hostname,
            String username,
            String password,
            String accessToken,
            boolean startTls) {
        // 1. Ehlo
        return sendEhlo(hostname).flatMap(response -> {
            if (!response.isPipeliningSupported()) {
                return Mono
                        .error(new RuntimeException("The SMTP server does not support PIPELINING"));
            }
            if (!response.is8BitMimeSupported()) {
                return Mono
                        .error(new RuntimeException("The SMTP server does not support 8BITMIME"));
            }
            ehloResponse = response;
            // 2. StartTls
            if (!startTls) {
                return Mono.just(response);
            }
            if (response.isStartTlsSupported()) {
                return startTls(hostname).thenReturn(response);
            } else {
                return Mono
                        .error(new RuntimeException("The SMTP server does not support STARTTLS"));
            }
        })
                .flatMap(response -> {
                    // 3. Auth
                    if (response.isAuthPlainSupported()) {
                        return authPlain(username, password);
                    } else if (response.isAuthXoauth2Supported()) {
                        return authXoauth2(username, accessToken);
                    } else {
                        return Mono.error(new RuntimeException(
                                "The SMTP server does not support the auths: PLAIN, XOAUTH2"));
                    }
                })
                .doOnError(t -> {
                    LOGGER.error("Caught an error while establishing. Closing", t);
                    close();
                })
                .then();
    }

    private void handleResponse(SmtpResponse response) {
        int code = response.code();
        // Goodbye
        if (code == 221) {
            close();
            return;
        }
        ResponseCollector<?> currentCollector = currentResponseCollector;
        if (currentCollector == null) {
            currentResponseCollector = currentCollector = responseCollectorQueue.relaxedPoll();
            if (currentCollector == null) {
                LOGGER.warn(
                        "Received a response that does not respond to a request. Closing the session: "
                                + id);
                sendQuitAndClose().subscribe();
                return;
            }
        }
        if (currentCollector.addResponse(response)) {
            currentResponseCollector = null;
        }
    }

    public void close() {
        if (!connection.isDisposed()) {
            connection.dispose();
            LOGGER.info("The session ({}) has been closed", id);
        }
    }

    private Mono<Void> write(Object object) {
        return connection.outbound()
                .sendObject(object)
                .then()
                .doOnError(t -> {
                    LOGGER.error("Caught an error while writing buffer. Closing the session: "
                            + id, t);
                    close();
                });
    }

    private Mono<SmtpResponse> send(SmtpRequest request, int expectedCode) {
        return Mono.defer(() -> {
            if (connection.isDisposed()) {
                return Mono.error(new IllegalStateException("The session has been closed"));
            }
            SmtpResponseCollector collector = ResponseCollector.one(expectedCode);
            responseCollectorQueue.add(collector);
            Mono<SmtpResponse> mono = collector.getSink()
                    .asMono();
            return write(request).then(mono);
        });
    }

    private Mono<SmtpResponse> send(List<ByteBuf> buffers) {
        return Mono.defer(() -> {
            if (connection.isDisposed()) {
                return Mono.error(new IllegalStateException("The session has been closed"));
            }
            SmtpResponseCollector collector = ResponseCollector.one();
            responseCollectorQueue.add(collector);
            Mono<Void> send = Mono.empty();
            for (ByteBuf buffer : buffers) {
                send = send.then(Mono.defer(() -> write(buffer)));
            }
            Mono<SmtpResponse> mono = collector.getSink()
                    .asMono();
            return send.then(mono);
        });
    }

    private Mono<SmtpResponseGroup> sendPipelinedRequests(List<SmtpRequest> requests) {
        return Mono.defer(() -> {
            if (connection.isDisposed()) {
                return Mono.error(new IllegalStateException("The session has been closed"));
            }
            int count = requests.size();
            SmtpResponseGroupCollector collector = ResponseCollector.many(count);
            Mono<SmtpResponseGroup> mono = collector.getSink()
                    .asMono();
            responseCollectorQueue.add(collector);
            Mono<Void> send = Mono.empty();
            for (SmtpRequest request : requests) {
                send = send.then(Mono.defer(() -> write(request)));
            }
            return send.then(mono);
        });
    }

    private Mono<Void> sendAndForget(SmtpRequest request) {
        return Mono.defer(() -> {
            if (connection.isDisposed()) {
                return Mono.error(new IllegalStateException("The session has been closed"));
            }
            SmtpResponseCollector collector = ResponseCollector.one();
            responseCollectorQueue.add(collector);
            return write(request);
        });
    }

    public Mono<Void> sendQuitAndClose() {
        return sendAndForget(SmtpRequest.QUIT).doFinally(signalType -> close());
    }

    private Mono<EhloResponse> sendEhlo(String hostname) {
        SmtpRequest request = SmtpRequest.ehlo(hostname);
        return send(request, 250).map(response -> EhloResponse.create(response.details()));
    }

    private Mono<SmtpResponse> startTls(String hostname) {
        return send(SmtpRequest.STARTTLS, 220).flatMap(
                response -> performTlsHandshake().then(Mono.defer(() -> sendEhlo(hostname)))
                        .thenReturn(response));
    }

    private Mono<Void> performTlsHandshake() {
        SSLEngine sslEngine;
        try {
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            sslEngine = SslContextBuilder.forClient()
                    .trustManager(trustManagerFactory)
                    .build()
                    .newEngine(PooledByteBufAllocator.DEFAULT);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Could not create SSLEngine", e));
        }
        return Mono.create(sink -> {
            SslHandler sslHandler;
            try {
                // TODO: configuratable
                sslHandler = new SslHandler(sslEngine);
                connection.channel()
                        .pipeline()
                        .addFirst(sslHandler);
            } catch (Throwable t) {
                sink.error(t);
                return;
            }
            sslHandler.handshakeFuture()
                    .addListener(nettyFuture -> {
                        if (nettyFuture.isSuccess()) {
                            sink.success();
                        } else {
                            sink.error(nettyFuture.cause());
                            close();
                        }
                    });
        });
    }

    private Mono<SmtpResponse> authPlain(String username, String password) {
        String parameter = username
                + "\u0000"
                + username
                + "\u0000"
                + password;
        SmtpRequest request = SmtpRequest
                .auth(List.of(AUTH_PLAIN_MECHANISM, Base64Util.encodeToString(parameter)));
        return send(request, 235);
    }

    private Mono<SmtpResponse> authXoauth2(String username, String accessToken) {
        String parameter = "user="
                + username
                + "\u0001auth=Bearer "
                + accessToken
                + "\u0001\u0001";
        SmtpRequest request = SmtpRequest
                .auth(List.of(AUTH_XOAUTH2_MECHANISM, Base64Util.encodeToString(parameter)));
        return send(request, 235);
    }

    public Mono<SmtpResponseGroup> sendMail(MailMessage mail) {
        ByteBuf messageContent;
        try {
            messageContent = MailEncoder.encode(mail, ehloResponse.maxAllowedMessageSize());
        } catch (Exception e) {
            return Mono.error(e);
        }
        String fromAddress = mail.fromAddress()
                .address();
        List<String> toAddresses =
                CollectionUtil.transformAsList(mail.toAddresses(), MailAddress::address);
        return sendMail(fromAddress, toAddresses, messageContent);
    }

    private Mono<SmtpResponseGroup> sendMail(
            String fromAddress,
            Collection<String> recipients,
            ByteBuf messageContent) {
        SmtpRequest mail = SmtpRequest.mailWith8BitMime(fromAddress);
        Mono<SmtpResponseGroup> sendPipelinedRequests;
        if (requiresRset == 1) {
            List<SmtpRequest> requests = new ArrayList<>(3 + recipients.size());
            requests.add(SmtpRequest.RSET);
            requests.add(mail);
            requests.addAll(SmtpRequest.rcptRequests(recipients));
            requests.add(SmtpRequest.DATA);
            // Send RSET to isolate pipelines
            sendPipelinedRequests = sendPipelinedRequests(requests);
        } else if (REQUIRES_RSET.compareAndSet(this, 0, 1)) {
            List<SmtpRequest> requests = new ArrayList<>(2 + recipients.size());
            requests.add(mail);
            requests.addAll(SmtpRequest.rcptRequests(recipients));
            requests.add(SmtpRequest.DATA);
            sendPipelinedRequests = sendPipelinedRequests(requests);
        } else {
            return sendMail(fromAddress, recipients, messageContent);
        }
        return sendPipelinedRequests.flatMap(group -> {
            if (group.containsError()) {
                return Mono.just(group);
            }
            return send(List.of(messageContent)).map(response -> new SmtpResponseGroup(
                    CollectionUtil.add(group.responses(), response)));
        });
    }

}