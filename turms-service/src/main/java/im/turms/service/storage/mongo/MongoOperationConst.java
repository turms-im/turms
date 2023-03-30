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

package im.turms.service.storage.mongo;

import java.time.Duration;
import java.util.List;

import com.mongodb.MongoCommandException;
import reactor.util.retry.Retry;

import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.storage.mongo.MongoErrorCodes;
import im.turms.server.common.storage.mongo.exception.DocumentValidationFailureException;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;

/**
 * @author James Chen
 */
public final class MongoOperationConst {

    private MongoOperationConst() {
    }

    public static final int MONGO_TRANSACTION_RETRIES_NUMBER = 3;
    public static final Duration MONGO_TRANSACTION_BACKOFF = Duration.ofSeconds(3);

    public static final List<Class<? extends Throwable>> NON_RETRYABLE_EXCEPTIONS =
            List.of(DocumentValidationFailureException.class,
                    DuplicateKeyException.class,
                    ResponseException.class);
    public static final Retry TRANSACTION_RETRY =
            Retry.fixedDelay(MONGO_TRANSACTION_RETRIES_NUMBER, MONGO_TRANSACTION_BACKOFF)
                    .filter(throwable -> {
                        if (throwable == null) {
                            return true;
                        }
                        for (Class<? extends Throwable> clazz : NON_RETRYABLE_EXCEPTIONS) {
                            if (clazz.isInstance(throwable)) {
                                return false;
                            }
                        }
                        if (throwable instanceof MongoCommandException e
                                && MongoErrorCodes.TRANSLATION_RELATED_ERROR_CODES
                                        .contains(e.getErrorCode())) {
                            return false;
                        }
                        return true;
                    });

}