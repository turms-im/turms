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

public final class UpdateUserSettingsRequestOuterClass {
    private UpdateUserSettingsRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateUserSettingsRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserSettingsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserSettingsRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateUserSettingsRequest_SettingsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateUserSettingsRequest_SettingsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n/request/user/update_user_settings_requ"
                + "est.proto\022\016im.turms.proto\032\030model/common/"
                + "value.proto\"\340\001\n\031UpdateUserSettingsReques"
                + "t\022I\n\010settings\030\001 \003(\01327.im.turms.proto.Upd"
                + "ateUserSettingsRequest.SettingsEntry\0220\n\021"
                + "custom_attributes\030\017 \003(\0132\025.im.turms.proto"
                + ".Value\032F\n\rSettingsEntry\022\013\n\003key\030\001 \001(\t\022$\n\005"
                + "value\030\002 \001(\0132\025.im.turms.proto.Value:\0028\001B<"
                + "\n5im.turms.server.common.access.client.d"
                + "to.request.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateUserSettingsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_UpdateUserSettingsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserSettingsRequest_descriptor,
                        new java.lang.String[]{"Settings", "CustomAttributes",});
        internal_static_im_turms_proto_UpdateUserSettingsRequest_SettingsEntry_descriptor =
                internal_static_im_turms_proto_UpdateUserSettingsRequest_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_UpdateUserSettingsRequest_SettingsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateUserSettingsRequest_SettingsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}