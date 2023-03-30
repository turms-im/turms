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

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Message_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_Message_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\033model/message/message.proto\022\016im.turms."
                + "proto\"\270\003\n\007Message\022\017\n\002id\030\001 \001(\003H\000\210\001\001\022\032\n\rde"
                + "livery_date\030\002 \001(\003H\001\210\001\001\022\036\n\021modification_d"
                + "ate\030\003 \001(\003H\002\210\001\001\022\021\n\004text\030\004 \001(\tH\003\210\001\001\022\026\n\tsen"
                + "der_id\030\005 \001(\003H\004\210\001\001\022\025\n\010group_id\030\006 \001(\003H\005\210\001\001"
                + "\022\036\n\021is_system_message\030\007 \001(\010H\006\210\001\001\022\031\n\014reci"
                + "pient_id\030\010 \001(\003H\007\210\001\001\022\017\n\007records\030\t \003(\014\022\030\n\013"
                + "sequence_id\030\n \001(\005H\010\210\001\001\022\033\n\016pre_message_id"
                + "\030\013 \001(\003H\t\210\001\001B\005\n\003_idB\020\n\016_delivery_dateB\024\n\022"
                + "_modification_dateB\007\n\005_textB\014\n\n_sender_i"
                + "dB\013\n\t_group_idB\024\n\022_is_system_messageB\017\n\r"
                + "_recipient_idB\016\n\014_sequence_idB\021\n\017_pre_me"
                + "ssage_idB=\n6im.turms.server.common.acces"
                + "s.client.dto.model.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_Message_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_Message_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
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
                                "Id",
                                "DeliveryDate",
                                "ModificationDate",
                                "Text",
                                "SenderId",
                                "GroupId",
                                "IsSystemMessage",
                                "RecipientId",
                                "SequenceId",
                                "PreMessageId",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}