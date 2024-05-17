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

public interface MuteRoomTrackRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.MuteRoomTrackRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string room = 1;</code>
     *
     * @return The room.
     */
    java.lang.String getRoom();

    /**
     * <pre>
     * name of the room
     * </pre>
     *
     * <code>string room = 1;</code>
     *
     * @return The bytes for room.
     */
    com.google.protobuf.ByteString getRoomBytes();

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
     * <pre>
     * sid of the track to mute
     * </pre>
     *
     * <code>string track_sid = 3;</code>
     *
     * @return The trackSid.
     */
    java.lang.String getTrackSid();

    /**
     * <pre>
     * sid of the track to mute
     * </pre>
     *
     * <code>string track_sid = 3;</code>
     *
     * @return The bytes for trackSid.
     */
    com.google.protobuf.ByteString getTrackSidBytes();

    /**
     * <pre>
     * set to true to mute, false to unmute
     * </pre>
     *
     * <code>bool muted = 4;</code>
     *
     * @return The muted.
     */
    boolean getMuted();
}
