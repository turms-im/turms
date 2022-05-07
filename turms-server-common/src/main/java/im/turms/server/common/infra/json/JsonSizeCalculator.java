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

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.time.DateUtil;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author James Chen
 */
public final class JsonSizeCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSizeCalculator.class);

    private static final int ESTIMATED_JSON_FIELD_META_SIZE = 8;

    private JsonSizeCalculator() {
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
                        try {
                            field.setAccessible(true);
                            Object o = field.get(val);
                            valSize = estimateJson(o);
                            if (valSize > 0) {
                                size += valSize;
                                size += field.getName().length();
                                size += ESTIMATED_JSON_FIELD_META_SIZE;
                            }
                        } catch (Exception e) {
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
