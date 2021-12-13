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

import com.google.common.base.Strings;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.logging.core.context.LogThreadContext;
import im.turms.server.common.logging.core.model.LogLevel;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.DateUtil;
import im.turms.server.common.util.Formatter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author James Chen
 * @implNote Note that we do NOT escape or remove non-printable characters for logging
 * because it has a significant impact on performance. The caller of logger should ensure
 * won't pass malicious texts especially the user input texts
 */
public class TurmsTemplateLayout extends TemplateLayout {

    private static final int ESTIMATED_PATTERN_TEXT_LENGTH = 128;
    private static final byte[][] LEVELS;
    private static final byte[] NULL = new byte[]{'n', 'u', 'l', 'l'};
    private static final byte[] COLON_SEPARATOR = new byte[]{' ', ':', ' '};
    private static final int TRACE_ID_LENGTH = 19;
    private static final int CLASS_NAME_LENGTH = 40;

    static {
        LogLevel[] levels = LogLevel.values();
        LEVELS = new byte[levels.length][];
        int maxLength = 0;
        for (LogLevel level : levels) {
            maxLength = Math.max(level.name().length(), maxLength);
        }
        for (int i = 0; i < levels.length; i++) {
            LEVELS[i] = Strings.padStart(levels[i].name(), maxLength, ' ')
                    .toUpperCase()
                    .getBytes(StandardCharsets.US_ASCII);
        }
    }

    private final int nodeType;
    private final byte[] nodeId;

    public TurmsTemplateLayout(NodeType nodeType, String nodeId) {
        this.nodeType = nodeType == NodeType.GATEWAY ? 'G' : 'S';
        this.nodeId = nodeId.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * @implNote Note that we do NOT escape or remove non-printable characters
     */
    public ByteBuf format(boolean shouldParse, @Nullable byte[] className, LogLevel level, CharSequence msg, Object[] args, Throwable throwable) {
        int expectedLength = msg.length() + ESTIMATED_PATTERN_TEXT_LENGTH;
        if (args != null && shouldParse) {
            expectedLength += args.length * 8;
        }
        if (throwable != null) {
            expectedLength += 256;
        }
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(expectedLength);
        byte[] timestamp = DateUtil.toBytes(System.currentTimeMillis());

//        buffer.writeBytes(bytes);

        String threadName = Thread.currentThread().getName();

        // Write template text

        buffer.writeBytes(timestamp)
                .writeByte(WHITESPACE)
                .writeBytes(LEVELS[level.ordinal()])
                .writeByte(WHITESPACE)
                .writeByte(nodeType)
                .writeByte(WHITESPACE)
                .writeBytes(nodeId)
                .writeByte(WHITESPACE);
        TracingContext context = LogThreadContext.get();
        // trace ID
        if (context == null) {
            pad(buffer, TRACE_ID_LENGTH);
        } else {
            long traceId = context.getTraceId();
            if (traceId != TracingContext.UNDEFINED_TRACE_ID) {
                padStart(buffer, Formatter.toCharacterBytes(traceId), TRACE_ID_LENGTH);
            }
        }
        // thread name
        buffer.writeByte(WHITESPACE)
                .writeBytes(threadName.getBytes(StandardCharsets.UTF_8));
        // class name
        if (className != null) {
            buffer.writeByte(WHITESPACE)
                    .writeBytes(className);
        }
        buffer.writeBytes(COLON_SEPARATOR);
        // message
        appendMessage(shouldParse, msg, args, buffer);
        // exception
        if (throwable != null) {
            StringBuilder exception = appendException(throwable, new StringBuilder(256));
            buffer.writeCharSequence(exception, StandardCharsets.UTF_8);
        }
        // eol
        buffer.writeByte('\n');
        return buffer;
    }

    private void appendMessage(boolean shouldParse, CharSequence msg, Object[] args, ByteBuf buffer) {
        if (!shouldParse) {
            buffer.writeCharSequence(msg, StandardCharsets.UTF_8);
            return;
        }
        int argCount = args == null ? 0 : args.length;
        if (argCount == 0) {
            buffer.writeCharSequence(msg, StandardCharsets.UTF_8);
            return;
        }
        int argIndex = 0;
        byte[] bytes = msg.toString().getBytes();
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            byte b = bytes[i];
            if (b == '{' && i < length - 1 && bytes[i + 1] == '}') {
                if (argIndex < argCount) {
                    Object arg = args[argIndex++];
                    buffer.writeCharSequence(arg.toString(), StandardCharsets.UTF_8);
                } else {
                    buffer.writeBytes(NULL);
                }
                i++;
            } else {
                buffer.writeByte(b);
            }
        }
    }

    public static byte[] formatClassName(String name) {
        byte[] rawBytes = name.getBytes(StandardCharsets.US_ASCII);
        if (rawBytes.length == CLASS_NAME_LENGTH) {
            return name.getBytes(StandardCharsets.US_ASCII);
        }
        if (rawBytes.length > CLASS_NAME_LENGTH) {
            String[] parts = StringUtils.tokenizeToStringArray(name, ".");
            String className = parts[parts.length - 1];
            int classNameLength = className.length();
            if (classNameLength >= CLASS_NAME_LENGTH) {
                return className.substring(0, CLASS_NAME_LENGTH).getBytes(StandardCharsets.US_ASCII);
            }
            byte[] result = new byte[CLASS_NAME_LENGTH];
            int writeIndex = CLASS_NAME_LENGTH;
            for (int i = parts.length - 1; i >= 0; i--) {
                byte[] part = parts[i].getBytes(StandardCharsets.US_ASCII);
                if (i == parts.length - 1) {
                    writeIndex -= part.length;
                    System.arraycopy(part, 0, result, writeIndex, part.length);
                } else if (writeIndex >= 2) {
                    writeIndex -= 2;
                    result[writeIndex] = part[0];
                    result[writeIndex + 1] = '.';
                } else {
                    break;
                }
            }
            Arrays.fill(result, 0, writeIndex, (byte) ' ');
            return result;
        }
        return Strings.padStart(name, CLASS_NAME_LENGTH, ' ')
                .getBytes(StandardCharsets.US_ASCII);
    }

}
