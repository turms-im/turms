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

package im.turms.server.common.access.client.dto.request.storage;

public final class QueryMessageAttachmentInfosRequestOuterClass {
    private QueryMessageAttachmentInfosRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n<request/storage/query_message_attachme"
                + "nt_infos_request.proto\022\016im.turms.proto\"\257"
                + "\002\n\"QueryMessageAttachmentInfosRequest\022\020\n"
                + "\010user_ids\030\001 \003(\003\022\021\n\tgroup_ids\030\002 \003(\003\022 \n\023cr"
                + "eation_date_start\030\003 \001(\003H\000\210\001\001\022\036\n\021creation"
                + "_date_end\030\004 \001(\003H\001\210\001\001\022$\n\027in_private_conve"
                + "rsation\030\005 \001(\010H\002\210\001\001\022\035\n\020are_shared_by_me\030\006"
                + " \001(\010H\003\210\001\001B\026\n\024_creation_date_startB\024\n\022_cr"
                + "eation_date_endB\032\n\030_in_private_conversat"
                + "ionB\023\n\021_are_shared_by_meB?\n8im.turms.ser"
                + "ver.common.access.client.dto.request.sto"
                + "rageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor,
                        new java.lang.String[]{"UserIds",
                                "GroupIds",
                                "CreationDateStart",
                                "CreationDateEnd",
                                "InPrivateConversation",
                                "AreSharedByMe",
                                "CreationDateStart",
                                "CreationDateEnd",
                                "InPrivateConversation",
                                "AreSharedByMe",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}