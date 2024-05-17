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
 * Protobuf type {@code livekit.AutoParticipantEgress}
 */
public final class AutoParticipantEgress extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.AutoParticipantEgress)
        AutoParticipantEgressOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                AutoParticipantEgress.class.getName());
    }

    // Use AutoParticipantEgress.newBuilder() to construct.
    private AutoParticipantEgress(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private AutoParticipantEgress() {
        fileOutputs_ = java.util.Collections.emptyList();
        segmentOutputs_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AutoParticipantEgress_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AutoParticipantEgress_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.class,
                        im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.Builder.class);
    }

    private int optionsCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object options_;

    public enum OptionsCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        PRESET(1),
        ADVANCED(2),
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
                case 1 -> PRESET;
                case 2 -> ADVANCED;
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

    public static final int PRESET_FIELD_NUMBER = 1;

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
     *
     * @return Whether the preset field is set.
     */
    public boolean hasPreset() {
        return optionsCase_ == 1;
    }

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    public int getPresetValue() {
        if (optionsCase_ == 1) {
            return (java.lang.Integer) options_;
        }
        return 0;
    }

    /**
     * <pre>
     * (default H264_720P_30)
     * </pre>
     *
     * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
     *
     * @return The preset.
     */
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
        if (optionsCase_ == 1) {
            im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset result =
                    im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset
                            .forNumber((java.lang.Integer) options_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.UNRECOGNIZED
                    : result;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset.H264_720P_30;
    }

    public static final int ADVANCED_FIELD_NUMBER = 2;

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 2;</code>
     *
     * @return Whether the advanced field is set.
     */
    @java.lang.Override
    public boolean hasAdvanced() {
        return optionsCase_ == 2;
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 2;</code>
     *
     * @return The advanced.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
        if (optionsCase_ == 2) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    /**
     * <pre>
     * (optional)
     * </pre>
     *
     * <code>.livekit.EncodingOptions advanced = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
        if (optionsCase_ == 2) {
            return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
        }
        return im.turms.plugin.livekit.core.proto.egress.EncodingOptions.getDefaultInstance();
    }

    public static final int FILE_OUTPUTS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_;

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
        return fileOutputs_;
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    @java.lang.Override
    public int getFileOutputsCount() {
        return fileOutputs_.size();
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput getFileOutputs(int index) {
        return fileOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder getFileOutputsOrBuilder(
            int index) {
        return fileOutputs_.get(index);
    }

    public static final int SEGMENT_OUTPUTS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_;

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
        return segmentOutputs_;
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    @java.lang.Override
    public int getSegmentOutputsCount() {
        return segmentOutputs_.size();
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput getSegmentOutputs(
            int index) {
        return segmentOutputs_.get(index);
    }

    /**
     * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder getSegmentOutputsOrBuilder(
            int index) {
        return segmentOutputs_.get(index);
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
        if (optionsCase_ == 1) {
            output.writeEnum(1, ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 2) {
            output.writeMessage(2,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            output.writeMessage(3, encodedFileOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            output.writeMessage(4, segmentedFileOutput);
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
        if (optionsCase_ == 1) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1,
                    ((java.lang.Integer) options_));
        }
        if (optionsCase_ == 2) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2,
                    (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_);
        }
        for (EncodedFileOutput encodedFileOutput : fileOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, encodedFileOutput);
        }
        for (SegmentedFileOutput segmentedFileOutput : segmentOutputs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    segmentedFileOutput);
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
        if (!(obj instanceof AutoParticipantEgress other)) {
            return super.equals(obj);
        }

        if (!getFileOutputsList().equals(other.getFileOutputsList())) {
            return false;
        }
        if (!getSegmentOutputsList().equals(other.getSegmentOutputsList())) {
            return false;
        }
        if (!getOptionsCase().equals(other.getOptionsCase())) {
            return false;
        }
        switch (optionsCase_) {
            case 1 -> {
                if (getPresetValue() != other.getPresetValue()) {
                    return false;
                }
            }
            case 2 -> {
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
        if (getFileOutputsCount() > 0) {
            hash = (37 * hash) + FILE_OUTPUTS_FIELD_NUMBER;
            hash = (53 * hash) + getFileOutputsList().hashCode();
        }
        if (getSegmentOutputsCount() > 0) {
            hash = (37 * hash) + SEGMENT_OUTPUTS_FIELD_NUMBER;
            hash = (53 * hash) + getSegmentOutputsList().hashCode();
        }
        switch (optionsCase_) {
            case 1 -> {
                hash = (37 * hash) + PRESET_FIELD_NUMBER;
                hash = (53 * hash) + getPresetValue();
            }
            case 2 -> {
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

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress prototype) {
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
     * Protobuf type {@code livekit.AutoParticipantEgress}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.AutoParticipantEgress)
            im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgressOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AutoParticipantEgress_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AutoParticipantEgress_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.class,
                            im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (advancedBuilder_ != null) {
                advancedBuilder_.clear();
            }
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
            } else {
                fileOutputs_ = null;
                fileOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000004;
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
            } else {
                segmentOutputs_ = null;
                segmentOutputsBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            optionsCase_ = 0;
            options_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AutoParticipantEgress_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress build() {
            im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress result =
                    new im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress result) {
            if (fileOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)) {
                    fileOutputs_ = java.util.Collections.unmodifiableList(fileOutputs_);
                    bitField0_ &= ~0x00000004;
                }
                result.fileOutputs_ = fileOutputs_;
            } else {
                result.fileOutputs_ = fileOutputsBuilder_.build();
            }
            if (segmentOutputsBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    segmentOutputs_ = java.util.Collections.unmodifiableList(segmentOutputs_);
                    bitField0_ &= ~0x00000008;
                }
                result.segmentOutputs_ = segmentOutputs_;
            } else {
                result.segmentOutputs_ = segmentOutputsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress result) {
            int from_bitField0_ = bitField0_;
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress result) {
            result.optionsCase_ = optionsCase_;
            result.options_ = this.options_;
            if (optionsCase_ == 2 && advancedBuilder_ != null) {
                result.options_ = advancedBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                    .getDefaultInstance()) {
                return this;
            }
            if (fileOutputsBuilder_ == null) {
                if (!other.fileOutputs_.isEmpty()) {
                    if (fileOutputs_.isEmpty()) {
                        fileOutputs_ = other.fileOutputs_;
                        bitField0_ &= ~0x00000004;
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
                        bitField0_ &= ~0x00000004;
                        fileOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getFileOutputsFieldBuilder()
                                        : null;
                    } else {
                        fileOutputsBuilder_.addAllMessages(other.fileOutputs_);
                    }
                }
            }
            if (segmentOutputsBuilder_ == null) {
                if (!other.segmentOutputs_.isEmpty()) {
                    if (segmentOutputs_.isEmpty()) {
                        segmentOutputs_ = other.segmentOutputs_;
                        bitField0_ &= ~0x00000008;
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
                        bitField0_ &= ~0x00000008;
                        segmentOutputsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getSegmentOutputsFieldBuilder()
                                        : null;
                    } else {
                        segmentOutputsBuilder_.addAllMessages(other.segmentOutputs_);
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
                        case 8 -> {
                            int rawValue = input.readEnum();
                            optionsCase_ = 1;
                            options_ = rawValue;
                        } // case 8
                        case 18 -> {
                            input.readMessage(getAdvancedFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            optionsCase_ = 2;
                        } // case 18
                        case 26 -> {
                            EncodedFileOutput m = input.readMessage(EncodedFileOutput.parser(),
                                    extensionRegistry);
                            if (fileOutputsBuilder_ == null) {
                                ensureFileOutputsIsMutable();
                                fileOutputs_.add(m);
                            } else {
                                fileOutputsBuilder_.addMessage(m);
                            }
                        } // case 26
                        case 34 -> {
                            SegmentedFileOutput m = input.readMessage(SegmentedFileOutput.parser(),
                                    extensionRegistry);
                            if (segmentOutputsBuilder_ == null) {
                                ensureSegmentOutputsIsMutable();
                                segmentOutputs_.add(m);
                            } else {
                                segmentOutputsBuilder_.addMessage(m);
                            }
                        } // case 34
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

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
         *
         * @return Whether the preset field is set.
         */
        @java.lang.Override
        public boolean hasPreset() {
            return optionsCase_ == 1;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
         *
         * @return The enum numeric value on the wire for preset.
         */
        @java.lang.Override
        public int getPresetValue() {
            if (optionsCase_ == 1) {
                return (Integer) options_;
            }
            return 0;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
         *
         * @param value The enum numeric value on the wire for preset to set.
         * @return This builder for chaining.
         */
        public Builder setPresetValue(int value) {
            optionsCase_ = 1;
            options_ = value;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
         *
         * @return The preset.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset getPreset() {
            if (optionsCase_ == 1) {
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
         * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
         *
         * @param value The preset to set.
         * @return This builder for chaining.
         */
        public Builder setPreset(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptionsPreset value) {
            if (value == null) {
                throw new NullPointerException();
            }
            optionsCase_ = 1;
            options_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * (default H264_720P_30)
         * </pre>
         *
         * <code>.livekit.EncodingOptionsPreset preset = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreset() {
            if (optionsCase_ == 1) {
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
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         *
         * @return Whether the advanced field is set.
         */
        @java.lang.Override
        public boolean hasAdvanced() {
            return optionsCase_ == 2;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         *
         * @return The advanced.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions getAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 2) {
                    return (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_;
                }
                return im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                        .getDefaultInstance();
            } else {
                if (optionsCase_ == 2) {
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
         * <code>.livekit.EncodingOptions advanced = 2;</code>
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
            optionsCase_ = 2;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         */
        public Builder setAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder builderForValue) {
            if (advancedBuilder_ == null) {
                options_ = builderForValue.build();
                onChanged();
            } else {
                advancedBuilder_.setMessage(builderForValue.build());
            }
            optionsCase_ = 2;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         */
        public Builder mergeAdvanced(
                im.turms.plugin.livekit.core.proto.egress.EncodingOptions value) {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 2
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
                if (optionsCase_ == 2) {
                    advancedBuilder_.mergeFrom(value);
                } else {
                    advancedBuilder_.setMessage(value);
                }
            }
            optionsCase_ = 2;
            return this;
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         */
        public Builder clearAdvanced() {
            if (advancedBuilder_ == null) {
                if (optionsCase_ == 2) {
                    optionsCase_ = 0;
                    options_ = null;
                    onChanged();
                }
            } else {
                if (optionsCase_ == 2) {
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
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder getAdvancedBuilder() {
            return getAdvancedFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * (optional)
         * </pre>
         *
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder getAdvancedOrBuilder() {
            if ((optionsCase_ == 2) && (advancedBuilder_ != null)) {
                return advancedBuilder_.getMessageOrBuilder();
            } else {
                if (optionsCase_ == 2) {
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
         * <code>.livekit.EncodingOptions advanced = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodingOptions, im.turms.plugin.livekit.core.proto.egress.EncodingOptions.Builder, im.turms.plugin.livekit.core.proto.egress.EncodingOptionsOrBuilder> getAdvancedFieldBuilder() {
            if (advancedBuilder_ == null) {
                if (!(optionsCase_ == 2)) {
                    options_ = im.turms.plugin.livekit.core.proto.egress.EncodingOptions
                            .getDefaultInstance();
                }
                advancedBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.egress.EncodingOptions) options_,
                        getParentForChildren(),
                        isClean());
                options_ = null;
            }
            optionsCase_ = 2;
            onChanged();
            return advancedBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> fileOutputs_ =
                java.util.Collections.emptyList();

        private void ensureFileOutputsIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                fileOutputs_ = new java.util.ArrayList<>(fileOutputs_);
                bitField0_ |= 0x00000004;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> fileOutputsBuilder_;

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput> getFileOutputsList() {
            if (fileOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            } else {
                return fileOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public int getFileOutputsCount() {
            if (fileOutputsBuilder_ == null) {
                return fileOutputs_.size();
            } else {
                return fileOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public Builder clearFileOutputs() {
            if (fileOutputsBuilder_ == null) {
                fileOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000004;
                onChanged();
            } else {
                fileOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder getFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
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
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsOrBuilderList() {
            if (fileOutputsBuilder_ != null) {
                return fileOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(fileOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder() {
            return getFileOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder addFileOutputsBuilder(
                int index) {
            return getFileOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.EncodedFileOutput file_outputs = 3;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder> getFileOutputsBuilderList() {
            return getFileOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.EncodedFileOutputOrBuilder> getFileOutputsFieldBuilder() {
            if (fileOutputsBuilder_ == null) {
                fileOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        fileOutputs_,
                        ((bitField0_ & 0x00000004) != 0),
                        getParentForChildren(),
                        isClean());
                fileOutputs_ = null;
            }
            return fileOutputsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> segmentOutputs_ =
                java.util.Collections.emptyList();

        private void ensureSegmentOutputsIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                segmentOutputs_ = new java.util.ArrayList<>(segmentOutputs_);
                bitField0_ |= 0x00000008;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> segmentOutputsBuilder_;

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput> getSegmentOutputsList() {
            if (segmentOutputsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            } else {
                return segmentOutputsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public int getSegmentOutputsCount() {
            if (segmentOutputsBuilder_ == null) {
                return segmentOutputs_.size();
            } else {
                return segmentOutputsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public Builder clearSegmentOutputs() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000008;
                onChanged();
            } else {
                segmentOutputsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder getSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
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
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsOrBuilderList() {
            if (segmentOutputsBuilder_ != null) {
                return segmentOutputsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(segmentOutputs_);
            }
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder() {
            return getSegmentOutputsFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder addSegmentOutputsBuilder(
                int index) {
            return getSegmentOutputsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SegmentedFileOutput segment_outputs = 4;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder> getSegmentOutputsBuilderList() {
            return getSegmentOutputsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutput.Builder, im.turms.plugin.livekit.core.proto.egress.SegmentedFileOutputOrBuilder> getSegmentOutputsFieldBuilder() {
            if (segmentOutputsBuilder_ == null) {
                segmentOutputsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        segmentOutputs_,
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                segmentOutputs_ = null;
            }
            return segmentOutputsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.AutoParticipantEgress)
    }

    // @@protoc_insertion_point(class_scope:livekit.AutoParticipantEgress)
    private static final im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress();
    }

    public static im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AutoParticipantEgress> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public AutoParticipantEgress parsePartialFrom(
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

    public static com.google.protobuf.Parser<AutoParticipantEgress> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AutoParticipantEgress> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}