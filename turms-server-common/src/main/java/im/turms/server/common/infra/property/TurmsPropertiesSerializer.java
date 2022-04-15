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

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.util.concurrent.FastThreadLocal;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MUTABLE_PROPERTIES_WRITER;

/**
 * @author James Chen
 */
public class TurmsPropertiesSerializer {

    private static final FastThreadLocal<Yaml> YAML = new FastThreadLocal<>() {
        @Override
        protected Yaml initialValue() throws Exception {
            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            return new Yaml(options);
        }
    };

    private TurmsPropertiesSerializer() {
    }

    public static void persist(Path filePath, String propertiesJson) throws IOException {
        ObjectNode tree = TurmsPropertiesInspector.getNotNullPropertiesTree(propertiesJson);
        Yaml yaml = YAML.get();
        String configYaml = yaml.dump(yaml.load(MUTABLE_PROPERTIES_WRITER.writeValueAsString(tree)));
        Path dir = filePath.getParent();
        if (dir != null) {
            Files.createDirectories(dir);
        }
        Files.writeString(filePath, configYaml, StandardCharsets.UTF_8,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE);
    }

}
