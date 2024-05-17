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
 * Protobuf type {@code livekit.CreateRoomRequest}
 */
public final class CreateRoomRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.CreateRoomRequest)
        CreateRoomRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                CreateRoomRequest.class.getName());
    }

    // Use CreateRoomRequest.newBuilder() to construct.
    private CreateRoomRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private CreateRoomRequest() {
        name_ = "";
        nodeId_ = "";
        metadata_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_CreateRoomRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_CreateRoomRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.room.CreateRoomRequest.class,
                        im.turms.plugin.livekit.core.proto.room.CreateRoomRequest.Builder.class);
    }

    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string name = 1;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string name = 1;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int EMPTY_TIMEOUT_FIELD_NUMBER = 2;
    private int emptyTimeout_ = 0;

    /**
     * <pre>
     * number of seconds to keep the room open if no one joins
     * </pre>
     *
     * <code>uint32 empty_timeout = 2;</code>
     *
     * @return The emptyTimeout.
     */
    @java.lang.Override
    public int getEmptyTimeout() {
        return emptyTimeout_;
    }

    public static final int DEPARTURE_TIMEOUT_FIELD_NUMBER = 10;
    private int departureTimeout_ = 0;

    /**
     * <pre>
     * number of seconds to keep the room open after everyone leaves
     * </pre>
     *
     * <code>uint32 departure_timeout = 10;</code>
     *
     * @return The departureTimeout.
     */
    @java.lang.Override
    public int getDepartureTimeout() {
        return departureTimeout_;
    }

    public static final int MAX_PARTICIPANTS_FIELD_NUMBER = 3;
    private int maxParticipants_ = 0;

    /**
     * <pre>
     * limit number of participants that can be in a room
     * </pre>
     *
     * <code>uint32 max_participants = 3;</code>
     *
     * @return The maxParticipants.
     */
    @java.lang.Override
    public int getMaxParticipants() {
        return maxParticipants_;
    }

    public static final int NODE_ID_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object nodeId_ = "";

    /**
     * <pre>
     * override the node room is allocated to, for debugging
     * </pre>
     *
     * <code>string node_id = 4;</code>
     *
     * @return The nodeId.
     */
    @java.lang.Override
    public java.lang.String getNodeId() {
        java.lang.Object ref = nodeId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            nodeId_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * override the node room is allocated to, for debugging
     * </pre>
     *
     * <code>string node_id = 4;</code>
     *
     * @return The bytes for nodeId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNodeIdBytes() {
        java.lang.Object ref = nodeId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            nodeId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int METADATA_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object metadata_ = "";

    /**
     * <pre>
     * metadata of room
     * </pre>
     *
     * <code>string metadata = 5;</code>
     *
     * @return The metadata.
     */
    @java.lang.Override
    public java.lang.String getMetadata() {
        java.lang.Object ref = metadata_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            metadata_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * metadata of room
     * </pre>
     *
     * <code>string metadata = 5;</code>
     *
     * @return The bytes for metadata.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMetadataBytes() {
        java.lang.Object ref = metadata_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            metadata_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int EGRESS_FIELD_NUMBER = 6;
    private im.turms.plugin.livekit.core.proto.room.RoomEgress egress_;

    /**
     * <pre>
     * egress
     * </pre>
     *
     * <code>.livekit.RoomEgress egress = 6;</code>
     *
     * @return Whether the egress field is set.
     */
    @java.lang.Override
    public boolean hasEgress() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * egress
     * </pre>
     *
     * <code>.livekit.RoomEgress egress = 6;</code>
     *
     * @return The egress.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.RoomEgress getEgress() {
        return egress_ == null
                ? im.turms.plugin.livekit.core.proto.room.RoomEgress.getDefaultInstance()
                : egress_;
    }

    /**
     * <pre>
     * egress
     * </pre>
     *
     * <code>.livekit.RoomEgress egress = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.RoomEgressOrBuilder getEgressOrBuilder() {
        return egress_ == null
                ? im.turms.plugin.livekit.core.proto.room.RoomEgress.getDefaultInstance()
                : egress_;
    }

    public static final int MIN_PLAYOUT_DELAY_FIELD_NUMBER = 7;
    private int minPlayoutDelay_ = 0;

    /**
     * <pre>
     * playout delay of subscriber
     * </pre>
     *
     * <code>uint32 min_playout_delay = 7;</code>
     *
     * @return The minPlayoutDelay.
     */
    @java.lang.Override
    public int getMinPlayoutDelay() {
        return minPlayoutDelay_;
    }

    public static final int MAX_PLAYOUT_DELAY_FIELD_NUMBER = 8;
    private int maxPlayoutDelay_ = 0;

    /**
     * <code>uint32 max_playout_delay = 8;</code>
     *
     * @return The maxPlayoutDelay.
     */
    @java.lang.Override
    public int getMaxPlayoutDelay() {
        return maxPlayoutDelay_;
    }

    public static final int SYNC_STREAMS_FIELD_NUMBER = 9;
    private boolean syncStreams_ = false;

    /**
     * <pre>
     * improves A/V sync when playout_delay set to a value larger than 200ms. It will disables transceiver re-use
     * so not recommended for rooms with frequent subscription changes
     * </pre>
     *
     * <code>bool sync_streams = 9;</code>
     *
     * @return The syncStreams.
     */
    @java.lang.Override
    public boolean getSyncStreams() {
        return syncStreams_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, name_);
        }
        if (emptyTimeout_ != 0) {
            output.writeUInt32(2, emptyTimeout_);
        }
        if (maxParticipants_ != 0) {
            output.writeUInt32(3, maxParticipants_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(nodeId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, nodeId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, metadata_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(6, getEgress());
        }
        if (minPlayoutDelay_ != 0) {
            output.writeUInt32(7, minPlayoutDelay_);
        }
        if (maxPlayoutDelay_ != 0) {
            output.writeUInt32(8, maxPlayoutDelay_);
        }
        if (syncStreams_) {
            output.writeBool(9, syncStreams_);
        }
        if (departureTimeout_ != 0) {
            output.writeUInt32(10, departureTimeout_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, name_);
        }
        if (emptyTimeout_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(2, emptyTimeout_);
        }
        if (maxParticipants_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, maxParticipants_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(nodeId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, nodeId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, metadata_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, getEgress());
        }
        if (minPlayoutDelay_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(7, minPlayoutDelay_);
        }
        if (maxPlayoutDelay_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(8, maxPlayoutDelay_);
        }
        if (syncStreams_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(9, syncStreams_);
        }
        if (departureTimeout_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(10, departureTimeout_);
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
        if (!(obj instanceof CreateRoomRequest other)) {
            return super.equals(obj);
        }

        if (!getName().equals(other.getName())) {
            return false;
        }
        if (getEmptyTimeout() != other.getEmptyTimeout()) {
            return false;
        }
        if (getDepartureTimeout() != other.getDepartureTimeout()) {
            return false;
        }
        if (getMaxParticipants() != other.getMaxParticipants()) {
            return false;
        }
        if (!getNodeId().equals(other.getNodeId())) {
            return false;
        }
        if (!getMetadata().equals(other.getMetadata())) {
            return false;
        }
        if (hasEgress() != other.hasEgress()) {
            return false;
        }
        if (hasEgress()) {
            if (!getEgress().equals(other.getEgress())) {
                return false;
            }
        }
        if (getMinPlayoutDelay() != other.getMinPlayoutDelay()) {
            return false;
        }
        if (getMaxPlayoutDelay() != other.getMaxPlayoutDelay()) {
            return false;
        }
        if (getSyncStreams() != other.getSyncStreams()) {
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
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (37 * hash) + EMPTY_TIMEOUT_FIELD_NUMBER;
        hash = (53 * hash) + getEmptyTimeout();
        hash = (37 * hash) + DEPARTURE_TIMEOUT_FIELD_NUMBER;
        hash = (53 * hash) + getDepartureTimeout();
        hash = (37 * hash) + MAX_PARTICIPANTS_FIELD_NUMBER;
        hash = (53 * hash) + getMaxParticipants();
        hash = (37 * hash) + NODE_ID_FIELD_NUMBER;
        hash = (53 * hash) + getNodeId().hashCode();
        hash = (37 * hash) + METADATA_FIELD_NUMBER;
        hash = (53 * hash) + getMetadata().hashCode();
        if (hasEgress()) {
            hash = (37 * hash) + EGRESS_FIELD_NUMBER;
            hash = (53 * hash) + getEgress().hashCode();
        }
        hash = (37 * hash) + MIN_PLAYOUT_DELAY_FIELD_NUMBER;
        hash = (53 * hash) + getMinPlayoutDelay();
        hash = (37 * hash) + MAX_PLAYOUT_DELAY_FIELD_NUMBER;
        hash = (53 * hash) + getMaxPlayoutDelay();
        hash = (37 * hash) + SYNC_STREAMS_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getSyncStreams());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.room.CreateRoomRequest prototype) {
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
     * Protobuf type {@code livekit.CreateRoomRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.CreateRoomRequest)
            im.turms.plugin.livekit.core.proto.room.CreateRoomRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_CreateRoomRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_CreateRoomRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.room.CreateRoomRequest.class,
                            im.turms.plugin.livekit.core.proto.room.CreateRoomRequest.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.room.CreateRoomRequest.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getEgressFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            name_ = "";
            emptyTimeout_ = 0;
            departureTimeout_ = 0;
            maxParticipants_ = 0;
            nodeId_ = "";
            metadata_ = "";
            egress_ = null;
            if (egressBuilder_ != null) {
                egressBuilder_.dispose();
                egressBuilder_ = null;
            }
            minPlayoutDelay_ = 0;
            maxPlayoutDelay_ = 0;
            syncStreams_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.room.LivekitRoom.internal_static_livekit_CreateRoomRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.CreateRoomRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.room.CreateRoomRequest.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.CreateRoomRequest build() {
            im.turms.plugin.livekit.core.proto.room.CreateRoomRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.room.CreateRoomRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.room.CreateRoomRequest result =
                    new im.turms.plugin.livekit.core.proto.room.CreateRoomRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.room.CreateRoomRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.emptyTimeout_ = emptyTimeout_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.departureTimeout_ = departureTimeout_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.maxParticipants_ = maxParticipants_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.nodeId_ = nodeId_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.metadata_ = metadata_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.egress_ = egressBuilder_ == null
                        ? egress_
                        : egressBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.minPlayoutDelay_ = minPlayoutDelay_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.maxPlayoutDelay_ = maxPlayoutDelay_;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.syncStreams_ = syncStreams_;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.room.CreateRoomRequest) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.room.CreateRoomRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.room.CreateRoomRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.room.CreateRoomRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getEmptyTimeout() != 0) {
                setEmptyTimeout(other.getEmptyTimeout());
            }
            if (other.getDepartureTimeout() != 0) {
                setDepartureTimeout(other.getDepartureTimeout());
            }
            if (other.getMaxParticipants() != 0) {
                setMaxParticipants(other.getMaxParticipants());
            }
            if (!other.getNodeId()
                    .isEmpty()) {
                nodeId_ = other.nodeId_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (!other.getMetadata()
                    .isEmpty()) {
                metadata_ = other.metadata_;
                bitField0_ |= 0x00000020;
                onChanged();
            }
            if (other.hasEgress()) {
                mergeEgress(other.getEgress());
            }
            if (other.getMinPlayoutDelay() != 0) {
                setMinPlayoutDelay(other.getMinPlayoutDelay());
            }
            if (other.getMaxPlayoutDelay() != 0) {
                setMaxPlayoutDelay(other.getMaxPlayoutDelay());
            }
            if (other.getSyncStreams()) {
                setSyncStreams(other.getSyncStreams());
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
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            emptyTimeout_ = input.readUInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            maxParticipants_ = input.readUInt32();
                            bitField0_ |= 0x00000008;
                        } // case 24
                        case 34 -> {
                            nodeId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 34
                        case 42 -> {
                            metadata_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 42
                        case 50 -> {
                            input.readMessage(getEgressFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000040;
                        } // case 50
                        case 56 -> {
                            minPlayoutDelay_ = input.readUInt32();
                            bitField0_ |= 0x00000080;
                        } // case 56
                        case 64 -> {
                            maxPlayoutDelay_ = input.readUInt32();
                            bitField0_ |= 0x00000100;
                        } // case 64
                        case 72 -> {
                            syncStreams_ = input.readBool();
                            bitField0_ |= 0x00000200;
                        } // case 72
                        case 80 -> {
                            departureTimeout_ = input.readUInt32();
                            bitField0_ |= 0x00000004;
                        } // case 80
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

        private java.lang.Object name_ = "";

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string name = 1;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
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
         * <code>string name = 1;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
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
         * <code>string name = 1;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * name of the room
         * </pre>
         *
         * <code>string name = 1;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private int emptyTimeout_;

        /**
         * <pre>
         * number of seconds to keep the room open if no one joins
         * </pre>
         *
         * <code>uint32 empty_timeout = 2;</code>
         *
         * @return The emptyTimeout.
         */
        @java.lang.Override
        public int getEmptyTimeout() {
            return emptyTimeout_;
        }

        /**
         * <pre>
         * number of seconds to keep the room open if no one joins
         * </pre>
         *
         * <code>uint32 empty_timeout = 2;</code>
         *
         * @param value The emptyTimeout to set.
         * @return This builder for chaining.
         */
        public Builder setEmptyTimeout(int value) {

            emptyTimeout_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * number of seconds to keep the room open if no one joins
         * </pre>
         *
         * <code>uint32 empty_timeout = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEmptyTimeout() {
            bitField0_ &= ~0x00000002;
            emptyTimeout_ = 0;
            onChanged();
            return this;
        }

        private int departureTimeout_;

        /**
         * <pre>
         * number of seconds to keep the room open after everyone leaves
         * </pre>
         *
         * <code>uint32 departure_timeout = 10;</code>
         *
         * @return The departureTimeout.
         */
        @java.lang.Override
        public int getDepartureTimeout() {
            return departureTimeout_;
        }

        /**
         * <pre>
         * number of seconds to keep the room open after everyone leaves
         * </pre>
         *
         * <code>uint32 departure_timeout = 10;</code>
         *
         * @param value The departureTimeout to set.
         * @return This builder for chaining.
         */
        public Builder setDepartureTimeout(int value) {

            departureTimeout_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * number of seconds to keep the room open after everyone leaves
         * </pre>
         *
         * <code>uint32 departure_timeout = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDepartureTimeout() {
            bitField0_ &= ~0x00000004;
            departureTimeout_ = 0;
            onChanged();
            return this;
        }

        private int maxParticipants_;

        /**
         * <pre>
         * limit number of participants that can be in a room
         * </pre>
         *
         * <code>uint32 max_participants = 3;</code>
         *
         * @return The maxParticipants.
         */
        @java.lang.Override
        public int getMaxParticipants() {
            return maxParticipants_;
        }

        /**
         * <pre>
         * limit number of participants that can be in a room
         * </pre>
         *
         * <code>uint32 max_participants = 3;</code>
         *
         * @param value The maxParticipants to set.
         * @return This builder for chaining.
         */
        public Builder setMaxParticipants(int value) {

            maxParticipants_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * limit number of participants that can be in a room
         * </pre>
         *
         * <code>uint32 max_participants = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxParticipants() {
            bitField0_ &= ~0x00000008;
            maxParticipants_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object nodeId_ = "";

        /**
         * <pre>
         * override the node room is allocated to, for debugging
         * </pre>
         *
         * <code>string node_id = 4;</code>
         *
         * @return The nodeId.
         */
        public java.lang.String getNodeId() {
            java.lang.Object ref = nodeId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                nodeId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * override the node room is allocated to, for debugging
         * </pre>
         *
         * <code>string node_id = 4;</code>
         *
         * @return The bytes for nodeId.
         */
        public com.google.protobuf.ByteString getNodeIdBytes() {
            java.lang.Object ref = nodeId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                nodeId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * override the node room is allocated to, for debugging
         * </pre>
         *
         * <code>string node_id = 4;</code>
         *
         * @param value The nodeId to set.
         * @return This builder for chaining.
         */
        public Builder setNodeId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            nodeId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * override the node room is allocated to, for debugging
         * </pre>
         *
         * <code>string node_id = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNodeId() {
            nodeId_ = getDefaultInstance().getNodeId();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * override the node room is allocated to, for debugging
         * </pre>
         *
         * <code>string node_id = 4;</code>
         *
         * @param value The bytes for nodeId to set.
         * @return This builder for chaining.
         */
        public Builder setNodeIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            nodeId_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object metadata_ = "";

        /**
         * <pre>
         * metadata of room
         * </pre>
         *
         * <code>string metadata = 5;</code>
         *
         * @return The metadata.
         */
        public java.lang.String getMetadata() {
            java.lang.Object ref = metadata_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                metadata_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * metadata of room
         * </pre>
         *
         * <code>string metadata = 5;</code>
         *
         * @return The bytes for metadata.
         */
        public com.google.protobuf.ByteString getMetadataBytes() {
            java.lang.Object ref = metadata_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                metadata_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * metadata of room
         * </pre>
         *
         * <code>string metadata = 5;</code>
         *
         * @param value The metadata to set.
         * @return This builder for chaining.
         */
        public Builder setMetadata(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            metadata_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * metadata of room
         * </pre>
         *
         * <code>string metadata = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMetadata() {
            metadata_ = getDefaultInstance().getMetadata();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * metadata of room
         * </pre>
         *
         * <code>string metadata = 5;</code>
         *
         * @param value The bytes for metadata to set.
         * @return This builder for chaining.
         */
        public Builder setMetadataBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            metadata_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.room.RoomEgress egress_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.room.RoomEgress, im.turms.plugin.livekit.core.proto.room.RoomEgress.Builder, im.turms.plugin.livekit.core.proto.room.RoomEgressOrBuilder> egressBuilder_;

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         *
         * @return Whether the egress field is set.
         */
        public boolean hasEgress() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         *
         * @return The egress.
         */
        public im.turms.plugin.livekit.core.proto.room.RoomEgress getEgress() {
            if (egressBuilder_ == null) {
                return egress_ == null
                        ? im.turms.plugin.livekit.core.proto.room.RoomEgress.getDefaultInstance()
                        : egress_;
            } else {
                return egressBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        public Builder setEgress(im.turms.plugin.livekit.core.proto.room.RoomEgress value) {
            if (egressBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                egress_ = value;
            } else {
                egressBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        public Builder setEgress(
                im.turms.plugin.livekit.core.proto.room.RoomEgress.Builder builderForValue) {
            if (egressBuilder_ == null) {
                egress_ = builderForValue.build();
            } else {
                egressBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        public Builder mergeEgress(im.turms.plugin.livekit.core.proto.room.RoomEgress value) {
            if (egressBuilder_ == null) {
                if (((bitField0_ & 0x00000040) != 0)
                        && egress_ != null
                        && egress_ != im.turms.plugin.livekit.core.proto.room.RoomEgress
                                .getDefaultInstance()) {
                    getEgressBuilder().mergeFrom(value);
                } else {
                    egress_ = value;
                }
            } else {
                egressBuilder_.mergeFrom(value);
            }
            if (egress_ != null) {
                bitField0_ |= 0x00000040;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        public Builder clearEgress() {
            bitField0_ &= ~0x00000040;
            egress_ = null;
            if (egressBuilder_ != null) {
                egressBuilder_.dispose();
                egressBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.room.RoomEgress.Builder getEgressBuilder() {
            bitField0_ |= 0x00000040;
            onChanged();
            return getEgressFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.room.RoomEgressOrBuilder getEgressOrBuilder() {
            if (egressBuilder_ != null) {
                return egressBuilder_.getMessageOrBuilder();
            } else {
                return egress_ == null
                        ? im.turms.plugin.livekit.core.proto.room.RoomEgress.getDefaultInstance()
                        : egress_;
            }
        }

        /**
         * <pre>
         * egress
         * </pre>
         *
         * <code>.livekit.RoomEgress egress = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.room.RoomEgress, im.turms.plugin.livekit.core.proto.room.RoomEgress.Builder, im.turms.plugin.livekit.core.proto.room.RoomEgressOrBuilder> getEgressFieldBuilder() {
            if (egressBuilder_ == null) {
                egressBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getEgress(),
                        getParentForChildren(),
                        isClean());
                egress_ = null;
            }
            return egressBuilder_;
        }

        private int minPlayoutDelay_;

        /**
         * <pre>
         * playout delay of subscriber
         * </pre>
         *
         * <code>uint32 min_playout_delay = 7;</code>
         *
         * @return The minPlayoutDelay.
         */
        @java.lang.Override
        public int getMinPlayoutDelay() {
            return minPlayoutDelay_;
        }

        /**
         * <pre>
         * playout delay of subscriber
         * </pre>
         *
         * <code>uint32 min_playout_delay = 7;</code>
         *
         * @param value The minPlayoutDelay to set.
         * @return This builder for chaining.
         */
        public Builder setMinPlayoutDelay(int value) {

            minPlayoutDelay_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * playout delay of subscriber
         * </pre>
         *
         * <code>uint32 min_playout_delay = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMinPlayoutDelay() {
            bitField0_ &= ~0x00000080;
            minPlayoutDelay_ = 0;
            onChanged();
            return this;
        }

        private int maxPlayoutDelay_;

        /**
         * <code>uint32 max_playout_delay = 8;</code>
         *
         * @return The maxPlayoutDelay.
         */
        @java.lang.Override
        public int getMaxPlayoutDelay() {
            return maxPlayoutDelay_;
        }

        /**
         * <code>uint32 max_playout_delay = 8;</code>
         *
         * @param value The maxPlayoutDelay to set.
         * @return This builder for chaining.
         */
        public Builder setMaxPlayoutDelay(int value) {

            maxPlayoutDelay_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 max_playout_delay = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxPlayoutDelay() {
            bitField0_ &= ~0x00000100;
            maxPlayoutDelay_ = 0;
            onChanged();
            return this;
        }

        private boolean syncStreams_;

        /**
         * <pre>
         * improves A/V sync when playout_delay set to a value larger than 200ms. It will disables transceiver re-use
         * so not recommended for rooms with frequent subscription changes
         * </pre>
         *
         * <code>bool sync_streams = 9;</code>
         *
         * @return The syncStreams.
         */
        @java.lang.Override
        public boolean getSyncStreams() {
            return syncStreams_;
        }

        /**
         * <pre>
         * improves A/V sync when playout_delay set to a value larger than 200ms. It will disables transceiver re-use
         * so not recommended for rooms with frequent subscription changes
         * </pre>
         *
         * <code>bool sync_streams = 9;</code>
         *
         * @param value The syncStreams to set.
         * @return This builder for chaining.
         */
        public Builder setSyncStreams(boolean value) {

            syncStreams_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * improves A/V sync when playout_delay set to a value larger than 200ms. It will disables transceiver re-use
         * so not recommended for rooms with frequent subscription changes
         * </pre>
         *
         * <code>bool sync_streams = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSyncStreams() {
            bitField0_ &= ~0x00000200;
            syncStreams_ = false;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.CreateRoomRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.CreateRoomRequest)
    private static final im.turms.plugin.livekit.core.proto.room.CreateRoomRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.room.CreateRoomRequest();
    }

    public static im.turms.plugin.livekit.core.proto.room.CreateRoomRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CreateRoomRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public CreateRoomRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<CreateRoomRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CreateRoomRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.room.CreateRoomRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}