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

import java.nio.charset.StandardCharsets;

import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.lang.Utf8String;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author James Chen
 */
class Utf8StringTests {

    @Test
    void length_emptyString_returnZero() {
        Utf8String str = Utf8String.of("");
        assertThat(str.length()).isZero();
    }

    @Test
    void length_notEmptyString_returnLength() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat(str.length()).isEqualTo(10);

        str = Utf8String.of("Hello");
        assertThat(str.length()).isEqualTo(5);

        str = Utf8String.of("世界");
        assertThat(str.length()).isEqualTo(2);

        str = Utf8String.of("🌍");
        assertThat(str.length()).isEqualTo(1);
    }

    @Test
    void isEmpty_emptyString_returnTrue() {
        Utf8String str = Utf8String.of("");
        assertThat(str.isEmpty()).isTrue();
    }

    @Test
    void isEmpty_notEmptyString_returnFalse() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat(str.isEmpty()).isFalse();

        str = Utf8String.of("Hello");
        assertThat(str.isEmpty()).isFalse();

        str = Utf8String.of("世界");
        assertThat(str.isEmpty()).isFalse();

        str = Utf8String.of("🌍");
        assertThat(str.isEmpty()).isFalse();
    }

    @Test
    void hashCode_sameStrings_returnSameHashCode() {
        Utf8String str1 = Utf8String.of("Hello, 世界🌍");
        Utf8String str2 = Utf8String.of("Hello, 世界🌍");
        assertThat((CharSequence) str1).hasSameHashCodeAs(str2);
    }

    @Test
    void equals_sameStrings_returnTrue() {
        Utf8String str1 = Utf8String.of("Hello, 世界🌍");
        Utf8String str2 = Utf8String.of("Hello, 世界🌍");
        assertThat(str1.equals(str2)).isTrue();
    }

    @Test
    void equals_differentStrings_returnFalse() {
        Utf8String str1 = Utf8String.of("Hello, 世界🌍");
        Utf8String str2 = Utf8String.of("Bonjour, monde🌍");
        assertThat(str1.equals(str2)).isFalse();
    }

    @Test
    void charAt_validIndex_returnChar() {
        // The unicode of "🌍" is 0x1F30D
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat(str.charAt(0)).isEqualTo('H');
        assertThat(str.charAt(1)).isEqualTo('e');
        assertThat(str.charAt(2)).isEqualTo('l');
        assertThat(str.charAt(3)).isEqualTo('l');
        assertThat(str.charAt(4)).isEqualTo('o');
        assertThat(str.charAt(5)).isEqualTo(',');
        assertThat(str.charAt(6)).isEqualTo(' ');
        assertThat(str.charAt(7)).isEqualTo('世');
        assertThat(str.charAt(8)).isEqualTo('界');
        assertThat(str.charAt(9)).isEqualTo('\uF30D');
    }

    @Test
    void charAt_invalidIndex_throw() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThatThrownBy(() -> str.charAt(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.charAt(10)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.charAt(11)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void compareTo() {
        Utf8String str1 = Utf8String.of("hello");
        Utf8String str2 = Utf8String.of("hello");
        assertThat(str1.compareTo(str2)).isZero();
        assertThat(str2.compareTo(str1)).isZero();

        str1 = Utf8String.of("apple");
        str2 = Utf8String.of("banana");
        assertThat(str1.compareTo(str2)).isNegative();
        assertThat(str2.compareTo(str1)).isPositive();

        str2 = Utf8String.of("appl");
        assertThat(str1.compareTo(str2)).isPositive();
        assertThat(str2.compareTo(str1)).isNegative();
    }

    @Test
    void substring_validOffsetAndCount_returnSubstring() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat((CharSequence) str.substring(0, 10)).hasToString("Hello, 世界🌍");
        assertThat((CharSequence) str.substring(0, 5)).hasToString("Hello");
        assertThat((CharSequence) str.substring(7, 8)).hasToString("世");
        assertThat((CharSequence) str.substring(9, 10)).hasToString("🌍");
        assertThat((CharSequence) str.substring(7, 9)).hasToString("世界");
        assertThat((CharSequence) str.substring(0, 0)).hasToString("");
        assertThat((CharSequence) str.substring(10, 10)).hasToString("");
    }

    @Test
    void substring_validOffset_returnSubstring() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat((CharSequence) str.substring(0)).hasToString("Hello, 世界🌍");
        assertThat((CharSequence) str.substring(10)).hasToString("");
        assertThat((CharSequence) str.substring(7)).hasToString("世界🌍");
    }

    @Test
    void substring_invalidOffset_throw() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThatThrownBy(() -> str.substring(-1, 0))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.substring(-1, -1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.substring(-1, 11))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.substring(0, 11))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.substring(11, 12))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void getBytes() {
        String string = "Hello, 世界🌍";
        assertThat(Utf8String.of(string)
                .getBytes()).isEqualTo(string.getBytes(StandardCharsets.UTF_8));
        string = "Hello";
        assertThat(Utf8String.of(string)
                .getBytes()).isEqualTo(string.getBytes(StandardCharsets.UTF_8));
        string = "世界";
        assertThat(Utf8String.of(string)
                .getBytes()).isEqualTo(string.getBytes(StandardCharsets.UTF_8));
        string = "🌍";
        assertThat(Utf8String.of(string)
                .getBytes()).isEqualTo(string.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void byteIndexOf_validIndex_returnIndex() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat(str.byteIndexOf(0)).isEqualTo(0);
        assertThat(str.byteIndexOf(1)).isEqualTo(1);
        assertThat(str.byteIndexOf(2)).isEqualTo(2);
        assertThat(str.byteIndexOf(3)).isEqualTo(3);
        assertThat(str.byteIndexOf(4)).isEqualTo(4);
        assertThat(str.byteIndexOf(5)).isEqualTo(5);
        assertThat(str.byteIndexOf(6)).isEqualTo(6);
        assertThat(str.byteIndexOf(7)).isEqualTo(7);
        assertThat(str.byteIndexOf(8)).isEqualTo(10);
        assertThat(str.byteIndexOf(9)).isEqualTo(13);
    }

    @Test
    void byteIndexOf_invalidIndex_throw() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThatThrownBy(() -> str.byteIndexOf(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.byteIndexOf(11)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void codepointAt_validIndex_returnIndex() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThat(str.codepointAt(0)).isEqualTo('H');
        assertThat(str.codepointAt(1)).isEqualTo('e');
        assertThat(str.codepointAt(2)).isEqualTo('l');
        assertThat(str.codepointAt(3)).isEqualTo('l');
        assertThat(str.codepointAt(4)).isEqualTo('o');
        assertThat(str.codepointAt(5)).isEqualTo(',');
        assertThat(str.codepointAt(6)).isEqualTo(' ');
        assertThat(str.codepointAt(7)).isEqualTo('世');
        assertThat(str.codepointAt(8)).isEqualTo('界');
        assertThat(str.codepointAt(9)).isEqualTo(127757);
    }

    @Test
    void codepointAt_invalidIndex_throw() {
        Utf8String str = Utf8String.of("Hello, 世界🌍");
        assertThatThrownBy(() -> str.codepointAt(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> str.codepointAt(11)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testReference() throws InterruptedException {
        Cache<String, Utf8String> cache = Utf8String.STRING_TO_UTF8;
        cache.invalidateAll();

        String string = new String("Hello, 世界🌍");
        Utf8String utf8String1 = Utf8String.of(string);
        Utf8String utf8String2 = Utf8String.of(string);
        assertThat((CharSequence) utf8String1).isEqualTo(utf8String2);

        cache.cleanUp();
        assertThat(cache.estimatedSize()).isEqualTo(1);

        string = null;
        System.gc();
        Thread.sleep(3000);
        cache.cleanUp();
        assertThat(cache.estimatedSize()).isEqualTo(0);
    }
}