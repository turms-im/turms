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

public interface DataPacketOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.DataPacket)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
     * @return The enum numeric value on the wire for kind.
     */
    @java.lang.Deprecated
    int getKindValue();

    /**
     * <code>.livekit.DataPacket.Kind kind = 1 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.kind is deprecated. See livekit_models.proto;l=217
     * @return The kind.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.models.DataPacket.Kind getKind();

    /**
     * <pre>
     * participant identity of user that sent the message
     * </pre>
     *
     * <code>string participant_identity = 4;</code>
     *
     * @return The participantIdentity.
     */
    java.lang.String getParticipantIdentity();

    /**
     * <pre>
     * participant identity of user that sent the message
     * </pre>
     *
     * <code>string participant_identity = 4;</code>
     *
     * @return The bytes for participantIdentity.
     */
    com.google.protobuf.ByteString getParticipantIdentityBytes();

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @return A list containing the destinationIdentities.
     */
    java.util.List<java.lang.String> getDestinationIdentitiesList();

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @return The count of destinationIdentities.
     */
    int getDestinationIdentitiesCount();

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @param index The index of the element to return.
     * @return The destinationIdentities at the given index.
     */
    java.lang.String getDestinationIdentities(int index);

    /**
     * <pre>
     * identities of participants who will receive the message (sent to all by default)
     * </pre>
     *
     * <code>repeated string destination_identities = 5;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the destinationIdentities at the given index.
     */
    com.google.protobuf.ByteString getDestinationIdentitiesBytes(int index);

    /**
     * <code>.livekit.UserPacket user = 2;</code>
     *
     * @return Whether the user field is set.
     */
    boolean hasUser();

    /**
     * <code>.livekit.UserPacket user = 2;</code>
     *
     * @return The user.
     */
    im.turms.plugin.livekit.core.proto.models.UserPacket getUser();

    /**
     * <code>.livekit.UserPacket user = 2;</code>
     */
    im.turms.plugin.livekit.core.proto.models.UserPacketOrBuilder getUserOrBuilder();

    /**
     * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.speaker is deprecated. See livekit_models.proto;l=224
     * @return Whether the speaker field is set.
     */
    @java.lang.Deprecated
    boolean hasSpeaker();

    /**
     * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
     *
     * @deprecated livekit.DataPacket.speaker is deprecated. See livekit_models.proto;l=224
     * @return The speaker.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate getSpeaker();

    /**
     * <code>.livekit.ActiveSpeakerUpdate speaker = 3 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdateOrBuilder getSpeakerOrBuilder();

    /**
     * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
     *
     * @return Whether the sipDtmf field is set.
     */
    boolean hasSipDtmf();

    /**
     * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
     *
     * @return The sipDtmf.
     */
    im.turms.plugin.livekit.core.proto.models.SipDTMF getSipDtmf();

    /**
     * <code>.livekit.SipDTMF sip_dtmf = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.models.SipDTMFOrBuilder getSipDtmfOrBuilder();

    im.turms.plugin.livekit.core.proto.models.DataPacket.ValueCase getValueCase();
}
