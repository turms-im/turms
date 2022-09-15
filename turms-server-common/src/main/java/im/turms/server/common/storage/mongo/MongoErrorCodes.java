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

import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;

/**
 * https://github.com/mongodb/mongo/blob/master/src/mongo/base/error_codes.yml
 *
 * @author James Chen
 */
public final class MongoErrorCodes {

    private MongoErrorCodes() {
    }

    public static final int NAMESPACE_EXISTS = 48;

    public static final int DOCUMENT_VALIDATION_FAILURE = 121;

    public static final int NO_SUCH_TRANSACTION = 251;
    public static final int TRANSACTION_COMMITTED = 256;
    public static final int TRANSACTION_TOO_LARGE = 257;
    public static final int PREPARED_TRANSACTION_IN_PROGRESS = 267;

    public static final int INTERRUPTED = 11601;

    public static final IntHashSet TRANSLATION_RELATED_ERROR_CODES = new IntHashSet(
            NO_SUCH_TRANSACTION,
            TRANSACTION_COMMITTED,
            TRANSACTION_TOO_LARGE,
            PREPARED_TRANSACTION_IN_PROGRESS,
            INTERRUPTED);

}