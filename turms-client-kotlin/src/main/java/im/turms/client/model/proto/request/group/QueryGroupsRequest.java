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

package im.turms.client.model.proto.request.group;

/**
 * Protobuf type {@code im.turms.proto.QueryGroupsRequest}
 */
public final class QueryGroupsRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryGroupsRequest, QueryGroupsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryGroupsRequest)
        QueryGroupsRequestOrBuilder {
    private QueryGroupsRequest() {
        groupIds_ = emptyLongList();
        name_ = "";
        fieldsToHighlight_ = emptyIntList();
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int GROUP_IDS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.LongList groupIds_;

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @return A list containing the groupIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getGroupIdsList() {
        return groupIds_;
    }

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @return The count of groupIds.
     */
    @java.lang.Override
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <code>repeated int64 group_ids = 1;</code>
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
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @param index The index to set the value at.
     * @param value The groupIds to set.
     */
    private void setGroupIds(int index, long value) {
        ensureGroupIdsIsMutable();
        groupIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @param value The groupIds to add.
     */
    private void addGroupIds(long value) {
        ensureGroupIdsIsMutable();
        groupIds_.addLong(value);
    }

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @param values The groupIds to add.
     */
    private void addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureGroupIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupIds_);
    }

    /**
     * <code>repeated int64 group_ids = 1;</code>
     */
    private void clearGroupIds() {
        groupIds_ = emptyLongList();
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @param value The lastUpdatedDate to set.
     */
    private void setLastUpdatedDate(long value) {
        bitField0_ |= 0x00000001;
        lastUpdatedDate_ = value;
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     */
    private void clearLastUpdatedDate() {
        bitField0_ &= ~0x00000001;
        lastUpdatedDate_ = 0L;
    }

    public static final int NAME_FIELD_NUMBER = 3;
    private java.lang.String name_;

    /**
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        name_ = value;
    }

    /**
     * <code>optional string name = 3;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000002;
        name_ = getDefaultInstance().getName();
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();
        bitField0_ |= 0x00000002;
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

    public static final int FIELDS_TO_HIGHLIGHT_FIELD_NUMBER = 12;
    private com.google.protobuf.Internal.IntList fieldsToHighlight_;

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @return A list containing the fieldsToHighlight.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getFieldsToHighlightList() {
        return fieldsToHighlight_;
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @return The count of fieldsToHighlight.
     */
    @java.lang.Override
    public int getFieldsToHighlightCount() {
        return fieldsToHighlight_.size();
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @param index The index of the element to return.
     * @return The fieldsToHighlight at the given index.
     */
    @java.lang.Override
    public int getFieldsToHighlight(int index) {
        return fieldsToHighlight_.getInt(index);
    }

    private final int fieldsToHighlightMemoizedSerializedSize = -1;

    private void ensureFieldsToHighlightIsMutable() {
        com.google.protobuf.Internal.IntList tmp = fieldsToHighlight_;
        if (!tmp.isModifiable()) {
            fieldsToHighlight_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @param index The index to set the value at.
     * @param value The fieldsToHighlight to set.
     */
    private void setFieldsToHighlight(int index, int value) {
        ensureFieldsToHighlightIsMutable();
        fieldsToHighlight_.setInt(index, value);
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @param value The fieldsToHighlight to add.
     */
    private void addFieldsToHighlight(int value) {
        ensureFieldsToHighlightIsMutable();
        fieldsToHighlight_.addInt(value);
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @param values The fieldsToHighlight to add.
     */
    private void addAllFieldsToHighlight(java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureFieldsToHighlightIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, fieldsToHighlight_);
    }

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     */
    private void clearFieldsToHighlight() {
        fieldsToHighlight_ = emptyIntList();
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

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest parseFrom(
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
            im.turms.client.model.proto.request.group.QueryGroupsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryGroupsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.QueryGroupsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryGroupsRequest)
            im.turms.client.model.proto.request.group.QueryGroupsRequestOrBuilder {
        // Construct using im.turms.client.model.proto.request.group.QueryGroupsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated int64 group_ids = 1;</code>
         *
         * @return A list containing the groupIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getGroupIdsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupIdsList());
        }

        /**
         * <code>repeated int64 group_ids = 1;</code>
         *
         * @return The count of groupIds.
         */
        @java.lang.Override
        public int getGroupIdsCount() {
            return instance.getGroupIdsCount();
        }

        /**
         * <code>repeated int64 group_ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIds at the given index.
         */
        @java.lang.Override
        public long getGroupIds(int index) {
            return instance.getGroupIds(index);
        }

        /**
         * <code>repeated int64 group_ids = 1;</code>
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
         * <code>repeated int64 group_ids = 1;</code>
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
         * <code>repeated int64 group_ids = 1;</code>
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
         * <code>repeated int64 group_ids = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIds() {
            copyOnWrite();
            instance.clearGroupIds();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return instance.hasLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return instance.getLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
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
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            copyOnWrite();
            instance.clearLastUpdatedDate();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return Whether the name field is set.
         */
        @java.lang.Override
        public boolean hasName() {
            return instance.hasName();
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            copyOnWrite();
            instance.setName(value);
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setNameBytes(value);
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
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @return A list containing the fieldsToHighlight.
         */
        @java.lang.Override
        public java.util.List<java.lang.Integer> getFieldsToHighlightList() {
            return java.util.Collections.unmodifiableList(instance.getFieldsToHighlightList());
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @return The count of fieldsToHighlight.
         */
        @java.lang.Override
        public int getFieldsToHighlightCount() {
            return instance.getFieldsToHighlightCount();
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param index The index of the element to return.
         * @return The fieldsToHighlight at the given index.
         */
        @java.lang.Override
        public int getFieldsToHighlight(int index) {
            return instance.getFieldsToHighlight(index);
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param value The fieldsToHighlight to set.
         * @return This builder for chaining.
         */
        public Builder setFieldsToHighlight(int index, int value) {
            copyOnWrite();
            instance.setFieldsToHighlight(index, value);
            return this;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param value The fieldsToHighlight to add.
         * @return This builder for chaining.
         */
        public Builder addFieldsToHighlight(int value) {
            copyOnWrite();
            instance.addFieldsToHighlight(value);
            return this;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @param values The fieldsToHighlight to add.
         * @return This builder for chaining.
         */
        public Builder addAllFieldsToHighlight(
                java.lang.Iterable<? extends java.lang.Integer> values) {
            copyOnWrite();
            instance.addAllFieldsToHighlight(values);
            return this;
        }

        /**
         * <code>repeated int32 fields_to_highlight = 12;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFieldsToHighlight() {
            copyOnWrite();
            instance.clearFieldsToHighlight();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryGroupsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.QueryGroupsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupIds_",
                        "lastUpdatedDate_",
                        "name_",
                        "skip_",
                        "limit_",
                        "fieldsToHighlight_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0007\u0000\u0001\u0001\u000f\u0007\u0000\u0003\u0000\u0001%\u0002\u1002\u0000"
                                + "\u0003\u1208\u0001\n\u1004\u0002\u000b\u1004\u0003\f'\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.QueryGroupsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.QueryGroupsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryGroupsRequest)
    private static final im.turms.client.model.proto.request.group.QueryGroupsRequest DEFAULT_INSTANCE;
    static {
        QueryGroupsRequest defaultInstance = new QueryGroupsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(QueryGroupsRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.QueryGroupsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryGroupsRequest> PARSER;

    public static com.google.protobuf.Parser<QueryGroupsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}