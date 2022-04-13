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

import im.turms.server.common.access.servicerequest.rpc.HandleServiceRequestCodec;
import im.turms.server.common.access.servicerequest.rpc.ServiceResponseCodec;
import im.turms.server.common.domain.notification.rpc.SendNotificationRequestCodec;
import im.turms.server.common.domain.observation.rpc.CountOnlineUsersRequestCodec;
import im.turms.server.common.domain.session.rpc.QueryUserSessionsRequestCodec;
import im.turms.server.common.domain.session.rpc.SetUserOfflineRequestCodec;
import im.turms.server.common.domain.session.rpc.UserSessionsInfoCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.BoolCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.ByteCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.CharCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.DoubleCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.FloatCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.IntegerCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.ListCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.LongCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.NullCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.ShortCodec;
import im.turms.server.common.infra.cluster.service.codec.codec.impl.StringCodec;
import im.turms.server.common.infra.cluster.service.connection.codec.ClosingHandshakeRequestCodec;
import im.turms.server.common.infra.cluster.service.connection.codec.KeepaliveRequestCodec;
import im.turms.server.common.infra.cluster.service.connection.codec.OpeningHandshakeRequestCodec;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcExceptionCodec;
import io.netty.util.collection.IntObjectHashMap;
import org.springframework.core.GenericTypeResolver;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public final class CodecPool {

    private static final IntObjectHashMap<Codec> ID_TO_CODEC = new IntObjectHashMap<>(32);
    private static final Map<Class<?>, Codec> CLASS_TO_CODEC = new IdentityHashMap<>(32);

    public static void init() {
        if (!ID_TO_CODEC.isEmpty()) {
            return;
        }
        // Primitives
        register(new BoolCodec());
        register(new ByteCodec());
        register(new CharCodec());
        register(new DoubleCodec());
        register(new FloatCodec());
        register(new IntegerCodec());
        register(new LongCodec());
        register(new ShortCodec());

        register(new NullCodec());

        register(new StringCodec());

        // Collections
        register(new ListCodec());

        // RPC
        register(new RpcExceptionCodec());

        register(new OpeningHandshakeRequestCodec());
        register(new ClosingHandshakeRequestCodec());
        register(new KeepaliveRequestCodec());

        register(new CountOnlineUsersRequestCodec());
        register(new HandleServiceRequestCodec());
        register(new QueryUserSessionsRequestCodec());
        register(new SendNotificationRequestCodec());
        register(new SetUserOfflineRequestCodec());

        // DTO
        register(new ServiceResponseCodec());
        register(new UserSessionsInfoCodec());
    }

    private CodecPool() {
    }

    /**
     * Usually used when deserializing
     */
    public static <T> Codec<T> getCodec(int id) {
        return ID_TO_CODEC.get(id);
    }

    /**
     * Usually used when serializing
     */
    public static <T> Codec<T> getCodec(Class<?> clazz) {
        return CLASS_TO_CODEC.get(clazz);
    }

    /**
     * @implNote We don't support something like "instanceof" because of
     * its bad performance.
     */
    private static void register(Codec<?> codec) {
        List<Class<?>> encodableClasses = codec.getEncodableClasses();
        if (encodableClasses == null || encodableClasses.isEmpty()) {
            Class<?> clazz = GenericTypeResolver.resolveTypeArgument(codec.getClass(), Codec.class);
            if (clazz == null) {
                throw new IllegalStateException("The codec %s cannot be resolved".formatted(codec));
            }
            encodableClasses = List.of(clazz);
        }
        for (Class<?> encodableClass : encodableClasses) {
            if (CLASS_TO_CODEC.containsKey(encodableClass)) {
                throw new IllegalStateException("The codec for the class %s has already existed"
                        .formatted(encodableClass.getSimpleName()));
            }
            CLASS_TO_CODEC.put(encodableClass, codec);
        }
        int codecId = codec.getCodecId().getId();
        if (ID_TO_CODEC.containsKey(codecId)) {
            throw new IllegalStateException("The codec ID %d has already existed".formatted(codecId));
        }
        ID_TO_CODEC.put(codecId, codec);
    }

}
