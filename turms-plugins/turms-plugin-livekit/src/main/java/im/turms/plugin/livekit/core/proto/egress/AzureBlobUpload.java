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
 * Protobuf type {@code livekit.AzureBlobUpload}
 */
public final class AzureBlobUpload extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.AzureBlobUpload)
        AzureBlobUploadOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                AzureBlobUpload.class.getName());
    }

    // Use AzureBlobUpload.newBuilder() to construct.
    private AzureBlobUpload(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private AzureBlobUpload() {
        accountName_ = "";
        accountKey_ = "";
        containerName_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AzureBlobUpload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AzureBlobUpload_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.class,
                        im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder.class);
    }

    public static final int ACCOUNT_NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object accountName_ = "";

    /**
     * <code>string account_name = 1;</code>
     *
     * @return The accountName.
     */
    @java.lang.Override
    public java.lang.String getAccountName() {
        java.lang.Object ref = accountName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            accountName_ = s;
            return s;
        }
    }

    /**
     * <code>string account_name = 1;</code>
     *
     * @return The bytes for accountName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccountNameBytes() {
        java.lang.Object ref = accountName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            accountName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ACCOUNT_KEY_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object accountKey_ = "";

    /**
     * <code>string account_key = 2;</code>
     *
     * @return The accountKey.
     */
    @java.lang.Override
    public java.lang.String getAccountKey() {
        java.lang.Object ref = accountKey_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            accountKey_ = s;
            return s;
        }
    }

    /**
     * <code>string account_key = 2;</code>
     *
     * @return The bytes for accountKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccountKeyBytes() {
        java.lang.Object ref = accountKey_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            accountKey_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int CONTAINER_NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object containerName_ = "";

    /**
     * <code>string container_name = 3;</code>
     *
     * @return The containerName.
     */
    @java.lang.Override
    public java.lang.String getContainerName() {
        java.lang.Object ref = containerName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            containerName_ = s;
            return s;
        }
    }

    /**
     * <code>string container_name = 3;</code>
     *
     * @return The bytes for containerName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getContainerNameBytes() {
        java.lang.Object ref = containerName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            containerName_ = b;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accountName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, accountName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accountKey_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, accountKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(containerName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, containerName_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accountName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, accountName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accountKey_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, accountKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(containerName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, containerName_);
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
        if (!(obj instanceof AzureBlobUpload other)) {
            return super.equals(obj);
        }

        if (!getAccountName().equals(other.getAccountName())) {
            return false;
        }
        if (!getAccountKey().equals(other.getAccountKey())) {
            return false;
        }
        if (!getContainerName().equals(other.getContainerName())) {
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
        hash = (37 * hash) + ACCOUNT_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getAccountName().hashCode();
        hash = (37 * hash) + ACCOUNT_KEY_FIELD_NUMBER;
        hash = (53 * hash) + getAccountKey().hashCode();
        hash = (37 * hash) + CONTAINER_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getContainerName().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload prototype) {
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
     * Protobuf type {@code livekit.AzureBlobUpload}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.AzureBlobUpload)
            im.turms.plugin.livekit.core.proto.egress.AzureBlobUploadOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AzureBlobUpload_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AzureBlobUpload_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.class,
                            im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            accountName_ = "";
            accountKey_ = "";
            containerName_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AzureBlobUpload_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload build() {
            im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload result =
                    new im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.accountName_ = accountName_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.accountKey_ = accountKey_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.containerName_ = containerName_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getAccountName()
                    .isEmpty()) {
                accountName_ = other.accountName_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getAccountKey()
                    .isEmpty()) {
                accountKey_ = other.accountKey_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getContainerName()
                    .isEmpty()) {
                containerName_ = other.containerName_;
                bitField0_ |= 0x00000004;
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
                            accountName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            accountKey_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            containerName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
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

        private java.lang.Object accountName_ = "";

        /**
         * <code>string account_name = 1;</code>
         *
         * @return The accountName.
         */
        public java.lang.String getAccountName() {
            java.lang.Object ref = accountName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                accountName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string account_name = 1;</code>
         *
         * @return The bytes for accountName.
         */
        public com.google.protobuf.ByteString getAccountNameBytes() {
            java.lang.Object ref = accountName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                accountName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string account_name = 1;</code>
         *
         * @param value The accountName to set.
         * @return This builder for chaining.
         */
        public Builder setAccountName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            accountName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string account_name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAccountName() {
            accountName_ = getDefaultInstance().getAccountName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string account_name = 1;</code>
         *
         * @param value The bytes for accountName to set.
         * @return This builder for chaining.
         */
        public Builder setAccountNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            accountName_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object accountKey_ = "";

        /**
         * <code>string account_key = 2;</code>
         *
         * @return The accountKey.
         */
        public java.lang.String getAccountKey() {
            java.lang.Object ref = accountKey_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                accountKey_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string account_key = 2;</code>
         *
         * @return The bytes for accountKey.
         */
        public com.google.protobuf.ByteString getAccountKeyBytes() {
            java.lang.Object ref = accountKey_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                accountKey_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string account_key = 2;</code>
         *
         * @param value The accountKey to set.
         * @return This builder for chaining.
         */
        public Builder setAccountKey(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            accountKey_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string account_key = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAccountKey() {
            accountKey_ = getDefaultInstance().getAccountKey();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string account_key = 2;</code>
         *
         * @param value The bytes for accountKey to set.
         * @return This builder for chaining.
         */
        public Builder setAccountKeyBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            accountKey_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object containerName_ = "";

        /**
         * <code>string container_name = 3;</code>
         *
         * @return The containerName.
         */
        public java.lang.String getContainerName() {
            java.lang.Object ref = containerName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                containerName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string container_name = 3;</code>
         *
         * @return The bytes for containerName.
         */
        public com.google.protobuf.ByteString getContainerNameBytes() {
            java.lang.Object ref = containerName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                containerName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string container_name = 3;</code>
         *
         * @param value The containerName to set.
         * @return This builder for chaining.
         */
        public Builder setContainerName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            containerName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string container_name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContainerName() {
            containerName_ = getDefaultInstance().getContainerName();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string container_name = 3;</code>
         *
         * @param value The bytes for containerName to set.
         * @return This builder for chaining.
         */
        public Builder setContainerNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            containerName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.AzureBlobUpload)
    }

    // @@protoc_insertion_point(class_scope:livekit.AzureBlobUpload)
    private static final im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload();
    }

    public static im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AzureBlobUpload> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public AzureBlobUpload parsePartialFrom(
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

    public static com.google.protobuf.Parser<AzureBlobUpload> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AzureBlobUpload> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AzureBlobUpload getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}