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

package im.turms.client.model.proto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserFriendRequest}
 */
public final class UserFriendRequest extends
        com.google.protobuf.GeneratedMessageLite<UserFriendRequest, UserFriendRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserFriendRequest)
        UserFriendRequestOrBuilder {
    private UserFriendRequest() {
        content_ = "";
        reason_ = "";
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
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
     */
    private void setId(long value) {
        bitField0_ |= 0x00000001;
        id_ = value;
    }

    /**
     * <code>optional int64 id = 1;</code>
     */
    private void clearId() {
        bitField0_ &= ~0x00000001;
        id_ = 0L;
    }

    public static final int CREATION_DATE_FIELD_NUMBER = 2;
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
     */
    private void setCreationDate(long value) {
        bitField0_ |= 0x00000002;
        creationDate_ = value;
    }

    /**
     * <code>optional int64 creation_date = 2;</code>
     */
    private void clearCreationDate() {
        bitField0_ &= ~0x00000002;
        creationDate_ = 0L;
    }

    public static final int CONTENT_FIELD_NUMBER = 3;
    private java.lang.String content_;

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
        return content_;
    }

    /**
     * <code>optional string content = 3;</code>
     *
     * @return The bytes for content.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getContentBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(content_);
    }

    /**
     * <code>optional string content = 3;</code>
     *
     * @param value The content to set.
     */
    private void setContent(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000004;
        content_ = value;
    }

    /**
     * <code>optional string content = 3;</code>
     */
    private void clearContent() {
        bitField0_ &= ~0x00000004;
        content_ = getDefaultInstance().getContent();
    }

    /**
     * <code>optional string content = 3;</code>
     *
     * @param value The bytes for content to set.
     */
    private void setContentBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        content_ = value.toStringUtf8();
        bitField0_ |= 0x00000004;
    }

    public static final int REQUEST_STATUS_FIELD_NUMBER = 4;
    private int requestStatus_;

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
    public im.turms.client.model.proto.constant.RequestStatus getRequestStatus() {
        im.turms.client.model.proto.constant.RequestStatus result =
                im.turms.client.model.proto.constant.RequestStatus.forNumber(requestStatus_);
        return result == null
                ? im.turms.client.model.proto.constant.RequestStatus.UNRECOGNIZED
                : result;
    }

    /**
     * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
     *
     * @param value The enum numeric value on the wire for requestStatus to set.
     */
    private void setRequestStatusValue(int value) {
        bitField0_ |= 0x00000008;
        requestStatus_ = value;
    }

    /**
     * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
     *
     * @param value The requestStatus to set.
     */
    private void setRequestStatus(im.turms.client.model.proto.constant.RequestStatus value) {
        requestStatus_ = value.getNumber();
        bitField0_ |= 0x00000008;
    }

    /**
     * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
     */
    private void clearRequestStatus() {
        bitField0_ &= ~0x00000008;
        requestStatus_ = 0;
    }

    public static final int REASON_FIELD_NUMBER = 5;
    private java.lang.String reason_;

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
        return reason_;
    }

    /**
     * <code>optional string reason = 5;</code>
     *
     * @return The bytes for reason.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getReasonBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(reason_);
    }

    /**
     * <code>optional string reason = 5;</code>
     *
     * @param value The reason to set.
     */
    private void setReason(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000010;
        reason_ = value;
    }

    /**
     * <code>optional string reason = 5;</code>
     */
    private void clearReason() {
        bitField0_ &= ~0x00000010;
        reason_ = getDefaultInstance().getReason();
    }

    /**
     * <code>optional string reason = 5;</code>
     *
     * @param value The bytes for reason to set.
     */
    private void setReasonBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        reason_ = value.toStringUtf8();
        bitField0_ |= 0x00000010;
    }

    public static final int EXPIRATION_DATE_FIELD_NUMBER = 6;
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
     */
    private void setExpirationDate(long value) {
        bitField0_ |= 0x00000020;
        expirationDate_ = value;
    }

    /**
     * <code>optional int64 expiration_date = 6;</code>
     */
    private void clearExpirationDate() {
        bitField0_ &= ~0x00000020;
        expirationDate_ = 0L;
    }

    public static final int REQUESTER_ID_FIELD_NUMBER = 7;
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
     */
    private void setRequesterId(long value) {
        bitField0_ |= 0x00000040;
        requesterId_ = value;
    }

    /**
     * <code>optional int64 requester_id = 7;</code>
     */
    private void clearRequesterId() {
        bitField0_ &= ~0x00000040;
        requesterId_ = 0L;
    }

    public static final int RECIPIENT_ID_FIELD_NUMBER = 8;
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
     */
    private void setRecipientId(long value) {
        bitField0_ |= 0x00000080;
        recipientId_ = value;
    }

    /**
     * <code>optional int64 recipient_id = 8;</code>
     */
    private void clearRecipientId() {
        bitField0_ &= ~0x00000080;
        recipientId_ = 0L;
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest parseFrom(
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
            im.turms.client.model.proto.model.user.UserFriendRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserFriendRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.UserFriendRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserFriendRequest)
            im.turms.client.model.proto.model.user.UserFriendRequestOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.UserFriendRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return Whether the id field is set.
         */
        @java.lang.Override
        public boolean hasId() {
            return instance.hasId();
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return The id.
         */
        @java.lang.Override
        public long getId() {
            return instance.getId();
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @param value The id to set.
         * @return This builder for chaining.
         */
        public Builder setId(long value) {
            copyOnWrite();
            instance.setId(value);
            return this;
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            copyOnWrite();
            instance.clearId();
            return this;
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @return Whether the creationDate field is set.
         */
        @java.lang.Override
        public boolean hasCreationDate() {
            return instance.hasCreationDate();
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @return The creationDate.
         */
        @java.lang.Override
        public long getCreationDate() {
            return instance.getCreationDate();
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @param value The creationDate to set.
         * @return This builder for chaining.
         */
        public Builder setCreationDate(long value) {
            copyOnWrite();
            instance.setCreationDate(value);
            return this;
        }

        /**
         * <code>optional int64 creation_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationDate() {
            copyOnWrite();
            instance.clearCreationDate();
            return this;
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return Whether the content field is set.
         */
        @java.lang.Override
        public boolean hasContent() {
            return instance.hasContent();
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return The content.
         */
        @java.lang.Override
        public java.lang.String getContent() {
            return instance.getContent();
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return The bytes for content.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getContentBytes() {
            return instance.getContentBytes();
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @param value The content to set.
         * @return This builder for chaining.
         */
        public Builder setContent(java.lang.String value) {
            copyOnWrite();
            instance.setContent(value);
            return this;
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContent() {
            copyOnWrite();
            instance.clearContent();
            return this;
        }

        /**
         * <code>optional string content = 3;</code>
         *
         * @param value The bytes for content to set.
         * @return This builder for chaining.
         */
        public Builder setContentBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setContentBytes(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return Whether the requestStatus field is set.
         */
        @java.lang.Override
        public boolean hasRequestStatus() {
            return instance.hasRequestStatus();
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return The enum numeric value on the wire for requestStatus.
         */
        @java.lang.Override
        public int getRequestStatusValue() {
            return instance.getRequestStatusValue();
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @param value The requestStatus to set.
         * @return This builder for chaining.
         */
        public Builder setRequestStatusValue(int value) {
            copyOnWrite();
            instance.setRequestStatusValue(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return The requestStatus.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.RequestStatus getRequestStatus() {
            return instance.getRequestStatus();
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @param value The enum numeric value on the wire for requestStatus to set.
         * @return This builder for chaining.
         */
        public Builder setRequestStatus(im.turms.client.model.proto.constant.RequestStatus value) {
            copyOnWrite();
            instance.setRequestStatus(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.RequestStatus request_status = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequestStatus() {
            copyOnWrite();
            instance.clearRequestStatus();
            return this;
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return Whether the reason field is set.
         */
        @java.lang.Override
        public boolean hasReason() {
            return instance.hasReason();
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return The reason.
         */
        @java.lang.Override
        public java.lang.String getReason() {
            return instance.getReason();
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return The bytes for reason.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getReasonBytes() {
            return instance.getReasonBytes();
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @param value The reason to set.
         * @return This builder for chaining.
         */
        public Builder setReason(java.lang.String value) {
            copyOnWrite();
            instance.setReason(value);
            return this;
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReason() {
            copyOnWrite();
            instance.clearReason();
            return this;
        }

        /**
         * <code>optional string reason = 5;</code>
         *
         * @param value The bytes for reason to set.
         * @return This builder for chaining.
         */
        public Builder setReasonBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setReasonBytes(value);
            return this;
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @return Whether the expirationDate field is set.
         */
        @java.lang.Override
        public boolean hasExpirationDate() {
            return instance.hasExpirationDate();
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @return The expirationDate.
         */
        @java.lang.Override
        public long getExpirationDate() {
            return instance.getExpirationDate();
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @param value The expirationDate to set.
         * @return This builder for chaining.
         */
        public Builder setExpirationDate(long value) {
            copyOnWrite();
            instance.setExpirationDate(value);
            return this;
        }

        /**
         * <code>optional int64 expiration_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearExpirationDate() {
            copyOnWrite();
            instance.clearExpirationDate();
            return this;
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @return Whether the requesterId field is set.
         */
        @java.lang.Override
        public boolean hasRequesterId() {
            return instance.hasRequesterId();
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @return The requesterId.
         */
        @java.lang.Override
        public long getRequesterId() {
            return instance.getRequesterId();
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @param value The requesterId to set.
         * @return This builder for chaining.
         */
        public Builder setRequesterId(long value) {
            copyOnWrite();
            instance.setRequesterId(value);
            return this;
        }

        /**
         * <code>optional int64 requester_id = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequesterId() {
            copyOnWrite();
            instance.clearRequesterId();
            return this;
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @return Whether the recipientId field is set.
         */
        @java.lang.Override
        public boolean hasRecipientId() {
            return instance.hasRecipientId();
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @return The recipientId.
         */
        @java.lang.Override
        public long getRecipientId() {
            return instance.getRecipientId();
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @param value The recipientId to set.
         * @return This builder for chaining.
         */
        public Builder setRecipientId(long value) {
            copyOnWrite();
            instance.setRecipientId(value);
            return this;
        }

        /**
         * <code>optional int64 recipient_id = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecipientId() {
            copyOnWrite();
            instance.clearRecipientId();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserFriendRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.UserFriendRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "id_",
                        "creationDate_",
                        "content_",
                        "requestStatus_",
                        "reason_",
                        "expirationDate_",
                        "requesterId_",
                        "recipientId_",};
                java.lang.String info =
                        "\u0000\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001\u1002\u0000\u0002\u1002\u0001"
                                + "\u0003\u1208\u0002\u0004\u100c\u0003\u0005\u1208\u0004\u0006\u1002\u0005\u0007\u1002"
                                + "\u0006\b\u1002\u0007";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.UserFriendRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.UserFriendRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserFriendRequest)
    private static final im.turms.client.model.proto.model.user.UserFriendRequest DEFAULT_INSTANCE;

    static {
        UserFriendRequest defaultInstance = new UserFriendRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UserFriendRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.UserFriendRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserFriendRequest> PARSER;

    public static com.google.protobuf.Parser<UserFriendRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}