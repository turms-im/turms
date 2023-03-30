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
 * Protobuf type {@code im.turms.proto.NearbyUsers}
 */
public final class NearbyUsers extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.NearbyUsers)
        NearbyUsersOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use NearbyUsers.newBuilder() to construct.
    private NearbyUsers(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private NearbyUsers() {
        nearbyUsers_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new NearbyUsers();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass.internal_static_im_turms_proto_NearbyUsers_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass.internal_static_im_turms_proto_NearbyUsers_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.NearbyUsers.class,
                        im.turms.server.common.access.client.dto.model.user.NearbyUsers.Builder.class);
    }

    public static final int NEARBY_USERS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.NearbyUser> nearbyUsers_;

    /**
     * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.NearbyUser> getNearbyUsersList() {
        return nearbyUsers_;
    }

    /**
     * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder> getNearbyUsersOrBuilderList() {
        return nearbyUsers_;
    }

    /**
     * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
     */
    @java.lang.Override
    public int getNearbyUsersCount() {
        return nearbyUsers_.size();
    }

    /**
     * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.NearbyUser getNearbyUsers(
            int index) {
        return nearbyUsers_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder getNearbyUsersOrBuilder(
            int index) {
        return nearbyUsers_.get(index);
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
        for (NearbyUser nearbyUser : nearbyUsers_) {
            output.writeMessage(1, nearbyUser);
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
        for (NearbyUser nearbyUser : nearbyUsers_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, nearbyUser);
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
        if (!(obj instanceof NearbyUsers other)) {
            return super.equals(obj);
        }

        if (!getNearbyUsersList().equals(other.getNearbyUsersList())) {
            return false;
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
        if (getNearbyUsersCount() > 0) {
            hash = (37 * hash) + NEARBY_USERS_FIELD_NUMBER;
            hash = (53 * hash) + getNearbyUsersList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.NearbyUsers prototype) {
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
     * Protobuf type {@code im.turms.proto.NearbyUsers}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.NearbyUsers)
            im.turms.server.common.access.client.dto.model.user.NearbyUsersOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass.internal_static_im_turms_proto_NearbyUsers_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass.internal_static_im_turms_proto_NearbyUsers_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.NearbyUsers.class,
                            im.turms.server.common.access.client.dto.model.user.NearbyUsers.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.NearbyUsers.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (nearbyUsersBuilder_ == null) {
                nearbyUsers_ = java.util.Collections.emptyList();
            } else {
                nearbyUsers_ = null;
                nearbyUsersBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass.internal_static_im_turms_proto_NearbyUsers_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUsers getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.NearbyUsers
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUsers build() {
            im.turms.server.common.access.client.dto.model.user.NearbyUsers result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUsers buildPartial() {
            im.turms.server.common.access.client.dto.model.user.NearbyUsers result =
                    new im.turms.server.common.access.client.dto.model.user.NearbyUsers(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.NearbyUsers result) {
            if (nearbyUsersBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    nearbyUsers_ = java.util.Collections.unmodifiableList(nearbyUsers_);
                    bitField0_ &= ~0x00000001;
                }
                result.nearbyUsers_ = nearbyUsers_;
            } else {
                result.nearbyUsers_ = nearbyUsersBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.NearbyUsers result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.NearbyUsers) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.NearbyUsers) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.NearbyUsers other) {
            if (other == im.turms.server.common.access.client.dto.model.user.NearbyUsers
                    .getDefaultInstance()) {
                return this;
            }
            if (nearbyUsersBuilder_ == null) {
                if (!other.nearbyUsers_.isEmpty()) {
                    if (nearbyUsers_.isEmpty()) {
                        nearbyUsers_ = other.nearbyUsers_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureNearbyUsersIsMutable();
                        nearbyUsers_.addAll(other.nearbyUsers_);
                    }
                    onChanged();
                }
            } else {
                if (!other.nearbyUsers_.isEmpty()) {
                    if (nearbyUsersBuilder_.isEmpty()) {
                        nearbyUsersBuilder_.dispose();
                        nearbyUsersBuilder_ = null;
                        nearbyUsers_ = other.nearbyUsers_;
                        bitField0_ &= ~0x00000001;
                        nearbyUsersBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getNearbyUsersFieldBuilder()
                                        : null;
                    } else {
                        nearbyUsersBuilder_.addAllMessages(other.nearbyUsers_);
                    }
                }
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
                            NearbyUser m =
                                    input.readMessage(NearbyUser.parser(), extensionRegistry);
                            if (nearbyUsersBuilder_ == null) {
                                ensureNearbyUsersIsMutable();
                                nearbyUsers_.add(m);
                            } else {
                                nearbyUsersBuilder_.addMessage(m);
                            }
                        } // case 10
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

        private java.util.List<im.turms.server.common.access.client.dto.model.user.NearbyUser> nearbyUsers_ =
                java.util.Collections.emptyList();

        private void ensureNearbyUsersIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                nearbyUsers_ = new java.util.ArrayList<>(nearbyUsers_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.NearbyUser, im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder, im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder> nearbyUsersBuilder_;

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.NearbyUser> getNearbyUsersList() {
            if (nearbyUsersBuilder_ == null) {
                return java.util.Collections.unmodifiableList(nearbyUsers_);
            } else {
                return nearbyUsersBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public int getNearbyUsersCount() {
            if (nearbyUsersBuilder_ == null) {
                return nearbyUsers_.size();
            } else {
                return nearbyUsersBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.NearbyUser getNearbyUsers(
                int index) {
            if (nearbyUsersBuilder_ == null) {
                return nearbyUsers_.get(index);
            } else {
                return nearbyUsersBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder setNearbyUsers(
                int index,
                im.turms.server.common.access.client.dto.model.user.NearbyUser value) {
            if (nearbyUsersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureNearbyUsersIsMutable();
                nearbyUsers_.set(index, value);
                onChanged();
            } else {
                nearbyUsersBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder setNearbyUsers(
                int index,
                im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder builderForValue) {
            if (nearbyUsersBuilder_ == null) {
                ensureNearbyUsersIsMutable();
                nearbyUsers_.set(index, builderForValue.build());
                onChanged();
            } else {
                nearbyUsersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder addNearbyUsers(
                im.turms.server.common.access.client.dto.model.user.NearbyUser value) {
            if (nearbyUsersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureNearbyUsersIsMutable();
                nearbyUsers_.add(value);
                onChanged();
            } else {
                nearbyUsersBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder addNearbyUsers(
                int index,
                im.turms.server.common.access.client.dto.model.user.NearbyUser value) {
            if (nearbyUsersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureNearbyUsersIsMutable();
                nearbyUsers_.add(index, value);
                onChanged();
            } else {
                nearbyUsersBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder addNearbyUsers(
                im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder builderForValue) {
            if (nearbyUsersBuilder_ == null) {
                ensureNearbyUsersIsMutable();
                nearbyUsers_.add(builderForValue.build());
                onChanged();
            } else {
                nearbyUsersBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder addNearbyUsers(
                int index,
                im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder builderForValue) {
            if (nearbyUsersBuilder_ == null) {
                ensureNearbyUsersIsMutable();
                nearbyUsers_.add(index, builderForValue.build());
                onChanged();
            } else {
                nearbyUsersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder addAllNearbyUsers(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.user.NearbyUser> values) {
            if (nearbyUsersBuilder_ == null) {
                ensureNearbyUsersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, nearbyUsers_);
                onChanged();
            } else {
                nearbyUsersBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder clearNearbyUsers() {
            if (nearbyUsersBuilder_ == null) {
                nearbyUsers_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                nearbyUsersBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public Builder removeNearbyUsers(int index) {
            if (nearbyUsersBuilder_ == null) {
                ensureNearbyUsersIsMutable();
                nearbyUsers_.remove(index);
                onChanged();
            } else {
                nearbyUsersBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder getNearbyUsersBuilder(
                int index) {
            return getNearbyUsersFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder getNearbyUsersOrBuilder(
                int index) {
            if (nearbyUsersBuilder_ == null) {
                return nearbyUsers_.get(index);
            } else {
                return nearbyUsersBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder> getNearbyUsersOrBuilderList() {
            if (nearbyUsersBuilder_ != null) {
                return nearbyUsersBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(nearbyUsers_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder addNearbyUsersBuilder() {
            return getNearbyUsersFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.user.NearbyUser
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder addNearbyUsersBuilder(
                int index) {
            return getNearbyUsersFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.NearbyUser
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.NearbyUser nearby_users = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder> getNearbyUsersBuilderList() {
            return getNearbyUsersFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.NearbyUser, im.turms.server.common.access.client.dto.model.user.NearbyUser.Builder, im.turms.server.common.access.client.dto.model.user.NearbyUserOrBuilder> getNearbyUsersFieldBuilder() {
            if (nearbyUsersBuilder_ == null) {
                nearbyUsersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        nearbyUsers_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                nearbyUsers_ = null;
            }
            return nearbyUsersBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.NearbyUsers)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.NearbyUsers)
    private static final im.turms.server.common.access.client.dto.model.user.NearbyUsers DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.user.NearbyUsers();
    }

    public static im.turms.server.common.access.client.dto.model.user.NearbyUsers getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<NearbyUsers> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public NearbyUsers parsePartialFrom(
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

    public static com.google.protobuf.Parser<NearbyUsers> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<NearbyUsers> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.NearbyUsers getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}