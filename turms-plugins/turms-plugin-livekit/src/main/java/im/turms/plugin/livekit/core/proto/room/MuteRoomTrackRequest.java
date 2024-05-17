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
 * Protobuf type {@code livekit.MuteRoomTrackRequest}
 */
public final class MuteRoomTrackRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.MuteRoomTrackRequest)
        MuteRoomTrackRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                MuteRoomTrackRequest.class.getName());
    }

    // Use MuteRoomTrackRequest.newBuilder() to construct.
    private MuteRoomTrackRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private MuteRoomTrackRequest() {
        room_ = "";
        identity_ = "";
        trackSid_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest.class,
                        im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest.Builder.class);
    }

    public static final int ROOM_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object room_ = "";

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string room = 1;</code>
     *
     * @return The room.
     */
    @java.lang.Override
    public java.lang.String getRoom() {
        java.lang.Object ref = room_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            room_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string room = 1;</code>
     *
     * @return The bytes for room.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRoomBytes() {
        java.lang.Object ref = room_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            room_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int IDENTITY_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object identity_ = "";

    /**
     * <code>string identity = 2;</code>
     *
     * @return The identity.
     */
    @java.lang.Override
    public java.lang.String getIdentity() {
        java.lang.Object ref = identity_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            identity_ = s;
            return s;
        }
    }

    /**
     * <code>string identity = 2;</code>
     *
     * @return The bytes for identity.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIdentityBytes() {
        java.lang.Object ref = identity_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            identity_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int TRACK_SID_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object trackSid_ = "";

    /**
     * <pre>
     * sid of the track to mute
     * </pre>
     *
     * <code>string track_sid = 3;</code>
     *
     * @return The trackSid.
     */
    @java.lang.Override
    public java.lang.String getTrackSid() {
        java.lang.Object ref = trackSid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            trackSid_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * sid of the track to mute
     * </pre>
     *
     * <code>string track_sid = 3;</code>
     *
     * @return The bytes for trackSid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTrackSidBytes() {
        java.lang.Object ref = trackSid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            trackSid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int MUTED_FIELD_NUMBER = 4;
    private boolean muted_ = false;

    /**
     * <pre>
     * set to true to mute, false to unmute
     * </pre>
     *
     * <code>bool muted = 4;</code>
     *
     * @return The muted.
     */
    @java.lang.Override
    public boolean getMuted() {
        return muted_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(room_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, room_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, identity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(trackSid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, trackSid_);
        }
        if (muted_) {
            output.writeBool(4, muted_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(room_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, room_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(identity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, identity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(trackSid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, trackSid_);
        }
        if (muted_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(4, muted_);
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
        if (!(obj instanceof MuteRoomTrackRequest other)) {
            return super.equals(obj);
        }

        if (!getRoom().equals(other.getRoom())) {
            return false;
        }
        if (!getIdentity().equals(other.getIdentity())) {
            return false;
        }
        if (!getTrackSid().equals(other.getTrackSid())) {
            return false;
        }
        if (getMuted() != other.getMuted()) {
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
        hash = (37 * hash) + ROOM_FIELD_NUMBER;
        hash = (53 * hash) + getRoom().hashCode();
        hash = (37 * hash) + IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getIdentity().hashCode();
        hash = (37 * hash) + TRACK_SID_FIELD_NUMBER;
        hash = (53 * hash) + getTrackSid().hashCode();
        hash = (37 * hash) + MUTED_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getMuted());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest prototype) {
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
     * Protobuf type {@code livekit.MuteRoomTrackRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.MuteRoomTrackRequest)
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest.class,
                            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            room_ = "";
            identity_ = "";
            trackSid_ = "";
            muted_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_MuteRoomTrackRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest build() {
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest result =
                    new im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.room_ = room_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.identity_ = identity_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.trackSid_ = trackSid_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.muted_ = muted_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getRoom()
                    .isEmpty()) {
                room_ = other.room_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getIdentity()
                    .isEmpty()) {
                identity_ = other.identity_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getTrackSid()
                    .isEmpty()) {
                trackSid_ = other.trackSid_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.getMuted()) {
                setMuted(other.getMuted());
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
                            room_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            identity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            trackSid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 32 -> {
                            muted_ = input.readBool();
                            bitField0_ |= 0x00000008;
                        } // case 32
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

        private java.lang.Object room_ = "";

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string room = 1;</code>
         *
         * @return The room.
         */
        public java.lang.String getRoom() {
            java.lang.Object ref = room_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                room_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string room = 1;</code>
         *
         * @return The bytes for room.
         */
        public com.google.protobuf.ByteString getRoomBytes() {
            java.lang.Object ref = room_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                room_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string room = 1;</code>
         *
         * @param value The room to set.
         * @return This builder for chaining.
         */
        public Builder setRoom(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            room_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string room = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoom() {
            room_ = getDefaultInstance().getRoom();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string room = 1;</code>
         *
         * @param value The bytes for room to set.
         * @return This builder for chaining.
         */
        public Builder setRoomBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            room_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object identity_ = "";

        /**
         * <code>string identity = 2;</code>
         *
         * @return The identity.
         */
        public java.lang.String getIdentity() {
            java.lang.Object ref = identity_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                identity_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @return The bytes for identity.
         */
        public com.google.protobuf.ByteString getIdentityBytes() {
            java.lang.Object ref = identity_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                identity_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @param value The identity to set.
         * @return This builder for chaining.
         */
        public Builder setIdentity(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            identity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdentity() {
            identity_ = getDefaultInstance().getIdentity();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string identity = 2;</code>
         *
         * @param value The bytes for identity to set.
         * @return This builder for chaining.
         */
        public Builder setIdentityBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            identity_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private java.lang.Object trackSid_ = "";

        /**
         * <pre>
         * sid of the track to mute
         * </pre>
         *
         * <code>string track_sid = 3;</code>
         *
         * @return The trackSid.
         */
        public java.lang.String getTrackSid() {
            java.lang.Object ref = trackSid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                trackSid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * sid of the track to mute
         * </pre>
         *
         * <code>string track_sid = 3;</code>
         *
         * @return The bytes for trackSid.
         */
        public com.google.protobuf.ByteString getTrackSidBytes() {
            java.lang.Object ref = trackSid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                trackSid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * sid of the track to mute
         * </pre>
         *
         * <code>string track_sid = 3;</code>
         *
         * @param value The trackSid to set.
         * @return This builder for chaining.
         */
        public Builder setTrackSid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            trackSid_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sid of the track to mute
         * </pre>
         *
         * <code>string track_sid = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTrackSid() {
            trackSid_ = getDefaultInstance().getTrackSid();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * sid of the track to mute
         * </pre>
         *
         * <code>string track_sid = 3;</code>
         *
         * @param value The bytes for trackSid to set.
         * @return This builder for chaining.
         */
        public Builder setTrackSidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            trackSid_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private boolean muted_;

        /**
         * <pre>
         * set to true to mute, false to unmute
         * </pre>
         *
         * <code>bool muted = 4;</code>
         *
         * @return The muted.
         */
        @java.lang.Override
        public boolean getMuted() {
            return muted_;
        }

        /**
         * <pre>
         * set to true to mute, false to unmute
         * </pre>
         *
         * <code>bool muted = 4;</code>
         *
         * @param value The muted to set.
         * @return This builder for chaining.
         */
        public Builder setMuted(boolean value) {

            muted_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set to true to mute, false to unmute
         * </pre>
         *
         * <code>bool muted = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuted() {
            bitField0_ &= ~0x00000008;
            muted_ = false;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.MuteRoomTrackRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.MuteRoomTrackRequest)
    private static final im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest();
    }

    public static im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MuteRoomTrackRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public MuteRoomTrackRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<MuteRoomTrackRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MuteRoomTrackRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}