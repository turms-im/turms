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

public interface ListParticipantsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ListParticipantsRequest)
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
}
