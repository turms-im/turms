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

public interface IngressStateOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.IngressState)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.IngressState.Status status = 1;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    int getStatusValue();

    /**
     * <code>.livekit.IngressState.Status status = 1;</code>
     *
     * @return The status.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressState.Status getStatus();

    /**
     * <pre>
     * Error/non compliance description if any
     * </pre>
     *
     * <code>string error = 2;</code>
     *
     * @return The error.
     */
    java.lang.String getError();

    /**
     * <pre>
     * Error/non compliance description if any
     * </pre>
     *
     * <code>string error = 2;</code>
     *
     * @return The bytes for error.
     */
    com.google.protobuf.ByteString getErrorBytes();

    /**
     * <code>.livekit.InputVideoState video = 3;</code>
     *
     * @return Whether the video field is set.
     */
    boolean hasVideo();

    /**
     * <code>.livekit.InputVideoState video = 3;</code>
     *
     * @return The video.
     */
    im.turms.plugin.livekit.core.proto.ingress.InputVideoState getVideo();

    /**
     * <code>.livekit.InputVideoState video = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.InputVideoStateOrBuilder getVideoOrBuilder();

    /**
     * <code>.livekit.InputAudioState audio = 4;</code>
     *
     * @return Whether the audio field is set.
     */
    boolean hasAudio();

    /**
     * <code>.livekit.InputAudioState audio = 4;</code>
     *
     * @return The audio.
     */
    im.turms.plugin.livekit.core.proto.ingress.InputAudioState getAudio();

    /**
     * <code>.livekit.InputAudioState audio = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.InputAudioStateOrBuilder getAudioOrBuilder();

    /**
     * <pre>
     * ID of the current/previous room published to
     * </pre>
     *
     * <code>string room_id = 5;</code>
     *
     * @return The roomId.
     */
    java.lang.String getRoomId();

    /**
     * <pre>
     * ID of the current/previous room published to
     * </pre>
     *
     * <code>string room_id = 5;</code>
     *
     * @return The bytes for roomId.
     */
    com.google.protobuf.ByteString getRoomIdBytes();

    /**
     * <code>int64 started_at = 7;</code>
     *
     * @return The startedAt.
     */
    long getStartedAt();

    /**
     * <code>int64 ended_at = 8;</code>
     *
     * @return The endedAt.
     */
    long getEndedAt();

    /**
     * <code>int64 updated_at = 10;</code>
     *
     * @return The updatedAt.
     */
    long getUpdatedAt();

    /**
     * <code>string resource_id = 9;</code>
     *
     * @return The resourceId.
     */
    java.lang.String getResourceId();

    /**
     * <code>string resource_id = 9;</code>
     *
     * @return The bytes for resourceId.
     */
    com.google.protobuf.ByteString getResourceIdBytes();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> getTracksList();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TrackInfo getTracks(int index);

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    int getTracksCount();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksOrBuilderList();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTracksOrBuilder(int index);
}
