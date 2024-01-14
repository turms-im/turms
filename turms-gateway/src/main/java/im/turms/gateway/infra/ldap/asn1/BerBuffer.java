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

package im.turms.gateway.infra.ldap.asn1;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.ReferenceCounted;

import im.turms.server.common.infra.codec.DecodeException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author James Chen
 */
public class BerBuffer implements Closeable, ReferenceCounted {

    private int[] sequenceLengthWriterIndexes;
    private int currentSequenceLengthIndex;

    private final ByteBuf buffer;

    public BerBuffer(ByteBuf buffer) {
        this.buffer = buffer;
        sequenceLengthWriterIndexes = new int[8];
    }

    public BerBuffer() {
        buffer = PooledByteBufAllocator.DEFAULT.buffer();
        sequenceLengthWriterIndexes = new int[8];
    }

    public void skipTag() {
        buffer.skipBytes(1);
    }

    public void skipTagAndLength() {
        buffer.skipBytes(1);
        readLength();
    }

    public void skipTagAndLengthAndValue() {
        buffer.skipBytes(1);
        int length = readLength();
        buffer.skipBytes(length);
    }

    public byte readTag() {
        return buffer.readByte();
    }

    public boolean peekAndCheckTag(int tag) {
        return buffer.isReadable() && buffer.getByte(buffer.readerIndex()) == tag;
    }

    public void skipLength() {
        readLength();
    }

    public void skipLengthAndValue() {
        buffer.skipBytes(readLength());
    }

    public BerBuffer writeLength(int length) {
        if (length <= 0x7F) {
            buffer.writeByte(length);
        } else if (length <= 0xFF) {
            buffer.writeByte(0x81);
            buffer.writeByte(length);
        } else if (length <= 0xFFFF) {
            buffer.writeByte(0x82);
            buffer.writeByte((byte) (length >> 8));
            buffer.writeByte((byte) length);
        } else if (length <= 0xFF_FFFF) {
            buffer.writeByte(0x83);
            buffer.writeByte((byte) (length >> 16));
            buffer.writeByte((byte) (length >> 8));
            buffer.writeByte((byte) length);
        } else {
            buffer.writeByte(0x84);
            buffer.writeByte((byte) (length >> 24));
            buffer.writeByte((byte) (length >> 16));
            buffer.writeByte((byte) (length >> 8));
            buffer.writeByte((byte) length);
        }
        return this;
    }

    public int readLength() {
        int lengthBytes = buffer.readByte();
        if ((lengthBytes & 0x80) != 0x80) {
            return lengthBytes;
        }
        lengthBytes &= 0x7f;
        if (lengthBytes == 0) {
            throw new DecodeException("Indefinite length is not supported");
        }
        if (lengthBytes > 4) {
            throw new DecodeException(
                    "The length ("
                            + +lengthBytes
                            + ") is too long");
        }
        if (!buffer.isReadable(lengthBytes)) {
            throw new DecodeException("Insufficient data");
        }
        int length = 0;
        for (int i = 0; i < lengthBytes; i++) {
            length = (length << 8) + (buffer.readByte() & 0xFF);
        }
        if (length < 0) {
            throw new DecodeException("Invalid length bytes");
        }
        return length;
    }

    public int tryReadLengthIfReadable() {
        if (!buffer.isReadable()) {
            return -1;
        }
        int lengthBytes = buffer.readByte();
        if ((lengthBytes & 0x80) != 0x80) {
            return lengthBytes;
        }
        lengthBytes &= 0x7f;
        if (lengthBytes == 0) {
            throw new DecodeException("Indefinite length is not supported");
        }
        if (lengthBytes > 4) {
            throw new DecodeException(
                    "The length ("
                            + +lengthBytes
                            + ") is too long");
        }
        if (!buffer.isReadable(lengthBytes)) {
            return -1;
        }
        int length = 0;
        for (int i = 0; i < lengthBytes; i++) {
            length = (length << 8) + (buffer.readByte() & 0xFF);
        }
        if (length < 0) {
            throw new DecodeException("Invalid length bytes");
        }
        return length;
    }

    public BerBuffer beginSequence() {
        return beginSequence(Asn1IdConst.TAG_SEQUENCE | Asn1IdConst.FORM_CONSTRUCTED);
    }

    public BerBuffer beginSequence(int tag) {
        int writerIndexCount = sequenceLengthWriterIndexes.length;
        if (currentSequenceLengthIndex >= writerIndexCount) {
            int[] newSequenceLengthWriterIndexes = new int[writerIndexCount * 2];
            System.arraycopy(sequenceLengthWriterIndexes,
                    0,
                    newSequenceLengthWriterIndexes,
                    0,
                    writerIndexCount);
            sequenceLengthWriterIndexes = newSequenceLengthWriterIndexes;
        }

        buffer.writeByte(tag);
        int writerIndex = buffer.writerIndex();
        sequenceLengthWriterIndexes[currentSequenceLengthIndex] = writerIndex;
        // 3 = 1 (for the byte length of value length) + 2 (for the value length up to 64k)
        buffer.writerIndex(writerIndex + 3);

        currentSequenceLengthIndex++;
        return this;
    }

