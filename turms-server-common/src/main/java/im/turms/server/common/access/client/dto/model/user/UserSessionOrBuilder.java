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

package im.turms.server.common.access.client.dto.model.user;

public interface UserSessionOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UserSession)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string session_id = 1;</code>
     *
     * @return The sessionId.
     */
    java.lang.String getSessionId();

    /**
     * <code>string session_id = 1;</code>
     *
     * @return The bytes for sessionId.
     */
    com.google.protobuf.ByteString getSessionIdBytes();

    /**
     * <code>string server_id = 2;</code>
     *
     * @return The serverId.
     */
    java.lang.String getServerId();

    /**
     * <code>string server_id = 2;</code>
     *
     * @return The bytes for serverId.
     */
    com.google.protobuf.ByteString getServerIdBytes();
}