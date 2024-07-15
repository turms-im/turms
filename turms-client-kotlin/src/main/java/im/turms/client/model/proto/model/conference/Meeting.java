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

package im.turms.client.model.proto.model.conference;

/**
 * Protobuf type {@code im.turms.proto.Meeting}
 */
public final class Meeting
        extends com.google.protobuf.GeneratedMessageLite<Meeting, Meeting.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Meeting)
        MeetingOrBuilder {
    private Meeting() {
        accessToken_ = "";
        name_ = "";
        intro_ = "";
        password_ = "";
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private long id_;

    /**
     * <code>int64 id = 1;</code>
     *
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
        return id_;
    }

    /**
     * <code>int64 id = 1;</code>
     *
     * @param value The id to set.
     */
    private void setId(long value) {

        id_ = value;
    }

    /**
     * <code>int64 id = 1;</code>
     */
    private void clearId() {

        id_ = 0L;
    }

    public static final int USER_ID_FIELD_NUMBER = 2;
    private long userId_;

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @return Whether the userId field is set.
     */
    @java.lang.Override
    public boolean hasUserId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    /**
     * <code>optional int64 user_id = 2;</code>
     *
     * @param value The userId to set.
     */
    private void setUserId(long value) {
        bitField0_ |= 0x00000001;
        userId_ = value;
    }

    /**
     * <code>optional int64 user_id = 2;</code>
     */
    private void clearUserId() {
        bitField0_ &= ~0x00000001;
        userId_ = 0L;
    }

    public static final int GROUP_ID_FIELD_NUMBER = 3;
    private long groupId_;

    /**
     * <code>optional int64 group_id = 3;</code>
     *
     * @return Whether the groupId field is set.
     */
    @java.lang.Override
    public boolean hasGroupId() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 group_id = 3;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    /**
     * <code>optional int64 group_id = 3;</code>
     *
     * @param value The groupId to set.
     */
    private void setGroupId(long value) {
        bitField0_ |= 0x00000002;
        groupId_ = value;
    }

    /**
     * <code>optional int64 group_id = 3;</code>
     */
    private void clearGroupId() {
        bitField0_ &= ~0x00000002;
        groupId_ = 0L;
    }

    public static final int CREATOR_ID_FIELD_NUMBER = 4;
    private long creatorId_;

    /**
     * <code>int64 creator_id = 4;</code>
     *
     * @return The creatorId.
     */
    @java.lang.Override
    public long getCreatorId() {
        return creatorId_;
    }

    /**
     * <code>int64 creator_id = 4;</code>
     *
     * @param value The creatorId to set.
     */
    private void setCreatorId(long value) {

        creatorId_ = value;
    }

    /**
     * <code>int64 creator_id = 4;</code>
     */
    private void clearCreatorId() {

        creatorId_ = 0L;
    }

    public static final int ACCESS_TOKEN_FIELD_NUMBER = 5;
    private java.lang.String accessToken_;

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return Whether the accessToken field is set.
     */
    @java.lang.Override
    public boolean hasAccessToken() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return The accessToken.
     */
    @java.lang.Override
    public java.lang.String getAccessToken() {
        return accessToken_;
    }

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return The bytes for accessToken.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccessTokenBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(accessToken_);
    }

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @param value The accessToken to set.
     */
    private void setAccessToken(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000004;
        accessToken_ = value;
    }

    /**
     * <code>optional string access_token = 5;</code>
     */
    private void clearAccessToken() {
        bitField0_ &= ~0x00000004;
        accessToken_ = getDefaultInstance().getAccessToken();
    }

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @param value The bytes for accessToken to set.
     */
    private void setAccessTokenBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        accessToken_ = value.toStringUtf8();
        bitField0_ |= 0x00000004;
    }

    public static final int NAME_FIELD_NUMBER = 6;
    private java.lang.String name_;

    /**
     * <code>optional string name = 6;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional string name = 6;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <code>optional string name = 6;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <code>optional string name = 6;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000008;
        name_ = value;
    }

    /**
     * <code>optional string name = 6;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000008;
        name_ = getDefaultInstance().getName();
    }

    /**
     * <code>optional string name = 6;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();
        bitField0_ |= 0x00000008;
    }

    public static final int INTRO_FIELD_NUMBER = 7;
    private java.lang.String intro_;

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return Whether the intro field is set.
     */
    @java.lang.Override
    public boolean hasIntro() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return The intro.
     */
    @java.lang.Override
    public java.lang.String getIntro() {
        return intro_;
    }

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return The bytes for intro.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIntroBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(intro_);
    }

    /**
     * <code>optional string intro = 7;</code>
     *
     * @param value The intro to set.
     */
    private void setIntro(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000010;
        intro_ = value;
    }

    /**
     * <code>optional string intro = 7;</code>
     */
    private void clearIntro() {
        bitField0_ &= ~0x00000010;
        intro_ = getDefaultInstance().getIntro();
    }

    /**
     * <code>optional string intro = 7;</code>
     *
     * @param value The bytes for intro to set.
     */
    private void setIntroBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        intro_ = value.toStringUtf8();
        bitField0_ |= 0x00000010;
    }

    public static final int PASSWORD_FIELD_NUMBER = 8;
    private java.lang.String password_;

    /**
     * <code>optional string password = 8;</code>
     *
     * @return Whether the password field is set.
     */
    @java.lang.Override
    public boolean hasPassword() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional string password = 8;</code>
     *
     * @return The password.
     */
    @java.lang.Override
    public java.lang.String getPassword() {
        return password_;
    }

    /**
     * <code>optional string password = 8;</code>
     *
     * @return The bytes for password.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPasswordBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(password_);
    }

    /**
     * <code>optional string password = 8;</code>
     *
     * @param value The password to set.
     */
    private void setPassword(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000020;
        password_ = value;
    }

    /**
     * <code>optional string password = 8;</code>
     */
    private void clearPassword() {
        bitField0_ &= ~0x00000020;
        password_ = getDefaultInstance().getPassword();
    }

    /**
     * <code>optional string password = 8;</code>
     *
     * @param value The bytes for password to set.
     */
    private void setPasswordBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        password_ = value.toStringUtf8();
        bitField0_ |= 0x00000020;
    }

    public static final int START_DATE_FIELD_NUMBER = 9;
    private long startDate_;

    /**
     * <code>int64 start_date = 9;</code>
     *
     * @return The startDate.
     */
    @java.lang.Override
    public long getStartDate() {
        return startDate_;
    }

    /**
     * <code>int64 start_date = 9;</code>
     *
     * @param value The startDate to set.
     */
    private void setStartDate(long value) {

        startDate_ = value;
    }

    /**
     * <code>int64 start_date = 9;</code>
     */
    private void clearStartDate() {

        startDate_ = 0L;
    }

    public static final int END_DATE_FIELD_NUMBER = 10;
    private long endDate_;

    /**
     * <code>optional int64 end_date = 10;</code>
     *
     * @return Whether the endDate field is set.
     */
    @java.lang.Override
    public boolean hasEndDate() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>optional int64 end_date = 10;</code>
     *
     * @return The endDate.
     */
    @java.lang.Override
    public long getEndDate() {
        return endDate_;
    }

    /**
     * <code>optional int64 end_date = 10;</code>
     *
     * @param value The endDate to set.
     */
    private void setEndDate(long value) {
        bitField0_ |= 0x00000040;
        endDate_ = value;
    }

    /**
     * <code>optional int64 end_date = 10;</code>
     */
    private void clearEndDate() {
        bitField0_ &= ~0x00000040;
        endDate_ = 0L;
    }

    public static final int CANCEL_DATE_FIELD_NUMBER = 11;
    private long cancelDate_;

    /**
     * <code>optional int64 cancel_date = 11;</code>
     *
     * @return Whether the cancelDate field is set.
     */
    @java.lang.Override
    public boolean hasCancelDate() {
        return ((bitField0_ & 0x00000080) != 0);
    }

    /**
     * <code>optional int64 cancel_date = 11;</code>
     *
     * @return The cancelDate.
     */
    @java.lang.Override
    public long getCancelDate() {
        return cancelDate_;
    }

    /**
     * <code>optional int64 cancel_date = 11;</code>
     *
     * @param value The cancelDate to set.
     */
    private void setCancelDate(long value) {
        bitField0_ |= 0x00000080;
        cancelDate_ = value;
    }

    /**
     * <code>optional int64 cancel_date = 11;</code>
     */
    private void clearCancelDate() {
        bitField0_ &= ~0x00000080;
        cancelDate_ = 0L;
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

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conference.Meeting parseFrom(
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
            im.turms.client.model.proto.model.conference.Meeting prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.Meeting}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.conference.Meeting, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Meeting)
            im.turms.client.model.proto.model.conference.MeetingOrBuilder {
        // Construct using im.turms.client.model.proto.model.conference.Meeting.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 id = 1;</code>
         *
         * @return The id.
         */
        @java.lang.Override
        public long getId() {
            return instance.getId();
        }

        /**
         * <code>int64 id = 1;</code>
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
         * <code>int64 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            copyOnWrite();
            instance.clearId();
            return this;
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return Whether the userId field is set.
         */
        @java.lang.Override
        public boolean hasUserId() {
            return instance.hasUserId();
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return instance.getUserId();
        }

        /**
         * <code>optional int64 user_id = 2;</code>
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
         * <code>optional int64 user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            copyOnWrite();
            instance.clearUserId();
            return this;
        }

        /**
         * <code>optional int64 group_id = 3;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return instance.hasGroupId();
        }

        /**
         * <code>optional int64 group_id = 3;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <code>optional int64 group_id = 3;</code>
         *
         * @param value The groupId to set.
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {
            copyOnWrite();
            instance.setGroupId(value);
            return this;
        }

        /**
         * <code>optional int64 group_id = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            copyOnWrite();
            instance.clearGroupId();
            return this;
        }

        /**
         * <code>int64 creator_id = 4;</code>
         *
         * @return The creatorId.
         */
        @java.lang.Override
        public long getCreatorId() {
            return instance.getCreatorId();
        }

        /**
         * <code>int64 creator_id = 4;</code>
         *
         * @param value The creatorId to set.
         * @return This builder for chaining.
         */
        public Builder setCreatorId(long value) {
            copyOnWrite();
            instance.setCreatorId(value);
            return this;
        }

        /**
         * <code>int64 creator_id = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreatorId() {
            copyOnWrite();
            instance.clearCreatorId();
            return this;
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return Whether the accessToken field is set.
         */
        @java.lang.Override
        public boolean hasAccessToken() {
            return instance.hasAccessToken();
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return The accessToken.
         */
        @java.lang.Override
        public java.lang.String getAccessToken() {
            return instance.getAccessToken();
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return The bytes for accessToken.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getAccessTokenBytes() {
            return instance.getAccessTokenBytes();
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @param value The accessToken to set.
         * @return This builder for chaining.
         */
        public Builder setAccessToken(java.lang.String value) {
            copyOnWrite();
            instance.setAccessToken(value);
            return this;
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAccessToken() {
            copyOnWrite();
            instance.clearAccessToken();
            return this;
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @param value The bytes for accessToken to set.
         * @return This builder for chaining.
         */
        public Builder setAccessTokenBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setAccessTokenBytes(value);
            return this;
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @return Whether the name field is set.
         */
        @java.lang.Override
        public boolean hasName() {
            return instance.hasName();
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <code>optional string name = 6;</code>
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
         * <code>optional string name = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <code>optional string name = 6;</code>
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
         * <code>optional string intro = 7;</code>
         *
         * @return Whether the intro field is set.
         */
        @java.lang.Override
        public boolean hasIntro() {
            return instance.hasIntro();
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @return The intro.
         */
        @java.lang.Override
        public java.lang.String getIntro() {
            return instance.getIntro();
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @return The bytes for intro.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getIntroBytes() {
            return instance.getIntroBytes();
        }

        /**
         * <code>optional string intro = 7;</code>
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
         * <code>optional string intro = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            copyOnWrite();
            instance.clearIntro();
            return this;
        }

        /**
         * <code>optional string intro = 7;</code>
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
         * <code>optional string password = 8;</code>
         *
         * @return Whether the password field is set.
         */
        @java.lang.Override
        public boolean hasPassword() {
            return instance.hasPassword();
        }

        /**
         * <code>optional string password = 8;</code>
         *
         * @return The password.
         */
        @java.lang.Override
        public java.lang.String getPassword() {
            return instance.getPassword();
        }

        /**
         * <code>optional string password = 8;</code>
         *
         * @return The bytes for password.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getPasswordBytes() {
            return instance.getPasswordBytes();
        }

        /**
         * <code>optional string password = 8;</code>
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
         * <code>optional string password = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            copyOnWrite();
            instance.clearPassword();
            return this;
        }

        /**
         * <code>optional string password = 8;</code>
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
         * <code>int64 start_date = 9;</code>
         *
         * @return The startDate.
         */
        @java.lang.Override
        public long getStartDate() {
            return instance.getStartDate();
        }

        /**
         * <code>int64 start_date = 9;</code>
         *
         * @param value The startDate to set.
         * @return This builder for chaining.
         */
        public Builder setStartDate(long value) {
            copyOnWrite();
            instance.setStartDate(value);
            return this;
        }

        /**
         * <code>int64 start_date = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartDate() {
            copyOnWrite();
            instance.clearStartDate();
            return this;
        }

        /**
         * <code>optional int64 end_date = 10;</code>
         *
         * @return Whether the endDate field is set.
         */
        @java.lang.Override
        public boolean hasEndDate() {
            return instance.hasEndDate();
        }

        /**
         * <code>optional int64 end_date = 10;</code>
         *
         * @return The endDate.
         */
        @java.lang.Override
        public long getEndDate() {
            return instance.getEndDate();
        }

        /**
         * <code>optional int64 end_date = 10;</code>
         *
         * @param value The endDate to set.
         * @return This builder for chaining.
         */
        public Builder setEndDate(long value) {
            copyOnWrite();
            instance.setEndDate(value);
            return this;
        }

        /**
         * <code>optional int64 end_date = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndDate() {
            copyOnWrite();
            instance.clearEndDate();
            return this;
        }

        /**
         * <code>optional int64 cancel_date = 11;</code>
         *
         * @return Whether the cancelDate field is set.
         */
        @java.lang.Override
        public boolean hasCancelDate() {
            return instance.hasCancelDate();
        }

        /**
         * <code>optional int64 cancel_date = 11;</code>
         *
         * @return The cancelDate.
         */
        @java.lang.Override
        public long getCancelDate() {
            return instance.getCancelDate();
        }

        /**
         * <code>optional int64 cancel_date = 11;</code>
         *
         * @param value The cancelDate to set.
         * @return This builder for chaining.
         */
        public Builder setCancelDate(long value) {
            copyOnWrite();
            instance.setCancelDate(value);
            return this;
        }

        /**
         * <code>optional int64 cancel_date = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCancelDate() {
            copyOnWrite();
            instance.clearCancelDate();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Meeting)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.conference.Meeting();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "id_",
                        "userId_",
                        "groupId_",
                        "creatorId_",
                        "accessToken_",
                        "name_",
                        "intro_",
                        "password_",
                        "startDate_",
                        "endDate_",
                        "cancelDate_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\f\u0000\u0001\u0001\u000f\f\u0000\u0001\u0000\u0001\u0002\u0002\u1002\u0000"
                                + "\u0003\u1002\u0001\u0004\u0002\u0005\u1208\u0002\u0006\u1208\u0003\u0007\u1208\u0004"
                                + "\b\u1208\u0005\t\u0002\n\u1002\u0006\u000b\u1002\u0007\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.conference.Meeting> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.conference.Meeting.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.Meeting)
    private static final im.turms.client.model.proto.model.conference.Meeting DEFAULT_INSTANCE;
    static {
        Meeting defaultInstance = new Meeting();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Meeting.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.conference.Meeting getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Meeting> PARSER;

    public static com.google.protobuf.Parser<Meeting> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}