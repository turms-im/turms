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

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.domain.discovery.Leader;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.config.domain.property.SharedClusterProperties;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.exception.DuplicateKeyException;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * @author James Chen
 */
public class SharedConfigService implements ClusterService {

    // Note that 60s is the minimum TTL supported by the TTL index of MongoDB
    public static final int EXPIRABLE_RECORD_TTL = 60;

    // Service registry and config client
    private final TurmsMongoClient mongoClient;

    public SharedConfigService(TurmsMongoProperties properties) {
        try {
            mongoClient = TurmsMongoClient.of(properties)
                    .block(Duration.ofMinutes(1));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create the shared config service", e);
        }
        List<Class<?>> classes = List.of(SharedClusterProperties.class, Leader.class, Member.class);
        mongoClient.registerEntitiesByClasses(classes);
        try {
            mongoClient.ensureIndexesAndShard(classes).block(Duration.ofMinutes(1));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to ensure the indexes are created for the classes: " + classes.stream()
                    .map(Class::getName).toList(), e);
        }
    }

    @Override
    public void stop() {
        mongoClient.destroy();
    }

    /**
     * Note that only listen to the change in the same cluster
     */
    public <T> Flux<ChangeStreamDocument<T>> subscribe(Class<T> collectionClass, FullDocument fullDocument) {
        // Don't use the filter of watch because the code like
        // "filter(Criteria.where(clusterIdFieldName).is(clusterId))"
        // cannot work if it's a delete operation
        return mongoClient.watch(collectionClass, fullDocument);
    }

    public <T> Flux<T> find(Class<T> entityClass, Filter filter) {
        return mongoClient.findMany(entityClass, filter);
    }

    public <T> Mono<T> findOne(Class<T> clazz, Filter filter) {
        return mongoClient.findOne(clazz, filter);
    }

    public <T> Mono<T> findOne(Class<T> clazz) {
        return mongoClient.findOne(clazz);
    }

    public <T> Mono<T> insert(T record) {
        return mongoClient.insert(record)
                .thenReturn(record);
    }

    public <T> Mono<T> insertOrGet(T record) {
        return mongoClient.insert(record)
                .thenReturn(record)
                .onErrorResume(DuplicateKeyException.class, e -> mongoClient
                        .findOne(record.getClass())
                        .switchIfEmpty((Mono) insertOrGet(record)));
    }

    public Mono<UpdateResult> updateOne(Class<?> entityClass, Filter filter, Update update) {
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateMany(Class<?> entityClass, Filter filter, Update update) {
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Void> upsert(Filter filter, Update update, Object entity) {
        return mongoClient.updateOne(entity.getClass(), filter, update)
                .flatMap(updateResult -> {
                    if (updateResult.getMatchedCount() == 0) {
                        return mongoClient.insert(entity)
                                .onErrorResume(DuplicateKeyException.class, e -> this.upsert(filter, update, entity));
                    } else {
                        return Mono.empty();
                    }
                });
    }

    public Mono<DeleteResult> remove(Class<?> clazz, Filter filter) {
        return mongoClient.deleteMany(clazz, filter);
    }

    public Mono<DeleteResult> removeOne(Class<?> clazz, Filter filter) {
        return mongoClient.deleteOne(clazz, filter);
    }

}