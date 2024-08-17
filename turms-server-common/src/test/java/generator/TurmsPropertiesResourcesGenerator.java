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

package generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import helper.ResourceUtil;
import lombok.SneakyThrows;

import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesConvertor;
import im.turms.server.common.infra.property.TurmsPropertiesInspector;
import im.turms.server.common.testing.JsonUtil;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.METADATA;

/**
 * @author James Chen
 */
class TurmsPropertiesResourcesGenerator {

    private static final OpenOption[] OPEN_OPTIONS = {StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE};
    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer(new JsonPrettyPrinter());

    @SneakyThrows
    public static void main(String[] args) {
        write(TurmsPropertiesConvertor.mergeMetadataWithPropertyValue(METADATA,
                new TurmsProperties()),
                ResourceUtil.TEST_RESOURCES_PATH
                        .resolve("turms-properties-metadata-with-property-value.json"));
        write(TurmsPropertiesInspector.METADATA,
                ResourceUtil.TEST_RESOURCES_PATH.resolve("turms-properties-metadata.json"));
        write(TurmsPropertiesInspector.ONLY_MUTABLE_METADATA,
                ResourceUtil.TEST_RESOURCES_PATH
                        .resolve("turms-properties-only-mutable-metadata.json"));
        TurmsPropertiesPropertiesFileGenerator.generate();
    }

    private static void write(Map<String, Object> map, Path path) throws IOException {
        JsonNode jsonNode = JsonUtil.getSortedMapJsonNode(map);
        byte[] bytes = OBJECT_WRITER.writeValueAsBytes(jsonNode);
        Files.write(path, bytes, OPEN_OPTIONS);
    }

    public static class JsonPrettyPrinter extends DefaultPrettyPrinter {

        @Override
        public DefaultPrettyPrinter createInstance() {
            return new JsonPrettyPrinter();
        }

        @Override
        public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException {
            jg.writeRaw(": ");
        }

        @Override
        public void beforeArrayValues(JsonGenerator g) throws IOException {
            super.beforeArrayValues(g);
        }
    }
}