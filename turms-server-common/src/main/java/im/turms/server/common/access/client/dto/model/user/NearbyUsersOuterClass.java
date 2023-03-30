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

public final class NearbyUsersOuterClass {
    private NearbyUsersOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_NearbyUsers_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_NearbyUsers_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\035model/user/nearby_users.proto\022\016im.turm"
                + "s.proto\032\034model/user/nearby_user.proto\"?\n"
                + "\013NearbyUsers\0220\n\014nearby_users\030\001 \003(\0132\032.im."
                + "turms.proto.NearbyUserB:\n3im.turms.serve"
                + "r.common.access.client.dto.model.userP\001\272"
                + "\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_NearbyUsers_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_NearbyUsers_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_NearbyUsers_descriptor,
                        new java.lang.String[]{"NearbyUsers",});
        im.turms.server.common.access.client.dto.model.user.NearbyUserOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}