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

public final class UpdateUserOnlineStatusRequestOuterClass {
    private UpdateUserOnlineStatusRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateUserOnlineStatusRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n4request/user/update_user_online_status"
                + "_request.proto\022\016im.turms.proto\032\032constant"
                + "/device_type.proto\032\032constant/user_status"
                + ".proto\032\030model/common/value.proto\"\264\001\n\035Upd"
                + "ateUserOnlineStatusRequest\0220\n\014device_typ"
                + "es\030\001 \003(\0162\032.im.turms.proto.DeviceType\022/\n\013"
                + "user_status\030\002 \001(\0162\032.im.turms.proto.UserS"
                + "tatus\0220\n\021custom_attributes\030\017 \003(\0132\025.im.tu"
                + "rms.proto.ValueB<\n5im.turms.server.commo"
                + "n.access.client.dto.request.userP\001\272\002\000b\006p"
                + "roto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserOnlineStatusRequest_descriptor,
                        new java.lang.String[]{"DeviceTypes", "UserStatus", "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}