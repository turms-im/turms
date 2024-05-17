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

public interface ParticipantTracksOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ParticipantTracks)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * participant ID of participant to whom the tracks belong
     * </pre>
     *
     * <code>string participant_sid = 1;</code>
     *
     * @return The participantSid.
     */
    java.lang.String getParticipantSid();

    /**
     * <pre>
     * participant ID of participant to whom the tracks belong
     * </pre>
     *
     * <code>string participant_sid = 1;</code>
     *
     * @return The bytes for participantSid.
     */
    com.google.protobuf.ByteString getParticipantSidBytes();

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @return A list containing the trackSids.
     */
    java.util.List<java.lang.String> getTrackSidsList();

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @return The count of trackSids.
     */
    int getTrackSidsCount();

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The trackSids at the given index.
     */
    java.lang.String getTrackSids(int index);

    /**
     * <code>repeated string track_sids = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the trackSids at the given index.
     */
    com.google.protobuf.ByteString getTrackSidsBytes(int index);
}
