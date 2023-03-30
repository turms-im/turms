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

package im.turms.server.common.infra.cluster.service.config;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.mongodb.client.model.changestream.FullDocument;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.service.ClusterService;
import im.turms.server.common.infra.cluster.service.codec.CodecService;
import im.turms.server.common.infra.cluster.service.config.entity.property.CommonProperties;
import im.turms.server.common.infra.cluster.service.config.entity.property.SharedClusterProperties;
import im.turms.server.common.infra.cluster.service.connection.ConnectionService;
import im.turms.server.common.infra.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.infra.cluster.service.idgen.IdService;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;

/**
 * @author James Chen
 */
public class SharedPropertyService implements ClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SharedPropertyService.class);

    private final String clusterId;
    private final NodeType nodeType;

    private final TurmsPropertiesManager propertiesManager;
    private SharedClusterProperties sharedClusterProperties;
    private SharedConfigService sharedConfigService;

    private final List<Consumer<TurmsProperties>> propertiesChangeListeners = new LinkedList<>();

    public SharedPropertyService(
            String clusterId,
            NodeType nodeType,
            TurmsPropertiesManager propertiesManager) {
        this.clusterId = clusterId;
        this.nodeType = nodeType;
        this.propertiesManager = propertiesManager;
    }

    public TurmsProperties getSharedProperties() {
        return sharedClusterProperties.getTurmsProperties();
    }

    @Override
    public void lazyInit(
            CodecService codecService,
            ConnectionService connectionService,
            DiscoveryService discoveryService,
            IdService idService,
            RpcService rpcService,
            SharedConfigService sharedConfigService) {
        this.sharedConfigService = sharedConfigService;
    }

    @Override
    public void start() {
        sharedConfigService.subscribe(SharedClusterProperties.class, FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    SharedClusterProperties changedProperties = event.getFullDocument();
                    String changeClusterId = changedProperties != null
                            ? changedProperties.getClusterId()
                            : ChangeStreamUtil.getIdAsString(event.getDocumentKey());
                    if (changeClusterId.equals(clusterId)) {
                        switch (event.getOperationType()) {
                            case INSERT, REPLACE, UPDATE -> {
                                sharedClusterProperties = changedProperties;
                                notifyListeners(sharedClusterProperties.getTurmsProperties());
                            }
                            case INVALIDATE -> {
                                LOGGER.warn(
                                        "The shared properties has been deleted in MongoDB unexpectedly");
                                initializeSharedProperties().subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while initializing the shared properties",
                                                t));
                            }
                            default -> {
                            }
                        }
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event of SharedProperties: {}",
                        o,
                        throwable))
                .subscribe();
        try {
            initializeSharedProperties().block(DurationConst.ONE_MINUTE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize the shared properties", e);
        }
    }

    /**
     * @implNote We don't support the partial update by {@link java.util.Map} because there is not
     *           an efficient way to update nested objects of a document in MongoDB.
     */
    public Mono<Void> updateSharedProperties(TurmsProperties turmsProperties) {
        LOGGER.info("Sharing new turms properties to all members");
        SharedClusterProperties clusterProperties =
                getClusterProperties(sharedClusterProperties, turmsProperties);
        Date now = new Date();
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, clusterId)
                .lt(SharedClusterProperties.Fields.lastUpdatedTime, now);
        Update update = Update.newBuilder(3)
                .set(SharedClusterProperties.Fields.commonProperties,
                        clusterProperties.getCommonProperties());
        if (clusterProperties.getGatewayProperties() != null) {
            update.set(SharedClusterProperties.Fields.gatewayProperties,
                    clusterProperties.getGatewayProperties());
        }
        if (clusterProperties.getServiceProperties() != null) {
            update.set(SharedClusterProperties.Fields.serviceProperties,
                    clusterProperties.getServiceProperties());
        }
        return sharedConfigService.upsert(filter, update, clusterProperties)
                .doOnError(e -> LOGGER.error("Failed to share new turms properties", e))
                .then(Mono.defer(() -> {
                    sharedClusterProperties = clusterProperties;
                    LOGGER.info("Shared new turms properties");
                    return Mono.empty();
                }));
    }

    public void addChangeListener(Consumer<TurmsProperties> listener) {
        propertiesChangeListeners.add(listener);
    }

    private void notifyListeners(TurmsProperties properties) {
        for (Consumer<TurmsProperties> listener : propertiesChangeListeners) {
            try {
                listener.accept(properties);
            } catch (Exception e) {
                LOGGER.error(
                        "Caught an error while notifying the global properties change listener ({}) to handle new properties",
                        listener.getClass()
                                .getName(),
                        e);
            }
        }
    }

    private Mono<SharedClusterProperties> initializeSharedProperties() {
        LOGGER.info("Fetching shared properties");
        TurmsProperties localProperties = propertiesManager.getLocalProperties();
        SharedClusterProperties clusterProperties = new SharedClusterProperties(
                clusterId,
                TurmsProperties.SCHEMA_VERSION,
                localProperties,
                new Date());
        if (nodeType == NodeType.GATEWAY) {
            clusterProperties.setServiceProperties(null);
        } else {
            clusterProperties.setGatewayProperties(null);
        }
        return findAndUpdatePropertiesByNodeType(clusterProperties)
                .switchIfEmpty(Mono.defer(() -> sharedConfigService.insert(clusterProperties)))
                .onErrorResume(DuplicateKeyException.class,
                        e -> findAndUpdatePropertiesByNodeType(clusterProperties))
                .doOnError(t -> LOGGER.error("Failed to fetch shared properties", t))
                .doOnSuccess(properties -> {
                    sharedClusterProperties = properties;
                    LOGGER.info("Fetched shared properties");
                });
    }

    private Mono<SharedClusterProperties> findAndUpdatePropertiesByNodeType(
            SharedClusterProperties clusterProperties) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, clusterId);
        return sharedConfigService.findOne(SharedClusterProperties.class, filter)
                .flatMap(properties -> {
                    if (nodeType == NodeType.GATEWAY) {
                        if (properties.getGatewayProperties() == null) {
                            filter.eq(SharedClusterProperties.Fields.gatewayProperties, null);
                            Update update = Update.newBuilder(1)
                                    .set(SharedClusterProperties.Fields.gatewayProperties,
                                            clusterProperties.getGatewayProperties());
                            return sharedConfigService
                                    .updateOne(SharedClusterProperties.class, filter, update)
                                    .map(result -> {
                                        if (result.getModifiedCount() > 0) {
                                            properties.setGatewayProperties(
                                                    clusterProperties.getGatewayProperties());
                                            return properties;
                                        } else {
                                            throw new RuntimeException(
                                                    "Failed to update the cluster properties");
                                        }
                                    });
                        }
                    } else {
                        if (properties.getServiceProperties() == null) {
                            filter.eq(SharedClusterProperties.Fields.serviceProperties, null);
                            Update update = Update.newBuilder(1)
                                    .set(SharedClusterProperties.Fields.serviceProperties,
                                            clusterProperties.getServiceProperties());
                            return sharedConfigService
                                    .updateOne(SharedClusterProperties.class, filter, update)
                                    .map(result -> {
                                        if (result.getModifiedCount() > 0) {
                                            properties.setServiceProperties(
                                                    clusterProperties.getServiceProperties());
                                            return properties;
                                        } else {
                                            throw new RuntimeException(
                                                    "Failed to update the cluster properties");
                                        }
                                    });
                        }
                    }
                    return Mono.just(properties);
                });
    }

    private static SharedClusterProperties getClusterProperties(
            SharedClusterProperties clusterPropertiesSource,
            TurmsProperties turmsProperties) {
        CommonProperties commonProperties =
                SharedClusterProperties.getCommonProperties(turmsProperties);
        GatewayProperties gatewayProperties = turmsProperties.getGateway();
        ServiceProperties serviceProperties = turmsProperties.getService();
        return clusterPropertiesSource.toBuilder()
                .commonProperties(commonProperties)
                .gatewayProperties(gatewayProperties)
                .serviceProperties(serviceProperties)
                .turmsProperties(turmsProperties)
                .build();
    }
}