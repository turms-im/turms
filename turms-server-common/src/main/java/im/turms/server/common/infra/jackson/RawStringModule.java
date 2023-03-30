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

package im.turms.server.common.infra.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import im.turms.server.common.domain.common.access.dto.ControllerDTO;

/**
 * @author James Chen
 */
public class RawStringModule extends SimpleModule {

    /**
     * @see StringDeserializer
     */
    private static final JsonDeserializer<String> DESERIALIZER = new TurmsStringDeserializer();

    public RawStringModule() {
        addDeserializer(String.class, DESERIALIZER);
    }

    private static class TurmsStringDeserializer extends JsonDeserializer<String>
            implements ContextualDeserializer {
        @Override
        public String deserialize(JsonParser parser, DeserializationContext ctxt) {
            throw new UnsupportedOperationException("The method should not be called");
        }

        @Override
        public JsonDeserializer<?> createContextual(
                DeserializationContext ctxt,
                BeanProperty property) {
            boolean toRawString = property != null
                    && ControllerDTO.class.isAssignableFrom(property.getMember()
                            .getDeclaringClass());
            return toRawString
                    ? RawStringDeserializer.INSTANCE
                    : StringDeserializer.instance;
        }
    }

    private static class RawStringDeserializer extends JsonDeserializer<String> {
        static final RawStringDeserializer INSTANCE = new RawStringDeserializer();

        @Override
        public String deserialize(JsonParser parser, DeserializationContext ctxt)
                throws IOException {
            if (parser.hasToken(JsonToken.VALUE_STRING)) {
                return parser.getText();
            }
            JsonNode jsonNode = ctxt.readValue(parser, JsonNode.class);
            return jsonNode.toString();
        }
    }
}