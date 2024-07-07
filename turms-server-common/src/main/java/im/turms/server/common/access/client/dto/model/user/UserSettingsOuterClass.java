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

public final class UserSettingsOuterClass {
    private UserSettingsOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UserSettingsOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserSettings_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserSettings_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserSettings_SettingsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UserSettings_SettingsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\036model/user/user_settings.proto\022\016im.tur"
                + "ms.proto\032\030model/common/value.proto\"\374\001\n\014U"
                + "serSettings\022<\n\010settings\030\001 \003(\0132*.im.turms"
                + ".proto.UserSettings.SettingsEntry\022\036\n\021las"
                + "t_updated_date\030\002 \001(\003H\000\210\001\001\0220\n\021custom_attr"
                + "ibutes\030\017 \003(\0132\025.im.turms.proto.Value\032F\n\rS"
                + "ettingsEntry\022\013\n\003key\030\001 \001(\t\022$\n\005value\030\002 \001(\013"
                + "2\025.im.turms.proto.Value:\0028\001B\024\n\022_last_upd"
                + "ated_dateB:\n3im.turms.server.common.acce"
                + "ss.client.dto.model.userP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserSettings_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_UserSettings_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserSettings_descriptor,
                        new java.lang.String[]{"Settings", "LastUpdatedDate", "CustomAttributes",});
        internal_static_im_turms_proto_UserSettings_SettingsEntry_descriptor =
                internal_static_im_turms_proto_UserSettings_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_UserSettings_SettingsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UserSettings_SettingsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}