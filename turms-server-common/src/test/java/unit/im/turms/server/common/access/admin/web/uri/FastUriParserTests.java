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

package unit.im.turms.server.common.access.admin.web.uri;

import im.turms.server.common.infra.lang.Pair;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static im.turms.server.common.access.admin.web.FastUriParser.parsePathAndQueryParams;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author James Chen
 */
class FastUriParserTests {

    @Test
    void test() {
        Pair<String, Map<String, List<Object>>> actual = parsePathAndQueryParams("/hello/world");
        assertThat(actual)
                .isEqualTo(Pair.of("/hello/world", Collections.emptyMap()));

        actual = parsePathAndQueryParams("/hello/world?");
        assertThat(actual)
                .isEqualTo(Pair.of("/hello/world", Collections.emptyMap()));

        actual = parsePathAndQueryParams("/hello/world?&&");
        assertThat(actual)
                .isEqualTo(Pair.of("/hello/world", Collections.emptyMap()));

        actual = parsePathAndQueryParams("/hello/world?" +
                "singleKey=singleValue&" +
                "nestObjects[0].a=1&nestObjects[0].b=2&nestObjects[1].a=3&nestObjects[1].b=4&" +
                "firstValues=value&firstValues=value2&firstValues=value3&" +
                "secondValues[]=value&secondValues[]=value2&secondValues[]=value3&" +
                "thirdValues[0]=1&thirdValues[1]=2&thirdValues[2]=3&thirdValues[3]=4&thirdValues[4]=5&" +
                "thirdValues[5]=1&thirdValues[6]=2&thirdValues[7]=3&thirdValues[8]=4&thirdValues[9]=5&" +
                "thirdValues[10]=1&thirdValues[11]=2&thirdValues[12]=3&thirdValues[13]=4&thirdValues[14]=5&");
        assertThat(actual)
                .isEqualTo(Pair.of("/hello/world", Map.of(
                        "singleKey", List.of("singleValue"),
                        "nestObjects", List.of(
                                Map.of("a", "1",
                                        "b", "2"),
                                Map.of("a", "3",
                                        "b", "4")
                        ),
                        "firstValues", List.of("value", "value2", "value3"),
                        "secondValues", List.of("value", "value2", "value3"),
                        "thirdValues", List.of("1", "2", "3", "4", "5", "1", "2", "3", "4", "5", "1", "2", "3", "4", "5")
                )));

        actual = parsePathAndQueryParams("/hello/world?firstValues[]=1&firstValues[]=2&" +
                "secondValues[0]=1&secondValues[1]=2&secondValues[2]=3&secondValues[3]=4");
        assertThat(actual)
                .isEqualTo(Pair.of("/hello/world", Map.of(
                        "firstValues", List.of("1", "2"),
                        "secondValues", List.of("1", "2", "3", "4")
                )));

        assertThatThrownBy(() -> parsePathAndQueryParams("/hello/world?values[]=1&values[1]=2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The array items should be all indexed or not indexed");

        actual = parsePathAndQueryParams("/metrics?names=system.cpu.usage%2Csystem.memory.total%2Csystem.memory.free%2Cdisk.total%2Cdisk.free");
        assertThat(actual)
                .isEqualTo(Pair.of("/metrics", Map.of(
                        "names", List.of("system.cpu.usage", "system.memory.total", "system.memory.free", "disk.total", "disk.free")
                )));

    }

}
