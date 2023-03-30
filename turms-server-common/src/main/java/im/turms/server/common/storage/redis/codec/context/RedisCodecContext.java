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

package im.turms.server.common.storage.redis.codec.context;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.RefCntCorrectorByteBuf;
import lombok.Builder;
import lombok.Data;

import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.serialization.SerializationException;
import im.turms.server.common.storage.redis.codec.TurmsRedisCodec;

/**
 * @author James Chen
 */
@Data
@Builder
public class RedisCodecContext {

    private TurmsRedisCodec hashKeyCodec;
    private TurmsRedisCodec hashFieldCodec;
    private TurmsRedisCodec hashValueCodec;

    private TurmsRedisCodec geoKeyCodec;
    private TurmsRedisCodec geoMemberCodec;

    // Hashes

    public ByteBuf encodeHashKey(Object key) {
        return encode(hashKeyCodec, key);
    }

    public ByteBuf encodeHashField(Object field) {
        return encode(hashFieldCodec, field);
    }

    public ByteBuf[] encodeHashFields(Object[] fields) {
        int length = fields.length;
        ByteBuf[] buffers = new ByteBuf[length];
        for (int i = 0; i < length; i++) {
            try {
                buffers[i] = encodeHashField(fields[i]);
            } catch (Exception e) {
                for (int j = 0; j < i; j++) {
                    buffers[j].release();
                }
                throw new SerializationException(
                        "Failed to encode the fields: "
                                + Arrays.toString(fields),
                        e);
            }
        }
        return buffers;
    }

    // Geo

    public ByteBuf encodeGeoMember(Object member) {
        return encode(geoMemberCodec, member);
    }

    public ByteBuf[] encodeGeoMembers(Object[] members) {
        int length = members.length;
        ByteBuf[] buffers = new ByteBuf[length];
        for (int i = 0; i < length; i++) {
            try {
                buffers[i] = encodeGeoMember(members[i]);
            } catch (Exception e) {
                for (int j = 0; j < i; j++) {
                    buffers[j].release();
                }
                throw new SerializationException(
                        "Failed to encode the geo members: "
                                + Arrays.toString(members),
                        e);
            }
        }
        return buffers;
    }

    public ByteBuf encodeGeoKey(Object key) {
        return encode(geoKeyCodec, key);
    }

    // Internal

    private ByteBuf encode(TurmsRedisCodec codec, Object value) {
        if (codec == null) {
            ByteBuf byteBuf = ByteBufUtil.writeObject(value);
            if (byteBuf == null) {
                throw new UnsupportedOperationException(
                        "Could not encode the value: "
                                + value);
            }
            return ByteBufUtil.ensureByteBufRefCnfCorrect(byteBuf);
        }
        return new RefCntCorrectorByteBuf(codec.encode(value));
    }

}
