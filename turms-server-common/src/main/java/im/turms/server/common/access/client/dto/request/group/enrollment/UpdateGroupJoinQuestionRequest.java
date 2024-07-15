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
 * Protobuf type {@code im.turms.proto.UpdateGroupJoinQuestionRequest}
 */
public final class UpdateGroupJoinQuestionRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateGroupJoinQuestionRequest)
        UpdateGroupJoinQuestionRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateGroupJoinQuestionRequest.class.getName());
    }

    // Use UpdateGroupJoinQuestionRequest.newBuilder() to construct.
    private UpdateGroupJoinQuestionRequest(
            com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateGroupJoinQuestionRequest() {
        question_ = "";
        answers_ = com.google.protobuf.LazyStringArrayList.emptyList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinQuestionRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinQuestionRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.class,
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder.class);
    }

    private int bitField0_;
    public static final int QUESTION_ID_FIELD_NUMBER = 1;
    private long questionId_;

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 question_id = 1;</code>
     *
     * @return The questionId.
     */
    @java.lang.Override
    public long getQuestionId() {
        return questionId_;
    }

    public static final int QUESTION_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object question_ = "";

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>optional string question = 2;</code>
     *
     * @return Whether the question field is set.
     */
    @java.lang.Override
    public boolean hasQuestion() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>optional string question = 2;</code>
     *
     * @return The question.
     */
    @java.lang.Override
    public java.lang.String getQuestion() {
        java.lang.Object ref = question_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            question_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>optional string question = 2;</code>
     *
     * @return The bytes for question.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getQuestionBytes() {
        java.lang.Object ref = question_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            question_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ANSWERS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList answers_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @return A list containing the answers.
     */
    public com.google.protobuf.ProtocolStringList getAnswersList() {
        return answers_;
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @return The count of answers.
     */
    public int getAnswersCount() {
        return answers_.size();
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The answers at the given index.
     */
    public java.lang.String getAnswers(int index) {
        return answers_.get(index);
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the answers at the given index.
     */
    public com.google.protobuf.ByteString getAnswersBytes(int index) {
        return answers_.getByteString(index);
    }

    public static final int SCORE_FIELD_NUMBER = 4;
    private int score_;

    /**
     * <code>optional int32 score = 4;</code>
     *
     * @return Whether the score field is set.
     */
    @java.lang.Override
    public boolean hasScore() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int32 score = 4;</code>
     *
     * @return The score.
     */
    @java.lang.Override
    public int getScore() {
        return score_;
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
        if (questionId_ != 0L) {
            output.writeInt64(1, questionId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, question_);
        }
        for (int i = 0; i < answers_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, answers_.getRaw(i));
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt32(4, score_);
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
        if (questionId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, questionId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, question_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < answers_.size(); i++) {
                dataSize += computeStringSizeNoTag(answers_.getRaw(i));
            }
            size += dataSize;
            size += getAnswersList().size();
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, score_);
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
        if (!(obj instanceof UpdateGroupJoinQuestionRequest other)) {
            return super.equals(obj);
        }

        if (getQuestionId() != other.getQuestionId()) {
            return false;
        }
        if (hasQuestion() != other.hasQuestion()) {
            return false;
        }
        if (hasQuestion()) {
            if (!getQuestion().equals(other.getQuestion())) {
                return false;
            }
        }
        if (!getAnswersList().equals(other.getAnswersList())) {
            return false;
        }
        if (hasScore() != other.hasScore()) {
            return false;
        }
        if (hasScore()) {
            if (getScore() != other.getScore()) {
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
        hash = (37 * hash) + QUESTION_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getQuestionId());
        if (hasQuestion()) {
            hash = (37 * hash) + QUESTION_FIELD_NUMBER;
            hash = (53 * hash) + getQuestion().hashCode();
        }
        if (getAnswersCount() > 0) {
            hash = (37 * hash) + ANSWERS_FIELD_NUMBER;
            hash = (53 * hash) + getAnswersList().hashCode();
        }
        if (hasScore()) {
            hash = (37 * hash) + SCORE_FIELD_NUMBER;
            hash = (53 * hash) + getScore();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateGroupJoinQuestionRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateGroupJoinQuestionRequest)
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinQuestionRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinQuestionRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.class,
                            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            questionId_ = 0L;
            question_ = "";
            answers_ = com.google.protobuf.LazyStringArrayList.emptyList();
            score_ = 0;
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000010;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass.internal_static_im_turms_proto_UpdateGroupJoinQuestionRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest build() {
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest result =
                    new im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000010;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.questionId_ = questionId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.question_ = question_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                answers_.makeImmutable();
                result.answers_ = answers_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.score_ = score_;
                to_bitField0_ |= 0x00000002;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getQuestionId() != 0L) {
                setQuestionId(other.getQuestionId());
            }
            if (other.hasQuestion()) {
                question_ = other.question_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.answers_.isEmpty()) {
                if (answers_.isEmpty()) {
                    answers_ = other.answers_;
                    bitField0_ |= 0x00000004;
                } else {
                    ensureAnswersIsMutable();
                    answers_.addAll(other.answers_);
                }
                onChanged();
            }
            if (other.hasScore()) {
                setScore(other.getScore());
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000010;
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
                        bitField0_ &= ~0x00000010;
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
                            questionId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            question_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            String s = input.readStringRequireUtf8();
                            ensureAnswersIsMutable();
                            answers_.add(s);
                        } // case 26
                        case 32 -> {
                            score_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
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

        private long questionId_;

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 question_id = 1;</code>
         *
         * @return The questionId.
         */
        @java.lang.Override
        public long getQuestionId() {
            return questionId_;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 question_id = 1;</code>
         *
         * @param value The questionId to set.
         * @return This builder for chaining.
         */
        public Builder setQuestionId(long value) {

            questionId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>int64 question_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuestionId() {
            bitField0_ &= ~0x00000001;
            questionId_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object question_ = "";

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>optional string question = 2;</code>
         *
         * @return Whether the question field is set.
         */
        public boolean hasQuestion() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>optional string question = 2;</code>
         *
         * @return The question.
         */
        public java.lang.String getQuestion() {
            java.lang.Object ref = question_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                question_ = s;
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
         * <code>optional string question = 2;</code>
         *
         * @return The bytes for question.
         */
        public com.google.protobuf.ByteString getQuestionBytes() {
            java.lang.Object ref = question_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                question_ = b;
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
         * <code>optional string question = 2;</code>
         *
         * @param value The question to set.
         * @return This builder for chaining.
         */
        public Builder setQuestion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            question_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>optional string question = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuestion() {
            question_ = getDefaultInstance().getQuestion();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>optional string question = 2;</code>
         *
         * @param value The bytes for question to set.
         * @return This builder for chaining.
         */
        public Builder setQuestionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            question_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList answers_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureAnswersIsMutable() {
            if (!answers_.isModifiable()) {
                answers_ = new com.google.protobuf.LazyStringArrayList(answers_);
            }
            bitField0_ |= 0x00000004;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @return A list containing the answers.
         */
        public com.google.protobuf.ProtocolStringList getAnswersList() {
            answers_.makeImmutable();
            return answers_;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @return The count of answers.
         */
        public int getAnswersCount() {
            return answers_.size();
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The answers at the given index.
         */
        public java.lang.String getAnswers(int index) {
            return answers_.get(index);
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the answers at the given index.
         */
        public com.google.protobuf.ByteString getAnswersBytes(int index) {
            return answers_.getByteString(index);
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The answers to set.
         * @return This builder for chaining.
         */
        public Builder setAnswers(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureAnswersIsMutable();
            answers_.set(index, value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param value The answers to add.
         * @return This builder for chaining.
         */
        public Builder addAnswers(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureAnswersIsMutable();
            answers_.add(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param values The answers to add.
         * @return This builder for chaining.
         */
        public Builder addAllAnswers(java.lang.Iterable<java.lang.String> values) {
            ensureAnswersIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, answers_);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAnswers() {
            answers_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param value The bytes of the answers to add.
         * @return This builder for chaining.
         */
        public Builder addAnswersBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureAnswersIsMutable();
            answers_.add(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int score_;

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @return Whether the score field is set.
         */
        @java.lang.Override
        public boolean hasScore() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @return The score.
         */
        @java.lang.Override
        public int getScore() {
            return score_;
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @param value The score to set.
         * @return This builder for chaining.
         */
        public Builder setScore(int value) {

            score_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearScore() {
            bitField0_ &= ~0x00000008;
            score_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000010) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000010;
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
                bitField0_ &= ~0x00000010;
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
                        ((bitField0_ & 0x00000010) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateGroupJoinQuestionRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateGroupJoinQuestionRequest)
    private static final im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateGroupJoinQuestionRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateGroupJoinQuestionRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateGroupJoinQuestionRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateGroupJoinQuestionRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}