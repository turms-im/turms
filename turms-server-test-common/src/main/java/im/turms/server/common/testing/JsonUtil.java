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

package im.turms.server.common.testing;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SequencedMap;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
public final class JsonUtil {

    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .addModule(new JavaTimeModule())
            .build();

    private JsonUtil() {
    }

    public static void assertEqual(Map<String, Object> actual, InputStream expected) {
        // We use String instead of byte[] for test debugging
        JsonNode expectedJson;
        JsonNode actualJson = getSortedMapJsonNode(actual);
        try {
            expectedJson = MAPPER.readTree(expected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(actualJson).isEqualTo(expectedJson);
    }

    public static JsonNode getSortedMapJsonNode(Map<String, Object> map) {
        Map<String, Object> sortedMap = sortMapEntries(map);
        String json;
        try {
            json = MAPPER.writeValueAsString(sortedMap);
            return MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @implNote 1. Sort map entries so that we can use the consistent output for comparison.
     *           <p>
     *           2. We don't use
     *           {@link com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS}
     *           because {@link MapSerializer#_orderEntries} in jackson-databind-2.17.1 will sort
     *           {@link LinkedHashMap}, which is unexpected for our use cases.
     */
    public static Map<String, Object> sortMapEntries(Map<String, Object> map) {
        Map<String, Object> newMap = LinkedHashMap.newLinkedHashMap(map.size());
        if (!(map instanceof SequencedMap<String, Object>)) {
            map = new TreeMap<>(map);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object entryValue = entry.getValue();
            if (entryValue instanceof Map<?, ?> entryValueMap) {
                entryValue = sortMapEntries((Map<String, Object>) entryValueMap);
            }
            newMap.put(entry.getKey(), entryValue);
        }
        return newMap;
    }

}