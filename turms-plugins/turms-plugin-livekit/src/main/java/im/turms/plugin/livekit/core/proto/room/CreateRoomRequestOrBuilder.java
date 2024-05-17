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

public interface CreateRoomRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.CreateRoomRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string name = 1;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string name = 1;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <pre>
     * number of seconds to keep the room open if no one joins
     * </pre>
     *
     * <code>uint32 empty_timeout = 2;</code>
     *
     * @return The emptyTimeout.
     */
    int getEmptyTimeout();

    /**
     * <pre>
     * number of seconds to keep the room open after everyone leaves
     * </pre>
     *
     * <code>uint32 departure_timeout = 10;</code>
     *
     * @return The departureTimeout.
     */
    int getDepartureTimeout();

    /**
     * <pre>
     * limit number of participants that can be in a room
     * </pre>
     *
     * <code>uint32 max_participants = 3;</code>
     *
     * @return The maxParticipants.
     */
    int getMaxParticipants();

    /**
     * <pre>
     * override the node room is allocated to, for debugging
     * </pre>
     *
     * <code>string node_id = 4;</code>
     *
     * @return The nodeId.
     */
    java.lang.String getNodeId();

    /**
     * <pre>
     * override the node room is allocated to, for debugging
     * </pre>
     *
     * <code>string node_id = 4;</code>
     *
     * @return The bytes for nodeId.
     */
    com.google.protobuf.ByteString getNodeIdBytes();

    /**
     * <pre>
     * metadata of room
     * </pre>
     *
     * <code>string metadata = 5;</code>
     *
     * @return The metadata.
     */
    java.lang.String getMetadata();

    /**
     * <pre>
     * metadata of room
     * </pre>
     *
     * <code>string metadata = 5;</code>
     *
     * @return The bytes for metadata.
     */
    com.google.protobuf.ByteString getMetadataBytes();

    /**
     * <pre>
     * egress
     * </pre>
     *
     * <code>.livekit.RoomEgress egress = 6;</code>
     *
     * @return Whether the egress field is set.
     */
    boolean hasEgress();

    /**
     * <pre>
     * egress
     * </pre>
     *
     * <code>.livekit.RoomEgress egress = 6;</code>
     *
     * @return The egress.
     */
    im.turms.plugin.livekit.core.proto.room.RoomEgress getEgress();

    /**
     * <pre>
     * egress
     * </pre>
     *
     * <code>.livekit.RoomEgress egress = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.room.RoomEgressOrBuilder getEgressOrBuilder();

    /**
     * <pre>
     * playout delay of subscriber
     * </pre>
     *
     * <code>uint32 min_playout_delay = 7;</code>
     *
     * @return The minPlayoutDelay.
     */
    int getMinPlayoutDelay();

    /**
     * <code>uint32 max_playout_delay = 8;</code>
     *
     * @return The maxPlayoutDelay.
     */
    int getMaxPlayoutDelay();

    /**
     * <pre>
     * improves A/V sync when playout_delay set to a value larger than 200ms. It will disables transceiver re-use
     * so not recommended for rooms with frequent subscription changes
     * </pre>
     *
     * <code>bool sync_streams = 9;</code>
     *
     * @return The syncStreams.
     */
    boolean getSyncStreams();
}
