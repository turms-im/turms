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

package im.turms.server.common.access.client.dto.request.message;

public final class CreateMessageRequestOuterClass {
    private CreateMessageRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateMessageRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_CreateMessageRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n,request/message/create_message_request"
                + ".proto\022\016im.turms.proto\"\367\002\n\024CreateMessage"
                + "Request\022\027\n\nmessage_id\030\001 \001(\003H\000\210\001\001\022\036\n\021is_s"
                + "ystem_message\030\002 \001(\010H\001\210\001\001\022\025\n\010group_id\030\003 \001"
                + "(\003H\002\210\001\001\022\031\n\014recipient_id\030\004 \001(\003H\003\210\001\001\022\032\n\rde"
                + "livery_date\030\005 \001(\003H\004\210\001\001\022\021\n\004text\030\006 \001(\tH\005\210\001"
                + "\001\022\017\n\007records\030\007 \003(\014\022\027\n\nburn_after\030\010 \001(\005H\006"
                + "\210\001\001\022\033\n\016pre_message_id\030\t \001(\003H\007\210\001\001B\r\n\013_mes"
                + "sage_idB\024\n\022_is_system_messageB\013\n\t_group_"
                + "idB\017\n\r_recipient_idB\020\n\016_delivery_dateB\007\n"
                + "\005_textB\r\n\013_burn_afterB\021\n\017_pre_message_id"
                + "B?\n8im.turms.server.common.access.client"
                + ".dto.request.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_CreateMessageRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_CreateMessageRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateMessageRequest_descriptor,
                        new java.lang.String[]{"MessageId",
                                "IsSystemMessage",
                                "GroupId",
                                "RecipientId",
                                "DeliveryDate",
                                "Text",
                                "Records",
                                "BurnAfter",
                                "PreMessageId",
                                "MessageId",
                                "IsSystemMessage",
                                "GroupId",
                                "RecipientId",
                                "DeliveryDate",
                                "Text",
                                "BurnAfter",
                                "PreMessageId",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}