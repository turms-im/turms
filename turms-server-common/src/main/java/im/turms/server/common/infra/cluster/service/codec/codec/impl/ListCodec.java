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

package im.turms.server.common.infra.cluster.service.codec.codec.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.io.Stream;

/**
 * @author James Chen
 */
public class ListCodec implements Codec<List<?>> {

    @Override
    public CodecId getCodecId() {
        return CodecId.COLLECTION_LIST;
    }

    @Override
    public List<Class<?>> getEncodableClasses() {
        return List.of(ArrayList.class, ChunkedArrayList.class, List.class, LinkedList.class);
    }

    @Override
    public List<Class<?>> getEncodableSuperClasses() {
        return List.of(List.class);
    }

    @Override
    public void write(CodecStreamOutput output, List<?> data) {
        int size = data.size();
        output.writeVarint32(size);
        if (size == 0) {
            return;
        }
        Class<?> elementClass = data.get(0)
                .getClass();
        Codec<Object> codec = CodecPool.getCodec(elementClass);
        output.writeShort(codec.getCodecId()
                .getId());
        for (Object element : data) {
            codec.write(output, element);
        }
    }

    @Override
    public List<?> read(CodecStreamInput input) {
        int size = input.readVarint32();
        if (size == 0) {
            return Collections.emptyList();
        }
        ArrayList<Object> list = new ArrayList<>(size);
        Codec<Object> codec = CodecPool.getCodec(input.readShort());
        for (int i = 0; i < size; i++) {
            list.add(codec.read(input));
        }
        return list;
    }

    @Override
    public int initialCapacity(List<?> items) {
        int size = items.size();
        if (size == 0) {
            // 1 byte for size
            return 1;
        }
        Object item = items.get(0);
        Codec<Object> codec = CodecPool.getCodec(item.getClass());
        if (codec == null) {
            throw new IllegalArgumentException(
                    "Cannot find a codec for the unknown class: "
                            + item.getClass()
                                    .getName());
        }
        return Stream.computeVarint32Size(size) + codec.initialCapacity(item) * size;
    }

}
