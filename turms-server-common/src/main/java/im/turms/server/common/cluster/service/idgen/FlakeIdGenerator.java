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

package im.turms.server.common.cluster.service.idgen;


import im.turms.common.util.RandomUtil;
import jdk.internal.vm.annotation.Contended;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The flake ID is designed for turms.
 * <p>
 * 64 bits:
 * <p>
 * 1 bit for positive ID.
 * Reference: https://stackoverflow.com/questions/8927761/why-is-negative-id-or-zero-considered-a-bad-practice
 * <p>
 * 41 bits for timestamp (69 years)
 * <p>
 * 3 bits for data center ID (8). Reserved for future.
 * <p>
 * 8 bits for memberId (256)
 * <p>
 * 11 bits for sequenceNumber (2,048)
 *
 * @author James Chen
 */
@Log4j2
public class FlakeIdGenerator {

    private static final int DATA_CENTER_ID_BITS = 3;
    private static final int MEMBER_ID_BITS = 8;
    private static final int SEQUENCE_NUMBER_BITS = 11;

    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_NUMBER_BITS + MEMBER_ID_BITS + DATA_CENTER_ID_BITS;
    private static final int DATA_CENTER_ID_SHIFT = SEQUENCE_NUMBER_BITS + MEMBER_ID_BITS;
    private static final int MEMBER_ID_SHIFT = SEQUENCE_NUMBER_BITS;

    private static final int SEQUENCE_NUMBER_MASK = (1 << SEQUENCE_NUMBER_BITS) - 1;

    // Used to ensure clock moves forward.
    private final AtomicLong lastTimestamp = new AtomicLong();

    // Because it's vulnerable if turms restarts after the clock goes backwards,
    // we randomize the sequenceNumber on init to decrease chance of collision
    private final AtomicInteger sequenceNumber = new AtomicInteger(RandomUtil.nextPositiveInt());

    @Contended("nodeInfo")
    private int dataCenterId;

    @Contended("nodeInfo")
    private int memberId;

    public FlakeIdGenerator(int dataCenterId, int memberId) {
        updateNodeInfo(dataCenterId, memberId);
    }

    public void updateNodeInfo(int dataCenterId, int memberId) {
        this.dataCenterId = dataCenterId;
        this.memberId = memberId;
    }

    public long getFlakeId() {
        // prepare each part of ID
        int sequenceId = sequenceNumber.incrementAndGet() & SEQUENCE_NUMBER_MASK;
        long timestamp = this.lastTimestamp.updateAndGet(lastTimestamp -> {
            // Don't let timestamp go backwards at least while this JVM is running.
            long nonBackwardsTimestamp = Math.max(lastTimestamp, System.currentTimeMillis());
            if (sequenceId == 0) {
                // Always force the clock to increment whenever sequence number is 0, in case we have a long
                // time-slip backwards
                nonBackwardsTimestamp++;
            }
            return nonBackwardsTimestamp;
        });

        // Get ID
        return sequenceId
                | (memberId << MEMBER_ID_SHIFT)
                | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (timestamp << TIMESTAMP_LEFT_SHIFT);
    }

}