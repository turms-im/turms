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

package im.turms.server.common.storage.redis.script;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import jakarta.annotation.Nullable;

import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.codec.Base16;
import io.lettuce.core.protocol.BaseRedisCommandBuilder;
import io.netty.buffer.ByteBuf;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ByteBufUtil;

import static im.turms.server.common.infra.unit.ByteSizeUnit.KB;

/**
 * @author James Chen
 */
public record RedisScript(
        ByteBuf script,
        ByteBuf digest,
        ScriptOutputType outputType
) {

    private static final int MAX_SCRIPT_SIZE = 10 * KB;

    /**
     * @param outputType {@link BaseRedisCommandBuilder#newScriptOutput}
     */
    public static RedisScript get(ClassPathResource resource, ScriptOutputType outputType) {
        return get(resource, outputType, null);
    }

    /**
     * @param outputType {@link BaseRedisCommandBuilder#newScriptOutput}
     */
    public static RedisScript get(
            ClassPathResource resource,
            ScriptOutputType outputType,
            @Nullable Map<String, Object> placeholders) {
        try {
            byte[] bytes = resource.getInputStream()
                    .readAllBytes();
            if (!CollectionUtils.isEmpty(placeholders)) {
                String s = new String(bytes, StandardCharsets.US_ASCII);
                for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
                    s = s.replace(entry.getKey(),
                            entry.getValue()
                                    .toString());
                }
                bytes = StringUtil.getBytes(s);
            }
            if (bytes.length > MAX_SCRIPT_SIZE) {
                String message = "The size of the script ("
                        + resource.getPath()
                        + ") must not be greater than "
                        + MAX_SCRIPT_SIZE
                        + " bytes, but got: "
                        + bytes.length;
                throw new InputOutputException(message);
            }
            return new RedisScript(
                    ByteBufUtil.getUnreleasableDirectBuffer(bytes),
                    ByteBufUtil
                            .getUnreleasableDirectBuffer(StringUtil.getBytes(Base16.digest(bytes))),
                    outputType);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
