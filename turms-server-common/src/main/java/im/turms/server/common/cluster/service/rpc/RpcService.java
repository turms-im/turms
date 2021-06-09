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
import im.turms.server.common.cluster.exception.RpcException;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.discovery.MemberInfoWithConnection;
import im.turms.server.common.cluster.service.idgen.IdService;
import im.turms.server.common.cluster.service.serialization.SerializationService;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerPool;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.logging.RequestLoggingContext;
import im.turms.server.common.property.env.common.cluster.RpcProperties;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.ExceptionUtil;
import im.turms.server.common.util.MapUtil;
import io.micrometer.core.instrument.Tag;
import io.netty.buffer.ByteBuf;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.exceptions.ApplicationErrorException;
import io.rsocket.util.ByteBufPayload;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Note that RpcService itself doesn't need to manage the life of the payload because
 * the implementation classes of Mono like RequestResponseRequesterMono in the package {@code io.rsocket.core}
 * will handle it.
 *
 * @author James Chen
 */
public class RpcService implements ClusterService {

    private static final String METRICS_NAME_RPC_REQUEST = "rpc.request";
    private static final String METRICS_TAG_REQUEST_NAME = "name";
    private static final String METRICS_TAG_REQUEST_TARGET_NODE_ID = "node";

    @Getter
    private final RpcAcceptor rpcAcceptor;
    private final SerializationService serializationService;
    private final Duration timeoutDuration;

    @Setter
    private DiscoveryService discoveryService;

    public RpcService(RpcProperties rpcProperties,
                      RpcAcceptor rpcAcceptor,
                      SerializationService serializationService) {
        this.rpcAcceptor = rpcAcceptor;
        this.serializationService = serializationService;
        timeoutDuration = Duration.ofMillis(rpcProperties.getTimeoutInMillis());
    }

    @Override
    public void lazyInit(DiscoveryService discoveryService, IdService idService, RpcService rpcService,
                         SerializationService serializationService, SharedConfigService sharedConfigService) {
        this.discoveryService = discoveryService;
    }

