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

package im.turms.server.common.infra.cluster.service.codec.io;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.infra.cluster.service.codec.exception.CodecNotFoundException;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.Stream;

/**
 * @author James Chen
 */
public class CodecStream extends Stream implements CodecStreamInput, CodecStreamOutput {

    public CodecStream(ByteBuf buf) {
        super(buf);
    }

    @Override
    public <T> T readObject() {
        int codecId = readShort();
        Codec<T> codec = CodecPool.getCodec(codecId);
        if (codec == null) {
            throw new CodecNotFoundException(
                    "Could not find the codec for the ID: "
                            + codecId);
        }
        return codec.read(this);
    }

    @Override
    public void writeObject(Object obj) {
        Codec<Object> codec = CodecPool.getCodec(obj.getClass());
        if (codec == null) {
            throw new CodecNotFoundException(
                    "Could not find the codec for the class: "
                            + obj.getClass()
                                    .getName());
        }
        codec.write(this, obj);
    }

    @Override
    public <K, V> Map<K, V> readMap() {
        int size = readVarint32();
        if (size == 0) {
            return Collections.emptyMap();
        }
        int keyCodecId = readShort();
        int valueCodecId = readShort();
        Codec<K> keyCodec = CodecPool.getCodec(keyCodecId);
        if (keyCodec == null) {
            throw new CodecNotFoundException(
                    "Could not find the map key codec for the ID: "
                            + keyCodecId);
        }
        Codec<V> valueCodec = CodecPool.getCodec(valueCodecId);
        if (valueCodec == null) {
            throw new CodecNotFoundException(
                    "Could not find the map value codec for the ID: "
                            + valueCodecId);
        }
        Map<K, V> map = CollectionUtil.newMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            map.put(keyCodec.read(this), valueCodec.read(this));
        }
        return map;
    }

    @Override
    public <K, V> void writeMap(@Nullable Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            writeShort(0);
            return;
        }
        writeVarint32(map.size());
        Iterator<Map.Entry<K, V>> iterator = map.entrySet()
                .iterator();
        Map.Entry<K, V> entry = iterator.next();
        Codec<K> keyCodec = CodecPool.getCodec(entry.getKey()
                .getClass());
        if (keyCodec == null) {
            throw new CodecNotFoundException(
                    "Could not find the map key codec for the class: "
                            + entry.getKey()
                                    .getClass()
                                    .getName());
        }
        Codec<V> valueCodec = CodecPool.getCodec(entry.getValue()
                .getClass());
        if (valueCodec == null) {
            throw new CodecNotFoundException(
                    "Could not find the map value codec for the class: "
                            + entry.getValue()
                                    .getClass()
                                    .getName());
        }
        writeObject(entry.getKey());
        writeObject(entry.getValue());
        while (iterator.hasNext()) {
            entry = iterator.next();
            keyCodec.write(this, entry.getKey());
            valueCodec.write(this, entry.getValue());
        }
    }

}
