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

package im.turms.plugin.livekit.core.proto.ingress;

/**
 * Protobuf type {@code livekit.ListIngressResponse}
 */
public final class ListIngressResponse extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ListIngressResponse)
        ListIngressResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ListIngressResponse.class.getName());
    }

    // Use ListIngressResponse.newBuilder() to construct.
    private ListIngressResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ListIngressResponse() {
        items_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_ListIngressResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_ListIngressResponse_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse.class,
                        im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse.Builder.class);
    }

    public static final int ITEMS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.ingress.IngressInfo> items_;

    /**
     * <code>repeated .livekit.IngressInfo items = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.ingress.IngressInfo> getItemsList() {
        return items_;
    }

    /**
     * <code>repeated .livekit.IngressInfo items = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder> getItemsOrBuilderList() {
        return items_;
    }

    /**
     * <code>repeated .livekit.IngressInfo items = 1;</code>
     */
    @java.lang.Override
    public int getItemsCount() {
        return items_.size();
    }

    /**
     * <code>repeated .livekit.IngressInfo items = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressInfo getItems(int index) {
        return items_.get(index);
    }

    /**
     * <code>repeated .livekit.IngressInfo items = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder getItemsOrBuilder(
            int index) {
        return items_.get(index);
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
        for (IngressInfo ingressInfo : items_) {
            output.writeMessage(1, ingressInfo);
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
        for (IngressInfo ingressInfo : items_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, ingressInfo);
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
        if (!(obj instanceof ListIngressResponse other)) {
            return super.equals(obj);
        }

        if (!getItemsList().equals(other.getItemsList())) {
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
        if (getItemsCount() > 0) {
            hash = (37 * hash) + ITEMS_FIELD_NUMBER;
            hash = (53 * hash) + getItemsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse prototype) {
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
     * Protobuf type {@code livekit.ListIngressResponse}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ListIngressResponse)
            im.turms.plugin.livekit.core.proto.ingress.ListIngressResponseOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_ListIngressResponse_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_ListIngressResponse_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse.class,
                            im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (itemsBuilder_ == null) {
                items_ = java.util.Collections.emptyList();
            } else {
                items_ = null;
                itemsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_ListIngressResponse_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse build() {
            im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse result =
                    new im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse result) {
            if (itemsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    items_ = java.util.Collections.unmodifiableList(items_);
                    bitField0_ &= ~0x00000001;
                }
                result.items_ = items_;
            } else {
                result.items_ = itemsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse
                    .getDefaultInstance()) {
                return this;
            }
            if (itemsBuilder_ == null) {
                if (!other.items_.isEmpty()) {
                    if (items_.isEmpty()) {
                        items_ = other.items_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureItemsIsMutable();
                        items_.addAll(other.items_);
                    }
                    onChanged();
                }
            } else {
                if (!other.items_.isEmpty()) {
                    if (itemsBuilder_.isEmpty()) {
                        itemsBuilder_.dispose();
                        itemsBuilder_ = null;
                        items_ = other.items_;
                        bitField0_ &= ~0x00000001;
                        itemsBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getItemsFieldBuilder()
                                : null;
                    } else {
                        itemsBuilder_.addAllMessages(other.items_);
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
                            IngressInfo m =
                                    input.readMessage(IngressInfo.parser(), extensionRegistry);
                            if (itemsBuilder_ == null) {
                                ensureItemsIsMutable();
                                items_.add(m);
                            } else {
                                itemsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.plugin.livekit.core.proto.ingress.IngressInfo> items_ =
                java.util.Collections.emptyList();

        private void ensureItemsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                items_ = new java.util.ArrayList<>(items_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressInfo, im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder> itemsBuilder_;

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.ingress.IngressInfo> getItemsList() {
            if (itemsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(items_);
            } else {
                return itemsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public int getItemsCount() {
            if (itemsBuilder_ == null) {
                return items_.size();
            } else {
                return itemsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo getItems(int index) {
            if (itemsBuilder_ == null) {
                return items_.get(index);
            } else {
                return itemsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder setItems(
                int index,
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo value) {
            if (itemsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureItemsIsMutable();
                items_.set(index, value);
                onChanged();
            } else {
                itemsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder setItems(
                int index,
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder builderForValue) {
            if (itemsBuilder_ == null) {
                ensureItemsIsMutable();
                items_.set(index, builderForValue.build());
                onChanged();
            } else {
                itemsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder addItems(im.turms.plugin.livekit.core.proto.ingress.IngressInfo value) {
            if (itemsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureItemsIsMutable();
                items_.add(value);
                onChanged();
            } else {
                itemsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder addItems(
                int index,
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo value) {
            if (itemsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureItemsIsMutable();
                items_.add(index, value);
                onChanged();
            } else {
                itemsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder addItems(
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder builderForValue) {
            if (itemsBuilder_ == null) {
                ensureItemsIsMutable();
                items_.add(builderForValue.build());
                onChanged();
            } else {
                itemsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder addItems(
                int index,
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder builderForValue) {
            if (itemsBuilder_ == null) {
                ensureItemsIsMutable();
                items_.add(index, builderForValue.build());
                onChanged();
            } else {
                itemsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder addAllItems(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.ingress.IngressInfo> values) {
            if (itemsBuilder_ == null) {
                ensureItemsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, items_);
                onChanged();
            } else {
                itemsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder clearItems() {
            if (itemsBuilder_ == null) {
                items_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                itemsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public Builder removeItems(int index) {
            if (itemsBuilder_ == null) {
                ensureItemsIsMutable();
                items_.remove(index);
                onChanged();
            } else {
                itemsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder getItemsBuilder(
                int index) {
            return getItemsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder getItemsOrBuilder(
                int index) {
            if (itemsBuilder_ == null) {
                return items_.get(index);
            } else {
                return itemsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder> getItemsOrBuilderList() {
            if (itemsBuilder_ != null) {
                return itemsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(items_);
            }
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder addItemsBuilder() {
            return getItemsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.ingress.IngressInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder addItemsBuilder(
                int index) {
            return getItemsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.ingress.IngressInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.IngressInfo items = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder> getItemsBuilderList() {
            return getItemsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressInfo, im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder> getItemsFieldBuilder() {
            if (itemsBuilder_ == null) {
                itemsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        items_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                items_ = null;
            }
            return itemsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ListIngressResponse)
    }

    // @@protoc_insertion_point(class_scope:livekit.ListIngressResponse)
    private static final im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ListIngressResponse> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ListIngressResponse parsePartialFrom(
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

    public static com.google.protobuf.Parser<ListIngressResponse> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ListIngressResponse> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.ListIngressResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}