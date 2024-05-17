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

public interface CreateIngressRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.CreateIngressRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.IngressInput input_type = 1;</code>
     *
     * @return The enum numeric value on the wire for inputType.
     */
    int getInputTypeValue();

    /**
     * <code>.livekit.IngressInput input_type = 1;</code>
     *
     * @return The inputType.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressInput getInputType();

    /**
     * <pre>
     * Where to pull media from, only for URL input type
     * </pre>
     *
     * <code>string url = 9;</code>
     *
     * @return The url.
     */
    java.lang.String getUrl();

    /**
     * <pre>
     * Where to pull media from, only for URL input type
     * </pre>
     *
     * <code>string url = 9;</code>
     *
     * @return The bytes for url.
     */
    com.google.protobuf.ByteString getUrlBytes();

    /**
     * <pre>
     * User provided identifier for the ingress
     * </pre>
     *
     * <code>string name = 2;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <pre>
     * User provided identifier for the ingress
     * </pre>
     *
     * <code>string name = 2;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <pre>
     * room to publish to
     * </pre>
     *
     * <code>string room_name = 3;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <pre>
     * room to publish to
     * </pre>
     *
     * <code>string room_name = 3;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <pre>
     * publish as participant
     * </pre>
     *
     * <code>string participant_identity = 4;</code>
     *
     * @return The participantIdentity.
     */
    java.lang.String getParticipantIdentity();

    /**
     * <pre>
     * publish as participant
     * </pre>
     *
     * <code>string participant_identity = 4;</code>
     *
     * @return The bytes for participantIdentity.
     */
    com.google.protobuf.ByteString getParticipantIdentityBytes();

    /**
     * <pre>
     * name of publishing participant (used for display only)
     * </pre>
     *
     * <code>string participant_name = 5;</code>
     *
     * @return The participantName.
     */
    java.lang.String getParticipantName();

    /**
     * <pre>
     * name of publishing participant (used for display only)
     * </pre>
     *
     * <code>string participant_name = 5;</code>
     *
     * @return The bytes for participantName.
     */
    com.google.protobuf.ByteString getParticipantNameBytes();

    /**
     * <pre>
     * metadata associated with the publishing participant
     * </pre>
     *
     * <code>string participant_metadata = 10;</code>
     *
     * @return The participantMetadata.
     */
    java.lang.String getParticipantMetadata();

    /**
     * <pre>
     * metadata associated with the publishing participant
     * </pre>
     *
     * <code>string participant_metadata = 10;</code>
     *
     * @return The bytes for participantMetadata.
     */
    com.google.protobuf.ByteString getParticipantMetadataBytes();

    /**
     * <pre>
     * [depreacted ] whether to pass through the incoming media without transcoding, only compatible with some input types. Use `enable_transcoding` instead.
     * </pre>
     *
     * <code>bool bypass_transcoding = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.CreateIngressRequest.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=42
     * @return The bypassTranscoding.
     */
    @java.lang.Deprecated
    boolean getBypassTranscoding();

    /**
     * <pre>
     * Whether to transcode the ingested media. Only WHIP supports disabling transcoding currently. WHIP will default to transcoding disabled. Replaces `bypass_transcoding.
     * </pre>
     *
     * <code>optional bool enable_transcoding = 11;</code>
     *
     * @return Whether the enableTranscoding field is set.
     */
    boolean hasEnableTranscoding();

    /**
     * <pre>
     * Whether to transcode the ingested media. Only WHIP supports disabling transcoding currently. WHIP will default to transcoding disabled. Replaces `bypass_transcoding.
     * </pre>
     *
     * <code>optional bool enable_transcoding = 11;</code>
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
}
