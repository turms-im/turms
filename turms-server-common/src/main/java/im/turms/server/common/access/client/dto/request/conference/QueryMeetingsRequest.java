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

package im.turms.server.common.access.client.dto.request.conference;

/**
 * Protobuf type {@code im.turms.proto.QueryMeetingsRequest}
 */
public final class QueryMeetingsRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMeetingsRequest)
        QueryMeetingsRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryMeetingsRequest.class.getName());
    }

    // Use QueryMeetingsRequest.newBuilder() to construct.
    private QueryMeetingsRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private QueryMeetingsRequest() {
        ids_ = emptyLongList();
        creatorIds_ = emptyLongList();
        userIds_ = emptyLongList();
        groupIds_ = emptyLongList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass.internal_static_im_turms_proto_QueryMeetingsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass.internal_static_im_turms_proto_QueryMeetingsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest.class,
                        im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest.Builder.class);
    }

    private int bitField0_;
    public static final int IDS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList ids_ = emptyLongList();

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
    public int getIdsCount() {
        return ids_.size();
    }

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The ids at the given index.
     */
    public long getIds(int index) {
        return ids_.getLong(index);
    }

    private int idsMemoizedSerializedSize = -1;

    public static final int CREATOR_IDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList creatorIds_ = emptyLongList();

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
    public int getCreatorIdsCount() {
        return creatorIds_.size();
    }

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The creatorIds at the given index.
     */
    public long getCreatorIds(int index) {
        return creatorIds_.getLong(index);
    }

    private int creatorIdsMemoizedSerializedSize = -1;

    public static final int USER_IDS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList userIds_ = emptyLongList();

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
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    public long getUserIds(int index) {
        return userIds_.getLong(index);
    }

    private int userIdsMemoizedSerializedSize = -1;

    public static final int GROUP_IDS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList groupIds_ = emptyLongList();

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
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    public long getGroupIds(int index) {
        return groupIds_.getLong(index);
    }

    private int groupIdsMemoizedSerializedSize = -1;

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

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
            int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
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
        if (!getIdsList().isEmpty()) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(idsMemoizedSerializedSize);
        }
        for (int i = 0; i < ids_.size(); i++) {
            output.writeInt64NoTag(ids_.getLong(i));
        }
        if (!getCreatorIdsList().isEmpty()) {
            output.writeUInt32NoTag(18);
            output.writeUInt32NoTag(creatorIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < creatorIds_.size(); i++) {
            output.writeInt64NoTag(creatorIds_.getLong(i));
        }
        if (!getUserIdsList().isEmpty()) {
            output.writeUInt32NoTag(26);
            output.writeUInt32NoTag(userIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < userIds_.size(); i++) {
            output.writeInt64NoTag(userIds_.getLong(i));
        }
        if (!getGroupIdsList().isEmpty()) {
            output.writeUInt32NoTag(34);
            output.writeUInt32NoTag(groupIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < groupIds_.size(); i++) {
            output.writeInt64NoTag(groupIds_.getLong(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(5, creationDateStart_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(6, creationDateEnd_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt32(10, skip_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt32(11, limit_);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            output.writeMessage(15, value);
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
        {
            int dataSize = 0;
            for (int i = 0; i < creatorIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(creatorIds_.getLong(i));
            }
            size += dataSize;
            if (!getCreatorIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            creatorIdsMemoizedSerializedSize = dataSize;
        }
        {
            int dataSize = 0;
            for (int i = 0; i < userIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(userIds_.getLong(i));
            }
            size += dataSize;
            if (!getUserIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            userIdsMemoizedSerializedSize = dataSize;
        }
        {
            int dataSize = 0;
            for (int i = 0; i < groupIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(groupIds_.getLong(i));
            }
            size += dataSize;
            if (!getGroupIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            groupIdsMemoizedSerializedSize = dataSize;
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, creationDateStart_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, creationDateEnd_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(10, skip_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(11, limit_);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, value);
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
        if (!(obj instanceof QueryMeetingsRequest other)) {
            return super.equals(obj);
        }

        if (!getIdsList().equals(other.getIdsList())) {
            return false;
        }
        if (!getCreatorIdsList().equals(other.getCreatorIdsList())) {
            return false;
        }
        if (!getUserIdsList().equals(other.getUserIdsList())) {
            return false;
        }
        if (!getGroupIdsList().equals(other.getGroupIdsList())) {
            return false;
        }
        if (hasCreationDateStart() != other.hasCreationDateStart()) {
            return false;
        }
        if (hasCreationDateStart()) {
            if (getCreationDateStart() != other.getCreationDateStart()) {
                return false;
            }
        }
        if (hasCreationDateEnd() != other.hasCreationDateEnd()) {
            return false;
        }
        if (hasCreationDateEnd()) {
            if (getCreationDateEnd() != other.getCreationDateEnd()) {
                return false;
            }
        }
        if (hasSkip() != other.hasSkip()) {
            return false;
        }
        if (hasSkip()) {
            if (getSkip() != other.getSkip()) {
                return false;
            }
        }
        if (hasLimit() != other.hasLimit()) {
            return false;
        }
        if (hasLimit()) {
            if (getLimit() != other.getLimit()) {
                return false;
            }
        }
        return getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
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
        if (getCreatorIdsCount() > 0) {
            hash = (37 * hash) + CREATOR_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getCreatorIdsList().hashCode();
        }
        if (getUserIdsCount() > 0) {
            hash = (37 * hash) + USER_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getUserIdsList().hashCode();
        }
        if (getGroupIdsCount() > 0) {
            hash = (37 * hash) + GROUP_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupIdsList().hashCode();
        }
        if (hasCreationDateStart()) {
            hash = (37 * hash) + CREATION_DATE_START_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCreationDateStart());
        }
        if (hasCreationDateEnd()) {
            hash = (37 * hash) + CREATION_DATE_END_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCreationDateEnd());
        }
        if (hasSkip()) {
            hash = (37 * hash) + SKIP_FIELD_NUMBER;
            hash = (53 * hash) + getSkip();
        }
        if (hasLimit()) {
            hash = (37 * hash) + LIMIT_FIELD_NUMBER;
            hash = (53 * hash) + getLimit();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
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
            im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest prototype) {
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
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryMeetingsRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMeetingsRequest)
            im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass.internal_static_im_turms_proto_QueryMeetingsRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass.internal_static_im_turms_proto_QueryMeetingsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest.class,
                            im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            ids_ = emptyLongList();
            creatorIds_ = emptyLongList();
            userIds_ = emptyLongList();
            groupIds_ = emptyLongList();
            creationDateStart_ = 0L;
            creationDateEnd_ = 0L;
            skip_ = 0;
            limit_ = 0;
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000100;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass.internal_static_im_turms_proto_QueryMeetingsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest build() {
            im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest result =
                    new im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000100) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000100;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                ids_.makeImmutable();
                result.ids_ = ids_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                creatorIds_.makeImmutable();
                result.creatorIds_ = creatorIds_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                userIds_.makeImmutable();
                result.userIds_ = userIds_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                groupIds_.makeImmutable();
                result.groupIds_ = groupIds_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.creationDateStart_ = creationDateStart_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.creationDateEnd_ = creationDateEnd_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.skip_ = skip_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.limit_ = limit_;
                to_bitField0_ |= 0x00000008;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.ids_.isEmpty()) {
                if (ids_.isEmpty()) {
                    ids_ = other.ids_;
                    ids_.makeImmutable();
                    bitField0_ |= 0x00000001;
                } else {
                    ensureIdsIsMutable();
                    ids_.addAll(other.ids_);
                }
                onChanged();
            }
            if (!other.creatorIds_.isEmpty()) {
                if (creatorIds_.isEmpty()) {
                    creatorIds_ = other.creatorIds_;
                    creatorIds_.makeImmutable();
                    bitField0_ |= 0x00000002;
                } else {
                    ensureCreatorIdsIsMutable();
                    creatorIds_.addAll(other.creatorIds_);
                }
                onChanged();
            }
            if (!other.userIds_.isEmpty()) {
                if (userIds_.isEmpty()) {
                    userIds_ = other.userIds_;
                    userIds_.makeImmutable();
                    bitField0_ |= 0x00000004;
                } else {
                    ensureUserIdsIsMutable();
                    userIds_.addAll(other.userIds_);
                }
                onChanged();
            }
            if (!other.groupIds_.isEmpty()) {
                if (groupIds_.isEmpty()) {
                    groupIds_ = other.groupIds_;
                    groupIds_.makeImmutable();
                    bitField0_ |= 0x00000008;
                } else {
                    ensureGroupIdsIsMutable();
                    groupIds_.addAll(other.groupIds_);
                }
                onChanged();
            }
            if (other.hasCreationDateStart()) {
                setCreationDateStart(other.getCreationDateStart());
            }
            if (other.hasCreationDateEnd()) {
                setCreationDateEnd(other.getCreationDateEnd());
            }
            if (other.hasSkip()) {
                setSkip(other.getSkip());
            }
            if (other.hasLimit()) {
                setLimit(other.getLimit());
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000100;
                    } else {
                        ensureCustomAttributesIsMutable();
                        customAttributes_.addAll(other.customAttributes_);
                    }
                    onChanged();
                }
            } else {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributesBuilder_.isEmpty()) {
                        customAttributesBuilder_.dispose();
                        customAttributesBuilder_ = null;
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000100;
                        customAttributesBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getCustomAttributesFieldBuilder()
                                        : null;
                    } else {
                        customAttributesBuilder_.addAllMessages(other.customAttributes_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
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
                            long v = input.readInt64();
                            ensureCreatorIdsIsMutable();
                            creatorIds_.addLong(v);
                        } // case 16
                        case 18 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureCreatorIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                creatorIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 18
                        case 24 -> {
                            long v = input.readInt64();
                            ensureUserIdsIsMutable();
                            userIds_.addLong(v);
                        } // case 24
                        case 26 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureUserIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                userIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 26
                        case 32 -> {
                            long v = input.readInt64();
                            ensureGroupIdsIsMutable();
                            groupIds_.addLong(v);
                        } // case 32
                        case 34 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureGroupIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                groupIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 34
                        case 40 -> {
                            creationDateStart_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            creationDateEnd_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 80 -> {
                            skip_ = input.readInt32();
                            bitField0_ |= 0x00000040;
                        } // case 80
                        case 88 -> {
                            limit_ = input.readInt32();
                            bitField0_ |= 0x00000080;
                        } // case 88
                        case 122 -> {
                            im.turms.server.common.access.client.dto.model.common.Value m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.common.Value
                                                    .parser(),
                                            extensionRegistry);
                            if (customAttributesBuilder_ == null) {
                                ensureCustomAttributesIsMutable();
                                customAttributes_.add(m);
                            } else {
                                customAttributesBuilder_.addMessage(m);
                            }
                        } // case 122
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
            if (!ids_.isModifiable()) {
                ids_ = makeMutableCopy(ids_);
            }
            bitField0_ |= 0x00000001;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @return A list containing the ids.
         */
        public java.util.List<java.lang.Long> getIdsList() {
            ids_.makeImmutable();
            return ids_;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @return The count of ids.
         */
        public int getIdsCount() {
            return ids_.size();
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The ids at the given index.
         */
        public long getIds(int index) {
            return ids_.getLong(index);
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The ids to set.
         * @return This builder for chaining.
         */
        public Builder setIds(int index, long value) {

            ensureIdsIsMutable();
            ids_.setLong(index, value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param value The ids to add.
         * @return This builder for chaining.
         */
        public Builder addIds(long value) {

            ensureIdsIsMutable();
            ids_.addLong(value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 ids = 1;</code>
         *
         * @param values The ids to add.
         * @return This builder for chaining.
         */
        public Builder addAllIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, ids_);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
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

        private com.google.protobuf.Internal.LongList creatorIds_ = emptyLongList();

        private void ensureCreatorIdsIsMutable() {
            if (!creatorIds_.isModifiable()) {
                creatorIds_ = makeMutableCopy(creatorIds_);
            }
            bitField0_ |= 0x00000002;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @return A list containing the creatorIds.
         */
        public java.util.List<java.lang.Long> getCreatorIdsList() {
            creatorIds_.makeImmutable();
            return creatorIds_;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @return The count of creatorIds.
         */
        public int getCreatorIdsCount() {
            return creatorIds_.size();
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The creatorIds at the given index.
         */
        public long getCreatorIds(int index) {
            return creatorIds_.getLong(index);
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The creatorIds to set.
         * @return This builder for chaining.
         */
        public Builder setCreatorIds(int index, long value) {

            ensureCreatorIdsIsMutable();
            creatorIds_.setLong(index, value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param value The creatorIds to add.
         * @return This builder for chaining.
         */
        public Builder addCreatorIds(long value) {

            ensureCreatorIdsIsMutable();
            creatorIds_.addLong(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @param values The creatorIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllCreatorIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureCreatorIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, creatorIds_);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 creator_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreatorIds() {
            creatorIds_ = emptyLongList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList userIds_ = emptyLongList();

        private void ensureUserIdsIsMutable() {
            if (!userIds_.isModifiable()) {
                userIds_ = makeMutableCopy(userIds_);
            }
            bitField0_ |= 0x00000004;
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @return A list containing the userIds.
         */
        public java.util.List<java.lang.Long> getUserIdsList() {
            userIds_.makeImmutable();
            return userIds_;
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @return The count of userIds.
         */
        public int getUserIdsCount() {
            return userIds_.size();
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The userIds at the given index.
         */
        public long getUserIds(int index) {
            return userIds_.getLong(index);
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The userIds to set.
         * @return This builder for chaining.
         */
        public Builder setUserIds(int index, long value) {

            ensureUserIdsIsMutable();
            userIds_.setLong(index, value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @param value The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addUserIds(long value) {

            ensureUserIdsIsMutable();
            userIds_.addLong(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @param values The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureUserIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, userIds_);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIds() {
            userIds_ = emptyLongList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList groupIds_ = emptyLongList();

        private void ensureGroupIdsIsMutable() {
            if (!groupIds_.isModifiable()) {
                groupIds_ = makeMutableCopy(groupIds_);
            }
            bitField0_ |= 0x00000008;
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @return A list containing the groupIds.
         */
        public java.util.List<java.lang.Long> getGroupIdsList() {
            groupIds_.makeImmutable();
            return groupIds_;
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @return The count of groupIds.
         */
        public int getGroupIdsCount() {
            return groupIds_.size();
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIds at the given index.
         */
        public long getGroupIds(int index) {
            return groupIds_.getLong(index);
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @param index The index to set the value at.
         * @param value The groupIds to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIds(int index, long value) {

            ensureGroupIdsIsMutable();
            groupIds_.setLong(index, value);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @param value The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addGroupIds(long value) {

            ensureGroupIdsIsMutable();
            groupIds_.addLong(value);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @param values The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureGroupIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupIds_);
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIds() {
            groupIds_ = emptyLongList();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        private long creationDateStart_;

        /**
         * <code>optional int64 creation_date_start = 5;</code>
         *
         * @return Whether the creationDateStart field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateStart() {
            return ((bitField0_ & 0x00000010) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCreationDateStart(long value) {

            creationDateStart_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 creation_date_start = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateStart() {
            bitField0_ &= ~0x00000010;
            creationDateStart_ = 0L;
            onChanged();
            return this;
        }

        private long creationDateEnd_;

        /**
         * <code>optional int64 creation_date_end = 6;</code>
         *
         * @return Whether the creationDateEnd field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateEnd() {
            return ((bitField0_ & 0x00000020) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCreationDateEnd(long value) {

            creationDateEnd_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 creation_date_end = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateEnd() {
            bitField0_ &= ~0x00000020;
            creationDateEnd_ = 0L;
            onChanged();
            return this;
        }

        private int skip_;

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return Whether the skip field is set.
         */
        @java.lang.Override
        public boolean hasSkip() {
            return ((bitField0_ & 0x00000040) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setSkip(int value) {

            skip_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 skip = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSkip() {
            bitField0_ &= ~0x00000040;
            skip_ = 0;
            onChanged();
            return this;
        }

        private int limit_;

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return Whether the limit field is set.
         */
        @java.lang.Override
        public boolean hasLimit() {
            return ((bitField0_ & 0x00000080) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setLimit(int value) {

            limit_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 limit = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLimit() {
            bitField0_ &= ~0x00000080;
            limit_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000100) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000100;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(customAttributes_);
            } else {
                return customAttributesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public int getCustomAttributesCount() {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.size();
            } else {
                return customAttributesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, customAttributes_);
                onChanged();
            } else {
                customAttributesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000100;
                onChanged();
            } else {
                customAttributesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.remove(index);
                onChanged();
            } else {
                customAttributesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        customAttributes_,
                        ((bitField0_ & 0x00000100) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMeetingsRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMeetingsRequest)
    private static final im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest();
    }

    public static im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryMeetingsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryMeetingsRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryMeetingsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryMeetingsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}