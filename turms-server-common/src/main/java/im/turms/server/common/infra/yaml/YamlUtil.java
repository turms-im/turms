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

package im.turms.server.common.infra.yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * @author James Chen
 */
public final class YamlUtil {

    private static final YAMLMapper MAPPER =
            new YAMLMapper().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);

    private static final ObjectWriter WRITER = MAPPER.writerWithDefaultPrettyPrinter();

    private static final ObjectReader READER = MAPPER.reader();

    private YamlUtil() {
    }

    public static void writeValue(File file, JsonNode node) throws IOException {
        WRITER.writeValue(file, node);
    }

    public static <T> T readValue(InputStream stream, Class<T> valueType) throws IOException {
        return READER.readValue(stream, valueType);
    }
}
