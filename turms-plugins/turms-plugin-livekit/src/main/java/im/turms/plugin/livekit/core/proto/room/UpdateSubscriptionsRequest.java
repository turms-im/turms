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
 * Protobuf type {@code livekit.UpdateSubscriptionsRequest}
 */
public final class UpdateSubscriptionsRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:livekit.UpdateSubscriptionsRequest)
        UpdateSubscriptionsRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                UpdateSubscriptionsRequest.class.getName());
    }

    // Use UpdateSubscriptionsRequest.newBuilder() to construct.
    private UpdateSubscriptionsRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateSubscriptionsRequest() {
        room_ = "";
        identity_ = "";
        trackSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
        participantTracks_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateSubscriptionsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateSubscriptionsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest.class,
                        im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest.Builder.class);
    }

    public static final int ROOM_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object room_ = "";

    /**
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

    public static final int TRACK_SIDS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList trackSids_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <pre>
     * list of sids of tracks
     * </pre>
     *
     * <code>repeated string track_sids = 3;</code>
     *
     * @return A list containing the trackSids.
     */
    public com.google.protobuf.ProtocolStringList getTrackSidsList() {
        return trackSids_;
    }

    /**
     * <pre>
     * list of sids of tracks
     * </pre>
     *
     * <code>repeated string track_sids = 3;</code>
     *
     * @return The count of trackSids.
     */
    public int getTrackSidsCount() {
        return trackSids_.size();
    }

    /**
     * <pre>
     * list of sids of tracks
     * </pre>
     *
     * <code>repeated string track_sids = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The trackSids at the given index.
     */
    public java.lang.String getTrackSids(int index) {
        return trackSids_.get(index);
    }

    /**
     * <pre>
     * list of sids of tracks
     * </pre>
     *
     * <code>repeated string track_sids = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the trackSids at the given index.
     */
    public com.google.protobuf.ByteString getTrackSidsBytes(int index) {
        return trackSids_.getByteString(index);
    }

    public static final int SUBSCRIBE_FIELD_NUMBER = 4;
    private boolean subscribe_ = false;

    /**
     * <pre>
     * set to true to subscribe, false to unsubscribe from tracks
     * </pre>
     *
     * <code>bool subscribe = 4;</code>
     *
     * @return The subscribe.
     */
    @java.lang.Override
    public boolean getSubscribe() {
        return subscribe_;
    }

    public static final int PARTICIPANT_TRACKS_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantTracks> participantTracks_;

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantTracks> getParticipantTracksList() {
        return participantTracks_;
    }

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder> getParticipantTracksOrBuilderList() {
        return participantTracks_;
    }

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    @java.lang.Override
    public int getParticipantTracksCount() {
        return participantTracks_.size();
    }

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantTracks getParticipantTracks(
            int index) {
        return participantTracks_.get(index);
    }

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder getParticipantTracksOrBuilder(
            int index) {
        return participantTracks_.get(index);
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
        for (int i = 0; i < trackSids_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, trackSids_.getRaw(i));
        }
        if (subscribe_) {
            output.writeBool(4, subscribe_);
        }
        for (im.turms.plugin.livekit.core.proto.models.ParticipantTracks participantTracks : participantTracks_) {
            output.writeMessage(5, participantTracks);
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
        {
            int dataSize = 0;
            for (int i = 0; i < trackSids_.size(); i++) {
                dataSize += computeStringSizeNoTag(trackSids_.getRaw(i));
            }
            size += dataSize;
            size += getTrackSidsList().size();
        }
        if (subscribe_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(4, subscribe_);
        }
        for (im.turms.plugin.livekit.core.proto.models.ParticipantTracks participantTracks : participantTracks_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(5, participantTracks);
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
        if (!(obj instanceof UpdateSubscriptionsRequest other)) {
            return super.equals(obj);
        }

        if (!getRoom().equals(other.getRoom())) {
            return false;
        }
        if (!getIdentity().equals(other.getIdentity())) {
            return false;
        }
        if (!getTrackSidsList().equals(other.getTrackSidsList())) {
            return false;
        }
        if (getSubscribe() != other.getSubscribe()) {
            return false;
        }
        if (!getParticipantTracksList().equals(other.getParticipantTracksList())) {
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
        if (getTrackSidsCount() > 0) {
            hash = (37 * hash) + TRACK_SIDS_FIELD_NUMBER;
            hash = (53 * hash) + getTrackSidsList().hashCode();
        }
        hash = (37 * hash) + SUBSCRIBE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getSubscribe());
        if (getParticipantTracksCount() > 0) {
            hash = (37 * hash) + PARTICIPANT_TRACKS_FIELD_NUMBER;
            hash = (53 * hash) + getParticipantTracksList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest prototype) {
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
     * Protobuf type {@code livekit.UpdateSubscriptionsRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.UpdateSubscriptionsRequest)
            im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateSubscriptionsRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateSubscriptionsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest.class,
                            im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest.newBuilder()
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
            trackSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
            subscribe_ = false;
            if (participantTracksBuilder_ == null) {
                participantTracks_ = java.util.Collections.emptyList();
            } else {
                participantTracks_ = null;
                participantTracksBuilder_.clear();
            }
            bitField0_ &= ~0x00000010;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_UpdateSubscriptionsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest build() {
            im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest result =
                    new im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest result) {
            if (participantTracksBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)) {
                    participantTracks_ = java.util.Collections.unmodifiableList(participantTracks_);
                    bitField0_ &= ~0x00000010;
                }
                result.participantTracks_ = participantTracks_;
            } else {
                result.participantTracks_ = participantTracksBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.room_ = room_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.identity_ = identity_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                trackSids_.makeImmutable();
                result.trackSids_ = trackSids_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.subscribe_ = subscribe_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest
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
            if (!other.trackSids_.isEmpty()) {
                if (trackSids_.isEmpty()) {
                    trackSids_ = other.trackSids_;
                    bitField0_ |= 0x00000004;
                } else {
                    ensureTrackSidsIsMutable();
                    trackSids_.addAll(other.trackSids_);
                }
                onChanged();
            }
            if (other.getSubscribe()) {
                setSubscribe(other.getSubscribe());
            }
            if (participantTracksBuilder_ == null) {
                if (!other.participantTracks_.isEmpty()) {
                    if (participantTracks_.isEmpty()) {
                        participantTracks_ = other.participantTracks_;
                        bitField0_ &= ~0x00000010;
                    } else {
                        ensureParticipantTracksIsMutable();
                        participantTracks_.addAll(other.participantTracks_);
                    }
                    onChanged();
                }
            } else {
                if (!other.participantTracks_.isEmpty()) {
                    if (participantTracksBuilder_.isEmpty()) {
                        participantTracksBuilder_.dispose();
                        participantTracksBuilder_ = null;
                        participantTracks_ = other.participantTracks_;
                        bitField0_ &= ~0x00000010;
                        participantTracksBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getParticipantTracksFieldBuilder()
                                        : null;
                    } else {
                        participantTracksBuilder_.addAllMessages(other.participantTracks_);
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
                            room_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            identity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            String s = input.readStringRequireUtf8();
                            ensureTrackSidsIsMutable();
                            trackSids_.add(s);
                        } // case 26
                        case 32 -> {
                            subscribe_ = input.readBool();
                            bitField0_ |= 0x00000008;
                        } // case 32
                        case 42 -> {
                            im.turms.plugin.livekit.core.proto.models.ParticipantTracks m =
                                    input.readMessage(
                                            im.turms.plugin.livekit.core.proto.models.ParticipantTracks
                                                    .parser(),
                                            extensionRegistry);
                            if (participantTracksBuilder_ == null) {
                                ensureParticipantTracksIsMutable();
                                participantTracks_.add(m);
                            } else {
                                participantTracksBuilder_.addMessage(m);
                            }
                        } // case 42
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

        private com.google.protobuf.LazyStringArrayList trackSids_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureTrackSidsIsMutable() {
            if (!trackSids_.isModifiable()) {
                trackSids_ = new com.google.protobuf.LazyStringArrayList(trackSids_);
            }
            bitField0_ |= 0x00000004;
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @return A list containing the trackSids.
         */
        public com.google.protobuf.ProtocolStringList getTrackSidsList() {
            trackSids_.makeImmutable();
            return trackSids_;
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @return The count of trackSids.
         */
        public int getTrackSidsCount() {
            return trackSids_.size();
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The trackSids at the given index.
         */
        public java.lang.String getTrackSids(int index) {
            return trackSids_.get(index);
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the trackSids at the given index.
         */
        public com.google.protobuf.ByteString getTrackSidsBytes(int index) {
            return trackSids_.getByteString(index);
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The trackSids to set.
         * @return This builder for chaining.
         */
        public Builder setTrackSids(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrackSidsIsMutable();
            trackSids_.set(index, value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @param value The trackSids to add.
         * @return This builder for chaining.
         */
        public Builder addTrackSids(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureTrackSidsIsMutable();
            trackSids_.add(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @param values The trackSids to add.
         * @return This builder for chaining.
         */
        public Builder addAllTrackSids(java.lang.Iterable<java.lang.String> values) {
            ensureTrackSidsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, trackSids_);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTrackSids() {
            trackSids_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * list of sids of tracks
         * </pre>
         *
         * <code>repeated string track_sids = 3;</code>
         *
         * @param value The bytes of the trackSids to add.
         * @return This builder for chaining.
         */
        public Builder addTrackSidsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureTrackSidsIsMutable();
            trackSids_.add(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private boolean subscribe_;

        /**
         * <pre>
         * set to true to subscribe, false to unsubscribe from tracks
         * </pre>
         *
         * <code>bool subscribe = 4;</code>
         *
         * @return The subscribe.
         */
        @java.lang.Override
        public boolean getSubscribe() {
            return subscribe_;
        }

        /**
         * <pre>
         * set to true to subscribe, false to unsubscribe from tracks
         * </pre>
         *
         * <code>bool subscribe = 4;</code>
         *
         * @param value The subscribe to set.
         * @return This builder for chaining.
         */
        public Builder setSubscribe(boolean value) {

            subscribe_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set to true to subscribe, false to unsubscribe from tracks
         * </pre>
         *
         * <code>bool subscribe = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSubscribe() {
            bitField0_ &= ~0x00000008;
            subscribe_ = false;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantTracks> participantTracks_ =
                java.util.Collections.emptyList();

        private void ensureParticipantTracksIsMutable() {
            if ((bitField0_ & 0x00000010) == 0) {
                participantTracks_ = new java.util.ArrayList<>(participantTracks_);
                bitField0_ |= 0x00000010;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantTracks, im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder> participantTracksBuilder_;

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantTracks> getParticipantTracksList() {
            if (participantTracksBuilder_ == null) {
                return java.util.Collections.unmodifiableList(participantTracks_);
            } else {
                return participantTracksBuilder_.getMessageList();
            }
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public int getParticipantTracksCount() {
            if (participantTracksBuilder_ == null) {
                return participantTracks_.size();
            } else {
                return participantTracksBuilder_.getCount();
            }
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks getParticipantTracks(
                int index) {
            if (participantTracksBuilder_ == null) {
                return participantTracks_.get(index);
            } else {
                return participantTracksBuilder_.getMessage(index);
            }
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder setParticipantTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks value) {
            if (participantTracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParticipantTracksIsMutable();
                participantTracks_.set(index, value);
                onChanged();
            } else {
                participantTracksBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder setParticipantTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder builderForValue) {
            if (participantTracksBuilder_ == null) {
                ensureParticipantTracksIsMutable();
                participantTracks_.set(index, builderForValue.build());
                onChanged();
            } else {
                participantTracksBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder addParticipantTracks(
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks value) {
            if (participantTracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParticipantTracksIsMutable();
                participantTracks_.add(value);
                onChanged();
            } else {
                participantTracksBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder addParticipantTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks value) {
            if (participantTracksBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureParticipantTracksIsMutable();
                participantTracks_.add(index, value);
                onChanged();
            } else {
                participantTracksBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder addParticipantTracks(
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder builderForValue) {
            if (participantTracksBuilder_ == null) {
                ensureParticipantTracksIsMutable();
                participantTracks_.add(builderForValue.build());
                onChanged();
            } else {
                participantTracksBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder addParticipantTracks(
                int index,
                im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder builderForValue) {
            if (participantTracksBuilder_ == null) {
                ensureParticipantTracksIsMutable();
                participantTracks_.add(index, builderForValue.build());
                onChanged();
            } else {
                participantTracksBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder addAllParticipantTracks(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.ParticipantTracks> values) {
            if (participantTracksBuilder_ == null) {
                ensureParticipantTracksIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, participantTracks_);
                onChanged();
            } else {
                participantTracksBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder clearParticipantTracks() {
            if (participantTracksBuilder_ == null) {
                participantTracks_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000010;
                onChanged();
            } else {
                participantTracksBuilder_.clear();
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public Builder removeParticipantTracks(int index) {
            if (participantTracksBuilder_ == null) {
                ensureParticipantTracksIsMutable();
                participantTracks_.remove(index);
                onChanged();
            } else {
                participantTracksBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder getParticipantTracksBuilder(
                int index) {
            return getParticipantTracksFieldBuilder().getBuilder(index);
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder getParticipantTracksOrBuilder(
                int index) {
            if (participantTracksBuilder_ == null) {
                return participantTracks_.get(index);
            } else {
                return participantTracksBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder> getParticipantTracksOrBuilderList() {
            if (participantTracksBuilder_ != null) {
                return participantTracksBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(participantTracks_);
            }
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder addParticipantTracksBuilder() {
            return getParticipantTracksFieldBuilder()
                    .addBuilder(im.turms.plugin.livekit.core.proto.models.ParticipantTracks
                            .getDefaultInstance());
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder addParticipantTracksBuilder(
                int index) {
            return getParticipantTracksFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.ParticipantTracks
                            .getDefaultInstance());
        }

        /**
         * <pre>
         * list of participants and their tracks
         * </pre>
         *
         * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder> getParticipantTracksBuilderList() {
            return getParticipantTracksFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantTracks, im.turms.plugin.livekit.core.proto.models.ParticipantTracks.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder> getParticipantTracksFieldBuilder() {
            if (participantTracksBuilder_ == null) {
                participantTracksBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        participantTracks_,
                        ((bitField0_ & 0x00000010) != 0),
                        getParentForChildren(),
                        isClean());
                participantTracks_ = null;
            }
            return participantTracksBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.UpdateSubscriptionsRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.UpdateSubscriptionsRequest)
    private static final im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest();
    }

    public static im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateSubscriptionsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateSubscriptionsRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateSubscriptionsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateSubscriptionsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}