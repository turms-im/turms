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

package im.turms.server.common.access.client.dto.request.user.relationship;

public final class UpdateFriendRequestRequestOuterClass {
    private UpdateFriendRequestRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UpdateFriendRequestRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UpdateFriendRequestRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n=request/user/relationship/update_frien"
                + "d_request_request.proto\022\016im.turms.proto\032"
                + "\036constant/response_action.proto\"\211\001\n\032Upda"
                + "teFriendRequestRequest\022\022\n\nrequest_id\030\001 \001"
                + "(\003\0227\n\017response_action\030\002 \001(\0162\036.im.turms.p"
                + "roto.ResponseAction\022\023\n\006reason\030\003 \001(\tH\000\210\001\001"
                + "B\t\n\007_reasonBI\nBim.turms.server.common.ac"
                + "cess.client.dto.request.user.relationshi"
                + "pP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.ResponseActionOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UpdateFriendRequestRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UpdateFriendRequestRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UpdateFriendRequestRequest_descriptor,
                        new java.lang.String[]{"RequestId", "ResponseAction", "Reason", "Reason",});
        im.turms.server.common.access.client.dto.constant.ResponseActionOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}