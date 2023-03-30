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

public final class UpdateGroupMemberRequestOuterClass {
    private UpdateGroupMemberRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateGroupMemberRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateGroupMemberRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n6request/group/member/update_group_memb"
                + "er_request.proto\022\016im.turms.proto\032 consta"
                + "nt/group_member_role.proto\"\306\001\n\030UpdateGro"
                + "upMemberRequest\022\020\n\010group_id\030\001 \001(\003\022\021\n\tmem"
                + "ber_id\030\002 \001(\003\022\021\n\004name\030\003 \001(\tH\000\210\001\001\0222\n\004role\030"
                + "\004 \001(\0162\037.im.turms.proto.GroupMemberRoleH\001"
                + "\210\001\001\022\032\n\rmute_end_date\030\005 \001(\003H\002\210\001\001B\007\n\005_name"
                + "B\007\n\005_roleB\020\n\016_mute_end_dateBD\n=im.turms."
                + "server.common.access.client.dto.request."
                + "group.memberP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateGroupMemberRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateGroupMemberRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateGroupMemberRequest_descriptor,
                        new java.lang.String[]{"GroupId",
                                "MemberId",
                                "Name",
                                "Role",
                                "MuteEndDate",
                                "Name",
                                "Role",
                                "MuteEndDate",});
        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}