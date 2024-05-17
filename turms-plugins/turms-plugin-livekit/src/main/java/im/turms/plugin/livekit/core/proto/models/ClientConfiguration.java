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
 * server provided client configuration
 * </pre>
 *
 * Protobuf type {@code livekit.ClientConfiguration}
 */
public final class ClientConfiguration extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ClientConfiguration)
        ClientConfigurationOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ClientConfiguration.class.getName());
    }

    // Use ClientConfiguration.newBuilder() to construct.
    private ClientConfiguration(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ClientConfiguration() {
        resumeConnection_ = 0;
        forceRelay_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientConfiguration_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientConfiguration_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ClientConfiguration.class,
                        im.turms.plugin.livekit.core.proto.models.ClientConfiguration.Builder.class);
    }

    private int bitField0_;
    public static final int VIDEO_FIELD_NUMBER = 1;
    private im.turms.plugin.livekit.core.proto.models.VideoConfiguration video_;

    /**
     * <code>.livekit.VideoConfiguration video = 1;</code>
     *
     * @return Whether the video field is set.
     */
    @java.lang.Override
    public boolean hasVideo() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.VideoConfiguration video = 1;</code>
     *
     * @return The video.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoConfiguration getVideo() {
        return video_ == null
                ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration.getDefaultInstance()
                : video_;
    }

    /**
     * <code>.livekit.VideoConfiguration video = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder getVideoOrBuilder() {
        return video_ == null
                ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration.getDefaultInstance()
                : video_;
    }

    public static final int SCREEN_FIELD_NUMBER = 2;
    private im.turms.plugin.livekit.core.proto.models.VideoConfiguration screen_;

    /**
     * <code>.livekit.VideoConfiguration screen = 2;</code>
     *
     * @return Whether the screen field is set.
     */
    @java.lang.Override
    public boolean hasScreen() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>.livekit.VideoConfiguration screen = 2;</code>
     *
     * @return The screen.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoConfiguration getScreen() {
        return screen_ == null
                ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration.getDefaultInstance()
                : screen_;
    }

    /**
     * <code>.livekit.VideoConfiguration screen = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder getScreenOrBuilder() {
        return screen_ == null
                ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration.getDefaultInstance()
                : screen_;
    }

    public static final int RESUME_CONNECTION_FIELD_NUMBER = 3;
    private int resumeConnection_ = 0;

    /**
     * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
     *
     * @return The enum numeric value on the wire for resumeConnection.
     */
    @java.lang.Override
    public int getResumeConnectionValue() {
        return resumeConnection_;
    }

    /**
     * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
     *
     * @return The resumeConnection.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getResumeConnection() {
        im.turms.plugin.livekit.core.proto.models.ClientConfigSetting result =
                im.turms.plugin.livekit.core.proto.models.ClientConfigSetting
                        .forNumber(resumeConnection_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNRECOGNIZED
                : result;
    }

    public static final int DISABLED_CODECS_FIELD_NUMBER = 4;
    private im.turms.plugin.livekit.core.proto.models.DisabledCodecs disabledCodecs_;

    /**
     * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
     *
     * @return Whether the disabledCodecs field is set.
     */
    @java.lang.Override
    public boolean hasDisabledCodecs() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
     *
     * @return The disabledCodecs.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.DisabledCodecs getDisabledCodecs() {
        return disabledCodecs_ == null
                ? im.turms.plugin.livekit.core.proto.models.DisabledCodecs.getDefaultInstance()
                : disabledCodecs_;
    }

    /**
     * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.DisabledCodecsOrBuilder getDisabledCodecsOrBuilder() {
        return disabledCodecs_ == null
                ? im.turms.plugin.livekit.core.proto.models.DisabledCodecs.getDefaultInstance()
                : disabledCodecs_;
    }

    public static final int FORCE_RELAY_FIELD_NUMBER = 5;
    private int forceRelay_ = 0;

    /**
     * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
     *
     * @return The enum numeric value on the wire for forceRelay.
     */
    @java.lang.Override
    public int getForceRelayValue() {
        return forceRelay_;
    }

    /**
     * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
     *
     * @return The forceRelay.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getForceRelay() {
        im.turms.plugin.livekit.core.proto.models.ClientConfigSetting result =
                im.turms.plugin.livekit.core.proto.models.ClientConfigSetting
                        .forNumber(forceRelay_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNRECOGNIZED
                : result;
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(1, getVideo());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(2, getScreen());
        }
        if (resumeConnection_ != im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNSET
                .getNumber()) {
            output.writeEnum(3, resumeConnection_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(4, getDisabledCodecs());
        }
        if (forceRelay_ != im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNSET
                .getNumber()) {
            output.writeEnum(5, forceRelay_);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, getVideo());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, getScreen());
        }
        if (resumeConnection_ != im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNSET
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(3, resumeConnection_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                    getDisabledCodecs());
        }
        if (forceRelay_ != im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNSET
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5, forceRelay_);
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
        if (!(obj instanceof ClientConfiguration other)) {
            return super.equals(obj);
        }

        if (hasVideo() != other.hasVideo()) {
            return false;
        }
        if (hasVideo()) {
            if (!getVideo().equals(other.getVideo())) {
                return false;
            }
        }
        if (hasScreen() != other.hasScreen()) {
            return false;
        }
        if (hasScreen()) {
            if (!getScreen().equals(other.getScreen())) {
                return false;
            }
        }
        if (resumeConnection_ != other.resumeConnection_) {
            return false;
        }
        if (hasDisabledCodecs() != other.hasDisabledCodecs()) {
            return false;
        }
        if (hasDisabledCodecs()) {
            if (!getDisabledCodecs().equals(other.getDisabledCodecs())) {
                return false;
            }
        }
        if (forceRelay_ != other.forceRelay_) {
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
        if (hasVideo()) {
            hash = (37 * hash) + VIDEO_FIELD_NUMBER;
            hash = (53 * hash) + getVideo().hashCode();
        }
        if (hasScreen()) {
            hash = (37 * hash) + SCREEN_FIELD_NUMBER;
            hash = (53 * hash) + getScreen().hashCode();
        }
        hash = (37 * hash) + RESUME_CONNECTION_FIELD_NUMBER;
        hash = (53 * hash) + resumeConnection_;
        if (hasDisabledCodecs()) {
            hash = (37 * hash) + DISABLED_CODECS_FIELD_NUMBER;
            hash = (53 * hash) + getDisabledCodecs().hashCode();
        }
        hash = (37 * hash) + FORCE_RELAY_FIELD_NUMBER;
        hash = (53 * hash) + forceRelay_;
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ClientConfiguration prototype) {
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
     * server provided client configuration
     * </pre>
     *
     * Protobuf type {@code livekit.ClientConfiguration}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ClientConfiguration)
            im.turms.plugin.livekit.core.proto.models.ClientConfigurationOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientConfiguration_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientConfiguration_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ClientConfiguration.class,
                            im.turms.plugin.livekit.core.proto.models.ClientConfiguration.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.models.ClientConfiguration.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getVideoFieldBuilder();
                getScreenFieldBuilder();
                getDisabledCodecsFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            video_ = null;
            if (videoBuilder_ != null) {
                videoBuilder_.dispose();
                videoBuilder_ = null;
            }
            screen_ = null;
            if (screenBuilder_ != null) {
                screenBuilder_.dispose();
                screenBuilder_ = null;
            }
            resumeConnection_ = 0;
            disabledCodecs_ = null;
            if (disabledCodecsBuilder_ != null) {
                disabledCodecsBuilder_.dispose();
                disabledCodecsBuilder_ = null;
            }
            forceRelay_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientConfiguration_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientConfiguration getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ClientConfiguration
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientConfiguration build() {
            im.turms.plugin.livekit.core.proto.models.ClientConfiguration result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientConfiguration buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ClientConfiguration result =
                    new im.turms.plugin.livekit.core.proto.models.ClientConfiguration(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.ClientConfiguration result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.video_ = videoBuilder_ == null
                        ? video_
                        : videoBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.screen_ = screenBuilder_ == null
                        ? screen_
                        : screenBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.resumeConnection_ = resumeConnection_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.disabledCodecs_ = disabledCodecsBuilder_ == null
                        ? disabledCodecs_
                        : disabledCodecsBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.forceRelay_ = forceRelay_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ClientConfiguration) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.models.ClientConfiguration) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.models.ClientConfiguration other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ClientConfiguration
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasVideo()) {
                mergeVideo(other.getVideo());
            }
            if (other.hasScreen()) {
                mergeScreen(other.getScreen());
            }
            if (other.resumeConnection_ != 0) {
                setResumeConnectionValue(other.getResumeConnectionValue());
            }
            if (other.hasDisabledCodecs()) {
                mergeDisabledCodecs(other.getDisabledCodecs());
            }
            if (other.forceRelay_ != 0) {
                setForceRelayValue(other.getForceRelayValue());
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
                            input.readMessage(getVideoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            input.readMessage(getScreenFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            resumeConnection_ = input.readEnum();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            input.readMessage(getDisabledCodecsFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 40 -> {
                            forceRelay_ = input.readEnum();
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

        private im.turms.plugin.livekit.core.proto.models.VideoConfiguration video_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoConfiguration, im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder, im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder> videoBuilder_;

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         *
         * @return Whether the video field is set.
         */
        public boolean hasVideo() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         *
         * @return The video.
         */
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration getVideo() {
            if (videoBuilder_ == null) {
                return video_ == null
                        ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                                .getDefaultInstance()
                        : video_;
            } else {
                return videoBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        public Builder setVideo(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration value) {
            if (videoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                video_ = value;
            } else {
                videoBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        public Builder setVideo(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder builderForValue) {
            if (videoBuilder_ == null) {
                video_ = builderForValue.build();
            } else {
                videoBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        public Builder mergeVideo(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration value) {
            if (videoBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)
                        && video_ != null
                        && video_ != im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                                .getDefaultInstance()) {
                    getVideoBuilder().mergeFrom(value);
                } else {
                    video_ = value;
                }
            } else {
                videoBuilder_.mergeFrom(value);
            }
            if (video_ != null) {
                bitField0_ |= 0x00000001;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        public Builder clearVideo() {
            bitField0_ &= ~0x00000001;
            video_ = null;
            if (videoBuilder_ != null) {
                videoBuilder_.dispose();
                videoBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder getVideoBuilder() {
            bitField0_ |= 0x00000001;
            onChanged();
            return getVideoFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder getVideoOrBuilder() {
            if (videoBuilder_ != null) {
                return videoBuilder_.getMessageOrBuilder();
            } else {
                return video_ == null
                        ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                                .getDefaultInstance()
                        : video_;
            }
        }

        /**
         * <code>.livekit.VideoConfiguration video = 1;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoConfiguration, im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder, im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder> getVideoFieldBuilder() {
            if (videoBuilder_ == null) {
                videoBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getVideo(),
                        getParentForChildren(),
                        isClean());
                video_ = null;
            }
            return videoBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.models.VideoConfiguration screen_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoConfiguration, im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder, im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder> screenBuilder_;

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         *
         * @return Whether the screen field is set.
         */
        public boolean hasScreen() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         *
         * @return The screen.
         */
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration getScreen() {
            if (screenBuilder_ == null) {
                return screen_ == null
                        ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                                .getDefaultInstance()
                        : screen_;
            } else {
                return screenBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        public Builder setScreen(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration value) {
            if (screenBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                screen_ = value;
            } else {
                screenBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        public Builder setScreen(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder builderForValue) {
            if (screenBuilder_ == null) {
                screen_ = builderForValue.build();
            } else {
                screenBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        public Builder mergeScreen(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration value) {
            if (screenBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)
                        && screen_ != null
                        && screen_ != im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                                .getDefaultInstance()) {
                    getScreenBuilder().mergeFrom(value);
                } else {
                    screen_ = value;
                }
            } else {
                screenBuilder_.mergeFrom(value);
            }
            if (screen_ != null) {
                bitField0_ |= 0x00000002;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        public Builder clearScreen() {
            bitField0_ &= ~0x00000002;
            screen_ = null;
            if (screenBuilder_ != null) {
                screenBuilder_.dispose();
                screenBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder getScreenBuilder() {
            bitField0_ |= 0x00000002;
            onChanged();
            return getScreenFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder getScreenOrBuilder() {
            if (screenBuilder_ != null) {
                return screenBuilder_.getMessageOrBuilder();
            } else {
                return screen_ == null
                        ? im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                                .getDefaultInstance()
                        : screen_;
            }
        }

        /**
         * <code>.livekit.VideoConfiguration screen = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.VideoConfiguration, im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder, im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder> getScreenFieldBuilder() {
            if (screenBuilder_ == null) {
                screenBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getScreen(),
                        getParentForChildren(),
                        isClean());
                screen_ = null;
            }
            return screenBuilder_;
        }

        private int resumeConnection_ = 0;

        /**
         * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
         *
         * @return The enum numeric value on the wire for resumeConnection.
         */
        @java.lang.Override
        public int getResumeConnectionValue() {
            return resumeConnection_;
        }

        /**
         * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
         *
         * @param value The enum numeric value on the wire for resumeConnection to set.
         * @return This builder for chaining.
         */
        public Builder setResumeConnectionValue(int value) {
            resumeConnection_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
         *
         * @return The resumeConnection.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getResumeConnection() {
            im.turms.plugin.livekit.core.proto.models.ClientConfigSetting result =
                    im.turms.plugin.livekit.core.proto.models.ClientConfigSetting
                            .forNumber(resumeConnection_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
         *
         * @param value The resumeConnection to set.
         * @return This builder for chaining.
         */
        public Builder setResumeConnection(
                im.turms.plugin.livekit.core.proto.models.ClientConfigSetting value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000004;
            resumeConnection_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientConfigSetting resume_connection = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearResumeConnection() {
            bitField0_ &= ~0x00000004;
            resumeConnection_ = 0;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.models.DisabledCodecs disabledCodecs_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.DisabledCodecs, im.turms.plugin.livekit.core.proto.models.DisabledCodecs.Builder, im.turms.plugin.livekit.core.proto.models.DisabledCodecsOrBuilder> disabledCodecsBuilder_;

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         *
         * @return Whether the disabledCodecs field is set.
         */
        public boolean hasDisabledCodecs() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         *
         * @return The disabledCodecs.
         */
        public im.turms.plugin.livekit.core.proto.models.DisabledCodecs getDisabledCodecs() {
            if (disabledCodecsBuilder_ == null) {
                return disabledCodecs_ == null
                        ? im.turms.plugin.livekit.core.proto.models.DisabledCodecs
                                .getDefaultInstance()
                        : disabledCodecs_;
            } else {
                return disabledCodecsBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        public Builder setDisabledCodecs(
                im.turms.plugin.livekit.core.proto.models.DisabledCodecs value) {
            if (disabledCodecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                disabledCodecs_ = value;
            } else {
                disabledCodecsBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        public Builder setDisabledCodecs(
                im.turms.plugin.livekit.core.proto.models.DisabledCodecs.Builder builderForValue) {
            if (disabledCodecsBuilder_ == null) {
                disabledCodecs_ = builderForValue.build();
            } else {
                disabledCodecsBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        public Builder mergeDisabledCodecs(
                im.turms.plugin.livekit.core.proto.models.DisabledCodecs value) {
            if (disabledCodecsBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)
                        && disabledCodecs_ != null
                        && disabledCodecs_ != im.turms.plugin.livekit.core.proto.models.DisabledCodecs
                                .getDefaultInstance()) {
                    getDisabledCodecsBuilder().mergeFrom(value);
                } else {
                    disabledCodecs_ = value;
                }
            } else {
                disabledCodecsBuilder_.mergeFrom(value);
            }
            if (disabledCodecs_ != null) {
                bitField0_ |= 0x00000008;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        public Builder clearDisabledCodecs() {
            bitField0_ &= ~0x00000008;
            disabledCodecs_ = null;
            if (disabledCodecsBuilder_ != null) {
                disabledCodecsBuilder_.dispose();
                disabledCodecsBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.DisabledCodecs.Builder getDisabledCodecsBuilder() {
            bitField0_ |= 0x00000008;
            onChanged();
            return getDisabledCodecsFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.DisabledCodecsOrBuilder getDisabledCodecsOrBuilder() {
            if (disabledCodecsBuilder_ != null) {
                return disabledCodecsBuilder_.getMessageOrBuilder();
            } else {
                return disabledCodecs_ == null
                        ? im.turms.plugin.livekit.core.proto.models.DisabledCodecs
                                .getDefaultInstance()
                        : disabledCodecs_;
            }
        }

        /**
         * <code>.livekit.DisabledCodecs disabled_codecs = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.DisabledCodecs, im.turms.plugin.livekit.core.proto.models.DisabledCodecs.Builder, im.turms.plugin.livekit.core.proto.models.DisabledCodecsOrBuilder> getDisabledCodecsFieldBuilder() {
            if (disabledCodecsBuilder_ == null) {
                disabledCodecsBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getDisabledCodecs(),
                        getParentForChildren(),
                        isClean());
                disabledCodecs_ = null;
            }
            return disabledCodecsBuilder_;
        }

        private int forceRelay_ = 0;

        /**
         * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
         *
         * @return The enum numeric value on the wire for forceRelay.
         */
        @java.lang.Override
        public int getForceRelayValue() {
            return forceRelay_;
        }

        /**
         * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
         *
         * @param value The enum numeric value on the wire for forceRelay to set.
         * @return This builder for chaining.
         */
        public Builder setForceRelayValue(int value) {
            forceRelay_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
         *
         * @return The forceRelay.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getForceRelay() {
            im.turms.plugin.livekit.core.proto.models.ClientConfigSetting result =
                    im.turms.plugin.livekit.core.proto.models.ClientConfigSetting
                            .forNumber(forceRelay_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
         *
         * @param value The forceRelay to set.
         * @return This builder for chaining.
         */
        public Builder setForceRelay(
                im.turms.plugin.livekit.core.proto.models.ClientConfigSetting value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            forceRelay_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientConfigSetting force_relay = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearForceRelay() {
            bitField0_ &= ~0x00000010;
            forceRelay_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ClientConfiguration)
    }

    // @@protoc_insertion_point(class_scope:livekit.ClientConfiguration)
    private static final im.turms.plugin.livekit.core.proto.models.ClientConfiguration DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ClientConfiguration();
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientConfiguration getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ClientConfiguration> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ClientConfiguration parsePartialFrom(
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

    public static com.google.protobuf.Parser<ClientConfiguration> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ClientConfiguration> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ClientConfiguration getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}