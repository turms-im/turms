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

import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.cluster.exception.RpcException;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.discovery.MemberInfoWithConnection;
import im.turms.server.common.cluster.service.serialization.SerializationService;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerPool;
import im.turms.server.common.property.env.common.cluster.RpcProperties;
import io.micrometer.core.instrument.Tag;
import io.netty.buffer.ByteBuf;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.exceptions.ApplicationErrorException;
import io.rsocket.exceptions.ConnectionCloseException;
import io.rsocket.exceptions.ConnectionErrorException;
import io.rsocket.util.ByteBufPayload;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.nio.channels.ClosedChannelException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Note that RpcService itself doesn't need to manage the life of the payload because
 * the implementation classes of Mono like RequestResponseRequesterMono in the package {@code io.rsocket.core}
 * will handle it.
 *
 * @author James Chen
 */
@Log4j2
public class RpcService implements ClusterService {

    private static final String METRICS_NAME_RPC_REQUEST = "rpc.request";
    private static final String METRICS_TAG_REQUEST_NAME = "name";
    private static final String METRICS_TAG_REQUEST_TARGET_NODE_ID = "node";

    @Getter
    private static RpcAcceptor rpcAcceptor;

    private final SerializationService serializationService;
    private final DiscoveryService discoveryService;
    private final Duration timeoutDuration;

    public RpcService(RpcProperties rpcProperties, SerializationService serializationService, DiscoveryService discoveryService) {
        this.serializationService = serializationService;
        this.discoveryService = discoveryService;
        timeoutDuration = Duration.ofMillis(rpcProperties.getTimeoutInMillis());
    }

