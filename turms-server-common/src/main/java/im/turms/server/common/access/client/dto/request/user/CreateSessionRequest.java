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
 * Protobuf type {@code im.turms.proto.CreateSessionRequest}
 */
public final class CreateSessionRequest extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateSessionRequest)
        CreateSessionRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use CreateSessionRequest.newBuilder() to construct.
    private CreateSessionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CreateSessionRequest() {
        password_ = "";
        userStatus_ = 0;
        deviceType_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new CreateSessionRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass.internal_static_im_turms_proto_CreateSessionRequest_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(int number) {
        return switch (number) {
            case 6 -> internalGetDeviceDetails();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass.internal_static_im_turms_proto_CreateSessionRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.class,
                        im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.Builder.class);
    }

    private int bitField0_;
    public static final int VERSION_FIELD_NUMBER = 1;
    private int version_ = 0;

    /**
     * <code>int32 version = 1;</code>
     *
     * @return The version.
     */
    @java.lang.Override
    public int getVersion() {
        return version_;
    }

    public static final int USER_ID_FIELD_NUMBER = 2;
    private long userId_ = 0L;

    /**
     * <code>int64 user_id = 2;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    public static final int PASSWORD_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object password_ = "";

    /**
     * <code>optional string password = 3;</code>
     *
     * @return Whether the password field is set.
     */
    @java.lang.Override
    public boolean hasPassword() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string password = 3;</code>
     *
     * @return The password.
     */
    @java.lang.Override
    public java.lang.String getPassword() {
        java.lang.Object ref = password_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            password_ = s;
            return s;
        }
    }

    /**
     * <code>optional string password = 3;</code>
     *
     * @return The bytes for password.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPasswordBytes() {
        java.lang.Object ref = password_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            password_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int USER_STATUS_FIELD_NUMBER = 4;
    private int userStatus_ = 0;

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @return Whether the userStatus field is set.
     */
    @java.lang.Override
    public boolean hasUserStatus() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @return The enum numeric value on the wire for userStatus.
     */
    @java.lang.Override
    public int getUserStatusValue() {
        return userStatus_;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
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

    public static final int DEVICE_TYPE_FIELD_NUMBER = 5;
    private int deviceType_ = 0;

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
     *
     * @return The enum numeric value on the wire for deviceType.
     */
    @java.lang.Override
    public int getDeviceTypeValue() {
        return deviceType_;
    }

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
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

    public static final int DEVICE_DETAILS_FIELD_NUMBER = 6;

    private static final class DeviceDetailsDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntry.<java.lang.String, java.lang.String>newDefaultInstance(
                        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass.internal_static_im_turms_proto_CreateSessionRequest_DeviceDetailsEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.String, java.lang.String> deviceDetails_;

    private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetDeviceDetails() {
        if (deviceDetails_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(DeviceDetailsDefaultEntryHolder.defaultEntry);
        }
        return deviceDetails_;
    }

    public int getDeviceDetailsCount() {
        return internalGetDeviceDetails().getMap()
                .size();
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    @java.lang.Override
    public boolean containsDeviceDetails(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetDeviceDetails().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getDeviceDetailsMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getDeviceDetails() {
        return getDeviceDetailsMap();
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, java.lang.String> getDeviceDetailsMap() {
        return internalGetDeviceDetails().getMap();
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    @java.lang.Override
    public /* nullable */
    java.lang.String getDeviceDetailsOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDeviceDetails().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    @java.lang.Override
    public java.lang.String getDeviceDetailsOrThrow(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDeviceDetails().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    public static final int LOCATION_FIELD_NUMBER = 7;
    private im.turms.server.common.access.client.dto.model.user.UserLocation location_;

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     *
     * @return Whether the location field is set.
     */
    @java.lang.Override
    public boolean hasLocation() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
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
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
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
        if (version_ != 0) {
            output.writeInt32(1, version_);
        }
        if (userId_ != 0L) {
            output.writeInt64(2, userId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 3, password_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeEnum(4, userStatus_);
        }
        if (deviceType_ != im.turms.server.common.access.client.dto.constant.DeviceType.DESKTOP
                .getNumber()) {
            output.writeEnum(5, deviceType_);
        }
        com.google.protobuf.GeneratedMessageV3.serializeStringMapTo(output,
                internalGetDeviceDetails(),
                DeviceDetailsDefaultEntryHolder.defaultEntry,
                6);
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(7, getLocation());
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
        if (version_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(1, version_);
        }
        if (userId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, userId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, password_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(4, userStatus_);
        }
        if (deviceType_ != im.turms.server.common.access.client.dto.constant.DeviceType.DESKTOP
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5, deviceType_);
        }
        for (java.util.Map.Entry<java.lang.String, java.lang.String> entry : internalGetDeviceDetails()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, java.lang.String> deviceDetails__ =
                    DeviceDetailsDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, deviceDetails__);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, getLocation());
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
        if (!(obj instanceof CreateSessionRequest other)) {
            return super.equals(obj);
        }

        if (getVersion() != other.getVersion()) {
            return false;
        }
        if (getUserId() != other.getUserId()) {
            return false;
        }
        if (hasPassword() != other.hasPassword()) {
            return false;
        }
        if (hasPassword()) {
            if (!getPassword().equals(other.getPassword())) {
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
        if (deviceType_ != other.deviceType_) {
            return false;
        }
        if (!internalGetDeviceDetails().equals(other.internalGetDeviceDetails())) {
            return false;
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
        hash = (37 * hash) + VERSION_FIELD_NUMBER;
        hash = (53 * hash) + getVersion();
        hash = (37 * hash) + USER_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        if (hasPassword()) {
            hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
            hash = (53 * hash) + getPassword().hashCode();
        }
        if (hasUserStatus()) {
            hash = (37 * hash) + USER_STATUS_FIELD_NUMBER;
            hash = (53 * hash) + userStatus_;
        }
        hash = (37 * hash) + DEVICE_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + deviceType_;
        if (!internalGetDeviceDetails().getMap()
                .isEmpty()) {
            hash = (37 * hash) + DEVICE_DETAILS_FIELD_NUMBER;
            hash = (53 * hash) + internalGetDeviceDetails().hashCode();
        }
        if (hasLocation()) {
            hash = (37 * hash) + LOCATION_FIELD_NUMBER;
            hash = (53 * hash) + getLocation().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.user.CreateSessionRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.CreateSessionRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateSessionRequest)
            im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass.internal_static_im_turms_proto_CreateSessionRequest_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMapField(int number) {
            return switch (number) {
                case 6 -> internalGetDeviceDetails();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMutableMapField(int number) {
            return switch (number) {
                case 6 -> internalGetMutableDeviceDetails();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass.internal_static_im_turms_proto_CreateSessionRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.class,
                            im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.user.CreateSessionRequest.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
                getLocationFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            version_ = 0;
            userId_ = 0L;
            password_ = "";
            userStatus_ = 0;
            deviceType_ = 0;
            internalGetMutableDeviceDetails().clear();
            location_ = null;
            if (locationBuilder_ != null) {
                locationBuilder_.dispose();
                locationBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass.internal_static_im_turms_proto_CreateSessionRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest build() {
            im.turms.server.common.access.client.dto.request.user.CreateSessionRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.user.CreateSessionRequest result =
                    new im.turms.server.common.access.client.dto.request.user.CreateSessionRequest(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.user.CreateSessionRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.version_ = version_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.userId_ = userId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.password_ = password_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.userStatus_ = userStatus_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.deviceType_ = deviceType_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.deviceDetails_ = internalGetDeviceDetails();
                result.deviceDetails_.makeImmutable();
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.location_ = locationBuilder_ == null
                        ? location_
                        : locationBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.user.CreateSessionRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.user.CreateSessionRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.user.CreateSessionRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getVersion() != 0) {
                setVersion(other.getVersion());
            }
            if (other.getUserId() != 0L) {
                setUserId(other.getUserId());
            }
            if (other.hasPassword()) {
                password_ = other.password_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasUserStatus()) {
                setUserStatus(other.getUserStatus());
            }
            if (other.deviceType_ != 0) {
                setDeviceTypeValue(other.getDeviceTypeValue());
            }
            internalGetMutableDeviceDetails().mergeFrom(other.internalGetDeviceDetails());
            bitField0_ |= 0x00000020;
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
                            version_ = input.readInt32();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            password_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            userStatus_ = input.readEnum();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            deviceType_ = input.readEnum();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 50 -> {
                            com.google.protobuf.MapEntry<String, String> deviceDetails__ =
                                    input.readMessage(
                                            DeviceDetailsDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableDeviceDetails().getMutableMap()
                                    .put(deviceDetails__.getKey(), deviceDetails__.getValue());
                            bitField0_ |= 0x00000020;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getLocationFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000040;
                        } // case 58
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

        private int version_;

        /**
         * <code>int32 version = 1;</code>
         *
         * @return The version.
         */
        @java.lang.Override
        public int getVersion() {
            return version_;
        }

        /**
         * <code>int32 version = 1;</code>
         *
         * @param value The version to set.
         * @return This builder for chaining.
         */
        public Builder setVersion(int value) {

            version_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int32 version = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVersion() {
            bitField0_ &= ~0x00000001;
            version_ = 0;
            onChanged();
            return this;
        }

        private long userId_;

        /**
         * <code>int64 user_id = 2;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return userId_;
        }

        /**
         * <code>int64 user_id = 2;</code>
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
         * <code>int64 user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            bitField0_ &= ~0x00000002;
            userId_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object password_ = "";

        /**
         * <code>optional string password = 3;</code>
         *
         * @return Whether the password field is set.
         */
        public boolean hasPassword() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return The password.
         */
        public java.lang.String getPassword() {
            java.lang.Object ref = password_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                password_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return The bytes for password.
         */
        public com.google.protobuf.ByteString getPasswordBytes() {
            java.lang.Object ref = password_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                password_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @param value The password to set.
         * @return This builder for chaining.
         */
        public Builder setPassword(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            password_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            password_ = getDefaultInstance().getPassword();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @param value The bytes for password to set.
         * @return This builder for chaining.
         */
        public Builder setPasswordBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            password_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int userStatus_ = 0;

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return Whether the userStatus field is set.
         */
        @java.lang.Override
        public boolean hasUserStatus() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return The enum numeric value on the wire for userStatus.
         */
        @java.lang.Override
        public int getUserStatusValue() {
            return userStatus_;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @param value The enum numeric value on the wire for userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatusValue(int value) {
            userStatus_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
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
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @param value The userStatus to set.
         * @return This builder for chaining.
         */
        public Builder setUserStatus(
                im.turms.server.common.access.client.dto.constant.UserStatus value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000008;
            userStatus_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserStatus() {
            bitField0_ &= ~0x00000008;
            userStatus_ = 0;
            onChanged();
            return this;
        }

        private int deviceType_ = 0;

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @return The enum numeric value on the wire for deviceType.
         */
        @java.lang.Override
        public int getDeviceTypeValue() {
            return deviceType_;
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @param value The enum numeric value on the wire for deviceType to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceTypeValue(int value) {
            deviceType_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
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
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @param value The deviceType to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceType(
                im.turms.server.common.access.client.dto.constant.DeviceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            deviceType_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceType() {
            bitField0_ &= ~0x00000010;
            deviceType_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> deviceDetails_;

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetDeviceDetails() {
            if (deviceDetails_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(DeviceDetailsDefaultEntryHolder.defaultEntry);
            }
            return deviceDetails_;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetMutableDeviceDetails() {
            if (deviceDetails_ == null) {
                deviceDetails_ = com.google.protobuf.MapField
                        .newMapField(DeviceDetailsDefaultEntryHolder.defaultEntry);
            }
            if (!deviceDetails_.isMutable()) {
                deviceDetails_ = deviceDetails_.copy();
            }
            bitField0_ |= 0x00000020;
            onChanged();
            return deviceDetails_;
        }

        public int getDeviceDetailsCount() {
            return internalGetDeviceDetails().getMap()
                    .size();
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        @java.lang.Override
        public boolean containsDeviceDetails(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetDeviceDetails().getMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getDeviceDetailsMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getDeviceDetails() {
            return getDeviceDetailsMap();
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, java.lang.String> getDeviceDetailsMap() {
            return internalGetDeviceDetails().getMap();
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        @java.lang.Override
        public /* nullable */
        java.lang.String getDeviceDetailsOrDefault(
                java.lang.String key,
                /* nullable */
                java.lang.String defaultValue) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map =
                    internalGetDeviceDetails().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        @java.lang.Override
        public java.lang.String getDeviceDetailsOrThrow(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map =
                    internalGetDeviceDetails().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearDeviceDetails() {
            bitField0_ &= ~0x00000020;
            internalGetMutableDeviceDetails().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        public Builder removeDeviceDetails(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableDeviceDetails().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getMutableDeviceDetails() {
            bitField0_ |= 0x00000020;
            return internalGetMutableDeviceDetails().getMutableMap();
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        public Builder putDeviceDetails(java.lang.String key, java.lang.String value) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableDeviceDetails().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x00000020;
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        public Builder putAllDeviceDetails(
                java.util.Map<java.lang.String, java.lang.String> values) {
            internalGetMutableDeviceDetails().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000020;
            return this;
        }

        private im.turms.server.common.access.client.dto.model.user.UserLocation location_;
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserLocation, im.turms.server.common.access.client.dto.model.user.UserLocation.Builder, im.turms.server.common.access.client.dto.model.user.UserLocationOrBuilder> locationBuilder_;

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         *
         * @return Whether the location field is set.
         */
        public boolean hasLocation() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
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
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
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
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder setLocation(
                im.turms.server.common.access.client.dto.model.user.UserLocation.Builder builderForValue) {
            if (locationBuilder_ == null) {
                location_ = builderForValue.build();
            } else {
                locationBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder mergeLocation(
                im.turms.server.common.access.client.dto.model.user.UserLocation value) {
            if (locationBuilder_ == null) {
                if (((bitField0_ & 0x00000040) != 0)
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
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder clearLocation() {
            bitField0_ &= ~0x00000040;
            location_ = null;
            if (locationBuilder_ != null) {
                locationBuilder_.dispose();
                locationBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserLocation.Builder getLocationBuilder() {
            bitField0_ |= 0x00000040;
            onChanged();
            return getLocationFieldBuilder().getBuilder();
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
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
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateSessionRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateSessionRequest)
    private static final im.turms.server.common.access.client.dto.request.user.CreateSessionRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.user.CreateSessionRequest();
    }

    public static im.turms.server.common.access.client.dto.request.user.CreateSessionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CreateSessionRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public CreateSessionRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<CreateSessionRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CreateSessionRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.CreateSessionRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}