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

package im.turms.server.common.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import im.turms.server.common.mongo.codec.MongoCodecProvider;
import im.turms.server.common.mongo.entity.MongoEntity;
import im.turms.server.common.mongo.operation.MongoCollectionOptions;
import im.turms.server.common.util.CollectorUtil;
import lombok.Getter;
import org.bson.codecs.BsonCodecProvider;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.Codec;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.IterableCodecProvider;
import org.bson.codecs.MapCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public class MongoContext {
    @Getter
    private final MongoClient client;
    @Getter
    private final MongoDatabase database;
    @Getter
    private final MongoDatabase adminDatabase;
    private final CodecRegistry codecRegistry;
    private final Map<Class<?>, MongoEntity<?>> entityMap = new IdentityHashMap<>(64);
    private final Map<Class<?>, MongoCollection<?>> collectionMap = new IdentityHashMap<>(64);

    public MongoContext(String connectionString) {
        if (connectionString == null) {
            throw new IllegalArgumentException("The connection string cannot not be null");
        }
        // MongoClients.getDefaultCodecRegistry()
        CodecRegistry commonCodecRegistry = CodecRegistries.fromProviders(
                new ValueCodecProvider(),
                new BsonValueCodecProvider(),
                new DocumentCodecProvider(),
                new MapCodecProvider(),
                new IterableCodecProvider(),
                new GeoJsonCodecProvider(),
                new Jsr310CodecProvider(),
                new BsonCodecProvider());
        MongoCodecProvider mongoCodecProvider = new MongoCodecProvider();
        codecRegistry = CodecRegistries.fromRegistries(commonCodecRegistry,
                CodecRegistries.fromProviders(mongoCodecProvider));
        mongoCodecProvider.setRegistry(codecRegistry);
        ConnectionString connectionSettings = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionSettings)
                .codecRegistry(codecRegistry)
                .build();
        client = MongoClients.create(settings);
        database = client.getDatabase(connectionSettings.getDatabase());
        adminDatabase = client.getDatabase("admin");
    }

    public void destroy() {
        client.close();
    }

    public <T> Codec<T> getCodec(Class<T> clazz) {
        return codecRegistry.get(clazz);
    }

    public <T> MongoCollection<T> getCollection(Class<T> clazz) {
        MongoCollection<T> collection = (MongoCollection<T>) collectionMap.get(clazz);
        if (collection == null) {
            registerEntitiesByClasses(List.of(clazz))
                    .get(0).getSecond();
        }
        return collection;
    }

    public List<MongoEntity<?>> getEntities() {
        return new ArrayList<>(entityMap.values());
    }

    public <T> MongoEntity<T> getEntity(Class<T> clazz) {
        MongoEntity<T> entity = (MongoEntity<T>) entityMap.get(clazz);
        if (entity == null) {
            registerEntitiesByClasses(List.of(clazz))
                    .get(0).getFirst();
        }
        return entity;
    }

    public List<Pair<MongoEntity<?>, MongoCollection<?>>> registerEntitiesByClasses(Collection<Class<?>> classes) {
        return registerEntitiesByOptions(classes.stream()
                .map(MongoCollectionOptions::of)
                .collect(CollectorUtil.toList(classes.size())));
    }

    public synchronized List<Pair<MongoEntity<?>, MongoCollection<?>>> registerEntitiesByOptions(
            Collection<MongoCollectionOptions> optionsList) {
        List<Pair<MongoEntity<?>, MongoCollection<?>>> pairs = new ArrayList<>(optionsList.size());
        for (MongoCollectionOptions properties : optionsList) {
            Class<?> clazz = properties.getClazz();
            MongoEntity<?> entity = entityMap.get(clazz);
            if (entity != null) {
                MongoCollection<?> collection = collectionMap.get(clazz);
                pairs.add(Pair.of(entity, collection));
            } else {
                entity = new MongoEntity<>(clazz);
                MongoCollection<?> collection = database.getCollection(entity.getCollectionName(), clazz)
                        .withWriteConcern(properties.getWriteConcern());
                entityMap.put(clazz, entity);
                collectionMap.put(clazz, collection);
                pairs.add(Pair.of(entity, collection));
            }
        }
        return pairs;
    }

}