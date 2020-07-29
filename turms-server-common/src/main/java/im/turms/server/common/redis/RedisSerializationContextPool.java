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

package im.turms.server.common.redis;


import im.turms.server.common.bo.session.UserSessionId;
import im.turms.server.common.redis.serializer.*;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author James Chen
 */
public class RedisSerializationContextPool {

    private RedisSerializationContextPool() {
    }

    public static final RedisSerializationContext<String, UserSessionId> GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT;
    public static final RedisSerializationContext<String, Long> GEO_USER_ID_SERIALIZATION_CONTEXT;
    public static final RedisSerializationContext<Long, String> USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT;

    static {
        RedisSerializer<String> stringRedisSerializer = RedisSerializer.string();

        GeoUserSessionIdSerializer geoUserSessionIdSerializer = new GeoUserSessionIdSerializer();
        GEO_USER_SESSION_ID_SERIALIZATION_CONTEXT = RedisSerializationContext
                .<String, UserSessionId>newSerializationContext()
                .key(stringRedisSerializer)
                .value(geoUserSessionIdSerializer, geoUserSessionIdSerializer)
                .hashKey(stringRedisSerializer)
                .hashValue(geoUserSessionIdSerializer, geoUserSessionIdSerializer)
                .build();

        GeoUserIdSerializer geoUserIdSerializer = new GeoUserIdSerializer();
        GEO_USER_ID_SERIALIZATION_CONTEXT = RedisSerializationContext
                .<String, Long>newSerializationContext()
                .key(stringRedisSerializer)
                .value(geoUserIdSerializer, geoUserIdSerializer)
                .hashKey(stringRedisSerializer)
                .hashValue(geoUserIdSerializer, geoUserIdSerializer)
                .build();

        SessionKeySerializer sessionKeySerializer = new SessionKeySerializer();
        SessionHashKeySerializer sessionHashKeySerializer = new SessionHashKeySerializer();
        SessionHashValueSerializer sessionHashValueSerializer = new SessionHashValueSerializer();
        USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT = RedisSerializationContext
                .<Long, String>newSerializationContext()
                .key(sessionKeySerializer, sessionKeySerializer)
                // We don't use the value serializer and put the string serializer here as a placeholder
                .value(StringRedisSerializer.UTF_8)
                .hashKey(sessionHashKeySerializer, sessionHashKeySerializer)
                .hashValue(sessionHashValueSerializer, sessionHashValueSerializer)
                .build();
    }

}
