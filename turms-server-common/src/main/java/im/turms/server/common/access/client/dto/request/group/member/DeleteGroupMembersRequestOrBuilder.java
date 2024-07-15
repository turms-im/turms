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

package im.turms.server.common.access.client.dto.request.group.member;

public interface DeleteGroupMembersRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.DeleteGroupMembersRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @return A list containing the memberIds.
     */
    java.util.List<java.lang.Long> getMemberIdsList();

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @return The count of memberIds.
     */
    int getMemberIdsCount();

    /**
     * <code>repeated int64 member_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The memberIds at the given index.
     */
    long getMemberIds(int index);

    /**
     * <code>optional int64 successor_id = 3;</code>
     *
     * @return Whether the successorId field is set.
     */
    boolean hasSuccessorId();

    /**
     * <code>optional int64 successor_id = 3;</code>
     *
     * @return The successorId.
     */
    long getSuccessorId();

    /**
     * <code>optional bool quit_after_transfer = 4;</code>
     *
     * @return Whether the quitAfterTransfer field is set.
     */
    boolean hasQuitAfterTransfer();

    /**
     * <code>optional bool quit_after_transfer = 4;</code>
     *
     * @return The quitAfterTransfer.
     */
    boolean getQuitAfterTransfer();

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
