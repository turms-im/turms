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

package unit.im.turms.service.infra.cluster;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.service.infra.cluster.NodeConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class NodeConfigTests {

    @Test
    void nodeType_shouldReturnService() {
        assertThat(new NodeConfig().nodeType()).isEqualTo(NodeType.SERVICE);
    }

}
