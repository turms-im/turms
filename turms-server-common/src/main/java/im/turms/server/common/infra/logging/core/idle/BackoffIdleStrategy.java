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

package im.turms.server.common.infra.logging.core.idle;

import java.util.concurrent.locks.LockSupport;

/**
 * @author James Chen
 */
public final class BackoffIdleStrategy {

    private static final int WORKING = 0;
    private static final int SPINNING = 1;
    private static final int YIELDING = 2;
    private static final int PARKING = 3;

    private final long maxSpins;
    private final long maxYields;
    private final long minParkPeriodNs;
    private final long maxParkPeriodNs;

    private int state = WORKING;
    private long value;

    public BackoffIdleStrategy(
            long maxSpins,
            long maxYields,
            long minParkPeriodNs,
            long maxParkPeriodNs) {
        if (minParkPeriodNs < 1 || maxParkPeriodNs < minParkPeriodNs) {
            throw new IllegalArgumentException(
                    "The minimum park period ("
                            + minParkPeriodNs
                            + ") must be less than 1, "
                            + "and the maximum park ("
                            + maxParkPeriodNs
                            + ") period must less than the minimum park period");
        }
        this.maxSpins = maxSpins;
        this.maxYields = maxYields;
        this.minParkPeriodNs = minParkPeriodNs;
        this.maxParkPeriodNs = maxParkPeriodNs;
    }

    public void idle() {
        switch (state) {
            case WORKING:
                value = 0;
                state = SPINNING;
                // fallthrough

            case SPINNING:
                if (++value <= maxSpins) {
                    Thread.onSpinWait();
                    break;
                }
                value = 0;
                state = YIELDING;
                // fallthrough

            case YIELDING:
                if (++value <= maxYields) {
                    Thread.yield();
                    break;
                }
                value = minParkPeriodNs;
                state = PARKING;
                // fallthrough

            case PARKING:
                LockSupport.parkNanos(value);
                value = Math.min(value << 1, maxParkPeriodNs);
        }
    }

    public void reset() {
        state = WORKING;
    }

}