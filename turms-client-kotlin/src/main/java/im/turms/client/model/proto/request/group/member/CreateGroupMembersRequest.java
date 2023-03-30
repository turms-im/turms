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
 * Protobuf type {@code im.turms.proto.CreateGroupMembersRequest}
 */
public final class CreateGroupMembersRequest extends
        com.google.protobuf.GeneratedMessageLite<CreateGroupMembersRequest, CreateGroupMembersRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateGroupMembersRequest)
        CreateGroupMembersRequestOrBuilder {
    private CreateGroupMembersRequest() {
        userIds_ = emptyLongList();
        name_ = "";
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

    public static final int USER_IDS_FIELD_NUMBER = 2;
    private com.google.protobuf.Internal.LongList userIds_;

    /**
     * <code>repeated int64 user_ids = 2;</code>
     *
     * @return A list containing the userIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getUserIdsList() {
        return userIds_;
    }

    /**
     * <code>repeated int64 user_ids = 2;</code>
     *
     * @return The count of userIds.
     */
    @java.lang.Override
    public int getUserIdsCount() {
        return userIds_.size();
    }

    /**
     * <code>repeated int64 user_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    @java.lang.Override
    public long getUserIds(int index) {
        return userIds_.getLong(index);
    }

    private int userIdsMemoizedSerializedSize = -1;

    private void ensureUserIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = userIds_;
        if (!tmp.isModifiable()) {
            userIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 user_ids = 2;</code>
     *
     * @param index The index to set the value at.
     * @param value The userIds to set.
     */
    private void setUserIds(int index, long value) {
        ensureUserIdsIsMutable();
        userIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 user_ids = 2;</code>
     *
     * @param value The userIds to add.
     */
    private void addUserIds(long value) {
        ensureUserIdsIsMutable();
        userIds_.addLong(value);
    }

    /**
     * <code>repeated int64 user_ids = 2;</code>
     *
     * @param values The userIds to add.
     */
    private void addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureUserIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, userIds_);
    }

    /**
     * <code>repeated int64 user_ids = 2;</code>
     */
    private void clearUserIds() {
        userIds_ = emptyLongList();
    }

    public static final int NAME_FIELD_NUMBER = 3;
    private java.lang.String name_;

    /**
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        name_ = value;
    }

    /**
     * <code>optional string name = 3;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000001;
        name_ = getDefaultInstance().getName();
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int ROLE_FIELD_NUMBER = 4;
    private int role_;

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @return Whether the role field is set.
     */
    @java.lang.Override
    public boolean hasRole() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @return The enum numeric value on the wire for role.
     */
    @java.lang.Override
    public int getRoleValue() {
        return role_;
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @return The role.
     */
    @java.lang.Override
    public im.turms.client.model.proto.constant.GroupMemberRole getRole() {
        im.turms.client.model.proto.constant.GroupMemberRole result =
                im.turms.client.model.proto.constant.GroupMemberRole.forNumber(role_);
        return result == null
                ? im.turms.client.model.proto.constant.GroupMemberRole.UNRECOGNIZED
                : result;
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @param value The enum numeric value on the wire for role to set.
     */
    private void setRoleValue(int value) {
        bitField0_ |= 0x00000002;
        role_ = value;
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @param value The role to set.
     */
    private void setRole(im.turms.client.model.proto.constant.GroupMemberRole value) {
        role_ = value.getNumber();
        bitField0_ |= 0x00000002;
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     */
    private void clearRole() {
        bitField0_ &= ~0x00000002;
        role_ = 0;
    }

    public static final int MUTE_END_DATE_FIELD_NUMBER = 5;
    private long muteEndDate_;

    /**
     * <code>optional int64 mute_end_date = 5;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    @java.lang.Override
    public boolean hasMuteEndDate() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int64 mute_end_date = 5;</code>
     *
     * @return The muteEndDate.
     */
    @java.lang.Override
    public long getMuteEndDate() {
        return muteEndDate_;
    }

    /**
     * <code>optional int64 mute_end_date = 5;</code>
     *
     * @param value The muteEndDate to set.
     */
    private void setMuteEndDate(long value) {
        bitField0_ |= 0x00000004;
        muteEndDate_ = value;
    }

    /**
     * <code>optional int64 mute_end_date = 5;</code>
     */
    private void clearMuteEndDate() {
        bitField0_ &= ~0x00000004;
        muteEndDate_ = 0L;
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest parseFrom(
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
            im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.CreateGroupMembersRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateGroupMembersRequest)
            im.turms.client.model.proto.request.group.member.CreateGroupMembersRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest.newBuilder()
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
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @return A list containing the userIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getUserIdsList() {
            return java.util.Collections.unmodifiableList(instance.getUserIdsList());
        }

        /**
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @return The count of userIds.
         */
        @java.lang.Override
        public int getUserIdsCount() {
            return instance.getUserIdsCount();
        }

        /**
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The userIds at the given index.
         */
        @java.lang.Override
        public long getUserIds(int index) {
            return instance.getUserIds(index);
        }

        /**
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @param value The userIds to set.
         * @return This builder for chaining.
         */
        public Builder setUserIds(int index, long value) {
            copyOnWrite();
            instance.setUserIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @param value The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addUserIds(long value) {
            copyOnWrite();
            instance.addUserIds(value);
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @param values The userIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllUserIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllUserIds(values);
            return this;
        }

        /**
         * <code>repeated int64 user_ids = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserIds() {
            copyOnWrite();
            instance.clearUserIds();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return Whether the name field is set.
         */
        @java.lang.Override
        public boolean hasName() {
            return instance.hasName();
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            copyOnWrite();
            instance.setName(value);
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setNameBytes(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @return Whether the role field is set.
         */
        @java.lang.Override
        public boolean hasRole() {
            return instance.hasRole();
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @return The enum numeric value on the wire for role.
         */
        @java.lang.Override
        public int getRoleValue() {
            return instance.getRoleValue();
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @param value The role to set.
         * @return This builder for chaining.
         */
        public Builder setRoleValue(int value) {
            copyOnWrite();
            instance.setRoleValue(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @return The role.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.GroupMemberRole getRole() {
            return instance.getRole();
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @param value The enum numeric value on the wire for role to set.
         * @return This builder for chaining.
         */
        public Builder setRole(im.turms.client.model.proto.constant.GroupMemberRole value) {
            copyOnWrite();
            instance.setRole(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRole() {
            copyOnWrite();
            instance.clearRole();
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 5;</code>
         *
         * @return Whether the muteEndDate field is set.
         */
        @java.lang.Override
        public boolean hasMuteEndDate() {
            return instance.hasMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 5;</code>
         *
         * @return The muteEndDate.
         */
        @java.lang.Override
        public long getMuteEndDate() {
            return instance.getMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 5;</code>
         *
         * @param value The muteEndDate to set.
         * @return This builder for chaining.
         */
        public Builder setMuteEndDate(long value) {
            copyOnWrite();
            instance.setMuteEndDate(value);
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuteEndDate() {
            copyOnWrite();
            instance.clearMuteEndDate();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateGroupMembersRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupId_",
                        "userIds_",
                        "name_",
                        "role_",
                        "muteEndDate_",};
                java.lang.String info =
                        "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u0002\u0002%\u0003"
                                + "\u1208\u0000\u0004\u100c\u0001\u0005\u1002\u0002";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateGroupMembersRequest)
    private static final im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest DEFAULT_INSTANCE;

    static {
        CreateGroupMembersRequest defaultInstance = new CreateGroupMembersRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(CreateGroupMembersRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.member.CreateGroupMembersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CreateGroupMembersRequest> PARSER;

    public static com.google.protobuf.Parser<CreateGroupMembersRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}