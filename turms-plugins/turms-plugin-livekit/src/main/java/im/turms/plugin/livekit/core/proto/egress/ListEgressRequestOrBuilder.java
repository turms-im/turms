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

public interface ListEgressRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ListEgressRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * (optional, filter by room name)
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The roomName.
     */
    java.lang.String getRoomName();

    /**
     * <pre>
     * (optional, filter by room name)
     * </pre>
     *
     * <code>string room_name = 1;</code>
     *
     * @return The bytes for roomName.
     */
    com.google.protobuf.ByteString getRoomNameBytes();

    /**
     * <pre>
     * (optional, filter by egress ID)
     * </pre>
     *
     * <code>string egress_id = 2;</code>
     *
     * @return The egressId.
     */
    java.lang.String getEgressId();

    /**
     * <pre>
     * (optional, filter by egress ID)
     * </pre>
     *
     * <code>string egress_id = 2;</code>
     *
     * @return The bytes for egressId.
     */
    com.google.protobuf.ByteString getEgressIdBytes();

    /**
     * <pre>
     * (optional, list active egress only)
     * </pre>
     *
     * <code>bool active = 3;</code>
     *
     * @return The active.
     */
    boolean getActive();
}
