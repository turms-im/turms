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
 * Protobuf type {@code livekit.IngressInfo}
 */
public final class IngressInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.IngressInfo)
        IngressInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressInfo.class.getName());
    }

    // Use IngressInfo.newBuilder() to construct.
    private IngressInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private IngressInfo() {
        ingressId_ = "";
        name_ = "";
        streamKey_ = "";
        url_ = "";
        inputType_ = 0;
        roomName_ = "";
        participantIdentity_ = "";
        participantName_ = "";
        participantMetadata_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.ingress.IngressInfo.class,
                        im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder.class);
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

    public static final int STREAM_KEY_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object streamKey_ = "";

    /**
     * <code>string stream_key = 3;</code>
     *
     * @return The streamKey.
     */
    @java.lang.Override
    public java.lang.String getStreamKey() {
        java.lang.Object ref = streamKey_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            streamKey_ = s;
            return s;
        }
    }

    /**
     * <code>string stream_key = 3;</code>
     *
     * @return The bytes for streamKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getStreamKeyBytes() {
        java.lang.Object ref = streamKey_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            streamKey_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int URL_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object url_ = "";

    /**
     * <pre>
     * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
     * </pre>
     *
     * <code>string url = 4;</code>
     *
     * @return The url.
     */
    @java.lang.Override
    public java.lang.String getUrl() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            url_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
     * </pre>
     *
     * <code>string url = 4;</code>
     *
     * @return The bytes for url.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getUrlBytes() {
        java.lang.Object ref = url_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            url_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int INPUT_TYPE_FIELD_NUMBER = 5;
    private int inputType_ = 0;

    /**
     * <pre>
     * for RTMP input, it'll be a rtmp:// URL
     * for FILE input, it'll be a http:// URL
     * for SRT input, it'll be a srt:// URL
     * </pre>
     *
     * <code>.livekit.IngressInput input_type = 5;</code>
     *
     * @return The enum numeric value on the wire for inputType.
     */
    @java.lang.Override
    public int getInputTypeValue() {
        return inputType_;
    }

    /**
     * <pre>
     * for RTMP input, it'll be a rtmp:// URL
     * for FILE input, it'll be a http:// URL
     * for SRT input, it'll be a srt:// URL
     * </pre>
     *
     * <code>.livekit.IngressInput input_type = 5;</code>
     *
     * @return The inputType.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressInput getInputType() {
        im.turms.plugin.livekit.core.proto.ingress.IngressInput result =
                im.turms.plugin.livekit.core.proto.ingress.IngressInput.forNumber(inputType_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressInput.UNRECOGNIZED
                : result;
    }

    public static final int BYPASS_TRANSCODING_FIELD_NUMBER = 13;
    private boolean bypassTranscoding_ = false;

    /**
     * <code>bool bypass_transcoding = 13 [deprecated = true];</code>
     *
     * @deprecated livekit.IngressInfo.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=120
     * @return The bypassTranscoding.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public boolean getBypassTranscoding() {
        return bypassTranscoding_;
    }

    public static final int ENABLE_TRANSCODING_FIELD_NUMBER = 15;
    private boolean enableTranscoding_ = false;

    /**
     * <code>optional bool enable_transcoding = 15;</code>
     *
     * @return Whether the enableTranscoding field is set.
     */
    @java.lang.Override
    public boolean hasEnableTranscoding() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional bool enable_transcoding = 15;</code>
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
        return ((bitField0_ & 0x00000002) != 0);
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
        return ((bitField0_ & 0x00000004) != 0);
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

    public static final int ROOM_NAME_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private volatile java.lang.Object roomName_ = "";

    /**
     * <code>string room_name = 8;</code>
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
     * <code>string room_name = 8;</code>
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

    public static final int PARTICIPANT_IDENTITY_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantIdentity_ = "";

    /**
     * <code>string participant_identity = 9;</code>
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
     * <code>string participant_identity = 9;</code>
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

    public static final int PARTICIPANT_NAME_FIELD_NUMBER = 10;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantName_ = "";

    /**
     * <code>string participant_name = 10;</code>
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
     * <code>string participant_name = 10;</code>
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

    public static final int PARTICIPANT_METADATA_FIELD_NUMBER = 14;
    @SuppressWarnings("serial")
    private volatile java.lang.Object participantMetadata_ = "";

    /**
     * <code>string participant_metadata = 14;</code>
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
     * <code>string participant_metadata = 14;</code>
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

    public static final int REUSABLE_FIELD_NUMBER = 11;
    private boolean reusable_ = false;

    /**
     * <code>bool reusable = 11;</code>
     *
     * @return The reusable.
     */
    @java.lang.Override
    public boolean getReusable() {
        return reusable_;
    }

    public static final int STATE_FIELD_NUMBER = 12;
    private im.turms.plugin.livekit.core.proto.ingress.IngressState state_;

    /**
     * <pre>
     * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
     * </pre>
     *
     * <code>.livekit.IngressState state = 12;</code>
     *
     * @return Whether the state field is set.
     */
    @java.lang.Override
    public boolean hasState() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <pre>
     * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
     * </pre>
     *
     * <code>.livekit.IngressState state = 12;</code>
     *
     * @return The state.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressState getState() {
        return state_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressState.getDefaultInstance()
                : state_;
    }

    /**
     * <pre>
     * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
     * </pre>
     *
     * <code>.livekit.IngressState state = 12;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressStateOrBuilder getStateOrBuilder() {
        return state_ == null
                ? im.turms.plugin.livekit.core.proto.ingress.IngressState.getDefaultInstance()
                : state_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(streamKey_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 3, streamKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, url_);
        }
        if (inputType_ != im.turms.plugin.livekit.core.proto.ingress.IngressInput.RTMP_INPUT
                .getNumber()) {
            output.writeEnum(5, inputType_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeMessage(6, getAudio());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeMessage(7, getVideo());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 8, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantIdentity_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, participantIdentity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantName_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 10, participantName_);
        }
        if (reusable_) {
            output.writeBool(11, reusable_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeMessage(12, getState());
        }
        if (bypassTranscoding_) {
            output.writeBool(13, bypassTranscoding_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantMetadata_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 14, participantMetadata_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeBool(15, enableTranscoding_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(streamKey_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(3, streamKey_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(url_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, url_);
        }
        if (inputType_ != im.turms.plugin.livekit.core.proto.ingress.IngressInput.RTMP_INPUT
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(5, inputType_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, getAudio());
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, getVideo());
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(roomName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(8, roomName_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantIdentity_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, participantIdentity_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantName_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(10, participantName_);
        }
        if (reusable_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(11, reusable_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(12, getState());
        }
        if (bypassTranscoding_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(13, bypassTranscoding_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(participantMetadata_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(14,
                    participantMetadata_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(15, enableTranscoding_);
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
        if (!(obj instanceof IngressInfo other)) {
            return super.equals(obj);
        }

        if (!getIngressId().equals(other.getIngressId())) {
            return false;
        }
        if (!getName().equals(other.getName())) {
            return false;
        }
        if (!getStreamKey().equals(other.getStreamKey())) {
            return false;
        }
        if (!getUrl().equals(other.getUrl())) {
            return false;
        }
        if (inputType_ != other.inputType_) {
            return false;
        }
        if (getBypassTranscoding() != other.getBypassTranscoding()) {
            return false;
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
        if (getReusable() != other.getReusable()) {
            return false;
        }
        if (hasState() != other.hasState()) {
            return false;
        }
        if (hasState()) {
            if (!getState().equals(other.getState())) {
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
        hash = (37 * hash) + STREAM_KEY_FIELD_NUMBER;
        hash = (53 * hash) + getStreamKey().hashCode();
        hash = (37 * hash) + URL_FIELD_NUMBER;
        hash = (53 * hash) + getUrl().hashCode();
        hash = (37 * hash) + INPUT_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + inputType_;
        hash = (37 * hash) + BYPASS_TRANSCODING_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getBypassTranscoding());
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
        hash = (37 * hash) + ROOM_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getRoomName().hashCode();
        hash = (37 * hash) + PARTICIPANT_IDENTITY_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantIdentity().hashCode();
        hash = (37 * hash) + PARTICIPANT_NAME_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantName().hashCode();
        hash = (37 * hash) + PARTICIPANT_METADATA_FIELD_NUMBER;
        hash = (53 * hash) + getParticipantMetadata().hashCode();
        hash = (37 * hash) + REUSABLE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getReusable());
        if (hasState()) {
            hash = (37 * hash) + STATE_FIELD_NUMBER;
            hash = (53 * hash) + getState().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.ingress.IngressInfo prototype) {
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
     * Protobuf type {@code livekit.IngressInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.IngressInfo)
            im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.ingress.IngressInfo.class,
                            im.turms.plugin.livekit.core.proto.ingress.IngressInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.ingress.IngressInfo.newBuilder()
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
                getStateFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            ingressId_ = "";
            name_ = "";
            streamKey_ = "";
            url_ = "";
            inputType_ = 0;
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
            roomName_ = "";
            participantIdentity_ = "";
            participantName_ = "";
            participantMetadata_ = "";
            reusable_ = false;
            state_ = null;
            if (stateBuilder_ != null) {
                stateBuilder_.dispose();
                stateBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.internal_static_livekit_IngressInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.ingress.IngressInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo build() {
            im.turms.plugin.livekit.core.proto.ingress.IngressInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.ingress.IngressInfo result =
                    new im.turms.plugin.livekit.core.proto.ingress.IngressInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.ingress.IngressInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.ingressId_ = ingressId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.name_ = name_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.streamKey_ = streamKey_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.url_ = url_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.inputType_ = inputType_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.bypassTranscoding_ = bypassTranscoding_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.enableTranscoding_ = enableTranscoding_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.audio_ = audioBuilder_ == null
                        ? audio_
                        : audioBuilder_.build();
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.video_ = videoBuilder_ == null
                        ? video_
                        : videoBuilder_.build();
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.roomName_ = roomName_;
            }
            if (((from_bitField0_ & 0x00000400) != 0)) {
                result.participantIdentity_ = participantIdentity_;
            }
            if (((from_bitField0_ & 0x00000800) != 0)) {
                result.participantName_ = participantName_;
            }
            if (((from_bitField0_ & 0x00001000) != 0)) {
                result.participantMetadata_ = participantMetadata_;
            }
            if (((from_bitField0_ & 0x00002000) != 0)) {
                result.reusable_ = reusable_;
            }
            if (((from_bitField0_ & 0x00004000) != 0)) {
                result.state_ = stateBuilder_ == null
                        ? state_
                        : stateBuilder_.build();
                to_bitField0_ |= 0x00000008;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.ingress.IngressInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.ingress.IngressInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.ingress.IngressInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.ingress.IngressInfo
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
            if (!other.getStreamKey()
                    .isEmpty()) {
                streamKey_ = other.streamKey_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (!other.getUrl()
                    .isEmpty()) {
                url_ = other.url_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.inputType_ != 0) {
                setInputTypeValue(other.getInputTypeValue());
            }
            if (other.getBypassTranscoding()) {
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
            if (!other.getRoomName()
                    .isEmpty()) {
                roomName_ = other.roomName_;
                bitField0_ |= 0x00000200;
                onChanged();
            }
            if (!other.getParticipantIdentity()
                    .isEmpty()) {
                participantIdentity_ = other.participantIdentity_;
                bitField0_ |= 0x00000400;
                onChanged();
            }
            if (!other.getParticipantName()
                    .isEmpty()) {
                participantName_ = other.participantName_;
                bitField0_ |= 0x00000800;
                onChanged();
            }
            if (!other.getParticipantMetadata()
                    .isEmpty()) {
                participantMetadata_ = other.participantMetadata_;
                bitField0_ |= 0x00001000;
                onChanged();
            }
            if (other.getReusable()) {
                setReusable(other.getReusable());
            }
            if (other.hasState()) {
                mergeState(other.getState());
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
                            streamKey_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            url_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 40 -> {
                            inputType_ = input.readEnum();
                            bitField0_ |= 0x00000010;
                        } // case 40
                        case 50 -> {
                            input.readMessage(getAudioFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000080;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getVideoFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000100;
                        } // case 58
                        case 66 -> {
                            roomName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000200;
                        } // case 66
                        case 74 -> {
                            participantIdentity_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000400;
                        } // case 74
                        case 82 -> {
                            participantName_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000800;
                        } // case 82
                        case 88 -> {
                            reusable_ = input.readBool();
                            bitField0_ |= 0x00002000;
                        } // case 88
                        case 98 -> {
                            input.readMessage(getStateFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00004000;
                        } // case 98
                        case 104 -> {
                            bypassTranscoding_ = input.readBool();
                            bitField0_ |= 0x00000020;
                        } // case 104
                        case 114 -> {
                            participantMetadata_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00001000;
                        } // case 114
                        case 120 -> {
                            enableTranscoding_ = input.readBool();
                            bitField0_ |= 0x00000040;
                        } // case 120
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

        private java.lang.Object streamKey_ = "";

        /**
         * <code>string stream_key = 3;</code>
         *
         * @return The streamKey.
         */
        public java.lang.String getStreamKey() {
            java.lang.Object ref = streamKey_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                streamKey_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string stream_key = 3;</code>
         *
         * @return The bytes for streamKey.
         */
        public com.google.protobuf.ByteString getStreamKeyBytes() {
            java.lang.Object ref = streamKey_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                streamKey_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string stream_key = 3;</code>
         *
         * @param value The streamKey to set.
         * @return This builder for chaining.
         */
        public Builder setStreamKey(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            streamKey_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string stream_key = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStreamKey() {
            streamKey_ = getDefaultInstance().getStreamKey();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>string stream_key = 3;</code>
         *
         * @param value The bytes for streamKey to set.
         * @return This builder for chaining.
         */
        public Builder setStreamKeyBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            streamKey_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object url_ = "";

        /**
         * <pre>
         * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
         * </pre>
         *
         * <code>string url = 4;</code>
         *
         * @return The url.
         */
        public java.lang.String getUrl() {
            java.lang.Object ref = url_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                url_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
         * </pre>
         *
         * <code>string url = 4;</code>
         *
         * @return The bytes for url.
         */
        public com.google.protobuf.ByteString getUrlBytes() {
            java.lang.Object ref = url_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                url_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
         * </pre>
         *
         * <code>string url = 4;</code>
         *
         * @param value The url to set.
         * @return This builder for chaining.
         */
        public Builder setUrl(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            url_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
         * </pre>
         *
         * <code>string url = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUrl() {
            url_ = getDefaultInstance().getUrl();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
         * </pre>
         *
         * <code>string url = 4;</code>
         *
         * @param value The bytes for url to set.
         * @return This builder for chaining.
         */
        public Builder setUrlBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            url_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private int inputType_ = 0;

        /**
         * <pre>
         * for RTMP input, it'll be a rtmp:// URL
         * for FILE input, it'll be a http:// URL
         * for SRT input, it'll be a srt:// URL
         * </pre>
         *
         * <code>.livekit.IngressInput input_type = 5;</code>
         *
         * @return The enum numeric value on the wire for inputType.
         */
        @java.lang.Override
        public int getInputTypeValue() {
            return inputType_;
        }

        /**
         * <pre>
         * for RTMP input, it'll be a rtmp:// URL
         * for FILE input, it'll be a http:// URL
         * for SRT input, it'll be a srt:// URL
         * </pre>
         *
         * <code>.livekit.IngressInput input_type = 5;</code>
         *
         * @param value The enum numeric value on the wire for inputType to set.
         * @return This builder for chaining.
         */
        public Builder setInputTypeValue(int value) {
            inputType_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * for RTMP input, it'll be a rtmp:// URL
         * for FILE input, it'll be a http:// URL
         * for SRT input, it'll be a srt:// URL
         * </pre>
         *
         * <code>.livekit.IngressInput input_type = 5;</code>
         *
         * @return The inputType.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.ingress.IngressInput getInputType() {
            im.turms.plugin.livekit.core.proto.ingress.IngressInput result =
                    im.turms.plugin.livekit.core.proto.ingress.IngressInput.forNumber(inputType_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.ingress.IngressInput.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * for RTMP input, it'll be a rtmp:// URL
         * for FILE input, it'll be a http:// URL
         * for SRT input, it'll be a srt:// URL
         * </pre>
         *
         * <code>.livekit.IngressInput input_type = 5;</code>
         *
         * @param value The inputType to set.
         * @return This builder for chaining.
         */
        public Builder setInputType(im.turms.plugin.livekit.core.proto.ingress.IngressInput value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000010;
            inputType_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * for RTMP input, it'll be a rtmp:// URL
         * for FILE input, it'll be a http:// URL
         * for SRT input, it'll be a srt:// URL
         * </pre>
         *
         * <code>.livekit.IngressInput input_type = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearInputType() {
            bitField0_ &= ~0x00000010;
            inputType_ = 0;
            onChanged();
            return this;
        }

        private boolean bypassTranscoding_;

        /**
         * <code>bool bypass_transcoding = 13 [deprecated = true];</code>
         *
         * @deprecated livekit.IngressInfo.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=120
         * @return The bypassTranscoding.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public boolean getBypassTranscoding() {
            return bypassTranscoding_;
        }

        /**
         * <code>bool bypass_transcoding = 13 [deprecated = true];</code>
         *
         * @deprecated livekit.IngressInfo.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=120
         * @param value The bypassTranscoding to set.
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder setBypassTranscoding(boolean value) {

            bypassTranscoding_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>bool bypass_transcoding = 13 [deprecated = true];</code>
         *
         * @deprecated livekit.IngressInfo.bypass_transcoding is deprecated. See
         *             livekit_ingress.proto;l=120
         * @return This builder for chaining.
         */
        @java.lang.Deprecated
        public Builder clearBypassTranscoding() {
            bitField0_ &= ~0x00000020;
            bypassTranscoding_ = false;
            onChanged();
            return this;
        }

        private boolean enableTranscoding_;

        /**
         * <code>optional bool enable_transcoding = 15;</code>
         *
         * @return Whether the enableTranscoding field is set.
         */
        @java.lang.Override
        public boolean hasEnableTranscoding() {
            return ((bitField0_ & 0x00000040) != 0);
        }

        /**
         * <code>optional bool enable_transcoding = 15;</code>
         *
         * @return The enableTranscoding.
         */
        @java.lang.Override
        public boolean getEnableTranscoding() {
            return enableTranscoding_;
        }

        /**
         * <code>optional bool enable_transcoding = 15;</code>
         *
         * @param value The enableTranscoding to set.
         * @return This builder for chaining.
         */
        public Builder setEnableTranscoding(boolean value) {

            enableTranscoding_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional bool enable_transcoding = 15;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEnableTranscoding() {
            bitField0_ &= ~0x00000040;
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
            return ((bitField0_ & 0x00000080) != 0);
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
            bitField0_ |= 0x00000080;
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
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public Builder mergeAudio(
                im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions value) {
            if (audioBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)
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
                bitField0_ |= 0x00000080;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.IngressAudioOptions audio = 6;</code>
         */
        public Builder clearAudio() {
            bitField0_ &= ~0x00000080;
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
            bitField0_ |= 0x00000080;
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
            return ((bitField0_ & 0x00000100) != 0);
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
            bitField0_ |= 0x00000100;
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
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public Builder mergeVideo(
                im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions value) {
            if (videoBuilder_ == null) {
                if (((bitField0_ & 0x00000100) != 0)
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
                bitField0_ |= 0x00000100;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.livekit.IngressVideoOptions video = 7;</code>
         */
        public Builder clearVideo() {
            bitField0_ &= ~0x00000100;
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
            bitField0_ |= 0x00000100;
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

        private java.lang.Object roomName_ = "";

        /**
         * <code>string room_name = 8;</code>
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
         * <code>string room_name = 8;</code>
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
         * <code>string room_name = 8;</code>
         *
         * @param value The roomName to set.
         * @return This builder for chaining.
         */
        public Builder setRoomName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            roomName_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>string room_name = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRoomName() {
            roomName_ = getDefaultInstance().getRoomName();
            bitField0_ &= ~0x00000200;
            onChanged();
            return this;
        }

        /**
         * <code>string room_name = 8;</code>
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
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        private java.lang.Object participantIdentity_ = "";

        /**
         * <code>string participant_identity = 9;</code>
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
         * <code>string participant_identity = 9;</code>
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
         * <code>string participant_identity = 9;</code>
         *
         * @param value The participantIdentity to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantIdentity(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantIdentity_ = value;
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_identity = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantIdentity() {
            participantIdentity_ = getDefaultInstance().getParticipantIdentity();
            bitField0_ &= ~0x00000400;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_identity = 9;</code>
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
            bitField0_ |= 0x00000400;
            onChanged();
            return this;
        }

        private java.lang.Object participantName_ = "";

        /**
         * <code>string participant_name = 10;</code>
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
         * <code>string participant_name = 10;</code>
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
         * <code>string participant_name = 10;</code>
         *
         * @param value The participantName to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantName_ = value;
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_name = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantName() {
            participantName_ = getDefaultInstance().getParticipantName();
            bitField0_ &= ~0x00000800;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_name = 10;</code>
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
            bitField0_ |= 0x00000800;
            onChanged();
            return this;
        }

        private java.lang.Object participantMetadata_ = "";

        /**
         * <code>string participant_metadata = 14;</code>
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
         * <code>string participant_metadata = 14;</code>
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
         * <code>string participant_metadata = 14;</code>
         *
         * @param value The participantMetadata to set.
         * @return This builder for chaining.
         */
        public Builder setParticipantMetadata(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            participantMetadata_ = value;
            bitField0_ |= 0x00001000;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_metadata = 14;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearParticipantMetadata() {
            participantMetadata_ = getDefaultInstance().getParticipantMetadata();
            bitField0_ &= ~0x00001000;
            onChanged();
            return this;
        }

        /**
         * <code>string participant_metadata = 14;</code>
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
            bitField0_ |= 0x00001000;
            onChanged();
            return this;
        }

        private boolean reusable_;

        /**
         * <code>bool reusable = 11;</code>
         *
         * @return The reusable.
         */
        @java.lang.Override
        public boolean getReusable() {
            return reusable_;
        }

        /**
         * <code>bool reusable = 11;</code>
         *
         * @param value The reusable to set.
         * @return This builder for chaining.
         */
        public Builder setReusable(boolean value) {

            reusable_ = value;
            bitField0_ |= 0x00002000;
            onChanged();
            return this;
        }

        /**
         * <code>bool reusable = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReusable() {
            bitField0_ &= ~0x00002000;
            reusable_ = false;
            onChanged();
            return this;
        }

        private im.turms.plugin.livekit.core.proto.ingress.IngressState state_;
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressState, im.turms.plugin.livekit.core.proto.ingress.IngressState.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressStateOrBuilder> stateBuilder_;

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         *
         * @return Whether the state field is set.
         */
        public boolean hasState() {
            return ((bitField0_ & 0x00004000) != 0);
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         *
         * @return The state.
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressState getState() {
            if (stateBuilder_ == null) {
                return state_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressState
                                .getDefaultInstance()
                        : state_;
            } else {
                return stateBuilder_.getMessage();
            }
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        public Builder setState(im.turms.plugin.livekit.core.proto.ingress.IngressState value) {
            if (stateBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                state_ = value;
            } else {
                stateBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00004000;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        public Builder setState(
                im.turms.plugin.livekit.core.proto.ingress.IngressState.Builder builderForValue) {
            if (stateBuilder_ == null) {
                state_ = builderForValue.build();
            } else {
                stateBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00004000;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        public Builder mergeState(im.turms.plugin.livekit.core.proto.ingress.IngressState value) {
            if (stateBuilder_ == null) {
                if (((bitField0_ & 0x00004000) != 0)
                        && state_ != null
                        && state_ != im.turms.plugin.livekit.core.proto.ingress.IngressState
                                .getDefaultInstance()) {
                    getStateBuilder().mergeFrom(value);
                } else {
                    state_ = value;
                }
            } else {
                stateBuilder_.mergeFrom(value);
            }
            if (state_ != null) {
                bitField0_ |= 0x00004000;
                onChanged();
            }
            return this;
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        public Builder clearState() {
            bitField0_ &= ~0x00004000;
            state_ = null;
            if (stateBuilder_ != null) {
                stateBuilder_.dispose();
                stateBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressState.Builder getStateBuilder() {
            bitField0_ |= 0x00004000;
            onChanged();
            return getStateFieldBuilder().getBuilder();
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        public im.turms.plugin.livekit.core.proto.ingress.IngressStateOrBuilder getStateOrBuilder() {
            if (stateBuilder_ != null) {
                return stateBuilder_.getMessageOrBuilder();
            } else {
                return state_ == null
                        ? im.turms.plugin.livekit.core.proto.ingress.IngressState
                                .getDefaultInstance()
                        : state_;
            }
        }

        /**
         * <pre>
         * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
         * </pre>
         *
         * <code>.livekit.IngressState state = 12;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<im.turms.plugin.livekit.core.proto.ingress.IngressState, im.turms.plugin.livekit.core.proto.ingress.IngressState.Builder, im.turms.plugin.livekit.core.proto.ingress.IngressStateOrBuilder> getStateFieldBuilder() {
            if (stateBuilder_ == null) {
                stateBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        getState(),
                        getParentForChildren(),
                        isClean());
                state_ = null;
            }
            return stateBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.IngressInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.IngressInfo)
    private static final im.turms.plugin.livekit.core.proto.ingress.IngressInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.ingress.IngressInfo();
    }

    public static im.turms.plugin.livekit.core.proto.ingress.IngressInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<IngressInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public IngressInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<IngressInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<IngressInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.ingress.IngressInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}