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

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.time.DivideBy;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class DateUtilTests {

    @Test
    void divideDuration() {
        Date earlierDate = Date.from(Instant.parse("2011-12-03T10:15:30Z"));
        Date laterDate = Date.from(Instant.parse("2012-06-03T10:15:30Z"));
        List<Pair<Date, Date>> hours =
                DateUtil.divideDuration(earlierDate, laterDate, DivideBy.HOUR);
        List<Pair<Date, Date>> days = DateUtil.divideDuration(earlierDate, laterDate, DivideBy.DAY);
        List<Pair<Date, Date>> months =
                DateUtil.divideDuration(earlierDate, laterDate, DivideBy.MONTH);

        assertThat(hours).hasSize(4392);
        assertThat(days).hasSize(183);
        assertThat(months).hasSize(6);

        hours = DateUtil.divideDuration(laterDate, earlierDate, DivideBy.HOUR);
        days = DateUtil.divideDuration(laterDate, earlierDate, DivideBy.DAY);
        months = DateUtil.divideDuration(laterDate, earlierDate, DivideBy.MONTH);

        assertThat(hours).isEmpty();
        assertThat(days).isEmpty();
        assertThat(months).isEmpty();

        hours = DateUtil.divideDuration(earlierDate, earlierDate, DivideBy.HOUR);
        days = DateUtil.divideDuration(earlierDate, earlierDate, DivideBy.DAY);
        months = DateUtil.divideDuration(earlierDate, earlierDate, DivideBy.MONTH);

        assertThat(hours).isEmpty();
        assertThat(days).isEmpty();
        assertThat(months).isEmpty();
    }

}
