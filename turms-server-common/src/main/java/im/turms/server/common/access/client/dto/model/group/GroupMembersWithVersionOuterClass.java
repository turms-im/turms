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

public final class GroupMembersWithVersionOuterClass {
    private GroupMembersWithVersionOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupMembersWithVersion_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupMembersWithVersion_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n,model/group/group_members_with_version"
                + ".proto\022\016im.turms.proto\032\036model/group/grou"
                + "p_member.proto\"\203\001\n\027GroupMembersWithVersi"
                + "on\0222\n\rgroup_members\030\001 \003(\0132\033.im.turms.pro"
                + "to.GroupMember\022\036\n\021last_updated_date\030\002 \001("
                + "\003H\000\210\001\001B\024\n\022_last_updated_dateB;\n4im.turms"
                + ".server.common.access.client.dto.model.g"
                + "roupP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupMembersWithVersion_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupMembersWithVersion_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupMembersWithVersion_descriptor,
                        new java.lang.String[]{"GroupMembers",
                                "LastUpdatedDate",
                                "LastUpdatedDate",});
        im.turms.server.common.access.client.dto.model.group.GroupMemberOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}