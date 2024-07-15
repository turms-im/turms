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

public final class ConversationSettingsListOuterClass {
    private ConversationSettingsListOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                ConversationSettingsListOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_ConversationSettingsList_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_ConversationSettingsList_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n3model/conversation/conversation_settin"
                + "gs_list.proto\022\016im.turms.proto\032.model/con"
                + "versation/conversation_settings.proto\"d\n"
                + "\030ConversationSettingsList\022H\n\032conversatio"
                + "n_settings_list\030\001 \003(\0132$.im.turms.proto.C"
                + "onversationSettingsBB\n;im.turms.server.c"
                + "ommon.access.client.dto.model.conversati"
                + "onP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_ConversationSettingsList_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_ConversationSettingsList_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_ConversationSettingsList_descriptor,
                        new java.lang.String[]{"ConversationSettingsList",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}