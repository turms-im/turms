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
import com.mongodb.client.cursor.TimeoutMode;
import com.mongodb.internal.async.AsyncBatchCursor;
import com.mongodb.internal.async.SingleResultCallback;
import com.mongodb.internal.async.function.AsyncCallbackSupplier;
import com.mongodb.internal.async.function.RetryState;
import com.mongodb.internal.binding.AsyncReadBinding;
import com.mongodb.internal.binding.ReadBinding;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.lang.Nullable;
import lombok.Getter;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

import static com.mongodb.assertions.Assertions.notNull;
import static com.mongodb.internal.async.ErrorHandlingResultCallback.errorHandlingCallback;
import static com.mongodb.internal.operation.AsyncOperationHelper.createReadCommandAndExecuteAsync;
import static com.mongodb.internal.operation.AsyncOperationHelper.decorateReadWithRetriesAsync;
import static com.mongodb.internal.operation.AsyncOperationHelper.withAsyncSourceAndConnection;
import static com.mongodb.internal.operation.CommandOperationHelper.initialRetryState;
import static com.mongodb.internal.operation.ExplainHelper.asExplainCommand;
import static com.mongodb.internal.operation.OperationHelper.LOGGER;
import static com.mongodb.internal.operation.OperationHelper.canRetryRead;
import static com.mongodb.internal.operation.OperationReadConcernHelper.appendReadConcernToCommand;
import static com.mongodb.internal.operation.ServerVersionHelper.UNKNOWN_WIRE_VERSION;

/**
 * @author James Chen
 * @see FindOperation
 */
public class TurmsFindOperation<T> implements AsyncExplainableReadOperation<AsyncBatchCursor<T>>,
        ExplainableReadOperation<BatchCursor<T>> {
    private static final String FIRST_BATCH = "firstBatch";

    @Getter
    private final MongoNamespace namespace;
    private final Decoder<T> decoder;
    private final BsonDocument command;
    private boolean retryReads;

    public TurmsFindOperation(
            final MongoNamespace namespace,
            final Decoder<T> decoder,
            final BsonDocument command) {
        this.namespace = notNull("namespace", namespace);
        this.decoder = notNull("decoder", decoder);
        this.command = notNull("command", command);
    }

    public TurmsFindOperation<T> retryReads(final boolean retryReads) {
        this.retryReads = retryReads;
        return this;
    }

    @Override
    public BatchCursor<T> execute(final ReadBinding binding) {
        throw new UnsupportedOperationException("Should execute asynchronously");
    }

    @Override
    public void executeAsync(
            final AsyncReadBinding binding,
            final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        RetryState retryState = initialRetryState(retryReads,
                binding.getOperationContext()
                        .getTimeoutContext());
        binding.retain();
        AsyncCallbackSupplier<AsyncBatchCursor<T>> asyncRead = decorateReadWithRetriesAsync(
                retryState,
                binding.getOperationContext(),
                (AsyncCallbackSupplier<AsyncBatchCursor<T>>) funcCallback -> withAsyncSourceAndConnection(
                        binding::getReadConnectionSource,
                        false,
                        funcCallback,
                        (source, connection, releasingCallback) -> {
                            if (retryState
                                    .breakAndCompleteIfRetryAnd(
                                            () -> !canRetryRead(source.getServerDescription(),
                                                    binding.getOperationContext()),
                                            releasingCallback)) {
                                return;
                            }
                            SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback =
                                    exceptionTransformingCallback(releasingCallback);
                            createReadCommandAndExecuteAsync(retryState,
                                    binding.getOperationContext(),
                                    source,
                                    namespace.getDatabaseName(),
                                    getCommandCreator(),
                                    CommandResultDocumentCodec.create(decoder, FIRST_BATCH),
                                    asyncTransformer(),
                                    connection,
                                    wrappedCallback);
                        }))
                .whenComplete(binding::release);
        asyncRead.get(errorHandlingCallback(callback, LOGGER));
    }

    private static <T> SingleResultCallback<T> exceptionTransformingCallback(
            final SingleResultCallback<T> callback) {
        return (result, t) -> {
            if (t != null) {
                if (t instanceof MongoCommandException commandException) {
                    callback.onResult(result,
                            new MongoQueryException(
                                    commandException.getResponse(),
                                    commandException.getServerAddress()));
                } else {
                    callback.onResult(result, t);
                }
            } else {
                callback.onResult(result, null);
            }
        };
    }

    @Override
    public <R> ReadOperation<R> asExplainableOperation(
            @Nullable final ExplainVerbosity verbosity,
            final Decoder<R> resultDecoder) {
        appendReadConcernToCommand(NoOpSessionContext.INSTANCE, UNKNOWN_WIRE_VERSION, command);
        return new CommandReadOperation<>(
                getNamespace().getDatabaseName(),
                asExplainCommand(command, verbosity),
                resultDecoder);
    }

    @Override
    public <R> AsyncReadOperation<R> asAsyncExplainableOperation(
            @Nullable final ExplainVerbosity verbosity,
            final Decoder<R> resultDecoder) {
        appendReadConcernToCommand(NoOpSessionContext.INSTANCE, UNKNOWN_WIRE_VERSION, command);
        return new CommandReadOperation<>(
                getNamespace().getDatabaseName(),
                asExplainCommand(command, verbosity),
                resultDecoder);
    }

    private CommandOperationHelper.CommandCreator getCommandCreator() {
        return (operationContext, serverDescription, connectionDescription) -> {
            appendReadConcernToCommand(operationContext.getSessionContext(),
                    connectionDescription.getMaxWireVersion(),
                    command);
            return command;
        };
    }

    private AsyncOperationHelper.CommandReadTransformerAsync<BsonDocument, AsyncBatchCursor<T>> asyncTransformer() {
        return (result, source, connection) -> new AsyncCommandBatchCursor<>(
                TimeoutMode.CURSOR_LIFETIME,
                result,
                getBatchSize(),
                0,
                decoder,
                null,
                source,
                connection);
    }

    private int getBatchSize() {
        BsonValue batchSize = command.get("batchSize");
        if (batchSize == null) {
            return 0;
        }
        return batchSize.asInt32()
                .getValue();
    }

}