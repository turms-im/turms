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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import im.turms.server.common.infra.yaml.YamlUtil;

/**
 * @author James Chen
 */
public class TurmsPropertiesSerializer {

    private TurmsPropertiesSerializer() {
    }

    public static void persist(Path filePath, JsonNode properties) throws IOException {
        Path dir = filePath.getParent();
        if (dir != null) {
            Files.createDirectories(dir);
        }
        JsonNode node = JsonNodeFactory.instance.objectNode()
                .set(TurmsProperties.PROPERTIES_PREFIX, properties);
        YamlUtil.writeValue(filePath.toFile(), node);
    }

}
