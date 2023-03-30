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

import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
// TODO: @FieldNameConstants: https://github.com/mplushnikov/lombok-intellij-plugin/issues/1082
public record FieldMetadata(
        boolean deprecated,
        boolean global,
        boolean mutable,
        boolean sensitive,
        String type,
        @Nullable String elementType,
        @Nullable Object[] options,
        @Nullable String description
) {

    public static final class Fields {
        public static final String deprecated = "deprecated";
        public static final String global = "global";
        public static final String mutable = "mutable";
        public static final String sensitive = "sensitive";
        public static final String type = "type";
        public static final String elementType = "elementType";
        public static final String options = "options";
        public static final String description = "description";

        private Fields() {
        }
    }

}