    public BerBuffer endSequence() {
        if (--currentSequenceLengthIndex < 0) {
            throw new IllegalStateException("Unbalanced sequences");
        }

        buffer.markWriterIndex();
        int lengthWriterIndex = sequenceLengthWriterIndexes[currentSequenceLengthIndex];
        // 3 = 1 (for the byte length of value length) + 2 (for the value length up to 64k)
        int valueWriterIndexStart = lengthWriterIndex + 3;
        int valueLength = buffer.writerIndex() - valueWriterIndexStart;

        if (valueLength <= 0xFFFF) {
            buffer.writerIndex(lengthWriterIndex);

            buffer.writeByte(0x82);
            buffer.writeByte((byte) (valueLength >> 8));
            buffer.writeByte((byte) valueLength);

            buffer.resetWriterIndex();
        } else {
            throw new DecodeException(
                    "Expecting the sequence value length to be less than or equal to 64k, but got "
                            + valueLength);
        }
        return this;
    }

    public BerBuffer writeBoolean(boolean value) {
        buffer.writeByte(Asn1IdConst.TAG_BOOLEAN);
        buffer.writeByte(1);
        buffer.writeByte(value
                ? 0xFF
                : 0);
        return this;
    }

    public BerBuffer writeBoolean(int tag, boolean value) {
        buffer.writeByte(tag);
        buffer.writeByte(1);
        buffer.writeByte(value
                ? 0xFF
                : 0);
        return this;
    }

    public boolean readBoolean() {
        byte actualTag = buffer.readByte();
        if (actualTag != Asn1IdConst.TAG_BOOLEAN) {
            throw new DecodeException(
                    "Expecting tag: "
                            + Asn1IdConst.TAG_BOOLEAN
                            + ", but got: "
                            + (actualTag & 0xff));
        }
        int length = readLength();
        if (length > 1) {
            throw new DecodeException("The boolean is too large");
        } else if (!buffer.isReadable(length)) {
            throw new DecodeException("Insufficient data");
        }
        return buffer.readByte() != 0;
    }

    public BerBuffer writeInteger(int value) {
        return writeInteger(Asn1IdConst.TAG_INTEGER, value);
    }

    public BerBuffer writeInteger(int tag, int value) {
        buffer.writeByte(tag);
        if (value < 0) {
            if ((value & 0xFFFF_FF80) == 0xFFFF_FF80) {
                buffer.writeByte(1);

                buffer.writeByte(value & 0xFF);
            } else if ((value & 0xFFFF_8000) == 0xFFFF_8000) {
                buffer.writeByte(2);

                buffer.writeByte(((value >> 8) & 0xFF));
                buffer.writeByte((value & 0xFF));
            } else if ((value & 0xFF80_0000) == 0xFF80_0000) {
                buffer.writeByte(3);

                buffer.writeByte(value >> 16 & 0xFF);
                buffer.writeByte(value >> 8 & 0xFF);
                buffer.writeByte(value & 0xFF);
            } else {
                buffer.writeByte(4);

                buffer.writeByte(value >> 24 & 0xFF);
                buffer.writeByte(value >> 16 & 0xFF);
                buffer.writeByte(value >> 8 & 0xFF);
                buffer.writeByte(value & 0xFF);
            }
        } else {
            if ((value & 0x0000_007F) == value) {
                buffer.writeByte(1);

                buffer.writeByte(value & 0x7F);
            } else if ((value & 0x0000_7FFF) == value) {
                buffer.writeByte(2);

                buffer.writeByte((value >> 8) & 0x7F);
                buffer.writeByte(value & 0xFF);
            } else if ((value & 0x007F_FFFF) == value) {
                buffer.writeByte(3);

                buffer.writeByte((value >> 16) & 0x7F);
                buffer.writeByte((value >> 8) & 0xFF);
                buffer.writeByte(value & 0xFF);
            } else {
                buffer.writeByte(4);

                buffer.writeByte((value >> 24) & 0x7F);
                buffer.writeByte((value >> 16) & 0xFF);
                buffer.writeByte((value >> 8) & 0xFF);
                buffer.writeByte(value & 0xFF);
            }
        }
        return this;
    }

    public int readInteger() {
        return readIntWithTag(Asn1IdConst.TAG_INTEGER);
    }

