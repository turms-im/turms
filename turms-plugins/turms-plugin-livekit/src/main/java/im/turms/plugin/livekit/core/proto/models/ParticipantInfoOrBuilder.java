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

public interface ParticipantInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ParticipantInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    java.lang.String getSid();

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    com.google.protobuf.ByteString getSidBytes();

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
     * <code>.livekit.ParticipantInfo.State state = 3;</code>
     *
     * @return The enum numeric value on the wire for state.
     */
    int getStateValue();

    /**
     * <code>.livekit.ParticipantInfo.State state = 3;</code>
     *
     * @return The state.
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantInfo.State getState();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.TrackInfo> getTracksList();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TrackInfo getTracks(int index);

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    int getTracksCount();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder> getTracksOrBuilderList();

    /**
     * <code>repeated .livekit.TrackInfo tracks = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.models.TrackInfoOrBuilder getTracksOrBuilder(int index);

    /**
     * <code>string metadata = 5;</code>
     *
     * @return The metadata.
     */
    java.lang.String getMetadata();

    /**
     * <code>string metadata = 5;</code>
     *
     * @return The bytes for metadata.
     */
    com.google.protobuf.ByteString getMetadataBytes();

    /**
     * <pre>
     * timestamp when participant joined room, in seconds
     * </pre>
     *
     * <code>int64 joined_at = 6;</code>
     *
     * @return The joinedAt.
     */
    long getJoinedAt();

    /**
     * <code>string name = 9;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 9;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>uint32 version = 10;</code>
     *
     * @return The version.
     */
    int getVersion();

    /**
     * <code>.livekit.ParticipantPermission permission = 11;</code>
     *
     * @return Whether the permission field is set.
     */
    boolean hasPermission();

    /**
     * <code>.livekit.ParticipantPermission permission = 11;</code>
     *
     * @return The permission.
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantPermission getPermission();

    /**
     * <code>.livekit.ParticipantPermission permission = 11;</code>
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder getPermissionOrBuilder();

    /**
     * <code>string region = 12;</code>
     *
     * @return The region.
     */
    java.lang.String getRegion();

    /**
     * <code>string region = 12;</code>
     *
     * @return The bytes for region.
     */
    com.google.protobuf.ByteString getRegionBytes();

    /**
     * <pre>
     * indicates the participant has an active publisher connection
     * and can publish to the server
     * </pre>
     *
     * <code>bool is_publisher = 13;</code>
     *
     * @return The isPublisher.
     */
    boolean getIsPublisher();

    /**
     * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
     *
     * @return The enum numeric value on the wire for kind.
     */
    int getKindValue();

    /**
     * <code>.livekit.ParticipantInfo.Kind kind = 14;</code>
     *
     * @return The kind.
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantInfo.Kind getKind();
}
