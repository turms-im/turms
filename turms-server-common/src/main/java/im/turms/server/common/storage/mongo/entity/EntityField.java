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

package im.turms.server.common.storage.mongo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import im.turms.server.common.infra.reflect.VarAccessor;

/**
 * @author James Chen
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EntityField<T> {

    static final int UNSET_CTOR_PARAM_INDEX = -1;

    private final Class<T> fieldClass;
    /**
     * Key for Map
     */
    private final Class keyClass;
    /**
     * Value for Map or Iterable (e.g. Array, List and Set)
     */
    private final Class elementClass;
    @EqualsAndHashCode.Include
    private final String name;
    private final boolean isIdField;
    /**
     * Don't use a map to find the parameter index in the constructor dynamically to avoid
     * unnecessary overhead when decoding (instantiate) entity from a BSON. It is -1 if the field
     * isn't a field in the constructor.
     */
    private final int ctorParamIndex;

    private final VarAccessor varAccessor;

    private final boolean isEnumNumber;

    public T get(Object entity) {
        return (T) varAccessor.get(entity);
    }

    public void set(Object entity, T value) {
        varAccessor.set(entity, value);
    }
}
