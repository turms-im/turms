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

package im.turms.plugin.livekit.core.service;

import java.util.List;
import jakarta.annotation.Nullable;

import com.google.protobuf.ByteStringUtil;
import reactor.core.publisher.Mono;

import im.turms.plugin.livekit.core.LiveKitClient;
import im.turms.plugin.livekit.core.auth.VideoGrant;
import im.turms.plugin.livekit.core.proto.models.DataPacket;
import im.turms.plugin.livekit.core.proto.models.ParticipantInfo;
import im.turms.plugin.livekit.core.proto.models.ParticipantPermission;
import im.turms.plugin.livekit.core.proto.models.Room;
import im.turms.plugin.livekit.core.proto.models.TrackInfo;
import im.turms.plugin.livekit.core.proto.room.CreateRoomRequest;
import im.turms.plugin.livekit.core.proto.room.DeleteRoomRequest;
import im.turms.plugin.livekit.core.proto.room.ListParticipantsRequest;
import im.turms.plugin.livekit.core.proto.room.ListParticipantsResponse;
import im.turms.plugin.livekit.core.proto.room.ListRoomsRequest;
import im.turms.plugin.livekit.core.proto.room.ListRoomsResponse;
import im.turms.plugin.livekit.core.proto.room.MuteRoomTrackRequest;
import im.turms.plugin.livekit.core.proto.room.MuteRoomTrackResponse;
import im.turms.plugin.livekit.core.proto.room.RoomParticipantIdentity;
import im.turms.plugin.livekit.core.proto.room.SendDataRequest;
import im.turms.plugin.livekit.core.proto.room.UpdateParticipantRequest;
import im.turms.plugin.livekit.core.proto.room.UpdateRoomMetadataRequest;
import im.turms.plugin.livekit.core.proto.room.UpdateSubscriptionsRequest;

/**
 * @author James Chen
 */
public class RoomService {

    private final LiveKitClient client;

    public RoomService(LiveKitClient liveKitClient) {
        client = liveKitClient;
    }

    public Mono<Room> createRoom(
            String name,
            @Nullable Integer emptyTimeout,
            @Nullable Integer maxParticipants,
            @Nullable String nodeId,
            @Nullable String metadata,
            @Nullable Integer minPlayoutDelay,
            @Nullable Integer maxPlayoutDelay,
            @Nullable Boolean syncStreams,
            @Nullable Integer departureTimeout) {
        CreateRoomRequest.Builder builder = CreateRoomRequest.newBuilder();
        builder.setName(name);
        if (emptyTimeout != null) {
            builder.setEmptyTimeout(emptyTimeout);
        }
        if (maxParticipants != null) {
            builder.setMaxParticipants(maxParticipants);
        }
        if (nodeId != null) {
            builder.setNodeId(nodeId);
        }
        if (metadata != null) {
            builder.setMetadata(metadata);
        }
        if (minPlayoutDelay != null) {
            builder.setMinPlayoutDelay(minPlayoutDelay);
        }
        if (maxPlayoutDelay != null) {
            builder.setMaxPlayoutDelay(maxPlayoutDelay);
        }
        if (syncStreams != null) {
            builder.setSyncStreams(syncStreams);
        }
        if (departureTimeout != null) {
            builder.setDepartureTimeout(departureTimeout);
        }
        return client.sendHttpRequest("/twirp/livekit.RoomService/CreateRoom",
                List.of(VideoGrant.RoomCreate.ENABLED),
                Room.getDefaultInstance(),
                builder.build());
    }

    public Mono<List<Room>> listRooms(@Nullable List<String> names) {
        ListRoomsRequest.Builder builder = ListRoomsRequest.newBuilder();
        if (names != null) {
            builder.addAllNames(names);
        }
        return client
                .sendHttpRequest("/twirp/livekit.RoomService/ListRooms",
                        List.of(VideoGrant.RoomList.ENABLED),
                        ListRoomsResponse.getDefaultInstance(),
                        builder.build())
                .map(ListRoomsResponse::getRoomsList);
    }

    public Mono<Void> deleteRoom(String roomName) {
        DeleteRoomRequest request = DeleteRoomRequest.newBuilder()
                .setRoom(roomName)
                .build();
        return client.sendHttpRequest("/twirp/livekit.RoomService/DeleteRoom",
                List.of(VideoGrant.RoomCreate.ENABLED),
                request);
    }

