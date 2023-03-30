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

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;

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
    void joinLatin1() {
        String separator = ", ";
        assertThat(StringUtil.joinLatin1(separator, Collections.emptyList())).isEqualTo("");
        assertThat(StringUtil.joinLatin1(separator, List.of(1))).isEqualTo("1");
        assertThat(StringUtil.joinLatin1(separator, List.of(1, "abc"))).isEqualTo("1, abc");
        assertThat(StringUtil.joinLatin1(separator, List.of(1, "abc", Boolean.TRUE)))
                .isEqualTo("1, abc, true");
    }

    @Test
    void matchLatin1() {
        assertThat(StringUtil.matchLatin1("", "")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "Fo")).isFalse();
        assertThat(StringUtil.matchLatin1("Foo", "Foo")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "Bar")).isFalse();

        assertThat(StringUtil.matchLatin1("Foo", "*")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "Fo*")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "Fo**")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo Bar", "Fo*")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo Bar Foo", "F*o Bar*")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo Bar Foo", "F*b Bar*")).isFalse();
        assertThat(StringUtil.matchLatin1("Foo", "*Foo")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "Foo*")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "*Foo*")).isTrue();

        assertThat(StringUtil.matchLatin1("Foo", "???")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "?")).isFalse();
        assertThat(StringUtil.matchLatin1("Foo", "?Foo")).isFalse();
        assertThat(StringUtil.matchLatin1("Foo", "Fo?")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "Fo??")).isFalse();
        assertThat(StringUtil.matchLatin1("Foo Bar", "F?o B?r")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo Bar", "F?o B??r")).isFalse();

        assertThat(StringUtil.matchLatin1("Foo", "*?")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "**?")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "***?")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "?*")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "?**")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "?***")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "*o?")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "*?o?")).isTrue();
        assertThat(StringUtil.matchLatin1("Foo", "*??o?")).isFalse();
    }

    @Test
    void padStartLatin1() {
        assertThat(StringUtil.padStartLatin1("", 10, (byte) '*')).isEqualTo("**********");
        assertThat(StringUtil.padStartLatin1("abc", 10, (byte) '*')).isEqualTo("*******abc");
    }

    @Test
    void replaceLatin1() {
        assertThat(StringUtil.replaceLatin1("Hello, world!", (byte) 'o', (byte) 'a'))
                .isEqualTo("Hella, warld!");
        assertThat(StringUtil.replaceLatin1("", (byte) 'o', (byte) 'a')).isEmpty();
        assertThatThrownBy(() -> StringUtil.replaceLatin1(null, (byte) 'o', (byte) 'a'))
                .isExactlyInstanceOf(IllegalArgumentException.class);
        assertThat(StringUtil.replaceLatin1("Hello, world!", (byte) 'x', (byte) 'y'))
                .isEqualTo("Hello, world!");
    }

    @Test
    void caseFormatConversations() {
        assertThat(StringUtil.lowerCamelToLowerHyphenLatin1("helloWorld")).isEqualTo("hello-world");
        assertThat(StringUtil.lowerCamelToLowerHyphenLatin1("")).isEmpty();
        assertThat(StringUtil.lowerCamelToLowerHyphenLatin1("h")).isEqualTo("h");

        assertThat(StringUtil.upperCamelToLowerCamelLatin1("HelloWorld")).isEqualTo("helloWorld");
        assertThat(StringUtil.upperCamelToLowerCamelLatin1("")).isEmpty();
        assertThat(StringUtil.upperCamelToLowerCamelLatin1("H")).isEqualTo("h");

        assertThat(StringUtil.upperCamelToUpperUnderscoreLatin1("HelloWorld"))
                .isEqualTo("HELLO_WORLD");
        assertThat(StringUtil.upperCamelToUpperUnderscoreLatin1("")).isEmpty();
        assertThat(StringUtil.upperCamelToUpperUnderscoreLatin1("H")).isEqualTo("H");

        assertThat(StringUtil.upperCamelToLowerHyphenLatin1("HelloWorld")).isEqualTo("hello-world");
        assertThat(StringUtil.upperCamelToLowerHyphenLatin1("")).isEmpty();
        assertThat(StringUtil.upperCamelToLowerHyphenLatin1("H")).isEqualTo("h");
    }

    @Test
    void splitLatin1() {
        byte delimiter = ';';
        assertThat(StringUtil.splitLatin1("", delimiter)).isNull();
        assertThat(StringUtil.splitLatin1(";", delimiter)).isEqualTo(Pair.of("", ""));
        assertThat(StringUtil.splitLatin1(";abc", delimiter)).isEqualTo(Pair.of("", "abc"));
        assertThat(StringUtil.splitLatin1("abc;", delimiter)).isEqualTo(Pair.of("abc", ""));
        assertThat(StringUtil.splitLatin1(";abc;", delimiter)).isEqualTo(Pair.of("", "abc;"));
        assertThat(StringUtil.splitLatin1("123;abc", delimiter)).isEqualTo(Pair.of("123", "abc"));
        assertThat(StringUtil.splitLatin1("abc;123", delimiter)).isEqualTo(Pair.of("abc", "123"));
        assertThat(StringUtil.splitLatin1("123;abc;123", delimiter))
                .isEqualTo(Pair.of("123", "abc;123"));
    }

    @Test
    void substitute() {
        assertThat(StringUtil.substitute("my-{}-string", "test")).isEqualTo("my-test-string");
        assertThatThrownBy(() -> StringUtil.substitute("my-{}-string", "test", "string"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(StringUtil.substitute("my-{}-{}", "test", "string")).isEqualTo("my-test-string");
        assertThat(StringUtil.substitute("my-{}-{", "test")).isEqualTo("my-test-{");
        assertThat(StringUtil.substitute("my-{}-}", "test")).isEqualTo("my-test-}");
        assertThat(StringUtil.substitute("my-{{}-{{}}", "test", "string"))
                .isEqualTo("my-{test-{string}");
        assertThatThrownBy(() -> StringUtil.substitute("my-{}-{}", "test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void substringToFirstDelimiter() {
        char delimiter = '.';
        assertThat(StringUtil.substringToFirstDelimiter("", delimiter)).isEmpty();
        assertThat(StringUtil.substringToFirstDelimiter("abcde", delimiter)).isEqualTo("abcde");
        assertThat(StringUtil.substringToFirstDelimiter("abcde.", delimiter)).isEqualTo("abcde");
        assertThat(StringUtil.substringToFirstDelimiter("abcde.abc", delimiter)).isEqualTo("abcde");
    }

    @Test
    void toQuotedStringLatin1() {
        assertThat(StringUtil.toQuotedStringLatin1(Collections.emptyList())).isEmpty();
        assertThat(StringUtil.toQuotedStringLatin1(List.of("a"))).isEqualTo("[\"a\"]");
        assertThat(StringUtil.toQuotedStringLatin1(List.of("a", "b"))).isEqualTo("[\"a\", \"b\"]");
        assertThat(StringUtil.toQuotedStringLatin1(List.of("a", "b", "1")))
                .isEqualTo("[\"a\", \"b\", \"1\"]");
    }

}