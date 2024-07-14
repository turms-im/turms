//
//  Generated code. Do not modify.
//  source: request/turms_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../model/common/value.pb.dart' as $0;
import 'conference/create_meeting_request.pb.dart' as $58;
import 'conference/delete_meeting_request.pb.dart' as $59;
import 'conference/query_meetings_request.pb.dart' as $60;
import 'conference/update_meeting_invitation_request.pb.dart' as $62;
import 'conference/update_meeting_request.pb.dart' as $61;
import 'conversation/delete_conversation_settings_request.pb.dart' as $68;
import 'conversation/query_conversation_settings_request.pb.dart' as $69;
import 'conversation/query_conversations_request.pb.dart' as $3;
import 'conversation/update_conversation_request.pb.dart' as $4;
import 'conversation/update_conversation_settings_request.pb.dart' as $70;
import 'conversation/update_typing_status_request.pb.dart' as $5;
import 'group/blocklist/create_group_blocked_user_request.pb.dart' as $41;
import 'group/blocklist/delete_group_blocked_user_request.pb.dart' as $42;
import 'group/blocklist/query_group_blocked_user_ids_request.pb.dart' as $43;
import 'group/blocklist/query_group_blocked_user_infos_request.pb.dart' as $44;
import 'group/create_group_request.pb.dart' as $35;
import 'group/delete_group_request.pb.dart' as $36;
import 'group/enrollment/check_group_join_questions_answers_request.pb.dart'
    as $45;
import 'group/enrollment/create_group_invitation_request.pb.dart' as $46;
import 'group/enrollment/create_group_join_questions_request.pb.dart' as $48;
import 'group/enrollment/create_group_join_request_request.pb.dart' as $47;
import 'group/enrollment/delete_group_invitation_request.pb.dart' as $49;
import 'group/enrollment/delete_group_join_questions_request.pb.dart' as $51;
import 'group/enrollment/delete_group_join_request_request.pb.dart' as $50;
import 'group/enrollment/query_group_invitations_request.pb.dart' as $52;
import 'group/enrollment/query_group_join_questions_request.pb.dart' as $54;
import 'group/enrollment/query_group_join_requests_request.pb.dart' as $53;
import 'group/enrollment/update_group_invitation_request.pb.dart' as $55;
import 'group/enrollment/update_group_join_question_request.pb.dart' as $56;
import 'group/enrollment/update_group_join_request_request.pb.dart' as $57;
import 'group/member/create_group_members_request.pb.dart' as $9;
import 'group/member/delete_group_members_request.pb.dart' as $10;
import 'group/member/query_group_members_request.pb.dart' as $11;
import 'group/member/update_group_member_request.pb.dart' as $12;
import 'group/query_groups_request.pb.dart' as $37;
import 'group/query_joined_group_ids_request.pb.dart' as $38;
import 'group/query_joined_group_infos_request.pb.dart' as $39;
import 'group/update_group_request.pb.dart' as $40;
import 'message/create_message_reactions_request.pb.dart' as $71;
import 'message/create_message_request.pb.dart' as $6;
import 'message/delete_message_reactions_request.pb.dart' as $72;
import 'message/query_messages_request.pb.dart' as $7;
import 'message/update_message_request.pb.dart' as $8;
import 'storage/delete_resource_request.pb.dart' as $63;
import 'storage/query_message_attachment_infos_request.pb.dart' as $66;
import 'storage/query_resource_download_info_request.pb.dart' as $64;
import 'storage/query_resource_upload_info_request.pb.dart' as $65;
import 'storage/update_message_attachment_info_request.pb.dart' as $67;
import 'user/create_session_request.pb.dart' as $1;
import 'user/delete_session_request.pb.dart' as $2;
import 'user/delete_user_settings_request.pb.dart' as $20;
import 'user/query_nearby_users_request.pb.dart' as $14;
import 'user/query_user_online_statuses_request.pb.dart' as $15;
import 'user/query_user_profiles_request.pb.dart' as $13;
import 'user/query_user_settings_request.pb.dart' as $21;
import 'user/relationship/create_friend_request_request.pb.dart' as $22;
import 'user/relationship/create_relationship_group_request.pb.dart' as $23;
import 'user/relationship/create_relationship_request.pb.dart' as $24;
import 'user/relationship/delete_friend_request_request.pb.dart' as $25;
import 'user/relationship/delete_relationship_group_request.pb.dart' as $26;
import 'user/relationship/delete_relationship_request.pb.dart' as $27;
import 'user/relationship/query_friend_requests_request.pb.dart' as $28;
import 'user/relationship/query_related_user_ids_request.pb.dart' as $29;
import 'user/relationship/query_relationship_groups_request.pb.dart' as $30;
import 'user/relationship/query_relationships_request.pb.dart' as $31;
import 'user/relationship/update_friend_request_request.pb.dart' as $32;
import 'user/relationship/update_relationship_group_request.pb.dart' as $33;
import 'user/relationship/update_relationship_request.pb.dart' as $34;
import 'user/update_user_location_request.pb.dart' as $16;
import 'user/update_user_online_status_request.pb.dart' as $17;
import 'user/update_user_request.pb.dart' as $18;
import 'user/update_user_settings_request.pb.dart' as $19;

enum TurmsRequest_Kind {
  createSessionRequest,
  deleteSessionRequest,
  queryConversationsRequest,
  updateConversationRequest,
  updateTypingStatusRequest,
  createMessageRequest,
  queryMessagesRequest,
  updateMessageRequest,
  createGroupMembersRequest,
  deleteGroupMembersRequest,
  queryGroupMembersRequest,
  updateGroupMemberRequest,
  queryUserProfilesRequest,
  queryNearbyUsersRequest,
  queryUserOnlineStatusesRequest,
  updateUserLocationRequest,
  updateUserOnlineStatusRequest,
  updateUserRequest,
  updateUserSettingsRequest,
  deleteUserSettingsRequest,
  queryUserSettingsRequest,
  createFriendRequestRequest,
  createRelationshipGroupRequest,
  createRelationshipRequest,
  deleteFriendRequestRequest,
  deleteRelationshipGroupRequest,
  deleteRelationshipRequest,
  queryFriendRequestsRequest,
  queryRelatedUserIdsRequest,
  queryRelationshipGroupsRequest,
  queryRelationshipsRequest,
  updateFriendRequestRequest,
  updateRelationshipGroupRequest,
  updateRelationshipRequest,
  createGroupRequest,
  deleteGroupRequest,
  queryGroupsRequest,
  queryJoinedGroupIdsRequest,
  queryJoinedGroupInfosRequest,
  updateGroupRequest,
  createGroupBlockedUserRequest,
  deleteGroupBlockedUserRequest,
  queryGroupBlockedUserIdsRequest,
  queryGroupBlockedUserInfosRequest,
  checkGroupJoinQuestionsAnswersRequest,
  createGroupInvitationRequest,
  createGroupJoinRequestRequest,
  createGroupJoinQuestionsRequest,
  deleteGroupInvitationRequest,
  deleteGroupJoinRequestRequest,
  deleteGroupJoinQuestionsRequest,
  queryGroupInvitationsRequest,
  queryGroupJoinRequestsRequest,
  queryGroupJoinQuestionsRequest,
  updateGroupInvitationRequest,
  updateGroupJoinQuestionRequest,
  updateGroupJoinRequestRequest,
  createMeetingRequest,
  deleteMeetingRequest,
  queryMeetingsRequest,
  updateMeetingRequest,
  updateMeetingInvitationRequest,
  deleteResourceRequest,
  queryResourceDownloadInfoRequest,
  queryResourceUploadInfoRequest,
  queryMessageAttachmentInfosRequest,
  updateMessageAttachmentInfoRequest,
  deleteConversationSettingsRequest,
  queryConversationSettingsRequest,
  updateConversationSettingsRequest,
  createMessageReactionsRequest,
  deleteMessageReactionsRequest,
  notSet
}

