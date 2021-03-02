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

package unit.im.turms.turms.bo;

import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.exception.TurmsBusinessException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author James Chen
 */
class DateRangeTests {

    @Test
    void of_shouldThrowException_whenPassingWrongParameters() {
        Date earlier = new Date(0);
        Date later = new Date(1);
        assertThrows(
                TurmsBusinessException.class,
                () -> DateRange.of(later, earlier));
    }

    @Test
    void of_shouldAssignValues_whenPassingCorrectParameters() {
        Date earlier = new Date(0);
        Date later = new Date(1);

        DateRange nullNull = DateRange.of(null, null);
        DateRange nullDate = DateRange.of(null, earlier);
        DateRange dateNull = DateRange.of(earlier, null);
        DateRange same = DateRange.of(earlier, earlier);
        DateRange earlierLater = DateRange.of(earlier, later);

        assertNull(nullNull);
        assertEquals(earlier, nullDate.getEnd());
        assertEquals(earlier, dateNull.getStart());
        assertEquals(earlier, dateNull.getStart());
        assertEquals(earlier, same.getStart());
        assertEquals(earlier, same.getEnd());
        assertEquals(earlier, earlierLater.getStart());
        assertEquals(later, earlierLater.getEnd());
    }

}
