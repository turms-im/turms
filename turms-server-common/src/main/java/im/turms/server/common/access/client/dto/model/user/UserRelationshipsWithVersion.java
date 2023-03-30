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
 * Protobuf type {@code im.turms.proto.UserRelationshipsWithVersion}
 */
public final class UserRelationshipsWithVersion extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserRelationshipsWithVersion)
        UserRelationshipsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserRelationshipsWithVersion.newBuilder() to construct.
    private UserRelationshipsWithVersion(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserRelationshipsWithVersion() {
        userRelationships_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserRelationshipsWithVersion();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int USER_RELATIONSHIPS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationship> userRelationships_;

    /**
     * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationship> getUserRelationshipsList() {
        return userRelationships_;
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder> getUserRelationshipsOrBuilderList() {
        return userRelationships_;
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
     */
    @java.lang.Override
    public int getUserRelationshipsCount() {
        return userRelationships_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationship getUserRelationships(
            int index) {
        return userRelationships_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder getUserRelationshipsOrBuilder(
            int index) {
        return userRelationships_.get(index);
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
        for (UserRelationship userRelationship : userRelationships_) {
            output.writeMessage(1, userRelationship);
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
        for (UserRelationship userRelationship : userRelationships_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, userRelationship);
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
        if (!(obj instanceof UserRelationshipsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getUserRelationshipsList().equals(other.getUserRelationshipsList())) {
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
        if (getUserRelationshipsCount() > 0) {
            hash = (37 * hash) + USER_RELATIONSHIPS_FIELD_NUMBER;
            hash = (53 * hash) + getUserRelationshipsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.UserRelationshipsWithVersion}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserRelationshipsWithVersion)
            im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (userRelationshipsBuilder_ == null) {
                userRelationships_ = java.util.Collections.emptyList();
            } else {
                userRelationships_ = null;
                userRelationshipsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass.internal_static_im_turms_proto_UserRelationshipsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion build() {
            im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion result) {
            if (userRelationshipsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    userRelationships_ = java.util.Collections.unmodifiableList(userRelationships_);
                    bitField0_ &= ~0x00000001;
                }
                result.userRelationships_ = userRelationships_;
            } else {
                result.userRelationships_ = userRelationshipsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion result) {
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
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (userRelationshipsBuilder_ == null) {
                if (!other.userRelationships_.isEmpty()) {
                    if (userRelationships_.isEmpty()) {
                        userRelationships_ = other.userRelationships_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureUserRelationshipsIsMutable();
                        userRelationships_.addAll(other.userRelationships_);
                    }
                    onChanged();
                }
            } else {
                if (!other.userRelationships_.isEmpty()) {
                    if (userRelationshipsBuilder_.isEmpty()) {
                        userRelationshipsBuilder_.dispose();
                        userRelationshipsBuilder_ = null;
                        userRelationships_ = other.userRelationships_;
                        bitField0_ &= ~0x00000001;
                        userRelationshipsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getUserRelationshipsFieldBuilder()
                                        : null;
                    } else {
                        userRelationshipsBuilder_.addAllMessages(other.userRelationships_);
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
                            UserRelationship m =
                                    input.readMessage(UserRelationship.parser(), extensionRegistry);
                            if (userRelationshipsBuilder_ == null) {
                                ensureUserRelationshipsIsMutable();
                                userRelationships_.add(m);
                            } else {
                                userRelationshipsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationship> userRelationships_ =
                java.util.Collections.emptyList();

        private void ensureUserRelationshipsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                userRelationships_ = new java.util.ArrayList<>(userRelationships_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationship, im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder> userRelationshipsBuilder_;

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationship> getUserRelationshipsList() {
            if (userRelationshipsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(userRelationships_);
            } else {
                return userRelationshipsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public int getUserRelationshipsCount() {
            if (userRelationshipsBuilder_ == null) {
                return userRelationships_.size();
            } else {
                return userRelationshipsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationship getUserRelationships(
                int index) {
            if (userRelationshipsBuilder_ == null) {
                return userRelationships_.get(index);
            } else {
                return userRelationshipsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder setUserRelationships(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationship value) {
            if (userRelationshipsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserRelationshipsIsMutable();
                userRelationships_.set(index, value);
                onChanged();
            } else {
                userRelationshipsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder setUserRelationships(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder builderForValue) {
            if (userRelationshipsBuilder_ == null) {
                ensureUserRelationshipsIsMutable();
                userRelationships_.set(index, builderForValue.build());
                onChanged();
            } else {
                userRelationshipsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder addUserRelationships(
                im.turms.server.common.access.client.dto.model.user.UserRelationship value) {
            if (userRelationshipsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserRelationshipsIsMutable();
                userRelationships_.add(value);
                onChanged();
            } else {
                userRelationshipsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder addUserRelationships(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationship value) {
            if (userRelationshipsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureUserRelationshipsIsMutable();
                userRelationships_.add(index, value);
                onChanged();
            } else {
                userRelationshipsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder addUserRelationships(
                im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder builderForValue) {
            if (userRelationshipsBuilder_ == null) {
                ensureUserRelationshipsIsMutable();
                userRelationships_.add(builderForValue.build());
                onChanged();
            } else {
                userRelationshipsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder addUserRelationships(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder builderForValue) {
            if (userRelationshipsBuilder_ == null) {
                ensureUserRelationshipsIsMutable();
                userRelationships_.add(index, builderForValue.build());
                onChanged();
            } else {
                userRelationshipsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder addAllUserRelationships(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.user.UserRelationship> values) {
            if (userRelationshipsBuilder_ == null) {
                ensureUserRelationshipsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, userRelationships_);
                onChanged();
            } else {
                userRelationshipsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder clearUserRelationships() {
            if (userRelationshipsBuilder_ == null) {
                userRelationships_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                userRelationshipsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public Builder removeUserRelationships(int index) {
            if (userRelationshipsBuilder_ == null) {
                ensureUserRelationshipsIsMutable();
                userRelationships_.remove(index);
                onChanged();
            } else {
                userRelationshipsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder getUserRelationshipsBuilder(
                int index) {
            return getUserRelationshipsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder getUserRelationshipsOrBuilder(
                int index) {
            if (userRelationshipsBuilder_ == null) {
                return userRelationships_.get(index);
            } else {
                return userRelationshipsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder> getUserRelationshipsOrBuilderList() {
            if (userRelationshipsBuilder_ != null) {
                return userRelationshipsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(userRelationships_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder addUserRelationshipsBuilder() {
            return getUserRelationshipsFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.user.UserRelationship
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder addUserRelationshipsBuilder(
                int index) {
            return getUserRelationshipsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.UserRelationship
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserRelationship user_relationships = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder> getUserRelationshipsBuilderList() {
            return getUserRelationshipsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationship, im.turms.server.common.access.client.dto.model.user.UserRelationship.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipOrBuilder> getUserRelationshipsFieldBuilder() {
            if (userRelationshipsBuilder_ == null) {
                userRelationshipsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        userRelationships_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                userRelationships_ = null;
            }
            return userRelationshipsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserRelationshipsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserRelationshipsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserRelationshipsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserRelationshipsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserRelationshipsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserRelationshipsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}