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
                + "lationship_request.proto\032=request/user/r"
                + "elationship/delete_friend_request_reques"
                + "t.proto\032Arequest/user/relationship/delet"
                + "e_relationship_group_request.proto\032;requ"
                + "est/user/relationship/delete_relationshi"
                + "p_request.proto\032=request/user/relationsh"
                + "ip/query_friend_requests_request.proto\032>"
                + "request/user/relationship/query_related_"
                + "user_ids_request.proto\032Arequest/user/rel"
                + "ationship/query_relationship_groups_requ"
                + "est.proto\032;request/user/relationship/que"
                + "ry_relationships_request.proto\032=request/"
                + "user/relationship/update_friend_request_"
                + "request.proto\032Arequest/user/relationship"
                + "/update_relationship_group_request.proto"
                + "\032;request/user/relationship/update_relat"
                + "ionship_request.proto\032(request/group/cre"
                + "ate_group_request.proto\032(request/group/d"
                + "elete_group_request.proto\032(request/group"
                + "/query_groups_request.proto\0322request/gro"
                + "up/query_joined_group_ids_request.proto\032"
                + "4request/group/query_joined_group_infos_"
                + "request.proto\032(request/group/update_grou"
                + "p_request.proto\032?request/group/blocklist"
                + "/create_group_blocked_user_request.proto"
                + "\032?request/group/blocklist/delete_group_b"
                + "locked_user_request.proto\032Brequest/group"
                + "/blocklist/query_group_blocked_user_ids_"
                + "request.proto\032Drequest/group/blocklist/q"
                + "uery_group_blocked_user_infos_request.pr"
                + "oto\032Irequest/group/enrollment/check_grou"
                + "p_join_questions_answers_request.proto\032>"
                + "request/group/enrollment/create_group_in"
                + "vitation_request.proto\032@request/group/en"
                + "rollment/create_group_join_request_reque"
                + "st.proto\032Brequest/group/enrollment/creat"
                + "e_group_join_questions_request.proto\032>re"
                + "quest/group/enrollment/delete_group_invi"
                + "tation_request.proto\032@request/group/enro"
                + "llment/delete_group_join_request_request"
                + ".proto\032Brequest/group/enrollment/delete_"
                + "group_join_questions_request.proto\032>requ"
                + "est/group/enrollment/query_group_invitat"
                + "ions_request.proto\032@request/group/enroll"
                + "ment/query_group_join_requests_request.p"
                + "roto\032Arequest/group/enrollment/query_gro"
                + "up_join_questions_request.proto\032>request"
                + "/group/enrollment/update_group_invitatio"
                + "n_request.proto\032Arequest/group/enrollmen"
                + "t/update_group_join_question_request.pro"
                + "to\032@request/group/enrollment/update_grou"
                + "p_join_request_request.proto\0327request/gr"
                + "oup/member/create_group_members_request."
                + "proto\0327request/group/member/delete_group"
                + "_members_request.proto\0326request/group/me"
                + "mber/query_group_members_request.proto\0326"
                + "request/group/member/update_group_member"
                + "_request.proto\0326request/conversation/que"
                + "ry_conversations_request.proto\0326request/"
                + "conversation/update_conversation_request"
                + ".proto\0327request/conversation/update_typi"
                + "ng_status_request.proto\032,request/message"
                + "/create_message_request.proto\032,request/m"
                + "essage/query_messages_request.proto\032,req"
                + "uest/message/update_message_request.prot"
                + "o\"\264(\n\014TurmsRequest\022\027\n\nrequest_id\030\001 \001(\003H\001"
                + "\210\001\001\022F\n\026create_session_request\030\003 \001(\0132$.im"
                + ".turms.proto.CreateSessionRequestH\000\022F\n\026d"
                + "elete_session_request\030\004 \001(\0132$.im.turms.p"
                + "roto.DeleteSessionRequestH\000\022P\n\033query_con"
                + "versations_request\030\005 \001(\0132).im.turms.prot"
                + "o.QueryConversationsRequestH\000\022P\n\033update_"
                + "conversation_request\030\006 \001(\0132).im.turms.pr"
                + "oto.UpdateConversationRequestH\000\022Q\n\034updat"
                + "e_typing_status_request\030\007 \001(\0132).im.turms"
                + ".proto.UpdateTypingStatusRequestH\000\022F\n\026cr"
                + "eate_message_request\030\010 \001(\0132$.im.turms.pr"
                + "oto.CreateMessageRequestH\000\022F\n\026query_mess"
                + "ages_request\030\t \001(\0132$.im.turms.proto.Quer"
                + "yMessagesRequestH\000\022F\n\026update_message_req"
                + "uest\030\n \001(\0132$.im.turms.proto.UpdateMessag"
                + "eRequestH\000\022Q\n\034create_group_members_reque"
                + "st\030\013 \001(\0132).im.turms.proto.CreateGroupMem"
                + "bersRequestH\000\022Q\n\034delete_group_members_re"
                + "quest\030\014 \001(\0132).im.turms.proto.DeleteGroup"
                + "MembersRequestH\000\022O\n\033query_group_members_"
                + "request\030\r \001(\0132(.im.turms.proto.QueryGrou"
                + "pMembersRequestH\000\022O\n\033update_group_member"
                + "_request\030\016 \001(\0132(.im.turms.proto.UpdateGr"
                + "oupMemberRequestH\000\022O\n\033query_user_profile"
                + "s_request\030d \001(\0132(.im.turms.proto.QueryUs"
                + "erProfilesRequestH\000\022M\n\032query_nearby_user"
                + "s_request\030e \001(\0132\'.im.turms.proto.QueryNe"
                + "arbyUsersRequestH\000\022\\\n\"query_user_online_"
                + "statuses_request\030f \001(\0132..im.turms.proto."
                + "QueryUserOnlineStatusesRequestH\000\022Q\n\034upda"
                + "te_user_location_request\030g \001(\0132).im.turm"
                + "s.proto.UpdateUserLocationRequestH\000\022Z\n!u"
                + "pdate_user_online_status_request\030h \001(\0132-"
                + ".im.turms.proto.UpdateUserOnlineStatusRe"
                + "questH\000\022@\n\023update_user_request\030i \001(\0132!.i"
                + "m.turms.proto.UpdateUserRequestH\000\022T\n\035cre"
                + "ate_friend_request_request\030\310\001 \001(\0132*.im.t"
                + "urms.proto.CreateFriendRequestRequestH\000\022"
                + "\\\n!create_relationship_group_request\030\311\001 "
                + "\001(\0132..im.turms.proto.CreateRelationshipG"
                + "roupRequestH\000\022Q\n\033create_relationship_req"
                + "uest\030\312\001 \001(\0132).im.turms.proto.CreateRelat"
                + "ionshipRequestH\000\022T\n\035delete_friend_reques"
                + "t_request\030\313\001 \001(\0132*.im.turms.proto.Delete"
                + "FriendRequestRequestH\000\022\\\n!delete_relatio"
                + "nship_group_request\030\314\001 \001(\0132..im.turms.pr"
                + "oto.DeleteRelationshipGroupRequestH\000\022Q\n\033"
                + "delete_relationship_request\030\315\001 \001(\0132).im."
                + "turms.proto.DeleteRelationshipRequestH\000\022"
                + "T\n\035query_friend_requests_request\030\316\001 \001(\0132"
                + "*.im.turms.proto.QueryFriendRequestsRequ"
                + "estH\000\022U\n\036query_related_user_ids_request\030"
                + "\317\001 \001(\0132*.im.turms.proto.QueryRelatedUser"
                + "IdsRequestH\000\022\\\n!query_relationship_group"
                + "s_request\030\320\001 \001(\0132..im.turms.proto.QueryR"
                + "elationshipGroupsRequestH\000\022Q\n\033query_rela"
                + "tionships_request\030\321\001 \001(\0132).im.turms.prot"
                + "o.QueryRelationshipsRequestH\000\022T\n\035update_"
                + "friend_request_request\030\322\001 \001(\0132*.im.turms"
                + ".proto.UpdateFriendRequestRequestH\000\022\\\n!u"
                + "pdate_relationship_group_request\030\323\001 \001(\0132"
                + "..im.turms.proto.UpdateRelationshipGroup"
                + "RequestH\000\022Q\n\033update_relationship_request"
                + "\030\324\001 \001(\0132).im.turms.proto.UpdateRelations"
                + "hipRequestH\000\022C\n\024create_group_request\030\254\002 "
                + "\001(\0132\".im.turms.proto.CreateGroupRequestH"
                + "\000\022C\n\024delete_group_request\030\255\002 \001(\0132\".im.tu"
                + "rms.proto.DeleteGroupRequestH\000\022C\n\024query_"
                + "groups_request\030\256\002 \001(\0132\".im.turms.proto.Q"
                + "ueryGroupsRequestH\000\022U\n\036query_joined_grou"
                + "p_ids_request\030\257\002 \001(\0132*.im.turms.proto.Qu"
                + "eryJoinedGroupIdsRequestH\000\022Y\n query_join"
                + "ed_group_infos_request\030\260\002 \001(\0132,.im.turms"
                + ".proto.QueryJoinedGroupInfosRequestH\000\022C\n"
                + "\024update_group_request\030\261\002 \001(\0132\".im.turms."
                + "proto.UpdateGroupRequestH\000\022[\n!create_gro"
                + "up_blocked_user_request\030\220\003 \001(\0132-.im.turm"
                + "s.proto.CreateGroupBlockedUserRequestH\000\022"
                + "[\n!delete_group_blocked_user_request\030\221\003 "
                + "\001(\0132-.im.turms.proto.DeleteGroupBlockedU"
                + "serRequestH\000\022`\n$query_group_blocked_user"
                + "_ids_request\030\222\003 \001(\0132/.im.turms.proto.Que"
                + "ryGroupBlockedUserIdsRequestH\000\022d\n&query_"
                + "group_blocked_user_infos_request\030\223\003 \001(\0132"
                + "1.im.turms.proto.QueryGroupBlockedUserIn"
                + "fosRequestH\000\022l\n*check_group_join_questio"
                + "ns_answers_request\030\364\003 \001(\01325.im.turms.pro"
                + "to.CheckGroupJoinQuestionsAnswersRequest"
                + "H\000\022X\n\037create_group_invitation_request\030\365\003"
                + " \001(\0132,.im.turms.proto.CreateGroupInvitat"
                + "ionRequestH\000\022[\n!create_group_join_reques"
                + "t_request\030\366\003 \001(\0132-.im.turms.proto.Create"
                + "GroupJoinRequestRequestH\000\022_\n#create_grou"
                + "p_join_questions_request\030\367\003 \001(\0132/.im.tur"
                + "ms.proto.CreateGroupJoinQuestionsRequest"
                + "H\000\022X\n\037delete_group_invitation_request\030\370\003"
                + " \001(\0132,.im.turms.proto.DeleteGroupInvitat"
                + "ionRequestH\000\022[\n!delete_group_join_reques"
                + "t_request\030\371\003 \001(\0132-.im.turms.proto.Delete"
                + "GroupJoinRequestRequestH\000\022_\n#delete_grou"
                + "p_join_questions_request\030\372\003 \001(\0132/.im.tur"
                + "ms.proto.DeleteGroupJoinQuestionsRequest"
                + "H\000\022X\n\037query_group_invitations_request\030\373\003"
                + " \001(\0132,.im.turms.proto.QueryGroupInvitati"
                + "onsRequestH\000\022[\n!query_group_join_request"
                + "s_request\030\374\003 \001(\0132-.im.turms.proto.QueryG"
                + "roupJoinRequestsRequestH\000\022]\n\"query_group"
                + "_join_questions_request\030\375\003 \001(\0132..im.turm"
                + "s.proto.QueryGroupJoinQuestionsRequestH\000"
                + "\022X\n\037update_group_invitation_request\030\376\003 \001"
                + "(\0132,.im.turms.proto.UpdateGroupInvitatio"
                + "nRequestH\000\022]\n\"update_group_join_question"
                + "_request\030\377\003 \001(\0132..im.turms.proto.UpdateG"
                + "roupJoinQuestionRequestH\000\022[\n!update_grou"
                + "p_join_request_request\030\200\004 \001(\0132-.im.turms"
                + ".proto.UpdateGroupJoinRequestRequestH\000\022I"
                + "\n\027delete_resource_request\030\350\007 \001(\0132%.im.tu"
                + "rms.proto.DeleteResourceRequestH\000\022a\n$que"
                + "ry_resource_download_info_request\030\351\007 \001(\013"
                + "20.im.turms.proto.QueryResourceDownloadI"
                + "nfoRequestH\000\022]\n\"query_resource_upload_in"
                + "fo_request\030\352\007 \001(\0132..im.turms.proto.Query"
                + "ResourceUploadInfoRequestH\000\022e\n&query_mes"
                + "sage_attachment_infos_request\030\353\007 \001(\01322.i"
                + "m.turms.proto.QueryMessageAttachmentInfo"
                + "sRequestH\000\022e\n&update_message_attachment_"
                + "info_request\030\354\007 \001(\01322.im.turms.proto.Upd"
                + "ateMessageAttachmentInfoRequestH\000B\006\n\004kin"
                + "dB\r\n\013_request_idB7\n0im.turms.server.comm"
                + "on.access.client.dto.requestP\001\272\002\000b\006proto"
                + "3"};
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
                        im.turms.server.common.access.client.dto.request.user.relationship.DeleteFriendRequestRequestOuterClass
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
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass
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
                                "DeleteFriendRequestRequest",
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
                                "UpdateGroupInvitationRequest",
                                "UpdateGroupJoinQuestionRequest",
                                "UpdateGroupJoinRequestRequest",
                                "DeleteResourceRequest",
                                "QueryResourceDownloadInfoRequest",
                                "QueryResourceUploadInfoRequest",
                                "QueryMessageAttachmentInfosRequest",
                                "UpdateMessageAttachmentInfoRequest",
                                "Kind",});
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
        im.turms.server.common.access.client.dto.request.user.relationship.DeleteFriendRequestRequestOuterClass
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
        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupInvitationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinQuestionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.UpdateGroupJoinRequestRequestOuterClass
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