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

package im.turms.server.common.lang;

import java.util.Arrays;

/**
 * @author James Chen
 */
public record ByteWrapper(byte[] bytes) implements Comparable {

    @Override
    public boolean equals(Object o) {
        if (o instanceof ByteWrapper that) {
            return Arrays.equals(bytes, that.bytes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return Arrays.toString(bytes);
    }

    @Override
    public int compareTo(Object o) {
        return Arrays.compare(bytes, ((ByteWrapper) o).bytes);
    }

}