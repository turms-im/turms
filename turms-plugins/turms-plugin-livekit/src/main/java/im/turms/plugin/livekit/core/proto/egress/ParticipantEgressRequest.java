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
 * record audio and video from a single participant
 * </pre>
 *
 * Protobuf type {@code livekit.ParticipantEgressRequest}
 */
public final class ParticipantEgressRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ParticipantEgressRequest)
        ParticipantEgressRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ParticipantEgressRequest.class.getName());
    }

    // Use ParticipantEgressRequest.newBuilder() to construct.
    private ParticipantEgressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ParticipantEgressRequest() {
        roomName_ = "";
        identity_ = "";
        fileOutputs_ = java.util.Collections.emptyList();
        streamOutputs_ = java.util.Collections.emptyList();
        segmentOutputs_ = java.util.Collections.emptyList();
        imageOutputs_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ParticipantEgressRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ParticipantEgressRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.class,
                        im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.Builder.class);
    }

    private int optionsCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object options_;

    public enum OptionsCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        PRESET(4),
        ADVANCED(5),
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
                case 4 -> PRESET;
                case 5 -> ADVANCED;
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

    public static final int IDENTITY_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object identity_ = "";

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string identity = 2;</code>
     *
     * @return The identity.
     */
    @java.lang.Override
    public java.lang.String getIdentity() {
        java.lang.Object ref = identity_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            identity_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string identity = 2;</code>
     *
     * @return The bytes for identity.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIdentityBytes() {
        java.lang.Object ref = identity_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            identity_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SCREEN_SHARE_FIELD_NUMBER = 3;
    private boolean screenShare_ = false;

    /**
     * <pre>
     * (default false)
     * </pre>
     *
     * <code>bool screen_share = 3;</code>
     *
     * @return The screenShare.
     */
    @java.lang.Override
    public boolean getScreenShare() {
        return screenShare_;
    }

    public static final int PRESET_FIELD_NUMBER = 4;

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
     *
     * @return Whether the preset field is set.
     */
    public boolean hasPreset() {
        return optionsCase_ == 4;
    }

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    public int getPresetValue() {
        if (optionsCase_ == 4) {
            return (java.lang.Integer) options_;
        }
        return 0;
    }

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
     *
     * @return The preset.
     */
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
        if (optionsCase_ == 4) {
            im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset result =
                    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset
                            .forNumber((java.lang.Integer) options_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.UNRECOGNIZED
                    : result;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.H264_720P_30;
    }

    public static final int ADVANCED_FIELD_NUMBER = 5;

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 5;</code>
     *
     * @return Whether the advanced field is set.
     */
    @java.lang.Override
    public boolean hasAdvanced() {
        return optionsCase_ == 5;
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 5;</code>
     *
     * @return The advanced.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
        if (optionsCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 5;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
        if (optionsCase_ == 5) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    public static final int FILE_OUTPUTS_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_;

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    @java.lang.Override
    public int getFileOutputsCount() {
        return fileOutputs_.size();
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index) {
        return fileOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index) {
        return fileOutputs_.get(index);
    }

    public static final int STREAM_OUTPUTS_FIELD_NUMBER = 7;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> streamOutputs_;

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList() {
        return streamOutputs_;
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList() {
        return streamOutputs_;
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    @java.lang.Override
    public int getStreamOutputsCount() {
        return streamOutputs_.size();
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index) {
        return streamOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder getStreamOutputsOrBuilder(
            int index) {
        return streamOutputs_.get(index);
    }

    public static final int SEGMENT_OUTPUTS_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_;

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    @java.lang.Override
    public int getSegmentOutputsCount() {
        return segmentOutputs_.size();
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(
            int index) {
        return segmentOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index) {
        return segmentOutputs_.get(index);
    }

    public static final int IMAGE_OUTPUTS_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> imageOutputs_;

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList() {
        return imageOutputs_;
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList() {
        return imageOutputs_;
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    @java.lang.Override
    public int getImageOutputsCount() {
        return imageOutputs_.size();
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index) {
        return imageOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, identity_);
        }
        if (screenShare_) {
            output.writeBool(3, screenShare_);
        }
        if (optionsCase_ == 4) {
            output.writeEnum(4, ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 5) {
            output.writeMessage(5,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            output.writeMessage(6, encodedFileOutput);
        }
        for (StreamOutput streamOutput : streamOutputs_) {
            output.writeMessage(7, streamOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            output.writeMessage(8, segmentedFileOutput);
        }
        for (ImageOutput imageOutput : imageOutputs_) {
            output.writeMessage(9, imageOutput);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, identity_);
        }
        if (screenShare_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, screenShare_);
        }
        if (optionsCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(4,
                    ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 5) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, encodedFileOutput);
        }
        for (StreamOutput streamOutput : streamOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, streamOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8,
                    segmentedFileOutput);
        }
        for (ImageOutput imageOutput : imageOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9, imageOutput);
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
        if (!(obj instanceof ParticipantEgressRequest other)) {
            return super.equals(obj);
        }

        if (!getRoomName().equals(other.getRoomName())) {
            return false;
        }
        if (!getIdentity().equals(other.getIdentity())) {
            return false;
        }
        if (getScreenShare() != other.getScreenShare()) {
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
        if (!getOptionsCase().equals(other.getOptionsCase())) {
            return false;
        }
        switch (optionsCase_) {
            case 4 -> {
                if (getPresetValue() != other.getPresetValue()) {
                    return false;
                }
            }
            case 5 -> {
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
        hash = (37 * hash) + IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getIdentity().hashCode();
        hash = (37 * hash) + SCREEN_SHARE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getScreenShare());
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
        switch (optionsCase_) {
            case 4 -> {
                hash = (37 * hash) + PRESET_FIELD_NUMBER;
                hash = (53 * hash) + getPresetValue();
            }
            case 5 -> {
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

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest prototype) {
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
     * record audio and video from a single participant
     * </pre>
     *
     * Protobuf type {@code livekit.ParticipantEgressRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ParticipantEgressRequest)
            im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ParticipantEgressRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ParticipantEgressRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.class,
                            im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest.newBuilder()
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
            identity_ = "";
            screenShare_ = false;
            if (advancedBuilder_ != null) {
                advancedBuilder_.clear();
            }
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
            } else {
                fileOutputs_ = null;
                fileOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000020;
            if (streamOutputsBuilder_ == null) {
                streamOutputs_ = java.util.Collections.emptyList();
            } else {
                streamOutputs_ = null;
                streamOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000040;
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
            } else {
                segmentOutputs_ = null;
                segmentOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000080;
            if (imageOutputsBuilder_ == null) {
                imageOutputs_ = java.util.Collections.emptyList();
            } else {
                imageOutputs_ = null;
                imageOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000100;
            optionsCase_ = 0;
            options_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ParticipantEgressRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest build() {
            im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest result =
                    new im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest result) {
            if (fileOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000020) != 0)) {
                    fileOutputs_ = java.util.Collections.unmodifiableList(fileOutputs_);
                    bitField0_ &= ~0x00000020;
                }
                result.fileOutputs_ = fileOutputs_;
            } else {
                result.fileOutputs_ = fileOutputsBuilder_.build();
            }
            if (streamOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000040) != 0)) {
                    streamOutputs_ = java.util.Collections.unmodifiableList(streamOutputs_);
                    bitField0_ &= ~0x00000040;
                }
                result.streamOutputs_ = streamOutputs_;
            } else {
                result.streamOutputs_ = streamOutputsBuilder_.build();
            }
            if (segmentOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)) {
                    segmentOutputs_ = java.util.Collections.unmodifiableList(segmentOutputs_);
                    bitField0_ &= ~0x00000080;
                }
                result.segmentOutputs_ = segmentOutputs_;
            } else {
                result.segmentOutputs_ = segmentOutputsBuilder_.build();
            }
            if (imageOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000100) != 0)) {
                    imageOutputs_ = java.util.Collections.unmodifiableList(imageOutputs_);
                    bitField0_ &= ~0x00000100;
                }
                result.imageOutputs_ = imageOutputs_;
            } else {
                result.imageOutputs_ = imageOutputsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.identity_ = identity_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.screenShare_ = screenShare_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest result) {
            result.optionsCase_ = optionsCase_;
            result.options_ = this.options_;
            if (optionsCase_ == 5 && advancedBuilder_ != null) {
                result.options_ = advancedBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getIdentity()
                    .isEmpty()) {
                identity_ = other.identity_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getScreenShare()) {
                setScreenShare(other.getScreenShare());
            }
            if (fileOutputsBuilder_ == null) {
                if (!other.fileOutputs_.isEmpty()) {
                    if (fileOutputs_.isEmpty()) {
                        fileOutputs_ = other.fileOutputs_;
                        bitField0_ &= ~0x00000020;
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
                        bitField0_ &= ~0x00000020;
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
                        bitField0_ &= ~0x00000040;
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
                        bitField0_ &= ~0x00000040;
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
                        bitField0_ &= ~0x00000080;
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
                        bitField0_ &= ~0x00000080;
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
                        bitField0_ &= ~0x00000100;
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
                        bitField0_ &= ~0x00000100;
                        imageOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getImageOutputsFieldBuilder()
                                        : null;
                    } else {
                        imageOutputsBuilder_.addAllMessages(other.imageOutputs_);
                    }
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
                            identity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            screenShare_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            int rawValue = input.readEnum();
                            optionsCase_ = 4;
                            options_ = rawValue;
                        } // case 32
                        case 42 -> {
                            input.readMessage(getAdvancedFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            optionsCase_ = 5;
                        } // case 42
                        case 50 -> {
                            EncodedFileOutput m = input.readMessage(EncodedFileOutput.parser(),
                                    extensionRegistry);
                            if (fileOutputsBuilder_ == null) {
                                ensureFileOutputsIsMutable();
                                fileOutputs_.add(m);
                            } else {
                                fileOutputsBuilder_.addMessage(m);
                            }
                        } // case 50
                        case 58 -> {
                            StreamOutput m =
                                    input.readMessage(StreamOutput.parser(), extensionRegistry);
                            if (streamOutputsBuilder_ == null) {
                                ensureStreamOutputsIsMutable();
                                streamOutputs_.add(m);
                            } else {
                                streamOutputsBuilder_.addMessage(m);
                            }
                        } // case 58
                        case 66 -> {
                            SegmentedFileOutput m = input.readMessage(SegmentedFileOutput.parser(),
                                    extensionRegistry);
                            if (segmentOutputsBuilder_ == null) {
                                ensureSegmentOutputsIsMutable();
                                segmentOutputs_.add(m);
                            } else {
                                segmentOutputsBuilder_.addMessage(m);
                            }
                        } // case 66
                        case 74 -> {
                            ImageOutput m =
                                    input.readMessage(ImageOutput.parser(), extensionRegistry);
                            if (imageOutputsBuilder_ == null) {
                                ensureImageOutputsIsMutable();
                                imageOutputs_.add(m);
                            } else {
                                imageOutputsBuilder_.addMessage(m);
                            }
                        } // case 74
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

        private java.lang.Object identity_ = "";

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string identity = 2;</code>
         *
         * @return The identity.
         */
        public java.lang.String getIdentity() {
            java.lang.Object ref = identity_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                identity_ = s;
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
         * <code>string identity = 2;</code>
         *
         * @return The bytes for identity.
         */
        public com.google.protobuf.ByteString getIdentityBytes() {
            java.lang.Object ref = identity_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                identity_ = b;
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
         * <code>string identity = 2;</code>
         *
         * @param value The identity to set.
         * @return This builder for chaining.
         */
        public Builder setIdentity(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            identity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string identity = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdentity() {
            identity_ = getDefaultInstance().getIdentity();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>string identity = 2;</code>
         *
         * @param value The bytes for identity to set.
         * @return This builder for chaining.
         */
        public Builder setIdentityBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            identity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private boolean screenShare_;

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool screen_share = 3;</code>
         *
         * @return The screenShare.
         */
        @java.lang.Override
        public boolean getScreenShare() {
            return screenShare_;
        }

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool screen_share = 3;</code>
         *
         * @param value The screenShare to set.
         * @return This builder for chaining.
         */
        public Builder setScreenShare(boolean value) {

            screenShare_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default false)
         * </pre>
         *
         * <code>bool screen_share = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearScreenShare() {
            bitField0_ &= ~0x00000004;
            screenShare_ = false;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
         *
         * @return Whether the preset field is set.
         */
        @java.lang.Override
        public boolean hasPreset() {
            return optionsCase_ == 4;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
         *
         * @return The enum numeric value on the wire for preset.
         */
        @java.lang.Override
        public int getPresetValue() {
            if (optionsCase_ == 4) {
                return (Integer) options_;
            }
            return 0;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
         *
         * @param value The enum numeric value on the wire for preset to set.
         * @return This builder for chaining.
         */
        public Builder setPresetValue(int value) {
            optionsCase_ = 4;
            options_ = value;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
         *
         * @return The preset.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
            if (optionsCase_ == 4) {
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
         * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
         *
         * @param value The preset to set.
         * @return This builder for chaining.
         */
        public Builder setPreset(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset value) {
            if (value == null) {
                throw new NullPointerException();
            }
            optionsCase_ = 4;
            options_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreset() {
            if (optionsCase_ == 4) {
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
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         *
         * @return Whether the advanced field is set.
         */
        @java.lang.Override
        public boolean hasAdvanced() {
            return optionsCase_ == 5;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         *
         * @return The advanced.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 5) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            } else {
                if (optionsCase_ == 5) {
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
         * <code>.livekit.EncodingOptions advanced = 5;</code>
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
            optionsCase_ = 5;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         */
        public Builder setAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder builderForValue) {
            if (advancedBuilder_ == null) {
                options_ = builderForValue.build();
                onChanged();
            } else {
                advancedBuilder_.setMessage(builderForValue.build());
            }
            optionsCase_ = 5;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         */
        public Builder mergeAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions value) {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 5
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
                if (optionsCase_ == 5) {
                    advancedBuilder_.mergeFrom(value);
                } else {
                    advancedBuilder_.setMessage(value);
                }
            }
            optionsCase_ = 5;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         */
        public Builder clearAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 5) {
                    optionsCase_ = 0;
                    options_ = null;
                    onChanged();
                }
            } else {
                if (optionsCase_ == 5) {
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
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder getAdvancedBuilder() {
            return getAdvancedFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
            if ((optionsCase_ == 5) && (advancedBuilder_ != null)) {
                return advancedBuilder_.getMessageOrBuilder();
            } else {
                if (optionsCase_ == 5) {
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
         * <code>.livekit.EncodingOptions advanced = 5;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodingOptions, im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder, im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder> getAdvancedFieldBuilder() {
            if (advancedBuilder_ == null) {
                if (!(optionsCase_ == 5)) {
                    options_ = im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                            .getDefaultInstance();
                }
                advancedBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_,
                        getParentForChildren(),
                        isClean());
                options_ = null;
            }
            optionsCase_ = 5;
            onChanged();
            return advancedBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_ =
                java.util.Collections.emptyList();

        private void ensureFileOutputsIsMutable() {
            if ((bitField0_ & 0x00000020) == 0) {
                fileOutputs_ = new java.util.ArrayList<>(fileOutputs_);
                bitField0_ |= 0x00000020;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> fileOutputsBuilder_;

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
            if (fileOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            } else {
                return fileOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public int getFileOutputsCount() {
            if (fileOutputsBuilder_ == null) {
                return fileOutputs_.size();
            } else {
                return fileOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public Builder clearFileOutputs() {
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000020;
                onChanged();
            } else {
                fileOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder getFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
            if (fileOutputsBuilder_ != null) {
                return fileOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder() {
            return getFileOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 6;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder> getFileOutputsBuilderList() {
            return getFileOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsFieldBuilder() {
            if (fileOutputsBuilder_ == null) {
                fileOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        fileOutputs_,
                        ((bitField0_ & 0x00000020) != 0),
                        getParentForChildren(),
                        isClean());
                fileOutputs_ = null;
            }
            return fileOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> streamOutputs_ =
                java.util.Collections.emptyList();

        private void ensureStreamOutputsIsMutable() {
            if ((bitField0_ & 0x00000040) == 0) {
                streamOutputs_ = new java.util.ArrayList<>(streamOutputs_);
                bitField0_ |= 0x00000040;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> streamOutputsBuilder_;

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput> getStreamOutputsList() {
            if (streamOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(streamOutputs_);
            } else {
                return streamOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public int getStreamOutputsCount() {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.size();
            } else {
                return streamOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput getStreamOutputs(int index) {
            if (streamOutputsBuilder_ == null) {
                return streamOutputs_.get(index);
            } else {
                return streamOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public Builder clearStreamOutputs() {
            if (streamOutputsBuilder_ == null) {
                streamOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000040;
                onChanged();
            } else {
                streamOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder getStreamOutputsBuilder(
                int index) {
            return getStreamOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
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
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsOrBuilderList() {
            if (streamOutputsBuilder_ != null) {
                return streamOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(streamOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder addStreamOutputsBuilder() {
            return getStreamOutputsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder addStreamOutputsBuilder(
                int index) {
            return getStreamOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamOutput stream_outputs = 7;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder> getStreamOutputsBuilderList() {
            return getStreamOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamOutput, im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder, im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder> getStreamOutputsFieldBuilder() {
            if (streamOutputsBuilder_ == null) {
                streamOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        streamOutputs_,
                        ((bitField0_ & 0x00000040) != 0),
                        getParentForChildren(),
                        isClean());
                streamOutputs_ = null;
            }
            return streamOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_ =
                java.util.Collections.emptyList();

        private void ensureSegmentOutputsIsMutable() {
            if ((bitField0_ & 0x00000080) == 0) {
                segmentOutputs_ = new java.util.ArrayList<>(segmentOutputs_);
                bitField0_ |= 0x00000080;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> segmentOutputsBuilder_;

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
            if (segmentOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            } else {
                return segmentOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public int getSegmentOutputsCount() {
            if (segmentOutputsBuilder_ == null) {
                return segmentOutputs_.size();
            } else {
                return segmentOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public Builder clearSegmentOutputs() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000080;
                onChanged();
            } else {
                segmentOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder getSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
            if (segmentOutputsBuilder_ != null) {
                return segmentOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder() {
            return getSegmentOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 8;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder> getSegmentOutputsBuilderList() {
            return getSegmentOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsFieldBuilder() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        segmentOutputs_,
                        ((bitField0_ & 0x00000080) != 0),
                        getParentForChildren(),
                        isClean());
                segmentOutputs_ = null;
            }
            return segmentOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> imageOutputs_ =
                java.util.Collections.emptyList();

        private void ensureImageOutputsIsMutable() {
            if ((bitField0_ & 0x00000100) == 0) {
                imageOutputs_ = new java.util.ArrayList<>(imageOutputs_);
                bitField0_ |= 0x00000100;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImageOutput, im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder, im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> imageOutputsBuilder_;

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput> getImageOutputsList() {
            if (imageOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(imageOutputs_);
            } else {
                return imageOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public int getImageOutputsCount() {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.size();
            } else {
                return imageOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput getImageOutputs(int index) {
            if (imageOutputsBuilder_ == null) {
                return imageOutputs_.get(index);
            } else {
                return imageOutputsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public Builder clearImageOutputs() {
            if (imageOutputsBuilder_ == null) {
                imageOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000100;
                onChanged();
            } else {
                imageOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder getImageOutputsBuilder(
                int index) {
            return getImageOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
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
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsOrBuilderList() {
            if (imageOutputsBuilder_ != null) {
                return imageOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(imageOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder addImageOutputsBuilder() {
            return getImageOutputsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder addImageOutputsBuilder(
                int index) {
            return getImageOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.ImageOutput.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ImageOutput image_outputs = 9;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder> getImageOutputsBuilderList() {
            return getImageOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ImageOutput, im.turms.plugin.livekit.core.proto.egress.ImageOutput.Builder, im.turms.plugin.livekit.core.proto.egress.ImageOutputOrBuilder> getImageOutputsFieldBuilder() {
            if (imageOutputsBuilder_ == null) {
                imageOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        imageOutputs_,
                        ((bitField0_ & 0x00000100) != 0),
                        getParentForChildren(),
                        isClean());
                imageOutputs_ = null;
            }
            return imageOutputsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ParticipantEgressRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.ParticipantEgressRequest)
    private static final im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest();
    }

    public static im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ParticipantEgressRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ParticipantEgressRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<ParticipantEgressRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ParticipantEgressRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}