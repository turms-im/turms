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

package unit.im.turms.server.common.infra.lang;

import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author James Chen
 */
class StringUtilTests {

    @Test
    void test() {
        String expected = "myテスト字符串";
        byte[] bytes = StringUtil.getBytes(expected);
        byte coder = StringUtil.getCoder(expected);
        String actual = StringUtil.newString(bytes, coder);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void match() {
        assertThat(StringUtil.match("", "")).isTrue();
        assertThat(StringUtil.match("Foo", "Fo")).isFalse();
        assertThat(StringUtil.match("Foo", "Foo")).isTrue();
        assertThat(StringUtil.match("Foo", "Bar")).isFalse();

        assertThat(StringUtil.match("Foo", "*")).isTrue();
        assertThat(StringUtil.match("Foo", "Fo*")).isTrue();
        assertThat(StringUtil.match("Foo", "Fo**")).isTrue();
        assertThat(StringUtil.match("Foo Bar", "Fo*")).isTrue();
        assertThat(StringUtil.match("Foo Bar Foo", "F*o Bar*")).isTrue();
        assertThat(StringUtil.match("Foo Bar Foo", "F*b Bar*")).isFalse();
        assertThat(StringUtil.match("Foo", "*Foo")).isTrue();
        assertThat(StringUtil.match("Foo", "Foo*")).isTrue();
        assertThat(StringUtil.match("Foo", "*Foo*")).isTrue();

        assertThat(StringUtil.match("Foo", "???")).isTrue();
        assertThat(StringUtil.match("Foo", "?")).isFalse();
        assertThat(StringUtil.match("Foo", "?Foo")).isFalse();
        assertThat(StringUtil.match("Foo", "Fo?")).isTrue();
        assertThat(StringUtil.match("Foo", "Fo??")).isFalse();
        assertThat(StringUtil.match("Foo Bar", "F?o B?r")).isTrue();
        assertThat(StringUtil.match("Foo Bar", "F?o B??r")).isFalse();

        assertThat(StringUtil.match("Foo", "*?")).isTrue();
        assertThat(StringUtil.match("Foo", "**?")).isTrue();
        assertThat(StringUtil.match("Foo", "***?")).isTrue();
        assertThat(StringUtil.match("Foo", "?*")).isTrue();
        assertThat(StringUtil.match("Foo", "?**")).isTrue();
        assertThat(StringUtil.match("Foo", "?***")).isTrue();
        assertThat(StringUtil.match("Foo", "*o?")).isTrue();
        assertThat(StringUtil.match("Foo", "*?o?")).isTrue();
        assertThat(StringUtil.match("Foo", "*??o?")).isFalse();
    }

    @Test
    void splitLatin1() {
        byte delimiter = ';';
        assertThat(StringUtil.splitLatin1("", delimiter))
                .isNull();
        assertThat(StringUtil.splitLatin1(";", delimiter))
                .isEqualTo(Pair.of("", ""));
        assertThat(StringUtil.splitLatin1(";abc", delimiter))
                .isEqualTo(Pair.of("", "abc"));
        assertThat(StringUtil.splitLatin1("abc;", delimiter))
                .isEqualTo(Pair.of("abc", ""));
        assertThat(StringUtil.splitLatin1(";abc;", delimiter))
                .isEqualTo(Pair.of("", "abc;"));
        assertThat(StringUtil.splitLatin1("123;abc", delimiter))
                .isEqualTo(Pair.of("123", "abc"));
        assertThat(StringUtil.splitLatin1("abc;123", delimiter))
                .isEqualTo(Pair.of("abc", "123"));
        assertThat(StringUtil.splitLatin1("123;abc;123", delimiter))
                .isEqualTo(Pair.of("123", "abc;123"));
    }

    @Test
    void substitute() {
        assertThat(StringUtil.substitute("my-{}-string", "test"))
                .isEqualTo("my-test-string");
        assertThatThrownBy(() -> StringUtil.substitute("my-{}-string", "test", "string"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(StringUtil.substitute("my-{}-{}", "test", "string"))
                .isEqualTo("my-test-string");
        assertThat(StringUtil.substitute("my-{}-{", "test"))
                .isEqualTo("my-test-{");
        assertThat(StringUtil.substitute("my-{}-}", "test"))
                .isEqualTo("my-test-}");
        assertThat(StringUtil.substitute("my-{{}-{{}}", "test", "string"))
                .isEqualTo("my-{test-{string}");
        assertThatThrownBy(() -> StringUtil.substitute("my-{}-{}", "test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
