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

import java.io.Writer;

import im.turms.server.common.infra.thread.NotThreadSafe;

/**
 * @author James Chen
 */
@NotThreadSafe
public class StringBuilderWriter extends Writer {

    private final StringBuilder builder;
    private final int maxLength;

    public StringBuilderWriter(int capacity, int maxLength) {
        this.builder = new StringBuilder(capacity);
        this.maxLength = maxLength;
    }

    @Override
    public Writer append(char value) {
        builder.append(value);
        return this;
    }

    @Override
    public Writer append(CharSequence value) {
        builder.append(value);
        return this;
    }

    @Override
    public Writer append(CharSequence value, int start, int end) {
        builder.append(value, start, end);
        return this;
    }

    @Override
    public void close() {
        if (builder.length() > maxLength) {
            builder.setLength(maxLength);
            builder.trimToSize();
        }
        builder.setLength(0);
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(String value) {
        if (value != null) {
            builder.append(value);
        }
    }

    @Override
    public void write(char[] value, int offset, int length) {
        if (value != null) {
            builder.append(value, offset, length);
        }
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}