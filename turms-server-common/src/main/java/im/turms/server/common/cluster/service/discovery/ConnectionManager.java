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

package im.turms.server.common.cluster.service.discovery;

import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.property.env.common.cluster.DiscoveryProperties;
import im.turms.server.common.util.SslUtil;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketConnector;
import io.rsocket.micrometer.MicrometerDuplexConnectionInterceptor;
import io.rsocket.micrometer.MicrometerRSocketInterceptor;
import io.rsocket.transport.netty.client.TcpClientTransport;
import jdk.internal.vm.annotation.Contended;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.server.Ssl;
import reactor.core.publisher.Mono;
import reactor.netty.Metrics;
import reactor.netty.tcp.TcpClient;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
@Log4j2
public class ConnectionManager {

    private final Ssl clientSsl;
    private final Duration keepaliveInterval;
    private final Duration keepaliveTimeout;
    private final Duration reconnectInterval;
    private final NioEventLoopGroup eventLoopGroup;

    /**
     * Note that it is acceptable if a socket is connected to non-member turms servers
     */
    @Getter
    private final Map<String, RSocket> connectionMap = new ConcurrentHashMap<>();
    private final Set<Member> connectingMembers = ConcurrentHashMap.newKeySet();
    /**
     * Address -> Retry times.
     * Never stop reconnecting until the member is removed from cluster
     */
    private final Map<Member, Integer> failedConnectionMemberMap = new HashMap<>();
    private final DiscoveryService discoveryService;
    private final List<MemberConnectionChangeListener> memberConnectionChangeListeners = new LinkedList<>();

    @Getter
    @Contended
    private boolean hasConnectedToAllMembers;

