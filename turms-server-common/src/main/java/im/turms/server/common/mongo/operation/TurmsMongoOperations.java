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

package im.turms.server.common.mongo.operation;

import com.mongodb.ClientSessionOptions;
import com.mongodb.TransactionOptions;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.ListIndexesPublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.internal.MongoCollectionUtil;
import com.mongodb.reactivestreams.client.internal.MongoOperationPublisher;
import com.mongodb.reactivestreams.client.internal.TurmsFindPublisherImpl;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.mongo.BsonPool;
import im.turms.server.common.mongo.DomainFieldName;
import im.turms.server.common.mongo.MongoContext;
import im.turms.server.common.mongo.entity.Index;
import im.turms.server.common.mongo.entity.MongoEntity;
import im.turms.server.common.mongo.entity.ShardKey;
import im.turms.server.common.mongo.exception.CorruptedDocumentException;
import im.turms.server.common.mongo.exception.MongoExceptionTranslator;
import im.turms.server.common.mongo.model.Tag;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.EncoderContext;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author James Chen
 * @implNote 1. We operate in an unsafe way, which means
 * we don't check whether arguments are legal or not and so on
 * 2. The publishers of mongo-java-driver are cold
 * and the publishers of TurmsMongoOperations are also cold
 * Reference: https://github.com/mongodb/mongo/blob/master/src/mongo/shell/utils_sh.js
 */
