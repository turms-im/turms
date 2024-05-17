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

package im.turms.plugin.livekit.core.proto.models;

public interface RTPDriftOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.RTPDrift)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.google.protobuf.Timestamp start_time = 1;</code>
     *
     * @return Whether the startTime field is set.
     */
    boolean hasStartTime();

    /**
     * <code>.google.protobuf.Timestamp start_time = 1;</code>
     *
     * @return The startTime.
     */
    com.google.protobuf.Timestamp getStartTime();

    /**
     * <code>.google.protobuf.Timestamp start_time = 1;</code>
     */
    com.google.protobuf.TimestampOrBuilder getStartTimeOrBuilder();

    /**
     * <code>.google.protobuf.Timestamp end_time = 2;</code>
     *
     * @return Whether the endTime field is set.
     */
    boolean hasEndTime();

    /**
     * <code>.google.protobuf.Timestamp end_time = 2;</code>
     *
     * @return The endTime.
     */
    com.google.protobuf.Timestamp getEndTime();

    /**
     * <code>.google.protobuf.Timestamp end_time = 2;</code>
     */
    com.google.protobuf.TimestampOrBuilder getEndTimeOrBuilder();

    /**
     * <code>double duration = 3;</code>
     *
     * @return The duration.
     */
    double getDuration();

    /**
     * <code>uint64 start_timestamp = 4;</code>
     *
     * @return The startTimestamp.
     */
    long getStartTimestamp();

    /**
     * <code>uint64 end_timestamp = 5;</code>
     *
     * @return The endTimestamp.
     */
    long getEndTimestamp();

    /**
     * <code>uint64 rtp_clock_ticks = 6;</code>
     *
     * @return The rtpClockTicks.
     */
    long getRtpClockTicks();

    /**
     * <code>int64 drift_samples = 7;</code>
     *
     * @return The driftSamples.
     */
    long getDriftSamples();

    /**
     * <code>double drift_ms = 8;</code>
     *
     * @return The driftMs.
     */
    double getDriftMs();

    /**
     * <code>double clock_rate = 9;</code>
     *
     * @return The clockRate.
     */
    double getClockRate();
}
