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

package im.turms.server.common.access.client.dto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserOnlineStatus}
 */
public final class UserOnlineStatus extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserOnlineStatus)
        UserOnlineStatusOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserOnlineStatus.class.getName());
    }

    // Use UserOnlineStatus.newBuilder() to construct.
    private UserOnlineStatus(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UserOnlineStatus() {
        userStatus_ = 0;
        deviceTypes_ = java.util.Collections.emptyList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.class,
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder.class);
    }

    public static final int USER_ID_FIELD_NUMBER = 1;
    private long userId_;

    /**
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    public static final int USER_STATUS_FIELD_NUMBER = 2;
    private int userStatus_;

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    @java.lang.Override
    public int getUserStatusValue() {
        return userStatus_;
    }

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
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

    public static final int DEVICE_TYPES_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private java.util.List<java.lang.Integer> deviceTypes_;
    private static final com.google.protobuf.Internal.ListAdapter.Converter<java.lang.Integer, im.turms.server.common.access.client.dto.constant.DeviceType> deviceTypes_converter_ =
            from -> {
                im.turms.server.common.access.client.dto.constant.DeviceType result =
                        im.turms.server.common.access.client.dto.constant.DeviceType
                                .forNumber(from);
                return result == null
                        ? im.turms.server.common.access.client.dto.constant.DeviceType.UNRECOGNIZED
                        : result;
            };

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return A list containing the deviceTypes.
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getDeviceTypesList() {
        return new com.google.protobuf.Internal.ListAdapter<>(deviceTypes_, deviceTypes_converter_);
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return The count of deviceTypes.
     */
    @java.lang.Override
    public int getDeviceTypesCount() {
        return deviceTypes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The deviceTypes at the given index.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.constant.DeviceType getDeviceTypes(int index) {
        return deviceTypes_converter_.convert(deviceTypes_.get(index));
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return A list containing the enum numeric values on the wire for deviceTypes.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getDeviceTypesValueList() {
        return deviceTypes_;
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of deviceTypes at the given index.
     */
    @java.lang.Override
    public int getDeviceTypesValue(int index) {
        return deviceTypes_.get(index);
    }

    private int deviceTypesMemoizedSerializedSize;

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
            int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
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
        if (userId_ != 0L) {
            output.writeInt64(1, userId_);
        }
        if (userStatus_ != im.turms.server.common.access.client.dto.constant.UserStatus.AVAILABLE
                .getNumber()) {
            output.writeEnum(2, userStatus_);
        }
        if (!getDeviceTypesList().isEmpty()) {
            output.writeUInt32NoTag(26);
            output.writeUInt32NoTag(deviceTypesMemoizedSerializedSize);
        }
        for (Integer integer : deviceTypes_) {
            output.writeEnumNoTag(integer);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            output.writeMessage(15, value);
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
        if (userId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, userId_);
        }
        if (userStatus_ != im.turms.server.common.access.client.dto.constant.UserStatus.AVAILABLE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, userStatus_);
        }
        {
            int dataSize = 0;
            for (Integer integer : deviceTypes_) {
                dataSize += com.google.protobuf.CodedOutputStream.computeEnumSizeNoTag(integer);
            }
            size += dataSize;
            if (!getDeviceTypesList().isEmpty()) {
                size += 1;
                size += com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(dataSize);
            }
            deviceTypesMemoizedSerializedSize = dataSize;
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, value);
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
        if (!(obj instanceof UserOnlineStatus other)) {
            return super.equals(obj);
        }

        if (getUserId() != other.getUserId()) {
            return false;
        }
        return userStatus_ == other.userStatus_
                && deviceTypes_.equals(other.deviceTypes_)
                && getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + USER_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        hash = (37 * hash) + USER_STATUS_FIELD_NUMBER;
        hash = (53 * hash) + userStatus_;
        if (getDeviceTypesCount() > 0) {
            hash = (37 * hash) + DEVICE_TYPES_FIELD_NUMBER;
            hash = (53 * hash) + deviceTypes_.hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
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
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus prototype) {
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
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserOnlineStatus}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserOnlineStatus)
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.class,
                            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userId_ = 0L;
            userStatus_ = 0;
            deviceTypes_ = java.util.Collections.emptyList();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatus
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus build() {
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus result =
                    new im.turms.server.common.access.client.dto.model.user.UserOnlineStatus(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus result) {
            if (((bitField0_ & 0x00000004) != 0)) {
                deviceTypes_ = java.util.Collections.unmodifiableList(deviceTypes_);
                bitField0_ &= ~0x00000004;
            }
            result.deviceTypes_ = deviceTypes_;
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000008;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.userId_ = userId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.userStatus_ = userStatus_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserOnlineStatus) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserOnlineStatus) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserOnlineStatus
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getUserId() != 0L) {
                setUserId(other.getUserId());
            }
            if (other.userStatus_ != 0) {
                setUserStatusValue(other.getUserStatusValue());
            }
            if (!other.deviceTypes_.isEmpty()) {
                if (deviceTypes_.isEmpty()) {
                    deviceTypes_ = other.deviceTypes_;
                    bitField0_ &= ~0x00000004;
                } else {
                    ensureDeviceTypesIsMutable();
                    deviceTypes_.addAll(other.deviceTypes_);
                }
                onChanged();
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
                    } else {
                        ensureCustomAttributesIsMutable();
                        customAttributes_.addAll(other.customAttributes_);
                    }
                    onChanged();
                }
            } else {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributesBuilder_.isEmpty()) {
                        customAttributesBuilder_.dispose();
                        customAttributesBuilder_ = null;
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
                        customAttributesBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getCustomAttributesFieldBuilder()
                                        : null;
                    } else {
                        customAttributesBuilder_.addAllMessages(other.customAttributes_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
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
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            userStatus_ = input.readEnum();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            int tmpRaw = input.readEnum();
                            ensureDeviceTypesIsMutable();
                            deviceTypes_.add(tmpRaw);
                        } // case 24
                        case 26 -> {
                            int length = input.readRawVarint32();
                            int oldLimit = input.pushLimit(length);
                            while (input.getBytesUntilLimit() > 0) {
                                int tmpRaw = input.readEnum();
                                ensureDeviceTypesIsMutable();
                                deviceTypes_.add(tmpRaw);
                            }
                            input.popLimit(oldLimit);
                        } // case 26
                        case 122 -> {
                            im.turms.server.common.access.client.dto.model.common.Value m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.common.Value
                                                    .parser(),
                                            extensionRegistry);
                            if (customAttributesBuilder_ == null) {
                                ensureCustomAttributesIsMutable();
                                customAttributes_.add(m);
                            } else {
                                customAttributesBuilder_.addMessage(m);
                            }
                        } // case 122
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

        private long userId_;

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return userId_;
        }

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @param value The userId to set.
         * @return This builder for chaining.
         */
        public Builder setUserId(long value) {

            userId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            bitField0_ &= ~0x00000001;
            userId_ = 0L;
            onChanged();
            return this;
        }

        private int userStatus_;

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @return The enum numeric value on the wire for userStatus.
         */
        @java.lang.Override
        public int getUserStatusValue() {
            return userStatus_;
        }

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @param value The enum numeric value on the wire for userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatusValue(int value) {
            userStatus_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
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
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @param value The userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatus(
                im.turms.server.common.access.client.dto.constant.UserStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000002;
            userStatus_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserStatus() {
            bitField0_ &= ~0x00000002;
            userStatus_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<java.lang.Integer> deviceTypes_ = java.util.Collections.emptyList();

        private void ensureDeviceTypesIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                deviceTypes_ = new java.util.ArrayList<>(deviceTypes_);
                bitField0_ |= 0x00000004;
            }
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return A list containing the deviceTypes.
         */
        public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getDeviceTypesList() {
            return new com.google.protobuf.Internal.ListAdapter<>(
                    deviceTypes_,
                    deviceTypes_converter_);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return The count of deviceTypes.
         */
        public int getDeviceTypesCount() {
            return deviceTypes_.size();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The deviceTypes at the given index.
         */
        public im.turms.server.common.access.client.dto.constant.DeviceType getDeviceTypes(
                int index) {
            return deviceTypes_converter_.convert(deviceTypes_.get(index));
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The deviceTypes to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceTypes(
                int index,
                im.turms.server.common.access.client.dto.constant.DeviceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureDeviceTypesIsMutable();
            deviceTypes_.set(index, value.getNumber());
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param value The deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addDeviceTypes(
                im.turms.server.common.access.client.dto.constant.DeviceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureDeviceTypesIsMutable();
            deviceTypes_.add(value.getNumber());
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param values The deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllDeviceTypes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.constant.DeviceType> values) {
            ensureDeviceTypesIsMutable();
            for (im.turms.server.common.access.client.dto.constant.DeviceType value : values) {
                deviceTypes_.add(value.getNumber());
            }
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceTypes() {
            deviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return A list containing the enum numeric values on the wire for deviceTypes.
         */
        public java.util.List<java.lang.Integer> getDeviceTypesValueList() {
            return java.util.Collections.unmodifiableList(deviceTypes_);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of deviceTypes at the given index.
         */
        public int getDeviceTypesValue(int index) {
            return deviceTypes_.get(index);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The enum numeric value on the wire for deviceTypes to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceTypesValue(int index, int value) {
            ensureDeviceTypesIsMutable();
            deviceTypes_.set(index, value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param value The enum numeric value on the wire for deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addDeviceTypesValue(int value) {
            ensureDeviceTypesIsMutable();
            deviceTypes_.add(value);
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param values The enum numeric values on the wire for deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllDeviceTypesValue(java.lang.Iterable<java.lang.Integer> values) {
            ensureDeviceTypesIsMutable();
            for (int value : values) {
                deviceTypes_.add(value);
            }
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000008;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(customAttributes_);
            } else {
                return customAttributesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public int getCustomAttributesCount() {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.size();
            } else {
                return customAttributesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, customAttributes_);
                onChanged();
            } else {
                customAttributesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000008;
                onChanged();
            } else {
                customAttributesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.remove(index);
                onChanged();
            } else {
                customAttributesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        customAttributes_,
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserOnlineStatus)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserOnlineStatus)
    private static final im.turms.server.common.access.client.dto.model.user.UserOnlineStatus DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserOnlineStatus();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserOnlineStatus> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserOnlineStatus parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserOnlineStatus> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserOnlineStatus> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}