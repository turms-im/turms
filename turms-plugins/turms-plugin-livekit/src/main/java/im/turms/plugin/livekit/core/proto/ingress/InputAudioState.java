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
 * Protobuf type {@code livekit.InputAudioState}
 */
public final class InputAudioState extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.InputAudioState)
        InputAudioStateOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                InputAudioState.class.getName());
    }

    // Use InputAudioState.newBuilder() to construct.
    private InputAudioState(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private InputAudioState() {
        mimeType_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputAudioState_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputAudioState_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.InputAudioState.class,
                        im.turms.plugin.livekit.core.proto.ingress.InputAudioState.Builder.class);
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

    public static final int CHANNELS_FIELD_NUMBER = 3;
    private int channels_ = 0;

    /**
     * <code>uint32 channels = 3;</code>
     *
     * @return The channels.
     */
    @java.lang.Override
    public int getChannels() {
        return channels_;
    }

    public static final int SAMPLE_RATE_FIELD_NUMBER = 4;
    private int sampleRate_ = 0;

    /**
     * <code>uint32 sample_rate = 4;</code>
     *
     * @return The sampleRate.
     */
    @java.lang.Override
    public int getSampleRate() {
        return sampleRate_;
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
        if (channels_ != 0) {
            output.writeUInt32(3, channels_);
        }
        if (sampleRate_ != 0) {
            output.writeUInt32(4, sampleRate_);
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
        if (channels_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, channels_);
        }
        if (sampleRate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, sampleRate_);
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
        if (!(obj instanceof InputAudioState other)) {
            return super.equals(obj);
        }

        if (!getMimeType().equals(other.getMimeType())) {
            return false;
        }
        if (getAverageBitrate() != other.getAverageBitrate()) {
            return false;
        }
        if (getChannels() != other.getChannels()) {
            return false;
        }
        if (getSampleRate() != other.getSampleRate()) {
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
        hash = (37 * hash) + CHANNELS_FIELD_NUMBER;
        hash = (53 * hash) + getChannels();
        hash = (37 * hash) + SAMPLE_RATE_FIELD_NUMBER;
        hash = (53 * hash) + getSampleRate();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.InputAudioState prototype) {
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
     * Protobuf type {@code livekit.InputAudioState}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.InputAudioState)
            im.turms.plugin.livekit.core.proto.ingress.InputAudioStateOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputAudioState_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputAudioState_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.InputAudioState.class,
                            im.turms.plugin.livekit.core.proto.ingress.InputAudioState.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.ingress.InputAudioState.newBuilder()
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
            channels_ = 0;
            sampleRate_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_InputAudioState_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.InputAudioState getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.InputAudioState.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.InputAudioState build() {
            im.turms.plugin.livekit.core.proto.ingress.InputAudioState result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.InputAudioState buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.InputAudioState result =
                    new im.turms.plugin.livekit.core.proto.ingress.InputAudioState(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.InputAudioState result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.mimeType_ = mimeType_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.averageBitrate_ = averageBitrate_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.channels_ = channels_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.sampleRate_ = sampleRate_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.InputAudioState) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.InputAudioState) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.ingress.InputAudioState other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.InputAudioState
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
            if (other.getChannels() != 0) {
                setChannels(other.getChannels());
            }
            if (other.getSampleRate() != 0) {
                setSampleRate(other.getSampleRate());
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
                            channels_ = input.readUInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            sampleRate_ = input.readUInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
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

        private int channels_;

        /**
         * <code>uint32 channels = 3;</code>
         *
         * @return The channels.
         */
        @java.lang.Override
        public int getChannels() {
            return channels_;
        }

        /**
         * <code>uint32 channels = 3;</code>
         *
         * @param value The channels to set.
         * @return This builder for chaining.
         */
        public Builder setChannels(int value) {

            channels_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 channels = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearChannels() {
            bitField0_ &= ~0x00000004;
            channels_ = 0;
            onChanged();
            return this;
        }

        private int sampleRate_;

        /**
         * <code>uint32 sample_rate = 4;</code>
         *
         * @return The sampleRate.
         */
        @java.lang.Override
        public int getSampleRate() {
            return sampleRate_;
        }

        /**
         * <code>uint32 sample_rate = 4;</code>
         *
         * @param value The sampleRate to set.
         * @return This builder for chaining.
         */
        public Builder setSampleRate(int value) {

            sampleRate_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 sample_rate = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSampleRate() {
            bitField0_ &= ~0x00000008;
            sampleRate_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.InputAudioState)
    }

    // @@protoc_insertion_point(class_scope:livekit.InputAudioState)
    private static final im.turms.plugin.livekit.core.proto.ingress.InputAudioState DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.InputAudioState();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.InputAudioState getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<InputAudioState> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public InputAudioState parsePartialFrom(
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

    public static com.google.protobuf.Parser<InputAudioState> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<InputAudioState> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.InputAudioState getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}