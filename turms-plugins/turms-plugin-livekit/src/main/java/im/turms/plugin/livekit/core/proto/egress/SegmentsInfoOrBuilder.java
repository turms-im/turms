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

public interface SegmentsInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.SegmentsInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string playlist_name = 1;</code>
     *
     * @return The playlistName.
     */
    java.lang.String getPlaylistName();

    /**
     * <code>string playlist_name = 1;</code>
     *
     * @return The bytes for playlistName.
     */
    com.google.protobuf.ByteString getPlaylistNameBytes();

    /**
     * <code>string live_playlist_name = 8;</code>
     *
     * @return The livePlaylistName.
     */
    java.lang.String getLivePlaylistName();

    /**
     * <code>string live_playlist_name = 8;</code>
     *
     * @return The bytes for livePlaylistName.
     */
    com.google.protobuf.ByteString getLivePlaylistNameBytes();

    /**
     * <code>int64 duration = 2;</code>
     *
     * @return The duration.
     */
    long getDuration();

    /**
     * <code>int64 size = 3;</code>
     *
     * @return The size.
     */
    long getSize();

    /**
     * <code>string playlist_location = 4;</code>
     *
     * @return The playlistLocation.
     */
    java.lang.String getPlaylistLocation();

    /**
     * <code>string playlist_location = 4;</code>
     *
     * @return The bytes for playlistLocation.
     */
    com.google.protobuf.ByteString getPlaylistLocationBytes();

    /**
     * <code>string live_playlist_location = 9;</code>
     *
     * @return The livePlaylistLocation.
     */
    java.lang.String getLivePlaylistLocation();

    /**
     * <code>string live_playlist_location = 9;</code>
     *
     * @return The bytes for livePlaylistLocation.
     */
    com.google.protobuf.ByteString getLivePlaylistLocationBytes();

    /**
     * <code>int64 segment_count = 5;</code>
     *
     * @return The segmentCount.
     */
    long getSegmentCount();

    /**
     * <code>int64 started_at = 6;</code>
     *
     * @return The startedAt.
     */
    long getStartedAt();

    /**
     * <code>int64 ended_at = 7;</code>
     *
     * @return The endedAt.
     */
    long getEndedAt();
}
