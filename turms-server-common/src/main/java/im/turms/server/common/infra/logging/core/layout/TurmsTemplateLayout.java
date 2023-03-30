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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.springframework.util.StringUtils;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.context.LogThreadContext;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.tracing.TracingContext;

/**
 * @author James Chen
 * @implNote 1. Note that we do NOT escape or remove non-printable characters for logging because it
 *           has an impact on performance. The caller of logger should ensure won't pass malicious
 *           texts especially the user input texts. 2. The template is designed for ascii-only text
 *           because: a. We won't convert String to the consistent UTF-8 bytes because it needs to
 *           copy memory b. We encourage caller to pass ascii-only texts only. In other words, if
 *           the message, args, or exception includes non-ascii text, the logger will just print
 *           error codes
 */
public class TurmsTemplateLayout extends TemplateLayout {

    private static final int ESTIMATED_PATTERN_TEXT_LENGTH = 128;
    private static final byte[][] LEVELS;
    private static final byte[] NULL = {'n', 'u', 'l', 'l'};
    private static final byte[] COLON_SEPARATOR = {' ', ':', ' '};
    private static final int TRACE_ID_LENGTH = 19;
    private static final int CLASS_NAME_LENGTH = 40;

    private static final int NODE_TYPE_GATEWAY = 'G';
    private static final int NODE_TYPE_SERVICE = 'S';
    private static final int NODE_TYPE_UNKNOWN = 'U';

    static {
        LogLevel[] levels = ClassUtil.getSharedEnumConstants(LogLevel.class);
        int levelCount = levels.length;
        LEVELS = new byte[levelCount][];
        int maxLength = 0;
        for (LogLevel level : levels) {
            maxLength = Math.max(level.name()
                    .length(), maxLength);
        }
        for (int i = 0; i < levelCount; i++) {
            String level = StringUtil.padStartLatin1(levels[i].name(), maxLength, AsciiCode.SPACE)
                    .toUpperCase();
            LEVELS[i] = StringUtil.getBytes(level);
        }
    }

    private final int nodeType;
    private final byte[] nodeId;

    public TurmsTemplateLayout(@Nullable NodeType nodeType, String nodeId) {
        int type;
        if (nodeType == null) {
            type = NODE_TYPE_UNKNOWN;
        } else {
            type = switch (nodeType) {
                case GATEWAY -> NODE_TYPE_GATEWAY;
                case SERVICE -> NODE_TYPE_SERVICE;
                default -> throw new IncompatibleInternalChangeException(
                        "Unknown node type: "
                                + nodeType);
            };
        }
        this.nodeType = type;
        this.nodeId = StringUtil.getUtf8Bytes(nodeId);
    }

    public ByteBuf format(@Nullable byte[] className, LogLevel level, ByteBuf msg) {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(ESTIMATED_PATTERN_TEXT_LENGTH);
        try {
            return format0(buffer, className, level, msg);
        } catch (Exception e) {
            ReferenceCountUtil.safeEnsureReleased(buffer);
            ReferenceCountUtil.safeEnsureReleased(msg);
            throw e;
        }
    }

    /**
     * @implNote Note that we do NOT escape or remove non-printable characters
     */
    public ByteBuf format(
            boolean shouldParse,
            @Nullable byte[] className,
            LogLevel level,
            @Nullable CharSequence msg,
            @Nullable Object[] args,
            @Nullable Throwable throwable) {
        int estimatedThrowableLength = 0;
        if (throwable != null) {
            int causes = ThrowableUtil.countCauses(throwable);
            estimatedThrowableLength = causes == 0
                    ? 64
                    : causes * 1024;
        }
        int estimatedLength = (msg == null
                ? 0
                : msg.length()) + ESTIMATED_PATTERN_TEXT_LENGTH + estimatedThrowableLength;
        if (args != null && shouldParse) {
            estimatedLength += args.length * 16;
        }
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(estimatedLength);
        try {
            return format0(buffer, shouldParse, className, level, msg, args, throwable);
        } catch (Exception e) {
            ReferenceCountUtil.safeEnsureReleased(buffer);
            throw e;
        }
    }

