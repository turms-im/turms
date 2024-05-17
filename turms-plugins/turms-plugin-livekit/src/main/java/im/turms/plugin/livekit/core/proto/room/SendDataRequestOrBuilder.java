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

public interface SendDataRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.SendDataRequest)
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
     * <code>bytes data = 2;</code>
     *
     * @return The data.
     */
    com.google.protobuf.ByteString getData();

    /**
     * <code>.livekit.DataPacket.Kind kind = 3;</code>
     *
     * @return The enum numeric value on the wire for kind.
     */
    int getKindValue();

    /**
     * <code>.livekit.DataPacket.Kind kind = 3;</code>
     *
     * @return The kind.
     */
    im.turms.plugin.livekit.core.proto.models.DataPacket.Kind getKind();

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @return A list containing the destinationSids.
     */
    @java.lang.Deprecated
    java.util.List<java.lang.String> getDestinationSidsList();

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @return The count of destinationSids.
     */
    @java.lang.Deprecated
    int getDestinationSidsCount();

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @param index The index of the element to return.
     * @return The destinationSids at the given index.
     */
    @java.lang.Deprecated
    java.lang.String getDestinationSids(int index);

    /**
     * <pre>
     * mark deprecated
     * </pre>
     *
     * <code>repeated string destination_sids = 4 [deprecated = true];</code>
     *
     * @deprecated livekit.SendDataRequest.destination_sids is deprecated. See
     *             livekit_room.proto;l=139
     * @param index The index of the value to return.
     * @return The bytes of the destinationSids at the given index.
     */
    @java.lang.Deprecated
    com.google.protobuf.ByteString getDestinationSidsBytes(int index);

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @return A list containing the destinationIdentities.
     */
    java.util.List<java.lang.String> getDestinationIdentitiesList();

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @return The count of destinationIdentities.
     */
    int getDestinationIdentitiesCount();

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @param index The index of the element to return.
     * @return The destinationIdentities at the given index.
     */
    java.lang.String getDestinationIdentities(int index);

    /**
     * <pre>
     * when set, only forward to these identities
     * </pre>
     *
     * <code>repeated string destination_identities = 6;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the destinationIdentities at the given index.
     */
    com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index);

    /**
     * <code>optional string topic = 5;</code>
     *
     * @return Whether the topic field is set.
     */
    boolean hasTopic();

    /**
     * <code>optional string topic = 5;</code>
     *
     * @return The topic.
     */
    java.lang.String getTopic();

    /**
     * <code>optional string topic = 5;</code>
     *
     * @return The bytes for topic.
     */
    com.google.protobuf.ByteString getTopicBytes();
}
