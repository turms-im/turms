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
 * Protobuf type {@code im.turms.proto.QueryMessageAttachmentInfosRequest}
 */
public final class QueryMessageAttachmentInfosRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryMessageAttachmentInfosRequest, QueryMessageAttachmentInfosRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMessageAttachmentInfosRequest)
        QueryMessageAttachmentInfosRequestOrBuilder {
    private QueryMessageAttachmentInfosRequest() {
        userIds_ = emptyLongList();
        groupIds_ = emptyLongList();
    }

    private int bitField0_;
    public static final int USER_IDS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.LongList userIds_;

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return A list containing the userIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getUserIdsList() {
        return userIds_;
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return The count of userIds.
     */
    @java.lang.Override
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    @java.lang.Override
    public long getUserIds(int index) {
        return userIds_.getLong(index);
    }

    private int userIdsMemoizedSerializedSize = -1;

    private void ensureUserIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = userIds_;
        if (!tmp.isModifiable()) {
            userIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param index The index to set the value at.
     * @param value The userIds to set.
     */
    private void setUserIds(int index, long value) {
        ensureUserIdsIsMutable();
        userIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param value The userIds to add.
     */
    private void addUserIds(long value) {
        ensureUserIdsIsMutable();
        userIds_.addLong(value);
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param values The userIds to add.
     */
    private void addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureUserIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, userIds_);
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     */
    private void clearUserIds() {
        userIds_ = emptyLongList();
    }

    public static final int GROUP_IDS_FIELD_NUMBER = 2;
    private com.google.protobuf.Internal.LongList groupIds_;

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return A list containing the groupIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getGroupIdsList() {
        return groupIds_;
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return The count of groupIds.
     */
    @java.lang.Override
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    @java.lang.Override
    public long getGroupIds(int index) {
        return groupIds_.getLong(index);
    }

    private int groupIdsMemoizedSerializedSize = -1;

    private void ensureGroupIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = groupIds_;
        if (!tmp.isModifiable()) {
            groupIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The groupIds to set.
     */
    private void setGroupIds(int index, long value) {
        ensureGroupIdsIsMutable();
        groupIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param value The groupIds to add.
     */
    private void addGroupIds(long value) {
        ensureGroupIdsIsMutable();
        groupIds_.addLong(value);
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param values The groupIds to add.
     */
    private void addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureGroupIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupIds_);
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     */
    private void clearGroupIds() {
        groupIds_ = emptyLongList();
    }

    public static final int CREATION_DATE_START_FIELD_NUMBER = 3;
    private long creationDateStart_;

    /**
     * <code>optional int64 creation_date_start = 3;</code>
     *
     * @return Whether the creationDateStart field is set.
     */
    @java.lang.Override
    public boolean hasCreationDateStart() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 creation_date_start = 3;</code>
     *
     * @return The creationDateStart.
     */
    @java.lang.Override
    public long getCreationDateStart() {
        return creationDateStart_;
    }

    /**
     * <code>optional int64 creation_date_start = 3;</code>
     *
     * @param value The creationDateStart to set.
     */
    private void setCreationDateStart(long value) {
        bitField0_ |= 0x00000001;
        creationDateStart_ = value;
    }

    /**
     * <code>optional int64 creation_date_start = 3;</code>
     */
    private void clearCreationDateStart() {
        bitField0_ &= ~0x00000001;
        creationDateStart_ = 0L;
    }

    public static final int CREATION_DATE_END_FIELD_NUMBER = 4;
    private long creationDateEnd_;

    /**
     * <code>optional int64 creation_date_end = 4;</code>
     *
     * @return Whether the creationDateEnd field is set.
     */
    @java.lang.Override
    public boolean hasCreationDateEnd() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 creation_date_end = 4;</code>
     *
     * @return The creationDateEnd.
     */
    @java.lang.Override
    public long getCreationDateEnd() {
        return creationDateEnd_;
    }

    /**
     * <code>optional int64 creation_date_end = 4;</code>
     *
     * @param value The creationDateEnd to set.
     */
    private void setCreationDateEnd(long value) {
        bitField0_ |= 0x00000002;
        creationDateEnd_ = value;
    }

    /**
     * <code>optional int64 creation_date_end = 4;</code>
     */
    private void clearCreationDateEnd() {
        bitField0_ &= ~0x00000002;
        creationDateEnd_ = 0L;
    }

    public static final int IN_PRIVATE_CONVERSATION_FIELD_NUMBER = 5;
    private boolean inPrivateConversation_;

    /**
     * <code>optional bool in_private_conversation = 5;</code>
     *
     * @return Whether the inPrivateConversation field is set.
     */
    @java.lang.Override
    public boolean hasInPrivateConversation() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional bool in_private_conversation = 5;</code>
     *
     * @return The inPrivateConversation.
     */
    @java.lang.Override
    public boolean getInPrivateConversation() {
        return inPrivateConversation_;
    }

    /**
     * <code>optional bool in_private_conversation = 5;</code>
     *
     * @param value The inPrivateConversation to set.
     */
    private void setInPrivateConversation(boolean value) {
        bitField0_ |= 0x00000004;
        inPrivateConversation_ = value;
    }

    /**
     * <code>optional bool in_private_conversation = 5;</code>
     */
    private void clearInPrivateConversation() {
        bitField0_ &= ~0x00000004;
        inPrivateConversation_ = false;
    }

    public static final int ARE_SHARED_BY_ME_FIELD_NUMBER = 6;
    private boolean areSharedByMe_;

    /**
     * <code>optional bool are_shared_by_me = 6;</code>
     *
     * @return Whether the areSharedByMe field is set.
     */
    @java.lang.Override
    public boolean hasAreSharedByMe() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional bool are_shared_by_me = 6;</code>
     *
     * @return The areSharedByMe.
     */
    @java.lang.Override
    public boolean getAreSharedByMe() {
        return areSharedByMe_;
    }

    /**
     * <code>optional bool are_shared_by_me = 6;</code>
     *
     * @param value The areSharedByMe to set.
     */
    private void setAreSharedByMe(boolean value) {
        bitField0_ |= 0x00000008;
        areSharedByMe_ = value;
    }

    /**
     * <code>optional bool are_shared_by_me = 6;</code>
     */
    private void clearAreSharedByMe() {
        bitField0_ &= ~0x00000008;
        areSharedByMe_ = false;
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
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
            im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryMessageAttachmentInfosRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMessageAttachmentInfosRequest)
            im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return A list containing the userIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getUserIdsList() {
            return java.util.Collections.unmodifiableList(instance.getUserIdsList());
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return The count of userIds.
         */
        @java.lang.Override
        public int getUserIdsCount() {
            return instance.getUserIdsCount();
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The userIds at the given index.
         */
        @java.lang.Override
        public long getUserIds(int index) {
            return instance.getUserIds(index);
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param value The userIds to set.
         * @return This builder for chaining.
         */
        public Builder setUserIds(int index, long value) {
            copyOnWrite();
            instance.setUserIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param value The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addUserIds(long value) {
            copyOnWrite();
            instance.addUserIds(value);
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param values The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllUserIds(values);
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIds() {
            copyOnWrite();
            instance.clearUserIds();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return A list containing the groupIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getGroupIdsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupIdsList());
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return The count of groupIds.
         */
        @java.lang.Override
        public int getGroupIdsCount() {
            return instance.getGroupIdsCount();
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIds at the given index.
         */
        @java.lang.Override
        public long getGroupIds(int index) {
            return instance.getGroupIds(index);
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param value The groupIds to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIds(int index, long value) {
            copyOnWrite();
            instance.setGroupIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param value The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addGroupIds(long value) {
            copyOnWrite();
            instance.addGroupIds(value);
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param values The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllGroupIds(values);
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIds() {
            copyOnWrite();
            instance.clearGroupIds();
            return this;
        }

        /**
         * <code>optional int64 creation_date_start = 3;</code>
         *
         * @return Whether the creationDateStart field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateStart() {
            return instance.hasCreationDateStart();
        }

        /**
         * <code>optional int64 creation_date_start = 3;</code>
         *
         * @return The creationDateStart.
         */
        @java.lang.Override
        public long getCreationDateStart() {
            return instance.getCreationDateStart();
        }

        /**
         * <code>optional int64 creation_date_start = 3;</code>
         *
         * @param value The creationDateStart to set.
         * @return This builder for chaining.
         */
        public Builder setCreationDateStart(long value) {
            copyOnWrite();
            instance.setCreationDateStart(value);
            return this;
        }

        /**
         * <code>optional int64 creation_date_start = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateStart() {
            copyOnWrite();
            instance.clearCreationDateStart();
            return this;
        }

        /**
         * <code>optional int64 creation_date_end = 4;</code>
         *
         * @return Whether the creationDateEnd field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateEnd() {
            return instance.hasCreationDateEnd();
        }

        /**
         * <code>optional int64 creation_date_end = 4;</code>
         *
         * @return The creationDateEnd.
         */
        @java.lang.Override
        public long getCreationDateEnd() {
            return instance.getCreationDateEnd();
        }

        /**
         * <code>optional int64 creation_date_end = 4;</code>
         *
         * @param value The creationDateEnd to set.
         * @return This builder for chaining.
         */
        public Builder setCreationDateEnd(long value) {
            copyOnWrite();
            instance.setCreationDateEnd(value);
            return this;
        }

        /**
         * <code>optional int64 creation_date_end = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateEnd() {
            copyOnWrite();
            instance.clearCreationDateEnd();
            return this;
        }

        /**
         * <code>optional bool in_private_conversation = 5;</code>
         *
         * @return Whether the inPrivateConversation field is set.
         */
        @java.lang.Override
        public boolean hasInPrivateConversation() {
            return instance.hasInPrivateConversation();
        }

        /**
         * <code>optional bool in_private_conversation = 5;</code>
         *
         * @return The inPrivateConversation.
         */
        @java.lang.Override
        public boolean getInPrivateConversation() {
            return instance.getInPrivateConversation();
        }

        /**
         * <code>optional bool in_private_conversation = 5;</code>
         *
         * @param value The inPrivateConversation to set.
         * @return This builder for chaining.
         */
        public Builder setInPrivateConversation(boolean value) {
            copyOnWrite();
            instance.setInPrivateConversation(value);
            return this;
        }

        /**
         * <code>optional bool in_private_conversation = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInPrivateConversation() {
            copyOnWrite();
            instance.clearInPrivateConversation();
            return this;
        }

        /**
         * <code>optional bool are_shared_by_me = 6;</code>
         *
         * @return Whether the areSharedByMe field is set.
         */
        @java.lang.Override
        public boolean hasAreSharedByMe() {
            return instance.hasAreSharedByMe();
        }

        /**
         * <code>optional bool are_shared_by_me = 6;</code>
         *
         * @return The areSharedByMe.
         */
        @java.lang.Override
        public boolean getAreSharedByMe() {
            return instance.getAreSharedByMe();
        }

        /**
         * <code>optional bool are_shared_by_me = 6;</code>
         *
         * @param value The areSharedByMe to set.
         * @return This builder for chaining.
         */
        public Builder setAreSharedByMe(boolean value) {
            copyOnWrite();
            instance.setAreSharedByMe(value);
            return this;
        }

        /**
         * <code>optional bool are_shared_by_me = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAreSharedByMe() {
            copyOnWrite();
            instance.clearAreSharedByMe();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMessageAttachmentInfosRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "userIds_",
                        "groupIds_",
                        "creationDateStart_",
                        "creationDateEnd_",
                        "inPrivateConversation_",
                        "areSharedByMe_",};
                java.lang.String info =
                        "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001%\u0002%\u0003"
                                + "\u1002\u0000\u0004\u1002\u0001\u0005\u1007\u0002\u0006\u1007\u0003";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMessageAttachmentInfosRequest)
    private static final im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest DEFAULT_INSTANCE;

    static {
        QueryMessageAttachmentInfosRequest defaultInstance =
                new QueryMessageAttachmentInfosRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryMessageAttachmentInfosRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryMessageAttachmentInfosRequest> PARSER;

    public static com.google.protobuf.Parser<QueryMessageAttachmentInfosRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}