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

public final class CreateGroupRequestOuterClass {
    private CreateGroupRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateGroupRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_CreateGroupRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n(request/group/create_group_request.pro"
                + "to\022\016im.turms.proto\"\342\001\n\022CreateGroupReques"
                + "t\022\014\n\004name\030\001 \001(\t\022\022\n\005intro\030\002 \001(\tH\000\210\001\001\022\031\n\014a"
                + "nnouncement\030\003 \001(\tH\001\210\001\001\022\026\n\tmin_score\030\004 \001("
                + "\005H\002\210\001\001\022\024\n\007type_id\030\005 \001(\003H\003\210\001\001\022\032\n\rmute_end"
                + "_date\030\006 \001(\003H\004\210\001\001B\010\n\006_introB\017\n\r_announcem"
                + "entB\014\n\n_min_scoreB\n\n\010_type_idB\020\n\016_mute_e"
                + "nd_dateB=\n6im.turms.server.common.access"
                + ".client.dto.request.groupP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_CreateGroupRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_CreateGroupRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateGroupRequest_descriptor,
                        new java.lang.String[]{"Name",
                                "Intro",
                                "Announcement",
                                "MinScore",
                                "TypeId",
                                "MuteEndDate",
                                "Intro",
                                "Announcement",
                                "MinScore",
                                "TypeId",
                                "MuteEndDate",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}