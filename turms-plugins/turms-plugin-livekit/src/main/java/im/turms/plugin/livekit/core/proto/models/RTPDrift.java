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
 * Protobuf type {@code livekit.RTPDrift}
 */
public final class RTPDrift extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.RTPDrift)
        RTPDriftOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                RTPDrift.class.getName());
    }

    // Use RTPDrift.newBuilder() to construct.
    private RTPDrift(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private RTPDrift() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPDrift_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPDrift_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.RTPDrift.class,
                        im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder.class);
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

    public static final int START_TIMESTAMP_FIELD_NUMBER = 4;
    private long startTimestamp_ = 0L;

    /**
     * <code>uint64 start_timestamp = 4;</code>
     *
     * @return The startTimestamp.
     */
    @java.lang.Override
    public long getStartTimestamp() {
        return startTimestamp_;
    }

    public static final int END_TIMESTAMP_FIELD_NUMBER = 5;
    private long endTimestamp_ = 0L;

    /**
     * <code>uint64 end_timestamp = 5;</code>
     *
     * @return The endTimestamp.
     */
    @java.lang.Override
    public long getEndTimestamp() {
        return endTimestamp_;
    }

    public static final int RTP_CLOCK_TICKS_FIELD_NUMBER = 6;
    private long rtpClockTicks_ = 0L;

    /**
     * <code>uint64 rtp_clock_ticks = 6;</code>
     *
     * @return The rtpClockTicks.
     */
    @java.lang.Override
    public long getRtpClockTicks() {
        return rtpClockTicks_;
    }

    public static final int DRIFT_SAMPLES_FIELD_NUMBER = 7;
    private long driftSamples_ = 0L;

    /**
     * <code>int64 drift_samples = 7;</code>
     *
     * @return The driftSamples.
     */
    @java.lang.Override
    public long getDriftSamples() {
        return driftSamples_;
    }

    public static final int DRIFT_MS_FIELD_NUMBER = 8;
    private double driftMs_ = 0D;

    /**
     * <code>double drift_ms = 8;</code>
     *
     * @return The driftMs.
     */
    @java.lang.Override
    public double getDriftMs() {
        return driftMs_;
    }

    public static final int CLOCK_RATE_FIELD_NUMBER = 9;
    private double clockRate_ = 0D;

    /**
     * <code>double clock_rate = 9;</code>
     *
     * @return The clockRate.
     */
    @java.lang.Override
    public double getClockRate() {
        return clockRate_;
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
        if (startTimestamp_ != 0L) {
            output.writeUInt64(4, startTimestamp_);
        }
        if (endTimestamp_ != 0L) {
            output.writeUInt64(5, endTimestamp_);
        }
        if (rtpClockTicks_ != 0L) {
            output.writeUInt64(6, rtpClockTicks_);
        }
        if (driftSamples_ != 0L) {
            output.writeInt64(7, driftSamples_);
        }
        if (java.lang.Double.doubleToRawLongBits(driftMs_) != 0) {
            output.writeDouble(8, driftMs_);
        }
        if (java.lang.Double.doubleToRawLongBits(clockRate_) != 0) {
            output.writeDouble(9, clockRate_);
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
        if (startTimestamp_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(4, startTimestamp_);
        }
        if (endTimestamp_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(5, endTimestamp_);
        }
        if (rtpClockTicks_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeUInt64Size(6, rtpClockTicks_);
        }
        if (driftSamples_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(7, driftSamples_);
        }
        if (java.lang.Double.doubleToRawLongBits(driftMs_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(8, driftMs_);
        }
        if (java.lang.Double.doubleToRawLongBits(clockRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(9, clockRate_);
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
        if (!(obj instanceof RTPDrift other)) {
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
        if (getStartTimestamp() != other.getStartTimestamp()) {
            return false;
        }
        if (getEndTimestamp() != other.getEndTimestamp()) {
            return false;
        }
        if (getRtpClockTicks() != other.getRtpClockTicks()) {
            return false;
        }
        if (getDriftSamples() != other.getDriftSamples()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getDriftMs()) != java.lang.Double
                .doubleToLongBits(other.getDriftMs())) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getClockRate()) != java.lang.Double
                .doubleToLongBits(other.getClockRate())) {
            return false;
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
        hash = (37 * hash) + START_TIMESTAMP_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartTimestamp());
        hash = (37 * hash) + END_TIMESTAMP_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndTimestamp());
        hash = (37 * hash) + RTP_CLOCK_TICKS_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRtpClockTicks());
        hash = (37 * hash) + DRIFT_SAMPLES_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDriftSamples());
        hash = (37 * hash) + DRIFT_MS_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getDriftMs()));
        hash = (37 * hash) + CLOCK_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getClockRate()));
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.models.RTPDrift prototype) {
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
     * Protobuf type {@code livekit.RTPDrift}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.RTPDrift)
            im.turms.plugin.livekit.core.proto.models.RTPDriftOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPDrift_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPDrift_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.RTPDrift.class,
                            im.turms.plugin.livekit.core.proto.models.RTPDrift.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.RTPDrift.newBuilder()
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
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
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
            startTimestamp_ = 0L;
            endTimestamp_ = 0L;
            rtpClockTicks_ = 0L;
            driftSamples_ = 0L;
            driftMs_ = 0D;
            clockRate_ = 0D;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_RTPDrift_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.RTPDrift getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.RTPDrift build() {
            im.turms.plugin.livekit.core.proto.models.RTPDrift result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.RTPDrift buildPartial() {
            im.turms.plugin.livekit.core.proto.models.RTPDrift result =
                    new im.turms.plugin.livekit.core.proto.models.RTPDrift(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.RTPDrift result) {
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
                result.startTimestamp_ = startTimestamp_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.endTimestamp_ = endTimestamp_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.rtpClockTicks_ = rtpClockTicks_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.driftSamples_ = driftSamples_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.driftMs_ = driftMs_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.clockRate_ = clockRate_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.RTPDrift) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.RTPDrift) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.RTPDrift other) {
            if (other == im.turms.plugin.livekit.core.proto.models.RTPDrift.getDefaultInstance()) {
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
            if (other.getStartTimestamp() != 0L) {
                setStartTimestamp(other.getStartTimestamp());
            }
            if (other.getEndTimestamp() != 0L) {
                setEndTimestamp(other.getEndTimestamp());
            }
            if (other.getRtpClockTicks() != 0L) {
                setRtpClockTicks(other.getRtpClockTicks());
            }
            if (other.getDriftSamples() != 0L) {
                setDriftSamples(other.getDriftSamples());
            }
            if (other.getDriftMs() != 0D) {
                setDriftMs(other.getDriftMs());
            }
            if (other.getClockRate() != 0D) {
                setClockRate(other.getClockRate());
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
                            startTimestamp_ = input.readUInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            endTimestamp_ = input.readUInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            rtpClockTicks_ = input.readUInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            driftSamples_ = input.readInt64();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 65 -> {
                            driftMs_ = input.readDouble();
                            bitField0_ |= 0x00000080;
                        } // case 65
                        case 73 -> {
                            clockRate_ = input.readDouble();
                            bitField0_ |= 0x00000100;
                        } // case 73
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

        private long startTimestamp_;

        /**
         * <code>uint64 start_timestamp = 4;</code>
         *
         * @return The startTimestamp.
         */
        @java.lang.Override
        public long getStartTimestamp() {
            return startTimestamp_;
        }

        /**
         * <code>uint64 start_timestamp = 4;</code>
         *
         * @param value The startTimestamp to set.
         * @return This builder for chaining.
         */
        public Builder setStartTimestamp(long value) {

            startTimestamp_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 start_timestamp = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartTimestamp() {
            bitField0_ &= ~0x00000008;
            startTimestamp_ = 0L;
            onChanged();
            return this;
        }

        private long endTimestamp_;

        /**
         * <code>uint64 end_timestamp = 5;</code>
         *
         * @return The endTimestamp.
         */
        @java.lang.Override
        public long getEndTimestamp() {
            return endTimestamp_;
        }

        /**
         * <code>uint64 end_timestamp = 5;</code>
         *
         * @param value The endTimestamp to set.
         * @return This builder for chaining.
         */
        public Builder setEndTimestamp(long value) {

            endTimestamp_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 end_timestamp = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndTimestamp() {
            bitField0_ &= ~0x00000010;
            endTimestamp_ = 0L;
            onChanged();
            return this;
        }

        private long rtpClockTicks_;

        /**
         * <code>uint64 rtp_clock_ticks = 6;</code>
         *
         * @return The rtpClockTicks.
         */
        @java.lang.Override
        public long getRtpClockTicks() {
            return rtpClockTicks_;
        }

        /**
         * <code>uint64 rtp_clock_ticks = 6;</code>
         *
         * @param value The rtpClockTicks to set.
         * @return This builder for chaining.
         */
        public Builder setRtpClockTicks(long value) {

            rtpClockTicks_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>uint64 rtp_clock_ticks = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRtpClockTicks() {
            bitField0_ &= ~0x00000020;
            rtpClockTicks_ = 0L;
            onChanged();
            return this;
        }

        private long driftSamples_;

        /**
         * <code>int64 drift_samples = 7;</code>
         *
         * @return The driftSamples.
         */
        @java.lang.Override
        public long getDriftSamples() {
            return driftSamples_;
        }

        /**
         * <code>int64 drift_samples = 7;</code>
         *
         * @param value The driftSamples to set.
         * @return This builder for chaining.
         */
        public Builder setDriftSamples(long value) {

            driftSamples_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>int64 drift_samples = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDriftSamples() {
            bitField0_ &= ~0x00000040;
            driftSamples_ = 0L;
            onChanged();
            return this;
        }

        private double driftMs_;

        /**
         * <code>double drift_ms = 8;</code>
         *
         * @return The driftMs.
         */
        @java.lang.Override
        public double getDriftMs() {
            return driftMs_;
        }

        /**
         * <code>double drift_ms = 8;</code>
         *
         * @param value The driftMs to set.
         * @return This builder for chaining.
         */
        public Builder setDriftMs(double value) {

            driftMs_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>double drift_ms = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDriftMs() {
            bitField0_ &= ~0x00000080;
            driftMs_ = 0D;
            onChanged();
            return this;
        }

        private double clockRate_;

        /**
         * <code>double clock_rate = 9;</code>
         *
         * @return The clockRate.
         */
        @java.lang.Override
        public double getClockRate() {
            return clockRate_;
        }

        /**
         * <code>double clock_rate = 9;</code>
         *
         * @param value The clockRate to set.
         * @return This builder for chaining.
         */
        public Builder setClockRate(double value) {

            clockRate_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>double clock_rate = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearClockRate() {
            bitField0_ &= ~0x00000100;
            clockRate_ = 0D;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.RTPDrift)
    }

    // @@protoc_insertion_point(class_scope:livekit.RTPDrift)
    private static final im.turms.plugin.livekit.core.proto.models.RTPDrift DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.RTPDrift();
    }

    public static im.turms.plugin.livekit.core.proto.models.RTPDrift getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RTPDrift> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public RTPDrift parsePartialFrom(
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

    public static com.google.protobuf.Parser<RTPDrift> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RTPDrift> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RTPDrift getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}