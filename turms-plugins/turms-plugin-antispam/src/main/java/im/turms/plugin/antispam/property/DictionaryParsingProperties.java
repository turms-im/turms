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

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@Data
public class DictionaryParsingProperties {

    @Description("Path to the raw text dictionary file. Used to build a trie from scratch")
    private String textFilePath;

    private String textFileCharset = "UTF-8";

    @Description("Whether to skip invalid characters in the dictionary file. e.g. '/', '?'. "
            + "If false, the plugin will throw if encounters an invalid character when parsing")
    private boolean skipInvalidCharacter = true;

    @Description("Path to the binary dictionary file. "
            + "Used to build a trie from binary to avoid building a trie from scratch")
    private String binFilePath;

    @NestedConfigurationProperty
    private ExtendedWordProperties extendedWord = new ExtendedWordProperties();

}