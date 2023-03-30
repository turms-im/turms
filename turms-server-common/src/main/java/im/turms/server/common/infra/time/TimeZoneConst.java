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

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * @author James Chen
 */
public class TimeZoneConst {

    private TimeZoneConst() {
    }

    /**
     * turms servers always use the UTC time zone, and it is a bad practice to use other time zones
     * especially servers may be deployed in different countries, so we don't allow users to set
     * their own time zone.
     */
    public static final ZoneId ZONE_ID = ZoneOffset.UTC;
    public static final TimeZone ZONE = TimeZone.getTimeZone(ZONE_ID);

}
