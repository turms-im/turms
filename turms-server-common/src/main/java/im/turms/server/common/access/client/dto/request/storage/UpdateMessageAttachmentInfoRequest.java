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
 * Protobuf type {@code im.turms.proto.UpdateMessageAttachmentInfoRequest}
 */
public final class UpdateMessageAttachmentInfoRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateMessageAttachmentInfoRequest)
        UpdateMessageAttachmentInfoRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateMessageAttachmentInfoRequest.class.getName());
    }

    // Use UpdateMessageAttachmentInfoRequest.newBuilder() to construct.
    private UpdateMessageAttachmentInfoRequest(
            com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateMessageAttachmentInfoRequest() {
        attachmentIdStr_ = "";
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass.internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass.internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.class,
                        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.Builder.class);
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

    public static final int ATTACHMENT_ID_STR_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object attachmentIdStr_ = "";

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
        java.lang.Object ref = attachmentIdStr_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            attachmentIdStr_ = s;
            return s;
        }
    }

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return The bytes for attachmentIdStr.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAttachmentIdStrBytes() {
        java.lang.Object ref = attachmentIdStr_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            attachmentIdStr_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(1, attachmentIdNum_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, attachmentIdStr_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt64(3, userIdToShareWith_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt64(4, userIdToUnshareWith_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt64(5, groupIdToShareWith_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeInt64(6, groupIdToUnshareWith_);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, attachmentIdNum_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, attachmentIdStr_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, userIdToShareWith_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, userIdToUnshareWith_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, groupIdToShareWith_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6,
                    groupIdToUnshareWith_);
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
        if (!(obj instanceof UpdateMessageAttachmentInfoRequest other)) {
            return super.equals(obj);
        }

        if (hasAttachmentIdNum() != other.hasAttachmentIdNum()) {
            return false;
        }
        if (hasAttachmentIdNum()) {
            if (getAttachmentIdNum() != other.getAttachmentIdNum()) {
                return false;
            }
        }
        if (hasAttachmentIdStr() != other.hasAttachmentIdStr()) {
            return false;
        }
        if (hasAttachmentIdStr()) {
            if (!getAttachmentIdStr().equals(other.getAttachmentIdStr())) {
                return false;
            }
        }
        if (hasUserIdToShareWith() != other.hasUserIdToShareWith()) {
            return false;
        }
        if (hasUserIdToShareWith()) {
            if (getUserIdToShareWith() != other.getUserIdToShareWith()) {
                return false;
            }
        }
        if (hasUserIdToUnshareWith() != other.hasUserIdToUnshareWith()) {
            return false;
        }
        if (hasUserIdToUnshareWith()) {
            if (getUserIdToUnshareWith() != other.getUserIdToUnshareWith()) {
                return false;
            }
        }
        if (hasGroupIdToShareWith() != other.hasGroupIdToShareWith()) {
            return false;
        }
        if (hasGroupIdToShareWith()) {
            if (getGroupIdToShareWith() != other.getGroupIdToShareWith()) {
                return false;
            }
        }
        if (hasGroupIdToUnshareWith() != other.hasGroupIdToUnshareWith()) {
            return false;
        }
        if (hasGroupIdToUnshareWith()) {
            if (getGroupIdToUnshareWith() != other.getGroupIdToUnshareWith()) {
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
        if (hasAttachmentIdNum()) {
            hash = (37 * hash) + ATTACHMENT_ID_NUM_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getAttachmentIdNum());
        }
        if (hasAttachmentIdStr()) {
            hash = (37 * hash) + ATTACHMENT_ID_STR_FIELD_NUMBER;
            hash = (53 * hash) + getAttachmentIdStr().hashCode();
        }
        if (hasUserIdToShareWith()) {
            hash = (37 * hash) + USER_ID_TO_SHARE_WITH_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserIdToShareWith());
        }
        if (hasUserIdToUnshareWith()) {
            hash = (37 * hash) + USER_ID_TO_UNSHARE_WITH_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserIdToUnshareWith());
        }
        if (hasGroupIdToShareWith()) {
            hash = (37 * hash) + GROUP_ID_TO_SHARE_WITH_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupIdToShareWith());
        }
        if (hasGroupIdToUnshareWith()) {
            hash = (37 * hash) + GROUP_ID_TO_UNSHARE_WITH_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupIdToUnshareWith());
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateMessageAttachmentInfoRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateMessageAttachmentInfoRequest)
            im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass.internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass.internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.class,
                            im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            attachmentIdNum_ = 0L;
            attachmentIdStr_ = "";
            userIdToShareWith_ = 0L;
            userIdToUnshareWith_ = 0L;
            groupIdToShareWith_ = 0L;
            groupIdToUnshareWith_ = 0L;
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
            return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass.internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest build() {
            im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest result =
                    new im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest result) {
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
                im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.attachmentIdNum_ = attachmentIdNum_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.attachmentIdStr_ = attachmentIdStr_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.userIdToShareWith_ = userIdToShareWith_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.userIdToUnshareWith_ = userIdToUnshareWith_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.groupIdToShareWith_ = groupIdToShareWith_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.groupIdToUnshareWith_ = groupIdToUnshareWith_;
                to_bitField0_ |= 0x00000020;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasAttachmentIdNum()) {
                setAttachmentIdNum(other.getAttachmentIdNum());
            }
            if (other.hasAttachmentIdStr()) {
                attachmentIdStr_ = other.attachmentIdStr_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.hasUserIdToShareWith()) {
                setUserIdToShareWith(other.getUserIdToShareWith());
            }
            if (other.hasUserIdToUnshareWith()) {
                setUserIdToUnshareWith(other.getUserIdToUnshareWith());
            }
            if (other.hasGroupIdToShareWith()) {
                setGroupIdToShareWith(other.getGroupIdToShareWith());
            }
            if (other.hasGroupIdToUnshareWith()) {
                setGroupIdToUnshareWith(other.getGroupIdToUnshareWith());
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
                            attachmentIdNum_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            attachmentIdStr_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            userIdToShareWith_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            userIdToUnshareWith_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            groupIdToShareWith_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            groupIdToUnshareWith_ = input.readInt64();
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
         * @return This builder for chaining.
         */
        public Builder setAttachmentIdNum(long value) {

            attachmentIdNum_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
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
            bitField0_ &= ~0x00000001;
            attachmentIdNum_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object attachmentIdStr_ = "";

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return Whether the attachmentIdStr field is set.
         */
        public boolean hasAttachmentIdStr() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return The attachmentIdStr.
         */
        public java.lang.String getAttachmentIdStr() {
            java.lang.Object ref = attachmentIdStr_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                attachmentIdStr_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return The bytes for attachmentIdStr.
         */
        public com.google.protobuf.ByteString getAttachmentIdStrBytes() {
            java.lang.Object ref = attachmentIdStr_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                attachmentIdStr_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @param value The attachmentIdStr to set.
         * @return This builder for chaining.
         */
        public Builder setAttachmentIdStr(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            attachmentIdStr_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAttachmentIdStr() {
            attachmentIdStr_ = getDefaultInstance().getAttachmentIdStr();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional string attachment_id_str = 2;</code>
         *
         * @param value The bytes for attachmentIdStr to set.
         * @return This builder for chaining.
         */
        public Builder setAttachmentIdStrBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            attachmentIdStr_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setUserIdToShareWith(long value) {

            userIdToShareWith_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
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
            bitField0_ &= ~0x00000004;
            userIdToShareWith_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setUserIdToUnshareWith(long value) {

            userIdToUnshareWith_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 user_id_to_unshare_with = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIdToUnshareWith() {
            bitField0_ &= ~0x00000008;
            userIdToUnshareWith_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setGroupIdToShareWith(long value) {

            groupIdToShareWith_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_id_to_share_with = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIdToShareWith() {
            bitField0_ &= ~0x00000010;
            groupIdToShareWith_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setGroupIdToUnshareWith(long value) {

            groupIdToUnshareWith_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_id_to_unshare_with = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIdToUnshareWith() {
            bitField0_ &= ~0x00000020;
            groupIdToUnshareWith_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateMessageAttachmentInfoRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateMessageAttachmentInfoRequest)
    private static final im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest();
    }

    public static im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateMessageAttachmentInfoRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateMessageAttachmentInfoRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateMessageAttachmentInfoRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateMessageAttachmentInfoRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}