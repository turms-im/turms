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

package im.turms.server.common.access.client.dto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserInfo}
 */
public final class UserInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserInfo)
        UserInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserInfo.class.getName());
    }

    // Use UserInfo.newBuilder() to construct.
    private UserInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UserInfo() {
        name_ = "";
        intro_ = "";
        profilePicture_ = "";
        profileAccessStrategy_ = 0;
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.internal_static_im_turms_proto_UserInfo_descriptor;
    }

    @SuppressWarnings("rawtypes")
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        return switch (number) {
            case 9 -> internalGetUserDefinedAttributes();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.internal_static_im_turms_proto_UserInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserInfo.class,
                        im.turms.server.common.access.client.dto.model.user.UserInfo.Builder.class);
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

    public static final int NAME_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

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
     * <code>optional string name = 2;</code>
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

    public static final int INTRO_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object intro_ = "";

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
     * <code>optional string intro = 3;</code>
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

    public static final int PROFILE_PICTURE_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object profilePicture_ = "";

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
        java.lang.Object ref = profilePicture_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            profilePicture_ = s;
            return s;
        }
    }

    /**
     * <code>optional string profile_picture = 4;</code>
     *
     * @return The bytes for profilePicture.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getProfilePictureBytes() {
        java.lang.Object ref = profilePicture_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            profilePicture_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
    public im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy getProfileAccessStrategy() {
        im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy result =
                im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy
                        .forNumber(profileAccessStrategy_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy.UNRECOGNIZED
                : result;
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

    public static final int USER_DEFINED_ATTRIBUTES_FIELD_NUMBER = 9;

    private static final class UserDefinedAttributesDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.internal_static_im_turms_proto_UserInfo_UserDefinedAttributesEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.MESSAGE,
                        im.turms.server.common.access.client.dto.model.common.Value
                                .getDefaultInstance());
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> userDefinedAttributes_;

    private com.google.protobuf.MapField<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> internalGetUserDefinedAttributes() {
        if (userDefinedAttributes_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(UserDefinedAttributesDefaultEntryHolder.defaultEntry);
        }
        return userDefinedAttributes_;
    }

    public int getUserDefinedAttributesCount() {
        return internalGetUserDefinedAttributes().getMap()
                .size();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
     */
    @java.lang.Override
    public boolean containsUserDefinedAttributes(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetUserDefinedAttributes().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getUserDefinedAttributesMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributes() {
        return getUserDefinedAttributesMap();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributesMap() {
        return internalGetUserDefinedAttributes().getMap();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
     */
    @java.lang.Override
    public /* nullable */
    im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrDefault(
            java.lang.String key,
            /* nullable */
            im.turms.server.common.access.client.dto.model.common.Value defaultValue) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> map =
                internalGetUserDefinedAttributes().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrThrow(
            java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> map =
                internalGetUserDefinedAttributes().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(1, id_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, name_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, intro_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, profilePicture_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeEnum(5, profileAccessStrategy_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            output.writeInt64(6, registrationDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            output.writeInt64(7, lastUpdatedDate_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            output.writeBool(8, active_);
        }
        com.google.protobuf.GeneratedMessage.serializeStringMapTo(output,
                internalGetUserDefinedAttributes(),
                UserDefinedAttributesDefaultEntryHolder.defaultEntry,
                9);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, id_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, name_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, intro_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, profilePicture_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5,
                    profileAccessStrategy_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, registrationDate_);
        }
        if (((bitField0_ & 0x00000040) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(7, lastUpdatedDate_);
        }
        if (((bitField0_ & 0x00000080) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(8, active_);
        }
        for (java.util.Map.Entry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> entry : internalGetUserDefinedAttributes()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> userDefinedAttributes__ =
                    UserDefinedAttributesDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9,
                    userDefinedAttributes__);
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
        if (!(obj instanceof UserInfo other)) {
            return super.equals(obj);
        }

        if (hasId() != other.hasId()) {
            return false;
        }
        if (hasId()) {
            if (getId() != other.getId()) {
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
        if (hasProfilePicture() != other.hasProfilePicture()) {
            return false;
        }
        if (hasProfilePicture()) {
            if (!getProfilePicture().equals(other.getProfilePicture())) {
                return false;
            }
        }
        if (hasProfileAccessStrategy() != other.hasProfileAccessStrategy()) {
            return false;
        }
        if (hasProfileAccessStrategy()) {
            if (profileAccessStrategy_ != other.profileAccessStrategy_) {
                return false;
            }
        }
        if (hasRegistrationDate() != other.hasRegistrationDate()) {
            return false;
        }
        if (hasRegistrationDate()) {
            if (getRegistrationDate() != other.getRegistrationDate()) {
                return false;
            }
        }
        if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) {
            return false;
        }
        if (hasLastUpdatedDate()) {
            if (getLastUpdatedDate() != other.getLastUpdatedDate()) {
                return false;
            }
        }
        if (hasActive() != other.hasActive()) {
            return false;
        }
        if (hasActive()) {
            if (getActive() != other.getActive()) {
                return false;
            }
        }
        return internalGetUserDefinedAttributes().equals(other.internalGetUserDefinedAttributes())
                && getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (hasId()) {
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getId());
        }
        if (hasName()) {
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
        }
        if (hasIntro()) {
            hash = (37 * hash) + INTRO_FIELD_NUMBER;
            hash = (53 * hash) + getIntro().hashCode();
        }
        if (hasProfilePicture()) {
            hash = (37 * hash) + PROFILE_PICTURE_FIELD_NUMBER;
            hash = (53 * hash) + getProfilePicture().hashCode();
        }
        if (hasProfileAccessStrategy()) {
            hash = (37 * hash) + PROFILE_ACCESS_STRATEGY_FIELD_NUMBER;
            hash = (53 * hash) + profileAccessStrategy_;
        }
        if (hasRegistrationDate()) {
            hash = (37 * hash) + REGISTRATION_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRegistrationDate());
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        if (hasActive()) {
            hash = (37 * hash) + ACTIVE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getActive());
        }
        if (!internalGetUserDefinedAttributes().getMap()
                .isEmpty()) {
            hash = (37 * hash) + USER_DEFINED_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + internalGetUserDefinedAttributes().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserInfo prototype) {
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
     * Protobuf type {@code im.turms.proto.UserInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserInfo)
            im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.internal_static_im_turms_proto_UserInfo_descriptor;
        }

        @SuppressWarnings("rawtypes")
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            return switch (number) {
                case 9 -> internalGetUserDefinedAttributes();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings("rawtypes")
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            return switch (number) {
                case 9 -> internalGetMutableUserDefinedAttributes();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.internal_static_im_turms_proto_UserInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserInfo.class,
                            im.turms.server.common.access.client.dto.model.user.UserInfo.Builder.class);
        }

        // Construct using im.turms.server.common.access.client.dto.model.user.UserInfo.newBuilder()
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
            name_ = "";
            intro_ = "";
            profilePicture_ = "";
            profileAccessStrategy_ = 0;
            registrationDate_ = 0L;
            lastUpdatedDate_ = 0L;
            active_ = false;
            internalGetMutableUserDefinedAttributes().clear();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000200;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserInfoOuterClass.internal_static_im_turms_proto_UserInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfo getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserInfo
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfo build() {
            im.turms.server.common.access.client.dto.model.user.UserInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfo buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserInfo result =
                    new im.turms.server.common.access.client.dto.model.user.UserInfo(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserInfo result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000200) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000200;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserInfo result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.id_ = id_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.name_ = name_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.intro_ = intro_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.profilePicture_ = profilePicture_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.profileAccessStrategy_ = profileAccessStrategy_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.registrationDate_ = registrationDate_;
                to_bitField0_ |= 0x00000020;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.lastUpdatedDate_ = lastUpdatedDate_;
                to_bitField0_ |= 0x00000040;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.active_ = active_;
                to_bitField0_ |= 0x00000080;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.userDefinedAttributes_ = internalGetUserDefinedAttributes()
                        .build(UserDefinedAttributesDefaultEntryHolder.defaultEntry);
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserInfo) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserInfo other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasId()) {
                setId(other.getId());
            }
            if (other.hasName()) {
                name_ = other.name_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.hasIntro()) {
                intro_ = other.intro_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasProfilePicture()) {
                profilePicture_ = other.profilePicture_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.hasProfileAccessStrategy()) {
                setProfileAccessStrategy(other.getProfileAccessStrategy());
            }
            if (other.hasRegistrationDate()) {
                setRegistrationDate(other.getRegistrationDate());
            }
            if (other.hasLastUpdatedDate()) {
                setLastUpdatedDate(other.getLastUpdatedDate());
            }
            if (other.hasActive()) {
                setActive(other.getActive());
            }
            internalGetMutableUserDefinedAttributes()
                    .mergeFrom(other.internalGetUserDefinedAttributes());
            bitField0_ |= 0x00000100;
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000200;
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
                        bitField0_ &= ~0x00000200;
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
                        case 18 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            intro_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            profilePicture_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 40 -> {
                            profileAccessStrategy_ = input.readEnum();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            registrationDate_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 56 -> {
                            lastUpdatedDate_ = input.readInt64();
                            bitField0_ |= 0x00000040;
                        } // case 56
                        case 64 -> {
                            active_ = input.readBool();
                            bitField0_ |= 0x00000080;
                        } // case 64
                        case 74 -> {
                            com.google.protobuf.MapEntry<String, im.turms.server.common.access.client.dto.model.common.Value> userDefinedAttributes__ =
                                    input.readMessage(
                                            UserDefinedAttributesDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableUserDefinedAttributes().ensureBuilderMap()
                                    .put(userDefinedAttributes__.getKey(),
                                            userDefinedAttributes__.getValue());
                            bitField0_ |= 0x00000100;
                        } // case 74
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
         * @return This builder for chaining.
         */
        public Builder setId(long value) {

            id_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            bitField0_ &= ~0x00000001;
            id_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>optional string name = 2;</code>
         *
         * @return Whether the name field is set.
         */
        public boolean hasName() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional string name = 2;</code>
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
         * <code>optional string name = 2;</code>
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
         * <code>optional string name = 2;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 2;</code>
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
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object intro_ = "";

        /**
         * <code>optional string intro = 3;</code>
         *
         * @return Whether the intro field is set.
         */
        public boolean hasIntro() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string intro = 3;</code>
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
         * <code>optional string intro = 3;</code>
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
         * <code>optional string intro = 3;</code>
         *
         * @param value The intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntro(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            intro_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string intro = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            intro_ = getDefaultInstance().getIntro();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string intro = 3;</code>
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
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object profilePicture_ = "";

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return Whether the profilePicture field is set.
         */
        public boolean hasProfilePicture() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return The profilePicture.
         */
        public java.lang.String getProfilePicture() {
            java.lang.Object ref = profilePicture_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                profilePicture_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return The bytes for profilePicture.
         */
        public com.google.protobuf.ByteString getProfilePictureBytes() {
            java.lang.Object ref = profilePicture_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                profilePicture_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @param value The profilePicture to set.
         * @return This builder for chaining.
         */
        public Builder setProfilePicture(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            profilePicture_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProfilePicture() {
            profilePicture_ = getDefaultInstance().getProfilePicture();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string profile_picture = 4;</code>
         *
         * @param value The bytes for profilePicture to set.
         * @return This builder for chaining.
         */
        public Builder setProfilePictureBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            profilePicture_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

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
         * @param value The enum numeric value on the wire for profileAccessStrategy to set.
         * @return This builder for chaining.
         */
        public Builder setProfileAccessStrategyValue(int value) {
            profileAccessStrategy_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @return The profileAccessStrategy.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy getProfileAccessStrategy() {
            im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy result =
                    im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy
                            .forNumber(profileAccessStrategy_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @param value The profileAccessStrategy to set.
         * @return This builder for chaining.
         */
        public Builder setProfileAccessStrategy(
                im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            profileAccessStrategy_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ProfileAccessStrategy profile_access_strategy = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProfileAccessStrategy() {
            bitField0_ &= ~0x00000010;
            profileAccessStrategy_ = 0;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setRegistrationDate(long value) {

            registrationDate_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 registration_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRegistrationDate() {
            bitField0_ &= ~0x00000020;
            registrationDate_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {

            lastUpdatedDate_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            bitField0_ &= ~0x00000040;
            lastUpdatedDate_ = 0L;
            onChanged();
            return this;
        }

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
         * @return This builder for chaining.
         */
        public Builder setActive(boolean value) {

            active_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool active = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearActive() {
            bitField0_ &= ~0x00000080;
            active_ = false;
            onChanged();
            return this;
        }

        private static final class UserDefinedAttributesConverter implements
                com.google.protobuf.MapFieldBuilder.Converter<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value> {
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.common.Value build(
                    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder val) {
                if (val instanceof im.turms.server.common.access.client.dto.model.common.Value) {
                    return (im.turms.server.common.access.client.dto.model.common.Value) val;
                }
                return ((im.turms.server.common.access.client.dto.model.common.Value.Builder) val)
                        .build();
            }

            @java.lang.Override
            public com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> defaultEntry() {
                return UserDefinedAttributesDefaultEntryHolder.defaultEntry;
            }
        }

        private static final UserDefinedAttributesConverter userDefinedAttributesConverter =
                new UserDefinedAttributesConverter();

        private com.google.protobuf.MapFieldBuilder<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder> userDefinedAttributes_;

        private com.google.protobuf.MapFieldBuilder<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder> internalGetUserDefinedAttributes() {
            if (userDefinedAttributes_ == null) {
                return new com.google.protobuf.MapFieldBuilder<>(userDefinedAttributesConverter);
            }
            return userDefinedAttributes_;
        }

        private com.google.protobuf.MapFieldBuilder<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder> internalGetMutableUserDefinedAttributes() {
            if (userDefinedAttributes_ == null) {
                userDefinedAttributes_ =
                        new com.google.protobuf.MapFieldBuilder<>(userDefinedAttributesConverter);
            }
            bitField0_ |= 0x00000100;
            onChanged();
            return userDefinedAttributes_;
        }

        public int getUserDefinedAttributesCount() {
            return internalGetUserDefinedAttributes().ensureBuilderMap()
                    .size();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        @java.lang.Override
        public boolean containsUserDefinedAttributes(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetUserDefinedAttributes().ensureBuilderMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getUserDefinedAttributesMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributes() {
            return getUserDefinedAttributesMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributesMap() {
            return internalGetUserDefinedAttributes().getImmutableMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        @java.lang.Override
        public /* nullable */
        im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrDefault(
                java.lang.String key,
                /* nullable */
                im.turms.server.common.access.client.dto.model.common.Value defaultValue) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> map =
                    internalGetMutableUserDefinedAttributes().ensureBuilderMap();
            return map.containsKey(key)
                    ? userDefinedAttributesConverter.build(map.get(key))
                    : defaultValue;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrThrow(
                java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> map =
                    internalGetMutableUserDefinedAttributes().ensureBuilderMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return userDefinedAttributesConverter.build(map.get(key));
        }

        public Builder clearUserDefinedAttributes() {
            bitField0_ &= ~0x00000100;
            internalGetMutableUserDefinedAttributes().clear();
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        public Builder removeUserDefinedAttributes(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableUserDefinedAttributes().ensureBuilderMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getMutableUserDefinedAttributes() {
            bitField0_ |= 0x00000100;
            return internalGetMutableUserDefinedAttributes().ensureMessageMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        public Builder putUserDefinedAttributes(
                java.lang.String key,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableUserDefinedAttributes().ensureBuilderMap()
                    .put(key, value);
            bitField0_ |= 0x00000100;
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        public Builder putAllUserDefinedAttributes(
                java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> values) {
            for (java.util.Map.Entry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> e : values
                    .entrySet()) {
                if (e.getKey() == null || e.getValue() == null) {
                    throw new NullPointerException();
                }
            }
            internalGetMutableUserDefinedAttributes().ensureBuilderMap()
                    .putAll(values);
            bitField0_ |= 0x00000100;
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 9;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder putUserDefinedAttributesBuilderIfAbsent(
                java.lang.String key) {
            java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> builderMap =
                    internalGetMutableUserDefinedAttributes().ensureBuilderMap();
            im.turms.server.common.access.client.dto.model.common.ValueOrBuilder entry =
                    builderMap.get(key);
            if (entry == null) {
                entry = im.turms.server.common.access.client.dto.model.common.Value.newBuilder();
                builderMap.put(key, entry);
            }
            if (entry instanceof im.turms.server.common.access.client.dto.model.common.Value) {
                entry = ((im.turms.server.common.access.client.dto.model.common.Value) entry)
                        .toBuilder();
                builderMap.put(key, entry);
            }
            return (im.turms.server.common.access.client.dto.model.common.Value.Builder) entry;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000200) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000200;
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
                bitField0_ &= ~0x00000200;
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
                        ((bitField0_ & 0x00000200) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserInfo)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserInfo)
    private static final im.turms.server.common.access.client.dto.model.user.UserInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.user.UserInfo();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}