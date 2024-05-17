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
 * Protobuf type {@code livekit.ListRoomsResponse}
 */
public final class ListRoomsResponse extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ListRoomsResponse)
        ListRoomsResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ListRoomsResponse.class.getName());
    }

    // Use ListRoomsResponse.newBuilder() to construct.
    private ListRoomsResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ListRoomsResponse() {
        rooms_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListRoomsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListRoomsResponse_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.ListRoomsResponse.class,
                        im.turms.plugin.livekit.core.proto.room.ListRoomsResponse.Builder.class);
    }

    public static final int ROOMS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.Room> rooms_;

    /**
     * <code>repeated .livekit.Room rooms = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.Room> getRoomsList() {
        return rooms_;
    }

    /**
     * <code>repeated .livekit.Room rooms = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.RoomOrBuilder> getRoomsOrBuilderList() {
        return rooms_;
    }

    /**
     * <code>repeated .livekit.Room rooms = 1;</code>
     */
    @java.lang.Override
    public int getRoomsCount() {
        return rooms_.size();
    }

    /**
     * <code>repeated .livekit.Room rooms = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Room getRooms(int index) {
        return rooms_.get(index);
    }

    /**
     * <code>repeated .livekit.Room rooms = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RoomOrBuilder getRoomsOrBuilder(int index) {
        return rooms_.get(index);
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
        for (im.turms.plugin.livekit.core.proto.models.Room room : rooms_) {
            output.writeMessage(1, room);
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
        for (im.turms.plugin.livekit.core.proto.models.Room room : rooms_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, room);
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
        if (!(obj instanceof ListRoomsResponse other)) {
            return super.equals(obj);
        }

        if (!getRoomsList().equals(other.getRoomsList())) {
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
        if (getRoomsCount() > 0) {
            hash = (37 * hash) + ROOMS_FIELD_NUMBER;
            hash = (53 * hash) + getRoomsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.ListRoomsResponse prototype) {
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
     * Protobuf type {@code livekit.ListRoomsResponse}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ListRoomsResponse)
            im.turms.plugin.livekit.core.proto.room.ListRoomsResponseOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListRoomsResponse_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListRoomsResponse_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.ListRoomsResponse.class,
                            im.turms.plugin.livekit.core.proto.room.ListRoomsResponse.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.room.ListRoomsResponse.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (roomsBuilder_ == null) {
                rooms_ = java.util.Collections.emptyList();
            } else {
                rooms_ = null;
                roomsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_ListRoomsResponse_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.ListRoomsResponse getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.ListRoomsResponse.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.ListRoomsResponse build() {
            im.turms.plugin.livekit.core.proto.room.ListRoomsResponse result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.ListRoomsResponse buildPartial() {
            im.turms.plugin.livekit.core.proto.room.ListRoomsResponse result =
                    new im.turms.plugin.livekit.core.proto.room.ListRoomsResponse(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.room.ListRoomsResponse result) {
            if (roomsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    rooms_ = java.util.Collections.unmodifiableList(rooms_);
                    bitField0_ &= ~0x00000001;
                }
                result.rooms_ = rooms_;
            } else {
                result.rooms_ = roomsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.ListRoomsResponse result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.ListRoomsResponse) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.room.ListRoomsResponse) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.room.ListRoomsResponse other) {
            if (other == im.turms.plugin.livekit.core.proto.room.ListRoomsResponse
                    .getDefaultInstance()) {
                return this;
            }
            if (roomsBuilder_ == null) {
                if (!other.rooms_.isEmpty()) {
                    if (rooms_.isEmpty()) {
                        rooms_ = other.rooms_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureRoomsIsMutable();
                        rooms_.addAll(other.rooms_);
                    }
                    onChanged();
                }
            } else {
                if (!other.rooms_.isEmpty()) {
                    if (roomsBuilder_.isEmpty()) {
                        roomsBuilder_.dispose();
                        roomsBuilder_ = null;
                        rooms_ = other.rooms_;
                        bitField0_ &= ~0x00000001;
                        roomsBuilder_ = com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                ? getRoomsFieldBuilder()
                                : null;
                    } else {
                        roomsBuilder_.addAllMessages(other.rooms_);
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
                            im.turms.plugin.livekit.core.proto.models.Room m = input.readMessage(
                                    im.turms.plugin.livekit.core.proto.models.Room.parser(),
                                    extensionRegistry);
                            if (roomsBuilder_ == null) {
                                ensureRoomsIsMutable();
                                rooms_.add(m);
                            } else {
                                roomsBuilder_.addMessage(m);
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

        private java.util.List<im.turms.plugin.livekit.core.proto.models.Room> rooms_ =
                java.util.Collections.emptyList();

        private void ensureRoomsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                rooms_ = new java.util.ArrayList<>(rooms_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Room, im.turms.plugin.livekit.core.proto.models.Room.Builder, im.turms.plugin.livekit.core.proto.models.RoomOrBuilder> roomsBuilder_;

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Room> getRoomsList() {
            if (roomsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(rooms_);
            } else {
                return roomsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public int getRoomsCount() {
            if (roomsBuilder_ == null) {
                return rooms_.size();
            } else {
                return roomsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Room getRooms(int index) {
            if (roomsBuilder_ == null) {
                return rooms_.get(index);
            } else {
                return roomsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder setRooms(int index, im.turms.plugin.livekit.core.proto.models.Room value) {
            if (roomsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureRoomsIsMutable();
                rooms_.set(index, value);
                onChanged();
            } else {
                roomsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder setRooms(
                int index,
                im.turms.plugin.livekit.core.proto.models.Room.Builder builderForValue) {
            if (roomsBuilder_ == null) {
                ensureRoomsIsMutable();
                rooms_.set(index, builderForValue.build());
                onChanged();
            } else {
                roomsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder addRooms(im.turms.plugin.livekit.core.proto.models.Room value) {
            if (roomsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureRoomsIsMutable();
                rooms_.add(value);
                onChanged();
            } else {
                roomsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder addRooms(int index, im.turms.plugin.livekit.core.proto.models.Room value) {
            if (roomsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureRoomsIsMutable();
                rooms_.add(index, value);
                onChanged();
            } else {
                roomsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder addRooms(
                im.turms.plugin.livekit.core.proto.models.Room.Builder builderForValue) {
            if (roomsBuilder_ == null) {
                ensureRoomsIsMutable();
                rooms_.add(builderForValue.build());
                onChanged();
            } else {
                roomsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder addRooms(
                int index,
                im.turms.plugin.livekit.core.proto.models.Room.Builder builderForValue) {
            if (roomsBuilder_ == null) {
                ensureRoomsIsMutable();
                rooms_.add(index, builderForValue.build());
                onChanged();
            } else {
                roomsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder addAllRooms(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.Room> values) {
            if (roomsBuilder_ == null) {
                ensureRoomsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, rooms_);
                onChanged();
            } else {
                roomsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder clearRooms() {
            if (roomsBuilder_ == null) {
                rooms_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                roomsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public Builder removeRooms(int index) {
            if (roomsBuilder_ == null) {
                ensureRoomsIsMutable();
                rooms_.remove(index);
                onChanged();
            } else {
                roomsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Room.Builder getRoomsBuilder(int index) {
            return getRoomsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RoomOrBuilder getRoomsOrBuilder(
                int index) {
            if (roomsBuilder_ == null) {
                return rooms_.get(index);
            } else {
                return roomsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.RoomOrBuilder> getRoomsOrBuilderList() {
            if (roomsBuilder_ != null) {
                return roomsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(rooms_);
            }
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Room.Builder addRoomsBuilder() {
            return getRoomsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Room.Builder addRoomsBuilder(int index) {
            return getRoomsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.Room rooms = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Room.Builder> getRoomsBuilderList() {
            return getRoomsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Room, im.turms.plugin.livekit.core.proto.models.Room.Builder, im.turms.plugin.livekit.core.proto.models.RoomOrBuilder> getRoomsFieldBuilder() {
            if (roomsBuilder_ == null) {
                roomsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        rooms_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                rooms_ = null;
            }
            return roomsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ListRoomsResponse)
    }

    // @@protoc_insertion_point(class_scope:livekit.ListRoomsResponse)
    private static final im.turms.plugin.livekit.core.proto.room.ListRoomsResponse DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.ListRoomsResponse();
    }

    public static im.turms.plugin.livekit.core.proto.room.ListRoomsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ListRoomsResponse> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ListRoomsResponse parsePartialFrom(
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

    public static com.google.protobuf.Parser<ListRoomsResponse> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ListRoomsResponse> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.ListRoomsResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}