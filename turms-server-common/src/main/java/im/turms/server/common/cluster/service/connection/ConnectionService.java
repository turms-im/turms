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

package im.turms.server.common.cluster.service.connection;

import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.codec.CodecService;
import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.connection.request.ClosingHandshakeRequest;
import im.turms.server.common.cluster.service.connection.request.KeepaliveRequest;
import im.turms.server.common.cluster.service.connection.request.OpeningHandshakeRequest;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.discovery.MemberConnectionListener;
import im.turms.server.common.cluster.service.idgen.IdService;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.property.env.common.cluster.connection.ConnectionClientProperties;
import im.turms.server.common.property.env.common.cluster.connection.ConnectionProperties;
import im.turms.server.common.property.env.common.cluster.connection.ConnectionServerProperties;
import im.turms.server.common.util.ExceptionUtil;
import im.turms.server.common.util.SslUtil;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.boot.web.server.Ssl;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.tcp.TcpClient;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static im.turms.server.common.cluster.service.connection.request.ClosingHandshakeRequest.CLOSE_STATUS_CODE_SERVER_SHUTTING_DOWN;

/**
 * Responsibilities: Focus on endpoint communication (including network transport)
 * 1. Requested by DiscoveryService to disconnect/Connect to member
 * 2. Reconnect if disconnected unexpectedly
 * 3. Send keepalive probe (via RpcService)
 * <p>
 * Lifecycles of a connection:
 * 1. Creating a TCP connection -> Connected
 * 2. Start opening handshake -> Completed
 * 3. Start receiving data and be ready to send data
 * 4. Start closing handshake -> Completed
 *
 * @author James Chen
 * @implNote Note that ConnectionService has a strong relationship with RpcService because:
 * 1. ConnectionService isn't just TransportService, and it maintains transport channels between peers,
 * but also needs to check if channels still healthy by sending keepalive RPC requests via RpcService.
 * 2. RpcService sends RPC requests and receives RPC responses, depending on the transport channels provided by ConnectionService.
 * <p>
 * We don't make RpcService as a part of ConnectionService because:
 * 1. Decouple RPC ability from ConnectionService to follow single responsibility principle for better maintainability
 * 2. It's more nature for users to call "rpcService.requestResponse()" instead of "connectionService.requestResponse()",
 * which makes it too general in functionality
 */
@Log4j2
public class ConnectionService implements ClusterService {

    private final Ssl clientSsl;
    private final long keepaliveIntervalMillis;
    private final long keepaliveTimeoutMillis;
    private final Duration reconnectInterval;

    // Thread resources
    private final ScheduledExecutorService connectionRetryScheduler;
    private final NioEventLoopGroup eventLoopGroupForClients;
    private final Thread keepaliveThread;

    /**
     * Note that it is allowed to connect to non-member turms servers
     */
    private final Map<String, TurmsConnection> connectionPool = new ConcurrentHashMap<>();
    /**
     * Address -> Retry times.
     * Never stop reconnecting until the member is removed from the discovery registry
     */
    private final Map<String, Integer> connectionRetryTimesMap = new ConcurrentHashMap<>();
    private final Set<String> connectingMembers = ConcurrentHashMap.newKeySet();
    /**
     * Use supplier rather than just listener so that we can bind each generated listener
     * to a specific TcpConnection to make logic simple
     */
    private final List<Supplier<MemberConnectionListener>> memberConnectionListenerSuppliers = new ArrayList<>(4);
    private DiscoveryService discoveryService;
    private RpcService rpcService;
    @Getter
    private boolean hasConnectedToAllMembers;

    private final ConnectionServerProperties serverProperties;
    @Getter
    private final ConnectionServer server;

