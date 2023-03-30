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

package im.turms.server.common.infra.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import im.turms.server.common.infra.jackson.CaffeineLookupCache;
import im.turms.server.common.infra.jackson.RawStringModule;
import im.turms.server.common.infra.security.SensitiveProperty;
import im.turms.server.common.infra.time.TimeZoneConst;

/**
 * @author James Chen
 */
public class JsonCodecPool {

    private JsonCodecPool() {
    }

    public static final ObjectMapper MAPPER = JsonMapper.builder()
            // mapper
            .enable(MapperFeature.PROPAGATE_TRANSIENT_MARKER)
            // serialization
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            // modules
            .addModules(new JavaTimeModule(), new ParameterNamesModule(), new RawStringModule())
            // date format
            .defaultDateFormat(new StdDateFormat().withColonInTimeZone(true))
            // time zone
            .defaultTimeZone(TimeZoneConst.ZONE)
            // type factory
            .typeFactory(TypeFactory.defaultInstance()
                    .withCache(new CaffeineLookupCache<>()))
            .build();

    static {
        SerializationConfig serializationConfig = MAPPER.getSerializationConfig();
        DeserializationConfig deserializationConfig = MAPPER.getDeserializationConfig();
        AnnotationIntrospector introspector = serializationConfig.getAnnotationIntrospector();

        serializationConfig
                .with(AnnotationIntrospector.pair(introspector, new NopAnnotationIntrospector() {
                    @Override
                    public boolean hasIgnoreMarker(AnnotatedMember m) {
                        if (super.hasIgnoreMarker(m)) {
                            return true;
                        }
                        SensitiveProperty property = _findAnnotation(m, SensitiveProperty.class);
                        return property != null
                                && property.value() != SensitiveProperty.Access.ALLOW_SERIALIZATION;
                    }
                }));
        deserializationConfig
                .with(AnnotationIntrospector.pair(introspector, new NopAnnotationIntrospector() {
                    @Override
                    public boolean hasIgnoreMarker(AnnotatedMember m) {
                        if (super.hasIgnoreMarker(m)) {
                            return true;
                        }
                        SensitiveProperty property = _findAnnotation(m, SensitiveProperty.class);
                        return property != null
                                && property
                                        .value() != SensitiveProperty.Access.ALLOW_DESERIALIZATION;
                    }
                }));
    }

}
