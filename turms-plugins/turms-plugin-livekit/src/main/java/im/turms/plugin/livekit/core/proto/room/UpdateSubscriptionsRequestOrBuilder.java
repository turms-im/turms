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

public interface UpdateSubscriptionsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.UpdateSubscriptionsRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string room = 1;</code>
     *
     * @return The room.
     */
    java.lang.String getRoom();

    /**
     * <code>string room = 1;</code>
     *
     * @return The bytes for room.
     */
    com.google.protobuf.ByteString getRoomBytes();

    /**
     * <code>string identity = 2;</code>
     *
     * @return The identity.
     */
    java.lang.String getIdentity();

    /**
     * <code>string identity = 2;</code>
     *
     * @return The bytes for identity.
     */
    com.google.protobuf.ByteString getIdentityBytes();

    /**
     * <pre>
     * list of sids of tracks
     * </pre>
     *
     * <code>repeated string track_sids = 3;</code>
     *
     * @return A list containing the trackSids.
     */
    java.util.List<java.lang.String> getTrackSidsList();

    /**
     * <pre>
     * list of sids of tracks
     * </pre>
     *
     * <code>repeated string track_sids = 3;</code>
     *
     * @return The count of trackSids.
     */
    int getTrackSidsCount();

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
    java.lang.String getTrackSids(int index);

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
    com.google.protobuf.ByteString getTrackSidsBytes(int index);

    /**
     * <pre>
     * set to true to subscribe, false to unsubscribe from tracks
     * </pre>
     *
     * <code>bool subscribe = 4;</code>
     *
     * @return The subscribe.
     */
    boolean getSubscribe();

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.ParticipantTracks> getParticipantTracksList();

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantTracks getParticipantTracks(int index);

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    int getParticipantTracksCount();

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder> getParticipantTracksOrBuilderList();

    /**
     * <pre>
     * list of participants and their tracks
     * </pre>
     *
     * <code>repeated .livekit.ParticipantTracks participant_tracks = 5;</code>
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantTracksOrBuilder getParticipantTracksOrBuilder(
            int index);
}
