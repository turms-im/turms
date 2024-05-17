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
 * <pre>
 * record any website
 * </pre>
 *
 * Protobuf type {@code livekit.WebEgressRequest}
 */
public final class WebEgressRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.WebEgressRequest)
        WebEgressRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                WebEgressRequest.class.getName());
    }

    // Use WebEgressRequest.newBuilder() to construct.
    private WebEgressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private WebEgressRequest() {
        url_ = "";
        fileOutputs_ = java.util.Collections.emptyList();
        streamOutputs_ = java.util.Collections.emptyList();
        segmentOutputs_ = java.util.Collections.emptyList();
        imageOutputs_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_WebEgressRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_WebEgressRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.class,
                        im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.Builder.class);
    }

    private int outputCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object output_;

    public enum OutputCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        @java.lang.Deprecated
        FILE(4),
        @java.lang.Deprecated
        STREAM(5),
        @java.lang.Deprecated
        SEGMENTS(6),
        OUTPUT_NOT_SET(0);

        private final int value;

        OutputCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static OutputCase valueOf(int value) {
            return forNumber(value);
        }

        public static OutputCase forNumber(int value) {
            return switch (value) {
                case 4 -> FILE;
                case 5 -> STREAM;
                case 6 -> SEGMENTS;
                case 0 -> OUTPUT_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public OutputCase getOutputCase() {
        return OutputCase.forNumber(outputCase_);
    }

    private int optionsCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object options_;

    public enum OptionsCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        PRESET(7),
        ADVANCED(8),
        OPTIONS_NOT_SET(0);

        private final int value;

        OptionsCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static OptionsCase valueOf(int value) {
            return forNumber(value);
        }

        public static OptionsCase forNumber(int value) {
            return switch (value) {
                case 7 -> PRESET;
                case 8 -> ADVANCED;
                case 0 -> OPTIONS_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public OptionsCase getOptionsCase() {
        return OptionsCase.forNumber(optionsCase_);
    }

    public static final int URL_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object url_ = "";

    /**
     * <code>string url = 1;</code>
     *
     * @return The url.
     */
    @java.lang.Override
    public java.lang.String getUrl() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            url_ = s;
            return s;
        }
    }

    /**
     * <code>string url = 1;</code>
     *
     * @return The bytes for url.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getUrlBytes() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            url_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int AUDIO_ONLY_FIELD_NUMBER = 2;
    private boolean audioOnly_ = false;

    /**
     * <code>bool audio_only = 2;</code>
     *
     * @return The audioOnly.
     */
    @java.lang.Override
    public boolean getAudioOnly() {
        return audioOnly_;
    }

    public static final int VIDEO_ONLY_FIELD_NUMBER = 3;
    private boolean videoOnly_ = false;

    /**
     * <code>bool video_only = 3;</code>
     *
     * @return The videoOnly.
     */
    @java.lang.Override
    public boolean getVideoOnly() {
        return videoOnly_;
    }

    public static final int AWAIT_START_SIGNAL_FIELD_NUMBER = 12;
    private boolean awaitStartSignal_ = false;

    /**
     * <code>bool await_start_signal = 12;</code>
     *
     * @return The awaitStartSignal.
     */
    @java.lang.Override
    public boolean getAwaitStartSignal() {
        return awaitStartSignal_;
    }

    public static final int FILE_FIELD_NUMBER = 4;

    /**
     * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.WebEgressRequest.file is deprecated. See livekit_egress.proto;l=56
     * @return Whether the file field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasFile() {
        return outputCase_ == 4;
    }

    /**
     * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.WebEgressRequest.file is deprecated. See livekit_egress.proto;l=56
     * @return The file.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFile() {
        if (outputCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOrBuilder() {
        if (outputCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.getDefaultInstance();
    }

    public static final int STREAM_FIELD_NUMBER = 5;

    /**
     * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
     *
     * @deprecated livekit.WebEgressRequest.stream is deprecated. See livekit_egress.proto;l=57
     * @return Whether the stream field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasStream() {
        return outputCase_ == 5;
    }

    /**
     * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
     *
     * @deprecated livekit.WebEgressRequest.stream is deprecated. See livekit_egress.proto;l=57
     * @return The stream.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStream() {
        if (outputCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOrBuilder() {
        if (outputCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
    }

    public static final int SEGMENTS_FIELD_NUMBER = 6;

    /**
     * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.WebEgressRequest.segments is deprecated. See livekit_egress.proto;l=58
     * @return Whether the segments field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasSegments() {
        return outputCase_ == 6;
    }

    /**
     * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.WebEgressRequest.segments is deprecated. See livekit_egress.proto;l=58
     * @return The segments.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegments() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentsOrBuilder() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.getDefaultInstance();
    }

    public static final int PRESET_FIELD_NUMBER = 7;

    /**
     * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
     *
     * @return Whether the preset field is set.
     */
    public boolean hasPreset() {
        return optionsCase_ == 7;
    }

    /**
     * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    public int getPresetValue() {
        if (optionsCase_ == 7) {
            return (java.lang.Integer) options_;
        }
        return 0;
    }

    /**
     * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
     *
     * @return The preset.
     */
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
        if (optionsCase_ == 7) {
            im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset result =
                    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset
                            .forNumber((java.lang.Integer) options_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.UNRECOGNIZED
                    : result;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.H264_720P_30;
    }

    public static final int ADVANCED_FIELD_NUMBER = 8;

    /**
     * <code>.livekit.EncodingOptions advanced = 8;</code>
     *
     * @return Whether the advanced field is set.
     */
    @java.lang.Override
    public boolean hasAdvanced() {
        return optionsCase_ == 8;
    }

    /**
     * <code>.livekit.EncodingOptions advanced = 8;</code>
     *
     * @return The advanced.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
        if (optionsCase_ == 8) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    /**
     * <code>.livekit.EncodingOptions advanced = 8;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
        if (optionsCase_ == 8) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    public static final int FILE_OUTPUTS_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_;

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
     */
    @java.lang.Override
    public int getFileOutputsCount() {
        return fileOutputs_.size();
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index) {
        return fileOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index) {
        return fileOutputs_.get(index);
    }

    public static final int STREAM_OUTPUTS_FIELD_NUMBER = 10;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> streamOutputs_;

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList() {
        return streamOutputs_;
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList() {
        return streamOutputs_;
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
     */
    @java.lang.Override
    public int getStreamOutputsCount() {
        return streamOutputs_.size();
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index) {
        return streamOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOutputsOrBuilder(
            int index) {
        return streamOutputs_.get(index);
    }

    public static final int SEGMENT_OUTPUTS_FIELD_NUMBER = 11;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_;

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
     */
    @java.lang.Override
    public int getSegmentOutputsCount() {
        return segmentOutputs_.size();
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(
            int index) {
        return segmentOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index) {
        return segmentOutputs_.get(index);
    }

    public static final int IMAGE_OUTPUTS_FIELD_NUMBER = 13;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> imageOutputs_;

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList() {
        return imageOutputs_;
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList() {
        return imageOutputs_;
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
     */
    @java.lang.Override
    public int getImageOutputsCount() {
        return imageOutputs_.size();
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index) {
        return imageOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder getImageOutputsOrBuilder(
            int index) {
        return imageOutputs_.get(index);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, url_);
        }
        if (audioOnly_) {
            output.writeBool(2, audioOnly_);
        }
        if (videoOnly_) {
            output.writeBool(3, videoOnly_);
        }
        if (outputCase_ == 4) {
            output.writeMessage(4,
                    (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_);
        }
        if (outputCase_ == 5) {
            output.writeMessage(5,
                    (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_);
        }
        if (outputCase_ == 6) {
            output.writeMessage(6,
                    (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_);
        }
        if (optionsCase_ == 7) {
            output.writeEnum(7, ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 8) {
            output.writeMessage(8,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            output.writeMessage(9, encodedFileOutput);
        }
        for (StreamOutput streamOutput : streamOutputs_) {
            output.writeMessage(10, streamOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            output.writeMessage(11, segmentedFileOutput);
        }
        if (awaitStartSignal_) {
            output.writeBool(12, awaitStartSignal_);
        }
        for (ImageOutput imageOutput : imageOutputs_) {
            output.writeMessage(13, imageOutput);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, url_);
        }
        if (audioOnly_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(2, audioOnly_);
        }
        if (videoOnly_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, videoOnly_);
        }
        if (outputCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_);
        }
        if (outputCase_ == 5) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5,
                    (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_);
        }
        if (outputCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                    (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_);
        }
        if (optionsCase_ == 7) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(7,
                    ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 8) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9, encodedFileOutput);
        }
        for (StreamOutput streamOutput : streamOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10, streamOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(11,
                    segmentedFileOutput);
        }
        if (awaitStartSignal_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(12, awaitStartSignal_);
        }
        for (ImageOutput imageOutput : imageOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(13, imageOutput);
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
        if (!(obj instanceof WebEgressRequest other)) {
            return super.equals(obj);
        }

        if (!getUrl().equals(other.getUrl())) {
            return false;
        }
        if (getAudioOnly() != other.getAudioOnly()) {
            return false;
        }
        if (getVideoOnly() != other.getVideoOnly()) {
            return false;
        }
        if (getAwaitStartSignal() != other.getAwaitStartSignal()) {
            return false;
        }
        if (!getFileOutputsList().equals(other.getFileOutputsList())) {
            return false;
        }
        if (!getStreamOutputsList().equals(other.getStreamOutputsList())) {
            return false;
        }
        if (!getSegmentOutputsList().equals(other.getSegmentOutputsList())) {
            return false;
        }
        if (!getImageOutputsList().equals(other.getImageOutputsList())) {
            return false;
        }
        if (!getOutputCase().equals(other.getOutputCase())) {
            return false;
        }
        switch (outputCase_) {
            case 4 -> {
                if (!getFile().equals(other.getFile())) {
                    return false;
                }
            }
            case 5 -> {
                if (!getStream().equals(other.getStream())) {
                    return false;
                }
            }
            case 6 -> {
                if (!getSegments().equals(other.getSegments())) {
                    return false;
                }
            }
            default -> {
            }
        }
        if (!getOptionsCase().equals(other.getOptionsCase())) {
            return false;
        }
        switch (optionsCase_) {
            case 7 -> {
                if (getPresetValue() != other.getPresetValue()) {
                    return false;
                }
            }
            case 8 -> {
                if (!getAdvanced().equals(other.getAdvanced())) {
                    return false;
                }
            }
            default -> {
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
        hash = (37 * hash) + URL_FIELD_NUMBER;
        hash = (53 * hash) + getUrl().hashCode();
        hash = (37 * hash) + AUDIO_ONLY_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAudioOnly());
        hash = (37 * hash) + VIDEO_ONLY_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getVideoOnly());
        hash = (37 * hash) + AWAIT_START_SIGNAL_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAwaitStartSignal());
        if (getFileOutputsCount() > 0) {
            hash = (37 * hash) + FILE_OUTPUTS_FIELD_NUMBER;
            hash = (53 * hash) + getFileOutputsList().hashCode();
        }
        if (getStreamOutputsCount() > 0) {
            hash = (37 * hash) + STREAM_OUTPUTS_FIELD_NUMBER;
            hash = (53 * hash) + getStreamOutputsList().hashCode();
        }
        if (getSegmentOutputsCount() > 0) {
            hash = (37 * hash) + SEGMENT_OUTPUTS_FIELD_NUMBER;
            hash = (53 * hash) + getSegmentOutputsList().hashCode();
        }
        if (getImageOutputsCount() > 0) {
            hash = (37 * hash) + IMAGE_OUTPUTS_FIELD_NUMBER;
            hash = (53 * hash) + getImageOutputsList().hashCode();
        }
        switch (outputCase_) {
            case 4 -> {
                hash = (37 * hash) + FILE_FIELD_NUMBER;
                hash = (53 * hash) + getFile().hashCode();
            }
            case 5 -> {
                hash = (37 * hash) + STREAM_FIELD_NUMBER;
                hash = (53 * hash) + getStream().hashCode();
            }
            case 6 -> {
                hash = (37 * hash) + SEGMENTS_FIELD_NUMBER;
                hash = (53 * hash) + getSegments().hashCode();
            }
            default -> {
            }
        }
        switch (optionsCase_) {
            case 7 -> {
                hash = (37 * hash) + PRESET_FIELD_NUMBER;
                hash = (53 * hash) + getPresetValue();
            }
            case 8 -> {
                hash = (37 * hash) + ADVANCED_FIELD_NUMBER;
                hash = (53 * hash) + getAdvanced().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.WebEgressRequest prototype) {
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
     * <pre>
     * record any website
     * </pre>
     *
     * Protobuf type {@code livekit.WebEgressRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.WebEgressRequest)
            im.turms.plugin.livekit.core.proto.egress.WebEgressRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_WebEgressRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_WebEgressRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.class,
                            im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            url_ = "";
            audioOnly_ = false;
            videoOnly_ = false;
            awaitStartSignal_ = false;
            if (fileBuilder_ != null) {
                fileBuilder_.clear();
            }
            if (streamBuilder_ != null) {
                streamBuilder_.clear();
            }
            if (segmentsBuilder_ != null) {
                segmentsBuilder_.clear();
            }
            if (advancedBuilder_ != null) {
                advancedBuilder_.clear();
            }
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
            } else {
                fileOutputs_ = null;
                fileOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000200;
            if (streamOutputsBuilder_ == null) {
                streamOutputs_ = java.util.Collections.emptyList();
            } else {
                streamOutputs_ = null;
                streamOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000400;
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
            } else {
                segmentOutputs_ = null;
                segmentOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000800;
            if (imageOutputsBuilder_ == null) {
                imageOutputs_ = java.util.Collections.emptyList();
            } else {
                imageOutputs_ = null;
                imageOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00001000;
            outputCase_ = 0;
            output_ = null;
            optionsCase_ = 0;
            options_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_WebEgressRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.WebEgressRequest.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest build() {
            im.turms.plugin.livekit.core.proto.egress.WebEgressRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.WebEgressRequest result =
                    new im.turms.plugin.livekit.core.proto.egress.WebEgressRequest(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.egress.WebEgressRequest result) {
            if (fileOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000200) != 0)) {
                    fileOutputs_ = java.util.Collections.unmodifiableList(fileOutputs_);
                    bitField0_ &= ~0x00000200;
                }
                result.fileOutputs_ = fileOutputs_;
            } else {
                result.fileOutputs_ = fileOutputsBuilder_.build();
            }
            if (streamOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000400) != 0)) {
                    streamOutputs_ = java.util.Collections.unmodifiableList(streamOutputs_);
                    bitField0_ &= ~0x00000400;
                }
                result.streamOutputs_ = streamOutputs_;
            } else {
                result.streamOutputs_ = streamOutputsBuilder_.build();
            }
            if (segmentOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000800) != 0)) {
                    segmentOutputs_ = java.util.Collections.unmodifiableList(segmentOutputs_);
                    bitField0_ &= ~0x00000800;
                }
                result.segmentOutputs_ = segmentOutputs_;
            } else {
                result.segmentOutputs_ = segmentOutputsBuilder_.build();
            }
            if (imageOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00001000) != 0)) {
                    imageOutputs_ = java.util.Collections.unmodifiableList(imageOutputs_);
                    bitField0_ &= ~0x00001000;
                }
                result.imageOutputs_ = imageOutputs_;
            } else {
                result.imageOutputs_ = imageOutputsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.WebEgressRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.url_ = url_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.audioOnly_ = audioOnly_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.videoOnly_ = videoOnly_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.awaitStartSignal_ = awaitStartSignal_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.WebEgressRequest result) {
            result.outputCase_ = outputCase_;
            result.output_ = this.output_;
            if (outputCase_ == 4 && fileBuilder_ != null) {
                result.output_ = fileBuilder_.build();
            }
            if (outputCase_ == 5 && streamBuilder_ != null) {
                result.output_ = streamBuilder_.build();
            }
            if (outputCase_ == 6 && segmentsBuilder_ != null) {
                result.output_ = segmentsBuilder_.build();
            }
            result.optionsCase_ = optionsCase_;
            result.options_ = this.options_;
            if (optionsCase_ == 8 && advancedBuilder_ != null) {
                result.options_ = advancedBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.WebEgressRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.WebEgressRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.WebEgressRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getUrl()
                    .isEmpty()) {
                url_ = other.url_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getAudioOnly()) {
                setAudioOnly(other.getAudioOnly());
            }
            if (other.getVideoOnly()) {
                setVideoOnly(other.getVideoOnly());
            }
            if (other.getAwaitStartSignal()) {
                setAwaitStartSignal(other.getAwaitStartSignal());
            }
            if (fileOutputsBuilder_ == null) {
                if (!other.fileOutputs_.isEmpty()) {
                    if (fileOutputs_.isEmpty()) {
                        fileOutputs_ = other.fileOutputs_;
                        bitField0_ &= ~0x00000200;
                    } else {
                        ensureFileOutputsIsMutable();
                        fileOutputs_.addAll(other.fileOutputs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.fileOutputs_.isEmpty()) {
                    if (fileOutputsBuilder_.isEmpty()) {
                        fileOutputsBuilder_.dispose();
                        fileOutputsBuilder_ = null;
                        fileOutputs_ = other.fileOutputs_;
                        bitField0_ &= ~0x00000200;
                        fileOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getFileOutputsFieldBuilder()
                                        : null;
                    } else {
                        fileOutputsBuilder_.addAllMessages(other.fileOutputs_);
                    }
                }
            }
            if (streamOutputsBuilder_ == null) {
                if (!other.streamOutputs_.isEmpty()) {
                    if (streamOutputs_.isEmpty()) {
                        streamOutputs_ = other.streamOutputs_;
                        bitField0_ &= ~0x00000400;
                    } else {
                        ensureStreamOutputsIsMutable();
                        streamOutputs_.addAll(other.streamOutputs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.streamOutputs_.isEmpty()) {
                    if (streamOutputsBuilder_.isEmpty()) {
                        streamOutputsBuilder_.dispose();
                        streamOutputsBuilder_ = null;
                        streamOutputs_ = other.streamOutputs_;
                        bitField0_ &= ~0x00000400;
                        streamOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getStreamOutputsFieldBuilder()
                                        : null;
                    } else {
                        streamOutputsBuilder_.addAllMessages(other.streamOutputs_);
                    }
                }
            }
            if (segmentOutputsBuilder_ == null) {
                if (!other.segmentOutputs_.isEmpty()) {
                    if (segmentOutputs_.isEmpty()) {
                        segmentOutputs_ = other.segmentOutputs_;
                        bitField0_ &= ~0x00000800;
                    } else {
                        ensureSegmentOutputsIsMutable();
                        segmentOutputs_.addAll(other.segmentOutputs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.segmentOutputs_.isEmpty()) {
                    if (segmentOutputsBuilder_.isEmpty()) {
                        segmentOutputsBuilder_.dispose();
                        segmentOutputsBuilder_ = null;
                        segmentOutputs_ = other.segmentOutputs_;
                        bitField0_ &= ~0x00000800;
                        segmentOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getSegmentOutputsFieldBuilder()
                                        : null;
                    } else {
                        segmentOutputsBuilder_.addAllMessages(other.segmentOutputs_);
                    }
                }
            }
            if (imageOutputsBuilder_ == null) {
                if (!other.imageOutputs_.isEmpty()) {
                    if (imageOutputs_.isEmpty()) {
                        imageOutputs_ = other.imageOutputs_;
                        bitField0_ &= ~0x00001000;
                    } else {
                        ensureImageOutputsIsMutable();
                        imageOutputs_.addAll(other.imageOutputs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.imageOutputs_.isEmpty()) {
                    if (imageOutputsBuilder_.isEmpty()) {
                        imageOutputsBuilder_.dispose();
                        imageOutputsBuilder_ = null;
                        imageOutputs_ = other.imageOutputs_;
                        bitField0_ &= ~0x00001000;
                        imageOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getImageOutputsFieldBuilder()
                                        : null;
                    } else {
                        imageOutputsBuilder_.addAllMessages(other.imageOutputs_);
                    }
                }
            }
            switch (other.getOutputCase()) {
                case FILE -> mergeFile(other.getFile());
                case STREAM -> mergeStream(other.getStream());
                case SEGMENTS -> mergeSegments(other.getSegments());
                case OUTPUT_NOT_SET -> {
                }
            }
            switch (other.getOptionsCase()) {
                case PRESET -> setPresetValue(other.getPresetValue());
                case ADVANCED -> mergeAdvanced(other.getAdvanced());
                case OPTIONS_NOT_SET -> {
                }
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
                            url_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            audioOnly_ = input.readBool();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            videoOnly_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            input.readMessage(getFileFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 4;
                        } // case 34
                        case 42 -> {
                            input.readMessage(getStreamFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 5;
                        } // case 42
                        case 50 -> {
                            input.readMessage(getSegmentsFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 6;
                        } // case 50
                        case 56 -> {
                            int rawValue = input.readEnum();
                            optionsCase_ = 7;
                            options_ = rawValue;
                        } // case 56
                        case 66 -> {
                            input.readMessage(getAdvancedFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            optionsCase_ = 8;
                        } // case 66
                        case 74 -> {
                            EncodedFileOutput m = input.readMessage(EncodedFileOutput.parser(),
                                    extensionRegistry);
                            if (fileOutputsBuilder_ == null) {
                                ensureFileOutputsIsMutable();
                                fileOutputs_.add(m);
                            } else {
                                fileOutputsBuilder_.addMessage(m);
                            }
                        } // case 74
                        case 82 -> {
                            StreamOutput m =
                                    input.readMessage(StreamOutput.parser(), extensionRegistry);
                            if (streamOutputsBuilder_ == null) {
                                ensureStreamOutputsIsMutable();
                                streamOutputs_.add(m);
                            } else {
                                streamOutputsBuilder_.addMessage(m);
                            }
                        } // case 82
                        case 90 -> {
                            SegmentedFileOutput m = input.readMessage(SegmentedFileOutput.parser(),
                                    extensionRegistry);
                            if (segmentOutputsBuilder_ == null) {
                                ensureSegmentOutputsIsMutable();
                                segmentOutputs_.add(m);
                            } else {
                                segmentOutputsBuilder_.addMessage(m);
                            }
                        } // case 90
                        case 96 -> {
                            awaitStartSignal_ = input.readBool();
                            bitField0_ |= 0x00000008;
                        } // case 96
                        case 106 -> {
                            ImageOutput m =
                                    input.readMessage(ImageOutput.parser(), extensionRegistry);
                            if (imageOutputsBuilder_ == null) {
                                ensureImageOutputsIsMutable();
                                imageOutputs_.add(m);
                            } else {
                                imageOutputsBuilder_.addMessage(m);
                            }
                        } // case 106
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

        private int outputCase_ = 0;
        private java.lang.Object output_;

        public OutputCase getOutputCase() {
            return OutputCase.forNumber(outputCase_);
        }

        public Builder clearOutput() {
            outputCase_ = 0;
            output_ = null;
            onChanged();
            return this;
        }

        private int optionsCase_ = 0;
        private java.lang.Object options_;

        public OptionsCase getOptionsCase() {
            return OptionsCase.forNumber(optionsCase_);
        }

        public Builder clearOptions() {
            optionsCase_ = 0;
            options_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;

        private java.lang.Object url_ = "";

        /**
         * <code>string url = 1;</code>
         *
         * @return The url.
         */
        public java.lang.String getUrl() {
            java.lang.Object ref = url_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                url_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return The bytes for url.
         */
        public com.google.protobuf.ByteString getUrlBytes() {
            java.lang.Object ref = url_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                url_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The url to set.
         * @return This builder for chaining.
         */
        public Builder setUrl(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            url_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUrl() {
            url_ = getDefaultInstance().getUrl();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The bytes for url to set.
         * @return This builder for chaining.
         */
        public Builder setUrlBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            url_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private boolean audioOnly_;

        /**
         * <code>bool audio_only = 2;</code>
         *
         * @return The audioOnly.
         */
        @java.lang.Override
        public boolean getAudioOnly() {
            return audioOnly_;
        }

        /**
         * <code>bool audio_only = 2;</code>
         *
         * @param value The audioOnly to set.
         * @return This builder for chaining.
         */
        public Builder setAudioOnly(boolean value) {

            audioOnly_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>bool audio_only = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioOnly() {
            bitField0_ &= ~0x00000002;
            audioOnly_ = false;
            onChanged();
            return this;
        }

        private boolean videoOnly_;

        /**
         * <code>bool video_only = 3;</code>
         *
         * @return The videoOnly.
         */
        @java.lang.Override
        public boolean getVideoOnly() {
            return videoOnly_;
        }

        /**
         * <code>bool video_only = 3;</code>
         *
         * @param value The videoOnly to set.
         * @return This builder for chaining.
         */
        public Builder setVideoOnly(boolean value) {

            videoOnly_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>bool video_only = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVideoOnly() {
            bitField0_ &= ~0x00000004;
            videoOnly_ = false;
            onChanged();
            return this;
        }

        private boolean awaitStartSignal_;

        /**
         * <code>bool await_start_signal = 12;</code>
         *
         * @return The awaitStartSignal.
         */
        @java.lang.Override
        public boolean getAwaitStartSignal() {
            return awaitStartSignal_;
        }

        /**
         * <code>bool await_start_signal = 12;</code>
         *
         * @param value The awaitStartSignal to set.
         * @return This builder for chaining.
         */
        public Builder setAwaitStartSignal(boolean value) {

            awaitStartSignal_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>bool await_start_signal = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAwaitStartSignal() {
            bitField0_ &= ~0x00000008;
            awaitStartSignal_ = false;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> fileBuilder_;

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.WebEgressRequest.file is deprecated. See livekit_egress.proto;l=56
         * @return Whether the file field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasFile() {
            return outputCase_ == 4;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         *
         * @deprecated livekit.WebEgressRequest.file is deprecated. See livekit_egress.proto;l=56
         * @return The file.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFile() {
            if (fileBuilder_ == null) {
                if (outputCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 4) {
                    return fileBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setFile(im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput value) {
            if (fileBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                fileBuilder_.setMessage(value);
            }
            outputCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setFile(
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder builderForValue) {
            if (fileBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                fileBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeFile(
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput value) {
            if (fileBuilder_ == null) {
                if (outputCase_ == 4
                        && output_ != im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 4) {
                    fileBuilder_.mergeFrom(value);
                } else {
                    fileBuilder_.setMessage(value);
                }
            }
            outputCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearFile() {
            if (fileBuilder_ == null) {
                if (outputCase_ == 4) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 4) {
                    outputCase_ = 0;
                    output_ = null;
                }
                fileBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder getFileBuilder() {
            return getFileFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOrBuilder() {
            if ((outputCase_ == 4) && (fileBuilder_ != null)) {
                return fileBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 4 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileFieldBuilder() {
            if (fileBuilder_ == null) {
                if (!(outputCase_ == 4)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance();
                }
                fileBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 4;
            onChanged();
            return fileBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> streamBuilder_;

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         *
         * @deprecated livekit.WebEgressRequest.stream is deprecated. See livekit_egress.proto;l=57
         * @return Whether the stream field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasStream() {
            return outputCase_ == 5;
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         *
         * @deprecated livekit.WebEgressRequest.stream is deprecated. See livekit_egress.proto;l=57
         * @return The stream.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStream() {
            if (streamBuilder_ == null) {
                if (outputCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
            } else {
                if (outputCase_ == 5) {
                    return streamBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setStream(im.turms.plugin.livekit.core.proto.egress.StreamOutput value) {
            if (streamBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                streamBuilder_.setMessage(value);
            }
            outputCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setStream(
                im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder builderForValue) {
            if (streamBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                streamBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeStream(im.turms.plugin.livekit.core.proto.egress.StreamOutput value) {
            if (streamBuilder_ == null) {
                if (outputCase_ == 5
                        && output_ != im.turms.plugin.livekit.core.proto.egress.StreamOutput
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.StreamOutput.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 5) {
                    streamBuilder_.mergeFrom(value);
                } else {
                    streamBuilder_.setMessage(value);
                }
            }
            outputCase_ = 5;
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearStream() {
            if (streamBuilder_ == null) {
                if (outputCase_ == 5) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 5) {
                    outputCase_ = 0;
                    output_ = null;
                }
                streamBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder getStreamBuilder() {
            return getStreamFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOrBuilder() {
            if ((outputCase_ == 5) && (streamBuilder_ != null)) {
                return streamBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.StreamOutput stream = 5 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamFieldBuilder() {
            if (streamBuilder_ == null) {
                if (!(outputCase_ == 5)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.StreamOutput
                            .getDefaultInstance();
                }
                streamBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 5;
            onChanged();
            return streamBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> segmentsBuilder_;

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         *
         * @deprecated livekit.WebEgressRequest.segments is deprecated. See
         *             livekit_egress.proto;l=58
         * @return Whether the segments field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasSegments() {
            return outputCase_ == 6;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         *
         * @deprecated livekit.WebEgressRequest.segments is deprecated. See
         *             livekit_egress.proto;l=58
         * @return The segments.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegments() {
            if (segmentsBuilder_ == null) {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 6) {
                    return segmentsBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setSegments(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput value) {
            if (segmentsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                output_ = value;
                onChanged();
            } else {
                segmentsBuilder_.setMessage(value);
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder setSegments(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder builderForValue) {
            if (segmentsBuilder_ == null) {
                output_ = builderForValue.build();
                onChanged();
            } else {
                segmentsBuilder_.setMessage(builderForValue.build());
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeSegments(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput value) {
            if (segmentsBuilder_ == null) {
                if (outputCase_ == 6
                        && output_ != im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                                .getDefaultInstance()) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .newBuilder(
                                    (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    output_ = value;
                }
                onChanged();
            } else {
                if (outputCase_ == 6) {
                    segmentsBuilder_.mergeFrom(value);
                } else {
                    segmentsBuilder_.setMessage(value);
                }
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearSegments() {
            if (segmentsBuilder_ == null) {
                if (outputCase_ == 6) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 6) {
                    outputCase_ = 0;
                    output_ = null;
                }
                segmentsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder getSegmentsBuilder() {
            return getSegmentsFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentsOrBuilder() {
            if ((outputCase_ == 6) && (segmentsBuilder_ != null)) {
                return segmentsBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 6 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentsFieldBuilder() {
            if (segmentsBuilder_ == null) {
                if (!(outputCase_ == 6)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance();
                }
                segmentsBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 6;
            onChanged();
            return segmentsBuilder_;
        }

        /**
         * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
         *
         * @return Whether the preset field is set.
         */
        @java.lang.Override
        public boolean hasPreset() {
            return optionsCase_ == 7;
        }

        /**
         * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
         *
         * @return The enum numeric value on the wire for preset.
         */
        @java.lang.Override
        public int getPresetValue() {
            if (optionsCase_ == 7) {
                return (Integer) options_;
            }
            return 0;
        }

        /**
         * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
         *
         * @param value The enum numeric value on the wire for preset to set.
         * @return This builder for chaining.
         */
        public Builder setPresetValue(int value) {
            optionsCase_ = 7;
            options_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
         *
         * @return The preset.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
            if (optionsCase_ == 7) {
                im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset result =
                        im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset
                                .forNumber((java.lang.Integer) options_);
                return result == null
                        ? im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.UNRECOGNIZED
                        : result;
            }
            return im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.H264_720P_30;
        }

        /**
         * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
         *
         * @param value The preset to set.
         * @return This builder for chaining.
         */
        public Builder setPreset(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset value) {
            if (value == null) {
                throw new NullPointerException();
            }
            optionsCase_ = 7;
            options_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.EncodingOptionsPreset preset = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreset() {
            if (optionsCase_ == 7) {
                optionsCase_ = 0;
                options_ = null;
                onChanged();
            }
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodingOptions, im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder, im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder> advancedBuilder_;

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         *
         * @return Whether the advanced field is set.
         */
        @java.lang.Override
        public boolean hasAdvanced() {
            return optionsCase_ == 8;
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         *
         * @return The advanced.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 8) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            } else {
                if (optionsCase_ == 8) {
                    return advancedBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        public Builder setAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions value) {
            if (advancedBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                options_ = value;
                onChanged();
            } else {
                advancedBuilder_.setMessage(value);
            }
            optionsCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        public Builder setAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder builderForValue) {
            if (advancedBuilder_ == null) {
                options_ = builderForValue.build();
                onChanged();
            } else {
                advancedBuilder_.setMessage(builderForValue.build());
            }
            optionsCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        public Builder mergeAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions value) {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 8
                        && options_ != im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                                .getDefaultInstance()) {
                    options_ = im.turms.plugin.livekit.core.proto.egress.EncodingOptions.newBuilder(
                            (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_)
                            .mergeFrom(value)
                            .buildPartial();
                } else {
                    options_ = value;
                }
                onChanged();
            } else {
                if (optionsCase_ == 8) {
                    advancedBuilder_.mergeFrom(value);
                } else {
                    advancedBuilder_.setMessage(value);
                }
            }
            optionsCase_ = 8;
            return this;
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        public Builder clearAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 8) {
                    optionsCase_ = 0;
                    options_ = null;
                    onChanged();
                }
            } else {
                if (optionsCase_ == 8) {
                    optionsCase_ = 0;
                    options_ = null;
                }
                advancedBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder getAdvancedBuilder() {
            return getAdvancedFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
            if ((optionsCase_ == 8) && (advancedBuilder_ != null)) {
                return advancedBuilder_.getMessageOrBuilder();
            } else {
                if (optionsCase_ == 8) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.EncodingOptions advanced = 8;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodingOptions, im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder, im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder> getAdvancedFieldBuilder() {
            if (advancedBuilder_ == null) {
                if (!(optionsCase_ == 8)) {
                    options_ = im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                            .getDefaultInstance();
                }
                advancedBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_,
                        getParentForChildren(),
                        isClean());
                options_ = null;
            }
            optionsCase_ = 8;
            onChanged();
            return advancedBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_ =
                java.util.Collections.emptyList();

        private void ensureFileOutputsIsMutable() {
            if ((bitField0_ & 0x00000200) == 0) {
                fileOutputs_ = new java.util.ArrayList<>(fileOutputs_);
                bitField0_ |= 0x00000200;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> fileOutputsBuilder_;

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
            if (fileOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            } else {
                return fileOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public int getFileOutputsCount() {
            if (fileOutputsBuilder_ == null) {
                return fileOutputs_.size();
            } else {
                return fileOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(
                int index) {
            if (fileOutputsBuilder_ == null) {
                return fileOutputs_.get(index);
            } else {
                return fileOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder setFileOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput value) {
            if (fileOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureFileOutputsIsMutable();
                fileOutputs_.set(index, value);
                onChanged();
            } else {
                fileOutputsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder setFileOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder builderForValue) {
            if (fileOutputsBuilder_ == null) {
                ensureFileOutputsIsMutable();
                fileOutputs_.set(index, builderForValue.build());
                onChanged();
            } else {
                fileOutputsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder addFileOutputs(
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput value) {
            if (fileOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureFileOutputsIsMutable();
                fileOutputs_.add(value);
                onChanged();
            } else {
                fileOutputsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder addFileOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput value) {
            if (fileOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureFileOutputsIsMutable();
                fileOutputs_.add(index, value);
                onChanged();
            } else {
                fileOutputsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder addFileOutputs(
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder builderForValue) {
            if (fileOutputsBuilder_ == null) {
                ensureFileOutputsIsMutable();
                fileOutputs_.add(builderForValue.build());
                onChanged();
            } else {
                fileOutputsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder addFileOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder builderForValue) {
            if (fileOutputsBuilder_ == null) {
                ensureFileOutputsIsMutable();
                fileOutputs_.add(index, builderForValue.build());
                onChanged();
            } else {
                fileOutputsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder addAllFileOutputs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> values) {
            if (fileOutputsBuilder_ == null) {
                ensureFileOutputsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, fileOutputs_);
                onChanged();
            } else {
                fileOutputsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder clearFileOutputs() {
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000200;
                onChanged();
            } else {
                fileOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public Builder removeFileOutputs(int index) {
            if (fileOutputsBuilder_ == null) {
                ensureFileOutputsIsMutable();
                fileOutputs_.remove(index);
                onChanged();
            } else {
                fileOutputsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder getFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
                int index) {
            if (fileOutputsBuilder_ == null) {
                return fileOutputs_.get(index);
            } else {
                return fileOutputsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
            if (fileOutputsBuilder_ != null) {
                return fileOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder() {
            return getFileOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 9;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder> getFileOutputsBuilderList() {
            return getFileOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsFieldBuilder() {
            if (fileOutputsBuilder_ == null) {
                fileOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        fileOutputs_,
                        ((bitField0_ & 0x00000200) != 0),
                        getParentForChildren(),
                        isClean());
                fileOutputs_ = null;
            }
            return fileOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> streamOutputs_ =
                java.util.Collections.emptyList();

        private void ensureStreamOutputsIsMutable() {
            if ((bitField0_ & 0x00000400) == 0) {
                streamOutputs_ = new java.util.ArrayList<>(streamOutputs_);
                bitField0_ |= 0x00000400;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> streamOutputsBuilder_;

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList() {
            if (streamOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(streamOutputs_);
            } else {
                return streamOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public int getStreamOutputsCount() {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.size();
            } else {
                return streamOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index) {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.get(index);
            } else {
                return streamOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder setStreamOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamOutput value) {
            if (streamOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStreamOutputsIsMutable();
                streamOutputs_.set(index, value);
                onChanged();
            } else {
                streamOutputsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder setStreamOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder builderForValue) {
            if (streamOutputsBuilder_ == null) {
                ensureStreamOutputsIsMutable();
                streamOutputs_.set(index, builderForValue.build());
                onChanged();
            } else {
                streamOutputsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder addStreamOutputs(
                im.turms.plugin.livekit.core.proto.egress.StreamOutput value) {
            if (streamOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStreamOutputsIsMutable();
                streamOutputs_.add(value);
                onChanged();
            } else {
                streamOutputsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder addStreamOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamOutput value) {
            if (streamOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStreamOutputsIsMutable();
                streamOutputs_.add(index, value);
                onChanged();
            } else {
                streamOutputsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder addStreamOutputs(
                im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder builderForValue) {
            if (streamOutputsBuilder_ == null) {
                ensureStreamOutputsIsMutable();
                streamOutputs_.add(builderForValue.build());
                onChanged();
            } else {
                streamOutputsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder addStreamOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder builderForValue) {
            if (streamOutputsBuilder_ == null) {
                ensureStreamOutputsIsMutable();
                streamOutputs_.add(index, builderForValue.build());
                onChanged();
            } else {
                streamOutputsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder addAllStreamOutputs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutput> values) {
            if (streamOutputsBuilder_ == null) {
                ensureStreamOutputsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, streamOutputs_);
                onChanged();
            } else {
                streamOutputsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder clearStreamOutputs() {
            if (streamOutputsBuilder_ == null) {
                streamOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000400;
                onChanged();
            } else {
                streamOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public Builder removeStreamOutputs(int index) {
            if (streamOutputsBuilder_ == null) {
                ensureStreamOutputsIsMutable();
                streamOutputs_.remove(index);
                onChanged();
            } else {
                streamOutputsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder getStreamOutputsBuilder(
                int index) {
            return getStreamOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOutputsOrBuilder(
                int index) {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.get(index);
            } else {
                return streamOutputsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList() {
            if (streamOutputsBuilder_ != null) {
                return streamOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(streamOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder addStreamOutputsBuilder() {
            return getStreamOutputsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder addStreamOutputsBuilder(
                int index) {
            return getStreamOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 10;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder> getStreamOutputsBuilderList() {
            return getStreamOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsFieldBuilder() {
            if (streamOutputsBuilder_ == null) {
                streamOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        streamOutputs_,
                        ((bitField0_ & 0x00000400) != 0),
                        getParentForChildren(),
                        isClean());
                streamOutputs_ = null;
            }
            return streamOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_ =
                java.util.Collections.emptyList();

        private void ensureSegmentOutputsIsMutable() {
            if ((bitField0_ & 0x00000800) == 0) {
                segmentOutputs_ = new java.util.ArrayList<>(segmentOutputs_);
                bitField0_ |= 0x00000800;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> segmentOutputsBuilder_;

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
            if (segmentOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            } else {
                return segmentOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public int getSegmentOutputsCount() {
            if (segmentOutputsBuilder_ == null) {
                return segmentOutputs_.size();
            } else {
                return segmentOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(
                int index) {
            if (segmentOutputsBuilder_ == null) {
                return segmentOutputs_.get(index);
            } else {
                return segmentOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder setSegmentOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput value) {
            if (segmentOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.set(index, value);
                onChanged();
            } else {
                segmentOutputsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder setSegmentOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder builderForValue) {
            if (segmentOutputsBuilder_ == null) {
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.set(index, builderForValue.build());
                onChanged();
            } else {
                segmentOutputsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder addSegmentOutputs(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput value) {
            if (segmentOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.add(value);
                onChanged();
            } else {
                segmentOutputsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder addSegmentOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput value) {
            if (segmentOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.add(index, value);
                onChanged();
            } else {
                segmentOutputsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder addSegmentOutputs(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder builderForValue) {
            if (segmentOutputsBuilder_ == null) {
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.add(builderForValue.build());
                onChanged();
            } else {
                segmentOutputsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder addSegmentOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder builderForValue) {
            if (segmentOutputsBuilder_ == null) {
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.add(index, builderForValue.build());
                onChanged();
            } else {
                segmentOutputsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder addAllSegmentOutputs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> values) {
            if (segmentOutputsBuilder_ == null) {
                ensureSegmentOutputsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, segmentOutputs_);
                onChanged();
            } else {
                segmentOutputsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder clearSegmentOutputs() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000800;
                onChanged();
            } else {
                segmentOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public Builder removeSegmentOutputs(int index) {
            if (segmentOutputsBuilder_ == null) {
                ensureSegmentOutputsIsMutable();
                segmentOutputs_.remove(index);
                onChanged();
            } else {
                segmentOutputsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder getSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
                int index) {
            if (segmentOutputsBuilder_ == null) {
                return segmentOutputs_.get(index);
            } else {
                return segmentOutputsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
            if (segmentOutputsBuilder_ != null) {
                return segmentOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder() {
            return getSegmentOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 11;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder> getSegmentOutputsBuilderList() {
            return getSegmentOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsFieldBuilder() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        segmentOutputs_,
                        ((bitField0_ & 0x00000800) != 0),
                        getParentForChildren(),
                        isClean());
                segmentOutputs_ = null;
            }
            return segmentOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> imageOutputs_ =
                java.util.Collections.emptyList();

        private void ensureImageOutputsIsMutable() {
            if ((bitField0_ & 0x00001000) == 0) {
                imageOutputs_ = new java.util.ArrayList<>(imageOutputs_);
                bitField0_ |= 0x00001000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImageOutput, im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder, im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> imageOutputsBuilder_;

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList() {
            if (imageOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(imageOutputs_);
            } else {
                return imageOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public int getImageOutputsCount() {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.size();
            } else {
                return imageOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index) {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.get(index);
            } else {
                return imageOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder setImageOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImageOutput value) {
            if (imageOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureImageOutputsIsMutable();
                imageOutputs_.set(index, value);
                onChanged();
            } else {
                imageOutputsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder setImageOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder builderForValue) {
            if (imageOutputsBuilder_ == null) {
                ensureImageOutputsIsMutable();
                imageOutputs_.set(index, builderForValue.build());
                onChanged();
            } else {
                imageOutputsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder addImageOutputs(
                im.turms.plugin.livekit.core.proto.egress.ImageOutput value) {
            if (imageOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureImageOutputsIsMutable();
                imageOutputs_.add(value);
                onChanged();
            } else {
                imageOutputsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder addImageOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImageOutput value) {
            if (imageOutputsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureImageOutputsIsMutable();
                imageOutputs_.add(index, value);
                onChanged();
            } else {
                imageOutputsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder addImageOutputs(
                im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder builderForValue) {
            if (imageOutputsBuilder_ == null) {
                ensureImageOutputsIsMutable();
                imageOutputs_.add(builderForValue.build());
                onChanged();
            } else {
                imageOutputsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder addImageOutputs(
                int index,
                im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder builderForValue) {
            if (imageOutputsBuilder_ == null) {
                ensureImageOutputsIsMutable();
                imageOutputs_.add(index, builderForValue.build());
                onChanged();
            } else {
                imageOutputsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder addAllImageOutputs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutput> values) {
            if (imageOutputsBuilder_ == null) {
                ensureImageOutputsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, imageOutputs_);
                onChanged();
            } else {
                imageOutputsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder clearImageOutputs() {
            if (imageOutputsBuilder_ == null) {
                imageOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00001000;
                onChanged();
            } else {
                imageOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public Builder removeImageOutputs(int index) {
            if (imageOutputsBuilder_ == null) {
                ensureImageOutputsIsMutable();
                imageOutputs_.remove(index);
                onChanged();
            } else {
                imageOutputsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder getImageOutputsBuilder(
                int index) {
            return getImageOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder getImageOutputsOrBuilder(
                int index) {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.get(index);
            } else {
                return imageOutputsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList() {
            if (imageOutputsBuilder_ != null) {
                return imageOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(imageOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder addImageOutputsBuilder() {
            return getImageOutputsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder addImageOutputsBuilder(
                int index) {
            return getImageOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 13;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder> getImageOutputsBuilderList() {
            return getImageOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImageOutput, im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder, im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsFieldBuilder() {
            if (imageOutputsBuilder_ == null) {
                imageOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        imageOutputs_,
                        ((bitField0_ & 0x00001000) != 0),
                        getParentForChildren(),
                        isClean());
                imageOutputs_ = null;
            }
            return imageOutputsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.WebEgressRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.WebEgressRequest)
    private static final im.turms.plugin.livekit.core.proto.egress.WebEgressRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.WebEgressRequest();
    }

    public static im.turms.plugin.livekit.core.proto.egress.WebEgressRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<WebEgressRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public WebEgressRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<WebEgressRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<WebEgressRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.WebEgressRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}