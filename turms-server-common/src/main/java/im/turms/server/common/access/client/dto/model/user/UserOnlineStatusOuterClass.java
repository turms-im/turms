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

public final class UserOnlineStatusOuterClass {
    private UserOnlineStatusOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserOnlineStatus_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UserOnlineStatus_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n#model/user/user_online_status.proto\022\016i"
                + "m.turms.proto\032\032constant/user_status.prot"
                + "o\032\032constant/device_type.proto\"\214\001\n\020UserOn"
                + "lineStatus\022\017\n\007user_id\030\001 \001(\003\022/\n\013user_stat"
                + "us\030\002 \001(\0162\032.im.turms.proto.UserStatus\0226\n\022"
                + "using_device_types\030\003 \003(\0162\032.im.turms.prot"
                + "o.DeviceTypeB:\n3im.turms.server.common.a"
                + "ccess.client.dto.model.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserOnlineStatus_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UserOnlineStatus_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UserOnlineStatus_descriptor,
                        new java.lang.String[]{"UserId", "UserStatus", "UsingDeviceTypes",});
        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}