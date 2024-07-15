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

package im.turms.client.model.proto.request.conversation;

/**
 * Protobuf type {@code im.turms.proto.QueryConversationsRequest}
 */
public final class QueryConversationsRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryConversationsRequest, QueryConversationsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryConversationsRequest)
        QueryConversationsRequestOrBuilder {
    private QueryConversationsRequest() {
        userIds_ = emptyLongList();
        groupIds_ = emptyLongList();
        customAttributes_ = emptyProtobufList();
    }

    public static final int USER_IDS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.LongList userIds_;

    /**
     * <pre>
     * Private conversations
     * </pre>
     *
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return A list containing the userIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getUserIdsList() {
        return userIds_;
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     *
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return The count of userIds.
     */
    @java.lang.Override
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     *
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    @java.lang.Override
    public long getUserIds(int index) {
        return userIds_.getLong(index);
    }

    private final int userIdsMemoizedSerializedSize = -1;

    private void ensureUserIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = userIds_;
        if (!tmp.isModifiable()) {
            userIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     *
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
     * <pre>
     * Private conversations
     * </pre>
     *
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param value The userIds to add.
     */
    private void addUserIds(long value) {
        ensureUserIdsIsMutable();
        userIds_.addLong(value);
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     *
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param values The userIds to add.
     */
    private void addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureUserIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, userIds_);
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     *
     * <code>repeated int64 user_ids = 1;</code>
     */
    private void clearUserIds() {
        userIds_ = emptyLongList();
    }

    public static final int GROUP_IDS_FIELD_NUMBER = 2;
    private com.google.protobuf.Internal.LongList groupIds_;

    /**
     * <pre>
     * Group conversations
     * </pre>
     *
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return A list containing the groupIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getGroupIdsList() {
        return groupIds_;
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     *
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return The count of groupIds.
     */
    @java.lang.Override
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     *
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    @java.lang.Override
    public long getGroupIds(int index) {
        return groupIds_.getLong(index);
    }

    private final int groupIdsMemoizedSerializedSize = -1;

    private void ensureGroupIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = groupIds_;
        if (!tmp.isModifiable()) {
            groupIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     *
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
     * <pre>
     * Group conversations
     * </pre>
     *
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param value The groupIds to add.
     */
    private void addGroupIds(long value) {
        ensureGroupIdsIsMutable();
        groupIds_.addLong(value);
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     *
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param values The groupIds to add.
     */
    private void addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureGroupIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupIds_);
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     *
     * <code>repeated int64 group_ids = 2;</code>
     */
    private void clearGroupIds() {
        groupIds_ = emptyLongList();
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public int getCustomAttributesCount() {
        return customAttributes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private void ensureCustomAttributesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                customAttributes_;
        if (!tmp.isModifiable()) {
            customAttributes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void setCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addAllCustomAttributes(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureCustomAttributesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, customAttributes_);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void clearCustomAttributes() {
        customAttributes_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void removeCustomAttributes(int index) {
        ensureCustomAttributesIsMutable();
        customAttributes_.remove(index);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.conversation.QueryConversationsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryConversationsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.conversation.QueryConversationsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryConversationsRequest)
            im.turms.client.model.proto.request.conversation.QueryConversationsRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.conversation.QueryConversationsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         *
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return A list containing the userIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getUserIdsList() {
            return java.util.Collections.unmodifiableList(instance.getUserIdsList());
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         *
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return The count of userIds.
         */
        @java.lang.Override
        public int getUserIdsCount() {
            return instance.getUserIdsCount();
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         *
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
         * <pre>
         * Private conversations
         * </pre>
         *
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
         * <pre>
         * Private conversations
         * </pre>
         *
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
         * <pre>
         * Private conversations
         * </pre>
         *
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
         * <pre>
         * Private conversations
         * </pre>
         *
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
         * <pre>
         * Group conversations
         * </pre>
         *
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return A list containing the groupIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getGroupIdsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupIdsList());
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         *
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return The count of groupIds.
         */
        @java.lang.Override
        public int getGroupIdsCount() {
            return instance.getGroupIdsCount();
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         *
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
         * <pre>
         * Group conversations
         * </pre>
         *
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
         * <pre>
         * Group conversations
         * </pre>
         *
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
         * <pre>
         * Group conversations
         * </pre>
         *
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
         * <pre>
         * Group conversations
         * </pre>
         *
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
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
            return java.util.Collections.unmodifiableList(instance.getCustomAttributesList());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public int getCustomAttributesCount() {
            return instance.getCustomAttributesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
            return instance.getCustomAttributes(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllCustomAttributes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            copyOnWrite();
            instance.clearCustomAttributes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            copyOnWrite();
            instance.removeCustomAttributes(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryConversationsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.conversation.QueryConversationsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"userIds_",
                        "groupIds_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0000\u0001\u000f\u0003\u0000\u0003\u0000\u0001%\u0002%\u000f"
                                + "\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.conversation.QueryConversationsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.conversation.QueryConversationsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryConversationsRequest)
    private static final im.turms.client.model.proto.request.conversation.QueryConversationsRequest DEFAULT_INSTANCE;
    static {
        QueryConversationsRequest defaultInstance = new QueryConversationsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryConversationsRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.conversation.QueryConversationsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryConversationsRequest> PARSER;

    public static com.google.protobuf.Parser<QueryConversationsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}