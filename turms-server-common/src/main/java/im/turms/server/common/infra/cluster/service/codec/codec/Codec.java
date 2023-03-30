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

package im.turms.server.common.infra.cluster.service.codec.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;

/**
 * @author James Chen
 */
public interface Codec<T> {

    CodecId getCodecId();

    default List<Class<?>> getEncodableClasses() {
        return null;
    }

    default List<Class<?>> getEncodableSuperClasses() {
        return null;
    }

    void write(CodecStreamOutput output, T data);

    T read(CodecStreamInput input);

    int initialCapacity(T data);

    default ByteBuf byteBufToComposite(T data) {
        return null;
    }

}
