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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.time.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.SneakyThrows;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final int ESTIMATED_JSON_FIELD_META_SIZE = 8;
    private static final ObjectMapper MAPPER = JsonCodecPool.MAPPER;

    private static final JavaType JAVA_TYPE_STRING_OBJECT_MAP = constructJavaType(new TypeReference<HashMap<String, Object>>() {
    });
    private static final JavaType JAVA_TYPE_STRING_STRING_MAP = constructJavaType(new TypeReference<HashMap<String, String>>() {
    });

    private JsonUtil() {
    }

    public static JavaType constructJavaType(TypeReference<?> typeReference) {
        return MAPPER.constructType(typeReference);
    }

    public static JavaType constructJavaType(Class<?> type) {
        return MAPPER.constructType(type);
    }

    @SneakyThrows
    public static Map<String, Object> readStringObjectMapValue(byte[] src) {
        return MAPPER.readValue(src, JAVA_TYPE_STRING_OBJECT_MAP);
    }

    @SneakyThrows
    public static Map<String, Object> readStringObjectMapValue(InputStream src) {
        return MAPPER.readValue(src, JAVA_TYPE_STRING_OBJECT_MAP);
    }

    @SneakyThrows
    public static Map<String, String> readStringStringMapValue(byte[] src) {
        return MAPPER.readValue(src, JAVA_TYPE_STRING_STRING_MAP);
    }

    @SneakyThrows
    public static Map<String, String> readStringStringMapValue(InputStream src) {
        return MAPPER.readValue(src, JAVA_TYPE_STRING_STRING_MAP);
    }

    public static ByteBuf write(Object body) {
        int estimatedSize = estimateJson(body);
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(estimatedSize);
        try (OutputStream bufferOutputStream = new ByteBufOutputStream(buffer)) {
            MAPPER.writeValue(bufferOutputStream, body);
            return buffer;
        } catch (IOException e) {
            ReferenceCountUtil.ensureReleased(buffer);
            throw new RuntimeException(e);
        }
    }

    public static int estimateJson(@Nullable Object val) {
        if (val == null) {
            return 0;
        }
        int size = 0;
        if (val instanceof Collection<?> collection) {
            for (Object o : collection) {
                size += estimateJson(o);
            }
        } else if (val.getClass().isArray()) {
            if (val instanceof byte[] array) {
                size += array.length;
            } else if (val instanceof Object[] array) {
                for (Object element : array) {
                    size += estimateJson(element);
                }
            } else {
                // We don't support other array types now because we don't use them
                LOGGER.warn("Unknown array type: " + val.getClass());
            }
        } else if (val instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                size += key instanceof String str ? StringUtil.getLength(str) : 16;
                size += estimateJson(entry.getValue());
            }
        } else {
            int valSize = estimatePlainObject(val);
            if (valSize >= 0) {
                size += valSize;
            } else {
                Class<?> valClass = val.getClass();
                if (valClass.getPackageName().startsWith("im.turms")) {
                    for (Field field : valClass.getDeclaredFields()) {
                        if (Modifier.isStatic(field.getModifiers())) {
                            continue;
                        }
                        ReflectionUtil.setAccessible(field);
                        Object o;
                        try {
                            o = field.get(val);
                        } catch (Exception e) {
                            continue;
                        }
                        valSize = estimateJson(o);
                        if (valSize > 0) {
                            size += valSize;
                            size += field.getName().length();
                            size += ESTIMATED_JSON_FIELD_META_SIZE;
                        }
                    }
                }
            }
        }
        return size;
    }

    public static int estimatePlainObject(@Nullable Object val) {
        if (val == null) {
            return 0;
        }
        if (val instanceof Date) {
            return DateUtil.DATE_TIME_LENGTH;
        }
        if (val instanceof String str) {
            return StringUtil.getLength(str);
        }
        if (val.getClass().getPackageName().startsWith("java.lang")) {
            // We don't use "String.valueOf(val).length()" for better performance
            return 16;
        }
        return -1;
    }

}
