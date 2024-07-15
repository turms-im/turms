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
 * Protobuf type {@code im.turms.proto.QueryRelationshipsRequest}
 */
public final class QueryRelationshipsRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryRelationshipsRequest, QueryRelationshipsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryRelationshipsRequest)
        QueryRelationshipsRequestOrBuilder {
    private QueryRelationshipsRequest() {
        userIds_ = emptyLongList();
        groupIndexes_ = emptyIntList();
        customAttributes_ = emptyProtobufList();
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

    private final int userIdsMemoizedSerializedSize = -1;

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

    public static final int BLOCKED_FIELD_NUMBER = 2;
    private boolean blocked_;

    /**
     * <code>optional bool blocked = 2;</code>
     *
     * @return Whether the blocked field is set.
     */
    @java.lang.Override
    public boolean hasBlocked() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional bool blocked = 2;</code>
     *
     * @return The blocked.
     */
    @java.lang.Override
    public boolean getBlocked() {
        return blocked_;
    }

    /**
     * <code>optional bool blocked = 2;</code>
     *
     * @param value The blocked to set.
     */
    private void setBlocked(boolean value) {
        bitField0_ |= 0x00000001;
        blocked_ = value;
    }

    /**
     * <code>optional bool blocked = 2;</code>
     */
    private void clearBlocked() {
        bitField0_ &= ~0x00000001;
        blocked_ = false;
    }

    public static final int GROUP_INDEXES_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.IntList groupIndexes_;

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @return A list containing the groupIndexes.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getGroupIndexesList() {
        return groupIndexes_;
    }

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @return The count of groupIndexes.
     */
    @java.lang.Override
    public int getGroupIndexesCount() {
        return groupIndexes_.size();
    }

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIndexes at the given index.
     */
    @java.lang.Override
    public int getGroupIndexes(int index) {
        return groupIndexes_.getInt(index);
    }

    private final int groupIndexesMemoizedSerializedSize = -1;

    private void ensureGroupIndexesIsMutable() {
        com.google.protobuf.Internal.IntList tmp = groupIndexes_;
        if (!tmp.isModifiable()) {
            groupIndexes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @param index The index to set the value at.
     * @param value The groupIndexes to set.
     */
    private void setGroupIndexes(int index, int value) {
        ensureGroupIndexesIsMutable();
        groupIndexes_.setInt(index, value);
    }

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @param value The groupIndexes to add.
     */
    private void addGroupIndexes(int value) {
        ensureGroupIndexesIsMutable();
        groupIndexes_.addInt(value);
    }

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @param values The groupIndexes to add.
     */
    private void addAllGroupIndexes(java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureGroupIndexesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupIndexes_);
    }

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     */
    private void clearGroupIndexes() {
        groupIndexes_ = emptyIntList();
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 4;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 4;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 4;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    /**
     * <code>optional int64 last_updated_date = 4;</code>
     *
     * @param value The lastUpdatedDate to set.
     */
    private void setLastUpdatedDate(long value) {
        bitField0_ |= 0x00000002;
        lastUpdatedDate_ = value;
    }

    /**
     * <code>optional int64 last_updated_date = 4;</code>
     */
    private void clearLastUpdatedDate() {
        bitField0_ &= ~0x00000002;
        lastUpdatedDate_ = 0L;
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

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest parseFrom(
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
            im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryRelationshipsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryRelationshipsRequest)
            im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest.newBuilder()
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
         * <code>optional bool blocked = 2;</code>
         *
         * @return Whether the blocked field is set.
         */
        @java.lang.Override
        public boolean hasBlocked() {
            return instance.hasBlocked();
        }

        /**
         * <code>optional bool blocked = 2;</code>
         *
         * @return The blocked.
         */
        @java.lang.Override
        public boolean getBlocked() {
            return instance.getBlocked();
        }

        /**
         * <code>optional bool blocked = 2;</code>
         *
         * @param value The blocked to set.
         * @return This builder for chaining.
         */
        public Builder setBlocked(boolean value) {
            copyOnWrite();
            instance.setBlocked(value);
            return this;
        }

        /**
         * <code>optional bool blocked = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBlocked() {
            copyOnWrite();
            instance.clearBlocked();
            return this;
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @return A list containing the groupIndexes.
         */
        @java.lang.Override
        public java.util.List<java.lang.Integer> getGroupIndexesList() {
            return java.util.Collections.unmodifiableList(instance.getGroupIndexesList());
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @return The count of groupIndexes.
         */
        @java.lang.Override
        public int getGroupIndexesCount() {
            return instance.getGroupIndexesCount();
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIndexes at the given index.
         */
        @java.lang.Override
        public int getGroupIndexes(int index) {
            return instance.getGroupIndexes(index);
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @param value The groupIndexes to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIndexes(int index, int value) {
            copyOnWrite();
            instance.setGroupIndexes(index, value);
            return this;
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @param value The groupIndexes to add.
         * @return This builder for chaining.
         */
        public Builder addGroupIndexes(int value) {
            copyOnWrite();
            instance.addGroupIndexes(value);
            return this;
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @param values The groupIndexes to add.
         * @return This builder for chaining.
         */
        public Builder addAllGroupIndexes(java.lang.Iterable<? extends java.lang.Integer> values) {
            copyOnWrite();
            instance.addAllGroupIndexes(values);
            return this;
        }

        /**
         * <code>repeated int32 group_indexes = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIndexes() {
            copyOnWrite();
            instance.clearGroupIndexes();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 4;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return instance.hasLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 4;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return instance.getLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 4;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {
            copyOnWrite();
            instance.setLastUpdatedDate(value);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            copyOnWrite();
            instance.clearLastUpdatedDate();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryRelationshipsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "userIds_",
                        "blocked_",
                        "groupIndexes_",
                        "lastUpdatedDate_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0005\u0000\u0001\u0001\u000f\u0005\u0000\u0003\u0000\u0001%\u0002\u1007\u0000"
                                + "\u0003'\u0004\u1002\u0001\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryRelationshipsRequest)
    private static final im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest DEFAULT_INSTANCE;
    static {
        QueryRelationshipsRequest defaultInstance = new QueryRelationshipsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryRelationshipsRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.relationship.QueryRelationshipsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryRelationshipsRequest> PARSER;

    public static com.google.protobuf.Parser<QueryRelationshipsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}