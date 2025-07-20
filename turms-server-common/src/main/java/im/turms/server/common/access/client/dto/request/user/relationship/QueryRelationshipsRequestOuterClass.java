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

public final class QueryRelationshipsRequestOuterClass {
    private QueryRelationshipsRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                QueryRelationshipsRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryRelationshipsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_QueryRelationshipsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n;request/user/relationship/query_relati"
                + "onships_request.proto\022\016im.turms.proto\032\030m"
                + "odel/common/value.proto\"\371\001\n\031QueryRelatio"
                + "nshipsRequest\022\020\n\010user_ids\030\001 \003(\003\022\024\n\007block"
                + "ed\030\002 \001(\010H\000\210\001\001\022\025\n\rgroup_indexes\030\003 \003(\005\022\036\n\021"
                + "last_updated_date\030\004 \001(\003H\001\210\001\001\022)\n!user_ids"
                + "_for_common_relationships\030\005 \003(\003\0220\n\021custo"
                + "m_attributes\030\017 \003(\0132\025.im.turms.proto.Valu"
                + "eB\n\n\010_blockedB\024\n\022_last_updated_dateBI\nBi"
                + "m.turms.server.common.access.client.dto."
                + "request.user.relationshipP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QueryRelationshipsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_QueryRelationshipsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryRelationshipsRequest_descriptor,
                        new java.lang.String[]{"UserIds",
                                "Blocked",
                                "GroupIndexes",
                                "LastUpdatedDate",
                                "UserIdsForCommonRelationships",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}