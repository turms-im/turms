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

package im.turms.server.common.access.client.dto.model.user;

public final class UserRelationshipsWithVersionOuterClass {
    private UserRelationshipsWithVersionOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                UserRelationshipsWithVersionOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserRelationshipsWithVersion_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserRelationshipsWithVersion_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n0model/user/user_relationships_with_ver"
                + "sion.proto\022\016im.turms.proto\032\"model/user/u"
                + "ser_relationship.proto\"\222\001\n\034UserRelations"
                + "hipsWithVersion\022<\n\022user_relationships\030\001 "
                + "\003(\0132 .im.turms.proto.UserRelationship\022\036\n"
                + "\021last_updated_date\030\002 \001(\003H\000\210\001\001B\024\n\022_last_u"
                + "pdated_dateB:\n3im.turms.server.common.ac"
                + "cess.client.dto.model.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserRelationshipsWithVersion_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_UserRelationshipsWithVersion_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserRelationshipsWithVersion_descriptor,
                        new java.lang.String[]{"UserRelationships", "LastUpdatedDate",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.user.UserRelationshipOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}