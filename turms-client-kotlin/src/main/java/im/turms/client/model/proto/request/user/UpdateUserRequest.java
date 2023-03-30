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
 * Protobuf type {@code im.turms.proto.UpdateUserRequest}
 */
public final class UpdateUserRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateUserRequest, UpdateUserRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateUserRequest)
        UpdateUserRequestOrBuilder {
    private UpdateUserRequest() {
        password_ = "";
        name_ = "";
        intro_ = "";
        profilePicture_ = "";
    }

    private int bitField0_;
    public static final int PASSWORD_FIELD_NUMBER = 1;
    private java.lang.String password_;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string password = 1;</code>
     *
     * @return Whether the password field is set.
     */
    @java.lang.Override
    public boolean hasPassword() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string password = 1;</code>
     *
     * @return The password.
     */
    @java.lang.Override
    public java.lang.String getPassword() {
        return password_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string password = 1;</code>
     *
     * @return The bytes for password.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPasswordBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(password_);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string password = 1;</code>
     *
     * @param value The password to set.
     */
    private void setPassword(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        password_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string password = 1;</code>
     */
    private void clearPassword() {
        bitField0_ &= ~0x00000001;
        password_ = getDefaultInstance().getPassword();
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string password = 1;</code>
     *
     * @param value The bytes for password to set.
     */
    private void setPasswordBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        password_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int NAME_FIELD_NUMBER = 2;
    private java.lang.String name_;

    /**
     * <code>optional string name = 2;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string name = 2;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <code>optional string name = 2;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <code>optional string name = 2;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        name_ = value;
    }

    /**
     * <code>optional string name = 2;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000002;
        name_ = getDefaultInstance().getName();
    }

    /**
     * <code>optional string name = 2;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();
        bitField0_ |= 0x00000002;
    }

    public static final int INTRO_FIELD_NUMBER = 3;
    private java.lang.String intro_;

    /**
     * <code>optional string intro = 3;</code>
     *
     * @return Whether the intro field is set.
     */
    @java.lang.Override
    public boolean hasIntro() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional string intro = 3;</code>
     *
     * @return The intro.
     */
    @java.lang.Override
    public java.lang.String getIntro() {
        return intro_;
    }

    /**
     * <code>optional string intro = 3;</code>
     *
     * @return The bytes for intro.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIntroBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(intro_);
    }

    /**
     * <code>optional string intro = 3;</code>
     *
     * @param value The intro to set.
     */
    private void setIntro(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000004;
        intro_ = value;
    }

    /**
     * <code>optional string intro = 3;</code>
     */
    private void clearIntro() {
        bitField0_ &= ~0x00000004;
        intro_ = getDefaultInstance().getIntro();
    }

    /**
     * <code>optional string intro = 3;</code>
     *
     * @param value The bytes for intro to set.
     */
    private void setIntroBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        intro_ = value.toStringUtf8();
        bitField0_ |= 0x00000004;
    }

    public static final int PROFILE_PICTURE_FIELD_NUMBER = 4;
    private java.lang.String profilePicture_;

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return Whether the profilePicture field is set.
     */
    @java.lang.Override
    public boolean hasProfilePicture() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return The profilePicture.
     */
    @java.lang.Override
    public java.lang.String getProfilePicture() {
        return profilePicture_;
    }

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return The bytes for profilePicture.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getProfilePictureBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(profilePicture_);
    }

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @param value The profilePicture to set.
     */
    private void setProfilePicture(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000008;
        profilePicture_ = value;
    }

    /**
     * <code>optional string profile_picture = 4;</code>
     */
    private void clearProfilePicture() {
        bitField0_ &= ~0x00000008;
        profilePicture_ = getDefaultInstance().getProfilePicture();
    }

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @param value The bytes for profilePicture to set.
     */
    private void setProfilePictureBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        profilePicture_ = value.toStringUtf8();
        bitField0_ |= 0x00000008;
    }

    public static final int PROFILE_ACCESS_STRATEGY_FIELD_NUMBER = 5;
    private int profileAccessStrategy_;

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @return Whether the profileAccessStrategy field is set.
     */
    @java.lang.Override
    public boolean hasProfileAccessStrategy() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @return The enum numeric value on the wire for profileAccessStrategy.
     */
    @java.lang.Override
    public int getProfileAccessStrategyValue() {
        return profileAccessStrategy_;
    }

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @return The profileAccessStrategy.
     */
    @java.lang.Override
    public im.turms.client.model.proto.constant.ProfileAccessStrategy getProfileAccessStrategy() {
        im.turms.client.model.proto.constant.ProfileAccessStrategy result =
                im.turms.client.model.proto.constant.ProfileAccessStrategy
                        .forNumber(profileAccessStrategy_);
        return result == null
                ? im.turms.client.model.proto.constant.ProfileAccessStrategy.UNRECOGNIZED
                : result;
    }

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @param value The enum numeric value on the wire for profileAccessStrategy to set.
     */
    private void setProfileAccessStrategyValue(int value) {
        bitField0_ |= 0x00000010;
        profileAccessStrategy_ = value;
    }

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     *
     * @param value The profileAccessStrategy to set.
     */
    private void setProfileAccessStrategy(
            im.turms.client.model.proto.constant.ProfileAccessStrategy value) {
        profileAccessStrategy_ = value.getNumber();
        bitField0_ |= 0x00000010;
    }

    /**
     * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
     */
    private void clearProfileAccessStrategy() {
        bitField0_ &= ~0x00000010;
        profileAccessStrategy_ = 0;
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest parseFrom(
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
            im.turms.client.model.proto.request.user.UpdateUserRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateUserRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.UpdateUserRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateUserRequest)
            im.turms.client.model.proto.request.user.UpdateUserRequestOrBuilder {
        // Construct using im.turms.client.model.proto.request.user.UpdateUserRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string password = 1;</code>
         *
         * @return Whether the password field is set.
         */
        @java.lang.Override
        public boolean hasPassword() {
            return instance.hasPassword();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string password = 1;</code>
         *
         * @return The password.
         */
        @java.lang.Override
        public java.lang.String getPassword() {
            return instance.getPassword();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string password = 1;</code>
         *
         * @return The bytes for password.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getPasswordBytes() {
            return instance.getPasswordBytes();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string password = 1;</code>
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
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string password = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            copyOnWrite();
            instance.clearPassword();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string password = 1;</code>
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
         * <code>optional string name = 2;</code>
         *
         * @return Whether the name field is set.
         */
        @java.lang.Override
        public boolean hasName() {
            return instance.hasName();
        }

        /**
         * <code>optional string name = 2;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <code>optional string name = 2;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <code>optional string name = 2;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            copyOnWrite();
            instance.setName(value);
            return this;
        }

        /**
         * <code>optional string name = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <code>optional string name = 2;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setNameBytes(value);
            return this;
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @return Whether the intro field is set.
         */
        @java.lang.Override
        public boolean hasIntro() {
            return instance.hasIntro();
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @return The intro.
         */
        @java.lang.Override
        public java.lang.String getIntro() {
            return instance.getIntro();
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @return The bytes for intro.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getIntroBytes() {
            return instance.getIntroBytes();
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @param value The intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntro(java.lang.String value) {
            copyOnWrite();
            instance.setIntro(value);
            return this;
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            copyOnWrite();
            instance.clearIntro();
            return this;
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @param value The bytes for intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntroBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setIntroBytes(value);
            return this;
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return Whether the profilePicture field is set.
         */
        @java.lang.Override
        public boolean hasProfilePicture() {
            return instance.hasProfilePicture();
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return The profilePicture.
         */
        @java.lang.Override
        public java.lang.String getProfilePicture() {
            return instance.getProfilePicture();
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return The bytes for profilePicture.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getProfilePictureBytes() {
            return instance.getProfilePictureBytes();
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @param value The profilePicture to set.
         * @return This builder for chaining.
         */
        public Builder setProfilePicture(java.lang.String value) {
            copyOnWrite();
            instance.setProfilePicture(value);
            return this;
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProfilePicture() {
            copyOnWrite();
            instance.clearProfilePicture();
            return this;
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @param value The bytes for profilePicture to set.
         * @return This builder for chaining.
         */
        public Builder setProfilePictureBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setProfilePictureBytes(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @return Whether the profileAccessStrategy field is set.
         */
        @java.lang.Override
        public boolean hasProfileAccessStrategy() {
            return instance.hasProfileAccessStrategy();
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @return The enum numeric value on the wire for profileAccessStrategy.
         */
        @java.lang.Override
        public int getProfileAccessStrategyValue() {
            return instance.getProfileAccessStrategyValue();
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @param value The profileAccessStrategy to set.
         * @return This builder for chaining.
         */
        public Builder setProfileAccessStrategyValue(int value) {
            copyOnWrite();
            instance.setProfileAccessStrategyValue(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @return The profileAccessStrategy.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.ProfileAccessStrategy getProfileAccessStrategy() {
            return instance.getProfileAccessStrategy();
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @param value The enum numeric value on the wire for profileAccessStrategy to set.
         * @return This builder for chaining.
         */
        public Builder setProfileAccessStrategy(
                im.turms.client.model.proto.constant.ProfileAccessStrategy value) {
            copyOnWrite();
            instance.setProfileAccessStrategy(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProfileAccessStrategy() {
            copyOnWrite();
            instance.clearProfileAccessStrategy();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateUserRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.UpdateUserRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "password_",
                        "name_",
                        "intro_",
                        "profilePicture_",
                        "profileAccessStrategy_",};
                java.lang.String info =
                        "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u1208\u0000\u0002"
                                + "\u1208\u0001\u0003\u1208\u0002\u0004\u1208\u0003\u0005\u100c\u0004";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.UpdateUserRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.UpdateUserRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateUserRequest)
    private static final im.turms.client.model.proto.request.user.UpdateUserRequest DEFAULT_INSTANCE;

    static {
        UpdateUserRequest defaultInstance = new UpdateUserRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UpdateUserRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.UpdateUserRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateUserRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateUserRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}