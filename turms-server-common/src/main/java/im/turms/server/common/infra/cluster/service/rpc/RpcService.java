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

package im.turms.server.common.infra.cluster.service.rpc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.micrometer.core.instrument.Tag;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.channel.ChannelOperations;
import reactor.util.context.ContextView;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.service.ClusterService;
import im.turms.server.common.infra.cluster.service.codec.CodecService;
import im.turms.server.common.infra.cluster.service.config.SharedConfigService;
import im.turms.server.common.infra.cluster.service.config.entity.discovery.Member;
import im.turms.server.common.infra.cluster.service.connection.ConnectionService;
import im.turms.server.common.infra.cluster.service.connection.TurmsConnection;
import im.turms.server.common.infra.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.infra.cluster.service.discovery.MemberConnectionListener;
import im.turms.server.common.infra.cluster.service.idgen.IdService;
import im.turms.server.common.infra.cluster.service.rpc.channel.RpcFrameDecoder;
import im.turms.server.common.infra.cluster.service.rpc.channel.RpcFrameEncoder;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcResponse;
import im.turms.server.common.infra.cluster.service.rpc.exception.ConnectionNotFound;
import im.turms.server.common.infra.cluster.service.rpc.exception.RpcException;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.lang.Null;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.common.cluster.RpcProperties;
import im.turms.server.common.infra.random.RandomUtil;
import im.turms.server.common.infra.reactor.HashedWheelScheduler;
import im.turms.server.common.infra.serialization.SerializationException;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;

/**
 * @author James Chen
 */
