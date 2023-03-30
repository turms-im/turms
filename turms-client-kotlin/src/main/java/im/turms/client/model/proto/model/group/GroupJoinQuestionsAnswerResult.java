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

package im.turms.client.model.proto.model.group;

/**
 * Protobuf type {@code im.turms.proto.GroupJoinQuestionsAnswerResult}
 */
public final class GroupJoinQuestionsAnswerResult extends
        com.google.protobuf.GeneratedMessageLite<GroupJoinQuestionsAnswerResult, GroupJoinQuestionsAnswerResult.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupJoinQuestionsAnswerResult)
        GroupJoinQuestionsAnswerResultOrBuilder {
    private GroupJoinQuestionsAnswerResult() {
        questionIds_ = emptyLongList();
    }

    public static final int SCORE_FIELD_NUMBER = 1;
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
     */
    private void setScore(int value) {

        score_ = value;
    }

    /**
     * <code>int32 score = 1;</code>
     */
    private void clearScore() {

        score_ = 0;
    }

    public static final int QUESTION_IDS_FIELD_NUMBER = 2;
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
    @java.lang.Override
    public int getQuestionIdsCount() {
        return questionIds_.size();
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The questionIds at the given index.
     */
    @java.lang.Override
    public long getQuestionIds(int index) {
        return questionIds_.getLong(index);
    }

    private int questionIdsMemoizedSerializedSize = -1;

    private void ensureQuestionIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = questionIds_;
        if (!tmp.isModifiable()) {
            questionIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The questionIds to set.
     */
    private void setQuestionIds(int index, long value) {
        ensureQuestionIdsIsMutable();
        questionIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @param value The questionIds to add.
     */
    private void addQuestionIds(long value) {
        ensureQuestionIdsIsMutable();
        questionIds_.addLong(value);
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     *
     * @param values The questionIds to add.
     */
    private void addAllQuestionIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureQuestionIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, questionIds_);
    }

    /**
     * <code>repeated int64 question_ids = 2;</code>
     */
    private void clearQuestionIds() {
        questionIds_ = emptyLongList();
    }

    public static final int JOINED_FIELD_NUMBER = 3;
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
     */
    private void setJoined(boolean value) {

        joined_ = value;
    }

    /**
     * <code>bool joined = 3;</code>
     */
    private void clearJoined() {

        joined_ = false;
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult parseFrom(
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
            im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupJoinQuestionsAnswerResult}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupJoinQuestionsAnswerResult)
            im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResultOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int32 score = 1;</code>
         *
         * @return The score.
         */
        @java.lang.Override
        public int getScore() {
            return instance.getScore();
        }

        /**
         * <code>int32 score = 1;</code>
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
         * <code>int32 score = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearScore() {
            copyOnWrite();
            instance.clearScore();
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return A list containing the questionIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getQuestionIdsList() {
            return java.util.Collections.unmodifiableList(instance.getQuestionIdsList());
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return The count of questionIds.
         */
        @java.lang.Override
        public int getQuestionIdsCount() {
            return instance.getQuestionIdsCount();
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The questionIds at the given index.
         */
        @java.lang.Override
        public long getQuestionIds(int index) {
            return instance.getQuestionIds(index);
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param value The questionIds to set.
         * @return This builder for chaining.
         */
        public Builder setQuestionIds(int index, long value) {
            copyOnWrite();
            instance.setQuestionIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param value The questionIds to add.
         * @return This builder for chaining.
         */
        public Builder addQuestionIds(long value) {
            copyOnWrite();
            instance.addQuestionIds(value);
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @param values The questionIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllQuestionIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllQuestionIds(values);
            return this;
        }

        /**
         * <code>repeated int64 question_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuestionIds() {
            copyOnWrite();
            instance.clearQuestionIds();
            return this;
        }

        /**
         * <code>bool joined = 3;</code>
         *
         * @return The joined.
         */
        @java.lang.Override
        public boolean getJoined() {
            return instance.getJoined();
        }

        /**
         * <code>bool joined = 3;</code>
         *
         * @param value The joined to set.
         * @return This builder for chaining.
         */
        public Builder setJoined(boolean value) {
            copyOnWrite();
            instance.setJoined(value);
            return this;
        }

        /**
         * <code>bool joined = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJoined() {
            copyOnWrite();
            instance.clearJoined();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupJoinQuestionsAnswerResult)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects =
                        new java.lang.Object[]{"score_", "questionIds_", "joined_",};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u0004\u0002%\u0003"
                                + "\u0007";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupJoinQuestionsAnswerResult)
    private static final im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult DEFAULT_INSTANCE;

    static {
        GroupJoinQuestionsAnswerResult defaultInstance = new GroupJoinQuestionsAnswerResult();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(GroupJoinQuestionsAnswerResult.class, defaultInstance);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<GroupJoinQuestionsAnswerResult> PARSER;

    public static com.google.protobuf.Parser<GroupJoinQuestionsAnswerResult> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}