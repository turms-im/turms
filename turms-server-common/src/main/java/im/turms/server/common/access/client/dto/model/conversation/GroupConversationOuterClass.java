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

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupConversation_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupConversation_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n+model/conversation/group_conversation."
                + "proto\022\016im.turms.proto\"\273\001\n\021GroupConversat"
                + "ion\022\020\n\010group_id\030\001 \001(\003\022Y\n\026member_id_to_re"
                + "ad_date\030\002 \003(\01329.im.turms.proto.GroupConv"
                + "ersation.MemberIdToReadDateEntry\0329\n\027Memb"
                + "erIdToReadDateEntry\022\013\n\003key\030\001 \001(\003\022\r\n\005valu"
                + "e\030\002 \001(\003:\0028\001BB\n;im.turms.server.common.ac"
                + "cess.client.dto.model.conversationP\001\272\002\000b"
                + "\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_GroupConversation_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupConversation_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupConversation_descriptor,
                        new java.lang.String[]{"GroupId", "MemberIdToReadDate",});
        internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor =
                internal_static_im_turms_proto_GroupConversation_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}