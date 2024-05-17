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
 * Protobuf type {@code livekit.SimulcastCodecInfo}
 */
public final class SimulcastCodecInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.SimulcastCodecInfo)
        SimulcastCodecInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                SimulcastCodecInfo.class.getName());
    }

    // Use SimulcastCodecInfo.newBuilder() to construct.
    private SimulcastCodecInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private SimulcastCodecInfo() {
        mimeType_ = "";
        mid_ = "";
        cid_ = "";
        layers_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SimulcastCodecInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SimulcastCodecInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.class,
                        im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder.class);
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

    public static final int MID_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object mid_ = "";

    /**
     * <code>string mid = 2;</code>
     *
     * @return The mid.
     */
    @java.lang.Override
    public java.lang.String getMid() {
        java.lang.Object ref = mid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            mid_ = s;
            return s;
        }
    }

    /**
     * <code>string mid = 2;</code>
     *
     * @return The bytes for mid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMidBytes() {
        java.lang.Object ref = mid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            mid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int CID_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object cid_ = "";

    /**
     * <code>string cid = 3;</code>
     *
     * @return The cid.
     */
    @java.lang.Override
    public java.lang.String getCid() {
        java.lang.Object ref = cid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            cid_ = s;
            return s;
        }
    }

    /**
     * <code>string cid = 3;</code>
     *
     * @return The bytes for cid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getCidBytes() {
        java.lang.Object ref = cid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            cid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int LAYERS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> layers_;

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList() {
        return layers_;
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList() {
        return layers_;
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    @java.lang.Override
    public int getLayersCount() {
        return layers_.size();
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index) {
        return layers_.get(index);
    }

    /**
     * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mimeType_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, mimeType_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, mid_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(cid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, cid_);
        }
        for (VideoLayer videoLayer : layers_) {
            output.writeMessage(4, videoLayer);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, mid_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(cid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, cid_);
        }
        for (VideoLayer videoLayer : layers_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, videoLayer);
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
        if (!(obj instanceof SimulcastCodecInfo other)) {
            return super.equals(obj);
        }

        if (!getMimeType().equals(other.getMimeType())) {
            return false;
        }
        if (!getMid().equals(other.getMid())) {
            return false;
        }
        if (!getCid().equals(other.getCid())) {
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
        hash = (37 * hash) + MIME_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getMimeType().hashCode();
        hash = (37 * hash) + MID_FIELD_NUMBER;
        hash = (53 * hash) + getMid().hashCode();
        hash = (37 * hash) + CID_FIELD_NUMBER;
        hash = (53 * hash) + getCid().hashCode();
        if (getLayersCount() > 0) {
            hash = (37 * hash) + LAYERS_FIELD_NUMBER;
            hash = (53 * hash) + getLayersList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo prototype) {
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
     * Protobuf type {@code livekit.SimulcastCodecInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.SimulcastCodecInfo)
            im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SimulcastCodecInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SimulcastCodecInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.class,
                            im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo.newBuilder()
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
            mid_ = "";
            cid_ = "";
            if (layersBuilder_ == null) {
                layers_ = java.util.Collections.emptyList();
            } else {
                layers_ = null;
                layersBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SimulcastCodecInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo build() {
            im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo result =
                    new im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo result) {
            if (layersBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    layers_ = java.util.Collections.unmodifiableList(layers_);
                    bitField0_ &= ~0x00000008;
                }
                result.layers_ = layers_;
            } else {
                result.layers_ = layersBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.mimeType_ = mimeType_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.mid_ = mid_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.cid_ = cid_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getMimeType()
                    .isEmpty()) {
                mimeType_ = other.mimeType_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getMid()
                    .isEmpty()) {
                mid_ = other.mid_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getCid()
                    .isEmpty()) {
                cid_ = other.cid_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (layersBuilder_ == null) {
                if (!other.layers_.isEmpty()) {
                    if (layers_.isEmpty()) {
                        layers_ = other.layers_;
                        bitField0_ &= ~0x00000008;
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
                        bitField0_ &= ~0x00000008;
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
                        case 10 -> {
                            mimeType_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            mid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            cid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            VideoLayer m =
                                    input.readMessage(VideoLayer.parser(), extensionRegistry);
                            if (layersBuilder_ == null) {
                                ensureLayersIsMutable();
                                layers_.add(m);
                            } else {
                                layersBuilder_.addMessage(m);
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

        private java.lang.Object mid_ = "";

        /**
         * <code>string mid = 2;</code>
         *
         * @return The mid.
         */
        public java.lang.String getMid() {
            java.lang.Object ref = mid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                mid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string mid = 2;</code>
         *
         * @return The bytes for mid.
         */
        public com.google.protobuf.ByteString getMidBytes() {
            java.lang.Object ref = mid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                mid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string mid = 2;</code>
         *
         * @param value The mid to set.
         * @return This builder for chaining.
         */
        public Builder setMid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            mid_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string mid = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMid() {
            mid_ = getDefaultInstance().getMid();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string mid = 2;</code>
         *
         * @param value The bytes for mid to set.
         * @return This builder for chaining.
         */
        public Builder setMidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            mid_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object cid_ = "";

        /**
         * <code>string cid = 3;</code>
         *
         * @return The cid.
         */
        public java.lang.String getCid() {
            java.lang.Object ref = cid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                cid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string cid = 3;</code>
         *
         * @return The bytes for cid.
         */
        public com.google.protobuf.ByteString getCidBytes() {
            java.lang.Object ref = cid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                cid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string cid = 3;</code>
         *
         * @param value The cid to set.
         * @return This builder for chaining.
         */
        public Builder setCid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            cid_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string cid = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCid() {
            cid_ = getDefaultInstance().getCid();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string cid = 3;</code>
         *
         * @param value The bytes for cid to set.
         * @return This builder for chaining.
         */
        public Builder setCidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            cid_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> layers_ =
                java.util.Collections.emptyList();

        private void ensureLayersIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                layers_ = new java.util.ArrayList<>(layers_);
                bitField0_ |= 0x00000008;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoLayer, im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder, im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> layersBuilder_;

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer> getLayersList() {
            if (layersBuilder_ == null) {
                return java.util.Collections.unmodifiableList(layers_);
            } else {
                return layersBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public int getLayersCount() {
            if (layersBuilder_ == null) {
                return layers_.size();
            } else {
                return layersBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer getLayers(int index) {
            if (layersBuilder_ == null) {
                return layers_.get(index);
            } else {
                return layersBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public Builder clearLayers() {
            if (layersBuilder_ == null) {
                layers_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000008;
                onChanged();
            } else {
                layersBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder getLayersBuilder(
                int index) {
            return getLayersFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
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
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersOrBuilderList() {
            if (layersBuilder_ != null) {
                return layersBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(layers_);
            }
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder addLayersBuilder() {
            return getLayersFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder addLayersBuilder(
                int index) {
            return getLayersFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.VideoLayer.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.VideoLayer layers = 4;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder> getLayersBuilderList() {
            return getLayersFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoLayer, im.turms.plugin.livekit.core.proto.models.VideoLayer.Builder, im.turms.plugin.livekit.core.proto.models.VideoLayerOrBuilder> getLayersFieldBuilder() {
            if (layersBuilder_ == null) {
                layersBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        layers_,
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                layers_ = null;
            }
            return layersBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.SimulcastCodecInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.SimulcastCodecInfo)
    private static final im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo();
    }

    public static im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SimulcastCodecInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public SimulcastCodecInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<SimulcastCodecInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SimulcastCodecInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SimulcastCodecInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}