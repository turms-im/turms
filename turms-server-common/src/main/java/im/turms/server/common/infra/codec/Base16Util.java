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

package im.turms.server.common.infra.codec;

import im.turms.server.common.infra.lang.StringUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public final class Base16Util {

    private static final byte[] upper = "0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);

    private static final byte[] lower = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);

    private Base16Util() {
    }

    public static byte[] encode(byte[] bytes, boolean upper) {
        byte[] table = upper ? Base16Util.upper : Base16Util.lower;
        byte[] dst = new byte[bytes.length * 2];
        for (int i = 0, j = 0; i < bytes.length; i++) {
            dst[j++] = table[(bytes[i] >> 4) & 0x0f];
            dst[j++] = table[bytes[i] & 0x0f];
        }
        return dst;
    }

    public static String encodeAsString(byte[] src, boolean upper) {
        return StringUtil.newLatin1String(encode(src, upper));
    }

}