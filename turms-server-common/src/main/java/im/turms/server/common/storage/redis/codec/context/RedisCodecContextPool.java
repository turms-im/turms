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

import im.turms.server.common.storage.redis.codec.GeoUserIdCodec;
import im.turms.server.common.storage.redis.codec.GeoUserSessionIdCodec;
import im.turms.server.common.storage.redis.codec.SessionHashFieldCodec;
import im.turms.server.common.storage.redis.codec.SessionHashValueCodec;
import im.turms.server.common.storage.redis.codec.SessionKeyCodec;

/**
 * @author James Chen
 */
public final class RedisCodecContextPool {

    private RedisCodecContextPool() {
    }

    public static final RedisCodecContext GEO_USER_SESSION_ID_CODEC_CONTEXT;
    public static final RedisCodecContext GEO_USER_ID_CODEC_CONTEXT;
    public static final RedisCodecContext USER_SESSIONS_STATUS_CODEC_CONTEXT;

    static {
        GEO_USER_SESSION_ID_CODEC_CONTEXT = RedisCodecContext.builder()
                .geoMemberCodec(new GeoUserSessionIdCodec())
                .build();

        GEO_USER_ID_CODEC_CONTEXT = RedisCodecContext.builder()
                .geoMemberCodec(new GeoUserIdCodec())
                .build();

        USER_SESSIONS_STATUS_CODEC_CONTEXT = RedisCodecContext.builder()
                .hashKeyCodec(new SessionKeyCodec())
                .hashFieldCodec(new SessionHashFieldCodec())
                .hashValueCodec(new SessionHashValueCodec())
                .build();
    }

}
