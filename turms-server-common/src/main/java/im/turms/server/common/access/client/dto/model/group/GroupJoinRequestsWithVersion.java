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
 * Protobuf type {@code im.turms.proto.GroupJoinRequestsWithVersion}
 */
public final class GroupJoinRequestsWithVersion extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupJoinRequestsWithVersion)
        GroupJoinRequestsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupJoinRequestsWithVersion.newBuilder() to construct.
    private GroupJoinRequestsWithVersion(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupJoinRequestsWithVersion() {
        groupJoinRequests_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupJoinRequestsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinRequestsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinRequestsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int GROUP_JOIN_REQUESTS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest> groupJoinRequests_;

    /**
     * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest> getGroupJoinRequestsList() {
        return groupJoinRequests_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinRequestOrBuilder> getGroupJoinRequestsOrBuilderList() {
        return groupJoinRequests_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
     */
    @java.lang.Override
    public int getGroupJoinRequestsCount() {
        return groupJoinRequests_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinRequest getGroupJoinRequests(
            int index) {
        return groupJoinRequests_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestOrBuilder getGroupJoinRequestsOrBuilder(
            int index) {
        return groupJoinRequests_.get(index);
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
        for (GroupJoinRequest groupJoinRequest : groupJoinRequests_) {
            output.writeMessage(1, groupJoinRequest);
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
        for (GroupJoinRequest groupJoinRequest : groupJoinRequests_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, groupJoinRequest);
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
        if (!(obj instanceof GroupJoinRequestsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getGroupJoinRequestsList().equals(other.getGroupJoinRequestsList())) {
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
        if (getGroupJoinRequestsCount() > 0) {
            hash = (37 * hash) + GROUP_JOIN_REQUESTS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupJoinRequestsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.GroupJoinRequestsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupJoinRequestsWithVersion)
            im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinRequestsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinRequestsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (groupJoinRequestsBuilder_ == null) {
                groupJoinRequests_ = java.util.Collections.emptyList();
            } else {
                groupJoinRequests_ = null;
                groupJoinRequestsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass.internal_static_im_turms_proto_GroupJoinRequestsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion build() {
            im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion result) {
            if (groupJoinRequestsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    groupJoinRequests_ = java.util.Collections.unmodifiableList(groupJoinRequests_);
                    bitField0_ &= ~0x00000001;
                }
                result.groupJoinRequests_ = groupJoinRequests_;
            } else {
                result.groupJoinRequests_ = groupJoinRequestsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (groupJoinRequestsBuilder_ == null) {
                if (!other.groupJoinRequests_.isEmpty()) {
                    if (groupJoinRequests_.isEmpty()) {
                        groupJoinRequests_ = other.groupJoinRequests_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureGroupJoinRequestsIsMutable();
                        groupJoinRequests_.addAll(other.groupJoinRequests_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groupJoinRequests_.isEmpty()) {
                    if (groupJoinRequestsBuilder_.isEmpty()) {
                        groupJoinRequestsBuilder_.dispose();
                        groupJoinRequestsBuilder_ = null;
                        groupJoinRequests_ = other.groupJoinRequests_;
                        bitField0_ &= ~0x00000001;
                        groupJoinRequestsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getGroupJoinRequestsFieldBuilder()
                                        : null;
                    } else {
                        groupJoinRequestsBuilder_.addAllMessages(other.groupJoinRequests_);
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
                            GroupJoinRequest m =
                                    input.readMessage(GroupJoinRequest.parser(), extensionRegistry);
                            if (groupJoinRequestsBuilder_ == null) {
                                ensureGroupJoinRequestsIsMutable();
                                groupJoinRequests_.add(m);
                            } else {
                                groupJoinRequestsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest> groupJoinRequests_ =
                java.util.Collections.emptyList();

        private void ensureGroupJoinRequestsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                groupJoinRequests_ = new java.util.ArrayList<>(groupJoinRequests_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest, im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinRequestOrBuilder> groupJoinRequestsBuilder_;

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest> getGroupJoinRequestsList() {
            if (groupJoinRequestsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groupJoinRequests_);
            } else {
                return groupJoinRequestsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public int getGroupJoinRequestsCount() {
            if (groupJoinRequestsBuilder_ == null) {
                return groupJoinRequests_.size();
            } else {
                return groupJoinRequestsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequest getGroupJoinRequests(
                int index) {
            if (groupJoinRequestsBuilder_ == null) {
                return groupJoinRequests_.get(index);
            } else {
                return groupJoinRequestsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder setGroupJoinRequests(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequest value) {
            if (groupJoinRequestsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.set(index, value);
                onChanged();
            } else {
                groupJoinRequestsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder setGroupJoinRequests(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder builderForValue) {
            if (groupJoinRequestsBuilder_ == null) {
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupJoinRequestsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder addGroupJoinRequests(
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequest value) {
            if (groupJoinRequestsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.add(value);
                onChanged();
            } else {
                groupJoinRequestsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder addGroupJoinRequests(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequest value) {
            if (groupJoinRequestsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.add(index, value);
                onChanged();
            } else {
                groupJoinRequestsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder addGroupJoinRequests(
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder builderForValue) {
            if (groupJoinRequestsBuilder_ == null) {
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.add(builderForValue.build());
                onChanged();
            } else {
                groupJoinRequestsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder addGroupJoinRequests(
                int index,
                im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder builderForValue) {
            if (groupJoinRequestsBuilder_ == null) {
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupJoinRequestsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder addAllGroupJoinRequests(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinRequest> values) {
            if (groupJoinRequestsBuilder_ == null) {
                ensureGroupJoinRequestsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupJoinRequests_);
                onChanged();
            } else {
                groupJoinRequestsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder clearGroupJoinRequests() {
            if (groupJoinRequestsBuilder_ == null) {
                groupJoinRequests_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                groupJoinRequestsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public Builder removeGroupJoinRequests(int index) {
            if (groupJoinRequestsBuilder_ == null) {
                ensureGroupJoinRequestsIsMutable();
                groupJoinRequests_.remove(index);
                onChanged();
            } else {
                groupJoinRequestsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder getGroupJoinRequestsBuilder(
                int index) {
            return getGroupJoinRequestsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestOrBuilder getGroupJoinRequestsOrBuilder(
                int index) {
            if (groupJoinRequestsBuilder_ == null) {
                return groupJoinRequests_.get(index);
            } else {
                return groupJoinRequestsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.group.GroupJoinRequestOrBuilder> getGroupJoinRequestsOrBuilderList() {
            if (groupJoinRequestsBuilder_ != null) {
                return groupJoinRequestsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groupJoinRequests_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder addGroupJoinRequestsBuilder() {
            return getGroupJoinRequestsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinRequest
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder addGroupJoinRequestsBuilder(
                int index) {
            return getGroupJoinRequestsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.group.GroupJoinRequest
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupJoinRequest group_join_requests = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder> getGroupJoinRequestsBuilderList() {
            return getGroupJoinRequestsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinRequest, im.turms.server.common.access.client.dto.model.group.GroupJoinRequest.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinRequestOrBuilder> getGroupJoinRequestsFieldBuilder() {
            if (groupJoinRequestsBuilder_ == null) {
                groupJoinRequestsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        groupJoinRequests_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                groupJoinRequests_ = null;
            }
            return groupJoinRequestsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupJoinRequestsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupJoinRequestsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupJoinRequestsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupJoinRequestsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<GroupJoinRequestsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupJoinRequestsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}