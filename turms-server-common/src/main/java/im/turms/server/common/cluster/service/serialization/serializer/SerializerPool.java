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

package im.turms.server.common.cluster.service.serialization.serializer;

import im.turms.server.common.cluster.service.serialization.serializer.impl.BoolSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.ByteSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.CharSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.DoubleSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.FloatSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.IntegerSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.ListSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.LongSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.ShortSerializer;
import im.turms.server.common.cluster.service.serialization.serializer.impl.StringSerializer;
import im.turms.server.common.rpc.serializer.response.ServiceResponseSerializer;
import im.turms.server.common.rpc.serializer.request.CountOnlineUsersRequestSerializer;
import im.turms.server.common.rpc.serializer.request.HandleServiceRequestSerializer;
import im.turms.server.common.rpc.serializer.request.SendNotificationRequestSerializer;
import im.turms.server.common.rpc.serializer.request.SetUserOfflineRequestSerializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author James Chen
 */
@Log4j2
public final class SerializerPool {

    private static final Map<Integer, Serializer> SERIALIZERS_BY_ID = new HashMap<>(32);
    private static final Map<Class<?>, Serializer> SERIALIZERS_BY_CLASS = new IdentityHashMap<>(32);

    static {
        // Primitives
        register(new BoolSerializer());
        register(new ByteSerializer());
        register(new CharSerializer());
        register(new DoubleSerializer());
        register(new FloatSerializer());
        register(new IntegerSerializer());
        register(new LongSerializer());
        register(new ShortSerializer());

        register(new StringSerializer());

        // Collections
        register(new ListSerializer());

        // RPC
        register(new SendNotificationRequestSerializer());
        register(new HandleServiceRequestSerializer());
        register(new SetUserOfflineRequestSerializer());
        register(new CountOnlineUsersRequestSerializer());

        // DTO
        register(new ServiceResponseSerializer());
    }

    private SerializerPool() {
    }

    public static void register(Serializer<?> serializer) {
        Class<?> clazz = GenericTypeResolver.resolveTypeArgument(serializer.getClass(), Serializer.class);
        if (clazz != null) {
            if (SERIALIZERS_BY_CLASS.containsKey(clazz)) {
                log.error("The serializer for the class {} has already existed", clazz.getSimpleName());
            } else {
                SERIALIZERS_BY_CLASS.put(clazz, serializer);
                SERIALIZERS_BY_ID.put(serializer.getSerializerId().getId(), serializer);
            }
        } else {
            log.error("The serializer {} cannot be resolved", serializer);
        }
    }

    /**
     * Usually used when deserializing
     */
    public static <T> Serializer<T> getSerializer(int id) {
        return SERIALIZERS_BY_ID.get(id);
    }

    /**
     * Usually used when serializing
     */
    public static <T> Serializer<T> getSerializer(Class<?> clazz) {
        return SERIALIZERS_BY_CLASS.get(clazz);
    }
}
