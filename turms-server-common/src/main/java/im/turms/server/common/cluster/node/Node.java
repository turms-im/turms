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

package im.turms.server.common.cluster.node;

import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.SharedPropertyService;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.idgen.IdService;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.cluster.service.serialization.SerializationService;
import im.turms.server.common.manager.address.IServiceAddressManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.property.env.common.cluster.DiscoveryProperties;
import im.turms.server.common.property.env.common.cluster.NodeProperties;
import im.turms.server.common.property.env.common.cluster.NodeProperties.NetworkProperties;
import im.turms.server.common.property.env.common.cluster.SharedConfigProperties;
import im.turms.server.common.util.SslUtil;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.server.Ssl;
import org.springframework.context.ApplicationContext;
import reactor.netty.ChannelBindException;
import reactor.netty.tcp.TcpServer;

import java.net.InetSocketAddress;
import java.util.function.Consumer;

/**
 * The lifecycle of the local node is roughly the same with
 * the local Spring (HTTP/WebSocket) server that communicates with clients/admins
 *
 * @author James Chen
 */
@Getter
@Log4j2
public class Node {

    /**
     * For best performance for im.turms.server.common.log4j.plugin.TurmsContextLookup
     * to access, we use static.
     */
    @Getter
    private static NodeType nodeType;
    @Getter
    private static String nodeId;

    @Getter
    private final ApplicationContext context;

    // Transport

    private final CloseableChannel serverChannel;

    // Services

    private final SharedConfigService sharedConfigService;
    private final SharedPropertyService sharedPropertyService;
    private final DiscoveryService discoveryService;
    private final SerializationService serializationService;
    private final RpcService rpcService;
    private final IdService idService;

    /**
     * @param turmsPropertiesManager is used to get local properties and listen to their changes
     */
    public Node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsPropertiesManager turmsPropertiesManager,
            IServiceAddressManager serviceAddressManager) {
        // Prepare node information
        this.context = context;
        Node.nodeType = nodeType;
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        ClusterProperties clusterProperties = turmsProperties.getCluster();
        NodeProperties nodeProperties = clusterProperties.getNode();
        SharedConfigProperties sharedConfigProperties = clusterProperties.getSharedConfig();
        DiscoveryProperties discoveryProperties = clusterProperties.getDiscovery();
        NetworkProperties networkProperties = nodeProperties.getNetwork();

        NodeVersion nodeVersion;
        try {
            String version = nodeProperties.getVersion();
            nodeVersion = NodeVersion.parse(version);
            log.info("The local node version is {}", version);
        } catch (Exception e) {
            log.error("Failed to get the Turms version of the current node", e);
            throw e;
        }

        // Set up the local server
        RpcService.initRpcAcceptor(context);
        serverChannel = setupLocalDiscoveryServer(networkProperties.getHost(),
                networkProperties.getPort(),
                networkProperties.isAutoIncrement(),
                networkProperties.getPortCount(),
                clusterProperties.getRpc().getInputThreadNumber(),
                discoveryProperties.getServerSsl());
        InetSocketAddress address = serverChannel.address();
        String clusterId = clusterProperties.getId();
        String tempNodeId = nodeProperties.getId();
        if (StringUtils.isBlank(nodeId)) {
            tempNodeId = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
            log.warn("A random node ID {} has been used. You should better set a node ID manually in the production environment", tempNodeId);
        }
        nodeId = tempNodeId;

        // Init services
        // we pass the properties one by one rather than passing the node instance
        // to know their dependency relationships explicitly.
        sharedConfigService = new SharedConfigService(sharedConfigProperties.getMongo().getUri());
        discoveryService = new DiscoveryService(clusterId,
                nodeId,
                nodeType,
                nodeVersion,
                nodeProperties.isActiveByDefault(),
                nodeProperties.isLeaderEligible(),
                address.getHostString(),
                address.getPort(),
                clusterProperties.getRpc().getOutputThreadNumber(),
                discoveryProperties,
                serviceAddressManager,
                sharedConfigService);

        sharedPropertyService = new SharedPropertyService(clusterId, nodeType, turmsPropertiesManager, sharedConfigService);
        serializationService = new SerializationService();
        rpcService = new RpcService(clusterProperties.getRpc(), serializationService, discoveryService);
        idService = new IdService(discoveryService);
    }

    public void start() {
        sharedConfigService.start();
        sharedPropertyService.start();
        discoveryService.start();
        serializationService.start();
        rpcService.start();
        idService.start();
    }

    public void stop() {
        sharedConfigService.stop();
        sharedPropertyService.stop();
        discoveryService.stop();
        serializationService.stop();
        rpcService.stop();
        idService.stop();
    }

    // Frequently used methods for external classes

    public TurmsProperties getSharedProperties() {
        return sharedPropertyService.getSharedProperties();
    }

    public void addPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        sharedPropertyService.addListeners(listener);
    }

    public boolean isActive() {
        return discoveryService.getLocalNodeStatusManager().getLocalMember().isActive();
    }

    public long nextIncreasingId(ServiceType serviceType) {
        return idService.nextIncreasingId(serviceType);
    }

    public long nextRandomId(ServiceType serviceType) {
        return idService.nextRandomId(serviceType);
    }

    public boolean isLocalNodeMaster() {
        return discoveryService.getLocalNodeStatusManager().isLocalNodeMaster();
    }

    public String getLocalMemberId() {
        return discoveryService.getLocalMember().getNodeId();
    }

    // Private

    private CloseableChannel setupLocalDiscoveryServer(String host,
                                                       int port,
                                                       boolean autoIncrement,
                                                       int portCount,
                                                       int inputThreadNumber,
                                                       Ssl serverSsl) {
        int currentPort = port;
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(inputThreadNumber, new DefaultThreadFactory("member-input"));

        // Loop until the server is set up, or an exception occurs
        while (true) {
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(host, currentPort);
                TcpServer tcpServer = TcpServer.create()
                        .runOn(eventLoopGroup)
                        .bindAddress(() -> inetSocketAddress);
                if (serverSsl.isEnabled()) {
                    tcpServer.secure(spec -> SslUtil.configureSslContextSpec(spec, serverSsl, true));
                }
                TcpServerTransport transport = TcpServerTransport.create(tcpServer);
                CloseableChannel channel = RSocketServer
                        .create(SocketAcceptor.with(RpcService.getRpcAcceptor()))
                        .bindNow(transport);
                log.info("The local server {}:{} has been set up", host, currentPort);
                return channel;
            } catch (Exception e) { // e.g. port in use
                if (e instanceof ChannelBindException && autoIncrement && currentPort <= port + portCount) {
                    log.error("Failed to bind on the port {}. Trying to bind on the next port {}", currentPort++, currentPort);
                } else {
                    log.error("Failed to set up the local discovery server", e);
                    throw e;
                }
            }
        }
    }

}
