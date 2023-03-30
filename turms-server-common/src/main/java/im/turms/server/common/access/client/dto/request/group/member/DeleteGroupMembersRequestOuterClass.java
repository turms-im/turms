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

package im.turms.server.common.access.client.dto.request.group.member;

public final class DeleteGroupMembersRequestOuterClass {
    private DeleteGroupMembersRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_DeleteGroupMembersRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_DeleteGroupMembersRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n7request/group/member/delete_group_memb"
                + "ers_request.proto\022\016im.turms.proto\"\247\001\n\031De"
                + "leteGroupMembersRequest\022\020\n\010group_id\030\001 \001("
                + "\003\022\022\n\nmember_ids\030\002 \003(\003\022\031\n\014successor_id\030\003 "
                + "\001(\003H\000\210\001\001\022 \n\023quit_after_transfer\030\004 \001(\010H\001\210"
                + "\001\001B\017\n\r_successor_idB\026\n\024_quit_after_trans"
                + "ferBD\n=im.turms.server.common.access.cli"
                + "ent.dto.request.group.memberP\001\272\002\000b\006proto"
                + "3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_DeleteGroupMembersRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_DeleteGroupMembersRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_DeleteGroupMembersRequest_descriptor,
                        new java.lang.String[]{"GroupId",
                                "MemberIds",
                                "SuccessorId",
                                "QuitAfterTransfer",
                                "SuccessorId",
                                "QuitAfterTransfer",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}