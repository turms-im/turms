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

package com.mongodb.reactivestreams.client.internal;

import java.util.concurrent.TimeUnit;

import com.mongodb.CursorType;
import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoNamespace;
import com.mongodb.client.model.Collation;
import com.mongodb.internal.async.AsyncBatchCursor;
import com.mongodb.internal.client.model.FindOptions;
import com.mongodb.internal.operation.AsyncExplainableReadOperation;
import com.mongodb.internal.operation.AsyncReadOperation;
import com.mongodb.internal.operation.FindOperation;
import com.mongodb.internal.operation.TurmsFindOperation;
import com.mongodb.lang.Nullable;
import com.mongodb.reactivestreams.client.ClientSession;
import com.mongodb.reactivestreams.client.FindPublisher;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;

import im.turms.server.common.infra.exception.NotImplementedException;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;

import static com.mongodb.assertions.Assertions.notNull;

/**
 * @author James Chen
 * @implNote The heavy objects that we have eliminated: 1. {@link FindOptions} and
 *           {@link FindOperation} 3. A lot of intermediate operations 4. A lot of intermediate
 *           objects (especially documents)
 * @see FindPublisherImpl
 */
public class TurmsFindPublisherImpl<T> extends BatchCursorPublisher<T> implements FindPublisher<T> {

    private final Bson filter;
    private final QueryOptions queryOptions;

    public TurmsFindPublisherImpl(
            @Nullable final ClientSession clientSession,
            final MongoOperationPublisher<T> mongoOperationPublisher,
            final Bson filter,
            @Nullable final QueryOptions queryOptions) {
        super(clientSession, mongoOperationPublisher);
        this.filter = notNull("filter", filter);
        this.queryOptions = queryOptions == null
                ? QueryOptions.newBuilder()
                : queryOptions;
    }

    @Override
    public FindPublisher<T> filter(@Nullable final Bson filter) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> limit(final int limit) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> skip(final int skip) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> maxTime(final long maxTime, final TimeUnit timeUnit) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> maxAwaitTime(final long maxAwaitTime, final TimeUnit timeUnit) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> batchSize(final int batchSize) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> collation(@Nullable final Collation collation) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> projection(@Nullable final Bson projection) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> sort(@Nullable final Bson sort) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> noCursorTimeout(final boolean noCursorTimeout) {
        throw new NotImplementedException();
    }

    @Override
    @Deprecated
    public FindPublisher<T> oplogReplay(final boolean oplogReplay) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> partial(final boolean partial) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> cursorType(final CursorType cursorType) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> comment(@Nullable final String comment) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> comment(BsonValue comment) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> hint(@Nullable final Bson hint) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> hintString(@Nullable final String hint) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> let(Bson variables) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> max(@Nullable final Bson max) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> min(@Nullable final Bson min) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> returnKey(final boolean returnKey) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> showRecordId(final boolean showRecordId) {
        throw new NotImplementedException();
    }

    @Override
    public FindPublisher<T> allowDiskUse(@Nullable final Boolean allowDiskUse) {
        throw new NotImplementedException();
    }

    @Override
    public Publisher<Document> explain() {
        return publishExplain(Document.class, null);
    }

    @Override
    public Publisher<Document> explain(final ExplainVerbosity verbosity) {
        return publishExplain(Document.class, notNull("verbosity", verbosity));
    }

    @Override
    public <E> Publisher<E> explain(final Class<E> explainResultClass) {
        return publishExplain(explainResultClass, null);
    }

    @Override
    public <E> Publisher<E> explain(
            final Class<E> explainResultClass,
            final ExplainVerbosity verbosity) {
        return publishExplain(explainResultClass, notNull("verbosity", verbosity));
    }

    private <E> Publisher<E> publishExplain(
            final Class<E> explainResultClass,
            @Nullable final ExplainVerbosity verbosity) {
        notNull("explainDocumentClass", explainResultClass);
        return getMongoOperationPublisher().createReadOperationMono(() -> asAsyncReadOperation(0)
                .asAsyncExplainableOperation(verbosity, getCodecRegistry().get(explainResultClass)),
                getClientSession());
    }

    @Override
    AsyncExplainableReadOperation<AsyncBatchCursor<T>> asAsyncReadOperation(
            final int initialBatchSize) {
        return getFindOperation(queryOptions.batchSize(initialBatchSize));
    }

    @Override
    AsyncReadOperation<AsyncBatchCursor<T>> asAsyncFirstReadOperation() {
        return getFindOperation(queryOptions.limit(1)
                .singleBatch());
    }

    private TurmsFindOperation<T> getFindOperation(QueryOptions options) {
        MongoNamespace namespace = getNamespace();
        CodecRegistry codecRegistry = getCodecRegistry();
        Class<T> documentClass = getDocumentClass();
        Codec<T> codec = codecRegistry.get(documentClass);
        BsonDocument filterDoc = filter.toBsonDocument(documentClass, codecRegistry);
        BsonDocument command = options.asDocument(namespace.getCollectionName(), filterDoc);
        return new TurmsFindOperation<>(namespace, codec, command).retryReads(getRetryReads());
    }

}