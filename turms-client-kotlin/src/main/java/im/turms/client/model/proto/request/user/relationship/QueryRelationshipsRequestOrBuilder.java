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

package im.turms.client.model.proto.request.user.relationship;

public interface QueryRelationshipsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QueryRelationshipsRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return A list containing the userIds.
     */
    java.util.List<java.lang.Long> getUserIdsList();

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @return The count of userIds.
     */
    int getUserIdsCount();

    /**
     * <code>repeated int64 user_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    long getUserIds(int index);

    /**
     * <code>optional bool blocked = 2;</code>
     *
     * @return Whether the blocked field is set.
     */
    boolean hasBlocked();

    /**
     * <code>optional bool blocked = 2;</code>
     *
     * @return The blocked.
     */
    boolean getBlocked();

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @return A list containing the groupIndexes.
     */
    java.util.List<java.lang.Integer> getGroupIndexesList();

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @return The count of groupIndexes.
     */
    int getGroupIndexesCount();

    /**
     * <code>repeated int32 group_indexes = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIndexes at the given index.
     */
    int getGroupIndexes(int index);

    /**
     * <code>optional int64 last_updated_date = 4;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    boolean hasLastUpdatedDate();

    /**
     * <code>optional int64 last_updated_date = 4;</code>
     *
     * @return The lastUpdatedDate.
     */
    long getLastUpdatedDate();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.client.model.proto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();
}