    public ConnectionService(ConnectionProperties connectionProperties) {
        serverProperties = connectionProperties.getServer();
        ConnectionClientProperties clientProperties = connectionProperties.getClient();
        clientSsl = clientProperties.getSsl();
        keepaliveIntervalMillis = clientProperties.getKeepaliveIntervalSeconds() * 1000L;
        keepaliveTimeoutMillis = clientProperties.getKeepaliveTimeoutSeconds() * 1000L;
        reconnectInterval = Duration.ofSeconds(clientProperties.getReconnectIntervalSeconds());
        eventLoopGroupForClients = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(),
                new DefaultThreadFactory("turms-cluster-connection-client-io"));
        connectionRetryScheduler =
                Executors.newScheduledThreadPool(1, new DefaultThreadFactory("turms-cluster-connection-retry", true));
        keepaliveThread = new DefaultThreadFactory("turms-cluster-connection-keepalive", true)
                .newThread(() -> {
                    sendKeepaliveToConnectionsForever();
                    log.warn("Keepalive thread has been stopped");
                });
        keepaliveThread.start();
        server = setupServer();
    }

    @Override
    public void stop() {
        keepaliveThread.interrupt();
        if (server != null) {
            try {
                server.dispose();
            } catch (Exception e) {
                log.error("Failed to stop the local server", e);
            }
        }
        ClosingHandshakeRequest request = new ClosingHandshakeRequest(CLOSE_STATUS_CODE_SERVER_SHUTTING_DOWN);
        for (TurmsConnection connection : connectionPool.values()) {
            connection.setClosing(true);
            Connection conn = connection.getConnection();
            if (conn.isDisposed()) {
                continue;
            }
            String nodeId = connection.getNodeId();
            if (nodeId == null) {
                conn.dispose();
            } else {
                rpcService.requestResponse(nodeId, request)
                        .onErrorResume(t -> {
                            log.error("Failed to send a closing handshake request", t);
                            return Mono.empty();
                        })
                        .doOnTerminate(conn::dispose)
                        .subscribe();
            }
        }
        connectionPool.clear();
    }

    @Override
    public void lazyInit(CodecService codecService,
                         ConnectionService connectionService,
                         DiscoveryService discoveryService,
                         IdService idService,
                         RpcService rpcService,
                         SharedConfigService sharedConfigService) {
        this.discoveryService = discoveryService;
        this.rpcService = rpcService;
    }

    // Connect/Disconnect

    @Nullable
    public TurmsConnection getMemberConnection(String memberId) {
        return connectionPool.get(memberId);
    }

    public boolean isMemberConnected(String memberId) {
        TurmsConnection connection = connectionPool.get(memberId);
        return connection != null && !connection.getConnection().isDisposed();
    }

    public synchronized void updateHasConnectedToAllMembers(Set<String> allMemberNodeIds) {
        boolean connectedToAllMembers = true;
        for (String nodeId : allMemberNodeIds) {
            if (!isMemberConnected(nodeId) && !discoveryService.getLocalNodeStatusManager().isLocalNodeId(nodeId)) {
                connectedToAllMembers = false;
                break;
            }
        }
        hasConnectedToAllMembers = connectedToAllMembers;
    }

    private ConnectionServer setupServer() {
        ConnectionServer server = new ConnectionServer(serverProperties.getHost(),
                serverProperties.getPort(),
                serverProperties.isPortAutoIncrement(),
                serverProperties.getPortCount(),
                serverProperties.getSsl(),
                conn -> {
                    TurmsConnection connection = new TurmsConnection(null, conn, false, newMemberConnectionListeners());
                    onMemberConnectionAdded(null, connection);
                });
        server.blockUntilConnect();
        return server;
    }

    private Mono<? extends Connection> initTcpConnection(String host, int port) {
        TcpClient client = TcpClient.newConnection()
                .host(host)
                .port(port)
                .metrics(true)
                .runOn(eventLoopGroupForClients);
        if (clientSsl.isEnabled()) {
            client.secure(sslContextSpec -> SslUtil.configureSslContextSpec(sslContextSpec, clientSsl, false));
        }
        return client.connect();
    }

    public void connectMemberUntilSucceedOrRemoved(Member member) {
        String nodeId = member.getNodeId();
        if (!member.isSameNode(discoveryService.getLocalMember())
                && !isMemberConnected(nodeId)
                && connectingMembers.add(nodeId)) {
            connectMemberUntilSucceedOrRemoved0(member);
        }
    }

    private void connectMemberUntilSucceedOrRemoved0(Member member) {
        String nodeId = member.getNodeId();
        log.info("[Client] Connecting to member: {}[{}:{}]. Retry times: {}",
                nodeId,
                member.getMemberHost(),
                member.getMemberPort(),
                connectionRetryTimesMap.getOrDefault(nodeId, 0));
        initTcpConnection(member.getMemberHost(), member.getMemberPort())
                .doOnSuccess(conn -> {
                    TurmsConnection connection =
                            new TurmsConnection(nodeId, (ChannelOperations<?, ?>) conn, true, newMemberConnectionListeners());
                    onMemberConnectionAdded(member, connection);
                    String localNodeId = discoveryService.getLocalMember().getNodeId();
                    log.info("[Client] Sending a open handshake request to member: {}[{}:{}]",
                            nodeId, member.getMemberHost(), member.getMemberPort());
                    rpcService.requestResponse(nodeId, new OpeningHandshakeRequest(localNodeId), null, connection)
                            .doOnSuccess(unused -> onMemberConnectionHandshakeCompleted(member, connection, true))
                            .onErrorResume(throwable -> {
                                log.error("[Client] Failed to complete handshake with member: {}[{}:{}]. Closing connection to reconnect",
                                        nodeId, member.getMemberHost(), member.getMemberPort(), throwable);
                                // To keep logic simple, just disconnect to
                                // connect and start a handshake again.
                                // After disposed, the listener to onDispose will reconnect
                                disconnectConnection(connection);
                                return Mono.empty();
                            })
                            .subscribe();
                })
                .onErrorResume(throwable -> {
                    if (!discoveryService.isKnownMember(nodeId)) {
                        return Mono.empty();
                    }
                    int retryTimes = connectionRetryTimesMap.getOrDefault(nodeId, 0);
                    log.error("[Client] Failed to connect to member: {}[{}:{}]. Retry times: {}",
                            nodeId, member.getMemberHost(), member.getMemberPort(), retryTimes, throwable);
                    retryTimes++;
                    connectionRetryTimesMap.put(nodeId, retryTimes);
                    connectionRetryScheduler.schedule(() -> {
                        if (!isMemberConnected(nodeId) && discoveryService.isKnownMember(nodeId)) {
                            connectMemberUntilSucceedOrRemoved0(member);
                        } else {
                            connectionRetryTimesMap.remove(nodeId);
                        }
                    }, Math.min(retryTimes * 10, 60), TimeUnit.SECONDS);
                    return Mono.empty();
                })
                .subscribe();
    }

    private void disconnectConnection(TurmsConnection connection) {
        connection.setClosing(true);
        connection.getConnection().dispose();
    }

    // Keepalive

    public void keepalive(String nodeId) {
        TurmsConnection connection = connectionPool.get(nodeId);
        connection.setLastKeepaliveTimestamp(System.currentTimeMillis());
    }

    private void sendKeepaliveToConnectionsForever() {
        while (!Thread.currentThread().isInterrupted()) {
            Iterator<Map.Entry<String, TurmsConnection>> iterator = connectionPool.entrySet().iterator();
            while (iterator.hasNext()) {
                try {
                    sendKeepalive(iterator);
                } catch (Exception e) {
                    log.error("Caught an exception when sending keepalive", e);
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void sendKeepalive(Iterator<Map.Entry<String, TurmsConnection>> iterator) {
        Map.Entry<String, TurmsConnection> entry = iterator.next();
        String nodeId = entry.getKey();
        TurmsConnection connection = entry.getValue();
        Connection conn = connection.getConnection();
        if (conn.isDisposed()) {
            iterator.remove();
            return;
        }
        if (!connection.isLocalNodeClient()) {
            return;
        }
        long now = System.currentTimeMillis();
        long elapsedTime = now - connection.getLastKeepaliveTimestamp();
        if (elapsedTime > keepaliveTimeoutMillis) {
            log.warn("Reconnecting to the member {} due to keepalive timeout", nodeId);
            // onConnectionClosed() will reconnect the member
            disconnectConnection(connection);
            iterator.remove();
            return;
        }
        if (elapsedTime < keepaliveIntervalMillis) {
            return;
        }
        try {
            rpcService.requestResponse(nodeId, new KeepaliveRequest())
                    .doOnSuccess(unused -> connection.setLastKeepaliveTimestamp(System.currentTimeMillis()))
                    .onErrorResume(t -> {
                        log.warn("Failed to send a keepalive request to the member " + nodeId, t);
                        return Mono.empty();
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Failed to send a keepalive request to the member " + nodeId, e);
        }
    }

    // Handshake

    public byte handleClosingHandshakeRequest(TurmsConnection connection) {
        connection.setClosing(true);
        for (MemberConnectionListener listener : connection.getListeners()) {
            listener.onClosingHandshakeCompleted();
        }
        return ClosingHandshakeRequest.RESPONSE_CODE_SUCCESS;
    }

    public byte handleHandshakeRequest(TurmsConnection connection, String nodeId) {
        Member member = discoveryService.getMember(nodeId);
        if (member == null) {
            return OpeningHandshakeRequest.RESPONSE_CODE_UNKNOWN_MEMBER;
        }
        TurmsConnection existingConnection = connectionPool.get(nodeId);
        if (existingConnection != null) {
            if (!existingConnection.getConnection().isDisposed()) {
                return OpeningHandshakeRequest.RESPONSE_CODE_CONNECTION_ALREADY_EXISTS;
            }
        } else if (connection.getConnection().isDisposed()) {
            onConnectionClosed(connection, null);
            return OpeningHandshakeRequest.RESPONSE_CODE_CONNECTION_CLOSED;
        }
        connection.setNodeId(nodeId);
        onMemberConnectionHandshakeCompleted(member, connection, false);
        return OpeningHandshakeRequest.RESPONSE_CODE_SUCCESS;
    }

    // Lifecycle listeners

    public void addMemberConnectionListenerSupplier(Supplier<MemberConnectionListener> supplier) {
        memberConnectionListenerSuppliers.add(supplier);
    }

    private List<MemberConnectionListener> newMemberConnectionListeners() {
        ArrayList<MemberConnectionListener> list = new ArrayList<>(memberConnectionListenerSuppliers.size());
        for (Supplier<MemberConnectionListener> supplier : memberConnectionListenerSuppliers) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * @param member is null when accepting a connection on the server side
     */
    private void onMemberConnectionAdded(@Nullable Member member, TurmsConnection connection) {
        String endpointType = connection.isLocalNodeClient() ? "Client" : "Server";
        String memberIdAndAddress = getMemberIdAndAddress(member);
        log.info("[{}] Connected to member{}",
                endpointType,
                member == null ? "" : ": " + memberIdAndAddress);
        for (MemberConnectionListener listener : connection.getListeners()) {
            try {
                listener.onConnectionOpen(connection);
            } catch (Exception e) {
                log.error("Caught an error when invoking onConnectionOpen listeners", e);
            }
        }
        ChannelOperations<?, ?> conn = connection.getConnection();
        conn.receiveObject()
                .doOnNext(value -> {
                    for (MemberConnectionListener listener : connection.getListeners()) {
                        try {
                            listener.onDataReceived(value);
                        } catch (Exception e) {
                            log.error("Caught an error when invoking onDataReceived listeners", e);
                        }
                    }
                })
                .onErrorResume(t -> {
                    if (ExceptionUtil.isDisconnectedClientError(t) && connection.isClosing()) {
                        return Mono.empty();
                    }
                    log.error("[{}] Failed to listen to the connection to the member{}",
                            endpointType,
                            member == null ? "" : ": " + memberIdAndAddress,
                            t);
                    return Mono.empty();
                })
                .subscribe();
        conn.onDispose()
                .doOnSuccess(ignored -> onConnectionClosed(connection, null))
                .onErrorResume(throwable -> {
                    onConnectionClosed(connection, throwable);
                    return Mono.empty();
                })
                .subscribe();
    }

    private void onConnectionClosed(TurmsConnection connection, @Nullable Throwable throwable) {
        String nodeId = connection.getNodeId();
        Member member = discoveryService.getMember(nodeId);
        Level logLevel = connection.isClosing() ? Level.INFO : Level.WARN;
        log.log(logLevel, "[{}] The connection to a member has been closed{}{}",
                connection.isLocalNodeClient() ? "Client" : "Server",
                connection.isClosing() ? "" : " unexpectedly",
                member == null ? "" : ": " + getMemberIdAndAddress(member),
                throwable);
        for (MemberConnectionListener listener : connection.getListeners()) {
            try {
                listener.onConnectionClosed();
            } catch (Exception e) {
                log.error("Caught an error when invoking onConnectionClosed listeners", e);
            }
        }
        if (connection.isLocalNodeClient()
                && discoveryService.isKnownMember(nodeId)
                && !discoveryService.getLocalNodeStatusManager().isClosing()) {
            Mono.delay(reconnectInterval)
                    .subscribe(ignored -> {
                        Member memberToConnect = discoveryService.getAllKnownMembers().get(nodeId);
                        if (memberToConnect != null) {
                            connectMemberUntilSucceedOrRemoved(memberToConnect);
                        }
                    });
        }
    }

    private void onMemberConnectionHandshakeCompleted(Member member, TurmsConnection connection, boolean isLocalNodeClient) {
        String nodeId = member.getNodeId();
        log.info("[{}] Completed the handshake with member: {}[{}:{}]",
                isLocalNodeClient ? "Client" : "Server",
                nodeId,
                member.getMemberHost(),
                member.getMemberPort());
        connectionPool.put(nodeId, connection);
        connectionRetryTimesMap.remove(nodeId);
        connectingMembers.remove(nodeId);
        updateHasConnectedToAllMembers(discoveryService.getAllKnownMembers().keySet());
        for (MemberConnectionListener listener : connection.getListeners()) {
            try {
                listener.onOpeningHandshakeCompleted(member);
            } catch (Exception e) {
                log.error("Caught an error when invoking onOpeningHandshakeCompleted listeners", e);
            }
        }
    }

    private String getMemberIdAndAddress(Member member) {
        if (member == null) {
            return "";
        }
        return member.getNodeId() + "[" + member.getMemberHost() + ":" + member.getMemberPort() + "]";
    }

}