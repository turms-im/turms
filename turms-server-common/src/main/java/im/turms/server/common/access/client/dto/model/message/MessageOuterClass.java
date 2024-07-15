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

package im.turms.server.common.access.client.dto.model.message;

public final class MessageOuterClass {
    private MessageOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                MessageOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Message_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_Message_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\033model/message/message.proto\022\016im.turms."
                + "proto\032\030model/common/value.proto\032*model/m"
                + "essage/message_reaction_group.proto\"\251\004\n\007"
                + "Message\022\017\n\002id\030\001 \001(\003H\000\210\001\001\022\032\n\rdelivery_dat"
                + "e\030\002 \001(\003H\001\210\001\001\022\036\n\021modification_date\030\003 \001(\003H"
                + "\002\210\001\001\022\021\n\004text\030\004 \001(\tH\003\210\001\001\022\026\n\tsender_id\030\005 \001"
                + "(\003H\004\210\001\001\022\025\n\010group_id\030\006 \001(\003H\005\210\001\001\022\036\n\021is_sys"
                + "tem_message\030\007 \001(\010H\006\210\001\001\022\031\n\014recipient_id\030\010"
                + " \001(\003H\007\210\001\001\022\017\n\007records\030\t \003(\014\022\030\n\013sequence_i"
                + "d\030\n \001(\005H\010\210\001\001\022\033\n\016pre_message_id\030\013 \001(\003H\t\210\001"
                + "\001\022=\n\017reaction_groups\030\014 \003(\0132$.im.turms.pr"
                + "oto.MessageReactionGroup\0220\n\021custom_attri"
                + "butes\030\017 \003(\0132\025.im.turms.proto.ValueB\005\n\003_i"
                + "dB\020\n\016_delivery_dateB\024\n\022_modification_dat"
                + "eB\007\n\005_textB\014\n\n_sender_idB\013\n\t_group_idB\024\n"
                + "\022_is_system_messageB\017\n\r_recipient_idB\016\n\014"
                + "_sequence_idB\021\n\017_pre_message_idB=\n6im.tu"
                + "rms.server.common.access.client.dto.mode"
                + "l.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_Message_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_Message_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_Message_descriptor,
                        new java.lang.String[]{"Id",
                                "DeliveryDate",
                                "ModificationDate",
                                "Text",
                                "SenderId",
                                "GroupId",
                                "IsSystemMessage",
                                "RecipientId",
                                "Records",
                                "SequenceId",
                                "PreMessageId",
                                "ReactionGroups",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.message.MessageReactionGroupOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}