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

import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author James Chen
 */
public record Highlight(
        @JsonProperty("fields") Map<String, Map<String, Object>> fields,
        @JsonProperty("pre_tags") @Nullable List<String> preTags,
        @JsonProperty("post_tags") @Nullable List<String> postTags,
        @JsonProperty("type") @Nullable String type,
        @JsonProperty("number_of_fragments") @Nullable Integer numberOfFragments
) {
}