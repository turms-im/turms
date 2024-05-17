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

public interface UpdateIngressRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.UpdateIngressRequest)
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
     * <code>string room_name = 3;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <code>string room_name = 3;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <code>string participant_identity = 4;</code>
     *
     * @return The participantIdentity.
     */
    java.lang.String getParticipantIdentity();

    /**
     * <code>string participant_identity = 4;</code>
     *
     * @return The bytes for participantIdentity.
     */
    com.google.protobuf.ByteString getParticipantIdentityBytes();

    /**
     * <code>string participant_name = 5;</code>
     *
     * @return The participantName.
     */
    java.lang.String getParticipantName();

    /**
     * <code>string participant_name = 5;</code>
     *
     * @return The bytes for participantName.
     */
    com.google.protobuf.ByteString getParticipantNameBytes();

    /**
     * <code>string participant_metadata = 9;</code>
     *
     * @return The participantMetadata.
     */
    java.lang.String getParticipantMetadata();

    /**
     * <code>string participant_metadata = 9;</code>
     *
     * @return The bytes for participantMetadata.
     */
    com.google.protobuf.ByteString getParticipantMetadataBytes();

    /**
     * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=179
     * @return Whether the bypassTranscoding field is set.
     */
    @java.lang.Deprecated
    boolean hasBypassTranscoding();

    /**
     * <code>optional bool bypass_transcoding = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.UpdateIngressRequest.bypass_transcoding is deprecated. See
     *             livekit_ingress.proto;l=179
     * @return The bypassTranscoding.
     */
    @java.lang.Deprecated
    boolean getBypassTranscoding();

    /**
     * <code>optional bool enable_transcoding = 10;</code>
     *
     * @return Whether the enableTranscoding field is set.
     */
    boolean hasEnableTranscoding();

    /**
     * <code>optional bool enable_transcoding = 10;</code>
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
