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

package im.turms.client.model.proto.request.group.member;

/**
 * Protobuf type {@code im.turms.proto.DeleteGroupMembersRequest}
 */
public final class DeleteGroupMembersRequest extends
        com.google.protobuf.GeneratedMessageLite<DeleteGroupMembersRequest, DeleteGroupMembersRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteGroupMembersRequest)
        DeleteGroupMembersRequestOrBuilder {
    private DeleteGroupMembersRequest() {
        memberIds_ = emptyLongList();
    }

    private int bitField0_;
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

    public static final int MEMBER_IDS_FIELD_NUMBER = 2;
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
    @java.lang.Override
    public int getMemberIdsCount() {
        return memberIds_.size();
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The memberIds at the given index.
     */
    @java.lang.Override
    public long getMemberIds(int index) {
        return memberIds_.getLong(index);
    }

    private int memberIdsMemoizedSerializedSize = -1;

    private void ensureMemberIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = memberIds_;
        if (!tmp.isModifiable()) {
            memberIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The memberIds to set.
     */
    private void setMemberIds(int index, long value) {
        ensureMemberIdsIsMutable();
        memberIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @param value The memberIds to add.
     */
    private void addMemberIds(long value) {
        ensureMemberIdsIsMutable();
        memberIds_.addLong(value);
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @param values The memberIds to add.
     */
    private void addAllMemberIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureMemberIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, memberIds_);
    }

    /**
     * <code>repeated int64 member_ids = 2;</code>
     */
    private void clearMemberIds() {
        memberIds_ = emptyLongList();
    }

    public static final int SUCCESSOR_ID_FIELD_NUMBER = 3;
    private long successorId_;

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

    /**
     * <code>optional int64 successor_id = 3;</code>
     *
     * @param value The successorId to set.
     */
    private void setSuccessorId(long value) {
        bitField0_ |= 0x00000001;
        successorId_ = value;
    }

    /**
     * <code>optional int64 successor_id = 3;</code>
     */
    private void clearSuccessorId() {
        bitField0_ &= ~0x00000001;
        successorId_ = 0L;
    }

    public static final int QUIT_AFTER_TRANSFER_FIELD_NUMBER = 4;
    private boolean quitAfterTransfer_;

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

    /**
     * <code>optional bool quit_after_transfer = 4;</code>
     *
     * @param value The quitAfterTransfer to set.
     */
    private void setQuitAfterTransfer(boolean value) {
        bitField0_ |= 0x00000002;
        quitAfterTransfer_ = value;
    }

    /**
     * <code>optional bool quit_after_transfer = 4;</code>
     */
    private void clearQuitAfterTransfer() {
        bitField0_ &= ~0x00000002;
        quitAfterTransfer_ = false;
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest parseFrom(
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
            im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.DeleteGroupMembersRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteGroupMembersRequest)
            im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest.newBuilder()
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
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @return A list containing the memberIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getMemberIdsList() {
            return java.util.Collections.unmodifiableList(instance.getMemberIdsList());
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @return The count of memberIds.
         */
        @java.lang.Override
        public int getMemberIdsCount() {
            return instance.getMemberIdsCount();
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The memberIds at the given index.
         */
        @java.lang.Override
        public long getMemberIds(int index) {
            return instance.getMemberIds(index);
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param value The memberIds to set.
         * @return This builder for chaining.
         */
        public Builder setMemberIds(int index, long value) {
            copyOnWrite();
            instance.setMemberIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param value The memberIds to add.
         * @return This builder for chaining.
         */
        public Builder addMemberIds(long value) {
            copyOnWrite();
            instance.addMemberIds(value);
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @param values The memberIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllMemberIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllMemberIds(values);
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMemberIds() {
            copyOnWrite();
            instance.clearMemberIds();
            return this;
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @return Whether the successorId field is set.
         */
        @java.lang.Override
        public boolean hasSuccessorId() {
            return instance.hasSuccessorId();
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @return The successorId.
         */
        @java.lang.Override
        public long getSuccessorId() {
            return instance.getSuccessorId();
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @param value The successorId to set.
         * @return This builder for chaining.
         */
        public Builder setSuccessorId(long value) {
            copyOnWrite();
            instance.setSuccessorId(value);
            return this;
        }

        /**
         * <code>optional int64 successor_id = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSuccessorId() {
            copyOnWrite();
            instance.clearSuccessorId();
            return this;
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @return Whether the quitAfterTransfer field is set.
         */
        @java.lang.Override
        public boolean hasQuitAfterTransfer() {
            return instance.hasQuitAfterTransfer();
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @return The quitAfterTransfer.
         */
        @java.lang.Override
        public boolean getQuitAfterTransfer() {
            return instance.getQuitAfterTransfer();
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @param value The quitAfterTransfer to set.
         * @return This builder for chaining.
         */
        public Builder setQuitAfterTransfer(boolean value) {
            copyOnWrite();
            instance.setQuitAfterTransfer(value);
            return this;
        }

        /**
         * <code>optional bool quit_after_transfer = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuitAfterTransfer() {
            copyOnWrite();
            instance.clearQuitAfterTransfer();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteGroupMembersRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupId_",
                        "memberIds_",
                        "successorId_",
                        "quitAfterTransfer_",};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0002\u0002%\u0003"
                                + "\u1002\u0000\u0004\u1007\u0001";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteGroupMembersRequest)
    private static final im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest DEFAULT_INSTANCE;

    static {
        DeleteGroupMembersRequest defaultInstance = new DeleteGroupMembersRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(DeleteGroupMembersRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.member.DeleteGroupMembersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<DeleteGroupMembersRequest> PARSER;

    public static com.google.protobuf.Parser<DeleteGroupMembersRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}