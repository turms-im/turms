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

public interface MessagesWithTotalOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.MessagesWithTotal)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 total = 1;</code>
     *
     * @return The total.
     */
    int getTotal();

    /**
     * <code>bool is_group_message = 2;</code>
     *
     * @return The isGroupMessage.
     */
    boolean getIsGroupMessage();

    /**
     * <code>int64 from_id = 3;</code>
     *
     * @return The fromId.
     */
    long getFromId();

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.message.Message> getMessagesList();

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    im.turms.server.common.access.client.dto.model.message.Message getMessages(int index);

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    int getMessagesCount();

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessageOrBuilder> getMessagesOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    im.turms.server.common.access.client.dto.model.message.MessageOrBuilder getMessagesOrBuilder(
            int index);
}