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

package unit.im.turms.service.infra.time;

import java.util.Date;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.time.DateRange;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author James Chen
 */
class DateRangeTests {

    @Test
    void of_shouldThrowException_withInvalidParameters() {
        Date earlier = new Date(0);
        Date later = new Date(1);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> DateRange.of(later, earlier));
    }

    @Test
    void of_shouldAssignValues_withValidParameters() {
        Date earlier = new Date(0);
        Date later = new Date(1);

        DateRange nullNull = DateRange.of(null, null);
        DateRange nullDate = DateRange.of(null, earlier);
        DateRange dateNull = DateRange.of(earlier, null);
        DateRange same = DateRange.of(earlier, earlier);
        DateRange earlierLater = DateRange.of(earlier, later);

        assertThat(nullNull).isNull();
        assertThat(nullDate.end()).isEqualTo(earlier);
        assertThat(dateNull.start()).isEqualTo(earlier);
        assertThat(dateNull.end()).isNull();
        assertThat(same.start()).isEqualTo(earlier);
        assertThat(same.end()).isEqualTo(earlier);
        assertThat(earlierLater.start()).isEqualTo(earlier);
        assertThat(earlierLater.end()).isEqualTo(later);
    }

}
