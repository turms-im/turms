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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                ConversationsOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Conversations_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_Conversations_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n&model/conversation/conversations.proto"
                + "\022\016im.turms.proto\032+model/conversation/gro"
                + "up_conversation.proto\032-model/conversatio"
                + "n/private_conversation.proto\"\223\001\n\rConvers"
                + "ations\022B\n\025private_conversations\030\001 \003(\0132#."
                + "im.turms.proto.PrivateConversation\022>\n\023gr"
                + "oup_conversations\030\002 \003(\0132!.im.turms.proto"
                + ".GroupConversationBB\n;im.turms.server.co"
                + "mmon.access.client.dto.model.conversatio"
                + "nP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_Conversations_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_Conversations_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_Conversations_descriptor,
                        new java.lang.String[]{"PrivateConversations", "GroupConversations",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}