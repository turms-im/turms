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

import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.converter.DurationToLongConverter;
import im.turms.server.common.cluster.service.config.converter.LongToDurationConverter;
import im.turms.server.common.cluster.service.config.domain.discovery.Leader;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.config.domain.property.SharedClusterProperties;
import im.turms.server.common.dao.context.TurmsMongoMappingContext;
import im.turms.server.common.dao.util.MongoUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
@Log4j2
public class SharedConfigService implements ClusterService {

    // Note that 60s is the minimum TTL supported by the TTL index of MongoDB
    public static final int EXPIRABLE_RECORD_TTL = 60;

    // Service registry and config client
    private final ReactiveMongoTemplate mongoTemplate;

    public SharedConfigService(String url) {
        mongoTemplate = getMongoTemplate(url);
        MongoUtil.createIndexes(mongoTemplate, Set.of(SharedClusterProperties.class,
                Leader.class,
                Member.class));
    }

    private ReactiveMongoTemplate getMongoTemplate(String uri) {
        MongoProperties properties = new MongoProperties();
        properties.setUri(uri);
        // Note that "properties.setAutoIndexCreation(true)" itself won't work
        // because it won't be passed to the MongoMappingContext instance
        MongoPropertiesClientSettingsBuilderCustomizer customizer = new MongoPropertiesClientSettingsBuilderCustomizer(properties, null);
        ReactiveMongoClientFactory clientFactory = new ReactiveMongoClientFactory(List.of(customizer));
        MongoClient mongoClient = clientFactory.createMongoClient(MongoClientSettings.builder().build());
        SimpleReactiveMongoDatabaseFactory databaseFactory = new SimpleReactiveMongoDatabaseFactory(mongoClient, properties.getMongoClientDatabase());

        TurmsMongoMappingContext context = new TurmsMongoMappingContext();
        // We check and create indexes ourselves
        context.setAutoIndexCreation(false);

        CustomConversions customConversions = new CustomConversions(
                CustomConversions.StoreConversions.NONE,
                List.of(new DurationToLongConverter(), new LongToDurationConverter()));
        MappingMongoConverter converter = new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();

        ReactiveMongoTemplate template = new ReactiveMongoTemplate(databaseFactory, converter);
        template.setReadPreference(ReadPreference.primaryPreferred());
        return template;
    }

    /**
     * Note that only listen to the change in the same cluster
     */
    public <T> Flux<ChangeStreamEvent<T>> subscribe(Class<T> collectionClass, boolean returnFullDocumentOnUpdate) {
        ReactiveChangeStreamOperation.ReactiveChangeStream<T> stream = mongoTemplate.changeStream(collectionClass);
        if (returnFullDocumentOnUpdate) {
            stream = stream.withOptions(ChangeStreamOptions.ChangeStreamOptionsBuilder::returnFullDocumentOnUpdate);
        }
        return stream
                .watchCollection(collectionClass)
                // Don't use "filter(Criteria.where(clusterIdFieldName).is(clusterId))" here
                // because it cannot work if it's a delete operation
                .listen();
    }

    public <T> Flux<T> find(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass);
    }

    public <T> Mono<T> findOne(Query query, Class<T> clazz) {
        return mongoTemplate.findOne(query, clazz);
    }

    public <T> Mono<T> findOne(Class<T> clazz) {
        return mongoTemplate.findOne(new Query(), clazz);
    }

    public <T> Mono<T> insert(T record) {
        return mongoTemplate.insert(record);
    }

    public <T> Mono<T> insertOrGet(T record) {
        return mongoTemplate.insert(record)
                .onErrorResume(DuplicateKeyException.class, e -> mongoTemplate
                        .findOne(new Query(), record.getClass())
                        .switchIfEmpty((Mono) insertOrGet(record)));
    }

    public Mono<UpdateResult> updateFirst(Query query, Update update, Class<?> entityClass) {
        return mongoTemplate.updateFirst(query, update, entityClass);
    }

    public Mono<UpdateResult> updateMulti(Query query, Update update, Class<?> entityClass) {
        return mongoTemplate.updateMulti(query, update, entityClass);
    }

    public Mono<Void> upsert(Query query, UpdateDefinition update, Object entity, Class<?> entityClass) {
        return mongoTemplate.updateFirst(query, update, entityClass)
                .flatMap(updateResult -> {
                    if (updateResult.getMatchedCount() == 0) {
                        return mongoTemplate.insert(entity)
                                .then()
                                .onErrorResume(DuplicateKeyException.class, e -> this.upsert(query, update, entity, entityClass));
                    } else {
                        return Mono.empty();
                    }
                });
    }

    public Mono<DeleteResult> remove(Query query, Class<?> clazz) {
        return mongoTemplate.remove(query, clazz);
    }

}