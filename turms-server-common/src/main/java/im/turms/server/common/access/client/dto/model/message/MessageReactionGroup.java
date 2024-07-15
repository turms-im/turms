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

package im.turms.server.common.access.client.dto.model.message;

/**
 * Protobuf type {@code im.turms.proto.MessageReactionGroup}
 */
public final class MessageReactionGroup extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.MessageReactionGroup)
        MessageReactionGroupOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                MessageReactionGroup.class.getName());
    }

    // Use MessageReactionGroup.newBuilder() to construct.
    private MessageReactionGroup(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private MessageReactionGroup() {
        userInfos_ = java.util.Collections.emptyList();
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass.internal_static_im_turms_proto_MessageReactionGroup_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass.internal_static_im_turms_proto_MessageReactionGroup_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.message.MessageReactionGroup.class,
                        im.turms.server.common.access.client.dto.model.message.MessageReactionGroup.Builder.class);
    }

    public static final int REACTION_TYPE_FIELD_NUMBER = 1;
    private int reactionType_;

    /**
     * <code>int32 reaction_type = 1;</code>
     *
     * @return The reactionType.
     */
    @java.lang.Override
    public int getReactionType() {
        return reactionType_;
    }

    public static final int REACTION_COUNT_FIELD_NUMBER = 2;
    private int reactionCount_;

    /**
     * <code>int32 reaction_count = 2;</code>
     *
     * @return The reactionCount.
     */
    @java.lang.Override
    public int getReactionCount() {
        return reactionCount_;
    }

    public static final int USER_INFOS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> userInfos_;

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> getUserInfosList() {
        return userInfos_;
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosOrBuilderList() {
        return userInfos_;
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public int getUserInfosCount() {
        return userInfos_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfo getUserInfos(int index) {
        return userInfos_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getUserInfosOrBuilder(
            int index) {
        return userInfos_.get(index);
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public int getCustomAttributesCount() {
        return customAttributes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
            int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
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
        if (reactionType_ != 0) {
            output.writeInt32(1, reactionType_);
        }
        if (reactionCount_ != 0) {
            output.writeInt32(2, reactionCount_);
        }
        for (im.turms.server.common.access.client.dto.model.user.UserInfo userInfo : userInfos_) {
            output.writeMessage(3, userInfo);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            output.writeMessage(15, value);
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
        if (reactionType_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(1, reactionType_);
        }
        if (reactionCount_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, reactionCount_);
        }
        for (im.turms.server.common.access.client.dto.model.user.UserInfo userInfo : userInfos_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, userInfo);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, value);
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
        if (!(obj instanceof MessageReactionGroup other)) {
            return super.equals(obj);
        }

        if (getReactionType() != other.getReactionType()) {
            return false;
        }
        return getReactionCount() == other.getReactionCount()
                && getUserInfosList().equals(other.getUserInfosList())
                && getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + REACTION_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getReactionType();
        hash = (37 * hash) + REACTION_COUNT_FIELD_NUMBER;
        hash = (53 * hash) + getReactionCount();
        if (getUserInfosCount() > 0) {
            hash = (37 * hash) + USER_INFOS_FIELD_NUMBER;
            hash = (53 * hash) + getUserInfosList().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
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
            im.turms.server.common.access.client.dto.model.message.MessageReactionGroup prototype) {
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
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.MessageReactionGroup}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.MessageReactionGroup)
            im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass.internal_static_im_turms_proto_MessageReactionGroup_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass.internal_static_im_turms_proto_MessageReactionGroup_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.message.MessageReactionGroup.class,
                            im.turms.server.common.access.client.dto.model.message.MessageReactionGroup.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.message.MessageReactionGroup.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            reactionType_ = 0;
            reactionCount_ = 0;
            if (userInfosBuilder_ == null) {
                userInfos_ = java.util.Collections.emptyList();
            } else {
                userInfos_ = null;
                userInfosBuilder_.clear();
            }
            bitField0_ &= ~0x00000004;
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass.internal_static_im_turms_proto_MessageReactionGroup_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessageReactionGroup getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.message.MessageReactionGroup
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessageReactionGroup build() {
            im.turms.server.common.access.client.dto.model.message.MessageReactionGroup result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessageReactionGroup buildPartial() {
            im.turms.server.common.access.client.dto.model.message.MessageReactionGroup result =
                    new im.turms.server.common.access.client.dto.model.message.MessageReactionGroup(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.message.MessageReactionGroup result) {
            if (userInfosBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)) {
                    userInfos_ = java.util.Collections.unmodifiableList(userInfos_);
                    bitField0_ &= ~0x00000004;
                }
                result.userInfos_ = userInfos_;
            } else {
                result.userInfos_ = userInfosBuilder_.build();
            }
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000008;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.message.MessageReactionGroup result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.reactionType_ = reactionType_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.reactionCount_ = reactionCount_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.message.MessageReactionGroup) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.message.MessageReactionGroup) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.message.MessageReactionGroup other) {
            if (other == im.turms.server.common.access.client.dto.model.message.MessageReactionGroup
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getReactionType() != 0) {
                setReactionType(other.getReactionType());
            }
            if (other.getReactionCount() != 0) {
                setReactionCount(other.getReactionCount());
            }
            if (userInfosBuilder_ == null) {
                if (!other.userInfos_.isEmpty()) {
                    if (userInfos_.isEmpty()) {
                        userInfos_ = other.userInfos_;
                        bitField0_ &= ~0x00000004;
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
                        bitField0_ &= ~0x00000004;
                        userInfosBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getUserInfosFieldBuilder()
                                        : null;
                    } else {
                        userInfosBuilder_.addAllMessages(other.userInfos_);
                    }
                }
            }
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
                    } else {
                        ensureCustomAttributesIsMutable();
                        customAttributes_.addAll(other.customAttributes_);
                    }
                    onChanged();
                }
            } else {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributesBuilder_.isEmpty()) {
                        customAttributesBuilder_.dispose();
                        customAttributesBuilder_ = null;
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
                        customAttributesBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getCustomAttributesFieldBuilder()
                                        : null;
                    } else {
                        customAttributesBuilder_.addAllMessages(other.customAttributes_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
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
                        case 8 -> {
                            reactionType_ = input.readInt32();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            reactionCount_ = input.readInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            im.turms.server.common.access.client.dto.model.user.UserInfo m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.user.UserInfo
                                                    .parser(),
                                            extensionRegistry);
                            if (userInfosBuilder_ == null) {
                                ensureUserInfosIsMutable();
                                userInfos_.add(m);
                            } else {
                                userInfosBuilder_.addMessage(m);
                            }
                        } // case 26
                        case 122 -> {
                            im.turms.server.common.access.client.dto.model.common.Value m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.common.Value
                                                    .parser(),
                                            extensionRegistry);
                            if (customAttributesBuilder_ == null) {
                                ensureCustomAttributesIsMutable();
                                customAttributes_.add(m);
                            } else {
                                customAttributesBuilder_.addMessage(m);
                            }
                        } // case 122
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

        private int reactionType_;

        /**
         * <code>int32 reaction_type = 1;</code>
         *
         * @return The reactionType.
         */
        @java.lang.Override
        public int getReactionType() {
            return reactionType_;
        }

        /**
         * <code>int32 reaction_type = 1;</code>
         *
         * @param value The reactionType to set.
         * @return This builder for chaining.
         */
        public Builder setReactionType(int value) {

            reactionType_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int32 reaction_type = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReactionType() {
            bitField0_ &= ~0x00000001;
            reactionType_ = 0;
            onChanged();
            return this;
        }

        private int reactionCount_;

        /**
         * <code>int32 reaction_count = 2;</code>
         *
         * @return The reactionCount.
         */
        @java.lang.Override
        public int getReactionCount() {
            return reactionCount_;
        }

        /**
         * <code>int32 reaction_count = 2;</code>
         *
         * @param value The reactionCount to set.
         * @return This builder for chaining.
         */
        public Builder setReactionCount(int value) {

            reactionCount_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int32 reaction_count = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReactionCount() {
            bitField0_ &= ~0x00000002;
            reactionCount_ = 0;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> userInfos_ =
                java.util.Collections.emptyList();

        private void ensureUserInfosIsMutable() {
            if ((bitField0_ & 0x00000004) == 0) {
                userInfos_ = new java.util.ArrayList<>(userInfos_);
                bitField0_ |= 0x00000004;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.user.UserInfo, im.turms.server.common.access.client.dto.model.user.UserInfo.Builder, im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> userInfosBuilder_;

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> getUserInfosList() {
            if (userInfosBuilder_ == null) {
                return java.util.Collections.unmodifiableList(userInfos_);
            } else {
                return userInfosBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public int getUserInfosCount() {
            if (userInfosBuilder_ == null) {
                return userInfos_.size();
            } else {
                return userInfosBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder clearUserInfos() {
            if (userInfosBuilder_ == null) {
                userInfos_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000004;
                onChanged();
            } else {
                userInfosBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder getUserInfosBuilder(
                int index) {
            return getUserInfosFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
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
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosOrBuilderList() {
            if (userInfosBuilder_ != null) {
                return userInfosBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(userInfos_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder addUserInfosBuilder() {
            return getUserInfosFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.user.UserInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public im.turms.server.common.access.client.dto.model.user.UserInfo.Builder addUserInfosBuilder(
                int index) {
            return getUserInfosFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.user.UserInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo.Builder> getUserInfosBuilderList() {
            return getUserInfosFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.user.UserInfo, im.turms.server.common.access.client.dto.model.user.UserInfo.Builder, im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosFieldBuilder() {
            if (userInfosBuilder_ == null) {
                userInfosBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        userInfos_,
                        ((bitField0_ & 0x00000004) != 0),
                        getParentForChildren(),
                        isClean());
                userInfos_ = null;
            }
            return userInfosBuilder_;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000008;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(customAttributes_);
            } else {
                return customAttributesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public int getCustomAttributesCount() {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.size();
            } else {
                return customAttributesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, customAttributes_);
                onChanged();
            } else {
                customAttributesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000008;
                onChanged();
            } else {
                customAttributesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.remove(index);
                onChanged();
            } else {
                customAttributesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        customAttributes_,
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.MessageReactionGroup)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.MessageReactionGroup)
    private static final im.turms.server.common.access.client.dto.model.message.MessageReactionGroup DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.message.MessageReactionGroup();
    }

    public static im.turms.server.common.access.client.dto.model.message.MessageReactionGroup getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MessageReactionGroup> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public MessageReactionGroup parsePartialFrom(
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

    public static com.google.protobuf.Parser<MessageReactionGroup> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MessageReactionGroup> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.MessageReactionGroup getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}