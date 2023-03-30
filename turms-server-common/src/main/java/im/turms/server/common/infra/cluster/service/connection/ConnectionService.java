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

package im.turms.server.common.infra.cluster.service.connection;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import jakarta.annotation.Nullable;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.channel.MicrometerChannelMetricsRecorder;
import reactor.netty.tcp.TcpClient;

import im.turms.server.common.infra.cluster.service.ClusterService;
import im.turms.server.common.infra.cluster.service.codec.CodecService;
import im.turms.server.common.infra.cluster.service.config.SharedConfigService;
import im.turms.server.common.infra.cluster.service.config.entity.discovery.Member;
import im.turms.server.common.infra.cluster.service.connection.request.ClosingHandshakeRequest;
import im.turms.server.common.infra.cluster.service.connection.request.KeepaliveRequest;
import im.turms.server.common.infra.cluster.service.connection.request.OpeningHandshakeRequest;
import im.turms.server.common.infra.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.infra.cluster.service.discovery.MemberConnectionListener;
import im.turms.server.common.infra.cluster.service.idgen.IdService;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.net.SslUtil;
import im.turms.server.common.infra.property.env.common.SslProperties;
import im.turms.server.common.infra.property.env.common.cluster.connection.ConnectionClientProperties;
import im.turms.server.common.infra.property.env.common.cluster.connection.ConnectionProperties;
import im.turms.server.common.infra.property.env.common.cluster.connection.ConnectionServerProperties;
import im.turms.server.common.infra.thread.NamedThreadFactory;
import im.turms.server.common.infra.thread.ThreadNameConst;

import static im.turms.server.common.infra.metrics.CommonMetricNameConst.NODE_TCP_CLIENT;

/**
 * Responsibilities: Focus on endpoint communication (including network transport) 1. Requested by
 * DiscoveryService to disconnect/Connect to member 2. Reconnect if disconnected unexpectedly 3.
 * Send keepalive probe (via RpcService)
 * <p>
 * Lifecycles of a connection: 1. Creating a TCP connection -> Connected 2. Start opening handshake
 * -> Completed 3. Start receiving data and be ready to send data 4. Start closing handshake ->
 * Completed
 *
 * @author James Chen
 * @implNote Note that ConnectionService has a strong relationship with RpcService because: 1.
 *           ConnectionService isn't just TransportService, and it maintains transport channels
 *           between peers, but also needs to check if channels still healthy by sending keepalive
 *           RPC requests via RpcService. 2. RpcService sends RPC requests and receives RPC
 *           responses, depending on the transport channels provided by ConnectionService.
 *           <p>
 *           We don't make RpcService as a part of ConnectionService because: 1. Decouple RPC
 *           ability from ConnectionService to follow single responsibility principle for better
 *           maintainability 2. It is more nature for users to call "rpcService.requestResponse()"
 *           instead of "connectionService.requestResponse()", which makes it too general in
 *           functionality
 */
