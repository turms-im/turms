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
 * Protobuf type {@code livekit.RoomEgress}
 */
public final class RoomEgress extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.RoomEgress)
        RoomEgressOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                RoomEgress.class.getName());
    }

    // Use RoomEgress.newBuilder() to construct.
    private RoomEgress(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private RoomEgress() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_RoomEgress_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_RoomEgress_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.RoomEgress.class,
                        im.turms.plugin.livekit.core.proto.room.RoomEgress.Builder.class);
    }

    private int bitField0_;
    public static final int ROOM_FIELD_NUMBER = 1;
    private im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest room_;

    /**
     * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
     *
     * @return Whether the room field is set.
     */
    @java.lang.Override
    public boolean hasRoom() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
     *
     * @return The room.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getRoom() {
        return room_ == null
                ? im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                        .getDefaultInstance()
                : room_;
    }

    /**
     * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder getRoomOrBuilder() {
        return room_ == null
                ? im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                        .getDefaultInstance()
                : room_;
    }

    public static final int PARTICIPANT_FIELD_NUMBER = 3;
    private im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress participant_;

    /**
     * <code>.livekit.AutoParticipantEgress participant = 3;</code>
     *
     * @return Whether the participant field is set.
     */
    @java.lang.Override
    public boolean hasParticipant() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>.livekit.AutoParticipantEgress participant = 3;</code>
     *
     * @return The participant.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress getParticipant() {
        return participant_ == null
                ? im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                        .getDefaultInstance()
                : participant_;
    }

    /**
     * <code>.livekit.AutoParticipantEgress participant = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgressOrBuilder getParticipantOrBuilder() {
        return participant_ == null
                ? im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                        .getDefaultInstance()
                : participant_;
    }

    public static final int TRACKS_FIELD_NUMBER = 2;
    private im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress tracks_;

    /**
     * <code>.livekit.AutoTrackEgress tracks = 2;</code>
     *
     * @return Whether the tracks field is set.
     */
    @java.lang.Override
    public boolean hasTracks() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>.livekit.AutoTrackEgress tracks = 2;</code>
     *
     * @return The tracks.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress getTracks() {
        return tracks_ == null
                ? im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress.getDefaultInstance()
                : tracks_;
    }

    /**
     * <code>.livekit.AutoTrackEgress tracks = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.AutoTrackEgressOrBuilder getTracksOrBuilder() {
        return tracks_ == null
                ? im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress.getDefaultInstance()
                : tracks_;
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(1, getRoom());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(2, getTracks());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(3, getParticipant());
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, getRoom());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, getTracks());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, getParticipant());
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
        if (!(obj instanceof RoomEgress other)) {
            return super.equals(obj);
        }

        if (hasRoom() != other.hasRoom()) {
            return false;
        }
        if (hasRoom()) {
            if (!getRoom().equals(other.getRoom())) {
                return false;
            }
        }
        if (hasParticipant() != other.hasParticipant()) {
            return false;
        }
        if (hasParticipant()) {
            if (!getParticipant().equals(other.getParticipant())) {
                return false;
            }
        }
        if (hasTracks() != other.hasTracks()) {
            return false;
        }
        if (hasTracks()) {
            if (!getTracks().equals(other.getTracks())) {
                return false;
            }
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
        if (hasRoom()) {
            hash = (37 * hash) + ROOM_FIELD_NUMBER;
            hash = (53 * hash) + getRoom().hashCode();
        }
        if (hasParticipant()) {
            hash = (37 * hash) + PARTICIPANT_FIELD_NUMBER;
            hash = (53 * hash) + getParticipant().hashCode();
        }
        if (hasTracks()) {
            hash = (37 * hash) + TRACKS_FIELD_NUMBER;
            hash = (53 * hash) + getTracks().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.room.RoomEgress prototype) {
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
     * Protobuf type {@code livekit.RoomEgress}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.RoomEgress)
            im.turms.plugin.livekit.core.proto.room.RoomEgressOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_RoomEgress_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_RoomEgress_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.RoomEgress.class,
                            im.turms.plugin.livekit.core.proto.room.RoomEgress.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.room.RoomEgress.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getRoomFieldBuilder();
                getParticipantFieldBuilder();
                getTracksFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            room_ = null;
            if (roomBuilder_ != null) {
                roomBuilder_.dispose();
                roomBuilder_ = null;
            }
            participant_ = null;
            if (participantBuilder_ != null) {
                participantBuilder_.dispose();
                participantBuilder_ = null;
            }
            tracks_ = null;
            if (tracksBuilder_ != null) {
                tracksBuilder_.dispose();
                tracksBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_RoomEgress_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.RoomEgress getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.RoomEgress.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.RoomEgress build() {
            im.turms.plugin.livekit.core.proto.room.RoomEgress result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.RoomEgress buildPartial() {
            im.turms.plugin.livekit.core.proto.room.RoomEgress result =
                    new im.turms.plugin.livekit.core.proto.room.RoomEgress(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.room.RoomEgress result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.room_ = roomBuilder_ == null
                        ? room_
                        : roomBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.participant_ = participantBuilder_ == null
                        ? participant_
                        : participantBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.tracks_ = tracksBuilder_ == null
                        ? tracks_
                        : tracksBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.RoomEgress) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.room.RoomEgress) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.room.RoomEgress other) {
            if (other == im.turms.plugin.livekit.core.proto.room.RoomEgress.getDefaultInstance()) {
                return this;
            }
            if (other.hasRoom()) {
                mergeRoom(other.getRoom());
            }
            if (other.hasParticipant()) {
                mergeParticipant(other.getParticipant());
            }
            if (other.hasTracks()) {
                mergeTracks(other.getTracks());
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
                            input.readMessage(getRoomFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            input.readMessage(getTracksFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000004;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getParticipantFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000002;
                        } // case 26
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

        private im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest room_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder> roomBuilder_;

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         *
         * @return Whether the room field is set.
         */
        public boolean hasRoom() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         *
         * @return The room.
         */
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getRoom() {
            if (roomBuilder_ == null) {
                return room_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                                .getDefaultInstance()
                        : room_;
            } else {
                return roomBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        public Builder setRoom(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest value) {
            if (roomBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                room_ = value;
            } else {
                roomBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        public Builder setRoom(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder builderForValue) {
            if (roomBuilder_ == null) {
                room_ = builderForValue.build();
            } else {
                roomBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        public Builder mergeRoom(
                im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest value) {
            if (roomBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)
                        && room_ != null
                        && room_ != im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                                .getDefaultInstance()) {
                    getRoomBuilder().mergeFrom(value);
                } else {
                    room_ = value;
                }
            } else {
                roomBuilder_.mergeFrom(value);
            }
            if (room_ != null) {
                bitField0_ |= 0x00000001;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        public Builder clearRoom() {
            bitField0_ &= ~0x00000001;
            room_ = null;
            if (roomBuilder_ != null) {
                roomBuilder_.dispose();
                roomBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder getRoomBuilder() {
            bitField0_ |= 0x00000001;
            onChanged();
            return getRoomFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder getRoomOrBuilder() {
            if (roomBuilder_ != null) {
                return roomBuilder_.getMessageOrBuilder();
            } else {
                return room_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest
                                .getDefaultInstance()
                        : room_;
            }
        }

        /**
         * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest.Builder, im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder> getRoomFieldBuilder() {
            if (roomBuilder_ == null) {
                roomBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getRoom(),
                        getParentForChildren(),
                        isClean());
                room_ = null;
            }
            return roomBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress participant_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress, im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.Builder, im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgressOrBuilder> participantBuilder_;

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         *
         * @return Whether the participant field is set.
         */
        public boolean hasParticipant() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         *
         * @return The participant.
         */
        public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress getParticipant() {
            if (participantBuilder_ == null) {
                return participant_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                                .getDefaultInstance()
                        : participant_;
            } else {
                return participantBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        public Builder setParticipant(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress value) {
            if (participantBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                participant_ = value;
            } else {
                participantBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        public Builder setParticipant(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.Builder builderForValue) {
            if (participantBuilder_ == null) {
                participant_ = builderForValue.build();
            } else {
                participantBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        public Builder mergeParticipant(
                im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress value) {
            if (participantBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)
                        && participant_ != null
                        && participant_ != im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                                .getDefaultInstance()) {
                    getParticipantBuilder().mergeFrom(value);
                } else {
                    participant_ = value;
                }
            } else {
                participantBuilder_.mergeFrom(value);
            }
            if (participant_ != null) {
                bitField0_ |= 0x00000002;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        public Builder clearParticipant() {
            bitField0_ &= ~0x00000002;
            participant_ = null;
            if (participantBuilder_ != null) {
                participantBuilder_.dispose();
                participantBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.Builder getParticipantBuilder() {
            bitField0_ |= 0x00000002;
            onChanged();
            return getParticipantFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgressOrBuilder getParticipantOrBuilder() {
            if (participantBuilder_ != null) {
                return participantBuilder_.getMessageOrBuilder();
            } else {
                return participant_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress
                                .getDefaultInstance()
                        : participant_;
            }
        }

        /**
         * <code>.livekit.AutoParticipantEgress participant = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress, im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress.Builder, im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgressOrBuilder> getParticipantFieldBuilder() {
            if (participantBuilder_ == null) {
                participantBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getParticipant(),
                        getParentForChildren(),
                        isClean());
                participant_ = null;
            }
            return participantBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress tracks_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress, im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress.Builder, im.turms.plugin.livekit.core.proto.egress.AutoTrackEgressOrBuilder> tracksBuilder_;

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         *
         * @return Whether the tracks field is set.
         */
        public boolean hasTracks() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         *
         * @return The tracks.
         */
        public im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress getTracks() {
            if (tracksBuilder_ == null) {
                return tracks_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress
                                .getDefaultInstance()
                        : tracks_;
            } else {
                return tracksBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        public Builder setTracks(im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress value) {
            if (tracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                tracks_ = value;
            } else {
                tracksBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        public Builder setTracks(
                im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress.Builder builderForValue) {
            if (tracksBuilder_ == null) {
                tracks_ = builderForValue.build();
            } else {
                tracksBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        public Builder mergeTracks(
                im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress value) {
            if (tracksBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)
                        && tracks_ != null
                        && tracks_ != im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress
                                .getDefaultInstance()) {
                    getTracksBuilder().mergeFrom(value);
                } else {
                    tracks_ = value;
                }
            } else {
                tracksBuilder_.mergeFrom(value);
            }
            if (tracks_ != null) {
                bitField0_ |= 0x00000004;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        public Builder clearTracks() {
            bitField0_ &= ~0x00000004;
            tracks_ = null;
            if (tracksBuilder_ != null) {
                tracksBuilder_.dispose();
                tracksBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress.Builder getTracksBuilder() {
            bitField0_ |= 0x00000004;
            onChanged();
            return getTracksFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.AutoTrackEgressOrBuilder getTracksOrBuilder() {
            if (tracksBuilder_ != null) {
                return tracksBuilder_.getMessageOrBuilder();
            } else {
                return tracks_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress
                                .getDefaultInstance()
                        : tracks_;
            }
        }

        /**
         * <code>.livekit.AutoTrackEgress tracks = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress, im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress.Builder, im.turms.plugin.livekit.core.proto.egress.AutoTrackEgressOrBuilder> getTracksFieldBuilder() {
            if (tracksBuilder_ == null) {
                tracksBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getTracks(),
                        getParentForChildren(),
                        isClean());
                tracks_ = null;
            }
            return tracksBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.RoomEgress)
    }

    // @@protoc_insertion_point(class_scope:livekit.RoomEgress)
    private static final im.turms.plugin.livekit.core.proto.room.RoomEgress DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.RoomEgress();
    }

    public static im.turms.plugin.livekit.core.proto.room.RoomEgress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RoomEgress> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public RoomEgress parsePartialFrom(
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

    public static com.google.protobuf.Parser<RoomEgress> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RoomEgress> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.RoomEgress getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}