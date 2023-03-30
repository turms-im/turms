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

package im.turms.plugin.antispam.property;

import java.util.Collections;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static im.turms.plugin.antispam.property.TextParsingStrategy.NORMALIZATION_TRANSLITERATION;
import static im.turms.plugin.antispam.property.UnwantedWordHandleStrategy.REJECT_REQUEST;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@ConfigurationProperties("turms.plugin.antispam")
@Data
@NoArgsConstructor
public class AntiSpamProperties {

    private boolean enabled = true;

    @NestedConfigurationProperty
    private DictionaryParsingProperties dictParsing = new DictionaryParsingProperties();

    private TextParsingStrategy textParsingStrategy = NORMALIZATION_TRANSLITERATION;

    private UnwantedWordHandleStrategy unwantedWordHandleStrategy = REJECT_REQUEST;

    private byte mask = '*';

    private int maxNumberOfUnwantedWordsToReturn;

    private Set<TextType> textTypes = Set.of(TextType.values());

    private Set<TextType> silentIllegalTextTypes = Collections.emptySet();

}