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

package im.turms.server.common.redis.codec.context;

import im.turms.server.common.redis.codec.TurmsRedisCodec;
import im.turms.server.common.util.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import lombok.Builder;
import lombok.Data;

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
        ByteBuf[] buffers = new ByteBuf[fields.length];
        for (int i = 0; i < fields.length; i++) {
            buffers[i] = encodeHashField(fields[i]);
        }
        return buffers;
    }

    // Geo

    public ByteBuf encodeGeoMember(Object member) {
        return encode(geoMemberCodec, member);
    }

    public ByteBuf[] encodeGeoMembers(Object[] members) {
        ByteBuf[] buffers = new ByteBuf[members.length];
        for (int i = 0; i < members.length; i++) {
            buffers[i] = encodeGeoMember(members[i]);
        }
        return buffers;
    }

    public ByteBuf encodeGeoKey(Object key) {
        return encode(geoKeyCodec, key);
    }

    // Internal

    private ByteBuf encode(TurmsRedisCodec codec, Object value) {
        if (codec == null) {
            ByteBuf byteBuf = ByteBufUtil.obj2Buffer(value);
            if (byteBuf == null) {
                throw new UnsupportedOperationException("Cannot encode value: " + value);
            }
            return byteBuf;
        }
        return codec.encode(value);
    }

}
