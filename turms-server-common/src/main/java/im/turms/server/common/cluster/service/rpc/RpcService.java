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

package im.turms.server.common.cluster.service.rpc;

import im.turms.common.util.RandomUtil;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.codec.CodecService;
import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.connection.ConnectionService;
import im.turms.server.common.cluster.service.connection.TurmsConnection;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.discovery.MemberConnectionListener;
import im.turms.server.common.cluster.service.idgen.IdService;
import im.turms.server.common.cluster.service.rpc.codec.RpcFrameDecoder;
import im.turms.server.common.cluster.service.rpc.codec.RpcFrameEncoder;
import im.turms.server.common.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.cluster.service.rpc.dto.RpcResponse;
import im.turms.server.common.cluster.service.rpc.exception.RpcException;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.logging.RequestLoggingContext;
import im.turms.server.common.property.env.common.cluster.RpcProperties;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.ExceptionUtil;
import im.turms.server.common.util.MapUtil;
import io.micrometer.core.instrument.Tag;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.channel.ChannelOperations;
import reactor.util.context.ContextView;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Log4j2
public class RpcService implements ClusterService {

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

    /**
     * Node ID -> RPC Endpoint
     */
    private final Map<String, RpcEndpoint> endpointPool = new ConcurrentHashMap<>(32);

    public RpcService(ApplicationContext context,
                      NodeType nodeType,
                      RpcProperties rpcProperties) {
        this.nodeType = nodeType;
        this.requestExecutor = new RpcRequestExecutor(context);
        defaultRequestTimeoutDuration = Duration.ofMillis(rpcProperties.getRequestTimeoutMillis());
    }

