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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author James Chen
 */
public class Utf8String implements CharSequence {

    /**
     * @see java.nio.charset.CharsetEncoder#maxBytesPerChar()
     */
    private static final int MAX_BYTES_PER_UTF16_CHAR = 3;

    private static final int HASH_MULTIPLIER = 0x01000193;

    private final String str;
    private final byte[] bytes;
    private final int byteOffset;
    private final int byteCount;
    private final int charCount;
    private int hashcode;

    public Utf8String(String str, byte[] bytes, int byteOffset, int byteCount, int charCount) {
        if (byteOffset < 0) {
            throw new IndexOutOfBoundsException(
                    "The byte offset must be greater than or equal to 0");
        }
        if (byteCount < 0) {
            throw new IndexOutOfBoundsException(
                    "The byte count must be greater than or equal to 0");
        }
        if ((byteOffset + byteCount) > bytes.length) {
            throw new IndexOutOfBoundsException(
                    "byteOffset + byteCount must be less than or equal to the length of the byte array");
        }
        this.str = str;
        this.bytes = bytes;
        this.byteOffset = byteOffset;
        this.byteCount = byteCount;
        this.charCount = charCount;
        this.hashcode = -1;
    }

    public static Utf8String of(String string) {
        boolean isLatin1 = StringUtil.isLatin1(string);
        // Fast path for Latin-1
        byte[] bytes = StringUtil.getBytes(string);
        if (isLatin1) {
            int byteLength = bytes.length;
            return new Utf8String(string, bytes, 0, byteLength, byteLength);
        }
        // Slow path for UTF16
        int utf16CharLength = string.length();
        int maxBytesLength = utf16CharLength * MAX_BYTES_PER_UTF16_CHAR;
        byte[] out = new byte[maxBytesLength];
        int i = 0;
        char c;
        int charCount = 0;
        for (; i < utf16CharLength && (c = string.charAt(i)) < 0x80; i++) {
            out[i] = (byte) c;
            charCount++;
        }
        if (i == utf16CharLength) {
            return new Utf8String(string, out, 0, utf16CharLength, utf16CharLength);
        }
        int byteCount = i;

        for (; i < utf16CharLength; i++) {
            c = string.charAt(i);
            if (c < 0x80 && byteCount < maxBytesLength) {
                out[byteCount++] = (byte) c;
            } else if (c < 0x800 && byteCount <= maxBytesLength - 2) {
                // 11 bits, two UTF-8 bytes
                out[byteCount++] = (byte) ((0xF << 6) | (c >>> 6));
                out[byteCount++] = (byte) (0x80 | (0x3F & c));
            } else if ((c < Character.MIN_SURROGATE || Character.MAX_SURROGATE < c)
                    && byteCount <= maxBytesLength - 3) {
                // Maximum single-char code point is 0xFFFF, 16 bits, three UTF-8 bytes
                out[byteCount++] = (byte) ((0xF << 5) | (c >>> 12));
                out[byteCount++] = (byte) (0x80 | (0x3F & (c >>> 6)));
                out[byteCount++] = (byte) (0x80 | (0x3F & c));
            } else if (byteCount <= maxBytesLength - 4) {
                // Minimum code point represented by a surrogate pair is 0x10000, 17 bits,
                // four UTF-8 bytes
                char low;
                if (i + 1 == utf16CharLength
                        || !Character.isSurrogatePair(c, (low = string.charAt(++i)))) {
                    throw new IllegalArgumentException(
                            "Unpaired surrogate at the index "
                                    + (i - 1)
                                    + " of "
                                    + utf16CharLength);
                }
                int codePoint = Character.toCodePoint(c, low);
                out[byteCount++] = (byte) ((0xF << 4) | (codePoint >>> 18));
                out[byteCount++] = (byte) (0x80 | (0x3F & (codePoint >>> 12)));
                out[byteCount++] = (byte) (0x80 | (0x3F & (codePoint >>> 6)));
                out[byteCount++] = (byte) (0x80 | (0x3F & codePoint));
            } else {
                if ((Character.MIN_SURROGATE <= c && c <= Character.MAX_SURROGATE)
                        && (i + 1 == utf16CharLength
                                || !Character.isSurrogatePair(c, string.charAt(i + 1)))) {
                    throw new IllegalArgumentException(
                            "Unpaired surrogate at the index "
                                    + i
                                    + " of "
                                    + utf16CharLength);
                }
                throw new ArrayIndexOutOfBoundsException(
                        "Failed to write \""
                                + c
                                + "\" at the index "
                                + byteCount);
            }
            charCount++;
        }
        return new Utf8String(string, out, 0, byteCount, charCount);
    }

