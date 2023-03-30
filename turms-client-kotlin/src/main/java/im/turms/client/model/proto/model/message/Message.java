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

package im.turms.client.model.proto.model.message;

/**
 * Protobuf type {@code im.turms.proto.Message}
 */
public final class Message
        extends com.google.protobuf.GeneratedMessageLite<Message, Message.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Message)
        MessageOrBuilder {
    private Message() {
        text_ = "";
        records_ = emptyProtobufList();
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

    public static final int DELIVERY_DATE_FIELD_NUMBER = 2;
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
     */
    private void setDeliveryDate(long value) {
        bitField0_ |= 0x00000002;
        deliveryDate_ = value;
    }

    /**
     * <code>optional int64 delivery_date = 2;</code>
     */
    private void clearDeliveryDate() {
        bitField0_ &= ~0x00000002;
        deliveryDate_ = 0L;
    }

    public static final int MODIFICATION_DATE_FIELD_NUMBER = 3;
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
     */
    private void setModificationDate(long value) {
        bitField0_ |= 0x00000004;
        modificationDate_ = value;
    }

    /**
     * <code>optional int64 modification_date = 3;</code>
     */
    private void clearModificationDate() {
        bitField0_ &= ~0x00000004;
        modificationDate_ = 0L;
    }

    public static final int TEXT_FIELD_NUMBER = 4;
    private java.lang.String text_;

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
        return text_;
    }

    /**
     * <code>optional string text = 4;</code>
     *
     * @return The bytes for text.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTextBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(text_);
    }

    /**
     * <code>optional string text = 4;</code>
     *
     * @param value The text to set.
     */
    private void setText(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000008;
        text_ = value;
    }

    /**
     * <code>optional string text = 4;</code>
     */
    private void clearText() {
        bitField0_ &= ~0x00000008;
        text_ = getDefaultInstance().getText();
    }

    /**
     * <code>optional string text = 4;</code>
     *
     * @param value The bytes for text to set.
     */
    private void setTextBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        text_ = value.toStringUtf8();
        bitField0_ |= 0x00000008;
    }

    public static final int SENDER_ID_FIELD_NUMBER = 5;
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
     */
    private void setSenderId(long value) {
        bitField0_ |= 0x00000010;
        senderId_ = value;
    }

    /**
     * <code>optional int64 sender_id = 5;</code>
     */
    private void clearSenderId() {
        bitField0_ &= ~0x00000010;
        senderId_ = 0L;
    }

    public static final int GROUP_ID_FIELD_NUMBER = 6;
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
     */
    private void setGroupId(long value) {
        bitField0_ |= 0x00000020;
        groupId_ = value;
    }

    /**
     * <code>optional int64 group_id = 6;</code>
     */
    private void clearGroupId() {
        bitField0_ &= ~0x00000020;
        groupId_ = 0L;
    }

    public static final int IS_SYSTEM_MESSAGE_FIELD_NUMBER = 7;
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
     */
    private void setIsSystemMessage(boolean value) {
        bitField0_ |= 0x00000040;
        isSystemMessage_ = value;
    }

    /**
     * <code>optional bool is_system_message = 7;</code>
     */
    private void clearIsSystemMessage() {
        bitField0_ &= ~0x00000040;
        isSystemMessage_ = false;
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

    public static final int RECORDS_FIELD_NUMBER = 9;
    private com.google.protobuf.Internal.ProtobufList<com.google.protobuf.ByteString> records_;

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
    @java.lang.Override
    public int getRecordsCount() {
        return records_.size();
    }

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @param index The index of the element to return.
     * @return The records at the given index.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRecords(int index) {
        return records_.get(index);
    }

    private void ensureRecordsIsMutable() {
        com.google.protobuf.Internal.ProtobufList<com.google.protobuf.ByteString> tmp = records_;
        if (!tmp.isModifiable()) {
            records_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @param index The index to set the value at.
     * @param value The records to set.
     */
    private void setRecords(int index, com.google.protobuf.ByteString value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureRecordsIsMutable();
        records_.set(index, value);
    }

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @param value The records to add.
     */
    private void addRecords(com.google.protobuf.ByteString value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureRecordsIsMutable();
        records_.add(value);
    }

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @param values The records to add.
     */
    private void addAllRecords(
            java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
        ensureRecordsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, records_);
    }

    /**
     * <code>repeated bytes records = 9;</code>
     */
    private void clearRecords() {
        records_ = emptyProtobufList();
    }

    public static final int SEQUENCE_ID_FIELD_NUMBER = 10;
    private int sequenceId_;

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

    /**
     * <code>optional int32 sequence_id = 10;</code>
     *
     * @param value The sequenceId to set.
     */
    private void setSequenceId(int value) {
        bitField0_ |= 0x00000100;
        sequenceId_ = value;
    }

    /**
     * <code>optional int32 sequence_id = 10;</code>
     */
    private void clearSequenceId() {
        bitField0_ &= ~0x00000100;
        sequenceId_ = 0;
    }

    public static final int PRE_MESSAGE_ID_FIELD_NUMBER = 11;
    private long preMessageId_;

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

    /**
     * <code>optional int64 pre_message_id = 11;</code>
     *
     * @param value The preMessageId to set.
     */
    private void setPreMessageId(long value) {
        bitField0_ |= 0x00000200;
        preMessageId_ = value;
    }

    /**
     * <code>optional int64 pre_message_id = 11;</code>
     */
    private void clearPreMessageId() {
        bitField0_ &= ~0x00000200;
        preMessageId_ = 0L;
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.Message parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.Message parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.Message parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.message.Message prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.Message}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.message.Message, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Message)
            im.turms.client.model.proto.model.message.MessageOrBuilder {
        // Construct using im.turms.client.model.proto.model.message.Message.newBuilder()
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
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @return Whether the deliveryDate field is set.
         */
        @java.lang.Override
        public boolean hasDeliveryDate() {
            return instance.hasDeliveryDate();
        }

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @return The deliveryDate.
         */
        @java.lang.Override
        public long getDeliveryDate() {
            return instance.getDeliveryDate();
        }

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @param value The deliveryDate to set.
         * @return This builder for chaining.
         */
        public Builder setDeliveryDate(long value) {
            copyOnWrite();
            instance.setDeliveryDate(value);
            return this;
        }

        /**
         * <code>optional int64 delivery_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeliveryDate() {
            copyOnWrite();
            instance.clearDeliveryDate();
            return this;
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @return Whether the modificationDate field is set.
         */
        @java.lang.Override
        public boolean hasModificationDate() {
            return instance.hasModificationDate();
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @return The modificationDate.
         */
        @java.lang.Override
        public long getModificationDate() {
            return instance.getModificationDate();
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @param value The modificationDate to set.
         * @return This builder for chaining.
         */
        public Builder setModificationDate(long value) {
            copyOnWrite();
            instance.setModificationDate(value);
            return this;
        }

        /**
         * <code>optional int64 modification_date = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearModificationDate() {
            copyOnWrite();
            instance.clearModificationDate();
            return this;
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return Whether the text field is set.
         */
        @java.lang.Override
        public boolean hasText() {
            return instance.hasText();
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return The text.
         */
        @java.lang.Override
        public java.lang.String getText() {
            return instance.getText();
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return The bytes for text.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getTextBytes() {
            return instance.getTextBytes();
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @param value The text to set.
         * @return This builder for chaining.
         */
        public Builder setText(java.lang.String value) {
            copyOnWrite();
            instance.setText(value);
            return this;
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearText() {
            copyOnWrite();
            instance.clearText();
            return this;
        }

        /**
         * <code>optional string text = 4;</code>
         *
         * @param value The bytes for text to set.
         * @return This builder for chaining.
         */
        public Builder setTextBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setTextBytes(value);
            return this;
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @return Whether the senderId field is set.
         */
        @java.lang.Override
        public boolean hasSenderId() {
            return instance.hasSenderId();
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @return The senderId.
         */
        @java.lang.Override
        public long getSenderId() {
            return instance.getSenderId();
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @param value The senderId to set.
         * @return This builder for chaining.
         */
        public Builder setSenderId(long value) {
            copyOnWrite();
            instance.setSenderId(value);
            return this;
        }

        /**
         * <code>optional int64 sender_id = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSenderId() {
            copyOnWrite();
            instance.clearSenderId();
            return this;
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return instance.hasGroupId();
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @param value The groupId to set.
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {
            copyOnWrite();
            instance.setGroupId(value);
            return this;
        }

        /**
         * <code>optional int64 group_id = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            copyOnWrite();
            instance.clearGroupId();
            return this;
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @return Whether the isSystemMessage field is set.
         */
        @java.lang.Override
        public boolean hasIsSystemMessage() {
            return instance.hasIsSystemMessage();
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @return The isSystemMessage.
         */
        @java.lang.Override
        public boolean getIsSystemMessage() {
            return instance.getIsSystemMessage();
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @param value The isSystemMessage to set.
         * @return This builder for chaining.
         */
        public Builder setIsSystemMessage(boolean value) {
            copyOnWrite();
            instance.setIsSystemMessage(value);
            return this;
        }

        /**
         * <code>optional bool is_system_message = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIsSystemMessage() {
            copyOnWrite();
            instance.clearIsSystemMessage();
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

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @return A list containing the records.
         */
        @java.lang.Override
        public java.util.List<com.google.protobuf.ByteString> getRecordsList() {
            return java.util.Collections.unmodifiableList(instance.getRecordsList());
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @return The count of records.
         */
        @java.lang.Override
        public int getRecordsCount() {
            return instance.getRecordsCount();
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param index The index of the element to return.
         * @return The records at the given index.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getRecords(int index) {
            return instance.getRecords(index);
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param value The records to set.
         * @return This builder for chaining.
         */
        public Builder setRecords(int index, com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setRecords(index, value);
            return this;
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @param value The records to add.
         * @return This builder for chaining.
         */
        public Builder addRecords(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.addRecords(value);
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
            copyOnWrite();
            instance.addAllRecords(values);
            return this;
        }

        /**
         * <code>repeated bytes records = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecords() {
            copyOnWrite();
            instance.clearRecords();
            return this;
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @return Whether the sequenceId field is set.
         */
        @java.lang.Override
        public boolean hasSequenceId() {
            return instance.hasSequenceId();
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @return The sequenceId.
         */
        @java.lang.Override
        public int getSequenceId() {
            return instance.getSequenceId();
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @param value The sequenceId to set.
         * @return This builder for chaining.
         */
        public Builder setSequenceId(int value) {
            copyOnWrite();
            instance.setSequenceId(value);
            return this;
        }

        /**
         * <code>optional int32 sequence_id = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSequenceId() {
            copyOnWrite();
            instance.clearSequenceId();
            return this;
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @return Whether the preMessageId field is set.
         */
        @java.lang.Override
        public boolean hasPreMessageId() {
            return instance.hasPreMessageId();
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @return The preMessageId.
         */
        @java.lang.Override
        public long getPreMessageId() {
            return instance.getPreMessageId();
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @param value The preMessageId to set.
         * @return This builder for chaining.
         */
        public Builder setPreMessageId(long value) {
            copyOnWrite();
            instance.setPreMessageId(value);
            return this;
        }

        /**
         * <code>optional int64 pre_message_id = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPreMessageId() {
            copyOnWrite();
            instance.clearPreMessageId();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Message)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.message.Message();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "id_",
                        "deliveryDate_",
                        "modificationDate_",
                        "text_",
                        "senderId_",
                        "groupId_",
                        "isSystemMessage_",
                        "recipientId_",
                        "records_",
                        "sequenceId_",
                        "preMessageId_",};
                java.lang.String info =
                        "\u0000\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001\u1002\u0000\u0002"
                                + "\u1002\u0001\u0003\u1002\u0002\u0004\u1208\u0003\u0005\u1002\u0004\u0006\u1002\u0005"
                                + "\u0007\u1007\u0006\b\u1002\u0007\t\u001c\n\u1004\b\u000b\u1002\t";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.message.Message> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.message.Message.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.Message)
    private static final im.turms.client.model.proto.model.message.Message DEFAULT_INSTANCE;

    static {
        Message defaultInstance = new Message();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Message.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.message.Message getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Message> PARSER;

    public static com.google.protobuf.Parser<Message> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}