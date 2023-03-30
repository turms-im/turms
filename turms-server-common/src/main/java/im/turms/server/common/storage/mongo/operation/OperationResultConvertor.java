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

package im.turms.server.common.storage.mongo.operation;

import java.util.Collection;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * @author James Chen
 */
public final class OperationResultConvertor {

    private OperationResultConvertor() {
    }

    public static DeleteResult update2delete(UpdateResult result) {
        return result.wasAcknowledged()
                ? DeleteResult.acknowledged(result.getModifiedCount())
                : DeleteResult.unacknowledged();
    }

    public static UpdateResult delete2update(DeleteResult result) {
        if (result.wasAcknowledged()) {
            long deletedCount = result.getDeletedCount();
            return UpdateResult.acknowledged(deletedCount, deletedCount, null);
        }
        return UpdateResult.unacknowledged();
    }

    public static DeleteResult merge(DeleteResult r1, DeleteResult r2) {
        boolean wasAcknowledged = false;
        long count = 0;
        if (r1.wasAcknowledged()) {
            count += r1.getDeletedCount();
            wasAcknowledged = true;
        }
        if (r2.wasAcknowledged()) {
            count += r2.getDeletedCount();
            wasAcknowledged = true;
        }
        return wasAcknowledged
                ? DeleteResult.acknowledged(count)
                : DeleteResult.unacknowledged();
    }

    public static DeleteResult merge(Collection<DeleteResult> results) {
        boolean wasAcknowledged = false;
        long count = 0;
        for (DeleteResult result : results) {
            if (result.wasAcknowledged()) {
                wasAcknowledged = true;
                count += result.getDeletedCount();
            }
        }
        return wasAcknowledged
                ? DeleteResult.acknowledged(count)
                : DeleteResult.unacknowledged();
    }

}