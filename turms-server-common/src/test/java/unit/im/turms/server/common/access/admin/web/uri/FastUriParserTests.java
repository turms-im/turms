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

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.lang.Pair;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static im.turms.server.common.access.admin.web.FastUriParser.parsePathAndQueryParams;

/**
 * @author James Chen
 */
class FastUriParserTests {

    @Test
    void test() {
        testUrl("/hello/world", Pair.of("/hello/world", Collections.emptyMap()));

        testUrl("/hello/world?", Pair.of("/hello/world", Collections.emptyMap()));

        testUrl("/hello/world?&&", Pair.of("/hello/world", Collections.emptyMap()));

        testUrl("/hello/world?"
                + "empty=&"
                + "ids=123,321,789,987&"
                + "mybool=true",
                Pair.of("/hello/world",
                        Map.of("empty",
                                List.of(""),
                                "ids",
                                List.of("123", "321", "789", "987"),
                                "mybool",
                                List.of("true"))));

        testUrl("/hello/world?"
                + "keys%5B0%5D.a=1&keys%5B0%5D.b=1&"
                + "keys%5B1%5D.a=2&keys%5B1%5D.b=2&"
                + "keys%5B2%5D.a=3&keys%5B2%5D.b=3&"
                + "keys%5B3%5D.a=4&keys%5B3%5D.b=4&"
                + "keys%5B4%5D.a=5&keys%5B4%5D.b=5&"
                + "keys%5B5%5D.a=6&keys%5B5%5D.b=6&"
                + "keys%5B6%5D.a=7&keys%5B6%5D.b=7&"
                + "keys%5B7%5D.a=8&keys%5B7%5D.b=8&"
                + "keys%5B8%5D.a=9&keys%5B8%5D.b=9&"
                + "keys%5B9%5D.a=10&keys%5B9%5D.b=10&"
                + "keys%5B10%5D.a=11&keys%5B10%5D.b=11&"
                + "keys%5B11%5D.a=12&keys%5B11%5D.b=12&"
                + "keys%5B12%5D.a=12&keys%5B12%5D.b=12",
                Pair.of("/hello/world",
                        Map.of("keys",
                                List.of(Map.of("a", "1", "b", "1"),
                                        Map.of("a", "2", "b", "2"),
                                        Map.of("a", "3", "b", "3"),
                                        Map.of("a", "4", "b", "4"),
                                        Map.of("a", "5", "b", "5"),
                                        Map.of("a", "6", "b", "6"),
                                        Map.of("a", "7", "b", "7"),
                                        Map.of("a", "8", "b", "8"),
                                        Map.of("a", "9", "b", "9"),
                                        Map.of("a", "10", "b", "10"),
                                        Map.of("a", "11", "b", "11"),
                                        Map.of("a", "12", "b", "12"),
                                        Map.of("a", "12", "b", "12")))));

        testUrl("/hello/world?"
                + "singleKey=singleValue&"
                + "nestObjects%5B0%5D.a=1&"
                + "nestObjects%5B0%5D.b=2&"
                + "nestObjects%5B1%5D.a=3&"
                + "nestObjects%5B1%5D.b=4&"
                + "firstValues=value&"
                + "firstValues=value2&"
                + "firstValues=value3&"
                + "secondValues%5B%5D=value&"
                + "secondValues%5B%5D=value2&"
                + "secondValues%5B%5D=value3&"
                + "thirdValues%5B0%5D=1&"
                + "thirdValues%5B1%5D=2&"
                + "thirdValues%5B2%5D=3&"
                + "thirdValues%5B3%5D=4&"
                + "thirdValues%5B4%5D=5&"
                + "thirdValues%5B5%5D=1&"
                + "thirdValues%5B6%5D=2&"
                + "thirdValues%5B7%5D=3&"
                + "thirdValues%5B8%5D=4&"
                + "thirdValues%5B9%5D=5&"
                + "thirdValues%5B10%5D=1&"
                + "thirdValues%5B11%5D=2&"
                + "thirdValues%5B12%5D=3&"
                + "thirdValues%5B13%5D=4&"
                + "thirdValues%5B14%5D=5&",
                Pair.of("/hello/world",
                        Map.of("singleKey",
                                List.of("singleValue"),
                                "nestObjects",
                                List.of(Map.of("a", "1", "b", "2"), Map.of("a", "3", "b", "4")),
                                "firstValues",
                                List.of("value", "value2", "value3"),
                                "secondValues",
                                List.of("value", "value2", "value3"),
                                "thirdValues",
                                List.of("1",
                                        "2",
                                        "3",
                                        "4",
                                        "5",
                                        "1",
                                        "2",
                                        "3",
                                        "4",
                                        "5",
                                        "1",
                                        "2",
                                        "3",
                                        "4",
                                        "5"))));

        testUrl("/hello/world?"
                + "firstValues%5B%5D=1&"
                + "firstValues%5B%5D=2&"
                + "secondValues%5B0%5D=1&"
                + "secondValues%5B1%5D=2&"
                + "secondValues%5B2%5D=3&"
                + "secondValues%5B2%5D=4&"
                + "secondValues%5B2%5D=5&"
                + "secondValues%5B2%5D=6&"
                + "secondValues%5B2%5D=7&"
                + "secondValues%5B2%5D=8&"
                + "secondValues%5B2%5D=9&"
                + "secondValues%5B2%5D=10&"
                + "secondValues%5B2%5D=11&"
                + "secondValues%5B2%5D=12&"
                + "secondValues%5B3%5D=13",
                Pair.of("/hello/world",
                        Map.of("firstValues",
                                List.of("1", "2"),
                                "secondValues",
                                List.of("1",
                                        "2",
                                        "3",
                                        "4",
                                        "5",
                                        "6",
                                        "7",
                                        "8",
                                        "9",
                                        "10",
                                        "11",
                                        "12",
                                        "13"))));

        assertThatThrownBy(
                () -> parsePathAndQueryParams("/hello/world?values%5B%5D=1&values%5B1%5D=2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The array item must be all indexed or not indexed");

        testUrl("/metrics?names=system.cpu.usage%2Csystem.memory.total%2Csystem.memory.free%2Cdisk.total%2Cdisk.free",
                Pair.of("/metrics",
                        Map.of("names",
                                List.of("system.cpu.usage",
                                        "system.memory.total",
                                        "system.memory.free",
                                        "disk.total",
                                        "disk.free"))));
    }

    void testUrl(String encodedUrl, Object expected) {
        Pair<String, Map<String, List<Object>>> actual = parsePathAndQueryParams(encodedUrl);
        assertThat(actual).isEqualTo(expected);

        String decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8);
        actual = parsePathAndQueryParams(decodedUrl);
        assertThat(actual).isEqualTo(expected);
    }

}