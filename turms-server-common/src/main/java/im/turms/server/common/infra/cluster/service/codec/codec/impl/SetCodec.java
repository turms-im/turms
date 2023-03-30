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

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.Stream;

/**
 * @author James Chen
 */
public class SetCodec implements Codec<Set<?>> {

    @Override
    public CodecId getCodecId() {
        return CodecId.COLLECTION_SET;
    }

    @Override
    public List<Class<?>> getEncodableClasses() {
        return List.of(Set.class, UnifiedSet.class, HashSet.class, LinkedHashSet.class);
    }

    @Override
    public List<Class<?>> getEncodableSuperClasses() {
        return List.of(Set.class);
    }

    @Override
    public void write(CodecStreamOutput output, Set<?> data) {
        int size = data.size();
        output.writeVarint32(size);
        if (size == 0) {
            return;
        }
        Class<?> elementClass = data.iterator()
                .next()
                .getClass();
        Codec<Object> codec = CodecPool.getCodec(elementClass);
        output.writeShort(codec.getCodecId()
                .getId());
        for (Object element : data) {
            codec.write(output, element);
        }
    }

    @Override
    public Set<?> read(CodecStreamInput input) {
        int size = input.readVarint32();
        if (size == 0) {
            return Collections.emptySet();
        }
        Set<Object> set = CollectionUtil.newSetWithExpectedSize(size);
        Codec<Object> codec = CodecPool.getCodec(input.readShort());
        for (int i = 0; i < size; i++) {
            set.add(codec.read(input));
        }
        return set;
    }

    @Override
    public int initialCapacity(Set<?> items) {
        int size = items.size();
        if (size == 0) {
            // 1 byte for size
            return 1;
        }
        Object item = items.iterator()
                .next();
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