    @Override
    public void lazyInit(CodecService codecService,
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
            public void onConnectionOpen(TurmsConnection connection) {
                this.connection = connection;
                ChannelOperations<?, ?> conn = connection.getConnection();
                conn.addHandlerLast("rpcRequestFrameDecoder", new RpcFrameDecoder());
                conn.addHandlerLast("rpcRequestFrameEncoder", RpcFrameEncoder.INSTANCE);
            }

            @Override
            public void onConnectionClosed() {
                endpointPool.remove(member.getNodeId());
            }

            @Override
            public void onOpeningHandshakeCompleted(Member member) {
                this.member = member;
                endpoint = getOrCreateEndpoint(member.getNodeId(), connection);
            }

            @Override
            public void onDataReceived(Object data) {
                if (data instanceof RpcRequest<?> request) {
                    ChannelOperations<?, ?> conn = connection.getConnection();
                    requestExecutor.runRpcRequest(request, connection, connection.getNodeId())
                            .onErrorResume(RpcException.class, t -> (Mono) Mono.just(t))
                            .doOnNext(response -> {
                                if (conn.isDisposed()) {
                                    log.error("Cannot send response to disposed connection: " + response);
                                    return;
                                }
                                ByteBuf buf;
                                try {
                                    buf = RpcFrameEncoder.INSTANCE.encode(request.getRequestId(), response);
                                } catch (Exception e) {
                                    log.error("Failed to encode response: {}", response, e);
                                    return;
                                }
                                if (buf.refCnt() == 0) {
                                    log.error("The buffer of response is released unexpectedly: " + response);
                                    return;
                                }
                                conn.sendObject(buf)
                                        .then()
                                        .onErrorResume(t -> {
                                            log.error("Failed to send response", t);
                                            return Mono.empty();
                                        })
                                        .subscribe();
                            })
                            .subscribe();
                } else if (data instanceof RpcResponse response) {
                    if (endpoint == null) {
                        endpoint = getOrCreateEndpoint(connection.getNodeId(), connection);
                    }
                    endpoint.handleResponse(response);
                } else {
                    log.error("Receive an unknown data: " + data);
                }
            }
        });
    }

    public RpcEndpoint getOrCreateEndpoint(String nodeId) {
        return getOrCreateEndpoint(nodeId, null);
    }

    public RpcEndpoint getOrCreateEndpoint(String nodeId, @Nullable TurmsConnection connection) {
        if (nodeId.equals(discoveryService.getLocalMember().getNodeId())) {
            throw new IllegalArgumentException("The target node ID of RPC endpoint cannot be the local node ID: " + nodeId);
        }
        RpcEndpoint client = endpointPool.get(nodeId);
        if (client != null && (connection == null || connection == client.getConnection())) {
            return client;
        }
        client = createClient(nodeId, connection);
        endpointPool.put(nodeId, client);
        return client;
    }

    public synchronized RpcEndpoint createClient(String nodeId, @Nullable TurmsConnection connection) {
        if (connection == null) {
            connection = connectionService.getMemberConnection(nodeId);
            if (connection == null) {
                // This should never happen
                throw new IllegalStateException("The connection to the member " + nodeId
                        + " doesn't exist");
            }
        }
        return new RpcEndpoint(nodeId, connection);
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value;
     * 2. a non-empty publisher if the peer responds with a non-null value;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(RpcRequest<T> request) {
        //TODO: Provide richer load balancing strategies
        List<String> otherMembers = getOtherActiveConnectedMembersToRespond(request);
        int size = otherMembers.size();
        if (size == 0) {
            request.releaseBoundBuffer();
            return Mono.error(RpcException.get(RpcErrorCode.MEMBER_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        // use System.currentTimeMillis() instead of "RandomUtil.nextPositiveInt()" for better performance
        int index = (int) (System.currentTimeMillis() % size);
        String memberNodeId = otherMembers.get(index);
        RpcEndpoint client;
        try {
            client = getOrCreateEndpoint(memberNodeId);
        } catch (Exception e) {
            request.releaseBoundBuffer();
            return Mono.error(e);
        }
        // Retain to invoke requestResponse() again if an error occurs
        request.retainBoundBuffer();
        return requestResponse(client, request, defaultRequestTimeoutDuration)
                .onErrorResume(throwable -> {
                    if (ExceptionUtil.isDisconnectedClientError(throwable)) {
                        for (String newMemberId : getOtherActiveConnectedMembersToRespond(request)) {
                            if (!newMemberId.equals(memberNodeId)) {
                                return requestResponse(newMemberId, request, defaultRequestTimeoutDuration);
                            }
                        }
                    }
                    // No need to translate the error because it should have been translated
                    return Mono.error(throwable);
                })
                .doFinally(signal -> request.releaseBoundBuffer());
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value;
     * 2. a non-empty publisher if the peer responds with a non-null value;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RpcRequest<T> request) {
        return requestResponse(memberNodeId, request, null, null);
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value;
     * 2. a non-empty publisher if the peer responds with a non-null value;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RpcRequest<T> request, Duration timeout) {
        return requestResponse(memberNodeId, request, timeout, null);
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value;
     * 2. a non-empty publisher if the peer responds with a non-null value;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RpcRequest<T> request, Duration timeout, @Nullable TurmsConnection connection) {
        try {
            if (discoveryService.getLocalNodeStatusManager().isLocalNodeId(memberNodeId)) {
                return requestExecutor.runRpcRequest(request, null, memberNodeId);
            }
            RpcEndpoint endpoint = getOrCreateEndpoint(memberNodeId, connection);
            return requestResponse0(endpoint, request, timeout);
        } catch (Exception e) {
            request.releaseBoundBuffer();
            return Mono.error(e);
        }
    }

    /**
     * @return 1. an empty publisher if the peer responds with a null value;
     * 2. a non-empty publisher if the peer responds with a non-null value;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(RpcEndpoint connection, RpcRequest<T> request, @Nullable Duration timeout) {
        try {
            return requestResponse0(connection, request, timeout);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherMembers(@NotNull RpcRequest<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesFromOtherMembers(request, defaultRequestTimeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherMembers(@NotNull RpcRequest<T> request,
                                                        @NotNull Duration timeout,
                                                        boolean rejectIfMissingAnyConnection) {
        List<String> memberIds = getOtherActiveConnectedMembersToRespond(request);
        return requestResponsesFromOtherMembers(memberIds, request, timeout, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with a non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherMembers(@NotNull RpcRequest<T> request,
                                                                          boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherMembers(request, defaultRequestTimeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherMembers(@NotNull RpcRequest<T> request,
                                                                          @NotNull Duration timeout,
                                                                          boolean rejectIfMissingAnyConnection) {
        List<String> memberIds = getOtherActiveConnectedMembersToRespond(request);
        return requestResponsesAsMap(memberIds, request, timeout, rejectIfMissingAnyConnection);
    }

    // Internal implementations

    private <T> Mono<T> requestResponse0(RpcEndpoint endpoint,
                                         RpcRequest<T> request,
                                         @Nullable Duration timeout) {
        try {
            assertCurrentNodeIsAllowedToSend(request);
        } catch (Exception e) {
            request.releaseBoundBuffer();
            return Mono.error(e);
        }
        if (timeout == null) {
            timeout = defaultRequestTimeoutDuration;
        }
        Mono<T> mono = Mono
                .deferContextual(context -> {
                    addTraceIdToRequestFromContext(context, request);
                    ByteBuf requestBody;
                    try {
                        requestBody = codecService.serializeWithoutCodecId(request);
                    } catch (Exception e) {
                        request.releaseBoundBuffer();
                        return Mono.error(new IllegalStateException("Failed to encode the request: " + request, e));
                    }
                    return endpoint.sendRequest(request, requestBody);
                })
                .timeout(timeout)
                .name(METRICS_NAME_RPC_REQUEST)
                .tag(METRICS_TAG_REQUEST_NAME, request.name())
                .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, endpoint.getNodeId());
        Tag tag = request.tag();
        if (tag != null) {
            mono = mono.tag(tag.getKey(), tag.getValue());
        }
        return mono
                .metrics()
                .onErrorMap(t -> mapThrowable(t, request));
    }

    public <T> Flux<T> requestResponsesFromOtherMembers(List<String> memberIds,
                                                        @NotNull RpcRequest<T> request,
                                                        @NotNull Duration timeout,
                                                        boolean rejectIfMissingAnyConnection) {
        try {
            assertCurrentNodeIsAllowedToSend(request);
        } catch (Exception e) {
            return Flux.error(e);
        }
        if (memberIds.isEmpty()) {
            return Flux.error(RpcException.get(RpcErrorCode.MEMBER_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !connectionService.isHasConnectedToAllMembers()) {
            return Flux.error(RpcException
                    .get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "Not all connections are established"));
        }
        return Flux.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf requestBody = codecService.serializeWithoutCodecId(request);
            List<Mono<T>> results = new ArrayList<>(memberIds.size());
            for (String memberId : memberIds) {
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
            return responseFlux
                    .timeout(timeout)
                    .onErrorMap(t -> mapThrowable(t, request))
                    .doFinally(signal -> requestBody.release());
        });
    }

    private <T> Mono<Map<String, T>> requestResponsesAsMap(List<String> memberIds,
                                                           @NotNull RpcRequest<T> request,
                                                           @NotNull Duration timeout,
                                                           boolean rejectIfMissingAnyConnection) {
        try {
            assertCurrentNodeIsAllowedToSend(request);
        } catch (Exception e) {
            return Mono.error(e);
        }
        if (memberIds.isEmpty()) {
            return Mono.error(RpcException.get(RpcErrorCode.MEMBER_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !discoveryService.getLocalNodeStatusManager().getLocalMember().getStatus().isActive()) {
            return Mono.error(RpcException
                    .get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "Not all connections are established"));
        }
        return Mono.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf requestBody = codecService.serializeWithoutCodecId(request);
            int size = memberIds.size();
            List<Mono<Pair<String, T>>> results = new ArrayList<>(size);
            for (String memberId : memberIds) {
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
            return resultFlux
                    .timeout(timeout)
                    .collectMap(Pair::getFirst, Pair::getSecond, CollectorUtil.toMap(MapUtil.getCapability(size)))
                    .onErrorMap(t -> mapThrowable(t, request))
                    .doFinally(signal -> requestBody.release());
        });
    }

    private void addTraceIdToRequestFromContext(ContextView contextView, RpcRequest<?> request) {
        if (request.getTracingContext().hasTraceId()) {
            return;
        }
        Long traceId = RequestLoggingContext.readTraceIdFromContext(contextView);
        if (traceId == null) {
            traceId = RandomUtil.nextPositiveLong();
        }
        request.setTracingContext(new TracingContext(traceId));
    }


    private Throwable mapThrowable(Throwable throwable, RpcRequest<?> callable) {
        // else e.g. ClosedChannelException
        return new IllegalStateException("Failed to request a response for the request: " + callable, throwable);
    }

    // Validate node type

    private List<String> getOtherActiveConnectedMembersToRespond(RpcRequest<?> request) {
        return switch (request.nodeTypeToRespond()) {
            case BOTH -> discoveryService.getOtherActiveConnectedMemberIds();
            case GATEWAY -> discoveryService.getOtherActiveConnectedGatewayMemberIds();
            case SERVICE -> discoveryService.getOtherActiveConnectedServiceMemberIds();
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
            throw new IllegalStateException("The current server " + nodeType + " cannot send the request "
                    + request.name() + " that requires the node type " + type);
        }
    }

}