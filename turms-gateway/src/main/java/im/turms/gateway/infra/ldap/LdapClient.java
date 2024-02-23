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

package im.turms.gateway.infra.ldap;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import jakarta.annotation.Nullable;

import org.jctools.maps.NonBlockingHashMapLong;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.publisher.Sinks;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.channel.MicrometerChannelMetricsRecorder;
import reactor.netty.tcp.TcpClient;

import im.turms.gateway.infra.ldap.element.common.Attribute;
import im.turms.gateway.infra.ldap.element.common.LdapMessage;
import im.turms.gateway.infra.ldap.element.common.ResultCode;
import im.turms.gateway.infra.ldap.element.common.ResultCodeConst;
import im.turms.gateway.infra.ldap.element.common.control.Control;
import im.turms.gateway.infra.ldap.element.common.control.ControlOidConst;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;
import im.turms.gateway.infra.ldap.element.operation.bind.BindRequest;
import im.turms.gateway.infra.ldap.element.operation.bind.BindResponse;
import im.turms.gateway.infra.ldap.element.operation.modify.ModifyOperationChange;
import im.turms.gateway.infra.ldap.element.operation.modify.ModifyOperationType;
import im.turms.gateway.infra.ldap.element.operation.modify.ModifyRequest;
import im.turms.gateway.infra.ldap.element.operation.modify.ModifyResponse;
import im.turms.gateway.infra.ldap.element.operation.search.DerefAliases;
import im.turms.gateway.infra.ldap.element.operation.search.Scope;
import im.turms.gateway.infra.ldap.element.operation.search.SearchRequest;
import im.turms.gateway.infra.ldap.element.operation.search.SearchResult;
import im.turms.gateway.infra.ldap.handler.LdapMessageDecoder;
import im.turms.gateway.infra.ldap.handler.LdapMessageEncoder;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.net.SslContextSpecType;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.env.common.SslProperties;

import static im.turms.gateway.infra.metrics.MetricNameConst.LDAP_CLIENT;

/**
 * @author James Chen
 */
