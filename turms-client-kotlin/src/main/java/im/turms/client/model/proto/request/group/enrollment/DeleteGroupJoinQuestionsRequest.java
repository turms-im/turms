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
 * Protobuf type {@code im.turms.proto.DeleteGroupJoinQuestionsRequest}
 */
public final class DeleteGroupJoinQuestionsRequest extends
        com.google.protobuf.GeneratedMessageLite<DeleteGroupJoinQuestionsRequest, DeleteGroupJoinQuestionsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteGroupJoinQuestionsRequest)
        DeleteGroupJoinQuestionsRequestOrBuilder {
    private DeleteGroupJoinQuestionsRequest() {
        questionIds_ = emptyLongList();
        customAttributes_ = emptyProtobufList();
    }

    public static final int GROUP_ID_FIELD_NUMBER = 1;
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
     */
    private void setGroupId(long value) {

        groupId_ = value;
    }

    /**
     * <code>int64 group_id = 1;</code>
     */
    private void clearGroupId() {

        groupId_ = 0L;
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

    private final int questionIdsMemoizedSerializedSize = -1;

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

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private void ensureCustomAttributesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                customAttributes_;
        if (!tmp.isModifiable()) {
            customAttributes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void setCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addAllCustomAttributes(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureCustomAttributesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, customAttributes_);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void clearCustomAttributes() {
        customAttributes_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void removeCustomAttributes(int index) {
        ensureCustomAttributesIsMutable();
        customAttributes_.remove(index);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.DeleteGroupJoinQuestionsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteGroupJoinQuestionsRequest)
            im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <code>int64 group_id = 1;</code>
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
         * <code>int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            copyOnWrite();
            instance.clearGroupId();
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
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
            return java.util.Collections.unmodifiableList(instance.getCustomAttributesList());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public int getCustomAttributesCount() {
            return instance.getCustomAttributesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
            return instance.getCustomAttributes(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllCustomAttributes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            copyOnWrite();
            instance.clearCustomAttributes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            copyOnWrite();
            instance.removeCustomAttributes(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteGroupJoinQuestionsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"groupId_",
                        "questionIds_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0000\u0001\u000f\u0003\u0000\u0002\u0000\u0001\u0002\u0002%\u000f"
                                + "\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteGroupJoinQuestionsRequest)
    private static final im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest DEFAULT_INSTANCE;
    static {
        DeleteGroupJoinQuestionsRequest defaultInstance = new DeleteGroupJoinQuestionsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(DeleteGroupJoinQuestionsRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.enrollment.DeleteGroupJoinQuestionsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<DeleteGroupJoinQuestionsRequest> PARSER;

    public static com.google.protobuf.Parser<DeleteGroupJoinQuestionsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}