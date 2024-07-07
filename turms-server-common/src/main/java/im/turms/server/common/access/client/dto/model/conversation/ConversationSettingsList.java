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
 * Protobuf type {@code im.turms.proto.ConversationSettingsList}
 */
public final class ConversationSettingsList extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.ConversationSettingsList)
        ConversationSettingsListOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                ConversationSettingsList.class.getName());
    }

    // Use ConversationSettingsList.newBuilder() to construct.
    private ConversationSettingsList(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ConversationSettingsList() {
        conversationSettingsList_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass.internal_static_im_turms_proto_ConversationSettingsList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass.internal_static_im_turms_proto_ConversationSettingsList_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList.class,
                        im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList.Builder.class);
    }

    public static final int CONVERSATION_SETTINGS_LIST_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings> conversationSettingsList_;

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings> getConversationSettingsListList() {
        return conversationSettingsList_;
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOrBuilder> getConversationSettingsListOrBuilderList() {
        return conversationSettingsList_;
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public int getConversationSettingsListCount() {
        return conversationSettingsList_.size();
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.ConversationSettings getConversationSettingsList(
            int index) {
        return conversationSettingsList_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOrBuilder getConversationSettingsListOrBuilder(
            int index) {
        return conversationSettingsList_.get(index);
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
        for (ConversationSettings conversationSettings : conversationSettingsList_) {
            output.writeMessage(1, conversationSettings);
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
        for (ConversationSettings conversationSettings : conversationSettingsList_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1,
                    conversationSettings);
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
        if (!(obj instanceof ConversationSettingsList other)) {
            return super.equals(obj);
        }

        return getConversationSettingsListList().equals(other.getConversationSettingsListList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (getConversationSettingsListCount() > 0) {
            hash = (37 * hash) + CONVERSATION_SETTINGS_LIST_FIELD_NUMBER;
            hash = (53 * hash) + getConversationSettingsListList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList parseFrom(
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
            im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList prototype) {
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
     * Protobuf type {@code im.turms.proto.ConversationSettingsList}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.ConversationSettingsList)
            im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass.internal_static_im_turms_proto_ConversationSettingsList_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass.internal_static_im_turms_proto_ConversationSettingsList_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList.class,
                            im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (conversationSettingsListBuilder_ == null) {
                conversationSettingsList_ = java.util.Collections.emptyList();
            } else {
                conversationSettingsList_ = null;
                conversationSettingsListBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass.internal_static_im_turms_proto_ConversationSettingsList_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList build() {
            im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList buildPartial() {
            im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList result =
                    new im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList result) {
            if (conversationSettingsListBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    conversationSettingsList_ =
                            java.util.Collections.unmodifiableList(conversationSettingsList_);
                    bitField0_ &= ~0x00000001;
                }
                result.conversationSettingsList_ = conversationSettingsList_;
            } else {
                result.conversationSettingsList_ = conversationSettingsListBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList other) {
            if (other == im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList
                    .getDefaultInstance()) {
                return this;
            }
            if (conversationSettingsListBuilder_ == null) {
                if (!other.conversationSettingsList_.isEmpty()) {
                    if (conversationSettingsList_.isEmpty()) {
                        conversationSettingsList_ = other.conversationSettingsList_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureConversationSettingsListIsMutable();
                        conversationSettingsList_.addAll(other.conversationSettingsList_);
                    }
                    onChanged();
                }
            } else {
                if (!other.conversationSettingsList_.isEmpty()) {
                    if (conversationSettingsListBuilder_.isEmpty()) {
                        conversationSettingsListBuilder_.dispose();
                        conversationSettingsListBuilder_ = null;
                        conversationSettingsList_ = other.conversationSettingsList_;
                        bitField0_ &= ~0x00000001;
                        conversationSettingsListBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getConversationSettingsListFieldBuilder()
                                        : null;
                    } else {
                        conversationSettingsListBuilder_
                                .addAllMessages(other.conversationSettingsList_);
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
                        case 10 -> {
                            ConversationSettings m = input
                                    .readMessage(ConversationSettings.parser(), extensionRegistry);
                            if (conversationSettingsListBuilder_ == null) {
                                ensureConversationSettingsListIsMutable();
                                conversationSettingsList_.add(m);
                            } else {
                                conversationSettingsListBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings> conversationSettingsList_ =
                java.util.Collections.emptyList();

        private void ensureConversationSettingsListIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                conversationSettingsList_ = new java.util.ArrayList<>(conversationSettingsList_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings, im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder, im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOrBuilder> conversationSettingsListBuilder_;

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings> getConversationSettingsListList() {
            if (conversationSettingsListBuilder_ == null) {
                return java.util.Collections.unmodifiableList(conversationSettingsList_);
            } else {
                return conversationSettingsListBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public int getConversationSettingsListCount() {
            if (conversationSettingsListBuilder_ == null) {
                return conversationSettingsList_.size();
            } else {
                return conversationSettingsListBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettings getConversationSettingsList(
                int index) {
            if (conversationSettingsListBuilder_ == null) {
                return conversationSettingsList_.get(index);
            } else {
                return conversationSettingsListBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder setConversationSettingsList(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettings value) {
            if (conversationSettingsListBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.set(index, value);
                onChanged();
            } else {
                conversationSettingsListBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder setConversationSettingsList(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder builderForValue) {
            if (conversationSettingsListBuilder_ == null) {
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.set(index, builderForValue.build());
                onChanged();
            } else {
                conversationSettingsListBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettings value) {
            if (conversationSettingsListBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.add(value);
                onChanged();
            } else {
                conversationSettingsListBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettings value) {
            if (conversationSettingsListBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.add(index, value);
                onChanged();
            } else {
                conversationSettingsListBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder builderForValue) {
            if (conversationSettingsListBuilder_ == null) {
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.add(builderForValue.build());
                onChanged();
            } else {
                conversationSettingsListBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                int index,
                im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder builderForValue) {
            if (conversationSettingsListBuilder_ == null) {
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.add(index, builderForValue.build());
                onChanged();
            } else {
                conversationSettingsListBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addAllConversationSettingsList(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.conversation.ConversationSettings> values) {
            if (conversationSettingsListBuilder_ == null) {
                ensureConversationSettingsListIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values,
                        conversationSettingsList_);
                onChanged();
            } else {
                conversationSettingsListBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder clearConversationSettingsList() {
            if (conversationSettingsListBuilder_ == null) {
                conversationSettingsList_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                conversationSettingsListBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder removeConversationSettingsList(int index) {
            if (conversationSettingsListBuilder_ == null) {
                ensureConversationSettingsListIsMutable();
                conversationSettingsList_.remove(index);
                onChanged();
            } else {
                conversationSettingsListBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder getConversationSettingsListBuilder(
                int index) {
            return getConversationSettingsListFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOrBuilder getConversationSettingsListOrBuilder(
                int index) {
            if (conversationSettingsListBuilder_ == null) {
                return conversationSettingsList_.get(index);
            } else {
                return conversationSettingsListBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOrBuilder> getConversationSettingsListOrBuilderList() {
            if (conversationSettingsListBuilder_ != null) {
                return conversationSettingsListBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(conversationSettingsList_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder addConversationSettingsListBuilder() {
            return getConversationSettingsListFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.conversation.ConversationSettings
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder addConversationSettingsListBuilder(
                int index) {
            return getConversationSettingsListFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.conversation.ConversationSettings
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder> getConversationSettingsListBuilderList() {
            return getConversationSettingsListFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.conversation.ConversationSettings, im.turms.server.common.access.client.dto.model.conversation.ConversationSettings.Builder, im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOrBuilder> getConversationSettingsListFieldBuilder() {
            if (conversationSettingsListBuilder_ == null) {
                conversationSettingsListBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        conversationSettingsList_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                conversationSettingsList_ = null;
            }
            return conversationSettingsListBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.ConversationSettingsList)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.ConversationSettingsList)
    private static final im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList();
    }

    public static im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConversationSettingsList> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ConversationSettingsList parsePartialFrom(
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

    public static com.google.protobuf.Parser<ConversationSettingsList> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConversationSettingsList> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsList getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}