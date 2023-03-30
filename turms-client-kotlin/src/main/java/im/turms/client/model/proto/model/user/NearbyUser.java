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
 * Protobuf type {@code im.turms.proto.NearbyUser}
 */
public final class NearbyUser
        extends com.google.protobuf.GeneratedMessageLite<NearbyUser, NearbyUser.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.NearbyUser)
        NearbyUserOrBuilder {
    private NearbyUser() {
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
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
     */
    private void setUserId(long value) {

        userId_ = value;
    }

    /**
     * <pre>
     * session info
     * </pre>
     * 
     * <code>int64 user_id = 1;</code>
     */
    private void clearUserId() {

        userId_ = 0L;
    }

    public static final int DEVICE_TYPE_FIELD_NUMBER = 2;
    private int deviceType_;

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
    public im.turms.client.model.proto.constant.DeviceType getDeviceType() {
        im.turms.client.model.proto.constant.DeviceType result =
                im.turms.client.model.proto.constant.DeviceType.forNumber(deviceType_);
        return result == null
                ? im.turms.client.model.proto.constant.DeviceType.UNRECOGNIZED
                : result;
    }

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @param value The enum numeric value on the wire for deviceType to set.
     */
    private void setDeviceTypeValue(int value) {
        bitField0_ |= 0x00000001;
        deviceType_ = value;
    }

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     *
     * @param value The deviceType to set.
     */
    private void setDeviceType(im.turms.client.model.proto.constant.DeviceType value) {
        deviceType_ = value.getNumber();
        bitField0_ |= 0x00000001;
    }

    /**
     * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
     */
    private void clearDeviceType() {
        bitField0_ &= ~0x00000001;
        deviceType_ = 0;
    }

    public static final int INFO_FIELD_NUMBER = 3;
    private im.turms.client.model.proto.model.user.UserInfo info_;

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
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
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.user.UserInfo getInfo() {
        return info_ == null
                ? im.turms.client.model.proto.model.user.UserInfo.getDefaultInstance()
                : info_;
    }

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     */
    private void setInfo(im.turms.client.model.proto.model.user.UserInfo value) {
        value.getClass();
        info_ = value;
        bitField0_ |= 0x00000002;
    }

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeInfo(im.turms.client.model.proto.model.user.UserInfo value) {
        value.getClass();
        if (info_ != null
                && info_ != im.turms.client.model.proto.model.user.UserInfo.getDefaultInstance()) {
            info_ = im.turms.client.model.proto.model.user.UserInfo.newBuilder(info_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            info_ = value;
        }
        bitField0_ |= 0x00000002;
    }

    /**
     * <pre>
     * user info
     * </pre>
     * 
     * <code>optional .im.turms.proto.UserInfo info = 3;</code>
     */
    private void clearInfo() {
        info_ = null;
        bitField0_ &= ~0x00000002;
    }

    public static final int DISTANCE_FIELD_NUMBER = 4;
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

    /**
     * <pre>
     * geo info
     * </pre>
     * 
     * <code>optional int32 distance = 4;</code>
     *
     * @param value The distance to set.
     */
    private void setDistance(int value) {
        bitField0_ |= 0x00000004;
        distance_ = value;
    }

    /**
     * <pre>
     * geo info
     * </pre>
     * 
     * <code>optional int32 distance = 4;</code>
     */
    private void clearDistance() {
        bitField0_ &= ~0x00000004;
        distance_ = 0;
    }

    public static final int LOCATION_FIELD_NUMBER = 5;
    private im.turms.client.model.proto.model.user.UserLocation location_;

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     */
    @java.lang.Override
    public boolean hasLocation() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.user.UserLocation getLocation() {
        return location_ == null
                ? im.turms.client.model.proto.model.user.UserLocation.getDefaultInstance()
                : location_;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     */
    private void setLocation(im.turms.client.model.proto.model.user.UserLocation value) {
        value.getClass();
        location_ = value;
        bitField0_ |= 0x00000008;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
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
        bitField0_ |= 0x00000008;
    }

    /**
     * <code>optional .im.turms.proto.UserLocation location = 5;</code>
     */
    private void clearLocation() {
        location_ = null;
        bitField0_ &= ~0x00000008;
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.user.NearbyUser prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.NearbyUser}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.NearbyUser, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.NearbyUser)
            im.turms.client.model.proto.model.user.NearbyUserOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.NearbyUser.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

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
            return instance.getUserId();
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
            copyOnWrite();
            instance.setUserId(value);
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
            copyOnWrite();
            instance.clearUserId();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return Whether the deviceType field is set.
         */
        @java.lang.Override
        public boolean hasDeviceType() {
            return instance.hasDeviceType();
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return The enum numeric value on the wire for deviceType.
         */
        @java.lang.Override
        public int getDeviceTypeValue() {
            return instance.getDeviceTypeValue();
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
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
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return The deviceType.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.DeviceType getDeviceType() {
            return instance.getDeviceType();
        }

        /**
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
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
         * <code>optional .im.turms.proto.DeviceType device_type = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceType() {
            copyOnWrite();
            instance.clearDeviceType();
            return this;
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        @java.lang.Override
        public boolean hasInfo() {
            return instance.hasInfo();
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserInfo getInfo() {
            return instance.getInfo();
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public Builder setInfo(im.turms.client.model.proto.model.user.UserInfo value) {
            copyOnWrite();
            instance.setInfo(value);
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
                im.turms.client.model.proto.model.user.UserInfo.Builder builderForValue) {
            copyOnWrite();
            instance.setInfo(builderForValue.build());
            return this;
        }

        /**
         * <pre>
         * user info
         * </pre>
         * 
         * <code>optional .im.turms.proto.UserInfo info = 3;</code>
         */
        public Builder mergeInfo(im.turms.client.model.proto.model.user.UserInfo value) {
            copyOnWrite();
            instance.mergeInfo(value);
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
            copyOnWrite();
            instance.clearInfo();
            return this;
        }

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
            return instance.hasDistance();
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
            return instance.getDistance();
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
            copyOnWrite();
            instance.setDistance(value);
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
            copyOnWrite();
            instance.clearDistance();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        @java.lang.Override
        public boolean hasLocation() {
            return instance.hasLocation();
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserLocation getLocation() {
            return instance.getLocation();
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder setLocation(im.turms.client.model.proto.model.user.UserLocation value) {
            copyOnWrite();
            instance.setLocation(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder setLocation(
                im.turms.client.model.proto.model.user.UserLocation.Builder builderForValue) {
            copyOnWrite();
            instance.setLocation(builderForValue.build());
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder mergeLocation(im.turms.client.model.proto.model.user.UserLocation value) {
            copyOnWrite();
            instance.mergeLocation(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.UserLocation location = 5;</code>
         */
        public Builder clearLocation() {
            copyOnWrite();
            instance.clearLocation();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.NearbyUser)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.NearbyUser();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "userId_",
                        "deviceType_",
                        "info_",
                        "distance_",
                        "location_",};
                java.lang.String info =
                        "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0002\u0002\u100c"
                                + "\u0000\u0003\u1009\u0001\u0004\u1004\u0002\u0005\u1009\u0003";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.NearbyUser> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.NearbyUser.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.NearbyUser)
    private static final im.turms.client.model.proto.model.user.NearbyUser DEFAULT_INSTANCE;

    static {
        NearbyUser defaultInstance = new NearbyUser();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(NearbyUser.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.NearbyUser getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<NearbyUser> PARSER;

    public static com.google.protobuf.Parser<NearbyUser> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}