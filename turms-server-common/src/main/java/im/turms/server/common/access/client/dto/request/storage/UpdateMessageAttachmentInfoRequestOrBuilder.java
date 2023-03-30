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

package im.turms.server.common.access.client.dto.request.storage;

public interface UpdateMessageAttachmentInfoRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateMessageAttachmentInfoRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 attachment_id_num = 1;</code>
     *
     * @return Whether the attachmentIdNum field is set.
     */
    boolean hasAttachmentIdNum();

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 attachment_id_num = 1;</code>
     *
     * @return The attachmentIdNum.
     */
    long getAttachmentIdNum();

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return Whether the attachmentIdStr field is set.
     */
    boolean hasAttachmentIdStr();

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return The attachmentIdStr.
     */
    java.lang.String getAttachmentIdStr();

    /**
     * <code>optional string attachment_id_str = 2;</code>
     *
     * @return The bytes for attachmentIdStr.
     */
    com.google.protobuf.ByteString getAttachmentIdStrBytes();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional int64 user_id_to_share_with = 3;</code>
     *
     * @return Whether the userIdToShareWith field is set.
     */
    boolean hasUserIdToShareWith();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional int64 user_id_to_share_with = 3;</code>
     *
     * @return The userIdToShareWith.
     */
    long getUserIdToShareWith();

    /**
     * <code>optional int64 user_id_to_unshare_with = 4;</code>
     *
     * @return Whether the userIdToUnshareWith field is set.
     */
    boolean hasUserIdToUnshareWith();

    /**
     * <code>optional int64 user_id_to_unshare_with = 4;</code>
     *
     * @return The userIdToUnshareWith.
     */
    long getUserIdToUnshareWith();

    /**
     * <code>optional int64 group_id_to_share_with = 5;</code>
     *
     * @return Whether the groupIdToShareWith field is set.
     */
    boolean hasGroupIdToShareWith();

    /**
     * <code>optional int64 group_id_to_share_with = 5;</code>
     *
     * @return The groupIdToShareWith.
     */
    long getGroupIdToShareWith();

    /**
     * <code>optional int64 group_id_to_unshare_with = 6;</code>
     *
     * @return Whether the groupIdToUnshareWith field is set.
     */
    boolean hasGroupIdToUnshareWith();

    /**
     * <code>optional int64 group_id_to_unshare_with = 6;</code>
     *
     * @return The groupIdToUnshareWith.
     */
    long getGroupIdToUnshareWith();
}