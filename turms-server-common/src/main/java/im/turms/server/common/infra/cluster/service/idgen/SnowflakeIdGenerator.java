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

package im.turms.server.common.infra.cluster.service.idgen;

import java.util.concurrent.atomic.AtomicLong;

import im.turms.server.common.infra.random.RandomUtil;

/**
 * The flake ID is designed for turms. ID size: 64 bits.
 * <p>
 * 1 bit for the sign of ID. The most significant bit is always 0 to represent a positive number.
 * Reference:
 * https://stackoverflow.com/questions/8927761/why-is-negative-id-or-zero-considered-a-bad-practice
 * <p>
 * 41 bits for timestamp (69 years)
 * <p>
 * 4 bits for data center ID (16). A data center usually represents a region in cloud.
 * <p>
 * 8 bits for worker ID (256). Note turms-gateway also works as a load balancer to route traffic to
 * turms-service servers so the number of turms-service servers is better more than or equals to the
 * number of turms-gateway servers in practice. In other words, the max number that can be
 * represented by the bits of workerId should be better more than the number of turms-gateway
 * servers that you plan to deploy
 * <p>
 * 10 bits for sequenceNumber (1,024). It can represent up to 1024*1000 sequence numbers per second.
 *
 * @author James Chen
 */
public class SnowflakeIdGenerator {

    /**
     * 2020-10-13 00:00:00 in UTC
     */
    private static final long EPOCH = 1602547200000L;

    private static final int TIMESTAMP_BITS = 41;
    private static final int DATA_CENTER_ID_BITS = 4;
    private static final int WORKER_ID_BITS = 8;
    private static final int SEQUENCE_NUMBER_BITS = 10;

    private static final long TIMESTAMP_LEFT_SHIFT =
            SEQUENCE_NUMBER_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_NUMBER_BITS + WORKER_ID_BITS;
    private static final long WORKER_ID_SHIFT = SEQUENCE_NUMBER_BITS;

    private static final long SEQUENCE_NUMBER_MASK = (1 << SEQUENCE_NUMBER_BITS) - 1;

    public static final int MAX_DATA_CENTER_ID = 1 << DATA_CENTER_ID_BITS;
    public static final int MAX_WORKER_ID = 1 << WORKER_ID_BITS;

    // Used to ensure clock moves forward.
    private final AtomicLong lastTimestamp = new AtomicLong();

    // Because it is vulnerable if turms restarts after the clock goes backwards,
    // we randomize the sequenceNumber on init to decrease the chance of collision
    private final AtomicLong sequenceNumber = new AtomicLong(RandomUtil.nextPositiveInt());

    private long dataCenterId;
    private long workerId;

    public SnowflakeIdGenerator(int dataCenterId, int workerId) {
        updateNodeInfo(dataCenterId, workerId);
    }

    public void updateNodeInfo(int dataCenterId, int workerId) {
        if (dataCenterId >= MAX_DATA_CENTER_ID) {
            String reason = "The data center ID must be in the range: [0, "
                    + MAX_DATA_CENTER_ID
                    + "), but got: "
                    + dataCenterId;
            throw new IllegalArgumentException(reason);
        }
        if (workerId >= (1 << WORKER_ID_BITS)) {
            String reason = "The worker ID must be in the range: [0, "
                    + (1 << WORKER_ID_BITS)
                    + "), but got: "
                    + workerId;
            throw new IllegalArgumentException(reason);
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    public long nextIncreasingId() {
        // prepare each part of ID
        long sequenceNum = sequenceNumber.incrementAndGet() & SEQUENCE_NUMBER_MASK;
        long timestamp = lastTimestamp.updateAndGet(lastTs -> {
            // Don't let timestamp go backwards at least while this JVM is running.
            long nonBackwardsTimestamp = Math.max(lastTs, System.currentTimeMillis());
            if (sequenceNum == 0) {
                // Always force the clock to increment whenever sequence number is 0,
                // if we have a long time-slip backwards
                nonBackwardsTimestamp++;
            }
            return nonBackwardsTimestamp;
        }) - EPOCH;

        // Get ID
        return (timestamp << TIMESTAMP_LEFT_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT) | sequenceNum;
    }

    /**
     * 1. The purpose of generating IDs with large gaps is to generate shard keys in a large range
     * so that MongoDB can split them into a lot of chunks to distribute them to different MongoDB
     * servers 2. To avoid generating monotonical IDs to guarantee even distribution of data across
     * the sharded cluster
     */
    public long nextLargeGapId() {
        // prepare each part of ID
        long sequenceNum = sequenceNumber.incrementAndGet() & SEQUENCE_NUMBER_MASK;
        long timestamp = lastTimestamp.updateAndGet(now -> {
            // Don't let timestamp go backwards at least while this JVM is running.
            long nonBackwardsTimestamp = Math.max(now, System.currentTimeMillis());
            if (sequenceNum == 0) {
                // Always force the clock to increment whenever sequence number is 0,
                // in case we have a long time-slip backwards
                nonBackwardsTimestamp++;
            }
            return nonBackwardsTimestamp;
        }) - EPOCH;

        // Get ID
        return (sequenceNum << (TIMESTAMP_BITS + DATA_CENTER_ID_BITS + WORKER_ID_BITS))
                | (timestamp << (DATA_CENTER_ID_BITS + WORKER_ID_BITS))
                | (dataCenterId << WORKER_ID_BITS) | workerId;
    }

}