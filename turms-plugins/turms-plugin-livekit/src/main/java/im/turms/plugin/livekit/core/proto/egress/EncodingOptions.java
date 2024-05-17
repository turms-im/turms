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

package im.turms.plugin.livekit.core.proto.egress;

/**
 * Protobuf type {@code livekit.EncodingOptions}
 */
public final class EncodingOptions extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.EncodingOptions)
        EncodingOptionsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                EncodingOptions.class.getName());
    }

    // Use EncodingOptions.newBuilder() to construct.
    private EncodingOptions(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private EncodingOptions() {
        audioCodec_ = 0;
        videoCodec_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EncodingOptions_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EncodingOptions_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.EncodingOptions.class,
                        im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder.class);
    }

    public static final int WIDTH_FIELD_NUMBER = 1;
    private int width_ = 0;

    /**
     * <pre>
     * (default 1920)
     * </pre>
     *
     * <code>int32 width = 1;</code>
     *
     * @return The width.
     */
    @java.lang.Override
    public int getWidth() {
        return width_;
    }

    public static final int HEIGHT_FIELD_NUMBER = 2;
    private int height_ = 0;

    /**
     * <pre>
     * (default 1080)
     * </pre>
     *
     * <code>int32 height = 2;</code>
     *
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
        return height_;
    }

    public static final int DEPTH_FIELD_NUMBER = 3;
    private int depth_ = 0;

    /**
     * <pre>
     * (default 24)
     * </pre>
     *
     * <code>int32 depth = 3;</code>
     *
     * @return The depth.
     */
    @java.lang.Override
    public int getDepth() {
        return depth_;
    }

    public static final int FRAMERATE_FIELD_NUMBER = 4;
    private int framerate_ = 0;

    /**
     * <pre>
     * (default 30)
     * </pre>
     *
     * <code>int32 framerate = 4;</code>
     *
     * @return The framerate.
     */
    @java.lang.Override
    public int getFramerate() {
        return framerate_;
    }

    public static final int AUDIO_CODEC_FIELD_NUMBER = 5;
    private int audioCodec_ = 0;

    /**
     * <pre>
     * (default OPUS)
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 5;</code>
     *
     * @return The enum numeric value on the wire for audioCodec.
     */
    @java.lang.Override
    public int getAudioCodecValue() {
        return audioCodec_;
    }

    /**
     * <pre>
     * (default OPUS)
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 5;</code>
     *
     * @return The audioCodec.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.AudioCodec getAudioCodec() {
        im.turms.plugin.livekit.core.proto.models.AudioCodec result =
                im.turms.plugin.livekit.core.proto.models.AudioCodec.forNumber(audioCodec_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.AudioCodec.UNRECOGNIZED
                : result;
    }

    public static final int AUDIO_BITRATE_FIELD_NUMBER = 6;
    private int audioBitrate_ = 0;

    /**
     * <pre>
     * (default 128)
     * </pre>
     *
     * <code>int32 audio_bitrate = 6;</code>
     *
     * @return The audioBitrate.
     */
    @java.lang.Override
    public int getAudioBitrate() {
        return audioBitrate_;
    }

    public static final int AUDIO_QUALITY_FIELD_NUMBER = 11;
    private int audioQuality_ = 0;

    /**
     * <pre>
     * quality setting on audio encoder
     * </pre>
     *
     * <code>int32 audio_quality = 11;</code>
     *
     * @return The audioQuality.
     */
    @java.lang.Override
    public int getAudioQuality() {
        return audioQuality_;
    }

    public static final int AUDIO_FREQUENCY_FIELD_NUMBER = 7;
    private int audioFrequency_ = 0;

    /**
     * <pre>
     * (default 44100)
     * </pre>
     *
     * <code>int32 audio_frequency = 7;</code>
     *
     * @return The audioFrequency.
     */
    @java.lang.Override
    public int getAudioFrequency() {
        return audioFrequency_;
    }

    public static final int VIDEO_CODEC_FIELD_NUMBER = 8;
    private int videoCodec_ = 0;

    /**
     * <pre>
     * (default H264_MAIN)
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 8;</code>
     *
     * @return The enum numeric value on the wire for videoCodec.
     */
    @java.lang.Override
    public int getVideoCodecValue() {
        return videoCodec_;
    }

    /**
     * <pre>
     * (default H264_MAIN)
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 8;</code>
     *
     * @return The videoCodec.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoCodec getVideoCodec() {
        im.turms.plugin.livekit.core.proto.models.VideoCodec result =
                im.turms.plugin.livekit.core.proto.models.VideoCodec.forNumber(videoCodec_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.VideoCodec.UNRECOGNIZED
                : result;
    }

    public static final int VIDEO_BITRATE_FIELD_NUMBER = 9;
    private int videoBitrate_ = 0;

    /**
     * <pre>
     * (default 4500)
     * </pre>
     *
     * <code>int32 video_bitrate = 9;</code>
     *
     * @return The videoBitrate.
     */
    @java.lang.Override
    public int getVideoBitrate() {
        return videoBitrate_;
    }

    public static final int VIDEO_QUALITY_FIELD_NUMBER = 12;
    private int videoQuality_ = 0;

    /**
     * <pre>
     * quality setting on video encoder
     * </pre>
     *
     * <code>int32 video_quality = 12;</code>
     *
     * @return The videoQuality.
     */
    @java.lang.Override
    public int getVideoQuality() {
        return videoQuality_;
    }

    public static final int KEY_FRAME_INTERVAL_FIELD_NUMBER = 10;
    private double keyFrameInterval_ = 0D;

    /**
     * <pre>
     * in seconds (default 4s for streaming, segment duration for segmented output, encoder default for files)
     * </pre>
     *
     * <code>double key_frame_interval = 10;</code>
     *
     * @return The keyFrameInterval.
     */
    @java.lang.Override
    public double getKeyFrameInterval() {
        return keyFrameInterval_;
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
        if (width_ != 0) {
            output.writeInt32(1, width_);
        }
        if (height_ != 0) {
            output.writeInt32(2, height_);
        }
        if (depth_ != 0) {
            output.writeInt32(3, depth_);
        }
        if (framerate_ != 0) {
            output.writeInt32(4, framerate_);
        }
        if (audioCodec_ != im.turms.plugin.livekit.core.proto.models.AudioCodec.DEFAULT_AC
                .getNumber()) {
            output.writeEnum(5, audioCodec_);
        }
        if (audioBitrate_ != 0) {
            output.writeInt32(6, audioBitrate_);
        }
        if (audioFrequency_ != 0) {
            output.writeInt32(7, audioFrequency_);
        }
        if (videoCodec_ != im.turms.plugin.livekit.core.proto.models.VideoCodec.DEFAULT_VC
                .getNumber()) {
            output.writeEnum(8, videoCodec_);
        }
        if (videoBitrate_ != 0) {
            output.writeInt32(9, videoBitrate_);
        }
        if (java.lang.Double.doubleToRawLongBits(keyFrameInterval_) != 0) {
            output.writeDouble(10, keyFrameInterval_);
        }
        if (audioQuality_ != 0) {
            output.writeInt32(11, audioQuality_);
        }
        if (videoQuality_ != 0) {
            output.writeInt32(12, videoQuality_);
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
        if (width_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(1, width_);
        }
        if (height_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, height_);
        }
        if (depth_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, depth_);
        }
        if (framerate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, framerate_);
        }
        if (audioCodec_ != im.turms.plugin.livekit.core.proto.models.AudioCodec.DEFAULT_AC
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5, audioCodec_);
        }
        if (audioBitrate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(6, audioBitrate_);
        }
        if (audioFrequency_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(7, audioFrequency_);
        }
        if (videoCodec_ != im.turms.plugin.livekit.core.proto.models.VideoCodec.DEFAULT_VC
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(8, videoCodec_);
        }
        if (videoBitrate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(9, videoBitrate_);
        }
        if (java.lang.Double.doubleToRawLongBits(keyFrameInterval_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(10, keyFrameInterval_);
        }
        if (audioQuality_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(11, audioQuality_);
        }
        if (videoQuality_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(12, videoQuality_);
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
        if (!(obj instanceof EncodingOptions other)) {
            return super.equals(obj);
        }

        if (getWidth() != other.getWidth()) {
            return false;
        }
        if (getHeight() != other.getHeight()) {
            return false;
        }
        if (getDepth() != other.getDepth()) {
            return false;
        }
        if (getFramerate() != other.getFramerate()) {
            return false;
        }
        if (audioCodec_ != other.audioCodec_) {
            return false;
        }
        if (getAudioBitrate() != other.getAudioBitrate()) {
            return false;
        }
        if (getAudioQuality() != other.getAudioQuality()) {
            return false;
        }
        if (getAudioFrequency() != other.getAudioFrequency()) {
            return false;
        }
        if (videoCodec_ != other.videoCodec_) {
            return false;
        }
        if (getVideoBitrate() != other.getVideoBitrate()) {
            return false;
        }
        if (getVideoQuality() != other.getVideoQuality()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getKeyFrameInterval()) != java.lang.Double
                .doubleToLongBits(other.getKeyFrameInterval())) {
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
        hash = (37 * hash) + WIDTH_FIELD_NUMBER;
        hash = (53 * hash) + getWidth();
        hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
        hash = (53 * hash) + getHeight();
        hash = (37 * hash) + DEPTH_FIELD_NUMBER;
        hash = (53 * hash) + getDepth();
        hash = (37 * hash) + FRAMERATE_FIELD_NUMBER;
        hash = (53 * hash) + getFramerate();
        hash = (37 * hash) + AUDIO_CODEC_FIELD_NUMBER;
        hash = (53 * hash) + audioCodec_;
        hash = (37 * hash) + AUDIO_BITRATE_FIELD_NUMBER;
        hash = (53 * hash) + getAudioBitrate();
        hash = (37 * hash) + AUDIO_QUALITY_FIELD_NUMBER;
        hash = (53 * hash) + getAudioQuality();
        hash = (37 * hash) + AUDIO_FREQUENCY_FIELD_NUMBER;
        hash = (53 * hash) + getAudioFrequency();
        hash = (37 * hash) + VIDEO_CODEC_FIELD_NUMBER;
        hash = (53 * hash) + videoCodec_;
        hash = (37 * hash) + VIDEO_BITRATE_FIELD_NUMBER;
        hash = (53 * hash) + getVideoBitrate();
        hash = (37 * hash) + VIDEO_QUALITY_FIELD_NUMBER;
        hash = (53 * hash) + getVideoQuality();
        hash = (37 * hash) + KEY_FRAME_INTERVAL_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getKeyFrameInterval()));
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions parseFrom(
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

    public static Builder newBuilder(
            im.turms.plugin.livekit.core.proto.egress.EncodingOptions prototype) {
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
     * Protobuf type {@code livekit.EncodingOptions}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.EncodingOptions)
            im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EncodingOptions_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EncodingOptions_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.EncodingOptions.class,
                            im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.EncodingOptions.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            width_ = 0;
            height_ = 0;
            depth_ = 0;
            framerate_ = 0;
            audioCodec_ = 0;
            audioBitrate_ = 0;
            audioQuality_ = 0;
            audioFrequency_ = 0;
            videoCodec_ = 0;
            videoBitrate_ = 0;
            videoQuality_ = 0;
            keyFrameInterval_ = 0D;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_EncodingOptions_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions build() {
            im.turms.plugin.livekit.core.proto.egress.EncodingOptions result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.EncodingOptions result =
                    new im.turms.plugin.livekit.core.proto.egress.EncodingOptions(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.width_ = width_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.height_ = height_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.depth_ = depth_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.framerate_ = framerate_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.audioCodec_ = audioCodec_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.audioBitrate_ = audioBitrate_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.audioQuality_ = audioQuality_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.audioFrequency_ = audioFrequency_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.videoCodec_ = videoCodec_;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.videoBitrate_ = videoBitrate_;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.videoQuality_ = videoQuality_;
            }
            if (((from_bitField0_ & 0x00000800) != 0)) {
                result.keyFrameInterval_ = keyFrameInterval_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.EncodingOptions) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.EncodingOptions) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.EncodingOptions other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getWidth() != 0) {
                setWidth(other.getWidth());
            }
            if (other.getHeight() != 0) {
                setHeight(other.getHeight());
            }
            if (other.getDepth() != 0) {
                setDepth(other.getDepth());
            }
            if (other.getFramerate() != 0) {
                setFramerate(other.getFramerate());
            }
            if (other.audioCodec_ != 0) {
                setAudioCodecValue(other.getAudioCodecValue());
            }
            if (other.getAudioBitrate() != 0) {
                setAudioBitrate(other.getAudioBitrate());
            }
            if (other.getAudioQuality() != 0) {
                setAudioQuality(other.getAudioQuality());
            }
            if (other.getAudioFrequency() != 0) {
                setAudioFrequency(other.getAudioFrequency());
            }
            if (other.videoCodec_ != 0) {
                setVideoCodecValue(other.getVideoCodecValue());
            }
            if (other.getVideoBitrate() != 0) {
                setVideoBitrate(other.getVideoBitrate());
            }
            if (other.getVideoQuality() != 0) {
                setVideoQuality(other.getVideoQuality());
            }
            if (other.getKeyFrameInterval() != 0D) {
                setKeyFrameInterval(other.getKeyFrameInterval());
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
                        case 8 -> {
                            width_ = input.readInt32();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            height_ = input.readInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            depth_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            framerate_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            audioCodec_ = input.readEnum();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            audioBitrate_ = input.readInt32();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            audioFrequency_ = input.readInt32();
                            bitField0_ |= 0x00000080;
                        } // case 56
                        case 64 -> {
                            videoCodec_ = input.readEnum();
                            bitField0_ |= 0x00000100;
                        } // case 64
                        case 72 -> {
                            videoBitrate_ = input.readInt32();
                            bitField0_ |= 0x00000200;
                        } // case 72
                        case 81 -> {
                            keyFrameInterval_ = input.readDouble();
                            bitField0_ |= 0x00000800;
                        } // case 81
                        case 88 -> {
                            audioQuality_ = input.readInt32();
                            bitField0_ |= 0x00000040;
                        } // case 88
                        case 96 -> {
                            videoQuality_ = input.readInt32();
                            bitField0_ |= 0x00000400;
                        } // case 96
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

        private int width_;

        /**
         * <pre>
         * (default 1920)
         * </pre>
         *
         * <code>int32 width = 1;</code>
         *
         * @return The width.
         */
        @java.lang.Override
        public int getWidth() {
            return width_;
        }

        /**
         * <pre>
         * (default 1920)
         * </pre>
         *
         * <code>int32 width = 1;</code>
         *
         * @param value The width to set.
         * @return This builder for chaining.
         */
        public Builder setWidth(int value) {

            width_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 1920)
         * </pre>
         *
         * <code>int32 width = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWidth() {
            bitField0_ &= ~0x00000001;
            width_ = 0;
            onChanged();
            return this;
        }

        private int height_;

        /**
         * <pre>
         * (default 1080)
         * </pre>
         *
         * <code>int32 height = 2;</code>
         *
         * @return The height.
         */
        @java.lang.Override
        public int getHeight() {
            return height_;
        }

        /**
         * <pre>
         * (default 1080)
         * </pre>
         *
         * <code>int32 height = 2;</code>
         *
         * @param value The height to set.
         * @return This builder for chaining.
         */
        public Builder setHeight(int value) {

            height_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 1080)
         * </pre>
         *
         * <code>int32 height = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeight() {
            bitField0_ &= ~0x00000002;
            height_ = 0;
            onChanged();
            return this;
        }

        private int depth_;

        /**
         * <pre>
         * (default 24)
         * </pre>
         *
         * <code>int32 depth = 3;</code>
         *
         * @return The depth.
         */
        @java.lang.Override
        public int getDepth() {
            return depth_;
        }

        /**
         * <pre>
         * (default 24)
         * </pre>
         *
         * <code>int32 depth = 3;</code>
         *
         * @param value The depth to set.
         * @return This builder for chaining.
         */
        public Builder setDepth(int value) {

            depth_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 24)
         * </pre>
         *
         * <code>int32 depth = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDepth() {
            bitField0_ &= ~0x00000004;
            depth_ = 0;
            onChanged();
            return this;
        }

        private int framerate_;

        /**
         * <pre>
         * (default 30)
         * </pre>
         *
         * <code>int32 framerate = 4;</code>
         *
         * @return The framerate.
         */
        @java.lang.Override
        public int getFramerate() {
            return framerate_;
        }

        /**
         * <pre>
         * (default 30)
         * </pre>
         *
         * <code>int32 framerate = 4;</code>
         *
         * @param value The framerate to set.
         * @return This builder for chaining.
         */
        public Builder setFramerate(int value) {

            framerate_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 30)
         * </pre>
         *
         * <code>int32 framerate = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFramerate() {
            bitField0_ &= ~0x00000008;
            framerate_ = 0;
            onChanged();
            return this;
        }

        private int audioCodec_ = 0;

        /**
         * <pre>
         * (default OPUS)
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 5;</code>
         *
         * @return The enum numeric value on the wire for audioCodec.
         */
        @java.lang.Override
        public int getAudioCodecValue() {
            return audioCodec_;
        }

        /**
         * <pre>
         * (default OPUS)
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 5;</code>
         *
         * @param value The enum numeric value on the wire for audioCodec to set.
         * @return This builder for chaining.
         */
        public Builder setAudioCodecValue(int value) {
            audioCodec_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default OPUS)
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 5;</code>
         *
         * @return The audioCodec.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.AudioCodec getAudioCodec() {
            im.turms.plugin.livekit.core.proto.models.AudioCodec result =
                    im.turms.plugin.livekit.core.proto.models.AudioCodec.forNumber(audioCodec_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.AudioCodec.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * (default OPUS)
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 5;</code>
         *
         * @param value The audioCodec to set.
         * @return This builder for chaining.
         */
        public Builder setAudioCodec(im.turms.plugin.livekit.core.proto.models.AudioCodec value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            audioCodec_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default OPUS)
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioCodec() {
            bitField0_ &= ~0x00000010;
            audioCodec_ = 0;
            onChanged();
            return this;
        }

        private int audioBitrate_;

        /**
         * <pre>
         * (default 128)
         * </pre>
         *
         * <code>int32 audio_bitrate = 6;</code>
         *
         * @return The audioBitrate.
         */
        @java.lang.Override
        public int getAudioBitrate() {
            return audioBitrate_;
        }

        /**
         * <pre>
         * (default 128)
         * </pre>
         *
         * <code>int32 audio_bitrate = 6;</code>
         *
         * @param value The audioBitrate to set.
         * @return This builder for chaining.
         */
        public Builder setAudioBitrate(int value) {

            audioBitrate_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 128)
         * </pre>
         *
         * <code>int32 audio_bitrate = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioBitrate() {
            bitField0_ &= ~0x00000020;
            audioBitrate_ = 0;
            onChanged();
            return this;
        }

        private int audioQuality_;

        /**
         * <pre>
         * quality setting on audio encoder
         * </pre>
         *
         * <code>int32 audio_quality = 11;</code>
         *
         * @return The audioQuality.
         */
        @java.lang.Override
        public int getAudioQuality() {
            return audioQuality_;
        }

        /**
         * <pre>
         * quality setting on audio encoder
         * </pre>
         *
         * <code>int32 audio_quality = 11;</code>
         *
         * @param value The audioQuality to set.
         * @return This builder for chaining.
         */
        public Builder setAudioQuality(int value) {

            audioQuality_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * quality setting on audio encoder
         * </pre>
         *
         * <code>int32 audio_quality = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioQuality() {
            bitField0_ &= ~0x00000040;
            audioQuality_ = 0;
            onChanged();
            return this;
        }

        private int audioFrequency_;

        /**
         * <pre>
         * (default 44100)
         * </pre>
         *
         * <code>int32 audio_frequency = 7;</code>
         *
         * @return The audioFrequency.
         */
        @java.lang.Override
        public int getAudioFrequency() {
            return audioFrequency_;
        }

        /**
         * <pre>
         * (default 44100)
         * </pre>
         *
         * <code>int32 audio_frequency = 7;</code>
         *
         * @param value The audioFrequency to set.
         * @return This builder for chaining.
         */
        public Builder setAudioFrequency(int value) {

            audioFrequency_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 44100)
         * </pre>
         *
         * <code>int32 audio_frequency = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioFrequency() {
            bitField0_ &= ~0x00000080;
            audioFrequency_ = 0;
            onChanged();
            return this;
        }

        private int videoCodec_ = 0;

        /**
         * <pre>
         * (default H264_MAIN)
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 8;</code>
         *
         * @return The enum numeric value on the wire for videoCodec.
         */
        @java.lang.Override
        public int getVideoCodecValue() {
            return videoCodec_;
        }

        /**
         * <pre>
         * (default H264_MAIN)
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 8;</code>
         *
         * @param value The enum numeric value on the wire for videoCodec to set.
         * @return This builder for chaining.
         */
        public Builder setVideoCodecValue(int value) {
            videoCodec_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_MAIN)
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 8;</code>
         *
         * @return The videoCodec.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoCodec getVideoCodec() {
            im.turms.plugin.livekit.core.proto.models.VideoCodec result =
                    im.turms.plugin.livekit.core.proto.models.VideoCodec.forNumber(videoCodec_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.VideoCodec.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * (default H264_MAIN)
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 8;</code>
         *
         * @param value The videoCodec to set.
         * @return This builder for chaining.
         */
        public Builder setVideoCodec(im.turms.plugin.livekit.core.proto.models.VideoCodec value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000100;
            videoCodec_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_MAIN)
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVideoCodec() {
            bitField0_ &= ~0x00000100;
            videoCodec_ = 0;
            onChanged();
            return this;
        }

        private int videoBitrate_;

        /**
         * <pre>
         * (default 4500)
         * </pre>
         *
         * <code>int32 video_bitrate = 9;</code>
         *
         * @return The videoBitrate.
         */
        @java.lang.Override
        public int getVideoBitrate() {
            return videoBitrate_;
        }

        /**
         * <pre>
         * (default 4500)
         * </pre>
         *
         * <code>int32 video_bitrate = 9;</code>
         *
         * @param value The videoBitrate to set.
         * @return This builder for chaining.
         */
        public Builder setVideoBitrate(int value) {

            videoBitrate_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default 4500)
         * </pre>
         *
         * <code>int32 video_bitrate = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVideoBitrate() {
            bitField0_ &= ~0x00000200;
            videoBitrate_ = 0;
            onChanged();
            return this;
        }

        private int videoQuality_;

        /**
         * <pre>
         * quality setting on video encoder
         * </pre>
         *
         * <code>int32 video_quality = 12;</code>
         *
         * @return The videoQuality.
         */
        @java.lang.Override
        public int getVideoQuality() {
            return videoQuality_;
        }

        /**
         * <pre>
         * quality setting on video encoder
         * </pre>
         *
         * <code>int32 video_quality = 12;</code>
         *
         * @param value The videoQuality to set.
         * @return This builder for chaining.
         */
        public Builder setVideoQuality(int value) {

            videoQuality_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * quality setting on video encoder
         * </pre>
         *
         * <code>int32 video_quality = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVideoQuality() {
            bitField0_ &= ~0x00000400;
            videoQuality_ = 0;
            onChanged();
            return this;
        }

        private double keyFrameInterval_;

        /**
         * <pre>
         * in seconds (default 4s for streaming, segment duration for segmented output, encoder default for files)
         * </pre>
         *
         * <code>double key_frame_interval = 10;</code>
         *
         * @return The keyFrameInterval.
         */
        @java.lang.Override
        public double getKeyFrameInterval() {
            return keyFrameInterval_;
        }

        /**
         * <pre>
         * in seconds (default 4s for streaming, segment duration for segmented output, encoder default for files)
         * </pre>
         *
         * <code>double key_frame_interval = 10;</code>
         *
         * @param value The keyFrameInterval to set.
         * @return This builder for chaining.
         */
        public Builder setKeyFrameInterval(double value) {

            keyFrameInterval_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * in seconds (default 4s for streaming, segment duration for segmented output, encoder default for files)
         * </pre>
         *
         * <code>double key_frame_interval = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearKeyFrameInterval() {
            bitField0_ &= ~0x00000800;
            keyFrameInterval_ = 0D;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.EncodingOptions)
    }

    // @@protoc_insertion_point(class_scope:livekit.EncodingOptions)
    private static final im.turms.plugin.livekit.core.proto.egress.EncodingOptions DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.EncodingOptions();
    }

    public static im.turms.plugin.livekit.core.proto.egress.EncodingOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<EncodingOptions> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public EncodingOptions parsePartialFrom(
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

    public static com.google.protobuf.Parser<EncodingOptions> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<EncodingOptions> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}