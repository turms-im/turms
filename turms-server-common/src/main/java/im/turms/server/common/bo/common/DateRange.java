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

package im.turms.server.common.bo.common;

import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author James Chen
 */
@Data
public final class DateRange {

    private final Date start;
    private final Date end;

    private DateRange(Date start, Date end) {
        if (start != null && end != null && end.before(start)) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The end date must not be before the start date");
        }
        this.start = start;
        this.end = end;
    }

    public static DateRange of(Date start, Date end) {
        if (start != null || end != null) {
            return new DateRange(start, end);
        } else {
            return null;
        }
    }

    public DateRange intersect(DateRange range) {
        if (range == null || equals(range)) {
            return this;
        }
        return new DateRange(DateUtil.max(start, range.getStart()),
                DateUtil.min(end, range.getEnd()));
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
