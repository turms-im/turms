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

package im.turms.server.common.infra.lang;

/**
 * @author James Chen
 */
public final class ByteUtil {

    private ByteUtil() {
    }

    public static boolean isAllZeros(byte[] bytes) {
        for (byte b : bytes) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static int indexOf(byte[] bytes, char c, int start, int end) {
        for (int i = start; i < end; i++) {
            if (bytes[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] bytes, String target, int start, int end) {
        int length = target.length();
        for (int i = start; i < end; i++) {
            if (bytes[i] == target.charAt(0)) {
                boolean match = true;
                for (int j = 0; j < length; j++) {
                    if (i + j >= end || bytes[i + j] != target.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i;
                }
            }
        }
        return -1;
    }
}