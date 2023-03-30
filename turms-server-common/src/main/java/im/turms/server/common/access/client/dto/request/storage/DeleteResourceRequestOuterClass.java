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

public final class DeleteResourceRequestOuterClass {
    private DeleteResourceRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_DeleteResourceRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_DeleteResourceRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_DeleteResourceRequest_ExtraEntry_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_DeleteResourceRequest_ExtraEntry_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n-request/storage/delete_resource_reques"
                + "t.proto\022\016im.turms.proto\032$constant/storag"
                + "e_resource_type.proto\"\371\001\n\025DeleteResource"
                + "Request\0221\n\004type\030\001 \001(\0162#.im.turms.proto.S"
                + "torageResourceType\022\023\n\006id_num\030\002 \001(\003H\000\210\001\001\022"
                + "\023\n\006id_str\030\003 \001(\tH\001\210\001\001\022?\n\005extra\030\004 \003(\01320.im"
                + ".turms.proto.DeleteResourceRequest.Extra"
                + "Entry\032,\n\nExtraEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005valu"
                + "e\030\002 \001(\t:\0028\001B\t\n\007_id_numB\t\n\007_id_strB?\n8im."
                + "turms.server.common.access.client.dto.re"
                + "quest.storageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.StorageResourceTypeOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_DeleteResourceRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_DeleteResourceRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_DeleteResourceRequest_descriptor,
                        new java.lang.String[]{"Type",
                                "IdNum",
                                "IdStr",
                                "Extra",
                                "IdNum",
                                "IdStr",});
        internal_static_im_turms_proto_DeleteResourceRequest_ExtraEntry_descriptor =
                internal_static_im_turms_proto_DeleteResourceRequest_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_DeleteResourceRequest_ExtraEntry_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_DeleteResourceRequest_ExtraEntry_descriptor,
                        new java.lang.String[]{"Key", "Value",});
        im.turms.server.common.access.client.dto.constant.StorageResourceTypeOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}