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

package im.turms.server.common.access.client.dto.model.conversation;

public interface ConversationsOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.Conversations)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.conversation.PrivateConversation> getPrivateConversationsList();

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    im.turms.server.common.access.client.dto.model.conversation.PrivateConversation getPrivateConversations(
            int index);

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    int getPrivateConversationsCount();

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder> getPrivateConversationsOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder getPrivateConversationsOrBuilder(
            int index);

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.conversation.GroupConversation> getGroupConversationsList();

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    im.turms.server.common.access.client.dto.model.conversation.GroupConversation getGroupConversations(
            int index);

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    int getGroupConversationsCount();

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder> getGroupConversationsOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder getGroupConversationsOrBuilder(
            int index);
}