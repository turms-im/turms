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

    Stream writeByte(int value);

    Stream writeBytes(byte[] bytes);

    Stream writeShort(int value);

    Stream writeInt(int value);

    Stream writeInts(Collection<Integer> integers);

    Stream writeNullableInts(@Nullable Collection<Integer> integers);

    Stream writeLong(long value);

    Stream writeLongs(Collection<Long> longs);

    Stream writeNullableLongs(@Nullable Collection<Long> longs);

    Stream writeFloat(float value);

    Stream writeDouble(double value);

    Stream writeChar(int value);

    Stream writeBoolean(boolean value);

    Stream writeString(String str);

    Stream writeNullableString(@Nullable String str);

    Stream writeStrings(Collection<String> strings);

    Stream writeNullableStrings(@Nullable Collection<String> strings);

    Stream writeStringMap(Map<String, String> map);

    Stream writeNullableStringMap(@Nullable Map<String, String> map);

    Stream writeVarint32(int value);

    Stream writeNullableIp(@Nullable byte[] ipBytes);
}