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

package im.turms.server.common.infra.metrics;

import java.lang.invoke.MethodHandle;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;

import im.turms.server.common.infra.reflect.ReflectionUtil;

/**
 * @author James Chen
 */
public final class MetricsUtil {

    private static final MethodHandle GET_TAGS = ReflectionUtil.getGetter(Tags.class, "tags");

    private MetricsUtil() {
    }

    public static Tag[] getTags(Meter.Id id) {
        Tags tags = (Tags) id.getTagsAsIterable();
        try {
            return (Tag[]) GET_TAGS.invokeExact(tags);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to get tags", e);
        }
    }

}