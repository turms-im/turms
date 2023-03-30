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

package im.turms.server.common.access.client.dto.request.group;

public final class UpdateGroupRequestOuterClass {
    private UpdateGroupRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateGroupRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateGroupRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n(request/group/update_group_request.pro"
                + "to\022\016im.turms.proto\"\350\002\n\022UpdateGroupReques"
                + "t\022\020\n\010group_id\030\001 \001(\003\022 \n\023quit_after_transf"
                + "er\030\002 \001(\010H\000\210\001\001\022\021\n\004name\030\003 \001(\tH\001\210\001\001\022\022\n\005intr"
                + "o\030\004 \001(\tH\002\210\001\001\022\031\n\014announcement\030\005 \001(\tH\003\210\001\001\022"
                + "\026\n\tmin_score\030\006 \001(\005H\004\210\001\001\022\024\n\007type_id\030\007 \001(\003"
                + "H\005\210\001\001\022\032\n\rmute_end_date\030\010 \001(\003H\006\210\001\001\022\031\n\014suc"
                + "cessor_id\030\t \001(\003H\007\210\001\001B\026\n\024_quit_after_tran"
                + "sferB\007\n\005_nameB\010\n\006_introB\017\n\r_announcement"
                + "B\014\n\n_min_scoreB\n\n\010_type_idB\020\n\016_mute_end_"
                + "dateB\017\n\r_successor_idB=\n6im.turms.server"
                + ".common.access.client.dto.request.groupP"
                + "\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_UpdateGroupRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateGroupRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateGroupRequest_descriptor,
                        new java.lang.String[]{"GroupId",
                                "QuitAfterTransfer",
                                "Name",
                                "Intro",
                                "Announcement",
                                "MinScore",
                                "TypeId",
                                "MuteEndDate",
                                "SuccessorId",
                                "QuitAfterTransfer",
                                "Name",
                                "Intro",
                                "Announcement",
                                "MinScore",
                                "TypeId",
                                "MuteEndDate",
                                "SuccessorId",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}