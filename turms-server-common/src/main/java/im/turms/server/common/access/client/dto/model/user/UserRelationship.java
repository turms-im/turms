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
 * Protobuf type {@code im.turms.proto.UserRelationship}
 */
public final class UserRelationship extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserRelationship)
        UserRelationshipOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserRelationship.class.getName());
    }

    // Use UserRelationship.newBuilder() to construct.
    private UserRelationship(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UserRelationship() {
        name_ = "";
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass.internal_static_im_turms_proto_UserRelationship_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass.internal_static_im_turms_proto_UserRelationship_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserRelationship.class,
                        im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder.class);
    }

    private int bitField0_;
    public static final int OWNER_ID_FIELD_NUMBER = 1;
    private long ownerId_;

    /**
     * <code>optional int64 owner_id = 1;</code>
     *
     * @return Whether the ownerId field is set.
     */
    @java.lang.Override
    public boolean hasOwnerId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 owner_id = 1;</code>
     *
     * @return The ownerId.
     */
    @java.lang.Override
    public long getOwnerId() {
        return ownerId_;
    }

    public static final int RELATED_USER_ID_FIELD_NUMBER = 2;
    private long relatedUserId_;

    /**
     * <code>optional int64 related_user_id = 2;</code>
     *
     * @return Whether the relatedUserId field is set.
     */
    @java.lang.Override
    public boolean hasRelatedUserId() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 related_user_id = 2;</code>
     *
     * @return The relatedUserId.
     */
    @java.lang.Override
    public long getRelatedUserId() {
        return relatedUserId_;
    }

    public static final int BLOCK_DATE_FIELD_NUMBER = 3;
    private long blockDate_;

    /**
     * <code>optional int64 block_date = 3;</code>
     *
     * @return Whether the blockDate field is set.
     */
    @java.lang.Override
    public boolean hasBlockDate() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int64 block_date = 3;</code>
     *
     * @return The blockDate.
     */
    @java.lang.Override
    public long getBlockDate() {
        return blockDate_;
    }

    public static final int GROUP_INDEX_FIELD_NUMBER = 4;
    private long groupIndex_;

    /**
     * <code>optional int64 group_index = 4;</code>
     *
     * @return Whether the groupIndex field is set.
     */
    @java.lang.Override
    public boolean hasGroupIndex() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int64 group_index = 4;</code>
     *
     * @return The groupIndex.
     */
    @java.lang.Override
    public long getGroupIndex() {
        return groupIndex_;
    }

    public static final int ESTABLISHMENT_DATE_FIELD_NUMBER = 5;
    private long establishmentDate_;

    /**
     * <code>optional int64 establishment_date = 5;</code>
     *
     * @return Whether the establishmentDate field is set.
     */
    @java.lang.Override
    public boolean hasEstablishmentDate() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 establishment_date = 5;</code>
     *
     * @return The establishmentDate.
     */
    @java.lang.Override
    public long getEstablishmentDate() {
        return establishmentDate_;
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
        return ((bitField0_ & 0x00000020) != 0);
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
            output.writeInt64(1, ownerId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(2, relatedUserId_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt64(3, blockDate_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt64(4, groupIndex_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt64(5, establishmentDate_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, name_);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, ownerId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, relatedUserId_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, blockDate_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, groupIndex_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, establishmentDate_);
        }
        if (((bitField0_ & 0x00000020) != 0)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, name_);
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
        if (!(obj instanceof UserRelationship other)) {
            return super.equals(obj);
        }

        if (hasOwnerId() != other.hasOwnerId()) {
            return false;
        }
        if (hasOwnerId()) {
            if (getOwnerId() != other.getOwnerId()) {
                return false;
            }
        }
        if (hasRelatedUserId() != other.hasRelatedUserId()) {
            return false;
        }
        if (hasRelatedUserId()) {
            if (getRelatedUserId() != other.getRelatedUserId()) {
                return false;
            }
        }
        if (hasBlockDate() != other.hasBlockDate()) {
            return false;
        }
        if (hasBlockDate()) {
            if (getBlockDate() != other.getBlockDate()) {
                return false;
            }
        }
        if (hasGroupIndex() != other.hasGroupIndex()) {
            return false;
        }
        if (hasGroupIndex()) {
            if (getGroupIndex() != other.getGroupIndex()) {
                return false;
            }
        }
        if (hasEstablishmentDate() != other.hasEstablishmentDate()) {
            return false;
        }
        if (hasEstablishmentDate()) {
            if (getEstablishmentDate() != other.getEstablishmentDate()) {
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
        if (hasOwnerId()) {
            hash = (37 * hash) + OWNER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getOwnerId());
        }
        if (hasRelatedUserId()) {
            hash = (37 * hash) + RELATED_USER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRelatedUserId());
        }
        if (hasBlockDate()) {
            hash = (37 * hash) + BLOCK_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getBlockDate());
        }
        if (hasGroupIndex()) {
            hash = (37 * hash) + GROUP_INDEX_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupIndex());
        }
        if (hasEstablishmentDate()) {
            hash = (37 * hash) + ESTABLISHMENT_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getEstablishmentDate());
        }
        if (hasName()) {
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserRelationship prototype) {
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
     * Protobuf type {@code im.turms.proto.UserRelationship}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserRelationship)
            im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass.internal_static_im_turms_proto_UserRelationship_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass.internal_static_im_turms_proto_UserRelationship_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserRelationship.class,
                            im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserRelationship.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            ownerId_ = 0L;
            relatedUserId_ = 0L;
            blockDate_ = 0L;
            groupIndex_ = 0L;
            establishmentDate_ = 0L;
            name_ = "";
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000040;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass.internal_static_im_turms_proto_UserRelationship_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationship getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationship
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationship build() {
            im.turms.server.common.access.client.dto.model.user.UserRelationship result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationship buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserRelationship result =
                    new im.turms.server.common.access.client.dto.model.user.UserRelationship(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserRelationship result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000040) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000040;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserRelationship result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.ownerId_ = ownerId_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.relatedUserId_ = relatedUserId_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.blockDate_ = blockDate_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.groupIndex_ = groupIndex_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.establishmentDate_ = establishmentDate_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.name_ = name_;
                to_bitField0_ |= 0x00000020;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserRelationship) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserRelationship) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserRelationship other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserRelationship
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasOwnerId()) {
                setOwnerId(other.getOwnerId());
            }
            if (other.hasRelatedUserId()) {
                setRelatedUserId(other.getRelatedUserId());
            }
            if (other.hasBlockDate()) {
                setBlockDate(other.getBlockDate());
            }
            if (other.hasGroupIndex()) {
                setGroupIndex(other.getGroupIndex());
            }
            if (other.hasEstablishmentDate()) {
                setEstablishmentDate(other.getEstablishmentDate());
            }
            if (other.hasName()) {
                name_ = other.name_;
                bitField0_ |= 0x00000020;
                onChanged();
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000040;
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
                        bitField0_ &= ~0x00000040;
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
                            ownerId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            relatedUserId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            blockDate_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            groupIndex_ = input.readInt64();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 40 -> {
                            establishmentDate_ = input.readInt64();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 50 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 50
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

        private long ownerId_;

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @return Whether the ownerId field is set.
         */
        @java.lang.Override
        public boolean hasOwnerId() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @return The ownerId.
         */
        @java.lang.Override
        public long getOwnerId() {
            return ownerId_;
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @param value The ownerId to set.
         * @return This builder for chaining.
         */
        public Builder setOwnerId(long value) {

            ownerId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearOwnerId() {
            bitField0_ &= ~0x00000001;
            ownerId_ = 0L;
            onChanged();
            return this;
        }

        private long relatedUserId_;

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @return Whether the relatedUserId field is set.
         */
        @java.lang.Override
        public boolean hasRelatedUserId() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @return The relatedUserId.
         */
        @java.lang.Override
        public long getRelatedUserId() {
            return relatedUserId_;
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @param value The relatedUserId to set.
         * @return This builder for chaining.
         */
        public Builder setRelatedUserId(long value) {

            relatedUserId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRelatedUserId() {
            bitField0_ &= ~0x00000002;
            relatedUserId_ = 0L;
            onChanged();
            return this;
        }

        private long blockDate_;

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @return Whether the blockDate field is set.
         */
        @java.lang.Override
        public boolean hasBlockDate() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @return The blockDate.
         */
        @java.lang.Override
        public long getBlockDate() {
            return blockDate_;
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @param value The blockDate to set.
         * @return This builder for chaining.
         */
        public Builder setBlockDate(long value) {

            blockDate_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBlockDate() {
            bitField0_ &= ~0x00000004;
            blockDate_ = 0L;
            onChanged();
            return this;
        }

        private long groupIndex_;

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @return Whether the groupIndex field is set.
         */
        @java.lang.Override
        public boolean hasGroupIndex() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @return The groupIndex.
         */
        @java.lang.Override
        public long getGroupIndex() {
            return groupIndex_;
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @param value The groupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIndex(long value) {

            groupIndex_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIndex() {
            bitField0_ &= ~0x00000008;
            groupIndex_ = 0L;
            onChanged();
            return this;
        }

        private long establishmentDate_;

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @return Whether the establishmentDate field is set.
         */
        @java.lang.Override
        public boolean hasEstablishmentDate() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @return The establishmentDate.
         */
        @java.lang.Override
        public long getEstablishmentDate() {
            return establishmentDate_;
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @param value The establishmentDate to set.
         * @return This builder for chaining.
         */
        public Builder setEstablishmentDate(long value) {

            establishmentDate_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEstablishmentDate() {
            bitField0_ &= ~0x00000010;
            establishmentDate_ = 0L;
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

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000040) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000040;
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
                bitField0_ &= ~0x00000040;
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
                        ((bitField0_ & 0x00000040) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserRelationship)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserRelationship)
    private static final im.turms.server.common.access.client.dto.model.user.UserRelationship DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserRelationship();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationship getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserRelationship> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserRelationship parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserRelationship> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserRelationship> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationship getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}