    /**
     * @return 1. an empty publisher if the peer responds with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(RpcCallable<T> request) {
        //TODO: Provide richer load balancing strategies
        List<MemberInfoWithConnection> serviceMembers = discoveryService.getOtherActiveConnectedServiceMemberList();
        int size = serviceMembers.size();
        if (size == 0) {
            return Mono.error(RpcException.get(RpcErrorCode.SERVICE_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        // use System.currentTimeMillis() instead of "RandomUtil.nextPositiveInt()" for better performance
        int index = (int) (System.currentTimeMillis() % size);
        MemberInfoWithConnection info = serviceMembers.get(index);
        String memberNodeId = info.getMember().getNodeId();
        return requestResponse(memberNodeId, info.getConnection(), request, timeoutDuration)
                .onErrorResume(throwable -> {
                    if (ExceptionUtil.isDisconnectedClientError(throwable)) {
                        for (MemberInfoWithConnection memberInfo : discoveryService.getOtherActiveConnectedServiceMemberList()) {
                            String newMemberId = memberInfo.getMember().getNodeId();
                            if (!newMemberId.equals(memberNodeId)) {
                                return requestResponse(newMemberId, request, timeoutDuration);
                            }
                        }
                    }
                    return Mono.error(throwable);
                });
    }

    /**
     * @return 1. an empty publisher if the peer responds with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RpcCallable<T> request) {
        return requestResponse(memberNodeId, request, timeoutDuration);
    }

    /**
     * @return 1. an empty publisher if the peer responds with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RpcCallable<T> request, Duration timeout) {
        try {
            if (memberNodeId.equals(discoveryService.getLocalMember().getNodeId())) {
                return rpcAcceptor.runRpcRequest(request);
            }
            RSocket connection = discoveryService.getConnectionManager().getMemberConnection(memberNodeId);
            if (connection == null) {
                return Mono.error(RpcException.get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE,
                        "The RSocket instance is missing for the member ID: " + memberNodeId));
            }
            return requestResponse0(memberNodeId, connection, request, timeout);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    /**
     * @return 1. an empty publisher if the peer responds with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Mono<T> requestResponse(String memberNodeId, RSocket connection, RpcCallable<T> request, Duration timeout) {
        try {
            return requestResponse0(memberNodeId, connection, request, timeout);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherServices(@NotNull RpcCallable<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesFromOtherServices(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherServices(@NotNull RpcCallable<T> request,
                                                         @NotNull Duration timeout,
                                                         boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedServiceMemberList();
        return requestResponsesFromOtherMembers(members, request, timeout, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherGateways(@NotNull RpcCallable<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesFromOtherGateways(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an empty publisher if all peers respond with an empty payload;
     * 2. a non-empty publisher if the peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     */
    public <T> Flux<T> requestResponsesFromOtherGateways(@NotNull RpcCallable<T> request,
                                                         @NotNull Duration timeout,
                                                         boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedServiceMemberList();
        return requestResponsesFromOtherMembers(members, request, timeout, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherServices(@NotNull RpcCallable<T> request,
                                                                           boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherServices(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherServices(@NotNull RpcCallable<T> request,
                                                                           @NotNull Duration timeout,
                                                                           boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedServiceMemberList();
        return requestResponsesAsMap(members, request, timeout, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherGateways(@NotNull RpcCallable<T> request,
                                                                           boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherGateways(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * The key is the member node ID, and the value is the response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherGateways(@NotNull RpcCallable<T> request,
                                                                           @NotNull Duration timeout,
                                                                           boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedGatewayMemberList();
        return requestResponsesAsMap(members, request, timeout, rejectIfMissingAnyConnection);
    }

    // Internal implementations

    private <T> Mono<T> requestResponse0(String memberNodeId,
                                         RSocket connection,
                                         RpcCallable<T> request,
                                         Duration timeout) {
        ByteBuf buffer = serializationService.serialize(request);
        Payload requestPayload = ByteBufPayload.create(buffer);
        Mono<Payload> mono = Mono
                .deferContextual(context -> {
                    addTraceIdToRequestFromContext(context, request);
                    return connection.requestResponse(requestPayload);
                })
                .timeout(timeout)
                .name(METRICS_NAME_RPC_REQUEST)
                .tag(METRICS_TAG_REQUEST_NAME, request.name())
                .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, memberNodeId);
        Tag tag = request.tag();
        if (tag != null) {
            mono = mono.tag(tag.getKey(), tag.getValue());
        }
        Mono<T> responseMono = mono
                .metrics()
                .flatMap(this::parsePayload);
        return responseMono
                .onErrorMap(this::translateThrowable);
    }

    public <T> Flux<T> requestResponsesFromOtherMembers(List<MemberInfoWithConnection> members,
                                                        @NotNull RpcCallable<T> request,
                                                        @NotNull Duration timeout,
                                                        boolean rejectIfMissingAnyConnection) {
        if (members.isEmpty()) {
            return Flux.error(RpcException.get(RpcErrorCode.SERVICE_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !discoveryService.getConnectionManager().isHasConnectedToAllMembers()) {
            return Flux.error(RpcException
                    .get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "Not all connections are established"));
        }
        return Flux.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf buffer = serializationService.serialize(request);
            Payload requestPayload = ByteBufPayload.create(buffer);
            List<Mono<Payload>> results = new ArrayList<>(members.size());
            for (MemberInfoWithConnection info : members) {
                requestPayload.retain();
                Mono<Payload> responseMono = info.getConnection().requestResponse(requestPayload)
                        .name(METRICS_NAME_RPC_REQUEST)
                        .tag(METRICS_TAG_REQUEST_NAME, request.name())
                        .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, info.getMember().getNodeId())
                        .metrics();
                results.add(responseMono);
            }
            Flux<Payload> responsePayloadFlux = Flux.merge(results);
            Tag tag = request.tag();
            if (tag != null) {
                responsePayloadFlux = responsePayloadFlux.tag(tag.getKey(), tag.getValue());
            }
            Flux<T> responseFlux = responsePayloadFlux
                    .timeout(timeout)
                    .flatMap(this::parsePayload);
            return responseFlux
                    .onErrorMap(this::translateThrowable)
                    .doOnTerminate(requestPayload::release);
        });
    }

    private <T> Mono<Map<String, T>> requestResponsesAsMap(List<MemberInfoWithConnection> members,
                                                           @NotNull RpcCallable<T> request,
                                                           @NotNull Duration timeout,
                                                           boolean rejectIfMissingAnyConnection) {
        if (members.isEmpty()) {
            return Mono.error(RpcException.get(RpcErrorCode.SERVICE_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !discoveryService.getLocalNodeStatusManager().getLocalMember().getStatus().isActive()) {
            return Mono.error(RpcException
                    .get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "Not all connections are established"));
        }
        return Mono.deferContextual(context -> {
            addTraceIdToRequestFromContext(context, request);
            ByteBuf buffer = serializationService.serialize(request);
            Payload requestPayload = ByteBufPayload.create(buffer);
            int size = members.size();
            List<Mono<Pair<String, Payload>>> results = new ArrayList<>(size);
            for (MemberInfoWithConnection info : members) {
                requestPayload.retain();
                String memberId = info.getMember().getNodeId();
                RSocket connection = info.getConnection();
                Mono<Pair<String, Payload>> responseMono = connection.requestResponse(requestPayload)
                        .name(METRICS_NAME_RPC_REQUEST)
                        .tag(METRICS_TAG_REQUEST_NAME, request.name())
                        .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, memberId)
                        .metrics()
                        .map(payload -> Pair.of(memberId, payload));
                results.add(responseMono);
            }
            Flux<Pair<String, Payload>> flux = Flux.merge(results);
            Tag tag = request.tag();
            if (tag != null) {
                flux = flux.tag(tag.getKey(), tag.getValue());
            }
            return flux
                    .timeout(timeout)
                    .collectMap(Pair::getFirst, pair -> {
                        Payload payload = pair.getSecond();
                        Object data = getReturnValueFromRpc(payload.sliceData());
                        return (T) data;
                    }, CollectorUtil.toMap(MapUtil.getCapability(size)))
                    .onErrorMap(this::translateThrowable)
                    .doOnTerminate(requestPayload::release);
        });
    }

    private void addTraceIdToRequestFromContext(ContextView contextView, RpcCallable<?> request) {
        if (request.getTracingContext().hasTraceId()) {
            return;
        }
        Long traceId = RequestLoggingContext.readTraceIdFromContext(contextView);
        if (traceId == null) {
            traceId = RandomUtil.nextPositiveLong();
        }
        request.setTracingContext(new TracingContext(traceId));
    }

    private <T> Mono<T> parsePayload(Payload payload) {
        if (payload.data().isReadable()) {
            T data = getReturnValueFromRpc(payload.sliceData());
            return Mono.just(data);
        } else {
            return Mono.empty();
        }
    }

    private <T> T getReturnValueFromRpc(ByteBuf buffer) {
        int returnValueType = buffer.readShort();
        Serializer<Object> serializer = SerializerPool.getSerializer(returnValueType);
        if (serializer == null) {
            throw RpcException.get(RpcErrorCode.SERIALIZER_FOR_RETURN_VALUE_NOT_FOUND, TurmsStatusCode.SERVER_INTERNAL_ERROR);
        }
        return (T) serializer.read(buffer);
    }

    private Throwable translateThrowable(Throwable throwable) {
        if (throwable instanceof ApplicationErrorException e) {
            try {
                return RpcException.parse(e);
            } catch (Exception exception) {
                return new IllegalStateException("Failed to parse ApplicationErrorException", throwable);
            }
        }
        // e.g. ClosedChannelException
        return throwable;
    }

}