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

public final class CreateGroupMembersRequestOuterClass {
    private CreateGroupMembersRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateGroupMembersRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_CreateGroupMembersRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n7request/group/member/create_group_memb"
                + "ers_request.proto\022\016im.turms.proto\032 const"
                + "ant/group_member_role.proto\"\306\001\n\031CreateGr"
                + "oupMembersRequest\022\020\n\010group_id\030\001 \001(\003\022\020\n\010u"
                + "ser_ids\030\002 \003(\003\022\021\n\004name\030\003 \001(\tH\000\210\001\001\0222\n\004role"
                + "\030\004 \001(\0162\037.im.turms.proto.GroupMemberRoleH"
                + "\001\210\001\001\022\032\n\rmute_end_date\030\005 \001(\003H\002\210\001\001B\007\n\005_nam"
                + "eB\007\n\005_roleB\020\n\016_mute_end_dateBD\n=im.turms"
                + ".server.common.access.client.dto.request"
                + ".group.memberP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_CreateGroupMembersRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_CreateGroupMembersRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateGroupMembersRequest_descriptor,
                        new java.lang.String[]{"GroupId",
                                "UserIds",
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