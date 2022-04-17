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

package im.turms.server.common.infra.property;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author James Chen
 */
public class TurmsPropertiesSerializer {

    private static final YAMLFactory YAML_FACTORY = new YAMLFactory();
    private static final ObjectWriter YAML_WRITER = new YAMLMapper(YAML_FACTORY)
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            .writerWithDefaultPrettyPrinter();

    private TurmsPropertiesSerializer() {
    }

    public static void persist(Path filePath, JsonNode properties) throws IOException {
        Path dir = filePath.getParent();
        if (dir != null) {
            Files.createDirectories(dir);
        }
        try (SequenceWriter writer = YAML_WRITER.writeValues(filePath.toFile())) {
            writer.write(JsonNodeFactory.instance.objectNode()
                    .set("turms", properties));
        }
    }

}