    public Mono<Room> updateRoomMetadata(String roomName, String metadata) {
        UpdateRoomMetadataRequest request = UpdateRoomMetadataRequest.newBuilder()
                .setRoom(roomName)
                .setMetadata(metadata)
                .build();
        return client.sendHttpRequest("/twirp/livekit.RoomService/UpdateRoomMetadata",
                List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                Room.getDefaultInstance(),
                request);
    }

    public Mono<List<ParticipantInfo>> listParticipants(String roomName) {
        ListParticipantsRequest request = ListParticipantsRequest.newBuilder()
                .setRoom(roomName)
                .build();
        return client
                .sendHttpRequest("/twirp/livekit.RoomService/ListParticipants",
                        List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                        ListParticipantsResponse.getDefaultInstance(),
                        request)
                .map(ListParticipantsResponse::getParticipantsList);
    }

    public Mono<ParticipantInfo> getParticipant(String roomName, String identity) {
        RoomParticipantIdentity request = RoomParticipantIdentity.newBuilder()
                .setRoom(roomName)
                .setIdentity(identity)
                .build();
        return client.sendHttpRequest("/twirp/livekit.RoomService/GetParticipant",
                List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                ParticipantInfo.getDefaultInstance(),
                request);
    }

    public Mono<Void> removeParticipant(String roomName, String identity) {
        RoomParticipantIdentity request = RoomParticipantIdentity.newBuilder()
                .setRoom(roomName)
                .setIdentity(identity)
                .build();
        return client.sendHttpRequest("/twirp/livekit.RoomService/RemoveParticipant",
                List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                request);
    }

    public Mono<TrackInfo> mutePublishedTrack(
            String roomName,
            String identity,
            String trackSid,
            boolean mute) {
        MuteRoomTrackRequest request = MuteRoomTrackRequest.newBuilder()
                .setRoom(roomName)
                .setIdentity(identity)
                .setTrackSid(trackSid)
                .setMuted(mute)
                .build();
        return client
                .sendHttpRequest("/twirp/livekit.RoomService/MutePublishedTrack",
                        List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                        MuteRoomTrackResponse.getDefaultInstance(),
                        request)
                .map(MuteRoomTrackResponse::getTrack);
    }

    public Mono<ParticipantInfo> updateParticipant(
            String roomName,
            String identity,
            @Nullable String name,
            @Nullable String metadata,
            @Nullable ParticipantPermission participantPermission) {
        UpdateParticipantRequest.Builder builder = UpdateParticipantRequest.newBuilder();
        builder.setRoom(roomName);
        builder.setIdentity(identity);
        if (name != null) {
            builder.setName(name);
        }
        if (metadata != null) {
            builder.setMetadata(metadata);
        }
        if (participantPermission != null) {
            builder.setPermission(participantPermission);
        }
        return client.sendHttpRequest("/twirp/livekit.RoomService/UpdateParticipant",
                List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                ParticipantInfo.getDefaultInstance(),
                builder.build());

    }

    public Mono<Void> updateSubscriptions(
            String roomName,
            String identity,
            List<String> trackSids,
            boolean subscribe) {
        UpdateSubscriptionsRequest request = UpdateSubscriptionsRequest.newBuilder()
                .setRoom(roomName)
                .setIdentity(identity)
                .addAllTrackSids(trackSids)
                .setSubscribe(subscribe)
                .build();
        return client.sendHttpRequest("/twirp/livekit.RoomService/UpdateSubscriptions",
                List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                request);
    }

    public Mono<Void> sendData(
            String roomName,
            byte[] data,
            DataPacket.Kind kind,
            @Nullable List<String> destinationIdentities) {
        SendDataRequest.Builder builder = SendDataRequest.newBuilder()
                .setRoom(roomName)
                .setData(ByteStringUtil.wrap(data))
                .setKind(kind);
        if (destinationIdentities != null) {
            builder.addAllDestinationIdentities(destinationIdentities);
        }
        return client.sendHttpRequest("/twirp/livekit.RoomService/SendData",
                List.of(VideoGrant.RoomAdmin.ENABLED, new VideoGrant.RoomName(roomName)),
                builder.build());
    }

}