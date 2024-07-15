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

package im.turms.server.common.access.client.dto.model.common;

public final class ValueOuterClass {
    private ValueOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                ValueOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_Value_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_Value_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\030model/common/value.proto\022\016im.turms.pro"
                + "to\"\334\001\n\005Value\022\025\n\013int32_value\030\001 \001(\005H\000\022\025\n\013i"
                + "nt64_value\030\002 \001(\003H\000\022\025\n\013float_value\030\003 \001(\002H"
                + "\000\022\026\n\014double_value\030\004 \001(\001H\000\022\024\n\nbool_value\030"
                + "\005 \001(\010H\000\022\025\n\013bytes_value\030\006 \001(\014H\000\022\026\n\014string"
                + "_value\030\007 \001(\tH\000\022)\n\nlist_value\030\010 \003(\0132\025.im."
                + "turms.proto.ValueB\006\n\004kindB<\n5im.turms.se"
                + "rver.common.access.client.dto.model.comm"
                + "onP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{});
        internal_static_im_turms_proto_Value_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_Value_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_Value_descriptor,
                        new java.lang.String[]{"Int32Value",
                                "Int64Value",
                                "FloatValue",
                                "DoubleValue",
                                "BoolValue",
                                "BytesValue",
                                "StringValue",
                                "ListValue",
                                "Kind",});
        descriptor.resolveAllFeaturesImmutable();
    }

    // @@protoc_insertion_point(outer_class_scope)
}