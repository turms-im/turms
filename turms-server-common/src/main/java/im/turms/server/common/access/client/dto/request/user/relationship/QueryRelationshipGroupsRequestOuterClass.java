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

public final class QueryRelationshipGroupsRequestOuterClass {
    private QueryRelationshipGroupsRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                QueryRelationshipGroupsRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryRelationshipGroupsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_QueryRelationshipGroupsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\nArequest/user/relationship/query_relati"
                + "onship_groups_request.proto\022\016im.turms.pr"
                + "oto\032\030model/common/value.proto\"\210\001\n\036QueryR"
                + "elationshipGroupsRequest\022\036\n\021last_updated"
                + "_date\030\001 \001(\003H\000\210\001\001\0220\n\021custom_attributes\030\017 "
                + "\003(\0132\025.im.turms.proto.ValueB\024\n\022_last_upda"
                + "ted_dateBI\nBim.turms.server.common.acces"
                + "s.client.dto.request.user.relationshipP\001"
                + "\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QueryRelationshipGroupsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_QueryRelationshipGroupsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryRelationshipGroupsRequest_descriptor,
                        new java.lang.String[]{"LastUpdatedDate", "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}