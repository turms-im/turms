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

import im.turms.server.common.infra.cluster.service.codec.codec.Codec;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public class StringCodec implements Codec<String> {

    @Override
    public CodecId getCodecId() {
        return CodecId.STRING;
    }

    @Override
    public void write(CodecStreamOutput output, String data) {
        byte[] bytes = StringUtil.getBytes(data);
        byte coder = StringUtil.getCoder(data);
        int length = bytes.length;
        if (length > Short.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "The byte length of the string must not be greater than "
                            + Short.MAX_VALUE);
        }
        output.writeVarint32(length);
        if (length > 0) {
            output.writeBytes(bytes)
                    .writeByte(coder);
        }
    }

    @Override
    public String read(CodecStreamInput input) {
        int length = input.readVarint32();
        if (length <= 0) {
            return "";
        }
        return StringUtil.newString(input.readBytes(length), input.readByte());
    }

    @Override
    public int initialCapacity(String data) {
        return Short.BYTES + StringUtil.getLength(data) + 1;
    }

}
