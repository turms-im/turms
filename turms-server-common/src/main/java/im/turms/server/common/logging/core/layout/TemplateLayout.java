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

package im.turms.server.common.logging.core.layout;

import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public class TemplateLayout {

    static final int DEPTH_LIMIT = 16;
    static final int WHITESPACE = ' ';

    public void pad(ByteBuf buffer, int minLength) {
        for (int i = 0; i < minLength; i++) {
            buffer.writeByte(WHITESPACE);
        }
    }

    public void padStart(ByteBuf buffer, byte[] bytes, int minLength) {
        int diff = minLength - bytes.length;
        for (int i = 0; i < diff; i++) {
            buffer.writeByte(WHITESPACE);
        }
        buffer.writeBytes(bytes);
    }

    public StringBuilder appendException(Throwable e, StringBuilder entry) {
        entry.append('\n');
        return appendException(e, entry, 1, 1);
    }

    private StringBuilder appendException(Throwable e,
                                          StringBuilder builder,
                                          int indent,
                                          int depth) {
        if (depth > DEPTH_LIMIT) {
            builder.append(">>(Cyclic Exception?)>>");
            return builder;
        }

        builder.append(e.getClass().getName());

        String message = e.getMessage();
        if (message != null) {
            builder.append(": ")
                    .append(message);
        }

        StackTraceElement[] stacks = e.getStackTrace();
        Throwable[] suppresses = e.getSuppressed();
        Throwable cause = e.getCause();

        boolean isStacksNotEmpty = stacks != null && stacks.length > 0;
        boolean isSuppressesNotEmpty = suppresses != null && suppresses.length > 0;
        boolean isCauseNotNull = cause != null;

        if (isStacksNotEmpty || isSuppressesNotEmpty || isCauseNotNull) {
            builder.append('\n');
        }

        if (isStacksNotEmpty) {
            appendStack(stacks, builder, indent);
        }

        if (isSuppressesNotEmpty) {
            appendSuppresses(suppresses, builder, indent, depth);
        }

        if (isCauseNotNull) {
            appendCause(cause, builder, indent, depth);
        }
        return builder;
    }

    private void appendStack(StackTraceElement[] stack,
                             StringBuilder builder,
                             int indent) {
        for (StackTraceElement element : stack) {
            appendTabs(indent, builder);

            builder.append("at ");
            builder.append(element.getClassName());
            builder.append('.');
            builder.append(element.getMethodName());
            builder.append('(');

            if (element.isNativeMethod()) {
                builder.append("native");
            } else {
                String fileName = element.getFileName();
                int lineNumber = element.getLineNumber();
                if (fileName == null) {
                    builder.append("unknown");
                } else {
                    builder.append(fileName);
                    if (lineNumber >= 0) {
                        builder.append(':');
                        builder.append(lineNumber);
                    }
                }
            }
            builder.append(')');
            builder.append('\n');
        }
    }

    public void appendSuppresses(Throwable[] suppresses,
                                 StringBuilder entry,
                                 int indent,
                                 int depth) {
        for (Throwable suppress : suppresses) {
            entry.append('\n');
            appendTabs(indent, entry);
            entry.append("suppressed: ");
            appendException(suppress, entry, indent + 1, depth + 1);
        }
    }

    public void appendCause(Throwable cause,
                            StringBuilder entry,
                            int indent,
                            int depth) {
        entry.append('\n');
        appendTabs(indent - 1, entry);
        entry.append("caused by: ");
        appendException(cause, entry, indent, depth + 1);
    }

    public void appendTabs(int tabs, StringBuilder builder) {
        for (int i = 0; i < tabs; i++) {
            builder.append('\t');
        }
    }

}