public class ConnectionService implements ClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionService.class);

    private final SslProperties clientSsl;
    private final long keepaliveIntervalMillis;
    private final long keepaliveTimeoutMillis;
    private final Duration reconnectInterval;

    // Thread resources
    private final ScheduledExecutorService connectionRetryScheduler;
    private final NioEventLoopGroup eventLoopGroupForClients;
    private final Thread keepaliveThread;

    /**
     * Note that: 1. It is allowed to connect to non-member turms servers. 2. Only after handshake
     * done, a connection can be put in the pool.
     */
    private final Map<String, TurmsConnection> nodeIdToConnection = new ConcurrentHashMap<>();
    /**
     * Never stop reconnecting until the member is removed from the discovery registry
     */
    private final Map<String, Integer> nodeIdToConnectionRetries = new ConcurrentHashMap<>();
    private final Set<String> connectingMembers = ConcurrentHashMap.newKeySet();
    /**
     * Use supplier rather than just listener so that we can bind each generated listener to a
     * specific TcpConnection to make logic simple
     */
    private final List<Supplier<MemberConnectionListener>> memberConnectionListenerSuppliers =
            new ArrayList<>(4);
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
        eventLoopGroupForClients = new NioEventLoopGroup(
                Runtime.getRuntime()
                        .availableProcessors(),
                new DefaultThreadFactory(ThreadNameConst.NODE_CONNECTION_CLIENT_IO));
        connectionRetryScheduler = Executors.newScheduledThreadPool(1,
                new NamedThreadFactory(ThreadNameConst.NODE_CONNECTION_RETRY, true));
        keepaliveThread = NamedThreadFactory
                .newThread(ThreadNameConst.NODE_CONNECTION_KEEPALIVE, true, () -> {
                    sendKeepaliveToConnectionsForever();
                    LOGGER.warn("The node keepalive thread has been stopped");
                });
        keepaliveThread.start();
        server = setupServer();
    }

    @Override
    public Mono<Void> stop(long timeoutMillis) {
        List<Mono<Void>> monos = new LinkedList<>();
        keepaliveThread.interrupt();
        if (server != null) {
            monos.add(server.dispose()
                    .onErrorMap(
                            t -> new RuntimeException("Failed to stop the local node server", t)));
        }
        ClosingHandshakeRequest request = new ClosingHandshakeRequest(
                ClosingHandshakeRequest.CLOSE_STATUS_CODE_SERVER_SHUTTING_DOWN);
        for (TurmsConnection connection : nodeIdToConnection.values()) {
            connection.setClosing(true);
            Connection conn = connection.getConnection();
            if (conn.isDisposed()) {
                continue;
            }
            String nodeId = connection.getNodeId();
            if (nodeId == null) {
                conn.dispose();
                monos.add(conn.onDispose());
            } else {
                monos.add(rpcService.requestResponse(nodeId, request)
                        .onErrorMap(t -> new RuntimeException(
                                "Failed to send the closing handshake to the member: "
                                        + nodeId,
                                t))
                        .flatMap(unused -> {
                            conn.dispose();
                            return conn.onDispose();
                        }));
            }
        }
        nodeIdToConnection.clear();
        try {
            keepaliveThread.join(timeoutMillis);
        } catch (InterruptedException e) {
            // ignored
        }
        return Mono.whenDelayError(monos);
    }

    @Override
    public void lazyInit(
            CodecService codecService,
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
        return nodeIdToConnection.get(memberId);
    }

    public boolean isMemberConnected(String memberId) {
        TurmsConnection connection = nodeIdToConnection.get(memberId);
        return connection != null
                && !connection.getConnection()
                        .isDisposed()
                && !connection.isClosing();
    }

    public synchronized void updateHasConnectedToAllMembers(Set<String> allMemberNodeIds) {
        boolean connectedToAllMembers = true;
        for (String nodeId : allMemberNodeIds) {
            if (!isMemberConnected(nodeId)
                    && !discoveryService.getLocalNodeStatusManager()
                            .isLocalNodeId(nodeId)) {
                connectedToAllMembers = false;
                break;
            }
        }
        hasConnectedToAllMembers = connectedToAllMembers;
    }

    private ConnectionServer setupServer() {
        ConnectionServer server = new ConnectionServer(
                serverProperties.getHost(),
                serverProperties.getPort(),
                serverProperties.isPortAutoIncrement(),
                serverProperties.getPortCount(),
                serverProperties.getSsl(),
                conn -> {
                    TurmsConnection connection =
                            new TurmsConnection(null, conn, false, newMemberConnectionListeners());
                    onMemberConnectionAdded(null, connection);
                });
        server.blockUntilConnect();
        return server;
    }

    private Mono<? extends Connection> initTcpConnection(String host, int port) {
        TcpClient client = TcpClient.newConnection()
                .host(host)
                .port(port)
                .metrics(true, () -> new MicrometerChannelMetricsRecorder(NODE_TCP_CLIENT, "tcp"))
                .runOn(eventLoopGroupForClients);
        if (clientSsl.isEnabled()) {
            client.secure(sslContextSpec -> SslUtil
                    .configureSslContextSpec(sslContextSpec, clientSsl, false));
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
        LOGGER.info("[Client] Connecting to the member: {id={}, host={}, port={}}. Retry times: {}",
                nodeId,
                member.getMemberHost(),
                member.getMemberPort(),
                nodeIdToConnectionRetries.getOrDefault(nodeId, 0));
        initTcpConnection(member.getMemberHost(), member.getMemberPort()).doOnSuccess(conn -> {
            TurmsConnection connection = new TurmsConnection(
                    nodeId,
                    (ChannelOperations<?, ?>) conn,
                    true,
                    newMemberConnectionListeners());
            onMemberConnectionAdded(member, connection);
            String localNodeId = discoveryService.getLocalMember()
                    .getNodeId();
            LOGGER.info(
                    "[Client] Sending an opening handshake request to the member: {id={}, host={}, port={}}",
                    nodeId,
                    member.getMemberHost(),
                    member.getMemberPort());
            rpcService
                    .requestResponse(nodeId,
                            new OpeningHandshakeRequest(localNodeId),
                            null,
                            connection)
                    .subscribe(code -> {
                        if (code == OpeningHandshakeRequest.RESPONSE_CODE_SUCCESS) {
                            onMemberConnectionHandshakeCompleted(member, connection, true);
                        } else {
                            throw new RuntimeException(
                                    "Failure code: "
                                            + code);
                        }
                    }, t -> {
                        LOGGER.error(
                                "[Client] Failed to complete the opening handshake with the member: {id={}, host={}, port={}}. Closing connection to reconnect",
                                nodeId,
                                member.getMemberHost(),
                                member.getMemberPort(),
                                t);
                        // To keep logic simple, just disconnect to
                        // connect and start a handshake again.
                        // After disposed, the listener to onDispose will reconnect
                        disconnectConnection(connection);
                    });
        })
                .onErrorResume(throwable -> {
                    if (!discoveryService.isKnownMember(nodeId)) {
                        return Mono.empty();
                    }
                    int retryTimes = nodeIdToConnectionRetries.getOrDefault(nodeId, 0);
                    LOGGER.error(
                            "[Client] Failed to connect to member: {id={}, host={}, port={}}. Retry times: {}",
                            nodeId,
                            member.getMemberHost(),
                            member.getMemberPort(),
                            retryTimes,
                            throwable);
                    retryTimes++;
                    nodeIdToConnectionRetries.put(nodeId, retryTimes);
                    connectionRetryScheduler.schedule(() -> {
                        if (!isMemberConnected(nodeId) && discoveryService.isKnownMember(nodeId)) {
                            connectMemberUntilSucceedOrRemoved0(member);
                        } else {
                            nodeIdToConnectionRetries.remove(nodeId);
                        }
                    }, Math.min(retryTimes * 10, 60), TimeUnit.SECONDS);
                    return Mono.empty();
                })
                .subscribe();
    }

    private void disconnectConnection(TurmsConnection connection) {
        connection.setClosing(true);
        connection.getConnection()
                .dispose();
    }

    // Keepalive

    public void keepalive(String nodeId) {
        TurmsConnection connection = nodeIdToConnection.get(nodeId);
        if (connection == null) {
            throw new RuntimeException(
                    "Received a keepalive request from a non-connected node: "
                            + nodeId);
        }
        connection.setLastKeepaliveTimestamp(System.currentTimeMillis());
    }

    private void sendKeepaliveToConnectionsForever() {
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            Iterator<Map.Entry<String, TurmsConnection>> iterator = nodeIdToConnection.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                try {
                    sendKeepalive(iterator);
                } catch (Exception e) {
                    LOGGER.error("Caught an error while sending a keepalive request", e);
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
            LOGGER.warn("Reconnecting to the member ({}) due to keepalive timeout", nodeId);
            // onConnectionClosed() will reconnect the member
            disconnectConnection(connection);
            iterator.remove();
            return;
        }
        if (elapsedTime < keepaliveIntervalMillis) {
            return;
        }
        rpcService.requestResponse(nodeId, new KeepaliveRequest())
                .subscribe(null,
                        t -> LOGGER.warn("Failed to send a keepalive request to the member: "
                                + nodeId, t),
                        () -> connection.setLastKeepaliveTimestamp(System.currentTimeMillis()));
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
        TurmsConnection existingConnection = nodeIdToConnection.get(nodeId);
        if (existingConnection != null) {
            if (!existingConnection.getConnection()
                    .isDisposed()) {
                return OpeningHandshakeRequest.RESPONSE_CODE_CONNECTION_ALREADY_EXISTS;
            }
        } else if (connection.getConnection()
                .isDisposed()) {
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
        ArrayList<MemberConnectionListener> list =
                new ArrayList<>(memberConnectionListenerSuppliers.size());
        for (Supplier<MemberConnectionListener> supplier : memberConnectionListenerSuppliers) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * @param member is null when accepting a connection on the server side
     */
    private void onMemberConnectionAdded(@Nullable Member member, TurmsConnection connection) {
        String endpointType = connection.isLocalNodeClient()
                ? "Client"
                : "Server";
        String memberIdAndAddress = getMemberIdAndAddress(connection.getNodeId(), member);
        LOGGER.info("[{}] Connected to the member{}",
                endpointType,
                memberIdAndAddress.isEmpty()
                        ? ""
                        : ": "
                                + memberIdAndAddress);
        for (MemberConnectionListener listener : connection.getListeners()) {
            try {
                listener.onConnectionOpened(connection);
            } catch (Exception e) {
                LOGGER.error("Caught an error while notifying the onConnectionOpened listener: "
                        + listener.getClass()
                                .getName(),
                        e);
            }
        }
        ChannelOperations<?, ?> conn = connection.getConnection();
        conn.receiveObject()
                .doOnNext(value -> {
                    for (MemberConnectionListener listener : connection.getListeners()) {
                        try {
                            listener.onDataReceived(value);
                        } catch (Exception e) {
                            LOGGER.error(
                                    "Caught an error while notifying the onDataReceived listener: "
                                            + listener.getClass()
                                                    .getName(),
                                    e);
                        }
                    }
                })
                .onErrorResume(t -> {
                    if (ThrowableUtil.isDisconnectedClientError(t) && connection.isClosing()) {
                        return Mono.empty();
                    }
                    LOGGER.error("[{}] Failed to listen to the connection to the member{}",
                            endpointType,
                            memberIdAndAddress.isEmpty()
                                    ? ""
                                    : ": "
                                            + memberIdAndAddress,
                            t);
                    return Mono.empty();
                })
                .subscribe();
        conn.onDispose()
                .subscribe(null,
                        t -> onConnectionClosed(connection, t),
                        () -> onConnectionClosed(connection, null));
    }

    private void onConnectionClosed(TurmsConnection connection, @Nullable Throwable throwable) {
        boolean isLocalNodeClient = connection.isLocalNodeClient();
        String nodeType = isLocalNodeClient
                ? "Client"
                : "Server";
        String nodeId = connection.getNodeId();
        Member member = nodeId == null
                ? null
                : discoveryService.getMember(nodeId);
        String memberIdAndAddress = member == null
                ? ""
                : " "
                        + getMemberIdAndAddress(nodeId, member);
        boolean closing = connection.isClosing();
        LogLevel logLevel = closing
                ? LogLevel.INFO
                : LogLevel.WARN;
        if (member == null) {
            LOGGER.log(logLevel,
                    "[{}] The connection to an unknown member has been closed{}",
                    nodeType,
                    closing
                            ? ""
                            : " unexpectedly",
                    throwable);
        } else {
            LOGGER.log(logLevel,
                    "[{}] The connection to the member{} has been closed{}",
                    nodeType,
                    memberIdAndAddress,
                    closing
                            ? ""
                            : " unexpectedly",
                    throwable);
        }
        for (MemberConnectionListener listener : connection.getListeners()) {
            try {
                listener.onConnectionClosed();
            } catch (Exception e) {
                LOGGER.error("Caught an error while notifying the onConnectionClosed listener: "
                        + listener.getClass()
                                .getName(),
                        e);
            }
        }
        boolean isKnownMember = nodeId != null && discoveryService.isKnownMember(nodeId);
        boolean isClosing = discoveryService.getLocalNodeStatusManager()
                .isClosing();
        if (isLocalNodeClient && isKnownMember && !isClosing) {
            Mono.delay(reconnectInterval)
                    .subscribe(ignored -> {
                        Member memberToConnect = discoveryService.getAllKnownMembers()
                                .get(nodeId);
                        if (memberToConnect != null) {
                            connectMemberUntilSucceedOrRemoved(memberToConnect);
                        }
                    });
        } else {
            String reason = !isLocalNodeClient
                    ? "the local node is server"
                    : !isKnownMember
                            ? "the member is unknown"
                            : "the local node is closing";
            LOGGER.info("[{}] Stop to connect the member{} because {}",
                    nodeType,
                    memberIdAndAddress,
                    reason);
        }
    }

    private void onMemberConnectionHandshakeCompleted(
            Member member,
            TurmsConnection connection,
            boolean isLocalNodeClient) {
        String nodeId = member.getNodeId();
        LOGGER.info("[{}] Completed the opening handshake with the member: {}[{}:{}]",
                isLocalNodeClient
                        ? "Client"
                        : "Server",
                nodeId,
                member.getMemberHost(),
                member.getMemberPort());
        nodeIdToConnection.put(nodeId, connection);
        nodeIdToConnectionRetries.remove(nodeId);
        connectingMembers.remove(nodeId);
        updateHasConnectedToAllMembers(discoveryService.getAllKnownMembers()
                .keySet());
        for (MemberConnectionListener listener : connection.getListeners()) {
            try {
                listener.onOpeningHandshakeCompleted(member);
            } catch (Exception e) {
                LOGGER.error(
                        "Caught an error while notifying the onOpeningHandshakeCompleted listener: "
                                + listener.getClass()
                                        .getName(),
                        e);
            }
        }
    }

    private String getMemberIdAndAddress(@Nullable String nodeId, @Nullable Member member) {
        if (member == null) {
            return nodeId == null
                    ? ""
                    : "{id="
                            + nodeId
                            + "}";
        }
        return "{id="
                + member.getNodeId()
                + ", host="
                + member.getMemberHost()
                + ", port="
                + member.getMemberPort()
                + "}";
    }

}