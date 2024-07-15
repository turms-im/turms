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
 * Protobuf type {@code im.turms.proto.CheckGroupJoinQuestionsAnswersRequest}
 */
public final class CheckGroupJoinQuestionsAnswersRequest extends
        com.google.protobuf.GeneratedMessageLite<CheckGroupJoinQuestionsAnswersRequest, CheckGroupJoinQuestionsAnswersRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
        CheckGroupJoinQuestionsAnswersRequestOrBuilder {
    private CheckGroupJoinQuestionsAnswersRequest() {
        customAttributes_ = emptyProtobufList();
    }

    public static final int QUESTION_ID_TO_ANSWER_FIELD_NUMBER = 1;

    private static final class QuestionIdToAnswerDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.Long, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntryLite.newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.INT64,
                        0L,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    private com.google.protobuf.MapFieldLite<java.lang.Long, java.lang.String> questionIdToAnswer_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.Long, java.lang.String> internalGetQuestionIdToAnswer() {
        return questionIdToAnswer_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.Long, java.lang.String> internalGetMutableQuestionIdToAnswer() {
        if (!questionIdToAnswer_.isMutable()) {
            questionIdToAnswer_ = questionIdToAnswer_.mutableCopy();
        }
        return questionIdToAnswer_;
    }

    @java.lang.Override

    public int getQuestionIdToAnswerCount() {
        return internalGetQuestionIdToAnswer().size();
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    @java.lang.Override

    public boolean containsQuestionIdToAnswer(long key) {

        return internalGetQuestionIdToAnswer().containsKey(key);
    }

    /**
     * Use {@link #getQuestionIdToAnswerMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.Long, java.lang.String> getQuestionIdToAnswer() {
        return getQuestionIdToAnswerMap();
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.Long, java.lang.String> getQuestionIdToAnswerMap() {
        return java.util.Collections.unmodifiableMap(internalGetQuestionIdToAnswer());
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    @java.lang.Override

    public /* nullable */
    java.lang.String getQuestionIdToAnswerOrDefault(
            long key,
            /* nullable */
            java.lang.String defaultValue) {

        java.util.Map<java.lang.Long, java.lang.String> map = internalGetQuestionIdToAnswer();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    @java.lang.Override

    public java.lang.String getQuestionIdToAnswerOrThrow(long key) {

        java.util.Map<java.lang.Long, java.lang.String> map = internalGetQuestionIdToAnswer();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    private java.util.Map<java.lang.Long, java.lang.String> getMutableQuestionIdToAnswerMap() {
        return internalGetMutableQuestionIdToAnswer();
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

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
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
            im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.CheckGroupJoinQuestionsAnswersRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
            im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        @java.lang.Override

        public int getQuestionIdToAnswerCount() {
            return instance.getQuestionIdToAnswerMap()
                    .size();
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        @java.lang.Override

        public boolean containsQuestionIdToAnswer(long key) {

            return instance.getQuestionIdToAnswerMap()
                    .containsKey(key);
        }

        public Builder clearQuestionIdToAnswer() {
            copyOnWrite();
            instance.getMutableQuestionIdToAnswerMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */

        public Builder removeQuestionIdToAnswer(long key) {

            copyOnWrite();
            instance.getMutableQuestionIdToAnswerMap()
                    .remove(key);
            return this;
        }

        /**
         * Use {@link #getQuestionIdToAnswerMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.Long, java.lang.String> getQuestionIdToAnswer() {
            return getQuestionIdToAnswerMap();
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.Long, java.lang.String> getQuestionIdToAnswerMap() {
            return java.util.Collections.unmodifiableMap(instance.getQuestionIdToAnswerMap());
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        @java.lang.Override

        public /* nullable */
        java.lang.String getQuestionIdToAnswerOrDefault(
                long key,
                /* nullable */
                java.lang.String defaultValue) {

            java.util.Map<java.lang.Long, java.lang.String> map =
                    instance.getQuestionIdToAnswerMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        @java.lang.Override

        public java.lang.String getQuestionIdToAnswerOrThrow(long key) {

            java.util.Map<java.lang.Long, java.lang.String> map =
                    instance.getQuestionIdToAnswerMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        public Builder putQuestionIdToAnswer(long key, java.lang.String value) {

            java.lang.Class<?> valueClass = value.getClass();
            copyOnWrite();
            instance.getMutableQuestionIdToAnswerMap()
                    .put(key, value);
            return this;
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        public Builder putAllQuestionIdToAnswer(
                java.util.Map<java.lang.Long, java.lang.String> values) {
            copyOnWrite();
            instance.getMutableQuestionIdToAnswerMap()
                    .putAll(values);
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"questionIdToAnswer_",
                        QuestionIdToAnswerDefaultEntryHolder.defaultEntry,
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u000f\u0002\u0001\u0001\u0000\u00012\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
    private static final im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest DEFAULT_INSTANCE;
    static {
        CheckGroupJoinQuestionsAnswersRequest defaultInstance =
                new CheckGroupJoinQuestionsAnswersRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                CheckGroupJoinQuestionsAnswersRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CheckGroupJoinQuestionsAnswersRequest> PARSER;

    public static com.google.protobuf.Parser<CheckGroupJoinQuestionsAnswersRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}