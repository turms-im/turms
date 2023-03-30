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

package im.turms.server.common.storage.mongo.exception;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteError;
import com.mongodb.bulk.BulkWriteError;

import im.turms.server.common.storage.mongo.MongoErrorCodes;

/**
 * @author James Chen
 */
public final class MongoExceptionUtil {

    private MongoExceptionUtil() {
    }

    public static boolean isErrorOf(Throwable throwable, int errorCode) {
        if (throwable instanceof MongoException e) {
            return e.getCode() == errorCode;
        }
        return false;
    }

    public static Throwable translate(Throwable t) {
        if (t instanceof MongoWriteException e) {
            WriteError error = e.getError();
            if (error.getCategory()
                    .equals(ErrorCategory.DUPLICATE_KEY)) {
                return new DuplicateKeyException(t.getMessage(), t);
            }
            if (error.getCode() == MongoErrorCodes.DOCUMENT_VALIDATION_FAILURE) {
                throw new DocumentValidationFailureException(t.getMessage(), t);
            }
        } else if (t instanceof MongoBulkWriteException e) {
            boolean areAllDuplicateKeyErrors = true;
            boolean areAllValidationFailureErrors = true;
            for (BulkWriteError error : e.getWriteErrors()) {
                if (!error.getCategory()
                        .equals(ErrorCategory.DUPLICATE_KEY)) {
                    areAllDuplicateKeyErrors = false;
                    if (!areAllValidationFailureErrors) {
                        break;
                    }
                }
                if (error.getCode() != MongoErrorCodes.DOCUMENT_VALIDATION_FAILURE) {
                    areAllValidationFailureErrors = false;
                    if (!areAllDuplicateKeyErrors) {
                        break;
                    }
                }
            }
            if (areAllDuplicateKeyErrors) {
                return new DuplicateKeyException(t.getMessage(), t);
            } else if (areAllValidationFailureErrors) {
                return new DocumentValidationFailureException(t.getMessage(), t);
            }
        } else if (t instanceof com.mongodb.DuplicateKeyException) {
            return new DuplicateKeyException(t.getMessage(), t);
        }
        return t;
    }

}