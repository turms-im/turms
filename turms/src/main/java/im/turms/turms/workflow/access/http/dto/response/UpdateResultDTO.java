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

package im.turms.turms.workflow.access.http.dto.response;

import com.mongodb.client.result.UpdateResult;
import lombok.Data;

/**
 * @author James Chen
 * @implNote In most cases,
 * it's meaningful to use UpdateResultDTO instead of TurmsStatusCode.OK for the result of update operations
 * because a update operation is usually considered as successful even if the no documents changed (no documents matched)
 */
@Data
public class UpdateResultDTO {
    private final Long matchedCount;
    private final Long modifiedCount;

    public static UpdateResultDTO get(UpdateResult result) {
        return new UpdateResultDTO(result.getMatchedCount(), result.getModifiedCount());
    }
}