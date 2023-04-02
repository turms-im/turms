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

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import org.jctools.maps.NonBlockingIdentityHashMap;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.time.DateUtil;

/**
 * @author James Chen
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper MAPPER = JsonCodecPool.MAPPER;
    private static final JavaType JAVA_TYPE_STRING_OBJECT_MAP =
            constructJavaType(new TypeReference<HashMap<String, Object>>() {
            });
    private static final JavaType JAVA_TYPE_STRING_STRING_MAP =
            constructJavaType(new TypeReference<HashMap<String, String>>() {
            });

    private static final int ESTIMATED_JSON_FIELD_METADATA_SIZE = 8;

    private static final NonBlockingIdentityHashMap<Class<?>, RecordMetadata> CLASS_TO_METADATA =
            new NonBlockingIdentityHashMap<>(256);

    private JsonUtil() {
    }

    public static JavaType constructJavaType(TypeReference<?> typeReference) {
        return MAPPER.constructType(typeReference);
    }

    public static JavaType constructJavaType(Class<?> type) {
        return MAPPER.constructType(type);
    }

    public static Map<String, Object> readStringObjectMapValue(byte[] src) {
        try {
            return MAPPER.readValue(src, JAVA_TYPE_STRING_OBJECT_MAP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read a map from the input byte array", e);
        }
    }

    public static Map<String, Object> readStringObjectMapValue(InputStream src) {
        try {
            return MAPPER.readValue(src, JAVA_TYPE_STRING_OBJECT_MAP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read a map from the input stream", e);
        }
    }

    public static Map<String, String> readStringStringMapValue(byte[] src) {
        try {
            return MAPPER.readValue(src, JAVA_TYPE_STRING_STRING_MAP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read a map from the input byte array", e);
        }
    }

    public static Map<String, String> readStringStringMapValue(InputStream src) {
        try {
            return MAPPER.readValue(src, JAVA_TYPE_STRING_STRING_MAP);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read a map from the input stream", e);
        }
    }

    public static ByteBuf write(Object value) {
        int estimatedSize = estimateSize(value);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(estimatedSize);
        try (OutputStream bufferOutputStream = new ByteBufOutputStream(buffer)) {
            MAPPER.writeValue(bufferOutputStream, value);
            return buffer;
        } catch (Exception e) {
            ReferenceCountUtil.ensureReleased(buffer);
            throw new IllegalArgumentException(
                    "Failed to write the input object as a byte buffer",
                    e);
        }
    }

    public static String writeAsString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to write the input object as a string", e);
        }
    }

    public static int estimateSize(@Nullable Object value) {
        if (value == null) {
            return 0;
        }
        return value.getClass()
                .isRecord()
                        ? estimateRecordSize(value)
                        : estimateNonRecordSize(value);
    }

    private static int estimateRecordSize(Object value) {
        RecordMetadata metadata = getRecordMetadata(value);
        int size = 0;
        for (Component component : metadata.components) {
            Object componentValue;
            try {
                componentValue = component.accessor.invoke(value);
            } catch (Exception e) {
                continue;
            }
            size += estimateSize(componentValue);
            size += component.nameLength;
            size += ESTIMATED_JSON_FIELD_METADATA_SIZE;
        }
        return size;
    }

    private static int estimateNonRecordSize(Object value) {
        int size = 0;
        if (value instanceof Iterable<?> iterable) {
            for (Object element : iterable) {
                size += estimateSize(element);
            }
        } else if (value.getClass()
                .isArray()) {
            if (value instanceof byte[] array) {
                size += array.length;
            } else if (value instanceof Object[] array) {
                for (Object element : array) {
                    size += estimateSize(element);
                }
            } else {
                // We don't support other array types now because we don't use them
                LOGGER.warn("Unknown array type: "
                        + value.getClass()
                                .getName());
            }
        } else if (value instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                size += entry.getKey() instanceof String str
                        ? StringUtil.getLength(str)
                        : 16;
                Object entryValue = entry.getValue();
                size += estimateSize(entryValue);
                size += ESTIMATED_JSON_FIELD_METADATA_SIZE;
            }
        } else if (value instanceof Date) {
            size += DateUtil.DATE_TIME_LENGTH;
        } else if (value instanceof String str) {
            size += StringUtil.getLength(str);
        } else {
            // We don't use "String.valueOf(val).length()" for better performance
            size += 16;
        }
        return size;
    }

    private static RecordMetadata getRecordMetadata(Object value) {
        return CLASS_TO_METADATA.computeIfAbsent(value.getClass(), clazz -> {
            RecordComponent[] recordComponents = clazz.getRecordComponents();
            if (recordComponents.length == 0) {
                return RecordMetadata.EMPTY;
            }
            Component[] components = new Component[recordComponents.length];
            for (int i = 0, length = recordComponents.length; i < length; i++) {
                RecordComponent component = recordComponents[i];
                components[i] = new Component(
                        component.getName()
                                .length(),
                        component.getAccessor());
            }
            return new RecordMetadata(components);
        });
    }

    private record RecordMetadata(
            Component[] components
    ) {
        static final RecordMetadata EMPTY = new RecordMetadata(new Component[0]);
    }

    private record Component(
            int nameLength,
            Method accessor
    ) {
    }

}
