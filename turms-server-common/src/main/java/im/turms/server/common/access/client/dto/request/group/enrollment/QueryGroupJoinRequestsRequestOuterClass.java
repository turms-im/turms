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

package im.turms.server.common.access.client.dto.request.group.enrollment;

public final class QueryGroupJoinRequestsRequestOuterClass {
    private QueryGroupJoinRequestsRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryGroupJoinRequestsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_QueryGroupJoinRequestsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n@request/group/enrollment/query_group_j"
                + "oin_requests_request.proto\022\016im.turms.pro"
                + "to\"y\n\035QueryGroupJoinRequestsRequest\022\025\n\010g"
                + "roup_id\030\001 \001(\003H\000\210\001\001\022\036\n\021last_updated_date\030"
                + "\002 \001(\003H\001\210\001\001B\013\n\t_group_idB\024\n\022_last_updated"
                + "_dateBH\nAim.turms.server.common.access.c"
                + "lient.dto.request.group.enrollmentP\001\272\002\000b"
                + "\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_QueryGroupJoinRequestsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_QueryGroupJoinRequestsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryGroupJoinRequestsRequest_descriptor,
                        new java.lang.String[]{"GroupId",
                                "LastUpdatedDate",
                                "GroupId",
                                "LastUpdatedDate",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}