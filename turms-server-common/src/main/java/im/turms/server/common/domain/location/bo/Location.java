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

package im.turms.server.common.domain.location.bo;

import java.util.Date;
import java.util.Map;
import jakarta.annotation.Nullable;

/**
 * Note that though the first number is always the latitude and the second is the longitude, e.g.
 * "21.3069°, -157.8583°", the geoadd command in Redis requires the longitude come firsts.
 */
public record Location(
        // x, -180 to 180, West to East
        float longitude,
        // y, -85.05112878 to 85.05112878, South to North
        float latitude,
        @Nullable Date timestamp,
        Map<String, String> details
) {

    // From: https://github.com/redis/redis/blob/unstable/src/geohash.h
    public static final float LONGITUDE_MIN = -180;
    public static final float LONGITUDE_MAX = 180;
    public static final float LATITUDE_MIN = (float) -85.05112878;
    public static final float LATITUDE_MAX = (float) 85.05112878;

    public Location(float longitude, float latitude) {
        this(longitude, latitude, null, null);
    }

}