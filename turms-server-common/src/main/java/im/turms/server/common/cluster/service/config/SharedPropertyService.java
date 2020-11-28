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

package im.turms.server.common.cluster.service.config;

import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.domain.property.CommonProperties;
import im.turms.server.common.cluster.service.config.domain.property.SharedClusterProperties;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.property.env.service.ServiceProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static im.turms.server.common.cluster.service.config.domain.property.SharedClusterProperties.getCommonProperties;

/**
 * @author James Chen
 */
@Log4j2
public class SharedPropertyService implements ClusterService {

    public static final TurmsProperties DEFAULT_PROPERTIES = new TurmsProperties();

    private final SharedConfigService sharedConfigService;
    private final String clusterId;
    private final NodeType nodeType;

    private final TurmsPropertiesManager turmsPropertiesManager;
    private SharedClusterProperties sharedClusterProperties;

    private final List<Consumer<TurmsProperties>> propertiesChangeListeners = new LinkedList<>();

    public SharedPropertyService(String clusterId, NodeType nodeType, TurmsPropertiesManager turmsPropertiesManager, SharedConfigService sharedConfigService) {
        this.clusterId = clusterId;
        this.nodeType = nodeType;
        this.turmsPropertiesManager = turmsPropertiesManager;
        this.sharedConfigService = sharedConfigService;
    }

    public TurmsProperties getSharedProperties() {
        return sharedClusterProperties.getTurmsProperties();
    }

    @Override
    public void start() {
        sharedConfigService.subscribe(SharedClusterProperties.class, true)
                .doOnNext(event -> {
                    SharedClusterProperties changedProperties = event.getBody();
                    String changeClusterId = changedProperties != null
                            ? changedProperties.getClusterId()
                            : ChangeStreamUtil.getIdAsString(event);
                    if (changeClusterId.equals(clusterId)) {
                        switch (event.getOperationType()) {
                            case INSERT:
                            case REPLACE:
                            case UPDATE:
                                sharedClusterProperties = changedProperties;
                                notifyListeners(sharedClusterProperties.getTurmsProperties());
                                break;
                            case INVALIDATE:
                                log.warn("The shared properties has been removed from database unexpectedly");
                                initializeSharedProperties().subscribe();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of SharedProperties: {}", o, throwable))
                .subscribe();
        initializeSharedProperties().block();
    }

    /**
     * @implNote We don't support the partial update by Map<String, Object> because
     * there is not an efficient way to update nested objects of a document in MongoDB.
     */
    public Mono<Boolean> updateSharedProperties(TurmsProperties turmsProperties) {
        log.info("Share new turms properties to all members");
        SharedClusterProperties clusterProperties = getClusterProperties(sharedClusterProperties, turmsProperties);
        Date now = new Date();
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(clusterId))
                .addCriteria(Criteria.where(SharedClusterProperties.Fields.lastUpdatedTime).lt(now));
        Update update = new Update()
                .set(SharedClusterProperties.Fields.commonProperties, clusterProperties.getCommonProperties());
        if (clusterProperties.getGatewayProperties() != null) {
            update.set(SharedClusterProperties.Fields.gatewayProperties, clusterProperties.getGatewayProperties());
        }
        if (clusterProperties.getServiceProperties() != null) {
            update.set(SharedClusterProperties.Fields.serviceProperties, clusterProperties.getServiceProperties());
        }
        return sharedConfigService.upsert(query, update, clusterProperties, SharedClusterProperties.class)
                .doOnError(e -> log.error("Failed to share new turms properties", e))
                .doOnNext(wasSuccessful -> {
                    if (wasSuccessful) {
                        sharedClusterProperties = clusterProperties;
                        log.info("Turms properties have been shared");
                    } else {
                        log.error("Failed to share new turms properties");
                    }
                });
    }

    public void addListeners(Consumer<TurmsProperties> listener) {
        propertiesChangeListeners.add(listener);
    }

    private void notifyListeners(TurmsProperties properties) {
        for (Consumer<TurmsProperties> listener : propertiesChangeListeners) {
            try {
                listener.accept(properties);
            } catch (Exception e) {
                log.error("The properties listener {} failed to handle the new properties", listener.getClass().getName(), e);
            }
        }
    }

    private Mono<SharedClusterProperties> initializeSharedProperties() {
        log.info("Trying to get shared properties");
        TurmsProperties localProperties = turmsPropertiesManager.getLocalProperties();
        SharedClusterProperties clusterProperties = new SharedClusterProperties(clusterId, localProperties, new Date());
        if (nodeType == NodeType.GATEWAY) {
            clusterProperties.setServiceProperties(null);
        } else {
            clusterProperties.setGatewayProperties(null);
        }
        return findAndUpdatePropertiesByNodeType(clusterProperties)
                .switchIfEmpty(sharedConfigService.insert(clusterProperties))
                .onErrorResume(DuplicateKeyException.class, e -> findAndUpdatePropertiesByNodeType(clusterProperties))
                .doOnSuccess(properties -> {
                    sharedClusterProperties = properties;
                    log.info("Shared properties were retrieved successfully");
                });
    }

    private Mono<SharedClusterProperties> findAndUpdatePropertiesByNodeType(SharedClusterProperties clusterProperties) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(clusterId));
        return sharedConfigService.findOne(query, SharedClusterProperties.class)
                .flatMap(properties -> {
                    if (nodeType == NodeType.GATEWAY) {
                        if (properties.getGatewayProperties() == null) {
                            query.addCriteria(Criteria.where(SharedClusterProperties.Fields.gatewayProperties).is(null));
                            Update update = Update.update(SharedClusterProperties.Fields.gatewayProperties, clusterProperties.getGatewayProperties());
                            return sharedConfigService.updateFirst(query, update, SharedClusterProperties.class)
                                    .map(wasAcknowledged -> {
                                        if (wasAcknowledged) {
                                            properties.setGatewayProperties(clusterProperties.getGatewayProperties());
                                            return properties;
                                        } else {
                                            throw new IllegalStateException("Failed to update the cluster properties");
                                        }
                                    });
                        }
                    } else {
                        if (properties.getServiceProperties() == null) {
                            query.addCriteria(Criteria.where(SharedClusterProperties.Fields.serviceProperties).is(null));
                            Update update = Update.update(SharedClusterProperties.Fields.serviceProperties, clusterProperties.getServiceProperties());
                            return sharedConfigService.updateFirst(query, update, SharedClusterProperties.class)
                                    .map(wasAcknowledged -> {
                                        if (wasAcknowledged) {
                                            properties.setServiceProperties(clusterProperties.getServiceProperties());
                                            return properties;
                                        } else {
                                            throw new IllegalStateException("Failed to update the cluster properties");
                                        }
                                    });
                        }
                    }
                    return Mono.just(properties);
                });
    }

    private static SharedClusterProperties getClusterProperties(SharedClusterProperties clusterPropertiesSource, TurmsProperties turmsProperties) {
        CommonProperties commonProperties = getCommonProperties(turmsProperties);
        GatewayProperties gatewayProperties = turmsProperties.getGateway();
        ServiceProperties serviceProperties = turmsProperties.getService();
        return clusterPropertiesSource.toBuilder()
                .commonProperties(commonProperties)
                .gatewayProperties(gatewayProperties)
                .serviceProperties(serviceProperties)
                .build();
    }
}