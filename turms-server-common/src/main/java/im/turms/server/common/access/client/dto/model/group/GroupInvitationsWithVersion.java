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

package im.turms.server.common.access.client.dto.model.group;

/**
 * Protobuf type {@code im.turms.proto.GroupInvitationsWithVersion}
 */
public final class GroupInvitationsWithVersion extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupInvitationsWithVersion)
        GroupInvitationsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupInvitationsWithVersion.newBuilder() to construct.
    private GroupInvitationsWithVersion(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupInvitationsWithVersion() {
        groupInvitations_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupInvitationsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass.internal_static_im_turms_proto_GroupInvitationsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass.internal_static_im_turms_proto_GroupInvitationsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int GROUP_INVITATIONS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupInvitation> groupInvitations_;

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupInvitation> getGroupInvitationsList() {
        return groupInvitations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupInvitationOrBuilder> getGroupInvitationsOrBuilderList() {
        return groupInvitations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public int getGroupInvitationsCount() {
        return groupInvitations_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupInvitation getGroupInvitations(
            int index) {
        return groupInvitations_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupInvitationOrBuilder getGroupInvitationsOrBuilder(
            int index) {
        return groupInvitations_.get(index);
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_ = 0L;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
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
        for (GroupInvitation groupInvitation : groupInvitations_) {
            output.writeMessage(1, groupInvitation);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, lastUpdatedDate_);
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
        for (GroupInvitation groupInvitation : groupInvitations_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, groupInvitation);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, lastUpdatedDate_);
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
        if (!(obj instanceof GroupInvitationsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getGroupInvitationsList().equals(other.getGroupInvitationsList())) {
            return false;
        }
        if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) {
            return false;
        }
        if (hasLastUpdatedDate()) {
            if (getLastUpdatedDate() != other.getLastUpdatedDate()) {
                return false;
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (getGroupInvitationsCount() > 0) {
            hash = (37 * hash) + GROUP_INVITATIONS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupInvitationsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
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
            im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion prototype) {
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
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupInvitationsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupInvitationsWithVersion)
            im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass.internal_static_im_turms_proto_GroupInvitationsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass.internal_static_im_turms_proto_GroupInvitationsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (groupInvitationsBuilder_ == null) {
                groupInvitations_ = java.util.Collections.emptyList();
            } else {
                groupInvitations_ = null;
                groupInvitationsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass.internal_static_im_turms_proto_GroupInvitationsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion build() {
            im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion result) {
            if (groupInvitationsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    groupInvitations_ = java.util.Collections.unmodifiableList(groupInvitations_);
                    bitField0_ &= ~0x00000001;
                }
                result.groupInvitations_ = groupInvitations_;
            } else {
                result.groupInvitations_ = groupInvitationsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.lastUpdatedDate_ = lastUpdatedDate_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (groupInvitationsBuilder_ == null) {
                if (!other.groupInvitations_.isEmpty()) {
                    if (groupInvitations_.isEmpty()) {
                        groupInvitations_ = other.groupInvitations_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureGroupInvitationsIsMutable();
                        groupInvitations_.addAll(other.groupInvitations_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groupInvitations_.isEmpty()) {
                    if (groupInvitationsBuilder_.isEmpty()) {
                        groupInvitationsBuilder_.dispose();
                        groupInvitationsBuilder_ = null;
                        groupInvitations_ = other.groupInvitations_;
                        bitField0_ &= ~0x00000001;
                        groupInvitationsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getGroupInvitationsFieldBuilder()
                                        : null;
                    } else {
                        groupInvitationsBuilder_.addAllMessages(other.groupInvitations_);
                    }
                }
            }
            if (other.hasLastUpdatedDate()) {
                setLastUpdatedDate(other.getLastUpdatedDate());
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
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
                            GroupInvitation m =
                                    input.readMessage(GroupInvitation.parser(), extensionRegistry);
                            if (groupInvitationsBuilder_ == null) {
                                ensureGroupInvitationsIsMutable();
                                groupInvitations_.add(m);
                            } else {
                                groupInvitationsBuilder_.addMessage(m);
                            }
                        } // case 10
                        case 16 -> {
                            lastUpdatedDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
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

        private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupInvitation> groupInvitations_ =
                java.util.Collections.emptyList();

        private void ensureGroupInvitationsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                groupInvitations_ = new java.util.ArrayList<>(groupInvitations_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupInvitation, im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder, im.turms.server.common.access.client.dto.model.group.GroupInvitationOrBuilder> groupInvitationsBuilder_;

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupInvitation> getGroupInvitationsList() {
            if (groupInvitationsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groupInvitations_);
            } else {
                return groupInvitationsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public int getGroupInvitationsCount() {
            if (groupInvitationsBuilder_ == null) {
                return groupInvitations_.size();
            } else {
                return groupInvitationsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupInvitation getGroupInvitations(
                int index) {
            if (groupInvitationsBuilder_ == null) {
                return groupInvitations_.get(index);
            } else {
                return groupInvitationsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder setGroupInvitations(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupInvitation value) {
            if (groupInvitationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupInvitationsIsMutable();
                groupInvitations_.set(index, value);
                onChanged();
            } else {
                groupInvitationsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder setGroupInvitations(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder builderForValue) {
            if (groupInvitationsBuilder_ == null) {
                ensureGroupInvitationsIsMutable();
                groupInvitations_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupInvitationsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                im.turms.server.common.access.client.dto.model.group.GroupInvitation value) {
            if (groupInvitationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupInvitationsIsMutable();
                groupInvitations_.add(value);
                onChanged();
            } else {
                groupInvitationsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupInvitation value) {
            if (groupInvitationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupInvitationsIsMutable();
                groupInvitations_.add(index, value);
                onChanged();
            } else {
                groupInvitationsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder builderForValue) {
            if (groupInvitationsBuilder_ == null) {
                ensureGroupInvitationsIsMutable();
                groupInvitations_.add(builderForValue.build());
                onChanged();
            } else {
                groupInvitationsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder builderForValue) {
            if (groupInvitationsBuilder_ == null) {
                ensureGroupInvitationsIsMutable();
                groupInvitations_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupInvitationsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addAllGroupInvitations(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.group.GroupInvitation> values) {
            if (groupInvitationsBuilder_ == null) {
                ensureGroupInvitationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupInvitations_);
                onChanged();
            } else {
                groupInvitationsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder clearGroupInvitations() {
            if (groupInvitationsBuilder_ == null) {
                groupInvitations_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                groupInvitationsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder removeGroupInvitations(int index) {
            if (groupInvitationsBuilder_ == null) {
                ensureGroupInvitationsIsMutable();
                groupInvitations_.remove(index);
                onChanged();
            } else {
                groupInvitationsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder getGroupInvitationsBuilder(
                int index) {
            return getGroupInvitationsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupInvitationOrBuilder getGroupInvitationsOrBuilder(
                int index) {
            if (groupInvitationsBuilder_ == null) {
                return groupInvitations_.get(index);
            } else {
                return groupInvitationsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupInvitationOrBuilder> getGroupInvitationsOrBuilderList() {
            if (groupInvitationsBuilder_ != null) {
                return groupInvitationsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groupInvitations_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder addGroupInvitationsBuilder() {
            return getGroupInvitationsFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.group.GroupInvitation
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder addGroupInvitationsBuilder(
                int index) {
            return getGroupInvitationsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.group.GroupInvitation
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder> getGroupInvitationsBuilderList() {
            return getGroupInvitationsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupInvitation, im.turms.server.common.access.client.dto.model.group.GroupInvitation.Builder, im.turms.server.common.access.client.dto.model.group.GroupInvitationOrBuilder> getGroupInvitationsFieldBuilder() {
            if (groupInvitationsBuilder_ == null) {
                groupInvitationsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        groupInvitations_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                groupInvitations_ = null;
            }
            return groupInvitationsBuilder_;
        }

        private long lastUpdatedDate_;

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return lastUpdatedDate_;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {

            lastUpdatedDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            bitField0_ &= ~0x00000002;
            lastUpdatedDate_ = 0L;
            onChanged();
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupInvitationsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupInvitationsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupInvitationsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupInvitationsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupInvitationsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupInvitationsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}