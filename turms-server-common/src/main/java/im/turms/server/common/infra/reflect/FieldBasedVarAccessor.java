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

package im.turms.server.common.infra.reflect;

import java.lang.reflect.Field;

/**
 * @author James Chen
 */
public class FieldBasedVarAccessor<T, V> implements VarAccessor<T, V> {

    private final Field field;

    public FieldBasedVarAccessor(Field field) {
        ReflectionUtil.setAccessible(field);
        this.field = field;
    }

    @Override
    public V get(T object) {
        try {
            return (V) field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to get the value from the object: "
                            + object,
                    e);
        }
    }

    @Override
    public void set(T object, V value) {
        try {
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to set the value to ("
                            + value
                            + ") on the object: "
                            + object,
                    e);
        }
    }

}