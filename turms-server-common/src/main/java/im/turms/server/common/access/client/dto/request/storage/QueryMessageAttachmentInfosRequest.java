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

package im.turms.server.common.access.client.dto.request.storage;

/**
 * Protobuf type {@code im.turms.proto.QueryMessageAttachmentInfosRequest}
 */
public final class QueryMessageAttachmentInfosRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryMessageAttachmentInfosRequest)
        QueryMessageAttachmentInfosRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryMessageAttachmentInfosRequest.class.getName());
    }

    // Use QueryMessageAttachmentInfosRequest.newBuilder() to construct.
    private QueryMessageAttachmentInfosRequest(
            com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private QueryMessageAttachmentInfosRequest() {
        userIds_ = emptyLongList();
        groupIds_ = emptyLongList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass.internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass.internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.class,
                        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.Builder.class);
    }

    private int bitField0_;
    public static final int USER_IDS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList userIds_ = emptyLongList();

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
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    public long getUserIds(int index) {
        return userIds_.getLong(index);
    }

    private int userIdsMemoizedSerializedSize = -1;

    public static final int GROUP_IDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList groupIds_ = emptyLongList();

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
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    public long getGroupIds(int index) {
        return groupIds_.getLong(index);
    }

    private int groupIdsMemoizedSerializedSize = -1;

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
        if (!getUserIdsList().isEmpty()) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(userIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < userIds_.size(); i++) {
            output.writeInt64NoTag(userIds_.getLong(i));
        }
        if (!getGroupIdsList().isEmpty()) {
            output.writeUInt32NoTag(18);
            output.writeUInt32NoTag(groupIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < groupIds_.size(); i++) {
            output.writeInt64NoTag(groupIds_.getLong(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(3, creationDateStart_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(4, creationDateEnd_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeBool(5, inPrivateConversation_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeBool(6, areSharedByMe_);
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
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, creationDateStart_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, creationDateEnd_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(5,
                    inPrivateConversation_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(6, areSharedByMe_);
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
        if (!(obj instanceof QueryMessageAttachmentInfosRequest other)) {
            return super.equals(obj);
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
        if (hasInPrivateConversation() != other.hasInPrivateConversation()) {
            return false;
        }
        if (hasInPrivateConversation()) {
            if (getInPrivateConversation() != other.getInPrivateConversation()) {
                return false;
            }
        }
        if (hasAreSharedByMe() != other.hasAreSharedByMe()) {
            return false;
        }
        if (hasAreSharedByMe()) {
            if (getAreSharedByMe() != other.getAreSharedByMe()) {
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
        if (hasInPrivateConversation()) {
            hash = (37 * hash) + IN_PRIVATE_CONVERSATION_FIELD_NUMBER;
            hash = (53 * hash)
                    + com.google.protobuf.Internal.hashBoolean(getInPrivateConversation());
        }
        if (hasAreSharedByMe()) {
            hash = (37 * hash) + ARE_SHARED_BY_ME_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getAreSharedByMe());
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.QueryMessageAttachmentInfosRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryMessageAttachmentInfosRequest)
            im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass.internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass.internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.class,
                            im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userIds_ = emptyLongList();
            groupIds_ = emptyLongList();
            creationDateStart_ = 0L;
            creationDateEnd_ = 0L;
            inPrivateConversation_ = false;
            areSharedByMe_ = false;
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000040;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass.internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest build() {
            im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest result =
                    new im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000040) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000040;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                userIds_.makeImmutable();
                result.userIds_ = userIds_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                groupIds_.makeImmutable();
                result.groupIds_ = groupIds_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.creationDateStart_ = creationDateStart_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.creationDateEnd_ = creationDateEnd_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.inPrivateConversation_ = inPrivateConversation_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.areSharedByMe_ = areSharedByMe_;
                to_bitField0_ |= 0x00000008;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.userIds_.isEmpty()) {
                if (userIds_.isEmpty()) {
                    userIds_ = other.userIds_;
                    userIds_.makeImmutable();
                    bitField0_ |= 0x00000001;
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
                    bitField0_ |= 0x00000002;
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
            if (other.hasInPrivateConversation()) {
                setInPrivateConversation(other.getInPrivateConversation());
            }
            if (other.hasAreSharedByMe()) {
                setAreSharedByMe(other.getAreSharedByMe());
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000040;
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
                        bitField0_ &= ~0x00000040;
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
                            ensureUserIdsIsMutable();
                            userIds_.addLong(v);
                        } // case 8
                        case 10 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureUserIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                userIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 10
                        case 16 -> {
                            long v = input.readInt64();
                            ensureGroupIdsIsMutable();
                            groupIds_.addLong(v);
                        } // case 16
                        case 18 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureGroupIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                groupIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 18
                        case 24 -> {
                            creationDateStart_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            creationDateEnd_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            inPrivateConversation_ = input.readBool();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            areSharedByMe_ = input.readBool();
                            bitField0_ |= 0x00000020;
                        } // case 48
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

        private com.google.protobuf.Internal.LongList userIds_ = emptyLongList();

        private void ensureUserIdsIsMutable() {
            if (!userIds_.isModifiable()) {
                userIds_ = makeMutableCopy(userIds_);
            }
            bitField0_ |= 0x00000001;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return A list containing the userIds.
         */
        public java.util.List<java.lang.Long> getUserIdsList() {
            userIds_.makeImmutable();
            return userIds_;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return The count of userIds.
         */
        public int getUserIdsCount() {
            return userIds_.size();
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The userIds at the given index.
         */
        public long getUserIds(int index) {
            return userIds_.getLong(index);
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The userIds to set.
         * @return This builder for chaining.
         */
        public Builder setUserIds(int index, long value) {

            ensureUserIdsIsMutable();
            userIds_.setLong(index, value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param value The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addUserIds(long value) {

            ensureUserIdsIsMutable();
            userIds_.addLong(value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @param values The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureUserIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, userIds_);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIds() {
            userIds_ = emptyLongList();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList groupIds_ = emptyLongList();

        private void ensureGroupIdsIsMutable() {
            if (!groupIds_.isModifiable()) {
                groupIds_ = makeMutableCopy(groupIds_);
            }
            bitField0_ |= 0x00000002;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return A list containing the groupIds.
         */
        public java.util.List<java.lang.Long> getGroupIdsList() {
            groupIds_.makeImmutable();
            return groupIds_;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return The count of groupIds.
         */
        public int getGroupIdsCount() {
            return groupIds_.size();
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIds at the given index.
         */
        public long getGroupIds(int index) {
            return groupIds_.getLong(index);
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The groupIds to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIds(int index, long value) {

            ensureGroupIdsIsMutable();
            groupIds_.setLong(index, value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param value The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addGroupIds(long value) {

            ensureGroupIdsIsMutable();
            groupIds_.addLong(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param values The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureGroupIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupIds_);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIds() {
            groupIds_ = emptyLongList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        private long creationDateStart_;

        /**
         * <code>optional int64 creation_date_start = 3;</code>
         *
         * @return Whether the creationDateStart field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateStart() {
            return ((bitField0_ & 0x00000004) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCreationDateStart(long value) {

            creationDateStart_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 creation_date_start = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateStart() {
            bitField0_ &= ~0x00000004;
            creationDateStart_ = 0L;
            onChanged();
            return this;
        }

        private long creationDateEnd_;

        /**
         * <code>optional int64 creation_date_end = 4;</code>
         *
         * @return Whether the creationDateEnd field is set.
         */
        @java.lang.Override
        public boolean hasCreationDateEnd() {
            return ((bitField0_ & 0x00000008) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCreationDateEnd(long value) {

            creationDateEnd_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 creation_date_end = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDateEnd() {
            bitField0_ &= ~0x00000008;
            creationDateEnd_ = 0L;
            onChanged();
            return this;
        }

        private boolean inPrivateConversation_;

        /**
         * <code>optional bool in_private_conversation = 5;</code>
         *
         * @return Whether the inPrivateConversation field is set.
         */
        @java.lang.Override
        public boolean hasInPrivateConversation() {
            return ((bitField0_ & 0x00000010) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setInPrivateConversation(boolean value) {

            inPrivateConversation_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool in_private_conversation = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInPrivateConversation() {
            bitField0_ &= ~0x00000010;
            inPrivateConversation_ = false;
            onChanged();
            return this;
        }

        private boolean areSharedByMe_;

        /**
         * <code>optional bool are_shared_by_me = 6;</code>
         *
         * @return Whether the areSharedByMe field is set.
         */
        @java.lang.Override
        public boolean hasAreSharedByMe() {
            return ((bitField0_ & 0x00000020) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setAreSharedByMe(boolean value) {

            areSharedByMe_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool are_shared_by_me = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAreSharedByMe() {
            bitField0_ &= ~0x00000020;
            areSharedByMe_ = false;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000040) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000040;
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
                bitField0_ &= ~0x00000040;
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
                        ((bitField0_ & 0x00000040) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryMessageAttachmentInfosRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryMessageAttachmentInfosRequest)
    private static final im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest();
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryMessageAttachmentInfosRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryMessageAttachmentInfosRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryMessageAttachmentInfosRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryMessageAttachmentInfosRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}