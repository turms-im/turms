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

package im.turms.service.storage.elasticsearch;

import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import im.turms.server.common.infra.property.env.service.env.elasticsearch.LanguageCode;
import im.turms.service.storage.elasticsearch.model.IndexSettingsAnalysis;
import im.turms.service.storage.elasticsearch.model.Property;

/**
 * @author James Chen
 */
public class DefaultLanguageSettings {

    // Use the analyzer name as the field name
    // so that it is convenient and consistent to introduce
    // new fields with different analyzers in the future.
    public static final String ANALYZER_KUROMOJI = "turms_kuromoji_analyzer";
    public static final String ANALYZER_NGRAM = "turms_ngram_analyzer";
    public static final String TOKENIZER_KUROMOJI = "turms_kuromoji_tokenizer";
    public static final String TOKENIZER_NGRAM = "turms_ngram_tokenizer";

    public static final IndexTextFieldSetting DEFAULT = new IndexTextFieldSetting(
            Map.of("standard", new Property(Property.Type.TEXT, "standard", null, null)),
            null);

    private static final IndexTextFieldSetting JA = new IndexTextFieldSetting(
            Map.of("kuromoji",
                    new Property(Property.Type.TEXT, ANALYZER_KUROMOJI, null, null),
                    "ngram",
                    new Property(Property.Type.TEXT, ANALYZER_NGRAM, null, null)),
            new IndexSettingsAnalysis(
                    Map.of(
                            // Used to improve precision.
                            ANALYZER_KUROMOJI,
                            Map.of("type",
                                    "custom",
                                    "char_filter",
                                    List.of("icu_normalizer"),
                                    "tokenizer",
                                    TOKENIZER_KUROMOJI,
                                    "filter",
                                    List.of("kuromoji_baseform",
                                            "kuromoji_part_of_speech",
                                            "cjk_width",
                                            "ja_stop",
                                            "kuromoji_stemmer",
                                            "lowercase")),
                            // Used to improve recall.
                            ANALYZER_NGRAM,
                            Map.of("type",
                                    "custom",
                                    "char_filter",
                                    List.of("icu_normalizer"),
                                    "tokenizer",
                                    TOKENIZER_NGRAM,
                                    "filter",
                                    List.of("lowercase"))),
                    null,
                    null,
                    null,
                    Map.of(TOKENIZER_KUROMOJI,
                            Map.of("mode", "search", "type", "kuromoji_tokenizer"),
                            TOKENIZER_NGRAM,
                            Map.of("type",
                                    "ngram",
                                    // Use the same values to avoid disk usage explosion.
                                    "min_gram",
                                    2,
                                    "max_gram",
                                    2,
                                    "token_char",
                                    List.of("letter", "digit")))));
    private static final IndexTextFieldSetting ZH = new IndexTextFieldSetting(
            Map.of("ik", new Property(Property.Type.TEXT, "ik_max_word", "ik_smart", null)),
            null);

    private static final Map<LanguageCode, IndexTextFieldSetting> LANGUAGE_CODE_TO_SETTING =
            Map.of(LanguageCode.NONE, DEFAULT, LanguageCode.JA, JA, LanguageCode.ZH, ZH);

    private DefaultLanguageSettings() {
    }

    @Nullable
    public static IndexTextFieldSetting getSetting(LanguageCode code) {
        return LANGUAGE_CODE_TO_SETTING.get(code);
    }

}