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

package im.turms.server.common.storage.redis.codec;

import java.nio.ByteBuffer;

import io.lettuce.core.codec.RedisCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author James Chen
 */
public final class TurmsRedisCodecAdapter<K, V> implements RedisCodec<K, V> {

    public static final TurmsRedisCodecAdapter<ByteBuf, ByteBuf> DEFAULT =
            new TurmsRedisCodecAdapter<>();
    private final TurmsRedisCodec keyDecoder;
    private final TurmsRedisCodec valueDecoder;

    public TurmsRedisCodecAdapter() {
        keyDecoder = null;
        valueDecoder = null;
    }

    public TurmsRedisCodecAdapter(TurmsRedisCodec keyDecoder, TurmsRedisCodec valueDecoder) {
        this.keyDecoder = keyDecoder;
        this.valueDecoder = valueDecoder;
    }

    /**
     * @implNote We don't use Unpooled.wrappedBuffer(buffer) to wrap ByteBuffer to ByteBuf because
     *           the internal buffer is shared with the buffer of value by Lettuce. So the internal
     *           buffer will change and we must parse them when the buffer is passed
     * @see io.lettuce.core.protocol.RedisStateMachine#readBytes0(io.netty.buffer.ByteBuf, int)
     */
    @Override
    public K decodeKey(ByteBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (keyDecoder == null) {
            try {
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                return (K) Unpooled.wrappedBuffer(bytes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(
                        "No key decoder to decode the key: "
                                + buffer);
            }
        }
        return (K) keyDecoder.decode(buffer);
    }

    @Override
    public V decodeValue(ByteBuffer buffer) {
        if (buffer == null) {
            return null;
        }
        if (valueDecoder == null) {
            try {
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                return (V) Unpooled.wrappedBuffer(bytes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(
                        "No value decoder to decode the key buffer: "
                                + buffer);
            }
        }
        return (V) valueDecoder.decode(buffer);
    }

    @Override
    public ByteBuffer encodeKey(K key) {
        throw new UnsupportedOperationException(
                "TurmsRedisCodecAdapter#encodeKey should not be called");
    }

    @Override
    public ByteBuffer encodeValue(V value) {
        throw new UnsupportedOperationException(
                "TurmsRedisCodecAdapter#encodeValue should not be called");
    }

}
