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

package im.turms.server.common.infra.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import im.turms.server.common.infra.collection.MapUtil;
import lombok.SneakyThrows;

import javax.validation.constraints.NotNull;
import java.util.Map;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MUTABLE_PROPERTIES_WRITER;

/**
 * @author James Chen
 */
public class TurmsPropertiesConvertor {

    private TurmsPropertiesConvertor() {
    }

    // Merge

    @SneakyThrows
    public static TurmsProperties mergeAsProperties(
            @NotNull TurmsProperties propertiesToUpdate,
            @NotNull String propertiesForUpdating) {
        ObjectReader objectReader = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .readerForUpdating(propertiesToUpdate)
                .forType(TurmsProperties.class);
        return objectReader.readValue(propertiesForUpdating);
    }

    public static TurmsProperties mergeAsProperties(
            @NotNull TurmsProperties propertiesToUpdate,
            Map<String, Object> propertiesForUpdating) {
        return mergeAsProperties(propertiesToUpdate, toMutablePropertiesString(propertiesForUpdating));
    }

    public static Map<String, Object> mergePropertiesWithMetadata(
            @NotNull Map<String, Object> properties,
            @NotNull Map<String, Object> metadata) {
        properties = MapUtil.addValueKeyToAllLeaves(properties);
        return MapUtil.deepMerge(properties, metadata);
    }

    // Convert

    public static String toMutablePropertiesString(TurmsProperties propertiesForUpdating) {
        try {
            return MUTABLE_PROPERTIES_WRITER.writeValueAsString(propertiesForUpdating);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String toMutablePropertiesString(Map<String, Object> propertiesForUpdating) {
        try {
            return MUTABLE_PROPERTIES_WRITER.writeValueAsString(propertiesForUpdating);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
