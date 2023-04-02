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

package im.turms.server.common.access.admin.web;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author James Chen
 */
public record MethodParameterInfo(
        String name,
        Class<?> type,
        @Nullable JavaType typeForJackson,
        @Nullable Class<?> elementType,
        @Nullable JavaType elementTypeForJackson,
        boolean isRequired,
        boolean isHeader,
        boolean isBody,
        boolean isFormData,
        @Nullable Object defaultValue,
        @Nullable String contentType,
        boolean isVisibleForOpenApi
) {
}