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

package im.turms.client.model.proto.request.storage;

/**
 * Protobuf type {@code im.turms.proto.UpdateMessageAttachmentInfoRequest}
 */
public final class UpdateMessageAttachmentInfoRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateMessageAttachmentInfoRequest, UpdateMessageAttachmentInfoRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateMessageAttachmentInfoRequest)
        UpdateMessageAttachmentInfoRequestOrBuilder {
    private UpdateMessageAttachmentInfoRequest() {
        attachmentIdStr_ = "";
    }

    private int bitField0_;
    public static final int ATTACHMENT_ID_NUM_FIELD_NUMBER = 1;
    private long attachmentIdNum_;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 attachment_id_num = 1;</code>
     *
     * @return Whether the attachmentIdNum field is set.
     */
    @java.lang.Override
    public boolean hasAttachmentIdNum() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 attachment_id_num = 1;</code>
     *
     * @return The attachmentIdNum.
     */
    @java.lang.Override
    public long getAttachmentIdNum() {
        return attachmentIdNum_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 attachment_id_num = 1;</code>
     *
     * @param value The attachmentIdNum to set.
     */
    private void setAttachmentIdNum(long value) {
        bitField0_ |= 0x00000001;
        attachmentIdNum_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 attachment_id_num = 1;</code>
     */
    private void clearAttachmentIdNum() {
        bitField0_ &= ~0x00000001;
        attachmentIdNum_ = 0L;
    }

    public static final int ATTACHMENT_ID_STR_FIELD_NUMBER = 2;
    private java.lang.String attachmentIdStr_;

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return Whether the attachmentIdStr field is set.
     */
    @java.lang.Override
    public boolean hasAttachmentIdStr() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return The attachmentIdStr.
     */
    @java.lang.Override
    public java.lang.String getAttachmentIdStr() {
        return attachmentIdStr_;
    }

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return The bytes for attachmentIdStr.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAttachmentIdStrBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(attachmentIdStr_);
    }

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @param value The attachmentIdStr to set.
     */
    private void setAttachmentIdStr(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        attachmentIdStr_ = value;
    }

    /**
     * <code>optional string attachment_id_str = 2;</code>
     */
    private void clearAttachmentIdStr() {
        bitField0_ &= ~0x00000002;
        attachmentIdStr_ = getDefaultInstance().getAttachmentIdStr();
    }

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @param value The bytes for attachmentIdStr to set.
     */
    private void setAttachmentIdStrBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        attachmentIdStr_ = value.toStringUtf8();
        bitField0_ |= 0x00000002;
    }

    public static final int USER_ID_TO_SHARE_WITH_FIELD_NUMBER = 3;
    private long userIdToShareWith_;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional int64 user_id_to_share_with = 3;</code>
     *
     * @return Whether the userIdToShareWith field is set.
     */
    @java.lang.Override
    public boolean hasUserIdToShareWith() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional int64 user_id_to_share_with = 3;</code>
     *
     * @return The userIdToShareWith.
     */
    @java.lang.Override
    public long getUserIdToShareWith() {
        return userIdToShareWith_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional int64 user_id_to_share_with = 3;</code>
     *
     * @param value The userIdToShareWith to set.
     */
    private void setUserIdToShareWith(long value) {
        bitField0_ |= 0x00000004;
        userIdToShareWith_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional int64 user_id_to_share_with = 3;</code>
     */
    private void clearUserIdToShareWith() {
        bitField0_ &= ~0x00000004;
        userIdToShareWith_ = 0L;
    }

    public static final int USER_ID_TO_UNSHARE_WITH_FIELD_NUMBER = 4;
    private long userIdToUnshareWith_;

    /**
     * <code>optional int64 user_id_to_unshare_with = 4;</code>
     *
     * @return Whether the userIdToUnshareWith field is set.
     */
    @java.lang.Override
    public boolean hasUserIdToUnshareWith() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int64 user_id_to_unshare_with = 4;</code>
     *
     * @return The userIdToUnshareWith.
     */
    @java.lang.Override
    public long getUserIdToUnshareWith() {
        return userIdToUnshareWith_;
    }

    /**
     * <code>optional int64 user_id_to_unshare_with = 4;</code>
     *
     * @param value The userIdToUnshareWith to set.
     */
    private void setUserIdToUnshareWith(long value) {
        bitField0_ |= 0x00000008;
        userIdToUnshareWith_ = value;
    }

    /**
     * <code>optional int64 user_id_to_unshare_with = 4;</code>
     */
    private void clearUserIdToUnshareWith() {
        bitField0_ &= ~0x00000008;
        userIdToUnshareWith_ = 0L;
    }

    public static final int GROUP_ID_TO_SHARE_WITH_FIELD_NUMBER = 5;
    private long groupIdToShareWith_;

    /**
     * <code>optional int64 group_id_to_share_with = 5;</code>
     *
     * @return Whether the groupIdToShareWith field is set.
     */
    @java.lang.Override
    public boolean hasGroupIdToShareWith() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 group_id_to_share_with = 5;</code>
     *
     * @return The groupIdToShareWith.
     */
    @java.lang.Override
    public long getGroupIdToShareWith() {
        return groupIdToShareWith_;
    }

    /**
     * <code>optional int64 group_id_to_share_with = 5;</code>
     *
     * @param value The groupIdToShareWith to set.
     */
    private void setGroupIdToShareWith(long value) {
        bitField0_ |= 0x00000010;
        groupIdToShareWith_ = value;
    }

    /**
     * <code>optional int64 group_id_to_share_with = 5;</code>
     */
    private void clearGroupIdToShareWith() {
        bitField0_ &= ~0x00000010;
        groupIdToShareWith_ = 0L;
    }

    public static final int GROUP_ID_TO_UNSHARE_WITH_FIELD_NUMBER = 6;
    private long groupIdToUnshareWith_;

    /**
     * <code>optional int64 group_id_to_unshare_with = 6;</code>
     *
     * @return Whether the groupIdToUnshareWith field is set.
     */
    @java.lang.Override
    public boolean hasGroupIdToUnshareWith() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional int64 group_id_to_unshare_with = 6;</code>
     *
     * @return The groupIdToUnshareWith.
     */
    @java.lang.Override
    public long getGroupIdToUnshareWith() {
        return groupIdToUnshareWith_;
    }

    /**
     * <code>optional int64 group_id_to_unshare_with = 6;</code>
     *
     * @param value The groupIdToUnshareWith to set.
     */
    private void setGroupIdToUnshareWith(long value) {
        bitField0_ |= 0x00000020;
        groupIdToUnshareWith_ = value;
    }

    /**
     * <code>optional int64 group_id_to_unshare_with = 6;</code>
     */
    private void clearGroupIdToUnshareWith() {
        bitField0_ &= ~0x00000020;
        groupIdToUnshareWith_ = 0L;
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
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
            im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateMessageAttachmentInfoRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateMessageAttachmentInfoRequest)
            im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 attachment_id_num = 1;</code>
         *
         * @return Whether the attachmentIdNum field is set.
         */
        @java.lang.Override
        public boolean hasAttachmentIdNum() {
            return instance.hasAttachmentIdNum();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 attachment_id_num = 1;</code>
         *
         * @return The attachmentIdNum.
         */
        @java.lang.Override
        public long getAttachmentIdNum() {
            return instance.getAttachmentIdNum();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 attachment_id_num = 1;</code>
         *
         * @param value The attachmentIdNum to set.
         * @return This builder for chaining.
         */
        public Builder setAttachmentIdNum(long value) {
            copyOnWrite();
            instance.setAttachmentIdNum(value);
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 attachment_id_num = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAttachmentIdNum() {
            copyOnWrite();
            instance.clearAttachmentIdNum();
            return this;
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return Whether the attachmentIdStr field is set.
         */
        @java.lang.Override
        public boolean hasAttachmentIdStr() {
            return instance.hasAttachmentIdStr();
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return The attachmentIdStr.
         */
        @java.lang.Override
        public java.lang.String getAttachmentIdStr() {
            return instance.getAttachmentIdStr();
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return The bytes for attachmentIdStr.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getAttachmentIdStrBytes() {
            return instance.getAttachmentIdStrBytes();
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @param value The attachmentIdStr to set.
         * @return This builder for chaining.
         */
        public Builder setAttachmentIdStr(java.lang.String value) {
            copyOnWrite();
            instance.setAttachmentIdStr(value);
            return this;
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAttachmentIdStr() {
            copyOnWrite();
            instance.clearAttachmentIdStr();
            return this;
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @param value The bytes for attachmentIdStr to set.
         * @return This builder for chaining.
         */
        public Builder setAttachmentIdStrBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setAttachmentIdStrBytes(value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional int64 user_id_to_share_with = 3;</code>
         *
         * @return Whether the userIdToShareWith field is set.
         */
        @java.lang.Override
        public boolean hasUserIdToShareWith() {
            return instance.hasUserIdToShareWith();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional int64 user_id_to_share_with = 3;</code>
         *
         * @return The userIdToShareWith.
         */
        @java.lang.Override
        public long getUserIdToShareWith() {
            return instance.getUserIdToShareWith();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional int64 user_id_to_share_with = 3;</code>
         *
         * @param value The userIdToShareWith to set.
         * @return This builder for chaining.
         */
        public Builder setUserIdToShareWith(long value) {
            copyOnWrite();
            instance.setUserIdToShareWith(value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional int64 user_id_to_share_with = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIdToShareWith() {
            copyOnWrite();
            instance.clearUserIdToShareWith();
            return this;
        }

        /**
         * <code>optional int64 user_id_to_unshare_with = 4;</code>
         *
         * @return Whether the userIdToUnshareWith field is set.
         */
        @java.lang.Override
        public boolean hasUserIdToUnshareWith() {
            return instance.hasUserIdToUnshareWith();
        }

        /**
         * <code>optional int64 user_id_to_unshare_with = 4;</code>
         *
         * @return The userIdToUnshareWith.
         */
        @java.lang.Override
        public long getUserIdToUnshareWith() {
            return instance.getUserIdToUnshareWith();
        }

        /**
         * <code>optional int64 user_id_to_unshare_with = 4;</code>
         *
         * @param value The userIdToUnshareWith to set.
         * @return This builder for chaining.
         */
        public Builder setUserIdToUnshareWith(long value) {
            copyOnWrite();
            instance.setUserIdToUnshareWith(value);
            return this;
        }

        /**
         * <code>optional int64 user_id_to_unshare_with = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIdToUnshareWith() {
            copyOnWrite();
            instance.clearUserIdToUnshareWith();
            return this;
        }

        /**
         * <code>optional int64 group_id_to_share_with = 5;</code>
         *
         * @return Whether the groupIdToShareWith field is set.
         */
        @java.lang.Override
        public boolean hasGroupIdToShareWith() {
            return instance.hasGroupIdToShareWith();
        }

        /**
         * <code>optional int64 group_id_to_share_with = 5;</code>
         *
         * @return The groupIdToShareWith.
         */
        @java.lang.Override
        public long getGroupIdToShareWith() {
            return instance.getGroupIdToShareWith();
        }

        /**
         * <code>optional int64 group_id_to_share_with = 5;</code>
         *
         * @param value The groupIdToShareWith to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIdToShareWith(long value) {
            copyOnWrite();
            instance.setGroupIdToShareWith(value);
            return this;
        }

        /**
         * <code>optional int64 group_id_to_share_with = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIdToShareWith() {
            copyOnWrite();
            instance.clearGroupIdToShareWith();
            return this;
        }

        /**
         * <code>optional int64 group_id_to_unshare_with = 6;</code>
         *
         * @return Whether the groupIdToUnshareWith field is set.
         */
        @java.lang.Override
        public boolean hasGroupIdToUnshareWith() {
            return instance.hasGroupIdToUnshareWith();
        }

        /**
         * <code>optional int64 group_id_to_unshare_with = 6;</code>
         *
         * @return The groupIdToUnshareWith.
         */
        @java.lang.Override
        public long getGroupIdToUnshareWith() {
            return instance.getGroupIdToUnshareWith();
        }

        /**
         * <code>optional int64 group_id_to_unshare_with = 6;</code>
         *
         * @param value The groupIdToUnshareWith to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIdToUnshareWith(long value) {
            copyOnWrite();
            instance.setGroupIdToUnshareWith(value);
            return this;
        }

        /**
         * <code>optional int64 group_id_to_unshare_with = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIdToUnshareWith() {
            copyOnWrite();
            instance.clearGroupIdToUnshareWith();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateMessageAttachmentInfoRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "attachmentIdNum_",
                        "attachmentIdStr_",
                        "userIdToShareWith_",
                        "userIdToUnshareWith_",
                        "groupIdToShareWith_",
                        "groupIdToUnshareWith_",};
                java.lang.String info =
                        "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u1002\u0000\u0002"
                                + "\u1208\u0001\u0003\u1002\u0002\u0004\u1002\u0003\u0005\u1002\u0004\u0006\u1002\u0005"
                                + "";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateMessageAttachmentInfoRequest)
    private static final im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest DEFAULT_INSTANCE;

    static {
        UpdateMessageAttachmentInfoRequest defaultInstance =
                new UpdateMessageAttachmentInfoRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateMessageAttachmentInfoRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateMessageAttachmentInfoRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateMessageAttachmentInfoRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}