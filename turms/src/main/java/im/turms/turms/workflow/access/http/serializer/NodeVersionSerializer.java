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

package im.turms.turms.workflow.access.http.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import im.turms.server.common.cluster.node.NodeVersion;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author James Chen
 */
@JsonComponent
@Log4j2
public class NodeVersionSerializer {

    public static class Serializer extends JsonSerializer<NodeVersion> {
        @Override
        public void serialize(NodeVersion value, JsonGenerator gen, SerializerProvider provider) {
            try {
                gen.writeString(value.toString());
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

}