/// Client -> Server -> Client
class TurmsRequest extends $pb.GeneratedMessage {
  factory TurmsRequest({
    $fixnum.Int64? requestId,
    $core.Iterable<$0.Value>? customAttributes,
    $1.CreateSessionRequest? createSessionRequest,
    $2.DeleteSessionRequest? deleteSessionRequest,
    $3.QueryConversationsRequest? queryConversationsRequest,
    $4.UpdateConversationRequest? updateConversationRequest,
    $5.UpdateTypingStatusRequest? updateTypingStatusRequest,
    $6.CreateMessageRequest? createMessageRequest,
    $7.QueryMessagesRequest? queryMessagesRequest,
    $8.UpdateMessageRequest? updateMessageRequest,
    $9.CreateGroupMembersRequest? createGroupMembersRequest,
    $10.DeleteGroupMembersRequest? deleteGroupMembersRequest,
    $11.QueryGroupMembersRequest? queryGroupMembersRequest,
    $12.UpdateGroupMemberRequest? updateGroupMemberRequest,
    $13.QueryUserProfilesRequest? queryUserProfilesRequest,
    $14.QueryNearbyUsersRequest? queryNearbyUsersRequest,
    $15.QueryUserOnlineStatusesRequest? queryUserOnlineStatusesRequest,
    $16.UpdateUserLocationRequest? updateUserLocationRequest,
    $17.UpdateUserOnlineStatusRequest? updateUserOnlineStatusRequest,
    $18.UpdateUserRequest? updateUserRequest,
    $19.UpdateUserSettingsRequest? updateUserSettingsRequest,
    $20.DeleteUserSettingsRequest? deleteUserSettingsRequest,
    $21.QueryUserSettingsRequest? queryUserSettingsRequest,
    $22.CreateFriendRequestRequest? createFriendRequestRequest,
    $23.CreateRelationshipGroupRequest? createRelationshipGroupRequest,
    $24.CreateRelationshipRequest? createRelationshipRequest,
    $25.DeleteFriendRequestRequest? deleteFriendRequestRequest,
    $26.DeleteRelationshipGroupRequest? deleteRelationshipGroupRequest,
    $27.DeleteRelationshipRequest? deleteRelationshipRequest,
    $28.QueryFriendRequestsRequest? queryFriendRequestsRequest,
    $29.QueryRelatedUserIdsRequest? queryRelatedUserIdsRequest,
    $30.QueryRelationshipGroupsRequest? queryRelationshipGroupsRequest,
    $31.QueryRelationshipsRequest? queryRelationshipsRequest,
    $32.UpdateFriendRequestRequest? updateFriendRequestRequest,
    $33.UpdateRelationshipGroupRequest? updateRelationshipGroupRequest,
    $34.UpdateRelationshipRequest? updateRelationshipRequest,
    $35.CreateGroupRequest? createGroupRequest,
    $36.DeleteGroupRequest? deleteGroupRequest,
    $37.QueryGroupsRequest? queryGroupsRequest,
    $38.QueryJoinedGroupIdsRequest? queryJoinedGroupIdsRequest,
    $39.QueryJoinedGroupInfosRequest? queryJoinedGroupInfosRequest,
    $40.UpdateGroupRequest? updateGroupRequest,
    $41.CreateGroupBlockedUserRequest? createGroupBlockedUserRequest,
    $42.DeleteGroupBlockedUserRequest? deleteGroupBlockedUserRequest,
    $43.QueryGroupBlockedUserIdsRequest? queryGroupBlockedUserIdsRequest,
    $44.QueryGroupBlockedUserInfosRequest? queryGroupBlockedUserInfosRequest,
    $45.CheckGroupJoinQuestionsAnswersRequest?
        checkGroupJoinQuestionsAnswersRequest,
    $46.CreateGroupInvitationRequest? createGroupInvitationRequest,
    $47.CreateGroupJoinRequestRequest? createGroupJoinRequestRequest,
    $48.CreateGroupJoinQuestionsRequest? createGroupJoinQuestionsRequest,
    $49.DeleteGroupInvitationRequest? deleteGroupInvitationRequest,
    $50.DeleteGroupJoinRequestRequest? deleteGroupJoinRequestRequest,
    $51.DeleteGroupJoinQuestionsRequest? deleteGroupJoinQuestionsRequest,
    $52.QueryGroupInvitationsRequest? queryGroupInvitationsRequest,
    $53.QueryGroupJoinRequestsRequest? queryGroupJoinRequestsRequest,
    $54.QueryGroupJoinQuestionsRequest? queryGroupJoinQuestionsRequest,
    $55.UpdateGroupInvitationRequest? updateGroupInvitationRequest,
    $56.UpdateGroupJoinQuestionRequest? updateGroupJoinQuestionRequest,
    $57.UpdateGroupJoinRequestRequest? updateGroupJoinRequestRequest,
    $58.CreateMeetingRequest? createMeetingRequest,
    $59.DeleteMeetingRequest? deleteMeetingRequest,
    $60.QueryMeetingsRequest? queryMeetingsRequest,
    $61.UpdateMeetingRequest? updateMeetingRequest,
    $62.UpdateMeetingInvitationRequest? updateMeetingInvitationRequest,
    $63.DeleteResourceRequest? deleteResourceRequest,
    $64.QueryResourceDownloadInfoRequest? queryResourceDownloadInfoRequest,
    $65.QueryResourceUploadInfoRequest? queryResourceUploadInfoRequest,
    $66.QueryMessageAttachmentInfosRequest? queryMessageAttachmentInfosRequest,
    $67.UpdateMessageAttachmentInfoRequest? updateMessageAttachmentInfoRequest,
    $68.DeleteConversationSettingsRequest? deleteConversationSettingsRequest,
    $69.QueryConversationSettingsRequest? queryConversationSettingsRequest,
    $70.UpdateConversationSettingsRequest? updateConversationSettingsRequest,
    $71.CreateMessageReactionsRequest? createMessageReactionsRequest,
    $72.DeleteMessageReactionsRequest? deleteMessageReactionsRequest,
  }) {
    final $result = create();
    if (requestId != null) {
      $result.requestId = requestId;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    if (createSessionRequest != null) {
      $result.createSessionRequest = createSessionRequest;
    }
    if (deleteSessionRequest != null) {
      $result.deleteSessionRequest = deleteSessionRequest;
    }
    if (queryConversationsRequest != null) {
      $result.queryConversationsRequest = queryConversationsRequest;
    }
    if (updateConversationRequest != null) {
      $result.updateConversationRequest = updateConversationRequest;
    }
    if (updateTypingStatusRequest != null) {
      $result.updateTypingStatusRequest = updateTypingStatusRequest;
    }
    if (createMessageRequest != null) {
      $result.createMessageRequest = createMessageRequest;
    }
    if (queryMessagesRequest != null) {
      $result.queryMessagesRequest = queryMessagesRequest;
    }
    if (updateMessageRequest != null) {
      $result.updateMessageRequest = updateMessageRequest;
    }
    if (createGroupMembersRequest != null) {
      $result.createGroupMembersRequest = createGroupMembersRequest;
    }
    if (deleteGroupMembersRequest != null) {
      $result.deleteGroupMembersRequest = deleteGroupMembersRequest;
    }
    if (queryGroupMembersRequest != null) {
      $result.queryGroupMembersRequest = queryGroupMembersRequest;
    }
    if (updateGroupMemberRequest != null) {
      $result.updateGroupMemberRequest = updateGroupMemberRequest;
    }
    if (queryUserProfilesRequest != null) {
      $result.queryUserProfilesRequest = queryUserProfilesRequest;
    }
    if (queryNearbyUsersRequest != null) {
      $result.queryNearbyUsersRequest = queryNearbyUsersRequest;
    }
    if (queryUserOnlineStatusesRequest != null) {
      $result.queryUserOnlineStatusesRequest = queryUserOnlineStatusesRequest;
    }
    if (updateUserLocationRequest != null) {
      $result.updateUserLocationRequest = updateUserLocationRequest;
    }
    if (updateUserOnlineStatusRequest != null) {
      $result.updateUserOnlineStatusRequest = updateUserOnlineStatusRequest;
    }
    if (updateUserRequest != null) {
      $result.updateUserRequest = updateUserRequest;
    }
    if (updateUserSettingsRequest != null) {
      $result.updateUserSettingsRequest = updateUserSettingsRequest;
    }
    if (deleteUserSettingsRequest != null) {
      $result.deleteUserSettingsRequest = deleteUserSettingsRequest;
    }
    if (queryUserSettingsRequest != null) {
      $result.queryUserSettingsRequest = queryUserSettingsRequest;
    }
    if (createFriendRequestRequest != null) {
      $result.createFriendRequestRequest = createFriendRequestRequest;
    }
    if (createRelationshipGroupRequest != null) {
      $result.createRelationshipGroupRequest = createRelationshipGroupRequest;
    }
    if (createRelationshipRequest != null) {
      $result.createRelationshipRequest = createRelationshipRequest;
    }
    if (deleteFriendRequestRequest != null) {
      $result.deleteFriendRequestRequest = deleteFriendRequestRequest;
    }
    if (deleteRelationshipGroupRequest != null) {
      $result.deleteRelationshipGroupRequest = deleteRelationshipGroupRequest;
    }
    if (deleteRelationshipRequest != null) {
      $result.deleteRelationshipRequest = deleteRelationshipRequest;
    }
    if (queryFriendRequestsRequest != null) {
      $result.queryFriendRequestsRequest = queryFriendRequestsRequest;
    }
    if (queryRelatedUserIdsRequest != null) {
      $result.queryRelatedUserIdsRequest = queryRelatedUserIdsRequest;
    }
    if (queryRelationshipGroupsRequest != null) {
      $result.queryRelationshipGroupsRequest = queryRelationshipGroupsRequest;
    }
    if (queryRelationshipsRequest != null) {
      $result.queryRelationshipsRequest = queryRelationshipsRequest;
    }
    if (updateFriendRequestRequest != null) {
      $result.updateFriendRequestRequest = updateFriendRequestRequest;
    }
    if (updateRelationshipGroupRequest != null) {
      $result.updateRelationshipGroupRequest = updateRelationshipGroupRequest;
    }
    if (updateRelationshipRequest != null) {
      $result.updateRelationshipRequest = updateRelationshipRequest;
    }
    if (createGroupRequest != null) {
      $result.createGroupRequest = createGroupRequest;
    }
    if (deleteGroupRequest != null) {
      $result.deleteGroupRequest = deleteGroupRequest;
    }
    if (queryGroupsRequest != null) {
      $result.queryGroupsRequest = queryGroupsRequest;
    }
    if (queryJoinedGroupIdsRequest != null) {
      $result.queryJoinedGroupIdsRequest = queryJoinedGroupIdsRequest;
    }
    if (queryJoinedGroupInfosRequest != null) {
      $result.queryJoinedGroupInfosRequest = queryJoinedGroupInfosRequest;
    }
    if (updateGroupRequest != null) {
      $result.updateGroupRequest = updateGroupRequest;
    }
    if (createGroupBlockedUserRequest != null) {
      $result.createGroupBlockedUserRequest = createGroupBlockedUserRequest;
    }
    if (deleteGroupBlockedUserRequest != null) {
      $result.deleteGroupBlockedUserRequest = deleteGroupBlockedUserRequest;
    }
    if (queryGroupBlockedUserIdsRequest != null) {
      $result.queryGroupBlockedUserIdsRequest = queryGroupBlockedUserIdsRequest;
    }
    if (queryGroupBlockedUserInfosRequest != null) {
      $result.queryGroupBlockedUserInfosRequest =
          queryGroupBlockedUserInfosRequest;
    }
    if (checkGroupJoinQuestionsAnswersRequest != null) {
      $result.checkGroupJoinQuestionsAnswersRequest =
          checkGroupJoinQuestionsAnswersRequest;
    }
    if (createGroupInvitationRequest != null) {
      $result.createGroupInvitationRequest = createGroupInvitationRequest;
    }
    if (createGroupJoinRequestRequest != null) {
      $result.createGroupJoinRequestRequest = createGroupJoinRequestRequest;
    }
    if (createGroupJoinQuestionsRequest != null) {
      $result.createGroupJoinQuestionsRequest = createGroupJoinQuestionsRequest;
    }
    if (deleteGroupInvitationRequest != null) {
      $result.deleteGroupInvitationRequest = deleteGroupInvitationRequest;
    }
    if (deleteGroupJoinRequestRequest != null) {
      $result.deleteGroupJoinRequestRequest = deleteGroupJoinRequestRequest;
    }
    if (deleteGroupJoinQuestionsRequest != null) {
      $result.deleteGroupJoinQuestionsRequest = deleteGroupJoinQuestionsRequest;
    }
    if (queryGroupInvitationsRequest != null) {
      $result.queryGroupInvitationsRequest = queryGroupInvitationsRequest;
    }
    if (queryGroupJoinRequestsRequest != null) {
      $result.queryGroupJoinRequestsRequest = queryGroupJoinRequestsRequest;
    }
    if (queryGroupJoinQuestionsRequest != null) {
      $result.queryGroupJoinQuestionsRequest = queryGroupJoinQuestionsRequest;
    }
    if (updateGroupInvitationRequest != null) {
      $result.updateGroupInvitationRequest = updateGroupInvitationRequest;
    }
    if (updateGroupJoinQuestionRequest != null) {
      $result.updateGroupJoinQuestionRequest = updateGroupJoinQuestionRequest;
    }
    if (updateGroupJoinRequestRequest != null) {
      $result.updateGroupJoinRequestRequest = updateGroupJoinRequestRequest;
    }
    if (createMeetingRequest != null) {
      $result.createMeetingRequest = createMeetingRequest;
    }
    if (deleteMeetingRequest != null) {
      $result.deleteMeetingRequest = deleteMeetingRequest;
    }
    if (queryMeetingsRequest != null) {
      $result.queryMeetingsRequest = queryMeetingsRequest;
    }
    if (updateMeetingRequest != null) {
      $result.updateMeetingRequest = updateMeetingRequest;
    }
    if (updateMeetingInvitationRequest != null) {
      $result.updateMeetingInvitationRequest = updateMeetingInvitationRequest;
    }
    if (deleteResourceRequest != null) {
      $result.deleteResourceRequest = deleteResourceRequest;
    }
    if (queryResourceDownloadInfoRequest != null) {
      $result.queryResourceDownloadInfoRequest =
          queryResourceDownloadInfoRequest;
    }
    if (queryResourceUploadInfoRequest != null) {
      $result.queryResourceUploadInfoRequest = queryResourceUploadInfoRequest;
    }
    if (queryMessageAttachmentInfosRequest != null) {
      $result.queryMessageAttachmentInfosRequest =
          queryMessageAttachmentInfosRequest;
    }
    if (updateMessageAttachmentInfoRequest != null) {
      $result.updateMessageAttachmentInfoRequest =
          updateMessageAttachmentInfoRequest;
    }
    if (deleteConversationSettingsRequest != null) {
      $result.deleteConversationSettingsRequest =
          deleteConversationSettingsRequest;
    }
    if (queryConversationSettingsRequest != null) {
      $result.queryConversationSettingsRequest =
          queryConversationSettingsRequest;
    }
    if (updateConversationSettingsRequest != null) {
      $result.updateConversationSettingsRequest =
          updateConversationSettingsRequest;
    }
    if (createMessageReactionsRequest != null) {
      $result.createMessageReactionsRequest = createMessageReactionsRequest;
    }
    if (deleteMessageReactionsRequest != null) {
      $result.deleteMessageReactionsRequest = deleteMessageReactionsRequest;
    }
    return $result;
  }
  TurmsRequest._() : super();
  factory TurmsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory TurmsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static const $core.Map<$core.int, TurmsRequest_Kind> _TurmsRequest_KindByTag =
      {
    3: TurmsRequest_Kind.createSessionRequest,
    4: TurmsRequest_Kind.deleteSessionRequest,
    5: TurmsRequest_Kind.queryConversationsRequest,
    6: TurmsRequest_Kind.updateConversationRequest,
    7: TurmsRequest_Kind.updateTypingStatusRequest,
    8: TurmsRequest_Kind.createMessageRequest,
    9: TurmsRequest_Kind.queryMessagesRequest,
    10: TurmsRequest_Kind.updateMessageRequest,
    11: TurmsRequest_Kind.createGroupMembersRequest,
    12: TurmsRequest_Kind.deleteGroupMembersRequest,
    13: TurmsRequest_Kind.queryGroupMembersRequest,
    14: TurmsRequest_Kind.updateGroupMemberRequest,
    100: TurmsRequest_Kind.queryUserProfilesRequest,
    101: TurmsRequest_Kind.queryNearbyUsersRequest,
    102: TurmsRequest_Kind.queryUserOnlineStatusesRequest,
    103: TurmsRequest_Kind.updateUserLocationRequest,
    104: TurmsRequest_Kind.updateUserOnlineStatusRequest,
    105: TurmsRequest_Kind.updateUserRequest,
    106: TurmsRequest_Kind.updateUserSettingsRequest,
    107: TurmsRequest_Kind.deleteUserSettingsRequest,
    108: TurmsRequest_Kind.queryUserSettingsRequest,
    200: TurmsRequest_Kind.createFriendRequestRequest,
    201: TurmsRequest_Kind.createRelationshipGroupRequest,
    202: TurmsRequest_Kind.createRelationshipRequest,
    203: TurmsRequest_Kind.deleteFriendRequestRequest,
    204: TurmsRequest_Kind.deleteRelationshipGroupRequest,
    205: TurmsRequest_Kind.deleteRelationshipRequest,
    206: TurmsRequest_Kind.queryFriendRequestsRequest,
    207: TurmsRequest_Kind.queryRelatedUserIdsRequest,
    208: TurmsRequest_Kind.queryRelationshipGroupsRequest,
    209: TurmsRequest_Kind.queryRelationshipsRequest,
    210: TurmsRequest_Kind.updateFriendRequestRequest,
    211: TurmsRequest_Kind.updateRelationshipGroupRequest,
    212: TurmsRequest_Kind.updateRelationshipRequest,
    300: TurmsRequest_Kind.createGroupRequest,
    301: TurmsRequest_Kind.deleteGroupRequest,
    302: TurmsRequest_Kind.queryGroupsRequest,
    303: TurmsRequest_Kind.queryJoinedGroupIdsRequest,
    304: TurmsRequest_Kind.queryJoinedGroupInfosRequest,
    305: TurmsRequest_Kind.updateGroupRequest,
    400: TurmsRequest_Kind.createGroupBlockedUserRequest,
    401: TurmsRequest_Kind.deleteGroupBlockedUserRequest,
    402: TurmsRequest_Kind.queryGroupBlockedUserIdsRequest,
    403: TurmsRequest_Kind.queryGroupBlockedUserInfosRequest,
    500: TurmsRequest_Kind.checkGroupJoinQuestionsAnswersRequest,
    501: TurmsRequest_Kind.createGroupInvitationRequest,
    502: TurmsRequest_Kind.createGroupJoinRequestRequest,
    503: TurmsRequest_Kind.createGroupJoinQuestionsRequest,
    504: TurmsRequest_Kind.deleteGroupInvitationRequest,
    505: TurmsRequest_Kind.deleteGroupJoinRequestRequest,
    506: TurmsRequest_Kind.deleteGroupJoinQuestionsRequest,
    507: TurmsRequest_Kind.queryGroupInvitationsRequest,
    508: TurmsRequest_Kind.queryGroupJoinRequestsRequest,
    509: TurmsRequest_Kind.queryGroupJoinQuestionsRequest,
    510: TurmsRequest_Kind.updateGroupInvitationRequest,
    511: TurmsRequest_Kind.updateGroupJoinQuestionRequest,
    512: TurmsRequest_Kind.updateGroupJoinRequestRequest,
    900: TurmsRequest_Kind.createMeetingRequest,
    901: TurmsRequest_Kind.deleteMeetingRequest,
    902: TurmsRequest_Kind.queryMeetingsRequest,
    903: TurmsRequest_Kind.updateMeetingRequest,
    904: TurmsRequest_Kind.updateMeetingInvitationRequest,
    1000: TurmsRequest_Kind.deleteResourceRequest,
    1001: TurmsRequest_Kind.queryResourceDownloadInfoRequest,
    1002: TurmsRequest_Kind.queryResourceUploadInfoRequest,
    1003: TurmsRequest_Kind.queryMessageAttachmentInfosRequest,
    1004: TurmsRequest_Kind.updateMessageAttachmentInfoRequest,
    1100: TurmsRequest_Kind.deleteConversationSettingsRequest,
    1101: TurmsRequest_Kind.queryConversationSettingsRequest,
    1102: TurmsRequest_Kind.updateConversationSettingsRequest,
    1200: TurmsRequest_Kind.createMessageReactionsRequest,
    1201: TurmsRequest_Kind.deleteMessageReactionsRequest,
    0: TurmsRequest_Kind.notSet
  };
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'TurmsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..oo(0, [
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      200,
      201,
      202,
      203,
      204,
      205,
      206,
      207,
      208,
      209,
      210,
      211,
      212,
      300,
      301,
      302,
      303,
      304,
      305,
      400,
      401,
      402,
      403,
      500,
      501,
      502,
      503,
      504,
      505,
      506,
      507,
      508,
      509,
      510,
      511,
      512,
      900,
      901,
      902,
      903,
      904,
      1000,
      1001,
      1002,
      1003,
      1004,
      1100,
      1101,
      1102,
      1200,
      1201
    ])
    ..aInt64(1, _omitFieldNames ? '' : 'requestId')
    ..pc<$0.Value>(
        2, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..aOM<$1.CreateSessionRequest>(
        3, _omitFieldNames ? '' : 'createSessionRequest',
        subBuilder: $1.CreateSessionRequest.create)
    ..aOM<$2.DeleteSessionRequest>(
        4, _omitFieldNames ? '' : 'deleteSessionRequest',
        subBuilder: $2.DeleteSessionRequest.create)
    ..aOM<$3.QueryConversationsRequest>(
        5, _omitFieldNames ? '' : 'queryConversationsRequest',
        subBuilder: $3.QueryConversationsRequest.create)
    ..aOM<$4.UpdateConversationRequest>(
        6, _omitFieldNames ? '' : 'updateConversationRequest',
        subBuilder: $4.UpdateConversationRequest.create)
    ..aOM<$5.UpdateTypingStatusRequest>(
        7, _omitFieldNames ? '' : 'updateTypingStatusRequest',
        subBuilder: $5.UpdateTypingStatusRequest.create)
    ..aOM<$6.CreateMessageRequest>(
        8, _omitFieldNames ? '' : 'createMessageRequest',
        subBuilder: $6.CreateMessageRequest.create)
    ..aOM<$7.QueryMessagesRequest>(
        9, _omitFieldNames ? '' : 'queryMessagesRequest',
        subBuilder: $7.QueryMessagesRequest.create)
    ..aOM<$8.UpdateMessageRequest>(
        10, _omitFieldNames ? '' : 'updateMessageRequest',
        subBuilder: $8.UpdateMessageRequest.create)
    ..aOM<$9.CreateGroupMembersRequest>(
        11, _omitFieldNames ? '' : 'createGroupMembersRequest',
        subBuilder: $9.CreateGroupMembersRequest.create)
    ..aOM<$10.DeleteGroupMembersRequest>(
        12, _omitFieldNames ? '' : 'deleteGroupMembersRequest',
        subBuilder: $10.DeleteGroupMembersRequest.create)
    ..aOM<$11.QueryGroupMembersRequest>(
        13, _omitFieldNames ? '' : 'queryGroupMembersRequest',
        subBuilder: $11.QueryGroupMembersRequest.create)
    ..aOM<$12.UpdateGroupMemberRequest>(
        14, _omitFieldNames ? '' : 'updateGroupMemberRequest',
        subBuilder: $12.UpdateGroupMemberRequest.create)
    ..aOM<$13.QueryUserProfilesRequest>(
        100, _omitFieldNames ? '' : 'queryUserProfilesRequest',
        subBuilder: $13.QueryUserProfilesRequest.create)
    ..aOM<$14.QueryNearbyUsersRequest>(
        101, _omitFieldNames ? '' : 'queryNearbyUsersRequest',
        subBuilder: $14.QueryNearbyUsersRequest.create)
    ..aOM<$15.QueryUserOnlineStatusesRequest>(
        102, _omitFieldNames ? '' : 'queryUserOnlineStatusesRequest',
        subBuilder: $15.QueryUserOnlineStatusesRequest.create)
    ..aOM<$16.UpdateUserLocationRequest>(
        103, _omitFieldNames ? '' : 'updateUserLocationRequest',
        subBuilder: $16.UpdateUserLocationRequest.create)
    ..aOM<$17.UpdateUserOnlineStatusRequest>(
        104, _omitFieldNames ? '' : 'updateUserOnlineStatusRequest',
        subBuilder: $17.UpdateUserOnlineStatusRequest.create)
    ..aOM<$18.UpdateUserRequest>(
        105, _omitFieldNames ? '' : 'updateUserRequest',
        subBuilder: $18.UpdateUserRequest.create)
    ..aOM<$19.UpdateUserSettingsRequest>(
        106, _omitFieldNames ? '' : 'updateUserSettingsRequest',
        subBuilder: $19.UpdateUserSettingsRequest.create)
    ..aOM<$20.DeleteUserSettingsRequest>(
        107, _omitFieldNames ? '' : 'deleteUserSettingsRequest',
        subBuilder: $20.DeleteUserSettingsRequest.create)
    ..aOM<$21.QueryUserSettingsRequest>(
        108, _omitFieldNames ? '' : 'queryUserSettingsRequest',
        subBuilder: $21.QueryUserSettingsRequest.create)
    ..aOM<$22.CreateFriendRequestRequest>(
        200, _omitFieldNames ? '' : 'createFriendRequestRequest',
        subBuilder: $22.CreateFriendRequestRequest.create)
    ..aOM<$23.CreateRelationshipGroupRequest>(
        201, _omitFieldNames ? '' : 'createRelationshipGroupRequest',
        subBuilder: $23.CreateRelationshipGroupRequest.create)
    ..aOM<$24.CreateRelationshipRequest>(
        202, _omitFieldNames ? '' : 'createRelationshipRequest',
        subBuilder: $24.CreateRelationshipRequest.create)
    ..aOM<$25.DeleteFriendRequestRequest>(
        203, _omitFieldNames ? '' : 'deleteFriendRequestRequest',
        subBuilder: $25.DeleteFriendRequestRequest.create)
    ..aOM<$26.DeleteRelationshipGroupRequest>(
        204, _omitFieldNames ? '' : 'deleteRelationshipGroupRequest',
        subBuilder: $26.DeleteRelationshipGroupRequest.create)
    ..aOM<$27.DeleteRelationshipRequest>(
        205, _omitFieldNames ? '' : 'deleteRelationshipRequest',
        subBuilder: $27.DeleteRelationshipRequest.create)
    ..aOM<$28.QueryFriendRequestsRequest>(
        206, _omitFieldNames ? '' : 'queryFriendRequestsRequest',
        subBuilder: $28.QueryFriendRequestsRequest.create)
    ..aOM<$29.QueryRelatedUserIdsRequest>(
        207, _omitFieldNames ? '' : 'queryRelatedUserIdsRequest',
        subBuilder: $29.QueryRelatedUserIdsRequest.create)
    ..aOM<$30.QueryRelationshipGroupsRequest>(
        208, _omitFieldNames ? '' : 'queryRelationshipGroupsRequest',
        subBuilder: $30.QueryRelationshipGroupsRequest.create)
    ..aOM<$31.QueryRelationshipsRequest>(
        209, _omitFieldNames ? '' : 'queryRelationshipsRequest',
        subBuilder: $31.QueryRelationshipsRequest.create)
    ..aOM<$32.UpdateFriendRequestRequest>(
        210, _omitFieldNames ? '' : 'updateFriendRequestRequest',
        subBuilder: $32.UpdateFriendRequestRequest.create)
    ..aOM<$33.UpdateRelationshipGroupRequest>(
        211, _omitFieldNames ? '' : 'updateRelationshipGroupRequest',
        subBuilder: $33.UpdateRelationshipGroupRequest.create)
    ..aOM<$34.UpdateRelationshipRequest>(
        212, _omitFieldNames ? '' : 'updateRelationshipRequest',
        subBuilder: $34.UpdateRelationshipRequest.create)
    ..aOM<$35.CreateGroupRequest>(
        300, _omitFieldNames ? '' : 'createGroupRequest',
        subBuilder: $35.CreateGroupRequest.create)
    ..aOM<$36.DeleteGroupRequest>(
        301, _omitFieldNames ? '' : 'deleteGroupRequest',
        subBuilder: $36.DeleteGroupRequest.create)
    ..aOM<$37.QueryGroupsRequest>(
        302, _omitFieldNames ? '' : 'queryGroupsRequest',
        subBuilder: $37.QueryGroupsRequest.create)
    ..aOM<$38.QueryJoinedGroupIdsRequest>(
        303, _omitFieldNames ? '' : 'queryJoinedGroupIdsRequest',
        subBuilder: $38.QueryJoinedGroupIdsRequest.create)
    ..aOM<$39.QueryJoinedGroupInfosRequest>(
        304, _omitFieldNames ? '' : 'queryJoinedGroupInfosRequest',
        subBuilder: $39.QueryJoinedGroupInfosRequest.create)
    ..aOM<$40.UpdateGroupRequest>(
        305, _omitFieldNames ? '' : 'updateGroupRequest',
        subBuilder: $40.UpdateGroupRequest.create)
    ..aOM<$41.CreateGroupBlockedUserRequest>(
        400, _omitFieldNames ? '' : 'createGroupBlockedUserRequest',
        subBuilder: $41.CreateGroupBlockedUserRequest.create)
    ..aOM<$42.DeleteGroupBlockedUserRequest>(
        401, _omitFieldNames ? '' : 'deleteGroupBlockedUserRequest',
        subBuilder: $42.DeleteGroupBlockedUserRequest.create)
    ..aOM<$43.QueryGroupBlockedUserIdsRequest>(
        402, _omitFieldNames ? '' : 'queryGroupBlockedUserIdsRequest',
        subBuilder: $43.QueryGroupBlockedUserIdsRequest.create)
    ..aOM<$44.QueryGroupBlockedUserInfosRequest>(
        403, _omitFieldNames ? '' : 'queryGroupBlockedUserInfosRequest',
        subBuilder: $44.QueryGroupBlockedUserInfosRequest.create)
    ..aOM<$45.CheckGroupJoinQuestionsAnswersRequest>(
        500, _omitFieldNames ? '' : 'checkGroupJoinQuestionsAnswersRequest',
        subBuilder: $45.CheckGroupJoinQuestionsAnswersRequest.create)
    ..aOM<$46.CreateGroupInvitationRequest>(
        501, _omitFieldNames ? '' : 'createGroupInvitationRequest',
        subBuilder: $46.CreateGroupInvitationRequest.create)
    ..aOM<$47.CreateGroupJoinRequestRequest>(
        502, _omitFieldNames ? '' : 'createGroupJoinRequestRequest',
        subBuilder: $47.CreateGroupJoinRequestRequest.create)
    ..aOM<$48.CreateGroupJoinQuestionsRequest>(
        503, _omitFieldNames ? '' : 'createGroupJoinQuestionsRequest',
        subBuilder: $48.CreateGroupJoinQuestionsRequest.create)
    ..aOM<$49.DeleteGroupInvitationRequest>(
        504, _omitFieldNames ? '' : 'deleteGroupInvitationRequest',
        subBuilder: $49.DeleteGroupInvitationRequest.create)
    ..aOM<$50.DeleteGroupJoinRequestRequest>(
        505, _omitFieldNames ? '' : 'deleteGroupJoinRequestRequest',
        subBuilder: $50.DeleteGroupJoinRequestRequest.create)
    ..aOM<$51.DeleteGroupJoinQuestionsRequest>(
        506, _omitFieldNames ? '' : 'deleteGroupJoinQuestionsRequest',
        subBuilder: $51.DeleteGroupJoinQuestionsRequest.create)
    ..aOM<$52.QueryGroupInvitationsRequest>(
        507, _omitFieldNames ? '' : 'queryGroupInvitationsRequest',
        subBuilder: $52.QueryGroupInvitationsRequest.create)
    ..aOM<$53.QueryGroupJoinRequestsRequest>(
        508, _omitFieldNames ? '' : 'queryGroupJoinRequestsRequest',
        subBuilder: $53.QueryGroupJoinRequestsRequest.create)
    ..aOM<$54.QueryGroupJoinQuestionsRequest>(
        509, _omitFieldNames ? '' : 'queryGroupJoinQuestionsRequest',
        subBuilder: $54.QueryGroupJoinQuestionsRequest.create)
    ..aOM<$55.UpdateGroupInvitationRequest>(
        510, _omitFieldNames ? '' : 'updateGroupInvitationRequest',
        subBuilder: $55.UpdateGroupInvitationRequest.create)
    ..aOM<$56.UpdateGroupJoinQuestionRequest>(
        511, _omitFieldNames ? '' : 'updateGroupJoinQuestionRequest',
        subBuilder: $56.UpdateGroupJoinQuestionRequest.create)
    ..aOM<$57.UpdateGroupJoinRequestRequest>(
        512, _omitFieldNames ? '' : 'updateGroupJoinRequestRequest',
        subBuilder: $57.UpdateGroupJoinRequestRequest.create)
    ..aOM<$58.CreateMeetingRequest>(
        900, _omitFieldNames ? '' : 'createMeetingRequest',
        subBuilder: $58.CreateMeetingRequest.create)
    ..aOM<$59.DeleteMeetingRequest>(
        901, _omitFieldNames ? '' : 'deleteMeetingRequest',
        subBuilder: $59.DeleteMeetingRequest.create)
    ..aOM<$60.QueryMeetingsRequest>(
        902, _omitFieldNames ? '' : 'queryMeetingsRequest',
        subBuilder: $60.QueryMeetingsRequest.create)
    ..aOM<$61.UpdateMeetingRequest>(
        903, _omitFieldNames ? '' : 'updateMeetingRequest',
        subBuilder: $61.UpdateMeetingRequest.create)
    ..aOM<$62.UpdateMeetingInvitationRequest>(
        904, _omitFieldNames ? '' : 'updateMeetingInvitationRequest',
        subBuilder: $62.UpdateMeetingInvitationRequest.create)
    ..aOM<$63.DeleteResourceRequest>(
        1000, _omitFieldNames ? '' : 'deleteResourceRequest',
        subBuilder: $63.DeleteResourceRequest.create)
    ..aOM<$64.QueryResourceDownloadInfoRequest>(
        1001, _omitFieldNames ? '' : 'queryResourceDownloadInfoRequest',
        subBuilder: $64.QueryResourceDownloadInfoRequest.create)
    ..aOM<$65.QueryResourceUploadInfoRequest>(
        1002, _omitFieldNames ? '' : 'queryResourceUploadInfoRequest',
        subBuilder: $65.QueryResourceUploadInfoRequest.create)
    ..aOM<$66.QueryMessageAttachmentInfosRequest>(
        1003, _omitFieldNames ? '' : 'queryMessageAttachmentInfosRequest',
        subBuilder: $66.QueryMessageAttachmentInfosRequest.create)
    ..aOM<$67.UpdateMessageAttachmentInfoRequest>(
        1004, _omitFieldNames ? '' : 'updateMessageAttachmentInfoRequest',
        subBuilder: $67.UpdateMessageAttachmentInfoRequest.create)
    ..aOM<$68.DeleteConversationSettingsRequest>(
        1100, _omitFieldNames ? '' : 'deleteConversationSettingsRequest',
        subBuilder: $68.DeleteConversationSettingsRequest.create)
    ..aOM<$69.QueryConversationSettingsRequest>(
        1101, _omitFieldNames ? '' : 'queryConversationSettingsRequest',
        subBuilder: $69.QueryConversationSettingsRequest.create)
    ..aOM<$70.UpdateConversationSettingsRequest>(
        1102, _omitFieldNames ? '' : 'updateConversationSettingsRequest',
        subBuilder: $70.UpdateConversationSettingsRequest.create)
    ..aOM<$71.CreateMessageReactionsRequest>(
        1200, _omitFieldNames ? '' : 'createMessageReactionsRequest',
        subBuilder: $71.CreateMessageReactionsRequest.create)
    ..aOM<$72.DeleteMessageReactionsRequest>(
        1201, _omitFieldNames ? '' : 'deleteMessageReactionsRequest',
        subBuilder: $72.DeleteMessageReactionsRequest.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  TurmsRequest clone() => TurmsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  TurmsRequest copyWith(void Function(TurmsRequest) updates) =>
      super.copyWith((message) => updates(message as TurmsRequest))
          as TurmsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static TurmsRequest create() => TurmsRequest._();
  TurmsRequest createEmptyInstance() => create();
  static $pb.PbList<TurmsRequest> createRepeated() =>
      $pb.PbList<TurmsRequest>();
  @$core.pragma('dart2js:noInline')
  static TurmsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<TurmsRequest>(create);
  static TurmsRequest? _defaultInstance;

  TurmsRequest_Kind whichKind() => _TurmsRequest_KindByTag[$_whichOneof(0)]!;
  void clearKind() => clearField($_whichOneof(0));

  /// Note: "request_id" is allowed to be duplicate because
  /// it is used for clients to identify the response of the same request id in a session
  @$pb.TagNumber(1)
  $fixnum.Int64 get requestId => $_getI64(0);
  @$pb.TagNumber(1)
  set requestId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasRequestId() => $_has(0);
  @$pb.TagNumber(1)
  void clearRequestId() => clearField(1);

  @$pb.TagNumber(2)
  $core.List<$0.Value> get customAttributes => $_getList(1);

  /// User - Session
  @$pb.TagNumber(3)
  $1.CreateSessionRequest get createSessionRequest => $_getN(2);
  @$pb.TagNumber(3)
  set createSessionRequest($1.CreateSessionRequest v) {
    setField(3, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasCreateSessionRequest() => $_has(2);
  @$pb.TagNumber(3)
  void clearCreateSessionRequest() => clearField(3);
  @$pb.TagNumber(3)
  $1.CreateSessionRequest ensureCreateSessionRequest() => $_ensure(2);

  @$pb.TagNumber(4)
  $2.DeleteSessionRequest get deleteSessionRequest => $_getN(3);
  @$pb.TagNumber(4)
  set deleteSessionRequest($2.DeleteSessionRequest v) {
    setField(4, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasDeleteSessionRequest() => $_has(3);
  @$pb.TagNumber(4)
  void clearDeleteSessionRequest() => clearField(4);
  @$pb.TagNumber(4)
  $2.DeleteSessionRequest ensureDeleteSessionRequest() => $_ensure(3);

  /// Conversation
  @$pb.TagNumber(5)
  $3.QueryConversationsRequest get queryConversationsRequest => $_getN(4);
  @$pb.TagNumber(5)
  set queryConversationsRequest($3.QueryConversationsRequest v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasQueryConversationsRequest() => $_has(4);
  @$pb.TagNumber(5)
  void clearQueryConversationsRequest() => clearField(5);
  @$pb.TagNumber(5)
  $3.QueryConversationsRequest ensureQueryConversationsRequest() => $_ensure(4);

  @$pb.TagNumber(6)
  $4.UpdateConversationRequest get updateConversationRequest => $_getN(5);
  @$pb.TagNumber(6)
  set updateConversationRequest($4.UpdateConversationRequest v) {
    setField(6, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasUpdateConversationRequest() => $_has(5);
  @$pb.TagNumber(6)
  void clearUpdateConversationRequest() => clearField(6);
  @$pb.TagNumber(6)
  $4.UpdateConversationRequest ensureUpdateConversationRequest() => $_ensure(5);

  @$pb.TagNumber(7)
  $5.UpdateTypingStatusRequest get updateTypingStatusRequest => $_getN(6);
  @$pb.TagNumber(7)
  set updateTypingStatusRequest($5.UpdateTypingStatusRequest v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasUpdateTypingStatusRequest() => $_has(6);
  @$pb.TagNumber(7)
  void clearUpdateTypingStatusRequest() => clearField(7);
  @$pb.TagNumber(7)
  $5.UpdateTypingStatusRequest ensureUpdateTypingStatusRequest() => $_ensure(6);

  /// Message
  @$pb.TagNumber(8)
  $6.CreateMessageRequest get createMessageRequest => $_getN(7);
  @$pb.TagNumber(8)
  set createMessageRequest($6.CreateMessageRequest v) {
    setField(8, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasCreateMessageRequest() => $_has(7);
  @$pb.TagNumber(8)
  void clearCreateMessageRequest() => clearField(8);
  @$pb.TagNumber(8)
  $6.CreateMessageRequest ensureCreateMessageRequest() => $_ensure(7);

  @$pb.TagNumber(9)
  $7.QueryMessagesRequest get queryMessagesRequest => $_getN(8);
  @$pb.TagNumber(9)
  set queryMessagesRequest($7.QueryMessagesRequest v) {
    setField(9, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasQueryMessagesRequest() => $_has(8);
  @$pb.TagNumber(9)
  void clearQueryMessagesRequest() => clearField(9);
  @$pb.TagNumber(9)
  $7.QueryMessagesRequest ensureQueryMessagesRequest() => $_ensure(8);

  @$pb.TagNumber(10)
  $8.UpdateMessageRequest get updateMessageRequest => $_getN(9);
  @$pb.TagNumber(10)
  set updateMessageRequest($8.UpdateMessageRequest v) {
    setField(10, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasUpdateMessageRequest() => $_has(9);
  @$pb.TagNumber(10)
  void clearUpdateMessageRequest() => clearField(10);
  @$pb.TagNumber(10)
  $8.UpdateMessageRequest ensureUpdateMessageRequest() => $_ensure(9);

  /// Group Member
  @$pb.TagNumber(11)
  $9.CreateGroupMembersRequest get createGroupMembersRequest => $_getN(10);
  @$pb.TagNumber(11)
  set createGroupMembersRequest($9.CreateGroupMembersRequest v) {
    setField(11, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasCreateGroupMembersRequest() => $_has(10);
  @$pb.TagNumber(11)
  void clearCreateGroupMembersRequest() => clearField(11);
  @$pb.TagNumber(11)
  $9.CreateGroupMembersRequest ensureCreateGroupMembersRequest() =>
      $_ensure(10);

  @$pb.TagNumber(12)
  $10.DeleteGroupMembersRequest get deleteGroupMembersRequest => $_getN(11);
  @$pb.TagNumber(12)
  set deleteGroupMembersRequest($10.DeleteGroupMembersRequest v) {
    setField(12, v);
  }

  @$pb.TagNumber(12)
  $core.bool hasDeleteGroupMembersRequest() => $_has(11);
  @$pb.TagNumber(12)
  void clearDeleteGroupMembersRequest() => clearField(12);
  @$pb.TagNumber(12)
  $10.DeleteGroupMembersRequest ensureDeleteGroupMembersRequest() =>
      $_ensure(11);

  @$pb.TagNumber(13)
  $11.QueryGroupMembersRequest get queryGroupMembersRequest => $_getN(12);
  @$pb.TagNumber(13)
  set queryGroupMembersRequest($11.QueryGroupMembersRequest v) {
    setField(13, v);
  }

  @$pb.TagNumber(13)
  $core.bool hasQueryGroupMembersRequest() => $_has(12);
  @$pb.TagNumber(13)
  void clearQueryGroupMembersRequest() => clearField(13);
  @$pb.TagNumber(13)
  $11.QueryGroupMembersRequest ensureQueryGroupMembersRequest() => $_ensure(12);

  @$pb.TagNumber(14)
  $12.UpdateGroupMemberRequest get updateGroupMemberRequest => $_getN(13);
  @$pb.TagNumber(14)
  set updateGroupMemberRequest($12.UpdateGroupMemberRequest v) {
    setField(14, v);
  }

  @$pb.TagNumber(14)
  $core.bool hasUpdateGroupMemberRequest() => $_has(13);
  @$pb.TagNumber(14)
  void clearUpdateGroupMemberRequest() => clearField(14);
  @$pb.TagNumber(14)
  $12.UpdateGroupMemberRequest ensureUpdateGroupMemberRequest() => $_ensure(13);

  /// User
  @$pb.TagNumber(100)
  $13.QueryUserProfilesRequest get queryUserProfilesRequest => $_getN(14);
  @$pb.TagNumber(100)
  set queryUserProfilesRequest($13.QueryUserProfilesRequest v) {
    setField(100, v);
  }

  @$pb.TagNumber(100)
  $core.bool hasQueryUserProfilesRequest() => $_has(14);
  @$pb.TagNumber(100)
  void clearQueryUserProfilesRequest() => clearField(100);
  @$pb.TagNumber(100)
  $13.QueryUserProfilesRequest ensureQueryUserProfilesRequest() => $_ensure(14);

  @$pb.TagNumber(101)
  $14.QueryNearbyUsersRequest get queryNearbyUsersRequest => $_getN(15);
  @$pb.TagNumber(101)
  set queryNearbyUsersRequest($14.QueryNearbyUsersRequest v) {
    setField(101, v);
  }

  @$pb.TagNumber(101)
  $core.bool hasQueryNearbyUsersRequest() => $_has(15);
  @$pb.TagNumber(101)
  void clearQueryNearbyUsersRequest() => clearField(101);
  @$pb.TagNumber(101)
  $14.QueryNearbyUsersRequest ensureQueryNearbyUsersRequest() => $_ensure(15);

  @$pb.TagNumber(102)
  $15.QueryUserOnlineStatusesRequest get queryUserOnlineStatusesRequest =>
      $_getN(16);
  @$pb.TagNumber(102)
  set queryUserOnlineStatusesRequest($15.QueryUserOnlineStatusesRequest v) {
    setField(102, v);
  }

  @$pb.TagNumber(102)
  $core.bool hasQueryUserOnlineStatusesRequest() => $_has(16);
  @$pb.TagNumber(102)
  void clearQueryUserOnlineStatusesRequest() => clearField(102);
  @$pb.TagNumber(102)
  $15.QueryUserOnlineStatusesRequest ensureQueryUserOnlineStatusesRequest() =>
      $_ensure(16);

  @$pb.TagNumber(103)
  $16.UpdateUserLocationRequest get updateUserLocationRequest => $_getN(17);
  @$pb.TagNumber(103)
  set updateUserLocationRequest($16.UpdateUserLocationRequest v) {
    setField(103, v);
  }

  @$pb.TagNumber(103)
  $core.bool hasUpdateUserLocationRequest() => $_has(17);
  @$pb.TagNumber(103)
  void clearUpdateUserLocationRequest() => clearField(103);
  @$pb.TagNumber(103)
  $16.UpdateUserLocationRequest ensureUpdateUserLocationRequest() =>
      $_ensure(17);

  @$pb.TagNumber(104)
  $17.UpdateUserOnlineStatusRequest get updateUserOnlineStatusRequest =>
      $_getN(18);
  @$pb.TagNumber(104)
  set updateUserOnlineStatusRequest($17.UpdateUserOnlineStatusRequest v) {
    setField(104, v);
  }

  @$pb.TagNumber(104)
  $core.bool hasUpdateUserOnlineStatusRequest() => $_has(18);
  @$pb.TagNumber(104)
  void clearUpdateUserOnlineStatusRequest() => clearField(104);
  @$pb.TagNumber(104)
  $17.UpdateUserOnlineStatusRequest ensureUpdateUserOnlineStatusRequest() =>
      $_ensure(18);

  @$pb.TagNumber(105)
  $18.UpdateUserRequest get updateUserRequest => $_getN(19);
  @$pb.TagNumber(105)
  set updateUserRequest($18.UpdateUserRequest v) {
    setField(105, v);
  }

  @$pb.TagNumber(105)
  $core.bool hasUpdateUserRequest() => $_has(19);
  @$pb.TagNumber(105)
  void clearUpdateUserRequest() => clearField(105);
  @$pb.TagNumber(105)
  $18.UpdateUserRequest ensureUpdateUserRequest() => $_ensure(19);

  @$pb.TagNumber(106)
  $19.UpdateUserSettingsRequest get updateUserSettingsRequest => $_getN(20);
  @$pb.TagNumber(106)
  set updateUserSettingsRequest($19.UpdateUserSettingsRequest v) {
    setField(106, v);
  }

  @$pb.TagNumber(106)
  $core.bool hasUpdateUserSettingsRequest() => $_has(20);
  @$pb.TagNumber(106)
  void clearUpdateUserSettingsRequest() => clearField(106);
  @$pb.TagNumber(106)
  $19.UpdateUserSettingsRequest ensureUpdateUserSettingsRequest() =>
      $_ensure(20);

  @$pb.TagNumber(107)
  $20.DeleteUserSettingsRequest get deleteUserSettingsRequest => $_getN(21);
  @$pb.TagNumber(107)
  set deleteUserSettingsRequest($20.DeleteUserSettingsRequest v) {
    setField(107, v);
  }

  @$pb.TagNumber(107)
  $core.bool hasDeleteUserSettingsRequest() => $_has(21);
  @$pb.TagNumber(107)
  void clearDeleteUserSettingsRequest() => clearField(107);
  @$pb.TagNumber(107)
  $20.DeleteUserSettingsRequest ensureDeleteUserSettingsRequest() =>
      $_ensure(21);

  @$pb.TagNumber(108)
  $21.QueryUserSettingsRequest get queryUserSettingsRequest => $_getN(22);
  @$pb.TagNumber(108)
  set queryUserSettingsRequest($21.QueryUserSettingsRequest v) {
    setField(108, v);
  }

  @$pb.TagNumber(108)
  $core.bool hasQueryUserSettingsRequest() => $_has(22);
  @$pb.TagNumber(108)
  void clearQueryUserSettingsRequest() => clearField(108);
  @$pb.TagNumber(108)
  $21.QueryUserSettingsRequest ensureQueryUserSettingsRequest() => $_ensure(22);

  /// User Relationship
  @$pb.TagNumber(200)
  $22.CreateFriendRequestRequest get createFriendRequestRequest => $_getN(23);
  @$pb.TagNumber(200)
  set createFriendRequestRequest($22.CreateFriendRequestRequest v) {
    setField(200, v);
  }

  @$pb.TagNumber(200)
  $core.bool hasCreateFriendRequestRequest() => $_has(23);
  @$pb.TagNumber(200)
  void clearCreateFriendRequestRequest() => clearField(200);
  @$pb.TagNumber(200)
  $22.CreateFriendRequestRequest ensureCreateFriendRequestRequest() =>
      $_ensure(23);

  @$pb.TagNumber(201)
  $23.CreateRelationshipGroupRequest get createRelationshipGroupRequest =>
      $_getN(24);
  @$pb.TagNumber(201)
  set createRelationshipGroupRequest($23.CreateRelationshipGroupRequest v) {
    setField(201, v);
  }

  @$pb.TagNumber(201)
  $core.bool hasCreateRelationshipGroupRequest() => $_has(24);
  @$pb.TagNumber(201)
  void clearCreateRelationshipGroupRequest() => clearField(201);
  @$pb.TagNumber(201)
  $23.CreateRelationshipGroupRequest ensureCreateRelationshipGroupRequest() =>
      $_ensure(24);

  @$pb.TagNumber(202)
  $24.CreateRelationshipRequest get createRelationshipRequest => $_getN(25);
  @$pb.TagNumber(202)
  set createRelationshipRequest($24.CreateRelationshipRequest v) {
    setField(202, v);
  }

  @$pb.TagNumber(202)
  $core.bool hasCreateRelationshipRequest() => $_has(25);
  @$pb.TagNumber(202)
  void clearCreateRelationshipRequest() => clearField(202);
  @$pb.TagNumber(202)
  $24.CreateRelationshipRequest ensureCreateRelationshipRequest() =>
      $_ensure(25);

  @$pb.TagNumber(203)
  $25.DeleteFriendRequestRequest get deleteFriendRequestRequest => $_getN(26);
  @$pb.TagNumber(203)
  set deleteFriendRequestRequest($25.DeleteFriendRequestRequest v) {
    setField(203, v);
  }

  @$pb.TagNumber(203)
  $core.bool hasDeleteFriendRequestRequest() => $_has(26);
  @$pb.TagNumber(203)
  void clearDeleteFriendRequestRequest() => clearField(203);
  @$pb.TagNumber(203)
  $25.DeleteFriendRequestRequest ensureDeleteFriendRequestRequest() =>
      $_ensure(26);

  @$pb.TagNumber(204)
  $26.DeleteRelationshipGroupRequest get deleteRelationshipGroupRequest =>
      $_getN(27);
  @$pb.TagNumber(204)
  set deleteRelationshipGroupRequest($26.DeleteRelationshipGroupRequest v) {
    setField(204, v);
  }

  @$pb.TagNumber(204)
  $core.bool hasDeleteRelationshipGroupRequest() => $_has(27);
  @$pb.TagNumber(204)
  void clearDeleteRelationshipGroupRequest() => clearField(204);
  @$pb.TagNumber(204)
  $26.DeleteRelationshipGroupRequest ensureDeleteRelationshipGroupRequest() =>
      $_ensure(27);

  @$pb.TagNumber(205)
  $27.DeleteRelationshipRequest get deleteRelationshipRequest => $_getN(28);
  @$pb.TagNumber(205)
  set deleteRelationshipRequest($27.DeleteRelationshipRequest v) {
    setField(205, v);
  }

  @$pb.TagNumber(205)
  $core.bool hasDeleteRelationshipRequest() => $_has(28);
  @$pb.TagNumber(205)
  void clearDeleteRelationshipRequest() => clearField(205);
  @$pb.TagNumber(205)
  $27.DeleteRelationshipRequest ensureDeleteRelationshipRequest() =>
      $_ensure(28);

  @$pb.TagNumber(206)
  $28.QueryFriendRequestsRequest get queryFriendRequestsRequest => $_getN(29);
  @$pb.TagNumber(206)
  set queryFriendRequestsRequest($28.QueryFriendRequestsRequest v) {
    setField(206, v);
  }

  @$pb.TagNumber(206)
  $core.bool hasQueryFriendRequestsRequest() => $_has(29);
  @$pb.TagNumber(206)
  void clearQueryFriendRequestsRequest() => clearField(206);
  @$pb.TagNumber(206)
  $28.QueryFriendRequestsRequest ensureQueryFriendRequestsRequest() =>
      $_ensure(29);

  @$pb.TagNumber(207)
  $29.QueryRelatedUserIdsRequest get queryRelatedUserIdsRequest => $_getN(30);
  @$pb.TagNumber(207)
  set queryRelatedUserIdsRequest($29.QueryRelatedUserIdsRequest v) {
    setField(207, v);
  }

  @$pb.TagNumber(207)
  $core.bool hasQueryRelatedUserIdsRequest() => $_has(30);
  @$pb.TagNumber(207)
  void clearQueryRelatedUserIdsRequest() => clearField(207);
  @$pb.TagNumber(207)
  $29.QueryRelatedUserIdsRequest ensureQueryRelatedUserIdsRequest() =>
      $_ensure(30);

  @$pb.TagNumber(208)
  $30.QueryRelationshipGroupsRequest get queryRelationshipGroupsRequest =>
      $_getN(31);
  @$pb.TagNumber(208)
  set queryRelationshipGroupsRequest($30.QueryRelationshipGroupsRequest v) {
    setField(208, v);
  }

  @$pb.TagNumber(208)
  $core.bool hasQueryRelationshipGroupsRequest() => $_has(31);
  @$pb.TagNumber(208)
  void clearQueryRelationshipGroupsRequest() => clearField(208);
  @$pb.TagNumber(208)
  $30.QueryRelationshipGroupsRequest ensureQueryRelationshipGroupsRequest() =>
      $_ensure(31);

  @$pb.TagNumber(209)
  $31.QueryRelationshipsRequest get queryRelationshipsRequest => $_getN(32);
  @$pb.TagNumber(209)
  set queryRelationshipsRequest($31.QueryRelationshipsRequest v) {
    setField(209, v);
  }

  @$pb.TagNumber(209)
  $core.bool hasQueryRelationshipsRequest() => $_has(32);
  @$pb.TagNumber(209)
  void clearQueryRelationshipsRequest() => clearField(209);
  @$pb.TagNumber(209)
  $31.QueryRelationshipsRequest ensureQueryRelationshipsRequest() =>
      $_ensure(32);

  @$pb.TagNumber(210)
  $32.UpdateFriendRequestRequest get updateFriendRequestRequest => $_getN(33);
  @$pb.TagNumber(210)
  set updateFriendRequestRequest($32.UpdateFriendRequestRequest v) {
    setField(210, v);
  }

  @$pb.TagNumber(210)
  $core.bool hasUpdateFriendRequestRequest() => $_has(33);
  @$pb.TagNumber(210)
  void clearUpdateFriendRequestRequest() => clearField(210);
  @$pb.TagNumber(210)
  $32.UpdateFriendRequestRequest ensureUpdateFriendRequestRequest() =>
      $_ensure(33);

  @$pb.TagNumber(211)
  $33.UpdateRelationshipGroupRequest get updateRelationshipGroupRequest =>
      $_getN(34);
  @$pb.TagNumber(211)
  set updateRelationshipGroupRequest($33.UpdateRelationshipGroupRequest v) {
    setField(211, v);
  }

  @$pb.TagNumber(211)
  $core.bool hasUpdateRelationshipGroupRequest() => $_has(34);
  @$pb.TagNumber(211)
  void clearUpdateRelationshipGroupRequest() => clearField(211);
  @$pb.TagNumber(211)
  $33.UpdateRelationshipGroupRequest ensureUpdateRelationshipGroupRequest() =>
      $_ensure(34);

  @$pb.TagNumber(212)
  $34.UpdateRelationshipRequest get updateRelationshipRequest => $_getN(35);
  @$pb.TagNumber(212)
  set updateRelationshipRequest($34.UpdateRelationshipRequest v) {
    setField(212, v);
  }

  @$pb.TagNumber(212)
  $core.bool hasUpdateRelationshipRequest() => $_has(35);
  @$pb.TagNumber(212)
  void clearUpdateRelationshipRequest() => clearField(212);
  @$pb.TagNumber(212)
  $34.UpdateRelationshipRequest ensureUpdateRelationshipRequest() =>
      $_ensure(35);

  /// Group
  @$pb.TagNumber(300)
  $35.CreateGroupRequest get createGroupRequest => $_getN(36);
  @$pb.TagNumber(300)
  set createGroupRequest($35.CreateGroupRequest v) {
    setField(300, v);
  }

  @$pb.TagNumber(300)
  $core.bool hasCreateGroupRequest() => $_has(36);
  @$pb.TagNumber(300)
  void clearCreateGroupRequest() => clearField(300);
  @$pb.TagNumber(300)
  $35.CreateGroupRequest ensureCreateGroupRequest() => $_ensure(36);

  @$pb.TagNumber(301)
  $36.DeleteGroupRequest get deleteGroupRequest => $_getN(37);
  @$pb.TagNumber(301)
  set deleteGroupRequest($36.DeleteGroupRequest v) {
    setField(301, v);
  }

  @$pb.TagNumber(301)
  $core.bool hasDeleteGroupRequest() => $_has(37);
  @$pb.TagNumber(301)
  void clearDeleteGroupRequest() => clearField(301);
  @$pb.TagNumber(301)
  $36.DeleteGroupRequest ensureDeleteGroupRequest() => $_ensure(37);

  @$pb.TagNumber(302)
  $37.QueryGroupsRequest get queryGroupsRequest => $_getN(38);
  @$pb.TagNumber(302)
  set queryGroupsRequest($37.QueryGroupsRequest v) {
    setField(302, v);
  }

  @$pb.TagNumber(302)
  $core.bool hasQueryGroupsRequest() => $_has(38);
  @$pb.TagNumber(302)
  void clearQueryGroupsRequest() => clearField(302);
  @$pb.TagNumber(302)
  $37.QueryGroupsRequest ensureQueryGroupsRequest() => $_ensure(38);

  @$pb.TagNumber(303)
  $38.QueryJoinedGroupIdsRequest get queryJoinedGroupIdsRequest => $_getN(39);
  @$pb.TagNumber(303)
  set queryJoinedGroupIdsRequest($38.QueryJoinedGroupIdsRequest v) {
    setField(303, v);
  }

  @$pb.TagNumber(303)
  $core.bool hasQueryJoinedGroupIdsRequest() => $_has(39);
  @$pb.TagNumber(303)
  void clearQueryJoinedGroupIdsRequest() => clearField(303);
  @$pb.TagNumber(303)
  $38.QueryJoinedGroupIdsRequest ensureQueryJoinedGroupIdsRequest() =>
      $_ensure(39);

  @$pb.TagNumber(304)
  $39.QueryJoinedGroupInfosRequest get queryJoinedGroupInfosRequest =>
      $_getN(40);
  @$pb.TagNumber(304)
  set queryJoinedGroupInfosRequest($39.QueryJoinedGroupInfosRequest v) {
    setField(304, v);
  }

  @$pb.TagNumber(304)
  $core.bool hasQueryJoinedGroupInfosRequest() => $_has(40);
  @$pb.TagNumber(304)
  void clearQueryJoinedGroupInfosRequest() => clearField(304);
  @$pb.TagNumber(304)
  $39.QueryJoinedGroupInfosRequest ensureQueryJoinedGroupInfosRequest() =>
      $_ensure(40);

  @$pb.TagNumber(305)
  $40.UpdateGroupRequest get updateGroupRequest => $_getN(41);
  @$pb.TagNumber(305)
  set updateGroupRequest($40.UpdateGroupRequest v) {
    setField(305, v);
  }

  @$pb.TagNumber(305)
  $core.bool hasUpdateGroupRequest() => $_has(41);
  @$pb.TagNumber(305)
  void clearUpdateGroupRequest() => clearField(305);
  @$pb.TagNumber(305)
  $40.UpdateGroupRequest ensureUpdateGroupRequest() => $_ensure(41);

  /// Group Blocklist
  @$pb.TagNumber(400)
  $41.CreateGroupBlockedUserRequest get createGroupBlockedUserRequest =>
      $_getN(42);
  @$pb.TagNumber(400)
  set createGroupBlockedUserRequest($41.CreateGroupBlockedUserRequest v) {
    setField(400, v);
  }

  @$pb.TagNumber(400)
  $core.bool hasCreateGroupBlockedUserRequest() => $_has(42);
  @$pb.TagNumber(400)
  void clearCreateGroupBlockedUserRequest() => clearField(400);
  @$pb.TagNumber(400)
  $41.CreateGroupBlockedUserRequest ensureCreateGroupBlockedUserRequest() =>
      $_ensure(42);

  @$pb.TagNumber(401)
  $42.DeleteGroupBlockedUserRequest get deleteGroupBlockedUserRequest =>
      $_getN(43);
  @$pb.TagNumber(401)
  set deleteGroupBlockedUserRequest($42.DeleteGroupBlockedUserRequest v) {
    setField(401, v);
  }

  @$pb.TagNumber(401)
  $core.bool hasDeleteGroupBlockedUserRequest() => $_has(43);
  @$pb.TagNumber(401)
  void clearDeleteGroupBlockedUserRequest() => clearField(401);
  @$pb.TagNumber(401)
  $42.DeleteGroupBlockedUserRequest ensureDeleteGroupBlockedUserRequest() =>
      $_ensure(43);

  @$pb.TagNumber(402)
  $43.QueryGroupBlockedUserIdsRequest get queryGroupBlockedUserIdsRequest =>
      $_getN(44);
  @$pb.TagNumber(402)
  set queryGroupBlockedUserIdsRequest($43.QueryGroupBlockedUserIdsRequest v) {
    setField(402, v);
  }

  @$pb.TagNumber(402)
  $core.bool hasQueryGroupBlockedUserIdsRequest() => $_has(44);
  @$pb.TagNumber(402)
  void clearQueryGroupBlockedUserIdsRequest() => clearField(402);
  @$pb.TagNumber(402)
  $43.QueryGroupBlockedUserIdsRequest ensureQueryGroupBlockedUserIdsRequest() =>
      $_ensure(44);

  @$pb.TagNumber(403)
  $44.QueryGroupBlockedUserInfosRequest get queryGroupBlockedUserInfosRequest =>
      $_getN(45);
  @$pb.TagNumber(403)
  set queryGroupBlockedUserInfosRequest(
      $44.QueryGroupBlockedUserInfosRequest v) {
    setField(403, v);
  }

  @$pb.TagNumber(403)
  $core.bool hasQueryGroupBlockedUserInfosRequest() => $_has(45);
  @$pb.TagNumber(403)
  void clearQueryGroupBlockedUserInfosRequest() => clearField(403);
  @$pb.TagNumber(403)
  $44.QueryGroupBlockedUserInfosRequest
      ensureQueryGroupBlockedUserInfosRequest() => $_ensure(45);

  /// Group Enrollment
  @$pb.TagNumber(500)
  $45.CheckGroupJoinQuestionsAnswersRequest
      get checkGroupJoinQuestionsAnswersRequest => $_getN(46);
  @$pb.TagNumber(500)
  set checkGroupJoinQuestionsAnswersRequest(
      $45.CheckGroupJoinQuestionsAnswersRequest v) {
    setField(500, v);
  }

  @$pb.TagNumber(500)
  $core.bool hasCheckGroupJoinQuestionsAnswersRequest() => $_has(46);
  @$pb.TagNumber(500)
  void clearCheckGroupJoinQuestionsAnswersRequest() => clearField(500);
  @$pb.TagNumber(500)
  $45.CheckGroupJoinQuestionsAnswersRequest
      ensureCheckGroupJoinQuestionsAnswersRequest() => $_ensure(46);

  @$pb.TagNumber(501)
  $46.CreateGroupInvitationRequest get createGroupInvitationRequest =>
      $_getN(47);
  @$pb.TagNumber(501)
  set createGroupInvitationRequest($46.CreateGroupInvitationRequest v) {
    setField(501, v);
  }

  @$pb.TagNumber(501)
  $core.bool hasCreateGroupInvitationRequest() => $_has(47);
  @$pb.TagNumber(501)
  void clearCreateGroupInvitationRequest() => clearField(501);
  @$pb.TagNumber(501)
  $46.CreateGroupInvitationRequest ensureCreateGroupInvitationRequest() =>
      $_ensure(47);

  @$pb.TagNumber(502)
  $47.CreateGroupJoinRequestRequest get createGroupJoinRequestRequest =>
      $_getN(48);
  @$pb.TagNumber(502)
  set createGroupJoinRequestRequest($47.CreateGroupJoinRequestRequest v) {
    setField(502, v);
  }

  @$pb.TagNumber(502)
  $core.bool hasCreateGroupJoinRequestRequest() => $_has(48);
  @$pb.TagNumber(502)
  void clearCreateGroupJoinRequestRequest() => clearField(502);
  @$pb.TagNumber(502)
  $47.CreateGroupJoinRequestRequest ensureCreateGroupJoinRequestRequest() =>
      $_ensure(48);

  @$pb.TagNumber(503)
  $48.CreateGroupJoinQuestionsRequest get createGroupJoinQuestionsRequest =>
      $_getN(49);
  @$pb.TagNumber(503)
  set createGroupJoinQuestionsRequest($48.CreateGroupJoinQuestionsRequest v) {
    setField(503, v);
  }

  @$pb.TagNumber(503)
  $core.bool hasCreateGroupJoinQuestionsRequest() => $_has(49);
  @$pb.TagNumber(503)
  void clearCreateGroupJoinQuestionsRequest() => clearField(503);
  @$pb.TagNumber(503)
  $48.CreateGroupJoinQuestionsRequest ensureCreateGroupJoinQuestionsRequest() =>
      $_ensure(49);

  @$pb.TagNumber(504)
  $49.DeleteGroupInvitationRequest get deleteGroupInvitationRequest =>
      $_getN(50);
  @$pb.TagNumber(504)
  set deleteGroupInvitationRequest($49.DeleteGroupInvitationRequest v) {
    setField(504, v);
  }

  @$pb.TagNumber(504)
  $core.bool hasDeleteGroupInvitationRequest() => $_has(50);
  @$pb.TagNumber(504)
  void clearDeleteGroupInvitationRequest() => clearField(504);
  @$pb.TagNumber(504)
  $49.DeleteGroupInvitationRequest ensureDeleteGroupInvitationRequest() =>
      $_ensure(50);

  @$pb.TagNumber(505)
  $50.DeleteGroupJoinRequestRequest get deleteGroupJoinRequestRequest =>
      $_getN(51);
  @$pb.TagNumber(505)
  set deleteGroupJoinRequestRequest($50.DeleteGroupJoinRequestRequest v) {
    setField(505, v);
  }

  @$pb.TagNumber(505)
  $core.bool hasDeleteGroupJoinRequestRequest() => $_has(51);
  @$pb.TagNumber(505)
  void clearDeleteGroupJoinRequestRequest() => clearField(505);
  @$pb.TagNumber(505)
  $50.DeleteGroupJoinRequestRequest ensureDeleteGroupJoinRequestRequest() =>
      $_ensure(51);

  @$pb.TagNumber(506)
  $51.DeleteGroupJoinQuestionsRequest get deleteGroupJoinQuestionsRequest =>
      $_getN(52);
  @$pb.TagNumber(506)
  set deleteGroupJoinQuestionsRequest($51.DeleteGroupJoinQuestionsRequest v) {
    setField(506, v);
  }

  @$pb.TagNumber(506)
  $core.bool hasDeleteGroupJoinQuestionsRequest() => $_has(52);
  @$pb.TagNumber(506)
  void clearDeleteGroupJoinQuestionsRequest() => clearField(506);
  @$pb.TagNumber(506)
  $51.DeleteGroupJoinQuestionsRequest ensureDeleteGroupJoinQuestionsRequest() =>
      $_ensure(52);

  @$pb.TagNumber(507)
  $52.QueryGroupInvitationsRequest get queryGroupInvitationsRequest =>
      $_getN(53);
  @$pb.TagNumber(507)
  set queryGroupInvitationsRequest($52.QueryGroupInvitationsRequest v) {
    setField(507, v);
  }

  @$pb.TagNumber(507)
  $core.bool hasQueryGroupInvitationsRequest() => $_has(53);
  @$pb.TagNumber(507)
  void clearQueryGroupInvitationsRequest() => clearField(507);
  @$pb.TagNumber(507)
  $52.QueryGroupInvitationsRequest ensureQueryGroupInvitationsRequest() =>
      $_ensure(53);

  @$pb.TagNumber(508)
  $53.QueryGroupJoinRequestsRequest get queryGroupJoinRequestsRequest =>
      $_getN(54);
  @$pb.TagNumber(508)
  set queryGroupJoinRequestsRequest($53.QueryGroupJoinRequestsRequest v) {
    setField(508, v);
  }

  @$pb.TagNumber(508)
  $core.bool hasQueryGroupJoinRequestsRequest() => $_has(54);
  @$pb.TagNumber(508)
  void clearQueryGroupJoinRequestsRequest() => clearField(508);
  @$pb.TagNumber(508)
  $53.QueryGroupJoinRequestsRequest ensureQueryGroupJoinRequestsRequest() =>
      $_ensure(54);

  @$pb.TagNumber(509)
  $54.QueryGroupJoinQuestionsRequest get queryGroupJoinQuestionsRequest =>
      $_getN(55);
  @$pb.TagNumber(509)
  set queryGroupJoinQuestionsRequest($54.QueryGroupJoinQuestionsRequest v) {
    setField(509, v);
  }

  @$pb.TagNumber(509)
  $core.bool hasQueryGroupJoinQuestionsRequest() => $_has(55);
  @$pb.TagNumber(509)
  void clearQueryGroupJoinQuestionsRequest() => clearField(509);
  @$pb.TagNumber(509)
  $54.QueryGroupJoinQuestionsRequest ensureQueryGroupJoinQuestionsRequest() =>
      $_ensure(55);

  @$pb.TagNumber(510)
  $55.UpdateGroupInvitationRequest get updateGroupInvitationRequest =>
      $_getN(56);
  @$pb.TagNumber(510)
  set updateGroupInvitationRequest($55.UpdateGroupInvitationRequest v) {
    setField(510, v);
  }

  @$pb.TagNumber(510)
  $core.bool hasUpdateGroupInvitationRequest() => $_has(56);
  @$pb.TagNumber(510)
  void clearUpdateGroupInvitationRequest() => clearField(510);
  @$pb.TagNumber(510)
  $55.UpdateGroupInvitationRequest ensureUpdateGroupInvitationRequest() =>
      $_ensure(56);

  @$pb.TagNumber(511)
  $56.UpdateGroupJoinQuestionRequest get updateGroupJoinQuestionRequest =>
      $_getN(57);
  @$pb.TagNumber(511)
  set updateGroupJoinQuestionRequest($56.UpdateGroupJoinQuestionRequest v) {
    setField(511, v);
  }

  @$pb.TagNumber(511)
  $core.bool hasUpdateGroupJoinQuestionRequest() => $_has(57);
  @$pb.TagNumber(511)
  void clearUpdateGroupJoinQuestionRequest() => clearField(511);
  @$pb.TagNumber(511)
  $56.UpdateGroupJoinQuestionRequest ensureUpdateGroupJoinQuestionRequest() =>
      $_ensure(57);

  @$pb.TagNumber(512)
  $57.UpdateGroupJoinRequestRequest get updateGroupJoinRequestRequest =>
      $_getN(58);
  @$pb.TagNumber(512)
  set updateGroupJoinRequestRequest($57.UpdateGroupJoinRequestRequest v) {
    setField(512, v);
  }

  @$pb.TagNumber(512)
  $core.bool hasUpdateGroupJoinRequestRequest() => $_has(58);
  @$pb.TagNumber(512)
  void clearUpdateGroupJoinRequestRequest() => clearField(512);
  @$pb.TagNumber(512)
  $57.UpdateGroupJoinRequestRequest ensureUpdateGroupJoinRequestRequest() =>
      $_ensure(58);

  /// Conference
  @$pb.TagNumber(900)
  $58.CreateMeetingRequest get createMeetingRequest => $_getN(59);
  @$pb.TagNumber(900)
  set createMeetingRequest($58.CreateMeetingRequest v) {
    setField(900, v);
  }

  @$pb.TagNumber(900)
  $core.bool hasCreateMeetingRequest() => $_has(59);
  @$pb.TagNumber(900)
  void clearCreateMeetingRequest() => clearField(900);
  @$pb.TagNumber(900)
  $58.CreateMeetingRequest ensureCreateMeetingRequest() => $_ensure(59);

  @$pb.TagNumber(901)
  $59.DeleteMeetingRequest get deleteMeetingRequest => $_getN(60);
  @$pb.TagNumber(901)
  set deleteMeetingRequest($59.DeleteMeetingRequest v) {
    setField(901, v);
  }

  @$pb.TagNumber(901)
  $core.bool hasDeleteMeetingRequest() => $_has(60);
  @$pb.TagNumber(901)
  void clearDeleteMeetingRequest() => clearField(901);
  @$pb.TagNumber(901)
  $59.DeleteMeetingRequest ensureDeleteMeetingRequest() => $_ensure(60);

  @$pb.TagNumber(902)
  $60.QueryMeetingsRequest get queryMeetingsRequest => $_getN(61);
  @$pb.TagNumber(902)
  set queryMeetingsRequest($60.QueryMeetingsRequest v) {
    setField(902, v);
  }

  @$pb.TagNumber(902)
  $core.bool hasQueryMeetingsRequest() => $_has(61);
  @$pb.TagNumber(902)
  void clearQueryMeetingsRequest() => clearField(902);
  @$pb.TagNumber(902)
  $60.QueryMeetingsRequest ensureQueryMeetingsRequest() => $_ensure(61);

  @$pb.TagNumber(903)
  $61.UpdateMeetingRequest get updateMeetingRequest => $_getN(62);
  @$pb.TagNumber(903)
  set updateMeetingRequest($61.UpdateMeetingRequest v) {
    setField(903, v);
  }

  @$pb.TagNumber(903)
  $core.bool hasUpdateMeetingRequest() => $_has(62);
  @$pb.TagNumber(903)
  void clearUpdateMeetingRequest() => clearField(903);
  @$pb.TagNumber(903)
  $61.UpdateMeetingRequest ensureUpdateMeetingRequest() => $_ensure(62);

  @$pb.TagNumber(904)
  $62.UpdateMeetingInvitationRequest get updateMeetingInvitationRequest =>
      $_getN(63);
  @$pb.TagNumber(904)
  set updateMeetingInvitationRequest($62.UpdateMeetingInvitationRequest v) {
    setField(904, v);
  }

  @$pb.TagNumber(904)
  $core.bool hasUpdateMeetingInvitationRequest() => $_has(63);
  @$pb.TagNumber(904)
  void clearUpdateMeetingInvitationRequest() => clearField(904);
  @$pb.TagNumber(904)
  $62.UpdateMeetingInvitationRequest ensureUpdateMeetingInvitationRequest() =>
      $_ensure(63);

  /// Storage
  @$pb.TagNumber(1000)
  $63.DeleteResourceRequest get deleteResourceRequest => $_getN(64);
  @$pb.TagNumber(1000)
  set deleteResourceRequest($63.DeleteResourceRequest v) {
    setField(1000, v);
  }

  @$pb.TagNumber(1000)
  $core.bool hasDeleteResourceRequest() => $_has(64);
  @$pb.TagNumber(1000)
  void clearDeleteResourceRequest() => clearField(1000);
  @$pb.TagNumber(1000)
  $63.DeleteResourceRequest ensureDeleteResourceRequest() => $_ensure(64);

  @$pb.TagNumber(1001)
  $64.QueryResourceDownloadInfoRequest get queryResourceDownloadInfoRequest =>
      $_getN(65);
  @$pb.TagNumber(1001)
  set queryResourceDownloadInfoRequest($64.QueryResourceDownloadInfoRequest v) {
    setField(1001, v);
  }

  @$pb.TagNumber(1001)
  $core.bool hasQueryResourceDownloadInfoRequest() => $_has(65);
  @$pb.TagNumber(1001)
  void clearQueryResourceDownloadInfoRequest() => clearField(1001);
  @$pb.TagNumber(1001)
  $64.QueryResourceDownloadInfoRequest
      ensureQueryResourceDownloadInfoRequest() => $_ensure(65);

  @$pb.TagNumber(1002)
  $65.QueryResourceUploadInfoRequest get queryResourceUploadInfoRequest =>
      $_getN(66);
  @$pb.TagNumber(1002)
  set queryResourceUploadInfoRequest($65.QueryResourceUploadInfoRequest v) {
    setField(1002, v);
  }

  @$pb.TagNumber(1002)
  $core.bool hasQueryResourceUploadInfoRequest() => $_has(66);
  @$pb.TagNumber(1002)
  void clearQueryResourceUploadInfoRequest() => clearField(1002);
  @$pb.TagNumber(1002)
  $65.QueryResourceUploadInfoRequest ensureQueryResourceUploadInfoRequest() =>
      $_ensure(66);

  @$pb.TagNumber(1003)
  $66.QueryMessageAttachmentInfosRequest
      get queryMessageAttachmentInfosRequest => $_getN(67);
  @$pb.TagNumber(1003)
  set queryMessageAttachmentInfosRequest(
      $66.QueryMessageAttachmentInfosRequest v) {
    setField(1003, v);
  }

  @$pb.TagNumber(1003)
  $core.bool hasQueryMessageAttachmentInfosRequest() => $_has(67);
  @$pb.TagNumber(1003)
  void clearQueryMessageAttachmentInfosRequest() => clearField(1003);
  @$pb.TagNumber(1003)
  $66.QueryMessageAttachmentInfosRequest
      ensureQueryMessageAttachmentInfosRequest() => $_ensure(67);

  @$pb.TagNumber(1004)
  $67.UpdateMessageAttachmentInfoRequest
      get updateMessageAttachmentInfoRequest => $_getN(68);
  @$pb.TagNumber(1004)
  set updateMessageAttachmentInfoRequest(
      $67.UpdateMessageAttachmentInfoRequest v) {
    setField(1004, v);
  }

  @$pb.TagNumber(1004)
  $core.bool hasUpdateMessageAttachmentInfoRequest() => $_has(68);
  @$pb.TagNumber(1004)
  void clearUpdateMessageAttachmentInfoRequest() => clearField(1004);
  @$pb.TagNumber(1004)
  $67.UpdateMessageAttachmentInfoRequest
      ensureUpdateMessageAttachmentInfoRequest() => $_ensure(68);

  /// Conversation - Supplement
  @$pb.TagNumber(1100)
  $68.DeleteConversationSettingsRequest get deleteConversationSettingsRequest =>
      $_getN(69);
  @$pb.TagNumber(1100)
  set deleteConversationSettingsRequest(
      $68.DeleteConversationSettingsRequest v) {
    setField(1100, v);
  }

  @$pb.TagNumber(1100)
  $core.bool hasDeleteConversationSettingsRequest() => $_has(69);
  @$pb.TagNumber(1100)
  void clearDeleteConversationSettingsRequest() => clearField(1100);
  @$pb.TagNumber(1100)
  $68.DeleteConversationSettingsRequest
      ensureDeleteConversationSettingsRequest() => $_ensure(69);

  @$pb.TagNumber(1101)
  $69.QueryConversationSettingsRequest get queryConversationSettingsRequest =>
      $_getN(70);
  @$pb.TagNumber(1101)
  set queryConversationSettingsRequest($69.QueryConversationSettingsRequest v) {
    setField(1101, v);
  }

  @$pb.TagNumber(1101)
  $core.bool hasQueryConversationSettingsRequest() => $_has(70);
  @$pb.TagNumber(1101)
  void clearQueryConversationSettingsRequest() => clearField(1101);
  @$pb.TagNumber(1101)
  $69.QueryConversationSettingsRequest
      ensureQueryConversationSettingsRequest() => $_ensure(70);

  @$pb.TagNumber(1102)
  $70.UpdateConversationSettingsRequest get updateConversationSettingsRequest =>
      $_getN(71);
  @$pb.TagNumber(1102)
  set updateConversationSettingsRequest(
      $70.UpdateConversationSettingsRequest v) {
    setField(1102, v);
  }

  @$pb.TagNumber(1102)
  $core.bool hasUpdateConversationSettingsRequest() => $_has(71);
  @$pb.TagNumber(1102)
  void clearUpdateConversationSettingsRequest() => clearField(1102);
  @$pb.TagNumber(1102)
  $70.UpdateConversationSettingsRequest
      ensureUpdateConversationSettingsRequest() => $_ensure(71);

  /// Message - Supplement
  @$pb.TagNumber(1200)
  $71.CreateMessageReactionsRequest get createMessageReactionsRequest =>
      $_getN(72);
  @$pb.TagNumber(1200)
  set createMessageReactionsRequest($71.CreateMessageReactionsRequest v) {
    setField(1200, v);
  }

  @$pb.TagNumber(1200)
  $core.bool hasCreateMessageReactionsRequest() => $_has(72);
  @$pb.TagNumber(1200)
  void clearCreateMessageReactionsRequest() => clearField(1200);
  @$pb.TagNumber(1200)
  $71.CreateMessageReactionsRequest ensureCreateMessageReactionsRequest() =>
      $_ensure(72);

  @$pb.TagNumber(1201)
  $72.DeleteMessageReactionsRequest get deleteMessageReactionsRequest =>
      $_getN(73);
  @$pb.TagNumber(1201)
  set deleteMessageReactionsRequest($72.DeleteMessageReactionsRequest v) {
    setField(1201, v);
  }

  @$pb.TagNumber(1201)
  $core.bool hasDeleteMessageReactionsRequest() => $_has(73);
  @$pb.TagNumber(1201)
  void clearDeleteMessageReactionsRequest() => clearField(1201);
  @$pb.TagNumber(1201)
  $72.DeleteMessageReactionsRequest ensureDeleteMessageReactionsRequest() =>
      $_ensure(73);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
