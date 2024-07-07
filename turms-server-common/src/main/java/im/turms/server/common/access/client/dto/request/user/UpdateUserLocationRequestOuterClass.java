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

package im.turms.server.common.access.client.dto.request.user;

public final class UpdateUserLocationRequestOuterClass {
    private UpdateUserLocationRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateUserLocationRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserLocationRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n/request/user/update_user_location_requ"
                + "est.proto\022\016im.turms.proto\032\030model/common/"
                + "value.proto\"\353\001\n\031UpdateUserLocationReques"
                + "t\022\020\n\010latitude\030\001 \001(\002\022\021\n\tlongitude\030\002 \001(\002\022G"
                + "\n\007details\030\003 \003(\01326.im.turms.proto.UpdateU"
                + "serLocationRequest.DetailsEntry\0220\n\021custo"
                + "m_attributes\030\017 \003(\0132\025.im.turms.proto.Valu"
                + "e\032.\n\014DetailsEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030"
                + "\002 \001(\t:\0028\001B<\n5im.turms.server.common.acce"
                + "ss.client.dto.request.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserLocationRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor,
                        new java.lang.String[]{"Latitude",
                                "Longitude",
                                "Details",
                                "CustomAttributes",});
        internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_descriptor =
                internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}