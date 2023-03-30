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

public final class UpdateMessageAttachmentInfoRequestOuterClass {
    private UpdateMessageAttachmentInfoRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n<request/storage/update_message_attachm"
                + "ent_info_request.proto\022\016im.turms.proto\"\224"
                + "\003\n\"UpdateMessageAttachmentInfoRequest\022\036\n"
                + "\021attachment_id_num\030\001 \001(\003H\000\210\001\001\022\036\n\021attachm"
                + "ent_id_str\030\002 \001(\tH\001\210\001\001\022\"\n\025user_id_to_shar"
                + "e_with\030\003 \001(\003H\002\210\001\001\022$\n\027user_id_to_unshare_"
                + "with\030\004 \001(\003H\003\210\001\001\022#\n\026group_id_to_share_wit"
                + "h\030\005 \001(\003H\004\210\001\001\022%\n\030group_id_to_unshare_with"
                + "\030\006 \001(\003H\005\210\001\001B\024\n\022_attachment_id_numB\024\n\022_at"
                + "tachment_id_strB\030\n\026_user_id_to_share_wit"
                + "hB\032\n\030_user_id_to_unshare_withB\031\n\027_group_"
                + "id_to_share_withB\033\n\031_group_id_to_unshare"
                + "_withB?\n8im.turms.server.common.access.c"
                + "lient.dto.request.storageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateMessageAttachmentInfoRequest_descriptor,
                        new java.lang.String[]{"AttachmentIdNum",
                                "AttachmentIdStr",
                                "UserIdToShareWith",
                                "UserIdToUnshareWith",
                                "GroupIdToShareWith",
                                "GroupIdToUnshareWith",
                                "AttachmentIdNum",
                                "AttachmentIdStr",
                                "UserIdToShareWith",
                                "UserIdToUnshareWith",
                                "GroupIdToShareWith",
                                "GroupIdToUnshareWith",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}