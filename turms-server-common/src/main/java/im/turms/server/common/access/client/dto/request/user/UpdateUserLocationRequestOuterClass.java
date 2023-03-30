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

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateUserLocationRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n/request/user/update_user_location_requ"
                + "est.proto\022\016im.turms.proto\"\271\001\n\031UpdateUser"
                + "LocationRequest\022\020\n\010latitude\030\001 \001(\002\022\021\n\tlon"
                + "gitude\030\002 \001(\002\022G\n\007details\030\003 \003(\01326.im.turms"
                + ".proto.UpdateUserLocationRequest.Details"
                + "Entry\032.\n\014DetailsEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005va"
                + "lue\030\002 \001(\t:\0028\001B<\n5im.turms.server.common."
                + "access.client.dto.request.userP\001\272\002\000b\006pro"
                + "to3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserLocationRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor,
                        new java.lang.String[]{"Latitude", "Longitude", "Details",});
        internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_descriptor =
                internal_static_im_turms_proto_UpdateUserLocationRequest_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserLocationRequest_DetailsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}