    public ConnectionManager(DiscoveryService discoveryService, DiscoveryProperties discoveryProperties) {
        this.discoveryService = discoveryService;
        clientSsl = discoveryProperties.getClientSsl();
        keepaliveInterval = Duration.ofSeconds(discoveryProperties.getHeartbeatIntervalInSeconds());
        keepaliveTimeout = Duration.ofSeconds(discoveryProperties.getHeartbeatTimeoutInSeconds());
        reconnectInterval = Duration.ofSeconds(discoveryProperties.getReconnectIntervalInSeconds());
        eventLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new DefaultThreadFactory("connection-client"));
    }

    public void stop() {
        for (RSocket socket : connectionMap.values()) {
            socket.dispose();
        }
        connectionMap.clear();
    }

    @Nullable
    public RSocket getMemberConnection(String memberId) {
        return connectionMap.get(memberId);
    }

    public synchronized void updateHasConnectedToAllMembers(Set<String> allMemberNodeIds) {
        boolean connected = true;
        for (String nodeId : allMemberNodeIds) {
            if (!connectionMap.containsKey(nodeId) && !nodeId.equals(discoveryService.getLocalMember().getNodeId())) {
                connected = false;
                break;
            }
        }
        this.hasConnectedToAllMembers = connected;
    }

    public void connectMemberUntilSucceedOrRemoved(Member member) {
        if (!member.isSameNode(discoveryService.getLocalMember())
                && !connectionMap.containsKey(member.getNodeId())
                && connectingMembers.add(member)) {
            connectMemberUntilSucceedOrRemoved0(member);
        }
    }

    public void addMemberConnectionChangeListener(MemberConnectionChangeListener listener) {
        memberConnectionChangeListeners.add(listener);
    }

    public boolean isMemberConnected(String nodeId) {
        return connectionMap.containsKey(nodeId);
    }

    private void connectMemberUntilSucceedOrRemoved0(Member member) {
        String nodeId = member.getNodeId();
        log.info("Connecting to member: {}[{}:{}]", nodeId, member.getMemberHost(), member.getMemberPort());
        initConnection(member.getMemberHost(), member.getMemberPort())
                .doOnError(throwable -> {
                    if (discoveryService.isKnownMember(nodeId)) {
                        int retryTimes = failedConnectionMemberMap.getOrDefault(member, 0);
                        log.error("Failed to connect to member: {}[{}:{}]. Retry times: {}",
                                nodeId,
                                member.getMemberHost(), member.getMemberPort(), retryTimes, throwable);
                        retryTimes++;
                        failedConnectionMemberMap.put(member, retryTimes);
                        discoveryService.getScheduler().schedule(() -> {
                            boolean hasConnected = connectionMap.containsKey(nodeId);
                            boolean exists = discoveryService.isKnownMember(nodeId);
                            if (!hasConnected && exists) {
                                connectMemberUntilSucceedOrRemoved0(member);
                            } else {
                                failedConnectionMemberMap.remove(member);
                            }
                        }, Math.min(retryTimes * 10, 60), TimeUnit.SECONDS);
                    }
                })
                .doOnSuccess(connection -> {
                    log.info("Connected to member: {}[{}:{}]", nodeId, member.getMemberHost(), member.getMemberPort());
                    connectionMap.put(nodeId, connection);
                    failedConnectionMemberMap.remove(member);
                    connectingMembers.remove(member);
                    updateHasConnectedToAllMembers(discoveryService.getAllKnownMembers().keySet());
                    for (MemberConnectionChangeListener listener : memberConnectionChangeListeners) {
                        listener.onMemberConnectionAdded(member, connection);
                    }
                    // See io.rsocket.core.RSocketRequester.terminate
                    connection.onClose()
                            // e.g. ConnectionErrorException for keepalive timeout.
                            // See io.rsocket.core.RSocketRequester.tryTerminateOnKeepAlive
                            .doOnError(throwable -> onConnectClosed(member, throwable))
                            .doOnSuccess(ignored -> onConnectClosed(member, null))
                            .subscribe();
                })
                .subscribe();
    }

    private Mono<RSocket> initConnection(String host, int port) {
        TcpClient client = TcpClient.create()
                .host(host)
                .port(port)
                .runOn(eventLoopGroup);
        if (clientSsl.isEnabled()) {
            client.secure(sslContextSpec -> SslUtil.configureSslContextSpec(sslContextSpec, clientSsl, false));
        }
        TcpClientTransport transport = TcpClientTransport.create(client);
        return RSocketConnector.create()
                .interceptors(registry -> {
                    MicrometerDuplexConnectionInterceptor connectionInterceptor =
                            new MicrometerDuplexConnectionInterceptor(Metrics.REGISTRY);
                    MicrometerRSocketInterceptor interactionInterceptor = new MicrometerRSocketInterceptor(Metrics.REGISTRY);
                    registry.forConnection(connectionInterceptor);
                    registry.forRequester(interactionInterceptor);
                    registry.forResponder(interactionInterceptor);
                })
                .keepAlive(keepaliveInterval, keepaliveTimeout)
                .acceptor(SocketAcceptor.with(RpcService.getRpcAcceptor()))
                .connect(transport);
    }

    private void onConnectClosed(Member member, Throwable throwable) {
        String nodeId = member.getNodeId();
        connectionMap.remove(nodeId);
        if (throwable != null) {
            log.error("The connection to member {}[{}:{}] has been closed exceptionally",
                    nodeId,
                    member.getMemberHost(),
                    member.getMemberPort(),
                    throwable);
        } else {
            log.info("The connection to member {}[{}:{}] has been closed",
                    nodeId,
                    member.getMemberHost(),
                    member.getMemberPort());
        }
        for (MemberConnectionChangeListener listener : memberConnectionChangeListeners) {
            listener.onMemberConnectionRemoved(member);
        }
        // Don't use io.rsocket.core.Resume because it's troublesome for our cases
        if (discoveryService.isKnownMember(nodeId)) {
            Mono.delay(reconnectInterval)
                    .subscribe(ignored -> {
                        Member memberToConnect = discoveryService.getAllKnownMembers().get(nodeId);
                        if (memberToConnect != null) {
                            connectMemberUntilSucceedOrRemoved(member);
                        }
                    });
        }
    }

}
