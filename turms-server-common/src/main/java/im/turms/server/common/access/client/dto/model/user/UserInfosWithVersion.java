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
 * Protobuf type {@code im.turms.proto.UserInfosWithVersion}
 */
public final class UserInfosWithVersion extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserInfosWithVersion)
        UserInfosWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserInfosWithVersion.newBuilder() to construct.
    private UserInfosWithVersion(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserInfosWithVersion() {
        userInfos_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserInfosWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass.internal_static_im_turms_proto_UserInfosWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass.internal_static_im_turms_proto_UserInfosWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.class,
                        im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int USER_INFOS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> userInfos_;

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> getUserInfosList() {
        return userInfos_;
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosOrBuilderList() {
        return userInfos_;
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
     */
    @java.lang.Override
    public int getUserInfosCount() {
        return userInfos_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfo getUserInfos(int index) {
        return userInfos_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getUserInfosOrBuilder(
            int index) {
        return userInfos_.get(index);
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
        for (UserInfo userInfo : userInfos_) {
            output.writeMessage(1, userInfo);
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
        for (UserInfo userInfo : userInfos_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, userInfo);
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
        if (!(obj instanceof UserInfosWithVersion other)) {
            return super.equals(obj);
        }

        if (!getUserInfosList().equals(other.getUserInfosList())) {
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
        if (getUserInfosCount() > 0) {
            hash = (37 * hash) + USER_INFOS_FIELD_NUMBER;
            hash = (53 * hash) + getUserInfosList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.UserInfosWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserInfosWithVersion)
            im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass.internal_static_im_turms_proto_UserInfosWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass.internal_static_im_turms_proto_UserInfosWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.class,
                            im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (userInfosBuilder_ == null) {
                userInfos_ = java.util.Collections.emptyList();
            } else {
                userInfos_ = null;
                userInfosBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass.internal_static_im_turms_proto_UserInfosWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion build() {
            im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion result =
                    new im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion result) {
            if (userInfosBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    userInfos_ = java.util.Collections.unmodifiableList(userInfos_);
                    bitField0_ &= ~0x00000001;
                }
                result.userInfos_ = userInfos_;
            } else {
                result.userInfos_ = userInfosBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (userInfosBuilder_ == null) {
                if (!other.userInfos_.isEmpty()) {
                    if (userInfos_.isEmpty()) {
                        userInfos_ = other.userInfos_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureUserInfosIsMutable();
                        userInfos_.addAll(other.userInfos_);
                    }
                    onChanged();
                }
            } else {
                if (!other.userInfos_.isEmpty()) {
                    if (userInfosBuilder_.isEmpty()) {
                        userInfosBuilder_.dispose();
                        userInfosBuilder_ = null;
                        userInfos_ = other.userInfos_;
                        bitField0_ &= ~0x00000001;
                        userInfosBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getUserInfosFieldBuilder()
                                        : null;
                    } else {
                        userInfosBuilder_.addAllMessages(other.userInfos_);
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
                            UserInfo m = input.readMessage(UserInfo.parser(), extensionRegistry);
                            if (userInfosBuilder_ == null) {
                                ensureUserInfosIsMutable();
                                userInfos_.add(m);
                            } else {
                                userInfosBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> userInfos_ =
                java.util.Collections.emptyList();

        private void ensureUserInfosIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                userInfos_ = new java.util.ArrayList<>(userInfos_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserInfo, im.turms.server.common.access.client.dto.model.user.UserInfo.Builder, im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> userInfosBuilder_;

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> getUserInfosList() {
            if (userInfosBuilder_ == null) {
                return java.util.Collections.unmodifiableList(userInfos_);
            } else {
                return userInfosBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public int getUserInfosCount() {
            if (userInfosBuilder_ == null) {
                return userInfos_.size();
            } else {
                return userInfosBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo getUserInfos(
                int index) {
            if (userInfosBuilder_ == null) {
                return userInfos_.get(index);
            } else {
                return userInfosBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder setUserInfos(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserInfo value) {
            if (userInfosBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserInfosIsMutable();
                userInfos_.set(index, value);
                onChanged();
            } else {
                userInfosBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder setUserInfos(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserInfo.Builder builderForValue) {
            if (userInfosBuilder_ == null) {
                ensureUserInfosIsMutable();
                userInfos_.set(index, builderForValue.build());
                onChanged();
            } else {
                userInfosBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder addUserInfos(
                im.turms.server.common.access.client.dto.model.user.UserInfo value) {
            if (userInfosBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserInfosIsMutable();
                userInfos_.add(value);
                onChanged();
            } else {
                userInfosBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder addUserInfos(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserInfo value) {
            if (userInfosBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserInfosIsMutable();
                userInfos_.add(index, value);
                onChanged();
            } else {
                userInfosBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder addUserInfos(
                im.turms.server.common.access.client.dto.model.user.UserInfo.Builder builderForValue) {
            if (userInfosBuilder_ == null) {
                ensureUserInfosIsMutable();
                userInfos_.add(builderForValue.build());
                onChanged();
            } else {
                userInfosBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder addUserInfos(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserInfo.Builder builderForValue) {
            if (userInfosBuilder_ == null) {
                ensureUserInfosIsMutable();
                userInfos_.add(index, builderForValue.build());
                onChanged();
            } else {
                userInfosBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder addAllUserInfos(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.user.UserInfo> values) {
            if (userInfosBuilder_ == null) {
                ensureUserInfosIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, userInfos_);
                onChanged();
            } else {
                userInfosBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder clearUserInfos() {
            if (userInfosBuilder_ == null) {
                userInfos_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                userInfosBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public Builder removeUserInfos(int index) {
            if (userInfosBuilder_ == null) {
                ensureUserInfosIsMutable();
                userInfos_.remove(index);
                onChanged();
            } else {
                userInfosBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder getUserInfosBuilder(
                int index) {
            return getUserInfosFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getUserInfosOrBuilder(
                int index) {
            if (userInfosBuilder_ == null) {
                return userInfos_.get(index);
            } else {
                return userInfosBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosOrBuilderList() {
            if (userInfosBuilder_ != null) {
                return userInfosBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(userInfos_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder addUserInfosBuilder() {
            return getUserInfosFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.user.UserInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder addUserInfosBuilder(
                int index) {
            return getUserInfosFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.UserInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo.Builder> getUserInfosBuilderList() {
            return getUserInfosFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserInfo, im.turms.server.common.access.client.dto.model.user.UserInfo.Builder, im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosFieldBuilder() {
            if (userInfosBuilder_ == null) {
                userInfosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        userInfos_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                userInfos_ = null;
            }
            return userInfosBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserInfosWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserInfosWithVersion)
    private static final im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserInfosWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserInfosWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserInfosWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserInfosWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}