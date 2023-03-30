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
 * Protobuf type {@code im.turms.proto.CreateGroupJoinQuestionsRequest}
 */
public final class CreateGroupJoinQuestionsRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateGroupJoinQuestionsRequest)
        CreateGroupJoinQuestionsRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use CreateGroupJoinQuestionsRequest.newBuilder() to construct.
    private CreateGroupJoinQuestionsRequest(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CreateGroupJoinQuestionsRequest() {
        questions_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new CreateGroupJoinQuestionsRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.class,
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder.class);
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

    public static final int QUESTIONS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> questions_;

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> getQuestionsList() {
        return questions_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> getQuestionsOrBuilderList() {
        return questions_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
     */
    @java.lang.Override
    public int getQuestionsCount() {
        return questions_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion getQuestions(
            int index) {
        return questions_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder getQuestionsOrBuilder(
            int index) {
        return questions_.get(index);
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
        if (groupId_ != 0L) {
            output.writeInt64(1, groupId_);
        }
        for (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion groupJoinQuestion : questions_) {
            output.writeMessage(2, groupJoinQuestion);
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
        for (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion groupJoinQuestion : questions_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, groupJoinQuestion);
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
        if (!(obj instanceof CreateGroupJoinQuestionsRequest other)) {
            return super.equals(obj);
        }

        if (getGroupId() != other.getGroupId()) {
            return false;
        }
        if (!getQuestionsList().equals(other.getQuestionsList())) {
            return false;
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
        hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        if (getQuestionsCount() > 0) {
            hash = (37 * hash) + QUESTIONS_FIELD_NUMBER;
            hash = (53 * hash) + getQuestionsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.CreateGroupJoinQuestionsRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateGroupJoinQuestionsRequest)
            im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.class,
                            im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            groupId_ = 0L;
            if (questionsBuilder_ == null) {
                questions_ = java.util.Collections.emptyList();
            } else {
                questions_ = null;
                questionsBuilder_.clear();
            }
            bitField0_ &= ~0x00000002;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass.internal_static_im_turms_proto_CreateGroupJoinQuestionsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest build() {
            im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest result =
                    new im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest result) {
            if (questionsBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)) {
                    questions_ = java.util.Collections.unmodifiableList(questions_);
                    bitField0_ &= ~0x00000002;
                }
                result.questions_ = questions_;
            } else {
                result.questions_ = questionsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.groupId_ = groupId_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getGroupId() != 0L) {
                setGroupId(other.getGroupId());
            }
            if (questionsBuilder_ == null) {
                if (!other.questions_.isEmpty()) {
                    if (questions_.isEmpty()) {
                        questions_ = other.questions_;
                        bitField0_ &= ~0x00000002;
                    } else {
                        ensureQuestionsIsMutable();
                        questions_.addAll(other.questions_);
                    }
                    onChanged();
                }
            } else {
                if (!other.questions_.isEmpty()) {
                    if (questionsBuilder_.isEmpty()) {
                        questionsBuilder_.dispose();
                        questionsBuilder_ = null;
                        questions_ = other.questions_;
                        bitField0_ &= ~0x00000002;
                        questionsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getQuestionsFieldBuilder()
                                        : null;
                    } else {
                        questionsBuilder_.addAllMessages(other.questions_);
                    }
                }
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
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion
                                                    .parser(),
                                            extensionRegistry);
                            if (questionsBuilder_ == null) {
                                ensureQuestionsIsMutable();
                                questions_.add(m);
                            } else {
                                questionsBuilder_.addMessage(m);
                            }
                        } // case 18
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

        private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> questions_ =
                java.util.Collections.emptyList();

        private void ensureQuestionsIsMutable() {
            if ((bitField0_ & 0x00000002) == 0) {
                questions_ = new java.util.ArrayList<>(questions_);
                bitField0_ |= 0x00000002;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> questionsBuilder_;

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> getQuestionsList() {
            if (questionsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(questions_);
            } else {
                return questionsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public int getQuestionsCount() {
            if (questionsBuilder_ == null) {
                return questions_.size();
            } else {
                return questionsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion getQuestions(
                int index) {
            if (questionsBuilder_ == null) {
                return questions_.get(index);
            } else {
                return questionsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder setQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion value) {
            if (questionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureQuestionsIsMutable();
                questions_.set(index, value);
                onChanged();
            } else {
                questionsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder setQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder builderForValue) {
            if (questionsBuilder_ == null) {
                ensureQuestionsIsMutable();
                questions_.set(index, builderForValue.build());
                onChanged();
            } else {
                questionsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder addQuestions(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion value) {
            if (questionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureQuestionsIsMutable();
                questions_.add(value);
                onChanged();
            } else {
                questionsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder addQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion value) {
            if (questionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureQuestionsIsMutable();
                questions_.add(index, value);
                onChanged();
            } else {
                questionsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder addQuestions(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder builderForValue) {
            if (questionsBuilder_ == null) {
                ensureQuestionsIsMutable();
                questions_.add(builderForValue.build());
                onChanged();
            } else {
                questionsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder addQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder builderForValue) {
            if (questionsBuilder_ == null) {
                ensureQuestionsIsMutable();
                questions_.add(index, builderForValue.build());
                onChanged();
            } else {
                questionsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder addAllQuestions(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> values) {
            if (questionsBuilder_ == null) {
                ensureQuestionsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, questions_);
                onChanged();
            } else {
                questionsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder clearQuestions() {
            if (questionsBuilder_ == null) {
                questions_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000002;
                onChanged();
            } else {
                questionsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public Builder removeQuestions(int index) {
            if (questionsBuilder_ == null) {
                ensureQuestionsIsMutable();
                questions_.remove(index);
                onChanged();
            } else {
                questionsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder getQuestionsBuilder(
                int index) {
            return getQuestionsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder getQuestionsOrBuilder(
                int index) {
            if (questionsBuilder_ == null) {
                return questions_.get(index);
            } else {
                return questionsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> getQuestionsOrBuilderList() {
            if (questionsBuilder_ != null) {
                return questionsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(questions_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder addQuestionsBuilder() {
            return getQuestionsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder addQuestionsBuilder(
                int index) {
            return getQuestionsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion questions = 2;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder> getQuestionsBuilderList() {
            return getQuestionsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> getQuestionsFieldBuilder() {
            if (questionsBuilder_ == null) {
                questionsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        questions_,
                        ((bitField0_ & 0x00000002) != 0),
                        getParentForChildren(),
                        isClean());
                questions_ = null;
            }
            return questionsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateGroupJoinQuestionsRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateGroupJoinQuestionsRequest)
    private static final im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CreateGroupJoinQuestionsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public CreateGroupJoinQuestionsRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<CreateGroupJoinQuestionsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CreateGroupJoinQuestionsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}