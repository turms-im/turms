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

package im.turms.client.model.proto.request.storage;

public interface QueryMessageAttachmentInfosRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QueryMessageAttachmentInfosRequest)
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
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return A list containing the groupIds.
     */
    java.util.List<java.lang.Long> getGroupIdsList();

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @return The count of groupIds.
     */
    int getGroupIdsCount();

    /**
     * <code>repeated int64 group_ids = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    long getGroupIds(int index);

    /**
     * <code>optional int64 creation_date_start = 3;</code>
     *
     * @return Whether the creationDateStart field is set.
     */
    boolean hasCreationDateStart();

    /**
     * <code>optional int64 creation_date_start = 3;</code>
     *
     * @return The creationDateStart.
     */
    long getCreationDateStart();

    /**
     * <code>optional int64 creation_date_end = 4;</code>
     *
     * @return Whether the creationDateEnd field is set.
     */
    boolean hasCreationDateEnd();

    /**
     * <code>optional int64 creation_date_end = 4;</code>
     *
     * @return The creationDateEnd.
     */
    long getCreationDateEnd();

    /**
     * <code>optional bool in_private_conversation = 5;</code>
     *
     * @return Whether the inPrivateConversation field is set.
     */
    boolean hasInPrivateConversation();

    /**
     * <code>optional bool in_private_conversation = 5;</code>
     *
     * @return The inPrivateConversation.
     */
    boolean getInPrivateConversation();

    /**
     * <code>optional bool are_shared_by_me = 6;</code>
     *
     * @return Whether the areSharedByMe field is set.
     */
    boolean hasAreSharedByMe();

    /**
     * <code>optional bool are_shared_by_me = 6;</code>
     *
     * @return The areSharedByMe.
     */
    boolean getAreSharedByMe();
}