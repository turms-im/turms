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

package im.turms.server.common.access.client.dto.request.message;

/**
 * Protobuf type {@code im.turms.proto.QueryMessagesRequest}
 */
public final class QueryMessagesRequest extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMessagesRequest)
        QueryMessagesRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use QueryMessagesRequest.newBuilder() to construct.
    private QueryMessagesRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private QueryMessagesRequest() {
        ids_ = emptyLongList();
        fromIds_ = emptyLongList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new QueryMessagesRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass.internal_static_im_turms_proto_QueryMessagesRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass.internal_static_im_turms_proto_QueryMessagesRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.class,
                        im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.Builder.class);
    }

    private int bitField0_;
    public static final int IDS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
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
    public long getIds(int index) {
        return ids_.getLong(index);
    }

    private int idsMemoizedSerializedSize = -1;

    public static final int ARE_GROUP_MESSAGES_FIELD_NUMBER = 2;
    private boolean areGroupMessages_ = false;

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

    public static final int ARE_SYSTEM_MESSAGES_FIELD_NUMBER = 3;
    private boolean areSystemMessages_ = false;

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

    public static final int FROM_IDS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
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
    public int getFromIdsCount() {
        return fromIds_.size();
    }

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @param index The index of the element to return.
     * @return The fromIds at the given index.
     */
    public long getFromIds(int index) {
        return fromIds_.getLong(index);
    }

    private int fromIdsMemoizedSerializedSize = -1;

    public static final int DELIVERY_DATE_START_FIELD_NUMBER = 5;
    private long deliveryDateStart_ = 0L;

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

    public static final int DELIVERY_DATE_END_FIELD_NUMBER = 6;
    private long deliveryDateEnd_ = 0L;

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

    public static final int MAX_COUNT_FIELD_NUMBER = 7;
    private int maxCount_ = 0;

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

    public static final int WITH_TOTAL_FIELD_NUMBER = 8;
    private boolean withTotal_ = false;

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

    public static final int DESCENDING_FIELD_NUMBER = 9;
    private boolean descending_ = false;

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

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
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
        getSerializedSize();
        if (getIdsList().size() > 0) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(idsMemoizedSerializedSize);
        }
        for (int i = 0; i < ids_.size(); i++) {
            output.writeInt64NoTag(ids_.getLong(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeBool(2, areGroupMessages_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeBool(3, areSystemMessages_);
        }
        if (getFromIdsList().size() > 0) {
            output.writeUInt32NoTag(34);
            output.writeUInt32NoTag(fromIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < fromIds_.size(); i++) {
            output.writeInt64NoTag(fromIds_.getLong(i));
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt64(5, deliveryDateStart_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt64(6, deliveryDateEnd_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt32(7, maxCount_);
        }
        if (withTotal_) {
            output.writeBool(8, withTotal_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeBool(9, descending_);
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
        {
            int dataSize = 0;
            for (int i = 0; i < ids_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(ids_.getLong(i));
            }
            size += dataSize;
            if (!getIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            idsMemoizedSerializedSize = dataSize;
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(2, areGroupMessages_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, areSystemMessages_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < fromIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(fromIds_.getLong(i));
            }
            size += dataSize;
            if (!getFromIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            fromIdsMemoizedSerializedSize = dataSize;
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, deliveryDateStart_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, deliveryDateEnd_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(7, maxCount_);
        }
        if (withTotal_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(8, withTotal_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(9, descending_);
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
        if (!(obj instanceof QueryMessagesRequest other)) {
            return super.equals(obj);
        }

        if (!getIdsList().equals(other.getIdsList())) {
            return false;
        }
        if (hasAreGroupMessages() != other.hasAreGroupMessages()) {
            return false;
        }
        if (hasAreGroupMessages()) {
            if (getAreGroupMessages() != other.getAreGroupMessages()) {
                return false;
            }
        }
        if (hasAreSystemMessages() != other.hasAreSystemMessages()) {
            return false;
        }
        if (hasAreSystemMessages()) {
            if (getAreSystemMessages() != other.getAreSystemMessages()) {
                return false;
            }
        }
        if (!getFromIdsList().equals(other.getFromIdsList())) {
            return false;
        }
        if (hasDeliveryDateStart() != other.hasDeliveryDateStart()) {
            return false;
        }
        if (hasDeliveryDateStart()) {
            if (getDeliveryDateStart() != other.getDeliveryDateStart()) {
                return false;
            }
        }
        if (hasDeliveryDateEnd() != other.hasDeliveryDateEnd()) {
            return false;
        }
        if (hasDeliveryDateEnd()) {
            if (getDeliveryDateEnd() != other.getDeliveryDateEnd()) {
                return false;
            }
        }
        if (hasMaxCount() != other.hasMaxCount()) {
            return false;
        }
        if (hasMaxCount()) {
            if (getMaxCount() != other.getMaxCount()) {
                return false;
            }
        }
        if (getWithTotal() != other.getWithTotal()) {
            return false;
        }
        if (hasDescending() != other.hasDescending()) {
            return false;
        }
        if (hasDescending()) {
            if (getDescending() != other.getDescending()) {
                return false;
            }
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
        if (getIdsCount() > 0) {
            hash = (37 * hash) + IDS_FIELD_NUMBER;
            hash = (53 * hash) + getIdsList().hashCode();
        }
        if (hasAreGroupMessages()) {
            hash = (37 * hash) + ARE_GROUP_MESSAGES_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAreGroupMessages());
        }
        if (hasAreSystemMessages()) {
            hash = (37 * hash) + ARE_SYSTEM_MESSAGES_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAreSystemMessages());
        }
        if (getFromIdsCount() > 0) {
            hash = (37 * hash) + FROM_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getFromIdsList().hashCode();
        }
        if (hasDeliveryDateStart()) {
            hash = (37 * hash) + DELIVERY_DATE_START_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDeliveryDateStart());
        }
        if (hasDeliveryDateEnd()) {
            hash = (37 * hash) + DELIVERY_DATE_END_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDeliveryDateEnd());
        }
        if (hasMaxCount()) {
            hash = (37 * hash) + MAX_COUNT_FIELD_NUMBER;
            hash = (53 * hash) + getMaxCount();
        }
        hash = (37 * hash) + WITH_TOTAL_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getWithTotal());
        if (hasDescending()) {
            hash = (37 * hash) + DESCENDING_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getDescending());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
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
            im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest prototype) {
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
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryMessagesRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMessagesRequest)
            im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass.internal_static_im_turms_proto_QueryMessagesRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass.internal_static_im_turms_proto_QueryMessagesRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.class,
                            im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            ids_ = emptyLongList();
            areGroupMessages_ = false;
            areSystemMessages_ = false;
            fromIds_ = emptyLongList();
            deliveryDateStart_ = 0L;
            deliveryDateEnd_ = 0L;
            maxCount_ = 0;
            withTotal_ = false;
            descending_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass.internal_static_im_turms_proto_QueryMessagesRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest build() {
            im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest result =
                    new im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest result) {
            if (((bitField0_ & 0x00000001) != 0)) {
                ids_.makeImmutable();
                bitField0_ &= ~0x00000001;
            }
            result.ids_ = ids_;
            if (((bitField0_ & 0x00000008) != 0)) {
                fromIds_.makeImmutable();
                bitField0_ &= ~0x00000008;
            }
            result.fromIds_ = fromIds_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.areGroupMessages_ = areGroupMessages_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.areSystemMessages_ = areSystemMessages_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.deliveryDateStart_ = deliveryDateStart_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.deliveryDateEnd_ = deliveryDateEnd_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.maxCount_ = maxCount_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.withTotal_ = withTotal_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.descending_ = descending_;
                to_bitField0_ |= 0x00000020;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.ids_.isEmpty()) {
                if (ids_.isEmpty()) {
                    ids_ = other.ids_;
                    bitField0_ &= ~0x00000001;
                } else {
                    ensureIdsIsMutable();
                    ids_.addAll(other.ids_);
                }
                onChanged();
            }
            if (other.hasAreGroupMessages()) {
                setAreGroupMessages(other.getAreGroupMessages());
            }
            if (other.hasAreSystemMessages()) {
                setAreSystemMessages(other.getAreSystemMessages());
            }
            if (!other.fromIds_.isEmpty()) {
                if (fromIds_.isEmpty()) {
                    fromIds_ = other.fromIds_;
                    bitField0_ &= ~0x00000008;
                } else {
                    ensureFromIdsIsMutable();
                    fromIds_.addAll(other.fromIds_);
                }
                onChanged();
            }
            if (other.hasDeliveryDateStart()) {
                setDeliveryDateStart(other.getDeliveryDateStart());
            }
            if (other.hasDeliveryDateEnd()) {
                setDeliveryDateEnd(other.getDeliveryDateEnd());
            }
            if (other.hasMaxCount()) {
                setMaxCount(other.getMaxCount());
            }
            if (other.getWithTotal()) {
                setWithTotal(other.getWithTotal());
            }
            if (other.hasDescending()) {
                setDescending(other.getDescending());
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
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
                        case 8 -> {
                            long v = input.readInt64();
                            ensureIdsIsMutable();
                            ids_.addLong(v);
                        } // case 8
                        case 10 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                ids_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 10
                        case 16 -> {
                            areGroupMessages_ = input.readBool();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            areSystemMessages_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            long v = input.readInt64();
                            ensureFromIdsIsMutable();
                            fromIds_.addLong(v);
                        } // case 32
                        case 34 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureFromIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                fromIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 34
                        case 40 -> {
                            deliveryDateStart_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            deliveryDateEnd_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            maxCount_ = input.readInt32();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 64 -> {
                            withTotal_ = input.readBool();
                            bitField0_ |= 0x00000080;
                        } // case 64
                        case 72 -> {
                            descending_ = input.readBool();
                            bitField0_ |= 0x00000100;
                        } // case 72
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

        private com.google.protobuf.Internal.LongList ids_ = emptyLongList();

        private void ensureIdsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                ids_ = mutableCopy(ids_);
                bitField0_ |= 0x00000001;
            }
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
        public java.util.List<java.lang.Long> getIdsList() {
            return ((bitField0_ & 0x00000001) != 0)
                    ? java.util.Collections.unmodifiableList(ids_)
                    : ids_;
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
        public long getIds(int index) {
            return ids_.getLong(index);
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
         * @return This builder for chaining.
         */
        public Builder setIds(int index, long value) {

            ensureIdsIsMutable();
            ids_.setLong(index, value);
            onChanged();
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

            ensureIdsIsMutable();
            ids_.addLong(value);
            onChanged();
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
            ensureIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, ids_);
            onChanged();
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
            ids_ = emptyLongList();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        private boolean areGroupMessages_;

        /**
         * <code>optional bool are_group_messages = 2;</code>
         *
         * @return Whether the areGroupMessages field is set.
         */
        @java.lang.Override
        public boolean hasAreGroupMessages() {
            return ((bitField0_ & 0x00000002) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setAreGroupMessages(boolean value) {

            areGroupMessages_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool are_group_messages = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAreGroupMessages() {
            bitField0_ &= ~0x00000002;
            areGroupMessages_ = false;
            onChanged();
            return this;
        }

        private boolean areSystemMessages_;

        /**
         * <code>optional bool are_system_messages = 3;</code>
         *
         * @return Whether the areSystemMessages field is set.
         */
        @java.lang.Override
        public boolean hasAreSystemMessages() {
            return ((bitField0_ & 0x00000004) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setAreSystemMessages(boolean value) {

            areSystemMessages_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool are_system_messages = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAreSystemMessages() {
            bitField0_ &= ~0x00000004;
            areSystemMessages_ = false;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList fromIds_ = emptyLongList();

        private void ensureFromIdsIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                fromIds_ = mutableCopy(fromIds_);
                bitField0_ |= 0x00000008;
            }
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @return A list containing the fromIds.
         */
        public java.util.List<java.lang.Long> getFromIdsList() {
            return ((bitField0_ & 0x00000008) != 0)
                    ? java.util.Collections.unmodifiableList(fromIds_)
                    : fromIds_;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @return The count of fromIds.
         */
        public int getFromIdsCount() {
            return fromIds_.size();
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param index The index of the element to return.
         * @return The fromIds at the given index.
         */
        public long getFromIds(int index) {
            return fromIds_.getLong(index);
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param index The index to set the value at.
         * @param value The fromIds to set.
         * @return This builder for chaining.
         */
        public Builder setFromIds(int index, long value) {

            ensureFromIdsIsMutable();
            fromIds_.setLong(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param value The fromIds to add.
         * @return This builder for chaining.
         */
        public Builder addFromIds(long value) {

            ensureFromIdsIsMutable();
            fromIds_.addLong(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @param values The fromIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllFromIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureFromIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, fromIds_);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 from_ids = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFromIds() {
            fromIds_ = emptyLongList();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        private long deliveryDateStart_;

        /**
         * <code>optional int64 delivery_date_start = 5;</code>
         *
         * @return Whether the deliveryDateStart field is set.
         */
        @java.lang.Override
        public boolean hasDeliveryDateStart() {
            return ((bitField0_ & 0x00000010) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setDeliveryDateStart(long value) {

            deliveryDateStart_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 delivery_date_start = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeliveryDateStart() {
            bitField0_ &= ~0x00000010;
            deliveryDateStart_ = 0L;
            onChanged();
            return this;
        }

        private long deliveryDateEnd_;

        /**
         * <code>optional int64 delivery_date_end = 6;</code>
         *
         * @return Whether the deliveryDateEnd field is set.
         */
        @java.lang.Override
        public boolean hasDeliveryDateEnd() {
            return ((bitField0_ & 0x00000020) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setDeliveryDateEnd(long value) {

            deliveryDateEnd_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 delivery_date_end = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeliveryDateEnd() {
            bitField0_ &= ~0x00000020;
            deliveryDateEnd_ = 0L;
            onChanged();
            return this;
        }

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
            return ((bitField0_ & 0x00000040) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setMaxCount(int value) {

            maxCount_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
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
            bitField0_ &= ~0x00000040;
            maxCount_ = 0;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setWithTotal(boolean value) {

            withTotal_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
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
            bitField0_ &= ~0x00000080;
            withTotal_ = false;
            onChanged();
            return this;
        }

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
            return ((bitField0_ & 0x00000100) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setDescending(boolean value) {

            descending_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
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
            bitField0_ &= ~0x00000100;
            descending_ = false;
            onChanged();
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMessagesRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMessagesRequest)
    private static final im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest();
    }

    public static im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryMessagesRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryMessagesRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryMessagesRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryMessagesRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.QueryMessagesRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}