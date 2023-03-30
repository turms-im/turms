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

package unit.im.turms.server.common.infra.codec;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.codec.Base62;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class Base62Tests {

    private static final Base62 ENCODER = Base62.getDefaultInstance();

    @Test
    void testNumber() {
        Map<Long, String> map = new HashMap<>() {
            {
                put(0L, "0");
                put(1L, "1");
                put(12L, "C");
                put(123L, "1z");
                put(1234L, "Ju");
                put(12345L, "3D7");
                put(123456L, "W7E");
                put(1234567L, "5BAN");
                put(12345678L, "pnfq");
                put(123456789L, "8M0kX");
                put(1234567890L, "1LY7VK");
                put(12345678901L, "DTVD3F");
                put(123456789012L, "2Al26WS");
                put(1234567890123L, "LjaL3EZ");
                put(12345678901234L, "3VLrOWLi");
                put(123456789012345L, "Z3WbxDVB");
                put(1234567890123456L, "5eZG7YB1s");
                put(12345678901234567L, "uXgbDVmIp");
                put(123456789012345678L, "97Qs0B7n2M");
                put(1234567890123456789L, "1TCKi1nFuNh");
                put(Long.MAX_VALUE, "AzL8n0Y58m7");
            }
        };
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            long base10 = entry.getKey();
            byte[] encodeAsNumber = ENCODER.encode(base10);
            assertThat(new String(encodeAsNumber)).isEqualTo(entry.getValue());
        }
    }

    @Test
    void testAlphaNumericStrings() {
        byte[][] messages = {createIncreasingByteArray(),
                createZeroesByteArray(1),
                createZeroesByteArray(512),
                createPseudoRandomByteArray(0xAB, 40),
                createPseudoRandomByteArray(0x1C, 40),
                createPseudoRandomByteArray(0xF2, 40)};
        for (byte[] message : messages) {
            byte[] encoded = ENCODER.encode(message);
            String encodedStr = new String(encoded);
            assertThat(isAlphaNumeric(encodedStr)).isTrue();
        }
    }

    @Test
    void testAlphaOrNumberOnlyStrings() {
        Map<String, String> testSet = Map.of("",
                "",
                "a",
                "1Z",
                "Hello",
                "5TP3P3v",
                "Hello world!",
                "T8dgcjRGuYUueWht",
                "Just a test",
                "7G0iTmJjQFG2t6K",
                "!!!!!!!!!!!!!!!!!",
                "4A7f43EVXQoS6Am897ZKbAn",
                "0123456789",
                "18XU2xYejWO9d3",
                "The quick brown fox jumps over the lazy dog",
                "83UM8dOjD4xrzASgmqLOXTgTagvV1jPegUJ39mcYnwHwTlzpdfKXvpp4RL",
                "Sphinx of black quartz, judge my vow",
                "1Ul5yQGNM8YFBp3sz19dYj1kTp95OW7jI8pTcTP5JhYjIaFmx");
        for (Map.Entry<String, String> testSetEntry : testSet.entrySet()) {
            String s = new String(
                    ENCODER.encode(testSetEntry.getKey()
                            .getBytes()));
            assertThat(s).isEqualTo(testSetEntry.getValue());
        }
    }

    private static boolean isAlphaNumeric(String str) {
        return str.matches("^[a-zA-Z0-9]+$");
    }

    private static byte[] createIncreasingByteArray() {
        byte[] arr = new byte[256];
        for (int i = 0; i < 256; i++) {
            arr[i] = (byte) (i & 0xFF);
        }
        return arr;
    }

    private static byte[] createZeroesByteArray(int size) {
        return new byte[size];
    }

    private static byte[] createPseudoRandomByteArray(int seed, int size) {
        byte[] arr = new byte[size];
        int state = seed;
        for (int i = 0; i < size; i += 4) {
            state = xorshift(state);
            for (int j = 0; j < 4 && i + j < size; j++) {
                arr[i + j] = (byte) ((state >> j) & 0xFF);
            }
        }
        return arr;
    }

    private static int xorshift(int x) {
        x ^= (x << 13);
        x ^= (x >> 17);
        x ^= (x << 5);
        return x;
    }

}