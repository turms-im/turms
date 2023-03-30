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
 * Protobuf type {@code im.turms.proto.CheckGroupJoinQuestionsAnswersRequest}
 */
public final class CheckGroupJoinQuestionsAnswersRequest
        extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
        CheckGroupJoinQuestionsAnswersRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use CheckGroupJoinQuestionsAnswersRequest.newBuilder() to construct.
    private CheckGroupJoinQuestionsAnswersRequest(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CheckGroupJoinQuestionsAnswersRequest() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new CheckGroupJoinQuestionsAnswersRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass.internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(int number) {
        return switch (number) {
            case 1 -> internalGetQuestionIdToAnswer();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass.internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.class,
                        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder.class);
    }

    public static final int QUESTION_ID_TO_ANSWER_FIELD_NUMBER = 1;

    private static final class QuestionIdToAnswerDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.Long, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntry.<java.lang.Long, java.lang.String>newDefaultInstance(
                        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass.internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.INT64,
                        0L,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.Long, java.lang.String> questionIdToAnswer_;

    private com.google.protobuf.MapField<java.lang.Long, java.lang.String> internalGetQuestionIdToAnswer() {
        if (questionIdToAnswer_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(QuestionIdToAnswerDefaultEntryHolder.defaultEntry);
        }
        return questionIdToAnswer_;
    }

    public int getQuestionIdToAnswerCount() {
        return internalGetQuestionIdToAnswer().getMap()
                .size();
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    @java.lang.Override
    public boolean containsQuestionIdToAnswer(long key) {

        return internalGetQuestionIdToAnswer().getMap()
                .containsKey(key);
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
        return internalGetQuestionIdToAnswer().getMap();
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
                internalGetQuestionIdToAnswer().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
     */
    @java.lang.Override
    public java.lang.String getQuestionIdToAnswerOrThrow(long key) {

        java.util.Map<java.lang.Long, java.lang.String> map =
                internalGetQuestionIdToAnswer().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
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
        com.google.protobuf.GeneratedMessageV3.serializeLongMapTo(output,
                internalGetQuestionIdToAnswer(),
                QuestionIdToAnswerDefaultEntryHolder.defaultEntry,
                1);
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        for (java.util.Map.Entry<java.lang.Long, java.lang.String> entry : internalGetQuestionIdToAnswer()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.Long, java.lang.String> questionIdToAnswer__ =
                    QuestionIdToAnswerDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1,
                    questionIdToAnswer__);
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
        if (!(obj instanceof CheckGroupJoinQuestionsAnswersRequest other)) {
            return super.equals(obj);
        }

        if (!internalGetQuestionIdToAnswer().equals(other.internalGetQuestionIdToAnswer())) {
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
        if (!internalGetQuestionIdToAnswer().getMap()
                .isEmpty()) {
            hash = (37 * hash) + QUESTION_ID_TO_ANSWER_FIELD_NUMBER;
            hash = (53 * hash) + internalGetQuestionIdToAnswer().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.CheckGroupJoinQuestionsAnswersRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
            im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass.internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMapField(int number) {
            return switch (number) {
                case 1 -> internalGetQuestionIdToAnswer();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMutableMapField(int number) {
            return switch (number) {
                case 1 -> internalGetMutableQuestionIdToAnswer();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass.internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.class,
                            im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            internalGetMutableQuestionIdToAnswer().clear();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass.internal_static_im_turms_proto_CheckGroupJoinQuestionsAnswersRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest build() {
            im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest result =
                    new im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.questionIdToAnswer_ = internalGetQuestionIdToAnswer();
                result.questionIdToAnswer_.makeImmutable();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest
                    .getDefaultInstance()) {
                return this;
            }
            internalGetMutableQuestionIdToAnswer().mergeFrom(other.internalGetQuestionIdToAnswer());
            bitField0_ |= 0x00000001;
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
                        case 10 -> {
                            com.google.protobuf.MapEntry<Long, String> questionIdToAnswer__ =
                                    input.readMessage(
                                            QuestionIdToAnswerDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableQuestionIdToAnswer().getMutableMap()
                                    .put(questionIdToAnswer__.getKey(),
                                            questionIdToAnswer__.getValue());
                            bitField0_ |= 0x00000001;
                        } // case 10
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

        private com.google.protobuf.MapField<java.lang.Long, java.lang.String> questionIdToAnswer_;

        private com.google.protobuf.MapField<java.lang.Long, java.lang.String> internalGetQuestionIdToAnswer() {
            if (questionIdToAnswer_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(QuestionIdToAnswerDefaultEntryHolder.defaultEntry);
            }
            return questionIdToAnswer_;
        }

        private com.google.protobuf.MapField<java.lang.Long, java.lang.String> internalGetMutableQuestionIdToAnswer() {
            if (questionIdToAnswer_ == null) {
                questionIdToAnswer_ = com.google.protobuf.MapField
                        .newMapField(QuestionIdToAnswerDefaultEntryHolder.defaultEntry);
            }
            if (!questionIdToAnswer_.isMutable()) {
                questionIdToAnswer_ = questionIdToAnswer_.copy();
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return questionIdToAnswer_;
        }

        public int getQuestionIdToAnswerCount() {
            return internalGetQuestionIdToAnswer().getMap()
                    .size();
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        @java.lang.Override
        public boolean containsQuestionIdToAnswer(long key) {

            return internalGetQuestionIdToAnswer().getMap()
                    .containsKey(key);
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
            return internalGetQuestionIdToAnswer().getMap();
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
                    internalGetQuestionIdToAnswer().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        @java.lang.Override
        public java.lang.String getQuestionIdToAnswerOrThrow(long key) {

            java.util.Map<java.lang.Long, java.lang.String> map =
                    internalGetQuestionIdToAnswer().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearQuestionIdToAnswer() {
            bitField0_ &= ~0x00000001;
            internalGetMutableQuestionIdToAnswer().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        public Builder removeQuestionIdToAnswer(long key) {

            internalGetMutableQuestionIdToAnswer().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.Long, java.lang.String> getMutableQuestionIdToAnswer() {
            bitField0_ |= 0x00000001;
            return internalGetMutableQuestionIdToAnswer().getMutableMap();
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        public Builder putQuestionIdToAnswer(long key, java.lang.String value) {

            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableQuestionIdToAnswer().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x00000001;
            return this;
        }

        /**
         * <code>map&lt;int64, string&gt; question_id_to_answer = 1;</code>
         */
        public Builder putAllQuestionIdToAnswer(
                java.util.Map<java.lang.Long, java.lang.String> values) {
            internalGetMutableQuestionIdToAnswer().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000001;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.CheckGroupJoinQuestionsAnswersRequest)
    private static final im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CheckGroupJoinQuestionsAnswersRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public CheckGroupJoinQuestionsAnswersRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<CheckGroupJoinQuestionsAnswersRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CheckGroupJoinQuestionsAnswersRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}