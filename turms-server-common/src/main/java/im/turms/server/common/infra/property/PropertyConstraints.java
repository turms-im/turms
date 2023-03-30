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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import im.turms.server.common.infra.validation.LessThanOrEqualTo;
import im.turms.server.common.infra.validation.ValidCron;

/**
 * @author James Chen
 */
public record PropertyConstraints(
        long min,
        long max,
        @Nullable String lessThanOrEqualTo,
        @Nullable Size size,
        @Nullable ValidCron validCron
) {

    public static final PropertyConstraints NULL =
            new PropertyConstraints(Long.MIN_VALUE, Long.MAX_VALUE, null, null, null);

    public static PropertyConstraints of(
            @Nullable Min min,
            @Nullable Max max,
            @Nullable LessThanOrEqualTo lessThanOrEqualTo,
            @Nullable Size size,
            @Nullable ValidCron validCron) {
        if (min == null
                && max == null
                && lessThanOrEqualTo == null
                && size == null
                && validCron == null) {
            return NULL;
        }
        return new PropertyConstraints(
                min == null
                        ? Long.MIN_VALUE
                        : min.value(),
                max == null
                        ? Long.MAX_VALUE
                        : max.value(),
                lessThanOrEqualTo == null
                        ? null
                        : lessThanOrEqualTo.value(),
                size,
                validCron);
    }

}
