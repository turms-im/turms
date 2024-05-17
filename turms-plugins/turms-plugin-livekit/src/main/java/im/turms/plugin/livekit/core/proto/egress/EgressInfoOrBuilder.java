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

package im.turms.plugin.livekit.core.proto.egress;

public interface EgressInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.EgressInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The egressId.
     */
    java.lang.String getEgressId();

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The bytes for egressId.
     */
    com.google.protobuf.ByteString getEgressIdBytes();

    /**
     * <code>string room_id = 2;</code>
     *
     * @return The roomId.
     */
    java.lang.String getRoomId();

    /**
     * <code>string room_id = 2;</code>
     *
     * @return The bytes for roomId.
     */
    com.google.protobuf.ByteString getRoomIdBytes();

    /**
     * <code>string room_name = 13;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <code>string room_name = 13;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <code>.livekit.EgressStatus status = 3;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    int getStatusValue();

    /**
     * <code>.livekit.EgressStatus status = 3;</code>
     *
     * @return The status.
     */
    im.turms.plugin.livekit.core.proto.egress.EgressStatus getStatus();

    /**
     * <code>int64 started_at = 10;</code>
     *
     * @return The startedAt.
     */
    long getStartedAt();

    /**
     * <code>int64 ended_at = 11;</code>
     *
     * @return The endedAt.
     */
    long getEndedAt();

    /**
     * <code>int64 updated_at = 18;</code>
     *
     * @return The updatedAt.
     */
    long getUpdatedAt();

    /**
     * <code>string details = 21;</code>
     *
     * @return The details.
     */
    java.lang.String getDetails();

    /**
     * <code>string details = 21;</code>
     *
     * @return The bytes for details.
     */
    com.google.protobuf.ByteString getDetailsBytes();

    /**
     * <code>string error = 9;</code>
     *
     * @return The error.
     */
    java.lang.String getError();

    /**
     * <code>string error = 9;</code>
     *
     * @return The bytes for error.
     */
    com.google.protobuf.ByteString getErrorBytes();

    /**
     * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
     *
     * @return Whether the roomComposite field is set.
     */
    boolean hasRoomComposite();

    /**
     * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
     *
     * @return The roomComposite.
     */
    im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequest getRoomComposite();

    /**
     * <code>.livekit.RoomCompositeEgressRequest room_composite = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.RoomCompositeEgressRequestOrBuilder getRoomCompositeOrBuilder();

    /**
     * <code>.livekit.WebEgressRequest web = 14;</code>
     *
     * @return Whether the web field is set.
     */
    boolean hasWeb();

    /**
     * <code>.livekit.WebEgressRequest web = 14;</code>
     *
     * @return The web.
     */
    im.turms.plugin.livekit.core.proto.egress.WebEgressRequest getWeb();

    /**
     * <code>.livekit.WebEgressRequest web = 14;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.WebEgressRequestOrBuilder getWebOrBuilder();

    /**
     * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
     *
     * @return Whether the participant field is set.
     */
    boolean hasParticipant();

    /**
     * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
     *
     * @return The participant.
     */
    im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequest getParticipant();

    /**
     * <code>.livekit.ParticipantEgressRequest participant = 19;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ParticipantEgressRequestOrBuilder getParticipantOrBuilder();

    /**
     * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
     *
     * @return Whether the trackComposite field is set.
     */
    boolean hasTrackComposite();

    /**
     * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
     *
     * @return The trackComposite.
     */
    im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequest getTrackComposite();

    /**
     * <code>.livekit.TrackCompositeEgressRequest track_composite = 5;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.TrackCompositeEgressRequestOrBuilder getTrackCompositeOrBuilder();

    /**
     * <code>.livekit.TrackEgressRequest track = 6;</code>
     *
     * @return Whether the track field is set.
     */
    boolean hasTrack();

    /**
     * <code>.livekit.TrackEgressRequest track = 6;</code>
     *
     * @return The track.
     */
    im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest getTrack();

    /**
     * <code>.livekit.TrackEgressRequest track = 6;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.TrackEgressRequestOrBuilder getTrackOrBuilder();

    /**
     * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.stream is deprecated. See livekit_egress.proto;l=321
     * @return Whether the stream field is set.
     */
    @java.lang.Deprecated
    boolean hasStream();

    /**
     * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.stream is deprecated. See livekit_egress.proto;l=321
     * @return The stream.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.StreamInfoList getStream();

    /**
     * <code>.livekit.StreamInfoList stream = 7 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.StreamInfoListOrBuilder getStreamOrBuilder();

    /**
     * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.file is deprecated. See livekit_egress.proto;l=322
     * @return Whether the file field is set.
     */
    @java.lang.Deprecated
    boolean hasFile();

    /**
     * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.file is deprecated. See livekit_egress.proto;l=322
     * @return The file.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.FileInfo getFile();

    /**
     * <code>.livekit.FileInfo file = 8 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder getFileOrBuilder();

    /**
     * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.segments is deprecated. See livekit_egress.proto;l=323
     * @return Whether the segments field is set.
     */
    @java.lang.Deprecated
    boolean hasSegments();

    /**
     * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
     *
     * @deprecated livekit.EgressInfo.segments is deprecated. See livekit_egress.proto;l=323
     * @return The segments.
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getSegments();

    /**
     * <code>.livekit.SegmentsInfo segments = 12 [deprecated = true];</code>
     */
    @java.lang.Deprecated
    im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder getSegmentsOrBuilder();

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.StreamInfo> getStreamResultsList();

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.StreamInfo getStreamResults(int index);

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    int getStreamResultsCount();

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder> getStreamResultsOrBuilderList();

    /**
     * <code>repeated .livekit.StreamInfo stream_results = 15;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.StreamInfoOrBuilder getStreamResultsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.FileInfo> getFileResultsList();

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.FileInfo getFileResults(int index);

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    int getFileResultsCount();

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder> getFileResultsOrBuilderList();

    /**
     * <code>repeated .livekit.FileInfo file_results = 16;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.FileInfoOrBuilder getFileResultsOrBuilder(int index);

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.SegmentsInfo> getSegmentResultsList();

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentsInfo getSegmentResults(int index);

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    int getSegmentResultsCount();

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder> getSegmentResultsOrBuilderList();

    /**
     * <code>repeated .livekit.SegmentsInfo segment_results = 17;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.SegmentsInfoOrBuilder getSegmentResultsOrBuilder(
            int index);

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    java.util.List<im.turms.plugin.livekit.core.proto.egress.ImagesInfo> getImageResultsList();

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ImagesInfo getImageResults(int index);

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    int getImageResultsCount();

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    java.util.List<? extends im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder> getImageResultsOrBuilderList();

    /**
     * <code>repeated .livekit.ImagesInfo image_results = 20;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.ImagesInfoOrBuilder getImageResultsOrBuilder(
            int index);

    im.turms.plugin.livekit.core.proto.egress.EgressInfo.RequestCase getRequestCase();

    im.turms.plugin.livekit.core.proto.egress.EgressInfo.ResultCase getResultCase();
}
