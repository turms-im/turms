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

package im.turms.server.common.access.client.dto.request.user;

/**
 * Protobuf type {@code im.turms.proto.UpdateUserOnlineStatusRequest}
 */
public final class UpdateUserOnlineStatusRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateUserOnlineStatusRequest)
        UpdateUserOnlineStatusRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UpdateUserOnlineStatusRequest.newBuilder() to construct.
    private UpdateUserOnlineStatusRequest(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateUserOnlineStatusRequest() {
        deviceTypes_ = java.util.Collections.emptyList();
        userStatus_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UpdateUserOnlineStatusRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass.internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass.internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.class,
                        im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.Builder.class);
    }

    public static final int DEVICE_TYPES_FIELD_NUMBER = 1;
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
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @return A list containing the deviceTypes.
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getDeviceTypesList() {
        return new com.google.protobuf.Internal.ListAdapter<>(deviceTypes_, deviceTypes_converter_);
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @return The count of deviceTypes.
     */
    @java.lang.Override
    public int getDeviceTypesCount() {
        return deviceTypes_.size();
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The deviceTypes at the given index.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.constant.DeviceType getDeviceTypes(int index) {
        return deviceTypes_converter_.convert(deviceTypes_.get(index));
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @return A list containing the enum numeric values on the wire for deviceTypes.
     */
    @java.lang.Override
    public java.util.List<java.lang.Integer> getDeviceTypesValueList() {
        return deviceTypes_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of deviceTypes at the given index.
     */
    @java.lang.Override
    public int getDeviceTypesValue(int index) {
        return deviceTypes_.get(index);
    }

    private int deviceTypesMemoizedSerializedSize;

    public static final int USER_STATUS_FIELD_NUMBER = 2;
    private int userStatus_ = 0;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    @java.lang.Override
    public int getUserStatusValue() {
        return userStatus_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
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
        if (getDeviceTypesList().size() > 0) {
            output.writeUInt32NoTag(10);
            output.writeUInt32NoTag(deviceTypesMemoizedSerializedSize);
        }
        for (Integer integer : deviceTypes_) {
            output.writeEnumNoTag(integer);
        }
        if (userStatus_ != im.turms.server.common.access.client.dto.constant.UserStatus.AVAILABLE
                .getNumber()) {
            output.writeEnum(2, userStatus_);
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
        if (userStatus_ != im.turms.server.common.access.client.dto.constant.UserStatus.AVAILABLE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, userStatus_);
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
        if (!(obj instanceof UpdateUserOnlineStatusRequest other)) {
            return super.equals(obj);
        }

        if (!deviceTypes_.equals(other.deviceTypes_)) {
            return false;
        }
        if (userStatus_ != other.userStatus_) {
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
        if (getDeviceTypesCount() > 0) {
            hash = (37 * hash) + DEVICE_TYPES_FIELD_NUMBER;
            hash = (53 * hash) + deviceTypes_.hashCode();
        }
        hash = (37 * hash) + USER_STATUS_FIELD_NUMBER;
        hash = (53 * hash) + userStatus_;
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateUserOnlineStatusRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateUserOnlineStatusRequest)
            im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass.internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass.internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.class,
                            im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            deviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000001;
            userStatus_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass.internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest build() {
            im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest result =
                    new im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest result) {
            if (((bitField0_ & 0x00000001) != 0)) {
                deviceTypes_ = java.util.Collections.unmodifiableList(deviceTypes_);
                bitField0_ &= ~0x00000001;
            }
            result.deviceTypes_ = deviceTypes_;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.userStatus_ = userStatus_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.deviceTypes_.isEmpty()) {
                if (deviceTypes_.isEmpty()) {
                    deviceTypes_ = other.deviceTypes_;
                    bitField0_ &= ~0x00000001;
                } else {
                    ensureDeviceTypesIsMutable();
                    deviceTypes_.addAll(other.deviceTypes_);
                }
                onChanged();
            }
            if (other.userStatus_ != 0) {
                setUserStatusValue(other.getUserStatusValue());
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
                            int tmpRaw = input.readEnum();
                            ensureDeviceTypesIsMutable();
                            deviceTypes_.add(tmpRaw);
                        } // case 8
                        case 10 -> {
                            int length = input.readRawVarint32();
                            int oldLimit = input.pushLimit(length);
                            while (input.getBytesUntilLimit() > 0) {
                                int tmpRaw = input.readEnum();
                                ensureDeviceTypesIsMutable();
                                deviceTypes_.add(tmpRaw);
                            }
                            input.popLimit(oldLimit);
                        } // case 10
                        case 16 -> {
                            userStatus_ = input.readEnum();
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

        private java.util.List<java.lang.Integer> deviceTypes_ = java.util.Collections.emptyList();

        private void ensureDeviceTypesIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                deviceTypes_ = new java.util.ArrayList<>(deviceTypes_);
                bitField0_ |= 0x00000001;
            }
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
         *
         * @return A list containing the deviceTypes.
         */
        public java.util.List<im.turms.server.common.access.client.dto.constant.DeviceType> getDeviceTypesList() {
            return new com.google.protobuf.Internal.ListAdapter<>(
                    deviceTypes_,
                    deviceTypes_converter_);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
         *
         * @return The count of deviceTypes.
         */
        public int getDeviceTypesCount() {
            return deviceTypes_.size();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The deviceTypes at the given index.
         */
        public im.turms.server.common.access.client.dto.constant.DeviceType getDeviceTypes(
                int index) {
            return deviceTypes_converter_.convert(deviceTypes_.get(index));
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
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
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
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
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
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
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceTypes() {
            deviceTypes_ = java.util.Collections.emptyList();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
         *
         * @return A list containing the enum numeric values on the wire for deviceTypes.
         */
        public java.util.List<java.lang.Integer> getDeviceTypesValueList() {
            return java.util.Collections.unmodifiableList(deviceTypes_);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of deviceTypes at the given index.
         */
        public int getDeviceTypesValue(int index) {
            return deviceTypes_.get(index);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
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
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
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
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>repeated .im.turms.proto.DeviceType device_types = 1;</code>
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

        private int userStatus_ = 0;

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @return The enum numeric value on the wire for userStatus.
         */
        @java.lang.Override
        public int getUserStatusValue() {
            return userStatus_;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
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
         * <pre>
         * Update
         * </pre>
         * 
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
         * <pre>
         * Update
         * </pre>
         * 
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
         * <pre>
         * Update
         * </pre>
         * 
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateUserOnlineStatusRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateUserOnlineStatusRequest)
    private static final im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest();
    }

    public static im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateUserOnlineStatusRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateUserOnlineStatusRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateUserOnlineStatusRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateUserOnlineStatusRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}