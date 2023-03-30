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

package im.turms.server.common.access.client.dto.request.message;

public interface UpdateMessageRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateMessageRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 message_id = 1;</code>
     *
     * @return The messageId.
     */
    long getMessageId();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @return Whether the text field is set.
     */
    boolean hasText();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @return The text.
     */
    java.lang.String getText();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string text = 2;</code>
     *
     * @return The bytes for text.
     */
    com.google.protobuf.ByteString getTextBytes();

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @return A list containing the records.
     */
    java.util.List<com.google.protobuf.ByteString> getRecordsList();

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @return The count of records.
     */
    int getRecordsCount();

    /**
     * <code>repeated bytes records = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The records at the given index.
     */
    com.google.protobuf.ByteString getRecords(int index);

    /**
     * <code>optional int64 recall_date = 4;</code>
     *
     * @return Whether the recallDate field is set.
     */
    boolean hasRecallDate();

    /**
     * <code>optional int64 recall_date = 4;</code>
     *
     * @return The recallDate.
     */
    long getRecallDate();
}