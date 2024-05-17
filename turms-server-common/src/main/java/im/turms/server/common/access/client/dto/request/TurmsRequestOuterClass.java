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

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                TurmsRequestOuterClass.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_im_turms_proto_TurmsRequest_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData = {"\n\033request/turms_request.proto\022\016im.turms."
                + "proto\032)request/user/create_session_reque"
                + "st.proto\032)request/user/delete_session_re"
                + "quest.proto\032-request/user/query_nearby_u"
                + "sers_request.proto\0325request/user/query_u"
                + "ser_online_statuses_request.proto\032.reque"
                + "st/user/query_user_profiles_request.prot"
                + "o\032/request/user/update_user_location_req"
                + "uest.proto\0324request/user/update_user_onl"
                + "ine_status_request.proto\032&request/user/u"
                + "pdate_user_request.proto\032=request/user/r"
                + "elationship/create_friend_request_reques"
                + "t.proto\032Arequest/user/relationship/creat"
                + "e_relationship_group_request.proto\032;requ"
                + "est/user/relationship/create_relationshi"
                + "p_request.proto\032=request/user/relationsh"
                + "ip/delete_friend_request_request.proto\032A"
                + "request/user/relationship/delete_relatio"
                + "nship_group_request.proto\032;request/user/"
                + "relationship/delete_relationship_request"
                + ".proto\032=request/user/relationship/query_"
                + "friend_requests_request.proto\032>request/u"
                + "ser/relationship/query_related_user_ids_"
                + "request.proto\032Arequest/user/relationship"
                + "/query_relationship_groups_request.proto"
                + "\032;request/user/relationship/query_relati"
                + "onships_request.proto\032=request/user/rela"
                + "tionship/update_friend_request_request.p"
                + "roto\032Arequest/user/relationship/update_r"
                + "elationship_group_request.proto\032;request"
                + "/user/relationship/update_relationship_r"
                + "equest.proto\032(request/group/create_group"
                + "_request.proto\032(request/group/delete_gro"
                + "up_request.proto\032(request/group/query_gr"
                + "oups_request.proto\0322request/group/query_"
                + "joined_group_ids_request.proto\0324request/"
                + "group/query_joined_group_infos_request.p"
                + "roto\032(request/group/update_group_request"
                + ".proto\032?request/group/blocklist/create_g"
                + "roup_blocked_user_request.proto\032?request"
                + "/group/blocklist/delete_group_blocked_us"
                + "er_request.proto\032Brequest/group/blocklis"
                + "t/query_group_blocked_user_ids_request.p"
                + "roto\032Drequest/group/blocklist/query_grou"
                + "p_blocked_user_infos_request.proto\032Irequ"
                + "est/group/enrollment/check_group_join_qu"
                + "estions_answers_request.proto\032>request/g"
                + "roup/enrollment/create_group_invitation_"
                + "request.proto\032@request/group/enrollment/"
                + "create_group_join_request_request.proto\032"
                + "Brequest/group/enrollment/create_group_j"
                + "oin_questions_request.proto\032>request/gro"
                + "up/enrollment/delete_group_invitation_re"
                + "quest.proto\032@request/group/enrollment/de"
                + "lete_group_join_request_request.proto\032Br"
                + "equest/group/enrollment/delete_group_joi"
                + "n_questions_request.proto\032>request/group"
                + "/enrollment/query_group_invitations_requ"
                + "est.proto\032@request/group/enrollment/quer"
                + "y_group_join_requests_request.proto\032Areq"
                + "uest/group/enrollment/query_group_join_q"
                + "uestions_request.proto\032>request/group/en"
                + "rollment/update_group_invitation_request"
                + ".proto\032Arequest/group/enrollment/update_"
                + "group_join_question_request.proto\032@reque"
                + "st/group/enrollment/update_group_join_re"
                + "quest_request.proto\0327request/group/membe"
                + "r/create_group_members_request.proto\0327re"
                + "quest/group/member/delete_group_members_"
                + "request.proto\0326request/group/member/quer"
                + "y_group_members_request.proto\0326request/g"
                + "roup/member/update_group_member_request."
                + "proto\0326request/conversation/query_conver"
                + "sations_request.proto\0326request/conversat"
                + "ion/update_conversation_request.proto\0327r"
                + "equest/conversation/update_typing_status"
                + "_request.proto\032,request/message/create_m"
                + "essage_request.proto\032,request/message/qu"
                + "ery_messages_request.proto\032,request/mess"
                + "age/update_message_request.proto\032/reques"
                + "t/conference/create_meeting_request.prot"
                + "o\032/request/conference/delete_meeting_req"
                + "uest.proto\032/request/conference/query_mee"
                + "tings_request.proto\032/request/conference/"
                + "update_meeting_request.proto\032:request/co"
                + "nference/update_meeting_invitation_reque"
                + "st.proto\032-request/storage/delete_resourc"
                + "e_request.proto\032:request/storage/query_r"
                + "esource_download_info_request.proto\0328req"
                + "uest/storage/query_resource_upload_info_"
                + "request.proto\032<request/storage/query_mes"
                + "sage_attachment_infos_request.proto\032<req"
                + "uest/storage/update_message_attachment_i"
                + "nfo_request.proto\"\266+\n\014TurmsRequest\022\027\n\nre"
                + "quest_id\030\001 \001(\003H\001\210\001\001\022F\n\026create_session_re"
                + "quest\030\003 \001(\0132$.im.turms.proto.CreateSessi"
                + "onRequestH\000\022F\n\026delete_session_request\030\004 "
                + "\001(\0132$.im.turms.proto.DeleteSessionReques"
                + "tH\000\022P\n\033query_conversations_request\030\005 \001(\013"
                + "2).im.turms.proto.QueryConversationsRequ"
                + "estH\000\022P\n\033update_conversation_request\030\006 \001"
                + "(\0132).im.turms.proto.UpdateConversationRe"
                + "questH\000\022Q\n\034update_typing_status_request\030"
                + "\007 \001(\0132).im.turms.proto.UpdateTypingStatu"
                + "sRequestH\000\022F\n\026create_message_request\030\010 \001"
                + "(\0132$.im.turms.proto.CreateMessageRequest"
                + "H\000\022F\n\026query_messages_request\030\t \001(\0132$.im."
                + "turms.proto.QueryMessagesRequestH\000\022F\n\026up"
                + "date_message_request\030\n \001(\0132$.im.turms.pr"
                + "oto.UpdateMessageRequestH\000\022Q\n\034create_gro"
                + "up_members_request\030\013 \001(\0132).im.turms.prot"
                + "o.CreateGroupMembersRequestH\000\022Q\n\034delete_"
                + "group_members_request\030\014 \001(\0132).im.turms.p"
                + "roto.DeleteGroupMembersRequestH\000\022O\n\033quer"
                + "y_group_members_request\030\r \001(\0132(.im.turms"
                + ".proto.QueryGroupMembersRequestH\000\022O\n\033upd"
                + "ate_group_member_request\030\016 \001(\0132(.im.turm"
                + "s.proto.UpdateGroupMemberRequestH\000\022O\n\033qu"
                + "ery_user_profiles_request\030d \001(\0132(.im.tur"
                + "ms.proto.QueryUserProfilesRequestH\000\022M\n\032q"
                + "uery_nearby_users_request\030e \001(\0132\'.im.tur"
                + "ms.proto.QueryNearbyUsersRequestH\000\022\\\n\"qu"
                + "ery_user_online_statuses_request\030f \001(\0132."
                + ".im.turms.proto.QueryUserOnlineStatusesR"
                + "equestH\000\022Q\n\034update_user_location_request"
                + "\030g \001(\0132).im.turms.proto.UpdateUserLocati"
                + "onRequestH\000\022Z\n!update_user_online_status"
                + "_request\030h \001(\0132-.im.turms.proto.UpdateUs"
                + "erOnlineStatusRequestH\000\022@\n\023update_user_r"
                + "equest\030i \001(\0132!.im.turms.proto.UpdateUser"
                + "RequestH\000\022T\n\035create_friend_request_reque"
                + "st\030\310\001 \001(\0132*.im.turms.proto.CreateFriendR"
                + "equestRequestH\000\022\\\n!create_relationship_g"
                + "roup_request\030\311\001 \001(\0132..im.turms.proto.Cre"
                + "ateRelationshipGroupRequestH\000\022Q\n\033create_"
                + "relationship_request\030\312\001 \001(\0132).im.turms.p"
                + "roto.CreateRelationshipRequestH\000\022T\n\035dele"
                + "te_friend_request_request\030\313\001 \001(\0132*.im.tu"
                + "rms.proto.DeleteFriendRequestRequestH\000\022\\"
                + "\n!delete_relationship_group_request\030\314\001 \001"
                + "(\0132..im.turms.proto.DeleteRelationshipGr"
                + "oupRequestH\000\022Q\n\033delete_relationship_requ"
                + "est\030\315\001 \001(\0132).im.turms.proto.DeleteRelati"
                + "onshipRequestH\000\022T\n\035query_friend_requests"
                + "_request\030\316\001 \001(\0132*.im.turms.proto.QueryFr"
                + "iendRequestsRequestH\000\022U\n\036query_related_u"
                + "ser_ids_request\030\317\001 \001(\0132*.im.turms.proto."
                + "QueryRelatedUserIdsRequestH\000\022\\\n!query_re"
                + "lationship_groups_request\030\320\001 \001(\0132..im.tu"
                + "rms.proto.QueryRelationshipGroupsRequest"
                + "H\000\022Q\n\033query_relationships_request\030\321\001 \001(\013"
                + "2).im.turms.proto.QueryRelationshipsRequ"
                + "estH\000\022T\n\035update_friend_request_request\030\322"
                + "\001 \001(\0132*.im.turms.proto.UpdateFriendReque"
                + "stRequestH\000\022\\\n!update_relationship_group"
                + "_request\030\323\001 \001(\0132..im.turms.proto.UpdateR"
                + "elationshipGroupRequestH\000\022Q\n\033update_rela"
                + "tionship_request\030\324\001 \001(\0132).im.turms.proto"
                + ".UpdateRelationshipRequestH\000\022C\n\024create_g"
                + "roup_request\030\254\002 \001(\0132\".im.turms.proto.Cre"
                + "ateGroupRequestH\000\022C\n\024delete_group_reques"
                + "t\030\255\002 \001(\0132\".im.turms.proto.DeleteGroupReq"
                + "uestH\000\022C\n\024query_groups_request\030\256\002 \001(\0132\"."
                + "im.turms.proto.QueryGroupsRequestH\000\022U\n\036q"
                + "uery_joined_group_ids_request\030\257\002 \001(\0132*.i"
                + "m.turms.proto.QueryJoinedGroupIdsRequest"
                + "H\000\022Y\n query_joined_group_infos_request\030\260"
                + "\002 \001(\0132,.im.turms.proto.QueryJoinedGroupI"
                + "nfosRequestH\000\022C\n\024update_group_request\030\261\002"
                + " \001(\0132\".im.turms.proto.UpdateGroupRequest"
                + "H\000\022[\n!create_group_blocked_user_request\030"
                + "\220\003 \001(\0132-.im.turms.proto.CreateGroupBlock"
                + "edUserRequestH\000\022[\n!delete_group_blocked_"
                + "user_request\030\221\003 \001(\0132-.im.turms.proto.Del"
                + "eteGroupBlockedUserRequestH\000\022`\n$query_gr"
                + "oup_blocked_user_ids_request\030\222\003 \001(\0132/.im"
                + ".turms.proto.QueryGroupBlockedUserIdsReq"
                + "uestH\000\022d\n&query_group_blocked_user_infos"
                + "_request\030\223\003 \001(\01321.im.turms.proto.QueryGr"
                + "oupBlockedUserInfosRequestH\000\022l\n*check_gr"
                + "oup_join_questions_answers_request\030\364\003 \001("
                + "\01325.im.turms.proto.CheckGroupJoinQuestio"
                + "nsAnswersRequestH\000\022X\n\037create_group_invit"
                + "ation_request\030\365\003 \001(\0132,.im.turms.proto.Cr"
                + "eateGroupInvitationRequestH\000\022[\n!create_g"
                + "roup_join_request_request\030\366\003 \001(\0132-.im.tu"
                + "rms.proto.CreateGroupJoinRequestRequestH"
                + "\000\022_\n#create_group_join_questions_request"
                + "\030\367\003 \001(\0132/.im.turms.proto.CreateGroupJoin"
                + "QuestionsRequestH\000\022X\n\037delete_group_invit"
                + "ation_request\030\370\003 \001(\0132,.im.turms.proto.De"
                + "leteGroupInvitationRequestH\000\022[\n!delete_g"
                + "roup_join_request_request\030\371\003 \001(\0132-.im.tu"
                + "rms.proto.DeleteGroupJoinRequestRequestH"
                + "\000\022_\n#delete_group_join_questions_request"
                + "\030\372\003 \001(\0132/.im.turms.proto.DeleteGroupJoin"
                + "QuestionsRequestH\000\022X\n\037query_group_invita"
                + "tions_request\030\373\003 \001(\0132,.im.turms.proto.Qu"
                + "eryGroupInvitationsRequestH\000\022[\n!query_gr"
                + "oup_join_requests_request\030\374\003 \001(\0132-.im.tu"
                + "rms.proto.QueryGroupJoinRequestsRequestH"
                + "\000\022]\n\"query_group_join_questions_request\030"
                + "\375\003 \001(\0132..im.turms.proto.QueryGroupJoinQu"
                + "estionsRequestH\000\022X\n\037update_group_invitat"
                + "ion_request\030\376\003 \001(\0132,.im.turms.proto.Upda"
                + "teGroupInvitationRequestH\000\022]\n\"update_gro"
                + "up_join_question_request\030\377\003 \001(\0132..im.tur"
                + "ms.proto.UpdateGroupJoinQuestionRequestH"
                + "\000\022[\n!update_group_join_request_request\030\200"
                + "\004 \001(\0132-.im.turms.proto.UpdateGroupJoinRe"
                + "questRequestH\000\022G\n\026create_meeting_request"
                + "\030\204\007 \001(\0132$.im.turms.proto.CreateMeetingRe"
                + "questH\000\022G\n\026delete_meeting_request\030\205\007 \001(\013"
                + "2$.im.turms.proto.DeleteMeetingRequestH\000"
                + "\022G\n\026query_meetings_request\030\206\007 \001(\0132$.im.t"
                + "urms.proto.QueryMeetingsRequestH\000\022G\n\026upd"
                + "ate_meeting_request\030\207\007 \001(\0132$.im.turms.pr"
                + "oto.UpdateMeetingRequestH\000\022\\\n!update_mee"
                + "ting_invitation_request\030\210\007 \001(\0132..im.turm"
                + "s.proto.UpdateMeetingInvitationRequestH\000"
                + "\022I\n\027delete_resource_request\030\350\007 \001(\0132%.im."
                + "turms.proto.DeleteResourceRequestH\000\022a\n$q"
                + "uery_resource_download_info_request\030\351\007 \001"
                + "(\01320.im.turms.proto.QueryResourceDownloa"
                + "dInfoRequestH\000\022]\n\"query_resource_upload_"
                + "info_request\030\352\007 \001(\0132..im.turms.proto.Que"
                + "ryResourceUploadInfoRequestH\000\022e\n&query_m"
                + "essage_attachment_infos_request\030\353\007 \001(\01322"
                + ".im.turms.proto.QueryMessageAttachmentIn"
                + "fosRequestH\000\022e\n&update_message_attachmen"
                + "t_info_request\030\354\007 \001(\01322.im.turms.proto.U"
                + "pdateMessageAttachmentInfoRequestH\000B\006\n\004k"
                + "indB\r\n\013_request_idB7\n0im.turms.server.co"
                + "mmon.access.client.dto.requestP\001\272\002\000b\006pro"
                + "to3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
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
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.CreateMeetingRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.DeleteMeetingRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_TurmsRequest_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
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
                                "CreateMeetingRequest",
                                "DeleteMeetingRequest",
                                "QueryMeetingsRequest",
                                "UpdateMeetingRequest",
                                "UpdateMeetingInvitationRequest",
                                "DeleteResourceRequest",
                                "QueryResourceDownloadInfoRequest",
                                "QueryResourceUploadInfoRequest",
                                "QueryMessageAttachmentInfosRequest",
                                "UpdateMessageAttachmentInfoRequest",
                                "Kind",});
        descriptor.resolveAllFeaturesImmutable();
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
        im.turms.server.common.access.client.dto.request.conference.CreateMeetingRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.DeleteMeetingRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingInvitationRequestOuterClass
                .getDescriptor();
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
    }

    // @@protoc_insertion_point(outer_class_scope)
}