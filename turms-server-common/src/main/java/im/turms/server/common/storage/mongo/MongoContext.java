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

package im.turms.server.common.storage.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import com.mongodb.connection.ServerDescription;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import com.mongodb.event.ClusterDescriptionChangedEvent;
import com.mongodb.event.ClusterListener;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.thread.ThreadNameConst;
import im.turms.server.common.storage.mongo.codec.BsonValueEncoder;
import im.turms.server.common.storage.mongo.codec.MongoCodecProvider;
import im.turms.server.common.storage.mongo.entity.MongoEntity;
import im.turms.server.common.storage.mongo.entity.MongoEntityFactory;
import im.turms.server.common.storage.mongo.operation.MongoCollectionOptions;
import io.lettuce.core.internal.Futures;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.Future;
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
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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
    @Getter
    private final MongoDatabase configDatabase;
    private final CodecRegistry codecRegistry;
    private final Map<Class<?>, MongoEntity<?>> entityMap = new IdentityHashMap<>(64);
    private final Map<Class<?>, MongoCollection<?>> collectionMap = new IdentityHashMap<>(64);
    private final NioEventLoopGroup eventLoopGroup;

    public MongoContext(String connectionString, Consumer<List<ServerDescription>> onServerDescriptionChange) {
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
        eventLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(),
                // TODO: distinguish thread pool names when there are multiple contexts
                new DefaultThreadFactory(ThreadNameConst.MONGO_EVENT_LOOP));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionSettings)
                .applyToClusterSettings(builder -> builder.addClusterListener(new ClusterListener() {
                    @Override
                    public void clusterDescriptionChanged(ClusterDescriptionChangedEvent event) {
                        onServerDescriptionChange.accept(event.getNewDescription().getServerDescriptions());
                    }
                }))
                .codecRegistry(codecRegistry)
                // Do NOT use the default implementation of com.mongodb.connection.AsynchronousSocketChannelStreamFactory,
                // which use a heap buffer pool "bufferProvider" for BsonWriter for NIO.
                // They should go back to school to learn how to code efficiently.
                .streamFactoryFactory(NettyStreamFactoryFactory.builder()
                        .allocator(PooledByteBufAllocator.DEFAULT)
                        .eventLoopGroup(eventLoopGroup)
                        .socketChannelClass(NioSocketChannel.class)
                        .build())
                .build();
        client = MongoClients.create(settings);
        database = client.getDatabase(connectionSettings.getDatabase());
        adminDatabase = client.getDatabase("admin");
        configDatabase = client.getDatabase("config");

        BsonValueEncoder.codecRegistry = codecRegistry;
    }

    public Mono<Void> destroy(long timeoutMillis) {
        client.close();
        Future<?> future = eventLoopGroup.shutdownGracefully(0, timeoutMillis, TimeUnit.MILLISECONDS);
        return Mono
                .fromCompletionStage(Futures.toCompletionStage(future))
                .then();
    }

    public <T> Codec<T> getCodec(Class<T> clazz) {
        return codecRegistry.get(clazz);
    }

    public <T> MongoCollection<T> getCollection(Class<T> clazz) {
        MongoCollection<T> collection = (MongoCollection<T>) collectionMap.get(clazz);
        if (collection == null) {
            return (MongoCollection<T>) registerEntitiesByClasses(List.of(clazz))
                    .get(0).second();
        }
        return collection;
    }

    public List<MongoEntity<?>> getEntities() {
        return new ArrayList<>(entityMap.values());
    }

    public <T> MongoEntity<T> getEntity(Class<T> clazz) {
        MongoEntity<T> entity = (MongoEntity<T>) entityMap.get(clazz);
        if (entity == null) {
            return (MongoEntity<T>) registerEntitiesByClasses(List.of(clazz))
                    .get(0).first();
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
                entity = MongoEntityFactory.parse(clazz);
                MongoCollection<?> collection = database.getCollection(entity.collectionName(), clazz)
                        .withWriteConcern(properties.getWriteConcern());
                entityMap.put(clazz, entity);
                collectionMap.put(clazz, collection);
                pairs.add(Pair.of(entity, collection));
            }
        }
        return pairs;
    }

}