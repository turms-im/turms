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
                /* patch= */ 0,
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
                + "quest.proto\032/request/user/delete_user_se"
                + "ttings_request.proto\032.request/user/query"
                + "_user_settings_request.proto\032/request/us"
                + "er/update_user_settings_request.proto\032-r"
                + "equest/user/query_nearby_users_request.p"
                + "roto\0325request/user/query_user_online_sta"
                + "tuses_request.proto\032.request/user/query_"
                + "user_profiles_request.proto\032/request/use"
                + "r/update_user_location_request.proto\0324re"
                + "quest/user/update_user_online_status_req"
                + "uest.proto\032&request/user/update_user_req"
                + "uest.proto\032=request/user/relationship/cr"
                + "eate_friend_request_request.proto\032Areque"
                + "st/user/relationship/create_relationship"
                + "_group_request.proto\032;request/user/relat"
                + "ionship/create_relationship_request.prot"
                + "o\032=request/user/relationship/delete_frie"
                + "nd_request_request.proto\032Arequest/user/r"
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
                + "st.proto\032>request/group/enrollment/updat"
                + "e_group_invitation_request.proto\032Areques"
                + "t/group/enrollment/update_group_join_que"
                + "stion_request.proto\032@request/group/enrol"
                + "lment/update_group_join_request_request."
                + "proto\0327request/group/member/create_group"
                + "_members_request.proto\0327request/group/me"
                + "mber/delete_group_members_request.proto\032"
                + "6request/group/member/query_group_member"
                + "s_request.proto\0326request/group/member/up"
                + "date_group_member_request.proto\0326request"
                + "/conversation/query_conversations_reques"
                + "t.proto\0326request/conversation/update_con"
                + "versation_request.proto\0327request/convers"
                + "ation/update_typing_status_request.proto"
                + "\032?request/conversation/delete_conversati"
                + "on_settings_request.proto\032>request/conve"
                + "rsation/query_conversation_settings_requ"
                + "est.proto\032?request/conversation/update_c"
                + "onversation_settings_request.proto\032,requ"
                + "est/message/create_message_request.proto"
                + "\032,request/message/query_messages_request"
                + ".proto\032,request/message/update_message_r"
                + "equest.proto\032/request/conference/create_"
                + "meeting_request.proto\032/request/conferenc"
                + "e/delete_meeting_request.proto\032/request/"
                + "conference/query_meetings_request.proto\032"
                + "/request/conference/update_meeting_reque"
                + "st.proto\032:request/conference/update_meet"
                + "ing_invitation_request.proto\032-request/st"
                + "orage/delete_resource_request.proto\032:req"
                + "uest/storage/query_resource_download_inf"
                + "o_request.proto\0328request/storage/query_r"
                + "esource_upload_info_request.proto\032<reque"
                + "st/storage/query_message_attachment_info"
                + "s_request.proto\032<request/storage/update_"
                + "message_attachment_info_request.proto\"\327/"
                + "\n\014TurmsRequest\022\027\n\nrequest_id\030\001 \001(\003H\001\210\001\001\022"
                + "F\n\026create_session_request\030\003 \001(\0132$.im.tur"
                + "ms.proto.CreateSessionRequestH\000\022F\n\026delet"
                + "e_session_request\030\004 \001(\0132$.im.turms.proto"
                + ".DeleteSessionRequestH\000\022P\n\033query_convers"
                + "ations_request\030\005 \001(\0132).im.turms.proto.Qu"
                + "eryConversationsRequestH\000\022P\n\033update_conv"
                + "ersation_request\030\006 \001(\0132).im.turms.proto."
                + "UpdateConversationRequestH\000\022Q\n\034update_ty"
                + "ping_status_request\030\007 \001(\0132).im.turms.pro"
                + "to.UpdateTypingStatusRequestH\000\022F\n\026create"
                + "_message_request\030\010 \001(\0132$.im.turms.proto."
                + "CreateMessageRequestH\000\022F\n\026query_messages"
                + "_request\030\t \001(\0132$.im.turms.proto.QueryMes"
                + "sagesRequestH\000\022F\n\026update_message_request"
                + "\030\n \001(\0132$.im.turms.proto.UpdateMessageReq"
                + "uestH\000\022Q\n\034create_group_members_request\030\013"
                + " \001(\0132).im.turms.proto.CreateGroupMembers"
                + "RequestH\000\022Q\n\034delete_group_members_reques"
                + "t\030\014 \001(\0132).im.turms.proto.DeleteGroupMemb"
                + "ersRequestH\000\022O\n\033query_group_members_requ"
                + "est\030\r \001(\0132(.im.turms.proto.QueryGroupMem"
                + "bersRequestH\000\022O\n\033update_group_member_req"
                + "uest\030\016 \001(\0132(.im.turms.proto.UpdateGroupM"
                + "emberRequestH\000\022O\n\033query_user_profiles_re"
                + "quest\030d \001(\0132(.im.turms.proto.QueryUserPr"
                + "ofilesRequestH\000\022M\n\032query_nearby_users_re"
                + "quest\030e \001(\0132\'.im.turms.proto.QueryNearby"
                + "UsersRequestH\000\022\\\n\"query_user_online_stat"
                + "uses_request\030f \001(\0132..im.turms.proto.Quer"
                + "yUserOnlineStatusesRequestH\000\022Q\n\034update_u"
                + "ser_location_request\030g \001(\0132).im.turms.pr"
                + "oto.UpdateUserLocationRequestH\000\022Z\n!updat"
                + "e_user_online_status_request\030h \001(\0132-.im."
                + "turms.proto.UpdateUserOnlineStatusReques"
                + "tH\000\022@\n\023update_user_request\030i \001(\0132!.im.tu"
                + "rms.proto.UpdateUserRequestH\000\022Q\n\034update_"
                + "user_settings_request\030j \001(\0132).im.turms.p"
                + "roto.UpdateUserSettingsRequestH\000\022Q\n\034dele"
                + "te_user_settings_request\030k \001(\0132).im.turm"
                + "s.proto.DeleteUserSettingsRequestH\000\022O\n\033q"
                + "uery_user_settings_request\030l \001(\0132(.im.tu"
                + "rms.proto.QueryUserSettingsRequestH\000\022T\n\035"
                + "create_friend_request_request\030\310\001 \001(\0132*.i"
                + "m.turms.proto.CreateFriendRequestRequest"
                + "H\000\022\\\n!create_relationship_group_request\030"
                + "\311\001 \001(\0132..im.turms.proto.CreateRelationsh"
                + "ipGroupRequestH\000\022Q\n\033create_relationship_"
                + "request\030\312\001 \001(\0132).im.turms.proto.CreateRe"
                + "lationshipRequestH\000\022T\n\035delete_friend_req"
                + "uest_request\030\313\001 \001(\0132*.im.turms.proto.Del"
                + "eteFriendRequestRequestH\000\022\\\n!delete_rela"
                + "tionship_group_request\030\314\001 \001(\0132..im.turms"
                + ".proto.DeleteRelationshipGroupRequestH\000\022"
                + "Q\n\033delete_relationship_request\030\315\001 \001(\0132)."
                + "im.turms.proto.DeleteRelationshipRequest"
                + "H\000\022T\n\035query_friend_requests_request\030\316\001 \001"
                + "(\0132*.im.turms.proto.QueryFriendRequestsR"
                + "equestH\000\022U\n\036query_related_user_ids_reque"
                + "st\030\317\001 \001(\0132*.im.turms.proto.QueryRelatedU"
                + "serIdsRequestH\000\022\\\n!query_relationship_gr"
                + "oups_request\030\320\001 \001(\0132..im.turms.proto.Que"
                + "ryRelationshipGroupsRequestH\000\022Q\n\033query_r"
                + "elationships_request\030\321\001 \001(\0132).im.turms.p"
                + "roto.QueryRelationshipsRequestH\000\022T\n\035upda"
                + "te_friend_request_request\030\322\001 \001(\0132*.im.tu"
                + "rms.proto.UpdateFriendRequestRequestH\000\022\\"
                + "\n!update_relationship_group_request\030\323\001 \001"
                + "(\0132..im.turms.proto.UpdateRelationshipGr"
                + "oupRequestH\000\022Q\n\033update_relationship_requ"
                + "est\030\324\001 \001(\0132).im.turms.proto.UpdateRelati"
                + "onshipRequestH\000\022C\n\024create_group_request\030"
                + "\254\002 \001(\0132\".im.turms.proto.CreateGroupReque"
                + "stH\000\022C\n\024delete_group_request\030\255\002 \001(\0132\".im"
                + ".turms.proto.DeleteGroupRequestH\000\022C\n\024que"
                + "ry_groups_request\030\256\002 \001(\0132\".im.turms.prot"
                + "o.QueryGroupsRequestH\000\022U\n\036query_joined_g"
                + "roup_ids_request\030\257\002 \001(\0132*.im.turms.proto"
                + ".QueryJoinedGroupIdsRequestH\000\022Y\n query_j"
                + "oined_group_infos_request\030\260\002 \001(\0132,.im.tu"
                + "rms.proto.QueryJoinedGroupInfosRequestH\000"
                + "\022C\n\024update_group_request\030\261\002 \001(\0132\".im.tur"
                + "ms.proto.UpdateGroupRequestH\000\022[\n!create_"
                + "group_blocked_user_request\030\220\003 \001(\0132-.im.t"
                + "urms.proto.CreateGroupBlockedUserRequest"
                + "H\000\022[\n!delete_group_blocked_user_request\030"
                + "\221\003 \001(\0132-.im.turms.proto.DeleteGroupBlock"
                + "edUserRequestH\000\022`\n$query_group_blocked_u"
                + "ser_ids_request\030\222\003 \001(\0132/.im.turms.proto."
                + "QueryGroupBlockedUserIdsRequestH\000\022d\n&que"
                + "ry_group_blocked_user_infos_request\030\223\003 \001"
                + "(\01321.im.turms.proto.QueryGroupBlockedUse"
                + "rInfosRequestH\000\022l\n*check_group_join_ques"
                + "tions_answers_request\030\364\003 \001(\01325.im.turms."
                + "proto.CheckGroupJoinQuestionsAnswersRequ"
                + "estH\000\022X\n\037create_group_invitation_request"
                + "\030\365\003 \001(\0132,.im.turms.proto.CreateGroupInvi"
                + "tationRequestH\000\022[\n!create_group_join_req"
                + "uest_request\030\366\003 \001(\0132-.im.turms.proto.Cre"
                + "ateGroupJoinRequestRequestH\000\022_\n#create_g"
                + "roup_join_questions_request\030\367\003 \001(\0132/.im."
                + "turms.proto.CreateGroupJoinQuestionsRequ"
                + "estH\000\022X\n\037delete_group_invitation_request"
                + "\030\370\003 \001(\0132,.im.turms.proto.DeleteGroupInvi"
                + "tationRequestH\000\022[\n!delete_group_join_req"
                + "uest_request\030\371\003 \001(\0132-.im.turms.proto.Del"
                + "eteGroupJoinRequestRequestH\000\022_\n#delete_g"
                + "roup_join_questions_request\030\372\003 \001(\0132/.im."
                + "turms.proto.DeleteGroupJoinQuestionsRequ"
                + "estH\000\022X\n\037query_group_invitations_request"
                + "\030\373\003 \001(\0132,.im.turms.proto.QueryGroupInvit"
                + "ationsRequestH\000\022[\n!query_group_join_requ"
                + "ests_request\030\374\003 \001(\0132-.im.turms.proto.Que"
                + "ryGroupJoinRequestsRequestH\000\022]\n\"query_gr"
                + "oup_join_questions_request\030\375\003 \001(\0132..im.t"
                + "urms.proto.QueryGroupJoinQuestionsReques"
                + "tH\000\022X\n\037update_group_invitation_request\030\376"
                + "\003 \001(\0132,.im.turms.proto.UpdateGroupInvita"
                + "tionRequestH\000\022]\n\"update_group_join_quest"
                + "ion_request\030\377\003 \001(\0132..im.turms.proto.Upda"
                + "teGroupJoinQuestionRequestH\000\022[\n!update_g"
                + "roup_join_request_request\030\200\004 \001(\0132-.im.tu"
                + "rms.proto.UpdateGroupJoinRequestRequestH"
                + "\000\022G\n\026create_meeting_request\030\204\007 \001(\0132$.im."
                + "turms.proto.CreateMeetingRequestH\000\022G\n\026de"
                + "lete_meeting_request\030\205\007 \001(\0132$.im.turms.p"
                + "roto.DeleteMeetingRequestH\000\022G\n\026query_mee"
                + "tings_request\030\206\007 \001(\0132$.im.turms.proto.Qu"
                + "eryMeetingsRequestH\000\022G\n\026update_meeting_r"
                + "equest\030\207\007 \001(\0132$.im.turms.proto.UpdateMee"
                + "tingRequestH\000\022\\\n!update_meeting_invitati"
                + "on_request\030\210\007 \001(\0132..im.turms.proto.Updat"
                + "eMeetingInvitationRequestH\000\022I\n\027delete_re"
                + "source_request\030\350\007 \001(\0132%.im.turms.proto.D"
                + "eleteResourceRequestH\000\022a\n$query_resource"
                + "_download_info_request\030\351\007 \001(\01320.im.turms"
                + ".proto.QueryResourceDownloadInfoRequestH"
                + "\000\022]\n\"query_resource_upload_info_request\030"
                + "\352\007 \001(\0132..im.turms.proto.QueryResourceUpl"
                + "oadInfoRequestH\000\022e\n&query_message_attach"
                + "ment_infos_request\030\353\007 \001(\01322.im.turms.pro"
                + "to.QueryMessageAttachmentInfosRequestH\000\022"
                + "e\n&update_message_attachment_info_reques"
                + "t\030\354\007 \001(\01322.im.turms.proto.UpdateMessageA"
                + "ttachmentInfoRequestH\000\022b\n$delete_convers"
                + "ation_settings_request\030\314\010 \001(\01321.im.turms"
                + ".proto.DeleteConversationSettingsRequest"
                + "H\000\022`\n#query_conversation_settings_reques"
                + "t\030\315\010 \001(\01320.im.turms.proto.QueryConversat"
                + "ionSettingsRequestH\000\022b\n$update_conversat"
                + "ion_settings_request\030\316\010 \001(\01321.im.turms.p"
                + "roto.UpdateConversationSettingsRequestH\000"
                + "B\006\n\004kindB\r\n\013_request_idB7\n0im.turms.serv"
                + "er.common.access.client.dto.requestP\001\272\002\000"
                + "b\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.DeleteUserSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.QueryUserSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.user.UpdateUserSettingsRequestOuterClass
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
                        im.turms.server.common.access.client.dto.request.conversation.DeleteConversationSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.QueryConversationSettingsRequestOuterClass
                                .getDescriptor(),
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass
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
                                "Kind",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.server.common.access.client.dto.request.user.CreateSessionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.DeleteSessionRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.DeleteUserSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.QueryUserSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.user.UpdateUserSettingsRequestOuterClass
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
        im.turms.server.common.access.client.dto.request.conversation.DeleteConversationSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.QueryConversationSettingsRequestOuterClass
                .getDescriptor();
        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass
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