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
 * Protobuf type {@code im.turms.proto.Messages}
 */
public final class Messages extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Messages)
        MessagesOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use Messages.newBuilder() to construct.
    private Messages(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Messages() {
        messages_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new Messages();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.message.MessagesOuterClass.internal_static_im_turms_proto_Messages_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.message.MessagesOuterClass.internal_static_im_turms_proto_Messages_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.message.Messages.class,
                        im.turms.server.common.access.client.dto.model.message.Messages.Builder.class);
    }

    public static final int MESSAGES_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.message.Message> messages_;

    /**
     * <code>repeated .im.turms.proto.Message messages = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.message.Message> getMessagesList() {
        return messages_;
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessageOrBuilder> getMessagesOrBuilderList() {
        return messages_;
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 1;</code>
     */
    @java.lang.Override
    public int getMessagesCount() {
        return messages_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.Message getMessages(int index) {
        return messages_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.MessageOrBuilder getMessagesOrBuilder(
            int index) {
        return messages_.get(index);
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
        for (Message message : messages_) {
            output.writeMessage(1, message);
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
        for (Message message : messages_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, message);
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
        if (!(obj instanceof Messages other)) {
            return super.equals(obj);
        }

        if (!getMessagesList().equals(other.getMessagesList())) {
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
        if (getMessagesCount() > 0) {
            hash = (37 * hash) + MESSAGES_FIELD_NUMBER;
            hash = (53 * hash) + getMessagesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages parseFrom(
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
            im.turms.server.common.access.client.dto.model.message.Messages prototype) {
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
     * Protobuf type {@code im.turms.proto.Messages}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Messages)
            im.turms.server.common.access.client.dto.model.message.MessagesOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.message.MessagesOuterClass.internal_static_im_turms_proto_Messages_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.message.MessagesOuterClass.internal_static_im_turms_proto_Messages_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.message.Messages.class,
                            im.turms.server.common.access.client.dto.model.message.Messages.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.message.Messages.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (messagesBuilder_ == null) {
                messages_ = java.util.Collections.emptyList();
            } else {
                messages_ = null;
                messagesBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.message.MessagesOuterClass.internal_static_im_turms_proto_Messages_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Messages getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.message.Messages
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Messages build() {
            im.turms.server.common.access.client.dto.model.message.Messages result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Messages buildPartial() {
            im.turms.server.common.access.client.dto.model.message.Messages result =
                    new im.turms.server.common.access.client.dto.model.message.Messages(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.message.Messages result) {
            if (messagesBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    messages_ = java.util.Collections.unmodifiableList(messages_);
                    bitField0_ &= ~0x00000001;
                }
                result.messages_ = messages_;
            } else {
                result.messages_ = messagesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.message.Messages result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.message.Messages) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.message.Messages) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.message.Messages other) {
            if (other == im.turms.server.common.access.client.dto.model.message.Messages
                    .getDefaultInstance()) {
                return this;
            }
            if (messagesBuilder_ == null) {
                if (!other.messages_.isEmpty()) {
                    if (messages_.isEmpty()) {
                        messages_ = other.messages_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureMessagesIsMutable();
                        messages_.addAll(other.messages_);
                    }
                    onChanged();
                }
            } else {
                if (!other.messages_.isEmpty()) {
                    if (messagesBuilder_.isEmpty()) {
                        messagesBuilder_.dispose();
                        messagesBuilder_ = null;
                        messages_ = other.messages_;
                        bitField0_ &= ~0x00000001;
                        messagesBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getMessagesFieldBuilder()
                                        : null;
                    } else {
                        messagesBuilder_.addAllMessages(other.messages_);
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
                            Message m = input.readMessage(Message.parser(), extensionRegistry);
                            if (messagesBuilder_ == null) {
                                ensureMessagesIsMutable();
                                messages_.add(m);
                            } else {
                                messagesBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.message.Message> messages_ =
                java.util.Collections.emptyList();

        private void ensureMessagesIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                messages_ = new java.util.ArrayList<>(messages_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.Message, im.turms.server.common.access.client.dto.model.message.Message.Builder, im.turms.server.common.access.client.dto.model.message.MessageOrBuilder> messagesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.message.Message> getMessagesList() {
            if (messagesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(messages_);
            } else {
                return messagesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public int getMessagesCount() {
            if (messagesBuilder_ == null) {
                return messages_.size();
            } else {
                return messagesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.Message getMessages(
                int index) {
            if (messagesBuilder_ == null) {
                return messages_.get(index);
            } else {
                return messagesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder setMessages(
                int index,
                im.turms.server.common.access.client.dto.model.message.Message value) {
            if (messagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMessagesIsMutable();
                messages_.set(index, value);
                onChanged();
            } else {
                messagesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder setMessages(
                int index,
                im.turms.server.common.access.client.dto.model.message.Message.Builder builderForValue) {
            if (messagesBuilder_ == null) {
                ensureMessagesIsMutable();
                messages_.set(index, builderForValue.build());
                onChanged();
            } else {
                messagesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder addMessages(
                im.turms.server.common.access.client.dto.model.message.Message value) {
            if (messagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMessagesIsMutable();
                messages_.add(value);
                onChanged();
            } else {
                messagesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder addMessages(
                int index,
                im.turms.server.common.access.client.dto.model.message.Message value) {
            if (messagesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMessagesIsMutable();
                messages_.add(index, value);
                onChanged();
            } else {
                messagesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder addMessages(
                im.turms.server.common.access.client.dto.model.message.Message.Builder builderForValue) {
            if (messagesBuilder_ == null) {
                ensureMessagesIsMutable();
                messages_.add(builderForValue.build());
                onChanged();
            } else {
                messagesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder addMessages(
                int index,
                im.turms.server.common.access.client.dto.model.message.Message.Builder builderForValue) {
            if (messagesBuilder_ == null) {
                ensureMessagesIsMutable();
                messages_.add(index, builderForValue.build());
                onChanged();
            } else {
                messagesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder addAllMessages(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.message.Message> values) {
            if (messagesBuilder_ == null) {
                ensureMessagesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, messages_);
                onChanged();
            } else {
                messagesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder clearMessages() {
            if (messagesBuilder_ == null) {
                messages_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                messagesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public Builder removeMessages(int index) {
            if (messagesBuilder_ == null) {
                ensureMessagesIsMutable();
                messages_.remove(index);
                onChanged();
            } else {
                messagesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.Message.Builder getMessagesBuilder(
                int index) {
            return getMessagesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.MessageOrBuilder getMessagesOrBuilder(
                int index) {
            if (messagesBuilder_ == null) {
                return messages_.get(index);
            } else {
                return messagesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessageOrBuilder> getMessagesOrBuilderList() {
            if (messagesBuilder_ != null) {
                return messagesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(messages_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.Message.Builder addMessagesBuilder() {
            return getMessagesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.message.Message
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.Message.Builder addMessagesBuilder(
                int index) {
            return getMessagesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.message.Message
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.message.Message.Builder> getMessagesBuilderList() {
            return getMessagesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.Message, im.turms.server.common.access.client.dto.model.message.Message.Builder, im.turms.server.common.access.client.dto.model.message.MessageOrBuilder> getMessagesFieldBuilder() {
            if (messagesBuilder_ == null) {
                messagesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        messages_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                messages_ = null;
            }
            return messagesBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Messages)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Messages)
    private static final im.turms.server.common.access.client.dto.model.message.Messages DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.message.Messages();
    }

    public static im.turms.server.common.access.client.dto.model.message.Messages getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Messages> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Messages parsePartialFrom(
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

    public static com.google.protobuf.Parser<Messages> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Messages> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.Messages getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}