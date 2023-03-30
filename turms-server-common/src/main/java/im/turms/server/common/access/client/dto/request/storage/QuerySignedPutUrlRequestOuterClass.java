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

public final class QuerySignedPutUrlRequestOuterClass {
    private QuerySignedPutUrlRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_QuerySignedPutUrlRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_QuerySignedPutUrlRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n2request/storage/query_signed_put_url_r"
                + "equest.proto\022\016im.turms.proto\032\033constant/c"
                + "ontent_type.proto\"\251\001\n\030QuerySignedPutUrlR"
                + "equest\0221\n\014content_type\030\001 \001(\0162\033.im.turms."
                + "proto.ContentType\022\024\n\007key_str\030\002 \001(\tH\000\210\001\001\022"
                + "\024\n\007key_num\030\003 \001(\003H\001\210\001\001\022\026\n\016content_length\030"
                + "\004 \001(\003B\n\n\010_key_strB\n\n\010_key_numB?\n8im.turm"
                + "s.server.common.access.client.dto.reques"
                + "t.storageP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.ContentTypeOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_QuerySignedPutUrlRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_QuerySignedPutUrlRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_QuerySignedPutUrlRequest_descriptor,
                        new java.lang.String[]{"ContentType",
                                "KeyStr",
                                "KeyNum",
                                "ContentLength",
                                "KeyStr",
                                "KeyNum",});
        im.turms.server.common.access.client.dto.constant.ContentTypeOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}