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
 * Protobuf type {@code im.turms.proto.MessagesWithTotalList}
 */
public final class MessagesWithTotalList extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.MessagesWithTotalList)
        MessagesWithTotalListOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use MessagesWithTotalList.newBuilder() to construct.
    private MessagesWithTotalList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private MessagesWithTotalList() {
        messagesWithTotalList_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new MessagesWithTotalList();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass.internal_static_im_turms_proto_MessagesWithTotalList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass.internal_static_im_turms_proto_MessagesWithTotalList_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.class,
                        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.Builder.class);
    }

    public static final int MESSAGES_WITH_TOTAL_LIST_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal> messagesWithTotalList_;

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal> getMessagesWithTotalListList() {
        return messagesWithTotalList_;
    }

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder> getMessagesWithTotalListOrBuilderList() {
        return messagesWithTotalList_;
    }

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    @java.lang.Override
    public int getMessagesWithTotalListCount() {
        return messagesWithTotalList_.size();
    }

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.MessagesWithTotal getMessagesWithTotalList(
            int index) {
        return messagesWithTotalList_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder getMessagesWithTotalListOrBuilder(
            int index) {
        return messagesWithTotalList_.get(index);
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
        for (MessagesWithTotal messagesWithTotal : messagesWithTotalList_) {
            output.writeMessage(1, messagesWithTotal);
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
        for (MessagesWithTotal messagesWithTotal : messagesWithTotalList_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, messagesWithTotal);
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
        if (!(obj instanceof MessagesWithTotalList other)) {
            return super.equals(obj);
        }

        if (!getMessagesWithTotalListList().equals(other.getMessagesWithTotalListList())) {
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
        if (getMessagesWithTotalListCount() > 0) {
            hash = (37 * hash) + MESSAGES_WITH_TOTAL_LIST_FIELD_NUMBER;
            hash = (53 * hash) + getMessagesWithTotalListList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList parseFrom(
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
            im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList prototype) {
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
     * Protobuf type {@code im.turms.proto.MessagesWithTotalList}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.MessagesWithTotalList)
            im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass.internal_static_im_turms_proto_MessagesWithTotalList_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass.internal_static_im_turms_proto_MessagesWithTotalList_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.class,
                            im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (messagesWithTotalListBuilder_ == null) {
                messagesWithTotalList_ = java.util.Collections.emptyList();
            } else {
                messagesWithTotalList_ = null;
                messagesWithTotalListBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass.internal_static_im_turms_proto_MessagesWithTotalList_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList build() {
            im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList buildPartial() {
            im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList result =
                    new im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList result) {
            if (messagesWithTotalListBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    messagesWithTotalList_ =
                            java.util.Collections.unmodifiableList(messagesWithTotalList_);
                    bitField0_ &= ~0x00000001;
                }
                result.messagesWithTotalList_ = messagesWithTotalList_;
            } else {
                result.messagesWithTotalList_ = messagesWithTotalListBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList other) {
            if (other == im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                    .getDefaultInstance()) {
                return this;
            }
            if (messagesWithTotalListBuilder_ == null) {
                if (!other.messagesWithTotalList_.isEmpty()) {
                    if (messagesWithTotalList_.isEmpty()) {
                        messagesWithTotalList_ = other.messagesWithTotalList_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureMessagesWithTotalListIsMutable();
                        messagesWithTotalList_.addAll(other.messagesWithTotalList_);
                    }
                    onChanged();
                }
            } else {
                if (!other.messagesWithTotalList_.isEmpty()) {
                    if (messagesWithTotalListBuilder_.isEmpty()) {
                        messagesWithTotalListBuilder_.dispose();
                        messagesWithTotalListBuilder_ = null;
                        messagesWithTotalList_ = other.messagesWithTotalList_;
                        bitField0_ &= ~0x00000001;
                        messagesWithTotalListBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getMessagesWithTotalListFieldBuilder()
                                        : null;
                    } else {
                        messagesWithTotalListBuilder_.addAllMessages(other.messagesWithTotalList_);
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
                            MessagesWithTotal m = input.readMessage(MessagesWithTotal.parser(),
                                    extensionRegistry);
                            if (messagesWithTotalListBuilder_ == null) {
                                ensureMessagesWithTotalListIsMutable();
                                messagesWithTotalList_.add(m);
                            } else {
                                messagesWithTotalListBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal> messagesWithTotalList_ =
                java.util.Collections.emptyList();

        private void ensureMessagesWithTotalListIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                messagesWithTotalList_ = new java.util.ArrayList<>(messagesWithTotalList_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal, im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder, im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder> messagesWithTotalListBuilder_;

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal> getMessagesWithTotalListList() {
            if (messagesWithTotalListBuilder_ == null) {
                return java.util.Collections.unmodifiableList(messagesWithTotalList_);
            } else {
                return messagesWithTotalListBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public int getMessagesWithTotalListCount() {
            if (messagesWithTotalListBuilder_ == null) {
                return messagesWithTotalList_.size();
            } else {
                return messagesWithTotalListBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotal getMessagesWithTotalList(
                int index) {
            if (messagesWithTotalListBuilder_ == null) {
                return messagesWithTotalList_.get(index);
            } else {
                return messagesWithTotalListBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder setMessagesWithTotalList(
                int index,
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotal value) {
            if (messagesWithTotalListBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.set(index, value);
                onChanged();
            } else {
                messagesWithTotalListBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder setMessagesWithTotalList(
                int index,
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder builderForValue) {
            if (messagesWithTotalListBuilder_ == null) {
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.set(index, builderForValue.build());
                onChanged();
            } else {
                messagesWithTotalListBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder addMessagesWithTotalList(
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotal value) {
            if (messagesWithTotalListBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.add(value);
                onChanged();
            } else {
                messagesWithTotalListBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder addMessagesWithTotalList(
                int index,
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotal value) {
            if (messagesWithTotalListBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.add(index, value);
                onChanged();
            } else {
                messagesWithTotalListBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder addMessagesWithTotalList(
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder builderForValue) {
            if (messagesWithTotalListBuilder_ == null) {
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.add(builderForValue.build());
                onChanged();
            } else {
                messagesWithTotalListBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder addMessagesWithTotalList(
                int index,
                im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder builderForValue) {
            if (messagesWithTotalListBuilder_ == null) {
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.add(index, builderForValue.build());
                onChanged();
            } else {
                messagesWithTotalListBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder addAllMessagesWithTotalList(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.message.MessagesWithTotal> values) {
            if (messagesWithTotalListBuilder_ == null) {
                ensureMessagesWithTotalListIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values,
                        messagesWithTotalList_);
                onChanged();
            } else {
                messagesWithTotalListBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder clearMessagesWithTotalList() {
            if (messagesWithTotalListBuilder_ == null) {
                messagesWithTotalList_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                messagesWithTotalListBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public Builder removeMessagesWithTotalList(int index) {
            if (messagesWithTotalListBuilder_ == null) {
                ensureMessagesWithTotalListIsMutable();
                messagesWithTotalList_.remove(index);
                onChanged();
            } else {
                messagesWithTotalListBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder getMessagesWithTotalListBuilder(
                int index) {
            return getMessagesWithTotalListFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder getMessagesWithTotalListOrBuilder(
                int index) {
            if (messagesWithTotalListBuilder_ == null) {
                return messagesWithTotalList_.get(index);
            } else {
                return messagesWithTotalListBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder> getMessagesWithTotalListOrBuilderList() {
            if (messagesWithTotalListBuilder_ != null) {
                return messagesWithTotalListBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(messagesWithTotalList_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder addMessagesWithTotalListBuilder() {
            return getMessagesWithTotalListFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.message.MessagesWithTotal
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder addMessagesWithTotalListBuilder(
                int index) {
            return getMessagesWithTotalListFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.message.MessagesWithTotal
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder> getMessagesWithTotalListBuilderList() {
            return getMessagesWithTotalListFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal, im.turms.server.common.access.client.dto.model.message.MessagesWithTotal.Builder, im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder> getMessagesWithTotalListFieldBuilder() {
            if (messagesWithTotalListBuilder_ == null) {
                messagesWithTotalListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        messagesWithTotalList_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                messagesWithTotalList_ = null;
            }
            return messagesWithTotalListBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.MessagesWithTotalList)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.MessagesWithTotalList)
    private static final im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList();
    }

    public static im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MessagesWithTotalList> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public MessagesWithTotalList parsePartialFrom(
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

    public static com.google.protobuf.Parser<MessagesWithTotalList> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MessagesWithTotalList> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}