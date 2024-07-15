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
                /* minor= */ 27,
                /* patch= */ 2,
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
                + "proto\032\030model/common/value.proto\032/request"
                + "/conference/create_meeting_request.proto"
                + "\032/request/conference/delete_meeting_requ"
                + "est.proto\032/request/conference/query_meet"
                + "ings_request.proto\032:request/conference/u"
                + "pdate_meeting_invitation_request.proto\032/"
                + "request/conference/update_meeting_reques"
                + "t.proto\032?request/conversation/delete_con"
                + "versation_settings_request.proto\032>reques"
                + "t/conversation/query_conversation_settin"
                + "gs_request.proto\0326request/conversation/q"
                + "uery_conversations_request.proto\0326reques"
                + "t/conversation/update_conversation_reque"
                + "st.proto\032?request/conversation/update_co"
                + "nversation_settings_request.proto\0327reque"
                + "st/conversation/update_typing_status_req"
                + "uest.proto\032?request/group/blocklist/crea"
                + "te_group_blocked_user_request.proto\032?req"
                + "uest/group/blocklist/delete_group_blocke"
                + "d_user_request.proto\032Brequest/group/bloc"
                + "klist/query_group_blocked_user_ids_reque"
                + "st.proto\032Drequest/group/blocklist/query_"
                + "group_blocked_user_infos_request.proto\032("
                + "request/group/create_group_request.proto"
                + "\032(request/group/delete_group_request.pro"
                + "to\032Irequest/group/enrollment/check_group"
                + "_join_questions_answers_request.proto\032>r"
                + "equest/group/enrollment/create_group_inv"
                + "itation_request.proto\032Brequest/group/enr"
                + "ollment/create_group_join_questions_requ"
                + "est.proto\032@request/group/enrollment/crea"
                + "te_group_join_request_request.proto\032>req"
                + "uest/group/enrollment/delete_group_invit"
                + "ation_request.proto\032Brequest/group/enrol"
                + "lment/delete_group_join_questions_reques"
                + "t.proto\032@request/group/enrollment/delete"
                + "_group_join_request_request.proto\032>reque"
                + "st/group/enrollment/query_group_invitati"
                + "ons_request.proto\032Arequest/group/enrollm"
                + "ent/query_group_join_questions_request.p"
                + "roto\032@request/group/enrollment/query_gro"
                + "up_join_requests_request.proto\032>request/"
                + "group/enrollment/update_group_invitation"
                + "_request.proto\032Arequest/group/enrollment"
                + "/update_group_join_question_request.prot"
                + "o\032@request/group/enrollment/update_group"
                + "_join_request_request.proto\0327request/gro"
                + "up/member/create_group_members_request.p"
                + "roto\0327request/group/member/delete_group_"
                + "members_request.proto\0326request/group/mem"
                + "ber/query_group_members_request.proto\0326r"
                + "equest/group/member/update_group_member_"
                + "request.proto\032(request/group/query_group"
                + "s_request.proto\0322request/group/query_joi"
                + "ned_group_ids_request.proto\0324request/gro"
                + "up/query_joined_group_infos_request.prot"
                + "o\032(request/group/update_group_request.pr"
                + "oto\0326request/message/create_message_reac"
                + "tions_request.proto\032,request/message/cre"
                + "ate_message_request.proto\0326request/messa"
                + "ge/delete_message_reactions_request.prot"
                + "o\032,request/message/query_messages_reques"
                + "t.proto\032,request/message/update_message_"
                + "request.proto\032-request/storage/delete_re"
                + "source_request.proto\032<request/storage/qu"
                + "ery_message_attachment_infos_request.pro"
                + "to\032:request/storage/query_resource_downl"
                + "oad_info_request.proto\0328request/storage/"
                + "query_resource_upload_info_request.proto"
                + "\032<request/storage/update_message_attachm"
                + "ent_info_request.proto\032)request/user/cre"
                + "ate_session_request.proto\032)request/user/"
                + "delete_session_request.proto\032/request/us"
                + "er/delete_user_settings_request.proto\032-r"
                + "equest/user/query_nearby_users_request.p"
                + "roto\0325request/user/query_user_online_sta"
                + "tuses_request.proto\032.request/user/query_"
                + "user_profiles_request.proto\032.request/use"
                + "r/query_user_settings_request.proto\032=req"
                + "uest/user/relationship/create_friend_req"
                + "uest_request.proto\032Arequest/user/relatio"
                + "nship/create_relationship_group_request."
                + "proto\032;request/user/relationship/create_"
                + "relationship_request.proto\032=request/user"
                + "/relationship/delete_friend_request_requ"
                + "est.proto\032Arequest/user/relationship/del"
                + "ete_relationship_group_request.proto\032;re"
                + "quest/user/relationship/delete_relations"
                + "hip_request.proto\032=request/user/relation"
                + "ship/query_friend_requests_request.proto"
                + "\032>request/user/relationship/query_relate"
                + "d_user_ids_request.proto\032Arequest/user/r"
                + "elationship/query_relationship_groups_re"
                + "quest.proto\032;request/user/relationship/q"
                + "uery_relationships_request.proto\032=reques"
                + "t/user/relationship/update_friend_reques"
                + "t_request.proto\032Arequest/user/relationsh"
                + "ip/update_relationship_group_request.pro"
                + "to\032;request/user/relationship/update_rel"
                + "ationship_request.proto\032/request/user/up"
                + "date_user_location_request.proto\0324reques"
                + "t/user/update_user_online_status_request"
                + ".proto\032&request/user/update_user_request"
                + ".proto\032/request/user/update_user_setting"
                + "s_request.proto\"\3011\n\014TurmsRequest\022\027\n\nrequ"
                + "est_id\030\001 \001(\003H\001\210\001\001\0220\n\021custom_attributes\030\002"
                + " \003(\0132\025.im.turms.proto.Value\022F\n\026create_se"
                + "ssion_request\030\003 \001(\0132$.im.turms.proto.Cre"
                + "ateSessionRequestH\000\022F\n\026delete_session_re"
                + "quest\030\004 \001(\0132$.im.turms.proto.DeleteSessi"
                + "onRequestH\000\022P\n\033query_conversations_reque"
                + "st\030\005 \001(\0132).im.turms.proto.QueryConversat"
                + "ionsRequestH\000\022P\n\033update_conversation_req"
                + "uest\030\006 \001(\0132).im.turms.proto.UpdateConver"
                + "sationRequestH\000\022Q\n\034update_typing_status_"
                + "request\030\007 \001(\0132).im.turms.proto.UpdateTyp"
                + "ingStatusRequestH\000\022F\n\026create_message_req"
                + "uest\030\010 \001(\0132$.im.turms.proto.CreateMessag"
                + "eRequestH\000\022F\n\026query_messages_request\030\t \001"
                + "(\0132$.im.turms.proto.QueryMessagesRequest"
                + "H\000\022F\n\026update_message_request\030\n \001(\0132$.im."
                + "turms.proto.UpdateMessageRequestH\000\022Q\n\034cr"
                + "eate_group_members_request\030\013 \001(\0132).im.tu"
                + "rms.proto.CreateGroupMembersRequestH\000\022Q\n"
                + "\034delete_group_members_request\030\014 \001(\0132).im"
                + ".turms.proto.DeleteGroupMembersRequestH\000"
                + "\022O\n\033query_group_members_request\030\r \001(\0132(."
                + "im.turms.proto.QueryGroupMembersRequestH"
                + "\000\022O\n\033update_group_member_request\030\016 \001(\0132("
                + ".im.turms.proto.UpdateGroupMemberRequest"
                + "H\000\022O\n\033query_user_profiles_request\030d \001(\0132"
                + "(.im.turms.proto.QueryUserProfilesReques"
                + "tH\000\022M\n\032query_nearby_users_request\030e \001(\0132"
                + "'.im.turms.proto.QueryNearbyUsersRequest"
                + "H\000\022\\\n\"query_user_online_statuses_request"
                + "\030f \001(\0132..im.turms.proto.QueryUserOnlineS"
                + "tatusesRequestH\000\022Q\n\034update_user_location"
                + "_request\030g \001(\0132).im.turms.proto.UpdateUs"
                + "erLocationRequestH\000\022Z\n!update_user_onlin"
                + "e_status_request\030h \001(\0132-.im.turms.proto."
                + "UpdateUserOnlineStatusRequestH\000\022@\n\023updat"
                + "e_user_request\030i \001(\0132!.im.turms.proto.Up"
                + "dateUserRequestH\000\022Q\n\034update_user_setting"
                + "s_request\030j \001(\0132).im.turms.proto.UpdateU"
                + "serSettingsRequestH\000\022Q\n\034delete_user_sett"
                + "ings_request\030k \001(\0132).im.turms.proto.Dele"
                + "teUserSettingsRequestH\000\022O\n\033query_user_se"
                + "ttings_request\030l \001(\0132(.im.turms.proto.Qu"
                + "eryUserSettingsRequestH\000\022T\n\035create_frien"
                + "d_request_request\030\310\001 \001(\0132*.im.turms.prot"
                + "o.CreateFriendRequestRequestH\000\022\\\n!create"
                + "_relationship_group_request\030\311\001 \001(\0132..im."
                + "turms.proto.CreateRelationshipGroupReque"
                + "stH\000\022Q\n\033create_relationship_request\030\312\001 \001"
                + "(\0132).im.turms.proto.CreateRelationshipRe"
                + "questH\000\022T\n\035delete_friend_request_request"
                + "\030\313\001 \001(\0132*.im.turms.proto.DeleteFriendReq"
                + "uestRequestH\000\022\\\n!delete_relationship_gro"
                + "up_request\030\314\001 \001(\0132..im.turms.proto.Delet"
                + "eRelationshipGroupRequestH\000\022Q\n\033delete_re"
                + "lationship_request\030\315\001 \001(\0132).im.turms.pro"
                + "to.DeleteRelationshipRequestH\000\022T\n\035query_"
                + "friend_requests_request\030\316\001 \001(\0132*.im.turm"
                + "s.proto.QueryFriendRequestsRequestH\000\022U\n\036"
                + "query_related_user_ids_request\030\317\001 \001(\0132*."
                + "im.turms.proto.QueryRelatedUserIdsReques"
                + "tH\000\022\\\n!query_relationship_groups_request"
                + "\030\320\001 \001(\0132..im.turms.proto.QueryRelationsh"
                + "ipGroupsRequestH\000\022Q\n\033query_relationships"
                + "_request\030\321\001 \001(\0132).im.turms.proto.QueryRe"
                + "lationshipsRequestH\000\022T\n\035update_friend_re"
                + "quest_request\030\322\001 \001(\0132*.im.turms.proto.Up"
                + "dateFriendRequestRequestH\000\022\\\n!update_rel"
                + "ationship_group_request\030\323\001 \001(\0132..im.turm"
                + "s.proto.UpdateRelationshipGroupRequestH\000"
                + "\022Q\n\033update_relationship_request\030\324\001 \001(\0132)"
                + ".im.turms.proto.UpdateRelationshipReques"
                + "tH\000\022C\n\024create_group_request\030\254\002 \001(\0132\".im."
                + "turms.proto.CreateGroupRequestH\000\022C\n\024dele"
                + "te_group_request\030\255\002 \001(\0132\".im.turms.proto"
                + ".DeleteGroupRequestH\000\022C\n\024query_groups_re"
                + "quest\030\256\002 \001(\0132\".im.turms.proto.QueryGroup"
                + "sRequestH\000\022U\n\036query_joined_group_ids_req"
                + "uest\030\257\002 \001(\0132*.im.turms.proto.QueryJoined"
                + "GroupIdsRequestH\000\022Y\n query_joined_group_"
                + "infos_request\030\260\002 \001(\0132,.im.turms.proto.Qu"
                + "eryJoinedGroupInfosRequestH\000\022C\n\024update_g"
                + "roup_request\030\261\002 \001(\0132\".im.turms.proto.Upd"
                + "ateGroupRequestH\000\022[\n!create_group_blocke"
                + "d_user_request\030\220\003 \001(\0132-.im.turms.proto.C"
                + "reateGroupBlockedUserRequestH\000\022[\n!delete"
                + "_group_blocked_user_request\030\221\003 \001(\0132-.im."
                + "turms.proto.DeleteGroupBlockedUserReques"
                + "tH\000\022`\n$query_group_blocked_user_ids_requ"
                + "est\030\222\003 \001(\0132/.im.turms.proto.QueryGroupBl"
                + "ockedUserIdsRequestH\000\022d\n&query_group_blo"
                + "cked_user_infos_request\030\223\003 \001(\01321.im.turm"
                + "s.proto.QueryGroupBlockedUserInfosReques"
                + "tH\000\022l\n*check_group_join_questions_answer"
                + "s_request\030\364\003 \001(\01325.im.turms.proto.CheckG"
                + "roupJoinQuestionsAnswersRequestH\000\022X\n\037cre"
                + "ate_group_invitation_request\030\365\003 \001(\0132,.im"
                + ".turms.proto.CreateGroupInvitationReques"
                + "tH\000\022[\n!create_group_join_request_request"
                + "\030\366\003 \001(\0132-.im.turms.proto.CreateGroupJoin"
                + "RequestRequestH\000\022_\n#create_group_join_qu"
                + "estions_request\030\367\003 \001(\0132/.im.turms.proto."
                + "CreateGroupJoinQuestionsRequestH\000\022X\n\037del"
                + "ete_group_invitation_request\030\370\003 \001(\0132,.im"
                + ".turms.proto.DeleteGroupInvitationReques"
                + "tH\000\022[\n!delete_group_join_request_request"
                + "\030\371\003 \001(\0132-.im.turms.proto.DeleteGroupJoin"
                + "RequestRequestH\000\022_\n#delete_group_join_qu"
                + "estions_request\030\372\003 \001(\0132/.im.turms.proto."
                + "DeleteGroupJoinQuestionsRequestH\000\022X\n\037que"
                + "ry_group_invitations_request\030\373\003 \001(\0132,.im"
                + ".turms.proto.QueryGroupInvitationsReques"
                + "tH\000\022[\n!query_group_join_requests_request"
                + "\030\374\003 \001(\0132-.im.turms.proto.QueryGroupJoinR"
                + "equestsRequestH\000\022]\n\"query_group_join_que"
                + "stions_request\030\375\003 \001(\0132..im.turms.proto.Q"
                + "ueryGroupJoinQuestionsRequestH\000\022X\n\037updat"
                + "e_group_invitation_request\030\376\003 \001(\0132,.im.t"
                + "urms.proto.UpdateGroupInvitationRequestH"
                + "\000\022]\n\"update_group_join_question_request\030"
                + "\377\003 \001(\0132..im.turms.proto.UpdateGroupJoinQ"
                + "uestionRequestH\000\022[\n!update_group_join_re"
                + "quest_request\030\200\004 \001(\0132-.im.turms.proto.Up"
                + "dateGroupJoinRequestRequestH\000\022G\n\026create_"
                + "meeting_request\030\204\007 \001(\0132$.im.turms.proto."
                + "CreateMeetingRequestH\000\022G\n\026delete_meeting"
                + "_request\030\205\007 \001(\0132$.im.turms.proto.DeleteM"
                + "eetingRequestH\000\022G\n\026query_meetings_reques"
                + "t\030\206\007 \001(\0132$.im.turms.proto.QueryMeetingsR"
                + "equestH\000\022G\n\026update_meeting_request\030\207\007 \001("
                + "\0132$.im.turms.proto.UpdateMeetingRequestH"
                + "\000\022\\\n!update_meeting_invitation_request\030\210"
                + "\007 \001(\0132..im.turms.proto.UpdateMeetingInvi"
                + "tationRequestH\000\022I\n\027delete_resource_reque"
                + "st\030\350\007 \001(\0132%.im.turms.proto.DeleteResourc"
                + "eRequestH\000\022a\n$query_resource_download_in"
                + "fo_request\030\351\007 \001(\01320.im.turms.proto.Query"
                + "ResourceDownloadInfoRequestH\000\022]\n\"query_r"
                + "esource_upload_info_request\030\352\007 \001(\0132..im."
                + "turms.proto.QueryResourceUploadInfoReque"
                + "stH\000\022e\n&query_message_attachment_infos_r"
                + "equest\030\353\007 \001(\01322.im.turms.proto.QueryMess"
                + "ageAttachmentInfosRequestH\000\022e\n&update_me"
                + "ssage_attachment_info_request\030\354\007 \001(\01322.i"
                + "m.turms.proto.UpdateMessageAttachmentInf"
                + "oRequestH\000\022b\n$delete_conversation_settin"
                + "gs_request\030\314\010 \001(\01321.im.turms.proto.Delet"
                + "eConversationSettingsRequestH\000\022`\n#query_"
                + "conversation_settings_request\030\315\010 \001(\01320.i"
                + "m.turms.proto.QueryConversationSettingsR"
                + "equestH\000\022b\n$update_conversation_settings"
                + "_request\030\316\010 \001(\01321.im.turms.proto.UpdateC"
                + "onversationSettingsRequestH\000\022Z\n create_m"
                + "essage_reactions_request\030\260\t \001(\0132-.im.tur"
                + "ms.proto.CreateMessageReactionsRequestH\000"
                + "\022Z\n delete_message_reactions_request\030\261\t "
                + "\001(\0132-.im.turms.proto.DeleteMessageReacti"
                + "onsRequestH\000B\006\n\004kindB\r\n\013_request_idB7\n0i"
                + "m.turms.server.common.access.client.dto."
                + "requestP\001\272\002\000b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.model.common.ValueOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.CreateMeetingRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.DeleteMeetingRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.DeleteConversationSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.QueryConversationSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOuterClass
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
                        im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.CreateMessageReactionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.DeleteMessageReactionsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.DeleteUserSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryUserSettingsRequestOuterClass
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
                        im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserSettingsRequestOuterClass
                                .getDescriptor(),});
        internal_static_im_turms_proto_TurmsRequest_descriptor = getDescriptor().getMessageTypes()
                .getFirst();
        internal_static_im_turms_proto_TurmsRequest_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_im_turms_proto_TurmsRequest_descriptor,
                        new java.lang.String[]{"RequestId",
                                "CustomAttributes",
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
                                "UpdateUserSettingsRequest",
                                "DeleteUserSettingsRequest",
                                "QueryUserSettingsRequest",
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
                                "DeleteConversationSettingsRequest",
                                "QueryConversationSettingsRequest",
                                "UpdateConversationSettingsRequest",
                                "CreateMessageReactionsRequest",
                                "DeleteMessageReactionsRequest",
                                "Kind",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.model.common.ValueOuterClass.getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.CreateMeetingRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.DeleteMeetingRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.QueryMeetingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingInvitationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conference.UpdateMeetingRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.DeleteConversationSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.QueryConversationSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.QueryConversationsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.CreateGroupBlockedUserRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.DeleteGroupBlockedUserRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserIdsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.blocklist.QueryGroupBlockedUserInfosRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.CreateGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.DeleteGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CheckGroupJoinQuestionsAnswersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupInvitationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinQuestionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.CreateGroupJoinRequestRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupInvitationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinQuestionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.DeleteGroupJoinRequestRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupInvitationsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinQuestionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.enrollment.QueryGroupJoinRequestsRequestOuterClass
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
        im.turms.server.common.access.client.dto.request.group.QueryGroupsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupIdsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.QueryJoinedGroupInfosRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.group.UpdateGroupRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.CreateMessageReactionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.CreateMessageRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.DeleteMessageReactionsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.QueryMessagesRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.message.UpdateMessageRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.DeleteUserSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryNearbyUsersRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryUserOnlineStatusesRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryUserProfilesRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryUserSettingsRequestOuterClass
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
        im.turms.server.common.access.client.dto.request.user.UpdateUserLocationRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserSettingsRequestOuterClass
                .getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}