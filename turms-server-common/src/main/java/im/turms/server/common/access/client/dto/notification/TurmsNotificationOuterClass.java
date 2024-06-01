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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 0,
                /* suffix= */ "",
                TurmsNotificationOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_TurmsNotification_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_TurmsNotification_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_TurmsNotification_Data_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_TurmsNotification_Data_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n%notification/turms_notification.proto\022"
                + "\016im.turms.proto\032\033request/turms_request.p"
                + "roto\032%model/common/longs_with_version.pr"
                + "oto\032\'model/common/strings_with_version.p"
                + "roto\032\037model/conference/meetings.proto\0323m"
                + "odel/conversation/conversation_settings_"
                + "list.proto\032&model/conversation/conversat"
                + "ions.proto\0320model/group/group_invitation"
                + "s_with_version.proto\0324model/group/group_"
                + "join_questions_answer_result.proto\0323mode"
                + "l/group/group_join_questions_with_versio"
                + "n.proto\0322model/group/group_join_requests"
                + "_with_version.proto\032,model/group/group_m"
                + "embers_with_version.proto\032%model/group/g"
                + "roups_with_version.proto\032\034model/message/"
                + "messages.proto\032,model/message/messages_w"
                + "ith_total_list.proto\032*model/storage/stor"
                + "age_resource_infos.proto\032\035model/user/nea"
                + "rby_users.proto\0322model/user/user_friend_"
                + "requests_with_version.proto\032(model/user/"
                + "user_infos_with_version.proto\032%model/use"
                + "r/user_online_statuses.proto\0326model/user"
                + "/user_relationship_groups_with_version.p"
                + "roto\0320model/user/user_relationships_with"
                + "_version.proto\032\035model/user/user_session."
                + "proto\032\036model/user/user_settings.proto\"\301\017"
                + "\n\021TurmsNotification\022\021\n\ttimestamp\030\001 \001(\003\022\027"
                + "\n\nrequest_id\030\004 \001(\003H\000\210\001\001\022\021\n\004code\030\005 \001(\005H\001\210"
                + "\001\001\022\023\n\006reason\030\006 \001(\tH\002\210\001\001\0224\n\004data\030\007 \001(\0132&."
                + "im.turms.proto.TurmsNotification.Data\022\031\n"
                + "\014requester_id\030\n \001(\003H\003\210\001\001\022\031\n\014close_status"
                + "\030\013 \001(\005H\004\210\001\001\0225\n\017relayed_request\030\014 \001(\0132\034.i"
                + "m.turms.proto.TurmsRequest\032\357\014\n\004Data\022\016\n\004l"
                + "ong\030\001 \001(\003H\000\022\020\n\006string\030\002 \001(\tH\000\022>\n\022longs_w"
                + "ith_version\030\003 \001(\0132 .im.turms.proto.Longs"
                + "WithVersionH\000\022B\n\024strings_with_version\030\004 "
                + "\001(\0132\".im.turms.proto.StringsWithVersionH"
                + "\000\0226\n\rconversations\030\005 \001(\0132\035.im.turms.prot"
                + "o.ConversationsH\000\022,\n\010messages\030\006 \001(\0132\030.im"
                + ".turms.proto.MessagesH\000\022I\n\030messages_with"
                + "_total_list\030\007 \001(\0132%.im.turms.proto.Messa"
                + "gesWithTotalListH\000\0223\n\014user_session\030\010 \001(\013"
                + "2\033.im.turms.proto.UserSessionH\000\022G\n\027user_"
                + "infos_with_version\030\t \001(\0132$.im.turms.prot"
                + "o.UserInfosWithVersionH\000\022B\n\024user_online_"
                + "statuses\030\n \001(\0132\".im.turms.proto.UserOnli"
                + "neStatusesH\000\022Z\n!user_friend_requests_wit"
                + "h_version\030\013 \001(\0132-.im.turms.proto.UserFri"
                + "endRequestsWithVersionH\000\022b\n%user_relatio"
                + "nship_groups_with_version\030\014 \001(\01321.im.tur"
                + "ms.proto.UserRelationshipGroupsWithVersi"
                + "onH\000\022W\n\037user_relationships_with_version\030"
                + "\r \001(\0132,.im.turms.proto.UserRelationships"
                + "WithVersionH\000\0223\n\014nearby_users\030\016 \001(\0132\033.im"
                + ".turms.proto.NearbyUsersH\000\022U\n\036group_invi"
                + "tations_with_version\030\017 \001(\0132+.im.turms.pr"
                + "oto.GroupInvitationsWithVersionH\000\022[\n!gro"
                + "up_join_question_answer_result\030\020 \001(\0132..i"
                + "m.turms.proto.GroupJoinQuestionsAnswerRe"
                + "sultH\000\022X\n group_join_requests_with_versi"
                + "on\030\021 \001(\0132,.im.turms.proto.GroupJoinReque"
                + "stsWithVersionH\000\022Z\n!group_join_questions"
                + "_with_version\030\022 \001(\0132-.im.turms.proto.Gro"
                + "upJoinQuestionsWithVersionH\000\022M\n\032group_me"
                + "mbers_with_version\030\023 \001(\0132\'.im.turms.prot"
                + "o.GroupMembersWithVersionH\000\022@\n\023groups_wi"
                + "th_version\030\024 \001(\0132!.im.turms.proto.Groups"
                + "WithVersionH\000\022,\n\010meetings\030( \001(\0132\030.im.tur"
                + "ms.proto.MeetingsH\000\022F\n\026storage_resource_"
                + "infos\0302 \001(\0132$.im.turms.proto.StorageReso"
                + "urceInfosH\000\022O\n\032conversation_settings_lis"
                + "t\030\310\001 \001(\0132(.im.turms.proto.ConversationSe"
                + "ttingsListH\000\0226\n\ruser_settings\030\220\003 \001(\0132\034.i"
                + "m.turms.proto.UserSettingsH\000B\006\n\004kindB\r\n\013"
                + "_request_idB\007\n\005_codeB\t\n\007_reasonB\017\n\r_requ"
                + "ester_idB\017\n\r_close_statusB<\n5im.turms.se"
                + "rver.common.access.client.dto.notificati"
                + "onP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.LongsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass
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
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_TurmsNotification_descriptor =
                getDescriptor().getMessageTypes()
                        .get(0);
        internal_static_im_turms_proto_TurmsNotification_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_TurmsNotification_descriptor,
                        new java.lang.String[]{"Timestamp",
                                "RequestId",
                                "Code",
                                "Reason",
                                "Data",
                                "RequesterId",
                                "CloseStatus",
                                "RelayedRequest",});
        internal_static_im_turms_proto_TurmsNotification_Data_descriptor =
                internal_static_im_turms_proto_TurmsNotification_descriptor.getNestedTypes()
                        .get(0);
        internal_static_im_turms_proto_TurmsNotification_Data_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
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
                                "Meetings",
                                "StorageResourceInfos",
                                "ConversationSettingsList",
                                "UserSettings",
                                "Kind",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.request.TurmsRequestOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.model.common.LongsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.conversation.ConversationSettingsListOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.model.conversation.ConversationsOuterClass
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
        im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}