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
 * Protobuf type {@code livekit.IngressVideoEncodingOptions}
 */
public final class IngressVideoEncodingOptions extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:livekit.IngressVideoEncodingOptions)
        IngressVideoEncodingOptionsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressVideoEncodingOptions.class.getName());
    }

    // Use IngressVideoEncodingOptions.newBuilder() to construct.
    private IngressVideoEncodingOptions(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private IngressVideoEncodingOptions() {
        videoCodec_ = 0;
        layers_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoEncodingOptions_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoEncodingOptions_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.class,
                        im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.Builder.class);
    }

    public static final int VIDEO_CODEC_FIELD_NUMBER = 1;
    private int videoCodec_ = 0;

    /**
     * <pre>
     * desired codec to publish to room
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 1;</code>
     *
     * @return The enum numeric value on the wire for videoCodec.
     */
    @java.lang.Override
    public int getVideoCodecValue() {
        return videoCodec_;
    }

    /**
     * <pre>
     * desired codec to publish to room
     * </pre>
     *
     * <code>.livekit.VideoCodec video_codec = 1;</code>
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

    public static final int FRAME_RATE_FIELD_NUMBER = 2;
    private double frameRate_ = 0D;

    /**
     * <code>double frame_rate = 2;</code>
     *
     * @return The frameRate.
     */
    @java.lang.Override
    public double getFrameRate() {
        return frameRate_;
    }

    public static final int LAYERS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> layers_;

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList() {
        return layers_;
    }

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList() {
        return layers_;
    }

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    @java.lang.Override
    public int getLayersCount() {
        return layers_.size();
    }

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index) {
        return layers_.get(index);
    }

    /**
     * <pre>
     * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
     * </pre>
     *
     * <code>repeated .livekit.VideoLayer layers = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(
            int index) {
        return layers_.get(index);
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
        if (videoCodec_ != im.turms.plugin.livekit.core.proto.models.VideoCodec.DEFAULT_VC
                .getNumber()) {
            output.writeEnum(1, videoCodec_);
        }
        if (java.lang.Double.doubleToRawLongBits(frameRate_) != 0) {
            output.writeDouble(2, frameRate_);
        }
        for (im.turms.plugin.livekit.core.proto.models.VideoLayer videoLayer : layers_) {
            output.writeMessage(3, videoLayer);
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
        if (videoCodec_ != im.turms.plugin.livekit.core.proto.models.VideoCodec.DEFAULT_VC
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, videoCodec_);
        }
        if (java.lang.Double.doubleToRawLongBits(frameRate_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(2, frameRate_);
        }
        for (im.turms.plugin.livekit.core.proto.models.VideoLayer videoLayer : layers_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, videoLayer);
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
        if (!(obj instanceof IngressVideoEncodingOptions other)) {
            return super.equals(obj);
        }

        if (videoCodec_ != other.videoCodec_) {
            return false;
        }
        if (java.lang.Double.doubleToLongBits(getFrameRate()) != java.lang.Double
                .doubleToLongBits(other.getFrameRate())) {
            return false;
        }
        if (!getLayersList().equals(other.getLayersList())) {
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
        hash = (37 * hash) + VIDEO_CODEC_FIELD_NUMBER;
        hash = (53 * hash) + videoCodec_;
        hash = (37 * hash) + FRAME_RATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal
                .hashLong(java.lang.Double.doubleToLongBits(getFrameRate()));
        if (getLayersCount() > 0) {
            hash = (37 * hash) + LAYERS_FIELD_NUMBER;
            hash = (53 * hash) + getLayersList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions prototype) {
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
     * Protobuf type {@code livekit.IngressVideoEncodingOptions}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.IngressVideoEncodingOptions)
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptionsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoEncodingOptions_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoEncodingOptions_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.class,
                            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            videoCodec_ = 0;
            frameRate_ = 0D;
            if (layersBuilder_ == null) {
                layers_ = java.util.Collections.emptyList();
            } else {
                layers_ = null;
                layersBuilder_.clear();
            }
            bitField0_ &= ~0x00000004;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressVideoEncodingOptions_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions build() {
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions result =
                    new im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions result) {
            if (layersBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)) {
                    layers_ = java.util.Collections.unmodifiableList(layers_);
                    bitField0_ &= ~0x00000004;
                }
                result.layers_ = layers_;
            } else {
                result.layers_ = layersBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.videoCodec_ = videoCodec_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.frameRate_ = frameRate_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions
                    .getDefaultInstance()) {
                return this;
            }
            if (other.videoCodec_ != 0) {
                setVideoCodecValue(other.getVideoCodecValue());
            }
            if (other.getFrameRate() != 0D) {
                setFrameRate(other.getFrameRate());
            }
            if (layersBuilder_ == null) {
                if (!other.layers_.isEmpty()) {
                    if (layers_.isEmpty()) {
                        layers_ = other.layers_;
                        bitField0_ &= ~0x00000004;
                    } else {
                        ensureLayersIsMutable();
                        layers_.addAll(other.layers_);
                    }
                    onChanged();
                }
            } else {
                if (!other.layers_.isEmpty()) {
                    if (layersBuilder_.isEmpty()) {
                        layersBuilder_.dispose();
                        layersBuilder_ = null;
                        layers_ = other.layers_;
                        bitField0_ &= ~0x00000004;
                        layersBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getLayersFieldBuilder()
                                : null;
                    } else {
                        layersBuilder_.addAllMessages(other.layers_);
                    }
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
                            videoCodec_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 17 -> {
                            frameRate_ = input.readDouble();
                            bitField0_ |= 0x00000002;
                        } // case 17
                        case 26 -> {
                            im.turms.plugin.livekit.core.proto.models.VideoLayer m =
                                    input.readMessage(
                                            im.turms.plugin.livekit.core.proto.models.VideoLayer
                                                    .parser(),
                                            extensionRegistry);
                            if (layersBuilder_ == null) {
                                ensureLayersIsMutable();
                                layers_.add(m);
                            } else {
                                layersBuilder_.addMessage(m);
                            }
                        } // case 26
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

        private int videoCodec_ = 0;

        /**
         * <pre>
         * desired codec to publish to room
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 1;</code>
         *
         * @return The enum numeric value on the wire for videoCodec.
         */
        @java.lang.Override
        public int getVideoCodecValue() {
            return videoCodec_;
        }

        /**
         * <pre>
         * desired codec to publish to room
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 1;</code>
         *
         * @param value The enum numeric value on the wire for videoCodec to set.
         * @return This builder for chaining.
         */
        public Builder setVideoCodecValue(int value) {
            videoCodec_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * desired codec to publish to room
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 1;</code>
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
         * desired codec to publish to room
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 1;</code>
         *
         * @param value The videoCodec to set.
         * @return This builder for chaining.
         */
        public Builder setVideoCodec(im.turms.plugin.livekit.core.proto.models.VideoCodec value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            videoCodec_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * desired codec to publish to room
         * </pre>
         *
         * <code>.livekit.VideoCodec video_codec = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVideoCodec() {
            bitField0_ &= ~0x00000001;
            videoCodec_ = 0;
            onChanged();
            return this;
        }

        private double frameRate_;

        /**
         * <code>double frame_rate = 2;</code>
         *
         * @return The frameRate.
         */
        @java.lang.Override
        public double getFrameRate() {
            return frameRate_;
        }

        /**
         * <code>double frame_rate = 2;</code>
         *
         * @param value The frameRate to set.
         * @return This builder for chaining.
         */
        public Builder setFrameRate(double value) {

            frameRate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>double frame_rate = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFrameRate() {
            bitField0_ &= ~0x00000002;
            frameRate_ = 0D;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> layers_ =
                java.util.Collections.emptyList();

        private void ensureLayersIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                layers_ = new java.util.ArrayList<>(layers_);
                bitField0_ |= 0x00000004;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoLayer, im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder, im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> layersBuilder_;

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList() {
            if (layersBuilder_ == null) {
                return java.util.Collections.unmodifiableList(layers_);
            } else {
                return layersBuilder_.getMessageList();
            }
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public int getLayersCount() {
            if (layersBuilder_ == null) {
                return layers_.size();
            } else {
                return layersBuilder_.getCount();
            }
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index) {
            if (layersBuilder_ == null) {
                return layers_.get(index);
            } else {
                return layersBuilder_.getMessage(index);
            }
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder setLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer value) {
            if (layersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureLayersIsMutable();
                layers_.set(index, value);
                onChanged();
            } else {
                layersBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder setLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder builderForValue) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.set(index, builderForValue.build());
                onChanged();
            } else {
                layersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder addLayers(im.turms.plugin.livekit.core.proto.models.VideoLayer value) {
            if (layersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureLayersIsMutable();
                layers_.add(value);
                onChanged();
            } else {
                layersBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder addLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer value) {
            if (layersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureLayersIsMutable();
                layers_.add(index, value);
                onChanged();
            } else {
                layersBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder addLayers(
                im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder builderForValue) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.add(builderForValue.build());
                onChanged();
            } else {
                layersBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder addLayers(
                int index,
                im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder builderForValue) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.add(index, builderForValue.build());
                onChanged();
            } else {
                layersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder addAllLayers(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.VideoLayer> values) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, layers_);
                onChanged();
            } else {
                layersBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder clearLayers() {
            if (layersBuilder_ == null) {
                layers_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000004;
                onChanged();
            } else {
                layersBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public Builder removeLayers(int index) {
            if (layersBuilder_ == null) {
                ensureLayersIsMutable();
                layers_.remove(index);
                onChanged();
            } else {
                layersBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder getLayersBuilder(
                int index) {
            return getLayersFieldBuilder().getBuilder(index);
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder getLayersOrBuilder(
                int index) {
            if (layersBuilder_ == null) {
                return layers_.get(index);
            } else {
                return layersBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList() {
            if (layersBuilder_ != null) {
                return layersBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(layers_);
            }
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder addLayersBuilder() {
            return getLayersFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance());
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder addLayersBuilder(
                int index) {
            return getLayersFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance());
        }

        /**
         * <pre>
         * simulcast layers to publish, when empty, should usually be set to layers at 1/2 and 1/4 of the dimensions
         * </pre>
         *
         * <code>repeated .livekit.VideoLayer layers = 3;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder> getLayersBuilderList() {
            return getLayersFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoLayer, im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder, im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersFieldBuilder() {
            if (layersBuilder_ == null) {
                layersBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        layers_,
                        ((bitField0_ & 0x00000004) != 0),
                        getParentForChildren(),
                        isClean());
                layers_ = null;
            }
            return layersBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.IngressVideoEncodingOptions)
    }

    // @@protoc_insertion_point(class_scope:livekit.IngressVideoEncodingOptions)
    private static final im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<IngressVideoEncodingOptions> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public IngressVideoEncodingOptions parsePartialFrom(
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

    public static com.google.protobuf.Parser<IngressVideoEncodingOptions> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<IngressVideoEncodingOptions> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoEncodingOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}