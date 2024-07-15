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

package im.turms.server.common.access.client.dto.request.user.relationship;

public interface UpdateRelationshipRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateRelationshipRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    long getUserId();

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>optional bool blocked = 2;</code>
     *
     * @return Whether the blocked field is set.
     */
    boolean hasBlocked();

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>optional bool blocked = 2;</code>
     *
     * @return The blocked.
     */
    boolean getBlocked();

    /**
     * <code>optional int32 new_group_index = 3;</code>
     *
     * @return Whether the newGroupIndex field is set.
     */
    boolean hasNewGroupIndex();

    /**
     * <code>optional int32 new_group_index = 3;</code>
     *
     * @return The newGroupIndex.
     */
    int getNewGroupIndex();

    /**
     * <code>optional int32 delete_group_index = 4;</code>
     *
     * @return Whether the deleteGroupIndex field is set.
     */
    boolean hasDeleteGroupIndex();

    /**
     * <code>optional int32 delete_group_index = 4;</code>
     *
     * @return The deleteGroupIndex.
     */
    int getDeleteGroupIndex();

    /**
     * <code>optional string name = 5;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>optional string name = 5;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>optional string name = 5;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index);
}
