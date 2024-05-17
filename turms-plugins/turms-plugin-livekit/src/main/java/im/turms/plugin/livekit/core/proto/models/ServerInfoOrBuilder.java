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

public interface ServerInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ServerInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.livekit.ServerInfo.Edition edition = 1;</code>
     *
     * @return The enum numeric value on the wire for edition.
     */
    int getEditionValue();

    /**
     * <code>.livekit.ServerInfo.Edition edition = 1;</code>
     *
     * @return The edition.
     */
    im.turms.plugin.livekit.core.proto.models.ServerInfo.Edition getEdition();

    /**
     * <code>string version = 2;</code>
     *
     * @return The version.
     */
    java.lang.String getVersion();

    /**
     * <code>string version = 2;</code>
     *
     * @return The bytes for version.
     */
    com.google.protobuf.ByteString getVersionBytes();

    /**
     * <code>int32 protocol = 3;</code>
     *
     * @return The protocol.
     */
    int getProtocol();

    /**
     * <code>string region = 4;</code>
     *
     * @return The region.
     */
    java.lang.String getRegion();

    /**
     * <code>string region = 4;</code>
     *
     * @return The bytes for region.
     */
    com.google.protobuf.ByteString getRegionBytes();

    /**
     * <code>string node_id = 5;</code>
     *
     * @return The nodeId.
     */
    java.lang.String getNodeId();

    /**
     * <code>string node_id = 5;</code>
     *
     * @return The bytes for nodeId.
     */
    com.google.protobuf.ByteString getNodeIdBytes();

    /**
     * <pre>
     * additional debugging information. sent only if server is in development mode
     * </pre>
     *
     * <code>string debug_info = 6;</code>
     *
     * @return The debugInfo.
     */
    java.lang.String getDebugInfo();

    /**
     * <pre>
     * additional debugging information. sent only if server is in development mode
     * </pre>
     *
     * <code>string debug_info = 6;</code>
     *
     * @return The bytes for debugInfo.
     */
    com.google.protobuf.ByteString getDebugInfoBytes();
}
