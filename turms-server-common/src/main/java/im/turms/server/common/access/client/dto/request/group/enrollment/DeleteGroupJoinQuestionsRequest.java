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

package im.turms.server.common.access.client.dto.request.group.enrollment;

/**
 * Protobuf type {@code im.turms.proto.DeleteGroupJoinQuestionsRequest}
 */
public final class DeleteGroupJoinQuestionsRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteGroupJoinQuestionsRequest)
        DeleteGroupJoinQuestionsRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                DeleteGroupJoinQuestionsRequest.class.getName());
    }

    // Use DeleteGroupJoinQuestionsRequest.newBuilder() to construct.
    private DeleteGroupJoinQuestionsRequest(
            com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private DeleteGroupJoinQuestionsRequest() {
        questionIds_ = emptyLongList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_DeleteGroupJoinQuestionsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_DeleteGroupJoinQuestionsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.class,
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder.class);
    }

    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_ = 0L;

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    public static final int QUESTION_IDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList questionIds_ = emptyLongList();

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @return A list containing the questionIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getQuestionIdsList() {
        return questionIds_;
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @return The count of questionIds.
     */
    public int getQuestionIdsCount() {
        return questionIds_.size();
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The questionIds at the given index.
     */
    public long getQuestionIds(int index) {
        return questionIds_.getLong(index);
    }

    private int questionIdsMemoizedSerializedSize = -1;

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
        if (groupId_ != 0L) {
            output.writeInt64(1, groupId_);
        }
        if (!getQuestionIdsList().isEmpty()) {
            output.writeUInt32NoTag(18);
            output.writeUInt32NoTag(questionIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < questionIds_.size(); i++) {
            output.writeInt64NoTag(questionIds_.getLong(i));
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
        if (groupId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, groupId_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < questionIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(questionIds_.getLong(i));
            }
            size += dataSize;
            if (!getQuestionIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            questionIdsMemoizedSerializedSize = dataSize;
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
        if (!(obj instanceof DeleteGroupJoinQuestionsRequest other)) {
            return super.equals(obj);
        }

        if (getGroupId() != other.getGroupId()) {
            return false;
        }
        return getQuestionIdsList().equals(other.getQuestionIdsList())
                && getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        if (getQuestionIdsCount() > 0) {
            hash = (37 * hash) + QUESTION_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getQuestionIdsList().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.DeleteGroupJoinQuestionsRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteGroupJoinQuestionsRequest)
            im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_DeleteGroupJoinQuestionsRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_DeleteGroupJoinQuestionsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.class,
                            im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            groupId_ = 0L;
            questionIds_ = emptyLongList();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000004;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_DeleteGroupJoinQuestionsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest build() {
            im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest result =
                    new im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000004;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.groupId_ = groupId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                questionIds_.makeImmutable();
                result.questionIds_ = questionIds_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getGroupId() != 0L) {
                setGroupId(other.getGroupId());
            }
            if (!other.questionIds_.isEmpty()) {
                if (questionIds_.isEmpty()) {
                    questionIds_ = other.questionIds_;
                    questionIds_.makeImmutable();
                    bitField0_ |= 0x00000002;
                } else {
                    ensureQuestionIdsIsMutable();
                    questionIds_.addAll(other.questionIds_);
                }
                onChanged();
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000004;
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
                        bitField0_ &= ~0x00000004;
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
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            long v = input.readInt64();
                            ensureQuestionIdsIsMutable();
                            questionIds_.addLong(v);
                        } // case 16
                        case 18 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureQuestionIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                questionIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 18
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

        private long groupId_;

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return groupId_;
        }

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @param value The groupId to set.
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000001;
            groupId_ = 0L;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList questionIds_ = emptyLongList();

        private void ensureQuestionIdsIsMutable() {
            if (!questionIds_.isModifiable()) {
                questionIds_ = makeMutableCopy(questionIds_);
            }
            bitField0_ |= 0x00000002;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return A list containing the questionIds.
         */
        public java.util.List<java.lang.Long> getQuestionIdsList() {
            questionIds_.makeImmutable();
            return questionIds_;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return The count of questionIds.
         */
        public int getQuestionIdsCount() {
            return questionIds_.size();
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The questionIds at the given index.
         */
        public long getQuestionIds(int index) {
            return questionIds_.getLong(index);
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The questionIds to set.
         * @return This builder for chaining.
         */
        public Builder setQuestionIds(int index, long value) {

            ensureQuestionIdsIsMutable();
            questionIds_.setLong(index, value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param value The questionIds to add.
         * @return This builder for chaining.
         */
        public Builder addQuestionIds(long value) {

            ensureQuestionIdsIsMutable();
            questionIds_.addLong(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param values The questionIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllQuestionIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureQuestionIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, questionIds_);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuestionIds() {
            questionIds_ = emptyLongList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000004;
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
                bitField0_ &= ~0x00000004;
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
                        ((bitField0_ & 0x00000004) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteGroupJoinQuestionsRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteGroupJoinQuestionsRequest)
    private static final im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DeleteGroupJoinQuestionsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public DeleteGroupJoinQuestionsRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<DeleteGroupJoinQuestionsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DeleteGroupJoinQuestionsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}