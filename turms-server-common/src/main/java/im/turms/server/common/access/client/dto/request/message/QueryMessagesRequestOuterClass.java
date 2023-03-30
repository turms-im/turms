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

public final class QueryMessagesRequestOuterClass {
    private QueryMessagesRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryMessagesRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_QueryMessagesRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n,request/message/query_messages_request"
                + ".proto\022\016im.turms.proto\"\371\002\n\024QueryMessages"
                + "Request\022\013\n\003ids\030\001 \003(\003\022\037\n\022are_group_messag"
                + "es\030\002 \001(\010H\000\210\001\001\022 \n\023are_system_messages\030\003 \001"
                + "(\010H\001\210\001\001\022\020\n\010from_ids\030\004 \003(\003\022 \n\023delivery_da"
                + "te_start\030\005 \001(\003H\002\210\001\001\022\036\n\021delivery_date_end"
                + "\030\006 \001(\003H\003\210\001\001\022\026\n\tmax_count\030\007 \001(\005H\004\210\001\001\022\022\n\nw"
                + "ith_total\030\010 \001(\010\022\027\n\ndescending\030\t \001(\010H\005\210\001\001"
                + "B\025\n\023_are_group_messagesB\026\n\024_are_system_m"
                + "essagesB\026\n\024_delivery_date_startB\024\n\022_deli"
                + "very_date_endB\014\n\n_max_countB\r\n\013_descendi"
                + "ngB?\n8im.turms.server.common.access.clie"
                + "nt.dto.request.messageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_QueryMessagesRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_QueryMessagesRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryMessagesRequest_descriptor,
                        new java.lang.String[]{"Ids",
                                "AreGroupMessages",
                                "AreSystemMessages",
                                "FromIds",
                                "DeliveryDateStart",
                                "DeliveryDateEnd",
                                "MaxCount",
                                "WithTotal",
                                "Descending",
                                "AreGroupMessages",
                                "AreSystemMessages",
                                "DeliveryDateStart",
                                "DeliveryDateEnd",
                                "MaxCount",
                                "Descending",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}