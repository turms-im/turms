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

import java.util.LinkedList;
import java.util.List;

/**
 * @author James Chen
 */
public class FastStringBuilder {

    private final List<Entry> cache = new LinkedList<>();
    private int length = 0;

    public String build(byte coder, byte delimiter) {
        boolean isLatin1 = StringUtil.isLatin1(coder);
        int delimiterLength = isLatin1
                ? 1
                : 2;
        int entryCount = cache.size();
        byte[] value = new byte[length + delimiterLength * (entryCount - 1)];
        int destPos = 0;
        int i = 0;
        for (Entry entry : cache) {
            System.arraycopy(entry.bytes, entry.from, value, destPos, entry.length);
            if (i != entryCount - 1) {
                destPos += entry.length;
                value[destPos++] = delimiter;
                if (!isLatin1) {
                    value[destPos++] = 0;
                }
                i++;
            }
        }
        return StringUtil.newString(value, coder);
    }

    public byte[] buildAsBytes() {
        byte[] value = new byte[length];
        int destPos = 0;
        for (Entry entry : cache) {
            System.arraycopy(entry.bytes, entry.from, value, destPos, entry.length);
            destPos += entry.length;
        }
        return value;
    }

    public String buildLatin1() {
        return StringUtil.newLatin1String(buildAsBytes());
    }

    public void append(String str) {
        byte[] bytes = StringUtil.getBytes(str);
        cache.add(new Entry(bytes, 0, bytes.length));
        length += bytes.length;
    }

    public void append(byte[] bytes, int from, int length) {
        cache.add(new Entry(bytes, from, length));
        this.length += length;
    }

    public int entryCount() {
        return cache.size();
    }

    public record Entry(
            byte[] bytes,
            int from,
            int length
    ) {
    }

}
