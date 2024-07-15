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

public final class QueryResourceUploadInfoRequestOuterClass {
    private QueryResourceUploadInfoRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                QueryResourceUploadInfoRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QueryResourceUploadInfoRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_QueryResourceUploadInfoRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n8request/storage/query_resource_upload_"
                + "info_request.proto\022\016im.turms.proto\032$cons"
                + "tant/storage_resource_type.proto\032\030model/"
                + "common/value.proto\"\211\002\n\036QueryResourceUplo"
                + "adInfoRequest\0221\n\004type\030\001 \001(\0162#.im.turms.p"
                + "roto.StorageResourceType\022\023\n\006id_num\030\002 \001(\003"
                + "H\000\210\001\001\022\023\n\006id_str\030\003 \001(\tH\001\210\001\001\022\021\n\004name\030\004 \001(\t"
                + "H\002\210\001\001\022\027\n\nmedia_type\030\005 \001(\tH\003\210\001\001\0220\n\021custom"
                + "_attributes\030\017 \003(\0132\025.im.turms.proto.Value"
                + "B\t\n\007_id_numB\t\n\007_id_strB\007\n\005_nameB\r\n\013_medi"
                + "a_typeB?\n8im.turms.server.common.access."
                + "client.dto.request.storageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.StorageResourceTypeOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QueryResourceUploadInfoRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_QueryResourceUploadInfoRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_QueryResourceUploadInfoRequest_descriptor,
                        new java.lang.String[]{"Type",
                                "IdNum",
                                "IdStr",
                                "Name",
                                "MediaType",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.StorageResourceTypeOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}