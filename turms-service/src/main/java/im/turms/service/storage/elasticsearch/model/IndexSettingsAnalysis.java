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

import java.util.Map;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import im.turms.server.common.infra.collection.CollectionUtil;

/**
 * @author James Chen
 */
public record IndexSettingsAnalysis(
        @JsonProperty("analyzer") @Nullable Map<String, Map<String, Object>> analyzer,
        @JsonProperty("char_filter") @Nullable Map<String, Map<String, Object>> charFilter,
        @JsonProperty("filter") @Nullable Map<String, Map<String, Object>> filter,
        @JsonProperty("normalizer") @Nullable Map<String, Map<String, Object>> normalizer,
        @JsonProperty("tokenizer") @Nullable Map<String, Map<String, Object>> tokenizer
) {

    public IndexSettingsAnalysis merge(IndexSettingsAnalysis analysis) {
        return new IndexSettingsAnalysis(
                analyzer == null
                        ? analysis.analyzer
                        : analysis.analyzer == null
                                ? null
                                : CollectionUtil.merge(analyzer, analysis.analyzer),
                charFilter == null
                        ? analysis.charFilter
                        : analysis.charFilter == null
                                ? null
                                : CollectionUtil.merge(charFilter, analysis.charFilter),
                filter == null
                        ? analysis.filter
                        : analysis.filter == null
                                ? null
                                : CollectionUtil.merge(filter, analysis.filter),
                normalizer == null
                        ? analysis.normalizer
                        : analysis.normalizer == null
                                ? null
                                : CollectionUtil.merge(normalizer, analysis.normalizer),
                tokenizer == null
                        ? analysis.tokenizer
                        : analysis.tokenizer == null
                                ? null
                                : CollectionUtil.merge(tokenizer, analysis.tokenizer));
    }

}