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

package im.turms.server.common.infra.time;

import java.util.Date;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
public record DateRange(
        Date start,
        Date end
) {

    public static final DateRange NULL = new DateRange(null, null);

    public DateRange {
        if (start != null && end != null && end.before(start)) {
            throw new IllegalArgumentException("The end date must not be before the start date");
        }
    }

    public static DateRange of(Date start, Date end) {
        return start == null && end == null
                ? null
                : new DateRange(start, end);
    }

    public DateRange intersect(DateRange range) {
        return range == null || equals(range)
                ? this
                : new DateRange(DateUtil.max(start, range.start()), DateUtil.min(end, range.end()));
    }

    public DateRange move(long millis) {
        Date newStart = start == null
                ? null
                : new Date(start.getTime() + millis);
        Date newEnd = end == null
                ? null
                : new Date(end.getTime() + millis);
        return new DateRange(newStart, newEnd);
    }

}
