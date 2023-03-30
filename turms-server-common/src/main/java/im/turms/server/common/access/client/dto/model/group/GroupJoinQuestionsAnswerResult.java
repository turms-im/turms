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

package im.turms.server.common.access.client.dto.model.group;

/**
 * Protobuf type {@code im.turms.proto.GroupJoinQuestionsAnswerResult}
 */
public final class GroupJoinQuestionsAnswerResult extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupJoinQuestionsAnswerResult)
        GroupJoinQuestionsAnswerResultOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupJoinQuestionsAnswerResult.newBuilder() to construct.
    private GroupJoinQuestionsAnswerResult(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupJoinQuestionsAnswerResult() {
        questionIds_ = emptyLongList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupJoinQuestionsAnswerResult();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsAnswerResult_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsAnswerResult_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.class,
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.Builder.class);
    }

    public static final int SCORE_FIELD_NUMBER = 1;
    private int score_ = 0;

    /**
     * <code>int32 score = 1;</code>
     *
     * @return The score.
     */
    @java.lang.Override
    public int getScore() {
        return score_;
    }

    public static final int QUESTION_IDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList questionIds_;

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

    public static final int JOINED_FIELD_NUMBER = 3;
    private boolean joined_ = false;

    /**
     * <code>bool joined = 3;</code>
     *
     * @return The joined.
     */
    @java.lang.Override
    public boolean getJoined() {
        return joined_;
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
        getSerializedSize();
        if (score_ != 0) {
            output.writeInt32(1, score_);
        }
        if (getQuestionIdsList().size() > 0) {
            output.writeUInt32NoTag(18);
            output.writeUInt32NoTag(questionIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < questionIds_.size(); i++) {
            output.writeInt64NoTag(questionIds_.getLong(i));
        }
        if (joined_) {
            output.writeBool(3, joined_);
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
        if (score_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(1, score_);
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
        if (joined_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, joined_);
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
        if (!(obj instanceof GroupJoinQuestionsAnswerResult other)) {
            return super.equals(obj);
        }

        if (getScore() != other.getScore()) {
            return false;
        }
        if (!getQuestionIdsList().equals(other.getQuestionIdsList())) {
            return false;
        }
        if (getJoined() != other.getJoined()) {
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
        hash = (37 * hash) + SCORE_FIELD_NUMBER;
        hash = (53 * hash) + getScore();
        if (getQuestionIdsCount() > 0) {
            hash = (37 * hash) + QUESTION_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getQuestionIdsList().hashCode();
        }
        hash = (37 * hash) + JOINED_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getJoined());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
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
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult prototype) {
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
     * Protobuf type {@code im.turms.proto.GroupJoinQuestionsAnswerResult}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupJoinQuestionsAnswerResult)
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsAnswerResult_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsAnswerResult_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.class,
                            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            score_ = 0;
            questionIds_ = emptyLongList();
            joined_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsAnswerResult_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult build() {
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult result =
                    new im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult result) {
            if (((bitField0_ & 0x00000002) != 0)) {
                questionIds_.makeImmutable();
                bitField0_ &= ~0x00000002;
            }
            result.questionIds_ = questionIds_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.score_ = score_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.joined_ = joined_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getScore() != 0) {
                setScore(other.getScore());
            }
            if (!other.questionIds_.isEmpty()) {
                if (questionIds_.isEmpty()) {
                    questionIds_ = other.questionIds_;
                    bitField0_ &= ~0x00000002;
                } else {
                    ensureQuestionIdsIsMutable();
                    questionIds_.addAll(other.questionIds_);
                }
                onChanged();
            }
            if (other.getJoined()) {
                setJoined(other.getJoined());
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
                            score_ = input.readInt32();
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
                        case 24 -> {
                            joined_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
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

        private int score_;

        /**
         * <code>int32 score = 1;</code>
         *
         * @return The score.
         */
        @java.lang.Override
        public int getScore() {
            return score_;
        }

        /**
         * <code>int32 score = 1;</code>
         *
         * @param value The score to set.
         * @return This builder for chaining.
         */
        public Builder setScore(int value) {

            score_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int32 score = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearScore() {
            bitField0_ &= ~0x00000001;
            score_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList questionIds_ = emptyLongList();

        private void ensureQuestionIdsIsMutable() {
            if ((bitField0_ & 0x00000002) == 0) {
                questionIds_ = mutableCopy(questionIds_);
                bitField0_ |= 0x00000002;
            }
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return A list containing the questionIds.
         */
        public java.util.List<java.lang.Long> getQuestionIdsList() {
            return ((bitField0_ & 0x00000002) != 0)
                    ? java.util.Collections.unmodifiableList(questionIds_)
                    : questionIds_;
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

        private boolean joined_;

        /**
         * <code>bool joined = 3;</code>
         *
         * @return The joined.
         */
        @java.lang.Override
        public boolean getJoined() {
            return joined_;
        }

        /**
         * <code>bool joined = 3;</code>
         *
         * @param value The joined to set.
         * @return This builder for chaining.
         */
        public Builder setJoined(boolean value) {

            joined_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>bool joined = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJoined() {
            bitField0_ &= ~0x00000004;
            joined_ = false;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupJoinQuestionsAnswerResult)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupJoinQuestionsAnswerResult)
    private static final im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupJoinQuestionsAnswerResult> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupJoinQuestionsAnswerResult parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupJoinQuestionsAnswerResult> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupJoinQuestionsAnswerResult> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}