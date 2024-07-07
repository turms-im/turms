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

package im.turms.server.common.access.client.dto.model.file;

public final class VideoFileOuterClass {
    private VideoFileOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                VideoFileOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_VideoFile_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_VideoFile_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_VideoFile_Description_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_VideoFile_Description_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\033model/file/video_file.proto\022\016im.turms."
                + "proto\032\030model/common/value.proto\"\246\002\n\tVide"
                + "oFile\022?\n\013description\030\001 \001(\0132%.im.turms.pr"
                + "oto.VideoFile.DescriptionH\000\210\001\001\022\021\n\004data\030\002"
                + " \001(\014H\001\210\001\001\0220\n\021custom_attributes\030\017 \003(\0132\025.i"
                + "m.turms.proto.Value\032z\n\013Description\022\013\n\003ur"
                + "l\030\001 \001(\t\022\025\n\010duration\030\002 \001(\005H\000\210\001\001\022\021\n\004size\030\003"
                + " \001(\005H\001\210\001\001\022\023\n\006format\030\004 \001(\tH\002\210\001\001B\013\n\t_durat"
                + "ionB\007\n\005_sizeB\t\n\007_formatB\016\n\014_descriptionB"
                + "\007\n\005_dataB:\n3im.turms.server.common.acces"
                + "s.client.dto.model.fileP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_VideoFile_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_VideoFile_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_VideoFile_descriptor,
                        new java.lang.String[]{"Description", "Data", "CustomAttributes",});
        internal_static_im_turms_proto_VideoFile_Description_descriptor =
                internal_static_im_turms_proto_VideoFile_descriptor.getNestedTypes()
                        .getFirst();
        internal_static_im_turms_proto_VideoFile_Description_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_VideoFile_Description_descriptor,
                        new java.lang.String[]{"Url", "Duration", "Size", "Format",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}