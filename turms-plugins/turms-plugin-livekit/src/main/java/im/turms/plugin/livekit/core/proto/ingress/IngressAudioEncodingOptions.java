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
 * Protobuf type {@code livekit.IngressAudioEncodingOptions}
 */
public final class IngressAudioEncodingOptions extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:livekit.IngressAudioEncodingOptions)
        IngressAudioEncodingOptionsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressAudioEncodingOptions.class.getName());
    }

    // Use IngressAudioEncodingOptions.newBuilder() to construct.
    private IngressAudioEncodingOptions(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private IngressAudioEncodingOptions() {
        audioCodec_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressAudioEncodingOptions_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressAudioEncodingOptions_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions.class,
                        im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions.Builder.class);
    }

    public static final int AUDIO_CODEC_FIELD_NUMBER = 1;
    private int audioCodec_ = 0;

    /**
     * <pre>
     * desired audio codec to publish to room
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 1;</code>
     *
     * @return The enum numeric value on the wire for audioCodec.
     */
    @java.lang.Override
    public int getAudioCodecValue() {
        return audioCodec_;
    }

    /**
     * <pre>
     * desired audio codec to publish to room
     * </pre>
     *
     * <code>.livekit.AudioCodec audio_codec = 1;</code>
     *
     * @return The audioCodec.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.AudioCodec getAudioCodec() {
        im.turms.plugin.livekit.core.proto.models.AudioCodec result =
                im.turms.plugin.livekit.core.proto.models.AudioCodec.forNumber(audioCodec_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.AudioCodec.UNRECOGNIZED
                : result;
    }

    public static final int BITRATE_FIELD_NUMBER = 2;
    private int bitrate_ = 0;

    /**
     * <code>uint32 bitrate = 2;</code>
     *
     * @return The bitrate.
     */
    @java.lang.Override
    public int getBitrate() {
        return bitrate_;
    }

    public static final int DISABLE_DTX_FIELD_NUMBER = 3;
    private boolean disableDtx_ = false;

    /**
     * <code>bool disable_dtx = 3;</code>
     *
     * @return The disableDtx.
     */
    @java.lang.Override
    public boolean getDisableDtx() {
        return disableDtx_;
    }

    public static final int CHANNELS_FIELD_NUMBER = 4;
    private int channels_ = 0;

    /**
     * <code>uint32 channels = 4;</code>
     *
     * @return The channels.
     */
    @java.lang.Override
    public int getChannels() {
        return channels_;
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
        if (audioCodec_ != im.turms.plugin.livekit.core.proto.models.AudioCodec.DEFAULT_AC
                .getNumber()) {
            output.writeEnum(1, audioCodec_);
        }
        if (bitrate_ != 0) {
            output.writeUInt32(2, bitrate_);
        }
        if (disableDtx_) {
            output.writeBool(3, disableDtx_);
        }
        if (channels_ != 0) {
            output.writeUInt32(4, channels_);
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
        if (audioCodec_ != im.turms.plugin.livekit.core.proto.models.AudioCodec.DEFAULT_AC
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, audioCodec_);
        }
        if (bitrate_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(2, bitrate_);
        }
        if (disableDtx_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, disableDtx_);
        }
        if (channels_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, channels_);
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
        if (!(obj instanceof IngressAudioEncodingOptions other)) {
            return super.equals(obj);
        }

        if (audioCodec_ != other.audioCodec_) {
            return false;
        }
        if (getBitrate() != other.getBitrate()) {
            return false;
        }
        if (getDisableDtx() != other.getDisableDtx()) {
            return false;
        }
        if (getChannels() != other.getChannels()) {
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
        hash = (37 * hash) + AUDIO_CODEC_FIELD_NUMBER;
        hash = (53 * hash) + audioCodec_;
        hash = (37 * hash) + BITRATE_FIELD_NUMBER;
        hash = (53 * hash) + getBitrate();
        hash = (37 * hash) + DISABLE_DTX_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDisableDtx());
        hash = (37 * hash) + CHANNELS_FIELD_NUMBER;
        hash = (53 * hash) + getChannels();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions prototype) {
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
     * Protobuf type {@code livekit.IngressAudioEncodingOptions}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.IngressAudioEncodingOptions)
            im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptionsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressAudioEncodingOptions_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressAudioEncodingOptions_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions.class,
                            im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            audioCodec_ = 0;
            bitrate_ = 0;
            disableDtx_ = false;
            channels_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressAudioEncodingOptions_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions build() {
            im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions result =
                    new im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.audioCodec_ = audioCodec_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.bitrate_ = bitrate_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.disableDtx_ = disableDtx_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.channels_ = channels_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions
                    .getDefaultInstance()) {
                return this;
            }
            if (other.audioCodec_ != 0) {
                setAudioCodecValue(other.getAudioCodecValue());
            }
            if (other.getBitrate() != 0) {
                setBitrate(other.getBitrate());
            }
            if (other.getDisableDtx()) {
                setDisableDtx(other.getDisableDtx());
            }
            if (other.getChannels() != 0) {
                setChannels(other.getChannels());
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
                            audioCodec_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            bitrate_ = input.readUInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            disableDtx_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            channels_ = input.readUInt32();
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

        private int audioCodec_ = 0;

        /**
         * <pre>
         * desired audio codec to publish to room
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 1;</code>
         *
         * @return The enum numeric value on the wire for audioCodec.
         */
        @java.lang.Override
        public int getAudioCodecValue() {
            return audioCodec_;
        }

        /**
         * <pre>
         * desired audio codec to publish to room
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 1;</code>
         *
         * @param value The enum numeric value on the wire for audioCodec to set.
         * @return This builder for chaining.
         */
        public Builder setAudioCodecValue(int value) {
            audioCodec_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * desired audio codec to publish to room
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 1;</code>
         *
         * @return The audioCodec.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.AudioCodec getAudioCodec() {
            im.turms.plugin.livekit.core.proto.models.AudioCodec result =
                    im.turms.plugin.livekit.core.proto.models.AudioCodec.forNumber(audioCodec_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.AudioCodec.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * desired audio codec to publish to room
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 1;</code>
         *
         * @param value The audioCodec to set.
         * @return This builder for chaining.
         */
        public Builder setAudioCodec(im.turms.plugin.livekit.core.proto.models.AudioCodec value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            audioCodec_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * desired audio codec to publish to room
         * </pre>
         *
         * <code>.livekit.AudioCodec audio_codec = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAudioCodec() {
            bitField0_ &= ~0x00000001;
            audioCodec_ = 0;
            onChanged();
            return this;
        }

        private int bitrate_;

        /**
         * <code>uint32 bitrate = 2;</code>
         *
         * @return The bitrate.
         */
        @java.lang.Override
        public int getBitrate() {
            return bitrate_;
        }

        /**
         * <code>uint32 bitrate = 2;</code>
         *
         * @param value The bitrate to set.
         * @return This builder for chaining.
         */
        public Builder setBitrate(int value) {

            bitrate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 bitrate = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBitrate() {
            bitField0_ &= ~0x00000002;
            bitrate_ = 0;
            onChanged();
            return this;
        }

        private boolean disableDtx_;

        /**
         * <code>bool disable_dtx = 3;</code>
         *
         * @return The disableDtx.
         */
        @java.lang.Override
        public boolean getDisableDtx() {
            return disableDtx_;
        }

        /**
         * <code>bool disable_dtx = 3;</code>
         *
         * @param value The disableDtx to set.
         * @return This builder for chaining.
         */
        public Builder setDisableDtx(boolean value) {

            disableDtx_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>bool disable_dtx = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDisableDtx() {
            bitField0_ &= ~0x00000004;
            disableDtx_ = false;
            onChanged();
            return this;
        }

        private int channels_;

        /**
         * <code>uint32 channels = 4;</code>
         *
         * @return The channels.
         */
        @java.lang.Override
        public int getChannels() {
            return channels_;
        }

        /**
         * <code>uint32 channels = 4;</code>
         *
         * @param value The channels to set.
         * @return This builder for chaining.
         */
        public Builder setChannels(int value) {

            channels_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 channels = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearChannels() {
            bitField0_ &= ~0x00000008;
            channels_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.IngressAudioEncodingOptions)
    }

    // @@protoc_insertion_point(class_scope:livekit.IngressAudioEncodingOptions)
    private static final im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<IngressAudioEncodingOptions> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public IngressAudioEncodingOptions parsePartialFrom(
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

    public static com.google.protobuf.Parser<IngressAudioEncodingOptions> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<IngressAudioEncodingOptions> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressAudioEncodingOptions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}