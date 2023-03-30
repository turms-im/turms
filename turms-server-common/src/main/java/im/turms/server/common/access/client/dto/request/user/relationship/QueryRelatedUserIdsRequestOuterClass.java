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

package im.turms.server.common.access.client.dto.request.user.relationship;

public final class QueryRelatedUserIdsRequestOuterClass {
    private QueryRelatedUserIdsRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryRelatedUserIdsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_QueryRelatedUserIdsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n>request/user/relationship/query_relate"
                + "d_user_ids_request.proto\022\016im.turms.proto"
                + "\"\213\001\n\032QueryRelatedUserIdsRequest\022\024\n\007block"
                + "ed\030\001 \001(\010H\000\210\001\001\022\025\n\rgroup_indexes\030\002 \003(\005\022\036\n\021"
                + "last_updated_date\030\003 \001(\003H\001\210\001\001B\n\n\010_blocked"
                + "B\024\n\022_last_updated_dateBI\nBim.turms.serve"
                + "r.common.access.client.dto.request.user."
                + "relationshipP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_QueryRelatedUserIdsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_QueryRelatedUserIdsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryRelatedUserIdsRequest_descriptor,
                        new java.lang.String[]{"Blocked",
                                "GroupIndexes",
                                "LastUpdatedDate",
                                "Blocked",
                                "LastUpdatedDate",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}