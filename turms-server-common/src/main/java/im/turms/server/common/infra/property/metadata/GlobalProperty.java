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

package im.turms.server.common.infra.property.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The field marked with @GlobalProperty is considered a global property. Otherwise, it is a local
 * property (turms doesn't support mixed properties currently).
 * <p>
 * Both local properties and global properties can be immutable or mutable. 1. For immutable
 * local/global properties, they can only be applied on startup; 2. For mutable local properties,
 * they can only be updated via their own admin APIs. 3. For mutable global properties, they are
 * always synced with the ones in the config server, and the properties in the config server can be
 * updated via their own or others' admin APIs
 *
 * @author James Chen
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalProperty {
}