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

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.server.common.cluster.exception.ConnectionNotFoundException;
import im.turms.server.common.cluster.exception.RpcException;
import im.turms.server.common.cluster.exception.SocketInstanceNotFoundException;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.serialization.SerializationService;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerPool;
import im.turms.server.common.property.env.common.cluster.RpcProperties;
import io.netty.buffer.ByteBuf;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.exceptions.ApplicationErrorException;
import io.rsocket.util.DefaultPayload;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author James Chen
 */
@Log4j2
public class RpcService implements ClusterService {

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

    public static RpcAcceptor initRpcAcceptor(ApplicationContext context) {
        if (rpcAcceptor == null) {
            rpcAcceptor = new RpcAcceptor(context);
        }
        return rpcAcceptor;
    }

    public <T> Mono<T> requestResponse(RpcCallable<T> request) {
        //TODO: Provide richer load balancing strategies
        List<Member> serviceMembers = discoveryService.getOtherActiveConnectedServiceMemberList();
        int size = serviceMembers.size();
        if (size == 0) {
            return Mono.error(RpcException.get(RpcErrorCode.SERVICE_NOT_FOUND, TurmsStatusCode.UNAVAILABLE));
        } else {
            int index = ThreadLocalRandom.current().nextInt() % size;
            Member member = serviceMembers.get(index);
            return requestResponse(member.getNodeId(), request, timeoutDuration);
        }
    }

    public <T> Mono<T> requestResponse(String memberNodeId, RpcCallable<T> request) {
        return requestResponse(memberNodeId, request, timeoutDuration);
    }

    public <T> Mono<T> requestResponse(String memberNodeId, RpcCallable<T> request, Duration timeout) {
        try {
            if (memberNodeId.equals(discoveryService.getLocalMember().getNodeId())) {
                return rpcAcceptor.runRpcRequest(request);
            }
            RSocket socket = discoveryService.getConnectionManager().getMemberConnection(memberNodeId);
            if (socket != null) {
                ByteBuf buffer = serializationService.serialize(request);
                Payload requestPayload = DefaultPayload.create(buffer);
                return (Mono<T>) socket.requestResponse(requestPayload)
                        .flatMap(this::parsePayload)
                        .onErrorMap(throwable -> {
                            requestPayload.release();
                            return tryLogAndTranslateThrowable((Throwable) throwable, memberNodeId, null);
                        })
                        .timeout(timeout);
            } else {
                return Mono.error(new SocketInstanceNotFoundException("The RSocket instance is missing for the member ID: " + memberNodeId));
            }
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    public <T> Flux<T> requestResponsesFromOtherServices(@NotNull RpcCallable<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesFromOtherServices(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    public <T> Flux<T> requestResponsesFromOtherServices(@NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
        List<Member> otherActiveConnectedServiceMembers = discoveryService.getOtherActiveConnectedServiceMemberList();
        if (!otherActiveConnectedServiceMembers.isEmpty()) {
            if (rejectIfMissingAnyConnection && !discoveryService.getConnectionManager().isHasConnectedToAllMembers()) {
                return Flux.error(new ConnectionNotFoundException("Not all connections are established"));
            }
            ByteBuf buffer = serializationService.serialize(request);
            Payload requestPayload = DefaultPayload.create(buffer);
            Collection<RSocket> sockets = discoveryService.getConnectionManager().getConnectionMap().values();
            List<Mono<Payload>> results = new ArrayList<>(sockets.size());
            for (RSocket socket : sockets) {
                results.add(socket.requestResponse(requestPayload));
            }
            return (Flux<T>) Flux.merge(results)
                    .flatMap(this::parsePayload)
                    .onErrorMap(throwable -> {
                        requestPayload.release();
                        return tryLogAndTranslateThrowable((Throwable) throwable, null, otherActiveConnectedServiceMembers);
                    })
                    .timeout(timeout);
        } else {
            return Flux.empty();
        }
    }

    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherServices(@NotNull RpcCallable<T> request, boolean rejectIfMissingAnyConnection) {
        return requestResponsesAsMapFromOtherServices(request, timeoutDuration, rejectIfMissingAnyConnection);
    }

    /**
     * @return Member node ID -> Response
     */
    public <T> Mono<Map<String, T>> requestResponsesAsMapFromOtherServices(@NotNull RpcCallable<T> request, @NotNull Duration timeout, boolean rejectIfMissingAnyConnection) {
        List<Member> otherActiveConnectedServiceMembers = discoveryService.getOtherActiveConnectedServiceMemberList();
        if (!otherActiveConnectedServiceMembers.isEmpty()) {
            if (rejectIfMissingAnyConnection && !discoveryService.getLocalNodeStatusManager().getLocalMember().isActive()) {
                return Mono.error(new ConnectionNotFoundException("Not all connections are established"));
            }
            ByteBuf buffer = serializationService.serialize(request);
            Payload requestPayload = DefaultPayload.create(buffer);
            Collection<RSocket> sockets = discoveryService.getConnectionManager().getConnectionMap().values();
            List<Mono<Pair<String, Payload>>> results = new ArrayList<>(sockets.size());
            for (Map.Entry<String, RSocket> entry : discoveryService.getConnectionManager().getConnectionMap().entrySet()) {
                String memberId = entry.getKey();
                RSocket socket = entry.getValue();
                results.add(socket.requestResponse(requestPayload)
                        .map(payload -> Pair.of(memberId, payload)));
            }
            return Flux.merge(results)
                    .collectMap(Pair::getFirst, pair -> {
                        Payload payload = pair.getSecond();
                        Object data = getReturnValueFromRpc(payload.sliceData());
                        payload.release();
                        return (T) data;
                    })
                    .onErrorMap(throwable -> {
                        requestPayload.release();
                        return tryLogAndTranslateThrowable(throwable, null, otherActiveConnectedServiceMembers);
                    })
                    .timeout(timeout);
        } else {
            return Mono.empty();
        }
    }

    private Mono parsePayload(Payload payload) {
        if (payload.data().isReadable()) {
            Object data = getReturnValueFromRpc(payload.sliceData());
            payload.release();
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

    private Throwable tryLogAndTranslateThrowable(Throwable throwable, @Nullable String memberNodeId, @Nullable Collection<Member> members) {
        if (throwable instanceof ApplicationErrorException) {
            RpcException e = RpcException.parse((ApplicationErrorException) throwable);
            if (e != null) {
                if (e.isServerError()) {
                    if (memberNodeId != null) {
                        log.error("Failed to request response from member: " + memberNodeId, throwable);
                    } else {
                        log.error("Failed to request response from members: " + members, throwable);
                    }
                }
                return e;
            }
        } else {
            // e.g. ClosedChannelException
            log.error("Failed to request response from members: " + members, throwable);
        }
        return throwable;
    }

}