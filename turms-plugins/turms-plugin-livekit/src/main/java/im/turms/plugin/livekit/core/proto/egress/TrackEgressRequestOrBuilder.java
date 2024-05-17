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

public interface TrackEgressRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.TrackEgressRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string track_id = 2;</code>
     *
     * @return The trackId.
     */
    java.lang.String getTrackId();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>string track_id = 2;</code>
     *
     * @return The bytes for trackId.
     */
    com.google.protobuf.ByteString getTrackIdBytes();

    /**
     * <code>.livekit.DirectFileOutput file = 3;</code>
     *
     * @return Whether the file field is set.
     */
    boolean hasFile();

    /**
     * <code>.livekit.DirectFileOutput file = 3;</code>
     *
     * @return The file.
     */
    im.turms.plugin.livekit.core.proto.egress.DirectFileOutput getFile();

    /**
     * <code>.livekit.DirectFileOutput file = 3;</code>
     */
    im.turms.plugin.livekit.core.proto.egress.DirectFileOutputOrBuilder getFileOrBuilder();

    /**
     * <code>string websocket_url = 4;</code>
     *
     * @return Whether the websocketUrl field is set.
     */
    boolean hasWebsocketUrl();

    /**
     * <code>string websocket_url = 4;</code>
     *
     * @return The websocketUrl.
     */
    java.lang.String getWebsocketUrl();

    /**
     * <code>string websocket_url = 4;</code>
     *
     * @return The bytes for websocketUrl.
     */
    com.google.protobuf.ByteString getWebsocketUrlBytes();

    im.turms.plugin.livekit.core.proto.egress.TrackEgressRequest.OutputCase getOutputCase();
}
