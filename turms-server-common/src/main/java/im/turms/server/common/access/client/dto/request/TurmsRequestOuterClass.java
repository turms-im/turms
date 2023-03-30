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

package im.turms.server.common.access.client.dto.request;

public final class TurmsRequestOuterClass {
    private TurmsRequestOuterClass() {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_TurmsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {"\n\033request/turms_request.proto\022\016im.turms."
                + "proto\032-request/storage/delete_resource_r"
                + "equest.proto\032:request/storage/query_reso"
                + "urce_download_info_request.proto\0328reques"
                + "t/storage/query_resource_upload_info_req"
                + "uest.proto\032<request/storage/query_messag"
                + "e_attachment_infos_request.proto\032<reques"
                + "t/storage/update_message_attachment_info"
                + "_request.proto\032)request/user/create_sess"
                + "ion_request.proto\032)request/user/delete_s"
                + "ession_request.proto\032-request/user/query"
                + "_nearby_users_request.proto\0325request/use"
                + "r/query_user_online_statuses_request.pro"
                + "to\032.request/user/query_user_profiles_req"
                + "uest.proto\032/request/user/update_user_loc"
                + "ation_request.proto\0324request/user/update"
                + "_user_online_status_request.proto\032&reque"
                + "st/user/update_user_request.proto\032=reque"
                + "st/user/relationship/create_friend_reque"
                + "st_request.proto\032Arequest/user/relations"
                + "hip/create_relationship_group_request.pr"
                + "oto\032;request/user/relationship/create_re"
                + "lationship_request.proto\032Arequest/user/r"
                + "elationship/delete_relationship_group_re"
                + "quest.proto\032;request/user/relationship/d"
                + "elete_relationship_request.proto\032=reques"
                + "t/user/relationship/query_friend_request"
                + "s_request.proto\032>request/user/relationsh"
                + "ip/query_related_user_ids_request.proto\032"
                + "Arequest/user/relationship/query_relatio"
                + "nship_groups_request.proto\032;request/user"
                + "/relationship/query_relationships_reques"
                + "t.proto\032=request/user/relationship/updat"
                + "e_friend_request_request.proto\032Arequest/"
                + "user/relationship/update_relationship_gr"
                + "oup_request.proto\032;request/user/relation"
                + "ship/update_relationship_request.proto\032("
                + "request/group/create_group_request.proto"
                + "\032(request/group/delete_group_request.pro"
                + "to\032(request/group/query_groups_request.p"
                + "roto\0322request/group/query_joined_group_i"
                + "ds_request.proto\0324request/group/query_jo"
                + "ined_group_infos_request.proto\032(request/"
                + "group/update_group_request.proto\032?reques"
                + "t/group/blocklist/create_group_blocked_u"
                + "ser_request.proto\032?request/group/blockli"
                + "st/delete_group_blocked_user_request.pro"
                + "to\032Brequest/group/blocklist/query_group_"
                + "blocked_user_ids_request.proto\032Drequest/"
                + "group/blocklist/query_group_blocked_user"
                + "_infos_request.proto\032Irequest/group/enro"
                + "llment/check_group_join_questions_answer"
                + "s_request.proto\032>request/group/enrollmen"
                + "t/create_group_invitation_request.proto\032"
                + "@request/group/enrollment/create_group_j"
                + "oin_request_request.proto\032Brequest/group"
                + "/enrollment/create_group_join_questions_"
                + "request.proto\032>request/group/enrollment/"
                + "delete_group_invitation_request.proto\032@r"
                + "equest/group/enrollment/delete_group_joi"
                + "n_request_request.proto\032Brequest/group/e"
                + "nrollment/delete_group_join_questions_re"
                + "quest.proto\032>request/group/enrollment/qu"
                + "ery_group_invitations_request.proto\032@req"
                + "uest/group/enrollment/query_group_join_r"
                + "equests_request.proto\032Arequest/group/enr"
                + "ollment/query_group_join_questions_reque"
                + "st.proto\032Arequest/group/enrollment/updat"
                + "e_group_join_question_request.proto\0327req"
                + "uest/group/member/create_group_members_r"
                + "equest.proto\0327request/group/member/delet"
                + "e_group_members_request.proto\0326request/g"
                + "roup/member/query_group_members_request."
                + "proto\0326request/group/member/update_group"
                + "_member_request.proto\0326request/conversat"
                + "ion/query_conversations_request.proto\0326r"
                + "equest/conversation/update_conversation_"
                + "request.proto\0327request/conversation/upda"
                + "te_typing_status_request.proto\032,request/"
                + "message/create_message_request.proto\032,re"
                + "quest/message/query_messages_request.pro"
                + "to\032,request/message/update_message_reque"
                + "st.proto\"\247&\n\014TurmsRequest\022\027\n\nrequest_id\030"
                + "\001 \001(\003H\001\210\001\001\022F\n\026create_session_request\030\003 \001"
                + "(\0132$.im.turms.proto.CreateSessionRequest"
                + "H\000\022F\n\026delete_session_request\030\004 \001(\0132$.im."
                + "turms.proto.DeleteSessionRequestH\000\022P\n\033qu"
                + "ery_conversations_request\030\005 \001(\0132).im.tur"
                + "ms.proto.QueryConversationsRequestH\000\022P\n\033"
                + "update_conversation_request\030\006 \001(\0132).im.t"
                + "urms.proto.UpdateConversationRequestH\000\022Q"
                + "\n\034update_typing_status_request\030\007 \001(\0132).i"
                + "m.turms.proto.UpdateTypingStatusRequestH"
                + "\000\022F\n\026create_message_request\030\010 \001(\0132$.im.t"
                + "urms.proto.CreateMessageRequestH\000\022F\n\026que"
                + "ry_messages_request\030\t \001(\0132$.im.turms.pro"
                + "to.QueryMessagesRequestH\000\022F\n\026update_mess"
                + "age_request\030\n \001(\0132$.im.turms.proto.Updat"
                + "eMessageRequestH\000\022Q\n\034create_group_member"
                + "s_request\030\013 \001(\0132).im.turms.proto.CreateG"
                + "roupMembersRequestH\000\022Q\n\034delete_group_mem"
                + "bers_request\030\014 \001(\0132).im.turms.proto.Dele"
                + "teGroupMembersRequestH\000\022O\n\033query_group_m"
                + "embers_request\030\r \001(\0132(.im.turms.proto.Qu"
                + "eryGroupMembersRequestH\000\022O\n\033update_group"
                + "_member_request\030\016 \001(\0132(.im.turms.proto.U"
                + "pdateGroupMemberRequestH\000\022O\n\033query_user_"
                + "profiles_request\030d \001(\0132(.im.turms.proto."
                + "QueryUserProfilesRequestH\000\022M\n\032query_near"
                + "by_users_request\030e \001(\0132\'.im.turms.proto."
                + "QueryNearbyUsersRequestH\000\022\\\n\"query_user_"
                + "online_statuses_request\030f \001(\0132..im.turms"
                + ".proto.QueryUserOnlineStatusesRequestH\000\022"
                + "Q\n\034update_user_location_request\030g \001(\0132)."
                + "im.turms.proto.UpdateUserLocationRequest"
                + "H\000\022Z\n!update_user_online_status_request\030"
                + "h \001(\0132-.im.turms.proto.UpdateUserOnlineS"
                + "tatusRequestH\000\022@\n\023update_user_request\030i "
                + "\001(\0132!.im.turms.proto.UpdateUserRequestH\000"
                + "\022T\n\035create_friend_request_request\030\310\001 \001(\013"
                + "2*.im.turms.proto.CreateFriendRequestReq"
                + "uestH\000\022\\\n!create_relationship_group_requ"
                + "est\030\311\001 \001(\0132..im.turms.proto.CreateRelati"
                + "onshipGroupRequestH\000\022Q\n\033create_relations"
                + "hip_request\030\312\001 \001(\0132).im.turms.proto.Crea"
                + "teRelationshipRequestH\000\022\\\n!delete_relati"
                + "onship_group_request\030\313\001 \001(\0132..im.turms.p"
                + "roto.DeleteRelationshipGroupRequestH\000\022Q\n"
                + "\033delete_relationship_request\030\314\001 \001(\0132).im"
                + ".turms.proto.DeleteRelationshipRequestH\000"
                + "\022T\n\035query_friend_requests_request\030\315\001 \001(\013"
                + "2*.im.turms.proto.QueryFriendRequestsReq"
                + "uestH\000\022U\n\036query_related_user_ids_request"
                + "\030\316\001 \001(\0132*.im.turms.proto.QueryRelatedUse"
                + "rIdsRequestH\000\022\\\n!query_relationship_grou"
                + "ps_request\030\317\001 \001(\0132..im.turms.proto.Query"
                + "RelationshipGroupsRequestH\000\022Q\n\033query_rel"
                + "ationships_request\030\320\001 \001(\0132).im.turms.pro"
                + "to.QueryRelationshipsRequestH\000\022T\n\035update"
                + "_friend_request_request\030\321\001 \001(\0132*.im.turm"
                + "s.proto.UpdateFriendRequestRequestH\000\022\\\n!"
                + "update_relationship_group_request\030\322\001 \001(\013"
                + "2..im.turms.proto.UpdateRelationshipGrou"
                + "pRequestH\000\022Q\n\033update_relationship_reques"
                + "t\030\323\001 \001(\0132).im.turms.proto.UpdateRelation"
                + "shipRequestH\000\022C\n\024create_group_request\030\254\002"
                + " \001(\0132\".im.turms.proto.CreateGroupRequest"
                + "H\000\022C\n\024delete_group_request\030\255\002 \001(\0132\".im.t"
                + "urms.proto.DeleteGroupRequestH\000\022C\n\024query"
                + "_groups_request\030\256\002 \001(\0132\".im.turms.proto."
                + "QueryGroupsRequestH\000\022U\n\036query_joined_gro"
                + "up_ids_request\030\257\002 \001(\0132*.im.turms.proto.Q"
                + "ueryJoinedGroupIdsRequestH\000\022Y\n query_joi"
                + "ned_group_infos_request\030\260\002 \001(\0132,.im.turm"
                + "s.proto.QueryJoinedGroupInfosRequestH\000\022C"
                + "\n\024update_group_request\030\261\002 \001(\0132\".im.turms"
                + ".proto.UpdateGroupRequestH\000\022[\n!create_gr"
                + "oup_blocked_user_request\030\220\003 \001(\0132-.im.tur"
                + "ms.proto.CreateGroupBlockedUserRequestH\000"
                + "\022[\n!delete_group_blocked_user_request\030\221\003"
                + " \001(\0132-.im.turms.proto.DeleteGroupBlocked"
                + "UserRequestH\000\022`\n$query_group_blocked_use"
                + "r_ids_request\030\222\003 \001(\0132/.im.turms.proto.Qu"
                + "eryGroupBlockedUserIdsRequestH\000\022d\n&query"
                + "_group_blocked_user_infos_request\030\223\003 \001(\013"
                + "21.im.turms.proto.QueryGroupBlockedUserI"
                + "nfosRequestH\000\022l\n*check_group_join_questi"
                + "ons_answers_request\030\364\003 \001(\01325.im.turms.pr"
                + "oto.CheckGroupJoinQuestionsAnswersReques"
                + "tH\000\022X\n\037create_group_invitation_request\030\365"
                + "\003 \001(\0132,.im.turms.proto.CreateGroupInvita"
                + "tionRequestH\000\022[\n!create_group_join_reque"
                + "st_request\030\366\003 \001(\0132-.im.turms.proto.Creat"
                + "eGroupJoinRequestRequestH\000\022_\n#create_gro"
                + "up_join_questions_request\030\367\003 \001(\0132/.im.tu"
                + "rms.proto.CreateGroupJoinQuestionsReques"
                + "tH\000\022X\n\037delete_group_invitation_request\030\370"
                + "\003 \001(\0132,.im.turms.proto.DeleteGroupInvita"
                + "tionRequestH\000\022[\n!delete_group_join_reque"
                + "st_request\030\371\003 \001(\0132-.im.turms.proto.Delet"
                + "eGroupJoinRequestRequestH\000\022_\n#delete_gro"
                + "up_join_questions_request\030\372\003 \001(\0132/.im.tu"
                + "rms.proto.DeleteGroupJoinQuestionsReques"
                + "tH\000\022X\n\037query_group_invitations_request\030\373"
                + "\003 \001(\0132,.im.turms.proto.QueryGroupInvitat"
                + "ionsRequestH\000\022[\n!query_group_join_reques"
                + "ts_request\030\374\003 \001(\0132-.im.turms.proto.Query"
                + "GroupJoinRequestsRequestH\000\022]\n\"query_grou"
                + "p_join_questions_request\030\375\003 \001(\0132..im.tur"
                + "ms.proto.QueryGroupJoinQuestionsRequestH"
                + "\000\022]\n\"update_group_join_question_request\030"
                + "\376\003 \001(\0132..im.turms.proto.UpdateGroupJoinQ"
                + "uestionRequestH\000\022I\n\027delete_resource_requ"
                + "est\030\350\007 \001(\0132%.im.turms.proto.DeleteResour"
                + "ceRequestH\000\022a\n$query_resource_download_i"
                + "nfo_request\030\351\007 \001(\01320.im.turms.proto.Quer"
                + "yResourceDownloadInfoRequestH\000\022]\n\"query_"
                + "resource_upload_info_request\030\352\007 \001(\0132..im"
                + ".turms.proto.QueryResourceUploadInfoRequ"
                + "estH\000\022e\n&query_message_attachment_infos_"
                + "request\030\353\007 \001(\01322.im.turms.proto.QueryMes"
                + "sageAttachmentInfosRequestH\000\022e\n&update_m"
                + "essage_attachment_info_request\030\354\007 \001(\01322."
                + "im.turms.proto.UpdateMessageAttachmentIn"
                + "foRequestH\000B\006\n\004kindB\r\n\013_request_idB7\n0im"
                + ".turms.server.common.access.client.dto.r"
                + "equestP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_TurmsRequest_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                        internal_static_im_turms_proto_TurmsRequest_descriptor,
                        new java.lang.String[]{"RequestId",
                                "CreateSessionRequest",
                                "DeleteSessionRequest",
                                "QueryConversationsRequest",
                                "UpdateConversationRequest",
                                "UpdateTypingStatusRequest",
                                "CreateMessageRequest",
                                "QueryMessagesRequest",
                                "UpdateMessageRequest",
                                "CreateGroupMembersRequest",
                                "DeleteGroupMembersRequest",
                                "QueryGroupMembersRequest",
                                "UpdateGroupMemberRequest",
                                "QueryUserProfilesRequest",
                                "QueryNearbyUsersRequest",
                                "QueryUserOnlineStatusesRequest",
                                "UpdateUserLocationRequest",
                                "UpdateUserOnlineStatusRequest",
                                "UpdateUserRequest",
                                "CreateFriendRequestRequest",
                                "CreateRelationshipGroupRequest",
                                "CreateRelationshipRequest",
                                "DeleteRelationshipGroupRequest",
                                "DeleteRelationshipRequest",
                                "QueryFriendRequestsRequest",
                                "QueryRelatedUserIdsRequest",
                                "QueryRelationshipGroupsRequest",
                                "QueryRelationshipsRequest",
                                "UpdateFriendRequestRequest",
                                "UpdateRelationshipGroupRequest",
                                "UpdateRelationshipRequest",
                                "CreateGroupRequest",
                                "DeleteGroupRequest",
                                "QueryGroupsRequest",
                                "QueryJoinedGroupIdsRequest",
                                "QueryJoinedGroupInfosRequest",
                                "UpdateGroupRequest",
                                "CreateGroupBlockedUserRequest",
                                "DeleteGroupBlockedUserRequest",
                                "QueryGroupBlockedUserIdsRequest",
                                "QueryGroupBlockedUserInfosRequest",
                                "CheckGroupJoinQuestionsAnswersRequest",
                                "CreateGroupInvitationRequest",
                                "CreateGroupJoinRequestRequest",
                                "CreateGroupJoinQuestionsRequest",
                                "DeleteGroupInvitationRequest",
                                "DeleteGroupJoinRequestRequest",
                                "DeleteGroupJoinQuestionsRequest",
                                "QueryGroupInvitationsRequest",
                                "QueryGroupJoinRequestsRequest",
                                "QueryGroupJoinQuestionsRequest",
                                "UpdateGroupJoinQuestionRequest",
                                "DeleteResourceRequest",
                                "QueryResourceDownloadInfoRequest",
                                "QueryResourceUploadInfoRequest",
                                "QueryMessageAttachmentInfosRequest",
                                "UpdateMessageAttachmentInfoRequest",
                                "Kind",
                                "RequestId",});
        im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.CreateFriendRequestRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.CreateRelationshipRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.QueryFriendRequestsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.QueryRelatedUserIdsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipGroupsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.QueryRelationshipsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.UpdateFriendRequestRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.member.CreateGroupMembersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.member.DeleteGroupMembersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.member.QueryGroupMembersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.member.UpdateGroupMemberRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}