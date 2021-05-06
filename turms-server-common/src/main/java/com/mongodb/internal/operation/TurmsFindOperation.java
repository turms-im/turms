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

package com.mongodb.internal.operation;

import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoNamespace;
import com.mongodb.MongoQueryException;
import com.mongodb.internal.async.AsyncBatchCursor;
import com.mongodb.internal.async.SingleResultCallback;
import com.mongodb.internal.binding.AsyncReadBinding;
import com.mongodb.internal.binding.ReadBinding;
import com.mongodb.internal.connection.Connection;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.internal.connection.QueryResult;
import com.mongodb.internal.session.SessionContext;
import com.mongodb.lang.Nullable;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

import static com.mongodb.assertions.Assertions.notNull;
import static com.mongodb.internal.async.ErrorHandlingResultCallback.errorHandlingCallback;
import static com.mongodb.internal.operation.CommandOperationHelper.executeCommandAsyncWithConnection;
import static com.mongodb.internal.operation.CommandOperationHelper.executeCommandWithConnection;
import static com.mongodb.internal.operation.ExplainHelper.asExplainCommand;
import static com.mongodb.internal.operation.OperationHelper.LOGGER;
import static com.mongodb.internal.operation.OperationHelper.cursorDocumentToQueryResult;
import static com.mongodb.internal.operation.OperationHelper.withAsyncReadConnection;
import static com.mongodb.internal.operation.OperationHelper.withReadConnectionSource;
import static com.mongodb.internal.operation.OperationReadConcernHelper.appendReadConcernToCommand;

/**
 * @author James Chen
 * @see FindOperation
 */
public class TurmsFindOperation<T> implements AsyncExplainableReadOperation<AsyncBatchCursor<T>>, ExplainableReadOperation<BatchCursor<T>> {
    private static final String FIRST_BATCH = "firstBatch";

    private final MongoNamespace namespace;
    private final Decoder<T> decoder;
    private final BsonDocument command;
    private boolean retryReads;

    public TurmsFindOperation(final MongoNamespace namespace, final Decoder<T> decoder, final BsonDocument command) {
        this.namespace = notNull("namespace", namespace);
        this.decoder = notNull("decoder", decoder);
        this.command = notNull("command", command);
    }

    /**
     * Gets the namespace.
     *
     * @return the namespace
     */
    public MongoNamespace getNamespace() {
        return namespace;
    }

    /**
     * Gets the decoder used to decode the result documents.
     *
     * @return the decoder
     */
    public Decoder<T> getDecoder() {
        return decoder;
    }

    /**
     * Enables retryable reads if a read fails due to a network error.
     *
     * @param retryReads true if reads should be retried
     * @return this
     * @since 3.11
     */
    public TurmsFindOperation<T> retryReads(final boolean retryReads) {
        this.retryReads = retryReads;
        return this;
    }

    @Override
    public BatchCursor<T> execute(final ReadBinding binding) {
        return withReadConnectionSource(binding, source -> {
            Connection connection = source.getConnection();
            try {
                return executeCommandWithConnection(binding, source, namespace.getDatabaseName(),
                        getCommandCreator(binding.getSessionContext()),
                        CommandResultDocumentCodec.create(decoder, FIRST_BATCH), transformer(), retryReads, connection);
            } catch (MongoCommandException e) {
                throw new MongoQueryException(e);
            }
        });
    }

    @Override
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        withAsyncReadConnection(binding, (source, connection, t) -> {
            SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = errorHandlingCallback(callback, LOGGER);
            if (t != null) {
                errHandlingCallback.onResult(null, t);
            } else {
                final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback =
                        exceptionTransformingCallback(errHandlingCallback);
                executeCommandAsyncWithConnection(binding, source, namespace.getDatabaseName(),
                        getCommandCreator(binding.getSessionContext()), CommandResultDocumentCodec.create(decoder, FIRST_BATCH),
                        asyncTransformer(), retryReads, connection, wrappedCallback);
            }
        });
    }

    private static <T> SingleResultCallback<T> exceptionTransformingCallback(final SingleResultCallback<T> callback) {
        return (result, t) -> {
            if (t != null) {
                if (t instanceof MongoCommandException) {
                    MongoCommandException commandException = (MongoCommandException) t;
                    callback.onResult(result, new MongoQueryException(commandException.getServerAddress(),
                            commandException.getErrorCode(),
                            commandException.getErrorMessage()));
                } else {
                    callback.onResult(result, t);
                }
            } else {
                callback.onResult(result, null);
            }
        };
    }

    @Override
    public <R> ReadOperation<R> asExplainableOperation(@Nullable final ExplainVerbosity verbosity,
                                                       final Decoder<R> resultDecoder) {
        appendReadConcernToCommand(NoOpSessionContext.INSTANCE, command);
        return new CommandReadOperation<>(getNamespace().getDatabaseName(),
                asExplainCommand(command, verbosity),
                resultDecoder);
    }

    @Override
    public <R> AsyncReadOperation<R> asAsyncExplainableOperation(@Nullable final ExplainVerbosity verbosity,
                                                                 final Decoder<R> resultDecoder) {
        appendReadConcernToCommand(NoOpSessionContext.INSTANCE, command);
        return new CommandReadOperation<>(getNamespace().getDatabaseName(),
                asExplainCommand(command, verbosity),
                resultDecoder);
    }


    private CommandOperationHelper.CommandCreator getCommandCreator(final SessionContext sessionContext) {
        return (serverDescription, connectionDescription) -> {
            appendReadConcernToCommand(sessionContext, command);
            return command;
        };
    }

    private CommandOperationHelper.CommandReadTransformer<BsonDocument, AggregateResponseBatchCursor<T>> transformer() {
        return (result, source, connection) -> {
            QueryResult<T> queryResult = cursorDocumentToQueryResult(result.getDocument("cursor"),
                    connection.getDescription().getServerAddress());
            return new QueryBatchCursor<>(queryResult, getLimit(), getBatchSize(), 0, decoder, source, connection, result);
        };
    }

    private CommandOperationHelper.CommandReadTransformerAsync<BsonDocument, AsyncBatchCursor<T>> asyncTransformer() {
        return (result, source, connection) -> {
            QueryResult<T> queryResult = cursorDocumentToQueryResult(result.getDocument("cursor"),
                    connection.getDescription().getServerAddress());
            return new AsyncQueryBatchCursor<>(queryResult, getLimit(), getBatchSize(), 0, decoder, source, connection,
                    result);
        };
    }

    private int getBatchSize() {
        BsonValue batchSize = command.get("batchSize");
        if (batchSize == null) {
            return 0;
        }
        return batchSize.asInt32().getValue();
    }

    private int getLimit() {
        BsonValue limit = command.get("limit");
        if (limit == null) {
            return 0;
        }
        return limit.asInt32().getValue();
    }
}