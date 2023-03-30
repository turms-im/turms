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
 * Protobuf type {@code im.turms.proto.GroupMember}
 */
public final class GroupMember extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupMember)
        GroupMemberOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupMember.newBuilder() to construct.
    private GroupMember(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupMember() {
        name_ = "";
        role_ = 0;
        userStatus_ = 0;
        usingDeviceTypes_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupMember();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass.internal_static_im_turms_proto_GroupMember_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass.internal_static_im_turms_proto_GroupMember_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupMember.class,
                        im.turms.server.common.access.client.dto.model.group.GroupMember.Builder.class);
    }

    private int bitField0_;
    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_ = 0L;

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

    public static final int USER_ID_FIELD_NUMBER = 2;
    private long userId_ = 0L;

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

    public static final int NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

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
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ROLE_FIELD_NUMBER = 4;
    private int role_ = 0;

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
    public im.turms.server.common.access.client.dto.constant.GroupMemberRole getRole() {
        im.turms.server.common.access.client.dto.constant.GroupMemberRole result =
                im.turms.server.common.access.client.dto.constant.GroupMemberRole.forNumber(role_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.GroupMemberRole.UNRECOGNIZED
                : result;
    }

    public static final int JOIN_DATE_FIELD_NUMBER = 5;
    private long joinDate_ = 0L;

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

    public static final int MUTE_END_DATE_FIELD_NUMBER = 6;
    private long muteEndDate_ = 0L;

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

    public static final int USER_STATUS_FIELD_NUMBER = 7;
    private int userStatus_ = 0;

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
    public im.turms.server.common.access.client.dto.constant.UserStatus getUserStatus() {
        im.turms.server.common.access.client.dto.constant.UserStatus result =
                im.turms.server.common.access.client.dto.constant.UserStatus.forNumber(userStatus_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.UserStatus.UNRECOGNIZED
                : result;
    }

    public static final int USING_DEVICE_TYPES_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private java.util.List<java.lang.Integer> usingDeviceTypes_;
    private static final com.google.protobuf.Internal.ListAdapter.Converter<java.lang.Integer, im.turms.server.common.access.client.dto.constant.DeviceType> usingDeviceTypes_converter_ =
            from -> {
                im.turms.server.common.access.client.dto.constant.DeviceType result =
                        im.turms.server.common.access.client.dto.constant.DeviceType
                                .forNumber(from);
                return result == null
                        ? im.turms.server.common.access.client.dto.constant.DeviceType.UNRECOGNIZED
                        : result;
            };

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
     *
     * @return A list containing the usingDeviceTypes.
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getUsingDeviceTypesList() {
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
    public im.turms.server.common.access.client.dto.constant.DeviceType getUsingDeviceTypes(
            int index) {
        return usingDeviceTypes_converter_.convert(usingDeviceTypes_.get(index));
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
        return usingDeviceTypes_.get(index);
    }

    private int usingDeviceTypesMemoizedSerializedSize;

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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(1, groupId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(2, userId_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeEnum(4, role_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt64(5, joinDate_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeInt64(6, muteEndDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            output.writeEnum(7, userStatus_);
        }
        if (getUsingDeviceTypesList().size() > 0) {
            output.writeUInt32NoTag(66);
            output.writeUInt32NoTag(usingDeviceTypesMemoizedSerializedSize);
        }
        for (Integer integer : usingDeviceTypes_) {
            output.writeEnumNoTag(integer);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, groupId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, userId_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(4, role_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, joinDate_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, muteEndDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(7, userStatus_);
        }
        {
            int dataSize = 0;
            for (Integer integer : usingDeviceTypes_) {
                dataSize += com.google.protobuf.CodedOutputStream.computeEnumSizeNoTag(integer);
            }
            size += dataSize;
            if (!getUsingDeviceTypesList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(dataSize);
            }
            usingDeviceTypesMemoizedSerializedSize = dataSize;
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
        if (!(obj instanceof GroupMember other)) {
            return super.equals(obj);
        }

        if (hasGroupId() != other.hasGroupId()) {
            return false;
        }
        if (hasGroupId()) {
            if (getGroupId() != other.getGroupId()) {
                return false;
            }
        }
        if (hasUserId() != other.hasUserId()) {
            return false;
        }
        if (hasUserId()) {
            if (getUserId() != other.getUserId()) {
                return false;
            }
        }
        if (hasName() != other.hasName()) {
            return false;
        }
        if (hasName()) {
            if (!getName().equals(other.getName())) {
                return false;
            }
        }
        if (hasRole() != other.hasRole()) {
            return false;
        }
        if (hasRole()) {
            if (role_ != other.role_) {
                return false;
            }
        }
        if (hasJoinDate() != other.hasJoinDate()) {
            return false;
        }
        if (hasJoinDate()) {
            if (getJoinDate() != other.getJoinDate()) {
                return false;
            }
        }
        if (hasMuteEndDate() != other.hasMuteEndDate()) {
            return false;
        }
        if (hasMuteEndDate()) {
            if (getMuteEndDate() != other.getMuteEndDate()) {
                return false;
            }
        }
        if (hasUserStatus() != other.hasUserStatus()) {
            return false;
        }
        if (hasUserStatus()) {
            if (userStatus_ != other.userStatus_) {
                return false;
            }
        }
        if (!usingDeviceTypes_.equals(other.usingDeviceTypes_)) {
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
        if (hasGroupId()) {
            hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        }
        if (hasUserId()) {
            hash = (37 * hash) + USER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        }
        if (hasName()) {
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
        }
        if (hasRole()) {
            hash = (37 * hash) + ROLE_FIELD_NUMBER;
            hash = (53 * hash) + role_;
        }
        if (hasJoinDate()) {
            hash = (37 * hash) + JOIN_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getJoinDate());
        }
        if (hasMuteEndDate()) {
            hash = (37 * hash) + MUTE_END_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getMuteEndDate());
        }
        if (hasUserStatus()) {
            hash = (37 * hash) + USER_STATUS_FIELD_NUMBER;
            hash = (53 * hash) + userStatus_;
        }
        if (getUsingDeviceTypesCount() > 0) {
            hash = (37 * hash) + USING_DEVICE_TYPES_FIELD_NUMBER;
            hash = (53 * hash) + usingDeviceTypes_.hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember parseFrom(
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
            im.turms.server.common.access.client.dto.model.group.GroupMember prototype) {
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
     * Protobuf type {@code im.turms.proto.GroupMember}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupMember)
            im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass.internal_static_im_turms_proto_GroupMember_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass.internal_static_im_turms_proto_GroupMember_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupMember.class,
                            im.turms.server.common.access.client.dto.model.group.GroupMember.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupMember.newBuilder()
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
            userId_ = 0L;
            name_ = "";
            role_ = 0;
            joinDate_ = 0L;
            muteEndDate_ = 0L;
            userStatus_ = 0;
            usingDeviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000080;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass.internal_static_im_turms_proto_GroupMember_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMember getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupMember
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMember build() {
            im.turms.server.common.access.client.dto.model.group.GroupMember result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMember buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupMember result =
                    new im.turms.server.common.access.client.dto.model.group.GroupMember(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupMember result) {
            if (((bitField0_ & 0x00000080) != 0)) {
                usingDeviceTypes_ = java.util.Collections.unmodifiableList(usingDeviceTypes_);
                bitField0_ &= ~0x00000080;
            }
            result.usingDeviceTypes_ = usingDeviceTypes_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupMember result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.groupId_ = groupId_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.userId_ = userId_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.name_ = name_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.role_ = role_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.joinDate_ = joinDate_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.muteEndDate_ = muteEndDate_;
                to_bitField0_ |= 0x00000020;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.userStatus_ = userStatus_;
                to_bitField0_ |= 0x00000040;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupMember) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupMember) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupMember other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupMember
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasGroupId()) {
                setGroupId(other.getGroupId());
            }
            if (other.hasUserId()) {
                setUserId(other.getUserId());
            }
            if (other.hasName()) {
                name_ = other.name_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasRole()) {
                setRole(other.getRole());
            }
            if (other.hasJoinDate()) {
                setJoinDate(other.getJoinDate());
            }
            if (other.hasMuteEndDate()) {
                setMuteEndDate(other.getMuteEndDate());
            }
            if (other.hasUserStatus()) {
                setUserStatus(other.getUserStatus());
            }
            if (!other.usingDeviceTypes_.isEmpty()) {
                if (usingDeviceTypes_.isEmpty()) {
                    usingDeviceTypes_ = other.usingDeviceTypes_;
                    bitField0_ &= ~0x00000080;
                } else {
                    ensureUsingDeviceTypesIsMutable();
                    usingDeviceTypes_.addAll(other.usingDeviceTypes_);
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
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            role_ = input.readEnum();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            joinDate_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            muteEndDate_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            userStatus_ = input.readEnum();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 64 -> {
                            int tmpRaw = input.readEnum();
                            ensureUsingDeviceTypesIsMutable();
                            usingDeviceTypes_.add(tmpRaw);
                        } // case 64
                        case 66 -> {
                            int length = input.readRawVarint32();
                            int oldLimit = input.pushLimit(length);
                            while (input.getBytesUntilLimit() > 0) {
                                int tmpRaw = input.readEnum();
                                ensureUsingDeviceTypesIsMutable();
                                usingDeviceTypes_.add(tmpRaw);
                            }
                            input.popLimit(oldLimit);
                        } // case 66
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
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000001;
            groupId_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setUserId(long value) {

            userId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            bitField0_ &= ~0x00000002;
            userId_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>optional string name = 3;</code>
         *
         * @return Whether the name field is set.
         */
        public boolean hasName() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 3;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int role_ = 0;

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
         * @param value The enum numeric value on the wire for role to set.
         * @return This builder for chaining.
         */
        public Builder setRoleValue(int value) {
            role_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @return The role.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.GroupMemberRole getRole() {
            im.turms.server.common.access.client.dto.constant.GroupMemberRole result =
                    im.turms.server.common.access.client.dto.constant.GroupMemberRole
                            .forNumber(role_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.GroupMemberRole.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @param value The role to set.
         * @return This builder for chaining.
         */
        public Builder setRole(
                im.turms.server.common.access.client.dto.constant.GroupMemberRole value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000008;
            role_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.GroupMemberRole role = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRole() {
            bitField0_ &= ~0x00000008;
            role_ = 0;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setJoinDate(long value) {

            joinDate_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 join_date = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearJoinDate() {
            bitField0_ &= ~0x00000010;
            joinDate_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setMuteEndDate(long value) {

            muteEndDate_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuteEndDate() {
            bitField0_ &= ~0x00000020;
            muteEndDate_ = 0L;
            onChanged();
            return this;
        }

        private int userStatus_ = 0;

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
         * @param value The enum numeric value on the wire for userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatusValue(int value) {
            userStatus_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @return The userStatus.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.UserStatus getUserStatus() {
            im.turms.server.common.access.client.dto.constant.UserStatus result =
                    im.turms.server.common.access.client.dto.constant.UserStatus
                            .forNumber(userStatus_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.UserStatus.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @param value The userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatus(
                im.turms.server.common.access.client.dto.constant.UserStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000040;
            userStatus_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserStatus() {
            bitField0_ &= ~0x00000040;
            userStatus_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<java.lang.Integer> usingDeviceTypes_ =
                java.util.Collections.emptyList();

        private void ensureUsingDeviceTypesIsMutable() {
            if ((bitField0_ & 0x00000080) == 0) {
                usingDeviceTypes_ = new java.util.ArrayList<>(usingDeviceTypes_);
                bitField0_ |= 0x00000080;
            }
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return A list containing the usingDeviceTypes.
         */
        public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getUsingDeviceTypesList() {
            return new com.google.protobuf.Internal.ListAdapter<>(
                    usingDeviceTypes_,
                    usingDeviceTypes_converter_);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return The count of usingDeviceTypes.
         */
        public int getUsingDeviceTypesCount() {
            return usingDeviceTypes_.size();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index of the element to return.
         * @return The usingDeviceTypes at the given index.
         */
        public im.turms.server.common.access.client.dto.constant.DeviceType getUsingDeviceTypes(
                int index) {
            return usingDeviceTypes_converter_.convert(usingDeviceTypes_.get(index));
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
                im.turms.server.common.access.client.dto.constant.DeviceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureUsingDeviceTypesIsMutable();
            usingDeviceTypes_.set(index, value.getNumber());
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param value The usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addUsingDeviceTypes(
                im.turms.server.common.access.client.dto.constant.DeviceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureUsingDeviceTypesIsMutable();
            usingDeviceTypes_.add(value.getNumber());
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param values The usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllUsingDeviceTypes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.constant.DeviceType> values) {
            ensureUsingDeviceTypesIsMutable();
            for (im.turms.server.common.access.client.dto.constant.DeviceType value : values) {
                usingDeviceTypes_.add(value.getNumber());
            }
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUsingDeviceTypes() {
            usingDeviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
         */
        public java.util.List<java.lang.Integer> getUsingDeviceTypesValueList() {
            return java.util.Collections.unmodifiableList(usingDeviceTypes_);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of usingDeviceTypes at the given index.
         */
        public int getUsingDeviceTypesValue(int index) {
            return usingDeviceTypes_.get(index);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param index The index to set the value at.
         * @param value The enum numeric value on the wire for usingDeviceTypes to set.
         * @return This builder for chaining.
         */
        public Builder setUsingDeviceTypesValue(int index, int value) {
            ensureUsingDeviceTypesIsMutable();
            usingDeviceTypes_.set(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param value The enum numeric value on the wire for usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addUsingDeviceTypesValue(int value) {
            ensureUsingDeviceTypesIsMutable();
            usingDeviceTypes_.add(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 8;</code>
         *
         * @param values The enum numeric values on the wire for usingDeviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllUsingDeviceTypesValue(java.lang.Iterable<java.lang.Integer> values) {
            ensureUsingDeviceTypesIsMutable();
            for (int value : values) {
                usingDeviceTypes_.add(value);
            }
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupMember)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupMember)
    private static final im.turms.server.common.access.client.dto.model.group.GroupMember DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.group.GroupMember();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMember getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupMember> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupMember parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupMember> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupMember> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupMember getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}