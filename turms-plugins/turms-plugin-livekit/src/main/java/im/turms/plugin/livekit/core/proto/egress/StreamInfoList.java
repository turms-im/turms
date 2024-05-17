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

package im.turms.plugin.livekit.core.proto.egress;

/**
 * Protobuf type {@code livekit.StreamInfoList}
 */
@java.lang.Deprecated
public final class StreamInfoList extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.StreamInfoList)
        StreamInfoListOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                StreamInfoList.class.getName());
    }

    // Use StreamInfoList.newBuilder() to construct.
    private StreamInfoList(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private StreamInfoList() {
        info_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfoList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfoList_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.StreamInfoList.class,
                        im.turms.plugin.livekit.core.proto.egress.StreamInfoList.Builder.class);
    }

    public static final int INFO_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> info_;

    /**
     * <code>repeated .livekit.StreamInfo info = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> getInfoList() {
        return info_;
    }

    /**
     * <code>repeated .livekit.StreamInfo info = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getInfoOrBuilderList() {
        return info_;
    }

    /**
     * <code>repeated .livekit.StreamInfo info = 1;</code>
     */
    @java.lang.Override
    public int getInfoCount() {
        return info_.size();
    }

    /**
     * <code>repeated .livekit.StreamInfo info = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfo getInfo(int index) {
        return info_.get(index);
    }

    /**
     * <code>repeated .livekit.StreamInfo info = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder getInfoOrBuilder(
            int index) {
        return info_.get(index);
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
        for (StreamInfo streamInfo : info_) {
            output.writeMessage(1, streamInfo);
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
        for (StreamInfo streamInfo : info_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, streamInfo);
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
        if (!(obj instanceof StreamInfoList other)) {
            return super.equals(obj);
        }

        if (!getInfoList().equals(other.getInfoList())) {
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
        if (getInfoCount() > 0) {
            hash = (37 * hash) + INFO_FIELD_NUMBER;
            hash = (53 * hash) + getInfoList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.StreamInfoList prototype) {
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
     * Protobuf type {@code livekit.StreamInfoList}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.StreamInfoList)
            im.turms.plugin.livekit.core.proto.egress.StreamInfoListOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfoList_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfoList_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.StreamInfoList.class,
                            im.turms.plugin.livekit.core.proto.egress.StreamInfoList.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.StreamInfoList.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (infoBuilder_ == null) {
                info_ = java.util.Collections.emptyList();
            } else {
                info_ = null;
                infoBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamInfoList_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoList getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.StreamInfoList.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoList build() {
            im.turms.plugin.livekit.core.proto.egress.StreamInfoList result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoList buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.StreamInfoList result =
                    new im.turms.plugin.livekit.core.proto.egress.StreamInfoList(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.egress.StreamInfoList result) {
            if (infoBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    info_ = java.util.Collections.unmodifiableList(info_);
                    bitField0_ &= ~0x00000001;
                }
                result.info_ = info_;
            } else {
                result.info_ = infoBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.StreamInfoList result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.StreamInfoList) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.StreamInfoList) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.StreamInfoList other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.StreamInfoList
                    .getDefaultInstance()) {
                return this;
            }
            if (infoBuilder_ == null) {
                if (!other.info_.isEmpty()) {
                    if (info_.isEmpty()) {
                        info_ = other.info_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureInfoIsMutable();
                        info_.addAll(other.info_);
                    }
                    onChanged();
                }
            } else {
                if (!other.info_.isEmpty()) {
                    if (infoBuilder_.isEmpty()) {
                        infoBuilder_.dispose();
                        infoBuilder_ = null;
                        info_ = other.info_;
                        bitField0_ &= ~0x00000001;
                        infoBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getInfoFieldBuilder()
                                : null;
                    } else {
                        infoBuilder_.addAllMessages(other.info_);
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
                            StreamInfo m =
                                    input.readMessage(StreamInfo.parser(), extensionRegistry);
                            if (infoBuilder_ == null) {
                                ensureInfoIsMutable();
                                info_.add(m);
                            } else {
                                infoBuilder_.addMessage(m);
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

        private java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> info_ =
                java.util.Collections.emptyList();

        private void ensureInfoIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                info_ = new java.util.ArrayList<>(info_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamInfo, im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder, im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> infoBuilder_;

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> getInfoList() {
            if (infoBuilder_ == null) {
                return java.util.Collections.unmodifiableList(info_);
            } else {
                return infoBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public int getInfoCount() {
            if (infoBuilder_ == null) {
                return info_.size();
            } else {
                return infoBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo getInfo(int index) {
            if (infoBuilder_ == null) {
                return info_.get(index);
            } else {
                return infoBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder setInfo(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo value) {
            if (infoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureInfoIsMutable();
                info_.set(index, value);
                onChanged();
            } else {
                infoBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder setInfo(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder builderForValue) {
            if (infoBuilder_ == null) {
                ensureInfoIsMutable();
                info_.set(index, builderForValue.build());
                onChanged();
            } else {
                infoBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder addInfo(im.turms.plugin.livekit.core.proto.egress.StreamInfo value) {
            if (infoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureInfoIsMutable();
                info_.add(value);
                onChanged();
            } else {
                infoBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder addInfo(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo value) {
            if (infoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureInfoIsMutable();
                info_.add(index, value);
                onChanged();
            } else {
                infoBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder addInfo(
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder builderForValue) {
            if (infoBuilder_ == null) {
                ensureInfoIsMutable();
                info_.add(builderForValue.build());
                onChanged();
            } else {
                infoBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder addInfo(
                int index,
                im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder builderForValue) {
            if (infoBuilder_ == null) {
                ensureInfoIsMutable();
                info_.add(index, builderForValue.build());
                onChanged();
            } else {
                infoBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder addAllInfo(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfo> values) {
            if (infoBuilder_ == null) {
                ensureInfoIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, info_);
                onChanged();
            } else {
                infoBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder clearInfo() {
            if (infoBuilder_ == null) {
                info_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                infoBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public Builder removeInfo(int index) {
            if (infoBuilder_ == null) {
                ensureInfoIsMutable();
                info_.remove(index);
                onChanged();
            } else {
                infoBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder getInfoBuilder(
                int index) {
            return getInfoFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder getInfoOrBuilder(
                int index) {
            if (infoBuilder_ == null) {
                return info_.get(index);
            } else {
                return infoBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getInfoOrBuilderList() {
            if (infoBuilder_ != null) {
                return infoBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(info_);
            }
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder addInfoBuilder() {
            return getInfoFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.egress.StreamInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder addInfoBuilder(
                int index) {
            return getInfoFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.egress.StreamInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.StreamInfo info = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder> getInfoBuilderList() {
            return getInfoFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.egress.StreamInfo, im.turms.plugin.livekit.core.proto.egress.StreamInfo.Builder, im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getInfoFieldBuilder() {
            if (infoBuilder_ == null) {
                infoBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        info_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                info_ = null;
            }
            return infoBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.StreamInfoList)
    }

    // @@protoc_insertion_point(class_scope:livekit.StreamInfoList)
    private static final im.turms.plugin.livekit.core.proto.egress.StreamInfoList DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.StreamInfoList();
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamInfoList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StreamInfoList> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public StreamInfoList parsePartialFrom(
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

    public static com.google.protobuf.Parser<StreamInfoList> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StreamInfoList> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamInfoList getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}