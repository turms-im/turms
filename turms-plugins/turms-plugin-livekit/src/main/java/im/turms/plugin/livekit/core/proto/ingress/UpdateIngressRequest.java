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
 * Protobuf type {@code livekit.UpdateIngressRequest}
 */
public final class UpdateIngressRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.UpdateIngressRequest)
        UpdateIngressRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                UpdateIngressRequest.class.getName());
    }

    // Use UpdateIngressRequest.newBuilder() to construct.
    private UpdateIngressRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateIngressRequest() {
        ingressId_ = "";
        name_ = "";
        roomName_ = "";
        participantIdentity_ = "";
        participantName_ = "";
        participantMetadata_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_UpdateIngressRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_UpdateIngressRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest.class,
                        im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest.Builder.class);
    }

    private int bitField0_;
    public static final int INGRESS_ID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object ingressId_ = "";

    /**
     * <code>string ingress_id = 1;</code>
     *
     * @return The ingressId.
     */
    @java.lang.Override
    public java.lang.String getIngressId() {
        java.lang.Object ref = ingressId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            ingressId_ = s;
            return s;
        }
    }

    /**
     * <code>string ingress_id = 1;</code>
     *
     * @return The bytes for ingressId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIngressIdBytes() {
        java.lang.Object ref = ingressId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            ingressId_ = b;
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

    public static final int ROOM_NAME_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomName_ = "";

    /**
     * <code>string room_name = 3;</code>
     *
     * @return The roomName.
     */
    @java.lang.Override
    public java.lang.String getRoomName() {
        java.lang.Object ref = roomName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            roomName_ = s;
            return s;
        }
    }

    /**
     * <code>string room_name = 3;</code>
     *
     * @return The bytes for roomName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRoomNameBytes() {
        java.lang.Object ref = roomName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            roomName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PARTICIPANT_IDENTITY_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantIdentity_ = "";

    /**
     * <code>string participant_identity = 4;</code>
     *
     * @return The participantIdentity.
     */
    @java.lang.Override
    public java.lang.String getParticipantIdentity() {
        java.lang.Object ref = participantIdentity_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            participantIdentity_ = s;
            return s;
        }
    }

    /**
     * <code>string participant_identity = 4;</code>
     *
     * @return The bytes for participantIdentity.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getParticipantIdentityBytes() {
        java.lang.Object ref = participantIdentity_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            participantIdentity_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PARTICIPANT_NAME_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantName_ = "";

    /**
     * <code>string participant_name = 5;</code>
     *
     * @return The participantName.
     */
    @java.lang.Override
    public java.lang.String getParticipantName() {
        java.lang.Object ref = participantName_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            participantName_ = s;
            return s;
        }
    }

    /**
     * <code>string participant_name = 5;</code>
     *
     * @return The bytes for participantName.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getParticipantNameBytes() {
        java.lang.Object ref = participantName_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            participantName_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PARTICIPANT_METADATA_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantMetadata_ = "";

    /**
     * <code>string participant_metadata = 9;</code>
     *
     * @return The participantMetadata.
     */
    @java.lang.Override
    public java.lang.String getParticipantMetadata() {
        java.lang.Object ref = participantMetadata_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            participantMetadata_ = s;
            return s;
        }
    }

    /**
     * <code>string participant_metadata = 9;</code>
     *
     * @return The bytes for participantMetadata.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getParticipantMetadataBytes() {
        java.lang.Object ref = participantMetadata_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            participantMetadata_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int BYPASS_TRANSCODING_FIELD_NUMBER = 8;
    private boolean bypassTranscoding_ = false;

    /**
     * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=179
     * @return Whether the bypassTranscoding field is set.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean hasBypassTranscoding() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=179
     * @return The bypassTranscoding.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean getBypassTranscoding() {
        return bypassTranscoding_;
    }

    public static final int ENABLE_TRANSCODING_FIELD_NUMBER = 10;
    private boolean enableTranscoding_ = false;

    /**
     * <code>optional bool enable_transcoding = 10;</code>
     *
     * @return Whether the enableTranscoding field is set.
     */
    @java.lang.Override
    public boolean hasEnableTranscoding() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional bool enable_transcoding = 10;</code>
     *
     * @return The enableTranscoding.
     */
    @java.lang.Override
    public boolean getEnableTranscoding() {
        return enableTranscoding_;
    }

    public static final int AUDIO_FIELD_NUMBER = 6;
    private im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions audio_;

    /**
     * <code>.livekit.IngressAudioOptions audio = 6;</code>
     *
     * @return Whether the audio field is set.
     */
    @java.lang.Override
    public boolean hasAudio() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>.livekit.IngressAudioOptions audio = 6;</code>
     *
     * @return The audio.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions getAudio() {
        return audio_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions
                        .getDefaultInstance()
                : audio_;
    }

    /**
     * <code>.livekit.IngressAudioOptions audio = 6;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptionsOrBuilder getAudioOrBuilder() {
        return audio_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions
                        .getDefaultInstance()
                : audio_;
    }

    public static final int VIDEO_FIELD_NUMBER = 7;
    private im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions video_;

    /**
     * <code>.livekit.IngressVideoOptions video = 7;</code>
     *
     * @return Whether the video field is set.
     */
    @java.lang.Override
    public boolean hasVideo() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>.livekit.IngressVideoOptions video = 7;</code>
     *
     * @return The video.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions getVideo() {
        return video_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                        .getDefaultInstance()
                : video_;
    }

    /**
     * <code>.livekit.IngressVideoOptions video = 7;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptionsOrBuilder getVideoOrBuilder() {
        return video_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                        .getDefaultInstance()
                : video_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(ingressId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, ingressId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, name_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantIdentity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, participantIdentity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, participantName_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(6, getAudio());
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeMessage(7, getVideo());
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeBool(8, bypassTranscoding_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantMetadata_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, participantMetadata_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeBool(10, enableTranscoding_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(ingressId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, ingressId_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, name_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantIdentity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, participantIdentity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, participantName_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, getAudio());
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, getVideo());
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(8, bypassTranscoding_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantMetadata_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, participantMetadata_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(10, enableTranscoding_);
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
        if (!(obj instanceof UpdateIngressRequest other)) {
            return super.equals(obj);
        }

        if (!getIngressId().equals(other.getIngressId())) {
            return false;
        }
        if (!getName().equals(other.getName())) {
            return false;
        }
        if (!getRoomName().equals(other.getRoomName())) {
            return false;
        }
        if (!getParticipantIdentity().equals(other.getParticipantIdentity())) {
            return false;
        }
        if (!getParticipantName().equals(other.getParticipantName())) {
            return false;
        }
        if (!getParticipantMetadata().equals(other.getParticipantMetadata())) {
            return false;
        }
        if (hasBypassTranscoding() != other.hasBypassTranscoding()) {
            return false;
        }
        if (hasBypassTranscoding()) {
            if (getBypassTranscoding() != other.getBypassTranscoding()) {
                return false;
            }
        }
        if (hasEnableTranscoding() != other.hasEnableTranscoding()) {
            return false;
        }
        if (hasEnableTranscoding()) {
            if (getEnableTranscoding() != other.getEnableTranscoding()) {
                return false;
            }
        }
        if (hasAudio() != other.hasAudio()) {
            return false;
        }
        if (hasAudio()) {
            if (!getAudio().equals(other.getAudio())) {
                return false;
            }
        }
        if (hasVideo() != other.hasVideo()) {
            return false;
        }
        if (hasVideo()) {
            if (!getVideo().equals(other.getVideo())) {
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
        hash = (37 * hash) + INGRESS_ID_FIELD_NUMBER;
        hash = (53 * hash) + getIngressId().hashCode();
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
        hash = (37 * hash) + ROOM_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getRoomName().hashCode();
        hash = (37 * hash) + PARTICIPANT_IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantIdentity().hashCode();
        hash = (37 * hash) + PARTICIPANT_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantName().hashCode();
        hash = (37 * hash) + PARTICIPANT_METADATA_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantMetadata().hashCode();
        if (hasBypassTranscoding()) {
            hash = (37 * hash) + BYPASS_TRANSCODING_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getBypassTranscoding());
        }
        if (hasEnableTranscoding()) {
            hash = (37 * hash) + ENABLE_TRANSCODING_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getEnableTranscoding());
        }
        if (hasAudio()) {
            hash = (37 * hash) + AUDIO_FIELD_NUMBER;
            hash = (53 * hash) + getAudio().hashCode();
        }
        if (hasVideo()) {
            hash = (37 * hash) + VIDEO_FIELD_NUMBER;
            hash = (53 * hash) + getVideo().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest prototype) {
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
     * Protobuf type {@code livekit.UpdateIngressRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.UpdateIngressRequest)
            im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_UpdateIngressRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_UpdateIngressRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest.class,
                            im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getAudioFieldBuilder();
                getVideoFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            ingressId_ = "";
            name_ = "";
            roomName_ = "";
            participantIdentity_ = "";
            participantName_ = "";
            participantMetadata_ = "";
            bypassTranscoding_ = false;
            enableTranscoding_ = false;
            audio_ = null;
            if (audioBuilder_ != null) {
                audioBuilder_.dispose();
                audioBuilder_ = null;
            }
            video_ = null;
            if (videoBuilder_ != null) {
                videoBuilder_.dispose();
                videoBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_UpdateIngressRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest build() {
            im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest result =
                    new im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.ingressId_ = ingressId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.participantIdentity_ = participantIdentity_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.participantName_ = participantName_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.participantMetadata_ = participantMetadata_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.bypassTranscoding_ = bypassTranscoding_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.enableTranscoding_ = enableTranscoding_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.audio_ = audioBuilder_ == null
                        ? audio_
                        : audioBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.video_ = videoBuilder_ == null
                        ? video_
                        : videoBuilder_.build();
                to_bitField0_ |= 0x00000008;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getIngressId()
                    .isEmpty()) {
                ingressId_ = other.ingressId_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getName()
                    .isEmpty()) {
                name_ = other.name_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (!other.getParticipantIdentity()
                    .isEmpty()) {
                participantIdentity_ = other.participantIdentity_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (!other.getParticipantName()
                    .isEmpty()) {
                participantName_ = other.participantName_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (!other.getParticipantMetadata()
                    .isEmpty()) {
                participantMetadata_ = other.participantMetadata_;
                bitField0_ |= 0x00000020;
                onChanged();
            }
            if (other.hasBypassTranscoding()) {
                setBypassTranscoding(other.getBypassTranscoding());
            }
            if (other.hasEnableTranscoding()) {
                setEnableTranscoding(other.getEnableTranscoding());
            }
            if (other.hasAudio()) {
                mergeAudio(other.getAudio());
            }
            if (other.hasVideo()) {
                mergeVideo(other.getVideo());
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
                            ingressId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 26 -> {
                            roomName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            participantIdentity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            participantName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            input.readMessage(getAudioFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000100;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getVideoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000200;
                        } // case 58
                        case 64 -> {
                            bypassTranscoding_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 64
                        case 74 -> {
                            participantMetadata_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 74
                        case 80 -> {
                            enableTranscoding_ = input.readBool();
                            bitField0_ |= 0x00000080;
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

        private java.lang.Object ingressId_ = "";

        /**
         * <code>string ingress_id = 1;</code>
         *
         * @return The ingressId.
         */
        public java.lang.String getIngressId() {
            java.lang.Object ref = ingressId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                ingressId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string ingress_id = 1;</code>
         *
         * @return The bytes for ingressId.
         */
        public com.google.protobuf.ByteString getIngressIdBytes() {
            java.lang.Object ref = ingressId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                ingressId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string ingress_id = 1;</code>
         *
         * @param value The ingressId to set.
         * @return This builder for chaining.
         */
        public Builder setIngressId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ingressId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string ingress_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIngressId() {
            ingressId_ = getDefaultInstance().getIngressId();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string ingress_id = 1;</code>
         *
         * @param value The bytes for ingressId to set.
         * @return This builder for chaining.
         */
        public Builder setIngressIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ingressId_ = value;
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

        private java.lang.Object roomName_ = "";

        /**
         * <code>string room_name = 3;</code>
         *
         * @return The roomName.
         */
        public java.lang.String getRoomName() {
            java.lang.Object ref = roomName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                roomName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string room_name = 3;</code>
         *
         * @return The bytes for roomName.
         */
        public com.google.protobuf.ByteString getRoomNameBytes() {
            java.lang.Object ref = roomName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                roomName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string room_name = 3;</code>
         *
         * @param value The roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string room_name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomName() {
            roomName_ = getDefaultInstance().getRoomName();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string room_name = 3;</code>
         *
         * @param value The bytes for roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            roomName_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object participantIdentity_ = "";

        /**
         * <code>string participant_identity = 4;</code>
         *
         * @return The participantIdentity.
         */
        public java.lang.String getParticipantIdentity() {
            java.lang.Object ref = participantIdentity_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                participantIdentity_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string participant_identity = 4;</code>
         *
         * @return The bytes for participantIdentity.
         */
        public com.google.protobuf.ByteString getParticipantIdentityBytes() {
            java.lang.Object ref = participantIdentity_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                participantIdentity_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string participant_identity = 4;</code>
         *
         * @param value The participantIdentity to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantIdentity(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantIdentity_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_identity = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantIdentity() {
            participantIdentity_ = getDefaultInstance().getParticipantIdentity();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_identity = 4;</code>
         *
         * @param value The bytes for participantIdentity to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantIdentityBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            participantIdentity_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private java.lang.Object participantName_ = "";

        /**
         * <code>string participant_name = 5;</code>
         *
         * @return The participantName.
         */
        public java.lang.String getParticipantName() {
            java.lang.Object ref = participantName_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                participantName_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string participant_name = 5;</code>
         *
         * @return The bytes for participantName.
         */
        public com.google.protobuf.ByteString getParticipantNameBytes() {
            java.lang.Object ref = participantName_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                participantName_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string participant_name = 5;</code>
         *
         * @param value The participantName to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantName_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_name = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantName() {
            participantName_ = getDefaultInstance().getParticipantName();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_name = 5;</code>
         *
         * @param value The bytes for participantName to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            participantName_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object participantMetadata_ = "";

        /**
         * <code>string participant_metadata = 9;</code>
         *
         * @return The participantMetadata.
         */
        public java.lang.String getParticipantMetadata() {
            java.lang.Object ref = participantMetadata_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                participantMetadata_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string participant_metadata = 9;</code>
         *
         * @return The bytes for participantMetadata.
         */
        public com.google.protobuf.ByteString getParticipantMetadataBytes() {
            java.lang.Object ref = participantMetadata_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                participantMetadata_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string participant_metadata = 9;</code>
         *
         * @param value The participantMetadata to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantMetadata(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantMetadata_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_metadata = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantMetadata() {
            participantMetadata_ = getDefaultInstance().getParticipantMetadata();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_metadata = 9;</code>
         *
         * @param value The bytes for participantMetadata to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantMetadataBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            participantMetadata_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        private boolean bypassTranscoding_;

        /**
         * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
         *
         * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=179
         * @return Whether the bypassTranscoding field is set.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean hasBypassTranscoding() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
         *
         * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=179
         * @return The bypassTranscoding.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean getBypassTranscoding() {
            return bypassTranscoding_;
        }

        /**
         * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
         *
         * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=179
         * @param value The bypassTranscoding to set.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder setBypassTranscoding(boolean value) {

            bypassTranscoding_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
         *
         * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=179
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder clearBypassTranscoding() {
            bitField0_ &= ~0x00000040;
            bypassTranscoding_ = false;
            onChanged();
            return this;
        }

        private boolean enableTranscoding_;

        /**
         * <code>optional bool enable_transcoding = 10;</code>
         *
         * @return Whether the enableTranscoding field is set.
         */
        @java.lang.Override
        public boolean hasEnableTranscoding() {
            return ((bitField0_ & 0x00000080) != 0);
        }

        /**
         * <code>optional bool enable_transcoding = 10;</code>
         *
         * @return The enableTranscoding.
         */
        @java.lang.Override
        public boolean getEnableTranscoding() {
            return enableTranscoding_;
        }

        /**
         * <code>optional bool enable_transcoding = 10;</code>
         *
         * @param value The enableTranscoding to set.
         * @return This builder for chaining.
         */
        public Builder setEnableTranscoding(boolean value) {

            enableTranscoding_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool enable_transcoding = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEnableTranscoding() {
            bitField0_ &= ~0x00000080;
            enableTranscoding_ = false;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions audio_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions, im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptionsOrBuilder> audioBuilder_;

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         *
         * @return Whether the audio field is set.
         */
        public boolean hasAudio() {
            return ((bitField0_ & 0x00000100) != 0);
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         *
         * @return The audio.
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions getAudio() {
            if (audioBuilder_ == null) {
                return audio_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions
                                .getDefaultInstance()
                        : audio_;
            } else {
                return audioBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public Builder setAudio(
                im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions value) {
            if (audioBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                audio_ = value;
            } else {
                audioBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public Builder setAudio(
                im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions.Builder builderForValue) {
            if (audioBuilder_ == null) {
                audio_ = builderForValue.build();
            } else {
                audioBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public Builder mergeAudio(
                im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions value) {
            if (audioBuilder_ == null) {
                if (((bitField0_ & 0x00000100) != 0)
                        && audio_ != null
                        && audio_ != im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions
                                .getDefaultInstance()) {
                    getAudioBuilder().mergeFrom(value);
                } else {
                    audio_ = value;
                }
            } else {
                audioBuilder_.mergeFrom(value);
            }
            if (audio_ != null) {
                bitField0_ |= 0x00000100;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public Builder clearAudio() {
            bitField0_ &= ~0x00000100;
            audio_ = null;
            if (audioBuilder_ != null) {
                audioBuilder_.dispose();
                audioBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions.Builder getAudioBuilder() {
            bitField0_ |= 0x00000100;
            onChanged();
            return getAudioFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptionsOrBuilder getAudioOrBuilder() {
            if (audioBuilder_ != null) {
                return audioBuilder_.getMessageOrBuilder();
            } else {
                return audio_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions
                                .getDefaultInstance()
                        : audio_;
            }
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions, im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptionsOrBuilder> getAudioFieldBuilder() {
            if (audioBuilder_ == null) {
                audioBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getAudio(),
                        getParentForChildren(),
                        isClean());
                audio_ = null;
            }
            return audioBuilder_;
        }

        private im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions video_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions, im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptionsOrBuilder> videoBuilder_;

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         *
         * @return Whether the video field is set.
         */
        public boolean hasVideo() {
            return ((bitField0_ & 0x00000200) != 0);
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         *
         * @return The video.
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions getVideo() {
            if (videoBuilder_ == null) {
                return video_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                                .getDefaultInstance()
                        : video_;
            } else {
                return videoBuilder_.getMessage();
            }
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public Builder setVideo(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions value) {
            if (videoBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                video_ = value;
            } else {
                videoBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public Builder setVideo(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.Builder builderForValue) {
            if (videoBuilder_ == null) {
                video_ = builderForValue.build();
            } else {
                videoBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public Builder mergeVideo(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions value) {
            if (videoBuilder_ == null) {
                if (((bitField0_ & 0x00000200) != 0)
                        && video_ != null
                        && video_ != im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                                .getDefaultInstance()) {
                    getVideoBuilder().mergeFrom(value);
                } else {
                    video_ = value;
                }
            } else {
                videoBuilder_.mergeFrom(value);
            }
            if (video_ != null) {
                bitField0_ |= 0x00000200;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public Builder clearVideo() {
            bitField0_ &= ~0x00000200;
            video_ = null;
            if (videoBuilder_ != null) {
                videoBuilder_.dispose();
                videoBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.Builder getVideoBuilder() {
            bitField0_ |= 0x00000200;
            onChanged();
            return getVideoFieldBuilder().getBuilder();
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptionsOrBuilder getVideoOrBuilder() {
            if (videoBuilder_ != null) {
                return videoBuilder_.getMessageOrBuilder();
            } else {
                return video_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions
                                .getDefaultInstance()
                        : video_;
            }
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions, im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptionsOrBuilder> getVideoFieldBuilder() {
            if (videoBuilder_ == null) {
                videoBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getVideo(),
                        getParentForChildren(),
                        isClean());
                video_ = null;
            }
            return videoBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.UpdateIngressRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.UpdateIngressRequest)
    private static final im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateIngressRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateIngressRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateIngressRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateIngressRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.UpdateIngressRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}