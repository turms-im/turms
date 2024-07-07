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

package im.turms.server.common.access.client.dto.model.conference;

/**
 * Protobuf type {@code im.turms.proto.Meeting}
 */
public final class Meeting extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Meeting)
        MeetingOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                Meeting.class.getName());
    }

    // Use Meeting.newBuilder() to construct.
    private Meeting(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private Meeting() {
        accessToken_ = "";
        name_ = "";
        intro_ = "";
        password_ = "";
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass.internal_static_im_turms_proto_Meeting_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass.internal_static_im_turms_proto_Meeting_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.conference.Meeting.class,
                        im.turms.server.common.access.client.dto.model.conference.Meeting.Builder.class);
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private long id_ = 0L;

    /**
     * <code>int64 id = 1;</code>
     *
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
        return id_;
    }

    public static final int USER_ID_FIELD_NUMBER = 2;
    private long userId_ = 0L;

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

    public static final int GROUP_ID_FIELD_NUMBER = 3;
    private long groupId_ = 0L;

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

    public static final int CREATOR_ID_FIELD_NUMBER = 4;
    private long creatorId_ = 0L;

    /**
     * <code>int64 creator_id = 4;</code>
     *
     * @return The creatorId.
     */
    @java.lang.Override
    public long getCreatorId() {
        return creatorId_;
    }

    public static final int ACCESS_TOKEN_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object accessToken_ = "";

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
        java.lang.Object ref = accessToken_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            accessToken_ = s;
            return s;
        }
    }

    /**
     * <code>optional string access_token = 5;</code>
     *
     * @return The bytes for accessToken.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAccessTokenBytes() {
        java.lang.Object ref = accessToken_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            accessToken_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int NAME_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

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
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <code>optional string name = 6;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int INTRO_FIELD_NUMBER = 7;
    @SuppressWarnings("serial")
    private volatile java.lang.Object intro_ = "";

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
        java.lang.Object ref = intro_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            intro_ = s;
            return s;
        }
    }

    /**
     * <code>optional string intro = 7;</code>
     *
     * @return The bytes for intro.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIntroBytes() {
        java.lang.Object ref = intro_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            intro_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PASSWORD_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private volatile java.lang.Object password_ = "";

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
     * <code>optional string password = 8;</code>
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

    public static final int START_DATE_FIELD_NUMBER = 9;
    private long startDate_ = 0L;

    /**
     * <code>int64 start_date = 9;</code>
     *
     * @return The startDate.
     */
    @java.lang.Override
    public long getStartDate() {
        return startDate_;
    }

    public static final int END_DATE_FIELD_NUMBER = 10;
    private long endDate_ = 0L;

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

    public static final int CANCEL_DATE_FIELD_NUMBER = 11;
    private long cancelDate_ = 0L;

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

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
            int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
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
        if (id_ != 0L) {
            output.writeInt64(1, id_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, userId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(3, groupId_);
        }
        if (creatorId_ != 0L) {
            output.writeInt64(4, creatorId_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, accessToken_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, name_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 7, intro_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 8, password_);
        }
        if (startDate_ != 0L) {
            output.writeInt64(9, startDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            output.writeInt64(10, endDate_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            output.writeInt64(11, cancelDate_);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            output.writeMessage(15, value);
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
        if (id_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, id_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, userId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, groupId_);
        }
        if (creatorId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, creatorId_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, accessToken_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, name_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(7, intro_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(8, password_);
        }
        if (startDate_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(9, startDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(10, endDate_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(11, cancelDate_);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, value);
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
        if (!(obj instanceof Meeting other)) {
            return super.equals(obj);
        }

        if (getId() != other.getId()) {
            return false;
        }
        if (hasUserId() != other.hasUserId()) {
            return false;
        }
        if (hasUserId()) {
            if (getUserId() != other.getUserId()) {
                return false;
            }
        }
        if (hasGroupId() != other.hasGroupId()) {
            return false;
        }
        if (hasGroupId()) {
            if (getGroupId() != other.getGroupId()) {
                return false;
            }
        }
        if (getCreatorId() != other.getCreatorId()) {
            return false;
        }
        if (hasAccessToken() != other.hasAccessToken()) {
            return false;
        }
        if (hasAccessToken()) {
            if (!getAccessToken().equals(other.getAccessToken())) {
                return false;
            }
        }
        if (hasName() != other.hasName()) {
            return false;
        }
        if (hasName()) {
            if (!getName().equals(other.getName())) {
                return false;
            }
        }
        if (hasIntro() != other.hasIntro()) {
            return false;
        }
        if (hasIntro()) {
            if (!getIntro().equals(other.getIntro())) {
                return false;
            }
        }
        if (hasPassword() != other.hasPassword()) {
            return false;
        }
        if (hasPassword()) {
            if (!getPassword().equals(other.getPassword())) {
                return false;
            }
        }
        if (getStartDate() != other.getStartDate()) {
            return false;
        }
        if (hasEndDate() != other.hasEndDate()) {
            return false;
        }
        if (hasEndDate()) {
            if (getEndDate() != other.getEndDate()) {
                return false;
            }
        }
        if (hasCancelDate() != other.hasCancelDate()) {
            return false;
        }
        if (hasCancelDate()) {
            if (getCancelDate() != other.getCancelDate()) {
                return false;
            }
        }
        return getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getId());
        if (hasUserId()) {
            hash = (37 * hash) + USER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        }
        if (hasGroupId()) {
            hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        }
        hash = (37 * hash) + CREATOR_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCreatorId());
        if (hasAccessToken()) {
            hash = (37 * hash) + ACCESS_TOKEN_FIELD_NUMBER;
            hash = (53 * hash) + getAccessToken().hashCode();
        }
        if (hasName()) {
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
        }
        if (hasIntro()) {
            hash = (37 * hash) + INTRO_FIELD_NUMBER;
            hash = (53 * hash) + getIntro().hashCode();
        }
        if (hasPassword()) {
            hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
            hash = (53 * hash) + getPassword().hashCode();
        }
        hash = (37 * hash) + START_DATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getStartDate());
        if (hasEndDate()) {
            hash = (37 * hash) + END_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEndDate());
        }
        if (hasCancelDate()) {
            hash = (37 * hash) + CANCEL_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCancelDate());
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
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
            im.turms.server.common.access.client.dto.model.conference.Meeting prototype) {
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
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.Meeting}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Meeting)
            im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass.internal_static_im_turms_proto_Meeting_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass.internal_static_im_turms_proto_Meeting_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.conference.Meeting.class,
                            im.turms.server.common.access.client.dto.model.conference.Meeting.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.conference.Meeting.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            id_ = 0L;
            userId_ = 0L;
            groupId_ = 0L;
            creatorId_ = 0L;
            accessToken_ = "";
            name_ = "";
            intro_ = "";
            password_ = "";
            startDate_ = 0L;
            endDate_ = 0L;
            cancelDate_ = 0L;
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000800;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.conference.MeetingOuterClass.internal_static_im_turms_proto_Meeting_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conference.Meeting getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.conference.Meeting
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conference.Meeting build() {
            im.turms.server.common.access.client.dto.model.conference.Meeting result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conference.Meeting buildPartial() {
            im.turms.server.common.access.client.dto.model.conference.Meeting result =
                    new im.turms.server.common.access.client.dto.model.conference.Meeting(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.conference.Meeting result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000800) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000800;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.conference.Meeting result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.id_ = id_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.userId_ = userId_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.groupId_ = groupId_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.creatorId_ = creatorId_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.accessToken_ = accessToken_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.name_ = name_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.intro_ = intro_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.password_ = password_;
                to_bitField0_ |= 0x00000020;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.startDate_ = startDate_;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.endDate_ = endDate_;
                to_bitField0_ |= 0x00000040;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.cancelDate_ = cancelDate_;
                to_bitField0_ |= 0x00000080;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.conference.Meeting) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.conference.Meeting) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.conference.Meeting other) {
            if (other == im.turms.server.common.access.client.dto.model.conference.Meeting
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getId() != 0L) {
                setId(other.getId());
            }
            if (other.hasUserId()) {
                setUserId(other.getUserId());
            }
            if (other.hasGroupId()) {
                setGroupId(other.getGroupId());
            }
            if (other.getCreatorId() != 0L) {
                setCreatorId(other.getCreatorId());
            }
            if (other.hasAccessToken()) {
                accessToken_ = other.accessToken_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (other.hasName()) {
                name_ = other.name_;
                bitField0_ |= 0x00000020;
                onChanged();
            }
            if (other.hasIntro()) {
                intro_ = other.intro_;
                bitField0_ |= 0x00000040;
                onChanged();
            }
            if (other.hasPassword()) {
                password_ = other.password_;
                bitField0_ |= 0x00000080;
                onChanged();
            }
            if (other.getStartDate() != 0L) {
                setStartDate(other.getStartDate());
            }
            if (other.hasEndDate()) {
                setEndDate(other.getEndDate());
            }
            if (other.hasCancelDate()) {
                setCancelDate(other.getCancelDate());
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000800;
                    } else {
                        ensureCustomAttributesIsMutable();
                        customAttributes_.addAll(other.customAttributes_);
                    }
                    onChanged();
                }
            } else {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributesBuilder_.isEmpty()) {
                        customAttributesBuilder_.dispose();
                        customAttributesBuilder_ = null;
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000800;
                        customAttributesBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getCustomAttributesFieldBuilder()
                                        : null;
                    } else {
                        customAttributesBuilder_.addAllMessages(other.customAttributes_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
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
                            id_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            creatorId_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 42 -> {
                            accessToken_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 50
                        case 58 -> {
                            intro_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000040;
                        } // case 58
                        case 66 -> {
                            password_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000080;
                        } // case 66
                        case 72 -> {
                            startDate_ = input.readInt64();
                            bitField0_ |= 0x00000100;
                        } // case 72
                        case 80 -> {
                            endDate_ = input.readInt64();
                            bitField0_ |= 0x00000200;
                        } // case 80
                        case 88 -> {
                            cancelDate_ = input.readInt64();
                            bitField0_ |= 0x00000400;
                        } // case 88
                        case 122 -> {
                            im.turms.server.common.access.client.dto.model.common.Value m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.common.Value
                                                    .parser(),
                                            extensionRegistry);
                            if (customAttributesBuilder_ == null) {
                                ensureCustomAttributesIsMutable();
                                customAttributes_.add(m);
                            } else {
                                customAttributesBuilder_.addMessage(m);
                            }
                        } // case 122
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
         * @return This builder for chaining.
         */
        public Builder setId(long value) {

            id_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            bitField0_ &= ~0x00000001;
            id_ = 0L;
            onChanged();
            return this;
        }

        private long userId_;

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return Whether the userId field is set.
         */
        @java.lang.Override
        public boolean hasUserId() {
            return ((bitField0_ & 0x00000002) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setUserId(long value) {

            userId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            bitField0_ &= ~0x00000002;
            userId_ = 0L;
            onChanged();
            return this;
        }

        private long groupId_;

        /**
         * <code>optional int64 group_id = 3;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return ((bitField0_ & 0x00000004) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_id = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000004;
            groupId_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setCreatorId(long value) {

            creatorId_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>int64 creator_id = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreatorId() {
            bitField0_ &= ~0x00000008;
            creatorId_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object accessToken_ = "";

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return Whether the accessToken field is set.
         */
        public boolean hasAccessToken() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return The accessToken.
         */
        public java.lang.String getAccessToken() {
            java.lang.Object ref = accessToken_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                accessToken_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return The bytes for accessToken.
         */
        public com.google.protobuf.ByteString getAccessTokenBytes() {
            java.lang.Object ref = accessToken_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                accessToken_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @param value The accessToken to set.
         * @return This builder for chaining.
         */
        public Builder setAccessToken(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            accessToken_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAccessToken() {
            accessToken_ = getDefaultInstance().getAccessToken();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional string access_token = 5;</code>
         *
         * @param value The bytes for accessToken to set.
         * @return This builder for chaining.
         */
        public Builder setAccessTokenBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            accessToken_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>optional string name = 6;</code>
         *
         * @return Whether the name field is set.
         */
        public boolean hasName() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 6;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        private java.lang.Object intro_ = "";

        /**
         * <code>optional string intro = 7;</code>
         *
         * @return Whether the intro field is set.
         */
        public boolean hasIntro() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @return The intro.
         */
        public java.lang.String getIntro() {
            java.lang.Object ref = intro_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                intro_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @return The bytes for intro.
         */
        public com.google.protobuf.ByteString getIntroBytes() {
            java.lang.Object ref = intro_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                intro_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @param value The intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntro(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            intro_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            intro_ = getDefaultInstance().getIntro();
            bitField0_ &= ~0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional string intro = 7;</code>
         *
         * @param value The bytes for intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntroBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            intro_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        private java.lang.Object password_ = "";

        /**
         * <code>optional string password = 8;</code>
         *
         * @return Whether the password field is set.
         */
        public boolean hasPassword() {
            return ((bitField0_ & 0x00000080) != 0);
        }

        /**
         * <code>optional string password = 8;</code>
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
         * <code>optional string password = 8;</code>
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
         * <code>optional string password = 8;</code>
         *
         * @param value The password to set.
         * @return This builder for chaining.
         */
        public Builder setPassword(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            password_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>optional string password = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearPassword() {
            password_ = getDefaultInstance().getPassword();
            bitField0_ &= ~0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>optional string password = 8;</code>
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
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setStartDate(long value) {

            startDate_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>int64 start_date = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStartDate() {
            bitField0_ &= ~0x00000100;
            startDate_ = 0L;
            onChanged();
            return this;
        }

        private long endDate_;

        /**
         * <code>optional int64 end_date = 10;</code>
         *
         * @return Whether the endDate field is set.
         */
        @java.lang.Override
        public boolean hasEndDate() {
            return ((bitField0_ & 0x00000200) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setEndDate(long value) {

            endDate_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 end_date = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEndDate() {
            bitField0_ &= ~0x00000200;
            endDate_ = 0L;
            onChanged();
            return this;
        }

        private long cancelDate_;

        /**
         * <code>optional int64 cancel_date = 11;</code>
         *
         * @return Whether the cancelDate field is set.
         */
        @java.lang.Override
        public boolean hasCancelDate() {
            return ((bitField0_ & 0x00000400) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCancelDate(long value) {

            cancelDate_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 cancel_date = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCancelDate() {
            bitField0_ &= ~0x00000400;
            cancelDate_ = 0L;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000800) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000800;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(customAttributes_);
            } else {
                return customAttributesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public int getCustomAttributesCount() {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.size();
            } else {
                return customAttributesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, customAttributes_);
                onChanged();
            } else {
                customAttributesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000800;
                onChanged();
            } else {
                customAttributesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.remove(index);
                onChanged();
            } else {
                customAttributesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        customAttributes_,
                        ((bitField0_ & 0x00000800) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Meeting)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Meeting)
    private static final im.turms.server.common.access.client.dto.model.conference.Meeting DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.conference.Meeting();
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meeting getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Meeting> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Meeting parsePartialFrom(
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

    public static com.google.protobuf.Parser<Meeting> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Meeting> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conference.Meeting getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}