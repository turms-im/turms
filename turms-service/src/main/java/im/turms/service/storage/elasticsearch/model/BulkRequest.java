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

package im.turms.service.storage.elasticsearch.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author James Chen
 */
@JsonSerialize(using = BulkRequest.BulkRequestSerializer.class)
public record BulkRequest(
        List<Object> operations
) {

    public static class BulkRequestSerializer extends JsonSerializer<BulkRequest> {
        @Override
        public void serialize(BulkRequest value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            boolean isFirstOperation = true;
            for (Object operation : value.operations) {
                if (isFirstOperation) {
                    isFirstOperation = false;
                } else {
                    gen.writeRawValue("\n");
                }
                gen.writeObject(operation);
            }
            // The bulk request must be terminated by a newline.
            gen.writeRawValue("\n");
        }
    }

}