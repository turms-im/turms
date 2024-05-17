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

package im.turms.plugin.livekit.core.proto.models;

public interface UserPacketOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.UserPacket)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * participant ID of user that sent the message
     * </pre>
     *
     * <code>string participant_sid = 1 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.participant_sid is deprecated. See livekit_models.proto;l=243
     * @return The participantSid.
     */
    @java.lang.Deprecated
    java.lang.String getParticipantSid();

    /**
     * <pre>
     * participant ID of user that sent the message
     * </pre>
     *
     * <code>string participant_sid = 1 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.participant_sid is deprecated. See livekit_models.proto;l=243
     * @return The bytes for participantSid.
     */
    @java.lang.Deprecated
    com.google.protobuf.ByteString getParticipantSidBytes();

    /**
     * <code>string participant_identity = 5 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.participant_identity is deprecated. See
     *             livekit_models.proto;l=244
     * @return The participantIdentity.
     */
    @java.lang.Deprecated
    java.lang.String getParticipantIdentity();

    /**
     * <code>string participant_identity = 5 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.participant_identity is deprecated. See
     *             livekit_models.proto;l=244
     * @return The bytes for participantIdentity.
     */
    @java.lang.Deprecated
    com.google.protobuf.ByteString getParticipantIdentityBytes();

    /**
     * <pre>
     * user defined payload
     * </pre>
     *
     * <code>bytes payload = 2;</code>
     *
     * @return The payload.
     */
    com.google.protobuf.ByteString getPayload();

    /**
     * <pre>
     * the ID of the participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_sids = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_sids is deprecated. See livekit_models.proto;l=248
     * @return A list containing the destinationSids.
     */
    @java.lang.Deprecated
    java.util.List<java.lang.String> getDestinationSidsList();

    /**
     * <pre>
     * the ID of the participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_sids = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_sids is deprecated. See livekit_models.proto;l=248
     * @return The count of destinationSids.
     */
    @java.lang.Deprecated
    int getDestinationSidsCount();

    /**
     * <pre>
     * the ID of the participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_sids = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_sids is deprecated. See livekit_models.proto;l=248
     * @param index The index of the element to return.
     * @return The destinationSids at the given index.
     */
    @java.lang.Deprecated
    java.lang.String getDestinationSids(int index);

    /**
     * <pre>
     * the ID of the participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_sids = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_sids is deprecated. See livekit_models.proto;l=248
     * @param index The index of the value to return.
     * @return The bytes of the destinationSids at the given index.
     */
    @java.lang.Deprecated
    com.google.protobuf.ByteString getDestinationSidsBytes(int index);

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_identities is deprecated. See
     *             livekit_models.proto;l=250
     * @return A list containing the destinationIdentities.
     */
    @java.lang.Deprecated
    java.util.List<java.lang.String> getDestinationIdentitiesList();

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_identities is deprecated. See
     *             livekit_models.proto;l=250
     * @return The count of destinationIdentities.
     */
    @java.lang.Deprecated
    int getDestinationIdentitiesCount();

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_identities is deprecated. See
     *             livekit_models.proto;l=250
     * @param index The index of the element to return.
     * @return The destinationIdentities at the given index.
     */
    @java.lang.Deprecated
    java.lang.String getDestinationIdentities(int index);

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 6 [deprecated = true];</code>
     *
     * @deprecated livekit.UserPacket.destination_identities is deprecated. See
     *             livekit_models.proto;l=250
     * @param index The index of the value to return.
     * @return The bytes of the destinationIdentities at the given index.
     */
    @java.lang.Deprecated
    com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index);

    /**
     * <pre>
     * topic under which the message was published
     * </pre>
     *
     * <code>optional string topic = 4;</code>
     *
     * @return Whether the topic field is set.
     */
    boolean hasTopic();

    /**
     * <pre>
     * topic under which the message was published
     * </pre>
     *
     * <code>optional string topic = 4;</code>
     *
     * @return The topic.
     */
    java.lang.String getTopic();

    /**
     * <pre>
     * topic under which the message was published
     * </pre>
     *
     * <code>optional string topic = 4;</code>
     *
     * @return The bytes for topic.
     */
    com.google.protobuf.ByteString getTopicBytes();
}
