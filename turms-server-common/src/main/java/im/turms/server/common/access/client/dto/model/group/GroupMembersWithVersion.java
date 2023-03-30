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
 * Protobuf type {@code im.turms.proto.GroupMembersWithVersion}
 */
public final class GroupMembersWithVersion extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupMembersWithVersion)
        GroupMembersWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupMembersWithVersion.newBuilder() to construct.
    private GroupMembersWithVersion(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupMembersWithVersion() {
        groupMembers_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupMembersWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass.internal_static_im_turms_proto_GroupMembersWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass.internal_static_im_turms_proto_GroupMembersWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.class,
                        im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int GROUP_MEMBERS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupMember> groupMembers_;

    /**
     * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupMember> getGroupMembersList() {
        return groupMembers_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder> getGroupMembersOrBuilderList() {
        return groupMembers_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
     */
    @java.lang.Override
    public int getGroupMembersCount() {
        return groupMembers_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupMember getGroupMembers(
            int index) {
        return groupMembers_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder getGroupMembersOrBuilder(
            int index) {
        return groupMembers_.get(index);
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
        for (GroupMember groupMember : groupMembers_) {
            output.writeMessage(1, groupMember);
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
        for (GroupMember groupMember : groupMembers_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, groupMember);
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
        if (!(obj instanceof GroupMembersWithVersion other)) {
            return super.equals(obj);
        }

        if (!getGroupMembersList().equals(other.getGroupMembersList())) {
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
        if (getGroupMembersCount() > 0) {
            hash = (37 * hash) + GROUP_MEMBERS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupMembersList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.GroupMembersWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupMembersWithVersion)
            im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass.internal_static_im_turms_proto_GroupMembersWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass.internal_static_im_turms_proto_GroupMembersWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.class,
                            im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (groupMembersBuilder_ == null) {
                groupMembers_ = java.util.Collections.emptyList();
            } else {
                groupMembers_ = null;
                groupMembersBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass.internal_static_im_turms_proto_GroupMembersWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion build() {
            im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion result =
                    new im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion result) {
            if (groupMembersBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    groupMembers_ = java.util.Collections.unmodifiableList(groupMembers_);
                    bitField0_ &= ~0x00000001;
                }
                result.groupMembers_ = groupMembers_;
            } else {
                result.groupMembers_ = groupMembersBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (groupMembersBuilder_ == null) {
                if (!other.groupMembers_.isEmpty()) {
                    if (groupMembers_.isEmpty()) {
                        groupMembers_ = other.groupMembers_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureGroupMembersIsMutable();
                        groupMembers_.addAll(other.groupMembers_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groupMembers_.isEmpty()) {
                    if (groupMembersBuilder_.isEmpty()) {
                        groupMembersBuilder_.dispose();
                        groupMembersBuilder_ = null;
                        groupMembers_ = other.groupMembers_;
                        bitField0_ &= ~0x00000001;
                        groupMembersBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getGroupMembersFieldBuilder()
                                        : null;
                    } else {
                        groupMembersBuilder_.addAllMessages(other.groupMembers_);
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
                            GroupMember m =
                                    input.readMessage(GroupMember.parser(), extensionRegistry);
                            if (groupMembersBuilder_ == null) {
                                ensureGroupMembersIsMutable();
                                groupMembers_.add(m);
                            } else {
                                groupMembersBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupMember> groupMembers_ =
                java.util.Collections.emptyList();

        private void ensureGroupMembersIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                groupMembers_ = new java.util.ArrayList<>(groupMembers_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupMember, im.turms.server.common.access.client.dto.model.group.GroupMember.Builder, im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder> groupMembersBuilder_;

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupMember> getGroupMembersList() {
            if (groupMembersBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groupMembers_);
            } else {
                return groupMembersBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public int getGroupMembersCount() {
            if (groupMembersBuilder_ == null) {
                return groupMembers_.size();
            } else {
                return groupMembersBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupMember getGroupMembers(
                int index) {
            if (groupMembersBuilder_ == null) {
                return groupMembers_.get(index);
            } else {
                return groupMembersBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder setGroupMembers(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupMember value) {
            if (groupMembersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupMembersIsMutable();
                groupMembers_.set(index, value);
                onChanged();
            } else {
                groupMembersBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder setGroupMembers(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupMember.Builder builderForValue) {
            if (groupMembersBuilder_ == null) {
                ensureGroupMembersIsMutable();
                groupMembers_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupMembersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder addGroupMembers(
                im.turms.server.common.access.client.dto.model.group.GroupMember value) {
            if (groupMembersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupMembersIsMutable();
                groupMembers_.add(value);
                onChanged();
            } else {
                groupMembersBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder addGroupMembers(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupMember value) {
            if (groupMembersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupMembersIsMutable();
                groupMembers_.add(index, value);
                onChanged();
            } else {
                groupMembersBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder addGroupMembers(
                im.turms.server.common.access.client.dto.model.group.GroupMember.Builder builderForValue) {
            if (groupMembersBuilder_ == null) {
                ensureGroupMembersIsMutable();
                groupMembers_.add(builderForValue.build());
                onChanged();
            } else {
                groupMembersBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder addGroupMembers(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupMember.Builder builderForValue) {
            if (groupMembersBuilder_ == null) {
                ensureGroupMembersIsMutable();
                groupMembers_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupMembersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder addAllGroupMembers(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.group.GroupMember> values) {
            if (groupMembersBuilder_ == null) {
                ensureGroupMembersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupMembers_);
                onChanged();
            } else {
                groupMembersBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder clearGroupMembers() {
            if (groupMembersBuilder_ == null) {
                groupMembers_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                groupMembersBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public Builder removeGroupMembers(int index) {
            if (groupMembersBuilder_ == null) {
                ensureGroupMembersIsMutable();
                groupMembers_.remove(index);
                onChanged();
            } else {
                groupMembersBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupMember.Builder getGroupMembersBuilder(
                int index) {
            return getGroupMembersFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder getGroupMembersOrBuilder(
                int index) {
            if (groupMembersBuilder_ == null) {
                return groupMembers_.get(index);
            } else {
                return groupMembersBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder> getGroupMembersOrBuilderList() {
            if (groupMembersBuilder_ != null) {
                return groupMembersBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groupMembers_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupMember.Builder addGroupMembersBuilder() {
            return getGroupMembersFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.group.GroupMember
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupMember.Builder addGroupMembersBuilder(
                int index) {
            return getGroupMembersFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.group.GroupMember
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupMember group_members = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupMember.Builder> getGroupMembersBuilderList() {
            return getGroupMembersFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupMember, im.turms.server.common.access.client.dto.model.group.GroupMember.Builder, im.turms.server.common.access.client.dto.model.group.GroupMemberOrBuilder> getGroupMembersFieldBuilder() {
            if (groupMembersBuilder_ == null) {
                groupMembersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        groupMembers_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                groupMembers_ = null;
            }
            return groupMembersBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupMembersWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupMembersWithVersion)
    private static final im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupMembersWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupMembersWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupMembersWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupMembersWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}