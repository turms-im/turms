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
 * Protobuf type {@code im.turms.proto.UserFriendRequestsWithVersion}
 */
public final class UserFriendRequestsWithVersion extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserFriendRequestsWithVersion)
        UserFriendRequestsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserFriendRequestsWithVersion.newBuilder() to construct.
    private UserFriendRequestsWithVersion(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserFriendRequestsWithVersion() {
        userFriendRequests_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserFriendRequestsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass.internal_static_im_turms_proto_UserFriendRequestsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass.internal_static_im_turms_proto_UserFriendRequestsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int USER_FRIEND_REQUESTS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.UserFriendRequest> userFriendRequests_;

    /**
     * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.UserFriendRequest> getUserFriendRequestsList() {
        return userFriendRequests_;
    }

    /**
     * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder> getUserFriendRequestsOrBuilderList() {
        return userFriendRequests_;
    }

    /**
     * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
     */
    @java.lang.Override
    public int getUserFriendRequestsCount() {
        return userFriendRequests_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserFriendRequest getUserFriendRequests(
            int index) {
        return userFriendRequests_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder getUserFriendRequestsOrBuilder(
            int index) {
        return userFriendRequests_.get(index);
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
        for (UserFriendRequest userFriendRequest : userFriendRequests_) {
            output.writeMessage(1, userFriendRequest);
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
        for (UserFriendRequest userFriendRequest : userFriendRequests_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, userFriendRequest);
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
        if (!(obj instanceof UserFriendRequestsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getUserFriendRequestsList().equals(other.getUserFriendRequestsList())) {
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
        if (getUserFriendRequestsCount() > 0) {
            hash = (37 * hash) + USER_FRIEND_REQUESTS_FIELD_NUMBER;
            hash = (53 * hash) + getUserFriendRequestsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.UserFriendRequestsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserFriendRequestsWithVersion)
            im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass.internal_static_im_turms_proto_UserFriendRequestsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass.internal_static_im_turms_proto_UserFriendRequestsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (userFriendRequestsBuilder_ == null) {
                userFriendRequests_ = java.util.Collections.emptyList();
            } else {
                userFriendRequests_ = null;
                userFriendRequestsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass.internal_static_im_turms_proto_UserFriendRequestsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion build() {
            im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion result) {
            if (userFriendRequestsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    userFriendRequests_ =
                            java.util.Collections.unmodifiableList(userFriendRequests_);
                    bitField0_ &= ~0x00000001;
                }
                result.userFriendRequests_ = userFriendRequests_;
            } else {
                result.userFriendRequests_ = userFriendRequestsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (userFriendRequestsBuilder_ == null) {
                if (!other.userFriendRequests_.isEmpty()) {
                    if (userFriendRequests_.isEmpty()) {
                        userFriendRequests_ = other.userFriendRequests_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureUserFriendRequestsIsMutable();
                        userFriendRequests_.addAll(other.userFriendRequests_);
                    }
                    onChanged();
                }
            } else {
                if (!other.userFriendRequests_.isEmpty()) {
                    if (userFriendRequestsBuilder_.isEmpty()) {
                        userFriendRequestsBuilder_.dispose();
                        userFriendRequestsBuilder_ = null;
                        userFriendRequests_ = other.userFriendRequests_;
                        bitField0_ &= ~0x00000001;
                        userFriendRequestsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getUserFriendRequestsFieldBuilder()
                                        : null;
                    } else {
                        userFriendRequestsBuilder_.addAllMessages(other.userFriendRequests_);
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
                            UserFriendRequest m = input.readMessage(UserFriendRequest.parser(),
                                    extensionRegistry);
                            if (userFriendRequestsBuilder_ == null) {
                                ensureUserFriendRequestsIsMutable();
                                userFriendRequests_.add(m);
                            } else {
                                userFriendRequestsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.user.UserFriendRequest> userFriendRequests_ =
                java.util.Collections.emptyList();

        private void ensureUserFriendRequestsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                userFriendRequests_ = new java.util.ArrayList<>(userFriendRequests_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserFriendRequest, im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder, im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder> userFriendRequestsBuilder_;

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserFriendRequest> getUserFriendRequestsList() {
            if (userFriendRequestsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(userFriendRequests_);
            } else {
                return userFriendRequestsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public int getUserFriendRequestsCount() {
            if (userFriendRequestsBuilder_ == null) {
                return userFriendRequests_.size();
            } else {
                return userFriendRequestsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest getUserFriendRequests(
                int index) {
            if (userFriendRequestsBuilder_ == null) {
                return userFriendRequests_.get(index);
            } else {
                return userFriendRequestsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder setUserFriendRequests(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest value) {
            if (userFriendRequestsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.set(index, value);
                onChanged();
            } else {
                userFriendRequestsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder setUserFriendRequests(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder builderForValue) {
            if (userFriendRequestsBuilder_ == null) {
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.set(index, builderForValue.build());
                onChanged();
            } else {
                userFriendRequestsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder addUserFriendRequests(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest value) {
            if (userFriendRequestsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.add(value);
                onChanged();
            } else {
                userFriendRequestsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder addUserFriendRequests(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest value) {
            if (userFriendRequestsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.add(index, value);
                onChanged();
            } else {
                userFriendRequestsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder addUserFriendRequests(
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder builderForValue) {
            if (userFriendRequestsBuilder_ == null) {
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.add(builderForValue.build());
                onChanged();
            } else {
                userFriendRequestsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder addUserFriendRequests(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder builderForValue) {
            if (userFriendRequestsBuilder_ == null) {
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.add(index, builderForValue.build());
                onChanged();
            } else {
                userFriendRequestsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder addAllUserFriendRequests(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.user.UserFriendRequest> values) {
            if (userFriendRequestsBuilder_ == null) {
                ensureUserFriendRequestsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, userFriendRequests_);
                onChanged();
            } else {
                userFriendRequestsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder clearUserFriendRequests() {
            if (userFriendRequestsBuilder_ == null) {
                userFriendRequests_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                userFriendRequestsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public Builder removeUserFriendRequests(int index) {
            if (userFriendRequestsBuilder_ == null) {
                ensureUserFriendRequestsIsMutable();
                userFriendRequests_.remove(index);
                onChanged();
            } else {
                userFriendRequestsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder getUserFriendRequestsBuilder(
                int index) {
            return getUserFriendRequestsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder getUserFriendRequestsOrBuilder(
                int index) {
            if (userFriendRequestsBuilder_ == null) {
                return userFriendRequests_.get(index);
            } else {
                return userFriendRequestsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder> getUserFriendRequestsOrBuilderList() {
            if (userFriendRequestsBuilder_ != null) {
                return userFriendRequestsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(userFriendRequests_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder addUserFriendRequestsBuilder() {
            return getUserFriendRequestsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.user.UserFriendRequest
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder addUserFriendRequestsBuilder(
                int index) {
            return getUserFriendRequestsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.UserFriendRequest
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserFriendRequest user_friend_requests = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder> getUserFriendRequestsBuilderList() {
            return getUserFriendRequestsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserFriendRequest, im.turms.server.common.access.client.dto.model.user.UserFriendRequest.Builder, im.turms.server.common.access.client.dto.model.user.UserFriendRequestOrBuilder> getUserFriendRequestsFieldBuilder() {
            if (userFriendRequestsBuilder_ == null) {
                userFriendRequestsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        userFriendRequests_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                userFriendRequests_ = null;
            }
            return userFriendRequestsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserFriendRequestsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserFriendRequestsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserFriendRequestsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserFriendRequestsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserFriendRequestsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserFriendRequestsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}