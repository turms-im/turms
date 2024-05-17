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

package im.turms.plugin.livekit.core.proto.webhook;

/**
 * Protobuf type {@code livekit.WebhookEvent}
 */
public final class WebhookEvent extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.WebhookEvent)
        WebhookEventOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                WebhookEvent.class.getName());
    }

    // Use WebhookEvent.newBuilder() to construct.
    private WebhookEvent(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private WebhookEvent() {
        event_ = "";
        id_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.webhook.LivekitWebhook.internal_static_livekit_WebhookEvent_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.webhook.LivekitWebhook.internal_static_livekit_WebhookEvent_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.webhook.WebhookEvent.class,
                        im.turms.plugin.livekit.core.proto.webhook.WebhookEvent.Builder.class);
    }

    private int bitField0_;
    public static final int EVENT_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object event_ = "";

    /**
     * <pre>
     * one of room_started, room_finished, participant_joined, participant_left,
     * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
     * ingress_started, ingress_ended
     * </pre>
     *
     * <code>string event = 1;</code>
     *
     * @return The event.
     */
    @java.lang.Override
    public java.lang.String getEvent() {
        java.lang.Object ref = event_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            event_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * one of room_started, room_finished, participant_joined, participant_left,
     * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
     * ingress_started, ingress_ended
     * </pre>
     *
     * <code>string event = 1;</code>
     *
     * @return The bytes for event.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEventBytes() {
        java.lang.Object ref = event_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            event_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ROOM_FIELD_NUMBER = 2;
    private im.turms.plugin.livekit.core.proto.models.Room room_;

    /**
     * <code>.livekit.Room room = 2;</code>
     *
     * @return Whether the room field is set.
     */
    @java.lang.Override
    public boolean hasRoom() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.Room room = 2;</code>
     *
     * @return The room.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Room getRoom() {
        return room_ == null
                ? im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance()
                : room_;
    }

    /**
     * <code>.livekit.Room room = 2;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.RoomOrBuilder getRoomOrBuilder() {
        return room_ == null
                ? im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance()
                : room_;
    }

    public static final int PARTICIPANT_FIELD_NUMBER = 3;
    private im.turms.plugin.livekit.core.proto.models.ParticipantInfo participant_;

    /**
     * <pre>
     * set when event is participant_* or track_*
     * </pre>
     *
     * <code>.livekit.ParticipantInfo participant = 3;</code>
     *
     * @return Whether the participant field is set.
     */
    @java.lang.Override
    public boolean hasParticipant() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <pre>
     * set when event is participant_* or track_*
     * </pre>
     *
     * <code>.livekit.ParticipantInfo participant = 3;</code>
     *
     * @return The participant.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfo getParticipant() {
        return participant_ == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDefaultInstance()
                : participant_;
    }

    /**
     * <pre>
     * set when event is participant_* or track_*
     * </pre>
     *
     * <code>.livekit.ParticipantInfo participant = 3;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder getParticipantOrBuilder() {
        return participant_ == null
                ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo.getDefaultInstance()
                : participant_;
    }

    public static final int EGRESS_INFO_FIELD_NUMBER = 9;
    private im.turms.plugin.livekit.core.proto.egress.EgressInfo egressInfo_;

    /**
     * <pre>
     * set when event is egress_*
     * </pre>
     *
     * <code>.livekit.EgressInfo egress_info = 9;</code>
     *
     * @return Whether the egressInfo field is set.
     */
    @java.lang.Override
    public boolean hasEgressInfo() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <pre>
     * set when event is egress_*
     * </pre>
     *
     * <code>.livekit.EgressInfo egress_info = 9;</code>
     *
     * @return The egressInfo.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EgressInfo getEgressInfo() {
        return egressInfo_ == null
                ? im.turms.plugin.livekit.core.proto.egress.EgressInfo.getDefaultInstance()
                : egressInfo_;
    }

    /**
     * <pre>
     * set when event is egress_*
     * </pre>
     *
     * <code>.livekit.EgressInfo egress_info = 9;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.EgressInfoOrBuilder getEgressInfoOrBuilder() {
        return egressInfo_ == null
                ? im.turms.plugin.livekit.core.proto.egress.EgressInfo.getDefaultInstance()
                : egressInfo_;
    }

    public static final int INGRESS_INFO_FIELD_NUMBER = 10;
    private im.turms.plugin.livekit.core.proto.ingress.IngressInfo ingressInfo_;

    /**
     * <pre>
     * set when event is ingress_*
     * </pre>
     *
     * <code>.livekit.IngressInfo ingress_info = 10;</code>
     *
     * @return Whether the ingressInfo field is set.
     */
    @java.lang.Override
    public boolean hasIngressInfo() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <pre>
     * set when event is ingress_*
     * </pre>
     *
     * <code>.livekit.IngressInfo ingress_info = 10;</code>
     *
     * @return The ingressInfo.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressInfo getIngressInfo() {
        return ingressInfo_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressInfo.getDefaultInstance()
                : ingressInfo_;
    }

    /**
     * <pre>
     * set when event is ingress_*
     * </pre>
     *
     * <code>.livekit.IngressInfo ingress_info = 10;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder getIngressInfoOrBuilder() {
        return ingressInfo_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressInfo.getDefaultInstance()
                : ingressInfo_;
    }

    public static final int TRACK_FIELD_NUMBER = 8;
    private im.turms.plugin.livekit.core.proto.models.TrackInfo track_;

    /**
     * <pre>
     * set when event is track_*
     * </pre>
     *
     * <code>.livekit.TrackInfo track = 8;</code>
     *
     * @return Whether the track field is set.
     */
    @java.lang.Override
    public boolean hasTrack() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <pre>
     * set when event is track_*
     * </pre>
     *
     * <code>.livekit.TrackInfo track = 8;</code>
     *
     * @return The track.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfo getTrack() {
        return track_ == null
                ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                : track_;
    }

    /**
     * <pre>
     * set when event is track_*
     * </pre>
     *
     * <code>.livekit.TrackInfo track = 8;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTrackOrBuilder() {
        return track_ == null
                ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                : track_;
    }

    public static final int ID_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object id_ = "";

    /**
     * <pre>
     * unique event uuid
     * </pre>
     *
     * <code>string id = 6;</code>
     *
     * @return The id.
     */
    @java.lang.Override
    public java.lang.String getId() {
        java.lang.Object ref = id_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            id_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * unique event uuid
     * </pre>
     *
     * <code>string id = 6;</code>
     *
     * @return The bytes for id.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIdBytes() {
        java.lang.Object ref = id_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            id_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int CREATED_AT_FIELD_NUMBER = 7;
    private long createdAt_ = 0L;

    /**
     * <pre>
     * timestamp in seconds
     * </pre>
     *
     * <code>int64 created_at = 7;</code>
     *
     * @return The createdAt.
     */
    @java.lang.Override
    public long getCreatedAt() {
        return createdAt_;
    }

    public static final int NUM_DROPPED_FIELD_NUMBER = 11;
    private int numDropped_ = 0;

    /**
     * <code>int32 num_dropped = 11;</code>
     *
     * @return The numDropped.
     */
    @java.lang.Override
    public int getNumDropped() {
        return numDropped_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(event_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, event_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(2, getRoom());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(3, getParticipant());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(id_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, id_);
        }
        if (createdAt_ != 0L) {
            output.writeInt64(7, createdAt_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeMessage(8, getTrack());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(9, getEgressInfo());
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeMessage(10, getIngressInfo());
        }
        if (numDropped_ != 0) {
            output.writeInt32(11, numDropped_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(event_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, event_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, getRoom());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, getParticipant());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(id_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, id_);
        }
        if (createdAt_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(7, createdAt_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(8, getTrack());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(9, getEgressInfo());
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(10, getIngressInfo());
        }
        if (numDropped_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(11, numDropped_);
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
        if (!(obj instanceof WebhookEvent other)) {
            return super.equals(obj);
        }

        if (!getEvent().equals(other.getEvent())) {
            return false;
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
        if (hasEgressInfo() != other.hasEgressInfo()) {
            return false;
        }
        if (hasEgressInfo()) {
            if (!getEgressInfo().equals(other.getEgressInfo())) {
                return false;
            }
        }
        if (hasIngressInfo() != other.hasIngressInfo()) {
            return false;
        }
        if (hasIngressInfo()) {
            if (!getIngressInfo().equals(other.getIngressInfo())) {
                return false;
            }
        }
        if (hasTrack() != other.hasTrack()) {
            return false;
        }
        if (hasTrack()) {
            if (!getTrack().equals(other.getTrack())) {
                return false;
            }
        }
        if (!getId().equals(other.getId())) {
            return false;
        }
        if (getCreatedAt() != other.getCreatedAt()) {
            return false;
        }
        if (getNumDropped() != other.getNumDropped()) {
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
        hash = (37 * hash) + EVENT_FIELD_NUMBER;
        hash = (53 * hash) + getEvent().hashCode();
        if (hasRoom()) {
            hash = (37 * hash) + ROOM_FIELD_NUMBER;
            hash = (53 * hash) + getRoom().hashCode();
        }
        if (hasParticipant()) {
            hash = (37 * hash) + PARTICIPANT_FIELD_NUMBER;
            hash = (53 * hash) + getParticipant().hashCode();
        }
        if (hasEgressInfo()) {
            hash = (37 * hash) + EGRESS_INFO_FIELD_NUMBER;
            hash = (53 * hash) + getEgressInfo().hashCode();
        }
        if (hasIngressInfo()) {
            hash = (37 * hash) + INGRESS_INFO_FIELD_NUMBER;
            hash = (53 * hash) + getIngressInfo().hashCode();
        }
        if (hasTrack()) {
            hash = (37 * hash) + TRACK_FIELD_NUMBER;
            hash = (53 * hash) + getTrack().hashCode();
        }
        hash = (37 * hash) + ID_FIELD_NUMBER;
        hash = (53 * hash) + getId().hashCode();
        hash = (37 * hash) + CREATED_AT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCreatedAt());
        hash = (37 * hash) + NUM_DROPPED_FIELD_NUMBER;
        hash = (53 * hash) + getNumDropped();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent parseFrom(
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
            im.turms.plugin.livekit.core.proto.webhook.WebhookEvent prototype) {
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
     * Protobuf type {@code livekit.WebhookEvent}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.WebhookEvent)
            im.turms.plugin.livekit.core.proto.webhook.WebhookEventOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.webhook.LivekitWebhook.internal_static_livekit_WebhookEvent_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.webhook.LivekitWebhook.internal_static_livekit_WebhookEvent_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.webhook.WebhookEvent.class,
                            im.turms.plugin.livekit.core.proto.webhook.WebhookEvent.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.webhook.WebhookEvent.newBuilder()
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
                getEgressInfoFieldBuilder();
                getIngressInfoFieldBuilder();
                getTrackFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            event_ = "";
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
            egressInfo_ = null;
            if (egressInfoBuilder_ != null) {
                egressInfoBuilder_.dispose();
                egressInfoBuilder_ = null;
            }
            ingressInfo_ = null;
            if (ingressInfoBuilder_ != null) {
                ingressInfoBuilder_.dispose();
                ingressInfoBuilder_ = null;
            }
            track_ = null;
            if (trackBuilder_ != null) {
                trackBuilder_.dispose();
                trackBuilder_ = null;
            }
            id_ = "";
            createdAt_ = 0L;
            numDropped_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.webhook.LivekitWebhook.internal_static_livekit_WebhookEvent_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.webhook.WebhookEvent getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.webhook.WebhookEvent.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.webhook.WebhookEvent build() {
            im.turms.plugin.livekit.core.proto.webhook.WebhookEvent result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.webhook.WebhookEvent buildPartial() {
            im.turms.plugin.livekit.core.proto.webhook.WebhookEvent result =
                    new im.turms.plugin.livekit.core.proto.webhook.WebhookEvent(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.webhook.WebhookEvent result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.event_ = event_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.room_ = roomBuilder_ == null
                        ? room_
                        : roomBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.participant_ = participantBuilder_ == null
                        ? participant_
                        : participantBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.egressInfo_ = egressInfoBuilder_ == null
                        ? egressInfo_
                        : egressInfoBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.ingressInfo_ = ingressInfoBuilder_ == null
                        ? ingressInfo_
                        : ingressInfoBuilder_.build();
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.track_ = trackBuilder_ == null
                        ? track_
                        : trackBuilder_.build();
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.id_ = id_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.createdAt_ = createdAt_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.numDropped_ = numDropped_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.webhook.WebhookEvent) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.webhook.WebhookEvent) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.webhook.WebhookEvent other) {
            if (other == im.turms.plugin.livekit.core.proto.webhook.WebhookEvent
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getEvent()
                    .isEmpty()) {
                event_ = other.event_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.hasRoom()) {
                mergeRoom(other.getRoom());
            }
            if (other.hasParticipant()) {
                mergeParticipant(other.getParticipant());
            }
            if (other.hasEgressInfo()) {
                mergeEgressInfo(other.getEgressInfo());
            }
            if (other.hasIngressInfo()) {
                mergeIngressInfo(other.getIngressInfo());
            }
            if (other.hasTrack()) {
                mergeTrack(other.getTrack());
            }
            if (!other.getId()
                    .isEmpty()) {
                id_ = other.id_;
                bitField0_ |= 0x00000040;
                onChanged();
            }
            if (other.getCreatedAt() != 0L) {
                setCreatedAt(other.getCreatedAt());
            }
            if (other.getNumDropped() != 0) {
                setNumDropped(other.getNumDropped());
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
                            event_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            input.readMessage(getRoomFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            input.readMessage(getParticipantFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 50 -> {
                            id_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000040;
                        } // case 50
                        case 56 -> {
                            createdAt_ = input.readInt64();
                            bitField0_ |= 0x00000080;
                        } // case 56
                        case 66 -> {
                            input.readMessage(getTrackFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000020;
                        } // case 66
                        case 74 -> {
                            input.readMessage(getEgressInfoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000008;
                        } // case 74
                        case 82 -> {
                            input.readMessage(getIngressInfoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000010;
                        } // case 82
                        case 88 -> {
                            numDropped_ = input.readInt32();
                            bitField0_ |= 0x00000100;
                        } // case 88
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

        private java.lang.Object event_ = "";

        /**
         * <pre>
         * one of room_started, room_finished, participant_joined, participant_left,
         * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
         * ingress_started, ingress_ended
         * </pre>
         *
         * <code>string event = 1;</code>
         *
         * @return The event.
         */
        public java.lang.String getEvent() {
            java.lang.Object ref = event_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                event_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * one of room_started, room_finished, participant_joined, participant_left,
         * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
         * ingress_started, ingress_ended
         * </pre>
         *
         * <code>string event = 1;</code>
         *
         * @return The bytes for event.
         */
        public com.google.protobuf.ByteString getEventBytes() {
            java.lang.Object ref = event_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                event_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * one of room_started, room_finished, participant_joined, participant_left,
         * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
         * ingress_started, ingress_ended
         * </pre>
         *
         * <code>string event = 1;</code>
         *
         * @param value The event to set.
         * @return This builder for chaining.
         */
        public Builder setEvent(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            event_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * one of room_started, room_finished, participant_joined, participant_left,
         * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
         * ingress_started, ingress_ended
         * </pre>
         *
         * <code>string event = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEvent() {
            event_ = getDefaultInstance().getEvent();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * one of room_started, room_finished, participant_joined, participant_left,
         * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
         * ingress_started, ingress_ended
         * </pre>
         *
         * <code>string event = 1;</code>
         *
         * @param value The bytes for event to set.
         * @return This builder for chaining.
         */
        public Builder setEventBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            event_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.models.Room room_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.Room, im.turms.plugin.livekit.core.proto.models.Room.Builder, im.turms.plugin.livekit.core.proto.models.RoomOrBuilder> roomBuilder_;

        /**
         * <code>.livekit.Room room = 2;</code>
         *
         * @return Whether the room field is set.
         */
        public boolean hasRoom() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         *
         * @return The room.
         */
        public im.turms.plugin.livekit.core.proto.models.Room getRoom() {
            if (roomBuilder_ == null) {
                return room_ == null
                        ? im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance()
                        : room_;
            } else {
                return roomBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        public Builder setRoom(im.turms.plugin.livekit.core.proto.models.Room value) {
            if (roomBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                room_ = value;
            } else {
                roomBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        public Builder setRoom(
                im.turms.plugin.livekit.core.proto.models.Room.Builder builderForValue) {
            if (roomBuilder_ == null) {
                room_ = builderForValue.build();
            } else {
                roomBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        public Builder mergeRoom(im.turms.plugin.livekit.core.proto.models.Room value) {
            if (roomBuilder_ == null) {
                if (((bitField0_ & 0x00000002) != 0)
                        && room_ != null
                        && room_ != im.turms.plugin.livekit.core.proto.models.Room
                                .getDefaultInstance()) {
                    getRoomBuilder().mergeFrom(value);
                } else {
                    room_ = value;
                }
            } else {
                roomBuilder_.mergeFrom(value);
            }
            if (room_ != null) {
                bitField0_ |= 0x00000002;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        public Builder clearRoom() {
            bitField0_ &= ~0x00000002;
            room_ = null;
            if (roomBuilder_ != null) {
                roomBuilder_.dispose();
                roomBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Room.Builder getRoomBuilder() {
            bitField0_ |= 0x00000002;
            onChanged();
            return getRoomFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.RoomOrBuilder getRoomOrBuilder() {
            if (roomBuilder_ != null) {
                return roomBuilder_.getMessageOrBuilder();
            } else {
                return room_ == null
                        ? im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance()
                        : room_;
            }
        }

        /**
         * <code>.livekit.Room room = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.Room, im.turms.plugin.livekit.core.proto.models.Room.Builder, im.turms.plugin.livekit.core.proto.models.RoomOrBuilder> getRoomFieldBuilder() {
            if (roomBuilder_ == null) {
                roomBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getRoom(),
                        getParentForChildren(),
                        isClean());
                room_ = null;
            }
            return roomBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.models.ParticipantInfo participant_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantInfo, im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder> participantBuilder_;

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         *
         * @return Whether the participant field is set.
         */
        public boolean hasParticipant() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         *
         * @return The participant.
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo getParticipant() {
            if (participantBuilder_ == null) {
                return participant_ == null
                        ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo
                                .getDefaultInstance()
                        : participant_;
            } else {
                return participantBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        public Builder setParticipant(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo value) {
            if (participantBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                participant_ = value;
            } else {
                participantBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        public Builder setParticipant(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder builderForValue) {
            if (participantBuilder_ == null) {
                participant_ = builderForValue.build();
            } else {
                participantBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        public Builder mergeParticipant(
                im.turms.plugin.livekit.core.proto.models.ParticipantInfo value) {
            if (participantBuilder_ == null) {
                if (((bitField0_ & 0x00000004) != 0)
                        && participant_ != null
                        && participant_ != im.turms.plugin.livekit.core.proto.models.ParticipantInfo
                                .getDefaultInstance()) {
                    getParticipantBuilder().mergeFrom(value);
                } else {
                    participant_ = value;
                }
            } else {
                participantBuilder_.mergeFrom(value);
            }
            if (participant_ != null) {
                bitField0_ |= 0x00000004;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        public Builder clearParticipant() {
            bitField0_ &= ~0x00000004;
            participant_ = null;
            if (participantBuilder_ != null) {
                participantBuilder_.dispose();
                participantBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder getParticipantBuilder() {
            bitField0_ |= 0x00000004;
            onChanged();
            return getParticipantFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder getParticipantOrBuilder() {
            if (participantBuilder_ != null) {
                return participantBuilder_.getMessageOrBuilder();
            } else {
                return participant_ == null
                        ? im.turms.plugin.livekit.core.proto.models.ParticipantInfo
                                .getDefaultInstance()
                        : participant_;
            }
        }

        /**
         * <pre>
         * set when event is participant_* or track_*
         * </pre>
         *
         * <code>.livekit.ParticipantInfo participant = 3;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.ParticipantInfo, im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Builder, im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder> getParticipantFieldBuilder() {
            if (participantBuilder_ == null) {
                participantBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getParticipant(),
                        getParentForChildren(),
                        isClean());
                participant_ = null;
            }
            return participantBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.egress.EgressInfo egressInfo_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EgressInfo, im.turms.plugin.livekit.core.proto.egress.EgressInfo.Builder, im.turms.plugin.livekit.core.proto.egress.EgressInfoOrBuilder> egressInfoBuilder_;

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         *
         * @return Whether the egressInfo field is set.
         */
        public boolean hasEgressInfo() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         *
         * @return The egressInfo.
         */
        public im.turms.plugin.livekit.core.proto.egress.EgressInfo getEgressInfo() {
            if (egressInfoBuilder_ == null) {
                return egressInfo_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.EgressInfo.getDefaultInstance()
                        : egressInfo_;
            } else {
                return egressInfoBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        public Builder setEgressInfo(im.turms.plugin.livekit.core.proto.egress.EgressInfo value) {
            if (egressInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                egressInfo_ = value;
            } else {
                egressInfoBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        public Builder setEgressInfo(
                im.turms.plugin.livekit.core.proto.egress.EgressInfo.Builder builderForValue) {
            if (egressInfoBuilder_ == null) {
                egressInfo_ = builderForValue.build();
            } else {
                egressInfoBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        public Builder mergeEgressInfo(im.turms.plugin.livekit.core.proto.egress.EgressInfo value) {
            if (egressInfoBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)
                        && egressInfo_ != null
                        && egressInfo_ != im.turms.plugin.livekit.core.proto.egress.EgressInfo
                                .getDefaultInstance()) {
                    getEgressInfoBuilder().mergeFrom(value);
                } else {
                    egressInfo_ = value;
                }
            } else {
                egressInfoBuilder_.mergeFrom(value);
            }
            if (egressInfo_ != null) {
                bitField0_ |= 0x00000008;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        public Builder clearEgressInfo() {
            bitField0_ &= ~0x00000008;
            egressInfo_ = null;
            if (egressInfoBuilder_ != null) {
                egressInfoBuilder_.dispose();
                egressInfoBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EgressInfo.Builder getEgressInfoBuilder() {
            bitField0_ |= 0x00000008;
            onChanged();
            return getEgressInfoFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        public im.turms.plugin.livekit.core.proto.egress.EgressInfoOrBuilder getEgressInfoOrBuilder() {
            if (egressInfoBuilder_ != null) {
                return egressInfoBuilder_.getMessageOrBuilder();
            } else {
                return egressInfo_ == null
                        ? im.turms.plugin.livekit.core.proto.egress.EgressInfo.getDefaultInstance()
                        : egressInfo_;
            }
        }

        /**
         * <pre>
         * set when event is egress_*
         * </pre>
         *
         * <code>.livekit.EgressInfo egress_info = 9;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.egress.EgressInfo, im.turms.plugin.livekit.core.proto.egress.EgressInfo.Builder, im.turms.plugin.livekit.core.proto.egress.EgressInfoOrBuilder> getEgressInfoFieldBuilder() {
            if (egressInfoBuilder_ == null) {
                egressInfoBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getEgressInfo(),
                        getParentForChildren(),
                        isClean());
                egressInfo_ = null;
            }
            return egressInfoBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.ingress.IngressInfo ingressInfo_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressInfo, im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder> ingressInfoBuilder_;

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         *
         * @return Whether the ingressInfo field is set.
         */
        public boolean hasIngressInfo() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         *
         * @return The ingressInfo.
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo getIngressInfo() {
            if (ingressInfoBuilder_ == null) {
                return ingressInfo_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressInfo
                                .getDefaultInstance()
                        : ingressInfo_;
            } else {
                return ingressInfoBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        public Builder setIngressInfo(
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo value) {
            if (ingressInfoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ingressInfo_ = value;
            } else {
                ingressInfoBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        public Builder setIngressInfo(
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder builderForValue) {
            if (ingressInfoBuilder_ == null) {
                ingressInfo_ = builderForValue.build();
            } else {
                ingressInfoBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        public Builder mergeIngressInfo(
                im.turms.plugin.livekit.core.proto.ingress.IngressInfo value) {
            if (ingressInfoBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)
                        && ingressInfo_ != null
                        && ingressInfo_ != im.turms.plugin.livekit.core.proto.ingress.IngressInfo
                                .getDefaultInstance()) {
                    getIngressInfoBuilder().mergeFrom(value);
                } else {
                    ingressInfo_ = value;
                }
            } else {
                ingressInfoBuilder_.mergeFrom(value);
            }
            if (ingressInfo_ != null) {
                bitField0_ |= 0x00000010;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        public Builder clearIngressInfo() {
            bitField0_ &= ~0x00000010;
            ingressInfo_ = null;
            if (ingressInfoBuilder_ != null) {
                ingressInfoBuilder_.dispose();
                ingressInfoBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder getIngressInfoBuilder() {
            bitField0_ |= 0x00000010;
            onChanged();
            return getIngressInfoFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder getIngressInfoOrBuilder() {
            if (ingressInfoBuilder_ != null) {
                return ingressInfoBuilder_.getMessageOrBuilder();
            } else {
                return ingressInfo_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressInfo
                                .getDefaultInstance()
                        : ingressInfo_;
            }
        }

        /**
         * <pre>
         * set when event is ingress_*
         * </pre>
         *
         * <code>.livekit.IngressInfo ingress_info = 10;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressInfo, im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder> getIngressInfoFieldBuilder() {
            if (ingressInfoBuilder_ == null) {
                ingressInfoBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getIngressInfo(),
                        getParentForChildren(),
                        isClean());
                ingressInfo_ = null;
            }
            return ingressInfoBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.models.TrackInfo track_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> trackBuilder_;

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         *
         * @return Whether the track field is set.
         */
        public boolean hasTrack() {
            return ((bitField0_ & 0x00000020) != 0);
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         *
         * @return The track.
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo getTrack() {
            if (trackBuilder_ == null) {
                return track_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                        : track_;
            } else {
                return trackBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        public Builder setTrack(im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (trackBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                track_ = value;
            } else {
                trackBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        public Builder setTrack(
                im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder builderForValue) {
            if (trackBuilder_ == null) {
                track_ = builderForValue.build();
            } else {
                trackBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        public Builder mergeTrack(im.turms.plugin.livekit.core.proto.models.TrackInfo value) {
            if (trackBuilder_ == null) {
                if (((bitField0_ & 0x00000020) != 0)
                        && track_ != null
                        && track_ != im.turms.plugin.livekit.core.proto.models.TrackInfo
                                .getDefaultInstance()) {
                    getTrackBuilder().mergeFrom(value);
                } else {
                    track_ = value;
                }
            } else {
                trackBuilder_.mergeFrom(value);
            }
            if (track_ != null) {
                bitField0_ |= 0x00000020;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        public Builder clearTrack() {
            bitField0_ &= ~0x00000020;
            track_ = null;
            if (trackBuilder_ != null) {
                trackBuilder_.dispose();
                trackBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder getTrackBuilder() {
            bitField0_ |= 0x00000020;
            onChanged();
            return getTrackFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTrackOrBuilder() {
            if (trackBuilder_ != null) {
                return trackBuilder_.getMessageOrBuilder();
            } else {
                return track_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TrackInfo.getDefaultInstance()
                        : track_;
            }
        }

        /**
         * <pre>
         * set when event is track_*
         * </pre>
         *
         * <code>.livekit.TrackInfo track = 8;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TrackInfo, im.turms.plugin.livekit.core.proto.models.TrackInfo.Builder, im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTrackFieldBuilder() {
            if (trackBuilder_ == null) {
                trackBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getTrack(),
                        getParentForChildren(),
                        isClean());
                track_ = null;
            }
            return trackBuilder_;
        }

        private java.lang.Object id_ = "";

        /**
         * <pre>
         * unique event uuid
         * </pre>
         *
         * <code>string id = 6;</code>
         *
         * @return The id.
         */
        public java.lang.String getId() {
            java.lang.Object ref = id_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                id_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * unique event uuid
         * </pre>
         *
         * <code>string id = 6;</code>
         *
         * @return The bytes for id.
         */
        public com.google.protobuf.ByteString getIdBytes() {
            java.lang.Object ref = id_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                id_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * unique event uuid
         * </pre>
         *
         * <code>string id = 6;</code>
         *
         * @param value The id to set.
         * @return This builder for chaining.
         */
        public Builder setId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            id_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * unique event uuid
         * </pre>
         *
         * <code>string id = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearId() {
            id_ = getDefaultInstance().getId();
            bitField0_ &= ~0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * unique event uuid
         * </pre>
         *
         * <code>string id = 6;</code>
         *
         * @param value The bytes for id to set.
         * @return This builder for chaining.
         */
        public Builder setIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            id_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        private long createdAt_;

        /**
         * <pre>
         * timestamp in seconds
         * </pre>
         *
         * <code>int64 created_at = 7;</code>
         *
         * @return The createdAt.
         */
        @java.lang.Override
        public long getCreatedAt() {
            return createdAt_;
        }

        /**
         * <pre>
         * timestamp in seconds
         * </pre>
         *
         * <code>int64 created_at = 7;</code>
         *
         * @param value The createdAt to set.
         * @return This builder for chaining.
         */
        public Builder setCreatedAt(long value) {

            createdAt_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * timestamp in seconds
         * </pre>
         *
         * <code>int64 created_at = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreatedAt() {
            bitField0_ &= ~0x00000080;
            createdAt_ = 0L;
            onChanged();
            return this;
        }

        private int numDropped_;

        /**
         * <code>int32 num_dropped = 11;</code>
         *
         * @return The numDropped.
         */
        @java.lang.Override
        public int getNumDropped() {
            return numDropped_;
        }

        /**
         * <code>int32 num_dropped = 11;</code>
         *
         * @param value The numDropped to set.
         * @return This builder for chaining.
         */
        public Builder setNumDropped(int value) {

            numDropped_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>int32 num_dropped = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNumDropped() {
            bitField0_ &= ~0x00000100;
            numDropped_ = 0;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.WebhookEvent)
    }

    // @@protoc_insertion_point(class_scope:livekit.WebhookEvent)
    private static final im.turms.plugin.livekit.core.proto.webhook.WebhookEvent DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.webhook.WebhookEvent();
    }

    public static im.turms.plugin.livekit.core.proto.webhook.WebhookEvent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<WebhookEvent> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public WebhookEvent parsePartialFrom(
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

    public static com.google.protobuf.Parser<WebhookEvent> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<WebhookEvent> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.webhook.WebhookEvent getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}