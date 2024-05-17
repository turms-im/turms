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

package im.turms.plugin.livekit.core.proto.models;

/**
 * Protobuf type {@code livekit.Room}
 */
public final class Room extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.Room)
        RoomOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                Room.class.getName());
    }

    // Use Room.newBuilder() to construct.
    private Room(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private Room() {
        sid_ = "";
        name_ = "";
        turnPassword_ = "";
        enabledCodecs_ = java.util.Collections.emptyList();
        metadata_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Room_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Room_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.Room.class,
                        im.turms.plugin.livekit.core.proto.models.Room.Builder.class);
    }

    private int bitField0_;
    public static final int SID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object sid_ = "";

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    @java.lang.Override
    public java.lang.String getSid() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            sid_ = s;
            return s;
        }
    }

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSidBytes() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            sid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int NAME_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

    /**
     * <code>string name = 2;</code>
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
     * <code>string name = 2;</code>
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

    public static final int EMPTY_TIMEOUT_FIELD_NUMBER = 3;
    private int emptyTimeout_ = 0;

    /**
     * <code>uint32 empty_timeout = 3;</code>
     *
     * @return The emptyTimeout.
     */
    @java.lang.Override
    public int getEmptyTimeout() {
        return emptyTimeout_;
    }

    public static final int DEPARTURE_TIMEOUT_FIELD_NUMBER = 14;
    private int departureTimeout_ = 0;

    /**
     * <code>uint32 departure_timeout = 14;</code>
     *
     * @return The departureTimeout.
     */
    @java.lang.Override
    public int getDepartureTimeout() {
        return departureTimeout_;
    }

    public static final int MAX_PARTICIPANTS_FIELD_NUMBER = 4;
    private int maxParticipants_ = 0;

    /**
     * <code>uint32 max_participants = 4;</code>
     *
     * @return The maxParticipants.
     */
    @java.lang.Override
    public int getMaxParticipants() {
        return maxParticipants_;
    }

    public static final int CREATION_TIME_FIELD_NUMBER = 5;
    private long creationTime_ = 0L;

    /**
     * <code>int64 creation_time = 5;</code>
     *
     * @return The creationTime.
     */
    @java.lang.Override
    public long getCreationTime() {
        return creationTime_;
    }

    public static final int TURN_PASSWORD_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object turnPassword_ = "";

    /**
     * <code>string turn_password = 6;</code>
     *
     * @return The turnPassword.
     */
    @java.lang.Override
    public java.lang.String getTurnPassword() {
        java.lang.Object ref = turnPassword_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            turnPassword_ = s;
            return s;
        }
    }

    /**
     * <code>string turn_password = 6;</code>
     *
     * @return The bytes for turnPassword.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTurnPasswordBytes() {
        java.lang.Object ref = turnPassword_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            turnPassword_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ENABLED_CODECS_FIELD_NUMBER = 7;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> enabledCodecs_;

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getEnabledCodecsList() {
        return enabledCodecs_;
    }

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getEnabledCodecsOrBuilderList() {
        return enabledCodecs_;
    }

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    @java.lang.Override
    public int getEnabledCodecsCount() {
        return enabledCodecs_.size();
    }

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Codec getEnabledCodecs(int index) {
        return enabledCodecs_.get(index);
    }

    /**
     * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getEnabledCodecsOrBuilder(
            int index) {
        return enabledCodecs_.get(index);
    }

    public static final int METADATA_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private volatile java.lang.Object metadata_ = "";

    /**
     * <code>string metadata = 8;</code>
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
     * <code>string metadata = 8;</code>
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

    public static final int NUM_PARTICIPANTS_FIELD_NUMBER = 9;
    private int numParticipants_ = 0;

    /**
     * <code>uint32 num_participants = 9;</code>
     *
     * @return The numParticipants.
     */
    @java.lang.Override
    public int getNumParticipants() {
        return numParticipants_;
    }

    public static final int NUM_PUBLISHERS_FIELD_NUMBER = 11;
    private int numPublishers_ = 0;

    /**
     * <code>uint32 num_publishers = 11;</code>
     *
     * @return The numPublishers.
     */
    @java.lang.Override
    public int getNumPublishers() {
        return numPublishers_;
    }

    public static final int ACTIVE_RECORDING_FIELD_NUMBER = 10;
    private boolean activeRecording_ = false;

    /**
     * <code>bool active_recording = 10;</code>
     *
     * @return The activeRecording.
     */
    @java.lang.Override
    public boolean getActiveRecording() {
        return activeRecording_;
    }

    public static final int VERSION_FIELD_NUMBER = 13;
    private im.turms.plugin.livekit.core.proto.models.TimedVersion version_;

    /**
     * <code>.livekit.TimedVersion version = 13;</code>
     *
     * @return Whether the version field is set.
     */
    @java.lang.Override
    public boolean hasVersion() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.livekit.TimedVersion version = 13;</code>
     *
     * @return The version.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TimedVersion getVersion() {
        return version_ == null
                ? im.turms.plugin.livekit.core.proto.models.TimedVersion.getDefaultInstance()
                : version_;
    }

    /**
     * <code>.livekit.TimedVersion version = 13;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder getVersionOrBuilder() {
        return version_ == null
                ? im.turms.plugin.livekit.core.proto.models.TimedVersion.getDefaultInstance()
                : version_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, sid_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, name_);
        }
        if (emptyTimeout_ != 0) {
            output.writeUInt32(3, emptyTimeout_);
        }
        if (maxParticipants_ != 0) {
            output.writeUInt32(4, maxParticipants_);
        }
        if (creationTime_ != 0L) {
            output.writeInt64(5, creationTime_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(turnPassword_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, turnPassword_);
        }
        for (Codec codec : enabledCodecs_) {
            output.writeMessage(7, codec);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 8, metadata_);
        }
        if (numParticipants_ != 0) {
            output.writeUInt32(9, numParticipants_);
        }
        if (activeRecording_) {
            output.writeBool(10, activeRecording_);
        }
        if (numPublishers_ != 0) {
            output.writeUInt32(11, numPublishers_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(13, getVersion());
        }
        if (departureTimeout_ != 0) {
            output.writeUInt32(14, departureTimeout_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, sid_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, name_);
        }
        if (emptyTimeout_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, emptyTimeout_);
        }
        if (maxParticipants_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(4, maxParticipants_);
        }
        if (creationTime_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(5, creationTime_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(turnPassword_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, turnPassword_);
        }
        for (Codec codec : enabledCodecs_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, codec);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(metadata_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(8, metadata_);
        }
        if (numParticipants_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(9, numParticipants_);
        }
        if (activeRecording_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(10, activeRecording_);
        }
        if (numPublishers_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(11, numPublishers_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(13, getVersion());
        }
        if (departureTimeout_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(14, departureTimeout_);
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
        if (!(obj instanceof Room other)) {
            return super.equals(obj);
        }

        if (!getSid().equals(other.getSid())) {
            return false;
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
        if (getCreationTime() != other.getCreationTime()) {
            return false;
        }
        if (!getTurnPassword().equals(other.getTurnPassword())) {
            return false;
        }
        if (!getEnabledCodecsList().equals(other.getEnabledCodecsList())) {
            return false;
        }
        if (!getMetadata().equals(other.getMetadata())) {
            return false;
        }
        if (getNumParticipants() != other.getNumParticipants()) {
            return false;
        }
        if (getNumPublishers() != other.getNumPublishers()) {
            return false;
        }
        if (getActiveRecording() != other.getActiveRecording()) {
            return false;
        }
        if (hasVersion() != other.hasVersion()) {
            return false;
        }
        if (hasVersion()) {
            if (!getVersion().equals(other.getVersion())) {
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
        hash = (37 * hash) + SID_FIELD_NUMBER;
        hash = (53 * hash) + getSid().hashCode();
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (37 * hash) + EMPTY_TIMEOUT_FIELD_NUMBER;
        hash = (53 * hash) + getEmptyTimeout();
        hash = (37 * hash) + DEPARTURE_TIMEOUT_FIELD_NUMBER;
        hash = (53 * hash) + getDepartureTimeout();
        hash = (37 * hash) + MAX_PARTICIPANTS_FIELD_NUMBER;
        hash = (53 * hash) + getMaxParticipants();
        hash = (37 * hash) + CREATION_TIME_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getCreationTime());
        hash = (37 * hash) + TURN_PASSWORD_FIELD_NUMBER;
        hash = (53 * hash) + getTurnPassword().hashCode();
        if (getEnabledCodecsCount() > 0) {
            hash = (37 * hash) + ENABLED_CODECS_FIELD_NUMBER;
            hash = (53 * hash) + getEnabledCodecsList().hashCode();
        }
        hash = (37 * hash) + METADATA_FIELD_NUMBER;
        hash = (53 * hash) + getMetadata().hashCode();
        hash = (37 * hash) + NUM_PARTICIPANTS_FIELD_NUMBER;
        hash = (53 * hash) + getNumParticipants();
        hash = (37 * hash) + NUM_PUBLISHERS_FIELD_NUMBER;
        hash = (53 * hash) + getNumPublishers();
        hash = (37 * hash) + ACTIVE_RECORDING_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getActiveRecording());
        if (hasVersion()) {
            hash = (37 * hash) + VERSION_FIELD_NUMBER;
            hash = (53 * hash) + getVersion().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.Room parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.models.Room prototype) {
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
     * Protobuf type {@code livekit.Room}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.Room)
            im.turms.plugin.livekit.core.proto.models.RoomOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Room_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Room_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.Room.class,
                            im.turms.plugin.livekit.core.proto.models.Room.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.Room.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getEnabledCodecsFieldBuilder();
                getVersionFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            sid_ = "";
            name_ = "";
            emptyTimeout_ = 0;
            departureTimeout_ = 0;
            maxParticipants_ = 0;
            creationTime_ = 0L;
            turnPassword_ = "";
            if (enabledCodecsBuilder_ == null) {
                enabledCodecs_ = java.util.Collections.emptyList();
            } else {
                enabledCodecs_ = null;
                enabledCodecsBuilder_.clear();
            }
            bitField0_ &= ~0x00000080;
            metadata_ = "";
            numParticipants_ = 0;
            numPublishers_ = 0;
            activeRecording_ = false;
            version_ = null;
            if (versionBuilder_ != null) {
                versionBuilder_.dispose();
                versionBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Room_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Room getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Room build() {
            im.turms.plugin.livekit.core.proto.models.Room result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Room buildPartial() {
            im.turms.plugin.livekit.core.proto.models.Room result =
                    new im.turms.plugin.livekit.core.proto.models.Room(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.Room result) {
            if (enabledCodecsBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)) {
                    enabledCodecs_ = java.util.Collections.unmodifiableList(enabledCodecs_);
                    bitField0_ &= ~0x00000080;
                }
                result.enabledCodecs_ = enabledCodecs_;
            } else {
                result.enabledCodecs_ = enabledCodecsBuilder_.build();
            }
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.Room result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.sid_ = sid_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.emptyTimeout_ = emptyTimeout_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.departureTimeout_ = departureTimeout_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.maxParticipants_ = maxParticipants_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.creationTime_ = creationTime_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.turnPassword_ = turnPassword_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.metadata_ = metadata_;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.numParticipants_ = numParticipants_;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.numPublishers_ = numPublishers_;
            }
            if (((from_bitField0_ & 0x00000800) != 0)) {
                result.activeRecording_ = activeRecording_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00001000) != 0)) {
                result.version_ = versionBuilder_ == null
                        ? version_
                        : versionBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.Room) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.Room) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.Room other) {
            if (other == im.turms.plugin.livekit.core.proto.models.Room.getDefaultInstance()) {
                return this;
            }
            if (!other.getSid()
                    .isEmpty()) {
                sid_ = other.sid_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000002;
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
            if (other.getCreationTime() != 0L) {
                setCreationTime(other.getCreationTime());
            }
            if (!other.getTurnPassword()
                    .isEmpty()) {
                turnPassword_ = other.turnPassword_;
                bitField0_ |= 0x00000040;
                onChanged();
            }
            if (enabledCodecsBuilder_ == null) {
                if (!other.enabledCodecs_.isEmpty()) {
                    if (enabledCodecs_.isEmpty()) {
                        enabledCodecs_ = other.enabledCodecs_;
                        bitField0_ &= ~0x00000080;
                    } else {
                        ensureEnabledCodecsIsMutable();
                        enabledCodecs_.addAll(other.enabledCodecs_);
                    }
                    onChanged();
                }
            } else {
                if (!other.enabledCodecs_.isEmpty()) {
                    if (enabledCodecsBuilder_.isEmpty()) {
                        enabledCodecsBuilder_.dispose();
                        enabledCodecsBuilder_ = null;
                        enabledCodecs_ = other.enabledCodecs_;
                        bitField0_ &= ~0x00000080;
                        enabledCodecsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getEnabledCodecsFieldBuilder()
                                        : null;
                    } else {
                        enabledCodecsBuilder_.addAllMessages(other.enabledCodecs_);
                    }
                }
            }
            if (!other.getMetadata()
                    .isEmpty()) {
                metadata_ = other.metadata_;
                bitField0_ |= 0x00000100;
                onChanged();
            }
            if (other.getNumParticipants() != 0) {
                setNumParticipants(other.getNumParticipants());
            }
            if (other.getNumPublishers() != 0) {
                setNumPublishers(other.getNumPublishers());
            }
            if (other.getActiveRecording()) {
                setActiveRecording(other.getActiveRecording());
            }
            if (other.hasVersion()) {
                mergeVersion(other.getVersion());
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
                            sid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            emptyTimeout_ = input.readUInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            maxParticipants_ = input.readUInt32();
                            bitField0_ |= 0x00000010;
                        } // case 32
                        case 40 -> {
                            creationTime_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 40
                        case 50 -> {
                            turnPassword_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000040;
                        } // case 50
                        case 58 -> {
                            Codec m = input.readMessage(Codec.parser(), extensionRegistry);
                            if (enabledCodecsBuilder_ == null) {
                                ensureEnabledCodecsIsMutable();
                                enabledCodecs_.add(m);
                            } else {
                                enabledCodecsBuilder_.addMessage(m);
                            }
                        } // case 58
                        case 66 -> {
                            metadata_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000100;
                        } // case 66
                        case 72 -> {
                            numParticipants_ = input.readUInt32();
                            bitField0_ |= 0x00000200;
                        } // case 72
                        case 80 -> {
                            activeRecording_ = input.readBool();
                            bitField0_ |= 0x00000800;
                        } // case 80
                        case 88 -> {
                            numPublishers_ = input.readUInt32();
                            bitField0_ |= 0x00000400;
                        } // case 88
                        case 106 -> {
                            input.readMessage(getVersionFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00001000;
                        } // case 106
                        case 112 -> {
                            departureTimeout_ = input.readUInt32();
                            bitField0_ |= 0x00000008;
                        } // case 112
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

        private java.lang.Object sid_ = "";

        /**
         * <code>string sid = 1;</code>
         *
         * @return The sid.
         */
        public java.lang.String getSid() {
            java.lang.Object ref = sid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                sid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return The bytes for sid.
         */
        public com.google.protobuf.ByteString getSidBytes() {
            java.lang.Object ref = sid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                sid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The sid to set.
         * @return This builder for chaining.
         */
        public Builder setSid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSid() {
            sid_ = getDefaultInstance().getSid();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The bytes for sid to set.
         * @return This builder for chaining.
         */
        public Builder setSidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>string name = 2;</code>
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
         * <code>string name = 2;</code>
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
         * <code>string name = 2;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string name = 2;</code>
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
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private int emptyTimeout_;

        /**
         * <code>uint32 empty_timeout = 3;</code>
         *
         * @return The emptyTimeout.
         */
        @java.lang.Override
        public int getEmptyTimeout() {
            return emptyTimeout_;
        }

        /**
         * <code>uint32 empty_timeout = 3;</code>
         *
         * @param value The emptyTimeout to set.
         * @return This builder for chaining.
         */
        public Builder setEmptyTimeout(int value) {

            emptyTimeout_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 empty_timeout = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEmptyTimeout() {
            bitField0_ &= ~0x00000004;
            emptyTimeout_ = 0;
            onChanged();
            return this;
        }

        private int departureTimeout_;

        /**
         * <code>uint32 departure_timeout = 14;</code>
         *
         * @return The departureTimeout.
         */
        @java.lang.Override
        public int getDepartureTimeout() {
            return departureTimeout_;
        }

        /**
         * <code>uint32 departure_timeout = 14;</code>
         *
         * @param value The departureTimeout to set.
         * @return This builder for chaining.
         */
        public Builder setDepartureTimeout(int value) {

            departureTimeout_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 departure_timeout = 14;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDepartureTimeout() {
            bitField0_ &= ~0x00000008;
            departureTimeout_ = 0;
            onChanged();
            return this;
        }

        private int maxParticipants_;

        /**
         * <code>uint32 max_participants = 4;</code>
         *
         * @return The maxParticipants.
         */
        @java.lang.Override
        public int getMaxParticipants() {
            return maxParticipants_;
        }

        /**
         * <code>uint32 max_participants = 4;</code>
         *
         * @param value The maxParticipants to set.
         * @return This builder for chaining.
         */
        public Builder setMaxParticipants(int value) {

            maxParticipants_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 max_participants = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMaxParticipants() {
            bitField0_ &= ~0x00000010;
            maxParticipants_ = 0;
            onChanged();
            return this;
        }

        private long creationTime_;

        /**
         * <code>int64 creation_time = 5;</code>
         *
         * @return The creationTime.
         */
        @java.lang.Override
        public long getCreationTime() {
            return creationTime_;
        }

        /**
         * <code>int64 creation_time = 5;</code>
         *
         * @param value The creationTime to set.
         * @return This builder for chaining.
         */
        public Builder setCreationTime(long value) {

            creationTime_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>int64 creation_time = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCreationTime() {
            bitField0_ &= ~0x00000020;
            creationTime_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object turnPassword_ = "";

        /**
         * <code>string turn_password = 6;</code>
         *
         * @return The turnPassword.
         */
        public java.lang.String getTurnPassword() {
            java.lang.Object ref = turnPassword_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                turnPassword_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string turn_password = 6;</code>
         *
         * @return The bytes for turnPassword.
         */
        public com.google.protobuf.ByteString getTurnPasswordBytes() {
            java.lang.Object ref = turnPassword_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                turnPassword_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string turn_password = 6;</code>
         *
         * @param value The turnPassword to set.
         * @return This builder for chaining.
         */
        public Builder setTurnPassword(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            turnPassword_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>string turn_password = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTurnPassword() {
            turnPassword_ = getDefaultInstance().getTurnPassword();
            bitField0_ &= ~0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>string turn_password = 6;</code>
         *
         * @param value The bytes for turnPassword to set.
         * @return This builder for chaining.
         */
        public Builder setTurnPasswordBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            turnPassword_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        private java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> enabledCodecs_ =
                java.util.Collections.emptyList();

        private void ensureEnabledCodecsIsMutable() {
            if ((bitField0_ & 0x00000080) == 0) {
                enabledCodecs_ = new java.util.ArrayList<>(enabledCodecs_);
                bitField0_ |= 0x00000080;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Codec, im.turms.plugin.livekit.core.proto.models.Codec.Builder, im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> enabledCodecsBuilder_;

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec> getEnabledCodecsList() {
            if (enabledCodecsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(enabledCodecs_);
            } else {
                return enabledCodecsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public int getEnabledCodecsCount() {
            if (enabledCodecsBuilder_ == null) {
                return enabledCodecs_.size();
            } else {
                return enabledCodecsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec getEnabledCodecs(int index) {
            if (enabledCodecsBuilder_ == null) {
                return enabledCodecs_.get(index);
            } else {
                return enabledCodecsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder setEnabledCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (enabledCodecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.set(index, value);
                onChanged();
            } else {
                enabledCodecsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder setEnabledCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (enabledCodecsBuilder_ == null) {
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.set(index, builderForValue.build());
                onChanged();
            } else {
                enabledCodecsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder addEnabledCodecs(im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (enabledCodecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.add(value);
                onChanged();
            } else {
                enabledCodecsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder addEnabledCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec value) {
            if (enabledCodecsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.add(index, value);
                onChanged();
            } else {
                enabledCodecsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder addEnabledCodecs(
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (enabledCodecsBuilder_ == null) {
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.add(builderForValue.build());
                onChanged();
            } else {
                enabledCodecsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder addEnabledCodecs(
                int index,
                im.turms.plugin.livekit.core.proto.models.Codec.Builder builderForValue) {
            if (enabledCodecsBuilder_ == null) {
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.add(index, builderForValue.build());
                onChanged();
            } else {
                enabledCodecsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder addAllEnabledCodecs(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.Codec> values) {
            if (enabledCodecsBuilder_ == null) {
                ensureEnabledCodecsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, enabledCodecs_);
                onChanged();
            } else {
                enabledCodecsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder clearEnabledCodecs() {
            if (enabledCodecsBuilder_ == null) {
                enabledCodecs_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000080;
                onChanged();
            } else {
                enabledCodecsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public Builder removeEnabledCodecs(int index) {
            if (enabledCodecsBuilder_ == null) {
                ensureEnabledCodecsIsMutable();
                enabledCodecs_.remove(index);
                onChanged();
            } else {
                enabledCodecsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder getEnabledCodecsBuilder(
                int index) {
            return getEnabledCodecsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.CodecOrBuilder getEnabledCodecsOrBuilder(
                int index) {
            if (enabledCodecsBuilder_ == null) {
                return enabledCodecs_.get(index);
            } else {
                return enabledCodecsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getEnabledCodecsOrBuilderList() {
            if (enabledCodecsBuilder_ != null) {
                return enabledCodecsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(enabledCodecs_);
            }
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder addEnabledCodecsBuilder() {
            return getEnabledCodecsFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.Codec.Builder addEnabledCodecsBuilder(
                int index) {
            return getEnabledCodecsFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.Codec enabled_codecs = 7;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.Codec.Builder> getEnabledCodecsBuilderList() {
            return getEnabledCodecsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.Codec, im.turms.plugin.livekit.core.proto.models.Codec.Builder, im.turms.plugin.livekit.core.proto.models.CodecOrBuilder> getEnabledCodecsFieldBuilder() {
            if (enabledCodecsBuilder_ == null) {
                enabledCodecsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        enabledCodecs_,
                        ((bitField0_ & 0x00000080) != 0),
                        getParentForChildren(),
                        isClean());
                enabledCodecs_ = null;
            }
            return enabledCodecsBuilder_;
        }

        private java.lang.Object metadata_ = "";

        /**
         * <code>string metadata = 8;</code>
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
         * <code>string metadata = 8;</code>
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
         * <code>string metadata = 8;</code>
         *
         * @param value The metadata to set.
         * @return This builder for chaining.
         */
        public Builder setMetadata(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            metadata_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string metadata = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMetadata() {
            metadata_ = getDefaultInstance().getMetadata();
            bitField0_ &= ~0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string metadata = 8;</code>
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
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        private int numParticipants_;

        /**
         * <code>uint32 num_participants = 9;</code>
         *
         * @return The numParticipants.
         */
        @java.lang.Override
        public int getNumParticipants() {
            return numParticipants_;
        }

        /**
         * <code>uint32 num_participants = 9;</code>
         *
         * @param value The numParticipants to set.
         * @return This builder for chaining.
         */
        public Builder setNumParticipants(int value) {

            numParticipants_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 num_participants = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNumParticipants() {
            bitField0_ &= ~0x00000200;
            numParticipants_ = 0;
            onChanged();
            return this;
        }

        private int numPublishers_;

        /**
         * <code>uint32 num_publishers = 11;</code>
         *
         * @return The numPublishers.
         */
        @java.lang.Override
        public int getNumPublishers() {
            return numPublishers_;
        }

        /**
         * <code>uint32 num_publishers = 11;</code>
         *
         * @param value The numPublishers to set.
         * @return This builder for chaining.
         */
        public Builder setNumPublishers(int value) {

            numPublishers_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 num_publishers = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNumPublishers() {
            bitField0_ &= ~0x00000400;
            numPublishers_ = 0;
            onChanged();
            return this;
        }

        private boolean activeRecording_;

        /**
         * <code>bool active_recording = 10;</code>
         *
         * @return The activeRecording.
         */
        @java.lang.Override
        public boolean getActiveRecording() {
            return activeRecording_;
        }

        /**
         * <code>bool active_recording = 10;</code>
         *
         * @param value The activeRecording to set.
         * @return This builder for chaining.
         */
        public Builder setActiveRecording(boolean value) {

            activeRecording_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>bool active_recording = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearActiveRecording() {
            bitField0_ &= ~0x00000800;
            activeRecording_ = false;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.models.TimedVersion version_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TimedVersion, im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder, im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder> versionBuilder_;

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         *
         * @return Whether the version field is set.
         */
        public boolean hasVersion() {
            return ((bitField0_ & 0x00001000) != 0);
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         *
         * @return The version.
         */
        public im.turms.plugin.livekit.core.proto.models.TimedVersion getVersion() {
            if (versionBuilder_ == null) {
                return version_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TimedVersion
                                .getDefaultInstance()
                        : version_;
            } else {
                return versionBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        public Builder setVersion(im.turms.plugin.livekit.core.proto.models.TimedVersion value) {
            if (versionBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                version_ = value;
            } else {
                versionBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00001000;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        public Builder setVersion(
                im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder builderForValue) {
            if (versionBuilder_ == null) {
                version_ = builderForValue.build();
            } else {
                versionBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00001000;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        public Builder mergeVersion(im.turms.plugin.livekit.core.proto.models.TimedVersion value) {
            if (versionBuilder_ == null) {
                if (((bitField0_ & 0x00001000) != 0)
                        && version_ != null
                        && version_ != im.turms.plugin.livekit.core.proto.models.TimedVersion
                                .getDefaultInstance()) {
                    getVersionBuilder().mergeFrom(value);
                } else {
                    version_ = value;
                }
            } else {
                versionBuilder_.mergeFrom(value);
            }
            if (version_ != null) {
                bitField0_ |= 0x00001000;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        public Builder clearVersion() {
            bitField0_ &= ~0x00001000;
            version_ = null;
            if (versionBuilder_ != null) {
                versionBuilder_.dispose();
                versionBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder getVersionBuilder() {
            bitField0_ |= 0x00001000;
            onChanged();
            return getVersionFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder getVersionOrBuilder() {
            if (versionBuilder_ != null) {
                return versionBuilder_.getMessageOrBuilder();
            } else {
                return version_ == null
                        ? im.turms.plugin.livekit.core.proto.models.TimedVersion
                                .getDefaultInstance()
                        : version_;
            }
        }

        /**
         * <code>.livekit.TimedVersion version = 13;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.models.TimedVersion, im.turms.plugin.livekit.core.proto.models.TimedVersion.Builder, im.turms.plugin.livekit.core.proto.models.TimedVersionOrBuilder> getVersionFieldBuilder() {
            if (versionBuilder_ == null) {
                versionBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getVersion(),
                        getParentForChildren(),
                        isClean());
                version_ = null;
            }
            return versionBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.Room)
    }

    // @@protoc_insertion_point(class_scope:livekit.Room)
    private static final im.turms.plugin.livekit.core.proto.models.Room DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.Room();
    }

    public static im.turms.plugin.livekit.core.proto.models.Room getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Room> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Room parsePartialFrom(
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

    public static com.google.protobuf.Parser<Room> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Room> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Room getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}