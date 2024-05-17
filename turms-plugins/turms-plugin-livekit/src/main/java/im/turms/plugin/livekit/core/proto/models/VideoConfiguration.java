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
 * Protobuf type {@code livekit.VideoConfiguration}
 */
public final class VideoConfiguration extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.VideoConfiguration)
        VideoConfigurationOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                VideoConfiguration.class.getName());
    }

    // Use VideoConfiguration.newBuilder() to construct.
    private VideoConfiguration(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private VideoConfiguration() {
        hardwareEncoder_ = 0;
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoConfiguration_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoConfiguration_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.VideoConfiguration.class,
                        im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder.class);
    }

    public static final int HARDWARE_ENCODER_FIELD_NUMBER = 1;
    private int hardwareEncoder_ = 0;

    /**
     * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
     *
     * @return The enum numeric value on the wire for hardwareEncoder.
     */
    @java.lang.Override
    public int getHardwareEncoderValue() {
        return hardwareEncoder_;
    }

    /**
     * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
     *
     * @return The hardwareEncoder.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getHardwareEncoder() {
        im.turms.plugin.livekit.core.proto.models.ClientConfigSetting result =
                im.turms.plugin.livekit.core.proto.models.ClientConfigSetting
                        .forNumber(hardwareEncoder_);
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
        if (hardwareEncoder_ != im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNSET
                .getNumber()) {
            output.writeEnum(1, hardwareEncoder_);
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
        if (hardwareEncoder_ != im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNSET
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, hardwareEncoder_);
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
        if (!(obj instanceof VideoConfiguration other)) {
            return super.equals(obj);
        }

        if (hardwareEncoder_ != other.hardwareEncoder_) {
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
        hash = (37 * hash) + HARDWARE_ENCODER_FIELD_NUMBER;
        hash = (53 * hash) + hardwareEncoder_;
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.VideoConfiguration prototype) {
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
     * Protobuf type {@code livekit.VideoConfiguration}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.VideoConfiguration)
            im.turms.plugin.livekit.core.proto.models.VideoConfigurationOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoConfiguration_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoConfiguration_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.VideoConfiguration.class,
                            im.turms.plugin.livekit.core.proto.models.VideoConfiguration.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.VideoConfiguration.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            hardwareEncoder_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_VideoConfiguration_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration build() {
            im.turms.plugin.livekit.core.proto.models.VideoConfiguration result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.VideoConfiguration buildPartial() {
            im.turms.plugin.livekit.core.proto.models.VideoConfiguration result =
                    new im.turms.plugin.livekit.core.proto.models.VideoConfiguration(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.hardwareEncoder_ = hardwareEncoder_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.VideoConfiguration) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.models.VideoConfiguration) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.models.VideoConfiguration other) {
            if (other == im.turms.plugin.livekit.core.proto.models.VideoConfiguration
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hardwareEncoder_ != 0) {
                setHardwareEncoderValue(other.getHardwareEncoderValue());
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
                            hardwareEncoder_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
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

        private int hardwareEncoder_ = 0;

        /**
         * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
         *
         * @return The enum numeric value on the wire for hardwareEncoder.
         */
        @java.lang.Override
        public int getHardwareEncoderValue() {
            return hardwareEncoder_;
        }

        /**
         * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
         *
         * @param value The enum numeric value on the wire for hardwareEncoder to set.
         * @return This builder for chaining.
         */
        public Builder setHardwareEncoderValue(int value) {
            hardwareEncoder_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
         *
         * @return The hardwareEncoder.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientConfigSetting getHardwareEncoder() {
            im.turms.plugin.livekit.core.proto.models.ClientConfigSetting result =
                    im.turms.plugin.livekit.core.proto.models.ClientConfigSetting
                            .forNumber(hardwareEncoder_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ClientConfigSetting.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
         *
         * @param value The hardwareEncoder to set.
         * @return This builder for chaining.
         */
        public Builder setHardwareEncoder(
                im.turms.plugin.livekit.core.proto.models.ClientConfigSetting value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            hardwareEncoder_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientConfigSetting hardware_encoder = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearHardwareEncoder() {
            bitField0_ &= ~0x00000001;
            hardwareEncoder_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.VideoConfiguration)
    }

    // @@protoc_insertion_point(class_scope:livekit.VideoConfiguration)
    private static final im.turms.plugin.livekit.core.proto.models.VideoConfiguration DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.VideoConfiguration();
    }

    public static im.turms.plugin.livekit.core.proto.models.VideoConfiguration getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<VideoConfiguration> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public VideoConfiguration parsePartialFrom(
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

    public static com.google.protobuf.Parser<VideoConfiguration> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<VideoConfiguration> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.VideoConfiguration getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}