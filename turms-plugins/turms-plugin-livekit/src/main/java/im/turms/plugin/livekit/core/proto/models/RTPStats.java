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

/**
 * Protobuf type {@code livekit.RTPStats}
 */
public final class RTPStats extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.RTPStats)
        RTPStatsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                RTPStats.class.getName());
    }

    // Use RTPStats.newBuilder() to construct.
    private RTPStats(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private RTPStats() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPStats_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        return switch (number) {
            case 24 -> internalGetGapHistogram();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPStats_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.RTPStats.class,
                        im.turms.plugin.livekit.core.proto.models.RTPStats.Builder.class);
    }

    private int bitField0_;
    public static final int START_TIME_FIELD_NUMBER = 1;
    private com.google.protobuf.Timestamp startTime_;

    /**
     * <code>.google.protobuf.Timestamp start_time = 1;</code>
     *
     * @return Whether the startTime field is set.
     */
    @java.lang.Override
    public boolean hasStartTime() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.google.protobuf.Timestamp start_time = 1;</code>
     *
     * @return The startTime.
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getStartTime() {
        return startTime_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : startTime_;
    }

    /**
     * <code>.google.protobuf.Timestamp start_time = 1;</code>
     */
    @java.lang.Override
    public com.google.protobuf.TimestampOrBuilder getStartTimeOrBuilder() {
        return startTime_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : startTime_;
    }

    public static final int END_TIME_FIELD_NUMBER = 2;
    private com.google.protobuf.Timestamp endTime_;

    /**
     * <code>.google.protobuf.Timestamp end_time = 2;</code>
     *
     * @return Whether the endTime field is set.
     */
    @java.lang.Override
    public boolean hasEndTime() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>.google.protobuf.Timestamp end_time = 2;</code>
     *
     * @return The endTime.
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getEndTime() {
        return endTime_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : endTime_;
    }

    /**
     * <code>.google.protobuf.Timestamp end_time = 2;</code>
     */
    @java.lang.Override
    public com.google.protobuf.TimestampOrBuilder getEndTimeOrBuilder() {
        return endTime_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : endTime_;
    }

    public static final int DURATION_FIELD_NUMBER = 3;
    private double duration_ = 0D;

    /**
     * <code>double duration = 3;</code>
     *
     * @return The duration.
     */
    @java.lang.Override
    public double getDuration() {
        return duration_;
    }

    public static final int PACKETS_FIELD_NUMBER = 4;
    private int packets_ = 0;

    /**
     * <code>uint32 packets = 4;</code>
     *
     * @return The packets.
     */
    @java.lang.Override
    public int getPackets() {
        return packets_;
    }

    public static final int PACKET_RATE_FIELD_NUMBER = 5;
    private double packetRate_ = 0D;

    /**
     * <code>double packet_rate = 5;</code>
     *
     * @return The packetRate.
     */
    @java.lang.Override
    public double getPacketRate() {
        return packetRate_;
    }

    public static final int BYTES_FIELD_NUMBER = 6;
    private long bytes_ = 0L;

    /**
     * <code>uint64 bytes = 6;</code>
     *
     * @return The bytes.
     */
    @java.lang.Override
    public long getBytes() {
        return bytes_;
    }

    public static final int HEADER_BYTES_FIELD_NUMBER = 39;
    private long headerBytes_ = 0L;

    /**
     * <code>uint64 header_bytes = 39;</code>
     *
     * @return The headerBytes.
     */
    @java.lang.Override
    public long getHeaderBytes() {
        return headerBytes_;
    }

    public static final int BITRATE_FIELD_NUMBER = 7;
    private double bitrate_ = 0D;

    /**
     * <code>double bitrate = 7;</code>
     *
     * @return The bitrate.
     */
    @java.lang.Override
    public double getBitrate() {
        return bitrate_;
    }

    public static final int PACKETS_LOST_FIELD_NUMBER = 8;
    private int packetsLost_ = 0;

    /**
     * <code>uint32 packets_lost = 8;</code>
     *
     * @return The packetsLost.
     */
    @java.lang.Override
    public int getPacketsLost() {
        return packetsLost_;
    }

    public static final int PACKET_LOSS_RATE_FIELD_NUMBER = 9;
    private double packetLossRate_ = 0D;

    /**
     * <code>double packet_loss_rate = 9;</code>
     *
     * @return The packetLossRate.
     */
    @java.lang.Override
    public double getPacketLossRate() {
        return packetLossRate_;
    }

    public static final int PACKET_LOSS_PERCENTAGE_FIELD_NUMBER = 10;
    private float packetLossPercentage_ = 0F;

    /**
     * <code>float packet_loss_percentage = 10;</code>
     *
     * @return The packetLossPercentage.
     */
    @java.lang.Override
    public float getPacketLossPercentage() {
        return packetLossPercentage_;
    }

    public static final int PACKETS_DUPLICATE_FIELD_NUMBER = 11;
    private int packetsDuplicate_ = 0;

    /**
     * <code>uint32 packets_duplicate = 11;</code>
     *
     * @return The packetsDuplicate.
     */
    @java.lang.Override
    public int getPacketsDuplicate() {
        return packetsDuplicate_;
    }

    public static final int PACKET_DUPLICATE_RATE_FIELD_NUMBER = 12;
    private double packetDuplicateRate_ = 0D;

    /**
     * <code>double packet_duplicate_rate = 12;</code>
     *
     * @return The packetDuplicateRate.
     */
    @java.lang.Override
    public double getPacketDuplicateRate() {
        return packetDuplicateRate_;
    }

    public static final int BYTES_DUPLICATE_FIELD_NUMBER = 13;
    private long bytesDuplicate_ = 0L;

    /**
     * <code>uint64 bytes_duplicate = 13;</code>
     *
     * @return The bytesDuplicate.
     */
    @java.lang.Override
    public long getBytesDuplicate() {
        return bytesDuplicate_;
    }

    public static final int HEADER_BYTES_DUPLICATE_FIELD_NUMBER = 40;
    private long headerBytesDuplicate_ = 0L;

    /**
     * <code>uint64 header_bytes_duplicate = 40;</code>
     *
     * @return The headerBytesDuplicate.
     */
    @java.lang.Override
    public long getHeaderBytesDuplicate() {
        return headerBytesDuplicate_;
    }

    public static final int BITRATE_DUPLICATE_FIELD_NUMBER = 14;
    private double bitrateDuplicate_ = 0D;

    /**
     * <code>double bitrate_duplicate = 14;</code>
     *
     * @return The bitrateDuplicate.
     */
    @java.lang.Override
    public double getBitrateDuplicate() {
        return bitrateDuplicate_;
    }

    public static final int PACKETS_PADDING_FIELD_NUMBER = 15;
    private int packetsPadding_ = 0;

    /**
     * <code>uint32 packets_padding = 15;</code>
     *
     * @return The packetsPadding.
     */
    @java.lang.Override
    public int getPacketsPadding() {
        return packetsPadding_;
    }

    public static final int PACKET_PADDING_RATE_FIELD_NUMBER = 16;
    private double packetPaddingRate_ = 0D;

    /**
     * <code>double packet_padding_rate = 16;</code>
     *
     * @return The packetPaddingRate.
     */
    @java.lang.Override
    public double getPacketPaddingRate() {
        return packetPaddingRate_;
    }

    public static final int BYTES_PADDING_FIELD_NUMBER = 17;
    private long bytesPadding_ = 0L;

    /**
     * <code>uint64 bytes_padding = 17;</code>
     *
     * @return The bytesPadding.
     */
    @java.lang.Override
    public long getBytesPadding() {
        return bytesPadding_;
    }

    public static final int HEADER_BYTES_PADDING_FIELD_NUMBER = 41;
    private long headerBytesPadding_ = 0L;

    /**
     * <code>uint64 header_bytes_padding = 41;</code>
     *
     * @return The headerBytesPadding.
     */
    @java.lang.Override
    public long getHeaderBytesPadding() {
        return headerBytesPadding_;
    }

    public static final int BITRATE_PADDING_FIELD_NUMBER = 18;
    private double bitratePadding_ = 0D;

    /**
     * <code>double bitrate_padding = 18;</code>
     *
     * @return The bitratePadding.
     */
    @java.lang.Override
    public double getBitratePadding() {
        return bitratePadding_;
    }

    public static final int PACKETS_OUT_OF_ORDER_FIELD_NUMBER = 19;
    private int packetsOutOfOrder_ = 0;

    /**
     * <code>uint32 packets_out_of_order = 19;</code>
     *
     * @return The packetsOutOfOrder.
     */
    @java.lang.Override
    public int getPacketsOutOfOrder() {
        return packetsOutOfOrder_;
    }

    public static final int FRAMES_FIELD_NUMBER = 20;
    private int frames_ = 0;

    /**
     * <code>uint32 frames = 20;</code>
     *
     * @return The frames.
     */
    @java.lang.Override
    public int getFrames() {
        return frames_;
    }

    public static final int FRAME_RATE_FIELD_NUMBER = 21;
    private double frameRate_ = 0D;

    /**
     * <code>double frame_rate = 21;</code>
     *
     * @return The frameRate.
     */
    @java.lang.Override
    public double getFrameRate() {
        return frameRate_;
    }

    public static final int JITTER_CURRENT_FIELD_NUMBER = 22;
    private double jitterCurrent_ = 0D;

    /**
     * <code>double jitter_current = 22;</code>
     *
     * @return The jitterCurrent.
     */
    @java.lang.Override
    public double getJitterCurrent() {
        return jitterCurrent_;
    }

    public static final int JITTER_MAX_FIELD_NUMBER = 23;
    private double jitterMax_ = 0D;

    /**
     * <code>double jitter_max = 23;</code>
     *
     * @return The jitterMax.
     */
    @java.lang.Override
    public double getJitterMax() {
        return jitterMax_;
    }

    public static final int GAP_HISTOGRAM_FIELD_NUMBER = 24;

    private static final class GapHistogramDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.Integer, java.lang.Integer> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPStats_GapHistogramEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.INT32,
                        0,
                        com.google.protobuf.WireFormat.FieldType.UINT32,
                        0);
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.Integer, java.lang.Integer> gapHistogram_;

    private com.google.protobuf.MapField<java.lang.Integer, java.lang.Integer> internalGetGapHistogram() {
        if (gapHistogram_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(GapHistogramDefaultEntryHolder.defaultEntry);
        }
        return gapHistogram_;
    }

    public int getGapHistogramCount() {
        return internalGetGapHistogram().getMap()
                .size();
    }

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    @java.lang.Override
    public boolean containsGapHistogram(int key) {

        return internalGetGapHistogram().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getGapHistogramMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.Integer, java.lang.Integer> getGapHistogram() {
        return getGapHistogramMap();
    }

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.Integer, java.lang.Integer> getGapHistogramMap() {
        return internalGetGapHistogram().getMap();
    }

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    @java.lang.Override
    public int getGapHistogramOrDefault(int key, int defaultValue) {

        java.util.Map<java.lang.Integer, java.lang.Integer> map =
                internalGetGapHistogram().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
     */
    @java.lang.Override
    public int getGapHistogramOrThrow(int key) {

        java.util.Map<java.lang.Integer, java.lang.Integer> map =
                internalGetGapHistogram().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    public static final int NACKS_FIELD_NUMBER = 25;
    private int nacks_ = 0;

    /**
     * <code>uint32 nacks = 25;</code>
     *
     * @return The nacks.
     */
    @java.lang.Override
    public int getNacks() {
        return nacks_;
    }

    public static final int NACK_ACKS_FIELD_NUMBER = 37;
    private int nackAcks_ = 0;

    /**
     * <code>uint32 nack_acks = 37;</code>
     *
     * @return The nackAcks.
     */
    @java.lang.Override
    public int getNackAcks() {
        return nackAcks_;
    }

    public static final int NACK_MISSES_FIELD_NUMBER = 26;
    private int nackMisses_ = 0;

    /**
     * <code>uint32 nack_misses = 26;</code>
     *
     * @return The nackMisses.
     */
    @java.lang.Override
    public int getNackMisses() {
        return nackMisses_;
    }

    public static final int NACK_REPEATED_FIELD_NUMBER = 38;
    private int nackRepeated_ = 0;

    /**
     * <code>uint32 nack_repeated = 38;</code>
     *
     * @return The nackRepeated.
     */
    @java.lang.Override
    public int getNackRepeated() {
        return nackRepeated_;
    }

    public static final int PLIS_FIELD_NUMBER = 27;
    private int plis_ = 0;

    /**
     * <code>uint32 plis = 27;</code>
     *
     * @return The plis.
     */
    @java.lang.Override
    public int getPlis() {
        return plis_;
    }

    public static final int LAST_PLI_FIELD_NUMBER = 28;
    private com.google.protobuf.Timestamp lastPli_;

    /**
     * <code>.google.protobuf.Timestamp last_pli = 28;</code>
     *
     * @return Whether the lastPli field is set.
     */
    @java.lang.Override
    public boolean hasLastPli() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>.google.protobuf.Timestamp last_pli = 28;</code>
     *
     * @return The lastPli.
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getLastPli() {
        return lastPli_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastPli_;
    }

    /**
     * <code>.google.protobuf.Timestamp last_pli = 28;</code>
     */
    @java.lang.Override
    public com.google.protobuf.TimestampOrBuilder getLastPliOrBuilder() {
        return lastPli_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastPli_;
    }

    public static final int FIRS_FIELD_NUMBER = 29;
    private int firs_ = 0;

    /**
     * <code>uint32 firs = 29;</code>
     *
     * @return The firs.
     */
    @java.lang.Override
    public int getFirs() {
        return firs_;
    }

    public static final int LAST_FIR_FIELD_NUMBER = 30;
    private com.google.protobuf.Timestamp lastFir_;

    /**
     * <code>.google.protobuf.Timestamp last_fir = 30;</code>
     *
     * @return Whether the lastFir field is set.
     */
    @java.lang.Override
    public boolean hasLastFir() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>.google.protobuf.Timestamp last_fir = 30;</code>
     *
     * @return The lastFir.
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getLastFir() {
        return lastFir_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastFir_;
    }

    /**
     * <code>.google.protobuf.Timestamp last_fir = 30;</code>
     */
    @java.lang.Override
    public com.google.protobuf.TimestampOrBuilder getLastFirOrBuilder() {
        return lastFir_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastFir_;
    }

    public static final int RTT_CURRENT_FIELD_NUMBER = 31;
    private int rttCurrent_ = 0;

    /**
     * <code>uint32 rtt_current = 31;</code>
     *
     * @return The rttCurrent.
     */
    @java.lang.Override
    public int getRttCurrent() {
        return rttCurrent_;
    }

    public static final int RTT_MAX_FIELD_NUMBER = 32;
    private int rttMax_ = 0;

    /**
     * <code>uint32 rtt_max = 32;</code>
     *
     * @return The rttMax.
     */
    @java.lang.Override
    public int getRttMax() {
        return rttMax_;
    }

    public static final int KEY_FRAMES_FIELD_NUMBER = 33;
    private int keyFrames_ = 0;

    /**
     * <code>uint32 key_frames = 33;</code>
     *
     * @return The keyFrames.
     */
    @java.lang.Override
    public int getKeyFrames() {
        return keyFrames_;
    }

    public static final int LAST_KEY_FRAME_FIELD_NUMBER = 34;
    private com.google.protobuf.Timestamp lastKeyFrame_;

    /**
     * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
     *
     * @return Whether the lastKeyFrame field is set.
     */
    @java.lang.Override
    public boolean hasLastKeyFrame() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
     *
     * @return The lastKeyFrame.
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getLastKeyFrame() {
        return lastKeyFrame_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastKeyFrame_;
    }

    /**
     * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
     */
    @java.lang.Override
    public com.google.protobuf.TimestampOrBuilder getLastKeyFrameOrBuilder() {
        return lastKeyFrame_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastKeyFrame_;
    }

    public static final int LAYER_LOCK_PLIS_FIELD_NUMBER = 35;
    private int layerLockPlis_ = 0;

    /**
     * <code>uint32 layer_lock_plis = 35;</code>
     *
     * @return The layerLockPlis.
     */
    @java.lang.Override
    public int getLayerLockPlis() {
        return layerLockPlis_;
    }

    public static final int LAST_LAYER_LOCK_PLI_FIELD_NUMBER = 36;
    private com.google.protobuf.Timestamp lastLayerLockPli_;

    /**
     * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
     *
     * @return Whether the lastLayerLockPli field is set.
     */
    @java.lang.Override
    public boolean hasLastLayerLockPli() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
     *
     * @return The lastLayerLockPli.
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getLastLayerLockPli() {
        return lastLayerLockPli_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastLayerLockPli_;
    }

    /**
     * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
     */
    @java.lang.Override
    public com.google.protobuf.TimestampOrBuilder getLastLayerLockPliOrBuilder() {
        return lastLayerLockPli_ == null
                ? com.google.protobuf.Timestamp.getDefaultInstance()
                : lastLayerLockPli_;
    }

    public static final int PACKET_DRIFT_FIELD_NUMBER = 44;
    private im.turms.plugin.livekit.core.proto.models.RTPDrift packetDrift_;

    /**
     * <code>.livekit.RTPDrift packet_drift = 44;</code>
     *
     * @return Whether the packetDrift field is set.
     */
    @java.lang.Override
    public boolean hasPacketDrift() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>.livekit.RTPDrift packet_drift = 44;</code>
     *
     * @return The packetDrift.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDrift getPacketDrift() {
        return packetDrift_ == null
                ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                : packetDrift_;
    }

    /**
     * <code>.livekit.RTPDrift packet_drift = 44;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getPacketDriftOrBuilder() {
        return packetDrift_ == null
                ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                : packetDrift_;
    }

    public static final int REPORT_DRIFT_FIELD_NUMBER = 45;
    private im.turms.plugin.livekit.core.proto.models.RTPDrift reportDrift_;

    /**
     * <code>.livekit.RTPDrift report_drift = 45;</code>
     *
     * @return Whether the reportDrift field is set.
     */
    @java.lang.Override
    public boolean hasReportDrift() {
        return ((bitField0_ & 0x00000080) != 0);
    }

    /**
     * <code>.livekit.RTPDrift report_drift = 45;</code>
     *
     * @return The reportDrift.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDrift getReportDrift() {
        return reportDrift_ == null
                ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                : reportDrift_;
    }

    /**
     * <code>.livekit.RTPDrift report_drift = 45;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getReportDriftOrBuilder() {
        return reportDrift_ == null
                ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                : reportDrift_;
    }

    public static final int REBASED_REPORT_DRIFT_FIELD_NUMBER = 46;
    private im.turms.plugin.livekit.core.proto.models.RTPDrift rebasedReportDrift_;

    /**
     * <pre>
     * NEXT_ID: 47
     * </pre>
     *
     * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
     *
     * @return Whether the rebasedReportDrift field is set.
     */
    @java.lang.Override
    public boolean hasRebasedReportDrift() {
        return ((bitField0_ & 0x00000100) != 0);
    }

    /**
     * <pre>
     * NEXT_ID: 47
     * </pre>
     *
     * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
     *
     * @return The rebasedReportDrift.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDrift getRebasedReportDrift() {
        return rebasedReportDrift_ == null
                ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                : rebasedReportDrift_;
    }

    /**
     * <pre>
     * NEXT_ID: 47
     * </pre>
     *
     * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getRebasedReportDriftOrBuilder() {
        return rebasedReportDrift_ == null
                ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                : rebasedReportDrift_;
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(1, getStartTime());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(2, getEndTime());
        }
        if (java.lang.Double.doubleToRawLongBits(duration_) != 0) {
            output.writeDouble(3, duration_);
        }
        if (packets_ != 0) {
            output.writeUInt32(4, packets_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetRate_) != 0) {
            output.writeDouble(5, packetRate_);
        }
        if (bytes_ != 0L) {
            output.writeUInt64(6, bytes_);
        }
        if (java.lang.Double.doubleToRawLongBits(bitrate_) != 0) {
            output.writeDouble(7, bitrate_);
        }
        if (packetsLost_ != 0) {
            output.writeUInt32(8, packetsLost_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetLossRate_) != 0) {
            output.writeDouble(9, packetLossRate_);
        }
        if (java.lang.Float.floatToRawIntBits(packetLossPercentage_) != 0) {
            output.writeFloat(10, packetLossPercentage_);
        }
        if (packetsDuplicate_ != 0) {
            output.writeUInt32(11, packetsDuplicate_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetDuplicateRate_) != 0) {
            output.writeDouble(12, packetDuplicateRate_);
        }
        if (bytesDuplicate_ != 0L) {
            output.writeUInt64(13, bytesDuplicate_);
        }
        if (java.lang.Double.doubleToRawLongBits(bitrateDuplicate_) != 0) {
            output.writeDouble(14, bitrateDuplicate_);
        }
        if (packetsPadding_ != 0) {
            output.writeUInt32(15, packetsPadding_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetPaddingRate_) != 0) {
            output.writeDouble(16, packetPaddingRate_);
        }
        if (bytesPadding_ != 0L) {
            output.writeUInt64(17, bytesPadding_);
        }
        if (java.lang.Double.doubleToRawLongBits(bitratePadding_) != 0) {
            output.writeDouble(18, bitratePadding_);
        }
        if (packetsOutOfOrder_ != 0) {
            output.writeUInt32(19, packetsOutOfOrder_);
        }
        if (frames_ != 0) {
            output.writeUInt32(20, frames_);
        }
        if (java.lang.Double.doubleToRawLongBits(frameRate_) != 0) {
            output.writeDouble(21, frameRate_);
        }
        if (java.lang.Double.doubleToRawLongBits(jitterCurrent_) != 0) {
            output.writeDouble(22, jitterCurrent_);
        }
        if (java.lang.Double.doubleToRawLongBits(jitterMax_) != 0) {
            output.writeDouble(23, jitterMax_);
        }
        com.google.protobuf.GeneratedMessage.serializeIntegerMapTo(output,
                internalGetGapHistogram(),
                GapHistogramDefaultEntryHolder.defaultEntry,
                24);
        if (nacks_ != 0) {
            output.writeUInt32(25, nacks_);
        }
        if (nackMisses_ != 0) {
            output.writeUInt32(26, nackMisses_);
        }
        if (plis_ != 0) {
            output.writeUInt32(27, plis_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(28, getLastPli());
        }
        if (firs_ != 0) {
            output.writeUInt32(29, firs_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeMessage(30, getLastFir());
        }
        if (rttCurrent_ != 0) {
            output.writeUInt32(31, rttCurrent_);
        }
        if (rttMax_ != 0) {
            output.writeUInt32(32, rttMax_);
        }
        if (keyFrames_ != 0) {
            output.writeUInt32(33, keyFrames_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeMessage(34, getLastKeyFrame());
        }
        if (layerLockPlis_ != 0) {
            output.writeUInt32(35, layerLockPlis_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeMessage(36, getLastLayerLockPli());
        }
        if (nackAcks_ != 0) {
            output.writeUInt32(37, nackAcks_);
        }
        if (nackRepeated_ != 0) {
            output.writeUInt32(38, nackRepeated_);
        }
        if (headerBytes_ != 0L) {
            output.writeUInt64(39, headerBytes_);
        }
        if (headerBytesDuplicate_ != 0L) {
            output.writeUInt64(40, headerBytesDuplicate_);
        }
        if (headerBytesPadding_ != 0L) {
            output.writeUInt64(41, headerBytesPadding_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            output.writeMessage(44, getPacketDrift());
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            output.writeMessage(45, getReportDrift());
        }
        if (((bitField0_ & 0x00000100) != 0)) {
            output.writeMessage(46, getRebasedReportDrift());
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, getStartTime());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, getEndTime());
        }
        if (java.lang.Double.doubleToRawLongBits(duration_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(3, duration_);
        }
        if (packets_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, packets_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(5, packetRate_);
        }
        if (bytes_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(6, bytes_);
        }
        if (java.lang.Double.doubleToRawLongBits(bitrate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(7, bitrate_);
        }
        if (packetsLost_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(8, packetsLost_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetLossRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(9, packetLossRate_);
        }
        if (java.lang.Float.floatToRawIntBits(packetLossPercentage_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeFloatSize(10,
                    packetLossPercentage_);
        }
        if (packetsDuplicate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(11, packetsDuplicate_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetDuplicateRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(12,
                    packetDuplicateRate_);
        }
        if (bytesDuplicate_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(13, bytesDuplicate_);
        }
        if (java.lang.Double.doubleToRawLongBits(bitrateDuplicate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(14, bitrateDuplicate_);
        }
        if (packetsPadding_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(15, packetsPadding_);
        }
        if (java.lang.Double.doubleToRawLongBits(packetPaddingRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(16, packetPaddingRate_);
        }
        if (bytesPadding_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(17, bytesPadding_);
        }
        if (java.lang.Double.doubleToRawLongBits(bitratePadding_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(18, bitratePadding_);
        }
        if (packetsOutOfOrder_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(19, packetsOutOfOrder_);
        }
        if (frames_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(20, frames_);
        }
        if (java.lang.Double.doubleToRawLongBits(frameRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(21, frameRate_);
        }
        if (java.lang.Double.doubleToRawLongBits(jitterCurrent_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(22, jitterCurrent_);
        }
        if (java.lang.Double.doubleToRawLongBits(jitterMax_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(23, jitterMax_);
        }
        for (java.util.Map.Entry<java.lang.Integer, java.lang.Integer> entry : internalGetGapHistogram()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.Integer, java.lang.Integer> gapHistogram__ =
                    GapHistogramDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(24, gapHistogram__);
        }
        if (nacks_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(25, nacks_);
        }
        if (nackMisses_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(26, nackMisses_);
        }
        if (plis_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(27, plis_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(28, getLastPli());
        }
        if (firs_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(29, firs_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(30, getLastFir());
        }
        if (rttCurrent_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(31, rttCurrent_);
        }
        if (rttMax_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(32, rttMax_);
        }
        if (keyFrames_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(33, keyFrames_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(34, getLastKeyFrame());
        }
        if (layerLockPlis_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(35, layerLockPlis_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(36,
                    getLastLayerLockPli());
        }
        if (nackAcks_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(37, nackAcks_);
        }
        if (nackRepeated_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(38, nackRepeated_);
        }
        if (headerBytes_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(39, headerBytes_);
        }
        if (headerBytesDuplicate_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(40,
                    headerBytesDuplicate_);
        }
        if (headerBytesPadding_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(41,
                    headerBytesPadding_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(44, getPacketDrift());
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(45, getReportDrift());
        }
        if (((bitField0_ & 0x00000100) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(46,
                    getRebasedReportDrift());
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RTPStats other)) {
            return super.equals(obj);
        }

        if (hasStartTime() != other.hasStartTime()) {
            return false;
        }
        if (hasStartTime()) {
            if (!getStartTime().equals(other.getStartTime())) {
                return false;
            }
        }
        if (hasEndTime() != other.hasEndTime()) {
            return false;
        }
        if (hasEndTime()) {
            if (!getEndTime().equals(other.getEndTime())) {
                return false;
            }
        }
        if (java.lang.Double.doubleToLongBits(getDuration()) != java.lang.Double
                .doubleToLongBits(other.getDuration())) {
            return false;
        }
        if (getPackets() != other.getPackets()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getPacketRate()) != java.lang.Double
                .doubleToLongBits(other.getPacketRate())) {
            return false;
        }
        if (getBytes() != other.getBytes()) {
            return false;
        }
        if (getHeaderBytes() != other.getHeaderBytes()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getBitrate()) != java.lang.Double
                .doubleToLongBits(other.getBitrate())) {
            return false;
        }
        if (getPacketsLost() != other.getPacketsLost()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getPacketLossRate()) != java.lang.Double
                .doubleToLongBits(other.getPacketLossRate())) {
            return false;
        }
        if (java.lang.Float.floatToIntBits(getPacketLossPercentage()) != java.lang.Float
                .floatToIntBits(other.getPacketLossPercentage())) {
            return false;
        }
        if (getPacketsDuplicate() != other.getPacketsDuplicate()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getPacketDuplicateRate()) != java.lang.Double
                .doubleToLongBits(other.getPacketDuplicateRate())) {
            return false;
        }
        if (getBytesDuplicate() != other.getBytesDuplicate()) {
            return false;
        }
        if (getHeaderBytesDuplicate() != other.getHeaderBytesDuplicate()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getBitrateDuplicate()) != java.lang.Double
                .doubleToLongBits(other.getBitrateDuplicate())) {
            return false;
        }
        if (getPacketsPadding() != other.getPacketsPadding()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getPacketPaddingRate()) != java.lang.Double
                .doubleToLongBits(other.getPacketPaddingRate())) {
            return false;
        }
        if (getBytesPadding() != other.getBytesPadding()) {
            return false;
        }
        if (getHeaderBytesPadding() != other.getHeaderBytesPadding()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getBitratePadding()) != java.lang.Double
                .doubleToLongBits(other.getBitratePadding())) {
            return false;
        }
        if (getPacketsOutOfOrder() != other.getPacketsOutOfOrder()) {
            return false;
        }
        if (getFrames() != other.getFrames()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getFrameRate()) != java.lang.Double
                .doubleToLongBits(other.getFrameRate())) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getJitterCurrent()) != java.lang.Double
                .doubleToLongBits(other.getJitterCurrent())) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getJitterMax()) != java.lang.Double
                .doubleToLongBits(other.getJitterMax())) {
            return false;
        }
        if (!internalGetGapHistogram().equals(other.internalGetGapHistogram())) {
            return false;
        }
        if (getNacks() != other.getNacks()) {
            return false;
        }
        if (getNackAcks() != other.getNackAcks()) {
            return false;
        }
        if (getNackMisses() != other.getNackMisses()) {
            return false;
        }
        if (getNackRepeated() != other.getNackRepeated()) {
            return false;
        }
        if (getPlis() != other.getPlis()) {
            return false;
        }
        if (hasLastPli() != other.hasLastPli()) {
            return false;
        }
        if (hasLastPli()) {
            if (!getLastPli().equals(other.getLastPli())) {
                return false;
            }
        }
        if (getFirs() != other.getFirs()) {
            return false;
        }
        if (hasLastFir() != other.hasLastFir()) {
            return false;
        }
        if (hasLastFir()) {
            if (!getLastFir().equals(other.getLastFir())) {
                return false;
            }
        }
        if (getRttCurrent() != other.getRttCurrent()) {
            return false;
        }
        if (getRttMax() != other.getRttMax()) {
            return false;
        }
        if (getKeyFrames() != other.getKeyFrames()) {
            return false;
        }
        if (hasLastKeyFrame() != other.hasLastKeyFrame()) {
            return false;
        }
        if (hasLastKeyFrame()) {
            if (!getLastKeyFrame().equals(other.getLastKeyFrame())) {
                return false;
            }
        }
        if (getLayerLockPlis() != other.getLayerLockPlis()) {
            return false;
        }
        if (hasLastLayerLockPli() != other.hasLastLayerLockPli()) {
            return false;
        }
        if (hasLastLayerLockPli()) {
            if (!getLastLayerLockPli().equals(other.getLastLayerLockPli())) {
                return false;
            }
        }
        if (hasPacketDrift() != other.hasPacketDrift()) {
            return false;
        }
        if (hasPacketDrift()) {
            if (!getPacketDrift().equals(other.getPacketDrift())) {
                return false;
            }
        }
        if (hasReportDrift() != other.hasReportDrift()) {
            return false;
        }
        if (hasReportDrift()) {
            if (!getReportDrift().equals(other.getReportDrift())) {
                return false;
            }
        }
        if (hasRebasedReportDrift() != other.hasRebasedReportDrift()) {
            return false;
        }
        if (hasRebasedReportDrift()) {
            if (!getRebasedReportDrift().equals(other.getRebasedReportDrift())) {
                return false;
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (hasStartTime()) {
            hash = (37 * hash) + START_TIME_FIELD_NUMBER;
            hash = (53 * hash) + getStartTime().hashCode();
        }
        if (hasEndTime()) {
            hash = (37 * hash) + END_TIME_FIELD_NUMBER;
            hash = (53 * hash) + getEndTime().hashCode();
        }
        hash = (37 * hash) + DURATION_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getDuration()));
        hash = (37 * hash) + PACKETS_FIELD_NUMBER;
        hash = (53 * hash) + getPackets();
        hash = (37 * hash) + PACKET_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getPacketRate()));
        hash = (37 * hash) + BYTES_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getBytes());
        hash = (37 * hash) + HEADER_BYTES_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getHeaderBytes());
        hash = (37 * hash) + BITRATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getBitrate()));
        hash = (37 * hash) + PACKETS_LOST_FIELD_NUMBER;
        hash = (53 * hash) + getPacketsLost();
        hash = (37 * hash) + PACKET_LOSS_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getPacketLossRate()));
        hash = (37 * hash) + PACKET_LOSS_PERCENTAGE_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(getPacketLossPercentage());
        hash = (37 * hash) + PACKETS_DUPLICATE_FIELD_NUMBER;
        hash = (53 * hash) + getPacketsDuplicate();
        hash = (37 * hash) + PACKET_DUPLICATE_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getPacketDuplicateRate()));
        hash = (37 * hash) + BYTES_DUPLICATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getBytesDuplicate());
        hash = (37 * hash) + HEADER_BYTES_DUPLICATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getHeaderBytesDuplicate());
        hash = (37 * hash) + BITRATE_DUPLICATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getBitrateDuplicate()));
        hash = (37 * hash) + PACKETS_PADDING_FIELD_NUMBER;
        hash = (53 * hash) + getPacketsPadding();
        hash = (37 * hash) + PACKET_PADDING_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getPacketPaddingRate()));
        hash = (37 * hash) + BYTES_PADDING_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getBytesPadding());
        hash = (37 * hash) + HEADER_BYTES_PADDING_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getHeaderBytesPadding());
        hash = (37 * hash) + BITRATE_PADDING_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getBitratePadding()));
        hash = (37 * hash) + PACKETS_OUT_OF_ORDER_FIELD_NUMBER;
        hash = (53 * hash) + getPacketsOutOfOrder();
        hash = (37 * hash) + FRAMES_FIELD_NUMBER;
        hash = (53 * hash) + getFrames();
        hash = (37 * hash) + FRAME_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getFrameRate()));
        hash = (37 * hash) + JITTER_CURRENT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getJitterCurrent()));
        hash = (37 * hash) + JITTER_MAX_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getJitterMax()));
        if (!internalGetGapHistogram().getMap()
                .isEmpty()) {
            hash = (37 * hash) + GAP_HISTOGRAM_FIELD_NUMBER;
            hash = (53 * hash) + internalGetGapHistogram().hashCode();
        }
        hash = (37 * hash) + NACKS_FIELD_NUMBER;
        hash = (53 * hash) + getNacks();
        hash = (37 * hash) + NACK_ACKS_FIELD_NUMBER;
        hash = (53 * hash) + getNackAcks();
        hash = (37 * hash) + NACK_MISSES_FIELD_NUMBER;
        hash = (53 * hash) + getNackMisses();
        hash = (37 * hash) + NACK_REPEATED_FIELD_NUMBER;
        hash = (53 * hash) + getNackRepeated();
        hash = (37 * hash) + PLIS_FIELD_NUMBER;
        hash = (53 * hash) + getPlis();
        if (hasLastPli()) {
            hash = (37 * hash) + LAST_PLI_FIELD_NUMBER;
            hash = (53 * hash) + getLastPli().hashCode();
        }
        hash = (37 * hash) + FIRS_FIELD_NUMBER;
        hash = (53 * hash) + getFirs();
        if (hasLastFir()) {
            hash = (37 * hash) + LAST_FIR_FIELD_NUMBER;
            hash = (53 * hash) + getLastFir().hashCode();
        }
        hash = (37 * hash) + RTT_CURRENT_FIELD_NUMBER;
        hash = (53 * hash) + getRttCurrent();
        hash = (37 * hash) + RTT_MAX_FIELD_NUMBER;
        hash = (53 * hash) + getRttMax();
        hash = (37 * hash) + KEY_FRAMES_FIELD_NUMBER;
        hash = (53 * hash) + getKeyFrames();
        if (hasLastKeyFrame()) {
            hash = (37 * hash) + LAST_KEY_FRAME_FIELD_NUMBER;
            hash = (53 * hash) + getLastKeyFrame().hashCode();
        }
        hash = (37 * hash) + LAYER_LOCK_PLIS_FIELD_NUMBER;
        hash = (53 * hash) + getLayerLockPlis();
        if (hasLastLayerLockPli()) {
            hash = (37 * hash) + LAST_LAYER_LOCK_PLI_FIELD_NUMBER;
            hash = (53 * hash) + getLastLayerLockPli().hashCode();
        }
        if (hasPacketDrift()) {
            hash = (37 * hash) + PACKET_DRIFT_FIELD_NUMBER;
            hash = (53 * hash) + getPacketDrift().hashCode();
        }
        if (hasReportDrift()) {
            hash = (37 * hash) + REPORT_DRIFT_FIELD_NUMBER;
            hash = (53 * hash) + getReportDrift().hashCode();
        }
        if (hasRebasedReportDrift()) {
            hash = (37 * hash) + REBASED_REPORT_DRIFT_FIELD_NUMBER;
            hash = (53 * hash) + getRebasedReportDrift().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.models.RTPStats prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code livekit.RTPStats}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.RTPStats)
            im.turms.plugin.livekit.core.proto.models.RTPStatsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPStats_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            return switch (number) {
                case 24 -> internalGetGapHistogram();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            return switch (number) {
                case 24 -> internalGetMutableGapHistogram();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPStats_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.RTPStats.class,
                            im.turms.plugin.livekit.core.proto.models.RTPStats.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.RTPStats.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getStartTimeFieldBuilder();
                getEndTimeFieldBuilder();
                getLastPliFieldBuilder();
                getLastFirFieldBuilder();
                getLastKeyFrameFieldBuilder();
                getLastLayerLockPliFieldBuilder();
                getPacketDriftFieldBuilder();
                getReportDriftFieldBuilder();
                getRebasedReportDriftFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            bitField1_ = 0;
            startTime_ = null;
            if (startTimeBuilder_ != null) {
                startTimeBuilder_.dispose();
                startTimeBuilder_ = null;
            }
            endTime_ = null;
            if (endTimeBuilder_ != null) {
                endTimeBuilder_.dispose();
                endTimeBuilder_ = null;
            }
            duration_ = 0D;
            packets_ = 0;
            packetRate_ = 0D;
            bytes_ = 0L;
            headerBytes_ = 0L;
            bitrate_ = 0D;
            packetsLost_ = 0;
            packetLossRate_ = 0D;
            packetLossPercentage_ = 0F;
            packetsDuplicate_ = 0;
            packetDuplicateRate_ = 0D;
            bytesDuplicate_ = 0L;
            headerBytesDuplicate_ = 0L;
            bitrateDuplicate_ = 0D;
            packetsPadding_ = 0;
            packetPaddingRate_ = 0D;
            bytesPadding_ = 0L;
            headerBytesPadding_ = 0L;
            bitratePadding_ = 0D;
            packetsOutOfOrder_ = 0;
            frames_ = 0;
            frameRate_ = 0D;
            jitterCurrent_ = 0D;
            jitterMax_ = 0D;
            internalGetMutableGapHistogram().clear();
            nacks_ = 0;
            nackAcks_ = 0;
            nackMisses_ = 0;
            nackRepeated_ = 0;
            plis_ = 0;
            lastPli_ = null;
            if (lastPliBuilder_ != null) {
                lastPliBuilder_.dispose();
                lastPliBuilder_ = null;
            }
            firs_ = 0;
            lastFir_ = null;
            if (lastFirBuilder_ != null) {
                lastFirBuilder_.dispose();
                lastFirBuilder_ = null;
            }
            rttCurrent_ = 0;
            rttMax_ = 0;
            keyFrames_ = 0;
            lastKeyFrame_ = null;
            if (lastKeyFrameBuilder_ != null) {
                lastKeyFrameBuilder_.dispose();
                lastKeyFrameBuilder_ = null;
            }
            layerLockPlis_ = 0;
            lastLayerLockPli_ = null;
            if (lastLayerLockPliBuilder_ != null) {
                lastLayerLockPliBuilder_.dispose();
                lastLayerLockPliBuilder_ = null;
            }
            packetDrift_ = null;
            if (packetDriftBuilder_ != null) {
                packetDriftBuilder_.dispose();
                packetDriftBuilder_ = null;
            }
            reportDrift_ = null;
            if (reportDriftBuilder_ != null) {
                reportDriftBuilder_.dispose();
                reportDriftBuilder_ = null;
            }
            rebasedReportDrift_ = null;
            if (rebasedReportDriftBuilder_ != null) {
                rebasedReportDriftBuilder_.dispose();
                rebasedReportDriftBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPStats_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.RTPStats getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.RTPStats.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.RTPStats build() {
            im.turms.plugin.livekit.core.proto.models.RTPStats result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.RTPStats buildPartial() {
            im.turms.plugin.livekit.core.proto.models.RTPStats result =
                    new im.turms.plugin.livekit.core.proto.models.RTPStats(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            if (bitField1_ != 0) {
                buildPartial1(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.RTPStats result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.startTime_ = startTimeBuilder_ == null
                        ? startTime_
                        : startTimeBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.endTime_ = endTimeBuilder_ == null
                        ? endTime_
                        : endTimeBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.duration_ = duration_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.packets_ = packets_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.packetRate_ = packetRate_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.bytes_ = bytes_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.headerBytes_ = headerBytes_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.bitrate_ = bitrate_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.packetsLost_ = packetsLost_;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.packetLossRate_ = packetLossRate_;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.packetLossPercentage_ = packetLossPercentage_;
            }
            if (((from_bitField0_ & 0x00000800) != 0)) {
                result.packetsDuplicate_ = packetsDuplicate_;
            }
            if (((from_bitField0_ & 0x00001000) != 0)) {
                result.packetDuplicateRate_ = packetDuplicateRate_;
            }
            if (((from_bitField0_ & 0x00002000) != 0)) {
                result.bytesDuplicate_ = bytesDuplicate_;
            }
            if (((from_bitField0_ & 0x00004000) != 0)) {
                result.headerBytesDuplicate_ = headerBytesDuplicate_;
            }
            if (((from_bitField0_ & 0x00008000) != 0)) {
                result.bitrateDuplicate_ = bitrateDuplicate_;
            }
            if (((from_bitField0_ & 0x00010000) != 0)) {
                result.packetsPadding_ = packetsPadding_;
            }
            if (((from_bitField0_ & 0x00020000) != 0)) {
                result.packetPaddingRate_ = packetPaddingRate_;
            }
            if (((from_bitField0_ & 0x00040000) != 0)) {
                result.bytesPadding_ = bytesPadding_;
            }
            if (((from_bitField0_ & 0x00080000) != 0)) {
                result.headerBytesPadding_ = headerBytesPadding_;
            }
            if (((from_bitField0_ & 0x00100000) != 0)) {
                result.bitratePadding_ = bitratePadding_;
            }
            if (((from_bitField0_ & 0x00200000) != 0)) {
                result.packetsOutOfOrder_ = packetsOutOfOrder_;
            }
            if (((from_bitField0_ & 0x00400000) != 0)) {
                result.frames_ = frames_;
            }
            if (((from_bitField0_ & 0x00800000) != 0)) {
                result.frameRate_ = frameRate_;
            }
            if (((from_bitField0_ & 0x01000000) != 0)) {
                result.jitterCurrent_ = jitterCurrent_;
            }
            if (((from_bitField0_ & 0x02000000) != 0)) {
                result.jitterMax_ = jitterMax_;
            }
            if (((from_bitField0_ & 0x04000000) != 0)) {
                result.gapHistogram_ = internalGetGapHistogram();
                result.gapHistogram_.makeImmutable();
            }
            if (((from_bitField0_ & 0x08000000) != 0)) {
                result.nacks_ = nacks_;
            }
            if (((from_bitField0_ & 0x10000000) != 0)) {
                result.nackAcks_ = nackAcks_;
            }
            if (((from_bitField0_ & 0x20000000) != 0)) {
                result.nackMisses_ = nackMisses_;
            }
            if (((from_bitField0_ & 0x40000000) != 0)) {
                result.nackRepeated_ = nackRepeated_;
            }
            if (((from_bitField0_ & 0x80000000) != 0)) {
                result.plis_ = plis_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        private void buildPartial1(im.turms.plugin.livekit.core.proto.models.RTPStats result) {
            int from_bitField1_ = bitField1_;
            int to_bitField0_ = 0;
            if (((from_bitField1_ & 0x00000001) != 0)) {
                result.lastPli_ = lastPliBuilder_ == null
                        ? lastPli_
                        : lastPliBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField1_ & 0x00000002) != 0)) {
                result.firs_ = firs_;
            }
            if (((from_bitField1_ & 0x00000004) != 0)) {
                result.lastFir_ = lastFirBuilder_ == null
                        ? lastFir_
                        : lastFirBuilder_.build();
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField1_ & 0x00000008) != 0)) {
                result.rttCurrent_ = rttCurrent_;
            }
            if (((from_bitField1_ & 0x00000010) != 0)) {
                result.rttMax_ = rttMax_;
            }
            if (((from_bitField1_ & 0x00000020) != 0)) {
                result.keyFrames_ = keyFrames_;
            }
            if (((from_bitField1_ & 0x00000040) != 0)) {
                result.lastKeyFrame_ = lastKeyFrameBuilder_ == null
                        ? lastKeyFrame_
                        : lastKeyFrameBuilder_.build();
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField1_ & 0x00000080) != 0)) {
                result.layerLockPlis_ = layerLockPlis_;
            }
            if (((from_bitField1_ & 0x00000100) != 0)) {
                result.lastLayerLockPli_ = lastLayerLockPliBuilder_ == null
                        ? lastLayerLockPli_
                        : lastLayerLockPliBuilder_.build();
                to_bitField0_ |= 0x00000020;
            }
            if (((from_bitField1_ & 0x00000200) != 0)) {
                result.packetDrift_ = packetDriftBuilder_ == null
                        ? packetDrift_
                        : packetDriftBuilder_.build();
                to_bitField0_ |= 0x00000040;
            }
            if (((from_bitField1_ & 0x00000400) != 0)) {
                result.reportDrift_ = reportDriftBuilder_ == null
                        ? reportDrift_
                        : reportDriftBuilder_.build();
                to_bitField0_ |= 0x00000080;
            }
            if (((from_bitField1_ & 0x00000800) != 0)) {
                result.rebasedReportDrift_ = rebasedReportDriftBuilder_ == null
                        ? rebasedReportDrift_
                        : rebasedReportDriftBuilder_.build();
                to_bitField0_ |= 0x00000100;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.RTPStats) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.RTPStats) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.RTPStats other) {
            if (other == im.turms.plugin.livekit.core.proto.models.RTPStats.getDefaultInstance()) {
                return this;
            }
            if (other.hasStartTime()) {
                mergeStartTime(other.getStartTime());
            }
            if (other.hasEndTime()) {
                mergeEndTime(other.getEndTime());
            }
            if (other.getDuration() != 0D) {
                setDuration(other.getDuration());
            }
            if (other.getPackets() != 0) {
                setPackets(other.getPackets());
            }
            if (other.getPacketRate() != 0D) {
                setPacketRate(other.getPacketRate());
            }
            if (other.getBytes() != 0L) {
                setBytes(other.getBytes());
            }
            if (other.getHeaderBytes() != 0L) {
                setHeaderBytes(other.getHeaderBytes());
            }
            if (other.getBitrate() != 0D) {
                setBitrate(other.getBitrate());
            }
            if (other.getPacketsLost() != 0) {
                setPacketsLost(other.getPacketsLost());
            }
            if (other.getPacketLossRate() != 0D) {
                setPacketLossRate(other.getPacketLossRate());
            }
            if (other.getPacketLossPercentage() != 0F) {
                setPacketLossPercentage(other.getPacketLossPercentage());
            }
            if (other.getPacketsDuplicate() != 0) {
                setPacketsDuplicate(other.getPacketsDuplicate());
            }
            if (other.getPacketDuplicateRate() != 0D) {
                setPacketDuplicateRate(other.getPacketDuplicateRate());
            }
            if (other.getBytesDuplicate() != 0L) {
                setBytesDuplicate(other.getBytesDuplicate());
            }
            if (other.getHeaderBytesDuplicate() != 0L) {
                setHeaderBytesDuplicate(other.getHeaderBytesDuplicate());
            }
            if (other.getBitrateDuplicate() != 0D) {
                setBitrateDuplicate(other.getBitrateDuplicate());
            }
            if (other.getPacketsPadding() != 0) {
                setPacketsPadding(other.getPacketsPadding());
            }
            if (other.getPacketPaddingRate() != 0D) {
                setPacketPaddingRate(other.getPacketPaddingRate());
            }
            if (other.getBytesPadding() != 0L) {
                setBytesPadding(other.getBytesPadding());
            }
            if (other.getHeaderBytesPadding() != 0L) {
                setHeaderBytesPadding(other.getHeaderBytesPadding());
            }
            if (other.getBitratePadding() != 0D) {
                setBitratePadding(other.getBitratePadding());
            }
            if (other.getPacketsOutOfOrder() != 0) {
                setPacketsOutOfOrder(other.getPacketsOutOfOrder());
            }
            if (other.getFrames() != 0) {
                setFrames(other.getFrames());
            }
            if (other.getFrameRate() != 0D) {
                setFrameRate(other.getFrameRate());
            }
            if (other.getJitterCurrent() != 0D) {
                setJitterCurrent(other.getJitterCurrent());
            }
            if (other.getJitterMax() != 0D) {
                setJitterMax(other.getJitterMax());
            }
            internalGetMutableGapHistogram().mergeFrom(other.internalGetGapHistogram());
            bitField0_ |= 0x04000000;
            if (other.getNacks() != 0) {
                setNacks(other.getNacks());
            }
            if (other.getNackAcks() != 0) {
                setNackAcks(other.getNackAcks());
            }
            if (other.getNackMisses() != 0) {
                setNackMisses(other.getNackMisses());
            }
            if (other.getNackRepeated() != 0) {
                setNackRepeated(other.getNackRepeated());
            }
            if (other.getPlis() != 0) {
                setPlis(other.getPlis());
            }
            if (other.hasLastPli()) {
                mergeLastPli(other.getLastPli());
            }
            if (other.getFirs() != 0) {
                setFirs(other.getFirs());
            }
            if (other.hasLastFir()) {
                mergeLastFir(other.getLastFir());
            }
            if (other.getRttCurrent() != 0) {
                setRttCurrent(other.getRttCurrent());
            }
            if (other.getRttMax() != 0) {
                setRttMax(other.getRttMax());
            }
            if (other.getKeyFrames() != 0) {
                setKeyFrames(other.getKeyFrames());
            }
            if (other.hasLastKeyFrame()) {
                mergeLastKeyFrame(other.getLastKeyFrame());
            }
            if (other.getLayerLockPlis() != 0) {
                setLayerLockPlis(other.getLayerLockPlis());
            }
            if (other.hasLastLayerLockPli()) {
                mergeLastLayerLockPli(other.getLastLayerLockPli());
            }
            if (other.hasPacketDrift()) {
                mergePacketDrift(other.getPacketDrift());
            }
            if (other.hasReportDrift()) {
                mergeReportDrift(other.getReportDrift());
            }
            if (other.hasRebasedReportDrift()) {
                mergeRebasedReportDrift(other.getRebasedReportDrift());
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 10 -> {
                            input.readMessage(getStartTimeFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            input.readMessage(getEndTimeFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 25 -> {
                            duration_ = input.readDouble();
                            bitField0_ |= 0x00000004;
                        } // case 25
                        case 32 -> {
                            packets_ = input.readUInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 41 -> {
                            packetRate_ = input.readDouble();
                            bitField0_ |= 0x00000010;
                        } // case 41
                        case 48 -> {
                            bytes_ = input.readUInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 57 -> {
                            bitrate_ = input.readDouble();
                            bitField0_ |= 0x00000080;
                        } // case 57
                        case 64 -> {
                            packetsLost_ = input.readUInt32();
                            bitField0_ |= 0x00000100;
                        } // case 64
                        case 73 -> {
                            packetLossRate_ = input.readDouble();
                            bitField0_ |= 0x00000200;
                        } // case 73
                        case 85 -> {
                            packetLossPercentage_ = input.readFloat();
                            bitField0_ |= 0x00000400;
                        } // case 85
                        case 88 -> {
                            packetsDuplicate_ = input.readUInt32();
                            bitField0_ |= 0x00000800;
                        } // case 88
                        case 97 -> {
                            packetDuplicateRate_ = input.readDouble();
                            bitField0_ |= 0x00001000;
                        } // case 97
                        case 104 -> {
                            bytesDuplicate_ = input.readUInt64();
                            bitField0_ |= 0x00002000;
                        } // case 104
                        case 113 -> {
                            bitrateDuplicate_ = input.readDouble();
                            bitField0_ |= 0x00008000;
                        } // case 113
                        case 120 -> {
                            packetsPadding_ = input.readUInt32();
                            bitField0_ |= 0x00010000;
                        } // case 120
                        case 129 -> {
                            packetPaddingRate_ = input.readDouble();
                            bitField0_ |= 0x00020000;
                        } // case 129
                        case 136 -> {
                            bytesPadding_ = input.readUInt64();
                            bitField0_ |= 0x00040000;
                        } // case 136
                        case 145 -> {
                            bitratePadding_ = input.readDouble();
                            bitField0_ |= 0x00100000;
                        } // case 145
                        case 152 -> {
                            packetsOutOfOrder_ = input.readUInt32();
                            bitField0_ |= 0x00200000;
                        } // case 152
                        case 160 -> {
                            frames_ = input.readUInt32();
                            bitField0_ |= 0x00400000;
                        } // case 160
                        case 169 -> {
                            frameRate_ = input.readDouble();
                            bitField0_ |= 0x00800000;
                        } // case 169
                        case 177 -> {
                            jitterCurrent_ = input.readDouble();
                            bitField0_ |= 0x01000000;
                        } // case 177
                        case 185 -> {
                            jitterMax_ = input.readDouble();
                            bitField0_ |= 0x02000000;
                        } // case 185
                        case 194 -> {
                            com.google.protobuf.MapEntry<Integer, Integer> gapHistogram__ =
                                    input.readMessage(
                                            GapHistogramDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableGapHistogram().getMutableMap()
                                    .put(gapHistogram__.getKey(), gapHistogram__.getValue());
                            bitField0_ |= 0x04000000;
                        } // case 194
                        case 200 -> {
                            nacks_ = input.readUInt32();
                            bitField0_ |= 0x08000000;
                        } // case 200
                        case 208 -> {
                            nackMisses_ = input.readUInt32();
                            bitField0_ |= 0x20000000;
                        } // case 208
                        case 216 -> {
                            plis_ = input.readUInt32();
                            bitField0_ |= 0x80000000;
                        } // case 216
                        case 226 -> {
                            input.readMessage(getLastPliFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000001;
                        } // case 226
                        case 232 -> {
                            firs_ = input.readUInt32();
                            bitField1_ |= 0x00000002;
                        } // case 232
                        case 242 -> {
                            input.readMessage(getLastFirFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000004;
                        } // case 242
                        case 248 -> {
                            rttCurrent_ = input.readUInt32();
                            bitField1_ |= 0x00000008;
                        } // case 248
                        case 256 -> {
                            rttMax_ = input.readUInt32();
                            bitField1_ |= 0x00000010;
                        } // case 256
                        case 264 -> {
                            keyFrames_ = input.readUInt32();
                            bitField1_ |= 0x00000020;
                        } // case 264
                        case 274 -> {
                            input.readMessage(getLastKeyFrameFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000040;
                        } // case 274
                        case 280 -> {
                            layerLockPlis_ = input.readUInt32();
                            bitField1_ |= 0x00000080;
                        } // case 280
                        case 290 -> {
                            input.readMessage(getLastLayerLockPliFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000100;
                        } // case 290
                        case 296 -> {
                            nackAcks_ = input.readUInt32();
                            bitField0_ |= 0x10000000;
                        } // case 296
                        case 304 -> {
                            nackRepeated_ = input.readUInt32();
                            bitField0_ |= 0x40000000;
                        } // case 304
                        case 312 -> {
                            headerBytes_ = input.readUInt64();
                            bitField0_ |= 0x00000040;
                        } // case 312
                        case 320 -> {
                            headerBytesDuplicate_ = input.readUInt64();
                            bitField0_ |= 0x00004000;
                        } // case 320
                        case 328 -> {
                            headerBytesPadding_ = input.readUInt64();
                            bitField0_ |= 0x00080000;
                        } // case 328
                        case 354 -> {
                            input.readMessage(getPacketDriftFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000200;
                        } // case 354
                        case 362 -> {
                            input.readMessage(getReportDriftFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000400;
                        } // case 362
                        case 370 -> {
                            input.readMessage(getRebasedReportDriftFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField1_ |= 0x00000800;
                        } // case 370
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int bitField0_;
        private int bitField1_;

        private com.google.protobuf.Timestamp startTime_;
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> startTimeBuilder_;

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         *
         * @return Whether the startTime field is set.
         */
        public boolean hasStartTime() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         *
         * @return The startTime.
         */
        public com.google.protobuf.Timestamp getStartTime() {
            if (startTimeBuilder_ == null) {
                return startTime_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : startTime_;
            } else {
                return startTimeBuilder_.getMessage();
            }
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        public Builder setStartTime(com.google.protobuf.Timestamp value) {
            if (startTimeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                startTime_ = value;
            } else {
                startTimeBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        public Builder setStartTime(com.google.protobuf.Timestamp.Builder builderForValue) {
            if (startTimeBuilder_ == null) {
                startTime_ = builderForValue.build();
            } else {
                startTimeBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        public Builder mergeStartTime(com.google.protobuf.Timestamp value) {
            if (startTimeBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)
                        && startTime_ != null
                        && startTime_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
                    getStartTimeBuilder().mergeFrom(value);
                } else {
                    startTime_ = value;
                }
            } else {
                startTimeBuilder_.mergeFrom(value);
            }
            if (startTime_ != null) {
                bitField0_ |= 0x00000001;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        public Builder clearStartTime() {
            bitField0_ &= ~0x00000001;
            startTime_ = null;
            if (startTimeBuilder_ != null) {
                startTimeBuilder_.dispose();
                startTimeBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        public com.google.protobuf.Timestamp.Builder getStartTimeBuilder() {
            bitField0_ |= 0x00000001;
            onChanged();
            return getStartTimeFieldBuilder().getBuilder();
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        public com.google.protobuf.TimestampOrBuilder getStartTimeOrBuilder() {
            if (startTimeBuilder_ != null) {
                return startTimeBuilder_.getMessageOrBuilder();
            } else {
                return startTime_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : startTime_;
            }
        }

        /**
         * <code>.google.protobuf.Timestamp start_time = 1;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> getStartTimeFieldBuilder() {
            if (startTimeBuilder_ == null) {
                startTimeBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getStartTime(),
                        getParentForChildren(),
                        isClean());
                startTime_ = null;
            }
            return startTimeBuilder_;
        }

        private com.google.protobuf.Timestamp endTime_;
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> endTimeBuilder_;

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         *
         * @return Whether the endTime field is set.
         */
        public boolean hasEndTime() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         *
         * @return The endTime.
         */
        public com.google.protobuf.Timestamp getEndTime() {
            if (endTimeBuilder_ == null) {
                return endTime_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : endTime_;
            } else {
                return endTimeBuilder_.getMessage();
            }
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        public Builder setEndTime(com.google.protobuf.Timestamp value) {
            if (endTimeBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                endTime_ = value;
            } else {
                endTimeBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        public Builder setEndTime(com.google.protobuf.Timestamp.Builder builderForValue) {
            if (endTimeBuilder_ == null) {
                endTime_ = builderForValue.build();
            } else {
                endTimeBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        public Builder mergeEndTime(com.google.protobuf.Timestamp value) {
            if (endTimeBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)
                        && endTime_ != null
                        && endTime_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
                    getEndTimeBuilder().mergeFrom(value);
                } else {
                    endTime_ = value;
                }
            } else {
                endTimeBuilder_.mergeFrom(value);
            }
            if (endTime_ != null) {
                bitField0_ |= 0x00000002;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        public Builder clearEndTime() {
            bitField0_ &= ~0x00000002;
            endTime_ = null;
            if (endTimeBuilder_ != null) {
                endTimeBuilder_.dispose();
                endTimeBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        public com.google.protobuf.Timestamp.Builder getEndTimeBuilder() {
            bitField0_ |= 0x00000002;
            onChanged();
            return getEndTimeFieldBuilder().getBuilder();
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        public com.google.protobuf.TimestampOrBuilder getEndTimeOrBuilder() {
            if (endTimeBuilder_ != null) {
                return endTimeBuilder_.getMessageOrBuilder();
            } else {
                return endTime_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : endTime_;
            }
        }

        /**
         * <code>.google.protobuf.Timestamp end_time = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> getEndTimeFieldBuilder() {
            if (endTimeBuilder_ == null) {
                endTimeBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getEndTime(),
                        getParentForChildren(),
                        isClean());
                endTime_ = null;
            }
            return endTimeBuilder_;
        }

        private double duration_;

        /**
         * <code>double duration = 3;</code>
         *
         * @return The duration.
         */
        @java.lang.Override
        public double getDuration() {
            return duration_;
        }

        /**
         * <code>double duration = 3;</code>
         *
         * @param value The duration to set.
         * @return This builder for chaining.
         */
        public Builder setDuration(double value) {

            duration_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>double duration = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDuration() {
            bitField0_ &= ~0x00000004;
            duration_ = 0D;
            onChanged();
            return this;
        }

        private int packets_;

        /**
         * <code>uint32 packets = 4;</code>
         *
         * @return The packets.
         */
        @java.lang.Override
        public int getPackets() {
            return packets_;
        }

        /**
         * <code>uint32 packets = 4;</code>
         *
         * @param value The packets to set.
         * @return This builder for chaining.
         */
        public Builder setPackets(int value) {

            packets_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 packets = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPackets() {
            bitField0_ &= ~0x00000008;
            packets_ = 0;
            onChanged();
            return this;
        }

        private double packetRate_;

        /**
         * <code>double packet_rate = 5;</code>
         *
         * @return The packetRate.
         */
        @java.lang.Override
        public double getPacketRate() {
            return packetRate_;
        }

        /**
         * <code>double packet_rate = 5;</code>
         *
         * @param value The packetRate to set.
         * @return This builder for chaining.
         */
        public Builder setPacketRate(double value) {

            packetRate_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>double packet_rate = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketRate() {
            bitField0_ &= ~0x00000010;
            packetRate_ = 0D;
            onChanged();
            return this;
        }

        private long bytes_;

        /**
         * <code>uint64 bytes = 6;</code>
         *
         * @return The bytes.
         */
        @java.lang.Override
        public long getBytes() {
            return bytes_;
        }

        /**
         * <code>uint64 bytes = 6;</code>
         *
         * @param value The bytes to set.
         * @return This builder for chaining.
         */
        public Builder setBytes(long value) {

            bytes_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 bytes = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBytes() {
            bitField0_ &= ~0x00000020;
            bytes_ = 0L;
            onChanged();
            return this;
        }

        private long headerBytes_;

        /**
         * <code>uint64 header_bytes = 39;</code>
         *
         * @return The headerBytes.
         */
        @java.lang.Override
        public long getHeaderBytes() {
            return headerBytes_;
        }

        /**
         * <code>uint64 header_bytes = 39;</code>
         *
         * @param value The headerBytes to set.
         * @return This builder for chaining.
         */
        public Builder setHeaderBytes(long value) {

            headerBytes_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 header_bytes = 39;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeaderBytes() {
            bitField0_ &= ~0x00000040;
            headerBytes_ = 0L;
            onChanged();
            return this;
        }

        private double bitrate_;

        /**
         * <code>double bitrate = 7;</code>
         *
         * @return The bitrate.
         */
        @java.lang.Override
        public double getBitrate() {
            return bitrate_;
        }

        /**
         * <code>double bitrate = 7;</code>
         *
         * @param value The bitrate to set.
         * @return This builder for chaining.
         */
        public Builder setBitrate(double value) {

            bitrate_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>double bitrate = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBitrate() {
            bitField0_ &= ~0x00000080;
            bitrate_ = 0D;
            onChanged();
            return this;
        }

        private int packetsLost_;

        /**
         * <code>uint32 packets_lost = 8;</code>
         *
         * @return The packetsLost.
         */
        @java.lang.Override
        public int getPacketsLost() {
            return packetsLost_;
        }

        /**
         * <code>uint32 packets_lost = 8;</code>
         *
         * @param value The packetsLost to set.
         * @return This builder for chaining.
         */
        public Builder setPacketsLost(int value) {

            packetsLost_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 packets_lost = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketsLost() {
            bitField0_ &= ~0x00000100;
            packetsLost_ = 0;
            onChanged();
            return this;
        }

        private double packetLossRate_;

        /**
         * <code>double packet_loss_rate = 9;</code>
         *
         * @return The packetLossRate.
         */
        @java.lang.Override
        public double getPacketLossRate() {
            return packetLossRate_;
        }

        /**
         * <code>double packet_loss_rate = 9;</code>
         *
         * @param value The packetLossRate to set.
         * @return This builder for chaining.
         */
        public Builder setPacketLossRate(double value) {

            packetLossRate_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>double packet_loss_rate = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketLossRate() {
            bitField0_ &= ~0x00000200;
            packetLossRate_ = 0D;
            onChanged();
            return this;
        }

        private float packetLossPercentage_;

        /**
         * <code>float packet_loss_percentage = 10;</code>
         *
         * @return The packetLossPercentage.
         */
        @java.lang.Override
        public float getPacketLossPercentage() {
            return packetLossPercentage_;
        }

        /**
         * <code>float packet_loss_percentage = 10;</code>
         *
         * @param value The packetLossPercentage to set.
         * @return This builder for chaining.
         */
        public Builder setPacketLossPercentage(float value) {

            packetLossPercentage_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>float packet_loss_percentage = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketLossPercentage() {
            bitField0_ &= ~0x00000400;
            packetLossPercentage_ = 0F;
            onChanged();
            return this;
        }

        private int packetsDuplicate_;

        /**
         * <code>uint32 packets_duplicate = 11;</code>
         *
         * @return The packetsDuplicate.
         */
        @java.lang.Override
        public int getPacketsDuplicate() {
            return packetsDuplicate_;
        }

        /**
         * <code>uint32 packets_duplicate = 11;</code>
         *
         * @param value The packetsDuplicate to set.
         * @return This builder for chaining.
         */
        public Builder setPacketsDuplicate(int value) {

            packetsDuplicate_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 packets_duplicate = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketsDuplicate() {
            bitField0_ &= ~0x00000800;
            packetsDuplicate_ = 0;
            onChanged();
            return this;
        }

        private double packetDuplicateRate_;

        /**
         * <code>double packet_duplicate_rate = 12;</code>
         *
         * @return The packetDuplicateRate.
         */
        @java.lang.Override
        public double getPacketDuplicateRate() {
            return packetDuplicateRate_;
        }

        /**
         * <code>double packet_duplicate_rate = 12;</code>
         *
         * @param value The packetDuplicateRate to set.
         * @return This builder for chaining.
         */
        public Builder setPacketDuplicateRate(double value) {

            packetDuplicateRate_ = value;
            bitField0_ |= 0x00001000;
            onChanged();
            return this;
        }

        /**
         * <code>double packet_duplicate_rate = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketDuplicateRate() {
            bitField0_ &= ~0x00001000;
            packetDuplicateRate_ = 0D;
            onChanged();
            return this;
        }

        private long bytesDuplicate_;

        /**
         * <code>uint64 bytes_duplicate = 13;</code>
         *
         * @return The bytesDuplicate.
         */
        @java.lang.Override
        public long getBytesDuplicate() {
            return bytesDuplicate_;
        }

        /**
         * <code>uint64 bytes_duplicate = 13;</code>
         *
         * @param value The bytesDuplicate to set.
         * @return This builder for chaining.
         */
        public Builder setBytesDuplicate(long value) {

            bytesDuplicate_ = value;
            bitField0_ |= 0x00002000;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 bytes_duplicate = 13;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBytesDuplicate() {
            bitField0_ &= ~0x00002000;
            bytesDuplicate_ = 0L;
            onChanged();
            return this;
        }

        private long headerBytesDuplicate_;

        /**
         * <code>uint64 header_bytes_duplicate = 40;</code>
         *
         * @return The headerBytesDuplicate.
         */
        @java.lang.Override
        public long getHeaderBytesDuplicate() {
            return headerBytesDuplicate_;
        }

        /**
         * <code>uint64 header_bytes_duplicate = 40;</code>
         *
         * @param value The headerBytesDuplicate to set.
         * @return This builder for chaining.
         */
        public Builder setHeaderBytesDuplicate(long value) {

            headerBytesDuplicate_ = value;
            bitField0_ |= 0x00004000;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 header_bytes_duplicate = 40;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeaderBytesDuplicate() {
            bitField0_ &= ~0x00004000;
            headerBytesDuplicate_ = 0L;
            onChanged();
            return this;
        }

        private double bitrateDuplicate_;

        /**
         * <code>double bitrate_duplicate = 14;</code>
         *
         * @return The bitrateDuplicate.
         */
        @java.lang.Override
        public double getBitrateDuplicate() {
            return bitrateDuplicate_;
        }

        /**
         * <code>double bitrate_duplicate = 14;</code>
         *
         * @param value The bitrateDuplicate to set.
         * @return This builder for chaining.
         */
        public Builder setBitrateDuplicate(double value) {

            bitrateDuplicate_ = value;
            bitField0_ |= 0x00008000;
            onChanged();
            return this;
        }

        /**
         * <code>double bitrate_duplicate = 14;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBitrateDuplicate() {
            bitField0_ &= ~0x00008000;
            bitrateDuplicate_ = 0D;
            onChanged();
            return this;
        }

        private int packetsPadding_;

        /**
         * <code>uint32 packets_padding = 15;</code>
         *
         * @return The packetsPadding.
         */
        @java.lang.Override
        public int getPacketsPadding() {
            return packetsPadding_;
        }

        /**
         * <code>uint32 packets_padding = 15;</code>
         *
         * @param value The packetsPadding to set.
         * @return This builder for chaining.
         */
        public Builder setPacketsPadding(int value) {

            packetsPadding_ = value;
            bitField0_ |= 0x00010000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 packets_padding = 15;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketsPadding() {
            bitField0_ &= ~0x00010000;
            packetsPadding_ = 0;
            onChanged();
            return this;
        }

        private double packetPaddingRate_;

        /**
         * <code>double packet_padding_rate = 16;</code>
         *
         * @return The packetPaddingRate.
         */
        @java.lang.Override
        public double getPacketPaddingRate() {
            return packetPaddingRate_;
        }

        /**
         * <code>double packet_padding_rate = 16;</code>
         *
         * @param value The packetPaddingRate to set.
         * @return This builder for chaining.
         */
        public Builder setPacketPaddingRate(double value) {

            packetPaddingRate_ = value;
            bitField0_ |= 0x00020000;
            onChanged();
            return this;
        }

        /**
         * <code>double packet_padding_rate = 16;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketPaddingRate() {
            bitField0_ &= ~0x00020000;
            packetPaddingRate_ = 0D;
            onChanged();
            return this;
        }

        private long bytesPadding_;

        /**
         * <code>uint64 bytes_padding = 17;</code>
         *
         * @return The bytesPadding.
         */
        @java.lang.Override
        public long getBytesPadding() {
            return bytesPadding_;
        }

        /**
         * <code>uint64 bytes_padding = 17;</code>
         *
         * @param value The bytesPadding to set.
         * @return This builder for chaining.
         */
        public Builder setBytesPadding(long value) {

            bytesPadding_ = value;
            bitField0_ |= 0x00040000;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 bytes_padding = 17;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBytesPadding() {
            bitField0_ &= ~0x00040000;
            bytesPadding_ = 0L;
            onChanged();
            return this;
        }

        private long headerBytesPadding_;

        /**
         * <code>uint64 header_bytes_padding = 41;</code>
         *
         * @return The headerBytesPadding.
         */
        @java.lang.Override
        public long getHeaderBytesPadding() {
            return headerBytesPadding_;
        }

        /**
         * <code>uint64 header_bytes_padding = 41;</code>
         *
         * @param value The headerBytesPadding to set.
         * @return This builder for chaining.
         */
        public Builder setHeaderBytesPadding(long value) {

            headerBytesPadding_ = value;
            bitField0_ |= 0x00080000;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 header_bytes_padding = 41;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeaderBytesPadding() {
            bitField0_ &= ~0x00080000;
            headerBytesPadding_ = 0L;
            onChanged();
            return this;
        }

        private double bitratePadding_;

        /**
         * <code>double bitrate_padding = 18;</code>
         *
         * @return The bitratePadding.
         */
        @java.lang.Override
        public double getBitratePadding() {
            return bitratePadding_;
        }

        /**
         * <code>double bitrate_padding = 18;</code>
         *
         * @param value The bitratePadding to set.
         * @return This builder for chaining.
         */
        public Builder setBitratePadding(double value) {

            bitratePadding_ = value;
            bitField0_ |= 0x00100000;
            onChanged();
            return this;
        }

        /**
         * <code>double bitrate_padding = 18;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBitratePadding() {
            bitField0_ &= ~0x00100000;
            bitratePadding_ = 0D;
            onChanged();
            return this;
        }

        private int packetsOutOfOrder_;

        /**
         * <code>uint32 packets_out_of_order = 19;</code>
         *
         * @return The packetsOutOfOrder.
         */
        @java.lang.Override
        public int getPacketsOutOfOrder() {
            return packetsOutOfOrder_;
        }

        /**
         * <code>uint32 packets_out_of_order = 19;</code>
         *
         * @param value The packetsOutOfOrder to set.
         * @return This builder for chaining.
         */
        public Builder setPacketsOutOfOrder(int value) {

            packetsOutOfOrder_ = value;
            bitField0_ |= 0x00200000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 packets_out_of_order = 19;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPacketsOutOfOrder() {
            bitField0_ &= ~0x00200000;
            packetsOutOfOrder_ = 0;
            onChanged();
            return this;
        }

        private int frames_;

        /**
         * <code>uint32 frames = 20;</code>
         *
         * @return The frames.
         */
        @java.lang.Override
        public int getFrames() {
            return frames_;
        }

        /**
         * <code>uint32 frames = 20;</code>
         *
         * @param value The frames to set.
         * @return This builder for chaining.
         */
        public Builder setFrames(int value) {

            frames_ = value;
            bitField0_ |= 0x00400000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 frames = 20;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFrames() {
            bitField0_ &= ~0x00400000;
            frames_ = 0;
            onChanged();
            return this;
        }

        private double frameRate_;

        /**
         * <code>double frame_rate = 21;</code>
         *
         * @return The frameRate.
         */
        @java.lang.Override
        public double getFrameRate() {
            return frameRate_;
        }

        /**
         * <code>double frame_rate = 21;</code>
         *
         * @param value The frameRate to set.
         * @return This builder for chaining.
         */
        public Builder setFrameRate(double value) {

            frameRate_ = value;
            bitField0_ |= 0x00800000;
            onChanged();
            return this;
        }

        /**
         * <code>double frame_rate = 21;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFrameRate() {
            bitField0_ &= ~0x00800000;
            frameRate_ = 0D;
            onChanged();
            return this;
        }

        private double jitterCurrent_;

        /**
         * <code>double jitter_current = 22;</code>
         *
         * @return The jitterCurrent.
         */
        @java.lang.Override
        public double getJitterCurrent() {
            return jitterCurrent_;
        }

        /**
         * <code>double jitter_current = 22;</code>
         *
         * @param value The jitterCurrent to set.
         * @return This builder for chaining.
         */
        public Builder setJitterCurrent(double value) {

            jitterCurrent_ = value;
            bitField0_ |= 0x01000000;
            onChanged();
            return this;
        }

        /**
         * <code>double jitter_current = 22;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJitterCurrent() {
            bitField0_ &= ~0x01000000;
            jitterCurrent_ = 0D;
            onChanged();
            return this;
        }

        private double jitterMax_;

        /**
         * <code>double jitter_max = 23;</code>
         *
         * @return The jitterMax.
         */
        @java.lang.Override
        public double getJitterMax() {
            return jitterMax_;
        }

        /**
         * <code>double jitter_max = 23;</code>
         *
         * @param value The jitterMax to set.
         * @return This builder for chaining.
         */
        public Builder setJitterMax(double value) {

            jitterMax_ = value;
            bitField0_ |= 0x02000000;
            onChanged();
            return this;
        }

        /**
         * <code>double jitter_max = 23;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJitterMax() {
            bitField0_ &= ~0x02000000;
            jitterMax_ = 0D;
            onChanged();
            return this;
        }

        private com.google.protobuf.MapField<java.lang.Integer, java.lang.Integer> gapHistogram_;

        private com.google.protobuf.MapField<java.lang.Integer, java.lang.Integer> internalGetGapHistogram() {
            if (gapHistogram_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(GapHistogramDefaultEntryHolder.defaultEntry);
            }
            return gapHistogram_;
        }

        private com.google.protobuf.MapField<java.lang.Integer, java.lang.Integer> internalGetMutableGapHistogram() {
            if (gapHistogram_ == null) {
                gapHistogram_ = com.google.protobuf.MapField
                        .newMapField(GapHistogramDefaultEntryHolder.defaultEntry);
            }
            if (!gapHistogram_.isMutable()) {
                gapHistogram_ = gapHistogram_.copy();
            }
            bitField0_ |= 0x04000000;
            onChanged();
            return gapHistogram_;
        }

        public int getGapHistogramCount() {
            return internalGetGapHistogram().getMap()
                    .size();
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        @java.lang.Override
        public boolean containsGapHistogram(int key) {

            return internalGetGapHistogram().getMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getGapHistogramMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.Integer, java.lang.Integer> getGapHistogram() {
            return getGapHistogramMap();
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.Integer, java.lang.Integer> getGapHistogramMap() {
            return internalGetGapHistogram().getMap();
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        @java.lang.Override
        public int getGapHistogramOrDefault(int key, int defaultValue) {

            java.util.Map<java.lang.Integer, java.lang.Integer> map =
                    internalGetGapHistogram().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        @java.lang.Override
        public int getGapHistogramOrThrow(int key) {

            java.util.Map<java.lang.Integer, java.lang.Integer> map =
                    internalGetGapHistogram().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearGapHistogram() {
            bitField0_ &= ~0x04000000;
            internalGetMutableGapHistogram().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        public Builder removeGapHistogram(int key) {

            internalGetMutableGapHistogram().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.Integer, java.lang.Integer> getMutableGapHistogram() {
            bitField0_ |= 0x04000000;
            return internalGetMutableGapHistogram().getMutableMap();
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        public Builder putGapHistogram(int key, int value) {

            internalGetMutableGapHistogram().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x04000000;
            return this;
        }

        /**
         * <code>map&lt;int32, uint32&gt; gap_histogram = 24;</code>
         */
        public Builder putAllGapHistogram(
                java.util.Map<java.lang.Integer, java.lang.Integer> values) {
            internalGetMutableGapHistogram().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x04000000;
            return this;
        }

        private int nacks_;

        /**
         * <code>uint32 nacks = 25;</code>
         *
         * @return The nacks.
         */
        @java.lang.Override
        public int getNacks() {
            return nacks_;
        }

        /**
         * <code>uint32 nacks = 25;</code>
         *
         * @param value The nacks to set.
         * @return This builder for chaining.
         */
        public Builder setNacks(int value) {

            nacks_ = value;
            bitField0_ |= 0x08000000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 nacks = 25;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNacks() {
            bitField0_ &= ~0x08000000;
            nacks_ = 0;
            onChanged();
            return this;
        }

        private int nackAcks_;

        /**
         * <code>uint32 nack_acks = 37;</code>
         *
         * @return The nackAcks.
         */
        @java.lang.Override
        public int getNackAcks() {
            return nackAcks_;
        }

        /**
         * <code>uint32 nack_acks = 37;</code>
         *
         * @param value The nackAcks to set.
         * @return This builder for chaining.
         */
        public Builder setNackAcks(int value) {

            nackAcks_ = value;
            bitField0_ |= 0x10000000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 nack_acks = 37;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNackAcks() {
            bitField0_ &= ~0x10000000;
            nackAcks_ = 0;
            onChanged();
            return this;
        }

        private int nackMisses_;

        /**
         * <code>uint32 nack_misses = 26;</code>
         *
         * @return The nackMisses.
         */
        @java.lang.Override
        public int getNackMisses() {
            return nackMisses_;
        }

        /**
         * <code>uint32 nack_misses = 26;</code>
         *
         * @param value The nackMisses to set.
         * @return This builder for chaining.
         */
        public Builder setNackMisses(int value) {

            nackMisses_ = value;
            bitField0_ |= 0x20000000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 nack_misses = 26;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNackMisses() {
            bitField0_ &= ~0x20000000;
            nackMisses_ = 0;
            onChanged();
            return this;
        }

        private int nackRepeated_;

        /**
         * <code>uint32 nack_repeated = 38;</code>
         *
         * @return The nackRepeated.
         */
        @java.lang.Override
        public int getNackRepeated() {
            return nackRepeated_;
        }

        /**
         * <code>uint32 nack_repeated = 38;</code>
         *
         * @param value The nackRepeated to set.
         * @return This builder for chaining.
         */
        public Builder setNackRepeated(int value) {

            nackRepeated_ = value;
            bitField0_ |= 0x40000000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 nack_repeated = 38;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNackRepeated() {
            bitField0_ &= ~0x40000000;
            nackRepeated_ = 0;
            onChanged();
            return this;
        }

        private int plis_;

        /**
         * <code>uint32 plis = 27;</code>
         *
         * @return The plis.
         */
        @java.lang.Override
        public int getPlis() {
            return plis_;
        }

        /**
         * <code>uint32 plis = 27;</code>
         *
         * @param value The plis to set.
         * @return This builder for chaining.
         */
        public Builder setPlis(int value) {

            plis_ = value;
            bitField0_ |= 0x80000000;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 plis = 27;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPlis() {
            bitField0_ &= ~0x80000000;
            plis_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.Timestamp lastPli_;
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> lastPliBuilder_;

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         *
         * @return Whether the lastPli field is set.
         */
        public boolean hasLastPli() {
            return ((bitField1_ & 0x00000001) != 0);
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         *
         * @return The lastPli.
         */
        public com.google.protobuf.Timestamp getLastPli() {
            if (lastPliBuilder_ == null) {
                return lastPli_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastPli_;
            } else {
                return lastPliBuilder_.getMessage();
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        public Builder setLastPli(com.google.protobuf.Timestamp value) {
            if (lastPliBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                lastPli_ = value;
            } else {
                lastPliBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        public Builder setLastPli(com.google.protobuf.Timestamp.Builder builderForValue) {
            if (lastPliBuilder_ == null) {
                lastPli_ = builderForValue.build();
            } else {
                lastPliBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        public Builder mergeLastPli(com.google.protobuf.Timestamp value) {
            if (lastPliBuilder_ == null) {
                if (((bitField1_ & 0x00000001) != 0)
                        && lastPli_ != null
                        && lastPli_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
                    getLastPliBuilder().mergeFrom(value);
                } else {
                    lastPli_ = value;
                }
            } else {
                lastPliBuilder_.mergeFrom(value);
            }
            if (lastPli_ != null) {
                bitField1_ |= 0x00000001;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        public Builder clearLastPli() {
            bitField1_ &= ~0x00000001;
            lastPli_ = null;
            if (lastPliBuilder_ != null) {
                lastPliBuilder_.dispose();
                lastPliBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        public com.google.protobuf.Timestamp.Builder getLastPliBuilder() {
            bitField1_ |= 0x00000001;
            onChanged();
            return getLastPliFieldBuilder().getBuilder();
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        public com.google.protobuf.TimestampOrBuilder getLastPliOrBuilder() {
            if (lastPliBuilder_ != null) {
                return lastPliBuilder_.getMessageOrBuilder();
            } else {
                return lastPli_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastPli_;
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_pli = 28;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> getLastPliFieldBuilder() {
            if (lastPliBuilder_ == null) {
                lastPliBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getLastPli(),
                        getParentForChildren(),
                        isClean());
                lastPli_ = null;
            }
            return lastPliBuilder_;
        }

        private int firs_;

        /**
         * <code>uint32 firs = 29;</code>
         *
         * @return The firs.
         */
        @java.lang.Override
        public int getFirs() {
            return firs_;
        }

        /**
         * <code>uint32 firs = 29;</code>
         *
         * @param value The firs to set.
         * @return This builder for chaining.
         */
        public Builder setFirs(int value) {

            firs_ = value;
            bitField1_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 firs = 29;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFirs() {
            bitField1_ &= ~0x00000002;
            firs_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.Timestamp lastFir_;
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> lastFirBuilder_;

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         *
         * @return Whether the lastFir field is set.
         */
        public boolean hasLastFir() {
            return ((bitField1_ & 0x00000004) != 0);
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         *
         * @return The lastFir.
         */
        public com.google.protobuf.Timestamp getLastFir() {
            if (lastFirBuilder_ == null) {
                return lastFir_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastFir_;
            } else {
                return lastFirBuilder_.getMessage();
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        public Builder setLastFir(com.google.protobuf.Timestamp value) {
            if (lastFirBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                lastFir_ = value;
            } else {
                lastFirBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        public Builder setLastFir(com.google.protobuf.Timestamp.Builder builderForValue) {
            if (lastFirBuilder_ == null) {
                lastFir_ = builderForValue.build();
            } else {
                lastFirBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        public Builder mergeLastFir(com.google.protobuf.Timestamp value) {
            if (lastFirBuilder_ == null) {
                if (((bitField1_ & 0x00000004) != 0)
                        && lastFir_ != null
                        && lastFir_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
                    getLastFirBuilder().mergeFrom(value);
                } else {
                    lastFir_ = value;
                }
            } else {
                lastFirBuilder_.mergeFrom(value);
            }
            if (lastFir_ != null) {
                bitField1_ |= 0x00000004;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        public Builder clearLastFir() {
            bitField1_ &= ~0x00000004;
            lastFir_ = null;
            if (lastFirBuilder_ != null) {
                lastFirBuilder_.dispose();
                lastFirBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        public com.google.protobuf.Timestamp.Builder getLastFirBuilder() {
            bitField1_ |= 0x00000004;
            onChanged();
            return getLastFirFieldBuilder().getBuilder();
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        public com.google.protobuf.TimestampOrBuilder getLastFirOrBuilder() {
            if (lastFirBuilder_ != null) {
                return lastFirBuilder_.getMessageOrBuilder();
            } else {
                return lastFir_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastFir_;
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_fir = 30;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> getLastFirFieldBuilder() {
            if (lastFirBuilder_ == null) {
                lastFirBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getLastFir(),
                        getParentForChildren(),
                        isClean());
                lastFir_ = null;
            }
            return lastFirBuilder_;
        }

        private int rttCurrent_;

        /**
         * <code>uint32 rtt_current = 31;</code>
         *
         * @return The rttCurrent.
         */
        @java.lang.Override
        public int getRttCurrent() {
            return rttCurrent_;
        }

        /**
         * <code>uint32 rtt_current = 31;</code>
         *
         * @param value The rttCurrent to set.
         * @return This builder for chaining.
         */
        public Builder setRttCurrent(int value) {

            rttCurrent_ = value;
            bitField1_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 rtt_current = 31;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRttCurrent() {
            bitField1_ &= ~0x00000008;
            rttCurrent_ = 0;
            onChanged();
            return this;
        }

        private int rttMax_;

        /**
         * <code>uint32 rtt_max = 32;</code>
         *
         * @return The rttMax.
         */
        @java.lang.Override
        public int getRttMax() {
            return rttMax_;
        }

        /**
         * <code>uint32 rtt_max = 32;</code>
         *
         * @param value The rttMax to set.
         * @return This builder for chaining.
         */
        public Builder setRttMax(int value) {

            rttMax_ = value;
            bitField1_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 rtt_max = 32;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRttMax() {
            bitField1_ &= ~0x00000010;
            rttMax_ = 0;
            onChanged();
            return this;
        }

        private int keyFrames_;

        /**
         * <code>uint32 key_frames = 33;</code>
         *
         * @return The keyFrames.
         */
        @java.lang.Override
        public int getKeyFrames() {
            return keyFrames_;
        }

        /**
         * <code>uint32 key_frames = 33;</code>
         *
         * @param value The keyFrames to set.
         * @return This builder for chaining.
         */
        public Builder setKeyFrames(int value) {

            keyFrames_ = value;
            bitField1_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 key_frames = 33;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearKeyFrames() {
            bitField1_ &= ~0x00000020;
            keyFrames_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.Timestamp lastKeyFrame_;
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> lastKeyFrameBuilder_;

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         *
         * @return Whether the lastKeyFrame field is set.
         */
        public boolean hasLastKeyFrame() {
            return ((bitField1_ & 0x00000040) != 0);
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         *
         * @return The lastKeyFrame.
         */
        public com.google.protobuf.Timestamp getLastKeyFrame() {
            if (lastKeyFrameBuilder_ == null) {
                return lastKeyFrame_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastKeyFrame_;
            } else {
                return lastKeyFrameBuilder_.getMessage();
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        public Builder setLastKeyFrame(com.google.protobuf.Timestamp value) {
            if (lastKeyFrameBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                lastKeyFrame_ = value;
            } else {
                lastKeyFrameBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        public Builder setLastKeyFrame(com.google.protobuf.Timestamp.Builder builderForValue) {
            if (lastKeyFrameBuilder_ == null) {
                lastKeyFrame_ = builderForValue.build();
            } else {
                lastKeyFrameBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        public Builder mergeLastKeyFrame(com.google.protobuf.Timestamp value) {
            if (lastKeyFrameBuilder_ == null) {
                if (((bitField1_ & 0x00000040) != 0)
                        && lastKeyFrame_ != null
                        && lastKeyFrame_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
                    getLastKeyFrameBuilder().mergeFrom(value);
                } else {
                    lastKeyFrame_ = value;
                }
            } else {
                lastKeyFrameBuilder_.mergeFrom(value);
            }
            if (lastKeyFrame_ != null) {
                bitField1_ |= 0x00000040;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        public Builder clearLastKeyFrame() {
            bitField1_ &= ~0x00000040;
            lastKeyFrame_ = null;
            if (lastKeyFrameBuilder_ != null) {
                lastKeyFrameBuilder_.dispose();
                lastKeyFrameBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        public com.google.protobuf.Timestamp.Builder getLastKeyFrameBuilder() {
            bitField1_ |= 0x00000040;
            onChanged();
            return getLastKeyFrameFieldBuilder().getBuilder();
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        public com.google.protobuf.TimestampOrBuilder getLastKeyFrameOrBuilder() {
            if (lastKeyFrameBuilder_ != null) {
                return lastKeyFrameBuilder_.getMessageOrBuilder();
            } else {
                return lastKeyFrame_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastKeyFrame_;
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_key_frame = 34;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> getLastKeyFrameFieldBuilder() {
            if (lastKeyFrameBuilder_ == null) {
                lastKeyFrameBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getLastKeyFrame(),
                        getParentForChildren(),
                        isClean());
                lastKeyFrame_ = null;
            }
            return lastKeyFrameBuilder_;
        }

        private int layerLockPlis_;

        /**
         * <code>uint32 layer_lock_plis = 35;</code>
         *
         * @return The layerLockPlis.
         */
        @java.lang.Override
        public int getLayerLockPlis() {
            return layerLockPlis_;
        }

        /**
         * <code>uint32 layer_lock_plis = 35;</code>
         *
         * @param value The layerLockPlis to set.
         * @return This builder for chaining.
         */
        public Builder setLayerLockPlis(int value) {

            layerLockPlis_ = value;
            bitField1_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 layer_lock_plis = 35;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLayerLockPlis() {
            bitField1_ &= ~0x00000080;
            layerLockPlis_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.Timestamp lastLayerLockPli_;
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> lastLayerLockPliBuilder_;

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         *
         * @return Whether the lastLayerLockPli field is set.
         */
        public boolean hasLastLayerLockPli() {
            return ((bitField1_ & 0x00000100) != 0);
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         *
         * @return The lastLayerLockPli.
         */
        public com.google.protobuf.Timestamp getLastLayerLockPli() {
            if (lastLayerLockPliBuilder_ == null) {
                return lastLayerLockPli_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastLayerLockPli_;
            } else {
                return lastLayerLockPliBuilder_.getMessage();
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        public Builder setLastLayerLockPli(com.google.protobuf.Timestamp value) {
            if (lastLayerLockPliBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                lastLayerLockPli_ = value;
            } else {
                lastLayerLockPliBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        public Builder setLastLayerLockPli(com.google.protobuf.Timestamp.Builder builderForValue) {
            if (lastLayerLockPliBuilder_ == null) {
                lastLayerLockPli_ = builderForValue.build();
            } else {
                lastLayerLockPliBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        public Builder mergeLastLayerLockPli(com.google.protobuf.Timestamp value) {
            if (lastLayerLockPliBuilder_ == null) {
                if (((bitField1_ & 0x00000100) != 0)
                        && lastLayerLockPli_ != null
                        && lastLayerLockPli_ != com.google.protobuf.Timestamp
                                .getDefaultInstance()) {
                    getLastLayerLockPliBuilder().mergeFrom(value);
                } else {
                    lastLayerLockPli_ = value;
                }
            } else {
                lastLayerLockPliBuilder_.mergeFrom(value);
            }
            if (lastLayerLockPli_ != null) {
                bitField1_ |= 0x00000100;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        public Builder clearLastLayerLockPli() {
            bitField1_ &= ~0x00000100;
            lastLayerLockPli_ = null;
            if (lastLayerLockPliBuilder_ != null) {
                lastLayerLockPliBuilder_.dispose();
                lastLayerLockPliBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        public com.google.protobuf.Timestamp.Builder getLastLayerLockPliBuilder() {
            bitField1_ |= 0x00000100;
            onChanged();
            return getLastLayerLockPliFieldBuilder().getBuilder();
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        public com.google.protobuf.TimestampOrBuilder getLastLayerLockPliOrBuilder() {
            if (lastLayerLockPliBuilder_ != null) {
                return lastLayerLockPliBuilder_.getMessageOrBuilder();
            } else {
                return lastLayerLockPli_ == null
                        ? com.google.protobuf.Timestamp.getDefaultInstance()
                        : lastLayerLockPli_;
            }
        }

        /**
         * <code>.google.protobuf.Timestamp last_layer_lock_pli = 36;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> getLastLayerLockPliFieldBuilder() {
            if (lastLayerLockPliBuilder_ == null) {
                lastLayerLockPliBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getLastLayerLockPli(),
                        getParentForChildren(),
                        isClean());
                lastLayerLockPli_ = null;
            }
            return lastLayerLockPliBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.models.RTPDrift packetDrift_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.RTPDrift, im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder, im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder> packetDriftBuilder_;

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         *
         * @return Whether the packetDrift field is set.
         */
        public boolean hasPacketDrift() {
            return ((bitField1_ & 0x00000200) != 0);
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         *
         * @return The packetDrift.
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDrift getPacketDrift() {
            if (packetDriftBuilder_ == null) {
                return packetDrift_ == null
                        ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                        : packetDrift_;
            } else {
                return packetDriftBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        public Builder setPacketDrift(im.turms.plugin.livekit.core.proto.models.RTPDrift value) {
            if (packetDriftBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                packetDrift_ = value;
            } else {
                packetDriftBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        public Builder setPacketDrift(
                im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder builderForValue) {
            if (packetDriftBuilder_ == null) {
                packetDrift_ = builderForValue.build();
            } else {
                packetDriftBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        public Builder mergePacketDrift(im.turms.plugin.livekit.core.proto.models.RTPDrift value) {
            if (packetDriftBuilder_ == null) {
                if (((bitField1_ & 0x00000200) != 0)
                        && packetDrift_ != null
                        && packetDrift_ != im.turms.plugin.livekit.core.proto.models.RTPDrift
                                .getDefaultInstance()) {
                    getPacketDriftBuilder().mergeFrom(value);
                } else {
                    packetDrift_ = value;
                }
            } else {
                packetDriftBuilder_.mergeFrom(value);
            }
            if (packetDrift_ != null) {
                bitField1_ |= 0x00000200;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        public Builder clearPacketDrift() {
            bitField1_ &= ~0x00000200;
            packetDrift_ = null;
            if (packetDriftBuilder_ != null) {
                packetDriftBuilder_.dispose();
                packetDriftBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder getPacketDriftBuilder() {
            bitField1_ |= 0x00000200;
            onChanged();
            return getPacketDriftFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getPacketDriftOrBuilder() {
            if (packetDriftBuilder_ != null) {
                return packetDriftBuilder_.getMessageOrBuilder();
            } else {
                return packetDrift_ == null
                        ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                        : packetDrift_;
            }
        }

        /**
         * <code>.livekit.RTPDrift packet_drift = 44;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.RTPDrift, im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder, im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder> getPacketDriftFieldBuilder() {
            if (packetDriftBuilder_ == null) {
                packetDriftBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getPacketDrift(),
                        getParentForChildren(),
                        isClean());
                packetDrift_ = null;
            }
            return packetDriftBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.models.RTPDrift reportDrift_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.RTPDrift, im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder, im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder> reportDriftBuilder_;

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         *
         * @return Whether the reportDrift field is set.
         */
        public boolean hasReportDrift() {
            return ((bitField1_ & 0x00000400) != 0);
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         *
         * @return The reportDrift.
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDrift getReportDrift() {
            if (reportDriftBuilder_ == null) {
                return reportDrift_ == null
                        ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                        : reportDrift_;
            } else {
                return reportDriftBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        public Builder setReportDrift(im.turms.plugin.livekit.core.proto.models.RTPDrift value) {
            if (reportDriftBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                reportDrift_ = value;
            } else {
                reportDriftBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        public Builder setReportDrift(
                im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder builderForValue) {
            if (reportDriftBuilder_ == null) {
                reportDrift_ = builderForValue.build();
            } else {
                reportDriftBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        public Builder mergeReportDrift(im.turms.plugin.livekit.core.proto.models.RTPDrift value) {
            if (reportDriftBuilder_ == null) {
                if (((bitField1_ & 0x00000400) != 0)
                        && reportDrift_ != null
                        && reportDrift_ != im.turms.plugin.livekit.core.proto.models.RTPDrift
                                .getDefaultInstance()) {
                    getReportDriftBuilder().mergeFrom(value);
                } else {
                    reportDrift_ = value;
                }
            } else {
                reportDriftBuilder_.mergeFrom(value);
            }
            if (reportDrift_ != null) {
                bitField1_ |= 0x00000400;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        public Builder clearReportDrift() {
            bitField1_ &= ~0x00000400;
            reportDrift_ = null;
            if (reportDriftBuilder_ != null) {
                reportDriftBuilder_.dispose();
                reportDriftBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder getReportDriftBuilder() {
            bitField1_ |= 0x00000400;
            onChanged();
            return getReportDriftFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getReportDriftOrBuilder() {
            if (reportDriftBuilder_ != null) {
                return reportDriftBuilder_.getMessageOrBuilder();
            } else {
                return reportDrift_ == null
                        ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                        : reportDrift_;
            }
        }

        /**
         * <code>.livekit.RTPDrift report_drift = 45;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.RTPDrift, im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder, im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder> getReportDriftFieldBuilder() {
            if (reportDriftBuilder_ == null) {
                reportDriftBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getReportDrift(),
                        getParentForChildren(),
                        isClean());
                reportDrift_ = null;
            }
            return reportDriftBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.models.RTPDrift rebasedReportDrift_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.RTPDrift, im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder, im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder> rebasedReportDriftBuilder_;

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         *
         * @return Whether the rebasedReportDrift field is set.
         */
        public boolean hasRebasedReportDrift() {
            return ((bitField1_ & 0x00000800) != 0);
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         *
         * @return The rebasedReportDrift.
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDrift getRebasedReportDrift() {
            if (rebasedReportDriftBuilder_ == null) {
                return rebasedReportDrift_ == null
                        ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                        : rebasedReportDrift_;
            } else {
                return rebasedReportDriftBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        public Builder setRebasedReportDrift(
                im.turms.plugin.livekit.core.proto.models.RTPDrift value) {
            if (rebasedReportDriftBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                rebasedReportDrift_ = value;
            } else {
                rebasedReportDriftBuilder_.setMessage(value);
            }
            bitField1_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        public Builder setRebasedReportDrift(
                im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder builderForValue) {
            if (rebasedReportDriftBuilder_ == null) {
                rebasedReportDrift_ = builderForValue.build();
            } else {
                rebasedReportDriftBuilder_.setMessage(builderForValue.build());
            }
            bitField1_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        public Builder mergeRebasedReportDrift(
                im.turms.plugin.livekit.core.proto.models.RTPDrift value) {
            if (rebasedReportDriftBuilder_ == null) {
                if (((bitField1_ & 0x00000800) != 0)
                        && rebasedReportDrift_ != null
                        && rebasedReportDrift_ != im.turms.plugin.livekit.core.proto.models.RTPDrift
                                .getDefaultInstance()) {
                    getRebasedReportDriftBuilder().mergeFrom(value);
                } else {
                    rebasedReportDrift_ = value;
                }
            } else {
                rebasedReportDriftBuilder_.mergeFrom(value);
            }
            if (rebasedReportDrift_ != null) {
                bitField1_ |= 0x00000800;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        public Builder clearRebasedReportDrift() {
            bitField1_ &= ~0x00000800;
            rebasedReportDrift_ = null;
            if (rebasedReportDriftBuilder_ != null) {
                rebasedReportDriftBuilder_.dispose();
                rebasedReportDriftBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder getRebasedReportDriftBuilder() {
            bitField1_ |= 0x00000800;
            onChanged();
            return getRebasedReportDriftFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder getRebasedReportDriftOrBuilder() {
            if (rebasedReportDriftBuilder_ != null) {
                return rebasedReportDriftBuilder_.getMessageOrBuilder();
            } else {
                return rebasedReportDrift_ == null
                        ? im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()
                        : rebasedReportDrift_;
            }
        }

        /**
         * <pre>
         * NEXT_ID: 47
         * </pre>
         *
         * <code>.livekit.RTPDrift rebased_report_drift = 46;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.RTPDrift, im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder, im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder> getRebasedReportDriftFieldBuilder() {
            if (rebasedReportDriftBuilder_ == null) {
                rebasedReportDriftBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getRebasedReportDrift(),
                        getParentForChildren(),
                        isClean());
                rebasedReportDrift_ = null;
            }
            return rebasedReportDriftBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.RTPStats)
    }

    // @@protoc_insertion_point(class_scope:livekit.RTPStats)
    private static final im.turms.plugin.livekit.core.proto.models.RTPStats DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.RTPStats();
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPStats getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RTPStats> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public RTPStats parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    Builder builder = newBuilder();
                    try {
                        builder.mergeFrom(input, extensionRegistry);
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builder.buildPartial());
                    } catch (com.google.protobuf.UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException()
                                .setUnfinishedMessage(builder.buildPartial());
                    } catch (java.io.IOException e) {
                        throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                .setUnfinishedMessage(builder.buildPartial());
                    }
                    return builder.buildPartial();
                }
            };

    public static com.google.protobuf.Parser<RTPStats> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RTPStats> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPStats getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}