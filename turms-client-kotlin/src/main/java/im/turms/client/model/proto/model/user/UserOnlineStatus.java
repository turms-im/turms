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

package im.turms.client.model.proto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserOnlineStatus}
 */
public final class UserOnlineStatus
        extends com.google.protobuf.GeneratedMessageLite<UserOnlineStatus, UserOnlineStatus.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserOnlineStatus)
        UserOnlineStatusOrBuilder {
    private UserOnlineStatus() {
        deviceTypes_ = emptyIntList();
        customAttributes_ = emptyProtobufList();
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

    /**
     * <code>int64 user_id = 1;</code>
     *
     * @param value The userId to set.
     */
    private void setUserId(long value) {

        userId_ = value;
    }

    /**
     * <code>int64 user_id = 1;</code>
     */
    private void clearUserId() {

        userId_ = 0L;
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
    public im.turms.client.model.proto.constant.UserStatus getUserStatus() {
        im.turms.client.model.proto.constant.UserStatus result =
                im.turms.client.model.proto.constant.UserStatus.forNumber(userStatus_);
        return result == null
                ? im.turms.client.model.proto.constant.UserStatus.UNRECOGNIZED
                : result;
    }

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @param value The enum numeric value on the wire for userStatus to set.
     */
    private void setUserStatusValue(int value) {
        userStatus_ = value;
    }

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     *
     * @param value The userStatus to set.
     */
    private void setUserStatus(im.turms.client.model.proto.constant.UserStatus value) {
        userStatus_ = value.getNumber();

    }

    /**
     * <code>.im.turms.proto.UserStatus user_status = 2;</code>
     */
    private void clearUserStatus() {

        userStatus_ = 0;
    }

    public static final int DEVICE_TYPES_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.IntList deviceTypes_;
    private static final com.google.protobuf.Internal.ListAdapter.Converter<java.lang.Integer, im.turms.client.model.proto.constant.DeviceType> deviceTypes_converter_ =
            from -> {
                im.turms.client.model.proto.constant.DeviceType result =
                        im.turms.client.model.proto.constant.DeviceType.forNumber(from);
                return result == null
                        ? im.turms.client.model.proto.constant.DeviceType.UNRECOGNIZED
                        : result;
            };

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @return A list containing the deviceTypes.
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.constant.DeviceType> getDeviceTypesList() {
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
    public im.turms.client.model.proto.constant.DeviceType getDeviceTypes(int index) {
        im.turms.client.model.proto.constant.DeviceType result =
                im.turms.client.model.proto.constant.DeviceType
                        .forNumber(deviceTypes_.getInt(index));
        return result == null
                ? im.turms.client.model.proto.constant.DeviceType.UNRECOGNIZED
                : result;
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
        return deviceTypes_.getInt(index);
    }

    private int deviceTypesMemoizedSerializedSize;

    private void ensureDeviceTypesIsMutable() {
        com.google.protobuf.Internal.IntList tmp = deviceTypes_;
        if (!tmp.isModifiable()) {
            deviceTypes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param index The index to set the value at.
     * @param value The deviceTypes to set.
     */
    private void setDeviceTypes(int index, im.turms.client.model.proto.constant.DeviceType value) {
        value.getClass();
        ensureDeviceTypesIsMutable();
        deviceTypes_.setInt(index, value.getNumber());
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param value The deviceTypes to add.
     */
    private void addDeviceTypes(im.turms.client.model.proto.constant.DeviceType value) {
        value.getClass();
        ensureDeviceTypesIsMutable();
        deviceTypes_.addInt(value.getNumber());
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param values The deviceTypes to add.
     */
    private void addAllDeviceTypes(
            java.lang.Iterable<? extends im.turms.client.model.proto.constant.DeviceType> values) {
        ensureDeviceTypesIsMutable();
        for (im.turms.client.model.proto.constant.DeviceType value : values) {
            deviceTypes_.addInt(value.getNumber());
        }
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     */
    private void clearDeviceTypes() {
        deviceTypes_ = emptyIntList();
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param value The enum numeric value on the wire for deviceTypes to set.
     */
    private void setDeviceTypesValue(int index, int value) {
        ensureDeviceTypesIsMutable();
        deviceTypes_.setInt(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param value The enum numeric value on the wire for deviceTypes to add.
     */
    private void addDeviceTypesValue(int value) {
        ensureDeviceTypesIsMutable();
        deviceTypes_.addInt(value);
    }

    /**
     * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
     *
     * @param values The enum numeric values on the wire for deviceTypes to add.
     */
    private void addAllDeviceTypesValue(java.lang.Iterable<java.lang.Integer> values) {
        ensureDeviceTypesIsMutable();
        for (int value : values) {
            deviceTypes_.addInt(value);
        }
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private void ensureCustomAttributesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                customAttributes_;
        if (!tmp.isModifiable()) {
            customAttributes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void setCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addAllCustomAttributes(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureCustomAttributesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, customAttributes_);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void clearCustomAttributes() {
        customAttributes_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void removeCustomAttributes(int index) {
        ensureCustomAttributesIsMutable();
        customAttributes_.remove(index);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus parseFrom(
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
            im.turms.client.model.proto.model.user.UserOnlineStatus prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserOnlineStatus}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.UserOnlineStatus, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserOnlineStatus)
            im.turms.client.model.proto.model.user.UserOnlineStatusOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.UserOnlineStatus.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return instance.getUserId();
        }

        /**
         * <code>int64 user_id = 1;</code>
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
         * <code>int64 user_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            copyOnWrite();
            instance.clearUserId();
            return this;
        }

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @return The enum numeric value on the wire for userStatus.
         */
        @java.lang.Override
        public int getUserStatusValue() {
            return instance.getUserStatusValue();
        }

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
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
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @return The userStatus.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.UserStatus getUserStatus() {
            return instance.getUserStatus();
        }

        /**
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
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
         * <code>.im.turms.proto.UserStatus user_status = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserStatus() {
            copyOnWrite();
            instance.clearUserStatus();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return A list containing the deviceTypes.
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.constant.DeviceType> getDeviceTypesList() {
            return instance.getDeviceTypesList();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return The count of deviceTypes.
         */
        @java.lang.Override
        public int getDeviceTypesCount() {
            return instance.getDeviceTypesCount();
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The deviceTypes at the given index.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.DeviceType getDeviceTypes(int index) {
            return instance.getDeviceTypes(index);
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
                im.turms.client.model.proto.constant.DeviceType value) {
            copyOnWrite();
            instance.setDeviceTypes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param value The deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addDeviceTypes(im.turms.client.model.proto.constant.DeviceType value) {
            copyOnWrite();
            instance.addDeviceTypes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param values The deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllDeviceTypes(
                java.lang.Iterable<? extends im.turms.client.model.proto.constant.DeviceType> values) {
            copyOnWrite();
            instance.addAllDeviceTypes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceTypes() {
            copyOnWrite();
            instance.clearDeviceTypes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @return A list containing the enum numeric values on the wire for deviceTypes.
         */
        @java.lang.Override
        public java.util.List<java.lang.Integer> getDeviceTypesValueList() {
            return java.util.Collections.unmodifiableList(instance.getDeviceTypesValueList());
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The enum numeric value on the wire of deviceTypes at the given index.
         */
        @java.lang.Override
        public int getDeviceTypesValue(int index) {
            return instance.getDeviceTypesValue(index);
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The enum numeric value on the wire for deviceTypes to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceTypesValue(int index, int value) {
            copyOnWrite();
            instance.setDeviceTypesValue(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param value The enum numeric value on the wire for deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addDeviceTypesValue(int value) {
            copyOnWrite();
            instance.addDeviceTypesValue(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.DeviceType device_types = 3;</code>
         *
         * @param values The enum numeric values on the wire for deviceTypes to add.
         * @return This builder for chaining.
         */
        public Builder addAllDeviceTypesValue(java.lang.Iterable<java.lang.Integer> values) {
            copyOnWrite();
            instance.addAllDeviceTypesValue(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
            return java.util.Collections.unmodifiableList(instance.getCustomAttributesList());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public int getCustomAttributesCount() {
            return instance.getCustomAttributesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
            return instance.getCustomAttributes(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllCustomAttributes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            copyOnWrite();
            instance.clearCustomAttributes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            copyOnWrite();
            instance.removeCustomAttributes(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserOnlineStatus)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.UserOnlineStatus();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"userId_",
                        "userStatus_",
                        "deviceTypes_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0000\u0001\u000f\u0004\u0000\u0002\u0000\u0001\u0002\u0002\f"
                                + "\u0003,\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.UserOnlineStatus> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.UserOnlineStatus.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserOnlineStatus)
    private static final im.turms.client.model.proto.model.user.UserOnlineStatus DEFAULT_INSTANCE;
    static {
        UserOnlineStatus defaultInstance = new UserOnlineStatus();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UserOnlineStatus.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.UserOnlineStatus getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserOnlineStatus> PARSER;

    public static com.google.protobuf.Parser<UserOnlineStatus> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}