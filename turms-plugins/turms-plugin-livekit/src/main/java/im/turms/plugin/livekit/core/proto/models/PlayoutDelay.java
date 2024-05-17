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
 * Protobuf type {@code livekit.PlayoutDelay}
 */
public final class PlayoutDelay extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.PlayoutDelay)
        PlayoutDelayOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                PlayoutDelay.class.getName());
    }

    // Use PlayoutDelay.newBuilder() to construct.
    private PlayoutDelay(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private PlayoutDelay() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_PlayoutDelay_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_PlayoutDelay_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.PlayoutDelay.class,
                        im.turms.plugin.livekit.core.proto.models.PlayoutDelay.Builder.class);
    }

    public static final int ENABLED_FIELD_NUMBER = 1;
    private boolean enabled_ = false;

    /**
     * <code>bool enabled = 1;</code>
     *
     * @return The enabled.
     */
    @java.lang.Override
    public boolean getEnabled() {
        return enabled_;
    }

    public static final int MIN_FIELD_NUMBER = 2;
    private int min_ = 0;

    /**
     * <code>uint32 min = 2;</code>
     *
     * @return The min.
     */
    @java.lang.Override
    public int getMin() {
        return min_;
    }

    public static final int MAX_FIELD_NUMBER = 3;
    private int max_ = 0;

    /**
     * <code>uint32 max = 3;</code>
     *
     * @return The max.
     */
    @java.lang.Override
    public int getMax() {
        return max_;
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
        if (enabled_) {
            output.writeBool(1, enabled_);
        }
        if (min_ != 0) {
            output.writeUInt32(2, min_);
        }
        if (max_ != 0) {
            output.writeUInt32(3, max_);
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
        if (enabled_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(1, enabled_);
        }
        if (min_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(2, min_);
        }
        if (max_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, max_);
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
        if (!(obj instanceof PlayoutDelay other)) {
            return super.equals(obj);
        }

        if (getEnabled() != other.getEnabled()) {
            return false;
        }
        if (getMin() != other.getMin()) {
            return false;
        }
        if (getMax() != other.getMax()) {
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
        hash = (37 * hash) + ENABLED_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getEnabled());
        hash = (37 * hash) + MIN_FIELD_NUMBER;
        hash = (53 * hash) + getMin();
        hash = (37 * hash) + MAX_FIELD_NUMBER;
        hash = (53 * hash) + getMax();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.PlayoutDelay prototype) {
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
     * Protobuf type {@code livekit.PlayoutDelay}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.PlayoutDelay)
            im.turms.plugin.livekit.core.proto.models.PlayoutDelayOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_PlayoutDelay_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_PlayoutDelay_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.PlayoutDelay.class,
                            im.turms.plugin.livekit.core.proto.models.PlayoutDelay.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.PlayoutDelay.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            enabled_ = false;
            min_ = 0;
            max_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_PlayoutDelay_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.PlayoutDelay getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.PlayoutDelay.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.PlayoutDelay build() {
            im.turms.plugin.livekit.core.proto.models.PlayoutDelay result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.PlayoutDelay buildPartial() {
            im.turms.plugin.livekit.core.proto.models.PlayoutDelay result =
                    new im.turms.plugin.livekit.core.proto.models.PlayoutDelay(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.PlayoutDelay result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.enabled_ = enabled_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.min_ = min_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.max_ = max_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.PlayoutDelay) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.PlayoutDelay) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.PlayoutDelay other) {
            if (other == im.turms.plugin.livekit.core.proto.models.PlayoutDelay
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getEnabled()) {
                setEnabled(other.getEnabled());
            }
            if (other.getMin() != 0) {
                setMin(other.getMin());
            }
            if (other.getMax() != 0) {
                setMax(other.getMax());
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
                            enabled_ = input.readBool();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            min_ = input.readUInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            max_ = input.readUInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
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

        private boolean enabled_;

        /**
         * <code>bool enabled = 1;</code>
         *
         * @return The enabled.
         */
        @java.lang.Override
        public boolean getEnabled() {
            return enabled_;
        }

        /**
         * <code>bool enabled = 1;</code>
         *
         * @param value The enabled to set.
         * @return This builder for chaining.
         */
        public Builder setEnabled(boolean value) {

            enabled_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>bool enabled = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEnabled() {
            bitField0_ &= ~0x00000001;
            enabled_ = false;
            onChanged();
            return this;
        }

        private int min_;

        /**
         * <code>uint32 min = 2;</code>
         *
         * @return The min.
         */
        @java.lang.Override
        public int getMin() {
            return min_;
        }

        /**
         * <code>uint32 min = 2;</code>
         *
         * @param value The min to set.
         * @return This builder for chaining.
         */
        public Builder setMin(int value) {

            min_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 min = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMin() {
            bitField0_ &= ~0x00000002;
            min_ = 0;
            onChanged();
            return this;
        }

        private int max_;

        /**
         * <code>uint32 max = 3;</code>
         *
         * @return The max.
         */
        @java.lang.Override
        public int getMax() {
            return max_;
        }

        /**
         * <code>uint32 max = 3;</code>
         *
         * @param value The max to set.
         * @return This builder for chaining.
         */
        public Builder setMax(int value) {

            max_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 max = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMax() {
            bitField0_ &= ~0x00000004;
            max_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.PlayoutDelay)
    }

    // @@protoc_insertion_point(class_scope:livekit.PlayoutDelay)
    private static final im.turms.plugin.livekit.core.proto.models.PlayoutDelay DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.PlayoutDelay();
    }

    public static im.turms.plugin.livekit.core.proto.models.PlayoutDelay getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PlayoutDelay> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public PlayoutDelay parsePartialFrom(
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

    public static com.google.protobuf.Parser<PlayoutDelay> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PlayoutDelay> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.PlayoutDelay getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}