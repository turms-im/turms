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

public interface RoomEgressOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.RoomEgress)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
     *
     * @return Whether the room field is set.
     */
    boolean hasRoom();

    /**
     * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
     *
     * @return The room.
     */
    im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getRoom();

    /**
     * <code>.livekit.RoomCompositeEgressRequest room = 1;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder getRoomOrBuilder();

    /**
     * <code>.livekit.AutoParticipantEgress participant = 3;</code>
     *
     * @return Whether the participant field is set.
     */
    boolean hasParticipant();

    /**
     * <code>.livekit.AutoParticipantEgress participant = 3;</code>
     *
     * @return The participant.
     */
    im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgress getParticipant();

    /**
     * <code>.livekit.AutoParticipantEgress participant = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.AutoParticipantEgressOrBuilder getParticipantOrBuilder();

    /**
     * <code>.livekit.AutoTrackEgress tracks = 2;</code>
     *
     * @return Whether the tracks field is set.
     */
    boolean hasTracks();

    /**
     * <code>.livekit.AutoTrackEgress tracks = 2;</code>
     *
     * @return The tracks.
     */
    im.turms.plugin.livekit.core.proto.egress.AutoTrackEgress getTracks();

    /**
     * <code>.livekit.AutoTrackEgress tracks = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.AutoTrackEgressOrBuilder getTracksOrBuilder();
}
