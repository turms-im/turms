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

public interface RTPStatsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.RTPStats)
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
     * <code>uint32 packets = 4;</code>
     *
     * @return The packets.
     */
    int getPackets();

    /**
     * <code>double packet_rate = 5;</code>
     *
     * @return The packetRate.
     */
    double getPacketRate();

    /**
     * <code>uint64 bytes = 6;</code>
     *
     * @return The bytes.
     */
    long getBytes();

    /**
     * <code>uint64 header_bytes = 39;</code>
     *
     * @return The headerBytes.
     */
    long getHeaderBytes();

    /**
     * <code>double bitrate = 7;</code>
     *
     * @return The bitrate.
     */
    double getBitrate();

    /**
     * <code>uint32 packets_lost = 8;</code>
     *
     * @return The packetsLost.
     */
    int getPacketsLost();

    /**
     * <code>double packet_loss_rate = 9;</code>
     *
     * @return The packetLossRate.
     */
    double getPacketLossRate();

    /**
     * <code>float packet_loss_percentage = 10;</code>
     *
     * @return The packetLossPercentage.
     */
    float getPacketLossPercentage();

    /**
     * <code>uint32 packets_duplicate = 11;</code>
     *
     * @return The packetsDuplicate.
     */
    int getPacketsDuplicate();

    /**
     * <code>double packet_duplicate_rate = 12;</code>
     *
     * @return The packetDuplicateRate.
     */
    double getPacketDuplicateRate();

    /**
     * <code>uint64 bytes_duplicate = 13;</code>
     *
     * @return The bytesDuplicate.
     */
    long getBytesDuplicate();

    /**
     * <code>uint64 header_bytes_duplicate = 40;</code>
     *
     * @return The headerBytesDuplicate.
     */
    long getHeaderBytesDuplicate();

    /**
     * <code>double bitrate_duplicate = 14;</code>
     *
     * @return The bitrateDuplicate.
     */
    double getBitrateDuplicate();

    /**
     * <code>uint32 packets_padding = 15;</code>
     *
     * @return The packetsPadding.
     */
    int getPacketsPadding();

    /**
     * <code>double packet_padding_rate = 16;</code>
     *
     * @return The packetPaddingRate.
     */
    double getPacketPaddingRate();

    /**
     * <code>uint64 bytes_padding = 17;</code>
     *
     * @return The bytesPadding.
     */
    long getBytesPadding();

    /**
     * <code>uint64 header_bytes_padding = 41;</code>
     *
     * @return The headerBytesPadding.
     */
    long getHeaderBytesPadding();

    /**
     * <code>double bitrate_padding = 18;</code>
     *
     * @return The bitratePadding.
     */
    double getBitratePadding();

    /**
     * <code>uint32 packets_out_of_order = 19;</code>
     *
     * @return The packetsOutOfOrder.
     */
    int getPacketsOutOfOrder();

    /**
     * <code>uint32 frames = 20;</code>
     *
     * @return The frames.
     */
    int getFrames();

    /**
     * <code>double frame_rate = 21;</code>
     *
     * @return The frameRate.
     */
    double getFrameRate();

    /**
     * <code>double jitter_current = 22;</code>
     *
     * @return The jitterCurrent.
     */
    double getJitterCurrent();

    /**
     * <code>double jitter_max = 23;</code>
     *
     * @return The jitterMax.
     */
    double getJitterMax();

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    int getGapHistogramCount();

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    boolean containsGapHistogram(int key);

    /**
     * Use {@link #getGapHistogramMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.Integer, java.lang.Integer> getGapHistogram();

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    java.util.Map<java.lang.Integer, java.lang.Integer> getGapHistogramMap();

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    int getGapHistogramOrDefault(int key, int defaultValue);

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    int getGapHistogramOrThrow(int key);

    /**
     * <code>uint32 nacks = 25;</code>
     *
     * @return The nacks.
     */
    int getNacks();

    /**
     * <code>uint32 nack_acks = 37;</code>
     *
     * @return The nackAcks.
     */
    int getNackAcks();

    /**
     * <code>uint32 nack_misses = 26;</code>
     *
     * @return The nackMisses.
     */
    int getNackMisses();

    /**
     * <code>uint32 nack_repeated = 38;</code>
     *
     * @return The nackRepeated.
     */
    int getNackRepeated();

    /**
     * <code>uint32 plis = 27;</code>
     *
     * @return The plis.
     */
    int getPlis();

    /**
     * <code>.google.protobuf.Timestamp last_pli = 28;</code>
     *
     * @return Whether the lastPli field is set.
     */
    boolean hasLastPli();

    /**
     * <code>.google.protobuf.Timestamp last_pli = 28;</code>
     *
     * @return The lastPli.
     */
    com.google.protobuf.Timestamp getLastPli();

    /**
     * <code>.google.protobuf.Timestamp last_pli = 28;</code>
     */
    com.google.protobuf.TimestampOrBuilder getLastPliOrBuilder();

    /**
     * <code>uint32 firs = 29;</code>
     *
     * @return The firs.
     */
    int getFirs();

    /**
     * <code>.google.protobuf.Timestamp last_fir = 30;</code>
     *
     * @return Whether the lastFir field is set.
     */
    boolean hasLastFir();

    /**
     * <code>.google.protobuf.Timestamp last_fir = 30;</code>
     *
     * @return The lastFir.
     */
    com.google.protobuf.Timestamp getLastFir();

    /**
     * <code>.google.protobuf.Timestamp last_fir = 30;</code>
     */
    com.google.protobuf.TimestampOrBuilder getLastFirOrBuilder();

    /**
     * <code>uint32 rtt_current = 31;</code>
     *
     * @return The rttCurrent.
     */
    int getRttCurrent();

    /**
     * <code>uint32 rtt_max = 32;</code>
     *
     * @return The rttMax.
     */
    int getRttMax();

    /**
     * <code>uint32 key_frames = 33;</code>
     *
     * @return The keyFrames.
     */
    int getKeyFrames();

    /**
     * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
     *
     * @return Whether the lastKeyFrame field is set.
     */
    boolean hasLastKeyFrame();

    /**
     * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
     *
     * @return The lastKeyFrame.
     */
    com.google.protobuf.Timestamp getLastKeyFrame();

    /**
     * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
     */
    com.google.protobuf.TimestampOrBuilder getLastKeyFrameOrBuilder();

    /**
     * <code>uint32 layer_lock_plis = 35;</code>
     *
     * @return The layerLockPlis.
     */
    int getLayerLockPlis();

    /**
     * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
     *
     * @return Whether the lastLayerLockPli field is set.
     */
    boolean hasLastLayerLockPli();

    /**
     * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
     *
     * @return The lastLayerLockPli.
     */
    com.google.protobuf.Timestamp getLastLayerLockPli();

    /**
     * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
     */
    com.google.protobuf.TimestampOrBuilder getLastLayerLockPliOrBuilder();

    /**
     * <code>.livekit.RTPDrift packet_drift = 44;</code>
     *
     * @return Whether the packetDrift field is set.
     */
    boolean hasPacketDrift();

    /**
     * <code>.livekit.RTPDrift packet_drift = 44;</code>
     *
     * @return The packetDrift.
     */
    im.turms.plugin.livekit.core.proto.models.RTPDrift getPacketDrift();

    /**
     * <code>.livekit.RTPDrift packet_drift = 44;</code>
     */
    im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getPacketDriftOrBuilder();

    /**
     * <code>.livekit.RTPDrift report_drift = 45;</code>
     *
     * @return Whether the reportDrift field is set.
     */
    boolean hasReportDrift();

    /**
     * <code>.livekit.RTPDrift report_drift = 45;</code>
     *
     * @return The reportDrift.
     */
    im.turms.plugin.livekit.core.proto.models.RTPDrift getReportDrift();

    /**
     * <code>.livekit.RTPDrift report_drift = 45;</code>
     */
    im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getReportDriftOrBuilder();

    /**
     * <pre>
     * NEXT_ID: 47
     * </pre>
     *
     * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
     *
     * @return Whether the rebasedReportDrift field is set.
     */
    boolean hasRebasedReportDrift();

    /**
     * <pre>
     * NEXT_ID: 47
     * </pre>
     *
     * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
     *
     * @return The rebasedReportDrift.
     */
    im.turms.plugin.livekit.core.proto.models.RTPDrift getRebasedReportDrift();

    /**
     * <pre>
     * NEXT_ID: 47
     * </pre>
     *
     * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
     */
    im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getRebasedReportDriftOrBuilder();
}
