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

package im.turms.server.common.access.client.dto.model.message;

public interface MessageOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.Message)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return Whether the id field is set.
     */
    boolean hasId();

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return The id.
     */
    long getId();

    /**
     * <code>optional int64 delivery_date = 2;</code>
     *
     * @return Whether the deliveryDate field is set.
     */
    boolean hasDeliveryDate();

    /**
     * <code>optional int64 delivery_date = 2;</code>
     *
     * @return The deliveryDate.
     */
    long getDeliveryDate();

    /**
     * <code>optional int64 modification_date = 3;</code>
     *
     * @return Whether the modificationDate field is set.
     */
    boolean hasModificationDate();

    /**
     * <code>optional int64 modification_date = 3;</code>
     *
     * @return The modificationDate.
     */
    long getModificationDate();

    /**
     * <code>optional string text = 4;</code>
     *
     * @return Whether the text field is set.
     */
    boolean hasText();

    /**
     * <code>optional string text = 4;</code>
     *
     * @return The text.
     */
    java.lang.String getText();

    /**
     * <code>optional string text = 4;</code>
     *
     * @return The bytes for text.
     */
    com.google.protobuf.ByteString getTextBytes();

    /**
     * <code>optional int64 sender_id = 5;</code>
     *
     * @return Whether the senderId field is set.
     */
    boolean hasSenderId();

    /**
     * <code>optional int64 sender_id = 5;</code>
     *
     * @return The senderId.
     */
    long getSenderId();

    /**
     * <code>optional int64 group_id = 6;</code>
     *
     * @return Whether the groupId field is set.
     */
    boolean hasGroupId();

    /**
     * <code>optional int64 group_id = 6;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>optional bool is_system_message = 7;</code>
     *
     * @return Whether the isSystemMessage field is set.
     */
    boolean hasIsSystemMessage();

    /**
     * <code>optional bool is_system_message = 7;</code>
     *
     * @return The isSystemMessage.
     */
    boolean getIsSystemMessage();

    /**
     * <code>optional int64 recipient_id = 8;</code>
     *
     * @return Whether the recipientId field is set.
     */
    boolean hasRecipientId();

    /**
     * <code>optional int64 recipient_id = 8;</code>
     *
     * @return The recipientId.
     */
    long getRecipientId();

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @return A list containing the records.
     */
    java.util.List<com.google.protobuf.ByteString> getRecordsList();

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @return The count of records.
     */
    int getRecordsCount();

    /**
     * <code>repeated bytes records = 9;</code>
     *
     * @param index The index of the element to return.
     * @return The records at the given index.
     */
    com.google.protobuf.ByteString getRecords(int index);

    /**
     * <code>optional int32 sequence_id = 10;</code>
     *
     * @return Whether the sequenceId field is set.
     */
    boolean hasSequenceId();

    /**
     * <code>optional int32 sequence_id = 10;</code>
     *
     * @return The sequenceId.
     */
    int getSequenceId();

    /**
     * <code>optional int64 pre_message_id = 11;</code>
     *
     * @return Whether the preMessageId field is set.
     */
    boolean hasPreMessageId();

    /**
     * <code>optional int64 pre_message_id = 11;</code>
     *
     * @return The preMessageId.
     */
    long getPreMessageId();

    /**
     * <code>repeated .im.turms.proto.MessageReactionGroup reaction_groups = 12;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.message.MessageReactionGroup> getReactionGroupsList();

    /**
     * <code>repeated .im.turms.proto.MessageReactionGroup reaction_groups = 12;</code>
     */
    im.turms.server.common.access.client.dto.model.message.MessageReactionGroup getReactionGroups(
            int index);

    /**
     * <code>repeated .im.turms.proto.MessageReactionGroup reaction_groups = 12;</code>
     */
    int getReactionGroupsCount();

    /**
     * <code>repeated .im.turms.proto.MessageReactionGroup reaction_groups = 12;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOrBuilder> getReactionGroupsOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.MessageReactionGroup reaction_groups = 12;</code>
     */
    im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOrBuilder getReactionGroupsOrBuilder(
            int index);

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
