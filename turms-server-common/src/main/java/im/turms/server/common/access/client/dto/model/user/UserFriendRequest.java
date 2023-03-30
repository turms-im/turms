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

package im.turms.server.common.access.client.dto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserFriendRequest}
 */
public final class UserFriendRequest extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserFriendRequest)
        UserFriendRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserFriendRequest.newBuilder() to construct.
    private UserFriendRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserFriendRequest() {
        content_ = "";
        requestStatus_ = 0;
        reason_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserFriendRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserFriendRequestOuterClass.internal_static_im_turms_proto_UserFriendRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserFriendRequestOuterClass.internal_static_im_turms_proto_UserFriendRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserFriendRequest.class,
                        im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder.class);
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private long id_ = 0L;

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return Whether the id field is set.
     */
    @java.lang.Override
    public boolean hasId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
        return id_;
    }

    public static final int CREATION_DATE_FIELD_NUMBER = 2;
    private long creationDate_ = 0L;

    /**
     * <code>optional int64 creation_date = 2;</code>
     *
     * @return Whether the creationDate field is set.
     */
    @java.lang.Override
    public boolean hasCreationDate() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 creation_date = 2;</code>
     *
     * @return The creationDate.
     */
    @java.lang.Override
    public long getCreationDate() {
        return creationDate_;
    }

    public static final int CONTENT_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object content_ = "";

    /**
     * <code>optional string content = 3;</code>
     *
     * @return Whether the content field is set.
     */
    @java.lang.Override
    public boolean hasContent() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional string content = 3;</code>
     *
     * @return The content.
     */
    @java.lang.Override
    public java.lang.String getContent() {
        java.lang.Object ref = content_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            content_ = s;
            return s;
        }
    }

    /**
     * <code>optional string content = 3;</code>
     *
     * @return The bytes for content.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getContentBytes() {
        java.lang.Object ref = content_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            content_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int REQUEST_STATUS_FIELD_NUMBER = 4;
    private int requestStatus_ = 0;

    /**
     * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
     *
     * @return Whether the requestStatus field is set.
     */
    @java.lang.Override
    public boolean hasRequestStatus() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
     *
     * @return The enum numeric value on the wire for requestStatus.
     */
    @java.lang.Override
    public int getRequestStatusValue() {
        return requestStatus_;
    }

    /**
     * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
     *
     * @return The requestStatus.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.constant.RequestStatus getRequestStatus() {
        im.turms.server.common.access.client.dto.constant.RequestStatus result =
                im.turms.server.common.access.client.dto.constant.RequestStatus
                        .forNumber(requestStatus_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.RequestStatus.UNRECOGNIZED
                : result;
    }

    public static final int REASON_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object reason_ = "";

    /**
     * <code>optional string reason = 5;</code>
     *
     * @return Whether the reason field is set.
     */
    @java.lang.Override
    public boolean hasReason() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional string reason = 5;</code>
     *
     * @return The reason.
     */
    @java.lang.Override
    public java.lang.String getReason() {
        java.lang.Object ref = reason_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            reason_ = s;
            return s;
        }
    }

    /**
     * <code>optional string reason = 5;</code>
     *
     * @return The bytes for reason.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getReasonBytes() {
        java.lang.Object ref = reason_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            reason_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int EXPIRATION_DATE_FIELD_NUMBER = 6;
    private long expirationDate_ = 0L;

    /**
     * <code>optional int64 expiration_date = 6;</code>
     *
     * @return Whether the expirationDate field is set.
     */
    @java.lang.Override
    public boolean hasExpirationDate() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional int64 expiration_date = 6;</code>
     *
     * @return The expirationDate.
     */
    @java.lang.Override
    public long getExpirationDate() {
        return expirationDate_;
    }

    public static final int REQUESTER_ID_FIELD_NUMBER = 7;
    private long requesterId_ = 0L;

    /**
     * <code>optional int64 requester_id = 7;</code>
     *
     * @return Whether the requesterId field is set.
     */
    @java.lang.Override
    public boolean hasRequesterId() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>optional int64 requester_id = 7;</code>
     *
     * @return The requesterId.
     */
    @java.lang.Override
    public long getRequesterId() {
        return requesterId_;
    }

    public static final int RECIPIENT_ID_FIELD_NUMBER = 8;
    private long recipientId_ = 0L;

    /**
     * <code>optional int64 recipient_id = 8;</code>
     *
     * @return Whether the recipientId field is set.
     */
    @java.lang.Override
    public boolean hasRecipientId() {
        return ((bitField0_ & 0x00000080) != 0);
    }

    /**
     * <code>optional int64 recipient_id = 8;</code>
     *
     * @return The recipientId.
     */
    @java.lang.Override
    public long getRecipientId() {
        return recipientId_;
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(1, id_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(2, creationDate_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 3, content_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeEnum(4, requestStatus_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 5, reason_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeInt64(6, expirationDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            output.writeInt64(7, requesterId_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            output.writeInt64(8, recipientId_);
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
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, id_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, creationDate_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, content_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(4, requestStatus_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, reason_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, expirationDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(7, requesterId_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(8, recipientId_);
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
        if (!(obj instanceof UserFriendRequest other)) {
            return super.equals(obj);
        }

        if (hasId() != other.hasId()) {
            return false;
        }
        if (hasId()) {
            if (getId() != other.getId()) {
                return false;
            }
        }
        if (hasCreationDate() != other.hasCreationDate()) {
            return false;
        }
        if (hasCreationDate()) {
            if (getCreationDate() != other.getCreationDate()) {
                return false;
            }
        }
        if (hasContent() != other.hasContent()) {
            return false;
        }
        if (hasContent()) {
            if (!getContent().equals(other.getContent())) {
                return false;
            }
        }
        if (hasRequestStatus() != other.hasRequestStatus()) {
            return false;
        }
        if (hasRequestStatus()) {
            if (requestStatus_ != other.requestStatus_) {
                return false;
            }
        }
        if (hasReason() != other.hasReason()) {
            return false;
        }
        if (hasReason()) {
            if (!getReason().equals(other.getReason())) {
                return false;
            }
        }
        if (hasExpirationDate() != other.hasExpirationDate()) {
            return false;
        }
        if (hasExpirationDate()) {
            if (getExpirationDate() != other.getExpirationDate()) {
                return false;
            }
        }
        if (hasRequesterId() != other.hasRequesterId()) {
            return false;
        }
        if (hasRequesterId()) {
            if (getRequesterId() != other.getRequesterId()) {
                return false;
            }
        }
        if (hasRecipientId() != other.hasRecipientId()) {
            return false;
        }
        if (hasRecipientId()) {
            if (getRecipientId() != other.getRecipientId()) {
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
        if (hasId()) {
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getId());
        }
        if (hasCreationDate()) {
            hash = (37 * hash) + CREATION_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCreationDate());
        }
        if (hasContent()) {
            hash = (37 * hash) + CONTENT_FIELD_NUMBER;
            hash = (53 * hash) + getContent().hashCode();
        }
        if (hasRequestStatus()) {
            hash = (37 * hash) + REQUEST_STATUS_FIELD_NUMBER;
            hash = (53 * hash) + requestStatus_;
        }
        if (hasReason()) {
            hash = (37 * hash) + REASON_FIELD_NUMBER;
            hash = (53 * hash) + getReason().hashCode();
        }
        if (hasExpirationDate()) {
            hash = (37 * hash) + EXPIRATION_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getExpirationDate());
        }
        if (hasRequesterId()) {
            hash = (37 * hash) + REQUESTER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRequesterId());
        }
        if (hasRecipientId()) {
            hash = (37 * hash) + RECIPIENT_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRecipientId());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserFriendRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UserFriendRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserFriendRequest)
            im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestOuterClass.internal_static_im_turms_proto_UserFriendRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestOuterClass.internal_static_im_turms_proto_UserFriendRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserFriendRequest.class,
                            im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserFriendRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            id_ = 0L;
            creationDate_ = 0L;
            content_ = "";
            requestStatus_ = 0;
            reason_ = "";
            expirationDate_ = 0L;
            requesterId_ = 0L;
            recipientId_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestOuterClass.internal_static_im_turms_proto_UserFriendRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest build() {
            im.turms.server.common.access.client.dto.model.user.UserFriendRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserFriendRequest result =
                    new im.turms.server.common.access.client.dto.model.user.UserFriendRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.id_ = id_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.creationDate_ = creationDate_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.content_ = content_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.requestStatus_ = requestStatus_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.reason_ = reason_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.expirationDate_ = expirationDate_;
                to_bitField0_ |= 0x00000020;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.requesterId_ = requesterId_;
                to_bitField0_ |= 0x00000040;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.recipientId_ = recipientId_;
                to_bitField0_ |= 0x00000080;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserFriendRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserFriendRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserFriendRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasId()) {
                setId(other.getId());
            }
            if (other.hasCreationDate()) {
                setCreationDate(other.getCreationDate());
            }
            if (other.hasContent()) {
                content_ = other.content_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasRequestStatus()) {
                setRequestStatus(other.getRequestStatus());
            }
            if (other.hasReason()) {
                reason_ = other.reason_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (other.hasExpirationDate()) {
                setExpirationDate(other.getExpirationDate());
            }
            if (other.hasRequesterId()) {
                setRequesterId(other.getRequesterId());
            }
            if (other.hasRecipientId()) {
                setRecipientId(other.getRecipientId());
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
                            id_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            creationDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            content_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            requestStatus_ = input.readEnum();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 42 -> {
                            reason_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 48 -> {
                            expirationDate_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            requesterId_ = input.readInt64();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 64 -> {
                            recipientId_ = input.readInt64();
                            bitField0_ |= 0x00000080;
                        } // case 64
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

        private long id_;

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return Whether the id field is set.
         */
        @java.lang.Override
        public boolean hasId() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return The id.
         */
        @java.lang.Override
        public long getId() {
            return id_;
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @param value The id to set.
         * @return This builder for chaining.
         */
        public Builder setId(long value) {

            id_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            bitField0_ &= ~0x00000001;
            id_ = 0L;
            onChanged();
            return this;
        }

        private long creationDate_;

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @return Whether the creationDate field is set.
         */
        @java.lang.Override
        public boolean hasCreationDate() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @return The creationDate.
         */
        @java.lang.Override
        public long getCreationDate() {
            return creationDate_;
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @param value The creationDate to set.
         * @return This builder for chaining.
         */
        public Builder setCreationDate(long value) {

            creationDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDate() {
            bitField0_ &= ~0x00000002;
            creationDate_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object content_ = "";

        /**
         * <code>optional string content = 3;</code>
         *
         * @return Whether the content field is set.
         */
        public boolean hasContent() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return The content.
         */
        public java.lang.String getContent() {
            java.lang.Object ref = content_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                content_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return The bytes for content.
         */
        public com.google.protobuf.ByteString getContentBytes() {
            java.lang.Object ref = content_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                content_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @param value The content to set.
         * @return This builder for chaining.
         */
        public Builder setContent(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            content_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContent() {
            content_ = getDefaultInstance().getContent();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @param value The bytes for content to set.
         * @return This builder for chaining.
         */
        public Builder setContentBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            content_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int requestStatus_ = 0;

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return Whether the requestStatus field is set.
         */
        @java.lang.Override
        public boolean hasRequestStatus() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return The enum numeric value on the wire for requestStatus.
         */
        @java.lang.Override
        public int getRequestStatusValue() {
            return requestStatus_;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @param value The enum numeric value on the wire for requestStatus to set.
         * @return This builder for chaining.
         */
        public Builder setRequestStatusValue(int value) {
            requestStatus_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return The requestStatus.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.RequestStatus getRequestStatus() {
            im.turms.server.common.access.client.dto.constant.RequestStatus result =
                    im.turms.server.common.access.client.dto.constant.RequestStatus
                            .forNumber(requestStatus_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.RequestStatus.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @param value The requestStatus to set.
         * @return This builder for chaining.
         */
        public Builder setRequestStatus(
                im.turms.server.common.access.client.dto.constant.RequestStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000008;
            requestStatus_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequestStatus() {
            bitField0_ &= ~0x00000008;
            requestStatus_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object reason_ = "";

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return Whether the reason field is set.
         */
        public boolean hasReason() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return The reason.
         */
        public java.lang.String getReason() {
            java.lang.Object ref = reason_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                reason_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return The bytes for reason.
         */
        public com.google.protobuf.ByteString getReasonBytes() {
            java.lang.Object ref = reason_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                reason_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @param value The reason to set.
         * @return This builder for chaining.
         */
        public Builder setReason(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            reason_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReason() {
            reason_ = getDefaultInstance().getReason();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @param value The bytes for reason to set.
         * @return This builder for chaining.
         */
        public Builder setReasonBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            reason_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private long expirationDate_;

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @return Whether the expirationDate field is set.
         */
        @java.lang.Override
        public boolean hasExpirationDate() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @return The expirationDate.
         */
        @java.lang.Override
        public long getExpirationDate() {
            return expirationDate_;
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @param value The expirationDate to set.
         * @return This builder for chaining.
         */
        public Builder setExpirationDate(long value) {

            expirationDate_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearExpirationDate() {
            bitField0_ &= ~0x00000020;
            expirationDate_ = 0L;
            onChanged();
            return this;
        }

        private long requesterId_;

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @return Whether the requesterId field is set.
         */
        @java.lang.Override
        public boolean hasRequesterId() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @return The requesterId.
         */
        @java.lang.Override
        public long getRequesterId() {
            return requesterId_;
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @param value The requesterId to set.
         * @return This builder for chaining.
         */
        public Builder setRequesterId(long value) {

            requesterId_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequesterId() {
            bitField0_ &= ~0x00000040;
            requesterId_ = 0L;
            onChanged();
            return this;
        }

        private long recipientId_;

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @return Whether the recipientId field is set.
         */
        @java.lang.Override
        public boolean hasRecipientId() {
            return ((bitField0_ & 0x00000080) != 0);
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @return The recipientId.
         */
        @java.lang.Override
        public long getRecipientId() {
            return recipientId_;
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @param value The recipientId to set.
         * @return This builder for chaining.
         */
        public Builder setRecipientId(long value) {

            recipientId_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecipientId() {
            bitField0_ &= ~0x00000080;
            recipientId_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserFriendRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserFriendRequest)
    private static final im.turms.server.common.access.client.dto.model.user.UserFriendRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserFriendRequest();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserFriendRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserFriendRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserFriendRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserFriendRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserFriendRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}