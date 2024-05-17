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

public interface UpdateParticipantRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.UpdateParticipantRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string room = 1;</code>
     *
     * @return The room.
     */
    java.lang.String getRoom();

    /**
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
     * metadata to update. skipping updates if left empty
     * </pre>
     *
     * <code>string metadata = 3;</code>
     *
     * @return The metadata.
     */
    java.lang.String getMetadata();

    /**
     * <pre>
     * metadata to update. skipping updates if left empty
     * </pre>
     *
     * <code>string metadata = 3;</code>
     *
     * @return The bytes for metadata.
     */
    com.google.protobuf.ByteString getMetadataBytes();

    /**
     * <pre>
     * set to update the participant's permissions
     * </pre>
     *
     * <code>.livekit.ParticipantPermission permission = 4;</code>
     *
     * @return Whether the permission field is set.
     */
    boolean hasPermission();

    /**
     * <pre>
     * set to update the participant's permissions
     * </pre>
     *
     * <code>.livekit.ParticipantPermission permission = 4;</code>
     *
     * @return The permission.
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantPermission getPermission();

    /**
     * <pre>
     * set to update the participant's permissions
     * </pre>
     *
     * <code>.livekit.ParticipantPermission permission = 4;</code>
     */
    im.turms.plugin.livekit.core.proto.models.ParticipantPermissionOrBuilder getPermissionOrBuilder();

    /**
     * <pre>
     * display name to update
     * </pre>
     *
     * <code>string name = 5;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <pre>
     * display name to update
     * </pre>
     *
     * <code>string name = 5;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();
}
