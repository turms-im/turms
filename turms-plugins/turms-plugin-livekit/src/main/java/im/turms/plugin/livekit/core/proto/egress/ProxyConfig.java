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
 * Protobuf type {@code livekit.ProxyConfig}
 */
public final class ProxyConfig extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ProxyConfig)
        ProxyConfigOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ProxyConfig.class.getName());
    }

    // Use ProxyConfig.newBuilder() to construct.
    private ProxyConfig(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ProxyConfig() {
        url_ = "";
        username_ = "";
        password_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ProxyConfig_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ProxyConfig_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.ProxyConfig.class,
                        im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder.class);
    }

    public static final int URL_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object url_ = "";

    /**
     * <code>string url = 1;</code>
     *
     * @return The url.
     */
    @java.lang.Override
    public java.lang.String getUrl() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            url_ = s;
            return s;
        }
    }

    /**
     * <code>string url = 1;</code>
     *
     * @return The bytes for url.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getUrlBytes() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            url_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int USERNAME_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object username_ = "";

    /**
     * <code>string username = 2;</code>
     *
     * @return The username.
     */
    @java.lang.Override
    public java.lang.String getUsername() {
        java.lang.Object ref = username_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            username_ = s;
            return s;
        }
    }

    /**
     * <code>string username = 2;</code>
     *
     * @return The bytes for username.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getUsernameBytes() {
        java.lang.Object ref = username_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            username_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PASSWORD_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object password_ = "";

    /**
     * <code>string password = 3;</code>
     *
     * @return The password.
     */
    @java.lang.Override
    public java.lang.String getPassword() {
        java.lang.Object ref = password_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            password_ = s;
            return s;
        }
    }

    /**
     * <code>string password = 3;</code>
     *
     * @return The bytes for password.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPasswordBytes() {
        java.lang.Object ref = password_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            password_ = b;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, url_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(username_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, username_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(password_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, password_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, url_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(username_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, username_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(password_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, password_);
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
        if (!(obj instanceof ProxyConfig other)) {
            return super.equals(obj);
        }

        if (!getUrl().equals(other.getUrl())) {
            return false;
        }
        if (!getUsername().equals(other.getUsername())) {
            return false;
        }
        if (!getPassword().equals(other.getPassword())) {
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
        hash = (37 * hash) + URL_FIELD_NUMBER;
        hash = (53 * hash) + getUrl().hashCode();
        hash = (37 * hash) + USERNAME_FIELD_NUMBER;
        hash = (53 * hash) + getUsername().hashCode();
        hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
        hash = (53 * hash) + getPassword().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.ProxyConfig prototype) {
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
     * Protobuf type {@code livekit.ProxyConfig}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ProxyConfig)
            im.turms.plugin.livekit.core.proto.egress.ProxyConfigOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ProxyConfig_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ProxyConfig_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.ProxyConfig.class,
                            im.turms.plugin.livekit.core.proto.egress.ProxyConfig.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.ProxyConfig.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            url_ = "";
            username_ = "";
            password_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_ProxyConfig_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.ProxyConfig.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig build() {
            im.turms.plugin.livekit.core.proto.egress.ProxyConfig result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.ProxyConfig buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.ProxyConfig result =
                    new im.turms.plugin.livekit.core.proto.egress.ProxyConfig(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.ProxyConfig result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.url_ = url_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.username_ = username_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.password_ = password_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.ProxyConfig) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.ProxyConfig) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.ProxyConfig other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.ProxyConfig
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getUrl()
                    .isEmpty()) {
                url_ = other.url_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getUsername()
                    .isEmpty()) {
                username_ = other.username_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getPassword()
                    .isEmpty()) {
                password_ = other.password_;
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
                            url_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            username_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            password_ = input.readStringRequireUtf8();
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

        private java.lang.Object url_ = "";

        /**
         * <code>string url = 1;</code>
         *
         * @return The url.
         */
        public java.lang.String getUrl() {
            java.lang.Object ref = url_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                url_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return The bytes for url.
         */
        public com.google.protobuf.ByteString getUrlBytes() {
            java.lang.Object ref = url_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                url_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The url to set.
         * @return This builder for chaining.
         */
        public Builder setUrl(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            url_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUrl() {
            url_ = getDefaultInstance().getUrl();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The bytes for url to set.
         * @return This builder for chaining.
         */
        public Builder setUrlBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            url_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object username_ = "";

        /**
         * <code>string username = 2;</code>
         *
         * @return The username.
         */
        public java.lang.String getUsername() {
            java.lang.Object ref = username_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                username_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string username = 2;</code>
         *
         * @return The bytes for username.
         */
        public com.google.protobuf.ByteString getUsernameBytes() {
            java.lang.Object ref = username_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                username_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string username = 2;</code>
         *
         * @param value The username to set.
         * @return This builder for chaining.
         */
        public Builder setUsername(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            username_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string username = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUsername() {
            username_ = getDefaultInstance().getUsername();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string username = 2;</code>
         *
         * @param value The bytes for username to set.
         * @return This builder for chaining.
         */
        public Builder setUsernameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            username_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object password_ = "";

        /**
         * <code>string password = 3;</code>
         *
         * @return The password.
         */
        public java.lang.String getPassword() {
            java.lang.Object ref = password_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                password_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string password = 3;</code>
         *
         * @return The bytes for password.
         */
        public com.google.protobuf.ByteString getPasswordBytes() {
            java.lang.Object ref = password_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                password_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string password = 3;</code>
         *
         * @param value The password to set.
         * @return This builder for chaining.
         */
        public Builder setPassword(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            password_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string password = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            password_ = getDefaultInstance().getPassword();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string password = 3;</code>
         *
         * @param value The bytes for password to set.
         * @return This builder for chaining.
         */
        public Builder setPasswordBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            password_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ProxyConfig)
    }

    // @@protoc_insertion_point(class_scope:livekit.ProxyConfig)
    private static final im.turms.plugin.livekit.core.proto.egress.ProxyConfig DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.ProxyConfig();
    }

    public static im.turms.plugin.livekit.core.proto.egress.ProxyConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ProxyConfig> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ProxyConfig parsePartialFrom(
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

    public static com.google.protobuf.Parser<ProxyConfig> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ProxyConfig> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.ProxyConfig getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}