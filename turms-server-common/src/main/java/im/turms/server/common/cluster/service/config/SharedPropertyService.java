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

import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.domain.property.SharedProperties;
import im.turms.server.common.property.TurmsProperties;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
@Log4j2
public class SharedPropertyService implements ClusterService {

    private final SharedConfigService sharedConfigService;
    private final String clusterId;

    private final TurmsProperties localProperties;
    @Getter
    private TurmsProperties sharedProperties;

    private final List<Consumer<TurmsProperties>> propertiesChangeListeners = new LinkedList<>();

    public SharedPropertyService(String clusterId, TurmsProperties localProperties, SharedConfigService sharedConfigService) {
        this.clusterId = clusterId;
        this.localProperties = localProperties;
        this.sharedConfigService = sharedConfigService;
    }

    @Override
    public void start() {
        sharedConfigService.subscribe(SharedProperties.class, true)
                .doOnNext(event -> {
                    SharedProperties changedProperties = event.getBody();
                    String changeClusterId = changedProperties != null
                            ? changedProperties.getClusterId()
                            : ChangeStreamUtil.getIdAsString(event);
                    if (changeClusterId.equals(clusterId)) {
                        switch (event.getOperationType()) {
                            case INSERT:
                            case REPLACE:
                            case UPDATE:
                                sharedProperties = changedProperties.getTurmsProperties();
                                notifyListeners(sharedProperties);
                                break;
                            case INVALIDATE:
                                log.warn("The shared properties has been removed from database unexpectedly");
                                insertOrGetSharedProperties().subscribe();
                                break;
                            default:
                                break;
                        }
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of SharedProperties: {}", o, throwable))
                .subscribe();
        sharedProperties = insertOrGetSharedProperties()
                .block()
                .getTurmsProperties();
    }

    public Mono<SharedProperties> insertOrGetSharedProperties() {
        log.info("Trying to get shared properties");
        return sharedConfigService.insertOrGet(new SharedProperties(clusterId, localProperties, new Date()))
                .doOnSuccess(ignored -> log.info("Shared properties were retrieved successfully"));
    }

    public Mono<Void> updateSharedProperties(TurmsProperties turmsProperties) {
        log.info("Share new turms properties to all members");
        Date now = new Date();
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(clusterId))
                .addCriteria(Criteria.where(SharedProperties.Fields.lastUpdatedTime).lt(now));
        Update update = Update.update(SharedProperties.Fields.turmsProperties, turmsProperties);
        return sharedConfigService.upsert(query, update, turmsProperties, SharedProperties.class)
                .doOnError(e -> log.error("Failed to share new turms properties", e))
                .doOnSuccess(updated -> {
                    this.sharedProperties = turmsProperties;
                    log.info("Turms properties have been shared");
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

}