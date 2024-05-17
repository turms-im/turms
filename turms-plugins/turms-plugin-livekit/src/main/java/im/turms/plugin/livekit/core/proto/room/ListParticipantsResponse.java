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

package im.turms.plugin.livekit.core.proto.room;

/**
 * Protobuf type {@code livekit.ListParticipantsResponse}
 */
public final class ListParticipantsResponse extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ListParticipantsResponse)
        ListParticipantsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ListParticipantsResponse.class.getName());
    }

    // Use ListParticipantsResponse.newBuilder() to construct.
    private ListParticipantsResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ListParticipantsResponse() {
        participants_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListParticipantsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListParticipantsResponse_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse.class,
                        im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse.Builder.class);
    }

    public static final int PARTICIPANTS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantInfo> participants_;

    /**
     * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantInfo> getParticipantsList() {
        return participants_;
    }

    /**
     * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder> getParticipantsOrBuilderList() {
        return participants_;
    }

    /**
     * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
     */
    @java.lang.Override
    public int getParticipantsCount() {
        return participants_.size();
    }

    /**
     * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfo getParticipants(int index) {
        return participants_.get(index);
    }

    /**
     * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder getParticipantsOrBuilder(
            int index) {
        return participants_.get(index);
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
        for (im.turms.plugin.livekit.core.proto.models.ParticipantInfo participantInfo : participants_) {
            output.writeMessage(1, participantInfo);
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
        for (im.turms.plugin.livekit.core.proto.models.ParticipantInfo participantInfo : participants_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, participantInfo);
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
        if (!(obj instanceof ListParticipantsResponse other)) {
            return super.equals(obj);
        }

        if (!getParticipantsList().equals(other.getParticipantsList())) {
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
        if (getParticipantsCount() > 0) {
            hash = (37 * hash) + PARTICIPANTS_FIELD_NUMBER;
            hash = (53 * hash) + getParticipantsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse prototype) {
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
     * Protobuf type {@code livekit.ListParticipantsResponse}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ListParticipantsResponse)
            im.turms.plugin.livekit.core.proto.room.ListParticipantsResponseOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListParticipantsResponse_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListParticipantsResponse_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse.class,
                            im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (participantsBuilder_ == null) {
                participants_ = java.util.Collections.emptyList();
            } else {
                participants_ = null;
                participantsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListParticipantsResponse_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse build() {
            im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse buildPartial() {
            im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse result =
                    new im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse result) {
            if (participantsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    participants_ = java.util.Collections.unmodifiableList(participants_);
                    bitField0_ &= ~0x00000001;
                }
                result.participants_ = participants_;
            } else {
                result.participants_ = participantsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse other) {
            if (other == im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse
                    .getDefaultInstance()) {
                return this;
            }
            if (participantsBuilder_ == null) {
                if (!other.participants_.isEmpty()) {
                    if (participants_.isEmpty()) {
                        participants_ = other.participants_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureParticipantsIsMutable();
                        participants_.addAll(other.participants_);
                    }
                    onChanged();
                }
            } else {
                if (!other.participants_.isEmpty()) {
                    if (participantsBuilder_.isEmpty()) {
                        participantsBuilder_.dispose();
                        participantsBuilder_ = null;
                        participants_ = other.participants_;
                        bitField0_ &= ~0x00000001;
                        participantsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getParticipantsFieldBuilder()
                                        : null;
                    } else {
                        participantsBuilder_.addAllMessages(other.participants_);
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
                            im.turms.plugin.livekit.core.proto.models.ParticipantInfo m =
                                    input.readMessage(
                                            im.turms.plugin.livekit.core.proto.models.ParticipantInfo
                                                    .parser(),
                                            extensionRegistry);
                            if (participantsBuilder_ == null) {
                                ensureParticipantsIsMutable();
                                participants_.add(m);
                            } else {
                                participantsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantInfo> participants_ =
                java.util.Collections.emptyList();

        private void ensureParticipantsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                participants_ = new java.util.ArrayList<>(participants_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantInfo, im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder> participantsBuilder_;

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantInfo> getParticipantsList() {
            if (participantsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(participants_);
            } else {
                return participantsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public int getParticipantsCount() {
            if (participantsBuilder_ == null) {
                return participants_.size();
            } else {
                return participantsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo getParticipants(
                int index) {
            if (participantsBuilder_ == null) {
                return participants_.get(index);
            } else {
                return participantsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder setParticipants(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo value) {
            if (participantsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParticipantsIsMutable();
                participants_.set(index, value);
                onChanged();
            } else {
                participantsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder setParticipants(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder builderForValue) {
            if (participantsBuilder_ == null) {
                ensureParticipantsIsMutable();
                participants_.set(index, builderForValue.build());
                onChanged();
            } else {
                participantsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder addParticipants(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo value) {
            if (participantsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParticipantsIsMutable();
                participants_.add(value);
                onChanged();
            } else {
                participantsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder addParticipants(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo value) {
            if (participantsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParticipantsIsMutable();
                participants_.add(index, value);
                onChanged();
            } else {
                participantsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder addParticipants(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder builderForValue) {
            if (participantsBuilder_ == null) {
                ensureParticipantsIsMutable();
                participants_.add(builderForValue.build());
                onChanged();
            } else {
                participantsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder addParticipants(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder builderForValue) {
            if (participantsBuilder_ == null) {
                ensureParticipantsIsMutable();
                participants_.add(index, builderForValue.build());
                onChanged();
            } else {
                participantsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder addAllParticipants(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.ParticipantInfo> values) {
            if (participantsBuilder_ == null) {
                ensureParticipantsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, participants_);
                onChanged();
            } else {
                participantsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder clearParticipants() {
            if (participantsBuilder_ == null) {
                participants_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                participantsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public Builder removeParticipants(int index) {
            if (participantsBuilder_ == null) {
                ensureParticipantsIsMutable();
                participants_.remove(index);
                onChanged();
            } else {
                participantsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder getParticipantsBuilder(
                int index) {
            return getParticipantsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder getParticipantsOrBuilder(
                int index) {
            if (participantsBuilder_ == null) {
                return participants_.get(index);
            } else {
                return participantsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder> getParticipantsOrBuilderList() {
            if (participantsBuilder_ != null) {
                return participantsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(participants_);
            }
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder addParticipantsBuilder() {
            return getParticipantsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder addParticipantsBuilder(
                int index) {
            return getParticipantsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.ParticipantInfo participants = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder> getParticipantsBuilderList() {
            return getParticipantsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantInfo, im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder> getParticipantsFieldBuilder() {
            if (participantsBuilder_ == null) {
                participantsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        participants_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                participants_ = null;
            }
            return participantsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ListParticipantsResponse)
    }

    // @@protoc_insertion_point(class_scope:livekit.ListParticipantsResponse)
    private static final im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse();
    }

    public static im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ListParticipantsResponse> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ListParticipantsResponse parsePartialFrom(
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

    public static com.google.protobuf.Parser<ListParticipantsResponse> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ListParticipantsResponse> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}