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

package im.turms.server.common.infra.cluster.node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.cluster.service.ClusterService;
import im.turms.server.common.infra.cluster.service.codec.CodecService;
import im.turms.server.common.infra.cluster.service.config.SharedConfigService;
import im.turms.server.common.infra.cluster.service.config.SharedPropertyService;
import im.turms.server.common.infra.cluster.service.connection.ConnectionService;
import im.turms.server.common.infra.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.infra.cluster.service.idgen.IdService;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.context.ApplicationEnvironmentEventListener;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.healthcheck.HealthCheckManager;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.infra.property.env.common.cluster.DiscoveryProperties;
import im.turms.server.common.infra.property.env.common.cluster.NodeProperties;
import im.turms.server.common.infra.property.env.common.cluster.RpcProperties;
import im.turms.server.common.infra.property.env.common.cluster.SharedConfigProperties;
import im.turms.server.common.infra.property.env.common.cluster.connection.ConnectionProperties;

/**
 * The lifecycle of the local node is roughly the same with the local TCP/UDP/HTTP/WebSocket server
 * that communicates with clients/admins
 *
 * @author James Chen
 */
@Getter
public class Node {

    private static final Logger LOGGER = LoggerFactory.getLogger(Node.class);

    /**
     * We use static because: 1. The logger
     * {@link ApplicationEnvironmentEventListener#configureContextForLogging} needs the node ID to
     * initialize for logging before the node instance is created. 2. To avoid initializing the node
     * ID twice, one for logger, another for the node instance because there is a risk that the
     * logger and the node uses different node IDs.
     */
    @Getter
    private static String nodeId;
    @Getter
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
     * @param propertiesManager is used to get local properties and listen to their changes
     */
    public Node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsApplicationContext turmsContext,
            TurmsPropertiesManager propertiesManager,
            BaseServiceAddressManager serviceAddressManager,
            HealthCheckManager healthCheckManager) {
        // Prepare node information
        this.context = context;
        this.nodeType = nodeType;
        TurmsProperties turmsProperties = propertiesManager.getLocalProperties();
        ClusterProperties clusterProperties = turmsProperties.getCluster();
        SharedConfigProperties sharedConfigProperties = clusterProperties.getSharedConfig();
        NodeProperties nodeProperties = clusterProperties.getNode();
        ConnectionProperties connectionProperties = clusterProperties.getConnection();
        DiscoveryProperties discoveryProperties = clusterProperties.getDiscovery();
        RpcProperties rpcProperties = clusterProperties.getRpc();

        String version = turmsContext.getBuildProperties()
                .version();
        NodeVersion nodeVersion = NodeVersion.parse(version);
        LOGGER.info("The local node version is: {}", version);

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
        discoveryService = new DiscoveryService(
                clusterId,
                nodeId,
                zone,
                nodeType,
                nodeVersion,
                nodeType == NodeType.SERVICE && nodeProperties.isLeaderEligible(),
                nodeProperties.getPriority(),
                nodeProperties.isActiveByDefault(),
                healthCheckManager.isHealthy(),
                connectionService.getServer()
                        .getPort(),
                discoveryProperties,
                serviceAddressManager,
                sharedConfigService);
        sharedPropertyService = new SharedPropertyService(clusterId, nodeType, propertiesManager);
        idService = new IdService(discoveryService);

        List<ClusterService> allServices = List.of(connectionService,
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
            id = RandomStringUtils.randomAlphabetic(8)
                    .toLowerCase();
            LOGGER.warn(
                    "A random node ID ({}) has been used. You should better set a node ID manually in production",
                    id);
        } else {
            if (id.length() > NodeProperties.NODE_ID_MAX_LENGTH) {
                throw new IllegalArgumentException(
                        "The length of node ID must be less than or equal to "
                                + NodeProperties.NODE_ID_MAX_LENGTH);
            }
            if (!id.matches("^[a-zA-Z_]\\w*$")) {
                throw new IllegalArgumentException(
                        "The node ID must start with a letter or underscore, "
                                + "and matches zero or more of characters [a-zA-Z0-9_] after the beginning");
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

    public Mono<Void> stop(long timeoutMillis) {
        List<ClusterService> services = List.of(
                // Note that discoveryService should be stopped before sharedConfigService
                // because discoveryService needs to unregister the local member info in the shared
                // config
                discoveryService,
                sharedConfigService,
                sharedPropertyService,
                codecService,
                rpcService,
                idService,
                connectionService);
        List<Mono<Void>> monos = new ArrayList<>(services.size());
        for (ClusterService service : services) {
            try {
                Mono<Void> stop = service.stop(timeoutMillis);
                monos.add(stop);
            } catch (Exception e) {
                monos.add(Mono.error(new RuntimeException(
                        "Caught an error while stopping the service: "
                                + service.getClass()
                                        .getName(),
                        e)));
            }
        }
        return Mono.zipDelayError(monos, Function.identity())
                .then();
    }

    // Frequently used methods for external classes

    public TurmsProperties getSharedProperties() {
        return sharedPropertyService.getSharedProperties();
    }

    public Mono<Void> updateSharedProperties(TurmsProperties properties) {
        return sharedPropertyService.updateSharedProperties(properties);
    }

    public void addPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        sharedPropertyService.addChangeListener(listener);
    }

    public boolean isActive() {
        return discoveryService.getLocalNodeStatusManager()
                .getLocalMember()
                .getStatus()
                .isActive();
    }

    public long nextIncreasingId(ServiceType serviceType) {
        return idService.nextIncreasingId(serviceType);
    }

    public long nextLargeGapId(ServiceType serviceType) {
        return idService.nextLargeGapId(serviceType);
    }

    public boolean isLocalNodeLeader() {
        return discoveryService.getLocalNodeStatusManager()
                .isLocalNodeLeader();
    }

    public String getLocalMemberId() {
        return discoveryService.getLocalMember()
                .getNodeId();
    }

}
