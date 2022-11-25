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

import java.util.Collection;
import java.util.Map;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
public interface StreamOutput {

    public Stream writeByte(int value);

    public Stream writeBytes(byte[] bytes);

    public Stream writeShort(int value);

    public Stream writeInt(int value);

    public Stream writeInts(Collection<Integer> integers);

    public Stream writeLong(long value);

    public Stream writeLongs(Collection<Long> longs);

    public Stream writeFloat(float value);

    public Stream writeDouble(double value);

    public Stream writeChar(int value);

    public Stream writeBoolean(boolean value);

    public Stream writeString(@Nullable String str);

    public Stream writeStrings(Collection<String> strings);

    public Stream writeStringMap(@Nullable Map<String, String> map);

    public Stream writeVarint32(int value);

    public Stream writeIp(@Nullable byte[] ipBytes);

}