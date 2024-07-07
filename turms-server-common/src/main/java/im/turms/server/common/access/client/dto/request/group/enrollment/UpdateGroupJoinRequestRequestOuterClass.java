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

public final class UpdateGroupJoinRequestRequestOuterClass {
    private UpdateGroupJoinRequestRequestOuterClass() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateGroupJoinRequestRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n@request/group/enrollment/update_group_"
                + "join_request_request.proto\022\016im.turms.pro"
                + "to\032\036constant/response_action.proto\032\030mode"
                + "l/common/value.proto\"\276\001\n\035UpdateGroupJoin"
                + "RequestRequest\022\022\n\nrequest_id\030\001 \001(\003\0227\n\017re"
                + "sponse_action\030\002 \001(\0162\036.im.turms.proto.Res"
                + "ponseAction\022\023\n\006reason\030\003 \001(\tH\000\210\001\001\0220\n\021cust"
                + "om_attributes\030\017 \003(\0132\025.im.turms.proto.Val"
                + "ueB\t\n\007_reasonBH\nAim.turms.server.common."
                + "access.client.dto.request.group.enrollme"
                + "ntP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.ResponseActionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateGroupJoinRequestRequest_descriptor,
                        new java.lang.String[]{"RequestId",
                                "ResponseAction",
                                "Reason",
                                "CustomAttributes",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.constant.ResponseActionOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}