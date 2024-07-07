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

public final class CreateSessionRequestOuterClass {
    private CreateSessionRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                CreateSessionRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateSessionRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateSessionRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateSessionRequest_DeviceDetailsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateSessionRequest_DeviceDetailsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n)request/user/create_session_request.pr"
                + "oto\022\016im.turms.proto\032\032constant/device_typ"
                + "e.proto\032\032constant/user_status.proto\032\030mod"
                + "el/common/value.proto\032\036model/user/user_l"
                + "ocation.proto\"\316\003\n\024CreateSessionRequest\022\017"
                + "\n\007version\030\001 \001(\005\022\017\n\007user_id\030\002 \001(\003\022\025\n\010pass"
                + "word\030\003 \001(\tH\000\210\001\001\0224\n\013user_status\030\004 \001(\0162\032.i"
                + "m.turms.proto.UserStatusH\001\210\001\001\022/\n\013device_"
                + "type\030\005 \001(\0162\032.im.turms.proto.DeviceType\022O"
                + "\n\016device_details\030\006 \003(\01327.im.turms.proto."
                + "CreateSessionRequest.DeviceDetailsEntry\022"
                + "3\n\010location\030\007 \001(\0132\034.im.turms.proto.UserL"
                + "ocationH\002\210\001\001\0220\n\021custom_attributes\030\017 \003(\0132"
                + "\025.im.turms.proto.Value\0324\n\022DeviceDetailsE"
                + "ntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001B\013\n\t"
                + "_passwordB\016\n\014_user_statusB\013\n\t_locationB<"
                + "\n5im.turms.server.common.access.client.d"
                + "to.request.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_CreateSessionRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_CreateSessionRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateSessionRequest_descriptor,
                        new java.lang.String[]{"Version",
                                "UserId",
                                "Password",
                                "UserStatus",
                                "DeviceType",
                                "DeviceDetails",
                                "Location",
                                "CustomAttributes",});
        internal_static_im_turms_proto_CreateSessionRequest_DeviceDetailsEntry_descriptor =
                internal_static_im_turms_proto_CreateSessionRequest_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_CreateSessionRequest_DeviceDetailsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateSessionRequest_DeviceDetailsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.constant.UserStatusOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserLocationOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}