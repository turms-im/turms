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

package im.turms.server.common.access.client.dto.request.conversation;

/**
 * Protobuf type {@code im.turms.proto.QueryConversationsRequest}
 */
public final class QueryConversationsRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryConversationsRequest)
        QueryConversationsRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use QueryConversationsRequest.newBuilder() to construct.
    private QueryConversationsRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private QueryConversationsRequest() {
        targetIds_ = emptyLongList();
        groupIds_ = emptyLongList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new QueryConversationsRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass.internal_static_im_turms_proto_QueryConversationsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass.internal_static_im_turms_proto_QueryConversationsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.class,
                        im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.Builder.class);
    }

    public static final int TARGET_IDS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList targetIds_;

    /**
     * <pre>
     * Private conversations
     * </pre>
     * 
     * <code>repeated int64 target_ids = 1;</code>
     *
     * @return A list containing the targetIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getTargetIdsList() {
        return targetIds_;
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     * 
     * <code>repeated int64 target_ids = 1;</code>
     *
     * @return The count of targetIds.
     */
    public int getTargetIdsCount() {
        return targetIds_.size();
    }

    /**
     * <pre>
     * Private conversations
     * </pre>
     * 
     * <code>repeated int64 target_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The targetIds at the given index.
     */
    public long getTargetIds(int index) {
        return targetIds_.getLong(index);
    }

    private int targetIdsMemoizedSerializedSize = -1;

    public static final int GROUP_IDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList groupIds_;

    /**
     * <pre>
     * Group conversations
     * </pre>
     * 
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return A list containing the groupIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getGroupIdsList() {
        return groupIds_;
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     * 
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return The count of groupIds.
     */
    public int getGroupIdsCount() {
        return groupIds_.size();
    }

    /**
     * <pre>
     * Group conversations
     * </pre>
     * 
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    public long getGroupIds(int index) {
        return groupIds_.getLong(index);
    }

    private int groupIdsMemoizedSerializedSize = -1;

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
        if (getTargetIdsList().size() > 0) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(targetIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < targetIds_.size(); i++) {
            output.writeInt64NoTag(targetIds_.getLong(i));
        }
        if (getGroupIdsList().size() > 0) {
            output.writeUInt32NoTag(18);
            output.writeUInt32NoTag(groupIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < groupIds_.size(); i++) {
            output.writeInt64NoTag(groupIds_.getLong(i));
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
        {
            int dataSize = 0;
            for (int i = 0; i < targetIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(targetIds_.getLong(i));
            }
            size += dataSize;
            if (!getTargetIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            targetIdsMemoizedSerializedSize = dataSize;
        }
        {
            int dataSize = 0;
            for (int i = 0; i < groupIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(groupIds_.getLong(i));
            }
            size += dataSize;
            if (!getGroupIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            groupIdsMemoizedSerializedSize = dataSize;
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
        if (!(obj instanceof QueryConversationsRequest other)) {
            return super.equals(obj);
        }

        if (!getTargetIdsList().equals(other.getTargetIdsList())) {
            return false;
        }
        if (!getGroupIdsList().equals(other.getGroupIdsList())) {
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
        if (getTargetIdsCount() > 0) {
            hash = (37 * hash) + TARGET_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getTargetIdsList().hashCode();
        }
        if (getGroupIdsCount() > 0) {
            hash = (37 * hash) + GROUP_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupIdsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.QueryConversationsRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryConversationsRequest)
            im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass.internal_static_im_turms_proto_QueryConversationsRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass.internal_static_im_turms_proto_QueryConversationsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.class,
                            im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            targetIds_ = emptyLongList();
            groupIds_ = emptyLongList();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass.internal_static_im_turms_proto_QueryConversationsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest build() {
            im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest result =
                    new im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest result) {
            if (((bitField0_ & 0x00000001) != 0)) {
                targetIds_.makeImmutable();
                bitField0_ &= ~0x00000001;
            }
            result.targetIds_ = targetIds_;
            if (((bitField0_ & 0x00000002) != 0)) {
                groupIds_.makeImmutable();
                bitField0_ &= ~0x00000002;
            }
            result.groupIds_ = groupIds_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.targetIds_.isEmpty()) {
                if (targetIds_.isEmpty()) {
                    targetIds_ = other.targetIds_;
                    bitField0_ &= ~0x00000001;
                } else {
                    ensureTargetIdsIsMutable();
                    targetIds_.addAll(other.targetIds_);
                }
                onChanged();
            }
            if (!other.groupIds_.isEmpty()) {
                if (groupIds_.isEmpty()) {
                    groupIds_ = other.groupIds_;
                    bitField0_ &= ~0x00000002;
                } else {
                    ensureGroupIdsIsMutable();
                    groupIds_.addAll(other.groupIds_);
                }
                onChanged();
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
                            long v = input.readInt64();
                            ensureTargetIdsIsMutable();
                            targetIds_.addLong(v);
                        } // case 8
                        case 10 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureTargetIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                targetIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 10
                        case 16 -> {
                            long v = input.readInt64();
                            ensureGroupIdsIsMutable();
                            groupIds_.addLong(v);
                        } // case 16
                        case 18 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureGroupIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                groupIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
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

        private com.google.protobuf.Internal.LongList targetIds_ = emptyLongList();

        private void ensureTargetIdsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                targetIds_ = mutableCopy(targetIds_);
                bitField0_ |= 0x00000001;
            }
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @return A list containing the targetIds.
         */
        public java.util.List<java.lang.Long> getTargetIdsList() {
            return ((bitField0_ & 0x00000001) != 0)
                    ? java.util.Collections.unmodifiableList(targetIds_)
                    : targetIds_;
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @return The count of targetIds.
         */
        public int getTargetIdsCount() {
            return targetIds_.size();
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The targetIds at the given index.
         */
        public long getTargetIds(int index) {
            return targetIds_.getLong(index);
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The targetIds to set.
         * @return This builder for chaining.
         */
        public Builder setTargetIds(int index, long value) {

            ensureTargetIdsIsMutable();
            targetIds_.setLong(index, value);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @param value The targetIds to add.
         * @return This builder for chaining.
         */
        public Builder addTargetIds(long value) {

            ensureTargetIdsIsMutable();
            targetIds_.addLong(value);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @param values The targetIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllTargetIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureTargetIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, targetIds_);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Private conversations
         * </pre>
         * 
         * <code>repeated int64 target_ids = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTargetIds() {
            targetIds_ = emptyLongList();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList groupIds_ = emptyLongList();

        private void ensureGroupIdsIsMutable() {
            if ((bitField0_ & 0x00000002) == 0) {
                groupIds_ = mutableCopy(groupIds_);
                bitField0_ |= 0x00000002;
            }
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return A list containing the groupIds.
         */
        public java.util.List<java.lang.Long> getGroupIdsList() {
            return ((bitField0_ & 0x00000002) != 0)
                    ? java.util.Collections.unmodifiableList(groupIds_)
                    : groupIds_;
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return The count of groupIds.
         */
        public int getGroupIdsCount() {
            return groupIds_.size();
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The groupIds at the given index.
         */
        public long getGroupIds(int index) {
            return groupIds_.getLong(index);
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The groupIds to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIds(int index, long value) {

            ensureGroupIdsIsMutable();
            groupIds_.setLong(index, value);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param value The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addGroupIds(long value) {

            ensureGroupIdsIsMutable();
            groupIds_.addLong(value);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @param values The groupIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllGroupIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureGroupIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupIds_);
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Group conversations
         * </pre>
         * 
         * <code>repeated int64 group_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIds() {
            groupIds_ = emptyLongList();
            bitField0_ &= ~0x00000002;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryConversationsRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryConversationsRequest)
    private static final im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest();
    }

    public static im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryConversationsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryConversationsRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryConversationsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryConversationsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}