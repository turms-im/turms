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
 * Protobuf type {@code im.turms.proto.QueryNearbyUsersRequest}
 */
public final class QueryNearbyUsersRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryNearbyUsersRequest, QueryNearbyUsersRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryNearbyUsersRequest)
        QueryNearbyUsersRequestOrBuilder {
    private QueryNearbyUsersRequest() {
    }

    private int bitField0_;
    public static final int LATITUDE_FIELD_NUMBER = 1;
    private float latitude_;

    /**
     * <code>float latitude = 1;</code>
     *
     * @return The latitude.
     */
    @java.lang.Override
    public float getLatitude() {
        return latitude_;
    }

    /**
     * <code>float latitude = 1;</code>
     *
     * @param value The latitude to set.
     */
    private void setLatitude(float value) {

        latitude_ = value;
    }

    /**
     * <code>float latitude = 1;</code>
     */
    private void clearLatitude() {

        latitude_ = 0F;
    }

    public static final int LONGITUDE_FIELD_NUMBER = 2;
    private float longitude_;

    /**
     * <code>float longitude = 2;</code>
     *
     * @return The longitude.
     */
    @java.lang.Override
    public float getLongitude() {
        return longitude_;
    }

    /**
     * <code>float longitude = 2;</code>
     *
     * @param value The longitude to set.
     */
    private void setLongitude(float value) {

        longitude_ = value;
    }

    /**
     * <code>float longitude = 2;</code>
     */
    private void clearLongitude() {

        longitude_ = 0F;
    }

    public static final int MAX_COUNT_FIELD_NUMBER = 3;
    private int maxCount_;

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @return Whether the maxCount field is set.
     */
    @java.lang.Override
    public boolean hasMaxCount() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @return The maxCount.
     */
    @java.lang.Override
    public int getMaxCount() {
        return maxCount_;
    }

    /**
     * <code>optional int32 max_count = 3;</code>
     *
     * @param value The maxCount to set.
     */
    private void setMaxCount(int value) {
        bitField0_ |= 0x00000001;
        maxCount_ = value;
    }

    /**
     * <code>optional int32 max_count = 3;</code>
     */
    private void clearMaxCount() {
        bitField0_ &= ~0x00000001;
        maxCount_ = 0;
    }

    public static final int MAX_DISTANCE_FIELD_NUMBER = 4;
    private int maxDistance_;

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @return Whether the maxDistance field is set.
     */
    @java.lang.Override
    public boolean hasMaxDistance() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @return The maxDistance.
     */
    @java.lang.Override
    public int getMaxDistance() {
        return maxDistance_;
    }

    /**
     * <code>optional int32 max_distance = 4;</code>
     *
     * @param value The maxDistance to set.
     */
    private void setMaxDistance(int value) {
        bitField0_ |= 0x00000002;
        maxDistance_ = value;
    }

    /**
     * <code>optional int32 max_distance = 4;</code>
     */
    private void clearMaxDistance() {
        bitField0_ &= ~0x00000002;
        maxDistance_ = 0;
    }

    public static final int WITH_COORDINATES_FIELD_NUMBER = 5;
    private boolean withCoordinates_;

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @return Whether the withCoordinates field is set.
     */
    @java.lang.Override
    public boolean hasWithCoordinates() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @return The withCoordinates.
     */
    @java.lang.Override
    public boolean getWithCoordinates() {
        return withCoordinates_;
    }

    /**
     * <code>optional bool with_coordinates = 5;</code>
     *
     * @param value The withCoordinates to set.
     */
    private void setWithCoordinates(boolean value) {
        bitField0_ |= 0x00000004;
        withCoordinates_ = value;
    }

    /**
     * <code>optional bool with_coordinates = 5;</code>
     */
    private void clearWithCoordinates() {
        bitField0_ &= ~0x00000004;
        withCoordinates_ = false;
    }

    public static final int WITH_DISTANCE_FIELD_NUMBER = 6;
    private boolean withDistance_;

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @return Whether the withDistance field is set.
     */
    @java.lang.Override
    public boolean hasWithDistance() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @return The withDistance.
     */
    @java.lang.Override
    public boolean getWithDistance() {
        return withDistance_;
    }

    /**
     * <code>optional bool with_distance = 6;</code>
     *
     * @param value The withDistance to set.
     */
    private void setWithDistance(boolean value) {
        bitField0_ |= 0x00000008;
        withDistance_ = value;
    }

    /**
     * <code>optional bool with_distance = 6;</code>
     */
    private void clearWithDistance() {
        bitField0_ &= ~0x00000008;
        withDistance_ = false;
    }

    public static final int WITH_USER_INFO_FIELD_NUMBER = 7;
    private boolean withUserInfo_;

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @return Whether the withUserInfo field is set.
     */
    @java.lang.Override
    public boolean hasWithUserInfo() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @return The withUserInfo.
     */
    @java.lang.Override
    public boolean getWithUserInfo() {
        return withUserInfo_;
    }

    /**
     * <code>optional bool with_user_info = 7;</code>
     *
     * @param value The withUserInfo to set.
     */
    private void setWithUserInfo(boolean value) {
        bitField0_ |= 0x00000010;
        withUserInfo_ = value;
    }

    /**
     * <code>optional bool with_user_info = 7;</code>
     */
    private void clearWithUserInfo() {
        bitField0_ &= ~0x00000010;
        withUserInfo_ = false;
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest parseFrom(
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
            im.turms.client.model.proto.request.user.QueryNearbyUsersRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryNearbyUsersRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.QueryNearbyUsersRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryNearbyUsersRequest)
            im.turms.client.model.proto.request.user.QueryNearbyUsersRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.QueryNearbyUsersRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>float latitude = 1;</code>
         *
         * @return The latitude.
         */
        @java.lang.Override
        public float getLatitude() {
            return instance.getLatitude();
        }

        /**
         * <code>float latitude = 1;</code>
         *
         * @param value The latitude to set.
         * @return This builder for chaining.
         */
        public Builder setLatitude(float value) {
            copyOnWrite();
            instance.setLatitude(value);
            return this;
        }

        /**
         * <code>float latitude = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLatitude() {
            copyOnWrite();
            instance.clearLatitude();
            return this;
        }

        /**
         * <code>float longitude = 2;</code>
         *
         * @return The longitude.
         */
        @java.lang.Override
        public float getLongitude() {
            return instance.getLongitude();
        }

        /**
         * <code>float longitude = 2;</code>
         *
         * @param value The longitude to set.
         * @return This builder for chaining.
         */
        public Builder setLongitude(float value) {
            copyOnWrite();
            instance.setLongitude(value);
            return this;
        }

        /**
         * <code>float longitude = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLongitude() {
            copyOnWrite();
            instance.clearLongitude();
            return this;
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @return Whether the maxCount field is set.
         */
        @java.lang.Override
        public boolean hasMaxCount() {
            return instance.hasMaxCount();
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @return The maxCount.
         */
        @java.lang.Override
        public int getMaxCount() {
            return instance.getMaxCount();
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @param value The maxCount to set.
         * @return This builder for chaining.
         */
        public Builder setMaxCount(int value) {
            copyOnWrite();
            instance.setMaxCount(value);
            return this;
        }

        /**
         * <code>optional int32 max_count = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxCount() {
            copyOnWrite();
            instance.clearMaxCount();
            return this;
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @return Whether the maxDistance field is set.
         */
        @java.lang.Override
        public boolean hasMaxDistance() {
            return instance.hasMaxDistance();
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @return The maxDistance.
         */
        @java.lang.Override
        public int getMaxDistance() {
            return instance.getMaxDistance();
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @param value The maxDistance to set.
         * @return This builder for chaining.
         */
        public Builder setMaxDistance(int value) {
            copyOnWrite();
            instance.setMaxDistance(value);
            return this;
        }

        /**
         * <code>optional int32 max_distance = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxDistance() {
            copyOnWrite();
            instance.clearMaxDistance();
            return this;
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @return Whether the withCoordinates field is set.
         */
        @java.lang.Override
        public boolean hasWithCoordinates() {
            return instance.hasWithCoordinates();
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @return The withCoordinates.
         */
        @java.lang.Override
        public boolean getWithCoordinates() {
            return instance.getWithCoordinates();
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @param value The withCoordinates to set.
         * @return This builder for chaining.
         */
        public Builder setWithCoordinates(boolean value) {
            copyOnWrite();
            instance.setWithCoordinates(value);
            return this;
        }

        /**
         * <code>optional bool with_coordinates = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithCoordinates() {
            copyOnWrite();
            instance.clearWithCoordinates();
            return this;
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @return Whether the withDistance field is set.
         */
        @java.lang.Override
        public boolean hasWithDistance() {
            return instance.hasWithDistance();
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @return The withDistance.
         */
        @java.lang.Override
        public boolean getWithDistance() {
            return instance.getWithDistance();
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @param value The withDistance to set.
         * @return This builder for chaining.
         */
        public Builder setWithDistance(boolean value) {
            copyOnWrite();
            instance.setWithDistance(value);
            return this;
        }

        /**
         * <code>optional bool with_distance = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithDistance() {
            copyOnWrite();
            instance.clearWithDistance();
            return this;
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @return Whether the withUserInfo field is set.
         */
        @java.lang.Override
        public boolean hasWithUserInfo() {
            return instance.hasWithUserInfo();
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @return The withUserInfo.
         */
        @java.lang.Override
        public boolean getWithUserInfo() {
            return instance.getWithUserInfo();
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @param value The withUserInfo to set.
         * @return This builder for chaining.
         */
        public Builder setWithUserInfo(boolean value) {
            copyOnWrite();
            instance.setWithUserInfo(value);
            return this;
        }

        /**
         * <code>optional bool with_user_info = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithUserInfo() {
            copyOnWrite();
            instance.clearWithUserInfo();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryNearbyUsersRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.QueryNearbyUsersRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "latitude_",
                        "longitude_",
                        "maxCount_",
                        "maxDistance_",
                        "withCoordinates_",
                        "withDistance_",
                        "withUserInfo_",};
                java.lang.String info =
                        "\u0000\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0001\u0002\u0001"
                                + "\u0003\u1004\u0000\u0004\u1004\u0001\u0005\u1007\u0002\u0006\u1007\u0003\u0007\u1007"
                                + "\u0004";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.QueryNearbyUsersRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.QueryNearbyUsersRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryNearbyUsersRequest)
    private static final im.turms.client.model.proto.request.user.QueryNearbyUsersRequest DEFAULT_INSTANCE;

    static {
        QueryNearbyUsersRequest defaultInstance = new QueryNearbyUsersRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryNearbyUsersRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.QueryNearbyUsersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryNearbyUsersRequest> PARSER;

    public static com.google.protobuf.Parser<QueryNearbyUsersRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}