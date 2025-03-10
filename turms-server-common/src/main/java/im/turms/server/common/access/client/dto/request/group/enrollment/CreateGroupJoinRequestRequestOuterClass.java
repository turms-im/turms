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

package im.turms.server.common.access.client.dto.request.group.enrollment;

public final class CreateGroupJoinRequestRequestOuterClass {
    private CreateGroupJoinRequestRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 29,
                /* patch= */ 1,
                /* suffix= */ "",
                CreateGroupJoinRequestRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_CreateGroupJoinRequestRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_CreateGroupJoinRequestRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n@request/group/enrollment/create_group_"
                + "join_request_request.proto\022\016im.turms.pro"
                + "to\032\030model/common/value.proto\"t\n\035CreateGr"
                + "oupJoinRequestRequest\022\020\n\010group_id\030\001 \001(\003\022"
                + "\017\n\007content\030\002 \001(\t\0220\n\021custom_attributes\030\017 "
                + "\003(\0132\025.im.turms.proto.ValueBH\nAim.turms.s"
                + "erver.common.access.client.dto.request.g"
                + "roup.enrollmentP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_CreateGroupJoinRequestRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .getFirst();
        internal_static_im_turms_proto_CreateGroupJoinRequestRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_CreateGroupJoinRequestRequest_descriptor,
                        new java.lang.String[]{"GroupId", "Content", "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}