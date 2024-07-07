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

package im.turms.server.common.access.client.dto.model.conversation;

public final class ConversationSettingsOuterClass {
    private ConversationSettingsOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                ConversationSettingsOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_ConversationSettings_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_ConversationSettings_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_ConversationSettings_SettingsEntry_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_ConversationSettings_SettingsEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n.model/conversation/conversation_settin"
                + "gs.proto\022\016im.turms.proto\032\030model/common/v"
                + "alue.proto\"\322\002\n\024ConversationSettings\022\024\n\007u"
                + "ser_id\030\001 \001(\003H\000\210\001\001\022\025\n\010group_id\030\002 \001(\003H\001\210\001\001"
                + "\022D\n\010settings\030\003 \003(\01322.im.turms.proto.Conv"
                + "ersationSettings.SettingsEntry\022\036\n\021last_u"
                + "pdated_date\030\004 \001(\003H\002\210\001\001\0220\n\021custom_attribu"
                + "tes\030\017 \003(\0132\025.im.turms.proto.Value\032F\n\rSett"
                + "ingsEntry\022\013\n\003key\030\001 \001(\t\022$\n\005value\030\002 \001(\0132\025."
                + "im.turms.proto.Value:\0028\001B\n\n\010_user_idB\013\n\t"
                + "_group_idB\024\n\022_last_updated_dateBB\n;im.tu"
                + "rms.server.common.access.client.dto.mode"
                + "l.conversationP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_ConversationSettings_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_ConversationSettings_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_ConversationSettings_descriptor,
                        new java.lang.String[]{"UserId",
                                "GroupId",
                                "Settings",
                                "LastUpdatedDate",
                                "CustomAttributes",});
        internal_static_im_turms_proto_ConversationSettings_SettingsEntry_descriptor =
                internal_static_im_turms_proto_ConversationSettings_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_ConversationSettings_SettingsEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_ConversationSettings_SettingsEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}