    private ByteBuf format0(
            ByteBuf buffer,
            boolean shouldParse,
            @Nullable byte[] className,
            LogLevel level,
            CharSequence msg,
            Object[] args,
            Throwable throwable) {
        byte[] timestamp = DateUtil.toBytes(System.currentTimeMillis());

        String threadName = Thread.currentThread()
                .getName();

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
            if (traceId != TracingContext.UNSET_TRACE_ID) {
                padStart(buffer, NumberFormatter.toCharBytes(traceId), TRACE_ID_LENGTH);
            }
        }
        // thread name
        buffer.writeByte(WHITESPACE)
                .writeBytes(StringUtil.getBytes(threadName));
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
            appendException(throwable, buffer);
        }
        // eol
        buffer.writeByte('\n');
        return buffer;
    }

    private ByteBuf format0(
            ByteBuf buffer,
            @Nullable byte[] className,
            LogLevel level,
            ByteBuf msg) {
        byte[] timestamp = DateUtil.toBytes(System.currentTimeMillis());

        String threadName = Thread.currentThread()
                .getName();

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
            if (traceId != TracingContext.UNSET_TRACE_ID) {
                padStart(buffer, NumberFormatter.toCharBytes(traceId), TRACE_ID_LENGTH);
            }
        }
        // thread name
        buffer.writeByte(WHITESPACE)
                .writeBytes(StringUtil.getBytes(threadName));
        // class name
        if (className != null) {
            buffer.writeByte(WHITESPACE)
                    .writeBytes(className);
        }
        buffer.writeBytes(COLON_SEPARATOR);
        // eol
        msg.writeByte('\n');

        return PooledByteBufAllocator.DEFAULT.compositeBuffer(2)
                .addComponent(true, buffer)
                .addComponent(true, msg);
    }

    private void appendMessage(
            boolean shouldParse,
            CharSequence msg,
            Object[] args,
            ByteBuf buffer) {
        String message = msg.toString();
        if (!shouldParse) {
            buffer.writeBytes(StringUtil.getBytes(message));
            return;
        }
        int argCount = args == null
                ? 0
                : args.length;
        if (argCount == 0) {
            buffer.writeBytes(StringUtil.getBytes(message));
            return;
        }
        int argIndex = 0;
        byte[] bytes = StringUtil.getBytes(message);
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            byte b = bytes[i];
            if (b == '{' && i < length - 1 && bytes[i + 1] == '}') {
                if (argIndex < argCount) {
                    Object arg = args[argIndex++];
                    buffer.writeBytes(StringUtil.getUtf8Bytes(String.valueOf(arg)));
                } else {
                    buffer.writeBytes(NULL);
                }
                i++;
            } else {
                buffer.writeByte(b);
            }
        }
    }

    /**
     * @return a class name in a byte array with the exact length of {@link CLASS_NAME_LENGTH}
     */
    public static byte[] formatClassName(String name) {
        byte[] rawBytes = name.getBytes(StandardCharsets.US_ASCII);
        if (rawBytes.length == CLASS_NAME_LENGTH) {
            return rawBytes;
        }
        if (rawBytes.length > CLASS_NAME_LENGTH) {
            String[] parts = StringUtils.tokenizeToStringArray(name, ".");
            String className = parts[parts.length - 1];
            int classNameLength = className.length();
            if (classNameLength >= CLASS_NAME_LENGTH) {
                return StringUtil.getBytes(className.substring(0, CLASS_NAME_LENGTH));
            }
            byte[] result = new byte[CLASS_NAME_LENGTH];
            int writeIndex = CLASS_NAME_LENGTH;
            for (int i = parts.length - 1; i >= 0; i--) {
                byte[] part = StringUtil.getBytes(parts[i]);
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
        return StringUtil
                .getBytes(StringUtil.padStartLatin1(name, CLASS_NAME_LENGTH, AsciiCode.SPACE));
    }

}
