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

package im.turms.server.common.access.client.dto.request.conversation;

public final class UpdateConversationSettingsRequestOuterClass {
    private UpdateConversationSettingsRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 0,
                /* suffix= */ "",
                UpdateConversationSettingsRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateConversationSettingsRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateConversationSettingsRequest_SettingsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateConversationSettingsRequest_SettingsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n?request/conversation/update_conversati"
                + "on_settings_request.proto\022\016im.turms.prot"
                + "o\032\030model/common/value.proto\"\204\002\n!UpdateCo"
                + "nversationSettingsRequest\022\024\n\007user_id\030\001 \001"
                + "(\003H\000\210\001\001\022\025\n\010group_id\030\002 \001(\003H\001\210\001\001\022Q\n\010settin"
                + "gs\030\003 \003(\0132?.im.turms.proto.UpdateConversa"
                + "tionSettingsRequest.SettingsEntry\032F\n\rSet"
                + "tingsEntry\022\013\n\003key\030\001 \001(\t\022$\n\005value\030\002 \001(\0132\025"
                + ".im.turms.proto.Value:\0028\001B\n\n\010_user_idB\013\n"
                + "\t_group_idBD\n=im.turms.server.common.acc"
                + "ess.client.dto.request.conversationP\001\272\002\000"
                + "b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateConversationSettingsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor,
                        new java.lang.String[]{"UserId", "GroupId", "Settings",});
        internal_static_im_turms_proto_UpdateConversationSettingsRequest_SettingsEntry_descriptor =
                internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor
                        .getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateConversationSettingsRequest_SettingsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateConversationSettingsRequest_SettingsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}