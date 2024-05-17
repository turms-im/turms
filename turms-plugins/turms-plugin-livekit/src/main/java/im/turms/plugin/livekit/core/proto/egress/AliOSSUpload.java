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
 * Protobuf type {@code livekit.AliOSSUpload}
 */
public final class AliOSSUpload extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.AliOSSUpload)
        AliOSSUploadOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                AliOSSUpload.class.getName());
    }

    // Use AliOSSUpload.newBuilder() to construct.
    private AliOSSUpload(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private AliOSSUpload() {
        accessKey_ = "";
        secret_ = "";
        region_ = "";
        endpoint_ = "";
        bucket_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AliOSSUpload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AliOSSUpload_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.class,
                        im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder.class);
    }

    public static final int ACCESS_KEY_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object accessKey_ = "";

    /**
     * <code>string access_key = 1;</code>
     *
     * @return The accessKey.
     */
    @java.lang.Override
    public java.lang.String getAccessKey() {
        java.lang.Object ref = accessKey_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            accessKey_ = s;
            return s;
        }
    }

    /**
     * <code>string access_key = 1;</code>
     *
     * @return The bytes for accessKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccessKeyBytes() {
        java.lang.Object ref = accessKey_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            accessKey_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SECRET_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object secret_ = "";

    /**
     * <code>string secret = 2;</code>
     *
     * @return The secret.
     */
    @java.lang.Override
    public java.lang.String getSecret() {
        java.lang.Object ref = secret_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            secret_ = s;
            return s;
        }
    }

    /**
     * <code>string secret = 2;</code>
     *
     * @return The bytes for secret.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSecretBytes() {
        java.lang.Object ref = secret_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            secret_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int REGION_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object region_ = "";

    /**
     * <code>string region = 3;</code>
     *
     * @return The region.
     */
    @java.lang.Override
    public java.lang.String getRegion() {
        java.lang.Object ref = region_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            region_ = s;
            return s;
        }
    }

    /**
     * <code>string region = 3;</code>
     *
     * @return The bytes for region.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRegionBytes() {
        java.lang.Object ref = region_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            region_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ENDPOINT_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object endpoint_ = "";

    /**
     * <code>string endpoint = 4;</code>
     *
     * @return The endpoint.
     */
    @java.lang.Override
    public java.lang.String getEndpoint() {
        java.lang.Object ref = endpoint_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            endpoint_ = s;
            return s;
        }
    }

    /**
     * <code>string endpoint = 4;</code>
     *
     * @return The bytes for endpoint.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEndpointBytes() {
        java.lang.Object ref = endpoint_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            endpoint_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int BUCKET_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object bucket_ = "";

    /**
     * <code>string bucket = 5;</code>
     *
     * @return The bucket.
     */
    @java.lang.Override
    public java.lang.String getBucket() {
        java.lang.Object ref = bucket_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            bucket_ = s;
            return s;
        }
    }

    /**
     * <code>string bucket = 5;</code>
     *
     * @return The bytes for bucket.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getBucketBytes() {
        java.lang.Object ref = bucket_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            bucket_ = b;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accessKey_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, accessKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(secret_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, secret_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, region_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(endpoint_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, endpoint_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(bucket_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, bucket_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(accessKey_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, accessKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(secret_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, secret_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(region_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, region_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(endpoint_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, endpoint_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(bucket_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, bucket_);
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
        if (!(obj instanceof AliOSSUpload other)) {
            return super.equals(obj);
        }

        if (!getAccessKey().equals(other.getAccessKey())) {
            return false;
        }
        if (!getSecret().equals(other.getSecret())) {
            return false;
        }
        if (!getRegion().equals(other.getRegion())) {
            return false;
        }
        if (!getEndpoint().equals(other.getEndpoint())) {
            return false;
        }
        if (!getBucket().equals(other.getBucket())) {
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
        hash = (37 * hash) + ACCESS_KEY_FIELD_NUMBER;
        hash = (53 * hash) + getAccessKey().hashCode();
        hash = (37 * hash) + SECRET_FIELD_NUMBER;
        hash = (53 * hash) + getSecret().hashCode();
        hash = (37 * hash) + REGION_FIELD_NUMBER;
        hash = (53 * hash) + getRegion().hashCode();
        hash = (37 * hash) + ENDPOINT_FIELD_NUMBER;
        hash = (53 * hash) + getEndpoint().hashCode();
        hash = (37 * hash) + BUCKET_FIELD_NUMBER;
        hash = (53 * hash) + getBucket().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.AliOSSUpload prototype) {
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
     * Protobuf type {@code livekit.AliOSSUpload}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.AliOSSUpload)
            im.turms.plugin.livekit.core.proto.egress.AliOSSUploadOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AliOSSUpload_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AliOSSUpload_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.class,
                            im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            accessKey_ = "";
            secret_ = "";
            region_ = "";
            endpoint_ = "";
            bucket_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_AliOSSUpload_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.AliOSSUpload.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload build() {
            im.turms.plugin.livekit.core.proto.egress.AliOSSUpload result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.AliOSSUpload result =
                    new im.turms.plugin.livekit.core.proto.egress.AliOSSUpload(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.AliOSSUpload result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.accessKey_ = accessKey_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.secret_ = secret_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.region_ = region_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.endpoint_ = endpoint_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.bucket_ = bucket_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.AliOSSUpload) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.AliOSSUpload other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.AliOSSUpload
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getAccessKey()
                    .isEmpty()) {
                accessKey_ = other.accessKey_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getSecret()
                    .isEmpty()) {
                secret_ = other.secret_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getRegion()
                    .isEmpty()) {
                region_ = other.region_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (!other.getEndpoint()
                    .isEmpty()) {
                endpoint_ = other.endpoint_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (!other.getBucket()
                    .isEmpty()) {
                bucket_ = other.bucket_;
                bitField0_ |= 0x00000010;
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
                            accessKey_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            secret_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            region_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            endpoint_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            bucket_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
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

        private java.lang.Object accessKey_ = "";

        /**
         * <code>string access_key = 1;</code>
         *
         * @return The accessKey.
         */
        public java.lang.String getAccessKey() {
            java.lang.Object ref = accessKey_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                accessKey_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @return The bytes for accessKey.
         */
        public com.google.protobuf.ByteString getAccessKeyBytes() {
            java.lang.Object ref = accessKey_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                accessKey_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @param value The accessKey to set.
         * @return This builder for chaining.
         */
        public Builder setAccessKey(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            accessKey_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAccessKey() {
            accessKey_ = getDefaultInstance().getAccessKey();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string access_key = 1;</code>
         *
         * @param value The bytes for accessKey to set.
         * @return This builder for chaining.
         */
        public Builder setAccessKeyBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            accessKey_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object secret_ = "";

        /**
         * <code>string secret = 2;</code>
         *
         * @return The secret.
         */
        public java.lang.String getSecret() {
            java.lang.Object ref = secret_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                secret_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @return The bytes for secret.
         */
        public com.google.protobuf.ByteString getSecretBytes() {
            java.lang.Object ref = secret_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                secret_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @param value The secret to set.
         * @return This builder for chaining.
         */
        public Builder setSecret(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            secret_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSecret() {
            secret_ = getDefaultInstance().getSecret();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string secret = 2;</code>
         *
         * @param value The bytes for secret to set.
         * @return This builder for chaining.
         */
        public Builder setSecretBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            secret_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object region_ = "";

        /**
         * <code>string region = 3;</code>
         *
         * @return The region.
         */
        public java.lang.String getRegion() {
            java.lang.Object ref = region_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                region_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string region = 3;</code>
         *
         * @return The bytes for region.
         */
        public com.google.protobuf.ByteString getRegionBytes() {
            java.lang.Object ref = region_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                region_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string region = 3;</code>
         *
         * @param value The region to set.
         * @return This builder for chaining.
         */
        public Builder setRegion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            region_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRegion() {
            region_ = getDefaultInstance().getRegion();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string region = 3;</code>
         *
         * @param value The bytes for region to set.
         * @return This builder for chaining.
         */
        public Builder setRegionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            region_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object endpoint_ = "";

        /**
         * <code>string endpoint = 4;</code>
         *
         * @return The endpoint.
         */
        public java.lang.String getEndpoint() {
            java.lang.Object ref = endpoint_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                endpoint_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @return The bytes for endpoint.
         */
        public com.google.protobuf.ByteString getEndpointBytes() {
            java.lang.Object ref = endpoint_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                endpoint_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @param value The endpoint to set.
         * @return This builder for chaining.
         */
        public Builder setEndpoint(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            endpoint_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndpoint() {
            endpoint_ = getDefaultInstance().getEndpoint();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string endpoint = 4;</code>
         *
         * @param value The bytes for endpoint to set.
         * @return This builder for chaining.
         */
        public Builder setEndpointBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            endpoint_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private java.lang.Object bucket_ = "";

        /**
         * <code>string bucket = 5;</code>
         *
         * @return The bucket.
         */
        public java.lang.String getBucket() {
            java.lang.Object ref = bucket_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                bucket_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @return The bytes for bucket.
         */
        public com.google.protobuf.ByteString getBucketBytes() {
            java.lang.Object ref = bucket_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                bucket_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @param value The bucket to set.
         * @return This builder for chaining.
         */
        public Builder setBucket(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bucket_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBucket() {
            bucket_ = getDefaultInstance().getBucket();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string bucket = 5;</code>
         *
         * @param value The bytes for bucket to set.
         * @return This builder for chaining.
         */
        public Builder setBucketBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            bucket_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.AliOSSUpload)
    }

    // @@protoc_insertion_point(class_scope:livekit.AliOSSUpload)
    private static final im.turms.plugin.livekit.core.proto.egress.AliOSSUpload DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.AliOSSUpload();
    }

    public static im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AliOSSUpload> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public AliOSSUpload parsePartialFrom(
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

    public static com.google.protobuf.Parser<AliOSSUpload> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AliOSSUpload> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AliOSSUpload getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}