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
 * Protobuf type {@code im.turms.proto.UserLocation}
 */
public final class UserLocation extends
        com.google.protobuf.GeneratedMessageLite<UserLocation, UserLocation.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserLocation)
        UserLocationOrBuilder {
    private UserLocation() {
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

    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private long timestamp_;

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return Whether the timestamp field is set.
     */
    @java.lang.Override
    public boolean hasTimestamp() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
        return timestamp_;
    }

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @param value The timestamp to set.
     */
    private void setTimestamp(long value) {
        bitField0_ |= 0x00000001;
        timestamp_ = value;
    }

    /**
     * <code>optional int64 timestamp = 3;</code>
     */
    private void clearTimestamp() {
        bitField0_ &= ~0x00000001;
        timestamp_ = 0L;
    }

    public static final int DETAILS_FIELD_NUMBER = 4;

    private static final class DetailsDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntryLite.<java.lang.String, java.lang.String>newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> details_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> internalGetDetails() {
        return details_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> internalGetMutableDetails() {
        if (!details_.isMutable()) {
            details_ = details_.mutableCopy();
        }
        return details_;
    }

    @java.lang.Override

    public int getDetailsCount() {
        return internalGetDetails().size();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override

    public boolean containsDetails(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        return internalGetDetails().containsKey(key);
    }

    /**
     * Use {@link #getDetailsMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getDetails() {
        return getDetailsMap();
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, java.lang.String> getDetailsMap() {
        return java.util.Collections.unmodifiableMap(internalGetDetails());
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override

    public /* nullable */
    java.lang.String getDetailsOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDetails();
        return map.containsKey(key)
                ? map.get(key)
                : defaultValue;
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    @java.lang.Override

    public java.lang.String getDetailsOrThrow(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, java.lang.String> map = internalGetDetails();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    private java.util.Map<java.lang.String, java.lang.String> getMutableDetailsMap() {
        return internalGetMutableDetails();
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserLocation parseFrom(
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
            im.turms.client.model.proto.model.user.UserLocation prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserLocation}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.UserLocation, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserLocation)
            im.turms.client.model.proto.model.user.UserLocationOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.UserLocation.newBuilder()
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
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return Whether the timestamp field is set.
         */
        @java.lang.Override
        public boolean hasTimestamp() {
            return instance.hasTimestamp();
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return The timestamp.
         */
        @java.lang.Override
        public long getTimestamp() {
            return instance.getTimestamp();
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @param value The timestamp to set.
         * @return This builder for chaining.
         */
        public Builder setTimestamp(long value) {
            copyOnWrite();
            instance.setTimestamp(value);
            return this;
        }

        /**
         * <code>optional int64 timestamp = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTimestamp() {
            copyOnWrite();
            instance.clearTimestamp();
            return this;
        }

        @java.lang.Override

        public int getDetailsCount() {
            return instance.getDetailsMap()
                    .size();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override

        public boolean containsDetails(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            return instance.getDetailsMap()
                    .containsKey(key);
        }

        public Builder clearDetails() {
            copyOnWrite();
            instance.getMutableDetailsMap()
                    .clear();
            return this;
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */

        public Builder removeDetails(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            copyOnWrite();
            instance.getMutableDetailsMap()
                    .remove(key);
            return this;
        }

        /**
         * Use {@link #getDetailsMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getDetails() {
            return getDetailsMap();
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, java.lang.String> getDetailsMap() {
            return java.util.Collections.unmodifiableMap(instance.getDetailsMap());
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override

        public /* nullable */
        java.lang.String getDetailsOrDefault(
                java.lang.String key,
                /* nullable */
                java.lang.String defaultValue) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, java.lang.String> map = instance.getDetailsMap();
            return map.containsKey(key)
                    ? map.get(key)
                    : defaultValue;
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        @java.lang.Override

        public java.lang.String getDetailsOrThrow(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, java.lang.String> map = instance.getDetailsMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        public Builder putDetails(java.lang.String key, java.lang.String value) {
            java.lang.Class<?> keyClass = key.getClass();
            java.lang.Class<?> valueClass = value.getClass();
            copyOnWrite();
            instance.getMutableDetailsMap()
                    .put(key, value);
            return this;
        }

        /**
         * <pre>
         * e.g. street address, city, state, country, etc.
         * </pre>
         * 
         * <code>map&lt;string, string&gt; details = 4;</code>
         */
        public Builder putAllDetails(java.util.Map<java.lang.String, java.lang.String> values) {
            copyOnWrite();
            instance.getMutableDetailsMap()
                    .putAll(values);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserLocation)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.UserLocation();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "latitude_",
                        "longitude_",
                        "timestamp_",
                        "details_",
                        DetailsDefaultEntryHolder.defaultEntry,};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0001\u0000\u0000\u0001\u0001\u0002\u0001"
                                + "\u0003\u1002\u0000\u00042";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.UserLocation> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.UserLocation.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserLocation)
    private static final im.turms.client.model.proto.model.user.UserLocation DEFAULT_INSTANCE;

    static {
        UserLocation defaultInstance = new UserLocation();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UserLocation.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.UserLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserLocation> PARSER;

    public static com.google.protobuf.Parser<UserLocation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}