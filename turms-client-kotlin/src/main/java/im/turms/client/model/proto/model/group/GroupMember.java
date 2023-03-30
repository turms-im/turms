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
 * Protobuf type {@code im.turms.proto.GroupMember}
 */
public final class GroupMember extends
        com.google.protobuf.GeneratedMessageLite<GroupMember, GroupMember.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupMember)
        GroupMemberOrBuilder {
    private GroupMember() {
        name_ = "";
        usingDeviceTypes_ = emptyIntList();
    }

    private int bitField0_;
    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_;

    /**
     * <code>optional int64 group_id = 1;</code>
     *
     * @return Whether the groupId field is set.
     */
    @java.lang.Override
    public boolean hasGroupId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    /**
     * <code>optional int64 group_id = 1;</code>
     *
     * @param value The groupId to set.
     */
    private void setGroupId(long value) {
        bitField0_ |= 0x00000001;
        groupId_ = value;
    }

    /**
     * <code>optional int64 group_id = 1;</code>
     */
    private void clearGroupId() {
        bitField0_ &= ~0x00000001;
        groupId_ = 0L;
    }

    public static final int USER_ID_FIELD_NUMBER = 2;
    private long userId_;

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @return Whether the userId field is set.
     */
    @java.lang.Override
    public boolean hasUserId() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @param value The userId to set.
     */
    private void setUserId(long value) {
        bitField0_ |= 0x00000002;
        userId_ = value;
    }

    /**
     * <code>optional int64 user_id = 2;</code>
     */
    private void clearUserId() {
        bitField0_ &= ~0x00000002;
        userId_ = 0L;
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
        return ((bitField0_ & 0x00000004) != 0);
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
        bitField0_ |= 0x00000004;
        name_ = value;
    }

    /**
     * <code>optional string name = 3;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000004;
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
        bitField0_ |= 0x00000004;
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
        return ((bitField0_ & 0x00000008) != 0);
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
        bitField0_ |= 0x00000008;
        role_ = value;
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     *
     * @param value The role to set.
     */
    private void setRole(im.turms.client.model.proto.constant.GroupMemberRole value) {
        role_ = value.getNumber();
        bitField0_ |= 0x00000008;
    }

    /**
     * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
     */
    private void clearRole() {
        bitField0_ &= ~0x00000008;
        role_ = 0;
    }

    public static final int JOIN_DATE_FIELD_NUMBER = 5;
    private long joinDate_;

    /**
     * <code>optional int64 join_date = 5;</code>
     *
     * @return Whether the joinDate field is set.
     */
    @java.lang.Override
    public boolean hasJoinDate() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 join_date = 5;</code>
     *
     * @return The joinDate.
     */
    @java.lang.Override
    public long getJoinDate() {
        return joinDate_;
    }

    /**
     * <code>optional int64 join_date = 5;</code>
     *
     * @param value The joinDate to set.
     */
    private void setJoinDate(long value) {
        bitField0_ |= 0x00000010;
        joinDate_ = value;
    }

    /**
     * <code>optional int64 join_date = 5;</code>
     */
    private void clearJoinDate() {
        bitField0_ &= ~0x00000010;
        joinDate_ = 0L;
    }

    public static final int MUTE_END_DATE_FIELD_NUMBER = 6;
    private long muteEndDate_;

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    @java.lang.Override
    public boolean hasMuteEndDate() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return The muteEndDate.
     */
    @java.lang.Override
    public long getMuteEndDate() {
        return muteEndDate_;
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @param value The muteEndDate to set.
     */
    private void setMuteEndDate(long value) {
        bitField0_ |= 0x00000020;
        muteEndDate_ = value;
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     */
    private void clearMuteEndDate() {
        bitField0_ &= ~0x00000020;
        muteEndDate_ = 0L;
    }

    public static final int USER_STATUS_FIELD_NUMBER = 7;
    private int userStatus_;

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @return Whether the userStatus field is set.
     */
    @java.lang.Override
    public boolean hasUserStatus() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    @java.lang.Override
    public int getUserStatusValue() {
        return userStatus_;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @return The userStatus.
     */
    @java.lang.Override
    public im.turms.client.model.proto.constant.UserStatus getUserStatus() {
        im.turms.client.model.proto.constant.UserStatus result =
                im.turms.client.model.proto.constant.UserStatus.forNumber(userStatus_);
        return result == null
                ? im.turms.client.model.proto.constant.UserStatus.UNRECOGNIZED
                : result;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @param value The enum numeric value on the wire for userStatus to set.
     */
    private void setUserStatusValue(int value) {
        bitField0_ |= 0x00000040;
        userStatus_ = value;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     *
     * @param value The userStatus to set.
     */
    private void setUserStatus(im.turms.client.model.proto.constant.UserStatus value) {
        userStatus_ = value.getNumber();
        bitField0_ |= 0x00000040;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
     */
    private void clearUserStatus() {
        bitField0_ &= ~0x00000040;
        userStatus_ = 0;
    }

    public static final int USING_DEVICE_TYPES_FIELD_NUMBER = 8;
    private com.google.protobuf.Internal.IntList usingDeviceTypes_;
    private static final com.google.protobuf.Internal.ListAdapter.Converter<java.lang.Integer, im.turms.client.model.proto.constant.DeviceType> usingDeviceTypes_converter_ =
            from -> {
                im.turms.client.model.proto.constant.DeviceType result =
                        im.turms.client.model.proto.constant.DeviceType.forNumber(from);
                return result == null
                        ? im.turms.client.model.proto.constant.DeviceType.UNRECOGNIZED
                        : result;
            };

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return A list containing the usingDeviceTypes.
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.constant.DeviceType> getUsingDeviceTypesList() {
        return new com.google.protobuf.Internal.ListAdapter<>(
                usingDeviceTypes_,
                usingDeviceTypes_converter_);
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return The count of usingDeviceTypes.
     */
    @java.lang.Override
    public int getUsingDeviceTypesCount() {
        return usingDeviceTypes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param index The index of the element to return.
     * @return The usingDeviceTypes at the given index.
     */
    @java.lang.Override
    public im.turms.client.model.proto.constant.DeviceType getUsingDeviceTypes(int index) {
        im.turms.client.model.proto.constant.DeviceType result =
                im.turms.client.model.proto.constant.DeviceType
                        .forNumber(usingDeviceTypes_.getInt(index));
        return result == null
                ? im.turms.client.model.proto.constant.DeviceType.UNRECOGNIZED
                : result;
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getUsingDeviceTypesValueList() {
        return usingDeviceTypes_;
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of usingDeviceTypes at the given index.
     */
    @java.lang.Override
    public int getUsingDeviceTypesValue(int index) {
        return usingDeviceTypes_.getInt(index);
    }

    private int usingDeviceTypesMemoizedSerializedSize;

    private void ensureUsingDeviceTypesIsMutable() {
        com.google.protobuf.Internal.IntList tmp = usingDeviceTypes_;
        if (!tmp.isModifiable()) {
            usingDeviceTypes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param index The index to set the value at.
     * @param value The usingDeviceTypes to set.
     */
    private void setUsingDeviceTypes(
            int index,
            im.turms.client.model.proto.constant.DeviceType value) {
        value.getClass();
        ensureUsingDeviceTypesIsMutable();
        usingDeviceTypes_.setInt(index, value.getNumber());
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param value The usingDeviceTypes to add.
     */
    private void addUsingDeviceTypes(im.turms.client.model.proto.constant.DeviceType value) {
        value.getClass();
        ensureUsingDeviceTypesIsMutable();
        usingDeviceTypes_.addInt(value.getNumber());
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param values The usingDeviceTypes to add.
     */
    private void addAllUsingDeviceTypes(
            java.lang.Iterable<? extends im.turms.client.model.proto.constant.DeviceType> values) {
        ensureUsingDeviceTypesIsMutable();
        for (im.turms.client.model.proto.constant.DeviceType value : values) {
            usingDeviceTypes_.addInt(value.getNumber());
        }
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     */
    private void clearUsingDeviceTypes() {
        usingDeviceTypes_ = emptyIntList();
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param value The enum numeric value on the wire for usingDeviceTypes to set.
     */
    private void setUsingDeviceTypesValue(int index, int value) {
        ensureUsingDeviceTypesIsMutable();
        usingDeviceTypes_.setInt(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param value The enum numeric value on the wire for usingDeviceTypes to add.
     */
    private void addUsingDeviceTypesValue(int value) {
        ensureUsingDeviceTypesIsMutable();
        usingDeviceTypes_.addInt(value);
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @param values The enum numeric values on the wire for usingDeviceTypes to add.
     */
    private void addAllUsingDeviceTypesValue(java.lang.Iterable<java.lang.Integer> values) {
        ensureUsingDeviceTypesIsMutable();
        for (int value : values) {
            usingDeviceTypes_.addInt(value);
        }
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupMember parseFrom(
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
            im.turms.client.model.proto.model.group.GroupMember prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupMember}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.group.GroupMember, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupMember)
            im.turms.client.model.proto.model.group.GroupMemberOrBuilder {
        // Construct using im.turms.client.model.proto.model.group.GroupMember.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>optional int64 group_id = 1;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return instance.hasGroupId();
        }

        /**
         * <code>optional int64 group_id = 1;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <code>optional int64 group_id = 1;</code>
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
         * <code>optional int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            copyOnWrite();
            instance.clearGroupId();
            return this;
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return Whether the userId field is set.
         */
        @java.lang.Override
        public boolean hasUserId() {
            return instance.hasUserId();
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return instance.getUserId();
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @param value The userId to set.
         * @return This builder for chaining.
         */
        public Builder setUserId(long value) {
            copyOnWrite();
            instance.setUserId(value);
            return this;
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            copyOnWrite();
            instance.clearUserId();
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
         * <code>optional int64 join_date = 5;</code>
         *
         * @return Whether the joinDate field is set.
         */
        @java.lang.Override
        public boolean hasJoinDate() {
            return instance.hasJoinDate();
        }

        /**
         * <code>optional int64 join_date = 5;</code>
         *
         * @return The joinDate.
         */
        @java.lang.Override
        public long getJoinDate() {
            return instance.getJoinDate();
        }

        /**
         * <code>optional int64 join_date = 5;</code>
         *
         * @param value The joinDate to set.
         * @return This builder for chaining.
         */
        public Builder setJoinDate(long value) {
            copyOnWrite();
            instance.setJoinDate(value);
            return this;
        }

        /**
         * <code>optional int64 join_date = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJoinDate() {
            copyOnWrite();
            instance.clearJoinDate();
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return Whether the muteEndDate field is set.
         */
        @java.lang.Override
        public boolean hasMuteEndDate() {
            return instance.hasMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return The muteEndDate.
         */
        @java.lang.Override
        public long getMuteEndDate() {
            return instance.getMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
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
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuteEndDate() {
            copyOnWrite();
            instance.clearMuteEndDate();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @return Whether the userStatus field is set.
         */
        @java.lang.Override
        public boolean hasUserStatus() {
            return instance.hasUserStatus();
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @return The enum numeric value on the wire for userStatus.
         */
        @java.lang.Override
        public int getUserStatusValue() {
            return instance.getUserStatusValue();
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @param value The userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatusValue(int value) {
            copyOnWrite();
            instance.setUserStatusValue(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @return The userStatus.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.UserStatus getUserStatus() {
            return instance.getUserStatus();
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @param value The enum numeric value on the wire for userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatus(im.turms.client.model.proto.constant.UserStatus value) {
            copyOnWrite();
            instance.setUserStatus(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserStatus() {
            copyOnWrite();
            instance.clearUserStatus();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return A list containing the usingDeviceTypes.
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.constant.DeviceType> getUsingDeviceTypesList() {
            return instance.getUsingDeviceTypesList();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return The count of usingDeviceTypes.
         */
        @java.lang.Override
        public int getUsingDeviceTypesCount() {
            return instance.getUsingDeviceTypesCount();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index of the element to return.
         * @return The usingDeviceTypes at the given index.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.DeviceType getUsingDeviceTypes(int index) {
            return instance.getUsingDeviceTypes(index);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index to set the value at.
         * @param value The usingDeviceTypes to set.
         * @return This builder for chaining.
         */
        public Builder setUsingDeviceTypes(
                int index,
                im.turms.client.model.proto.constant.DeviceType value) {
            copyOnWrite();
            instance.setUsingDeviceTypes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param value The usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addUsingDeviceTypes(im.turms.client.model.proto.constant.DeviceType value) {
            copyOnWrite();
            instance.addUsingDeviceTypes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param values The usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllUsingDeviceTypes(
                java.lang.Iterable<? extends im.turms.client.model.proto.constant.DeviceType> values) {
            copyOnWrite();
            instance.addAllUsingDeviceTypes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUsingDeviceTypes() {
            copyOnWrite();
            instance.clearUsingDeviceTypes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
         */
        @java.lang.Override
        public java.util.List<java.lang.Integer> getUsingDeviceTypesValueList() {
            return java.util.Collections.unmodifiableList(instance.getUsingDeviceTypesValueList());
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of usingDeviceTypes at the given index.
         */
        @java.lang.Override
        public int getUsingDeviceTypesValue(int index) {
            return instance.getUsingDeviceTypesValue(index);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index to set the value at.
         * @param value The enum numeric value on the wire for usingDeviceTypes to set.
         * @return This builder for chaining.
         */
        public Builder setUsingDeviceTypesValue(int index, int value) {
            copyOnWrite();
            instance.setUsingDeviceTypesValue(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param value The enum numeric value on the wire for usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addUsingDeviceTypesValue(int value) {
            copyOnWrite();
            instance.addUsingDeviceTypesValue(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param values The enum numeric values on the wire for usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllUsingDeviceTypesValue(java.lang.Iterable<java.lang.Integer> values) {
            copyOnWrite();
            instance.addAllUsingDeviceTypesValue(values);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupMember)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.group.GroupMember();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupId_",
                        "userId_",
                        "name_",
                        "role_",
                        "joinDate_",
                        "muteEndDate_",
                        "userStatus_",
                        "usingDeviceTypes_",};
                java.lang.String info =
                        "\u0000\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001\u1002\u0000\u0002\u1002\u0001"
                                + "\u0003\u1208\u0002\u0004\u100c\u0003\u0005\u1002\u0004\u0006\u1002\u0005\u0007\u100c"
                                + "\u0006\b,";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.group.GroupMember> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.group.GroupMember.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupMember)
    private static final im.turms.client.model.proto.model.group.GroupMember DEFAULT_INSTANCE;

    static {
        GroupMember defaultInstance = new GroupMember();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(GroupMember.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.group.GroupMember getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<GroupMember> PARSER;

    public static com.google.protobuf.Parser<GroupMember> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}