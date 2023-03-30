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
 * Protobuf type {@code im.turms.proto.GroupsWithVersion}
 */
public final class GroupsWithVersion extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupsWithVersion)
        GroupsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupsWithVersion.newBuilder() to construct.
    private GroupsWithVersion(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupsWithVersion() {
        groups_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass.internal_static_im_turms_proto_GroupsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass.internal_static_im_turms_proto_GroupsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int GROUPS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.group.Group> groups_;

    /**
     * <code>repeated .im.turms.proto.Group groups = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.group.Group> getGroupsList() {
        return groups_;
    }

    /**
     * <code>repeated .im.turms.proto.Group groups = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupOrBuilder> getGroupsOrBuilderList() {
        return groups_;
    }

    /**
     * <code>repeated .im.turms.proto.Group groups = 1;</code>
     */
    @java.lang.Override
    public int getGroupsCount() {
        return groups_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Group groups = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.Group getGroups(int index) {
        return groups_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Group groups = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupOrBuilder getGroupsOrBuilder(
            int index) {
        return groups_.get(index);
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
        for (Group group : groups_) {
            output.writeMessage(1, group);
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
        for (Group group : groups_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, group);
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
        if (!(obj instanceof GroupsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getGroupsList().equals(other.getGroupsList())) {
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
        if (getGroupsCount() > 0) {
            hash = (37 * hash) + GROUPS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.group.GroupsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.GroupsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupsWithVersion)
            im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass.internal_static_im_turms_proto_GroupsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass.internal_static_im_turms_proto_GroupsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (groupsBuilder_ == null) {
                groups_ = java.util.Collections.emptyList();
            } else {
                groups_ = null;
                groupsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass.internal_static_im_turms_proto_GroupsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion build() {
            im.turms.server.common.access.client.dto.model.group.GroupsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.group.GroupsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupsWithVersion result) {
            if (groupsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    groups_ = java.util.Collections.unmodifiableList(groups_);
                    bitField0_ &= ~0x00000001;
                }
                result.groups_ = groups_;
            } else {
                result.groups_ = groupsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupsWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (groupsBuilder_ == null) {
                if (!other.groups_.isEmpty()) {
                    if (groups_.isEmpty()) {
                        groups_ = other.groups_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureGroupsIsMutable();
                        groups_.addAll(other.groups_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groups_.isEmpty()) {
                    if (groupsBuilder_.isEmpty()) {
                        groupsBuilder_.dispose();
                        groupsBuilder_ = null;
                        groups_ = other.groups_;
                        bitField0_ &= ~0x00000001;
                        groupsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getGroupsFieldBuilder()
                                        : null;
                    } else {
                        groupsBuilder_.addAllMessages(other.groups_);
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
                            Group m = input.readMessage(Group.parser(), extensionRegistry);
                            if (groupsBuilder_ == null) {
                                ensureGroupsIsMutable();
                                groups_.add(m);
                            } else {
                                groupsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.group.Group> groups_ =
                java.util.Collections.emptyList();

        private void ensureGroupsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                groups_ = new java.util.ArrayList<>(groups_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.Group, im.turms.server.common.access.client.dto.model.group.Group.Builder, im.turms.server.common.access.client.dto.model.group.GroupOrBuilder> groupsBuilder_;

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.Group> getGroupsList() {
            if (groupsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groups_);
            } else {
                return groupsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public int getGroupsCount() {
            if (groupsBuilder_ == null) {
                return groups_.size();
            } else {
                return groupsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.Group getGroups(int index) {
            if (groupsBuilder_ == null) {
                return groups_.get(index);
            } else {
                return groupsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder setGroups(
                int index,
                im.turms.server.common.access.client.dto.model.group.Group value) {
            if (groupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupsIsMutable();
                groups_.set(index, value);
                onChanged();
            } else {
                groupsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder setGroups(
                int index,
                im.turms.server.common.access.client.dto.model.group.Group.Builder builderForValue) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder addGroups(im.turms.server.common.access.client.dto.model.group.Group value) {
            if (groupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupsIsMutable();
                groups_.add(value);
                onChanged();
            } else {
                groupsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder addGroups(
                int index,
                im.turms.server.common.access.client.dto.model.group.Group value) {
            if (groupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupsIsMutable();
                groups_.add(index, value);
                onChanged();
            } else {
                groupsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder addGroups(
                im.turms.server.common.access.client.dto.model.group.Group.Builder builderForValue) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.add(builderForValue.build());
                onChanged();
            } else {
                groupsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder addGroups(
                int index,
                im.turms.server.common.access.client.dto.model.group.Group.Builder builderForValue) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder addAllGroups(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.group.Group> values) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groups_);
                onChanged();
            } else {
                groupsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder clearGroups() {
            if (groupsBuilder_ == null) {
                groups_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                groupsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public Builder removeGroups(int index) {
            if (groupsBuilder_ == null) {
                ensureGroupsIsMutable();
                groups_.remove(index);
                onChanged();
            } else {
                groupsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.Group.Builder getGroupsBuilder(
                int index) {
            return getGroupsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupOrBuilder getGroupsOrBuilder(
                int index) {
            if (groupsBuilder_ == null) {
                return groups_.get(index);
            } else {
                return groupsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupOrBuilder> getGroupsOrBuilderList() {
            if (groupsBuilder_ != null) {
                return groupsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groups_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.Group.Builder addGroupsBuilder() {
            return getGroupsFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.group.Group
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.Group.Builder addGroupsBuilder(
                int index) {
            return getGroupsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.group.Group
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Group groups = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.Group.Builder> getGroupsBuilderList() {
            return getGroupsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.Group, im.turms.server.common.access.client.dto.model.group.Group.Builder, im.turms.server.common.access.client.dto.model.group.GroupOrBuilder> getGroupsFieldBuilder() {
            if (groupsBuilder_ == null) {
                groupsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        groups_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                groups_ = null;
            }
            return groupsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.group.GroupsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.group.GroupsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}