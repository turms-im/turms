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

package im.turms.server.common.cluster.service.codec.codec.impl;

import im.turms.server.common.cluster.service.codec.codec.Codec;
import im.turms.server.common.cluster.service.codec.codec.CodecId;
import im.turms.server.common.cluster.service.codec.codec.CodecPool;
import im.turms.server.common.util.CodecUtil;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author James Chen
 */
public class ListCodec implements Codec<List<?>> {

    @Override
    public CodecId getCodecId() {
        return CodecId.COLLECTION_LIST;
    }

    @Override
    public void write(ByteBuf output, List<?> data) {
        int size = data.size();
        output.writeShort(size);
        if (size != 0) {
            Class<?> elementClass = data.get(0).getClass();
            CodecId elementCodecId = CodecPool.getCodec(elementClass).getCodecId();
            output.writeShort(elementCodecId.getId());
            for (Object element : data) {
                CodecUtil.writeObject(output, element);
            }
        }
    }

    @Override
    public List<?> read(ByteBuf input) {
        int size = input.readShort();
        if (size == 0) {
            return Collections.emptyList();
        } else {
            int elementCodecId = input.readShort();
            ArrayList<Object> list = new ArrayList<>(size);
            Codec<Object> codec = CodecPool.getCodec(elementCodecId);
            for (int i = 0; i < size; i++) {
                Object obj = codec.read(input);
                list.add(obj);
            }
            return list;
        }
    }

    @Override
    public int initialCapacity(List<?> data) {
        return -1;
    }

}
