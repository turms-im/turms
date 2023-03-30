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

package im.turms.client.model.proto.request.message;

/**
 * Protobuf type {@code im.turms.proto.QueryMessagesRequest}
 */
public final class QueryMessagesRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryMessagesRequest, QueryMessagesRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMessagesRequest)
        QueryMessagesRequestOrBuilder {
    private QueryMessagesRequest() {
        ids_ = emptyLongList();
        fromIds_ = emptyLongList();
    }

    private int bitField0_;
    public static final int IDS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.LongList ids_;

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @return A list containing the ids.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getIdsList() {
        return ids_;
    }

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @return The count of ids.
     */
    @java.lang.Override
    public int getIdsCount() {
        return ids_.size();
    }

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The ids at the given index.
     */
    @java.lang.Override
    public long getIds(int index) {
        return ids_.getLong(index);
    }

    private int idsMemoizedSerializedSize = -1;

    private void ensureIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = ids_;
        if (!tmp.isModifiable()) {
            ids_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <pre>
     * Filter
     * </pre>
     * 
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
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @param value The ids to add.
     */
    private void addIds(long value) {
        ensureIdsIsMutable();
        ids_.addLong(value);
    }

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @param values The ids to add.
     */
    private void addAllIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, ids_);
    }

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     */
    private void clearIds() {
        ids_ = emptyLongList();
    }

    public static final int ARE_GROUP_MESSAGES_FIELD_NUMBER = 2;
    private boolean areGroupMessages_;

    /**
     * <code>optional bool are_group_messages = 2;</code>
     *
     * @return Whether the areGroupMessages field is set.
     */
    @java.lang.Override
    public boolean hasAreGroupMessages() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional bool are_group_messages = 2;</code>
     *
     * @return The areGroupMessages.
     */
    @java.lang.Override
    public boolean getAreGroupMessages() {
        return areGroupMessages_;
    }

    /**
     * <code>optional bool are_group_messages = 2;</code>
     *
     * @param value The areGroupMessages to set.
     */
    private void setAreGroupMessages(boolean value) {
        bitField0_ |= 0x00000001;
        areGroupMessages_ = value;
    }

    /**
     * <code>optional bool are_group_messages = 2;</code>
     */
    private void clearAreGroupMessages() {
        bitField0_ &= ~0x00000001;
        areGroupMessages_ = false;
    }

    public static final int ARE_SYSTEM_MESSAGES_FIELD_NUMBER = 3;
    private boolean areSystemMessages_;

    /**
     * <code>optional bool are_system_messages = 3;</code>
     *
     * @return Whether the areSystemMessages field is set.
     */
    @java.lang.Override
    public boolean hasAreSystemMessages() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional bool are_system_messages = 3;</code>
     *
     * @return The areSystemMessages.
     */
    @java.lang.Override
    public boolean getAreSystemMessages() {
        return areSystemMessages_;
    }

    /**
     * <code>optional bool are_system_messages = 3;</code>
     *
     * @param value The areSystemMessages to set.
     */
    private void setAreSystemMessages(boolean value) {
        bitField0_ |= 0x00000002;
        areSystemMessages_ = value;
    }

    /**
     * <code>optional bool are_system_messages = 3;</code>
     */
    private void clearAreSystemMessages() {
        bitField0_ &= ~0x00000002;
        areSystemMessages_ = false;
    }

    public static final int FROM_IDS_FIELD_NUMBER = 4;
    private com.google.protobuf.Internal.LongList fromIds_;

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @return A list containing the fromIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getFromIdsList() {
        return fromIds_;
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @return The count of fromIds.
     */
    @java.lang.Override
    public int getFromIdsCount() {
        return fromIds_.size();
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @param index The index of the element to return.
     * @return The fromIds at the given index.
     */
    @java.lang.Override
    public long getFromIds(int index) {
        return fromIds_.getLong(index);
    }

    private int fromIdsMemoizedSerializedSize = -1;

    private void ensureFromIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = fromIds_;
        if (!tmp.isModifiable()) {
            fromIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @param index The index to set the value at.
     * @param value The fromIds to set.
     */
    private void setFromIds(int index, long value) {
        ensureFromIdsIsMutable();
        fromIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @param value The fromIds to add.
     */
    private void addFromIds(long value) {
        ensureFromIdsIsMutable();
        fromIds_.addLong(value);
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @param values The fromIds to add.
     */
    private void addAllFromIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureFromIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, fromIds_);
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     */
    private void clearFromIds() {
        fromIds_ = emptyLongList();
    }

    public static final int DELIVERY_DATE_START_FIELD_NUMBER = 5;
    private long deliveryDateStart_;

    /**
     * <code>optional int64 delivery_date_start = 5;</code>
     *
     * @return Whether the deliveryDateStart field is set.
     */
    @java.lang.Override
    public boolean hasDeliveryDateStart() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int64 delivery_date_start = 5;</code>
     *
     * @return The deliveryDateStart.
     */
    @java.lang.Override
    public long getDeliveryDateStart() {
        return deliveryDateStart_;
    }

    /**
     * <code>optional int64 delivery_date_start = 5;</code>
     *
     * @param value The deliveryDateStart to set.
     */
    private void setDeliveryDateStart(long value) {
        bitField0_ |= 0x00000004;
        deliveryDateStart_ = value;
    }

    /**
     * <code>optional int64 delivery_date_start = 5;</code>
     */
    private void clearDeliveryDateStart() {
        bitField0_ &= ~0x00000004;
        deliveryDateStart_ = 0L;
    }

    public static final int DELIVERY_DATE_END_FIELD_NUMBER = 6;
    private long deliveryDateEnd_;

    /**
     * <code>optional int64 delivery_date_end = 6;</code>
     *
     * @return Whether the deliveryDateEnd field is set.
     */
    @java.lang.Override
    public boolean hasDeliveryDateEnd() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int64 delivery_date_end = 6;</code>
     *
     * @return The deliveryDateEnd.
     */
    @java.lang.Override
    public long getDeliveryDateEnd() {
        return deliveryDateEnd_;
    }

    /**
     * <code>optional int64 delivery_date_end = 6;</code>
     *
     * @param value The deliveryDateEnd to set.
     */
    private void setDeliveryDateEnd(long value) {
        bitField0_ |= 0x00000008;
        deliveryDateEnd_ = value;
    }

    /**
     * <code>optional int64 delivery_date_end = 6;</code>
     */
    private void clearDeliveryDateEnd() {
        bitField0_ &= ~0x00000008;
        deliveryDateEnd_ = 0L;
    }

    public static final int MAX_COUNT_FIELD_NUMBER = 7;
    private int maxCount_;

    /**
     * <pre>
     * Option
     * </pre>
     * 
     * <code>optional int32 max_count = 7;</code>
     *
     * @return Whether the maxCount field is set.
     */
    @java.lang.Override
    public boolean hasMaxCount() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <pre>
     * Option
     * </pre>
     * 
     * <code>optional int32 max_count = 7;</code>
     *
     * @return The maxCount.
     */
    @java.lang.Override
    public int getMaxCount() {
        return maxCount_;
    }

    /**
     * <pre>
     * Option
     * </pre>
     * 
     * <code>optional int32 max_count = 7;</code>
     *
     * @param value The maxCount to set.
     */
    private void setMaxCount(int value) {
        bitField0_ |= 0x00000010;
        maxCount_ = value;
    }

    /**
     * <pre>
     * Option
     * </pre>
     * 
     * <code>optional int32 max_count = 7;</code>
     */
    private void clearMaxCount() {
        bitField0_ &= ~0x00000010;
        maxCount_ = 0;
    }

    public static final int WITH_TOTAL_FIELD_NUMBER = 8;
    private boolean withTotal_;

    /**
     * <pre>
     * Command
     * </pre>
     * 
     * <code>bool with_total = 8;</code>
     *
     * @return The withTotal.
     */
    @java.lang.Override
    public boolean getWithTotal() {
        return withTotal_;
    }

    /**
     * <pre>
     * Command
     * </pre>
     * 
     * <code>bool with_total = 8;</code>
     *
     * @param value The withTotal to set.
     */
    private void setWithTotal(boolean value) {

        withTotal_ = value;
    }

    /**
     * <pre>
     * Command
     * </pre>
     * 
     * <code>bool with_total = 8;</code>
     */
    private void clearWithTotal() {

        withTotal_ = false;
    }

    public static final int DESCENDING_FIELD_NUMBER = 9;
    private boolean descending_;

    /**
     * <pre>
     * Option
     * TODO: reorder
     * </pre>
     * 
     * <code>optional bool descending = 9;</code>
     *
     * @return Whether the descending field is set.
     */
    @java.lang.Override
    public boolean hasDescending() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <pre>
     * Option
     * TODO: reorder
     * </pre>
     * 
     * <code>optional bool descending = 9;</code>
     *
     * @return The descending.
     */
    @java.lang.Override
    public boolean getDescending() {
        return descending_;
    }

    /**
     * <pre>
     * Option
     * TODO: reorder
     * </pre>
     * 
     * <code>optional bool descending = 9;</code>
     *
     * @param value The descending to set.
     */
    private void setDescending(boolean value) {
        bitField0_ |= 0x00000020;
        descending_ = value;
    }

    /**
     * <pre>
     * Option
     * TODO: reorder
     * </pre>
     * 
     * <code>optional bool descending = 9;</code>
     */
    private void clearDescending() {
        bitField0_ &= ~0x00000020;
        descending_ = false;
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest parseFrom(
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
            im.turms.client.model.proto.request.message.QueryMessagesRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryMessagesRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.message.QueryMessagesRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMessagesRequest)
            im.turms.client.model.proto.request.message.QueryMessagesRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.message.QueryMessagesRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Filter
         * </pre>
         * 
         * <code>repeated int64 ids = 1;</code>
         *
         * @return A list containing the ids.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getIdsList() {
            return java.util.Collections.unmodifiableList(instance.getIdsList());
        }

        /**
         * <pre>
         * Filter
         * </pre>
         * 
         * <code>repeated int64 ids = 1;</code>
         *
         * @return The count of ids.
         */
        @java.lang.Override
        public int getIdsCount() {
            return instance.getIdsCount();
        }

        /**
         * <pre>
         * Filter
         * </pre>
         * 
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
         * <pre>
         * Filter
         * </pre>
         * 
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
         * <pre>
         * Filter
         * </pre>
         * 
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
         * <pre>
         * Filter
         * </pre>
         * 
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
         * <pre>
         * Filter
         * </pre>
         * 
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
         * <code>optional bool are_group_messages = 2;</code>
         *
         * @return Whether the areGroupMessages field is set.
         */
        @java.lang.Override
        public boolean hasAreGroupMessages() {
            return instance.hasAreGroupMessages();
        }

        /**
         * <code>optional bool are_group_messages = 2;</code>
         *
         * @return The areGroupMessages.
         */
        @java.lang.Override
        public boolean getAreGroupMessages() {
            return instance.getAreGroupMessages();
        }

        /**
         * <code>optional bool are_group_messages = 2;</code>
         *
         * @param value The areGroupMessages to set.
         * @return This builder for chaining.
         */
        public Builder setAreGroupMessages(boolean value) {
            copyOnWrite();
            instance.setAreGroupMessages(value);
            return this;
        }

        /**
         * <code>optional bool are_group_messages = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAreGroupMessages() {
            copyOnWrite();
            instance.clearAreGroupMessages();
            return this;
        }

        /**
         * <code>optional bool are_system_messages = 3;</code>
         *
         * @return Whether the areSystemMessages field is set.
         */
        @java.lang.Override
        public boolean hasAreSystemMessages() {
            return instance.hasAreSystemMessages();
        }

        /**
         * <code>optional bool are_system_messages = 3;</code>
         *
         * @return The areSystemMessages.
         */
        @java.lang.Override
        public boolean getAreSystemMessages() {
            return instance.getAreSystemMessages();
        }

        /**
         * <code>optional bool are_system_messages = 3;</code>
         *
         * @param value The areSystemMessages to set.
         * @return This builder for chaining.
         */
        public Builder setAreSystemMessages(boolean value) {
            copyOnWrite();
            instance.setAreSystemMessages(value);
            return this;
        }

        /**
         * <code>optional bool are_system_messages = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAreSystemMessages() {
            copyOnWrite();
            instance.clearAreSystemMessages();
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @return A list containing the fromIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getFromIdsList() {
            return java.util.Collections.unmodifiableList(instance.getFromIdsList());
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @return The count of fromIds.
         */
        @java.lang.Override
        public int getFromIdsCount() {
            return instance.getFromIdsCount();
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param index The index of the element to return.
         * @return The fromIds at the given index.
         */
        @java.lang.Override
        public long getFromIds(int index) {
            return instance.getFromIds(index);
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param value The fromIds to set.
         * @return This builder for chaining.
         */
        public Builder setFromIds(int index, long value) {
            copyOnWrite();
            instance.setFromIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param value The fromIds to add.
         * @return This builder for chaining.
         */
        public Builder addFromIds(long value) {
            copyOnWrite();
            instance.addFromIds(value);
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param values The fromIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllFromIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllFromIds(values);
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFromIds() {
            copyOnWrite();
            instance.clearFromIds();
            return this;
        }

        /**
         * <code>optional int64 delivery_date_start = 5;</code>
         *
         * @return Whether the deliveryDateStart field is set.
         */
        @java.lang.Override
        public boolean hasDeliveryDateStart() {
            return instance.hasDeliveryDateStart();
        }

        /**
         * <code>optional int64 delivery_date_start = 5;</code>
         *
         * @return The deliveryDateStart.
         */
        @java.lang.Override
        public long getDeliveryDateStart() {
            return instance.getDeliveryDateStart();
        }

        /**
         * <code>optional int64 delivery_date_start = 5;</code>
         *
         * @param value The deliveryDateStart to set.
         * @return This builder for chaining.
         */
        public Builder setDeliveryDateStart(long value) {
            copyOnWrite();
            instance.setDeliveryDateStart(value);
            return this;
        }

        /**
         * <code>optional int64 delivery_date_start = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeliveryDateStart() {
            copyOnWrite();
            instance.clearDeliveryDateStart();
            return this;
        }

        /**
         * <code>optional int64 delivery_date_end = 6;</code>
         *
         * @return Whether the deliveryDateEnd field is set.
         */
        @java.lang.Override
        public boolean hasDeliveryDateEnd() {
            return instance.hasDeliveryDateEnd();
        }

        /**
         * <code>optional int64 delivery_date_end = 6;</code>
         *
         * @return The deliveryDateEnd.
         */
        @java.lang.Override
        public long getDeliveryDateEnd() {
            return instance.getDeliveryDateEnd();
        }

        /**
         * <code>optional int64 delivery_date_end = 6;</code>
         *
         * @param value The deliveryDateEnd to set.
         * @return This builder for chaining.
         */
        public Builder setDeliveryDateEnd(long value) {
            copyOnWrite();
            instance.setDeliveryDateEnd(value);
            return this;
        }

        /**
         * <code>optional int64 delivery_date_end = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeliveryDateEnd() {
            copyOnWrite();
            instance.clearDeliveryDateEnd();
            return this;
        }

        /**
         * <pre>
         * Option
         * </pre>
         * 
         * <code>optional int32 max_count = 7;</code>
         *
         * @return Whether the maxCount field is set.
         */
        @java.lang.Override
        public boolean hasMaxCount() {
            return instance.hasMaxCount();
        }

        /**
         * <pre>
         * Option
         * </pre>
         * 
         * <code>optional int32 max_count = 7;</code>
         *
         * @return The maxCount.
         */
        @java.lang.Override
        public int getMaxCount() {
            return instance.getMaxCount();
        }

        /**
         * <pre>
         * Option
         * </pre>
         * 
         * <code>optional int32 max_count = 7;</code>
         *
         * @param value The maxCount to set.
         * @return This builder for chaining.
         */
        public Builder setMaxCount(int value) {
            copyOnWrite();
            instance.setMaxCount(value);
            return this;
        }

        /**
         * <pre>
         * Option
         * </pre>
         * 
         * <code>optional int32 max_count = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxCount() {
            copyOnWrite();
            instance.clearMaxCount();
            return this;
        }

        /**
         * <pre>
         * Command
         * </pre>
         * 
         * <code>bool with_total = 8;</code>
         *
         * @return The withTotal.
         */
        @java.lang.Override
        public boolean getWithTotal() {
            return instance.getWithTotal();
        }

        /**
         * <pre>
         * Command
         * </pre>
         * 
         * <code>bool with_total = 8;</code>
         *
         * @param value The withTotal to set.
         * @return This builder for chaining.
         */
        public Builder setWithTotal(boolean value) {
            copyOnWrite();
            instance.setWithTotal(value);
            return this;
        }

        /**
         * <pre>
         * Command
         * </pre>
         * 
         * <code>bool with_total = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithTotal() {
            copyOnWrite();
            instance.clearWithTotal();
            return this;
        }

        /**
         * <pre>
         * Option
         * TODO: reorder
         * </pre>
         * 
         * <code>optional bool descending = 9;</code>
         *
         * @return Whether the descending field is set.
         */
        @java.lang.Override
        public boolean hasDescending() {
            return instance.hasDescending();
        }

        /**
         * <pre>
         * Option
         * TODO: reorder
         * </pre>
         * 
         * <code>optional bool descending = 9;</code>
         *
         * @return The descending.
         */
        @java.lang.Override
        public boolean getDescending() {
            return instance.getDescending();
        }

        /**
         * <pre>
         * Option
         * TODO: reorder
         * </pre>
         * 
         * <code>optional bool descending = 9;</code>
         *
         * @param value The descending to set.
         * @return This builder for chaining.
         */
        public Builder setDescending(boolean value) {
            copyOnWrite();
            instance.setDescending(value);
            return this;
        }

        /**
         * <pre>
         * Option
         * TODO: reorder
         * </pre>
         * 
         * <code>optional bool descending = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDescending() {
            copyOnWrite();
            instance.clearDescending();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMessagesRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.message.QueryMessagesRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "ids_",
                        "areGroupMessages_",
                        "areSystemMessages_",
                        "fromIds_",
                        "deliveryDateStart_",
                        "deliveryDateEnd_",
                        "maxCount_",
                        "withTotal_",
                        "descending_",};
                java.lang.String info =
                        "\u0000\t\u0000\u0001\u0001\t\t\u0000\u0002\u0000\u0001%\u0002\u1007\u0000\u0003\u1007"
                                + "\u0001\u0004%\u0005\u1002\u0002\u0006\u1002\u0003\u0007\u1004\u0004\b\u0007\t\u1007"
                                + "\u0005";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.message.QueryMessagesRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.message.QueryMessagesRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMessagesRequest)
    private static final im.turms.client.model.proto.request.message.QueryMessagesRequest DEFAULT_INSTANCE;

    static {
        QueryMessagesRequest defaultInstance = new QueryMessagesRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(QueryMessagesRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.message.QueryMessagesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryMessagesRequest> PARSER;

    public static com.google.protobuf.Parser<QueryMessagesRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}