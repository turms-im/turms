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
 * Protobuf type {@code livekit.DisabledCodecs}
 */
public final class DisabledCodecs extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.DisabledCodecs)
        DisabledCodecsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                DisabledCodecs.class.getName());
    }

    // Use DisabledCodecs.newBuilder() to construct.
    private DisabledCodecs(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private DisabledCodecs() {
        codecs_ = java.util.Collections.emptyList();
        publish_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DisabledCodecs_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DisabledCodecs_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.DisabledCodecs.class,
                        im.turms.plugin.livekit.core.proto.models.DisabledCodecs.Builder.class);
    }

    public static final int CODECS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> codecs_;

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getCodecsList() {
        return codecs_;
    }

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getCodecsOrBuilderList() {
        return codecs_;
    }

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    @java.lang.Override
    public int getCodecsCount() {
        return codecs_.size();
    }

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Codec getCodecs(int index) {
        return codecs_.get(index);
    }

    /**
     * <pre>
     * disabled for both publish and subscribe
     * </pre>
     *
     * <code>repeated .livekit.Codec codecs = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getCodecsOrBuilder(int index) {
        return codecs_.get(index);
    }

    public static final int PUBLISH_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> publish_;

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getPublishList() {
        return publish_;
    }

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getPublishOrBuilderList() {
        return publish_;
    }

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    @java.lang.Override
    public int getPublishCount() {
        return publish_.size();
    }

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Codec getPublish(int index) {
        return publish_.get(index);
    }

    /**
     * <pre>
     * only disable for publish
     * </pre>
     *
     * <code>repeated .livekit.Codec publish = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getPublishOrBuilder(int index) {
        return publish_.get(index);
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
        for (Codec value : codecs_) {
            output.writeMessage(1, value);
        }
        for (Codec codec : publish_) {
            output.writeMessage(2, codec);
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
        for (Codec value : codecs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, value);
        }
        for (Codec codec : publish_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, codec);
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
        if (!(obj instanceof DisabledCodecs other)) {
            return super.equals(obj);
        }

        if (!getCodecsList().equals(other.getCodecsList())) {
            return false;
        }
        if (!getPublishList().equals(other.getPublishList())) {
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
        if (getCodecsCount() > 0) {
            hash = (37 * hash) + CODECS_FIELD_NUMBER;
            hash = (53 * hash) + getCodecsList().hashCode();
        }
        if (getPublishCount() > 0) {
            hash = (37 * hash) + PUBLISH_FIELD_NUMBER;
            hash = (53 * hash) + getPublishList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.DisabledCodecs prototype) {
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
     * Protobuf type {@code livekit.DisabledCodecs}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.DisabledCodecs)
            im.turms.plugin.livekit.core.proto.models.DisabledCodecsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DisabledCodecs_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DisabledCodecs_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.DisabledCodecs.class,
                            im.turms.plugin.livekit.core.proto.models.DisabledCodecs.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.DisabledCodecs.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (codecsBuilder_ == null) {
                codecs_ = java.util.Collections.emptyList();
            } else {
                codecs_ = null;
                codecsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            if (publishBuilder_ == null) {
                publish_ = java.util.Collections.emptyList();
            } else {
                publish_ = null;
                publishBuilder_.clear();
            }
            bitField0_ &= ~0x00000002;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_DisabledCodecs_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DisabledCodecs getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.DisabledCodecs.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DisabledCodecs build() {
            im.turms.plugin.livekit.core.proto.models.DisabledCodecs result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.DisabledCodecs buildPartial() {
            im.turms.plugin.livekit.core.proto.models.DisabledCodecs result =
                    new im.turms.plugin.livekit.core.proto.models.DisabledCodecs(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.DisabledCodecs result) {
            if (codecsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    codecs_ = java.util.Collections.unmodifiableList(codecs_);
                    bitField0_ &= ~0x00000001;
                }
                result.codecs_ = codecs_;
            } else {
                result.codecs_ = codecsBuilder_.build();
            }
            if (publishBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)) {
                    publish_ = java.util.Collections.unmodifiableList(publish_);
                    bitField0_ &= ~0x00000002;
                }
                result.publish_ = publish_;
            } else {
                result.publish_ = publishBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.DisabledCodecs result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.DisabledCodecs) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.DisabledCodecs) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.DisabledCodecs other) {
            if (other == im.turms.plugin.livekit.core.proto.models.DisabledCodecs
                    .getDefaultInstance()) {
                return this;
            }
            if (codecsBuilder_ == null) {
                if (!other.codecs_.isEmpty()) {
                    if (codecs_.isEmpty()) {
                        codecs_ = other.codecs_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureCodecsIsMutable();
                        codecs_.addAll(other.codecs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.codecs_.isEmpty()) {
                    if (codecsBuilder_.isEmpty()) {
                        codecsBuilder_.dispose();
                        codecsBuilder_ = null;
                        codecs_ = other.codecs_;
                        bitField0_ &= ~0x00000001;
                        codecsBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getCodecsFieldBuilder()
                                : null;
                    } else {
                        codecsBuilder_.addAllMessages(other.codecs_);
                    }
                }
            }
            if (publishBuilder_ == null) {
                if (!other.publish_.isEmpty()) {
                    if (publish_.isEmpty()) {
                        publish_ = other.publish_;
                        bitField0_ &= ~0x00000002;
                    } else {
                        ensurePublishIsMutable();
                        publish_.addAll(other.publish_);
                    }
                    onChanged();
                }
            } else {
                if (!other.publish_.isEmpty()) {
                    if (publishBuilder_.isEmpty()) {
                        publishBuilder_.dispose();
                        publishBuilder_ = null;
                        publish_ = other.publish_;
                        bitField0_ &= ~0x00000002;
                        publishBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getPublishFieldBuilder()
                                        : null;
                    } else {
                        publishBuilder_.addAllMessages(other.publish_);
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
                            Codec m = input.readMessage(Codec.parser(), extensionRegistry);
                            if (codecsBuilder_ == null) {
                                ensureCodecsIsMutable();
                                codecs_.add(m);
                            } else {
                                codecsBuilder_.addMessage(m);
                            }
                        } // case 10
                        case 18 -> {
                            Codec m = input.readMessage(Codec.parser(), extensionRegistry);
                            if (publishBuilder_ == null) {
                                ensurePublishIsMutable();
                                publish_.add(m);
                            } else {
                                publishBuilder_.addMessage(m);
                            }
                        } // case 18
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

        private java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> codecs_ =
                java.util.Collections.emptyList();

        private void ensureCodecsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                codecs_ = new java.util.ArrayList<>(codecs_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Codec, im.turms.plugin.livekit.core.proto.models.Codec.Builder, im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> codecsBuilder_;

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getCodecsList() {
            if (codecsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(codecs_);
            } else {
                return codecsBuilder_.getMessageList();
            }
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public int getCodecsCount() {
            if (codecsBuilder_ == null) {
                return codecs_.size();
            } else {
                return codecsBuilder_.getCount();
            }
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec getCodecs(int index) {
            if (codecsBuilder_ == null) {
                return codecs_.get(index);
            } else {
                return codecsBuilder_.getMessage(index);
            }
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder setCodecs(int index, im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (codecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCodecsIsMutable();
                codecs_.set(index, value);
                onChanged();
            } else {
                codecsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder setCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.set(index, builderForValue.build());
                onChanged();
            } else {
                codecsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder addCodecs(im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (codecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCodecsIsMutable();
                codecs_.add(value);
                onChanged();
            } else {
                codecsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder addCodecs(int index, im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (codecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCodecsIsMutable();
                codecs_.add(index, value);
                onChanged();
            } else {
                codecsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder addCodecs(
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.add(builderForValue.build());
                onChanged();
            } else {
                codecsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder addCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.add(index, builderForValue.build());
                onChanged();
            } else {
                codecsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder addAllCodecs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.Codec> values) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, codecs_);
                onChanged();
            } else {
                codecsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder clearCodecs() {
            if (codecsBuilder_ == null) {
                codecs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                codecsBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public Builder removeCodecs(int index) {
            if (codecsBuilder_ == null) {
                ensureCodecsIsMutable();
                codecs_.remove(index);
                onChanged();
            } else {
                codecsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder getCodecsBuilder(int index) {
            return getCodecsFieldBuilder().getBuilder(index);
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getCodecsOrBuilder(
                int index) {
            if (codecsBuilder_ == null) {
                return codecs_.get(index);
            } else {
                return codecsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getCodecsOrBuilderList() {
            if (codecsBuilder_ != null) {
                return codecsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(codecs_);
            }
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder addCodecsBuilder() {
            return getCodecsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance());
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder addCodecsBuilder(int index) {
            return getCodecsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance());
        }

        /**
         * <pre>
         * disabled for both publish and subscribe
         * </pre>
         *
         * <code>repeated .livekit.Codec codecs = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec.Builder> getCodecsBuilderList() {
            return getCodecsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Codec, im.turms.plugin.livekit.core.proto.models.Codec.Builder, im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getCodecsFieldBuilder() {
            if (codecsBuilder_ == null) {
                codecsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        codecs_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                codecs_ = null;
            }
            return codecsBuilder_;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> publish_ =
                java.util.Collections.emptyList();

        private void ensurePublishIsMutable() {
            if ((bitField0_ & 0x00000002) == 0) {
                publish_ = new java.util.ArrayList<>(publish_);
                bitField0_ |= 0x00000002;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Codec, im.turms.plugin.livekit.core.proto.models.Codec.Builder, im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> publishBuilder_;

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getPublishList() {
            if (publishBuilder_ == null) {
                return java.util.Collections.unmodifiableList(publish_);
            } else {
                return publishBuilder_.getMessageList();
            }
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public int getPublishCount() {
            if (publishBuilder_ == null) {
                return publish_.size();
            } else {
                return publishBuilder_.getCount();
            }
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec getPublish(int index) {
            if (publishBuilder_ == null) {
                return publish_.get(index);
            } else {
                return publishBuilder_.getMessage(index);
            }
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder setPublish(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (publishBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensurePublishIsMutable();
                publish_.set(index, value);
                onChanged();
            } else {
                publishBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder setPublish(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (publishBuilder_ == null) {
                ensurePublishIsMutable();
                publish_.set(index, builderForValue.build());
                onChanged();
            } else {
                publishBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder addPublish(im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (publishBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensurePublishIsMutable();
                publish_.add(value);
                onChanged();
            } else {
                publishBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder addPublish(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (publishBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensurePublishIsMutable();
                publish_.add(index, value);
                onChanged();
            } else {
                publishBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder addPublish(
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (publishBuilder_ == null) {
                ensurePublishIsMutable();
                publish_.add(builderForValue.build());
                onChanged();
            } else {
                publishBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder addPublish(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (publishBuilder_ == null) {
                ensurePublishIsMutable();
                publish_.add(index, builderForValue.build());
                onChanged();
            } else {
                publishBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder addAllPublish(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.Codec> values) {
            if (publishBuilder_ == null) {
                ensurePublishIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, publish_);
                onChanged();
            } else {
                publishBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder clearPublish() {
            if (publishBuilder_ == null) {
                publish_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000002;
                onChanged();
            } else {
                publishBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public Builder removePublish(int index) {
            if (publishBuilder_ == null) {
                ensurePublishIsMutable();
                publish_.remove(index);
                onChanged();
            } else {
                publishBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder getPublishBuilder(
                int index) {
            return getPublishFieldBuilder().getBuilder(index);
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getPublishOrBuilder(
                int index) {
            if (publishBuilder_ == null) {
                return publish_.get(index);
            } else {
                return publishBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getPublishOrBuilderList() {
            if (publishBuilder_ != null) {
                return publishBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(publish_);
            }
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder addPublishBuilder() {
            return getPublishFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance());
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder addPublishBuilder(
                int index) {
            return getPublishFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance());
        }

        /**
         * <pre>
         * only disable for publish
         * </pre>
         *
         * <code>repeated .livekit.Codec publish = 2;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec.Builder> getPublishBuilderList() {
            return getPublishFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Codec, im.turms.plugin.livekit.core.proto.models.Codec.Builder, im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getPublishFieldBuilder() {
            if (publishBuilder_ == null) {
                publishBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        publish_,
                        ((bitField0_ & 0x00000002) != 0),
                        getParentForChildren(),
                        isClean());
                publish_ = null;
            }
            return publishBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.DisabledCodecs)
    }

    // @@protoc_insertion_point(class_scope:livekit.DisabledCodecs)
    private static final im.turms.plugin.livekit.core.proto.models.DisabledCodecs DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.DisabledCodecs();
    }

    public static im.turms.plugin.livekit.core.proto.models.DisabledCodecs getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DisabledCodecs> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public DisabledCodecs parsePartialFrom(
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

    public static com.google.protobuf.Parser<DisabledCodecs> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DisabledCodecs> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.DisabledCodecs getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}