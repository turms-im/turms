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

package im.turms.plugin.push.core.smtp.codec;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import com.sanctionco.jmail.JMail;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.eclipse.collections.impl.map.mutable.primitive.ByteObjectHashMap;

import im.turms.plugin.push.core.smtp.model.MailAddress;
import im.turms.plugin.push.core.smtp.model.MailMessage;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.CharUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.serialization.SerializationException;

/**
 * @author James Chen
 */
public final class MailEncoder {

    private MailEncoder() {
    }

    /**
     * line limit (78) - CRLF (2) - Space (1) = 76
     * https://www.rfc-editor.org/rfc/rfc5322#section-2.1.1
     */
    private static final int SOFT_LINE_LIMIT_EXCLUDING_CRLF_WHITESPACE = 78 - 3;

    /**
     * https://www.rfc-editor.org/rfc/rfc5322#section-2.1.1
     */
    private static final int HARD_LINE_LIMIT = 1000;
    private static final int HARD_LINE_LIMIT_EXCLUDING_CRLF = HARD_LINE_LIMIT - 2;

    /**
     * charset: "UTF-8"; encoding: "Q" => "QuotedPrintable"
     */
    private static final byte[] QP_PREFIX = StringUtil.getBytes("=?UTF-8?Q?");
    private static final int QP_PREFIX_LENGTH = 10;

    private static final byte[] QP_SUFFIX = StringUtil.getBytes("?=");
    private static final int QP_SUFFIX_LENGTH = 2;

    private static final byte[] SUFFIX_CRLF_PREFIX = StringUtil.getBytes("?=\r\n =?UTF-8?Q?");

    private static final byte[] CRLF_SPACE = StringUtil.getBytes("\r\n ");
    private static final byte[] ESCAPED_DOT = StringUtil.getBytes("\r\n..\r\n");
    private static final byte[] END = StringUtil.getBytes("\r\n.\r\n");

    private static final byte[] CRLF = {'\r', '\n'};
    private static final byte ESCAPE_CHAR = '=';

    private static final byte[] HEADER_MIME_VERSION = StringUtil.getBytes("MIME-Version: \"1.0\"");
    private static final byte[] HEADER_FROM = StringUtil.getBytes("From: ");
    private static final byte[] HEADER_TO = StringUtil.getBytes("To: ");
    private static final byte[] HEADER_SUBJECT = StringUtil.getBytes("Subject: ");

    private static final byte[] HEADER_CONTENT_TYPE_UTF8 =
            StringUtil.getBytes("Content-Type: text/plain; charset=utf-8");
    private static final byte[] HEADER_CONTENT_TRANSFER_ENCODING_8BIT =
            StringUtil.getBytes("Content-Transfer-Encoding: 8bit");

    private static final ByteObjectHashMap<byte[]> CHAR_ENCODING = new ByteObjectHashMap<>();

    static {
        byte b;
        // 9 for '\t', 32 for ' '
        for (b = Byte.MIN_VALUE; b < 33; b++) {
            CHAR_ENCODING.put(b, encodeChar(b));
        }
        for (b = 33; b <= 60; b++) {
            CHAR_ENCODING.put(b, new byte[]{b});
        }
        for (b = 62; b <= 126; b++) {
            CHAR_ENCODING.put(b, new byte[]{b});
        }
        // 61
        CHAR_ENCODING.put((byte) '=', encodeChar('='));
        // 63
        CHAR_ENCODING.put((byte) '?', encodeChar('?'));
        // 95
        CHAR_ENCODING.put((byte) '_', encodeChar('_'));
        CHAR_ENCODING.put((byte) 127, encodeChar(127));
    }

