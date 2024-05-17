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
 * Protobuf type {@code livekit.InputVideoState}
 */
public final class InputVideoState extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.InputVideoState)
        InputVideoStateOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                InputVideoState.class.getName());
    }

    // Use InputVideoState.newBuilder() to construct.
    private InputVideoState(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private InputVideoState() {
        mimeType_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputVideoState_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputVideoState_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.InputVideoState.class,
                        im.turms.plugin.livekit.core.proto.ingress.InputVideoState.Builder.class);
    }

    public static final int MIME_TYPE_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object mimeType_ = "";

    /**
     * <code>string mime_type = 1;</code>
     *
     * @return The mimeType.
     */
    @java.lang.Override
    public java.lang.String getMimeType() {
        java.lang.Object ref = mimeType_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            mimeType_ = s;
            return s;
        }
    }

    /**
     * <code>string mime_type = 1;</code>
     *
     * @return The bytes for mimeType.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMimeTypeBytes() {
        java.lang.Object ref = mimeType_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            mimeType_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int AVERAGE_BITRATE_FIELD_NUMBER = 2;
    private int averageBitrate_ = 0;

    /**
     * <code>uint32 average_bitrate = 2;</code>
     *
     * @return The averageBitrate.
     */
    @java.lang.Override
    public int getAverageBitrate() {
        return averageBitrate_;
    }

    public static final int WIDTH_FIELD_NUMBER = 3;
    private int width_ = 0;

    /**
     * <code>uint32 width = 3;</code>
     *
     * @return The width.
     */
    @java.lang.Override
    public int getWidth() {
        return width_;
    }

    public static final int HEIGHT_FIELD_NUMBER = 4;
    private int height_ = 0;

    /**
     * <code>uint32 height = 4;</code>
     *
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
        return height_;
    }

    public static final int FRAMERATE_FIELD_NUMBER = 5;
    private double framerate_ = 0D;

    /**
     * <code>double framerate = 5;</code>
     *
     * @return The framerate.
     */
    @java.lang.Override
    public double getFramerate() {
        return framerate_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mimeType_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, mimeType_);
        }
        if (averageBitrate_ != 0) {
            output.writeUInt32(2, averageBitrate_);
        }
        if (width_ != 0) {
            output.writeUInt32(3, width_);
        }
        if (height_ != 0) {
            output.writeUInt32(4, height_);
        }
        if (java.lang.Double.doubleToRawLongBits(framerate_) != 0) {
            output.writeDouble(5, framerate_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mimeType_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, mimeType_);
        }
        if (averageBitrate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(2, averageBitrate_);
        }
        if (width_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, width_);
        }
        if (height_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, height_);
        }
        if (java.lang.Double.doubleToRawLongBits(framerate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(5, framerate_);
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
        if (!(obj instanceof InputVideoState other)) {
            return super.equals(obj);
        }

        if (!getMimeType().equals(other.getMimeType())) {
            return false;
        }
        if (getAverageBitrate() != other.getAverageBitrate()) {
            return false;
        }
        if (getWidth() != other.getWidth()) {
            return false;
        }
        if (getHeight() != other.getHeight()) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getFramerate()) != java.lang.Double
                .doubleToLongBits(other.getFramerate())) {
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
        hash = (37 * hash) + MIME_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getMimeType().hashCode();
        hash = (37 * hash) + AVERAGE_BITRATE_FIELD_NUMBER;
        hash = (53 * hash) + getAverageBitrate();
        hash = (37 * hash) + WIDTH_FIELD_NUMBER;
        hash = (53 * hash) + getWidth();
        hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
        hash = (53 * hash) + getHeight();
        hash = (37 * hash) + FRAMERATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getFramerate()));
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.InputVideoState prototype) {
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
     * Protobuf type {@code livekit.InputVideoState}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.InputVideoState)
            im.turms.plugin.livekit.core.proto.ingress.InputVideoStateOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputVideoState_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputVideoState_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.InputVideoState.class,
                            im.turms.plugin.livekit.core.proto.ingress.InputVideoState.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.ingress.InputVideoState.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            mimeType_ = "";
            averageBitrate_ = 0;
            width_ = 0;
            height_ = 0;
            framerate_ = 0D;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputVideoState_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.InputVideoState getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.InputVideoState.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.InputVideoState build() {
            im.turms.plugin.livekit.core.proto.ingress.InputVideoState result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.InputVideoState buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.InputVideoState result =
                    new im.turms.plugin.livekit.core.proto.ingress.InputVideoState(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.InputVideoState result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.mimeType_ = mimeType_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.averageBitrate_ = averageBitrate_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.width_ = width_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.height_ = height_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.framerate_ = framerate_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.InputVideoState) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.InputVideoState) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.ingress.InputVideoState other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.InputVideoState
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getMimeType()
                    .isEmpty()) {
                mimeType_ = other.mimeType_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getAverageBitrate() != 0) {
                setAverageBitrate(other.getAverageBitrate());
            }
            if (other.getWidth() != 0) {
                setWidth(other.getWidth());
            }
            if (other.getHeight() != 0) {
                setHeight(other.getHeight());
            }
            if (other.getFramerate() != 0D) {
                setFramerate(other.getFramerate());
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
                            mimeType_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            averageBitrate_ = input.readUInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            width_ = input.readUInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            height_ = input.readUInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 41 -> {
                            framerate_ = input.readDouble();
                            bitField0_ |= 0x00000010;
                        } // case 41
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

        private java.lang.Object mimeType_ = "";

        /**
         * <code>string mime_type = 1;</code>
         *
         * @return The mimeType.
         */
        public java.lang.String getMimeType() {
            java.lang.Object ref = mimeType_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                mimeType_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string mime_type = 1;</code>
         *
         * @return The bytes for mimeType.
         */
        public com.google.protobuf.ByteString getMimeTypeBytes() {
            java.lang.Object ref = mimeType_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                mimeType_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string mime_type = 1;</code>
         *
         * @param value The mimeType to set.
         * @return This builder for chaining.
         */
        public Builder setMimeType(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            mimeType_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string mime_type = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMimeType() {
            mimeType_ = getDefaultInstance().getMimeType();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string mime_type = 1;</code>
         *
         * @param value The bytes for mimeType to set.
         * @return This builder for chaining.
         */
        public Builder setMimeTypeBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            mimeType_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private int averageBitrate_;

        /**
         * <code>uint32 average_bitrate = 2;</code>
         *
         * @return The averageBitrate.
         */
        @java.lang.Override
        public int getAverageBitrate() {
            return averageBitrate_;
        }

        /**
         * <code>uint32 average_bitrate = 2;</code>
         *
         * @param value The averageBitrate to set.
         * @return This builder for chaining.
         */
        public Builder setAverageBitrate(int value) {

            averageBitrate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 average_bitrate = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAverageBitrate() {
            bitField0_ &= ~0x00000002;
            averageBitrate_ = 0;
            onChanged();
            return this;
        }

        private int width_;

        /**
         * <code>uint32 width = 3;</code>
         *
         * @return The width.
         */
        @java.lang.Override
        public int getWidth() {
            return width_;
        }

        /**
         * <code>uint32 width = 3;</code>
         *
         * @param value The width to set.
         * @return This builder for chaining.
         */
        public Builder setWidth(int value) {

            width_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 width = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWidth() {
            bitField0_ &= ~0x00000004;
            width_ = 0;
            onChanged();
            return this;
        }

        private int height_;

        /**
         * <code>uint32 height = 4;</code>
         *
         * @return The height.
         */
        @java.lang.Override
        public int getHeight() {
            return height_;
        }

        /**
         * <code>uint32 height = 4;</code>
         *
         * @param value The height to set.
         * @return This builder for chaining.
         */
        public Builder setHeight(int value) {

            height_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 height = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeight() {
            bitField0_ &= ~0x00000008;
            height_ = 0;
            onChanged();
            return this;
        }

        private double framerate_;

        /**
         * <code>double framerate = 5;</code>
         *
         * @return The framerate.
         */
        @java.lang.Override
        public double getFramerate() {
            return framerate_;
        }

        /**
         * <code>double framerate = 5;</code>
         *
         * @param value The framerate to set.
         * @return This builder for chaining.
         */
        public Builder setFramerate(double value) {

            framerate_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>double framerate = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFramerate() {
            bitField0_ &= ~0x00000010;
            framerate_ = 0D;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.InputVideoState)
    }

    // @@protoc_insertion_point(class_scope:livekit.InputVideoState)
    private static final im.turms.plugin.livekit.core.proto.ingress.InputVideoState DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.InputVideoState();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputVideoState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<InputVideoState> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public InputVideoState parsePartialFrom(
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

    public static com.google.protobuf.Parser<InputVideoState> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<InputVideoState> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.InputVideoState getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}