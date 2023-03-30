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

public final class ConversationsOuterClass {
    private ConversationsOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Conversations_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_Conversations_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n&model/conversation/conversations.proto"
                + "\022\016im.turms.proto\032-model/conversation/pri"
                + "vate_conversation.proto\032+model/conversat"
                + "ion/group_conversation.proto\"\223\001\n\rConvers"
                + "ations\022B\n\025private_conversations\030\001 \003(\0132#."
                + "im.turms.proto.PrivateConversation\022>\n\023gr"
                + "oup_conversations\030\002 \003(\0132!.im.turms.proto"
                + ".GroupConversationBB\n;im.turms.server.co"
                + "mmon.access.client.dto.model.conversatio"
                + "nP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_Conversations_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_Conversations_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_Conversations_descriptor,
                        new java.lang.String[]{"PrivateConversations", "GroupConversations",});
        im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}