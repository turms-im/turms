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

package im.turms.client.model.proto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserSession}
 */
public final class UserSession extends
        com.google.protobuf.GeneratedMessageLite<UserSession, UserSession.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserSession)
        UserSessionOrBuilder {
    private UserSession() {
        sessionId_ = "";
        serverId_ = "";
    }

    public static final int SESSION_ID_FIELD_NUMBER = 1;
    private java.lang.String sessionId_;

    /**
     * <code>string session_id = 1;</code>
     *
     * @return The sessionId.
     */
    @java.lang.Override
    public java.lang.String getSessionId() {
        return sessionId_;
    }

    /**
     * <code>string session_id = 1;</code>
     *
     * @return The bytes for sessionId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSessionIdBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(sessionId_);
    }

    /**
     * <code>string session_id = 1;</code>
     *
     * @param value The sessionId to set.
     */
    private void setSessionId(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();

        sessionId_ = value;
    }

    /**
     * <code>string session_id = 1;</code>
     */
    private void clearSessionId() {

        sessionId_ = getDefaultInstance().getSessionId();
    }

    /**
     * <code>string session_id = 1;</code>
     *
     * @param value The bytes for sessionId to set.
     */
    private void setSessionIdBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        sessionId_ = value.toStringUtf8();

    }

    public static final int SERVER_ID_FIELD_NUMBER = 2;
    private java.lang.String serverId_;

    /**
     * <code>string server_id = 2;</code>
     *
     * @return The serverId.
     */
    @java.lang.Override
    public java.lang.String getServerId() {
        return serverId_;
    }

    /**
     * <code>string server_id = 2;</code>
     *
     * @return The bytes for serverId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getServerIdBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(serverId_);
    }

    /**
     * <code>string server_id = 2;</code>
     *
     * @param value The serverId to set.
     */
    private void setServerId(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();

        serverId_ = value;
    }

    /**
     * <code>string server_id = 2;</code>
     */
    private void clearServerId() {

        serverId_ = getDefaultInstance().getServerId();
    }

    /**
     * <code>string server_id = 2;</code>
     *
     * @param value The bytes for serverId to set.
     */
    private void setServerIdBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        serverId_ = value.toStringUtf8();

    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserSession parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.user.UserSession prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserSession}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.UserSession, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserSession)
            im.turms.client.model.proto.model.user.UserSessionOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.UserSession.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>string session_id = 1;</code>
         *
         * @return The sessionId.
         */
        @java.lang.Override
        public java.lang.String getSessionId() {
            return instance.getSessionId();
        }

        /**
         * <code>string session_id = 1;</code>
         *
         * @return The bytes for sessionId.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getSessionIdBytes() {
            return instance.getSessionIdBytes();
        }

        /**
         * <code>string session_id = 1;</code>
         *
         * @param value The sessionId to set.
         * @return This builder for chaining.
         */
        public Builder setSessionId(java.lang.String value) {
            copyOnWrite();
            instance.setSessionId(value);
            return this;
        }

        /**
         * <code>string session_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSessionId() {
            copyOnWrite();
            instance.clearSessionId();
            return this;
        }

        /**
         * <code>string session_id = 1;</code>
         *
         * @param value The bytes for sessionId to set.
         * @return This builder for chaining.
         */
        public Builder setSessionIdBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setSessionIdBytes(value);
            return this;
        }

        /**
         * <code>string server_id = 2;</code>
         *
         * @return The serverId.
         */
        @java.lang.Override
        public java.lang.String getServerId() {
            return instance.getServerId();
        }

        /**
         * <code>string server_id = 2;</code>
         *
         * @return The bytes for serverId.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getServerIdBytes() {
            return instance.getServerIdBytes();
        }

        /**
         * <code>string server_id = 2;</code>
         *
         * @param value The serverId to set.
         * @return This builder for chaining.
         */
        public Builder setServerId(java.lang.String value) {
            copyOnWrite();
            instance.setServerId(value);
            return this;
        }

        /**
         * <code>string server_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearServerId() {
            copyOnWrite();
            instance.clearServerId();
            return this;
        }

        /**
         * <code>string server_id = 2;</code>
         *
         * @param value The bytes for serverId to set.
         * @return This builder for chaining.
         */
        public Builder setServerIdBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setServerIdBytes(value);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserSession)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.UserSession();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"sessionId_", "serverId_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0208\u0002\u0208"
                                + "";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.UserSession> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.UserSession.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            }
            case GET_MEMOIZED_IS_INITIALIZED: {
                return (byte) 1;
            }
            case SET_MEMOIZED_IS_INITIALIZED: {
                return null;
            }
        }
        throw new UnsupportedOperationException();
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserSession)
    private static final im.turms.client.model.proto.model.user.UserSession DEFAULT_INSTANCE;

    static {
        UserSession defaultInstance = new UserSession();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UserSession.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.UserSession getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserSession> PARSER;

    public static com.google.protobuf.Parser<UserSession> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}