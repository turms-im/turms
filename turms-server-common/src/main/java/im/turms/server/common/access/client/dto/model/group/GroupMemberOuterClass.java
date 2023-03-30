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

package im.turms.server.common.access.client.dto.model.group;

public final class GroupMemberOuterClass {
    private GroupMemberOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupMember_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupMember_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\036model/group/group_member.proto\022\016im.tur"
                + "ms.proto\032 constant/group_member_role.pro"
                + "to\032\032constant/user_status.proto\032\032constant"
                + "/device_type.proto\"\376\002\n\013GroupMember\022\025\n\010gr"
                + "oup_id\030\001 \001(\003H\000\210\001\001\022\024\n\007user_id\030\002 \001(\003H\001\210\001\001\022"
                + "\021\n\004name\030\003 \001(\tH\002\210\001\001\0222\n\004role\030\004 \001(\0162\037.im.tu"
                + "rms.proto.GroupMemberRoleH\003\210\001\001\022\026\n\tjoin_d"
                + "ate\030\005 \001(\003H\004\210\001\001\022\032\n\rmute_end_date\030\006 \001(\003H\005\210"
                + "\001\001\0224\n\013user_status\030\007 \001(\0162\032.im.turms.proto"
                + ".UserStatusH\006\210\001\001\0226\n\022using_device_types\030\010"
                + " \003(\0162\032.im.turms.proto.DeviceTypeB\013\n\t_gro"
                + "up_idB\n\n\010_user_idB\007\n\005_nameB\007\n\005_roleB\014\n\n_"
                + "join_dateB\020\n\016_mute_end_dateB\016\n\014_user_sta"
                + "tusB;\n4im.turms.server.common.access.cli"
                + "ent.dto.model.groupP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupMember_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_GroupMember_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupMember_descriptor,
                        new java.lang.String[]{"GroupId",
                                "UserId",
                                "Name",
                                "Role",
                                "JoinDate",
                                "MuteEndDate",
                                "UserStatus",
                                "UsingDeviceTypes",
                                "GroupId",
                                "UserId",
                                "Name",
                                "Role",
                                "JoinDate",
                                "MuteEndDate",
                                "UserStatus",});
        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}