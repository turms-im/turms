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

package im.turms.server.common.infra.io;

import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public interface StreamInput {

    byte readByte();

    byte[] readBytes(int length);

    short readShort();

    int readInt();

    int[] readSparseInts();

    int[] readInts();

    List<Integer> readIntList();

    @Nullable
    List<Integer> readNullableIntList();

    int[][] readSparseInt2DArray();

    long readLong();

    @Nullable
    List<Long> readNullableLongList();

    Set<Long> readLongSet();

    @Nullable
    Set<Long> readNullableLongSet();

    float readFloat();

    double readDouble();

    char readChar();

    char[] readChars();

    boolean readBoolean();

    String readString();

    @Nullable
    String readNullableString();

    List<String> readStringList();

    @Nullable
    List<String> readNullableStringList();

    Map<String, String> readStringMap();

    @Nullable
    Map<String, String> readNullableStringMap();

    int readVarint32();

    @Nullable
    byte[] readNullableIp();

    int readableBytes();

    boolean isReadable();

    ByteBuf readSlice(int length);

    ByteBuf readRetainedSlice(int length);

}