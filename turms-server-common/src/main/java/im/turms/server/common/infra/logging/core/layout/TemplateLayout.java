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

package im.turms.server.common.infra.logging.core.layout;

import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.lang.StringUtil;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public class TemplateLayout {

    static final byte[] AT = "at ".getBytes(StandardCharsets.US_ASCII);
    static final byte[] CAUSED_BY = "caused by: ".getBytes(StandardCharsets.US_ASCII);
    static final byte[] COLON = ": ".getBytes(StandardCharsets.US_ASCII);
    static final byte[] CYCLIC_EXCEPTION = ">>(Cyclic Exception?)>>".getBytes(StandardCharsets.US_ASCII);
    static final byte[] NATIVE = "native".getBytes(StandardCharsets.US_ASCII);
    static final byte[] SUPPRESSED = "suppressed: ".getBytes(StandardCharsets.US_ASCII);
    static final byte[] UNKNOWN = "unknown".getBytes(StandardCharsets.US_ASCII);

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

    public void appendException(Throwable e, ByteBuf buffer) {
        buffer.writeByte('\n');
        appendException(e, buffer, 1, 1);
    }

    private void appendException(Throwable e,
                                 ByteBuf buffer,
                                 int indent,
                                 int depth) {
        if (depth > DEPTH_LIMIT) {
            buffer.writeBytes(CYCLIC_EXCEPTION);
            return;
        }

        buffer.writeBytes(StringUtil.getBytes(e.getClass().getName()));

        String message = e.getMessage();
        if (message != null) {
            buffer.writeBytes(COLON)
                    .writeBytes(StringUtil.getBytes(message));
        }

        StackTraceElement[] stacks = e.getStackTrace();
        Throwable[] suppresses = e.getSuppressed();
        Throwable cause = e.getCause();

        boolean isStacksNotEmpty = stacks != null && stacks.length > 0;
        boolean isSuppressesNotEmpty = suppresses != null && suppresses.length > 0;
        boolean isCauseNotNull = cause != null;

        if (isStacksNotEmpty || isSuppressesNotEmpty || isCauseNotNull) {
            buffer.writeByte('\n');
        } else {
            return;
        }

        if (isStacksNotEmpty) {
            appendStack(stacks, buffer, indent);
        }

        if (isSuppressesNotEmpty) {
            appendSuppresses(suppresses, buffer, indent, depth);
        }

        if (isCauseNotNull) {
            appendCause(cause, buffer, indent, depth);
        }
    }

    private void appendStack(StackTraceElement[] stack,
                             ByteBuf buffer,
                             int indent) {
        for (StackTraceElement element : stack) {
            appendTabs(indent, buffer);

            buffer.writeBytes(AT);
            buffer.writeBytes(StringUtil.getBytes(element.getClassName()));
            buffer.writeByte('.');
            buffer.writeBytes(StringUtil.getBytes(element.getMethodName()));
            buffer.writeByte('(');

            if (element.isNativeMethod()) {
                buffer.writeBytes(NATIVE);
            } else {
                String fileName = element.getFileName();
                int lineNumber = element.getLineNumber();
                if (fileName == null) {
                    buffer.writeBytes(UNKNOWN);
                } else {
                    buffer.writeBytes(StringUtil.getBytes(fileName));
                    if (lineNumber >= 0) {
                        buffer.writeByte(':');
                        buffer.writeBytes(NumberFormatter.toCharBytes(lineNumber));
                    }
                }
            }
            buffer.writeByte(')');
            buffer.writeByte('\n');
        }
    }

    public void appendSuppresses(Throwable[] suppresses,
                                 ByteBuf buffer,
                                 int indent,
                                 int depth) {
        for (Throwable suppress : suppresses) {
            buffer.writeByte('\n');
            appendTabs(indent, buffer);
            buffer.writeBytes(SUPPRESSED);
            appendException(suppress, buffer, indent + 1, depth + 1);
        }
    }

    public void appendCause(Throwable cause,
                            ByteBuf buffer,
                            int indent,
                            int depth) {
        buffer.writeByte('\n');
        appendTabs(indent - 1, buffer);
        buffer.writeBytes(CAUSED_BY);
        appendException(cause, buffer, indent, depth + 1);
    }

    public void appendTabs(int tabs, ByteBuf buffer) {
        for (int i = 0; i < tabs; i++) {
            buffer.writeByte('\t');
        }
    }

}