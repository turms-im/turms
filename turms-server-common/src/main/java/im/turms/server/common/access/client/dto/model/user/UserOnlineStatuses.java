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
 * Protobuf type {@code im.turms.proto.UserOnlineStatuses}
 */
public final class UserOnlineStatuses extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserOnlineStatuses)
        UserOnlineStatusesOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UserOnlineStatuses.newBuilder() to construct.
    private UserOnlineStatuses(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UserOnlineStatuses() {
        statuses_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UserOnlineStatuses();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass.internal_static_im_turms_proto_UserOnlineStatuses_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass.internal_static_im_turms_proto_UserOnlineStatuses_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.class,
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.Builder.class);
    }

    public static final int STATUSES_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus> statuses_;

    /**
     * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus> getStatusesList() {
        return statuses_;
    }

    /**
     * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder> getStatusesOrBuilderList() {
        return statuses_;
    }

    /**
     * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
     */
    @java.lang.Override
    public int getStatusesCount() {
        return statuses_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus getStatuses(
            int index) {
        return statuses_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder getStatusesOrBuilder(
            int index) {
        return statuses_.get(index);
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
        for (UserOnlineStatus userOnlineStatus : statuses_) {
            output.writeMessage(1, userOnlineStatus);
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
        for (UserOnlineStatus userOnlineStatus : statuses_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, userOnlineStatus);
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
        if (!(obj instanceof UserOnlineStatuses other)) {
            return super.equals(obj);
        }

        if (!getStatusesList().equals(other.getStatusesList())) {
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
        if (getStatusesCount() > 0) {
            hash = (37 * hash) + STATUSES_FIELD_NUMBER;
            hash = (53 * hash) + getStatusesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses prototype) {
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
     * Protobuf type {@code im.turms.proto.UserOnlineStatuses}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserOnlineStatuses)
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass.internal_static_im_turms_proto_UserOnlineStatuses_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass.internal_static_im_turms_proto_UserOnlineStatuses_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.class,
                            im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (statusesBuilder_ == null) {
                statuses_ = java.util.Collections.emptyList();
            } else {
                statuses_ = null;
                statusesBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass.internal_static_im_turms_proto_UserOnlineStatuses_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses build() {
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses result =
                    new im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses result) {
            if (statusesBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    statuses_ = java.util.Collections.unmodifiableList(statuses_);
                    bitField0_ &= ~0x00000001;
                }
                result.statuses_ = statuses_;
            } else {
                result.statuses_ = statusesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                    .getDefaultInstance()) {
                return this;
            }
            if (statusesBuilder_ == null) {
                if (!other.statuses_.isEmpty()) {
                    if (statuses_.isEmpty()) {
                        statuses_ = other.statuses_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureStatusesIsMutable();
                        statuses_.addAll(other.statuses_);
                    }
                    onChanged();
                }
            } else {
                if (!other.statuses_.isEmpty()) {
                    if (statusesBuilder_.isEmpty()) {
                        statusesBuilder_.dispose();
                        statusesBuilder_ = null;
                        statuses_ = other.statuses_;
                        bitField0_ &= ~0x00000001;
                        statusesBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getStatusesFieldBuilder()
                                        : null;
                    } else {
                        statusesBuilder_.addAllMessages(other.statuses_);
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
                            UserOnlineStatus m =
                                    input.readMessage(UserOnlineStatus.parser(), extensionRegistry);
                            if (statusesBuilder_ == null) {
                                ensureStatusesIsMutable();
                                statuses_.add(m);
                            } else {
                                statusesBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus> statuses_ =
                java.util.Collections.emptyList();

        private void ensureStatusesIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                statuses_ = new java.util.ArrayList<>(statuses_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus, im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder, im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder> statusesBuilder_;

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus> getStatusesList() {
            if (statusesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(statuses_);
            } else {
                return statusesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public int getStatusesCount() {
            if (statusesBuilder_ == null) {
                return statuses_.size();
            } else {
                return statusesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus getStatuses(
                int index) {
            if (statusesBuilder_ == null) {
                return statuses_.get(index);
            } else {
                return statusesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder setStatuses(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus value) {
            if (statusesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStatusesIsMutable();
                statuses_.set(index, value);
                onChanged();
            } else {
                statusesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder setStatuses(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder builderForValue) {
            if (statusesBuilder_ == null) {
                ensureStatusesIsMutable();
                statuses_.set(index, builderForValue.build());
                onChanged();
            } else {
                statusesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder addStatuses(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus value) {
            if (statusesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStatusesIsMutable();
                statuses_.add(value);
                onChanged();
            } else {
                statusesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder addStatuses(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus value) {
            if (statusesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureStatusesIsMutable();
                statuses_.add(index, value);
                onChanged();
            } else {
                statusesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder addStatuses(
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder builderForValue) {
            if (statusesBuilder_ == null) {
                ensureStatusesIsMutable();
                statuses_.add(builderForValue.build());
                onChanged();
            } else {
                statusesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder addStatuses(
                int index,
                im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder builderForValue) {
            if (statusesBuilder_ == null) {
                ensureStatusesIsMutable();
                statuses_.add(index, builderForValue.build());
                onChanged();
            } else {
                statusesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder addAllStatuses(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.user.UserOnlineStatus> values) {
            if (statusesBuilder_ == null) {
                ensureStatusesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, statuses_);
                onChanged();
            } else {
                statusesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder clearStatuses() {
            if (statusesBuilder_ == null) {
                statuses_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                statusesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public Builder removeStatuses(int index) {
            if (statusesBuilder_ == null) {
                ensureStatusesIsMutable();
                statuses_.remove(index);
                onChanged();
            } else {
                statusesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder getStatusesBuilder(
                int index) {
            return getStatusesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder getStatusesOrBuilder(
                int index) {
            if (statusesBuilder_ == null) {
                return statuses_.get(index);
            } else {
                return statusesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder> getStatusesOrBuilderList() {
            if (statusesBuilder_ != null) {
                return statusesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(statuses_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder addStatusesBuilder() {
            return getStatusesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.user.UserOnlineStatus
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder addStatusesBuilder(
                int index) {
            return getStatusesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.UserOnlineStatus
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserOnlineStatus statuses = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder> getStatusesBuilderList() {
            return getStatusesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserOnlineStatus, im.turms.server.common.access.client.dto.model.user.UserOnlineStatus.Builder, im.turms.server.common.access.client.dto.model.user.UserOnlineStatusOrBuilder> getStatusesFieldBuilder() {
            if (statusesBuilder_ == null) {
                statusesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        statuses_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                statuses_ = null;
            }
            return statusesBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserOnlineStatuses)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserOnlineStatuses)
    private static final im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserOnlineStatuses> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserOnlineStatuses parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserOnlineStatuses> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserOnlineStatuses> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}