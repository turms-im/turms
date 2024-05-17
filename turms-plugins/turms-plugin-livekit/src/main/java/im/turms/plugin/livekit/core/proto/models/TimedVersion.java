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
 * Protobuf type {@code livekit.TimedVersion}
 */
public final class TimedVersion extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.TimedVersion)
        TimedVersionOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                TimedVersion.class.getName());
    }

    // Use TimedVersion.newBuilder() to construct.
    private TimedVersion(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private TimedVersion() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TimedVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TimedVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.TimedVersion.class,
                        im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder.class);
    }

    public static final int UNIX_MICRO_FIELD_NUMBER = 1;
    private long unixMicro_ = 0L;

    /**
     * <code>int64 unix_micro = 1;</code>
     *
     * @return The unixMicro.
     */
    @java.lang.Override
    public long getUnixMicro() {
        return unixMicro_;
    }

    public static final int TICKS_FIELD_NUMBER = 2;
    private int ticks_ = 0;

    /**
     * <code>int32 ticks = 2;</code>
     *
     * @return The ticks.
     */
    @java.lang.Override
    public int getTicks() {
        return ticks_;
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
        if (unixMicro_ != 0L) {
            output.writeInt64(1, unixMicro_);
        }
        if (ticks_ != 0) {
            output.writeInt32(2, ticks_);
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
        if (unixMicro_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, unixMicro_);
        }
        if (ticks_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, ticks_);
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
        if (!(obj instanceof TimedVersion other)) {
            return super.equals(obj);
        }

        if (getUnixMicro() != other.getUnixMicro()) {
            return false;
        }
        if (getTicks() != other.getTicks()) {
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
        hash = (37 * hash) + UNIX_MICRO_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUnixMicro());
        hash = (37 * hash) + TICKS_FIELD_NUMBER;
        hash = (53 * hash) + getTicks();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.TimedVersion prototype) {
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
     * Protobuf type {@code livekit.TimedVersion}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.TimedVersion)
            im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TimedVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TimedVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.TimedVersion.class,
                            im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.TimedVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            unixMicro_ = 0L;
            ticks_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_TimedVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TimedVersion getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.TimedVersion.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TimedVersion build() {
            im.turms.plugin.livekit.core.proto.models.TimedVersion result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.TimedVersion buildPartial() {
            im.turms.plugin.livekit.core.proto.models.TimedVersion result =
                    new im.turms.plugin.livekit.core.proto.models.TimedVersion(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.TimedVersion result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.unixMicro_ = unixMicro_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.ticks_ = ticks_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.TimedVersion) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.TimedVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.TimedVersion other) {
            if (other == im.turms.plugin.livekit.core.proto.models.TimedVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getUnixMicro() != 0L) {
                setUnixMicro(other.getUnixMicro());
            }
            if (other.getTicks() != 0) {
                setTicks(other.getTicks());
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
                            unixMicro_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            ticks_ = input.readInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
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

        private long unixMicro_;

        /**
         * <code>int64 unix_micro = 1;</code>
         *
         * @return The unixMicro.
         */
        @java.lang.Override
        public long getUnixMicro() {
            return unixMicro_;
        }

        /**
         * <code>int64 unix_micro = 1;</code>
         *
         * @param value The unixMicro to set.
         * @return This builder for chaining.
         */
        public Builder setUnixMicro(long value) {

            unixMicro_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 unix_micro = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUnixMicro() {
            bitField0_ &= ~0x00000001;
            unixMicro_ = 0L;
            onChanged();
            return this;
        }

        private int ticks_;

        /**
         * <code>int32 ticks = 2;</code>
         *
         * @return The ticks.
         */
        @java.lang.Override
        public int getTicks() {
            return ticks_;
        }

        /**
         * <code>int32 ticks = 2;</code>
         *
         * @param value The ticks to set.
         * @return This builder for chaining.
         */
        public Builder setTicks(int value) {

            ticks_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int32 ticks = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTicks() {
            bitField0_ &= ~0x00000002;
            ticks_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.TimedVersion)
    }

    // @@protoc_insertion_point(class_scope:livekit.TimedVersion)
    private static final im.turms.plugin.livekit.core.proto.models.TimedVersion DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.TimedVersion();
    }

    public static im.turms.plugin.livekit.core.proto.models.TimedVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TimedVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public TimedVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<TimedVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TimedVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TimedVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}