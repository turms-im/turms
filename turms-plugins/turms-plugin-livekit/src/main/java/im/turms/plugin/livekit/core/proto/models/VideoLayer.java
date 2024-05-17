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
 * <pre>
 * provide information about available spatial layers
 * </pre>
 *
 * Protobuf type {@code livekit.VideoLayer}
 */
public final class VideoLayer extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.VideoLayer)
        VideoLayerOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                VideoLayer.class.getName());
    }

    // Use VideoLayer.newBuilder() to construct.
    private VideoLayer(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private VideoLayer() {
        quality_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoLayer_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoLayer_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.VideoLayer.class,
                        im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder.class);
    }

    public static final int QUALITY_FIELD_NUMBER = 1;
    private int quality_ = 0;

    /**
     * <pre>
     * for tracks with a single layer, this should be HIGH
     * </pre>
     *
     * <code>.livekit.VideoQuality quality = 1;</code>
     *
     * @return The enum numeric value on the wire for quality.
     */
    @java.lang.Override
    public int getQualityValue() {
        return quality_;
    }

    /**
     * <pre>
     * for tracks with a single layer, this should be HIGH
     * </pre>
     *
     * <code>.livekit.VideoQuality quality = 1;</code>
     *
     * @return The quality.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoQuality getQuality() {
        im.turms.plugin.livekit.core.proto.models.VideoQuality result =
                im.turms.plugin.livekit.core.proto.models.VideoQuality.forNumber(quality_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.VideoQuality.UNRECOGNIZED
                : result;
    }

    public static final int WIDTH_FIELD_NUMBER = 2;
    private int width_ = 0;

    /**
     * <code>uint32 width = 2;</code>
     *
     * @return The width.
     */
    @java.lang.Override
    public int getWidth() {
        return width_;
    }

    public static final int HEIGHT_FIELD_NUMBER = 3;
    private int height_ = 0;

    /**
     * <code>uint32 height = 3;</code>
     *
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
        return height_;
    }

    public static final int BITRATE_FIELD_NUMBER = 4;
    private int bitrate_ = 0;

    /**
     * <pre>
     * target bitrate in bit per second (bps), server will measure actual
     * </pre>
     *
     * <code>uint32 bitrate = 4;</code>
     *
     * @return The bitrate.
     */
    @java.lang.Override
    public int getBitrate() {
        return bitrate_;
    }

    public static final int SSRC_FIELD_NUMBER = 5;
    private int ssrc_ = 0;

    /**
     * <code>uint32 ssrc = 5;</code>
     *
     * @return The ssrc.
     */
    @java.lang.Override
    public int getSsrc() {
        return ssrc_;
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
        if (quality_ != im.turms.plugin.livekit.core.proto.models.VideoQuality.LOW.getNumber()) {
            output.writeEnum(1, quality_);
        }
        if (width_ != 0) {
            output.writeUInt32(2, width_);
        }
        if (height_ != 0) {
            output.writeUInt32(3, height_);
        }
        if (bitrate_ != 0) {
            output.writeUInt32(4, bitrate_);
        }
        if (ssrc_ != 0) {
            output.writeUInt32(5, ssrc_);
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
        if (quality_ != im.turms.plugin.livekit.core.proto.models.VideoQuality.LOW.getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, quality_);
        }
        if (width_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(2, width_);
        }
        if (height_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, height_);
        }
        if (bitrate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, bitrate_);
        }
        if (ssrc_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(5, ssrc_);
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
        if (!(obj instanceof VideoLayer other)) {
            return super.equals(obj);
        }

        if (quality_ != other.quality_) {
            return false;
        }
        if (getWidth() != other.getWidth()) {
            return false;
        }
        if (getHeight() != other.getHeight()) {
            return false;
        }
        if (getBitrate() != other.getBitrate()) {
            return false;
        }
        if (getSsrc() != other.getSsrc()) {
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
        hash = (37 * hash) + QUALITY_FIELD_NUMBER;
        hash = (53 * hash) + quality_;
        hash = (37 * hash) + WIDTH_FIELD_NUMBER;
        hash = (53 * hash) + getWidth();
        hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
        hash = (53 * hash) + getHeight();
        hash = (37 * hash) + BITRATE_FIELD_NUMBER;
        hash = (53 * hash) + getBitrate();
        hash = (37 * hash) + SSRC_FIELD_NUMBER;
        hash = (53 * hash) + getSsrc();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.VideoLayer prototype) {
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
     * provide information about available spatial layers
     * </pre>
     *
     * Protobuf type {@code livekit.VideoLayer}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.VideoLayer)
            im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoLayer_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoLayer_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.VideoLayer.class,
                            im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.VideoLayer.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            quality_ = 0;
            width_ = 0;
            height_ = 0;
            bitrate_ = 0;
            ssrc_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoLayer_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoLayer getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoLayer build() {
            im.turms.plugin.livekit.core.proto.models.VideoLayer result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoLayer buildPartial() {
            im.turms.plugin.livekit.core.proto.models.VideoLayer result =
                    new im.turms.plugin.livekit.core.proto.models.VideoLayer(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.VideoLayer result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.quality_ = quality_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.width_ = width_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.height_ = height_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.bitrate_ = bitrate_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.ssrc_ = ssrc_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.VideoLayer) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.VideoLayer) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.VideoLayer other) {
            if (other == im.turms.plugin.livekit.core.proto.models.VideoLayer
                    .getDefaultInstance()) {
                return this;
            }
            if (other.quality_ != 0) {
                setQualityValue(other.getQualityValue());
            }
            if (other.getWidth() != 0) {
                setWidth(other.getWidth());
            }
            if (other.getHeight() != 0) {
                setHeight(other.getHeight());
            }
            if (other.getBitrate() != 0) {
                setBitrate(other.getBitrate());
            }
            if (other.getSsrc() != 0) {
                setSsrc(other.getSsrc());
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
                            quality_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            width_ = input.readUInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            height_ = input.readUInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            bitrate_ = input.readUInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            ssrc_ = input.readUInt32();
                            bitField0_ |= 0x00000010;
                        } // case 40
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

        private int quality_ = 0;

        /**
         * <pre>
         * for tracks with a single layer, this should be HIGH
         * </pre>
         *
         * <code>.livekit.VideoQuality quality = 1;</code>
         *
         * @return The enum numeric value on the wire for quality.
         */
        @java.lang.Override
        public int getQualityValue() {
            return quality_;
        }

        /**
         * <pre>
         * for tracks with a single layer, this should be HIGH
         * </pre>
         *
         * <code>.livekit.VideoQuality quality = 1;</code>
         *
         * @param value The enum numeric value on the wire for quality to set.
         * @return This builder for chaining.
         */
        public Builder setQualityValue(int value) {
            quality_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * for tracks with a single layer, this should be HIGH
         * </pre>
         *
         * <code>.livekit.VideoQuality quality = 1;</code>
         *
         * @return The quality.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoQuality getQuality() {
            im.turms.plugin.livekit.core.proto.models.VideoQuality result =
                    im.turms.plugin.livekit.core.proto.models.VideoQuality.forNumber(quality_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.VideoQuality.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * for tracks with a single layer, this should be HIGH
         * </pre>
         *
         * <code>.livekit.VideoQuality quality = 1;</code>
         *
         * @param value The quality to set.
         * @return This builder for chaining.
         */
        public Builder setQuality(im.turms.plugin.livekit.core.proto.models.VideoQuality value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            quality_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * for tracks with a single layer, this should be HIGH
         * </pre>
         *
         * <code>.livekit.VideoQuality quality = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuality() {
            bitField0_ &= ~0x00000001;
            quality_ = 0;
            onChanged();
            return this;
        }

        private int width_;

        /**
         * <code>uint32 width = 2;</code>
         *
         * @return The width.
         */
        @java.lang.Override
        public int getWidth() {
            return width_;
        }

        /**
         * <code>uint32 width = 2;</code>
         *
         * @param value The width to set.
         * @return This builder for chaining.
         */
        public Builder setWidth(int value) {

            width_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 width = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWidth() {
            bitField0_ &= ~0x00000002;
            width_ = 0;
            onChanged();
            return this;
        }

        private int height_;

        /**
         * <code>uint32 height = 3;</code>
         *
         * @return The height.
         */
        @java.lang.Override
        public int getHeight() {
            return height_;
        }

        /**
         * <code>uint32 height = 3;</code>
         *
         * @param value The height to set.
         * @return This builder for chaining.
         */
        public Builder setHeight(int value) {

            height_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 height = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHeight() {
            bitField0_ &= ~0x00000004;
            height_ = 0;
            onChanged();
            return this;
        }

        private int bitrate_;

        /**
         * <pre>
         * target bitrate in bit per second (bps), server will measure actual
         * </pre>
         *
         * <code>uint32 bitrate = 4;</code>
         *
         * @return The bitrate.
         */
        @java.lang.Override
        public int getBitrate() {
            return bitrate_;
        }

        /**
         * <pre>
         * target bitrate in bit per second (bps), server will measure actual
         * </pre>
         *
         * <code>uint32 bitrate = 4;</code>
         *
         * @param value The bitrate to set.
         * @return This builder for chaining.
         */
        public Builder setBitrate(int value) {

            bitrate_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * target bitrate in bit per second (bps), server will measure actual
         * </pre>
         *
         * <code>uint32 bitrate = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBitrate() {
            bitField0_ &= ~0x00000008;
            bitrate_ = 0;
            onChanged();
            return this;
        }

        private int ssrc_;

        /**
         * <code>uint32 ssrc = 5;</code>
         *
         * @return The ssrc.
         */
        @java.lang.Override
        public int getSsrc() {
            return ssrc_;
        }

        /**
         * <code>uint32 ssrc = 5;</code>
         *
         * @param value The ssrc to set.
         * @return This builder for chaining.
         */
        public Builder setSsrc(int value) {

            ssrc_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 ssrc = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSsrc() {
            bitField0_ &= ~0x00000010;
            ssrc_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.VideoLayer)
    }

    // @@protoc_insertion_point(class_scope:livekit.VideoLayer)
    private static final im.turms.plugin.livekit.core.proto.models.VideoLayer DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.VideoLayer();
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoLayer getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<VideoLayer> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public VideoLayer parsePartialFrom(
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

    public static com.google.protobuf.Parser<VideoLayer> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<VideoLayer> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoLayer getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}