public class LdapClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapClient.class);

    private static final AtomicIntegerFieldUpdater<LdapClient> MESSAGE_ID_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(LdapClient.class, "messageId");

    private static final AtomicReferenceFieldUpdater<LdapClient, Mono> CONNECTION_MONO_UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(LdapClient.class, Mono.class, "connectionMono");

    private static final List<Control> REQUEST_CONTROLS_FAST_BIND =
            List.of(new Control(ControlOidConst.FAST_BIND, false));

    private final String host;
    private final int port;
    @Nullable
    private final SslProperties sslProperties;

    // RFC 4511
    // C.1.5. Section 4.1.1.1 (Message ID)
    // - Required that the messageID of requests MUST be non-zero as the
    // zero is reserved for Notice of Disconnection.
    private volatile int messageId = 1;
    private final NonBlockingHashMapLong<PendingLdapRequestContext<? extends ProtocolOperation<?>>> messageIdToRequestContext =
            new NonBlockingHashMapLong<>(128);

    private ChannelOperations<?, ?> connection;
    /**
     * Connecting or connected connection.
     */
    private volatile Mono<ChannelOperations<?, ?>> connectionMono;

    public LdapClient(String host, int port, @Nullable SslProperties sslProperties) {
        this.host = host;
        this.port = port;
        this.sslProperties = sslProperties;
    }

    public boolean isConnected() {
        return connection != null && !connection.isDisposed();
    }

    public Mono<ChannelOperations<?, ?>> connect() {
        Mono<ChannelOperations<?, ?>> mono = CONNECTION_MONO_UPDATER.get(this);
        if (mono != null) {
            return mono;
        }
        Mono<ChannelOperations<?, ?>> connect = Mono.defer(() -> {
            TcpClient client = TcpClient.newConnection()
                    .host(host)
                    .port(port)
                    .metrics(true, () -> new MicrometerChannelMetricsRecorder(LDAP_CLIENT, "ldap"));
            if (sslProperties != null && sslProperties.isEnabled()) {
                client = client
                        .secure(sslContextSpec -> SslUtil.configureSslContextSpec(sslContextSpec,
                                SslContextSpecType.TCP,
                                sslProperties,
                                false));
            }
            return client.connect()
                    .map(conn -> {
                        ChannelOperations<?, ?> operations = (ChannelOperations<?, ?>) conn;
                        operations.addHandlerLast("ldapMessageDecoder",
                                new LdapMessageDecoder(messageIdToRequestContext));
                        operations.addHandlerFirst("ldapMessageEncoder", new LdapMessageEncoder());

                        operations.receiveObject()
                                .subscribe(null, t -> {
                                    operations.dispose();
                                    LOGGER.error("Closed the LDAP client because of the error", t);
                                });
                        connection = operations;
                        return operations;
                    });
        });
        if (CONNECTION_MONO_UPDATER.compareAndSet(this, null, connect)) {
            return connect;
        }
        return CONNECTION_MONO_UPDATER.get(this);
    }

    private <T extends ProtocolOperation<T>, R extends ProtocolOperation<R>> Mono<R> sendRequest(
            T request,
            R responseDecoder) {
        return sendRequest(request, Collections.emptyList(), responseDecoder);
    }

    private <T extends ProtocolOperation<T>, R extends ProtocolOperation<R>> Mono<R> sendRequest(
            T request,
            List<Control> requestControls,
            R responseDecoder) {
        Mono<ChannelOperations<?, ?>> connectMono = connectionMono;
        if (connectMono == null) {
            connectionMono = connectMono = connect();
        }
        int msgId = MESSAGE_ID_UPDATER.getAndIncrement(this);
        Sinks.One<R> sink = Sinks.one();
        messageIdToRequestContext.put(msgId,
                new PendingLdapRequestContext<>(sink, responseDecoder));
        return connectMono.flatMap(c -> c.outbound()
                .sendObject(new LdapMessage<>(msgId, request, requestControls))
                .then()
                .doOnError(throwable -> {
                    PendingLdapRequestContext<?> requestContext =
                            messageIdToRequestContext.remove(msgId);
                    if (requestContext != null) {
                        requestContext.getSink()
                                .tryEmitError(throwable);
                    }
                })
                .doFinally(signalType -> {
                    if (signalType == SignalType.CANCEL) {
                        PendingLdapRequestContext<?> requestContext =
                                messageIdToRequestContext.remove(msgId);
                        if (requestContext != null) {
                            requestContext.getSink()
                                    .tryEmitError(new RuntimeException("Cancelled"));
                        }
                    }
                }))
                .then(sink.asMono());
    }

    public Mono<Boolean> bind(boolean useFastBind, String dn, String password) {
        Mono<BindResponse> bind = sendRequest(new BindRequest(dn, password),
                useFastBind
                        ? REQUEST_CONTROLS_FAST_BIND
                        : Collections.emptyList(),
                BindResponse.DECODER);
        return bind.map(response -> {
            if (response.isSuccess()) {
                return true;
            }
            int resultCode = response.getResultCode();
            if (resultCode == ResultCodeConst.INVALID_CREDENTIALS) {
                return false;
            }
            throw new LdapException(resultCode, response.getDiagnosticMessage());
        });
    }

    public Mono<SearchResult> search(
            String baseDn,
            Scope scope,
            DerefAliases derefAliases,
            int sizeLimit,
            int timeLimit,
            boolean typeOnly,
            List<String> attributes,
            String filter) {
        return sendRequest(
                new SearchRequest(
                        baseDn,
                        scope,
                        derefAliases,
                        sizeLimit,
                        timeLimit,
                        typeOnly,
                        attributes,
                        filter),
                SearchResult.DECODER);
    }

    public Mono<ModifyResponse> modify(String dn, List<ModifyOperationChange> changes) {
        if (changes.isEmpty()) {
            return Mono.empty();
        }
        for (ModifyOperationChange change : changes) {
            Attribute attribute = change.getAttribute();
            if (change.getType() == ModifyOperationType.ADD && attribute.isEmpty()) {
                throw new LdapException(
                        ResultCode.INVALID_ATTRIBUTE_SYNTAX,
                        "Add operation for \""
                                + attribute.getType()
                                + "\" should have at least one value");
            }
        }
        return sendRequest(new ModifyRequest(dn, changes), ModifyResponse.DECODER);
    }
}