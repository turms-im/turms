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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                GroupJoinRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_GroupJoinRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_GroupJoinRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n$model/group/group_join_request.proto\022\016"
                + "im.turms.proto\032\035constant/request_status."
                + "proto\032\030model/common/value.proto\"\231\003\n\020Grou"
                + "pJoinRequest\022\017\n\002id\030\001 \001(\003H\000\210\001\001\022\032\n\rcreatio"
                + "n_date\030\002 \001(\003H\001\210\001\001\022\024\n\007content\030\003 \001(\tH\002\210\001\001\022"
                + "2\n\006status\030\004 \001(\0162\035.im.turms.proto.Request"
                + "StatusH\003\210\001\001\022\034\n\017expiration_date\030\005 \001(\003H\004\210\001"
                + "\001\022\025\n\010group_id\030\006 \001(\003H\005\210\001\001\022\031\n\014requester_id"
                + "\030\007 \001(\003H\006\210\001\001\022\031\n\014responder_id\030\010 \001(\003H\007\210\001\001\0220"
                + "\n\021custom_attributes\030\017 \003(\0132\025.im.turms.pro"
                + "to.ValueB\005\n\003_idB\020\n\016_creation_dateB\n\n\010_co"
                + "ntentB\t\n\007_statusB\022\n\020_expiration_dateB\013\n\t"
                + "_group_idB\017\n\r_requester_idB\017\n\r_responder"
                + "_idB;\n4im.turms.server.common.access.cli"
                + "ent.dto.model.groupP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_GroupJoinRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_GroupJoinRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_GroupJoinRequest_descriptor,
                        new java.lang.String[]{"Id",
                                "CreationDate",
                                "Content",
                                "Status",
                                "ExpirationDate",
                                "GroupId",
                                "RequesterId",
                                "ResponderId",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}