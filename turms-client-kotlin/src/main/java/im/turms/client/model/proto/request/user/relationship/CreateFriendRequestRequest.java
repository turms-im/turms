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

package im.turms.client.model.proto.request.user.relationship;

/**
 * Protobuf type {@code im.turms.proto.CreateFriendRequestRequest}
 */
public final class CreateFriendRequestRequest extends
        com.google.protobuf.GeneratedMessageLite<CreateFriendRequestRequest, CreateFriendRequestRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateFriendRequestRequest)
        CreateFriendRequestRequestOrBuilder {
    private CreateFriendRequestRequest() {
        content_ = "";
    }

    public static final int RECIPIENT_ID_FIELD_NUMBER = 1;
    private long recipientId_;

    /**
     * <code>int64 recipient_id = 1;</code>
     *
     * @return The recipientId.
     */
    @java.lang.Override
    public long getRecipientId() {
        return recipientId_;
    }

    /**
     * <code>int64 recipient_id = 1;</code>
     *
     * @param value The recipientId to set.
     */
    private void setRecipientId(long value) {

        recipientId_ = value;
    }

    /**
     * <code>int64 recipient_id = 1;</code>
     */
    private void clearRecipientId() {

        recipientId_ = 0L;
    }

    public static final int CONTENT_FIELD_NUMBER = 2;
    private java.lang.String content_;

    /**
     * <code>string content = 2;</code>
     *
     * @return The content.
     */
    @java.lang.Override
    public java.lang.String getContent() {
        return content_;
    }

    /**
     * <code>string content = 2;</code>
     *
     * @return The bytes for content.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getContentBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(content_);
    }

    /**
     * <code>string content = 2;</code>
     *
     * @param value The content to set.
     */
    private void setContent(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();

        content_ = value;
    }

    /**
     * <code>string content = 2;</code>
     */
    private void clearContent() {

        content_ = getDefaultInstance().getContent();
    }

    /**
     * <code>string content = 2;</code>
     *
     * @param value The bytes for content to set.
     */
    private void setContentBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        content_ = value.toStringUtf8();

    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.CreateFriendRequestRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateFriendRequestRequest)
            im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 recipient_id = 1;</code>
         *
         * @return The recipientId.
         */
        @java.lang.Override
        public long getRecipientId() {
            return instance.getRecipientId();
        }

        /**
         * <code>int64 recipient_id = 1;</code>
         *
         * @param value The recipientId to set.
         * @return This builder for chaining.
         */
        public Builder setRecipientId(long value) {
            copyOnWrite();
            instance.setRecipientId(value);
            return this;
        }

        /**
         * <code>int64 recipient_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecipientId() {
            copyOnWrite();
            instance.clearRecipientId();
            return this;
        }

        /**
         * <code>string content = 2;</code>
         *
         * @return The content.
         */
        @java.lang.Override
        public java.lang.String getContent() {
            return instance.getContent();
        }

        /**
         * <code>string content = 2;</code>
         *
         * @return The bytes for content.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getContentBytes() {
            return instance.getContentBytes();
        }

        /**
         * <code>string content = 2;</code>
         *
         * @param value The content to set.
         * @return This builder for chaining.
         */
        public Builder setContent(java.lang.String value) {
            copyOnWrite();
            instance.setContent(value);
            return this;
        }

        /**
         * <code>string content = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContent() {
            copyOnWrite();
            instance.clearContent();
            return this;
        }

        /**
         * <code>string content = 2;</code>
         *
         * @param value The bytes for content to set.
         * @return This builder for chaining.
         */
        public Builder setContentBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setContentBytes(value);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateFriendRequestRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"recipientId_", "content_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0208"
                                + "";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateFriendRequestRequest)
    private static final im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest DEFAULT_INSTANCE;

    static {
        CreateFriendRequestRequest defaultInstance = new CreateFriendRequestRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(CreateFriendRequestRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.relationship.CreateFriendRequestRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CreateFriendRequestRequest> PARSER;

    public static com.google.protobuf.Parser<CreateFriendRequestRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}