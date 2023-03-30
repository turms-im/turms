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

package im.turms.server.common.access.client.dto.notification;

public final class TurmsNotificationOuterClass {
    private TurmsNotificationOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_TurmsNotification_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_TurmsNotification_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_TurmsNotification_Data_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_TurmsNotification_Data_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n%notification/turms_notification.proto\022"
                + "\016im.turms.proto\032\033request/turms_request.p"
                + "roto\032%model/common/longs_with_version.pr"
                + "oto\032\'model/common/strings_with_version.p"
                + "roto\0320model/group/group_invitations_with"
                + "_version.proto\0324model/group/group_join_q"
                + "uestions_answer_result.proto\0323model/grou"
                + "p/group_join_questions_with_version.prot"
                + "o\0322model/group/group_join_requests_with_"
                + "version.proto\032,model/group/group_members"
                + "_with_version.proto\032%model/group/groups_"
                + "with_version.proto\032&model/conversation/c"
                + "onversations.proto\032\034model/message/messag"
                + "es.proto\032,model/message/messages_with_to"
                + "tal_list.proto\032*model/storage/storage_re"
                + "source_infos.proto\032\035model/user/nearby_us"
                + "ers.proto\0322model/user/user_friend_reques"
                + "ts_with_version.proto\032(model/user/user_i"
                + "nfos_with_version.proto\032%model/user/user"
                + "_online_statuses.proto\0326model/user/user_"
                + "relationship_groups_with_version.proto\0320"
                + "model/user/user_relationships_with_versi"
                + "on.proto\032\035model/user/user_session.proto\""
                + "\212\016\n\021TurmsNotification\022\021\n\ttimestamp\030\001 \001(\003"
                + "\022\027\n\nrequest_id\030\004 \001(\003H\000\210\001\001\022\021\n\004code\030\005 \001(\005H"
                + "\001\210\001\001\022\023\n\006reason\030\006 \001(\tH\002\210\001\001\0224\n\004data\030\007 \001(\0132"
                + "&.im.turms.proto.TurmsNotification.Data\022"
                + "\031\n\014requester_id\030\n \001(\003H\003\210\001\001\022\031\n\014close_stat"
                + "us\030\013 \001(\005H\004\210\001\001\0225\n\017relayed_request\030\014 \001(\0132\034"
                + ".im.turms.proto.TurmsRequest\032\270\013\n\004Data\022\016\n"
                + "\004long\030\001 \001(\003H\000\022\020\n\006string\030\002 \001(\tH\000\022>\n\022longs"
                + "_with_version\030\003 \001(\0132 .im.turms.proto.Lon"
                + "gsWithVersionH\000\022B\n\024strings_with_version\030"
                + "\004 \001(\0132\".im.turms.proto.StringsWithVersio"
                + "nH\000\0226\n\rconversations\030\005 \001(\0132\035.im.turms.pr"
                + "oto.ConversationsH\000\022,\n\010messages\030\006 \001(\0132\030."
                + "im.turms.proto.MessagesH\000\022I\n\030messages_wi"
                + "th_total_list\030\007 \001(\0132%.im.turms.proto.Mes"
                + "sagesWithTotalListH\000\0223\n\014user_session\030\010 \001"
                + "(\0132\033.im.turms.proto.UserSessionH\000\022G\n\027use"
                + "r_infos_with_version\030\t \001(\0132$.im.turms.pr"
                + "oto.UserInfosWithVersionH\000\022B\n\024user_onlin"
                + "e_statuses\030\n \001(\0132\".im.turms.proto.UserOn"
                + "lineStatusesH\000\022Z\n!user_friend_requests_w"
                + "ith_version\030\013 \001(\0132-.im.turms.proto.UserF"
                + "riendRequestsWithVersionH\000\022b\n%user_relat"
                + "ionship_groups_with_version\030\014 \001(\01321.im.t"
                + "urms.proto.UserRelationshipGroupsWithVer"
                + "sionH\000\022W\n\037user_relationships_with_versio"
                + "n\030\r \001(\0132,.im.turms.proto.UserRelationshi"
                + "psWithVersionH\000\0223\n\014nearby_users\030\016 \001(\0132\033."
                + "im.turms.proto.NearbyUsersH\000\022U\n\036group_in"
                + "vitations_with_version\030\017 \001(\0132+.im.turms."
                + "proto.GroupInvitationsWithVersionH\000\022[\n!g"
                + "roup_join_question_answer_result\030\020 \001(\0132."
                + ".im.turms.proto.GroupJoinQuestionsAnswer"
                + "ResultH\000\022X\n group_join_requests_with_ver"
                + "sion\030\021 \001(\0132,.im.turms.proto.GroupJoinReq"
                + "uestsWithVersionH\000\022Z\n!group_join_questio"
                + "ns_with_version\030\022 \001(\0132-.im.turms.proto.G"
                + "roupJoinQuestionsWithVersionH\000\022M\n\032group_"
                + "members_with_version\030\023 \001(\0132\'.im.turms.pr"
                + "oto.GroupMembersWithVersionH\000\022@\n\023groups_"
                + "with_version\030\024 \001(\0132!.im.turms.proto.Grou"
                + "psWithVersionH\000\022F\n\026storage_resource_info"
                + "s\0302 \001(\0132$.im.turms.proto.StorageResource"
                + "InfosH\000B\006\n\004kindB\r\n\013_request_idB\007\n\005_codeB"
                + "\t\n\007_reasonB\017\n\r_requester_idB\017\n\r_close_st"
                + "atusB<\n5im.turms.server.common.access.cl"
                + "ient.dto.notificationP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.LongsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.message.MessagesOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserSessionOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_TurmsNotification_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_TurmsNotification_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_TurmsNotification_descriptor,
                        new java.lang.String[]{"Timestamp",
                                "RequestId",
                                "Code",
                                "Reason",
                                "Data",
                                "RequesterId",
                                "CloseStatus",
                                "RelayedRequest",
                                "RequestId",
                                "Code",
                                "Reason",
                                "RequesterId",
                                "CloseStatus",});
        internal_static_im_turms_proto_TurmsNotification_Data_descriptor =
                internal_static_im_turms_proto_TurmsNotification_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_TurmsNotification_Data_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_TurmsNotification_Data_descriptor,
                        new java.lang.String[]{"Long",
                                "String",
                                "LongsWithVersion",
                                "StringsWithVersion",
                                "Conversations",
                                "Messages",
                                "MessagesWithTotalList",
                                "UserSession",
                                "UserInfosWithVersion",
                                "UserOnlineStatuses",
                                "UserFriendRequestsWithVersion",
                                "UserRelationshipGroupsWithVersion",
                                "UserRelationshipsWithVersion",
                                "NearbyUsers",
                                "GroupInvitationsWithVersion",
                                "GroupJoinQuestionAnswerResult",
                                "GroupJoinRequestsWithVersion",
                                "GroupJoinQuestionsWithVersion",
                                "GroupMembersWithVersion",
                                "GroupsWithVersion",
                                "StorageResourceInfos",
                                "Kind",});
        im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.LongsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.message.MessagesOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.user.NearbyUsersOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.user.UserSessionOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}