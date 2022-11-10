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

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Chen
 */
public class StrJoiner {

    private final List<String> elements;
    private int charCount;

    public StrJoiner(int size) {
        elements = new ArrayList<>(size);
    }

    @Override
    public String toString() {
        byte[] newBytes = new byte[charCount];
        int writerIndex = 0;
        for (String element : elements) {
            byte[] elementBytes = StringUtil.getBytes(element);
            System.arraycopy(elementBytes, 0, newBytes, writerIndex, elementBytes.length);
            writerIndex += elementBytes.length;
        }
        return StringUtil.newLatin1String(newBytes);
    }

    public String toStringWithBrackets() {
        int size = charCount + 2;
        byte[] newBytes = new byte[size];
        newBytes[0] = '[';
        newBytes[size - 1] = ']';
        int writerIndex = 1;
        for (String element : elements) {
            byte[] elementBytes = StringUtil.getBytes(element);
            System.arraycopy(elementBytes, 0, newBytes, writerIndex, elementBytes.length);
            writerIndex += elementBytes.length;
        }
        return StringUtil.newLatin1String(newBytes);
    }

    public StrJoiner add(String element) {
        if (!StringUtil.isLatin1(element)) {
            throw new IllegalArgumentException("Only Latin1 string is supported");
        }
        elements.add(element);
        charCount += element.length();
        return this;
    }

}
