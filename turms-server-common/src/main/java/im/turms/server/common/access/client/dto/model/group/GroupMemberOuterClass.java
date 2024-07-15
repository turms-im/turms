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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                GroupMemberOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupMember_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_GroupMember_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\036model/group/group_member.proto\022\016im.tur"
                + "ms.proto\032\032constant/device_type.proto\032 co"
                + "nstant/group_member_role.proto\032\032constant"
                + "/user_status.proto\032\030model/common/value.p"
                + "roto\"\260\003\n\013GroupMember\022\025\n\010group_id\030\001 \001(\003H\000"
                + "\210\001\001\022\024\n\007user_id\030\002 \001(\003H\001\210\001\001\022\021\n\004name\030\003 \001(\tH"
                + "\002\210\001\001\0222\n\004role\030\004 \001(\0162\037.im.turms.proto.Grou"
                + "pMemberRoleH\003\210\001\001\022\026\n\tjoin_date\030\005 \001(\003H\004\210\001\001"
                + "\022\032\n\rmute_end_date\030\006 \001(\003H\005\210\001\001\0224\n\013user_sta"
                + "tus\030\007 \001(\0162\032.im.turms.proto.UserStatusH\006\210"
                + "\001\001\0226\n\022using_device_types\030\010 \003(\0162\032.im.turm"
                + "s.proto.DeviceType\0220\n\021custom_attributes\030"
                + "\017 \003(\0132\025.im.turms.proto.ValueB\013\n\t_group_i"
                + "dB\n\n\010_user_idB\007\n\005_nameB\007\n\005_roleB\014\n\n_join"
                + "_dateB\020\n\016_mute_end_dateB\016\n\014_user_statusB"
                + ";\n4im.turms.server.common.access.client."
                + "dto.model.groupP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupMember_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_GroupMember_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupMember_descriptor,
                        new java.lang.String[]{"GroupId",
                                "UserId",
                                "Name",
                                "Role",
                                "JoinDate",
                                "MuteEndDate",
                                "UserStatus",
                                "UsingDeviceTypes",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.GroupMemberRoleOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}