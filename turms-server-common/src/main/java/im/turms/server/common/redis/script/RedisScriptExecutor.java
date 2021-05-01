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

package im.turms.server.common.redis.script;

import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.ReactiveScriptingCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.script.RedisScript;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public final class RedisScriptExecutor {

    private RedisScriptExecutor() {
    }

    public static <T> Mono<T> execute(ReactiveScriptingCommands commands,
                                      RedisScript<?> script,
                                      ReturnType returnType,
                                      Object... keys) {
        ByteBuffer[] buffers = new ByteBuffer[keys.length];
        for (int i = 0; i < keys.length; i++) {
            Object obj = keys[i];
            buffers[i] = obj2Buffer(obj);
        }
        return execute(commands, script, returnType, buffers);
    }

    /**
     * @see org.springframework.data.redis.core.script.DefaultReactiveScriptExecutor#eval
     */
    public static <T> Mono<T> execute(ReactiveScriptingCommands commands,
                                      RedisScript<?> script,
                                      ReturnType returnType,
                                      ByteBuffer[] keys) {
        return (Mono<T>) commands.evalSha(script.getSha1(), returnType, keys.length, keys)
                .onErrorResume(e -> {
                    if (exceptionContainsNoScriptError(e)) {
                        return commands.eval(ByteBuffer.wrap(script.getScriptAsString().getBytes(StandardCharsets.UTF_8)), returnType,
                                keys.length, keys);
                    }
                    return Flux.error(e instanceof RuntimeException ? e : new RedisSystemException(e.getMessage(), e));
                })
                .single();
    }

    public static ByteBuffer obj2Buffer(Object obj) {
        if (obj instanceof Byte) {
            return ByteBuffer.wrap(new byte[] {(byte) obj});
        } else if (obj instanceof Short) {
            return ByteBuffer.allocate(Short.BYTES).putShort((short) obj).flip();
        } else if (obj instanceof Integer) {
            return ByteBuffer.allocate(Integer.BYTES).putInt((int) obj).flip();
        } else if (obj instanceof Long) {
            return ByteBuffer.allocate(Long.BYTES).putLong((long) obj).flip();
        } else if (obj instanceof String) {
            return ByteBuffer.wrap(((String) obj).getBytes(StandardCharsets.UTF_8));
        }
        throw new IllegalArgumentException("Cannot serialize the unknown value: " + obj);
    }

    /**
     * @see org.springframework.data.redis.core.script.ScriptUtils#exceptionContainsNoScriptError(java.lang.Throwable)
     */
    private static boolean exceptionContainsNoScriptError(Throwable e) {
        if (!(e instanceof NonTransientDataAccessException)) {
            return false;
        }
        Throwable current = e;
        while (current != null) {
            String exMessage = current.getMessage();
            if (exMessage != null && exMessage.contains("NOSCRIPT")) {
                return true;
            }
            current = current.getCause();
        }
        return false;
    }

}