public class RpcService implements ClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcService.class);

    private static final String METRICS_NAME_RPC_REQUEST = "rpc.request";
    private static final String METRICS_TAG_REQUEST_NAME = "name";
    private static final String METRICS_TAG_REQUEST_TARGET_NODE_ID = "node";

    private final NodeType nodeType;
    @Getter
    private final RpcRequestExecutor requestExecutor;
    private final Duration defaultRequestTimeoutDuration;

    private CodecService codecService;
    private ConnectionService connectionService;
    private DiscoveryService discoveryService;

    private final Map<String, RpcEndpoint> nodeIdToEndpoint = new ConcurrentHashMap<>(32);

    public RpcService(ApplicationContext context, NodeType nodeType, RpcProperties rpcProperties) {
        this.nodeType = nodeType;
        this.requestExecutor = new RpcRequestExecutor(context);
        defaultRequestTimeoutDuration = Duration.ofMillis(rpcProperties.getRequestTimeoutMillis());
    }

    @Override
    public void lazyInit(
            CodecService codecService,
            ConnectionService connectionService,
            DiscoveryService discoveryService,
            IdService idService,
            RpcService rpcService,
            SharedConfigService sharedConfigService) {
        this.codecService = codecService;
        this.connectionService = connectionService;
        this.discoveryService = discoveryService;
        connectionService.addMemberConnectionListenerSupplier(() -> new MemberConnectionListener() {
            private TurmsConnection connection;
            private Member member;
            private RpcEndpoint endpoint;

            @Override
            public void onConnectionOpened(TurmsConnection connection) {
                this.connection = connection;
                ChannelOperations<?, ?> conn = connection.getConnection();
                conn.addHandlerLast("rpcRequestFrameDecoder", new RpcFrameDecoder());
                conn.addHandlerLast("rpcRequestFrameEncoder", RpcFrameEncoder.INSTANCE);
            }

            @Override
            public void onConnectionClosed() {
                if (member != null) {
                    nodeIdToEndpoint.remove(member.getNodeId());
                }
            }

            @Override
            public void onOpeningHandshakeCompleted(Member member) {
                this.member = member;
                endpoint = getOrCreateEndpoint(member.getNodeId(), connection);
            }

            @Override
            public void onDataReceived(Object data) {
                if (data instanceof RpcRequest<?> request) {
                    onRequestReceived(request);
                } else if (data instanceof RpcResponse response) {
                    onResponseReceived(response);
                } else {
                    LOGGER.error("Received unknown data: "
                            + data);
                }
            }

            public void onRequestReceived(RpcRequest<?> request) {
                // Retain to avoid being released by FluxReceive
                request.retain();
                ChannelOperations<?, ?> conn = connection.getConnection();
                TracingContext ctx = request.getTracingContext();
                String nodeId = connection.getNodeId();
                requestExecutor.runRpcRequest(ctx, request, connection, nodeId)
                        .cast(Object.class)
                        .onErrorResume(RpcException.class, Mono::just)
                        .defaultIfEmpty(Null.INSTANCE)
                        .doOnNext(response -> {
                            if (conn.isDisposed()) {
                                try (TracingCloseableContext ignored = ctx.asCloseable()) {
                                    LOGGER.error(
                                            "Could not send the response ({}) to the disposed connection of the node: {}",
                                            response,
                                            nodeId);
                                }
                                return;
                            }
                            ByteBuf buf;
                            try {
                                buf = RpcFrameEncoder.INSTANCE.encode(request.getRequestId(),
                                        response);
                                buf.touch(response);
                            } catch (Exception e) {
                                try (TracingCloseableContext ignored = ctx.asCloseable()) {
                                    LOGGER.error("Failed to encode the response: {}", response, e);
                                }
                                if (response instanceof RpcException) {
                                    return;
                                }
                                // Try to fall back to the RpcException response
                                try {
                                    response = RpcException.get(RpcErrorCode.CODEC_FAILED_TO_ENCODE,
                                            ResponseStatusCode.SERVER_INTERNAL_ERROR,
                                            "Failed to encode the response: "
                                                    + response);
                                    buf = RpcFrameEncoder.INSTANCE.encode(request.getRequestId(),
                                            response);
                                } catch (Exception exception) {
                                    try (TracingCloseableContext ignored = ctx.asCloseable()) {
                                        LOGGER.error(
                                                "Failed to fall back to the RpcException since failing to encode the response: {}",
                                                response,
                                                exception);
                                    }
                                    return;
                                }
                            }
                            if (buf.refCnt() == 0) {
                                try (TracingCloseableContext ignored = ctx.asCloseable()) {
                                    LOGGER.error(
                                            "The buffer of response ({}) is released unexpectedly",
                                            response);
                                }
                                return;
                            }
                            // Duplicate the buffer to use an independent reader index
                            // because we don't want to modify the reader index of the original
                            // buffer
                            // if it is an unreleasable buffer internally, or it may be sent to
                            // multiple endpoints.
                            // Note that the content of the buffer is not copied, so "duplicate()"
                            // is efficient.
                            ByteBuf buffer = buf.duplicate();
                            conn.sendObject(buffer)
                                    .then()
                                    .subscribe(null, t -> {
                                        try (TracingCloseableContext ignored = ctx.asCloseable()) {
                                            LOGGER.error("Failed to send the response buffer: "
                                                    + buffer, t);
                                        }
                                    });
                        })
                        .contextWrite(context -> context.put(TracingContext.CTX_KEY_NAME, ctx))
                        .subscribe();
            }

            public void onResponseReceived(RpcResponse response) {
                if (endpoint == null) {
                    endpoint = getOrCreateEndpoint(connection.getNodeId(), connection);
                }
                endpoint.handleResponse(response);
            }
        });
    }

    public RpcEndpoint getOrCreateEndpoint(String nodeId) {
        return getOrCreateEndpoint(nodeId, null);
    }

    public RpcEndpoint getOrCreateEndpoint(String nodeId, @Nullable TurmsConnection connection) {
        if (nodeId.equals(discoveryService.getLocalMember()
                .getNodeId())) {
            throw new IllegalArgumentException(
                    "The target node ID of RPC endpoint cannot be the local node ID: "
                            + nodeId);
        }
        RpcEndpoint endpoint = nodeIdToEndpoint.get(nodeId);
        if (endpoint != null && (connection == null || connection == endpoint.getConnection())) {
            return endpoint;
        }
        return nodeIdToEndpoint.computeIfAbsent(nodeId, id -> createEndpoint(id, connection));
    }

    public RpcEndpoint createEndpoint(String nodeId, @Nullable TurmsConnection connection) {
        if (connection == null) {
            connection = connectionService.getMemberConnection(nodeId);
            if (connection == null) {
                throw new ConnectionNotFound(
                        "The connection to the member "
                                + nodeId
                                + " does not exist");
            }
        }
        return new RpcEndpoint(nodeId, connection);
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value; 2. a non-empty
     *         publisher if the peer responds with a non-null value; 3. error for other cases (e.g.
     *         no peer exists).
     */
    public <T> Mono<T> requestResponse(RpcRequest<T> request) {
        // TODO: Provide richer load balancing strategies
        List<Member> otherMembers = getOtherActiveConnectedMembersToRespond(request);
        int size = otherMembers.size();
        if (size == 0) {
            request.release();
            return Mono.error(RpcException.get(RpcErrorCode.MEMBER_NOT_FOUND,
                    ResponseStatusCode.SERVER_UNAVAILABLE));
        }
        // use System.currentTimeMillis() instead of "RandomUtil.nextPositiveInt()" for better
        // performance
        int index = (int) (System.currentTimeMillis() % size);
        Member member = otherMembers.get(index);
        // fast path
        if (!member.getStatus()
                .isHealthy()) {
            // slow path
            int retry = 0;
            boolean isUnhealthy;
            do {
                retry++;
                index = (index + retry) % size;
                member = otherMembers.get(index);
                isUnhealthy = !member.getStatus()
                        .isHealthy();
            } while (isUnhealthy && retry < size);
            if (isUnhealthy) {
                request.release();
                return Mono.error(RpcException.get(RpcErrorCode.HEALTHY_MEMBER_NOT_FOUND,
                        ResponseStatusCode.SERVER_UNAVAILABLE));
            }
        }
        String memberNodeId = member.getNodeId();
        RpcEndpoint client;
        try {
            client = getOrCreateEndpoint(memberNodeId);
        } catch (Exception e) {
            request.release();
            return Mono.error(e);
        }
        // Retain to invoke requestResponse() again if an error occurs
        request.retain();
        return requestResponse(client, request, defaultRequestTimeoutDuration)
                .onErrorResume(throwable -> {
                    if (ThrowableUtil.isDisconnectedClientError(throwable)) {
                        for (Member newMember : getOtherActiveConnectedMembersToRespond(request)) {
                            String newMemberId = newMember.getNodeId();
                            if (!newMemberId.equals(memberNodeId)
                                    && newMember.getStatus()
                                            .isHealthy()) {
                                return requestResponse(newMemberId,
                                        request,
                                        defaultRequestTimeoutDuration);
                            }
                        }
                    }
                    // No need to translate the error because it should have been translated
                    return Mono.error(throwable);
                })
                .doFinally(signal -> request.release());
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value; 2. a non-empty
     *         publisher if the peer responds with a non-null value; 3. error for other cases (e.g.
     *         no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RpcRequest<T> request) {
        return requestResponse(memberNodeId, request, null, null);
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value; 2. a non-empty
     *         publisher if the peer responds with a non-null value; 3. error for other cases (e.g.
     *         no peer exists).
     */
    public <T> Mono<T> requestResponse(
            String memberNodeId,
            RpcRequest<T> request,
            Duration timeout) {
        return requestResponse(memberNodeId, request, timeout, null);
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value; 2. a non-empty
     *         publisher if the peer responds with a non-null value; 3. error for other cases (e.g.
     *         no peer exists).
     */
    public <T> Mono<T> requestResponse(
            String memberNodeId,
            RpcRequest<T> request,
            Duration timeout,
            @Nullable TurmsConnection connection) {
        try {
            if (discoveryService.getLocalNodeStatusManager()
                    .isLocalNodeId(memberNodeId)) {
                return requestExecutor.runRpcRequest(new TracingContext(
                        request.getTracingContext()), request, null, memberNodeId);
            }
            RpcEndpoint endpoint = getOrCreateEndpoint(memberNodeId, connection);
            return requestResponse0(endpoint, request, timeout);
        } catch (Exception e) {
            request.release();
            return Mono.error(e);
        }
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value; 2. a non-empty
     *         publisher if the peer responds with a non-null value; 3. error for other cases (e.g.
     *         no peer exists).
     */
    public <T> Mono<T> requestResponse(
            RpcEndpoint connection,
            RpcRequest<T> request,
            @Nullable Duration timeout) {
        try {
            return requestResponse0(connection, request, timeout);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload; 2. a non-empty
     *         publisher if the peer responds with an non-empty valid payload; 3. error for other
     *         cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherMembers(
            @NotNull RpcRequest<T> request,
            boolean rejectIfMissingAnyConnection) {
        return requestResponsesFromOtherMembers(request,
                defaultRequestTimeoutDuration,
                rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload; 2. a non-empty
     *         publisher if the peer responds with a non-empty valid payload; 3. error for other
     *         cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherMembers(
            @NotNull RpcRequest<T> request,
            @NotNull Duration timeout,
            boolean rejectIfMissingAnyConnection) {
        List<Member> members = getOtherActiveConnectedMembersToRespond(request);
        return requestResponsesFromOtherMembers(members,
                request,
                timeout,
                rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. a non-empty publisher that publishes an empty map if all peers respond with an
     *         empty payload; 2. a non-empty publisher that publishes a non-empty map if any peer
     *         responds with a non-empty valid payload; 3. error for other cases (e.g. no peer
     *         exists). The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherMembers(
            @NotNull RpcRequest<T> request,
            boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherMembers(request,
                defaultRequestTimeoutDuration,
                rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. a non-empty publisher that publishes an empty map if all peers respond with an
     *         empty payload; 2. a non-empty publisher that publishes a non-empty map if any peer
     *         responds with an non-empty valid payload; 3. error for other cases (e.g. no peer
     *         exists). The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherMembers(
            @NotNull RpcRequest<T> request,
            @NotNull Duration timeout,
            boolean rejectIfMissingAnyConnection) {
        List<Member> members = getOtherActiveConnectedMembersToRespond(request);
        return requestResponsesAsMap(members, request, timeout, rejectIfMissingAnyConnection);
    }

    // Internal implementations

    private <T> Mono<T> requestResponse0(
            RpcEndpoint endpoint,
            RpcRequest<T> request,
            @Nullable Duration timeout) {
        try {
            assertCurrentNodeIsAllowedToSend(request);
        } catch (Exception e) {
            request.release();
            return Mono.error(e);
        }
        if (timeout == null) {
            timeout = defaultRequestTimeoutDuration;
        }
        Mono<T> mono = Mono.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf requestBody;
            try {
                requestBody = codecService.serializeWithoutCodecId(request);
            } catch (Exception e) {
                request.release();
                return Mono.error(new SerializationException(
                        "Failed to encode the request: "
                                + request,
                        e));
            }
            return endpoint.sendRequest(request, requestBody);
        })
                .timeout(timeout, HashedWheelScheduler.getDaemon())
                .name(METRICS_NAME_RPC_REQUEST)
                .tag(METRICS_TAG_REQUEST_NAME, request.name())
                .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, endpoint.getNodeId());
        Tag tag = request.tag();
        if (tag != null) {
            mono = mono.tag(tag.getKey(), tag.getValue());
        }
        return mono.metrics()
                .onErrorMap(t -> mapThrowable(t, request));
    }

    public <T> Flux<T> requestResponsesFromOtherMembers(
            List<Member> members,
            @NotNull RpcRequest<T> request,
            @NotNull Duration timeout,
            boolean rejectIfMissingAnyConnection) {
        try {
            assertCurrentNodeIsAllowedToSend(request);
        } catch (Exception e) {
            return Flux.error(e);
        }
        if (members.isEmpty()) {
            return Flux.error(RpcException.get(RpcErrorCode.MEMBER_NOT_FOUND,
                    ResponseStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !connectionService.isHasConnectedToAllMembers()) {
            return Flux.error(RpcException.get(RpcErrorCode.CONNECTION_NOT_FOUND,
                    ResponseStatusCode.SERVER_UNAVAILABLE,
                    "Not all connections are established"));
        }
        return Flux.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf requestBody = codecService.serializeWithoutCodecId(request);
            List<Mono<T>> results = new ArrayList<>(members.size());
            for (Member member : members) {
                String memberId = member.getNodeId();
                RpcEndpoint client;
                try {
                    client = getOrCreateEndpoint(memberId);
                } catch (Exception e) {
                    results.add(Mono.error(e));
                    continue;
                }
                requestBody.retain();
                Mono<T> responseMono = client.sendRequest(request, requestBody)
                        .name(METRICS_NAME_RPC_REQUEST)
                        .tag(METRICS_TAG_REQUEST_NAME, request.name())
                        .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, memberId)
                        .metrics();
                results.add(responseMono);
            }
            Flux<T> responseFlux = Flux.merge(results);
            Tag tag = request.tag();
            if (tag != null) {
                responseFlux = responseFlux.tag(tag.getKey(), tag.getValue());
            }
            return responseFlux.timeout(timeout, HashedWheelScheduler.getDaemon())
                    .onErrorMap(t -> mapThrowable(t, request))
                    .doFinally(signal -> requestBody.release());
        });
    }

    private <T> Mono<Map<String, T>> requestResponsesAsMap(
            List<Member> members,
            @NotNull RpcRequest<T> request,
            @NotNull Duration timeout,
            boolean rejectIfMissingAnyConnection) {
        try {
            assertCurrentNodeIsAllowedToSend(request);
        } catch (Exception e) {
            return Mono.error(e);
        }
        if (members.isEmpty()) {
            return Mono.error(RpcException.get(RpcErrorCode.MEMBER_NOT_FOUND,
                    ResponseStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection
                && !discoveryService.getLocalNodeStatusManager()
                        .getLocalMember()
                        .getStatus()
                        .isActive()) {
            return Mono.error(RpcException.get(RpcErrorCode.CONNECTION_NOT_FOUND,
                    ResponseStatusCode.SERVER_UNAVAILABLE,
                    "Some connections have not established"));
        }
        return Mono.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf requestBody = codecService.serializeWithoutCodecId(request);
            int size = members.size();
            List<Mono<Pair<String, T>>> results = new ArrayList<>(size);
            for (Member member : members) {
                String memberId = member.getNodeId();
                RpcEndpoint client;
                try {
                    client = getOrCreateEndpoint(memberId);
                } catch (Exception e) {
                    results.add(Mono.error(e));
                    continue;
                }
                requestBody.retain();
                Mono<Pair<String, T>> responseMono = client.sendRequest(request, requestBody)
                        .name(METRICS_NAME_RPC_REQUEST)
                        .tag(METRICS_TAG_REQUEST_NAME, request.name())
                        .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, memberId)
                        .metrics()
                        .map(payload -> Pair.of(memberId, payload));
                results.add(responseMono);
            }
            Flux<Pair<String, T>> resultFlux = Flux.merge(results);
            Tag tag = request.tag();
            if (tag != null) {
                resultFlux = resultFlux.tag(tag.getKey(), tag.getValue());
            }
            return resultFlux.timeout(timeout, HashedWheelScheduler.getDaemon())
                    .collectMap(Pair::first, Pair::second, CollectorUtil.toMap(size))
                    .onErrorMap(t -> mapThrowable(t, request))
                    .doFinally(signal -> requestBody.release());
        });
    }

    private void addTraceIdToRequestFromContext(ContextView contextView, RpcRequest<?> request) {
        long traceId = request.getTracingContext()
                .getTraceId();
        if (traceId != TracingContext.UNSET_TRACE_ID) {
            return;
        }
        traceId = TracingContext.getTraceId(contextView);
        if (traceId == TracingContext.UNSET_TRACE_ID) {
            traceId = RandomUtil.nextPositiveLong();
        }
        request.setTracingContext(new TracingContext(traceId));
    }

    private Throwable mapThrowable(Throwable throwable, RpcRequest<?> request) {
        // e.g. ClosedChannelException
        return new RuntimeException(
                "Failed to request a response for the request: "
                        + request,
                throwable);
    }

    // Validate node type

    private List<Member> getOtherActiveConnectedMembersToRespond(RpcRequest<?> request) {
        return switch (request.nodeTypeToRespond()) {
            case BOTH -> discoveryService.getOtherActiveConnectedMembers();
            case GATEWAY -> discoveryService.getOtherActiveConnectedGatewayMembers();
            case SERVICE -> discoveryService.getOtherActiveConnectedServiceMembers();
        };
    }

    private void assertCurrentNodeIsAllowedToSend(RpcRequest<?> request) {
        NodeTypeToHandleRpc type = request.nodeTypeToRequest();
        boolean allowed = switch (type) {
            case BOTH -> true;
            case GATEWAY -> nodeType == NodeType.GATEWAY;
            case SERVICE -> nodeType == NodeType.SERVICE;
        };
        if (!allowed) {
            throw new IllegalArgumentException(
                    "The node type of the current server is: "
                            + nodeType
                            + ", which cannot send the request \""
                            + request.name()
                            + "\" that requires the node type: "
                            + type);
        }
    }

}