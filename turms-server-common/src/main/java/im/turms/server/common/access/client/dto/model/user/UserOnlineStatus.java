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
public final class UserOnlineStatus extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserOnlineStatus)
        UserOnlineStatusOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserOnlineStatus.newBuilder() to construct.
    private UserOnlineStatus(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserOnlineStatus() {
        userStatus_ = 0;
        usingDeviceTypes_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserOnlineStatus();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.class,
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder.class);
    }

    public static final int USER_ID_FIELD_NUMBER = 1;
    private long userId_ = 0L;

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
    private int userStatus_ = 0;

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

    public static final int USING_DEVICE_TYPES_FIELD_NUMBER = 3;
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
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @return The count of usingDeviceTypes.
     */
    @java.lang.Override
    public int getUsingDeviceTypesCount() {
        return usingDeviceTypes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
     *
     * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getUsingDeviceTypesValueList() {
        return usingDeviceTypes_;
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
        if (userId_ != 0L) {
            output.writeInt64(1, userId_);
        }
        if (userStatus_ != im.turms.server.common.access.client.dto.constant.UserStatus.AVAILABLE
                .getNumber()) {
            output.writeEnum(2, userStatus_);
        }
        if (getUsingDeviceTypesList().size() > 0) {
            output.writeUInt32NoTag(26);
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
        if (userId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, userId_);
        }
        if (userStatus_ != im.turms.server.common.access.client.dto.constant.UserStatus.AVAILABLE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, userStatus_);
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
        if (!(obj instanceof UserOnlineStatus other)) {
            return super.equals(obj);
        }

        if (getUserId() != other.getUserId()) {
            return false;
        }
        if (userStatus_ != other.userStatus_) {
            return false;
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
        hash = (37 * hash) + USER_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        hash = (37 * hash) + USER_STATUS_FIELD_NUMBER;
        hash = (53 * hash) + userStatus_;
        if (getUsingDeviceTypesCount() > 0) {
            hash = (37 * hash) + USING_DEVICE_TYPES_FIELD_NUMBER;
            hash = (53 * hash) + usingDeviceTypes_.hashCode();
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
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatus parseFrom(
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
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserOnlineStatus}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserOnlineStatus)
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOuterClass.internal_static_im_turms_proto_UserOnlineStatus_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.class,
                            im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userId_ = 0L;
            userStatus_ = 0;
            usingDeviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000004;
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
                usingDeviceTypes_ = java.util.Collections.unmodifiableList(usingDeviceTypes_);
                bitField0_ &= ~0x00000004;
            }
            result.usingDeviceTypes_ = usingDeviceTypes_;
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
            if (!other.usingDeviceTypes_.isEmpty()) {
                if (usingDeviceTypes_.isEmpty()) {
                    usingDeviceTypes_ = other.usingDeviceTypes_;
                    bitField0_ &= ~0x00000004;
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
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            userStatus_ = input.readEnum();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            int tmpRaw = input.readEnum();
                            ensureUsingDeviceTypesIsMutable();
                            usingDeviceTypes_.add(tmpRaw);
                        } // case 24
                        case 26 -> {
                            int length = input.readRawVarint32();
                            int oldLimit = input.pushLimit(length);
                            while (input.getBytesUntilLimit() > 0) {
                                int tmpRaw = input.readEnum();
                                ensureUsingDeviceTypesIsMutable();
                                usingDeviceTypes_.add(tmpRaw);
                            }
                            input.popLimit(oldLimit);
                        } // case 26
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

        private int userStatus_ = 0;

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

        private java.util.List<java.lang.Integer> usingDeviceTypes_ =
                java.util.Collections.emptyList();

        private void ensureUsingDeviceTypesIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                usingDeviceTypes_ = new java.util.ArrayList<>(usingDeviceTypes_);
                bitField0_ |= 0x00000004;
            }
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
         *
         * @return A list containing the usingDeviceTypes.
         */
        public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getUsingDeviceTypesList() {
            return new com.google.protobuf.Internal.ListAdapter<>(
                    usingDeviceTypes_,
                    usingDeviceTypes_converter_);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
         *
         * @return The count of usingDeviceTypes.
         */
        public int getUsingDeviceTypesCount() {
            return usingDeviceTypes_.size();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The usingDeviceTypes at the given index.
         */
        public im.turms.server.common.access.client.dto.constant.DeviceType getUsingDeviceTypes(
                int index) {
            return usingDeviceTypes_converter_.convert(usingDeviceTypes_.get(index));
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUsingDeviceTypes() {
            usingDeviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
         *
         * @return A list containing the enum numeric values on the wire for usingDeviceTypes.
         */
        public java.util.List<java.lang.Integer> getUsingDeviceTypesValueList() {
            return java.util.Collections.unmodifiableList(usingDeviceTypes_);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of usingDeviceTypes at the given index.
         */
        public int getUsingDeviceTypesValue(int index) {
            return usingDeviceTypes_.get(index);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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
         * <code>repeated .im.turms.proto.DeviceType using_device_types = 3;</code>
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