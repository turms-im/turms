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

public final class GroupJoinRequestOuterClass {
    private GroupJoinRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupJoinRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_GroupJoinRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n$model/group/group_join_request.proto\022\016"
                + "im.turms.proto\032\035constant/request_status."
                + "proto\"\347\002\n\020GroupJoinRequest\022\017\n\002id\030\001 \001(\003H\000"
                + "\210\001\001\022\032\n\rcreation_date\030\002 \001(\003H\001\210\001\001\022\024\n\007conte"
                + "nt\030\003 \001(\tH\002\210\001\001\0222\n\006status\030\004 \001(\0162\035.im.turms"
                + ".proto.RequestStatusH\003\210\001\001\022\034\n\017expiration_"
                + "date\030\005 \001(\003H\004\210\001\001\022\025\n\010group_id\030\006 \001(\003H\005\210\001\001\022\031"
                + "\n\014requester_id\030\007 \001(\003H\006\210\001\001\022\031\n\014responder_i"
                + "d\030\010 \001(\003H\007\210\001\001B\005\n\003_idB\020\n\016_creation_dateB\n\n"
                + "\010_contentB\t\n\007_statusB\022\n\020_expiration_date"
                + "B\013\n\t_group_idB\017\n\r_requester_idB\017\n\r_respo"
                + "nder_idB;\n4im.turms.server.common.access"
                + ".client.dto.model.groupP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupJoinRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupJoinRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupJoinRequest_descriptor,
                        new java.lang.String[]{"Id",
                                "CreationDate",
                                "Content",
                                "Status",
                                "ExpirationDate",
                                "GroupId",
                                "RequesterId",
                                "ResponderId",
                                "Id",
                                "CreationDate",
                                "Content",
                                "Status",
                                "ExpirationDate",
                                "GroupId",
                                "RequesterId",
                                "ResponderId",});
        im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}