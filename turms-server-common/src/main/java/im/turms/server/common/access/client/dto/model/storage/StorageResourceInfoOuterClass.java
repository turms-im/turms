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

package im.turms.server.common.access.client.dto.model.storage;

public final class StorageResourceInfoOuterClass {
    private StorageResourceInfoOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_StorageResourceInfo_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_StorageResourceInfo_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n)model/storage/storage_resource_info.pr"
                + "oto\022\016im.turms.proto\"\305\001\n\023StorageResourceI"
                + "nfo\022\023\n\006id_num\030\001 \001(\003H\000\210\001\001\022\023\n\006id_str\030\002 \001(\t"
                + "H\001\210\001\001\022\021\n\004name\030\003 \001(\tH\002\210\001\001\022\027\n\nmedia_type\030\004"
                + " \001(\tH\003\210\001\001\022\023\n\013uploader_id\030\005 \001(\003\022\025\n\rcreati"
                + "on_date\030\006 \001(\003B\t\n\007_id_numB\t\n\007_id_strB\007\n\005_"
                + "nameB\r\n\013_media_typeB=\n6im.turms.server.c"
                + "ommon.access.client.dto.model.storageP\001\272"
                + "\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_StorageResourceInfo_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_StorageResourceInfo_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_StorageResourceInfo_descriptor,
                        new java.lang.String[]{"IdNum",
                                "IdStr",
                                "Name",
                                "MediaType",
                                "UploaderId",
                                "CreationDate",
                                "IdNum",
                                "IdStr",
                                "Name",
                                "MediaType",});
    }

    // @@protoc_insertion_point(outer_class_scope)
}