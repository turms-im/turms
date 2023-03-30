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

package unit.im.turms.server.common.infra.cluster.node;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.cluster.node.NodeVersion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author James Chen
 */
class NodeVersionTests {

    @Test
    void parse_shouldBeSame_forCorrectVersionWithoutQualifier() {
        String version = "1.2.3";
        NodeVersion nodeVersion = NodeVersion.parse(version);
        assertThat(nodeVersion.toString()).hasToString(version);
    }

    @Test
    void parse_shouldBeSame_forCorrectVersionWithQualifier() {
        String version = "1.2.3-SNAPSHOT";
        NodeVersion nodeVersion = NodeVersion.parse(version);
        assertThat(nodeVersion.toString()).hasToString(version);
    }

    @Test
    void parse_shouldThrow_forWrongVersionWithoutQualifier() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> NodeVersion.parse("1.2.3.4"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> NodeVersion.parse("1.2"));
    }

    @Test
    void parse_shouldThrow_forWrongVersionWithQualifier() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> NodeVersion.parse("1.2.3-SNAP"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> NodeVersion.parse("1.2-SNAP"));
    }

}
