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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                CreateGroupRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateGroupRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateGroupRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateGroupRequest_UserDefinedAttributesEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateGroupRequest_UserDefinedAttributesEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n(request/group/create_group_request.pro"
                + "to\022\016im.turms.proto\032\030model/common/value.p"
                + "roto\"\311\003\n\022CreateGroupRequest\022\014\n\004name\030\001 \001("
                + "\t\022\022\n\005intro\030\002 \001(\tH\000\210\001\001\022\031\n\014announcement\030\003 "
                + "\001(\tH\001\210\001\001\022\026\n\tmin_score\030\004 \001(\005H\002\210\001\001\022\024\n\007type"
                + "_id\030\005 \001(\003H\003\210\001\001\022\032\n\rmute_end_date\030\006 \001(\003H\004\210"
                + "\001\001\022^\n\027user_defined_attributes\030\007 \003(\0132=.im"
                + ".turms.proto.CreateGroupRequest.UserDefi"
                + "nedAttributesEntry\0220\n\021custom_attributes\030"
                + "\017 \003(\0132\025.im.turms.proto.Value\032S\n\032UserDefi"
                + "nedAttributesEntry\022\013\n\003key\030\001 \001(\t\022$\n\005value"
                + "\030\002 \001(\0132\025.im.turms.proto.Value:\0028\001B\010\n\006_in"
                + "troB\017\n\r_announcementB\014\n\n_min_scoreB\n\n\010_t"
                + "ype_idB\020\n\016_mute_end_dateB=\n6im.turms.ser"
                + "ver.common.access.client.dto.request.gro"
                + "upP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_CreateGroupRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_CreateGroupRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateGroupRequest_descriptor,
                        new java.lang.String[]{"Name",
                                "Intro",
                                "Announcement",
                                "MinScore",
                                "TypeId",
                                "MuteEndDate",
                                "UserDefinedAttributes",
                                "CustomAttributes",});
        internal_static_im_turms_proto_CreateGroupRequest_UserDefinedAttributesEntry_descriptor =
                internal_static_im_turms_proto_CreateGroupRequest_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_CreateGroupRequest_UserDefinedAttributesEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateGroupRequest_UserDefinedAttributesEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}