    @Override
    public int length() {
        return charCount;
    }

    @Override
    public boolean isEmpty() {
        return byteCount == 0;
    }

    @Override
    public int hashCode() {
        if (hashcode < 0) {
            hashcode = hashCode(HASH_MULTIPLIER, bytes, byteOffset, byteCount);
        }
        return hashcode;
    }

    private int hashCode(int seed, byte[] bytes, int offset, int count) {
        for (int i = offset, limit = offset + count; i < limit; i++) {
            seed = (seed * HASH_MULTIPLIER) ^ (bytes[i] & 0xFF);
        }
        return seed & 0x7FFFFFFF;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Utf8String b = (Utf8String) obj;
        if (this == b) {
            return true;
        }
        int count = this.byteCount;
        if (count != b.byteCount) {
            return false;
        }
        byte[] aBytes = this.bytes;
        byte[] bBytes = b.bytes;
        int aOffset = this.byteOffset;
        int bOffset = b.byteOffset;
        for (int i = 0; i < count; i++) {
            if (aBytes[aOffset + i] != bBytes[bOffset + i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public char charAt(int utf8CharIndex) {
        return (char) codepointAt(utf8CharIndex);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return substring(start, end);
    }

    public Utf8String concat(Utf8String s) {
        String newOriginalString = str + s.str;
        int totalByteCount = byteCount + s.byteCount;
        int totalCharCount = charCount + s.charCount;
        byte[] newBytes = new byte[totalByteCount];
        System.arraycopy(bytes, byteOffset, newBytes, 0, byteCount);
        System.arraycopy(s.bytes, s.byteOffset, newBytes, byteCount, s.byteCount);
        return new Utf8String(newOriginalString, newBytes, 0, totalByteCount, totalCharCount);
    }

    public Utf8String substring(int utf8CharBeginIndex, int utf8CharEndIndex) {
        if (utf8CharBeginIndex < 0) {
            throw new IndexOutOfBoundsException(
                    "The begin index must be greater than or equal to 0");
        }
        if (utf8CharEndIndex > charCount) {
            throw new IndexOutOfBoundsException(
                    "The end index must be less than "
                            + charCount);
        }
        if (utf8CharBeginIndex > utf8CharEndIndex) {
            throw new IndexOutOfBoundsException(
                    "The begin index must be less than or equal to the end index");
        }
        int beginFirstByteIndex = byteOffset;
        int endByteIndex = byteOffset + byteCount;
        int seenCharacters = 0;
        byte[] localBytes = bytes;
        byte firstByte;
        while (seenCharacters < utf8CharBeginIndex && beginFirstByteIndex < endByteIndex) {
            firstByte = localBytes[beginFirstByteIndex];
            int count = getByteCountFromFirstUtf8Byte(firstByte);
            if (count > 0) {
                beginFirstByteIndex += count;
                seenCharacters++;
            }
        }

        int endFirstByteIndex = beginFirstByteIndex;
        while (seenCharacters < utf8CharEndIndex && endFirstByteIndex < endByteIndex) {
            firstByte = localBytes[endFirstByteIndex];
            int count = getByteCountFromFirstUtf8Byte(firstByte);
            if (count > 0) {
                endFirstByteIndex += count;
                seenCharacters++;
            }
        }

        int newByteCount = endFirstByteIndex - beginFirstByteIndex;
        String newStr =
                new String(localBytes, beginFirstByteIndex, newByteCount, StandardCharsets.UTF_8);
        return new Utf8String(
                newStr,
                localBytes,
                beginFirstByteIndex,
                newByteCount,
                utf8CharEndIndex - utf8CharBeginIndex);
    }

    public Utf8String substring(int utf8CharBeginIndex) {
        return substring(utf8CharBeginIndex, charCount);
    }

    public byte[] getBytes() {
        if (byteOffset != 0 || bytes.length != byteCount) {
            return Arrays.copyOfRange(bytes, byteOffset, byteOffset + byteCount);
        }
        return bytes;
    }

    /**
     * @return 0 if it is not the first byte of a UTF character
     */
    private int getByteCountFromFirstUtf8Byte(byte firstByte) {
        if ((firstByte & 0b10000000) == 0) {
            return 1;
        } else if ((firstByte & 0b11100000) == 0b11000000) {
            return 2;
        } else if ((firstByte & 0b11110000) == 0b11100000) {
            return 3;
        } else if ((firstByte & 0b11111000) == 0b11110000) {
            return 4;
        }
        return 0;
    }

    /**
     * @return the start byte index of the UTF-8 char
     */
    public int byteIndexOf(int utf8CharIndex) {
        if (utf8CharIndex < 0) {
            throw new IndexOutOfBoundsException("The index must be greater than or equal to 0");
        }
        if (utf8CharIndex >= charCount) {
            throw new IndexOutOfBoundsException(
                    "The index must be less than "
                            + charCount);
        }
        int firstByteIndex = byteOffset;
        int endByteIndex = byteOffset + byteCount;
        int seenCharacters = 0;
        byte[] localBytes = bytes;
        byte firstByte;
        while (seenCharacters < utf8CharIndex && firstByteIndex < endByteIndex) {
            firstByte = localBytes[firstByteIndex];
            int count = getByteCountFromFirstUtf8Byte(firstByte);
            if (count > 0) {
                firstByteIndex += count;
                seenCharacters++;
            }
        }
        return firstByteIndex;
    }

    public int codepointAt(int utf8CharIndex) {
        if (utf8CharIndex < 0) {
            throw new IndexOutOfBoundsException("The index must be greater than or equal to 0");
        }
        if (utf8CharIndex >= charCount) {
            throw new IndexOutOfBoundsException(
                    "The index must be less than "
                            + charCount);
        }

        int firstByteIndex = byteOffset;
        int endByteIndex = byteOffset + byteCount;
        int seenCharacters = 0;
        byte[] localBytes = bytes;
        byte firstByte;
        while (seenCharacters < utf8CharIndex && firstByteIndex < endByteIndex) {
            firstByte = localBytes[firstByteIndex];
            int count = getByteCountFromFirstUtf8Byte(firstByte);
            if (count > 0) {
                firstByteIndex += count;
                seenCharacters++;
            }
        }
        firstByte = localBytes[firstByteIndex];
        if ((firstByte & 0x80) == 0) {
            // ASCII character
            return firstByte;
        } else if ((firstByte & 0xE0) == 0xC0) {
            // Two-byte UTF-8 character
            return ((firstByte & 0x1F) << 6) | (localBytes[firstByteIndex + 1] & 0x3F);
        } else if ((firstByte & 0xF0) == 0xE0) {
            // Three-byte UTF-8 character
            return ((firstByte & 0x0F) << 12) | ((localBytes[firstByteIndex + 1] & 0x3F) << 6)
                    | (localBytes[firstByteIndex + 2] & 0x3F);
        } else if ((firstByte & 0xF8) == 0xF0) {
            // Four-byte UTF-8 character
            return ((firstByte & 0x07) << 18) | ((localBytes[firstByteIndex + 1] & 0x3F) << 12)
                    | ((localBytes[firstByteIndex + 2] & 0x3F) << 6)
                    | (localBytes[firstByteIndex + 3] & 0x3F);
        } else {
            throw new IllegalStateException("Invalid UTF-8 byte sequence");
        }
    }

}