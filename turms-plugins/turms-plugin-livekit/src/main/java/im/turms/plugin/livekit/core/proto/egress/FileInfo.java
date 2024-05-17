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

package im.turms.plugin.livekit.core.proto.egress;

/**
 * Protobuf type {@code livekit.FileInfo}
 */
public final class FileInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.FileInfo)
        FileInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                FileInfo.class.getName());
    }

    // Use FileInfo.newBuilder() to construct.
    private FileInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private FileInfo() {
        filename_ = "";
        location_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_FileInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_FileInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.FileInfo.class,
                        im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder.class);
    }

    public static final int FILENAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object filename_ = "";

    /**
     * <code>string filename = 1;</code>
     *
     * @return The filename.
     */
    @java.lang.Override
    public java.lang.String getFilename() {
        java.lang.Object ref = filename_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            filename_ = s;
            return s;
        }
    }

    /**
     * <code>string filename = 1;</code>
     *
     * @return The bytes for filename.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getFilenameBytes() {
        java.lang.Object ref = filename_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            filename_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int STARTED_AT_FIELD_NUMBER = 2;
    private long startedAt_ = 0L;

    /**
     * <code>int64 started_at = 2;</code>
     *
     * @return The startedAt.
     */
    @java.lang.Override
    public long getStartedAt() {
        return startedAt_;
    }

    public static final int ENDED_AT_FIELD_NUMBER = 3;
    private long endedAt_ = 0L;

    /**
     * <code>int64 ended_at = 3;</code>
     *
     * @return The endedAt.
     */
    @java.lang.Override
    public long getEndedAt() {
        return endedAt_;
    }

    public static final int DURATION_FIELD_NUMBER = 6;
    private long duration_ = 0L;

    /**
     * <code>int64 duration = 6;</code>
     *
     * @return The duration.
     */
    @java.lang.Override
    public long getDuration() {
        return duration_;
    }

    public static final int SIZE_FIELD_NUMBER = 4;
    private long size_ = 0L;

    /**
     * <code>int64 size = 4;</code>
     *
     * @return The size.
     */
    @java.lang.Override
    public long getSize() {
        return size_;
    }

    public static final int LOCATION_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object location_ = "";

    /**
     * <code>string location = 5;</code>
     *
     * @return The location.
     */
    @java.lang.Override
    public java.lang.String getLocation() {
        java.lang.Object ref = location_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            location_ = s;
            return s;
        }
    }

    /**
     * <code>string location = 5;</code>
     *
     * @return The bytes for location.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getLocationBytes() {
        java.lang.Object ref = location_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            location_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filename_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, filename_);
        }
        if (startedAt_ != 0L) {
            output.writeInt64(2, startedAt_);
        }
        if (endedAt_ != 0L) {
            output.writeInt64(3, endedAt_);
        }
        if (size_ != 0L) {
            output.writeInt64(4, size_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(location_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, location_);
        }
        if (duration_ != 0L) {
            output.writeInt64(6, duration_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filename_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, filename_);
        }
        if (startedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, startedAt_);
        }
        if (endedAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, endedAt_);
        }
        if (size_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, size_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(location_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, location_);
        }
        if (duration_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, duration_);
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
        if (!(obj instanceof FileInfo other)) {
            return super.equals(obj);
        }

        if (!getFilename().equals(other.getFilename())) {
            return false;
        }
        if (getStartedAt() != other.getStartedAt()) {
            return false;
        }
        if (getEndedAt() != other.getEndedAt()) {
            return false;
        }
        if (getDuration() != other.getDuration()) {
            return false;
        }
        if (getSize() != other.getSize()) {
            return false;
        }
        if (!getLocation().equals(other.getLocation())) {
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
        hash = (37 * hash) + FILENAME_FIELD_NUMBER;
        hash = (53 * hash) + getFilename().hashCode();
        hash = (37 * hash) + STARTED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartedAt());
        hash = (37 * hash) + ENDED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndedAt());
        hash = (37 * hash) + DURATION_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDuration());
        hash = (37 * hash) + SIZE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getSize());
        hash = (37 * hash) + LOCATION_FIELD_NUMBER;
        hash = (53 * hash) + getLocation().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.egress.FileInfo prototype) {
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
     * Protobuf type {@code livekit.FileInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.FileInfo)
            im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_FileInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_FileInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.FileInfo.class,
                            im.turms.plugin.livekit.core.proto.egress.FileInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.FileInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            filename_ = "";
            startedAt_ = 0L;
            endedAt_ = 0L;
            duration_ = 0L;
            size_ = 0L;
            location_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_FileInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.FileInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.FileInfo build() {
            im.turms.plugin.livekit.core.proto.egress.FileInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.FileInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.FileInfo result =
                    new im.turms.plugin.livekit.core.proto.egress.FileInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.FileInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.filename_ = filename_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.startedAt_ = startedAt_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.endedAt_ = endedAt_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.duration_ = duration_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.size_ = size_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.location_ = location_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.FileInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.FileInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.FileInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.FileInfo.getDefaultInstance()) {
                return this;
            }
            if (!other.getFilename()
                    .isEmpty()) {
                filename_ = other.filename_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getStartedAt() != 0L) {
                setStartedAt(other.getStartedAt());
            }
            if (other.getEndedAt() != 0L) {
                setEndedAt(other.getEndedAt());
            }
            if (other.getDuration() != 0L) {
                setDuration(other.getDuration());
            }
            if (other.getSize() != 0L) {
                setSize(other.getSize());
            }
            if (!other.getLocation()
                    .isEmpty()) {
                location_ = other.location_;
                bitField0_ |= 0x00000020;
                onChanged();
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
                            filename_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            startedAt_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            endedAt_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            size_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 32
                        case 42 -> {
                            location_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 42
                        case 48 -> {
                            duration_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 48
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

        private java.lang.Object filename_ = "";

        /**
         * <code>string filename = 1;</code>
         *
         * @return The filename.
         */
        public java.lang.String getFilename() {
            java.lang.Object ref = filename_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                filename_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string filename = 1;</code>
         *
         * @return The bytes for filename.
         */
        public com.google.protobuf.ByteString getFilenameBytes() {
            java.lang.Object ref = filename_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                filename_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string filename = 1;</code>
         *
         * @param value The filename to set.
         * @return This builder for chaining.
         */
        public Builder setFilename(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            filename_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string filename = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFilename() {
            filename_ = getDefaultInstance().getFilename();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string filename = 1;</code>
         *
         * @param value The bytes for filename to set.
         * @return This builder for chaining.
         */
        public Builder setFilenameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            filename_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private long startedAt_;

        /**
         * <code>int64 started_at = 2;</code>
         *
         * @return The startedAt.
         */
        @java.lang.Override
        public long getStartedAt() {
            return startedAt_;
        }

        /**
         * <code>int64 started_at = 2;</code>
         *
         * @param value The startedAt to set.
         * @return This builder for chaining.
         */
        public Builder setStartedAt(long value) {

            startedAt_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int64 started_at = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartedAt() {
            bitField0_ &= ~0x00000002;
            startedAt_ = 0L;
            onChanged();
            return this;
        }

        private long endedAt_;

        /**
         * <code>int64 ended_at = 3;</code>
         *
         * @return The endedAt.
         */
        @java.lang.Override
        public long getEndedAt() {
            return endedAt_;
        }

        /**
         * <code>int64 ended_at = 3;</code>
         *
         * @param value The endedAt to set.
         * @return This builder for chaining.
         */
        public Builder setEndedAt(long value) {

            endedAt_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int64 ended_at = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndedAt() {
            bitField0_ &= ~0x00000004;
            endedAt_ = 0L;
            onChanged();
            return this;
        }

        private long duration_;

        /**
         * <code>int64 duration = 6;</code>
         *
         * @return The duration.
         */
        @java.lang.Override
        public long getDuration() {
            return duration_;
        }

        /**
         * <code>int64 duration = 6;</code>
         *
         * @param value The duration to set.
         * @return This builder for chaining.
         */
        public Builder setDuration(long value) {

            duration_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>int64 duration = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDuration() {
            bitField0_ &= ~0x00000008;
            duration_ = 0L;
            onChanged();
            return this;
        }

        private long size_;

        /**
         * <code>int64 size = 4;</code>
         *
         * @return The size.
         */
        @java.lang.Override
        public long getSize() {
            return size_;
        }

        /**
         * <code>int64 size = 4;</code>
         *
         * @param value The size to set.
         * @return This builder for chaining.
         */
        public Builder setSize(long value) {

            size_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>int64 size = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSize() {
            bitField0_ &= ~0x00000010;
            size_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object location_ = "";

        /**
         * <code>string location = 5;</code>
         *
         * @return The location.
         */
        public java.lang.String getLocation() {
            java.lang.Object ref = location_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                location_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string location = 5;</code>
         *
         * @return The bytes for location.
         */
        public com.google.protobuf.ByteString getLocationBytes() {
            java.lang.Object ref = location_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                location_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string location = 5;</code>
         *
         * @param value The location to set.
         * @return This builder for chaining.
         */
        public Builder setLocation(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            location_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string location = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLocation() {
            location_ = getDefaultInstance().getLocation();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string location = 5;</code>
         *
         * @param value The bytes for location to set.
         * @return This builder for chaining.
         */
        public Builder setLocationBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            location_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.FileInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.FileInfo)
    private static final im.turms.plugin.livekit.core.proto.egress.FileInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.FileInfo();
    }

    public static im.turms.plugin.livekit.core.proto.egress.FileInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<FileInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public FileInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<FileInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<FileInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.FileInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}