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
 * Protobuf type {@code im.turms.proto.UserRelationshipGroupsWithVersion}
 */
public final class UserRelationshipGroupsWithVersion extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserRelationshipGroupsWithVersion)
        UserRelationshipGroupsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserRelationshipGroupsWithVersion.newBuilder() to construct.
    private UserRelationshipGroupsWithVersion(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserRelationshipGroupsWithVersion() {
        userRelationshipGroups_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserRelationshipGroupsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipGroupsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipGroupsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int USER_RELATIONSHIP_GROUPS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup> userRelationshipGroups_;

    /**
     * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup> getUserRelationshipGroupsList() {
        return userRelationshipGroups_;
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupOrBuilder> getUserRelationshipGroupsOrBuilderList() {
        return userRelationshipGroups_;
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
     */
    @java.lang.Override
    public int getUserRelationshipGroupsCount() {
        return userRelationshipGroups_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup getUserRelationshipGroups(
            int index) {
        return userRelationshipGroups_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupOrBuilder getUserRelationshipGroupsOrBuilder(
            int index) {
        return userRelationshipGroups_.get(index);
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
        for (UserRelationshipGroup userRelationshipGroup : userRelationshipGroups_) {
            output.writeMessage(1, userRelationshipGroup);
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
        for (UserRelationshipGroup userRelationshipGroup : userRelationshipGroups_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1,
                    userRelationshipGroup);
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
        if (!(obj instanceof UserRelationshipGroupsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getUserRelationshipGroupsList().equals(other.getUserRelationshipGroupsList())) {
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
        if (getUserRelationshipGroupsCount() > 0) {
            hash = (37 * hash) + USER_RELATIONSHIP_GROUPS_FIELD_NUMBER;
            hash = (53 * hash) + getUserRelationshipGroupsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.UserRelationshipGroupsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserRelationshipGroupsWithVersion)
            im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipGroupsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipGroupsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (userRelationshipGroupsBuilder_ == null) {
                userRelationshipGroups_ = java.util.Collections.emptyList();
            } else {
                userRelationshipGroups_ = null;
                userRelationshipGroupsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipGroupsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion build() {
            im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion result) {
            if (userRelationshipGroupsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    userRelationshipGroups_ =
                            java.util.Collections.unmodifiableList(userRelationshipGroups_);
                    bitField0_ &= ~0x00000001;
                }
                result.userRelationshipGroups_ = userRelationshipGroups_;
            } else {
                result.userRelationshipGroups_ = userRelationshipGroupsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (userRelationshipGroupsBuilder_ == null) {
                if (!other.userRelationshipGroups_.isEmpty()) {
                    if (userRelationshipGroups_.isEmpty()) {
                        userRelationshipGroups_ = other.userRelationshipGroups_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureUserRelationshipGroupsIsMutable();
                        userRelationshipGroups_.addAll(other.userRelationshipGroups_);
                    }
                    onChanged();
                }
            } else {
                if (!other.userRelationshipGroups_.isEmpty()) {
                    if (userRelationshipGroupsBuilder_.isEmpty()) {
                        userRelationshipGroupsBuilder_.dispose();
                        userRelationshipGroupsBuilder_ = null;
                        userRelationshipGroups_ = other.userRelationshipGroups_;
                        bitField0_ &= ~0x00000001;
                        userRelationshipGroupsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getUserRelationshipGroupsFieldBuilder()
                                        : null;
                    } else {
                        userRelationshipGroupsBuilder_
                                .addAllMessages(other.userRelationshipGroups_);
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
                            UserRelationshipGroup m = input
                                    .readMessage(UserRelationshipGroup.parser(), extensionRegistry);
                            if (userRelationshipGroupsBuilder_ == null) {
                                ensureUserRelationshipGroupsIsMutable();
                                userRelationshipGroups_.add(m);
                            } else {
                                userRelationshipGroupsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup> userRelationshipGroups_ =
                java.util.Collections.emptyList();

        private void ensureUserRelationshipGroupsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                userRelationshipGroups_ = new java.util.ArrayList<>(userRelationshipGroups_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupOrBuilder> userRelationshipGroupsBuilder_;

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup> getUserRelationshipGroupsList() {
            if (userRelationshipGroupsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(userRelationshipGroups_);
            } else {
                return userRelationshipGroupsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public int getUserRelationshipGroupsCount() {
            if (userRelationshipGroupsBuilder_ == null) {
                return userRelationshipGroups_.size();
            } else {
                return userRelationshipGroupsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup getUserRelationshipGroups(
                int index) {
            if (userRelationshipGroupsBuilder_ == null) {
                return userRelationshipGroups_.get(index);
            } else {
                return userRelationshipGroupsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder setUserRelationshipGroups(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup value) {
            if (userRelationshipGroupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.set(index, value);
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder setUserRelationshipGroups(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder builderForValue) {
            if (userRelationshipGroupsBuilder_ == null) {
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.set(index, builderForValue.build());
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder addUserRelationshipGroups(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup value) {
            if (userRelationshipGroupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.add(value);
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder addUserRelationshipGroups(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup value) {
            if (userRelationshipGroupsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.add(index, value);
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder addUserRelationshipGroups(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder builderForValue) {
            if (userRelationshipGroupsBuilder_ == null) {
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.add(builderForValue.build());
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder addUserRelationshipGroups(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder builderForValue) {
            if (userRelationshipGroupsBuilder_ == null) {
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.add(index, builderForValue.build());
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder addAllUserRelationshipGroups(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup> values) {
            if (userRelationshipGroupsBuilder_ == null) {
                ensureUserRelationshipGroupsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values,
                        userRelationshipGroups_);
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder clearUserRelationshipGroups() {
            if (userRelationshipGroupsBuilder_ == null) {
                userRelationshipGroups_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public Builder removeUserRelationshipGroups(int index) {
            if (userRelationshipGroupsBuilder_ == null) {
                ensureUserRelationshipGroupsIsMutable();
                userRelationshipGroups_.remove(index);
                onChanged();
            } else {
                userRelationshipGroupsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder getUserRelationshipGroupsBuilder(
                int index) {
            return getUserRelationshipGroupsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupOrBuilder getUserRelationshipGroupsOrBuilder(
                int index) {
            if (userRelationshipGroupsBuilder_ == null) {
                return userRelationshipGroups_.get(index);
            } else {
                return userRelationshipGroupsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupOrBuilder> getUserRelationshipGroupsOrBuilderList() {
            if (userRelationshipGroupsBuilder_ != null) {
                return userRelationshipGroupsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(userRelationshipGroups_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder addUserRelationshipGroupsBuilder() {
            return getUserRelationshipGroupsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder addUserRelationshipGroupsBuilder(
                int index) {
            return getUserRelationshipGroupsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationshipGroup user_relationship_groups = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder> getUserRelationshipGroupsBuilderList() {
            return getUserRelationshipGroupsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupOrBuilder> getUserRelationshipGroupsFieldBuilder() {
            if (userRelationshipGroupsBuilder_ == null) {
                userRelationshipGroupsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        userRelationshipGroups_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                userRelationshipGroups_ = null;
            }
            return userRelationshipGroupsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserRelationshipGroupsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserRelationshipGroupsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserRelationshipGroupsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserRelationshipGroupsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserRelationshipGroupsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserRelationshipGroupsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}