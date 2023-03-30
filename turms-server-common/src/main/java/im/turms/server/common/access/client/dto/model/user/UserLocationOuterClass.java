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

public final class UserLocationOuterClass {
    private UserLocationOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserLocation_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UserLocation_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserLocation_DetailsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UserLocation_DetailsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\036model/user/user_location.proto\022\016im.tur"
                + "ms.proto\"\305\001\n\014UserLocation\022\020\n\010latitude\030\001 "
                + "\001(\002\022\021\n\tlongitude\030\002 \001(\002\022\026\n\ttimestamp\030\003 \001("
                + "\003H\000\210\001\001\022:\n\007details\030\004 \003(\0132).im.turms.proto"
                + ".UserLocation.DetailsEntry\032.\n\014DetailsEnt"
                + "ry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001B\014\n\n_t"
                + "imestampB:\n3im.turms.server.common.acces"
                + "s.client.dto.model.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_UserLocation_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_UserLocation_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UserLocation_descriptor,
                        new java.lang.String[]{"Latitude",
                                "Longitude",
                                "Timestamp",
                                "Details",
                                "Timestamp",});
        internal_static_im_turms_proto_UserLocation_DetailsEntry_descriptor =
                internal_static_im_turms_proto_UserLocation_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_UserLocation_DetailsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UserLocation_DetailsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}