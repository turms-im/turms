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

package io.lettuce.core;

import java.util.List;

import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.output.GeoWithinListOutput;
import io.lettuce.core.output.KeyValueListOutput;
import io.lettuce.core.protocol.Command;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandType;
import io.netty.buffer.ByteBuf;

import im.turms.server.common.storage.redis.codec.TurmsRedisCodecAdapter;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContext;

import static io.lettuce.core.protocol.CommandType.EVAL;
import static io.lettuce.core.protocol.CommandType.EVALSHA;
import static io.lettuce.core.protocol.CommandType.HGETALL;

/**
 * @author James Chen
 */
public class TurmsRedisCommandBuilder extends RedisCommandBuilder {

    private final RedisCodec hashFieldAndValueCodec;
    private final RedisCodec memberCodec;

    public TurmsRedisCommandBuilder(RedisCodecContext context) {
        super(TurmsRedisCodecAdapter.DEFAULT);
        this.hashFieldAndValueCodec = new TurmsRedisCodecAdapter(
                context.getHashFieldCodec(),
                context.getHashValueCodec());
        this.memberCodec = new TurmsRedisCodecAdapter(null, context.getGeoMemberCodec());
    }

    // Hashes

    public <K, V> Command<ByteBuf, ByteBuf, List<KeyValue<K, V>>> hgetall(ByteBuf key) {
        return createCommand(HGETALL, new KeyValueListOutput<>(hashFieldAndValueCodec), key);
    }

    // Geo

    public <T> Command<ByteBuf, ByteBuf, List<GeoWithin<T>>> georadiusbymember(
            CommandType commandType,
            ByteBuf key,
            ByteBuf member,
            double distance,
            String unit,
            GeoArgs geoArgs) {
        CommandArgs<ByteBuf, ByteBuf> args = new CommandArgs<>(memberCodec).addKey(key)
                .addValue(member)
                .add(distance)
                .add(unit);
        geoArgs.build(args);
        GeoWithinListOutput output = new GeoWithinListOutput<>(
                memberCodec,
                geoArgs.isWithDistance(),
                geoArgs.isWithHash(),
                geoArgs.isWithCoordinates());
        return createCommand(commandType, output, args);
    }

    // Scripting

    public <T> Command<ByteBuf, ByteBuf, T> evalsha(
            ByteBuf digest,
            ScriptOutputType type,
            ByteBuf[] keys,
            int keyLength) {
        CommandArgs<ByteBuf, ByteBuf> args = new CommandArgs<>(codec);
        args.addKey(digest)
                .add(keyLength)
                .addKeys(keys);
        CommandOutput<ByteBuf, ByteBuf, T> output = newScriptOutput(codec, type);
        return createCommand(EVALSHA, output, args);
    }

    public <T> Command<ByteBuf, ByteBuf, T> eval(
            ByteBuf script,
            ScriptOutputType type,
            ByteBuf[] keys,
            int keyLength) {
        CommandArgs<ByteBuf, ByteBuf> args = new CommandArgs<>(codec);
        args.addKey(script)
                .add(keyLength)
                .addKeys(keys);
        CommandOutput<ByteBuf, ByteBuf, T> output = newScriptOutput(codec, type);
        return createCommand(EVAL, output, args);
    }

}
