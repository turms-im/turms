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

package im.turms.client.model.proto.request.conference;

/**
 * Protobuf type {@code im.turms.proto.QueryMeetingsRequest}
 */
public final class QueryMeetingsRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryMeetingsRequest, QueryMeetingsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMeetingsRequest)
        QueryMeetingsRequestOrBuilder {
    private QueryMeetingsRequest() {
        ids_ = emptyLongList();
        creatorIds_ = emptyLongList();
        userIds_ = emptyLongList();
        groupIds_ = emptyLongList();
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int IDS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.LongList ids_;

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @return A list containing the ids.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getIdsList() {
        return ids_;
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @return The count of ids.
     */
    @java.lang.Override
    public int getIdsCount() {
        return ids_.size();
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The ids at the given index.
     */
    @java.lang.Override
    public long getIds(int index) {
        return ids_.getLong(index);
    }

    private final int idsMemoizedSerializedSize = -1;

    private void ensureIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = ids_;
        if (!tmp.isModifiable()) {
            ids_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @param index The index to set the value at.
     * @param value The ids to set.
     */
    private void setIds(int index, long value) {
        ensureIdsIsMutable();
        ids_.setLong(index, value);
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @param value The ids to add.
     */
    private void addIds(long value) {
        ensureIdsIsMutable();
        ids_.addLong(value);
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @param values The ids to add.
     */
    private void addAllIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, ids_);
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     */
    private void clearIds() {
        ids_ = emptyLongList();
    }

    public static final int CREATOR_IDS_FIELD_NUMBER = 2;
    private com.google.protobuf.Internal.LongList creatorIds_;

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @return A list containing the creatorIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getCreatorIdsList() {
        return creatorIds_;
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @return The count of creatorIds.
     */
    @java.lang.Override
    public int getCreatorIdsCount() {
        return creatorIds_.size();
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The creatorIds at the given index.
     */
    @java.lang.Override
    public long getCreatorIds(int index) {
        return creatorIds_.getLong(index);
    }

    private final int creatorIdsMemoizedSerializedSize = -1;

    private void ensureCreatorIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = creatorIds_;
        if (!tmp.isModifiable()) {
            creatorIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The creatorIds to set.
     */
    private void setCreatorIds(int index, long value) {
        ensureCreatorIdsIsMutable();
        creatorIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @param value The creatorIds to add.
     */
    private void addCreatorIds(long value) {
        ensureCreatorIdsIsMutable();
        creatorIds_.addLong(value);
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @param values The creatorIds to add.
     */
    private void addAllCreatorIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureCreatorIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, creatorIds_);
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     */
    private void clearCreatorIds() {
        creatorIds_ = emptyLongList();
    }

    public static final int USER_IDS_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.LongList userIds_;

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @return A list containing the userIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getUserIdsList() {
        return userIds_;
    }

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @return The count of userIds.
     */
    @java.lang.Override
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <code>repeated int64 user_ids = 3;</code>
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
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @param index The index to set the value at.
     * @param value The userIds to set.
     */
    private void setUserIds(int index, long value) {
        ensureUserIdsIsMutable();
        userIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @param value The userIds to add.
     */
    private void addUserIds(long value) {
        ensureUserIdsIsMutable();
        userIds_.addLong(value);
    }

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @param values The userIds to add.
     */
    private void addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureUserIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, userIds_);
    }

    /**
     * <code>repeated int64 user_ids = 3;</code>
     */
    private void clearUserIds() {
        userIds_ = emptyLongList();
    }

    public static final int GROUP_IDS_FIELD_NUMBER = 4;
    private com.google.protobuf.Internal.LongList groupIds_;

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @return A list containing the groupIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getGroupIdsList() {
        return groupIds_;
    }

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @return The count of groupIds.
     */
    @java.lang.Override
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <code>repeated int64 group_ids = 4;</code>
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
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @param index The index to set the value at.
     * @param value The groupIds to set.
     */
    private void setGroupIds(int index, long value) {
        ensureGroupIdsIsMutable();
        groupIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @param value The groupIds to add.
     */
    private void addGroupIds(long value) {
        ensureGroupIdsIsMutable();
        groupIds_.addLong(value);
    }

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @param values The groupIds to add.
     */
    private void addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureGroupIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupIds_);
    }

    /**
     * <code>repeated int64 group_ids = 4;</code>
     */
    private void clearGroupIds() {
        groupIds_ = emptyLongList();
    }

    public static final int CREATION_DATE_START_FIELD_NUMBER = 5;
    private long creationDateStart_;

    /**
     * <code>optional int64 creation_date_start = 5;</code>
     *
     * @return Whether the creationDateStart field is set.
     */
    @java.lang.Override
    public boolean hasCreationDateStart() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 creation_date_start = 5;</code>
     *
     * @return The creationDateStart.
     */
    @java.lang.Override
    public long getCreationDateStart() {
        return creationDateStart_;
    }

    /**
     * <code>optional int64 creation_date_start = 5;</code>
     *
     * @param value The creationDateStart to set.
     */
    private void setCreationDateStart(long value) {
        bitField0_ |= 0x00000001;
        creationDateStart_ = value;
    }

    /**
     * <code>optional int64 creation_date_start = 5;</code>
     */
    private void clearCreationDateStart() {
        bitField0_ &= ~0x00000001;
        creationDateStart_ = 0L;
    }

    public static final int CREATION_DATE_END_FIELD_NUMBER = 6;
    private long creationDateEnd_;

    /**
     * <code>optional int64 creation_date_end = 6;</code>
     *
     * @return Whether the creationDateEnd field is set.
     */
    @java.lang.Override
    public boolean hasCreationDateEnd() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 creation_date_end = 6;</code>
     *
     * @return The creationDateEnd.
     */
    @java.lang.Override
    public long getCreationDateEnd() {
        return creationDateEnd_;
    }

    /**
     * <code>optional int64 creation_date_end = 6;</code>
     *
     * @param value The creationDateEnd to set.
     */
    private void setCreationDateEnd(long value) {
        bitField0_ |= 0x00000002;
        creationDateEnd_ = value;
    }

    /**
     * <code>optional int64 creation_date_end = 6;</code>
     */
    private void clearCreationDateEnd() {
        bitField0_ &= ~0x00000002;
        creationDateEnd_ = 0L;
    }

    public static final int SKIP_FIELD_NUMBER = 10;
    private int skip_;

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return Whether the skip field is set.
     */
    @java.lang.Override
    public boolean hasSkip() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return The skip.
     */
    @java.lang.Override
    public int getSkip() {
        return skip_;
    }

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @param value The skip to set.
     */
    private void setSkip(int value) {
        bitField0_ |= 0x00000004;
        skip_ = value;
    }

    /**
     * <code>optional int32 skip = 10;</code>
     */
    private void clearSkip() {
        bitField0_ &= ~0x00000004;
        skip_ = 0;
    }

    public static final int LIMIT_FIELD_NUMBER = 11;
    private int limit_;

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return Whether the limit field is set.
     */
    @java.lang.Override
    public boolean hasLimit() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return The limit.
     */
    @java.lang.Override
    public int getLimit() {
        return limit_;
    }

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @param value The limit to set.
     */
    private void setLimit(int value) {
        bitField0_ |= 0x00000008;
        limit_ = value;
    }

    /**
     * <code>optional int32 limit = 11;</code>
     */
    private void clearLimit() {
        bitField0_ &= ~0x00000008;
        limit_ = 0;
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

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest parseFrom(
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
            im.turms.client.model.proto.request.conference.QueryMeetingsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryMeetingsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.conference.QueryMeetingsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMeetingsRequest)
            im.turms.client.model.proto.request.conference.QueryMeetingsRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.conference.QueryMeetingsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @return A list containing the ids.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getIdsList() {
            return java.util.Collections.unmodifiableList(instance.getIdsList());
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @return The count of ids.
         */
        @java.lang.Override
        public int getIdsCount() {
            return instance.getIdsCount();
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The ids at the given index.
         */
        @java.lang.Override
        public long getIds(int index) {
            return instance.getIds(index);
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param value The ids to set.
         * @return This builder for chaining.
         */
        public Builder setIds(int index, long value) {
            copyOnWrite();
            instance.setIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param value The ids to add.
         * @return This builder for chaining.
         */
        public Builder addIds(long value) {
            copyOnWrite();
            instance.addIds(value);
            return this;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param values The ids to add.
         * @return This builder for chaining.
         */
        public Builder addAllIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllIds(values);
            return this;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIds() {
            copyOnWrite();
            instance.clearIds();
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @return A list containing the creatorIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getCreatorIdsList() {
            return java.util.Collections.unmodifiableList(instance.getCreatorIdsList());
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @return The count of creatorIds.
         */
        @java.lang.Override
        public int getCreatorIdsCount() {
            return instance.getCreatorIdsCount();
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The creatorIds at the given index.
         */
        @java.lang.Override
        public long getCreatorIds(int index) {
            return instance.getCreatorIds(index);
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param value The creatorIds to set.
         * @return This builder for chaining.
         */
        public Builder setCreatorIds(int index, long value) {
            copyOnWrite();
            instance.setCreatorIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param value The creatorIds to add.
         * @return This builder for chaining.
         */
        public Builder addCreatorIds(long value) {
            copyOnWrite();
            instance.addCreatorIds(value);
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param values The creatorIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllCreatorIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllCreatorIds(values);
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreatorIds() {
            copyOnWrite();
            instance.clearCreatorIds();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @return A list containing the userIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getUserIdsList() {
            return java.util.Collections.unmodifiableList(instance.getUserIdsList());
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @return The count of userIds.
         */
        @java.lang.Override
        public int getUserIdsCount() {
            return instance.getUserIdsCount();
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The userIds at the given index.
         */
        @java.lang.Override
        public long getUserIds(int index) {
            return instance.getUserIds(index);
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
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
         * <code>repeated int64 user_ids = 3;</code>
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
         * <code>repeated int64 user_ids = 3;</code>
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
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIds() {
            copyOnWrite();
            instance.clearUserIds();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @return A list containing the groupIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getGroupIdsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupIdsList());
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @return The count of groupIds.
         */
        @java.lang.Override
        public int getGroupIdsCount() {
            return instance.getGroupIdsCount();
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIds at the given index.
         */
        @java.lang.Override
        public long getGroupIds(int index) {
            return instance.getGroupIds(index);
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
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
         * <code>repeated int64 group_ids = 4;</code>
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
         * <code>repeated int64 group_ids = 4;</code>
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
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIds() {
            copyOnWrite();
            instance.clearGroupIds();
            return this;
        }

        /**
         * <code>optional int64 creation_date_start = 5;</code>
         *
         * @return Whether the creationDateStart field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateStart() {
            return instance.hasCreationDateStart();
        }

        /**
         * <code>optional int64 creation_date_start = 5;</code>
         *
         * @return The creationDateStart.
         */
        @java.lang.Override
        public long getCreationDateStart() {
            return instance.getCreationDateStart();
        }

        /**
         * <code>optional int64 creation_date_start = 5;</code>
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
         * <code>optional int64 creation_date_start = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateStart() {
            copyOnWrite();
            instance.clearCreationDateStart();
            return this;
        }

        /**
         * <code>optional int64 creation_date_end = 6;</code>
         *
         * @return Whether the creationDateEnd field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateEnd() {
            return instance.hasCreationDateEnd();
        }

        /**
         * <code>optional int64 creation_date_end = 6;</code>
         *
         * @return The creationDateEnd.
         */
        @java.lang.Override
        public long getCreationDateEnd() {
            return instance.getCreationDateEnd();
        }

        /**
         * <code>optional int64 creation_date_end = 6;</code>
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
         * <code>optional int64 creation_date_end = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateEnd() {
            copyOnWrite();
            instance.clearCreationDateEnd();
            return this;
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return Whether the skip field is set.
         */
        @java.lang.Override
        public boolean hasSkip() {
            return instance.hasSkip();
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return The skip.
         */
        @java.lang.Override
        public int getSkip() {
            return instance.getSkip();
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @param value The skip to set.
         * @return This builder for chaining.
         */
        public Builder setSkip(int value) {
            copyOnWrite();
            instance.setSkip(value);
            return this;
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSkip() {
            copyOnWrite();
            instance.clearSkip();
            return this;
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return Whether the limit field is set.
         */
        @java.lang.Override
        public boolean hasLimit() {
            return instance.hasLimit();
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return The limit.
         */
        @java.lang.Override
        public int getLimit() {
            return instance.getLimit();
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @param value The limit to set.
         * @return This builder for chaining.
         */
        public Builder setLimit(int value) {
            copyOnWrite();
            instance.setLimit(value);
            return this;
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLimit() {
            copyOnWrite();
            instance.clearLimit();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMeetingsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.conference.QueryMeetingsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "ids_",
                        "creatorIds_",
                        "userIds_",
                        "groupIds_",
                        "creationDateStart_",
                        "creationDateEnd_",
                        "skip_",
                        "limit_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\t\u0000\u0001\u0001\u000f\t\u0000\u0005\u0000\u0001%\u0002%\u0003%\u0004%"
                                + "\u0005\u1002\u0000\u0006\u1002\u0001\n\u1004\u0002\u000b\u1004\u0003\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.conference.QueryMeetingsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.conference.QueryMeetingsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMeetingsRequest)
    private static final im.turms.client.model.proto.request.conference.QueryMeetingsRequest DEFAULT_INSTANCE;
    static {
        QueryMeetingsRequest defaultInstance = new QueryMeetingsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(QueryMeetingsRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.conference.QueryMeetingsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryMeetingsRequest> PARSER;

    public static com.google.protobuf.Parser<QueryMeetingsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}