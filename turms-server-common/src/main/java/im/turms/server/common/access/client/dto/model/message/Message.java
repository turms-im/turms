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

package im.turms.server.common.access.client.dto.model.message;

/**
 * Protobuf type {@code im.turms.proto.Message}
 */
public final class Message extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Message)
        MessageOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use Message.newBuilder() to construct.
    private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Message() {
        text_ = "";
        records_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new Message();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.message.MessageOuterClass.internal_static_im_turms_proto_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.message.MessageOuterClass.internal_static_im_turms_proto_Message_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.message.Message.class,
                        im.turms.server.common.access.client.dto.model.message.Message.Builder.class);
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

    public static final int DELIVERY_DATE_FIELD_NUMBER = 2;
    private long deliveryDate_ = 0L;

    /**
     * <code>optional int64 delivery_date = 2;</code>
     *
     * @return Whether the deliveryDate field is set.
     */
    @java.lang.Override
    public boolean hasDeliveryDate() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 delivery_date = 2;</code>
     *
     * @return The deliveryDate.
     */
    @java.lang.Override
    public long getDeliveryDate() {
        return deliveryDate_;
    }

    public static final int MODIFICATION_DATE_FIELD_NUMBER = 3;
    private long modificationDate_ = 0L;

    /**
     * <code>optional int64 modification_date = 3;</code>
     *
     * @return Whether the modificationDate field is set.
     */
    @java.lang.Override
    public boolean hasModificationDate() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int64 modification_date = 3;</code>
     *
     * @return The modificationDate.
     */
    @java.lang.Override
    public long getModificationDate() {
        return modificationDate_;
    }

    public static final int TEXT_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object text_ = "";

    /**
     * <code>optional string text = 4;</code>
     *
     * @return Whether the text field is set.
     */
    @java.lang.Override
    public boolean hasText() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional string text = 4;</code>
     *
     * @return The text.
     */
    @java.lang.Override
    public java.lang.String getText() {
        java.lang.Object ref = text_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            text_ = s;
            return s;
        }
    }

    /**
     * <code>optional string text = 4;</code>
     *
     * @return The bytes for text.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTextBytes() {
        java.lang.Object ref = text_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            text_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int SENDER_ID_FIELD_NUMBER = 5;
    private long senderId_ = 0L;

    /**
     * <code>optional int64 sender_id = 5;</code>
     *
     * @return Whether the senderId field is set.
     */
    @java.lang.Override
    public boolean hasSenderId() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 sender_id = 5;</code>
     *
     * @return The senderId.
     */
    @java.lang.Override
    public long getSenderId() {
        return senderId_;
    }

    public static final int GROUP_ID_FIELD_NUMBER = 6;
    private long groupId_ = 0L;

    /**
     * <code>optional int64 group_id = 6;</code>
     *
     * @return Whether the groupId field is set.
     */
    @java.lang.Override
    public boolean hasGroupId() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional int64 group_id = 6;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    public static final int IS_SYSTEM_MESSAGE_FIELD_NUMBER = 7;
    private boolean isSystemMessage_ = false;

    /**
     * <code>optional bool is_system_message = 7;</code>
     *
     * @return Whether the isSystemMessage field is set.
     */
    @java.lang.Override
    public boolean hasIsSystemMessage() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>optional bool is_system_message = 7;</code>
     *
     * @return The isSystemMessage.
     */
    @java.lang.Override
    public boolean getIsSystemMessage() {
        return isSystemMessage_;
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

    public static final int RECORDS_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private java.util.List<com.google.protobuf.ByteString> records_;

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @return A list containing the records.
     */
    @java.lang.Override
    public java.util.List<com.google.protobuf.ByteString> getRecordsList() {
        return records_;
    }

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @return The count of records.
     */
    public int getRecordsCount() {
        return records_.size();
    }

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @param index The index of the element to return.
     * @return The records at the given index.
     */
    public com.google.protobuf.ByteString getRecords(int index) {
        return records_.get(index);
    }

    public static final int SEQUENCE_ID_FIELD_NUMBER = 10;
    private int sequenceId_ = 0;

    /**
     * <code>optional int32 sequence_id = 10;</code>
     *
     * @return Whether the sequenceId field is set.
     */
    @java.lang.Override
    public boolean hasSequenceId() {
        return ((bitField0_ & 0x00000100) != 0);
    }

    /**
     * <code>optional int32 sequence_id = 10;</code>
     *
     * @return The sequenceId.
     */
    @java.lang.Override
    public int getSequenceId() {
        return sequenceId_;
    }

    public static final int PRE_MESSAGE_ID_FIELD_NUMBER = 11;
    private long preMessageId_ = 0L;

    /**
     * <code>optional int64 pre_message_id = 11;</code>
     *
     * @return Whether the preMessageId field is set.
     */
    @java.lang.Override
    public boolean hasPreMessageId() {
        return ((bitField0_ & 0x00000200) != 0);
    }

    /**
     * <code>optional int64 pre_message_id = 11;</code>
     *
     * @return The preMessageId.
     */
    @java.lang.Override
    public long getPreMessageId() {
        return preMessageId_;
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
            output.writeInt64(2, deliveryDate_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt64(3, modificationDate_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 4, text_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt64(5, senderId_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeInt64(6, groupId_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            output.writeBool(7, isSystemMessage_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            output.writeInt64(8, recipientId_);
        }
        for (com.google.protobuf.ByteString bytes : records_) {
            output.writeBytes(9, bytes);
        }
        if (((bitField0_ & 0x00000100) != 0)) {
            output.writeInt32(10, sequenceId_);
        }
        if (((bitField0_ & 0x00000200) != 0)) {
            output.writeInt64(11, preMessageId_);
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
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, deliveryDate_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, modificationDate_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, text_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, senderId_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, groupId_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(7, isSystemMessage_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(8, recipientId_);
        }
        {
            int dataSize = 0;
            for (com.google.protobuf.ByteString bytes : records_) {
                dataSize += com.google.protobuf.CodedOutputStream.computeBytesSizeNoTag(bytes);
            }
            size += dataSize;
            size += 1 * getRecordsList().size();
        }
        if (((bitField0_ & 0x00000100) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(10, sequenceId_);
        }
        if (((bitField0_ & 0x00000200) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(11, preMessageId_);
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
        if (!(obj instanceof Message other)) {
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
        if (hasDeliveryDate() != other.hasDeliveryDate()) {
            return false;
        }
        if (hasDeliveryDate()) {
            if (getDeliveryDate() != other.getDeliveryDate()) {
                return false;
            }
        }
        if (hasModificationDate() != other.hasModificationDate()) {
            return false;
        }
        if (hasModificationDate()) {
            if (getModificationDate() != other.getModificationDate()) {
                return false;
            }
        }
        if (hasText() != other.hasText()) {
            return false;
        }
        if (hasText()) {
            if (!getText().equals(other.getText())) {
                return false;
            }
        }
        if (hasSenderId() != other.hasSenderId()) {
            return false;
        }
        if (hasSenderId()) {
            if (getSenderId() != other.getSenderId()) {
                return false;
            }
        }
        if (hasGroupId() != other.hasGroupId()) {
            return false;
        }
        if (hasGroupId()) {
            if (getGroupId() != other.getGroupId()) {
                return false;
            }
        }
        if (hasIsSystemMessage() != other.hasIsSystemMessage()) {
            return false;
        }
        if (hasIsSystemMessage()) {
            if (getIsSystemMessage() != other.getIsSystemMessage()) {
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
        if (!getRecordsList().equals(other.getRecordsList())) {
            return false;
        }
        if (hasSequenceId() != other.hasSequenceId()) {
            return false;
        }
        if (hasSequenceId()) {
            if (getSequenceId() != other.getSequenceId()) {
                return false;
            }
        }
        if (hasPreMessageId() != other.hasPreMessageId()) {
            return false;
        }
        if (hasPreMessageId()) {
            if (getPreMessageId() != other.getPreMessageId()) {
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
        if (hasDeliveryDate()) {
            hash = (37 * hash) + DELIVERY_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getDeliveryDate());
        }
        if (hasModificationDate()) {
            hash = (37 * hash) + MODIFICATION_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getModificationDate());
        }
        if (hasText()) {
            hash = (37 * hash) + TEXT_FIELD_NUMBER;
            hash = (53 * hash) + getText().hashCode();
        }
        if (hasSenderId()) {
            hash = (37 * hash) + SENDER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getSenderId());
        }
        if (hasGroupId()) {
            hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        }
        if (hasIsSystemMessage()) {
            hash = (37 * hash) + IS_SYSTEM_MESSAGE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getIsSystemMessage());
        }
        if (hasRecipientId()) {
            hash = (37 * hash) + RECIPIENT_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRecipientId());
        }
        if (getRecordsCount() > 0) {
            hash = (37 * hash) + RECORDS_FIELD_NUMBER;
            hash = (53 * hash) + getRecordsList().hashCode();
        }
        if (hasSequenceId()) {
            hash = (37 * hash) + SEQUENCE_ID_FIELD_NUMBER;
            hash = (53 * hash) + getSequenceId();
        }
        if (hasPreMessageId()) {
            hash = (37 * hash) + PRE_MESSAGE_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getPreMessageId());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.Message parseFrom(
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
            im.turms.server.common.access.client.dto.model.message.Message prototype) {
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
     * Protobuf type {@code im.turms.proto.Message}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Message)
            im.turms.server.common.access.client.dto.model.message.MessageOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.message.MessageOuterClass.internal_static_im_turms_proto_Message_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.message.MessageOuterClass.internal_static_im_turms_proto_Message_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.message.Message.class,
                            im.turms.server.common.access.client.dto.model.message.Message.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.message.Message.newBuilder()
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
            deliveryDate_ = 0L;
            modificationDate_ = 0L;
            text_ = "";
            senderId_ = 0L;
            groupId_ = 0L;
            isSystemMessage_ = false;
            recipientId_ = 0L;
            records_ = java.util.Collections.emptyList();
            sequenceId_ = 0;
            preMessageId_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.message.MessageOuterClass.internal_static_im_turms_proto_Message_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Message getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.message.Message
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Message build() {
            im.turms.server.common.access.client.dto.model.message.Message result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Message buildPartial() {
            im.turms.server.common.access.client.dto.model.message.Message result =
                    new im.turms.server.common.access.client.dto.model.message.Message(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.message.Message result) {
            if (((bitField0_ & 0x00000100) != 0)) {
                records_ = java.util.Collections.unmodifiableList(records_);
                bitField0_ &= ~0x00000100;
            }
            result.records_ = records_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.message.Message result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.id_ = id_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.deliveryDate_ = deliveryDate_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.modificationDate_ = modificationDate_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.text_ = text_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.senderId_ = senderId_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.groupId_ = groupId_;
                to_bitField0_ |= 0x00000020;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.isSystemMessage_ = isSystemMessage_;
                to_bitField0_ |= 0x00000040;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.recipientId_ = recipientId_;
                to_bitField0_ |= 0x00000080;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.sequenceId_ = sequenceId_;
                to_bitField0_ |= 0x00000100;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.preMessageId_ = preMessageId_;
                to_bitField0_ |= 0x00000200;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.message.Message) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.message.Message) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.message.Message other) {
            if (other == im.turms.server.common.access.client.dto.model.message.Message
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasId()) {
                setId(other.getId());
            }
            if (other.hasDeliveryDate()) {
                setDeliveryDate(other.getDeliveryDate());
            }
            if (other.hasModificationDate()) {
                setModificationDate(other.getModificationDate());
            }
            if (other.hasText()) {
                text_ = other.text_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.hasSenderId()) {
                setSenderId(other.getSenderId());
            }
            if (other.hasGroupId()) {
                setGroupId(other.getGroupId());
            }
            if (other.hasIsSystemMessage()) {
                setIsSystemMessage(other.getIsSystemMessage());
            }
            if (other.hasRecipientId()) {
                setRecipientId(other.getRecipientId());
            }
            if (!other.records_.isEmpty()) {
                if (records_.isEmpty()) {
                    records_ = other.records_;
                    bitField0_ &= ~0x00000100;
                } else {
                    ensureRecordsIsMutable();
                    records_.addAll(other.records_);
                }
                onChanged();
            }
            if (other.hasSequenceId()) {
                setSequenceId(other.getSequenceId());
            }
            if (other.hasPreMessageId()) {
                setPreMessageId(other.getPreMessageId());
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
                            deliveryDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            modificationDate_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            text_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 40 -> {
                            senderId_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            isSystemMessage_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 64 -> {
                            recipientId_ = input.readInt64();
                            bitField0_ |= 0x00000080;
                        } // case 64
                        case 74 -> {
                            com.google.protobuf.ByteString v = input.readBytes();
                            ensureRecordsIsMutable();
                            records_.add(v);
                        } // case 74
                        case 80 -> {
                            sequenceId_ = input.readInt32();
                            bitField0_ |= 0x00000200;
                        } // case 80
                        case 88 -> {
                            preMessageId_ = input.readInt64();
                            bitField0_ |= 0x00000400;
                        } // case 88
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

        private long deliveryDate_;

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @return Whether the deliveryDate field is set.
         */
        @java.lang.Override
        public boolean hasDeliveryDate() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @return The deliveryDate.
         */
        @java.lang.Override
        public long getDeliveryDate() {
            return deliveryDate_;
        }

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @param value The deliveryDate to set.
         * @return This builder for chaining.
         */
        public Builder setDeliveryDate(long value) {

            deliveryDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeliveryDate() {
            bitField0_ &= ~0x00000002;
            deliveryDate_ = 0L;
            onChanged();
            return this;
        }

        private long modificationDate_;

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @return Whether the modificationDate field is set.
         */
        @java.lang.Override
        public boolean hasModificationDate() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @return The modificationDate.
         */
        @java.lang.Override
        public long getModificationDate() {
            return modificationDate_;
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @param value The modificationDate to set.
         * @return This builder for chaining.
         */
        public Builder setModificationDate(long value) {

            modificationDate_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearModificationDate() {
            bitField0_ &= ~0x00000004;
            modificationDate_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object text_ = "";

        /**
         * <code>optional string text = 4;</code>
         *
         * @return Whether the text field is set.
         */
        public boolean hasText() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return The text.
         */
        public java.lang.String getText() {
            java.lang.Object ref = text_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                text_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return The bytes for text.
         */
        public com.google.protobuf.ByteString getTextBytes() {
            java.lang.Object ref = text_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                text_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @param value The text to set.
         * @return This builder for chaining.
         */
        public Builder setText(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            text_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearText() {
            text_ = getDefaultInstance().getText();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @param value The bytes for text to set.
         * @return This builder for chaining.
         */
        public Builder setTextBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            text_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private long senderId_;

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @return Whether the senderId field is set.
         */
        @java.lang.Override
        public boolean hasSenderId() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @return The senderId.
         */
        @java.lang.Override
        public long getSenderId() {
            return senderId_;
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @param value The senderId to set.
         * @return This builder for chaining.
         */
        public Builder setSenderId(long value) {

            senderId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSenderId() {
            bitField0_ &= ~0x00000010;
            senderId_ = 0L;
            onChanged();
            return this;
        }

        private long groupId_;

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return groupId_;
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @param value The groupId to set.
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000020;
            groupId_ = 0L;
            onChanged();
            return this;
        }

        private boolean isSystemMessage_;

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @return Whether the isSystemMessage field is set.
         */
        @java.lang.Override
        public boolean hasIsSystemMessage() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @return The isSystemMessage.
         */
        @java.lang.Override
        public boolean getIsSystemMessage() {
            return isSystemMessage_;
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @param value The isSystemMessage to set.
         * @return This builder for chaining.
         */
        public Builder setIsSystemMessage(boolean value) {

            isSystemMessage_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIsSystemMessage() {
            bitField0_ &= ~0x00000040;
            isSystemMessage_ = false;
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

        private java.util.List<com.google.protobuf.ByteString> records_ =
                java.util.Collections.emptyList();

        private void ensureRecordsIsMutable() {
            if ((bitField0_ & 0x00000100) == 0) {
                records_ = new java.util.ArrayList<>(records_);
                bitField0_ |= 0x00000100;
            }
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @return A list containing the records.
         */
        public java.util.List<com.google.protobuf.ByteString> getRecordsList() {
            return ((bitField0_ & 0x00000100) != 0)
                    ? java.util.Collections.unmodifiableList(records_)
                    : records_;
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @return The count of records.
         */
        public int getRecordsCount() {
            return records_.size();
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param index The index of the element to return.
         * @return The records at the given index.
         */
        public com.google.protobuf.ByteString getRecords(int index) {
            return records_.get(index);
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param index The index to set the value at.
         * @param value The records to set.
         * @return This builder for chaining.
         */
        public Builder setRecords(int index, com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureRecordsIsMutable();
            records_.set(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param value The records to add.
         * @return This builder for chaining.
         */
        public Builder addRecords(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureRecordsIsMutable();
            records_.add(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param values The records to add.
         * @return This builder for chaining.
         */
        public Builder addAllRecords(
                java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
            ensureRecordsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, records_);
            onChanged();
            return this;
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecords() {
            records_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000100;
            onChanged();
            return this;
        }

        private int sequenceId_;

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @return Whether the sequenceId field is set.
         */
        @java.lang.Override
        public boolean hasSequenceId() {
            return ((bitField0_ & 0x00000200) != 0);
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @return The sequenceId.
         */
        @java.lang.Override
        public int getSequenceId() {
            return sequenceId_;
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @param value The sequenceId to set.
         * @return This builder for chaining.
         */
        public Builder setSequenceId(int value) {

            sequenceId_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSequenceId() {
            bitField0_ &= ~0x00000200;
            sequenceId_ = 0;
            onChanged();
            return this;
        }

        private long preMessageId_;

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @return Whether the preMessageId field is set.
         */
        @java.lang.Override
        public boolean hasPreMessageId() {
            return ((bitField0_ & 0x00000400) != 0);
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @return The preMessageId.
         */
        @java.lang.Override
        public long getPreMessageId() {
            return preMessageId_;
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @param value The preMessageId to set.
         * @return This builder for chaining.
         */
        public Builder setPreMessageId(long value) {

            preMessageId_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreMessageId() {
            bitField0_ &= ~0x00000400;
            preMessageId_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Message)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Message)
    private static final im.turms.server.common.access.client.dto.model.message.Message DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.message.Message();
    }

    public static im.turms.server.common.access.client.dto.model.message.Message getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Message> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Message parsePartialFrom(
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

    public static com.google.protobuf.Parser<Message> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Message> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.Message getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}