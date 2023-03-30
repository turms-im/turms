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

package im.turms.server.common.access.client.dto.model.conversation;

/**
 * Protobuf type {@code im.turms.proto.Conversations}
 */
public final class Conversations extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Conversations)
        ConversationsOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use Conversations.newBuilder() to construct.
    private Conversations(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Conversations() {
        privateConversations_ = java.util.Collections.emptyList();
        groupConversations_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new Conversations();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass.internal_static_im_turms_proto_Conversations_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass.internal_static_im_turms_proto_Conversations_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.conversation.Conversations.class,
                        im.turms.server.common.access.client.dto.model.conversation.Conversations.Builder.class);
    }

    public static final int PRIVATE_CONVERSATIONS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation> privateConversations_;

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation> getPrivateConversationsList() {
        return privateConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder> getPrivateConversationsOrBuilderList() {
        return privateConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public int getPrivateConversationsCount() {
        return privateConversations_.size();
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation getPrivateConversations(
            int index) {
        return privateConversations_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder getPrivateConversationsOrBuilder(
            int index) {
        return privateConversations_.get(index);
    }

    public static final int GROUP_CONVERSATIONS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.conversation.GroupConversation> groupConversations_;

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.conversation.GroupConversation> getGroupConversationsList() {
        return groupConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder> getGroupConversationsOrBuilderList() {
        return groupConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public int getGroupConversationsCount() {
        return groupConversations_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.GroupConversation getGroupConversations(
            int index) {
        return groupConversations_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder getGroupConversationsOrBuilder(
            int index) {
        return groupConversations_.get(index);
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
        for (PrivateConversation privateConversation : privateConversations_) {
            output.writeMessage(1, privateConversation);
        }
        for (GroupConversation groupConversation : groupConversations_) {
            output.writeMessage(2, groupConversation);
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
        for (PrivateConversation privateConversation : privateConversations_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1,
                    privateConversation);
        }
        for (GroupConversation groupConversation : groupConversations_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, groupConversation);
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
        if (!(obj instanceof Conversations other)) {
            return super.equals(obj);
        }

        if (!getPrivateConversationsList().equals(other.getPrivateConversationsList())) {
            return false;
        }
        if (!getGroupConversationsList().equals(other.getGroupConversationsList())) {
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
        if (getPrivateConversationsCount() > 0) {
            hash = (37 * hash) + PRIVATE_CONVERSATIONS_FIELD_NUMBER;
            hash = (53 * hash) + getPrivateConversationsList().hashCode();
        }
        if (getGroupConversationsCount() > 0) {
            hash = (37 * hash) + GROUP_CONVERSATIONS_FIELD_NUMBER;
            hash = (53 * hash) + getGroupConversationsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations parseFrom(
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
            im.turms.server.common.access.client.dto.model.conversation.Conversations prototype) {
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
     * Protobuf type {@code im.turms.proto.Conversations}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Conversations)
            im.turms.server.common.access.client.dto.model.conversation.ConversationsOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass.internal_static_im_turms_proto_Conversations_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass.internal_static_im_turms_proto_Conversations_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.conversation.Conversations.class,
                            im.turms.server.common.access.client.dto.model.conversation.Conversations.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.conversation.Conversations.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (privateConversationsBuilder_ == null) {
                privateConversations_ = java.util.Collections.emptyList();
            } else {
                privateConversations_ = null;
                privateConversationsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            if (groupConversationsBuilder_ == null) {
                groupConversations_ = java.util.Collections.emptyList();
            } else {
                groupConversations_ = null;
                groupConversationsBuilder_.clear();
            }
            bitField0_ &= ~0x00000002;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass.internal_static_im_turms_proto_Conversations_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.Conversations getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.conversation.Conversations
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.Conversations build() {
            im.turms.server.common.access.client.dto.model.conversation.Conversations result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.Conversations buildPartial() {
            im.turms.server.common.access.client.dto.model.conversation.Conversations result =
                    new im.turms.server.common.access.client.dto.model.conversation.Conversations(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.conversation.Conversations result) {
            if (privateConversationsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    privateConversations_ =
                            java.util.Collections.unmodifiableList(privateConversations_);
                    bitField0_ &= ~0x00000001;
                }
                result.privateConversations_ = privateConversations_;
            } else {
                result.privateConversations_ = privateConversationsBuilder_.build();
            }
            if (groupConversationsBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)) {
                    groupConversations_ =
                            java.util.Collections.unmodifiableList(groupConversations_);
                    bitField0_ &= ~0x00000002;
                }
                result.groupConversations_ = groupConversations_;
            } else {
                result.groupConversations_ = groupConversationsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.conversation.Conversations result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.conversation.Conversations) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.conversation.Conversations) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.conversation.Conversations other) {
            if (other == im.turms.server.common.access.client.dto.model.conversation.Conversations
                    .getDefaultInstance()) {
                return this;
            }
            if (privateConversationsBuilder_ == null) {
                if (!other.privateConversations_.isEmpty()) {
                    if (privateConversations_.isEmpty()) {
                        privateConversations_ = other.privateConversations_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensurePrivateConversationsIsMutable();
                        privateConversations_.addAll(other.privateConversations_);
                    }
                    onChanged();
                }
            } else {
                if (!other.privateConversations_.isEmpty()) {
                    if (privateConversationsBuilder_.isEmpty()) {
                        privateConversationsBuilder_.dispose();
                        privateConversationsBuilder_ = null;
                        privateConversations_ = other.privateConversations_;
                        bitField0_ &= ~0x00000001;
                        privateConversationsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getPrivateConversationsFieldBuilder()
                                        : null;
                    } else {
                        privateConversationsBuilder_.addAllMessages(other.privateConversations_);
                    }
                }
            }
            if (groupConversationsBuilder_ == null) {
                if (!other.groupConversations_.isEmpty()) {
                    if (groupConversations_.isEmpty()) {
                        groupConversations_ = other.groupConversations_;
                        bitField0_ &= ~0x00000002;
                    } else {
                        ensureGroupConversationsIsMutable();
                        groupConversations_.addAll(other.groupConversations_);
                    }
                    onChanged();
                }
            } else {
                if (!other.groupConversations_.isEmpty()) {
                    if (groupConversationsBuilder_.isEmpty()) {
                        groupConversationsBuilder_.dispose();
                        groupConversationsBuilder_ = null;
                        groupConversations_ = other.groupConversations_;
                        bitField0_ &= ~0x00000002;
                        groupConversationsBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getGroupConversationsFieldBuilder()
                                        : null;
                    } else {
                        groupConversationsBuilder_.addAllMessages(other.groupConversations_);
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
                            PrivateConversation m = input.readMessage(PrivateConversation.parser(),
                                    extensionRegistry);
                            if (privateConversationsBuilder_ == null) {
                                ensurePrivateConversationsIsMutable();
                                privateConversations_.add(m);
                            } else {
                                privateConversationsBuilder_.addMessage(m);
                            }
                        } // case 10
                        case 18 -> {
                            GroupConversation m = input.readMessage(GroupConversation.parser(),
                                    extensionRegistry);
                            if (groupConversationsBuilder_ == null) {
                                ensureGroupConversationsIsMutable();
                                groupConversations_.add(m);
                            } else {
                                groupConversationsBuilder_.addMessage(m);
                            }
                        } // case 18
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

        private java.util.List<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation> privateConversations_ =
                java.util.Collections.emptyList();

        private void ensurePrivateConversationsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                privateConversations_ = new java.util.ArrayList<>(privateConversations_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation, im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder, im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder> privateConversationsBuilder_;

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation> getPrivateConversationsList() {
            if (privateConversationsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(privateConversations_);
            } else {
                return privateConversationsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public int getPrivateConversationsCount() {
            if (privateConversationsBuilder_ == null) {
                return privateConversations_.size();
            } else {
                return privateConversationsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation getPrivateConversations(
                int index) {
            if (privateConversationsBuilder_ == null) {
                return privateConversations_.get(index);
            } else {
                return privateConversationsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder setPrivateConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation value) {
            if (privateConversationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensurePrivateConversationsIsMutable();
                privateConversations_.set(index, value);
                onChanged();
            } else {
                privateConversationsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder setPrivateConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder builderForValue) {
            if (privateConversationsBuilder_ == null) {
                ensurePrivateConversationsIsMutable();
                privateConversations_.set(index, builderForValue.build());
                onChanged();
            } else {
                privateConversationsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation value) {
            if (privateConversationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensurePrivateConversationsIsMutable();
                privateConversations_.add(value);
                onChanged();
            } else {
                privateConversationsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation value) {
            if (privateConversationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensurePrivateConversationsIsMutable();
                privateConversations_.add(index, value);
                onChanged();
            } else {
                privateConversationsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder builderForValue) {
            if (privateConversationsBuilder_ == null) {
                ensurePrivateConversationsIsMutable();
                privateConversations_.add(builderForValue.build());
                onChanged();
            } else {
                privateConversationsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder builderForValue) {
            if (privateConversationsBuilder_ == null) {
                ensurePrivateConversationsIsMutable();
                privateConversations_.add(index, builderForValue.build());
                onChanged();
            } else {
                privateConversationsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addAllPrivateConversations(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.conversation.PrivateConversation> values) {
            if (privateConversationsBuilder_ == null) {
                ensurePrivateConversationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values,
                        privateConversations_);
                onChanged();
            } else {
                privateConversationsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder clearPrivateConversations() {
            if (privateConversationsBuilder_ == null) {
                privateConversations_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                privateConversationsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder removePrivateConversations(int index) {
            if (privateConversationsBuilder_ == null) {
                ensurePrivateConversationsIsMutable();
                privateConversations_.remove(index);
                onChanged();
            } else {
                privateConversationsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder getPrivateConversationsBuilder(
                int index) {
            return getPrivateConversationsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder getPrivateConversationsOrBuilder(
                int index) {
            if (privateConversationsBuilder_ == null) {
                return privateConversations_.get(index);
            } else {
                return privateConversationsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder> getPrivateConversationsOrBuilderList() {
            if (privateConversationsBuilder_ != null) {
                return privateConversationsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(privateConversations_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder addPrivateConversationsBuilder() {
            return getPrivateConversationsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.conversation.PrivateConversation
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder addPrivateConversationsBuilder(
                int index) {
            return getPrivateConversationsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.conversation.PrivateConversation
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder> getPrivateConversationsBuilderList() {
            return getPrivateConversationsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation, im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder, im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder> getPrivateConversationsFieldBuilder() {
            if (privateConversationsBuilder_ == null) {
                privateConversationsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        privateConversations_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                privateConversations_ = null;
            }
            return privateConversationsBuilder_;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.conversation.GroupConversation> groupConversations_ =
                java.util.Collections.emptyList();

        private void ensureGroupConversationsIsMutable() {
            if ((bitField0_ & 0x00000002) == 0) {
                groupConversations_ = new java.util.ArrayList<>(groupConversations_);
                bitField0_ |= 0x00000002;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.conversation.GroupConversation, im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder, im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder> groupConversationsBuilder_;

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conversation.GroupConversation> getGroupConversationsList() {
            if (groupConversationsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(groupConversations_);
            } else {
                return groupConversationsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public int getGroupConversationsCount() {
            if (groupConversationsBuilder_ == null) {
                return groupConversations_.size();
            } else {
                return groupConversationsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation getGroupConversations(
                int index) {
            if (groupConversationsBuilder_ == null) {
                return groupConversations_.get(index);
            } else {
                return groupConversationsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder setGroupConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation value) {
            if (groupConversationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupConversationsIsMutable();
                groupConversations_.set(index, value);
                onChanged();
            } else {
                groupConversationsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder setGroupConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder builderForValue) {
            if (groupConversationsBuilder_ == null) {
                ensureGroupConversationsIsMutable();
                groupConversations_.set(index, builderForValue.build());
                onChanged();
            } else {
                groupConversationsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation value) {
            if (groupConversationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupConversationsIsMutable();
                groupConversations_.add(value);
                onChanged();
            } else {
                groupConversationsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation value) {
            if (groupConversationsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureGroupConversationsIsMutable();
                groupConversations_.add(index, value);
                onChanged();
            } else {
                groupConversationsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder builderForValue) {
            if (groupConversationsBuilder_ == null) {
                ensureGroupConversationsIsMutable();
                groupConversations_.add(builderForValue.build());
                onChanged();
            } else {
                groupConversationsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder builderForValue) {
            if (groupConversationsBuilder_ == null) {
                ensureGroupConversationsIsMutable();
                groupConversations_.add(index, builderForValue.build());
                onChanged();
            } else {
                groupConversationsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addAllGroupConversations(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.conversation.GroupConversation> values) {
            if (groupConversationsBuilder_ == null) {
                ensureGroupConversationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, groupConversations_);
                onChanged();
            } else {
                groupConversationsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder clearGroupConversations() {
            if (groupConversationsBuilder_ == null) {
                groupConversations_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000002;
                onChanged();
            } else {
                groupConversationsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder removeGroupConversations(int index) {
            if (groupConversationsBuilder_ == null) {
                ensureGroupConversationsIsMutable();
                groupConversations_.remove(index);
                onChanged();
            } else {
                groupConversationsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder getGroupConversationsBuilder(
                int index) {
            return getGroupConversationsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder getGroupConversationsOrBuilder(
                int index) {
            if (groupConversationsBuilder_ == null) {
                return groupConversations_.get(index);
            } else {
                return groupConversationsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder> getGroupConversationsOrBuilderList() {
            if (groupConversationsBuilder_ != null) {
                return groupConversationsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(groupConversations_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder addGroupConversationsBuilder() {
            return getGroupConversationsFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.conversation.GroupConversation
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder addGroupConversationsBuilder(
                int index) {
            return getGroupConversationsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.conversation.GroupConversation
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder> getGroupConversationsBuilderList() {
            return getGroupConversationsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.conversation.GroupConversation, im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder, im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder> getGroupConversationsFieldBuilder() {
            if (groupConversationsBuilder_ == null) {
                groupConversationsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        groupConversations_,
                        ((bitField0_ & 0x00000002) != 0),
                        getParentForChildren(),
                        isClean());
                groupConversations_ = null;
            }
            return groupConversationsBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Conversations)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Conversations)
    private static final im.turms.server.common.access.client.dto.model.conversation.Conversations DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.conversation.Conversations();
    }

    public static im.turms.server.common.access.client.dto.model.conversation.Conversations getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Conversations> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Conversations parsePartialFrom(
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

    public static com.google.protobuf.Parser<Conversations> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Conversations> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.Conversations getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}