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

public final class GroupConversationOuterClass {
    private GroupConversationOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                GroupConversationOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupConversation_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_GroupConversation_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n+model/conversation/group_conversation."
                + "proto\022\016im.turms.proto\032\030model/common/valu"
                + "e.proto\"\355\001\n\021GroupConversation\022\020\n\010group_i"
                + "d\030\001 \001(\003\022Y\n\026member_id_to_read_date\030\002 \003(\0132"
                + "9.im.turms.proto.GroupConversation.Membe"
                + "rIdToReadDateEntry\0220\n\021custom_attributes\030"
                + "\017 \003(\0132\025.im.turms.proto.Value\0329\n\027MemberId"
                + "ToReadDateEntry\022\013\n\003key\030\001 \001(\003\022\r\n\005value\030\002 "
                + "\001(\003:\0028\001BB\n;im.turms.server.common.access"
                + ".client.dto.model.conversationP\001\272\002\000b\006pro"
                + "to3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupConversation_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_GroupConversation_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupConversation_descriptor,
                        new java.lang.String[]{"GroupId",
                                "MemberIdToReadDate",
                                "CustomAttributes",});
        internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor =
                internal_static_im_turms_proto_GroupConversation_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}