    public static ByteBuf encode(MailMessage mail, long maxAllowedSize) {
        MailAddress fromAddress = mail.fromAddress();
        if (JMail.isInvalid(fromAddress.address())) {
            throw new IllegalArgumentException("The from address is invalid");
        }
        List<MailAddress> toAddresses = mail.toAddresses();
        if (CollectionUtil.isEmpty(toAddresses)) {
            throw new IllegalArgumentException("The to addresses must not be empty");
        }
        String subject = mail.subject();
        if (subject == null) {
            throw new IllegalArgumentException("The subject must not be null");
        }
        String body = mail.body();
        if (body == null) {
            throw new IllegalArgumentException("The body must not be null");
        }
        byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
        int bodyLength = bodyBytes.length;
        maxAllowedSize -= CRLF.length * (bodyLength % HARD_LINE_LIMIT);
        if (bodyLength > maxAllowedSize) {
            throw new IllegalArgumentException(
                    "The message size must be less than: "
                            + maxAllowedSize
                            + ", but got: "
                            + bodyLength);
        }
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        try {
            // 1. mime version
            buffer.writeBytes(HEADER_MIME_VERSION)
                    .writeBytes(CRLF)
                    // 2. from
                    .writeBytes(HEADER_FROM);
            encodeAddress(buffer, fromAddress, HEADER_FROM.length);
            buffer.writeBytes(CRLF)
                    // 3. to
                    .writeBytes(HEADER_TO);
            encodeAddresses(buffer, toAddresses, HEADER_TO.length);
            buffer.writeBytes(CRLF)
                    // 4. subject
                    .writeBytes(HEADER_SUBJECT);
            encodeQuotedPrintableHeader(buffer, subject, HEADER_SUBJECT.length);
            buffer.writeBytes(CRLF);
            // 5. content type
            buffer.writeBytes(HEADER_CONTENT_TYPE_UTF8)
                    .writeBytes(CRLF)
                    // 6. content transfer encoding
                    .writeBytes(HEADER_CONTENT_TRANSFER_ENCODING_8BIT)
                    .writeBytes(CRLF)
                    .writeBytes(CRLF);
            // 7. body
            encode8BitMime(buffer, bodyBytes);
        } catch (Exception e) {
            ReferenceCountUtil.ensureReleased(buffer);
            throw new SerializationException("Failed to encode", e);
        }
        return buffer;
    }

    private static void encode8BitMime(ByteBuf buffer, byte[] bytes) {
        int currentPos = 0;
        int length = bytes.length;
        byte b;
        for (int i = 0; i < length; i++) {
            b = bytes[i];
            char c = (char) b;
            if (c == '\r') {
                if ((i + 4 < length)
                        && bytes[i + 1] == '\n'
                        && bytes[i + 2] == '.'
                        && bytes[i + 3] == '\r'
                        && bytes[i + 4] == '\n') {
                    buffer.writeBytes(ESCAPED_DOT);
                    currentPos = 0;
                    i += 4;
                } else {
                    buffer.writeByte(c);
                    currentPos = 0;
                }
                continue;
            } else if (c == '\n') {
                buffer.writeByte(c);
                currentPos = 0;
                continue;
            }
            if (currentPos == HARD_LINE_LIMIT_EXCLUDING_CRLF) {
                buffer.writeBytes(CRLF);
                currentPos = 0;
            } else {
                currentPos++;
            }
            buffer.writeByte(b);
        }
        buffer.writeBytes(END);
    }

    /**
     * TODO: handle especials
     * <p>
     * https://www.rfc-editor.org/rfc/rfc2047
     *
     * @param posInLine starts from 0
     * @return position
     */
    private static int encodeQuotedPrintableHeader(ByteBuf buffer, String text, int posInLine) {
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(QP_PREFIX);
        int pos = QP_PREFIX_LENGTH + posInLine;
        for (byte b : textBytes) {
            int c = b & 0xFF;
            if (c == '\r' || c == '\n') {
                continue;
            }
            byte[] bytes = CHAR_ENCODING.get(b);
            pos += bytes.length;
            // -1 for "="
            if (pos <= SOFT_LINE_LIMIT_EXCLUDING_CRLF_WHITESPACE - QP_SUFFIX_LENGTH - 1) {
                buffer.writeByte(ESCAPE_CHAR)
                        .writeBytes(bytes);
            } else {
                buffer.writeBytes(SUFFIX_CRLF_PREFIX)
                        .writeByte(ESCAPE_CHAR)
                        .writeBytes(bytes);
                // 11 for " =?UTF-8?Q?"
                pos = 11 + 3;
            }
        }
        buffer.writeBytes(QP_SUFFIX);
        return pos;
    }

