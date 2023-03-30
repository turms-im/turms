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

package im.turms.client.model.proto.request.group.enrollment;

/**
 * Protobuf type {@code im.turms.proto.UpdateGroupJoinQuestionRequest}
 */
public final class UpdateGroupJoinQuestionRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateGroupJoinQuestionRequest, UpdateGroupJoinQuestionRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateGroupJoinQuestionRequest)
        UpdateGroupJoinQuestionRequestOrBuilder {
    private UpdateGroupJoinQuestionRequest() {
        question_ = "";
        answers_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
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

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 question_id = 1;</code>
     *
     * @param value The questionId to set.
     */
    private void setQuestionId(long value) {

        questionId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 question_id = 1;</code>
     */
    private void clearQuestionId() {

        questionId_ = 0L;
    }

    public static final int QUESTION_FIELD_NUMBER = 2;
    private java.lang.String question_;

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
        return question_;
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
        return com.google.protobuf.ByteString.copyFromUtf8(question_);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string question = 2;</code>
     *
     * @param value The question to set.
     */
    private void setQuestion(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        question_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string question = 2;</code>
     */
    private void clearQuestion() {
        bitField0_ &= ~0x00000001;
        question_ = getDefaultInstance().getQuestion();
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string question = 2;</code>
     *
     * @param value The bytes for question to set.
     */
    private void setQuestionBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        question_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int ANSWERS_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.ProtobufList<java.lang.String> answers_;

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @return A list containing the answers.
     */
    @java.lang.Override
    public java.util.List<java.lang.String> getAnswersList() {
        return answers_;
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @return The count of answers.
     */
    @java.lang.Override
    public int getAnswersCount() {
        return answers_.size();
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The answers at the given index.
     */
    @java.lang.Override
    public java.lang.String getAnswers(int index) {
        return answers_.get(index);
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the answers at the given index.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAnswersBytes(int index) {
        return com.google.protobuf.ByteString.copyFromUtf8(answers_.get(index));
    }

    private void ensureAnswersIsMutable() {
        com.google.protobuf.Internal.ProtobufList<java.lang.String> tmp = answers_;
        if (!tmp.isModifiable()) {
            answers_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index to set the value at.
     * @param value The answers to set.
     */
    private void setAnswers(int index, java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureAnswersIsMutable();
        answers_.set(index, value);
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param value The answers to add.
     */
    private void addAnswers(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureAnswersIsMutable();
        answers_.add(value);
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param values The answers to add.
     */
    private void addAllAnswers(java.lang.Iterable<java.lang.String> values) {
        ensureAnswersIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, answers_);
    }

    /**
     * <code>repeated string answers = 3;</code>
     */
    private void clearAnswers() {
        answers_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
    }

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param value The bytes of the answers to add.
     */
    private void addAnswersBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        ensureAnswersIsMutable();
        answers_.add(value.toStringUtf8());
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

    /**
     * <code>optional int32 score = 4;</code>
     *
     * @param value The score to set.
     */
    private void setScore(int value) {
        bitField0_ |= 0x00000002;
        score_ = value;
    }

    /**
     * <code>optional int32 score = 4;</code>
     */
    private void clearScore() {
        bitField0_ &= ~0x00000002;
        score_ = 0;
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest parseFrom(
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
            im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateGroupJoinQuestionRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateGroupJoinQuestionRequest)
            im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

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
            return instance.getQuestionId();
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
            copyOnWrite();
            instance.setQuestionId(value);
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
            copyOnWrite();
            instance.clearQuestionId();
            return this;
        }

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
            return instance.hasQuestion();
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
            return instance.getQuestion();
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
            return instance.getQuestionBytes();
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
            copyOnWrite();
            instance.setQuestion(value);
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
            copyOnWrite();
            instance.clearQuestion();
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
            copyOnWrite();
            instance.setQuestionBytes(value);
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @return A list containing the answers.
         */
        @java.lang.Override
        public java.util.List<java.lang.String> getAnswersList() {
            return java.util.Collections.unmodifiableList(instance.getAnswersList());
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @return The count of answers.
         */
        @java.lang.Override
        public int getAnswersCount() {
            return instance.getAnswersCount();
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The answers at the given index.
         */
        @java.lang.Override
        public java.lang.String getAnswers(int index) {
            return instance.getAnswers(index);
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the answers at the given index.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getAnswersBytes(int index) {
            return instance.getAnswersBytes(index);
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The answers to set.
         * @return This builder for chaining.
         */
        public Builder setAnswers(int index, java.lang.String value) {
            copyOnWrite();
            instance.setAnswers(index, value);
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param value The answers to add.
         * @return This builder for chaining.
         */
        public Builder addAnswers(java.lang.String value) {
            copyOnWrite();
            instance.addAnswers(value);
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param values The answers to add.
         * @return This builder for chaining.
         */
        public Builder addAllAnswers(java.lang.Iterable<java.lang.String> values) {
            copyOnWrite();
            instance.addAllAnswers(values);
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAnswers() {
            copyOnWrite();
            instance.clearAnswers();
            return this;
        }

        /**
         * <code>repeated string answers = 3;</code>
         *
         * @param value The bytes of the answers to add.
         * @return This builder for chaining.
         */
        public Builder addAnswersBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.addAnswersBytes(value);
            return this;
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @return Whether the score field is set.
         */
        @java.lang.Override
        public boolean hasScore() {
            return instance.hasScore();
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @return The score.
         */
        @java.lang.Override
        public int getScore() {
            return instance.getScore();
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @param value The score to set.
         * @return This builder for chaining.
         */
        public Builder setScore(int value) {
            copyOnWrite();
            instance.setScore(value);
            return this;
        }

        /**
         * <code>optional int32 score = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearScore() {
            copyOnWrite();
            instance.clearScore();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateGroupJoinQuestionRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "questionId_",
                        "question_",
                        "answers_",
                        "score_",};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0002\u0002\u1208"
                                + "\u0000\u0003\u021a\u0004\u1004\u0001";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateGroupJoinQuestionRequest)
    private static final im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest DEFAULT_INSTANCE;

    static {
        UpdateGroupJoinQuestionRequest defaultInstance = new UpdateGroupJoinQuestionRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateGroupJoinQuestionRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.enrollment.UpdateGroupJoinQuestionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateGroupJoinQuestionRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateGroupJoinQuestionRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}