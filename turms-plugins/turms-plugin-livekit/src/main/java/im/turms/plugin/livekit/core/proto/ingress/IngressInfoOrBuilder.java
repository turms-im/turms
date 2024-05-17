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

public interface IngressInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.IngressInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string ingress_id = 1;</code>
     *
     * @return The ingressId.
     */
    java.lang.String getIngressId();

    /**
     * <code>string ingress_id = 1;</code>
     *
     * @return The bytes for ingressId.
     */
    com.google.protobuf.ByteString getIngressIdBytes();

    /**
     * <code>string name = 2;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 2;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>string stream_key = 3;</code>
     *
     * @return The streamKey.
     */
    java.lang.String getStreamKey();

    /**
     * <code>string stream_key = 3;</code>
     *
     * @return The bytes for streamKey.
     */
    com.google.protobuf.ByteString getStreamKeyBytes();

    /**
     * <pre>
     * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
     * </pre>
     *
     * <code>string url = 4;</code>
     *
     * @return The url.
     */
    java.lang.String getUrl();

    /**
     * <pre>
     * URL to point the encoder to for push (RTMP, WHIP), or location to pull media from for pull (URL)
     * </pre>
     *
     * <code>string url = 4;</code>
     *
     * @return The bytes for url.
     */
    com.google.protobuf.ByteString getUrlBytes();

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
    int getInputTypeValue();

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
    im.turms.plugin.livekit.core.proto.ingress.IngressInput getInputType();

    /**
     * <code>bool bypass_transcoding = 13 [deprecated = true];</code>
     *
     * @deprecated livekit.IngressInfo.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=120
     * @return The bypassTranscoding.
     */
    @java.lang.Deprecated
    boolean getBypassTranscoding();

    /**
     * <code>optional bool enable_transcoding = 15;</code>
     *
     * @return Whether the enableTranscoding field is set.
     */
    boolean hasEnableTranscoding();

    /**
     * <code>optional bool enable_transcoding = 15;</code>
     *
     * @return The enableTranscoding.
     */
    boolean getEnableTranscoding();

    /**
     * <code>.livekit.IngressAudioOptions audio = 6;</code>
     *
     * @return Whether the audio field is set.
     */
    boolean hasAudio();

    /**
     * <code>.livekit.IngressAudioOptions audio = 6;</code>
     *
     * @return The audio.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptions getAudio();

    /**
     * <code>.livekit.IngressAudioOptions audio = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressAudioOptionsOrBuilder getAudioOrBuilder();

    /**
     * <code>.livekit.IngressVideoOptions video = 7;</code>
     *
     * @return Whether the video field is set.
     */
    boolean hasVideo();

    /**
     * <code>.livekit.IngressVideoOptions video = 7;</code>
     *
     * @return The video.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptions getVideo();

    /**
     * <code>.livekit.IngressVideoOptions video = 7;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressVideoOptionsOrBuilder getVideoOrBuilder();

    /**
     * <code>string room_name = 8;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <code>string room_name = 8;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <code>string participant_identity = 9;</code>
     *
     * @return The participantIdentity.
     */
    java.lang.String getParticipantIdentity();

    /**
     * <code>string participant_identity = 9;</code>
     *
     * @return The bytes for participantIdentity.
     */
    com.google.protobuf.ByteString getParticipantIdentityBytes();

    /**
     * <code>string participant_name = 10;</code>
     *
     * @return The participantName.
     */
    java.lang.String getParticipantName();

    /**
     * <code>string participant_name = 10;</code>
     *
     * @return The bytes for participantName.
     */
    com.google.protobuf.ByteString getParticipantNameBytes();

    /**
     * <code>string participant_metadata = 14;</code>
     *
     * @return The participantMetadata.
     */
    java.lang.String getParticipantMetadata();

    /**
     * <code>string participant_metadata = 14;</code>
     *
     * @return The bytes for participantMetadata.
     */
    com.google.protobuf.ByteString getParticipantMetadataBytes();

    /**
     * <code>bool reusable = 11;</code>
     *
     * @return The reusable.
     */
    boolean getReusable();

    /**
     * <pre>
     * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
     * </pre>
     *
     * <code>.livekit.IngressState state = 12;</code>
     *
     * @return Whether the state field is set.
     */
    boolean hasState();

    /**
     * <pre>
     * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
     * </pre>
     *
     * <code>.livekit.IngressState state = 12;</code>
     *
     * @return The state.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressState getState();

    /**
     * <pre>
     * Description of error/stream non compliance and debug info for publisher otherwise (received bitrate, resolution, bandwidth)
     * </pre>
     *
     * <code>.livekit.IngressState state = 12;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressStateOrBuilder getStateOrBuilder();
}