    private static boolean shouldEncodeQuotedPrintable(char ch) {
        return ch >= 128 || ch < 10 || (ch >= 11 && ch < 32) || ch == '=';
    }

    /**
     * https://www.rfc-editor.org/rfc/rfc5322#section-3.4
     * https://www.rfc-editor.org/rfc/rfc5322#section-3.6.2
     */
    public static void encodeAddress(ByteBuf buffer, MailAddress address, int posInLine) {
        String name = address.name();
        String addr = address.address();
        if (name == null) {
            buffer.writeBytes(StringUtil.getBytes(addr));
            return;
        }
        if (shouldEncodeQuotedPrintableOrWrap(name, 0)) {
            buffer.writeBytes(StringUtil.getBytes(addr))
                    .writeBytes(StringUtil.getBytes(" ("));
            encodeQuotedPrintableHeader(buffer, name, posInLine + addr.length() + 2);
            buffer.writeByte(')');
            return;
        }
        buffer.writeBytes(StringUtil.getBytes(addr))
                .writeBytes(StringUtil.getBytes(" ("))
                .writeBytes(StringUtil.getBytes(name))
                .writeByte(')');
    }

    /**
     * https://www.rfc-editor.org/rfc/rfc5322#section-3.4
     * https://www.rfc-editor.org/rfc/rfc5322#section-3.6.2
     */
    private static void encodeAddresses(
            ByteBuf buffer,
            Collection<MailAddress> addresses,
            int posInLine) {
        boolean isFirstAddress = true;
        for (MailAddress addr : addresses) {
            if (isFirstAddress) {
                isFirstAddress = false;
            } else {
                buffer.writeByte(',');
                posInLine++;
            }
            String address = addr.address();
            String name = addr.name();
            int addressLength = address.length();
            if (posInLine + addressLength >= SOFT_LINE_LIMIT_EXCLUDING_CRLF_WHITESPACE) {
                buffer.writeBytes(CRLF_SPACE);
                posInLine = 1;
            }
            buffer.writeBytes(StringUtil.getBytes(address));
            posInLine += addressLength;
            if (name == null || name.isEmpty()) {
                continue;
            }
            if (shouldEncodeQuotedPrintableOrWrap(name, posInLine)) {
                boolean hadSpace = false;
                if (posInLine + 12 >= 71) {
                    buffer.writeBytes(CRLF_SPACE);
                    posInLine = 1;
                    hadSpace = true;
                }
                if (!hadSpace) {
                    buffer.writeByte(' ');
                    posInLine++;
                }
                buffer.writeByte('(');
                posInLine++;
                posInLine = encodeQuotedPrintableHeader(buffer, name, posInLine);
                buffer.writeByte(')');
                posInLine++;
            } else {
                boolean hadSpace = false;
                if (posInLine + name.length() + 3 >= SOFT_LINE_LIMIT_EXCLUDING_CRLF_WHITESPACE) {
                    buffer.writeBytes(CRLF_SPACE);
                    posInLine = 1;
                    hadSpace = true;
                }
                if (!hadSpace) {
                    buffer.writeByte(' ');
                    posInLine++;
                }
                buffer.writeByte('(');
                posInLine++;
                buffer.writeBytes(StringUtil.getBytes(name));
                buffer.writeByte(')');
                posInLine += addressLength + 3;
            }
        }
    }

    private static boolean shouldEncodeQuotedPrintableOrWrap(String s, int posInLine) {
        int lineLength = posInLine;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c != '=' && c != '\t' && shouldEncodeQuotedPrintable(c)) {
                return true;
            }
            if (c == '\r' || c == '\n') {
                lineLength = 0;
                continue;
            }
            if (++lineLength > HARD_LINE_LIMIT_EXCLUDING_CRLF) {
                return true;
            }
        }
        return false;
    }

    private static byte[] encodeChar(int i) {
        char hex1 = CharUtil.toUppercaseHex((i >> 4) & 0xF);
        char hex2 = CharUtil.toUppercaseHex(i & 0xF);
        return new byte[]{ESCAPE_CHAR, (byte) hex1, (byte) hex2};
    }

}