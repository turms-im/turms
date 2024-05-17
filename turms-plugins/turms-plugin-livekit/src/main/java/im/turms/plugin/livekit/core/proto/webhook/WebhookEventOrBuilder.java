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

package im.turms.plugin.livekit.core.proto.webhook;

public interface WebhookEventOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.WebhookEvent)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * one of room_started, room_finished, participant_joined, participant_left,
     * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
     * ingress_started, ingress_ended
     * </pre>
     *
     * <code>string event = 1;</code>
     *
     * @return The event.
     */
    java.lang.String getEvent();

    /**
     * <pre>
     * one of room_started, room_finished, participant_joined, participant_left,
     * track_published, track_unpublished, egress_started, egress_updated, egress_ended,
     * ingress_started, ingress_ended
     * </pre>
     *
     * <code>string event = 1;</code>
     *
     * @return The bytes for event.
     */
    com.google.protobuf.ByteString getEventBytes();

    /**
     * <code>.livekit.Room room = 2;</code>
     *
     * @return Whether the room field is set.
     */
    boolean hasRoom();

    /**
     * <code>.livekit.Room room = 2;</code>
     *
     * @return The room.
     */
    im.turms.plugin.livekit.core.proto.models.Room getRoom();

    /**
     * <code>.livekit.Room room = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.models.RoomOrBuilder getRoomOrBuilder();

    /**
     * <pre>
     * set when event is participant_* or track_*
     * </pre>
     *
     * <code>.livekit.ParticipantInfo participant = 3;</code>
     *
     * @return Whether the participant field is set.
     */
    boolean hasParticipant();

    /**
     * <pre>
     * set when event is participant_* or track_*
     * </pre>
     *
     * <code>.livekit.ParticipantInfo participant = 3;</code>
     *
     * @return The participant.
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantInfo getParticipant();

    /**
     * <pre>
     * set when event is participant_* or track_*
     * </pre>
     *
     * <code>.livekit.ParticipantInfo participant = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantInfoOrBuilder getParticipantOrBuilder();

    /**
     * <pre>
     * set when event is egress_*
     * </pre>
     *
     * <code>.livekit.EgressInfo egress_info = 9;</code>
     *
     * @return Whether the egressInfo field is set.
     */
    boolean hasEgressInfo();

    /**
     * <pre>
     * set when event is egress_*
     * </pre>
     *
     * <code>.livekit.EgressInfo egress_info = 9;</code>
     *
     * @return The egressInfo.
     */
    im.turms.plugin.livekit.core.proto.egress.EgressInfo getEgressInfo();

    /**
     * <pre>
     * set when event is egress_*
     * </pre>
     *
     * <code>.livekit.EgressInfo egress_info = 9;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.EgressInfoOrBuilder getEgressInfoOrBuilder();

    /**
     * <pre>
     * set when event is ingress_*
     * </pre>
     *
     * <code>.livekit.IngressInfo ingress_info = 10;</code>
     *
     * @return Whether the ingressInfo field is set.
     */
    boolean hasIngressInfo();

    /**
     * <pre>
     * set when event is ingress_*
     * </pre>
     *
     * <code>.livekit.IngressInfo ingress_info = 10;</code>
     *
     * @return The ingressInfo.
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressInfo getIngressInfo();

    /**
     * <pre>
     * set when event is ingress_*
     * </pre>
     *
     * <code>.livekit.IngressInfo ingress_info = 10;</code>
     */
    im.turms.plugin.livekit.core.proto.ingress.IngressInfoOrBuilder getIngressInfoOrBuilder();

    /**
     * <pre>
     * set when event is track_*
     * </pre>
     *
     * <code>.livekit.TrackInfo track = 8;</code>
     *
     * @return Whether the track field is set.
     */
    boolean hasTrack();

    /**
     * <pre>
     * set when event is track_*
     * </pre>
     *
     * <code>.livekit.TrackInfo track = 8;</code>
     *
     * @return The track.
     */
    im.turms.plugin.livekit.core.proto.models.TrackInfo getTrack();

    /**
     * <pre>
     * set when event is track_*
     * </pre>
     *
     * <code>.livekit.TrackInfo track = 8;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTrackOrBuilder();

    /**
     * <pre>
     * unique event uuid
     * </pre>
     *
     * <code>string id = 6;</code>
     *
     * @return The id.
     */
    java.lang.String getId();

    /**
     * <pre>
     * unique event uuid
     * </pre>
     *
     * <code>string id = 6;</code>
     *
     * @return The bytes for id.
     */
    com.google.protobuf.ByteString getIdBytes();

    /**
     * <pre>
     * timestamp in seconds
     * </pre>
     *
     * <code>int64 created_at = 7;</code>
     *
     * @return The createdAt.
     */
    long getCreatedAt();

    /**
     * <code>int32 num_dropped = 11;</code>
     *
     * @return The numDropped.
     */
    int getNumDropped();
}