    public static void initRpcAcceptor(ApplicationContext context) {
        if (rpcAcceptor == null) {
            rpcAcceptor = new RpcAcceptor(context);
        }
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
                    if (isConnectionError(throwable)) {
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
                return Mono.error(RpcException.get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "The RSocket instance is missing for the member ID: " + memberNodeId));
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
    public <T> Flux<T> requestResponsesFromOtherServices(@NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
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
    public <T> Flux<T> requestResponsesFromOtherGateways(@NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedServiceMemberList();
        return requestResponsesFromOtherMembers(members, request, timeout, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * Map<String, T> = Member node ID -> Response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherServices(@NotNull RpcCallable<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherServices(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * Map<String, T> = Member node ID -> Response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherServices(@NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedServiceMemberList();
        return requestResponsesAsMap(members, request, timeout, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * Map<String, T> = Member node ID -> Response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherGateways(@NotNull RpcCallable<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherGateways(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return 1. an non-empty publisher that publishes an empty map if all peers respond with an empty payload;
     * 2. a non-empty publisher that publishes a non-empty map if any peer responds with an non-empty valid payload;
     * 3. error for other cases (e.g. no peer exists).
     * Map<String, T> = Member node ID -> Response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherGateways(@NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
        List<MemberInfoWithConnection> members = discoveryService.getOtherActiveConnectedGatewayMemberList();
        return requestResponsesAsMap(members, request, timeout, rejectIfMissingAnyConnection);
    }

    // Internal implementations

    private <T> Mono<T> requestResponse0(String memberNodeId, RSocket connection, RpcCallable<T> request, Duration timeout) {
        ByteBuf buffer = serializationService.serialize(request);
        Payload requestPayload = ByteBufPayload.create(buffer);
        Mono<Payload> mono = connection.requestResponse(requestPayload)
                .timeout(timeout)
                .name(METRICS_NAME_RPC_REQUEST)
                .tag(METRICS_TAG_REQUEST_NAME, request.name())
                .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, memberNodeId);
        Tag tag = request.tag();
        if (tag != null) {
            mono = mono.tag(tag.getKey(), tag.getValue());
        }
        return (Mono<T>) mono
                .metrics()
                .flatMap(this::parsePayload)
                .onErrorMap(throwable -> tryLogAndTranslateThrowable(throwable, memberNodeId));
    }

    public <T> Flux<T> requestResponsesFromOtherMembers(List<MemberInfoWithConnection> members, @NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
        if (members.isEmpty()) {
            return Flux.error(RpcException.get(RpcErrorCode.SERVICE_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !discoveryService.getConnectionManager().isHasConnectedToAllMembers()) {
            return Flux.error(RpcException.get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "Not all connections are established"));
        }
        ByteBuf buffer = serializationService.serialize(request);
        Payload requestPayload = ByteBufPayload.create(buffer);
        List<Mono<Payload>> results = new ArrayList<>(members.size());
        for (MemberInfoWithConnection info : members) {
            Mono<Payload> mono = info.getConnection().requestResponse(requestPayload)
                    .name(METRICS_NAME_RPC_REQUEST)
                    .tag(METRICS_TAG_REQUEST_NAME, request.name())
                    .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, info.getMember().getNodeId())
                    .metrics();
            results.add(mono);
        }
        Flux<Payload> flux = Flux.merge(results);
        Tag tag = request.tag();
        if (tag != null) {
            flux = flux.tag(tag.getKey(), tag.getValue());
        }
        return (Flux<T>) flux
                .timeout(timeout)
                .flatMap(this::parsePayload)
                .onErrorMap(throwable -> tryLogAndTranslateThrowable(throwable, members));
    }

    private <T> Mono<Map<String, T>> requestResponsesAsMap(List<MemberInfoWithConnection> members,
                                                           @NotNull RpcCallable<T> request,
                                                           @NotNull Duration timeout,
                                                           boolean rejectIfMissingAnyConnection) {
        if (members.isEmpty()) {
            return Mono.error(RpcException.get(RpcErrorCode.SERVICE_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (rejectIfMissingAnyConnection && !discoveryService.getLocalNodeStatusManager().getLocalMember().isActive()) {
            return Mono.error(RpcException.get(RpcErrorCode.CONNECTION_NOT_FOUND, TurmsStatusCode.SERVER_UNAVAILABLE, "Not all connections are established"));
        }
        ByteBuf buffer = serializationService.serialize(request);
        Payload requestPayload = ByteBufPayload.create(buffer);
        List<Mono<Pair<String, Payload>>> results = new ArrayList<>(members.size());
        for (MemberInfoWithConnection info : members) {
            String memberId = info.getMember().getNodeId();
            RSocket connection = info.getConnection();
            Mono<Pair<String, Payload>> mono = connection.requestResponse(requestPayload)
                    .name(METRICS_NAME_RPC_REQUEST)
                    .tag(METRICS_TAG_REQUEST_NAME, request.name())
                    .tag(METRICS_TAG_REQUEST_TARGET_NODE_ID, memberId)
                    .metrics()
                    .map(payload -> Pair.of(memberId, payload));
            results.add(mono);
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
                })
                .onErrorMap(throwable -> tryLogAndTranslateThrowable(throwable, members));
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
        if (serializer != null) {
            return (T) serializer.read(buffer);
        } else {
            throw RpcException.get(RpcErrorCode.SERIALIZER_FOR_RETURN_VALUE_NOT_FOUND, TurmsStatusCode.SERVER_INTERNAL_ERROR);
        }
    }

    private Throwable tryLogAndTranslateThrowable(Throwable throwable, @NotNull String memberNodeId) {
        Throwable translatedThrowable = translateThrowable(throwable);
        if (isServerError(translatedThrowable)) {
            log.error("Failed to request response from member: " + memberNodeId, throwable);
        }
        return translatedThrowable;
    }

    private Throwable tryLogAndTranslateThrowable(Throwable throwable, @NotNull Collection<MemberInfoWithConnection> members) {
        Throwable translatedThrowable = translateThrowable(throwable);
        if (isServerError(translatedThrowable)) {
            log.error("Failed to request response from members: " + members, throwable);
        }
        return translatedThrowable;
    }

    private Throwable translateThrowable(Throwable throwable) {
        if (throwable instanceof ApplicationErrorException) {
            try {
                return RpcException.parse((ApplicationErrorException) throwable);
            } catch (Exception exception) {
                log.error(exception);
                return throwable;
            }
        }
        return throwable;
    }

    private boolean isServerError(Throwable throwable) {
        if (throwable instanceof RpcException) {
            return ((RpcException) throwable).isServerError();
        } else {
            // e.g. ClosedChannelException
            return true;
        }
    }

    private boolean isConnectionError(Throwable throwable) {
        return throwable instanceof ClosedChannelException
                || throwable instanceof ConnectionErrorException
                || throwable instanceof ConnectionCloseException;
    }

}