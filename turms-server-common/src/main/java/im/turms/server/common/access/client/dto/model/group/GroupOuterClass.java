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

public final class GroupOuterClass {
    private GroupOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Group_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_Group_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\027model/group/group.proto\022\016im.turms.prot"
                + "o\"\245\003\n\005Group\022\017\n\002id\030\001 \001(\003H\000\210\001\001\022\024\n\007type_id\030"
                + "\002 \001(\003H\001\210\001\001\022\027\n\ncreator_id\030\003 \001(\003H\002\210\001\001\022\025\n\010o"
                + "wner_id\030\004 \001(\003H\003\210\001\001\022\021\n\004name\030\005 \001(\tH\004\210\001\001\022\022\n"
                + "\005intro\030\006 \001(\tH\005\210\001\001\022\031\n\014announcement\030\007 \001(\tH"
                + "\006\210\001\001\022\032\n\rcreation_date\030\010 \001(\003H\007\210\001\001\022\036\n\021last"
                + "_updated_date\030\t \001(\003H\010\210\001\001\022\032\n\rmute_end_dat"
                + "e\030\n \001(\003H\t\210\001\001\022\023\n\006active\030\013 \001(\010H\n\210\001\001B\005\n\003_id"
                + "B\n\n\010_type_idB\r\n\013_creator_idB\013\n\t_owner_id"
                + "B\007\n\005_nameB\010\n\006_introB\017\n\r_announcementB\020\n\016"
                + "_creation_dateB\024\n\022_last_updated_dateB\020\n\016"
                + "_mute_end_dateB\t\n\007_activeB;\n4im.turms.se"
                + "rver.common.access.client.dto.model.grou"
                + "pP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_Group_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_Group_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_Group_descriptor,
                        new java.lang.String[]{"Id",
                                "TypeId",
                                "CreatorId",
                                "OwnerId",
                                "Name",
                                "Intro",
                                "Announcement",
                                "CreationDate",
                                "LastUpdatedDate",
                                "MuteEndDate",
                                "Active",
                                "Id",
                                "TypeId",
                                "CreatorId",
                                "OwnerId",
                                "Name",
                                "Intro",
                                "Announcement",
                                "CreationDate",
                                "LastUpdatedDate",
                                "MuteEndDate",
                                "Active",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}