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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryMessageAttachmentInfosRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n<request/storage/query_message_attachme"
                + "nt_infos_request.proto\022\016im.turms.proto\032\030"
                + "model/common/value.proto\"\341\002\n\"QueryMessag"
                + "eAttachmentInfosRequest\022\020\n\010user_ids\030\001 \003("
                + "\003\022\021\n\tgroup_ids\030\002 \003(\003\022 \n\023creation_date_st"
                + "art\030\003 \001(\003H\000\210\001\001\022\036\n\021creation_date_end\030\004 \001("
                + "\003H\001\210\001\001\022$\n\027in_private_conversation\030\005 \001(\010H"
                + "\002\210\001\001\022\035\n\020are_shared_by_me\030\006 \001(\010H\003\210\001\001\0220\n\021c"
                + "ustom_attributes\030\017 \003(\0132\025.im.turms.proto."
                + "ValueB\026\n\024_creation_date_startB\024\n\022_creati"
                + "on_date_endB\032\n\030_in_private_conversationB"
                + "\023\n\021_are_shared_by_meB?\n8im.turms.server."
                + "common.access.client.dto.request.storage"
                + "P\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryMessageAttachmentInfosRequest_descriptor,
                        new java.lang.String[]{"UserIds",
                                "GroupIds",
                                "CreationDateStart",
                                "CreationDateEnd",
                                "InPrivateConversation",
                                "AreSharedByMe",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}