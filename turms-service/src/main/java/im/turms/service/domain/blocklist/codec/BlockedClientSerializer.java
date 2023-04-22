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

package im.turms.service.domain.blocklist.codec;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import im.turms.server.common.domain.blocklist.bo.BlockedClient;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.time.DateUtil;

/**
 * @author James Chen
 */
@JsonComponent
public class BlockedClientSerializer {

    public static class Serializer extends JsonSerializer<BlockedClient> {
        @Override
        public void serialize(BlockedClient value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeStartObject();
            Object id = value.id();
            if (id instanceof Long userId) {
                gen.writeNumberField("id", userId);
            } else {
                gen.writeStringField("id",
                        InetAddressUtil.ipBytesToString(((ByteArrayWrapper) id).getBytes()));
            }
            gen.writeStringField("blockEndTime", DateUtil.toStr(value.blockEndTimeMillis()));
            gen.writeEndObject();
        }
    }

}