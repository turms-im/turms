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

package im.turms.client.model.proto.request.user;

/**
 * Protobuf type {@code im.turms.proto.CreateSessionRequest}
 */
public final class CreateSessionRequest extends
        com.google.protobuf.GeneratedMessageLite<CreateSessionRequest, CreateSessionRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateSessionRequest)
        CreateSessionRequestOrBuilder {
    private CreateSessionRequest() {
        password_ = "";
    }

    private int bitField0_;
    public static final int VERSION_FIELD_NUMBER = 1;
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
     */
    private void setVersion(int value) {

        version_ = value;
    }

    /**
     * <code>int32 version = 1;</code>
     */
    private void clearVersion() {

        version_ = 0;
    }

    public static final int USER_ID_FIELD_NUMBER = 2;
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
     */
    private void setUserId(long value) {

        userId_ = value;
    }

    /**
     * <code>int64 user_id = 2;</code>
     */
    private void clearUserId() {

        userId_ = 0L;
    }

    public static final int PASSWORD_FIELD_NUMBER = 3;
    private java.lang.String password_;

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
        return password_;
    }

    /**
     * <code>optional string password = 3;</code>
     *
     * @return The bytes for password.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPasswordBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(password_);
    }

    /**
     * <code>optional string password = 3;</code>
     *
     * @param value The password to set.
     */
    private void setPassword(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        password_ = value;
    }

    /**
     * <code>optional string password = 3;</code>
     */
    private void clearPassword() {
        bitField0_ &= ~0x00000001;
        password_ = getDefaultInstance().getPassword();
    }

    /**
     * <code>optional string password = 3;</code>
     *
     * @param value The bytes for password to set.
     */
    private void setPasswordBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        password_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int USER_STATUS_FIELD_NUMBER = 4;
    private int userStatus_;

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
    public im.turms.client.model.proto.constant.UserStatus getUserStatus() {
        im.turms.client.model.proto.constant.UserStatus result =
                im.turms.client.model.proto.constant.UserStatus.forNumber(userStatus_);
        return result == null
                ? im.turms.client.model.proto.constant.UserStatus.UNRECOGNIZED
                : result;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @param value The enum numeric value on the wire for userStatus to set.
     */
    private void setUserStatusValue(int value) {
        bitField0_ |= 0x00000002;
        userStatus_ = value;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     *
     * @param value The userStatus to set.
     */
    private void setUserStatus(im.turms.client.model.proto.constant.UserStatus value) {
        userStatus_ = value.getNumber();
        bitField0_ |= 0x00000002;
    }

    /**
     * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
     */
    private void clearUserStatus() {
        bitField0_ &= ~0x00000002;
        userStatus_ = 0;
    }

    public static final int DEVICE_TYPE_FIELD_NUMBER = 5;
    private int deviceType_;

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
    public im.turms.client.model.proto.constant.DeviceType getDeviceType() {
        im.turms.client.model.proto.constant.DeviceType result =
                im.turms.client.model.proto.constant.DeviceType.forNumber(deviceType_);
        return result == null
                ? im.turms.client.model.proto.constant.DeviceType.UNRECOGNIZED
                : result;
    }

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
     *
     * @param value The enum numeric value on the wire for deviceType to set.
     */
    private void setDeviceTypeValue(int value) {
        deviceType_ = value;
    }

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
     *
     * @param value The deviceType to set.
     */
    private void setDeviceType(im.turms.client.model.proto.constant.DeviceType value) {
        deviceType_ = value.getNumber();

    }

    /**
     * <code>.im.turms.proto.DeviceType device_type = 5;</code>
     */
    private void clearDeviceType() {

        deviceType_ = 0;
    }

    public static final int DEVICE_DETAILS_FIELD_NUMBER = 6;

    private static final class DeviceDetailsDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntryLite.<java.lang.String, java.lang.String>newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> deviceDetails_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> internalGetDeviceDetails() {
        return deviceDetails_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> internalGetMutableDeviceDetails() {
        if (!deviceDetails_.isMutable()) {
            deviceDetails_ = deviceDetails_.mutableCopy();
        }
        return deviceDetails_;
    }

    @java.lang.Override

    public int getDeviceDetailsCount() {
        return internalGetDeviceDetails().size();
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    @java.lang.Override

    public boolean containsDeviceDetails(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        return internalGetDeviceDetails().containsKey(key);
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
        return java.util.Collections.unmodifiableMap(internalGetDeviceDetails());
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
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDeviceDetails();
        return map.containsKey(key)
                ? map.get(key)
                : defaultValue;
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    @java.lang.Override

    public java.lang.String getDeviceDetailsOrThrow(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDeviceDetails();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <code>map&lt;string, string&gt; device_details = 6;</code>
     */
    private java.util.Map<java.lang.String, java.lang.String> getMutableDeviceDetailsMap() {
        return internalGetMutableDeviceDetails();
    }

    public static final int LOCATION_FIELD_NUMBER = 7;
    private im.turms.client.model.proto.model.user.UserLocation location_;

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     */
    @java.lang.Override
    public boolean hasLocation() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.user.UserLocation getLocation() {
        return location_ == null
                ? im.turms.client.model.proto.model.user.UserLocation.getDefaultInstance()
                : location_;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     */
    private void setLocation(im.turms.client.model.proto.model.user.UserLocation value) {
        value.getClass();
        location_ = value;
        bitField0_ |= 0x00000004;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeLocation(im.turms.client.model.proto.model.user.UserLocation value) {
        value.getClass();
        if (location_ != null
                && location_ != im.turms.client.model.proto.model.user.UserLocation
                        .getDefaultInstance()) {
            location_ = im.turms.client.model.proto.model.user.UserLocation.newBuilder(location_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            location_ = value;
        }
        bitField0_ |= 0x00000004;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 7;</code>
     */
    private void clearLocation() {
        location_ = null;
        bitField0_ &= ~0x00000004;
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest parseFrom(
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
            im.turms.client.model.proto.request.user.CreateSessionRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.CreateSessionRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.CreateSessionRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateSessionRequest)
            im.turms.client.model.proto.request.user.CreateSessionRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.CreateSessionRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int32 version = 1;</code>
         *
         * @return The version.
         */
        @java.lang.Override
        public int getVersion() {
            return instance.getVersion();
        }

        /**
         * <code>int32 version = 1;</code>
         *
         * @param value The version to set.
         * @return This builder for chaining.
         */
        public Builder setVersion(int value) {
            copyOnWrite();
            instance.setVersion(value);
            return this;
        }

        /**
         * <code>int32 version = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVersion() {
            copyOnWrite();
            instance.clearVersion();
            return this;
        }

        /**
         * <code>int64 user_id = 2;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return instance.getUserId();
        }

        /**
         * <code>int64 user_id = 2;</code>
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
         * <code>int64 user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            copyOnWrite();
            instance.clearUserId();
            return this;
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return Whether the password field is set.
         */
        @java.lang.Override
        public boolean hasPassword() {
            return instance.hasPassword();
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return The password.
         */
        @java.lang.Override
        public java.lang.String getPassword() {
            return instance.getPassword();
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return The bytes for password.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getPasswordBytes() {
            return instance.getPasswordBytes();
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @param value The password to set.
         * @return This builder for chaining.
         */
        public Builder setPassword(java.lang.String value) {
            copyOnWrite();
            instance.setPassword(value);
            return this;
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            copyOnWrite();
            instance.clearPassword();
            return this;
        }

        /**
         * <code>optional string password = 3;</code>
         *
         * @param value The bytes for password to set.
         * @return This builder for chaining.
         */
        public Builder setPasswordBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setPasswordBytes(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return Whether the userStatus field is set.
         */
        @java.lang.Override
        public boolean hasUserStatus() {
            return instance.hasUserStatus();
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return The enum numeric value on the wire for userStatus.
         */
        @java.lang.Override
        public int getUserStatusValue() {
            return instance.getUserStatusValue();
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
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
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return The userStatus.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.UserStatus getUserStatus() {
            return instance.getUserStatus();
        }

        /**
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
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
         * <code>optional .im.turms.proto.UserStatus user_status = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserStatus() {
            copyOnWrite();
            instance.clearUserStatus();
            return this;
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @return The enum numeric value on the wire for deviceType.
         */
        @java.lang.Override
        public int getDeviceTypeValue() {
            return instance.getDeviceTypeValue();
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @param value The deviceType to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceTypeValue(int value) {
            copyOnWrite();
            instance.setDeviceTypeValue(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @return The deviceType.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.DeviceType getDeviceType() {
            return instance.getDeviceType();
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @param value The enum numeric value on the wire for deviceType to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceType(im.turms.client.model.proto.constant.DeviceType value) {
            copyOnWrite();
            instance.setDeviceType(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.DeviceType device_type = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceType() {
            copyOnWrite();
            instance.clearDeviceType();
            return this;
        }

        @java.lang.Override

        public int getDeviceDetailsCount() {
            return instance.getDeviceDetailsMap()
                    .size();
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        @java.lang.Override

        public boolean containsDeviceDetails(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            return instance.getDeviceDetailsMap()
                    .containsKey(key);
        }

        public Builder clearDeviceDetails() {
            copyOnWrite();
            instance.getMutableDeviceDetailsMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */

        public Builder removeDeviceDetails(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            copyOnWrite();
            instance.getMutableDeviceDetailsMap()
                    .remove(key);
            return this;
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
            return java.util.Collections.unmodifiableMap(instance.getDeviceDetailsMap());
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
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, java.lang.String> map = instance.getDeviceDetailsMap();
            return map.containsKey(key)
                    ? map.get(key)
                    : defaultValue;
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        @java.lang.Override

        public java.lang.String getDeviceDetailsOrThrow(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, java.lang.String> map = instance.getDeviceDetailsMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        public Builder putDeviceDetails(java.lang.String key, java.lang.String value) {
            java.lang.Class<?> keyClass = key.getClass();
            java.lang.Class<?> valueClass = value.getClass();
            copyOnWrite();
            instance.getMutableDeviceDetailsMap()
                    .put(key, value);
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; device_details = 6;</code>
         */
        public Builder putAllDeviceDetails(
                java.util.Map<java.lang.String, java.lang.String> values) {
            copyOnWrite();
            instance.getMutableDeviceDetailsMap()
                    .putAll(values);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        @java.lang.Override
        public boolean hasLocation() {
            return instance.hasLocation();
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserLocation getLocation() {
            return instance.getLocation();
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder setLocation(im.turms.client.model.proto.model.user.UserLocation value) {
            copyOnWrite();
            instance.setLocation(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder setLocation(
                im.turms.client.model.proto.model.user.UserLocation.Builder builderForValue) {
            copyOnWrite();
            instance.setLocation(builderForValue.build());
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder mergeLocation(im.turms.client.model.proto.model.user.UserLocation value) {
            copyOnWrite();
            instance.mergeLocation(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 7;</code>
         */
        public Builder clearLocation() {
            copyOnWrite();
            instance.clearLocation();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateSessionRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.CreateSessionRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "version_",
                        "userId_",
                        "password_",
                        "userStatus_",
                        "deviceType_",
                        "deviceDetails_",
                        DeviceDetailsDefaultEntryHolder.defaultEntry,
                        "location_",};
                java.lang.String info =
                        "\u0000\u0007\u0000\u0001\u0001\u0007\u0007\u0001\u0000\u0000\u0001\u0004\u0002\u0002"
                                + "\u0003\u1208\u0000\u0004\u100c\u0001\u0005\f\u00062\u0007\u1009\u0002";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.CreateSessionRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.CreateSessionRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateSessionRequest)
    private static final im.turms.client.model.proto.request.user.CreateSessionRequest DEFAULT_INSTANCE;

    static {
        CreateSessionRequest defaultInstance = new CreateSessionRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(CreateSessionRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.CreateSessionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CreateSessionRequest> PARSER;

    public static com.google.protobuf.Parser<CreateSessionRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}