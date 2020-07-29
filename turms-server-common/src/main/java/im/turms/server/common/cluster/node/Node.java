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
import im.turms.server.common.cluster.service.idgen.FlakeIdService;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.cluster.service.serialization.SerializationService;
import im.turms.server.common.manager.address.IServiceAddressManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.property.env.common.cluster.NodeProperties;
import im.turms.server.common.property.env.common.cluster.NodeProperties.NetworkProperties;
import im.turms.server.common.property.env.common.cluster.SharedConfigProperties;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import reactor.netty.ChannelBindException;
import reactor.netty.tcp.TcpServer;

import java.net.InetSocketAddress;

/**
 * The lifecycle of the local node is roughly the same as the local Spring server that communicates with for clients/admins
 *
 * @author James Chen
 */
@Getter
@Log4j2
public class Node {

    @Getter
    private final ApplicationContext context;

    private final NodeType nodeType;

    // Transport

    private final CloseableChannel serverChannel;

    // Services

    private final SharedConfigService sharedConfigService;
    private final SharedPropertyService sharedPropertyService;
    private final DiscoveryService discoveryService;
    private final SerializationService serializationService;
    private final RpcService rpcService;
    private final FlakeIdService flakeIdService;

    public Node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsPropertiesManager turmsPropertiesManager,
            IServiceAddressManager serviceAddressManager) {
        // Prepare node information
        this.context = context;
        this.nodeType = nodeType;
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        ClusterProperties clusterProperties = turmsProperties.getCluster();
        NodeProperties nodeProperties = clusterProperties.getNode();
        SharedConfigProperties sharedConfigProperties = clusterProperties.getSharedConfig();
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
                clusterProperties.getRpc().getInputThreadNumber());
        InetSocketAddress address = serverChannel.address();
        String clusterId = clusterProperties.getId();
        String nodeId = nodeProperties.getId();
        nodeId = StringUtils.isEmpty(nodeId) ? RandomStringUtils.randomAlphanumeric(8) : nodeId;

        // Init services
        // we pass the properties one by one rather than passing the node instance
        // to know their dependency relationships explicitly.
        sharedConfigService = new SharedConfigService(sharedConfigProperties.getMongo().getUri());
        discoveryService = new DiscoveryService(clusterId,
                nodeId,
                nodeType,
                nodeVersion,
                nodeProperties.isActiveByDefault(),
                address.getHostString(),
                address.getPort(),
                clusterProperties.getRpc().getOutputThreadNumber(),
                clusterProperties.getDiscovery(),
                serviceAddressManager,
                sharedConfigService);

        sharedPropertyService = new SharedPropertyService(clusterId, turmsProperties, sharedConfigService);
        serializationService = new SerializationService();
        rpcService = new RpcService(clusterProperties.getRpc(), serializationService, discoveryService);
        // We don't design a data center for turms currently but reserve the dataCenterId value for future use.
        flakeIdService = new FlakeIdService(discoveryService);
    }

    public void start() {
        sharedConfigService.start();
        sharedPropertyService.start();
        discoveryService.start();
        serializationService.start();
        rpcService.start();
        flakeIdService.start();
    }

    public void stop() {
        sharedConfigService.stop();
        sharedPropertyService.stop();
        discoveryService.stop();
        serializationService.stop();
        rpcService.stop();
        flakeIdService.stop();
    }

    // Frequently used methods for external classes

    public String getNodeId() {
        return discoveryService.getLocalMember().getNodeId();
    }

    public TurmsProperties getSharedProperties() {
        return sharedPropertyService.getSharedProperties();
    }

    public boolean isActive() {
        return discoveryService.getLocalNodeStatusManager().getLocalMember().isActive();
    }

    public long nextId(ServiceType serviceType) {
        return flakeIdService.nextId(serviceType);
    }

    public boolean isLocalNodeMaster() {
        return discoveryService.getLocalNodeStatusManager().isLocalNodeMaster();
    }

    public String getLocalMemberId() {
        return discoveryService.getLocalMember().getNodeId();
    }

    // Private

    private CloseableChannel setupLocalDiscoveryServer(String host, int port, boolean autoIncrement, int portCount, int inputThreadNumber) {
        int currentPort = port;
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(inputThreadNumber, new DefaultThreadFactory("member-input"));

        // Loop until the server is set up, or an exception occurs
        while (true) {
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(host, currentPort);
                TcpServer tcpServer = TcpServer.create()
                        .runOn(eventLoopGroup)
                        .bindAddress(() -> inetSocketAddress);
                TcpServerTransport transport = TcpServerTransport.create(tcpServer);
                CloseableChannel channel = RSocketServer
                        .create(SocketAcceptor.with(RpcService.getRpcAcceptor()))
                        .bindNow(transport);
                log.info("The local server {}:{} has been set up", host, currentPort);
                return channel;
            } catch (Exception e) { // port in use
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
