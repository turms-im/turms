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

package im.turms.server.common.access.client.dto.model.user;

public final class UserFriendRequestOuterClass {
    private UserFriendRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_UserFriendRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_UserFriendRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n$model/user/user_friend_request.proto\022\016"
                + "im.turms.proto\032\035constant/request_status."
                + "proto\"\364\002\n\021UserFriendRequest\022\017\n\002id\030\001 \001(\003H"
                + "\000\210\001\001\022\032\n\rcreation_date\030\002 \001(\003H\001\210\001\001\022\024\n\007cont"
                + "ent\030\003 \001(\tH\002\210\001\001\022:\n\016request_status\030\004 \001(\0162\035"
                + ".im.turms.proto.RequestStatusH\003\210\001\001\022\023\n\006re"
                + "ason\030\005 \001(\tH\004\210\001\001\022\034\n\017expiration_date\030\006 \001(\003"
                + "H\005\210\001\001\022\031\n\014requester_id\030\007 \001(\003H\006\210\001\001\022\031\n\014reci"
                + "pient_id\030\010 \001(\003H\007\210\001\001B\005\n\003_idB\020\n\016_creation_"
                + "dateB\n\n\010_contentB\021\n\017_request_statusB\t\n\007_"
                + "reasonB\022\n\020_expiration_dateB\017\n\r_requester"
                + "_idB\017\n\r_recipient_idB:\n3im.turms.server."
                + "common.access.client.dto.model.userP\001\272\002\000"
                + "b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_UserFriendRequest_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_UserFriendRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_UserFriendRequest_descriptor,
                        new java.lang.String[]{"Id",
                                "CreationDate",
                                "Content",
                                "RequestStatus",
                                "Reason",
                                "ExpirationDate",
                                "RequesterId",
                                "RecipientId",
                                "Id",
                                "CreationDate",
                                "Content",
                                "RequestStatus",
                                "Reason",
                                "ExpirationDate",
                                "RequesterId",
                                "RecipientId",});
        im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}