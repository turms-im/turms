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
 * Protobuf type {@code im.turms.proto.UpdateMessageRequest}
 */
public final class UpdateMessageRequest extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateMessageRequest)
        UpdateMessageRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UpdateMessageRequest.newBuilder() to construct.
    private UpdateMessageRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateMessageRequest() {
        text_ = "";
        records_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UpdateMessageRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass.internal_static_im_turms_proto_UpdateMessageRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass.internal_static_im_turms_proto_UpdateMessageRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.class,
                        im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.Builder.class);
    }

    private int bitField0_;
    public static final int MESSAGE_ID_FIELD_NUMBER = 1;
    private long messageId_ = 0L;

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

    public static final int TEXT_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object text_ = "";

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

    public static final int RECORDS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private java.util.List<com.google.protobuf.ByteString> records_;

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
    public int getRecordsCount() {
        return records_.size();
    }

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The records at the given index.
     */
    public com.google.protobuf.ByteString getRecords(int index) {
        return records_.get(index);
    }

    public static final int RECALL_DATE_FIELD_NUMBER = 4;
    private long recallDate_ = 0L;

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
        if (messageId_ != 0L) {
            output.writeInt64(1, messageId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 2, text_);
        }
        for (com.google.protobuf.ByteString bytes : records_) {
            output.writeBytes(3, bytes);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(4, recallDate_);
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
        if (messageId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, messageId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, text_);
        }
        {
            int dataSize = 0;
            for (com.google.protobuf.ByteString bytes : records_) {
                dataSize += com.google.protobuf.CodedOutputStream.computeBytesSizeNoTag(bytes);
            }
            size += dataSize;
            size += 1 * getRecordsList().size();
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, recallDate_);
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
        if (!(obj instanceof UpdateMessageRequest other)) {
            return super.equals(obj);
        }

        if (getMessageId() != other.getMessageId()) {
            return false;
        }
        if (hasText() != other.hasText()) {
            return false;
        }
        if (hasText()) {
            if (!getText().equals(other.getText())) {
                return false;
            }
        }
        if (!getRecordsList().equals(other.getRecordsList())) {
            return false;
        }
        if (hasRecallDate() != other.hasRecallDate()) {
            return false;
        }
        if (hasRecallDate()) {
            if (getRecallDate() != other.getRecallDate()) {
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
        hash = (37 * hash) + MESSAGE_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getMessageId());
        if (hasText()) {
            hash = (37 * hash) + TEXT_FIELD_NUMBER;
            hash = (53 * hash) + getText().hashCode();
        }
        if (getRecordsCount() > 0) {
            hash = (37 * hash) + RECORDS_FIELD_NUMBER;
            hash = (53 * hash) + getRecordsList().hashCode();
        }
        if (hasRecallDate()) {
            hash = (37 * hash) + RECALL_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRecallDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateMessageRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateMessageRequest)
            im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass.internal_static_im_turms_proto_UpdateMessageRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass.internal_static_im_turms_proto_UpdateMessageRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.class,
                            im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            messageId_ = 0L;
            text_ = "";
            records_ = java.util.Collections.emptyList();
            recallDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass.internal_static_im_turms_proto_UpdateMessageRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest build() {
            im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest result =
                    new im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest result) {
            if (((bitField0_ & 0x00000004) != 0)) {
                records_ = java.util.Collections.unmodifiableList(records_);
                bitField0_ &= ~0x00000004;
            }
            result.records_ = records_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.messageId_ = messageId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.text_ = text_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.recallDate_ = recallDate_;
                to_bitField0_ |= 0x00000002;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getMessageId() != 0L) {
                setMessageId(other.getMessageId());
            }
            if (other.hasText()) {
                text_ = other.text_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.records_.isEmpty()) {
                if (records_.isEmpty()) {
                    records_ = other.records_;
                    bitField0_ &= ~0x00000004;
                } else {
                    ensureRecordsIsMutable();
                    records_.addAll(other.records_);
                }
                onChanged();
            }
            if (other.hasRecallDate()) {
                setRecallDate(other.getRecallDate());
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
                            messageId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            text_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            com.google.protobuf.ByteString v = input.readBytes();
                            ensureRecordsIsMutable();
                            records_.add(v);
                        } // case 26
                        case 32 -> {
                            recallDate_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
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
         * @return This builder for chaining.
         */
        public Builder setMessageId(long value) {

            messageId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
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
            bitField0_ &= ~0x00000001;
            messageId_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object text_ = "";

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
         *
         * @return Whether the text field is set.
         */
        public boolean hasText() {
            return ((bitField0_ & 0x00000002) != 0);
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
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string text = 2;</code>
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
            if (value == null) {
                throw new NullPointerException();
            }
            text_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
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
            text_ = getDefaultInstance().getText();
            bitField0_ &= ~0x00000002;
            onChanged();
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
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            text_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.util.List<com.google.protobuf.ByteString> records_ =
                java.util.Collections.emptyList();

        private void ensureRecordsIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                records_ = new java.util.ArrayList<>(records_);
                bitField0_ |= 0x00000004;
            }
        }

        /**
         * <code>repeated bytes records = 3;</code>
         *
         * @return A list containing the records.
         */
        public java.util.List<com.google.protobuf.ByteString> getRecordsList() {
            return ((bitField0_ & 0x00000004) != 0)
                    ? java.util.Collections.unmodifiableList(records_)
                    : records_;
        }

        /**
         * <code>repeated bytes records = 3;</code>
         *
         * @return The count of records.
         */
        public int getRecordsCount() {
            return records_.size();
        }

        /**
         * <code>repeated bytes records = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The records at the given index.
         */
        public com.google.protobuf.ByteString getRecords(int index) {
            return records_.get(index);
        }

        /**
         * <code>repeated bytes records = 3;</code>
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
         * <code>repeated bytes records = 3;</code>
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
         * <code>repeated bytes records = 3;</code>
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
         * <code>repeated bytes records = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecords() {
            records_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        private long recallDate_;

        /**
         * <code>optional int64 recall_date = 4;</code>
         *
         * @return Whether the recallDate field is set.
         */
        @java.lang.Override
        public boolean hasRecallDate() {
            return ((bitField0_ & 0x00000008) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setRecallDate(long value) {

            recallDate_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 recall_date = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRecallDate() {
            bitField0_ &= ~0x00000008;
            recallDate_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateMessageRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateMessageRequest)
    private static final im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest();
    }

    public static im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateMessageRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateMessageRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateMessageRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateMessageRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.message.UpdateMessageRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}