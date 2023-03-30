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

public interface MessagesWithTotalListOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.MessagesWithTotalList)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.message.MessagesWithTotal> getMessagesWithTotalListList();

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    im.turms.server.common.access.client.dto.model.message.MessagesWithTotal getMessagesWithTotalList(
            int index);

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    int getMessagesWithTotalListCount();

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder> getMessagesWithTotalListOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.MessagesWithTotal messages_with_total_list = 1;</code>
     */
    im.turms.server.common.access.client.dto.model.message.MessagesWithTotalOrBuilder getMessagesWithTotalListOrBuilder(
            int index);
}