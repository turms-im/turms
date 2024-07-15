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

package im.turms.client.model.proto.request.conference;

public interface QueryMeetingsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QueryMeetingsRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @return A list containing the ids.
     */
    java.util.List<java.lang.Long> getIdsList();

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @return The count of ids.
     */
    int getIdsCount();

    /**
     * <code>repeated int64 ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The ids at the given index.
     */
    long getIds(int index);

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @return A list containing the creatorIds.
     */
    java.util.List<java.lang.Long> getCreatorIdsList();

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @return The count of creatorIds.
     */
    int getCreatorIdsCount();

    /**
     * <code>repeated int64 creator_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The creatorIds at the given index.
     */
    long getCreatorIds(int index);

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @return A list containing the userIds.
     */
    java.util.List<java.lang.Long> getUserIdsList();

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @return The count of userIds.
     */
    int getUserIdsCount();

    /**
     * <code>repeated int64 user_ids = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The userIds at the given index.
     */
    long getUserIds(int index);

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @return A list containing the groupIds.
     */
    java.util.List<java.lang.Long> getGroupIdsList();

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @return The count of groupIds.
     */
    int getGroupIdsCount();

    /**
     * <code>repeated int64 group_ids = 4;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    long getGroupIds(int index);

    /**
     * <code>optional int64 creation_date_start = 5;</code>
     *
     * @return Whether the creationDateStart field is set.
     */
    boolean hasCreationDateStart();

    /**
     * <code>optional int64 creation_date_start = 5;</code>
     *
     * @return The creationDateStart.
     */
    long getCreationDateStart();

    /**
     * <code>optional int64 creation_date_end = 6;</code>
     *
     * @return Whether the creationDateEnd field is set.
     */
    boolean hasCreationDateEnd();

    /**
     * <code>optional int64 creation_date_end = 6;</code>
     *
     * @return The creationDateEnd.
     */
    long getCreationDateEnd();

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return Whether the skip field is set.
     */
    boolean hasSkip();

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return The skip.
     */
    int getSkip();

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return Whether the limit field is set.
     */
    boolean hasLimit();

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return The limit.
     */
    int getLimit();

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
