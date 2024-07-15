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

package im.turms.server.common.access.client.dto.request.group;

/**
 * Protobuf type {@code im.turms.proto.CreateGroupRequest}
 */
public final class CreateGroupRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateGroupRequest)
        CreateGroupRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                CreateGroupRequest.class.getName());
    }

    // Use CreateGroupRequest.newBuilder() to construct.
    private CreateGroupRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private CreateGroupRequest() {
        name_ = "";
        intro_ = "";
        announcement_ = "";
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass.internal_static_im_turms_proto_CreateGroupRequest_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        return switch (number) {
            case 7 -> internalGetUserDefinedAttributes();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass.internal_static_im_turms_proto_CreateGroupRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.class,
                        im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.Builder.class);
    }

    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <code>string name = 1;</code>
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
     * <code>string name = 1;</code>
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

    public static final int INTRO_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object intro_ = "";

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return Whether the intro field is set.
     */
    @java.lang.Override
    public boolean hasIntro() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string intro = 2;</code>
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
     * <code>optional string intro = 2;</code>
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

    public static final int ANNOUNCEMENT_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object announcement_ = "";

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return Whether the announcement field is set.
     */
    @java.lang.Override
    public boolean hasAnnouncement() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return The announcement.
     */
    @java.lang.Override
    public java.lang.String getAnnouncement() {
        java.lang.Object ref = announcement_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            announcement_ = s;
            return s;
        }
    }

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return The bytes for announcement.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAnnouncementBytes() {
        java.lang.Object ref = announcement_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            announcement_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int MIN_SCORE_FIELD_NUMBER = 4;
    private int minScore_ = 0;

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @return Whether the minScore field is set.
     */
    @java.lang.Override
    public boolean hasMinScore() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @return The minScore.
     */
    @java.lang.Override
    public int getMinScore() {
        return minScore_;
    }

    public static final int TYPE_ID_FIELD_NUMBER = 5;
    private long typeId_ = 0L;

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @return Whether the typeId field is set.
     */
    @java.lang.Override
    public boolean hasTypeId() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @return The typeId.
     */
    @java.lang.Override
    public long getTypeId() {
        return typeId_;
    }

    public static final int MUTE_END_DATE_FIELD_NUMBER = 6;
    private long muteEndDate_ = 0L;

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    @java.lang.Override
    public boolean hasMuteEndDate() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return The muteEndDate.
     */
    @java.lang.Override
    public long getMuteEndDate() {
        return muteEndDate_;
    }

    public static final int USER_DEFINED_ATTRIBUTES_FIELD_NUMBER = 7;

    private static final class UserDefinedAttributesDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass.internal_static_im_turms_proto_CreateGroupRequest_UserDefinedAttributesEntry_descriptor,
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
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributesMap() {
        return internalGetUserDefinedAttributes().getMap();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, name_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, intro_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, announcement_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt32(4, minScore_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt64(5, typeId_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt64(6, muteEndDate_);
        }
        com.google.protobuf.GeneratedMessage.serializeStringMapTo(output,
                internalGetUserDefinedAttributes(),
                UserDefinedAttributesDefaultEntryHolder.defaultEntry,
                7);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, name_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, intro_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, announcement_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, minScore_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, typeId_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(6, muteEndDate_);
        }
        for (java.util.Map.Entry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> entry : internalGetUserDefinedAttributes()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> userDefinedAttributes__ =
                    UserDefinedAttributesDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7,
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
        if (!(obj instanceof CreateGroupRequest other)) {
            return super.equals(obj);
        }

        if (!getName().equals(other.getName())) {
            return false;
        }
        if (hasIntro() != other.hasIntro()) {
            return false;
        }
        if (hasIntro()) {
            if (!getIntro().equals(other.getIntro())) {
                return false;
            }
        }
        if (hasAnnouncement() != other.hasAnnouncement()) {
            return false;
        }
        if (hasAnnouncement()) {
            if (!getAnnouncement().equals(other.getAnnouncement())) {
                return false;
            }
        }
        if (hasMinScore() != other.hasMinScore()) {
            return false;
        }
        if (hasMinScore()) {
            if (getMinScore() != other.getMinScore()) {
                return false;
            }
        }
        if (hasTypeId() != other.hasTypeId()) {
            return false;
        }
        if (hasTypeId()) {
            if (getTypeId() != other.getTypeId()) {
                return false;
            }
        }
        if (hasMuteEndDate() != other.hasMuteEndDate()) {
            return false;
        }
        if (hasMuteEndDate()) {
            if (getMuteEndDate() != other.getMuteEndDate()) {
                return false;
            }
        }
        if (!internalGetUserDefinedAttributes().equals(other.internalGetUserDefinedAttributes())) {
            return false;
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
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        if (hasIntro()) {
            hash = (37 * hash) + INTRO_FIELD_NUMBER;
            hash = (53 * hash) + getIntro().hashCode();
        }
        if (hasAnnouncement()) {
            hash = (37 * hash) + ANNOUNCEMENT_FIELD_NUMBER;
            hash = (53 * hash) + getAnnouncement().hashCode();
        }
        if (hasMinScore()) {
            hash = (37 * hash) + MIN_SCORE_FIELD_NUMBER;
            hash = (53 * hash) + getMinScore();
        }
        if (hasTypeId()) {
            hash = (37 * hash) + TYPE_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getTypeId());
        }
        if (hasMuteEndDate()) {
            hash = (37 * hash) + MUTE_END_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getMuteEndDate());
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

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.group.CreateGroupRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.CreateGroupRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateGroupRequest)
            im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass.internal_static_im_turms_proto_CreateGroupRequest_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            return switch (number) {
                case 7 -> internalGetUserDefinedAttributes();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            return switch (number) {
                case 7 -> internalGetMutableUserDefinedAttributes();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass.internal_static_im_turms_proto_CreateGroupRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.class,
                            im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.group.CreateGroupRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            name_ = "";
            intro_ = "";
            announcement_ = "";
            minScore_ = 0;
            typeId_ = 0L;
            muteEndDate_ = 0L;
            internalGetMutableUserDefinedAttributes().clear();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000080;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass.internal_static_im_turms_proto_CreateGroupRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest build() {
            im.turms.server.common.access.client.dto.request.group.CreateGroupRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.group.CreateGroupRequest result =
                    new im.turms.server.common.access.client.dto.request.group.CreateGroupRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.group.CreateGroupRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000080;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.group.CreateGroupRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.name_ = name_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.intro_ = intro_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.announcement_ = announcement_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.minScore_ = minScore_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.typeId_ = typeId_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.muteEndDate_ = muteEndDate_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.userDefinedAttributes_ = internalGetUserDefinedAttributes()
                        .build(UserDefinedAttributesDefaultEntryHolder.defaultEntry);
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.group.CreateGroupRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.group.CreateGroupRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.group.CreateGroupRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.hasIntro()) {
                intro_ = other.intro_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.hasAnnouncement()) {
                announcement_ = other.announcement_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasMinScore()) {
                setMinScore(other.getMinScore());
            }
            if (other.hasTypeId()) {
                setTypeId(other.getTypeId());
            }
            if (other.hasMuteEndDate()) {
                setMuteEndDate(other.getMuteEndDate());
            }
            internalGetMutableUserDefinedAttributes()
                    .mergeFrom(other.internalGetUserDefinedAttributes());
            bitField0_ |= 0x00000040;
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000080;
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
                        bitField0_ &= ~0x00000080;
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
                        case 10 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            intro_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            announcement_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            minScore_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            typeId_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 48 -> {
                            muteEndDate_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 48
                        case 58 -> {
                            com.google.protobuf.MapEntry<String, im.turms.server.common.access.client.dto.model.common.Value> userDefinedAttributes__ =
                                    input.readMessage(
                                            UserDefinedAttributesDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableUserDefinedAttributes().ensureBuilderMap()
                                    .put(userDefinedAttributes__.getKey(),
                                            userDefinedAttributes__.getValue());
                            bitField0_ |= 0x00000040;
                        } // case 58
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

        private java.lang.Object name_ = "";

        /**
         * <code>string name = 1;</code>
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
         * <code>string name = 1;</code>
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
         * <code>string name = 1;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 1;</code>
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
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object intro_ = "";

        /**
         * <code>optional string intro = 2;</code>
         *
         * @return Whether the intro field is set.
         */
        public boolean hasIntro() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional string intro = 2;</code>
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
         * <code>optional string intro = 2;</code>
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
         * <code>optional string intro = 2;</code>
         *
         * @param value The intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntro(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            intro_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            intro_ = getDefaultInstance().getIntro();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional string intro = 2;</code>
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
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object announcement_ = "";

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return Whether the announcement field is set.
         */
        public boolean hasAnnouncement() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return The announcement.
         */
        public java.lang.String getAnnouncement() {
            java.lang.Object ref = announcement_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                announcement_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return The bytes for announcement.
         */
        public com.google.protobuf.ByteString getAnnouncementBytes() {
            java.lang.Object ref = announcement_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                announcement_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @param value The announcement to set.
         * @return This builder for chaining.
         */
        public Builder setAnnouncement(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            announcement_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAnnouncement() {
            announcement_ = getDefaultInstance().getAnnouncement();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @param value The bytes for announcement to set.
         * @return This builder for chaining.
         */
        public Builder setAnnouncementBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            announcement_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private int minScore_;

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @return Whether the minScore field is set.
         */
        @java.lang.Override
        public boolean hasMinScore() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @return The minScore.
         */
        @java.lang.Override
        public int getMinScore() {
            return minScore_;
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @param value The minScore to set.
         * @return This builder for chaining.
         */
        public Builder setMinScore(int value) {

            minScore_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMinScore() {
            bitField0_ &= ~0x00000008;
            minScore_ = 0;
            onChanged();
            return this;
        }

        private long typeId_;

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @return Whether the typeId field is set.
         */
        @java.lang.Override
        public boolean hasTypeId() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @return The typeId.
         */
        @java.lang.Override
        public long getTypeId() {
            return typeId_;
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @param value The typeId to set.
         * @return This builder for chaining.
         */
        public Builder setTypeId(long value) {

            typeId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTypeId() {
            bitField0_ &= ~0x00000010;
            typeId_ = 0L;
            onChanged();
            return this;
        }

        private long muteEndDate_;

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return Whether the muteEndDate field is set.
         */
        @java.lang.Override
        public boolean hasMuteEndDate() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return The muteEndDate.
         */
        @java.lang.Override
        public long getMuteEndDate() {
            return muteEndDate_;
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @param value The muteEndDate to set.
         * @return This builder for chaining.
         */
        public Builder setMuteEndDate(long value) {

            muteEndDate_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuteEndDate() {
            bitField0_ &= ~0x00000020;
            muteEndDate_ = 0L;
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
            bitField0_ |= 0x00000040;
            onChanged();
            return userDefinedAttributes_;
        }

        public int getUserDefinedAttributesCount() {
            return internalGetUserDefinedAttributes().ensureBuilderMap()
                    .size();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributesMap() {
            return internalGetUserDefinedAttributes().getImmutableMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
            bitField0_ &= ~0x00000040;
            internalGetMutableUserDefinedAttributes().clear();
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
            bitField0_ |= 0x00000040;
            return internalGetMutableUserDefinedAttributes().ensureMessageMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
            bitField0_ |= 0x00000040;
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
            bitField0_ |= 0x00000040;
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
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
            if ((bitField0_ & 0x00000080) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000080;
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
                bitField0_ &= ~0x00000080;
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
                        ((bitField0_ & 0x00000080) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateGroupRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateGroupRequest)
    private static final im.turms.server.common.access.client.dto.request.group.CreateGroupRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.group.CreateGroupRequest();
    }

    public static im.turms.server.common.access.client.dto.request.group.CreateGroupRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CreateGroupRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public CreateGroupRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<CreateGroupRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CreateGroupRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.group.CreateGroupRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}