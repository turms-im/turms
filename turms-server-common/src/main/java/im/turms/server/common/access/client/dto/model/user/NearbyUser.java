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
 * Protobuf type {@code im.turms.proto.NearbyUser}
 */
public final class NearbyUser extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.NearbyUser)
        NearbyUserOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use NearbyUser.newBuilder() to construct.
    private NearbyUser(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private NearbyUser() {
        deviceType_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new NearbyUser();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass.internal_static_im_turms_proto_NearbyUser_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass.internal_static_im_turms_proto_NearbyUser_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.NearbyUser.class,
                        im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder.class);
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private long userId_ = 0L;

    /**
     * <pre>
     * session info
     * </pre>
     * 
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    public static final int DEVICE_TYPE_FIELD_NUMBER = 2;
    private int deviceType_ = 0;

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @return Whether the deviceType field is set.
     */
    @java.lang.Override
    public boolean hasDeviceType() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @return The enum numeric value on the wire for deviceType.
     */
    @java.lang.Override
    public int getDeviceTypeValue() {
        return deviceType_;
    }

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @return The deviceType.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.constant.DeviceType getDeviceType() {
        im.turms.server.common.access.client.dto.constant.DeviceType result =
                im.turms.server.common.access.client.dto.constant.DeviceType.forNumber(deviceType_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.DeviceType.UNRECOGNIZED
                : result;
    }

    public static final int INFO_FIELD_NUMBER = 3;
    private im.turms.server.common.access.client.dto.model.user.UserInfo info_;

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     *
     * @return Whether the info field is set.
     */
    @java.lang.Override
    public boolean hasInfo() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     *
     * @return The info.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfo getInfo() {
        return info_ == null
                ? im.turms.server.common.access.client.dto.model.user.UserInfo.getDefaultInstance()
                : info_;
    }

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getInfoOrBuilder() {
        return info_ == null
                ? im.turms.server.common.access.client.dto.model.user.UserInfo.getDefaultInstance()
                : info_;
    }

    public static final int DISTANCE_FIELD_NUMBER = 4;
    private int distance_ = 0;

    /**
     * <pre>
     * geo info
     * </pre>
     * 
     * <code>optional int32 distance = 4;</code>
     *
     * @return Whether the distance field is set.
     */
    @java.lang.Override
    public boolean hasDistance() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <pre>
     * geo info
     * </pre>
     * 
     * <code>optional int32 distance = 4;</code>
     *
     * @return The distance.
     */
    @java.lang.Override
    public int getDistance() {
        return distance_;
    }

    public static final int LOCATION_FIELD_NUMBER = 5;
    private im.turms.server.common.access.client.dto.model.user.UserLocation location_;

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     *
     * @return Whether the location field is set.
     */
    @java.lang.Override
    public boolean hasLocation() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     *
     * @return The location.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserLocation getLocation() {
        return location_ == null
                ? im.turms.server.common.access.client.dto.model.user.UserLocation
                        .getDefaultInstance()
                : location_;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder getLocationOrBuilder() {
        return location_ == null
                ? im.turms.server.common.access.client.dto.model.user.UserLocation
                        .getDefaultInstance()
                : location_;
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
        if (userId_ != 0L) {
            output.writeInt64(1, userId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeEnum(2, deviceType_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(3, getInfo());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt32(4, distance_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeMessage(5, getLocation());
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, deviceType_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, getInfo());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, distance_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5, getLocation());
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
        if (!(obj instanceof NearbyUser other)) {
            return super.equals(obj);
        }

        if (getUserId() != other.getUserId()) {
            return false;
        }
        if (hasDeviceType() != other.hasDeviceType()) {
            return false;
        }
        if (hasDeviceType()) {
            if (deviceType_ != other.deviceType_) {
                return false;
            }
        }
        if (hasInfo() != other.hasInfo()) {
            return false;
        }
        if (hasInfo()) {
            if (!getInfo().equals(other.getInfo())) {
                return false;
            }
        }
        if (hasDistance() != other.hasDistance()) {
            return false;
        }
        if (hasDistance()) {
            if (getDistance() != other.getDistance()) {
                return false;
            }
        }
        if (hasLocation() != other.hasLocation()) {
            return false;
        }
        if (hasLocation()) {
            if (!getLocation().equals(other.getLocation())) {
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
        hash = (37 * hash) + USER_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        if (hasDeviceType()) {
            hash = (37 * hash) + DEVICE_TYPE_FIELD_NUMBER;
            hash = (53 * hash) + deviceType_;
        }
        if (hasInfo()) {
            hash = (37 * hash) + INFO_FIELD_NUMBER;
            hash = (53 * hash) + getInfo().hashCode();
        }
        if (hasDistance()) {
            hash = (37 * hash) + DISTANCE_FIELD_NUMBER;
            hash = (53 * hash) + getDistance();
        }
        if (hasLocation()) {
            hash = (37 * hash) + LOCATION_FIELD_NUMBER;
            hash = (53 * hash) + getLocation().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.NearbyUser prototype) {
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
     * Protobuf type {@code im.turms.proto.NearbyUser}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.NearbyUser)
            im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass.internal_static_im_turms_proto_NearbyUser_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass.internal_static_im_turms_proto_NearbyUser_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.NearbyUser.class,
                            im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.NearbyUser.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
                getInfoFieldBuilder();
                getLocationFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userId_ = 0L;
            deviceType_ = 0;
            info_ = null;
            if (infoBuilder_ != null) {
                infoBuilder_.dispose();
                infoBuilder_ = null;
            }
            distance_ = 0;
            location_ = null;
            if (locationBuilder_ != null) {
                locationBuilder_.dispose();
                locationBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass.internal_static_im_turms_proto_NearbyUser_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUser getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUser
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUser build() {
            im.turms.server.common.access.client.dto.model.user.NearbyUser result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUser buildPartial() {
            im.turms.server.common.access.client.dto.model.user.NearbyUser result =
                    new im.turms.server.common.access.client.dto.model.user.NearbyUser(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.NearbyUser result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.userId_ = userId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.deviceType_ = deviceType_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.info_ = infoBuilder_ == null
                        ? info_
                        : infoBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.distance_ = distance_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.location_ = locationBuilder_ == null
                        ? location_
                        : locationBuilder_.build();
                to_bitField0_ |= 0x00000008;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.NearbyUser) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.NearbyUser) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.NearbyUser other) {
            if (other == im.turms.server.common.access.client.dto.model.user.NearbyUser
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getUserId() != 0L) {
                setUserId(other.getUserId());
            }
            if (other.hasDeviceType()) {
                setDeviceType(other.getDeviceType());
            }
            if (other.hasInfo()) {
                mergeInfo(other.getInfo());
            }
            if (other.hasDistance()) {
                setDistance(other.getDistance());
            }
            if (other.hasLocation()) {
                mergeLocation(other.getLocation());
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
                            deviceType_ = input.readEnum();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            input.readMessage(getInfoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            distance_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 42 -> {
                            input.readMessage(getLocationFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000010;
                        } // case 42
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
         * <pre>
         * session info
         * </pre>
         * 
         * <code>int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return userId_;
        }

        /**
         * <pre>
         * session info
         * </pre>
         * 
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
         * <pre>
         * session info
         * </pre>
         * 
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

        private int deviceType_ = 0;

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return Whether the deviceType field is set.
         */
        @java.lang.Override
        public boolean hasDeviceType() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return The enum numeric value on the wire for deviceType.
         */
        @java.lang.Override
        public int getDeviceTypeValue() {
            return deviceType_;
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @param value The enum numeric value on the wire for deviceType to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceTypeValue(int value) {
            deviceType_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return The deviceType.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.DeviceType getDeviceType() {
            im.turms.server.common.access.client.dto.constant.DeviceType result =
                    im.turms.server.common.access.client.dto.constant.DeviceType
                            .forNumber(deviceType_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.DeviceType.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @param value The deviceType to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceType(
                im.turms.server.common.access.client.dto.constant.DeviceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000002;
            deviceType_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceType() {
            bitField0_ &= ~0x00000002;
            deviceType_ = 0;
            onChanged();
            return this;
        }

        private im.turms.server.common.access.client.dto.model.user.UserInfo info_;
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserInfo, im.turms.server.common.access.client.dto.model.user.UserInfo.Builder, im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> infoBuilder_;

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         *
         * @return Whether the info field is set.
         */
        public boolean hasInfo() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         *
         * @return The info.
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo getInfo() {
            if (infoBuilder_ == null) {
                return info_ == null
                        ? im.turms.server.common.access.client.dto.model.user.UserInfo
                                .getDefaultInstance()
                        : info_;
            } else {
                return infoBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public Builder setInfo(im.turms.server.common.access.client.dto.model.user.UserInfo value) {
            if (infoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                info_ = value;
            } else {
                infoBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public Builder setInfo(
                im.turms.server.common.access.client.dto.model.user.UserInfo.Builder builderForValue) {
            if (infoBuilder_ == null) {
                info_ = builderForValue.build();
            } else {
                infoBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public Builder mergeInfo(
                im.turms.server.common.access.client.dto.model.user.UserInfo value) {
            if (infoBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)
                        && info_ != null
                        && info_ != im.turms.server.common.access.client.dto.model.user.UserInfo
                                .getDefaultInstance()) {
                    getInfoBuilder().mergeFrom(value);
                } else {
                    info_ = value;
                }
            } else {
                infoBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public Builder clearInfo() {
            bitField0_ &= ~0x00000004;
            info_ = null;
            if (infoBuilder_ != null) {
                infoBuilder_.dispose();
                infoBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder getInfoBuilder() {
            bitField0_ |= 0x00000004;
            onChanged();
            return getInfoFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getInfoOrBuilder() {
            if (infoBuilder_ != null) {
                return infoBuilder_.getMessageOrBuilder();
            } else {
                return info_ == null
                        ? im.turms.server.common.access.client.dto.model.user.UserInfo
                                .getDefaultInstance()
                        : info_;
            }
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserInfo, im.turms.server.common.access.client.dto.model.user.UserInfo.Builder, im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getInfoFieldBuilder() {
            if (infoBuilder_ == null) {
                infoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        getInfo(),
                        getParentForChildren(),
                        isClean());
                info_ = null;
            }
            return infoBuilder_;
        }

        private int distance_;

        /**
         * <pre>
         * geo info
         * </pre>
         * 
         * <code>optional int32 distance = 4;</code>
         *
         * @return Whether the distance field is set.
         */
        @java.lang.Override
        public boolean hasDistance() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <pre>
         * geo info
         * </pre>
         * 
         * <code>optional int32 distance = 4;</code>
         *
         * @return The distance.
         */
        @java.lang.Override
        public int getDistance() {
            return distance_;
        }

        /**
         * <pre>
         * geo info
         * </pre>
         * 
         * <code>optional int32 distance = 4;</code>
         *
         * @param value The distance to set.
         * @return This builder for chaining.
         */
        public Builder setDistance(int value) {

            distance_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * geo info
         * </pre>
         * 
         * <code>optional int32 distance = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDistance() {
            bitField0_ &= ~0x00000008;
            distance_ = 0;
            onChanged();
            return this;
        }

        private im.turms.server.common.access.client.dto.model.user.UserLocation location_;
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserLocation, im.turms.server.common.access.client.dto.model.user.UserLocation.Builder, im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder> locationBuilder_;

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         *
         * @return Whether the location field is set.
         */
        public boolean hasLocation() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         *
         * @return The location.
         */
        public im.turms.server.common.access.client.dto.model.user.UserLocation getLocation() {
            if (locationBuilder_ == null) {
                return location_ == null
                        ? im.turms.server.common.access.client.dto.model.user.UserLocation
                                .getDefaultInstance()
                        : location_;
            } else {
                return locationBuilder_.getMessage();
            }
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder setLocation(
                im.turms.server.common.access.client.dto.model.user.UserLocation value) {
            if (locationBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                location_ = value;
            } else {
                locationBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder setLocation(
                im.turms.server.common.access.client.dto.model.user.UserLocation.Builder builderForValue) {
            if (locationBuilder_ == null) {
                location_ = builderForValue.build();
            } else {
                locationBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder mergeLocation(
                im.turms.server.common.access.client.dto.model.user.UserLocation value) {
            if (locationBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)
                        && location_ != null
                        && location_ != im.turms.server.common.access.client.dto.model.user.UserLocation
                                .getDefaultInstance()) {
                    getLocationBuilder().mergeFrom(value);
                } else {
                    location_ = value;
                }
            } else {
                locationBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder clearLocation() {
            bitField0_ &= ~0x00000010;
            location_ = null;
            if (locationBuilder_ != null) {
                locationBuilder_.dispose();
                locationBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserLocation.Builder getLocationBuilder() {
            bitField0_ |= 0x00000010;
            onChanged();
            return getLocationFieldBuilder().getBuilder();
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder getLocationOrBuilder() {
            if (locationBuilder_ != null) {
                return locationBuilder_.getMessageOrBuilder();
            } else {
                return location_ == null
                        ? im.turms.server.common.access.client.dto.model.user.UserLocation
                                .getDefaultInstance()
                        : location_;
            }
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserLocation, im.turms.server.common.access.client.dto.model.user.UserLocation.Builder, im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder> getLocationFieldBuilder() {
            if (locationBuilder_ == null) {
                locationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        getLocation(),
                        getParentForChildren(),
                        isClean());
                location_ = null;
            }
            return locationBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.NearbyUser)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.NearbyUser)
    private static final im.turms.server.common.access.client.dto.model.user.NearbyUser DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.user.NearbyUser();
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUser getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<NearbyUser> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public NearbyUser parsePartialFrom(
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

    public static com.google.protobuf.Parser<NearbyUser> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<NearbyUser> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.NearbyUser getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}