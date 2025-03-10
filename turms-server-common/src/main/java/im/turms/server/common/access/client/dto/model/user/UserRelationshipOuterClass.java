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

public final class UserRelationshipOuterClass {
    private UserRelationshipOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                UserRelationshipOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserRelationship_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserRelationship_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\"model/user/user_relationship.proto\022\016im"
                + ".turms.proto\032\030model/common/value.proto\"\334"
                + "\002\n\020UserRelationship\022\025\n\010owner_id\030\001 \001(\003H\000\210"
                + "\001\001\022\034\n\017related_user_id\030\002 \001(\003H\001\210\001\001\022\027\n\nbloc"
                + "k_date\030\003 \001(\003H\002\210\001\001\022\030\n\013group_index\030\004 \001(\003H\003"
                + "\210\001\001\022\037\n\022establishment_date\030\005 \001(\003H\004\210\001\001\022\021\n\004"
                + "name\030\006 \001(\tH\005\210\001\001\022\021\n\004note\030\007 \001(\tH\006\210\001\001\0220\n\021cu"
                + "stom_attributes\030\017 \003(\0132\025.im.turms.proto.V"
                + "alueB\013\n\t_owner_idB\022\n\020_related_user_idB\r\n"
                + "\013_block_dateB\016\n\014_group_indexB\025\n\023_establi"
                + "shment_dateB\007\n\005_nameB\007\n\005_noteB:\n3im.turm"
                + "s.server.common.access.client.dto.model."
                + "userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserRelationship_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_UserRelationship_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserRelationship_descriptor,
                        new java.lang.String[]{"OwnerId",
                                "RelatedUserId",
                                "BlockDate",
                                "GroupIndex",
                                "EstablishmentDate",
                                "Name",
                                "Note",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}