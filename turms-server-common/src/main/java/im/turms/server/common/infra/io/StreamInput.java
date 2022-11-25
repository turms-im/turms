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

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
public interface StreamInput {

    public byte readByte();

    public byte[] readBytes(int length);

    public short readShort();

    public int readInt();

    public List<Integer> readInts();

    public long readLong();

    public List<Long> readLongs();

    public Set<Long> readLongSet();

    public float readFloat();

    public double readDouble();

    public char readChar();

    public boolean readBoolean();

    @Nullable
    public String readString();

    public List<String> readStrings();

    public Map<String, String> readStringMap();

    public int readVarint32();

    @Nullable
    public byte[] readIp();

    public int readableBytes();

    public ByteBuf readSlice(int length);

    public ByteBuf readRetainedSlice(int length);

}