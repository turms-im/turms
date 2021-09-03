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

import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.codec.CodecService;
import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.SharedPropertyService;
import im.turms.server.common.cluster.service.connection.ConnectionService;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.idgen.IdService;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.manager.address.BaseServiceAddressManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.property.env.common.cluster.DiscoveryProperties;
import im.turms.server.common.property.env.common.cluster.NodeProperties;
import im.turms.server.common.property.env.common.cluster.RpcProperties;
import im.turms.server.common.property.env.common.cluster.SharedConfigProperties;
import im.turms.server.common.property.env.common.cluster.connection.ConnectionProperties;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.function.Consumer;

/**
 * The lifecycle of the local node is roughly the same with
 * the local Spring (TCP/UDP/HTTP/WebSocket) server that communicates with clients/admins
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
    private static String nodeId;
    private final NodeType nodeType;

    /**
     * Context
     */
    private final ApplicationContext context;

    /**
     * Services
     */
    private final SharedConfigService sharedConfigService;
    private final SharedPropertyService sharedPropertyService;
    private final CodecService codecService;
    private final ConnectionService connectionService;
    private final DiscoveryService discoveryService;
    private final RpcService rpcService;
    private final IdService idService;

    /**
     * @param turmsPropertiesManager is used to get local properties and listen to their changes
     */
    public Node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsApplicationContext turmsContext,
            TurmsPropertiesManager turmsPropertiesManager,
            BaseServiceAddressManager serviceAddressManager) {
        // Prepare node information
        this.context = context;
        this.nodeType = nodeType;
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        ClusterProperties clusterProperties = turmsProperties.getCluster();
        SharedConfigProperties sharedConfigProperties = clusterProperties.getSharedConfig();
        NodeProperties nodeProperties = clusterProperties.getNode();
        ConnectionProperties connectionProperties = clusterProperties.getConnection();
        DiscoveryProperties discoveryProperties = clusterProperties.getDiscovery();
        RpcProperties rpcProperties = clusterProperties.getRpc();

        NodeVersion nodeVersion;
        try {
            String version = turmsContext.getVersion();
            nodeVersion = NodeVersion.parse(version);
            log.info("The local node version is {}", version);
        } catch (Exception e) {
            log.error("Failed to get the Turms version of the current node", e);
            throw e;
        }

        String clusterId = clusterProperties.getId();
        nodeId = initNodeId(nodeProperties.getId());
        String zone = nodeProperties.getZone();
        if (zone == null) {
            zone = "";
        }

        // Init services
        // pass the properties one by one rather than passing the node instance
        // to know their dependency relationships explicitly.
        codecService = new CodecService();
        connectionService = new ConnectionService(connectionProperties);
        rpcService = new RpcService(context, nodeType, rpcProperties);
        sharedConfigService = new SharedConfigService(sharedConfigProperties.getMongo());
        discoveryService = new DiscoveryService(clusterId,
                nodeId,
                zone,
                nodeType,
                nodeVersion,
                nodeType == NodeType.SERVICE && nodeProperties.isLeaderEligible(),
                nodeProperties.isActiveByDefault(),
                connectionService.getServer().getPort(),
                discoveryProperties,
                serviceAddressManager,
                sharedConfigService);
        sharedPropertyService = new SharedPropertyService(clusterId, nodeType, turmsPropertiesManager);
        idService = new IdService(discoveryService);

        List<ClusterService> allServices = List.of(
                connectionService,
                discoveryService,
                sharedConfigService,
                sharedPropertyService,
                codecService,
                rpcService,
                idService);
        for (ClusterService service : allServices) {
            service.lazyInit(codecService,
                    connectionService,
                    discoveryService,
                    idService,
                    rpcService,
                    sharedConfigService);
        }
    }

    public static synchronized String initNodeId(String id) {
        if (nodeId != null) {
            return nodeId;
        }
        if (StringUtils.isBlank(id)) {
            id = RandomStringUtils.randomAlphabetic(8).toLowerCase();
            log.warn("A random node ID {} has been used. You should better set a node ID manually in the production environment",
                    id);
        } else {
            if (id.length() > NodeProperties.NODE_ID_MAX_LENGTH) {
                throw new IllegalArgumentException(
                        "The length of node ID must be less than or equals to " + NodeProperties.NODE_ID_MAX_LENGTH);
            }
            if (!id.matches("^[a-zA-Z_]\\w*$")) {
                throw new IllegalArgumentException("The node ID must start with a letter or underscore, " +
                        "and matches zero or more of characters [a-zA-Z0-9_] after the beginning");
            }
        }
        nodeId = id;
        return nodeId;
    }

    public void start() {
        sharedConfigService.start();
        sharedPropertyService.start();
        discoveryService.start();
        codecService.start();
        connectionService.start();
        rpcService.start();
        idService.start();
    }

    public void stop() {
        List<ClusterService> services = List.of(
                // Note that discoveryService should be stopped before sharedConfigService
                // because discoveryService needs to unregister the local member info in the shared config
                discoveryService,
                sharedConfigService,
                sharedPropertyService,
                codecService,
                rpcService,
                idService,
                connectionService);
        for (ClusterService service : services) {
            shutdownService(service);
        }
    }

    // Frequently used methods for external classes

    public TurmsProperties getSharedProperties() {
        return sharedPropertyService.getSharedProperties();
    }

    public void addPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        sharedPropertyService.addListeners(listener);
    }

    public boolean isActive() {
        return discoveryService.getLocalNodeStatusManager().getLocalMember().getStatus().isActive();
    }

    public long nextIncreasingId(ServiceType serviceType) {
        return idService.nextIncreasingId(serviceType);
    }

    public long nextRandomId(ServiceType serviceType) {
        return idService.nextRandomId(serviceType);
    }

    public boolean isLocalNodeLeader() {
        return discoveryService.getLocalNodeStatusManager().isLocalNodeLeader();
    }

    public String getLocalMemberId() {
        return discoveryService.getLocalMember().getNodeId();
    }

    // Private

    private void shutdownService(ClusterService service) {
        try {
            service.stop();
        } catch (Exception e) {
            log.error("Failed to stop service {}", service.getClass().getName(), e);
        }
    }

}
