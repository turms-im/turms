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

public final class UpdateRelationshipRequestOuterClass {
    private UpdateRelationshipRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateRelationshipRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateRelationshipRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n;request/user/relationship/update_relat"
                + "ionship_request.proto\022\016im.turms.proto\"\270\001"
                + "\n\031UpdateRelationshipRequest\022\017\n\007user_id\030\001"
                + " \001(\003\022\024\n\007blocked\030\002 \001(\010H\000\210\001\001\022\034\n\017new_group_"
                + "index\030\003 \001(\005H\001\210\001\001\022\037\n\022delete_group_index\030\004"
                + " \001(\005H\002\210\001\001B\n\n\010_blockedB\022\n\020_new_group_inde"
                + "xB\025\n\023_delete_group_indexBI\nBim.turms.ser"
                + "ver.common.access.client.dto.request.use"
                + "r.relationshipP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_UpdateRelationshipRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateRelationshipRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateRelationshipRequest_descriptor,
                        new java.lang.String[]{"UserId",
                                "Blocked",
                                "NewGroupIndex",
                                "DeleteGroupIndex",
                                "Blocked",
                                "NewGroupIndex",
                                "DeleteGroupIndex",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}