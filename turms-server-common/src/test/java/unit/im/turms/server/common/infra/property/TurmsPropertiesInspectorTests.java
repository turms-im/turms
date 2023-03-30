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

package unit.im.turms.server.common.infra.property;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.property.TurmsPropertiesInspector;
import im.turms.server.common.testing.JsonUtil;

/**
 * @author James Chen
 */
class TurmsPropertiesInspectorTests {

    @SneakyThrows
    @Test
    void metadata() {
        JsonUtil.assertEqual(TurmsPropertiesInspector.METADATA,
                getClass().getClassLoader()
                        .getResourceAsStream("turms-properties-metadata.json"));
    }

    @SneakyThrows
    @Test
    void onlyMutableMetadata() {
        JsonUtil.assertEqual(TurmsPropertiesInspector.ONLY_MUTABLE_METADATA,
                getClass().getClassLoader()
                        .getResourceAsStream("turms-properties-only-mutable-metadata.json"));
    }

}