    public int readIntWithTag(int tag) {
        byte actualTag = buffer.readByte();
        if (actualTag != tag) {
            throw new DecodeException(
                    "Expecting tag: "
                            + tag
                            + ", but got: "
                            + (actualTag & 0xff));
        }

        int length = readLength();

        if (length > 4) {
            throw new DecodeException("The integer is too long");
        } else if (!buffer.isReadable(length)) {
            throw new DecodeException("Insufficient data");
        }

        byte firstByte = buffer.readByte();

        int value = firstByte & 0x7F;
        for (int i = 1; i < length; i++) {
            value <<= 8;
            value |= (buffer.readByte() & 0xFF);
        }

        if ((firstByte & 0x80) == 0x80) {
            value = -value;
        }

        return value;
    }

    public BerBuffer writeOctetString(String value) {
        return writeOctetString(Asn1IdConst.TAG_OCTET_STRING, value);
    }

    public BerBuffer writeOctetString(byte[] value) {
        buffer.writeByte(Asn1IdConst.TAG_OCTET_STRING);
        writeLength(value.length);
        buffer.writeBytes(value);
        return this;
    }

    public BerBuffer writeOctetString(int tag, byte[] value) {
        buffer.writeByte(tag);
        writeLength(value.length);
        buffer.writeBytes(value);
        return this;
    }

    public BerBuffer writeOctetString(byte[] value, int start, int length) {
        buffer.writeByte(Asn1IdConst.TAG_OCTET_STRING);
        writeLength(length);
        buffer.writeBytes(value, start, length);
        return this;
    }

    public BerBuffer writeOctetString(int tag, byte[] value, int start, int length) {
        buffer.writeByte(tag);
        writeLength(length);
        buffer.writeBytes(value, start, length);
        return this;
    }

    public BerBuffer writeOctetString(int tag, String value) {
        buffer.writeByte(tag);

        int lengthWriterIndex = buffer.writerIndex();
        int valueBeginWriterIndex = lengthWriterIndex + 3;
        buffer.writerIndex(valueBeginWriterIndex);

        ByteBufUtil.writeUtf8(buffer, value);
        buffer.markWriterIndex();

        int valueLength = buffer.writerIndex() - valueBeginWriterIndex;

        if (valueLength <= 0xFFFF) {
            buffer.writerIndex(lengthWriterIndex);

            buffer.writeByte(0x82);
            buffer.writeByte((byte) (valueLength >> 8));
            buffer.writeByte((byte) valueLength);

            buffer.resetWriterIndex();
        } else {
            throw new DecodeException(
                    "Expecting the sequence value length to be less than or equal to 64k, but got "
                            + valueLength);
        }
        return this;
    }

    public BerBuffer writeOctetStrings(List<String> values) {
        for (String value : values) {
            writeOctetString(Asn1IdConst.TAG_OCTET_STRING, value);
        }
        return this;
    }

    public String readOctetString() {
        return readOctetStringWithTag(Asn1IdConst.TAG_OCTET_STRING);
    }

    public String readOctetStringWithTag(int tag) {
        int actualTag = buffer.readByte();
        if (actualTag != tag) {
            throw new DecodeException(
                    "Encountered ASN.1 tag "
                            + Integer.toString((byte) actualTag)
                            + " (expected tag "
                            + tag
                            + ")");
        }

        int length = readLength();

        if (!buffer.isReadable(length)) {
            throw new DecodeException("Insufficient data");
        }

        if (length == 0) {
            return "";
        }
        return buffer.readCharSequence(length, UTF_8)
                .toString();
    }

    public String readOctetStringWithLength(int length) {
        if (length == 0) {
            return "";
        }
        return buffer.readCharSequence(length, UTF_8)
                .toString();
    }

    public BerBuffer writeEnumeration(int value) {
        return writeInteger(Asn1IdConst.TAG_ENUMERATED, value);
    }

    public int readEnumeration() {
        return readIntWithTag(Asn1IdConst.TAG_ENUMERATED);
    }

    public byte[] getBytes() {
        return ByteBufUtil.getBytes(buffer);
    }

    public void skipBytes(int length) {
        buffer.skipBytes(length);
    }

    @Override
    public void close() throws IOException {
        buffer.release();
    }

    @Override
    public int refCnt() {
        return buffer.refCnt();
    }

    @Override
    public ReferenceCounted retain() {
        return buffer.retain();
    }

    @Override
    public ReferenceCounted retain(int increment) {
        return buffer.retain(increment);
    }

    @Override
    public ReferenceCounted touch() {
        return buffer.touch();
    }

    @Override
    public ReferenceCounted touch(Object hint) {
        return buffer.touch(hint);
    }

    @Override
    public boolean release() {
        return buffer.release();
    }

    @Override
    public boolean release(int decrement) {
        return buffer.release(decrement);
    }

    public boolean isReadable(int length) {
        return buffer.isReadable(length);
    }

    public boolean isReadable() {
        return buffer.isReadable();
    }

    public boolean isReadableWithEnd(int end) {
        return buffer.readerIndex() < end;
    }

    public int readerIndex() {
        return buffer.readerIndex();
    }
}