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

package im.turms.server.common.access.client.dto.request.group.member;

/**
 * Protobuf type {@code im.turms.proto.DeleteGroupMembersRequest}
 */
public final class DeleteGroupMembersRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteGroupMembersRequest)
        DeleteGroupMembersRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use DeleteGroupMembersRequest.newBuilder() to construct.
    private DeleteGroupMembersRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private DeleteGroupMembersRequest() {
        memberIds_ = emptyLongList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new DeleteGroupMembersRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass.internal_static_im_turms_proto_DeleteGroupMembersRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass.internal_static_im_turms_proto_DeleteGroupMembersRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.class,
                        im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.Builder.class);
    }

    private int bitField0_;
    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_ = 0L;

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    public static final int MEMBER_IDS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.Internal.LongList memberIds_;

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @return A list containing the memberIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getMemberIdsList() {
        return memberIds_;
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @return The count of memberIds.
     */
    public int getMemberIdsCount() {
        return memberIds_.size();
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The memberIds at the given index.
     */
    public long getMemberIds(int index) {
        return memberIds_.getLong(index);
    }

    private int memberIdsMemoizedSerializedSize = -1;

    public static final int SUCCESSOR_ID_FIELD_NUMBER = 3;
    private long successorId_ = 0L;

    /**
     * <code>optional int64 successor_id = 3;</code>
     *
     * @return Whether the successorId field is set.
     */
    @java.lang.Override
    public boolean hasSuccessorId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 successor_id = 3;</code>
     *
     * @return The successorId.
     */
    @java.lang.Override
    public long getSuccessorId() {
        return successorId_;
    }

    public static final int QUIT_AFTER_TRANSFER_FIELD_NUMBER = 4;
    private boolean quitAfterTransfer_ = false;

    /**
     * <code>optional bool quit_after_transfer = 4;</code>
     *
     * @return Whether the quitAfterTransfer field is set.
     */
    @java.lang.Override
    public boolean hasQuitAfterTransfer() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional bool quit_after_transfer = 4;</code>
     *
     * @return The quitAfterTransfer.
     */
    @java.lang.Override
    public boolean getQuitAfterTransfer() {
        return quitAfterTransfer_;
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
        if (groupId_ != 0L) {
            output.writeInt64(1, groupId_);
        }
        if (getMemberIdsList().size() > 0) {
            output.writeUInt32NoTag(18);
            output.writeUInt32NoTag(memberIdsMemoizedSerializedSize);
        }
        for (int i = 0; i < memberIds_.size(); i++) {
            output.writeInt64NoTag(memberIds_.getLong(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(3, successorId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeBool(4, quitAfterTransfer_);
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
        if (groupId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, groupId_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < memberIds_.size(); i++) {
                dataSize += com.google.protobuf.CodedOutputStream
                        .computeInt64SizeNoTag(memberIds_.getLong(i));
            }
            size += dataSize;
            if (!getMemberIdsList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(dataSize);
            }
            memberIdsMemoizedSerializedSize = dataSize;
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, successorId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(4, quitAfterTransfer_);
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
        if (!(obj instanceof DeleteGroupMembersRequest other)) {
            return super.equals(obj);
        }

        if (getGroupId() != other.getGroupId()) {
            return false;
        }
        if (!getMemberIdsList().equals(other.getMemberIdsList())) {
            return false;
        }
        if (hasSuccessorId() != other.hasSuccessorId()) {
            return false;
        }
        if (hasSuccessorId()) {
            if (getSuccessorId() != other.getSuccessorId()) {
                return false;
            }
        }
        if (hasQuitAfterTransfer() != other.hasQuitAfterTransfer()) {
            return false;
        }
        if (hasQuitAfterTransfer()) {
            if (getQuitAfterTransfer() != other.getQuitAfterTransfer()) {
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
        hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        if (getMemberIdsCount() > 0) {
            hash = (37 * hash) + MEMBER_IDS_FIELD_NUMBER;
            hash = (53 * hash) + getMemberIdsList().hashCode();
        }
        if (hasSuccessorId()) {
            hash = (37 * hash) + SUCCESSOR_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getSuccessorId());
        }
        if (hasQuitAfterTransfer()) {
            hash = (37 * hash) + QUIT_AFTER_TRANSFER_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getQuitAfterTransfer());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.DeleteGroupMembersRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteGroupMembersRequest)
            im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass.internal_static_im_turms_proto_DeleteGroupMembersRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass.internal_static_im_turms_proto_DeleteGroupMembersRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.class,
                            im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            groupId_ = 0L;
            memberIds_ = emptyLongList();
            successorId_ = 0L;
            quitAfterTransfer_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass.internal_static_im_turms_proto_DeleteGroupMembersRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest build() {
            im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest result =
                    new im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest result) {
            if (((bitField0_ & 0x00000002) != 0)) {
                memberIds_.makeImmutable();
                bitField0_ &= ~0x00000002;
            }
            result.memberIds_ = memberIds_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.groupId_ = groupId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.successorId_ = successorId_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.quitAfterTransfer_ = quitAfterTransfer_;
                to_bitField0_ |= 0x00000002;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getGroupId() != 0L) {
                setGroupId(other.getGroupId());
            }
            if (!other.memberIds_.isEmpty()) {
                if (memberIds_.isEmpty()) {
                    memberIds_ = other.memberIds_;
                    bitField0_ &= ~0x00000002;
                } else {
                    ensureMemberIdsIsMutable();
                    memberIds_.addAll(other.memberIds_);
                }
                onChanged();
            }
            if (other.hasSuccessorId()) {
                setSuccessorId(other.getSuccessorId());
            }
            if (other.hasQuitAfterTransfer()) {
                setQuitAfterTransfer(other.getQuitAfterTransfer());
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
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            long v = input.readInt64();
                            ensureMemberIdsIsMutable();
                            memberIds_.addLong(v);
                        } // case 16
                        case 18 -> {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            ensureMemberIdsIsMutable();
                            while (input.getBytesUntilLimit() > 0) {
                                memberIds_.addLong(input.readInt64());
                            }
                            input.popLimit(limit);
                        } // case 18
                        case 24 -> {
                            successorId_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            quitAfterTransfer_ = input.readBool();
                            bitField0_ |= 0x00000008;
                        } // case 32
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
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000001;
            groupId_ = 0L;
            onChanged();
            return this;
        }

        private com.google.protobuf.Internal.LongList memberIds_ = emptyLongList();

        private void ensureMemberIdsIsMutable() {
            if ((bitField0_ & 0x00000002) == 0) {
                memberIds_ = mutableCopy(memberIds_);
                bitField0_ |= 0x00000002;
            }
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @return A list containing the memberIds.
         */
        public java.util.List<java.lang.Long> getMemberIdsList() {
            return ((bitField0_ & 0x00000002) != 0)
                    ? java.util.Collections.unmodifiableList(memberIds_)
                    : memberIds_;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @return The count of memberIds.
         */
        public int getMemberIdsCount() {
            return memberIds_.size();
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The memberIds at the given index.
         */
        public long getMemberIds(int index) {
            return memberIds_.getLong(index);
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The memberIds to set.
         * @return This builder for chaining.
         */
        public Builder setMemberIds(int index, long value) {

            ensureMemberIdsIsMutable();
            memberIds_.setLong(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param value The memberIds to add.
         * @return This builder for chaining.
         */
        public Builder addMemberIds(long value) {

            ensureMemberIdsIsMutable();
            memberIds_.addLong(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param values The memberIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllMemberIds(java.lang.Iterable<? extends java.lang.Long> values) {
            ensureMemberIdsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, memberIds_);
            onChanged();
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMemberIds() {
            memberIds_ = emptyLongList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        private long successorId_;

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @return Whether the successorId field is set.
         */
        @java.lang.Override
        public boolean hasSuccessorId() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @return The successorId.
         */
        @java.lang.Override
        public long getSuccessorId() {
            return successorId_;
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @param value The successorId to set.
         * @return This builder for chaining.
         */
        public Builder setSuccessorId(long value) {

            successorId_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSuccessorId() {
            bitField0_ &= ~0x00000004;
            successorId_ = 0L;
            onChanged();
            return this;
        }

        private boolean quitAfterTransfer_;

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @return Whether the quitAfterTransfer field is set.
         */
        @java.lang.Override
        public boolean hasQuitAfterTransfer() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @return The quitAfterTransfer.
         */
        @java.lang.Override
        public boolean getQuitAfterTransfer() {
            return quitAfterTransfer_;
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @param value The quitAfterTransfer to set.
         * @return This builder for chaining.
         */
        public Builder setQuitAfterTransfer(boolean value) {

            quitAfterTransfer_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuitAfterTransfer() {
            bitField0_ &= ~0x00000008;
            quitAfterTransfer_ = false;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteGroupMembersRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteGroupMembersRequest)
    private static final im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DeleteGroupMembersRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public DeleteGroupMembersRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<DeleteGroupMembersRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DeleteGroupMembersRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}