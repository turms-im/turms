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
 * Protobuf type {@code im.turms.proto.UserInfo}
 */
public final class UserInfo
        extends com.google.protobuf.GeneratedMessageLite<UserInfo, UserInfo.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserInfo)
        UserInfoOrBuilder {
    private UserInfo() {
        name_ = "";
        intro_ = "";
        profilePicture_ = "";
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private long id_;

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return Whether the id field is set.
     */
    @java.lang.Override
    public boolean hasId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
        return id_;
    }

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @param value The id to set.
     */
    private void setId(long value) {
        bitField0_ |= 0x00000001;
        id_ = value;
    }

    /**
     * <code>optional int64 id = 1;</code>
     */
    private void clearId() {
        bitField0_ &= ~0x00000001;
        id_ = 0L;
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

    public static final int REGISTRATION_DATE_FIELD_NUMBER = 6;
    private long registrationDate_;

    /**
     * <code>optional int64 registration_date = 6;</code>
     *
     * @return Whether the registrationDate field is set.
     */
    @java.lang.Override
    public boolean hasRegistrationDate() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional int64 registration_date = 6;</code>
     *
     * @return The registrationDate.
     */
    @java.lang.Override
    public long getRegistrationDate() {
        return registrationDate_;
    }

    /**
     * <code>optional int64 registration_date = 6;</code>
     *
     * @param value The registrationDate to set.
     */
    private void setRegistrationDate(long value) {
        bitField0_ |= 0x00000020;
        registrationDate_ = value;
    }

    /**
     * <code>optional int64 registration_date = 6;</code>
     */
    private void clearRegistrationDate() {
        bitField0_ &= ~0x00000020;
        registrationDate_ = 0L;
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 7;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 7;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 7;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    /**
     * <code>optional int64 last_updated_date = 7;</code>
     *
     * @param value The lastUpdatedDate to set.
     */
    private void setLastUpdatedDate(long value) {
        bitField0_ |= 0x00000040;
        lastUpdatedDate_ = value;
    }

    /**
     * <code>optional int64 last_updated_date = 7;</code>
     */
    private void clearLastUpdatedDate() {
        bitField0_ &= ~0x00000040;
        lastUpdatedDate_ = 0L;
    }

    public static final int ACTIVE_FIELD_NUMBER = 8;
    private boolean active_;

    /**
     * <code>optional bool active = 8;</code>
     *
     * @return Whether the active field is set.
     */
    @java.lang.Override
    public boolean hasActive() {
        return ((bitField0_ & 0x00000080) != 0);
    }

    /**
     * <code>optional bool active = 8;</code>
     *
     * @return The active.
     */
    @java.lang.Override
    public boolean getActive() {
        return active_;
    }

    /**
     * <code>optional bool active = 8;</code>
     *
     * @param value The active to set.
     */
    private void setActive(boolean value) {
        bitField0_ |= 0x00000080;
        active_ = value;
    }

    /**
     * <code>optional bool active = 8;</code>
     */
    private void clearActive() {
        bitField0_ &= ~0x00000080;
        active_ = false;
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserInfo parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.user.UserInfo prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserInfo}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.UserInfo, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserInfo)
            im.turms.client.model.proto.model.user.UserInfoOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.UserInfo.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return Whether the id field is set.
         */
        @java.lang.Override
        public boolean hasId() {
            return instance.hasId();
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return The id.
         */
        @java.lang.Override
        public long getId() {
            return instance.getId();
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @param value The id to set.
         * @return This builder for chaining.
         */
        public Builder setId(long value) {
            copyOnWrite();
            instance.setId(value);
            return this;
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            copyOnWrite();
            instance.clearId();
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

        /**
         * <code>optional int64 registration_date = 6;</code>
         *
         * @return Whether the registrationDate field is set.
         */
        @java.lang.Override
        public boolean hasRegistrationDate() {
            return instance.hasRegistrationDate();
        }

        /**
         * <code>optional int64 registration_date = 6;</code>
         *
         * @return The registrationDate.
         */
        @java.lang.Override
        public long getRegistrationDate() {
            return instance.getRegistrationDate();
        }

        /**
         * <code>optional int64 registration_date = 6;</code>
         *
         * @param value The registrationDate to set.
         * @return This builder for chaining.
         */
        public Builder setRegistrationDate(long value) {
            copyOnWrite();
            instance.setRegistrationDate(value);
            return this;
        }

        /**
         * <code>optional int64 registration_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRegistrationDate() {
            copyOnWrite();
            instance.clearRegistrationDate();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 7;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return instance.hasLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 7;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return instance.getLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 7;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {
            copyOnWrite();
            instance.setLastUpdatedDate(value);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            copyOnWrite();
            instance.clearLastUpdatedDate();
            return this;
        }

        /**
         * <code>optional bool active = 8;</code>
         *
         * @return Whether the active field is set.
         */
        @java.lang.Override
        public boolean hasActive() {
            return instance.hasActive();
        }

        /**
         * <code>optional bool active = 8;</code>
         *
         * @return The active.
         */
        @java.lang.Override
        public boolean getActive() {
            return instance.getActive();
        }

        /**
         * <code>optional bool active = 8;</code>
         *
         * @param value The active to set.
         * @return This builder for chaining.
         */
        public Builder setActive(boolean value) {
            copyOnWrite();
            instance.setActive(value);
            return this;
        }

        /**
         * <code>optional bool active = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearActive() {
            copyOnWrite();
            instance.clearActive();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserInfo)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.UserInfo();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "id_",
                        "name_",
                        "intro_",
                        "profilePicture_",
                        "profileAccessStrategy_",
                        "registrationDate_",
                        "lastUpdatedDate_",
                        "active_",};
                java.lang.String info =
                        "\u0000\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001\u1002\u0000\u0002\u1208\u0001"
                                + "\u0003\u1208\u0002\u0004\u1208\u0003\u0005\u100c\u0004\u0006\u1002\u0005\u0007\u1002"
                                + "\u0006\b\u1007\u0007";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.UserInfo> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.UserInfo.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserInfo)
    private static final im.turms.client.model.proto.model.user.UserInfo DEFAULT_INSTANCE;

    static {
        UserInfo defaultInstance = new UserInfo();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UserInfo.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.UserInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserInfo> PARSER;

    public static com.google.protobuf.Parser<UserInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}