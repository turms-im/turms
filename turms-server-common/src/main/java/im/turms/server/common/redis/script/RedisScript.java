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

import im.turms.server.common.util.ByteBufUtil;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.codec.Base16;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author James Chen
 */
@Data
public class RedisScript {

    private static final int MAX_SCRIPT_SIZE = 1024;

    private final ByteBuf script;
    private final ByteBuf digest;
    private final ScriptOutputType outputType;

    public RedisScript(ClassPathResource resource, ScriptOutputType outputType) {
        this.outputType = outputType;
        try {
            byte[] bytes = resource.getInputStream().readAllBytes();
            if (bytes.length > MAX_SCRIPT_SIZE) {
                String error = "The script cannot be larger than " + MAX_SCRIPT_SIZE + ": " + resource.getPath();
                throw new IllegalStateException(error);
            }
            script = ByteBufUtil.getUnreleasableDirectBuffer(bytes);
            digest = ByteBufUtil.getUnreleasableDirectBuffer(Base16.digest(bytes).getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
