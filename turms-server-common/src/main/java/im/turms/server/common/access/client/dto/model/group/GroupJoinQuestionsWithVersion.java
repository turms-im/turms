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
 * Protobuf type {@code im.turms.proto.GroupJoinQuestionsWithVersion}
 */
public final class GroupJoinQuestionsWithVersion extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupJoinQuestionsWithVersion)
        GroupJoinQuestionsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupJoinQuestionsWithVersion.newBuilder() to construct.
    private GroupJoinQuestionsWithVersion(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupJoinQuestionsWithVersion() {
        groupJoinQuestions_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupJoinQuestionsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int GROUP_JOIN_QUESTIONS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> groupJoinQuestions_;

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> getGroupJoinQuestionsList() {
        return groupJoinQuestions_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> getGroupJoinQuestionsOrBuilderList() {
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
    public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion getGroupJoinQuestions(
            int index) {
        return groupJoinQuestions_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder getGroupJoinQuestionsOrBuilder(
            int index) {
        return groupJoinQuestions_.get(index);
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_ = 0L;

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
        for (GroupJoinQuestion groupJoinQuestion : groupJoinQuestions_) {
            output.writeMessage(1, groupJoinQuestion);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, lastUpdatedDate_);
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
        for (GroupJoinQuestion groupJoinQuestion : groupJoinQuestions_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, groupJoinQuestion);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, lastUpdatedDate_);
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
        if (!(obj instanceof GroupJoinQuestionsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getGroupJoinQuestionsList().equals(other.getGroupJoinQuestionsList())) {
            return false;
        }
        if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) {
            return false;
        }
        if (hasLastUpdatedDate()) {
            if (getLastUpdatedDate() != other.getLastUpdatedDate()) {
                return false;
            }
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
        if (getGroupJoinQuestionsCount() > 0) {
            hash = (37 * hash) + GROUP_JOIN_QUESTIONS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupJoinQuestionsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.GroupJoinQuestionsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupJoinQuestionsWithVersion)
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (groupJoinQuestionsBuilder_ == null) {
                groupJoinQuestions_ = java.util.Collections.emptyList();
            } else {
                groupJoinQuestions_ = null;
                groupJoinQuestionsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinQuestionsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion build() {
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion result) {
            if (groupJoinQuestionsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    groupJoinQuestions_ =
                            java.util.Collections.unmodifiableList(groupJoinQuestions_);
                    bitField0_ &= ~0x00000001;
                }
                result.groupJoinQuestions_ = groupJoinQuestions_;
            } else {
                result.groupJoinQuestions_ = groupJoinQuestionsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.lastUpdatedDate_ = lastUpdatedDate_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (groupJoinQuestionsBuilder_ == null) {
                if (!other.groupJoinQuestions_.isEmpty()) {
                    if (groupJoinQuestions_.isEmpty()) {
                        groupJoinQuestions_ = other.groupJoinQuestions_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureGroupJoinQuestionsIsMutable();
                        groupJoinQuestions_.addAll(other.groupJoinQuestions_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groupJoinQuestions_.isEmpty()) {
                    if (groupJoinQuestionsBuilder_.isEmpty()) {
                        groupJoinQuestionsBuilder_.dispose();
                        groupJoinQuestionsBuilder_ = null;
                        groupJoinQuestions_ = other.groupJoinQuestions_;
                        bitField0_ &= ~0x00000001;
                        groupJoinQuestionsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getGroupJoinQuestionsFieldBuilder()
                                        : null;
                    } else {
                        groupJoinQuestionsBuilder_.addAllMessages(other.groupJoinQuestions_);
                    }
                }
            }
            if (other.hasLastUpdatedDate()) {
                setLastUpdatedDate(other.getLastUpdatedDate());
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
                        case 10 -> {
                            GroupJoinQuestion m = input.readMessage(GroupJoinQuestion.parser(),
                                    extensionRegistry);
                            if (groupJoinQuestionsBuilder_ == null) {
                                ensureGroupJoinQuestionsIsMutable();
                                groupJoinQuestions_.add(m);
                            } else {
                                groupJoinQuestionsBuilder_.addMessage(m);
                            }
                        } // case 10
                        case 16 -> {
                            lastUpdatedDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
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

        private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> groupJoinQuestions_ =
                java.util.Collections.emptyList();

        private void ensureGroupJoinQuestionsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                groupJoinQuestions_ = new java.util.ArrayList<>(groupJoinQuestions_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> groupJoinQuestionsBuilder_;

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> getGroupJoinQuestionsList() {
            if (groupJoinQuestionsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groupJoinQuestions_);
            } else {
                return groupJoinQuestionsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public int getGroupJoinQuestionsCount() {
            if (groupJoinQuestionsBuilder_ == null) {
                return groupJoinQuestions_.size();
            } else {
                return groupJoinQuestionsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion getGroupJoinQuestions(
                int index) {
            if (groupJoinQuestionsBuilder_ == null) {
                return groupJoinQuestions_.get(index);
            } else {
                return groupJoinQuestionsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder setGroupJoinQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion value) {
            if (groupJoinQuestionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.set(index, value);
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder setGroupJoinQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder builderForValue) {
            if (groupJoinQuestionsBuilder_ == null) {
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion value) {
            if (groupJoinQuestionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.add(value);
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion value) {
            if (groupJoinQuestionsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.add(index, value);
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder builderForValue) {
            if (groupJoinQuestionsBuilder_ == null) {
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.add(builderForValue.build());
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addGroupJoinQuestions(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder builderForValue) {
            if (groupJoinQuestionsBuilder_ == null) {
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder addAllGroupJoinQuestions(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion> values) {
            if (groupJoinQuestionsBuilder_ == null) {
                ensureGroupJoinQuestionsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupJoinQuestions_);
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder clearGroupJoinQuestions() {
            if (groupJoinQuestionsBuilder_ == null) {
                groupJoinQuestions_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public Builder removeGroupJoinQuestions(int index) {
            if (groupJoinQuestionsBuilder_ == null) {
                ensureGroupJoinQuestionsIsMutable();
                groupJoinQuestions_.remove(index);
                onChanged();
            } else {
                groupJoinQuestionsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder getGroupJoinQuestionsBuilder(
                int index) {
            return getGroupJoinQuestionsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder getGroupJoinQuestionsOrBuilder(
                int index) {
            if (groupJoinQuestionsBuilder_ == null) {
                return groupJoinQuestions_.get(index);
            } else {
                return groupJoinQuestionsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> getGroupJoinQuestionsOrBuilderList() {
            if (groupJoinQuestionsBuilder_ != null) {
                return groupJoinQuestionsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groupJoinQuestions_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder addGroupJoinQuestionsBuilder() {
            return getGroupJoinQuestionsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder addGroupJoinQuestionsBuilder(
                int index) {
            return getGroupJoinQuestionsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinQuestion group_join_questions = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder> getGroupJoinQuestionsBuilderList() {
            return getGroupJoinQuestionsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionOrBuilder> getGroupJoinQuestionsFieldBuilder() {
            if (groupJoinQuestionsBuilder_ == null) {
                groupJoinQuestionsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        groupJoinQuestions_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                groupJoinQuestions_ = null;
            }
            return groupJoinQuestionsBuilder_;
        }

        private long lastUpdatedDate_;

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return ((bitField0_ & 0x00000002) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {

            lastUpdatedDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            bitField0_ &= ~0x00000002;
            lastUpdatedDate_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupJoinQuestionsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupJoinQuestionsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupJoinQuestionsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupJoinQuestionsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupJoinQuestionsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupJoinQuestionsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}