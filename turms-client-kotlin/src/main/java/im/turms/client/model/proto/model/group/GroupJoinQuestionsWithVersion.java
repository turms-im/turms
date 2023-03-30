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
 * Protobuf type {@code im.turms.proto.GroupJoinQuestionsWithVersion}
 */
public final class GroupJoinQuestionsWithVersion extends
        com.google.protobuf.GeneratedMessageLite<GroupJoinQuestionsWithVersion, GroupJoinQuestionsWithVersion.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupJoinQuestionsWithVersion)
        GroupJoinQuestionsWithVersionOrBuilder {
    private GroupJoinQuestionsWithVersion() {
        groupJoinQuestions_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int GROUP_JOIN_QUESTIONS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.group.GroupJoinQuestion> groupJoinQuestions_;

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.group.GroupJoinQuestion> getGroupJoinQuestionsList() {
        return groupJoinQuestions_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.group.GroupJoinQuestionOrBuilder> getGroupJoinQuestionsOrBuilderList() {
        return groupJoinQuestions_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    @java.lang.Override
    public int getGroupJoinQuestionsCount() {
        return groupJoinQuestions_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.group.GroupJoinQuestion getGroupJoinQuestions(
            int index) {
        return groupJoinQuestions_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    public im.turms.client.model.proto.model.group.GroupJoinQuestionOrBuilder getGroupJoinQuestionsOrBuilder(
            int index) {
        return groupJoinQuestions_.get(index);
    }

    private void ensureGroupJoinQuestionsIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.group.GroupJoinQuestion> tmp =
                groupJoinQuestions_;
        if (!tmp.isModifiable()) {
            groupJoinQuestions_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    private void setGroupJoinQuestions(
            int index,
            im.turms.client.model.proto.model.group.GroupJoinQuestion value) {
        value.getClass();
        ensureGroupJoinQuestionsIsMutable();
        groupJoinQuestions_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    private void addGroupJoinQuestions(
            im.turms.client.model.proto.model.group.GroupJoinQuestion value) {
        value.getClass();
        ensureGroupJoinQuestionsIsMutable();
        groupJoinQuestions_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    private void addGroupJoinQuestions(
            int index,
            im.turms.client.model.proto.model.group.GroupJoinQuestion value) {
        value.getClass();
        ensureGroupJoinQuestionsIsMutable();
        groupJoinQuestions_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    private void addAllGroupJoinQuestions(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.group.GroupJoinQuestion> values) {
        ensureGroupJoinQuestionsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupJoinQuestions_);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    private void clearGroupJoinQuestions() {
        groupJoinQuestions_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    private void removeGroupJoinQuestions(int index) {
        ensureGroupJoinQuestionsIsMutable();
        groupJoinQuestions_.remove(index);
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @param value The lastUpdatedDate to set.
     */
    private void setLastUpdatedDate(long value) {
        bitField0_ |= 0x00000001;
        lastUpdatedDate_ = value;
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     */
    private void clearLastUpdatedDate() {
        bitField0_ &= ~0x00000001;
        lastUpdatedDate_ = 0L;
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion parseFrom(
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
            im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupJoinQuestionsWithVersion}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupJoinQuestionsWithVersion)
            im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersionOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.group.GroupJoinQuestion> getGroupJoinQuestionsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupJoinQuestionsList());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        @java.lang.Override
        public int getGroupJoinQuestionsCount() {
            return instance.getGroupJoinQuestionsCount();
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupJoinQuestion getGroupJoinQuestions(
                int index) {
            return instance.getGroupJoinQuestions(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder setGroupJoinQuestions(
                int index,
                im.turms.client.model.proto.model.group.GroupJoinQuestion value) {
            copyOnWrite();
            instance.setGroupJoinQuestions(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder setGroupJoinQuestions(
                int index,
                im.turms.client.model.proto.model.group.GroupJoinQuestion.Builder builderForValue) {
            copyOnWrite();
            instance.setGroupJoinQuestions(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                im.turms.client.model.proto.model.group.GroupJoinQuestion value) {
            copyOnWrite();
            instance.addGroupJoinQuestions(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                int index,
                im.turms.client.model.proto.model.group.GroupJoinQuestion value) {
            copyOnWrite();
            instance.addGroupJoinQuestions(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                im.turms.client.model.proto.model.group.GroupJoinQuestion.Builder builderForValue) {
            copyOnWrite();
            instance.addGroupJoinQuestions(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                int index,
                im.turms.client.model.proto.model.group.GroupJoinQuestion.Builder builderForValue) {
            copyOnWrite();
            instance.addGroupJoinQuestions(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addAllGroupJoinQuestions(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.group.GroupJoinQuestion> values) {
            copyOnWrite();
            instance.addAllGroupJoinQuestions(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder clearGroupJoinQuestions() {
            copyOnWrite();
            instance.clearGroupJoinQuestions();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder removeGroupJoinQuestions(int index) {
            copyOnWrite();
            instance.removeGroupJoinQuestions(index);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return instance.hasLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return instance.getLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {
            copyOnWrite();
            instance.setLastUpdatedDate(value);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            copyOnWrite();
            instance.clearLastUpdatedDate();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupJoinQuestionsWithVersion)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupJoinQuestions_",
                        im.turms.client.model.proto.model.group.GroupJoinQuestion.class,
                        "lastUpdatedDate_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u1002"
                                + "\u0000";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupJoinQuestionsWithVersion)
    private static final im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion DEFAULT_INSTANCE;

    static {
        GroupJoinQuestionsWithVersion defaultInstance = new GroupJoinQuestionsWithVersion();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(GroupJoinQuestionsWithVersion.class, defaultInstance);
    }

    public static im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<GroupJoinQuestionsWithVersion> PARSER;

    public static com.google.protobuf.Parser<GroupJoinQuestionsWithVersion> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}