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

package im.turms.service.storage.elasticsearch.model;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author James Chen
 */
public record BulkResponseItem(
        @JsonProperty("_id") @Nullable String id,
        @JsonProperty("_index") String index,
        @JsonProperty("status") int status,
        @JsonProperty("error") @Nullable ErrorCause error,
        @JsonProperty("result") @Nullable String result,
        @JsonProperty("_seq_no") @Nullable Long seqNo,
        @JsonProperty("_shards") @Nullable ShardStatistics shards,
        @JsonProperty("_version") @Nullable Long version
) {
}