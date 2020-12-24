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

package im.turms.server.common.dao.util;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * @author James Chen
 */
public class OperationResultUtil {

    private OperationResultUtil() {
    }

    public static DeleteResult update2delete(UpdateResult result) {
        if (result.wasAcknowledged()) {
            return DeleteResult.acknowledged(result.getModifiedCount());
        } else {
            return DeleteResult.unacknowledged();
        }
    }

    public static UpdateResult delete2update(DeleteResult result) {
        if (result.wasAcknowledged()) {
            long deletedCount = result.getDeletedCount();
            return UpdateResult.acknowledged(deletedCount, deletedCount, null);
        } else {
            return UpdateResult.unacknowledged();
        }
    }

    //    public static void throwsForIllegalResult(UpdateResult result, Class<?> entityClass, UpdateDefinition update) {
//        if (result.getModifiedCount() <= 0 && result.getMatchedCount() > 0) {
//            // e.g. "Invalid $addFields :: caused by :: an empty object is not a valid value."
//            String reason = String.format("The update definition for the class %s may be wrong: %s", entityClass, update);
//            throw TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, reason);
//        }
//    }
//
//    public static void throwsForIllegalUpsertResult(UpdateResult result, Class<?> entityClass, UpdateDefinition update) {
//        if (result.getModifiedCount() <= 0) {
//            // e.g. "Invalid $addFields :: caused by :: an empty object is not a valid value."
//            String reason = String.format("The update definition for the class %s may be wrong: %s", entityClass, update);
//            throw TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, reason);
//        }
//    }

}