public class TurmsMongoOperations implements MongoOperationsSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsMongoOperations.class);

    private static final EncoderContext DEFAULT_ENCODER_CONTEXT = EncoderContext.builder().build();

    private static final BsonDocument ID_ONLY = new BsonDocument(DomainFieldName.ID, BsonPool.BSON_INT32_1);
    private static final Filter FILTER_ALL = Filter.newBuilder(0);
    private static final BsonDocument EMPTY_FILTER = new BsonDocument();
    private static final BsonDocument FILTER_ALL_DOCUMENT = EMPTY_FILTER;
    private static final Scheduler WATCH_SCHEDULER = Schedulers.newSingle("turms-mongo-watch", false);

    // Session
    private static final ClientSessionOptions DEFAULT_SESSION_OPTIONS = ClientSessionOptions.builder().build();
    private static final TransactionOptions DEFAULT_TRANSACTION_OPTIONS = TransactionOptions.builder().build();

    // CRUD
    private static final CountOptions DEFAULT_COUNT_OPTIONS = new CountOptions();
    private static final DeleteOptions DEFAULT_DELETE_OPTIONS = new DeleteOptions();
    private static final InsertManyOptions DEFAULT_INSERT_MANY_OPTIONS = new InsertManyOptions();
    private static final InsertOneOptions DEFAULT_INSERT_ONE_OPTIONS = new InsertOneOptions();
    private static final UpdateOptions DEFAULT_UPDATE_OPTIONS = new UpdateOptions();
    private static final UpdateOptions DEFAULT_UPSERT_OPTIONS = new UpdateOptions().upsert(true);

    private final MongoContext context;
    private final MongoExceptionTranslator translator = new MongoExceptionTranslator();
    private final Map<Class<?>, MongoOperationPublisher<?>> publisherMap = new IdentityHashMap<>(32);

    public TurmsMongoOperations(MongoContext context) {
        this.context = context;
    }

    // Query

    @Override
    public <T> Mono<T> findById(Class<T> clazz, Object id) {
        MongoCollection<T> collection = context.getCollection(clazz);
        FindPublisher<T> source = find(collection, new Document(DomainFieldName.ID, id), null);
        return Mono.from(source);
    }

    @Override
    public <T> Mono<T> findOne(Class<T> clazz) {
        return findOne(clazz, FILTER_ALL, null);
    }

    @Override
    public <T> Mono<T> findOne(Class<T> clazz, Filter filter) {
        return findOne(clazz, filter, null);
    }

    @Override
    public <T> Mono<T> findOne(Class<T> clazz, Filter filter, @Nullable QueryOptions options) {
        MongoCollection<T> collection = context.getCollection(clazz);
        options = options == null
                ? QueryOptions.newBuilder(1).limit(1)
                : options.limit(1);
        FindPublisher<T> source = find(collection, filter, options);
        return Mono.from(source);
    }

    @Override
    public <T> Flux<T> findMany(Class<T> clazz, Filter filter) {
        return findMany(clazz, filter, null);
    }

    @Override
    public <T> Flux<T> findMany(Class<T> clazz, Filter filter, @Nullable QueryOptions options) {
        MongoCollection<T> collection = context.getCollection(clazz);
        FindPublisher<T> source = find(collection, filter, options);
        return Flux.from(source);
    }

    @Override
    public <T> Flux<T> findAll(Class<T> clazz) {
        return findAll(clazz, null);
    }

    @Override
    public <T> Flux<T> findAll(Class<T> clazz, @Nullable QueryOptions options) {
        MongoCollection<T> collection = context.getCollection(clazz);
        FindPublisher<T> source = find(collection, FILTER_ALL_DOCUMENT, options);
        return Flux.from(source);
    }

    @Override
    public <T> Mono<Boolean> exists(Class<T> clazz, Filter filter) {
        MongoCollection<T> collection = context.getCollection(clazz);
        QueryOptions options = QueryOptions.newBuilder(2)
                .projection(ID_ONLY)
                .limit(1);
        FindPublisher<T> publisher = find(collection, filter, options);
        return Mono.from(publisher).hasElement();
    }

    // Count

    @Override
    public <T> Mono<Long> count(Class<T> clazz, Filter filter) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<Long> source = collection.countDocuments(filter, DEFAULT_COUNT_OPTIONS);
        return Mono.from(source);
    }

    @Override
    public <T> Mono<Long> countAll(Class<T> clazz) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<Long> source = collection.countDocuments(FILTER_ALL_DOCUMENT, DEFAULT_COUNT_OPTIONS);
        return Mono.from(source);
    }

    // Upsert

    @Override
    public <T> Mono<Void> upsert(T o) {
        return upsert(null, o);
    }

    @Override
    public <T> Mono<Void> upsert(@Nullable ClientSession session, T record) {
        Class<T> clazz = (Class<T>) record.getClass();
        MongoEntity<T> entity = (MongoEntity<T>) context.getEntity(record.getClass());
        MongoCollection<T> collection = context.getCollection(clazz);
        BsonDocument recordDoc = encodeEntity(record);
        ShardKey shardKey = entity.shardKey();
        BsonDocument filter = shardKey == null
                ? EMPTY_FILTER
                : applyShardKey(shardKey, recordDoc);
        BsonDocument update = encodeEntityForUpdateOps(recordDoc);
        Publisher<UpdateResult> result = session == null
                ? collection.updateOne(filter, update, DEFAULT_UPSERT_OPTIONS)
                : collection.updateOne(session, filter, update, DEFAULT_UPSERT_OPTIONS);
        return Mono.from(result)
                .onErrorMap(translator::translate)
                .then();
    }

    /**
     * apply the shard key to the filter so that mongos know to which
     * MongoDB the command needs to be sent for better performance
     */
    private BsonDocument applyShardKey(ShardKey shardKey, BsonDocument document) {
        List<ShardKey.Path> paths = shardKey.paths();
        BsonDocument filter = new BsonDocument(MapUtil.getCapability(paths.size()));
        for (ShardKey.Path shardKeyPath : paths) {
            String[] path = shardKeyPath.path();
            // fast path
            if (path.length == 1) {
                BsonValue shardKeyValue = document.get(path[0]);
                if (shardKeyValue != null) {
                    filter.append(path[0], shardKeyValue);
                }
            } else {
                // slow path
                BsonDocument currentPath = document;
                for (int i = 0, pathLength = path.length; i < pathLength; i++) {
                    String p = path[i];
                    BsonValue shardKeyValue = currentPath.get(p);
                    if (shardKeyValue == null) {
                        break;
                    }
                    if (i == pathLength - 1) {
                        filter.append(shardKeyPath.fullPath(), shardKeyValue);
                    } else if (shardKeyValue.isDocument()) {
                        currentPath = shardKeyValue.asDocument();
                    } else {
                        break;
                    }
                }
            }
        }
        return filter;
    }

    @Override
    public <T> Mono<Void> upsert(Class<T> clazz, Filter filter, Update update) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<UpdateResult> source = collection.updateOne(filter, update, DEFAULT_UPSERT_OPTIONS);
        return Mono.from(source)
                .onErrorMap(translator::translate)
                .then();
    }

    // Insert

    @Override
    public <T> Mono<Void> insert(T value) {
        return insert(null, value);
    }

    @Override
    public <T> Mono<Void> insert(@Nullable ClientSession session, T value) {
        Class<T> clazz = (Class<T>) value.getClass();
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<InsertOneResult> source = session == null
                ? collection.insertOne(value, DEFAULT_INSERT_ONE_OPTIONS)
                : collection.insertOne(session, value, DEFAULT_INSERT_ONE_OPTIONS);
        return Mono.from(source)
                .onErrorMap(translator::translate)
                .then();
    }

    @Override
    public Mono<Void> insertAll(List<?> values) {
        if (values.isEmpty()) {
            return Mono.empty();
        }
        List<Mono<Void>> sources = values.stream()
                .collect(Collectors.groupingBy(Object::getClass))
                .entrySet()
                .stream()
                .map(entry -> {
                    MongoCollection collection = context.getCollection(entry.getKey());
                    List<?> value = entry.getValue();
                    Publisher<InsertManyResult> source = collection.insertMany(value, DEFAULT_INSERT_MANY_OPTIONS);
                    return Mono.from(source)
                            .onErrorMap(translator::translate)
                            .then();
                })
                .toList();
        return Mono.when(sources);
    }

    @Override
    public Mono<Void> insertAllOfSameType(List<?> values) {
        if (values.isEmpty()) {
            return Mono.empty();
        }
        MongoCollection collection = context.getCollection(values.get(0).getClass());
        Publisher<InsertManyResult> source = collection.insertMany(values, DEFAULT_INSERT_MANY_OPTIONS);
        return Mono.from(source)
                .onErrorMap(translator::translate)
                .then();
    }

    // Update

    @Override
    public <T> Mono<UpdateResult> updateOne(Class<T> clazz, Filter filter, Update update) {
        return updateOne(null, clazz, filter, update);
    }

    @Override
    public <T> Mono<UpdateResult> updateOne(@Nullable ClientSession session, Class<T> clazz, Filter filter, Update update) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<UpdateResult> source = session == null
                ? collection.updateOne(filter, update, DEFAULT_UPDATE_OPTIONS)
                : collection.updateOne(session, filter, update, DEFAULT_UPDATE_OPTIONS);
        return Mono.from(source);
    }

    @Override
    public <T> Mono<UpdateResult> updateMany(Class<T> clazz, Filter filter, Update update) {
        return updateMany(null, clazz, filter, update);
    }

    @Override
    public <T> Mono<UpdateResult> updateMany(@Nullable ClientSession session, Class<T> clazz, Filter filter, Update update) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<UpdateResult> source = session == null
                ? collection.updateMany(filter, update, DEFAULT_UPDATE_OPTIONS)
                : collection.updateMany(session, filter, update, DEFAULT_UPDATE_OPTIONS);
        return Mono.from(source);
    }

    // Delete

    @Override
    public <T> Mono<DeleteResult> deleteOne(@Nullable ClientSession session, Class<T> clazz, Filter filter) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<DeleteResult> source = session == null
                ? collection.deleteOne(filter, DEFAULT_DELETE_OPTIONS)
                : collection.deleteOne(session, filter, DEFAULT_DELETE_OPTIONS);
        return Mono.from(source);
    }

    @Override
    public <T> Mono<DeleteResult> deleteOne(Class<T> clazz, Filter filter) {
        return deleteOne(null, clazz, filter);
    }

    @Override
    public <T> Mono<DeleteResult> deleteMany(@Nullable ClientSession session, Class<T> clazz, Filter filter) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<DeleteResult> source = session == null
                ? collection.deleteMany(filter, DEFAULT_DELETE_OPTIONS)
                : collection.deleteMany(session, filter, DEFAULT_DELETE_OPTIONS);
        return Mono.from(source);
    }

    @Override
    public <T> Mono<DeleteResult> deleteMany(Class<T> clazz, Filter filter) {
        return deleteMany(null, clazz, filter);
    }

    @Override
    public <T> Mono<DeleteResult> deleteAll(Class<T> clazz) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<DeleteResult> source = collection.deleteMany(FILTER_ALL_DOCUMENT, DEFAULT_DELETE_OPTIONS);
        return Mono.from(source);
    }

    // Change Stream

    @Override
    public <T> Flux<ChangeStreamDocument<T>> watch(Class<T> clazz, FullDocument fullDocument) {
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<ChangeStreamDocument<T>> source = collection.watch(clazz)
                .fullDocument(fullDocument);
        // Use single thread for onNext calls to ensure they happen in sequence
        // without the need of "synchronized" one by one

        // The code is a little hacky because only using one thread bases on the fact
        // that we know all our usages are so fast that they can run in a thread
        return Flux.from(source).publishOn(WATCH_SCHEDULER);
    }

    // Aggregation

    @Override
    public <T> Mono<Long> countDistinct(Class<T> clazz,
                                        Filter filter,
                                        String groupByFieldName) {
        MongoCollection<?> collection = context.getCollection(clazz);
        List<Bson> pipeline = List.of(
                Aggregates.match(filter),
                Aggregates.project(Projections.fields(
                        Projections.excludeId(),
                        Projections.include(groupByFieldName))),
                Aggregates.group("$" + groupByFieldName, Accumulators.sum("count", 1)));
        AggregatePublisher<Document> count = collection.aggregate(pipeline, Document.class);
        return Mono.from(count)
                .map(document -> Long.valueOf((Integer) document.get("count")))
                .defaultIfEmpty(0L);
    }

    // Index

    @Override
    public <T> Mono<Void> ensureIndexes(Class<T> clazz, List<IndexModel> indexModels) {
        if (indexModels.isEmpty()) {
            return Mono.empty();
        }
        MongoCollection<T> collection = context.getCollection(clazz);
        Publisher<String> source = collection.createIndexes(indexModels);
        String collectionName = context.getEntity(clazz).collectionName();
        return Flux.from(source)
                .then()
                .onErrorMap(t -> new IllegalStateException("Failed to index the collection " + collectionName, t))
                .doOnSuccess(ignored -> {
                    List<BsonDocument> indexDocs = indexModels
                            .stream()
                            .map(indexModel -> indexModel.getKeys().toBsonDocument())
                            .collect(CollectorUtil.toList(indexModels.size()));
                    String indexes = StringUtils.join(indexDocs, ", ");
                    LOGGER.info("Indexed the collection {}: [{}]", collectionName, indexes);
                });
    }

    @Override
    public <T> Flux<Document> listIndexes(Class<T> clazz) {
        MongoCollection<T> collection = context.getCollection(clazz);
        ListIndexesPublisher<Document> source = collection.listIndexes();
        return Flux.from(source);
    }

    // Shard

    @Override
    public Mono<List<Tag>> findTags(String collectionName) {
        String namespace = getNamespace(collectionName);
        BsonDocument filter = new BsonDocument("ns", new BsonString(namespace));
        Publisher<Document> source = context.getConfigDatabase().getCollection("tags").find(filter);
        return Flux.from(source)
                .map(document -> {
                    try {
                        return new Tag(document.getString("ns"),
                                document.getString("tag"),
                                document.get("max", Document.class),
                                document.get("min", Document.class));
                    } catch (Exception e) {
                        throw new CorruptedDocumentException("Failed to decode a tag from the document: " + document, e);
                    }
                })
                .collectList();
    }

    @Override
    public Mono<Void> deleteTags(String collectionName) {
        String namespace = getNamespace(collectionName);
        BsonDocument filter = new BsonDocument("ns", new BsonString(namespace));
        Publisher<DeleteResult> source = context.getConfigDatabase().getCollection("tags").deleteMany(filter);
        return Flux.from(source)
                .then();
    }

    @Override
    public Mono<Void> enableSharding() {
        String dbName = context.getDatabase().getName();
        Publisher<Document> source = context.getAdminDatabase().runCommand(new Document("enableSharding", dbName));
        return Mono.from(source)
                .onErrorMap(t -> new IllegalStateException("Failed to enable sharding the database " + dbName, t))
                .doOnSuccess(ignored -> LOGGER.info("Enabled sharding the database {}", dbName))
                .then();
    }

    @Override
    public Mono<Void> shard(MongoEntity<?> entity) {
        BsonDocument shardKey = entity.getShardKeyBson();
        if (shardKey == null) {
            return Mono.empty();
        }
        String namespace = getNamespace(entity.collectionName());
        Document command = new Document("shardCollection", namespace).append("key", shardKey);
        Mono<Document> shardCollection = Mono.from(context.getAdminDatabase().runCommand(command))
                .onErrorMap(t -> new IllegalStateException("Failed to shard the collection %s with the shard key %s".formatted(namespace, shardKey.toJson()), t))
                .doOnSuccess(ignored ->
                        LOGGER.info("Sharded the collection {} with the shard key {}", namespace, shardKey.toJson()));
        return shardCollection.then();
    }

    @Override
    public Mono<Void> ensureIndexesAndShard(Collection<Class<?>> classes) {
        return ensureIndexesAndShard(classes, null);
    }

    /**
     * @implNote Send requests to ensure indexes and shard collections synchronously
     * to avoid mongo servers throwing "LockBusy" error (error code 46)
     */
    @Override
    public Mono<Void> ensureIndexesAndShard(Collection<Class<?>> classes,
                                            @Nullable BiPredicate<Class<?>, Field> customIndexFilter) {
        Mono<Void> ensureIndexes = Mono.empty();
        for (Class<?> clazz : classes) {
            MongoEntity<?> entity = context.getEntity(clazz);
            List<Index> indexes = entity.indexes();
            List<IndexModel> indexModels = new ArrayList<>(indexes.size());
            IndexModel compoundIndex = entity.compoundIndex();
            if (compoundIndex != null) {
                indexModels.add(compoundIndex);
            }
            for (Index index : indexes) {
                if (index.indexed().optional()) {
                    if (customIndexFilter != null && customIndexFilter.test(clazz, index.field())) {
                        indexModels.add(index.model());
                    }
                } else {
                    indexModels.add(index.model());
                }
            }
            if (!indexModels.isEmpty()) {
                ensureIndexes = ensureIndexes
                        .then(Mono.defer(() -> ensureIndexes(entity.entityClass(), indexModels)));
            }
        }
        return ensureIndexes
                .then(enableSharding())
                .then(Mono.defer(() -> {
                    Mono<Void> shardEntities = Mono.empty();
                    for (Class<?> clazz : classes) {
                        MongoEntity<?> entity = context.getEntity(clazz);
                        shardEntities = shardEntities
                                .then(Mono.defer(() -> shard(entity)));
                    }
                    return shardEntities;
                }));
    }

    // Zone

    @Override
    public Mono<Void> addShardToZone(String shardName, String zoneName) {
        BsonDocument command = new BsonDocument("addShardToZone", new BsonString(shardName))
                .append("zone", new BsonString(zoneName));
        Publisher<Document> source = context.getAdminDatabase().runCommand(command);
        return Mono.from(source).then();
    }

    @Override
    public Mono<Void> updateZoneKeyRange(String collectionName,
                                         String zoneName,
                                         Document minimum,
                                         Document maximum) {
        Document command = new Document("updateZoneKeyRange", getNamespace(collectionName))
                .append("min", minimum)
                .append("max", maximum)
                .append("zone", zoneName);
        Publisher<Document> source = context.getAdminDatabase().runCommand(command);
        return Mono.from(source).then();
    }

    // Collection

    @Override
    public Mono<Void> createCollection(Class<?> clazz) {
        MongoEntity<?> entity = context.getEntity(clazz);
        Publisher<Void> source = context.getDatabase().createCollection(entity.collectionName());
        return Mono.from(source);
    }

    @Override
    public Mono<Boolean> collectionExists(Class<?> clazz) {
        MongoEntity<?> entity = context.getEntity(clazz);
        return Flux.from(context.getDatabase().listCollectionNames())
                .filter(name -> name.equals(entity.collectionName()))
                .map(name -> true)
                .single(false);
    }

    @Override
    public Mono<Void> dropDatabase() {
        Publisher<Void> source = context.getDatabase().drop();
        return Mono.from(source);
    }

    @Override
    public Mono<Boolean> validate(Class<?> clazz, String jsonSchema) {
        MongoCollection<?> collection = context.getCollection(clazz);
        QueryOptions options = QueryOptions.newBuilder(2)
                .projection(ID_ONLY)
                .limit(1);
        FindPublisher<?> publisher = find(collection, Document.parse(jsonSchema), options);
        return Mono.from(publisher)
                .hasElement();
    }

    // Transaction

    @Override
    public <T> Mono<T> inTransaction(Function<ClientSession, Mono<T>> action) {
        return Mono.usingWhen(
                context.getClient().startSession(DEFAULT_SESSION_OPTIONS),
                session -> {
                    session.startTransaction(DEFAULT_TRANSACTION_OPTIONS);
                    return action.apply(session);
                },
                ClientSession::commitTransaction,
                (session, t) -> session.abortTransaction(),
                ClientSession::commitTransaction);
    }

    // Balancer

    @Override
    public Mono<Void> disableBalancing(String collectionName) {
        String namespace = getNamespace(collectionName);
        BsonDocument filter = new BsonDocument(DomainFieldName.ID, new BsonString(namespace));
        BsonDocument update = new BsonDocument("$set", new BsonDocument("noBalance", BsonBoolean.TRUE));
        Publisher<UpdateResult> disableBalancing = context.getConfigDatabase().getCollection("collections")
                .updateOne(filter, update, DEFAULT_UPDATE_OPTIONS);
        return Mono.from(disableBalancing).then();
    }

    @Override
    public Mono<Void> enableBalancing(String collectionName) {
        String namespace = getNamespace(collectionName);
        BsonDocument filter = new BsonDocument(DomainFieldName.ID, new BsonString(namespace));
        BsonDocument update = new BsonDocument("$set", new BsonDocument("noBalance", BsonBoolean.FALSE));
        Publisher<UpdateResult> enableBalancing = context.getConfigDatabase().getCollection("collections")
                .updateOne(filter, update, DEFAULT_UPDATE_OPTIONS);
        return Mono.from(enableBalancing).then();
    }

    @Override
    public Mono<Boolean> isBalancerRunning() {
        Publisher<Document> balancerStatus = context.getAdminDatabase().runCommand(new BsonDocument("balancerStatus", BsonPool.BSON_INT32_1));
        return Mono.from(balancerStatus).map(document -> document.getBoolean("inBalancerRound"));
    }

    // Helper

    private <T> BsonDocument encodeEntity(T value) {
        Codec<T> codec = (Codec<T>) context.getCodec(value.getClass());
        BsonDocument document = new BsonDocument();
        BsonDocumentWriter writer = new BsonDocumentWriter(document);
        codec.encode(writer, value, DEFAULT_ENCODER_CONTEXT);
        return document;
    }

    private BsonDocument encodeEntityForUpdateOps(BsonDocument document) {
        document.remove(DomainFieldName.ID);
        return new BsonDocument("$set", document);
    }

    private <T> TurmsFindPublisherImpl<T> find(MongoCollection<T> collection,
                                               Bson filter,
                                               @Nullable QueryOptions options) {
        MongoOperationPublisher<T> publisher = getPublisher(collection);
        return new TurmsFindPublisherImpl<>(null, publisher, filter, options);
    }

    private String getNamespace(String collectionName) {
        return context.getDatabase().getName() + "." + collectionName;
    }

    private <T> MongoOperationPublisher<T> getPublisher(MongoCollection<T> collection) {
        Class<T> entityClass = collection.getDocumentClass();
        MongoOperationPublisher<T> publisher = (MongoOperationPublisher<T>) publisherMap.get(entityClass);
        if (publisher == null) {
            synchronized (publisherMap) {
                return (MongoOperationPublisher<T>) publisherMap
                        .computeIfAbsent(entityClass, clazz -> MongoCollectionUtil.getPublisher(collection));
            }
        }
        return publisher;
    }

}