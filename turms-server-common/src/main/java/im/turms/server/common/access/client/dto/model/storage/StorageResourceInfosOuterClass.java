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

public final class StorageResourceInfosOuterClass {
    private StorageResourceInfosOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_StorageResourceInfos_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_StorageResourceInfos_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n*model/storage/storage_resource_infos.p"
                + "roto\022\016im.turms.proto\032)model/storage/stor"
                + "age_resource_info.proto\"J\n\024StorageResour"
                + "ceInfos\0222\n\005infos\030\001 \003(\0132#.im.turms.proto."
                + "StorageResourceInfoB=\n6im.turms.server.c"
                + "ommon.access.client.dto.model.storageP\001\272"
                + "\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_StorageResourceInfos_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_StorageResourceInfos_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_StorageResourceInfos_descriptor,
                        new java.lang.String[]{"Infos",});
        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}