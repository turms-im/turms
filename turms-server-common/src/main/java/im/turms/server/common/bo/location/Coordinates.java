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

package im.turms.server.common.bo.location;

/**
 * Note that though the first number is always the latitude and the second is the longitude,
 * e.g. "21.3069°, -157.8583°",
 * the geoadd command in Redis requires the longitude come firsts.
 */
public record Coordinates(
        // x, -180 to 180, West to East
        double longitude,
        // y, -85.05112878 to 85.05112878, South to North
        double latitude
) {

    // From: https://github.com/redis/redis/blob/unstable/src/geohash.h
    public static final double LONGITUDE_MIN = -180;
    public static final double LONGITUDE_MAX = 180;
    public static final double LATITUDE_MIN = -85.05112878;
    public static final double LATITUDE_MAX = 85.05112878;

}