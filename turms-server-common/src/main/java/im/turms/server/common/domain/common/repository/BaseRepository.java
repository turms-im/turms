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

package im.turms.server.common.domain.common.repository;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import jakarta.annotation.Nullable;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.ClientSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;

/**
 * @author James Chen
 */
public abstract class BaseRepository<T, K> {

    protected TurmsMongoClient mongoClient;
    protected Class<T> entityClass;

    protected BaseRepository(TurmsMongoClient mongoClient, Class<T> entityClass) {
        this.mongoClient = mongoClient;
        this.entityClass = entityClass;
    }

    public Mono<Void> upsert(T doc) {
        return mongoClient.upsert(doc);
    }

    public Mono<Void> upsert(T doc, @Nullable ClientSession session) {
        return mongoClient.upsert(session, doc);
    }

    public Mono<Void> insert(T doc) {
        return mongoClient.insert(doc);
    }

    public Mono<Void> insert(T doc, @Nullable ClientSession session) {
        return mongoClient.insert(session, doc);
    }

    public Mono<Void> insertAllOfSameType(List<T> docs) {
        return mongoClient.insertAllOfSameType(docs);
    }

    public Mono<Void> insertAllOfSameType(List<T> docs, @Nullable ClientSession session) {
        return mongoClient.insertAllOfSameType(session, docs);
    }

    public Mono<DeleteResult> deleteAll() {
        return mongoClient.deleteAll(entityClass);
    }

    public Mono<DeleteResult> deleteById(K id) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, id);
        return mongoClient.deleteOne(entityClass, filter);
    }

    public Mono<DeleteResult> deleteById(K id, @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, id);
        return mongoClient.deleteOne(session, entityClass, filter);
    }

    public Mono<DeleteResult> deleteByIds(@Nullable Collection<K> ids) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DomainFieldName.ID, ids);
        return mongoClient.deleteMany(entityClass, filter);
    }

    public Mono<DeleteResult> deleteByIds(
            @Nullable Collection<K> ids,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DomainFieldName.ID, ids);
        return mongoClient.deleteMany(session, entityClass, filter);
    }

    public Mono<Long> countAll() {
        return mongoClient.countAll(entityClass);
    }

    public Mono<Boolean> existsById(K id) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, id);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> existsByIds(Collection<K> ids) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, ids);
        return mongoClient.exists(entityClass, filter);
    }

    public Flux<T> findAll() {
        return mongoClient.findAll(entityClass);
    }

    public Flux<T> findAll(@Nullable Integer page, @Nullable Integer size) {
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findAll(entityClass, options);
    }

    public Flux<T> findIdsByIds(Collection<K> ids) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, ids);
        return mongoClient.findIds(entityClass, filter);
    }

    public Mono<T> findById(K id) {
        return mongoClient.findById(entityClass, id);
    }

    public Flux<T> findByIds(Collection<K> ids) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, ids);
        return mongoClient.findMany(entityClass, filter);
    }

    public Flux<ChangeStreamDocument<T>> watch(FullDocument fullDocument) {
        return mongoClient.watch(entityClass, fullDocument);
    }

    public <R> Mono<R> inTransaction(Function<ClientSession, Mono<R>> action) {
        return mongoClient.inTransaction(action);
    }

}
