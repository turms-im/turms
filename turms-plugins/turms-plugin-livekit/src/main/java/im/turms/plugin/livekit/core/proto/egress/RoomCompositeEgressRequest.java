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
 * composite using a web browser
 * </pre>
 *
 * Protobuf type {@code livekit.RoomCompositeEgressRequest}
 */
public final class RoomCompositeEgressRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:livekit.RoomCompositeEgressRequest)
        RoomCompositeEgressRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                RoomCompositeEgressRequest.class.getName());
    }

    // Use RoomCompositeEgressRequest.newBuilder() to construct.
    private RoomCompositeEgressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private RoomCompositeEgressRequest() {
        roomName_ = "";
        layout_ = "";
        customBaseUrl_ = "";
        fileOutputs_ = java.util.Collections.emptyList();
        streamOutputs_ = java.util.Collections.emptyList();
        segmentOutputs_ = java.util.Collections.emptyList();
        imageOutputs_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_RoomCompositeEgressRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_RoomCompositeEgressRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.class,
                        im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder.class);
    }

    private int outputCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object output_;

    public enum OutputCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        @java.lang.Deprecated
        FILE(6),
        @java.lang.Deprecated
        STREAM(7),
        @java.lang.Deprecated
        SEGMENTS(10),
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
                case 6 -> FILE;
                case 7 -> STREAM;
                case 10 -> SEGMENTS;
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
        PRESET(8),
        ADVANCED(9),
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
                case 8 -> PRESET;
                case 9 -> ADVANCED;
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

    public static final int ROOM_NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomName_ = "";

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The roomName.
     */
    @java.lang.Override
    public java.lang.String getRoomName() {
        java.lang.Object ref = roomName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            roomName_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The bytes for roomName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRoomNameBytes() {
        java.lang.Object ref = roomName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            roomName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int LAYOUT_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object layout_ = "";

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string layout = 2;</code>
     *
     * @return The layout.
     */
    @java.lang.Override
    public java.lang.String getLayout() {
        java.lang.Object ref = layout_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            layout_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>string layout = 2;</code>
     *
     * @return The bytes for layout.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getLayoutBytes() {
        java.lang.Object ref = layout_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            layout_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int AUDIO_ONLY_FIELD_NUMBER = 3;
    private boolean audioOnly_ = false;

    /**
     * <pre>
     * (default false)
     * </pre>
     *
     * <code>bool audio_only = 3;</code>
     *
     * @return The audioOnly.
     */
    @java.lang.Override
    public boolean getAudioOnly() {
        return audioOnly_;
    }

    public static final int VIDEO_ONLY_FIELD_NUMBER = 4;
    private boolean videoOnly_ = false;

    /**
     * <pre>
     * (default false)
     * </pre>
     *
     * <code>bool video_only = 4;</code>
     *
     * @return The videoOnly.
     */
    @java.lang.Override
    public boolean getVideoOnly() {
        return videoOnly_;
    }

    public static final int CUSTOM_BASE_URL_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object customBaseUrl_ = "";

    /**
     * <pre>
     * template base url (default https://recorder.livekit.io)
     * </pre>
     *
     * <code>string custom_base_url = 5;</code>
     *
     * @return The customBaseUrl.
     */
    @java.lang.Override
    public java.lang.String getCustomBaseUrl() {
        java.lang.Object ref = customBaseUrl_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            customBaseUrl_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * template base url (default https://recorder.livekit.io)
     * </pre>
     *
     * <code>string custom_base_url = 5;</code>
     *
     * @return The bytes for customBaseUrl.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getCustomBaseUrlBytes() {
        java.lang.Object ref = customBaseUrl_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            customBaseUrl_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int FILE_FIELD_NUMBER = 6;

    /**
     * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.file is deprecated. See
     *             livekit_egress.proto;l=35
     * @return Whether the file field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasFile() {
        return outputCase_ == 6;
    }

    /**
     * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.file is deprecated. See
     *             livekit_egress.proto;l=35
     * @return The file.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFile() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOrBuilder() {
        if (outputCase_ == 6) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.getDefaultInstance();
    }

    public static final int STREAM_FIELD_NUMBER = 7;

    /**
     * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.stream is deprecated. See
     *             livekit_egress.proto;l=36
     * @return Whether the stream field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasStream() {
        return outputCase_ == 7;
    }

    /**
     * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.stream is deprecated. See
     *             livekit_egress.proto;l=36
     * @return The stream.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStream() {
        if (outputCase_ == 7) {
            return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOrBuilder() {
        if (outputCase_ == 7) {
            return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
    }

    public static final int SEGMENTS_FIELD_NUMBER = 10;

    /**
     * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.segments is deprecated. See
     *             livekit_egress.proto;l=37
     * @return Whether the segments field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasSegments() {
        return outputCase_ == 10;
    }

    /**
     * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
     *
     * @deprecated livekit.RoomCompositeEgressRequest.segments is deprecated. See
     *             livekit_egress.proto;l=37
     * @return The segments.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegments() {
        if (outputCase_ == 10) {
            return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.getDefaultInstance();
    }

    /**
     * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
     */
    @java.lang.Override
    @java.lang.Deprecated
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentsOrBuilder() {
        if (outputCase_ == 10) {
            return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
        }
        return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.getDefaultInstance();
    }

    public static final int PRESET_FIELD_NUMBER = 8;

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
     *
     * @return Whether the preset field is set.
     */
    public boolean hasPreset() {
        return optionsCase_ == 8;
    }

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    public int getPresetValue() {
        if (optionsCase_ == 8) {
            return (java.lang.Integer) options_;
        }
        return 0;
    }

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
     *
     * @return The preset.
     */
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
        if (optionsCase_ == 8) {
            im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset result =
                    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset
                            .forNumber((java.lang.Integer) options_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.UNRECOGNIZED
                    : result;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.H264_720P_30;
    }

    public static final int ADVANCED_FIELD_NUMBER = 9;

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 9;</code>
     *
     * @return Whether the advanced field is set.
     */
    @java.lang.Override
    public boolean hasAdvanced() {
        return optionsCase_ == 9;
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 9;</code>
     *
     * @return The advanced.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
        if (optionsCase_ == 9) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
        if (optionsCase_ == 9) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    public static final int FILE_OUTPUTS_FIELD_NUMBER = 11;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_;

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    @java.lang.Override
    public int getFileOutputsCount() {
        return fileOutputs_.size();
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index) {
        return fileOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index) {
        return fileOutputs_.get(index);
    }

    public static final int STREAM_OUTPUTS_FIELD_NUMBER = 12;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> streamOutputs_;

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList() {
        return streamOutputs_;
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList() {
        return streamOutputs_;
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    @java.lang.Override
    public int getStreamOutputsCount() {
        return streamOutputs_.size();
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index) {
        return streamOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOutputsOrBuilder(
            int index) {
        return streamOutputs_.get(index);
    }

    public static final int SEGMENT_OUTPUTS_FIELD_NUMBER = 13;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_;

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    @java.lang.Override
    public int getSegmentOutputsCount() {
        return segmentOutputs_.size();
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(
            int index) {
        return segmentOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index) {
        return segmentOutputs_.get(index);
    }

    public static final int IMAGE_OUTPUTS_FIELD_NUMBER = 14;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> imageOutputs_;

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList() {
        return imageOutputs_;
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList() {
        return imageOutputs_;
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    @java.lang.Override
    public int getImageOutputsCount() {
        return imageOutputs_.size();
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index) {
        return imageOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(layout_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, layout_);
        }
        if (audioOnly_) {
            output.writeBool(3, audioOnly_);
        }
        if (videoOnly_) {
            output.writeBool(4, videoOnly_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(customBaseUrl_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, customBaseUrl_);
        }
        if (outputCase_ == 6) {
            output.writeMessage(6,
                    (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_);
        }
        if (outputCase_ == 7) {
            output.writeMessage(7,
                    (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_);
        }
        if (optionsCase_ == 8) {
            output.writeEnum(8, ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 9) {
            output.writeMessage(9,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        if (outputCase_ == 10) {
            output.writeMessage(10,
                    (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            output.writeMessage(11, encodedFileOutput);
        }
        for (StreamOutput streamOutput : streamOutputs_) {
            output.writeMessage(12, streamOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            output.writeMessage(13, segmentedFileOutput);
        }
        for (ImageOutput imageOutput : imageOutputs_) {
            output.writeMessage(14, imageOutput);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(layout_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, layout_);
        }
        if (audioOnly_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, audioOnly_);
        }
        if (videoOnly_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(4, videoOnly_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(customBaseUrl_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, customBaseUrl_);
        }
        if (outputCase_ == 6) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                    (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_);
        }
        if (outputCase_ == 7) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7,
                    (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_);
        }
        if (optionsCase_ == 8) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(8,
                    ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 9) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        if (outputCase_ == 10) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10,
                    (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(11, encodedFileOutput);
        }
        for (StreamOutput streamOutput : streamOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(12, streamOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(13,
                    segmentedFileOutput);
        }
        for (ImageOutput imageOutput : imageOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(14, imageOutput);
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
        if (!(obj instanceof RoomCompositeEgressRequest other)) {
            return super.equals(obj);
        }

        if (!getRoomName().equals(other.getRoomName())) {
            return false;
        }
        if (!getLayout().equals(other.getLayout())) {
            return false;
        }
        if (getAudioOnly() != other.getAudioOnly()) {
            return false;
        }
        if (getVideoOnly() != other.getVideoOnly()) {
            return false;
        }
        if (!getCustomBaseUrl().equals(other.getCustomBaseUrl())) {
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
            case 6 -> {
                if (!getFile().equals(other.getFile())) {
                    return false;
                }
            }
            case 7 -> {
                if (!getStream().equals(other.getStream())) {
                    return false;
                }
            }
            case 10 -> {
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
            case 8 -> {
                if (getPresetValue() != other.getPresetValue()) {
                    return false;
                }
            }
            case 9 -> {
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
        hash = (37 * hash) + ROOM_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getRoomName().hashCode();
        hash = (37 * hash) + LAYOUT_FIELD_NUMBER;
        hash = (53 * hash) + getLayout().hashCode();
        hash = (37 * hash) + AUDIO_ONLY_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAudioOnly());
        hash = (37 * hash) + VIDEO_ONLY_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getVideoOnly());
        hash = (37 * hash) + CUSTOM_BASE_URL_FIELD_NUMBER;
        hash = (53 * hash) + getCustomBaseUrl().hashCode();
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
            case 6 -> {
                hash = (37 * hash) + FILE_FIELD_NUMBER;
                hash = (53 * hash) + getFile().hashCode();
            }
            case 7 -> {
                hash = (37 * hash) + STREAM_FIELD_NUMBER;
                hash = (53 * hash) + getStream().hashCode();
            }
            case 10 -> {
                hash = (37 * hash) + SEGMENTS_FIELD_NUMBER;
                hash = (53 * hash) + getSegments().hashCode();
            }
            default -> {
            }
        }
        switch (optionsCase_) {
            case 8 -> {
                hash = (37 * hash) + PRESET_FIELD_NUMBER;
                hash = (53 * hash) + getPresetValue();
            }
            case 9 -> {
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

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest prototype) {
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
     * composite using a web browser
     * </pre>
     *
     * Protobuf type {@code livekit.RoomCompositeEgressRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.RoomCompositeEgressRequest)
            im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_RoomCompositeEgressRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_RoomCompositeEgressRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.class,
                            im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            roomName_ = "";
            layout_ = "";
            audioOnly_ = false;
            videoOnly_ = false;
            customBaseUrl_ = "";
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
            bitField0_ &= ~0x00000400;
            if (streamOutputsBuilder_ == null) {
                streamOutputs_ = java.util.Collections.emptyList();
            } else {
                streamOutputs_ = null;
                streamOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000800;
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
            } else {
                segmentOutputs_ = null;
                segmentOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00001000;
            if (imageOutputsBuilder_ == null) {
                imageOutputs_ = java.util.Collections.emptyList();
            } else {
                imageOutputs_ = null;
                imageOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00002000;
            outputCase_ = 0;
            output_ = null;
            optionsCase_ = 0;
            options_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_RoomCompositeEgressRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest build() {
            im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest result =
                    new im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest result) {
            if (fileOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000400) != 0)) {
                    fileOutputs_ = java.util.Collections.unmodifiableList(fileOutputs_);
                    bitField0_ &= ~0x00000400;
                }
                result.fileOutputs_ = fileOutputs_;
            } else {
                result.fileOutputs_ = fileOutputsBuilder_.build();
            }
            if (streamOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000800) != 0)) {
                    streamOutputs_ = java.util.Collections.unmodifiableList(streamOutputs_);
                    bitField0_ &= ~0x00000800;
                }
                result.streamOutputs_ = streamOutputs_;
            } else {
                result.streamOutputs_ = streamOutputsBuilder_.build();
            }
            if (segmentOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00001000) != 0)) {
                    segmentOutputs_ = java.util.Collections.unmodifiableList(segmentOutputs_);
                    bitField0_ &= ~0x00001000;
                }
                result.segmentOutputs_ = segmentOutputs_;
            } else {
                result.segmentOutputs_ = segmentOutputsBuilder_.build();
            }
            if (imageOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00002000) != 0)) {
                    imageOutputs_ = java.util.Collections.unmodifiableList(imageOutputs_);
                    bitField0_ &= ~0x00002000;
                }
                result.imageOutputs_ = imageOutputs_;
            } else {
                result.imageOutputs_ = imageOutputsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.layout_ = layout_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.audioOnly_ = audioOnly_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.videoOnly_ = videoOnly_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.customBaseUrl_ = customBaseUrl_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest result) {
            result.outputCase_ = outputCase_;
            result.output_ = this.output_;
            if (outputCase_ == 6 && fileBuilder_ != null) {
                result.output_ = fileBuilder_.build();
            }
            if (outputCase_ == 7 && streamBuilder_ != null) {
                result.output_ = streamBuilder_.build();
            }
            if (outputCase_ == 10 && segmentsBuilder_ != null) {
                result.output_ = segmentsBuilder_.build();
            }
            result.optionsCase_ = optionsCase_;
            result.options_ = this.options_;
            if (optionsCase_ == 9 && advancedBuilder_ != null) {
                result.options_ = advancedBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getLayout()
                    .isEmpty()) {
                layout_ = other.layout_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getAudioOnly()) {
                setAudioOnly(other.getAudioOnly());
            }
            if (other.getVideoOnly()) {
                setVideoOnly(other.getVideoOnly());
            }
            if (!other.getCustomBaseUrl()
                    .isEmpty()) {
                customBaseUrl_ = other.customBaseUrl_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (fileOutputsBuilder_ == null) {
                if (!other.fileOutputs_.isEmpty()) {
                    if (fileOutputs_.isEmpty()) {
                        fileOutputs_ = other.fileOutputs_;
                        bitField0_ &= ~0x00000400;
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
                        bitField0_ &= ~0x00000400;
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
                        bitField0_ &= ~0x00000800;
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
                        bitField0_ &= ~0x00000800;
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
                        bitField0_ &= ~0x00001000;
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
                        bitField0_ &= ~0x00001000;
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
                        bitField0_ &= ~0x00002000;
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
                        bitField0_ &= ~0x00002000;
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
                            roomName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            layout_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            audioOnly_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            videoOnly_ = input.readBool();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 42 -> {
                            customBaseUrl_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            input.readMessage(getFileFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 6;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getStreamFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 7;
                        } // case 58
                        case 64 -> {
                            int rawValue = input.readEnum();
                            optionsCase_ = 8;
                            options_ = rawValue;
                        } // case 64
                        case 74 -> {
                            input.readMessage(getAdvancedFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            optionsCase_ = 9;
                        } // case 74
                        case 82 -> {
                            input.readMessage(getSegmentsFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            outputCase_ = 10;
                        } // case 82
                        case 90 -> {
                            EncodedFileOutput m = input.readMessage(EncodedFileOutput.parser(),
                                    extensionRegistry);
                            if (fileOutputsBuilder_ == null) {
                                ensureFileOutputsIsMutable();
                                fileOutputs_.add(m);
                            } else {
                                fileOutputsBuilder_.addMessage(m);
                            }
                        } // case 90
                        case 98 -> {
                            StreamOutput m =
                                    input.readMessage(StreamOutput.parser(), extensionRegistry);
                            if (streamOutputsBuilder_ == null) {
                                ensureStreamOutputsIsMutable();
                                streamOutputs_.add(m);
                            } else {
                                streamOutputsBuilder_.addMessage(m);
                            }
                        } // case 98
                        case 106 -> {
                            SegmentedFileOutput m = input.readMessage(SegmentedFileOutput.parser(),
                                    extensionRegistry);
                            if (segmentOutputsBuilder_ == null) {
                                ensureSegmentOutputsIsMutable();
                                segmentOutputs_.add(m);
                            } else {
                                segmentOutputsBuilder_.addMessage(m);
                            }
                        } // case 106
                        case 114 -> {
                            ImageOutput m =
                                    input.readMessage(ImageOutput.parser(), extensionRegistry);
                            if (imageOutputsBuilder_ == null) {
                                ensureImageOutputsIsMutable();
                                imageOutputs_.add(m);
                            } else {
                                imageOutputsBuilder_.addMessage(m);
                            }
                        } // case 114
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

        private java.lang.Object roomName_ = "";

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @return The roomName.
         */
        public java.lang.String getRoomName() {
            java.lang.Object ref = roomName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                roomName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @return The bytes for roomName.
         */
        public com.google.protobuf.ByteString getRoomNameBytes() {
            java.lang.Object ref = roomName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                roomName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @param value The roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomName() {
            roomName_ = getDefaultInstance().getRoomName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string room_name = 1;</code>
         *
         * @param value The bytes for roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            roomName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object layout_ = "";

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string layout = 2;</code>
         *
         * @return The layout.
         */
        public java.lang.String getLayout() {
            java.lang.Object ref = layout_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                layout_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string layout = 2;</code>
         *
         * @return The bytes for layout.
         */
        public com.google.protobuf.ByteString getLayoutBytes() {
            java.lang.Object ref = layout_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                layout_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string layout = 2;</code>
         *
         * @param value The layout to set.
         * @return This builder for chaining.
         */
        public Builder setLayout(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            layout_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string layout = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLayout() {
            layout_ = getDefaultInstance().getLayout();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>string layout = 2;</code>
         *
         * @param value The bytes for layout to set.
         * @return This builder for chaining.
         */
        public Builder setLayoutBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            layout_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private boolean audioOnly_;

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool audio_only = 3;</code>
         *
         * @return The audioOnly.
         */
        @java.lang.Override
        public boolean getAudioOnly() {
            return audioOnly_;
        }

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool audio_only = 3;</code>
         *
         * @param value The audioOnly to set.
         * @return This builder for chaining.
         */
        public Builder setAudioOnly(boolean value) {

            audioOnly_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool audio_only = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioOnly() {
            bitField0_ &= ~0x00000004;
            audioOnly_ = false;
            onChanged();
            return this;
        }

        private boolean videoOnly_;

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool video_only = 4;</code>
         *
         * @return The videoOnly.
         */
        @java.lang.Override
        public boolean getVideoOnly() {
            return videoOnly_;
        }

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool video_only = 4;</code>
         *
         * @param value The videoOnly to set.
         * @return This builder for chaining.
         */
        public Builder setVideoOnly(boolean value) {

            videoOnly_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool video_only = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVideoOnly() {
            bitField0_ &= ~0x00000008;
            videoOnly_ = false;
            onChanged();
            return this;
        }

        private java.lang.Object customBaseUrl_ = "";

        /**
         * <pre>
         * template base url (default https://recorder.livekit.io)
         * </pre>
         *
         * <code>string custom_base_url = 5;</code>
         *
         * @return The customBaseUrl.
         */
        public java.lang.String getCustomBaseUrl() {
            java.lang.Object ref = customBaseUrl_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                customBaseUrl_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * template base url (default https://recorder.livekit.io)
         * </pre>
         *
         * <code>string custom_base_url = 5;</code>
         *
         * @return The bytes for customBaseUrl.
         */
        public com.google.protobuf.ByteString getCustomBaseUrlBytes() {
            java.lang.Object ref = customBaseUrl_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                customBaseUrl_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * template base url (default https://recorder.livekit.io)
         * </pre>
         *
         * <code>string custom_base_url = 5;</code>
         *
         * @param value The customBaseUrl to set.
         * @return This builder for chaining.
         */
        public Builder setCustomBaseUrl(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            customBaseUrl_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * template base url (default https://recorder.livekit.io)
         * </pre>
         *
         * <code>string custom_base_url = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCustomBaseUrl() {
            customBaseUrl_ = getDefaultInstance().getCustomBaseUrl();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * template base url (default https://recorder.livekit.io)
         * </pre>
         *
         * <code>string custom_base_url = 5;</code>
         *
         * @param value The bytes for customBaseUrl to set.
         * @return This builder for chaining.
         */
        public Builder setCustomBaseUrlBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            customBaseUrl_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> fileBuilder_;

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         *
         * @deprecated livekit.RoomCompositeEgressRequest.file is deprecated. See
         *             livekit_egress.proto;l=35
         * @return Whether the file field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasFile() {
            return outputCase_ == 6;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         *
         * @deprecated livekit.RoomCompositeEgressRequest.file is deprecated. See
         *             livekit_egress.proto;l=35
         * @return The file.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFile() {
            if (fileBuilder_ == null) {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 6) {
                    return fileBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
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
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
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
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeFile(
                im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput value) {
            if (fileBuilder_ == null) {
                if (outputCase_ == 6
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
                if (outputCase_ == 6) {
                    fileBuilder_.mergeFrom(value);
                } else {
                    fileBuilder_.setMessage(value);
                }
            }
            outputCase_ = 6;
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearFile() {
            if (fileBuilder_ == null) {
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
                fileBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder getFileBuilder() {
            return getFileFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOrBuilder() {
            if ((outputCase_ == 6) && (fileBuilder_ != null)) {
                return fileBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 6) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.EncodedFileOutput file = 6 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileFieldBuilder() {
            if (fileBuilder_ == null) {
                if (!(outputCase_ == 6)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance();
                }
                fileBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 6;
            onChanged();
            return fileBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> streamBuilder_;

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         *
         * @deprecated livekit.RoomCompositeEgressRequest.stream is deprecated. See
         *             livekit_egress.proto;l=36
         * @return Whether the stream field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasStream() {
            return outputCase_ == 7;
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         *
         * @deprecated livekit.RoomCompositeEgressRequest.stream is deprecated. See
         *             livekit_egress.proto;l=36
         * @return The stream.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStream() {
            if (streamBuilder_ == null) {
                if (outputCase_ == 7) {
                    return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
            } else {
                if (outputCase_ == 7) {
                    return streamBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
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
            outputCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
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
            outputCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeStream(im.turms.plugin.livekit.core.proto.egress.StreamOutput value) {
            if (streamBuilder_ == null) {
                if (outputCase_ == 7
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
                if (outputCase_ == 7) {
                    streamBuilder_.mergeFrom(value);
                } else {
                    streamBuilder_.setMessage(value);
                }
            }
            outputCase_ = 7;
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearStream() {
            if (streamBuilder_ == null) {
                if (outputCase_ == 7) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 7) {
                    outputCase_ = 0;
                    output_ = null;
                }
                streamBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder getStreamBuilder() {
            return getStreamFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOrBuilder() {
            if ((outputCase_ == 7) && (streamBuilder_ != null)) {
                return streamBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 7) {
                    return (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.StreamOutput stream = 7 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamFieldBuilder() {
            if (streamBuilder_ == null) {
                if (!(outputCase_ == 7)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.StreamOutput
                            .getDefaultInstance();
                }
                streamBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.StreamOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 7;
            onChanged();
            return streamBuilder_;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> segmentsBuilder_;

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         *
         * @deprecated livekit.RoomCompositeEgressRequest.segments is deprecated. See
         *             livekit_egress.proto;l=37
         * @return Whether the segments field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasSegments() {
            return outputCase_ == 10;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         *
         * @deprecated livekit.RoomCompositeEgressRequest.segments is deprecated. See
         *             livekit_egress.proto;l=37
         * @return The segments.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegments() {
            if (segmentsBuilder_ == null) {
                if (outputCase_ == 10) {
                    return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                        .getDefaultInstance();
            } else {
                if (outputCase_ == 10) {
                    return segmentsBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
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
            outputCase_ = 10;
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
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
            outputCase_ = 10;
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder mergeSegments(
                im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput value) {
            if (segmentsBuilder_ == null) {
                if (outputCase_ == 10
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
                if (outputCase_ == 10) {
                    segmentsBuilder_.mergeFrom(value);
                } else {
                    segmentsBuilder_.setMessage(value);
                }
            }
            outputCase_ = 10;
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public Builder clearSegments() {
            if (segmentsBuilder_ == null) {
                if (outputCase_ == 10) {
                    outputCase_ = 0;
                    output_ = null;
                    onChanged();
                }
            } else {
                if (outputCase_ == 10) {
                    outputCase_ = 0;
                    output_ = null;
                }
                segmentsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         */
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder getSegmentsBuilder() {
            return getSegmentsFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         */
        @java.lang.Override
        @java.lang.Deprecated
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentsOrBuilder() {
            if ((outputCase_ == 10) && (segmentsBuilder_ != null)) {
                return segmentsBuilder_.getMessageOrBuilder();
            } else {
                if (outputCase_ == 10) {
                    return (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_;
                }
                return im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.SegmentedFileOutput segments = 10 [deprecated = true];</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentsFieldBuilder() {
            if (segmentsBuilder_ == null) {
                if (!(outputCase_ == 10)) {
                    output_ = im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance();
                }
                segmentsBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput) output_,
                        getParentForChildren(),
                        isClean());
                output_ = null;
            }
            outputCase_ = 10;
            onChanged();
            return segmentsBuilder_;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
         *
         * @return Whether the preset field is set.
         */
        @java.lang.Override
        public boolean hasPreset() {
            return optionsCase_ == 8;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
         *
         * @return The enum numeric value on the wire for preset.
         */
        @java.lang.Override
        public int getPresetValue() {
            if (optionsCase_ == 8) {
                return (Integer) options_;
            }
            return 0;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
         *
         * @param value The enum numeric value on the wire for preset to set.
         * @return This builder for chaining.
         */
        public Builder setPresetValue(int value) {
            optionsCase_ = 8;
            options_ = value;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
         *
         * @return The preset.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
            if (optionsCase_ == 8) {
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
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
         *
         * @param value The preset to set.
         * @return This builder for chaining.
         */
        public Builder setPreset(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset value) {
            if (value == null) {
                throw new NullPointerException();
            }
            optionsCase_ = 8;
            options_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreset() {
            if (optionsCase_ == 8) {
                optionsCase_ = 0;
                options_ = null;
                onChanged();
            }
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodingOptions, im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder, im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder> advancedBuilder_;

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         *
         * @return Whether the advanced field is set.
         */
        @java.lang.Override
        public boolean hasAdvanced() {
            return optionsCase_ == 9;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         *
         * @return The advanced.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 9) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            } else {
                if (optionsCase_ == 9) {
                    return advancedBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
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
            optionsCase_ = 9;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         */
        public Builder setAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder builderForValue) {
            if (advancedBuilder_ == null) {
                options_ = builderForValue.build();
                onChanged();
            } else {
                advancedBuilder_.setMessage(builderForValue.build());
            }
            optionsCase_ = 9;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         */
        public Builder mergeAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions value) {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 9
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
                if (optionsCase_ == 9) {
                    advancedBuilder_.mergeFrom(value);
                } else {
                    advancedBuilder_.setMessage(value);
                }
            }
            optionsCase_ = 9;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         */
        public Builder clearAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 9) {
                    optionsCase_ = 0;
                    options_ = null;
                    onChanged();
                }
            } else {
                if (optionsCase_ == 9) {
                    optionsCase_ = 0;
                    options_ = null;
                }
                advancedBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder getAdvancedBuilder() {
            return getAdvancedFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
            if ((optionsCase_ == 9) && (advancedBuilder_ != null)) {
                return advancedBuilder_.getMessageOrBuilder();
            } else {
                if (optionsCase_ == 9) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            }
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 9;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodingOptions, im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder, im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder> getAdvancedFieldBuilder() {
            if (advancedBuilder_ == null) {
                if (!(optionsCase_ == 9)) {
                    options_ = im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                            .getDefaultInstance();
                }
                advancedBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_,
                        getParentForChildren(),
                        isClean());
                options_ = null;
            }
            optionsCase_ = 9;
            onChanged();
            return advancedBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_ =
                java.util.Collections.emptyList();

        private void ensureFileOutputsIsMutable() {
            if ((bitField0_ & 0x00000400) == 0) {
                fileOutputs_ = new java.util.ArrayList<>(fileOutputs_);
                bitField0_ |= 0x00000400;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> fileOutputsBuilder_;

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
            if (fileOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            } else {
                return fileOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public int getFileOutputsCount() {
            if (fileOutputsBuilder_ == null) {
                return fileOutputs_.size();
            } else {
                return fileOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public Builder clearFileOutputs() {
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000400;
                onChanged();
            } else {
                fileOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder getFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
            if (fileOutputsBuilder_ != null) {
                return fileOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder() {
            return getFileOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 11;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder> getFileOutputsBuilderList() {
            return getFileOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsFieldBuilder() {
            if (fileOutputsBuilder_ == null) {
                fileOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        fileOutputs_,
                        ((bitField0_ & 0x00000400) != 0),
                        getParentForChildren(),
                        isClean());
                fileOutputs_ = null;
            }
            return fileOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> streamOutputs_ =
                java.util.Collections.emptyList();

        private void ensureStreamOutputsIsMutable() {
            if ((bitField0_ & 0x00000800) == 0) {
                streamOutputs_ = new java.util.ArrayList<>(streamOutputs_);
                bitField0_ |= 0x00000800;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> streamOutputsBuilder_;

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList() {
            if (streamOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(streamOutputs_);
            } else {
                return streamOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public int getStreamOutputsCount() {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.size();
            } else {
                return streamOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index) {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.get(index);
            } else {
                return streamOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public Builder clearStreamOutputs() {
            if (streamOutputsBuilder_ == null) {
                streamOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000800;
                onChanged();
            } else {
                streamOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder getStreamOutputsBuilder(
                int index) {
            return getStreamOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList() {
            if (streamOutputsBuilder_ != null) {
                return streamOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(streamOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder addStreamOutputsBuilder() {
            return getStreamOutputsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder addStreamOutputsBuilder(
                int index) {
            return getStreamOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 12;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder> getStreamOutputsBuilderList() {
            return getStreamOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsFieldBuilder() {
            if (streamOutputsBuilder_ == null) {
                streamOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        streamOutputs_,
                        ((bitField0_ & 0x00000800) != 0),
                        getParentForChildren(),
                        isClean());
                streamOutputs_ = null;
            }
            return streamOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_ =
                java.util.Collections.emptyList();

        private void ensureSegmentOutputsIsMutable() {
            if ((bitField0_ & 0x00001000) == 0) {
                segmentOutputs_ = new java.util.ArrayList<>(segmentOutputs_);
                bitField0_ |= 0x00001000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> segmentOutputsBuilder_;

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
            if (segmentOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            } else {
                return segmentOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public int getSegmentOutputsCount() {
            if (segmentOutputsBuilder_ == null) {
                return segmentOutputs_.size();
            } else {
                return segmentOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public Builder clearSegmentOutputs() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00001000;
                onChanged();
            } else {
                segmentOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder getSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
            if (segmentOutputsBuilder_ != null) {
                return segmentOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder() {
            return getSegmentOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 13;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder> getSegmentOutputsBuilderList() {
            return getSegmentOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsFieldBuilder() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        segmentOutputs_,
                        ((bitField0_ & 0x00001000) != 0),
                        getParentForChildren(),
                        isClean());
                segmentOutputs_ = null;
            }
            return segmentOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> imageOutputs_ =
                java.util.Collections.emptyList();

        private void ensureImageOutputsIsMutable() {
            if ((bitField0_ & 0x00002000) == 0) {
                imageOutputs_ = new java.util.ArrayList<>(imageOutputs_);
                bitField0_ |= 0x00002000;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImageOutput, im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder, im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> imageOutputsBuilder_;

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList() {
            if (imageOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(imageOutputs_);
            } else {
                return imageOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public int getImageOutputsCount() {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.size();
            } else {
                return imageOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index) {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.get(index);
            } else {
                return imageOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public Builder clearImageOutputs() {
            if (imageOutputsBuilder_ == null) {
                imageOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00002000;
                onChanged();
            } else {
                imageOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder getImageOutputsBuilder(
                int index) {
            return getImageOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList() {
            if (imageOutputsBuilder_ != null) {
                return imageOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(imageOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder addImageOutputsBuilder() {
            return getImageOutputsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder addImageOutputsBuilder(
                int index) {
            return getImageOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 14;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder> getImageOutputsBuilderList() {
            return getImageOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImageOutput, im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder, im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsFieldBuilder() {
            if (imageOutputsBuilder_ == null) {
                imageOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        imageOutputs_,
                        ((bitField0_ & 0x00002000) != 0),
                        getParentForChildren(),
                        isClean());
                imageOutputs_ = null;
            }
            return imageOutputsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.RoomCompositeEgressRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.RoomCompositeEgressRequest)
    private static final im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest();
    }

    public static im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RoomCompositeEgressRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public RoomCompositeEgressRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<RoomCompositeEgressRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RoomCompositeEgressRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}