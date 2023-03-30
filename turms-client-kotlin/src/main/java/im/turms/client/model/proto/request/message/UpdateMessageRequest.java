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
 * Protobuf type {@code im.turms.proto.UpdateMessageRequest}
 */
public final class UpdateMessageRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateMessageRequest, UpdateMessageRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateMessageRequest)
        UpdateMessageRequestOrBuilder {
    private UpdateMessageRequest() {
        text_ = "";
        records_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int MESSAGE_ID_FIELD_NUMBER = 1;
    private long messageId_;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 message_id = 1;</code>
     *
     * @return The messageId.
     */
    @java.lang.Override
    public long getMessageId() {
        return messageId_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 message_id = 1;</code>
     *
     * @param value The messageId to set.
     */
    private void setMessageId(long value) {

        messageId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 message_id = 1;</code>
     */
    private void clearMessageId() {

        messageId_ = 0L;
    }

    public static final int TEXT_FIELD_NUMBER = 2;
    private java.lang.String text_;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @return Whether the text field is set.
     */
    @java.lang.Override
    public boolean hasText() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @return The text.
     */
    @java.lang.Override
    public java.lang.String getText() {
        return text_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @return The bytes for text.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTextBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(text_);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @param value The text to set.
     */
    private void setText(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        text_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     */
    private void clearText() {
        bitField0_ &= ~0x00000001;
        text_ = getDefaultInstance().getText();
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @param value The bytes for text to set.
     */
    private void setTextBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        text_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int RECORDS_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.ProtobufList<com.google.protobuf.ByteString> records_;

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @return A list containing the records.
     */
    @java.lang.Override
    public java.util.List<com.google.protobuf.ByteString> getRecordsList() {
        return records_;
    }

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @return The count of records.
     */
    @java.lang.Override
    public int getRecordsCount() {
        return records_.size();
    }

    /**
     * <code>repeated bytes records = 3;</code>
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
     * <code>repeated bytes records = 3;</code>
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
     * <code>repeated bytes records = 3;</code>
     *
     * @param value The records to add.
     */
    private void addRecords(com.google.protobuf.ByteString value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureRecordsIsMutable();
        records_.add(value);
    }

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @param values The records to add.
     */
    private void addAllRecords(
            java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
        ensureRecordsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, records_);
    }

    /**
     * <code>repeated bytes records = 3;</code>
     */
    private void clearRecords() {
        records_ = emptyProtobufList();
    }

    public static final int RECALL_DATE_FIELD_NUMBER = 4;
    private long recallDate_;

    /**
     * <code>optional int64 recall_date = 4;</code>
     *
     * @return Whether the recallDate field is set.
     */
    @java.lang.Override
    public boolean hasRecallDate() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 recall_date = 4;</code>
     *
     * @return The recallDate.
     */
    @java.lang.Override
    public long getRecallDate() {
        return recallDate_;
    }

    /**
     * <code>optional int64 recall_date = 4;</code>
     *
     * @param value The recallDate to set.
     */
    private void setRecallDate(long value) {
        bitField0_ |= 0x00000002;
        recallDate_ = value;
    }

    /**
     * <code>optional int64 recall_date = 4;</code>
     */
    private void clearRecallDate() {
        bitField0_ &= ~0x00000002;
        recallDate_ = 0L;
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest parseFrom(
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
            im.turms.client.model.proto.request.message.UpdateMessageRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateMessageRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.message.UpdateMessageRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateMessageRequest)
            im.turms.client.model.proto.request.message.UpdateMessageRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.message.UpdateMessageRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int64 message_id = 1;</code>
         *
         * @return The messageId.
         */
        @java.lang.Override
        public long getMessageId() {
            return instance.getMessageId();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int64 message_id = 1;</code>
         *
         * @param value The messageId to set.
         * @return This builder for chaining.
         */
        public Builder setMessageId(long value) {
            copyOnWrite();
            instance.setMessageId(value);
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int64 message_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMessageId() {
            copyOnWrite();
            instance.clearMessageId();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
         *
         * @return Whether the text field is set.
         */
        @java.lang.Override
        public boolean hasText() {
            return instance.hasText();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
         *
         * @return The text.
         */
        @java.lang.Override
        public java.lang.String getText() {
            return instance.getText();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
         *
         * @return The bytes for text.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getTextBytes() {
            return instance.getTextBytes();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
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
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearText() {
            copyOnWrite();
            instance.clearText();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
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
         * <code>repeated bytes records = 3;</code>
         *
         * @return A list containing the records.
         */
        @java.lang.Override
        public java.util.List<com.google.protobuf.ByteString> getRecordsList() {
            return java.util.Collections.unmodifiableList(instance.getRecordsList());
        }

        /**
         * <code>repeated bytes records = 3;</code>
         *
         * @return The count of records.
         */
        @java.lang.Override
        public int getRecordsCount() {
            return instance.getRecordsCount();
        }

        /**
         * <code>repeated bytes records = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The records at the given index.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getRecords(int index) {
            return instance.getRecords(index);
        }

        /**
         * <code>repeated bytes records = 3;</code>
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
         * <code>repeated bytes records = 3;</code>
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
         * <code>repeated bytes records = 3;</code>
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
         * <code>repeated bytes records = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecords() {
            copyOnWrite();
            instance.clearRecords();
            return this;
        }

        /**
         * <code>optional int64 recall_date = 4;</code>
         *
         * @return Whether the recallDate field is set.
         */
        @java.lang.Override
        public boolean hasRecallDate() {
            return instance.hasRecallDate();
        }

        /**
         * <code>optional int64 recall_date = 4;</code>
         *
         * @return The recallDate.
         */
        @java.lang.Override
        public long getRecallDate() {
            return instance.getRecallDate();
        }

        /**
         * <code>optional int64 recall_date = 4;</code>
         *
         * @param value The recallDate to set.
         * @return This builder for chaining.
         */
        public Builder setRecallDate(long value) {
            copyOnWrite();
            instance.setRecallDate(value);
            return this;
        }

        /**
         * <code>optional int64 recall_date = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecallDate() {
            copyOnWrite();
            instance.clearRecallDate();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateMessageRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.message.UpdateMessageRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "messageId_",
                        "text_",
                        "records_",
                        "recallDate_",};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0002\u0002\u1208"
                                + "\u0000\u0003\u001c\u0004\u1002\u0001";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.message.UpdateMessageRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.message.UpdateMessageRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateMessageRequest)
    private static final im.turms.client.model.proto.request.message.UpdateMessageRequest DEFAULT_INSTANCE;

    static {
        UpdateMessageRequest defaultInstance = new UpdateMessageRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UpdateMessageRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.message.UpdateMessageRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateMessageRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateMessageRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}