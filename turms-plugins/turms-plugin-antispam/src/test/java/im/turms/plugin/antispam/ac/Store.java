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

package im.turms.plugin.antispam.ac;

import java.util.List;
import java.util.stream.Stream;

import im.turms.plugin.antispam.dictionary.Word;

/**
 * @author James Chen
 */
public final class Store {

    public static final List<Word> UNWANTED_WORDS = Stream.of("123",

            "敏感词",
            "敏感词句",

            "薬指",
            "リング",
            "人目忍ぶ恋",
            "選んだ",
            "強い",
            "女",
            "見えて",
            "心",
            "中",
            "切なさ",
            "揺れて",

            "oh no",
            "oh yes",
            "but no",
            "loving you is not right",

            "𤳵")
            .map(s -> new Word(s.toCharArray()))
            .toList();

    private Store() {
    }
}
