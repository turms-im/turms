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

package unit.im.turms.server.common.infra.time;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.time.DateUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class DateUtilTests {

    @Test
    void test() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                .withZone(ZoneOffset.UTC);
        List<String> dateTimes = List.of("1970-01-01 00:00:00.000",
                "1970-01-01 00:00:00.001",
                "1970-01-01 00:00:01.001",
                "1970-01-01 00:01:01.001",
                "1970-01-01 01:01:01.001",
                "1970-01-02 01:01:01.001",
                "1970-02-02 01:01:01.001",
                "1971-02-02 01:01:01.001",
                "2099-01-01 01:01:01.001",
                "2099-12-31 01:01:01.001");
        dateTimes.forEach(dateTime -> {
            long millis = formatter.parse(dateTime, Instant::from)
                    .toEpochMilli();
            String actual = DateUtil.toStr(millis);
            assertThat(actual).isEqualTo(dateTime);
        });
    }

}
