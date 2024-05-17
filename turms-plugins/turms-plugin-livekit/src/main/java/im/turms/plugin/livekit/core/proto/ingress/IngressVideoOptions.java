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

package im.turms.plugin.livekit.core.proto.ingress;

/**
 * Protobuf type {@code livekit.IngressVideoOptions}
 */
public final class IngressVideoOptions extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.IngressVideoOptions)
        IngressVideoOptionsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressVideoOptions.class.getName());
    }

    // Use IngressVideoOptions.newBuilder() to construct.
    private IngressVideoOptions(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private IngressVideoOptions() {
        name_ = "";
        source_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoOptions_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoOptions_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.class,
                        im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.Builder.class);
    }

    private int encodingOptionsCase_ = 0;
    @SuppressWarnings("serial")
    private java.lang.Object encodingOptions_;

    public enum EncodingOptionsCase implements com.google.protobuf.Internal.EnumLite,
            com.google.protobuf.AbstractMessage.InternalOneOfEnum {
        PRESET(3),
        OPTIONS(4),
        ENCODINGOPTIONS_NOT_SET(0);

        private final int value;

        EncodingOptionsCase(int value) {
            this.value = value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static EncodingOptionsCase valueOf(int value) {
            return forNumber(value);
        }

        public static EncodingOptionsCase forNumber(int value) {
            return switch (value) {
                case 3 -> PRESET;
                case 4 -> OPTIONS;
                case 0 -> ENCODINGOPTIONS_NOT_SET;
                default -> null;
            };
        }

        public int getNumber() {
            return this.value;
        }
    }

    public EncodingOptionsCase getEncodingOptionsCase() {
        return EncodingOptionsCase.forNumber(encodingOptionsCase_);
    }

    public static final int NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <code>string name = 1;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <code>string name = 1;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SOURCE_FIELD_NUMBER = 2;
    private int source_ = 0;

    /**
     * <code>.livekit.TrackSource source = 2;</code>
     *
     * @return The enum numeric value on the wire for source.
     */
    @java.lang.Override
    public int getSourceValue() {
        return source_;
    }

    /**
     * <code>.livekit.TrackSource source = 2;</code>
     *
     * @return The source.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackSource getSource() {
        im.turms.plugin.livekit.core.proto.models.TrackSource result =
                im.turms.plugin.livekit.core.proto.models.TrackSource.forNumber(source_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.TrackSource.UNRECOGNIZED
                : result;
    }

    public static final int PRESET_FIELD_NUMBER = 3;

    /**
     * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
     *
     * @return Whether the preset field is set.
     */
    public boolean hasPreset() {
        return encodingOptionsCase_ == 3;
    }

    /**
     * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
     *
     * @return The enum numeric value on the wire for preset.
     */
    public int getPresetValue() {
        if (encodingOptionsCase_ == 3) {
            return (java.lang.Integer) encodingOptions_;
        }
        return 0;
    }

    /**
     * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
     *
     * @return The preset.
     */
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset getPreset() {
        if (encodingOptionsCase_ == 3) {
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset result =
                    im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset
                            .forNumber((java.lang.Integer) encodingOptions_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset.UNRECOGNIZED
                    : result;
        }
        return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset.H264_720P_30FPS_3_LAYERS;
    }

    public static final int OPTIONS_FIELD_NUMBER = 4;

    /**
     * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
     *
     * @return Whether the options field is set.
     */
    @java.lang.Override
    public boolean hasOptions() {
        return encodingOptionsCase_ == 4;
    }

    /**
     * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
     *
     * @return The options.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions getOptions() {
        if (encodingOptionsCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_;
        }
        return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                .getDefaultInstance();
    }

    /**
     * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptionsOrBuilder getOptionsOrBuilder() {
        if (encodingOptionsCase_ == 4) {
            return (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_;
        }
        return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                .getDefaultInstance();
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, name_);
        }
        if (source_ != im.turms.plugin.livekit.core.proto.models.TrackSource.UNKNOWN.getNumber()) {
            output.writeEnum(2, source_);
        }
        if (encodingOptionsCase_ == 3) {
            output.writeEnum(3, ((java.lang.Integer) encodingOptions_));
        }
        if (encodingOptionsCase_ == 4) {
            output.writeMessage(4,
                    (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, name_);
        }
        if (source_ != im.turms.plugin.livekit.core.proto.models.TrackSource.UNKNOWN.getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, source_);
        }
        if (encodingOptionsCase_ == 3) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(3,
                    ((java.lang.Integer) encodingOptions_));
        }
        if (encodingOptionsCase_ == 4) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_);
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
        if (!(obj instanceof IngressVideoOptions other)) {
            return super.equals(obj);
        }

        if (!getName().equals(other.getName())) {
            return false;
        }
        if (source_ != other.source_) {
            return false;
        }
        if (!getEncodingOptionsCase().equals(other.getEncodingOptionsCase())) {
            return false;
        }
        switch (encodingOptionsCase_) {
            case 3 -> {
                if (getPresetValue() != other.getPresetValue()) {
                    return false;
                }
            }
            case 4 -> {
                if (!getOptions().equals(other.getOptions())) {
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
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (37 * hash) + SOURCE_FIELD_NUMBER;
        hash = (53 * hash) + source_;
        switch (encodingOptionsCase_) {
            case 3 -> {
                hash = (37 * hash) + PRESET_FIELD_NUMBER;
                hash = (53 * hash) + getPresetValue();
            }
            case 4 -> {
                hash = (37 * hash) + OPTIONS_FIELD_NUMBER;
                hash = (53 * hash) + getOptions().hashCode();
            }
            default -> {
            }
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions prototype) {
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
     * Protobuf type {@code livekit.IngressVideoOptions}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.IngressVideoOptions)
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptionsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoOptions_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoOptions_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.class,
                            im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            name_ = "";
            source_ = 0;
            if (optionsBuilder_ != null) {
                optionsBuilder_.clear();
            }
            encodingOptionsCase_ = 0;
            encodingOptions_ = null;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoOptions_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions build() {
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions result =
                    new im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            buildPartialOneofs(result);
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.source_ = source_;
            }
        }

        private void buildPartialOneofs(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions result) {
            result.encodingOptionsCase_ = encodingOptionsCase_;
            result.encodingOptions_ = this.encodingOptions_;
            if (encodingOptionsCase_ == 4 && optionsBuilder_ != null) {
                result.encodingOptions_ = optionsBuilder_.build();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.source_ != 0) {
                setSourceValue(other.getSourceValue());
            }
            switch (other.getEncodingOptionsCase()) {
                case PRESET -> setPresetValue(other.getPresetValue());
                case OPTIONS -> mergeOptions(other.getOptions());
                case ENCODINGOPTIONS_NOT_SET -> {
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
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            source_ = input.readEnum();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            int rawValue = input.readEnum();
                            encodingOptionsCase_ = 3;
                            encodingOptions_ = rawValue;
                        } // case 24
                        case 34 -> {
                            input.readMessage(getOptionsFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            encodingOptionsCase_ = 4;
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

        private int encodingOptionsCase_ = 0;
        private java.lang.Object encodingOptions_;

        public EncodingOptionsCase getEncodingOptionsCase() {
            return EncodingOptionsCase.forNumber(encodingOptionsCase_);
        }

        public Builder clearEncodingOptions() {
            encodingOptionsCase_ = 0;
            encodingOptions_ = null;
            onChanged();
            return this;
        }

        private int bitField0_;

        private java.lang.Object name_ = "";

        /**
         * <code>string name = 1;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string name = 1;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string name = 1;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 1;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private int source_ = 0;

        /**
         * <code>.livekit.TrackSource source = 2;</code>
         *
         * @return The enum numeric value on the wire for source.
         */
        @java.lang.Override
        public int getSourceValue() {
            return source_;
        }

        /**
         * <code>.livekit.TrackSource source = 2;</code>
         *
         * @param value The enum numeric value on the wire for source to set.
         * @return This builder for chaining.
         */
        public Builder setSourceValue(int value) {
            source_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackSource source = 2;</code>
         *
         * @return The source.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TrackSource getSource() {
            im.turms.plugin.livekit.core.proto.models.TrackSource result =
                    im.turms.plugin.livekit.core.proto.models.TrackSource.forNumber(source_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.TrackSource.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.TrackSource source = 2;</code>
         *
         * @param value The source to set.
         * @return This builder for chaining.
         */
        public Builder setSource(im.turms.plugin.livekit.core.proto.models.TrackSource value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000002;
            source_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TrackSource source = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSource() {
            bitField0_ &= ~0x00000002;
            source_ = 0;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
         *
         * @return Whether the preset field is set.
         */
        @java.lang.Override
        public boolean hasPreset() {
            return encodingOptionsCase_ == 3;
        }

        /**
         * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
         *
         * @return The enum numeric value on the wire for preset.
         */
        @java.lang.Override
        public int getPresetValue() {
            if (encodingOptionsCase_ == 3) {
                return (Integer) encodingOptions_;
            }
            return 0;
        }

        /**
         * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
         *
         * @param value The enum numeric value on the wire for preset to set.
         * @return This builder for chaining.
         */
        public Builder setPresetValue(int value) {
            encodingOptionsCase_ = 3;
            encodingOptions_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
         *
         * @return The preset.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset getPreset() {
            if (encodingOptionsCase_ == 3) {
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset result =
                        im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset
                                .forNumber((java.lang.Integer) encodingOptions_);
                return result == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset.UNRECOGNIZED
                        : result;
            }
            return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset.H264_720P_30FPS_3_LAYERS;
        }

        /**
         * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
         *
         * @param value The preset to set.
         * @return This builder for chaining.
         */
        public Builder setPreset(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingPreset value) {
            if (value == null) {
                throw new NullPointerException();
            }
            encodingOptionsCase_ = 3;
            encodingOptions_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingPreset preset = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreset() {
            if (encodingOptionsCase_ == 3) {
                encodingOptionsCase_ = 0;
                encodingOptions_ = null;
                onChanged();
            }
            return this;
        }

        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions, im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptionsOrBuilder> optionsBuilder_;

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         *
         * @return Whether the options field is set.
         */
        @java.lang.Override
        public boolean hasOptions() {
            return encodingOptionsCase_ == 4;
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         *
         * @return The options.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions getOptions() {
            if (optionsBuilder_ == null) {
                if (encodingOptionsCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_;
                }
                return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                        .getDefaultInstance();
            } else {
                if (encodingOptionsCase_ == 4) {
                    return optionsBuilder_.getMessage();
                }
                return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        public Builder setOptions(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions value) {
            if (optionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                encodingOptions_ = value;
                onChanged();
            } else {
                optionsBuilder_.setMessage(value);
            }
            encodingOptionsCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        public Builder setOptions(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.Builder builderForValue) {
            if (optionsBuilder_ == null) {
                encodingOptions_ = builderForValue.build();
                onChanged();
            } else {
                optionsBuilder_.setMessage(builderForValue.build());
            }
            encodingOptionsCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        public Builder mergeOptions(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions value) {
            if (optionsBuilder_ == null) {
                if (encodingOptionsCase_ == 4
                        && encodingOptions_ != im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                                .getDefaultInstance()) {
                    encodingOptions_ =
                            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                                    .newBuilder(
                                            (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_)
                                    .mergeFrom(value)
                                    .buildPartial();
                } else {
                    encodingOptions_ = value;
                }
                onChanged();
            } else {
                if (encodingOptionsCase_ == 4) {
                    optionsBuilder_.mergeFrom(value);
                } else {
                    optionsBuilder_.setMessage(value);
                }
            }
            encodingOptionsCase_ = 4;
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        public Builder clearOptions() {
            if (optionsBuilder_ == null) {
                if (encodingOptionsCase_ == 4) {
                    encodingOptionsCase_ = 0;
                    encodingOptions_ = null;
                    onChanged();
                }
            } else {
                if (encodingOptionsCase_ == 4) {
                    encodingOptionsCase_ = 0;
                    encodingOptions_ = null;
                }
                optionsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.Builder getOptionsBuilder() {
            return getOptionsFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptionsOrBuilder getOptionsOrBuilder() {
            if ((encodingOptionsCase_ == 4) && (optionsBuilder_ != null)) {
                return optionsBuilder_.getMessageOrBuilder();
            } else {
                if (encodingOptionsCase_ == 4) {
                    return (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_;
                }
                return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                        .getDefaultInstance();
            }
        }

        /**
         * <code>.livekit.IngressVideoEncodingOptions options = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions, im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptionsOrBuilder> getOptionsFieldBuilder() {
            if (optionsBuilder_ == null) {
                if (!(encodingOptionsCase_ == 4)) {
                    encodingOptions_ =
                            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                                    .getDefaultInstance();
                }
                optionsBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) encodingOptions_,
                        getParentForChildren(),
                        isClean());
                encodingOptions_ = null;
            }
            encodingOptionsCase_ = 4;
            onChanged();
            return optionsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.IngressVideoOptions)
    }

    // @@protoc_insertion_point(class_scope:livekit.IngressVideoOptions)
    private static final im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<IngressVideoOptions> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public IngressVideoOptions parsePartialFrom(
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

    public static com.google.protobuf.Parser<IngressVideoOptions> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<IngressVideoOptions> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}