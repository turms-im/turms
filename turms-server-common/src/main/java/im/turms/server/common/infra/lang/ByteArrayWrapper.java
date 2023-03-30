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

import java.util.Arrays;

import lombok.Getter;

/**
 * @author James Chen
 */
public final class ByteArrayWrapper implements Comparable<ByteArrayWrapper> {

    @Getter
    private final byte[] bytes;
    private int hashCode = -1;

    public ByteArrayWrapper(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ByteArrayWrapper that) {
            return Arrays.equals(bytes, that.bytes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (hashCode == -1) {
            hashCode = Arrays.hashCode(bytes);
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return Arrays.toString(bytes);
    }

    @Override
    public int compareTo(ByteArrayWrapper o) {
        return Arrays.compare(bytes, o.bytes);
    }

}