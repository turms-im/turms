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

public final class LivekitRoom {
    private LivekitRoom() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                LivekitRoom.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_CreateRoomRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_CreateRoomRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RoomEgress_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RoomEgress_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListRoomsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListRoomsRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListRoomsResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListRoomsResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_DeleteRoomRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_DeleteRoomRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_DeleteRoomResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_DeleteRoomResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListParticipantsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListParticipantsRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_ListParticipantsResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_ListParticipantsResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RoomParticipantIdentity_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RoomParticipantIdentity_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_RemoveParticipantResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_RemoveParticipantResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_MuteRoomTrackRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_MuteRoomTrackRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_MuteRoomTrackResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_MuteRoomTrackResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateParticipantRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateParticipantRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateSubscriptionsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateSubscriptionsRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateSubscriptionsResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateSubscriptionsResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SendDataRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SendDataRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_SendDataResponse_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_SendDataResponse_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_UpdateRoomMetadataRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_UpdateRoomMetadataRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData =
                {"\n\022livekit_room.proto\022\007livekit\032\024livekit_m"
                        + "odels.proto\032\024livekit_egress.proto\"\201\002\n\021Cr"
                        + "eateRoomRequest\022\014\n\004name\030\001 \001(\t\022\025\n\rempty_t"
                        + "imeout\030\002 \001(\r\022\031\n\021departure_timeout\030\n \001(\r\022"
                        + "\030\n\020max_participants\030\003 \001(\r\022\017\n\007node_id\030\004 \001"
                        + "(\t\022\020\n\010metadata\030\005 \001(\t\022#\n\006egress\030\006 \001(\0132\023.l"
                        + "ivekit.RoomEgress\022\031\n\021min_playout_delay\030\007"
                        + " \001(\r\022\031\n\021max_playout_delay\030\010 \001(\r\022\024\n\014sync_"
                        + "streams\030\t \001(\010\"\236\001\n\nRoomEgress\0221\n\004room\030\001 \001"
                        + "(\0132#.livekit.RoomCompositeEgressRequest\022"
                        + "3\n\013participant\030\003 \001(\0132\036.livekit.AutoParti"
                        + "cipantEgress\022(\n\006tracks\030\002 \001(\0132\030.livekit.A"
                        + "utoTrackEgress\"!\n\020ListRoomsRequest\022\r\n\005na"
                        + "mes\030\001 \003(\t\"1\n\021ListRoomsResponse\022\034\n\005rooms\030"
                        + "\001 \003(\0132\r.livekit.Room\"!\n\021DeleteRoomReques"
                        + "t\022\014\n\004room\030\001 \001(\t\"\024\n\022DeleteRoomResponse\"\'\n"
                        + "\027ListParticipantsRequest\022\014\n\004room\030\001 \001(\t\"J"
                        + "\n\030ListParticipantsResponse\022.\n\014participan"
                        + "ts\030\001 \003(\0132\030.livekit.ParticipantInfo\"9\n\027Ro"
                        + "omParticipantIdentity\022\014\n\004room\030\001 \001(\t\022\020\n\010i"
                        + "dentity\030\002 \001(\t\"\033\n\031RemoveParticipantRespon"
                        + "se\"X\n\024MuteRoomTrackRequest\022\014\n\004room\030\001 \001(\t"
                        + "\022\020\n\010identity\030\002 \001(\t\022\021\n\ttrack_sid\030\003 \001(\t\022\r\n"
                        + "\005muted\030\004 \001(\010\":\n\025MuteRoomTrackResponse\022!\n"
                        + "\005track\030\001 \001(\0132\022.livekit.TrackInfo\"\216\001\n\030Upd"
                        + "ateParticipantRequest\022\014\n\004room\030\001 \001(\t\022\020\n\010i"
                        + "dentity\030\002 \001(\t\022\020\n\010metadata\030\003 \001(\t\0222\n\npermi"
                        + "ssion\030\004 \001(\0132\036.livekit.ParticipantPermiss"
                        + "ion\022\014\n\004name\030\005 \001(\t\"\233\001\n\032UpdateSubscription"
                        + "sRequest\022\014\n\004room\030\001 \001(\t\022\020\n\010identity\030\002 \001(\t"
                        + "\022\022\n\ntrack_sids\030\003 \003(\t\022\021\n\tsubscribe\030\004 \001(\010\022"
                        + "6\n\022participant_tracks\030\005 \003(\0132\032.livekit.Pa"
                        + "rticipantTracks\"\035\n\033UpdateSubscriptionsRe"
                        + "sponse\"\261\001\n\017SendDataRequest\022\014\n\004room\030\001 \001(\t"
                        + "\022\014\n\004data\030\002 \001(\014\022&\n\004kind\030\003 \001(\0162\030.livekit.D"
                        + "ataPacket.Kind\022\034\n\020destination_sids\030\004 \003(\t"
                        + "B\002\030\001\022\036\n\026destination_identities\030\006 \003(\t\022\022\n\005"
                        + "topic\030\005 \001(\tH\000\210\001\001B\010\n\006_topic\"\022\n\020SendDataRe"
                        + "sponse\";\n\031UpdateRoomMetadataRequest\022\014\n\004r"
                        + "oom\030\001 \001(\t\022\020\n\010metadata\030\002 \001(\tBq\n\'im.turms."
                        + "plugin.livekit.core.proto.roomP\001Z#github"
                        + ".com/livekit/protocol/livekit\252\002\rLiveKit."
                        + "Proto\352\002\016LiveKit::Protob\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor(),
                        im.turms.plugin.livekit.core.proto.egress.LivekitEgress.getDescriptor(),});
        internal_static_livekit_CreateRoomRequest_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_livekit_CreateRoomRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_CreateRoomRequest_descriptor,
                        new java.lang.String[]{"Name",
                                "EmptyTimeout",
                                "DepartureTimeout",
                                "MaxParticipants",
                                "NodeId",
                                "Metadata",
                                "Egress",
                                "MinPlayoutDelay",
                                "MaxPlayoutDelay",
                                "SyncStreams",});
        internal_static_livekit_RoomEgress_descriptor = getDescriptor().getMessageTypes()
                .get(1);
        internal_static_livekit_RoomEgress_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RoomEgress_descriptor,
                        new java.lang.String[]{"Room", "Participant", "Tracks",});
        internal_static_livekit_ListRoomsRequest_descriptor = getDescriptor().getMessageTypes()
                .get(2);
        internal_static_livekit_ListRoomsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListRoomsRequest_descriptor,
                        new java.lang.String[]{"Names",});
        internal_static_livekit_ListRoomsResponse_descriptor = getDescriptor().getMessageTypes()
                .get(3);
        internal_static_livekit_ListRoomsResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListRoomsResponse_descriptor,
                        new java.lang.String[]{"Rooms",});
        internal_static_livekit_DeleteRoomRequest_descriptor = getDescriptor().getMessageTypes()
                .get(4);
        internal_static_livekit_DeleteRoomRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_DeleteRoomRequest_descriptor,
                        new java.lang.String[]{"Room",});
        internal_static_livekit_DeleteRoomResponse_descriptor = getDescriptor().getMessageTypes()
                .get(5);
        internal_static_livekit_DeleteRoomResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_DeleteRoomResponse_descriptor,
                        new java.lang.String[]{});
        internal_static_livekit_ListParticipantsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(6);
        internal_static_livekit_ListParticipantsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListParticipantsRequest_descriptor,
                        new java.lang.String[]{"Room",});
        internal_static_livekit_ListParticipantsResponse_descriptor =
                getDescriptor().getMessageTypes()
                        .get(7);
        internal_static_livekit_ListParticipantsResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_ListParticipantsResponse_descriptor,
                        new java.lang.String[]{"Participants",});
        internal_static_livekit_RoomParticipantIdentity_descriptor =
                getDescriptor().getMessageTypes()
                        .get(8);
        internal_static_livekit_RoomParticipantIdentity_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RoomParticipantIdentity_descriptor,
                        new java.lang.String[]{"Room", "Identity",});
        internal_static_livekit_RemoveParticipantResponse_descriptor =
                getDescriptor().getMessageTypes()
                        .get(9);
        internal_static_livekit_RemoveParticipantResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_RemoveParticipantResponse_descriptor,
                        new java.lang.String[]{});
        internal_static_livekit_MuteRoomTrackRequest_descriptor = getDescriptor().getMessageTypes()
                .get(10);
        internal_static_livekit_MuteRoomTrackRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_MuteRoomTrackRequest_descriptor,
                        new java.lang.String[]{"Room", "Identity", "TrackSid", "Muted",});
        internal_static_livekit_MuteRoomTrackResponse_descriptor = getDescriptor().getMessageTypes()
                .get(11);
        internal_static_livekit_MuteRoomTrackResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_MuteRoomTrackResponse_descriptor,
                        new java.lang.String[]{"Track",});
        internal_static_livekit_UpdateParticipantRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(12);
        internal_static_livekit_UpdateParticipantRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateParticipantRequest_descriptor,
                        new java.lang.String[]{"Room",
                                "Identity",
                                "Metadata",
                                "Permission",
                                "Name",});
        internal_static_livekit_UpdateSubscriptionsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(13);
        internal_static_livekit_UpdateSubscriptionsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateSubscriptionsRequest_descriptor,
                        new java.lang.String[]{"Room",
                                "Identity",
                                "TrackSids",
                                "Subscribe",
                                "ParticipantTracks",});
        internal_static_livekit_UpdateSubscriptionsResponse_descriptor =
                getDescriptor().getMessageTypes()
                        .get(14);
        internal_static_livekit_UpdateSubscriptionsResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateSubscriptionsResponse_descriptor,
                        new java.lang.String[]{});
        internal_static_livekit_SendDataRequest_descriptor = getDescriptor().getMessageTypes()
                .get(15);
        internal_static_livekit_SendDataRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SendDataRequest_descriptor,
                        new java.lang.String[]{"Room",
                                "Data",
                                "Kind",
                                "DestinationSids",
                                "DestinationIdentities",
                                "Topic",});
        internal_static_livekit_SendDataResponse_descriptor = getDescriptor().getMessageTypes()
                .get(16);
        internal_static_livekit_SendDataResponse_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_SendDataResponse_descriptor,
                        new java.lang.String[]{});
        internal_static_livekit_UpdateRoomMetadataRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(17);
        internal_static_livekit_UpdateRoomMetadataRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_UpdateRoomMetadataRequest_descriptor,
                        new java.lang.String[]{"Room", "Metadata",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor();
        im.turms.plugin.livekit.core.proto.egress.LivekitEgress.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}