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
 * Protobuf type {@code livekit.GCPUpload}
 */
public final class GCPUpload extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.GCPUpload)
        GCPUploadOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                GCPUpload.class.getName());
    }

    // Use GCPUpload.newBuilder() to construct.
    private GCPUpload(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private GCPUpload() {
        credentials_ = "";
        bucket_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_GCPUpload_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_GCPUpload_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.GCPUpload.class,
                        im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder.class);
    }

    private int bitField0_;
    public static final int CREDENTIALS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object credentials_ = "";

    /**
     * <pre>
     * service account credentials serialized in JSON "credentials.json"
     * </pre>
     *
     * <code>string credentials = 1;</code>
     *
     * @return The credentials.
     */
    @java.lang.Override
    public java.lang.String getCredentials() {
        java.lang.Object ref = credentials_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            credentials_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * service account credentials serialized in JSON "credentials.json"
     * </pre>
     *
     * <code>string credentials = 1;</code>
     *
     * @return The bytes for credentials.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getCredentialsBytes() {
        java.lang.Object ref = credentials_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            credentials_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int BUCKET_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object bucket_ = "";

    /**
     * <code>string bucket = 2;</code>
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
     * <code>string bucket = 2;</code>
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

    public static final int PROXY_FIELD_NUMBER = 3;
    private im.turms.plugin.livekit.core.proto.egress.ProxyConfig proxy_;

    /**
     * <code>.livekit.ProxyConfig proxy = 3;</code>
     *
     * @return Whether the proxy field is set.
     */
    @java.lang.Override
    public boolean hasProxy() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.ProxyConfig proxy = 3;</code>
     *
     * @return The proxy.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ProxyConfig getProxy() {
        return proxy_ == null
                ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                : proxy_;
    }

    /**
     * <code>.livekit.ProxyConfig proxy = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder getProxyOrBuilder() {
        return proxy_ == null
                ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                : proxy_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(credentials_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, credentials_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(bucket_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, bucket_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(3, getProxy());
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(credentials_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, credentials_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(bucket_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, bucket_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, getProxy());
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
        if (!(obj instanceof GCPUpload other)) {
            return super.equals(obj);
        }

        if (!getCredentials().equals(other.getCredentials())) {
            return false;
        }
        if (!getBucket().equals(other.getBucket())) {
            return false;
        }
        if (hasProxy() != other.hasProxy()) {
            return false;
        }
        if (hasProxy()) {
            if (!getProxy().equals(other.getProxy())) {
                return false;
            }
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
        hash = (37 * hash) + CREDENTIALS_FIELD_NUMBER;
        hash = (53 * hash) + getCredentials().hashCode();
        hash = (37 * hash) + BUCKET_FIELD_NUMBER;
        hash = (53 * hash) + getBucket().hashCode();
        if (hasProxy()) {
            hash = (37 * hash) + PROXY_FIELD_NUMBER;
            hash = (53 * hash) + getProxy().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.GCPUpload prototype) {
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
     * Protobuf type {@code livekit.GCPUpload}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.GCPUpload)
            im.turms.plugin.livekit.core.proto.egress.GCPUploadOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_GCPUpload_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_GCPUpload_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.GCPUpload.class,
                            im.turms.plugin.livekit.core.proto.egress.GCPUpload.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.GCPUpload.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getProxyFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            credentials_ = "";
            bucket_ = "";
            proxy_ = null;
            if (proxyBuilder_ != null) {
                proxyBuilder_.dispose();
                proxyBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_GCPUpload_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload build() {
            im.turms.plugin.livekit.core.proto.egress.GCPUpload result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.GCPUpload buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.GCPUpload result =
                    new im.turms.plugin.livekit.core.proto.egress.GCPUpload(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.GCPUpload result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.credentials_ = credentials_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.bucket_ = bucket_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.proxy_ = proxyBuilder_ == null
                        ? proxy_
                        : proxyBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.GCPUpload) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.GCPUpload) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.GCPUpload other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.GCPUpload.getDefaultInstance()) {
                return this;
            }
            if (!other.getCredentials()
                    .isEmpty()) {
                credentials_ = other.credentials_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getBucket()
                    .isEmpty()) {
                bucket_ = other.bucket_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.hasProxy()) {
                mergeProxy(other.getProxy());
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
                            credentials_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            bucket_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getProxyFieldBuilder().getBuilder(),
                                    extensionRegistry);
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

        private java.lang.Object credentials_ = "";

        /**
         * <pre>
         * service account credentials serialized in JSON "credentials.json"
         * </pre>
         *
         * <code>string credentials = 1;</code>
         *
         * @return The credentials.
         */
        public java.lang.String getCredentials() {
            java.lang.Object ref = credentials_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                credentials_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * service account credentials serialized in JSON "credentials.json"
         * </pre>
         *
         * <code>string credentials = 1;</code>
         *
         * @return The bytes for credentials.
         */
        public com.google.protobuf.ByteString getCredentialsBytes() {
            java.lang.Object ref = credentials_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                credentials_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * service account credentials serialized in JSON "credentials.json"
         * </pre>
         *
         * <code>string credentials = 1;</code>
         *
         * @param value The credentials to set.
         * @return This builder for chaining.
         */
        public Builder setCredentials(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            credentials_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * service account credentials serialized in JSON "credentials.json"
         * </pre>
         *
         * <code>string credentials = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCredentials() {
            credentials_ = getDefaultInstance().getCredentials();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * service account credentials serialized in JSON "credentials.json"
         * </pre>
         *
         * <code>string credentials = 1;</code>
         *
         * @param value The bytes for credentials to set.
         * @return This builder for chaining.
         */
        public Builder setCredentialsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            credentials_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object bucket_ = "";

        /**
         * <code>string bucket = 2;</code>
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
         * <code>string bucket = 2;</code>
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
         * <code>string bucket = 2;</code>
         *
         * @param value The bucket to set.
         * @return This builder for chaining.
         */
        public Builder setBucket(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bucket_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string bucket = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBucket() {
            bucket_ = getDefaultInstance().getBucket();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string bucket = 2;</code>
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
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.egress.ProxyConfig proxy_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ProxyConfig, im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder, im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder> proxyBuilder_;

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         *
         * @return Whether the proxy field is set.
         */
        public boolean hasProxy() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         *
         * @return The proxy.
         */
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig getProxy() {
            if (proxyBuilder_ == null) {
                return proxy_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                        : proxy_;
            } else {
                return proxyBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        public Builder setProxy(im.turms.plugin.livekit.core.proto.egress.ProxyConfig value) {
            if (proxyBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                proxy_ = value;
            } else {
                proxyBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        public Builder setProxy(
                im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder builderForValue) {
            if (proxyBuilder_ == null) {
                proxy_ = builderForValue.build();
            } else {
                proxyBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        public Builder mergeProxy(im.turms.plugin.livekit.core.proto.egress.ProxyConfig value) {
            if (proxyBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)
                        && proxy_ != null
                        && proxy_ != im.turms.plugin.livekit.core.proto.egress.ProxyConfig
                                .getDefaultInstance()) {
                    getProxyBuilder().mergeFrom(value);
                } else {
                    proxy_ = value;
                }
            } else {
                proxyBuilder_.mergeFrom(value);
            }
            if (proxy_ != null) {
                bitField0_ |= 0x00000004;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        public Builder clearProxy() {
            bitField0_ &= ~0x00000004;
            proxy_ = null;
            if (proxyBuilder_ != null) {
                proxyBuilder_.dispose();
                proxyBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder getProxyBuilder() {
            bitField0_ |= 0x00000004;
            onChanged();
            return getProxyFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder getProxyOrBuilder() {
            if (proxyBuilder_ != null) {
                return proxyBuilder_.getMessageOrBuilder();
            } else {
                return proxy_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance()
                        : proxy_;
            }
        }

        /**
         * <code>.livekit.ProxyConfig proxy = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.ProxyConfig, im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder, im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder> getProxyFieldBuilder() {
            if (proxyBuilder_ == null) {
                proxyBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getProxy(),
                        getParentForChildren(),
                        isClean());
                proxy_ = null;
            }
            return proxyBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.GCPUpload)
    }

    // @@protoc_insertion_point(class_scope:livekit.GCPUpload)
    private static final im.turms.plugin.livekit.core.proto.egress.GCPUpload DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.GCPUpload();
    }

    public static im.turms.plugin.livekit.core.proto.egress.GCPUpload getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GCPUpload> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GCPUpload parsePartialFrom(
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

    public static com.google.protobuf.Parser<GCPUpload> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GCPUpload> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.GCPUpload getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}