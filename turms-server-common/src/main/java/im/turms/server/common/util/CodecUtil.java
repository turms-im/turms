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

package im.turms.server.common.util;

import com.google.common.collect.Maps;
import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecPool;
import io.netty.buffer.ByteBuf;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public final class CodecUtil {

    private CodecUtil() {
    }

    // Common

    public static void writeMap(ByteBuf output, Map map) {
        int size = map.size();
        output.writeShort(size);
        final Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            writeObject(output, key);
            writeObject(output, value);
        }
    }

    public static Map readMaps(ByteBuf input) {
        final int size = input.readShort();
        Map map = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(readObject(input), readObject(input));
        }
        return map;
    }

    public static void writeObject(ByteBuf output, Object obj) {
        Codec<Object> codec = CodecPool.getCodec(obj.getClass());
        codec.write(output, obj);
    }

    public static <T> T readObject(ByteBuf input) {
        int codecId = input.readShort();
        Codec<T> codec = CodecPool.getCodec(codecId);
        return codec.read(input);
    }

    public static void writeString(ByteBuf output, @Nullable String str) {
        if (str == null) {
            output.writeShort(0);
        } else {
            byte[] bytes = StringUtil.getBytes(str);
            int length = bytes.length;
            output.writeShort(length);
            if (length > 0) {
                output.writeBytes(bytes)
                        .writeByte(StringUtil.getCoder(str));
            }
        }
    }

    public static String readString(ByteBuf input) {
        int length = input.readShort();
        if (length == 0) {
            return null;
        }
        byte[] bytes = new byte[length];
        input.readBytes(bytes);
        return StringUtil.newString(bytes, input.readByte());
    }

    public static void writeStrings(ByteBuf output, Collection<String> strings) {
        int size = strings.size();
        output.writeShort(size);
        for (String string : strings) {
            writeString(output, string);
        }
    }

    public static List<String> readStringList(ByteBuf input) {
        int length = input.readShort();
        List<String> strings = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            strings.add(readString(input));
        }
        return strings;
    }

    public static void writeInts(ByteBuf output, Collection<Integer> integers) {
        int size = integers.size();
        output.writeInt(size);
        for (Integer integer : integers) {
            output.writeInt(integer);
        }
    }

    public static List<Integer> readIntList(ByteBuf input) {
        int length = input.readInt();
        List<Integer> integers = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            integers.add(input.readInt());
        }
        return integers;
    }

    // Varint / ZigZag

    public static int computeVarint32Size(int value) {
        if ((value & (~0 << 7)) == 0) {
            return 1;
        }
        if ((value & (~0 << 14)) == 0) {
            return 2;
        }
        if ((value & (~0 << 21)) == 0) {
            return 3;
        }
        if ((value & (~0 << 28)) == 0) {
            return 4;
        }
        return 5;
    }

    public static void writeVarint32(ByteBuf out, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                out.writeByte(value);
                return;
            } else {
                out.writeByte((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static int readVarint32(ByteBuf in) {
        byte tmp = in.readByte();
        if (0 <= tmp) {
            return tmp;
        }
        int out = tmp & 0x7F;
        if ((tmp = in.readByte()) >= 0) {
            out |= tmp << 7;
        } else {
            out |= (tmp & 0x7F) << 7;
            if ((tmp = in.readByte()) >= 0) {
                out |= tmp << 14;
            } else {
                out |= (tmp & 0x7F) << 14;
                if ((tmp = in.readByte()) >= 0) {
                    out |= tmp << 21;
                } else {
                    out |= (tmp & 0x7F) << 21;
                    out |= (tmp = in.readByte()) << 28;
                    if (tmp < 0) {
                        // Discard upper 32 bits.
                        for (int i = 0; i < 5; i++) {
                            if (0 <= in.readByte()) {
                                return out;
                            }
                        }
                        throw new IllegalArgumentException("Failed to read varint32");
                    }
                }
            }
        }
        return out;
    }

}
