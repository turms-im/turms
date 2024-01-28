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

import 'conversation/query_conversations_request.pb.dart' as $2;
import 'conversation/update_conversation_request.pb.dart' as $3;
import 'conversation/update_typing_status_request.pb.dart' as $4;
import 'group/blocklist/create_group_blocked_user_request.pb.dart' as $37;
import 'group/blocklist/delete_group_blocked_user_request.pb.dart' as $38;
import 'group/blocklist/query_group_blocked_user_ids_request.pb.dart' as $39;
import 'group/blocklist/query_group_blocked_user_infos_request.pb.dart' as $40;
import 'group/create_group_request.pb.dart' as $31;
import 'group/delete_group_request.pb.dart' as $32;
import 'group/enrollment/check_group_join_questions_answers_request.pb.dart'
    as $41;
import 'group/enrollment/create_group_invitation_request.pb.dart' as $42;
import 'group/enrollment/create_group_join_questions_request.pb.dart' as $44;
import 'group/enrollment/create_group_join_request_request.pb.dart' as $43;
import 'group/enrollment/delete_group_invitation_request.pb.dart' as $45;
import 'group/enrollment/delete_group_join_questions_request.pb.dart' as $47;
import 'group/enrollment/delete_group_join_request_request.pb.dart' as $46;
import 'group/enrollment/query_group_invitations_request.pb.dart' as $48;
import 'group/enrollment/query_group_join_questions_request.pb.dart' as $50;
import 'group/enrollment/query_group_join_requests_request.pb.dart' as $49;
import 'group/enrollment/update_group_invitation_request.pb.dart' as $51;
import 'group/enrollment/update_group_join_question_request.pb.dart' as $52;
import 'group/enrollment/update_group_join_request_request.pb.dart' as $53;
import 'group/member/create_group_members_request.pb.dart' as $8;
import 'group/member/delete_group_members_request.pb.dart' as $9;
import 'group/member/query_group_members_request.pb.dart' as $10;
import 'group/member/update_group_member_request.pb.dart' as $11;
import 'group/query_groups_request.pb.dart' as $33;
import 'group/query_joined_group_ids_request.pb.dart' as $34;
import 'group/query_joined_group_infos_request.pb.dart' as $35;
import 'group/update_group_request.pb.dart' as $36;
import 'message/create_message_request.pb.dart' as $5;
import 'message/query_messages_request.pb.dart' as $6;
import 'message/update_message_request.pb.dart' as $7;
import 'storage/delete_resource_request.pb.dart' as $54;
import 'storage/query_message_attachment_infos_request.pb.dart' as $57;
import 'storage/query_resource_download_info_request.pb.dart' as $55;
import 'storage/query_resource_upload_info_request.pb.dart' as $56;
import 'storage/update_message_attachment_info_request.pb.dart' as $58;
import 'user/create_session_request.pb.dart' as $0;
import 'user/delete_session_request.pb.dart' as $1;
import 'user/query_nearby_users_request.pb.dart' as $13;
import 'user/query_user_online_statuses_request.pb.dart' as $14;
import 'user/query_user_profiles_request.pb.dart' as $12;
import 'user/relationship/create_friend_request_request.pb.dart' as $18;
import 'user/relationship/create_relationship_group_request.pb.dart' as $19;
import 'user/relationship/create_relationship_request.pb.dart' as $20;
import 'user/relationship/delete_friend_request_request.pb.dart' as $21;
import 'user/relationship/delete_relationship_group_request.pb.dart' as $22;
import 'user/relationship/delete_relationship_request.pb.dart' as $23;
import 'user/relationship/query_friend_requests_request.pb.dart' as $24;
import 'user/relationship/query_related_user_ids_request.pb.dart' as $25;
import 'user/relationship/query_relationship_groups_request.pb.dart' as $26;
import 'user/relationship/query_relationships_request.pb.dart' as $27;
import 'user/relationship/update_friend_request_request.pb.dart' as $28;
import 'user/relationship/update_relationship_group_request.pb.dart' as $29;
import 'user/relationship/update_relationship_request.pb.dart' as $30;
import 'user/update_user_location_request.pb.dart' as $15;
import 'user/update_user_online_status_request.pb.dart' as $16;
import 'user/update_user_request.pb.dart' as $17;

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
  deleteResourceRequest,
  queryResourceDownloadInfoRequest,
  queryResourceUploadInfoRequest,
  queryMessageAttachmentInfosRequest,
  updateMessageAttachmentInfoRequest,
  notSet
}

/// Client -> Server -> Client
class TurmsRequest extends $pb.GeneratedMessage {
  factory TurmsRequest({
    $fixnum.Int64? requestId,
    $0.CreateSessionRequest? createSessionRequest,
    $1.DeleteSessionRequest? deleteSessionRequest,
    $2.QueryConversationsRequest? queryConversationsRequest,
    $3.UpdateConversationRequest? updateConversationRequest,
    $4.UpdateTypingStatusRequest? updateTypingStatusRequest,
    $5.CreateMessageRequest? createMessageRequest,
    $6.QueryMessagesRequest? queryMessagesRequest,
    $7.UpdateMessageRequest? updateMessageRequest,
    $8.CreateGroupMembersRequest? createGroupMembersRequest,
    $9.DeleteGroupMembersRequest? deleteGroupMembersRequest,
    $10.QueryGroupMembersRequest? queryGroupMembersRequest,
    $11.UpdateGroupMemberRequest? updateGroupMemberRequest,
    $12.QueryUserProfilesRequest? queryUserProfilesRequest,
    $13.QueryNearbyUsersRequest? queryNearbyUsersRequest,
    $14.QueryUserOnlineStatusesRequest? queryUserOnlineStatusesRequest,
    $15.UpdateUserLocationRequest? updateUserLocationRequest,
    $16.UpdateUserOnlineStatusRequest? updateUserOnlineStatusRequest,
    $17.UpdateUserRequest? updateUserRequest,
    $18.CreateFriendRequestRequest? createFriendRequestRequest,
    $19.CreateRelationshipGroupRequest? createRelationshipGroupRequest,
    $20.CreateRelationshipRequest? createRelationshipRequest,
    $21.DeleteFriendRequestRequest? deleteFriendRequestRequest,
    $22.DeleteRelationshipGroupRequest? deleteRelationshipGroupRequest,
    $23.DeleteRelationshipRequest? deleteRelationshipRequest,
    $24.QueryFriendRequestsRequest? queryFriendRequestsRequest,
    $25.QueryRelatedUserIdsRequest? queryRelatedUserIdsRequest,
    $26.QueryRelationshipGroupsRequest? queryRelationshipGroupsRequest,
    $27.QueryRelationshipsRequest? queryRelationshipsRequest,
    $28.UpdateFriendRequestRequest? updateFriendRequestRequest,
    $29.UpdateRelationshipGroupRequest? updateRelationshipGroupRequest,
    $30.UpdateRelationshipRequest? updateRelationshipRequest,
    $31.CreateGroupRequest? createGroupRequest,
    $32.DeleteGroupRequest? deleteGroupRequest,
    $33.QueryGroupsRequest? queryGroupsRequest,
    $34.QueryJoinedGroupIdsRequest? queryJoinedGroupIdsRequest,
    $35.QueryJoinedGroupInfosRequest? queryJoinedGroupInfosRequest,
    $36.UpdateGroupRequest? updateGroupRequest,
    $37.CreateGroupBlockedUserRequest? createGroupBlockedUserRequest,
    $38.DeleteGroupBlockedUserRequest? deleteGroupBlockedUserRequest,
    $39.QueryGroupBlockedUserIdsRequest? queryGroupBlockedUserIdsRequest,
    $40.QueryGroupBlockedUserInfosRequest? queryGroupBlockedUserInfosRequest,
    $41.CheckGroupJoinQuestionsAnswersRequest?
        checkGroupJoinQuestionsAnswersRequest,
    $42.CreateGroupInvitationRequest? createGroupInvitationRequest,
    $43.CreateGroupJoinRequestRequest? createGroupJoinRequestRequest,
    $44.CreateGroupJoinQuestionsRequest? createGroupJoinQuestionsRequest,
    $45.DeleteGroupInvitationRequest? deleteGroupInvitationRequest,
    $46.DeleteGroupJoinRequestRequest? deleteGroupJoinRequestRequest,
    $47.DeleteGroupJoinQuestionsRequest? deleteGroupJoinQuestionsRequest,
    $48.QueryGroupInvitationsRequest? queryGroupInvitationsRequest,
    $49.QueryGroupJoinRequestsRequest? queryGroupJoinRequestsRequest,
    $50.QueryGroupJoinQuestionsRequest? queryGroupJoinQuestionsRequest,
    $51.UpdateGroupInvitationRequest? updateGroupInvitationRequest,
    $52.UpdateGroupJoinQuestionRequest? updateGroupJoinQuestionRequest,
    $53.UpdateGroupJoinRequestRequest? updateGroupJoinRequestRequest,
    $54.DeleteResourceRequest? deleteResourceRequest,
    $55.QueryResourceDownloadInfoRequest? queryResourceDownloadInfoRequest,
    $56.QueryResourceUploadInfoRequest? queryResourceUploadInfoRequest,
    $57.QueryMessageAttachmentInfosRequest? queryMessageAttachmentInfosRequest,
    $58.UpdateMessageAttachmentInfoRequest? updateMessageAttachmentInfoRequest,
  }) {
    final $result = create();
    if (requestId != null) {
      $result.requestId = requestId;
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
    1000: TurmsRequest_Kind.deleteResourceRequest,
    1001: TurmsRequest_Kind.queryResourceDownloadInfoRequest,
    1002: TurmsRequest_Kind.queryResourceUploadInfoRequest,
    1003: TurmsRequest_Kind.queryMessageAttachmentInfosRequest,
    1004: TurmsRequest_Kind.updateMessageAttachmentInfoRequest,
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
      1000,
      1001,
      1002,
      1003,
      1004
    ])
    ..aInt64(1, _omitFieldNames ? '' : 'requestId')
    ..aOM<$0.CreateSessionRequest>(
        3, _omitFieldNames ? '' : 'createSessionRequest',
        subBuilder: $0.CreateSessionRequest.create)
    ..aOM<$1.DeleteSessionRequest>(
        4, _omitFieldNames ? '' : 'deleteSessionRequest',
        subBuilder: $1.DeleteSessionRequest.create)
    ..aOM<$2.QueryConversationsRequest>(
        5, _omitFieldNames ? '' : 'queryConversationsRequest',
        subBuilder: $2.QueryConversationsRequest.create)
    ..aOM<$3.UpdateConversationRequest>(
        6, _omitFieldNames ? '' : 'updateConversationRequest',
        subBuilder: $3.UpdateConversationRequest.create)
    ..aOM<$4.UpdateTypingStatusRequest>(
        7, _omitFieldNames ? '' : 'updateTypingStatusRequest',
        subBuilder: $4.UpdateTypingStatusRequest.create)
    ..aOM<$5.CreateMessageRequest>(
        8, _omitFieldNames ? '' : 'createMessageRequest',
        subBuilder: $5.CreateMessageRequest.create)
    ..aOM<$6.QueryMessagesRequest>(
        9, _omitFieldNames ? '' : 'queryMessagesRequest',
        subBuilder: $6.QueryMessagesRequest.create)
    ..aOM<$7.UpdateMessageRequest>(
        10, _omitFieldNames ? '' : 'updateMessageRequest',
        subBuilder: $7.UpdateMessageRequest.create)
    ..aOM<$8.CreateGroupMembersRequest>(
        11, _omitFieldNames ? '' : 'createGroupMembersRequest',
        subBuilder: $8.CreateGroupMembersRequest.create)
    ..aOM<$9.DeleteGroupMembersRequest>(
        12, _omitFieldNames ? '' : 'deleteGroupMembersRequest',
        subBuilder: $9.DeleteGroupMembersRequest.create)
    ..aOM<$10.QueryGroupMembersRequest>(
        13, _omitFieldNames ? '' : 'queryGroupMembersRequest',
        subBuilder: $10.QueryGroupMembersRequest.create)
    ..aOM<$11.UpdateGroupMemberRequest>(
        14, _omitFieldNames ? '' : 'updateGroupMemberRequest',
        subBuilder: $11.UpdateGroupMemberRequest.create)
    ..aOM<$12.QueryUserProfilesRequest>(
        100, _omitFieldNames ? '' : 'queryUserProfilesRequest',
        subBuilder: $12.QueryUserProfilesRequest.create)
    ..aOM<$13.QueryNearbyUsersRequest>(
        101, _omitFieldNames ? '' : 'queryNearbyUsersRequest',
        subBuilder: $13.QueryNearbyUsersRequest.create)
    ..aOM<$14.QueryUserOnlineStatusesRequest>(
        102, _omitFieldNames ? '' : 'queryUserOnlineStatusesRequest',
        subBuilder: $14.QueryUserOnlineStatusesRequest.create)
    ..aOM<$15.UpdateUserLocationRequest>(
        103, _omitFieldNames ? '' : 'updateUserLocationRequest',
        subBuilder: $15.UpdateUserLocationRequest.create)
    ..aOM<$16.UpdateUserOnlineStatusRequest>(
        104, _omitFieldNames ? '' : 'updateUserOnlineStatusRequest',
        subBuilder: $16.UpdateUserOnlineStatusRequest.create)
    ..aOM<$17.UpdateUserRequest>(
        105, _omitFieldNames ? '' : 'updateUserRequest',
        subBuilder: $17.UpdateUserRequest.create)
    ..aOM<$18.CreateFriendRequestRequest>(
        200, _omitFieldNames ? '' : 'createFriendRequestRequest',
        subBuilder: $18.CreateFriendRequestRequest.create)
    ..aOM<$19.CreateRelationshipGroupRequest>(
        201, _omitFieldNames ? '' : 'createRelationshipGroupRequest',
        subBuilder: $19.CreateRelationshipGroupRequest.create)
    ..aOM<$20.CreateRelationshipRequest>(
        202, _omitFieldNames ? '' : 'createRelationshipRequest',
        subBuilder: $20.CreateRelationshipRequest.create)
    ..aOM<$21.DeleteFriendRequestRequest>(
        203, _omitFieldNames ? '' : 'deleteFriendRequestRequest',
        subBuilder: $21.DeleteFriendRequestRequest.create)
    ..aOM<$22.DeleteRelationshipGroupRequest>(
        204, _omitFieldNames ? '' : 'deleteRelationshipGroupRequest',
        subBuilder: $22.DeleteRelationshipGroupRequest.create)
    ..aOM<$23.DeleteRelationshipRequest>(
        205, _omitFieldNames ? '' : 'deleteRelationshipRequest',
        subBuilder: $23.DeleteRelationshipRequest.create)
    ..aOM<$24.QueryFriendRequestsRequest>(
        206, _omitFieldNames ? '' : 'queryFriendRequestsRequest',
        subBuilder: $24.QueryFriendRequestsRequest.create)
    ..aOM<$25.QueryRelatedUserIdsRequest>(
        207, _omitFieldNames ? '' : 'queryRelatedUserIdsRequest',
        subBuilder: $25.QueryRelatedUserIdsRequest.create)
    ..aOM<$26.QueryRelationshipGroupsRequest>(
        208, _omitFieldNames ? '' : 'queryRelationshipGroupsRequest',
        subBuilder: $26.QueryRelationshipGroupsRequest.create)
    ..aOM<$27.QueryRelationshipsRequest>(
        209, _omitFieldNames ? '' : 'queryRelationshipsRequest',
        subBuilder: $27.QueryRelationshipsRequest.create)
    ..aOM<$28.UpdateFriendRequestRequest>(
        210, _omitFieldNames ? '' : 'updateFriendRequestRequest',
        subBuilder: $28.UpdateFriendRequestRequest.create)
    ..aOM<$29.UpdateRelationshipGroupRequest>(
        211, _omitFieldNames ? '' : 'updateRelationshipGroupRequest',
        subBuilder: $29.UpdateRelationshipGroupRequest.create)
    ..aOM<$30.UpdateRelationshipRequest>(
        212, _omitFieldNames ? '' : 'updateRelationshipRequest',
        subBuilder: $30.UpdateRelationshipRequest.create)
    ..aOM<$31.CreateGroupRequest>(
        300, _omitFieldNames ? '' : 'createGroupRequest',
        subBuilder: $31.CreateGroupRequest.create)
    ..aOM<$32.DeleteGroupRequest>(
        301, _omitFieldNames ? '' : 'deleteGroupRequest',
        subBuilder: $32.DeleteGroupRequest.create)
    ..aOM<$33.QueryGroupsRequest>(
        302, _omitFieldNames ? '' : 'queryGroupsRequest',
        subBuilder: $33.QueryGroupsRequest.create)
    ..aOM<$34.QueryJoinedGroupIdsRequest>(
        303, _omitFieldNames ? '' : 'queryJoinedGroupIdsRequest',
        subBuilder: $34.QueryJoinedGroupIdsRequest.create)
    ..aOM<$35.QueryJoinedGroupInfosRequest>(
        304, _omitFieldNames ? '' : 'queryJoinedGroupInfosRequest',
        subBuilder: $35.QueryJoinedGroupInfosRequest.create)
    ..aOM<$36.UpdateGroupRequest>(
        305, _omitFieldNames ? '' : 'updateGroupRequest',
        subBuilder: $36.UpdateGroupRequest.create)
    ..aOM<$37.CreateGroupBlockedUserRequest>(
        400, _omitFieldNames ? '' : 'createGroupBlockedUserRequest',
        subBuilder: $37.CreateGroupBlockedUserRequest.create)
    ..aOM<$38.DeleteGroupBlockedUserRequest>(
        401, _omitFieldNames ? '' : 'deleteGroupBlockedUserRequest',
        subBuilder: $38.DeleteGroupBlockedUserRequest.create)
    ..aOM<$39.QueryGroupBlockedUserIdsRequest>(
        402, _omitFieldNames ? '' : 'queryGroupBlockedUserIdsRequest',
        subBuilder: $39.QueryGroupBlockedUserIdsRequest.create)
    ..aOM<$40.QueryGroupBlockedUserInfosRequest>(
        403, _omitFieldNames ? '' : 'queryGroupBlockedUserInfosRequest',
        subBuilder: $40.QueryGroupBlockedUserInfosRequest.create)
    ..aOM<$41.CheckGroupJoinQuestionsAnswersRequest>(
        500, _omitFieldNames ? '' : 'checkGroupJoinQuestionsAnswersRequest',
        subBuilder: $41.CheckGroupJoinQuestionsAnswersRequest.create)
    ..aOM<$42.CreateGroupInvitationRequest>(
        501, _omitFieldNames ? '' : 'createGroupInvitationRequest',
        subBuilder: $42.CreateGroupInvitationRequest.create)
    ..aOM<$43.CreateGroupJoinRequestRequest>(
        502, _omitFieldNames ? '' : 'createGroupJoinRequestRequest',
        subBuilder: $43.CreateGroupJoinRequestRequest.create)
    ..aOM<$44.CreateGroupJoinQuestionsRequest>(
        503, _omitFieldNames ? '' : 'createGroupJoinQuestionsRequest',
        subBuilder: $44.CreateGroupJoinQuestionsRequest.create)
    ..aOM<$45.DeleteGroupInvitationRequest>(
        504, _omitFieldNames ? '' : 'deleteGroupInvitationRequest',
        subBuilder: $45.DeleteGroupInvitationRequest.create)
    ..aOM<$46.DeleteGroupJoinRequestRequest>(
        505, _omitFieldNames ? '' : 'deleteGroupJoinRequestRequest',
        subBuilder: $46.DeleteGroupJoinRequestRequest.create)
    ..aOM<$47.DeleteGroupJoinQuestionsRequest>(
        506, _omitFieldNames ? '' : 'deleteGroupJoinQuestionsRequest',
        subBuilder: $47.DeleteGroupJoinQuestionsRequest.create)
    ..aOM<$48.QueryGroupInvitationsRequest>(
        507, _omitFieldNames ? '' : 'queryGroupInvitationsRequest',
        subBuilder: $48.QueryGroupInvitationsRequest.create)
    ..aOM<$49.QueryGroupJoinRequestsRequest>(
        508, _omitFieldNames ? '' : 'queryGroupJoinRequestsRequest',
        subBuilder: $49.QueryGroupJoinRequestsRequest.create)
    ..aOM<$50.QueryGroupJoinQuestionsRequest>(
        509, _omitFieldNames ? '' : 'queryGroupJoinQuestionsRequest',
        subBuilder: $50.QueryGroupJoinQuestionsRequest.create)
    ..aOM<$51.UpdateGroupInvitationRequest>(
        510, _omitFieldNames ? '' : 'updateGroupInvitationRequest',
        subBuilder: $51.UpdateGroupInvitationRequest.create)
    ..aOM<$52.UpdateGroupJoinQuestionRequest>(
        511, _omitFieldNames ? '' : 'updateGroupJoinQuestionRequest',
        subBuilder: $52.UpdateGroupJoinQuestionRequest.create)
    ..aOM<$53.UpdateGroupJoinRequestRequest>(
        512, _omitFieldNames ? '' : 'updateGroupJoinRequestRequest',
        subBuilder: $53.UpdateGroupJoinRequestRequest.create)
    ..aOM<$54.DeleteResourceRequest>(
        1000, _omitFieldNames ? '' : 'deleteResourceRequest',
        subBuilder: $54.DeleteResourceRequest.create)
    ..aOM<$55.QueryResourceDownloadInfoRequest>(
        1001, _omitFieldNames ? '' : 'queryResourceDownloadInfoRequest',
        subBuilder: $55.QueryResourceDownloadInfoRequest.create)
    ..aOM<$56.QueryResourceUploadInfoRequest>(
        1002, _omitFieldNames ? '' : 'queryResourceUploadInfoRequest',
        subBuilder: $56.QueryResourceUploadInfoRequest.create)
    ..aOM<$57.QueryMessageAttachmentInfosRequest>(
        1003, _omitFieldNames ? '' : 'queryMessageAttachmentInfosRequest',
        subBuilder: $57.QueryMessageAttachmentInfosRequest.create)
    ..aOM<$58.UpdateMessageAttachmentInfoRequest>(
        1004, _omitFieldNames ? '' : 'updateMessageAttachmentInfoRequest',
        subBuilder: $58.UpdateMessageAttachmentInfoRequest.create)
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

  /// User - Session
  @$pb.TagNumber(3)
  $0.CreateSessionRequest get createSessionRequest => $_getN(1);
  @$pb.TagNumber(3)
  set createSessionRequest($0.CreateSessionRequest v) {
    setField(3, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasCreateSessionRequest() => $_has(1);
  @$pb.TagNumber(3)
  void clearCreateSessionRequest() => clearField(3);
  @$pb.TagNumber(3)
  $0.CreateSessionRequest ensureCreateSessionRequest() => $_ensure(1);

  @$pb.TagNumber(4)
  $1.DeleteSessionRequest get deleteSessionRequest => $_getN(2);
  @$pb.TagNumber(4)
  set deleteSessionRequest($1.DeleteSessionRequest v) {
    setField(4, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasDeleteSessionRequest() => $_has(2);
  @$pb.TagNumber(4)
  void clearDeleteSessionRequest() => clearField(4);
  @$pb.TagNumber(4)
  $1.DeleteSessionRequest ensureDeleteSessionRequest() => $_ensure(2);

  /// Conversation
  @$pb.TagNumber(5)
  $2.QueryConversationsRequest get queryConversationsRequest => $_getN(3);
  @$pb.TagNumber(5)
  set queryConversationsRequest($2.QueryConversationsRequest v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasQueryConversationsRequest() => $_has(3);
  @$pb.TagNumber(5)
  void clearQueryConversationsRequest() => clearField(5);
  @$pb.TagNumber(5)
  $2.QueryConversationsRequest ensureQueryConversationsRequest() => $_ensure(3);

  @$pb.TagNumber(6)
  $3.UpdateConversationRequest get updateConversationRequest => $_getN(4);
  @$pb.TagNumber(6)
  set updateConversationRequest($3.UpdateConversationRequest v) {
    setField(6, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasUpdateConversationRequest() => $_has(4);
  @$pb.TagNumber(6)
  void clearUpdateConversationRequest() => clearField(6);
  @$pb.TagNumber(6)
  $3.UpdateConversationRequest ensureUpdateConversationRequest() => $_ensure(4);

  @$pb.TagNumber(7)
  $4.UpdateTypingStatusRequest get updateTypingStatusRequest => $_getN(5);
  @$pb.TagNumber(7)
  set updateTypingStatusRequest($4.UpdateTypingStatusRequest v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasUpdateTypingStatusRequest() => $_has(5);
  @$pb.TagNumber(7)
  void clearUpdateTypingStatusRequest() => clearField(7);
  @$pb.TagNumber(7)
  $4.UpdateTypingStatusRequest ensureUpdateTypingStatusRequest() => $_ensure(5);

  /// Message
  @$pb.TagNumber(8)
  $5.CreateMessageRequest get createMessageRequest => $_getN(6);
  @$pb.TagNumber(8)
  set createMessageRequest($5.CreateMessageRequest v) {
    setField(8, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasCreateMessageRequest() => $_has(6);
  @$pb.TagNumber(8)
  void clearCreateMessageRequest() => clearField(8);
  @$pb.TagNumber(8)
  $5.CreateMessageRequest ensureCreateMessageRequest() => $_ensure(6);

  @$pb.TagNumber(9)
  $6.QueryMessagesRequest get queryMessagesRequest => $_getN(7);
  @$pb.TagNumber(9)
  set queryMessagesRequest($6.QueryMessagesRequest v) {
    setField(9, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasQueryMessagesRequest() => $_has(7);
  @$pb.TagNumber(9)
  void clearQueryMessagesRequest() => clearField(9);
  @$pb.TagNumber(9)
  $6.QueryMessagesRequest ensureQueryMessagesRequest() => $_ensure(7);

  @$pb.TagNumber(10)
  $7.UpdateMessageRequest get updateMessageRequest => $_getN(8);
  @$pb.TagNumber(10)
  set updateMessageRequest($7.UpdateMessageRequest v) {
    setField(10, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasUpdateMessageRequest() => $_has(8);
  @$pb.TagNumber(10)
  void clearUpdateMessageRequest() => clearField(10);
  @$pb.TagNumber(10)
  $7.UpdateMessageRequest ensureUpdateMessageRequest() => $_ensure(8);

  /// Group Member
  @$pb.TagNumber(11)
  $8.CreateGroupMembersRequest get createGroupMembersRequest => $_getN(9);
  @$pb.TagNumber(11)
  set createGroupMembersRequest($8.CreateGroupMembersRequest v) {
    setField(11, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasCreateGroupMembersRequest() => $_has(9);
  @$pb.TagNumber(11)
  void clearCreateGroupMembersRequest() => clearField(11);
  @$pb.TagNumber(11)
  $8.CreateGroupMembersRequest ensureCreateGroupMembersRequest() => $_ensure(9);

  @$pb.TagNumber(12)
  $9.DeleteGroupMembersRequest get deleteGroupMembersRequest => $_getN(10);
  @$pb.TagNumber(12)
  set deleteGroupMembersRequest($9.DeleteGroupMembersRequest v) {
    setField(12, v);
  }

  @$pb.TagNumber(12)
  $core.bool hasDeleteGroupMembersRequest() => $_has(10);
  @$pb.TagNumber(12)
  void clearDeleteGroupMembersRequest() => clearField(12);
  @$pb.TagNumber(12)
  $9.DeleteGroupMembersRequest ensureDeleteGroupMembersRequest() =>
      $_ensure(10);

  @$pb.TagNumber(13)
  $10.QueryGroupMembersRequest get queryGroupMembersRequest => $_getN(11);
  @$pb.TagNumber(13)
  set queryGroupMembersRequest($10.QueryGroupMembersRequest v) {
    setField(13, v);
  }

  @$pb.TagNumber(13)
  $core.bool hasQueryGroupMembersRequest() => $_has(11);
  @$pb.TagNumber(13)
  void clearQueryGroupMembersRequest() => clearField(13);
  @$pb.TagNumber(13)
  $10.QueryGroupMembersRequest ensureQueryGroupMembersRequest() => $_ensure(11);

  @$pb.TagNumber(14)
  $11.UpdateGroupMemberRequest get updateGroupMemberRequest => $_getN(12);
  @$pb.TagNumber(14)
  set updateGroupMemberRequest($11.UpdateGroupMemberRequest v) {
    setField(14, v);
  }

  @$pb.TagNumber(14)
  $core.bool hasUpdateGroupMemberRequest() => $_has(12);
  @$pb.TagNumber(14)
  void clearUpdateGroupMemberRequest() => clearField(14);
  @$pb.TagNumber(14)
  $11.UpdateGroupMemberRequest ensureUpdateGroupMemberRequest() => $_ensure(12);

  /// User
  @$pb.TagNumber(100)
  $12.QueryUserProfilesRequest get queryUserProfilesRequest => $_getN(13);
  @$pb.TagNumber(100)
  set queryUserProfilesRequest($12.QueryUserProfilesRequest v) {
    setField(100, v);
  }

  @$pb.TagNumber(100)
  $core.bool hasQueryUserProfilesRequest() => $_has(13);
  @$pb.TagNumber(100)
  void clearQueryUserProfilesRequest() => clearField(100);
  @$pb.TagNumber(100)
  $12.QueryUserProfilesRequest ensureQueryUserProfilesRequest() => $_ensure(13);

  @$pb.TagNumber(101)
  $13.QueryNearbyUsersRequest get queryNearbyUsersRequest => $_getN(14);
  @$pb.TagNumber(101)
  set queryNearbyUsersRequest($13.QueryNearbyUsersRequest v) {
    setField(101, v);
  }

  @$pb.TagNumber(101)
  $core.bool hasQueryNearbyUsersRequest() => $_has(14);
  @$pb.TagNumber(101)
  void clearQueryNearbyUsersRequest() => clearField(101);
  @$pb.TagNumber(101)
  $13.QueryNearbyUsersRequest ensureQueryNearbyUsersRequest() => $_ensure(14);

  @$pb.TagNumber(102)
  $14.QueryUserOnlineStatusesRequest get queryUserOnlineStatusesRequest =>
      $_getN(15);
  @$pb.TagNumber(102)
  set queryUserOnlineStatusesRequest($14.QueryUserOnlineStatusesRequest v) {
    setField(102, v);
  }

  @$pb.TagNumber(102)
  $core.bool hasQueryUserOnlineStatusesRequest() => $_has(15);
  @$pb.TagNumber(102)
  void clearQueryUserOnlineStatusesRequest() => clearField(102);
  @$pb.TagNumber(102)
  $14.QueryUserOnlineStatusesRequest ensureQueryUserOnlineStatusesRequest() =>
      $_ensure(15);

  @$pb.TagNumber(103)
  $15.UpdateUserLocationRequest get updateUserLocationRequest => $_getN(16);
  @$pb.TagNumber(103)
  set updateUserLocationRequest($15.UpdateUserLocationRequest v) {
    setField(103, v);
  }

  @$pb.TagNumber(103)
  $core.bool hasUpdateUserLocationRequest() => $_has(16);
  @$pb.TagNumber(103)
  void clearUpdateUserLocationRequest() => clearField(103);
  @$pb.TagNumber(103)
  $15.UpdateUserLocationRequest ensureUpdateUserLocationRequest() =>
      $_ensure(16);

  @$pb.TagNumber(104)
  $16.UpdateUserOnlineStatusRequest get updateUserOnlineStatusRequest =>
      $_getN(17);
  @$pb.TagNumber(104)
  set updateUserOnlineStatusRequest($16.UpdateUserOnlineStatusRequest v) {
    setField(104, v);
  }

  @$pb.TagNumber(104)
  $core.bool hasUpdateUserOnlineStatusRequest() => $_has(17);
  @$pb.TagNumber(104)
  void clearUpdateUserOnlineStatusRequest() => clearField(104);
  @$pb.TagNumber(104)
  $16.UpdateUserOnlineStatusRequest ensureUpdateUserOnlineStatusRequest() =>
      $_ensure(17);

  @$pb.TagNumber(105)
  $17.UpdateUserRequest get updateUserRequest => $_getN(18);
  @$pb.TagNumber(105)
  set updateUserRequest($17.UpdateUserRequest v) {
    setField(105, v);
  }

  @$pb.TagNumber(105)
  $core.bool hasUpdateUserRequest() => $_has(18);
  @$pb.TagNumber(105)
  void clearUpdateUserRequest() => clearField(105);
  @$pb.TagNumber(105)
  $17.UpdateUserRequest ensureUpdateUserRequest() => $_ensure(18);

  /// User Relationship
  @$pb.TagNumber(200)
  $18.CreateFriendRequestRequest get createFriendRequestRequest => $_getN(19);
  @$pb.TagNumber(200)
  set createFriendRequestRequest($18.CreateFriendRequestRequest v) {
    setField(200, v);
  }

  @$pb.TagNumber(200)
  $core.bool hasCreateFriendRequestRequest() => $_has(19);
  @$pb.TagNumber(200)
  void clearCreateFriendRequestRequest() => clearField(200);
  @$pb.TagNumber(200)
  $18.CreateFriendRequestRequest ensureCreateFriendRequestRequest() =>
      $_ensure(19);

  @$pb.TagNumber(201)
  $19.CreateRelationshipGroupRequest get createRelationshipGroupRequest =>
      $_getN(20);
  @$pb.TagNumber(201)
  set createRelationshipGroupRequest($19.CreateRelationshipGroupRequest v) {
    setField(201, v);
  }

  @$pb.TagNumber(201)
  $core.bool hasCreateRelationshipGroupRequest() => $_has(20);
  @$pb.TagNumber(201)
  void clearCreateRelationshipGroupRequest() => clearField(201);
  @$pb.TagNumber(201)
  $19.CreateRelationshipGroupRequest ensureCreateRelationshipGroupRequest() =>
      $_ensure(20);

  @$pb.TagNumber(202)
  $20.CreateRelationshipRequest get createRelationshipRequest => $_getN(21);
  @$pb.TagNumber(202)
  set createRelationshipRequest($20.CreateRelationshipRequest v) {
    setField(202, v);
  }

  @$pb.TagNumber(202)
  $core.bool hasCreateRelationshipRequest() => $_has(21);
  @$pb.TagNumber(202)
  void clearCreateRelationshipRequest() => clearField(202);
  @$pb.TagNumber(202)
  $20.CreateRelationshipRequest ensureCreateRelationshipRequest() =>
      $_ensure(21);

  @$pb.TagNumber(203)
  $21.DeleteFriendRequestRequest get deleteFriendRequestRequest => $_getN(22);
  @$pb.TagNumber(203)
  set deleteFriendRequestRequest($21.DeleteFriendRequestRequest v) {
    setField(203, v);
  }

  @$pb.TagNumber(203)
  $core.bool hasDeleteFriendRequestRequest() => $_has(22);
  @$pb.TagNumber(203)
  void clearDeleteFriendRequestRequest() => clearField(203);
  @$pb.TagNumber(203)
  $21.DeleteFriendRequestRequest ensureDeleteFriendRequestRequest() =>
      $_ensure(22);

  @$pb.TagNumber(204)
  $22.DeleteRelationshipGroupRequest get deleteRelationshipGroupRequest =>
      $_getN(23);
  @$pb.TagNumber(204)
  set deleteRelationshipGroupRequest($22.DeleteRelationshipGroupRequest v) {
    setField(204, v);
  }

  @$pb.TagNumber(204)
  $core.bool hasDeleteRelationshipGroupRequest() => $_has(23);
  @$pb.TagNumber(204)
  void clearDeleteRelationshipGroupRequest() => clearField(204);
  @$pb.TagNumber(204)
  $22.DeleteRelationshipGroupRequest ensureDeleteRelationshipGroupRequest() =>
      $_ensure(23);

  @$pb.TagNumber(205)
  $23.DeleteRelationshipRequest get deleteRelationshipRequest => $_getN(24);
  @$pb.TagNumber(205)
  set deleteRelationshipRequest($23.DeleteRelationshipRequest v) {
    setField(205, v);
  }

  @$pb.TagNumber(205)
  $core.bool hasDeleteRelationshipRequest() => $_has(24);
  @$pb.TagNumber(205)
  void clearDeleteRelationshipRequest() => clearField(205);
  @$pb.TagNumber(205)
  $23.DeleteRelationshipRequest ensureDeleteRelationshipRequest() =>
      $_ensure(24);

  @$pb.TagNumber(206)
  $24.QueryFriendRequestsRequest get queryFriendRequestsRequest => $_getN(25);
  @$pb.TagNumber(206)
  set queryFriendRequestsRequest($24.QueryFriendRequestsRequest v) {
    setField(206, v);
  }

  @$pb.TagNumber(206)
  $core.bool hasQueryFriendRequestsRequest() => $_has(25);
  @$pb.TagNumber(206)
  void clearQueryFriendRequestsRequest() => clearField(206);
  @$pb.TagNumber(206)
  $24.QueryFriendRequestsRequest ensureQueryFriendRequestsRequest() =>
      $_ensure(25);

  @$pb.TagNumber(207)
  $25.QueryRelatedUserIdsRequest get queryRelatedUserIdsRequest => $_getN(26);
  @$pb.TagNumber(207)
  set queryRelatedUserIdsRequest($25.QueryRelatedUserIdsRequest v) {
    setField(207, v);
  }

  @$pb.TagNumber(207)
  $core.bool hasQueryRelatedUserIdsRequest() => $_has(26);
  @$pb.TagNumber(207)
  void clearQueryRelatedUserIdsRequest() => clearField(207);
  @$pb.TagNumber(207)
  $25.QueryRelatedUserIdsRequest ensureQueryRelatedUserIdsRequest() =>
      $_ensure(26);

  @$pb.TagNumber(208)
  $26.QueryRelationshipGroupsRequest get queryRelationshipGroupsRequest =>
      $_getN(27);
  @$pb.TagNumber(208)
  set queryRelationshipGroupsRequest($26.QueryRelationshipGroupsRequest v) {
    setField(208, v);
  }

  @$pb.TagNumber(208)
  $core.bool hasQueryRelationshipGroupsRequest() => $_has(27);
  @$pb.TagNumber(208)
  void clearQueryRelationshipGroupsRequest() => clearField(208);
  @$pb.TagNumber(208)
  $26.QueryRelationshipGroupsRequest ensureQueryRelationshipGroupsRequest() =>
      $_ensure(27);

  @$pb.TagNumber(209)
  $27.QueryRelationshipsRequest get queryRelationshipsRequest => $_getN(28);
  @$pb.TagNumber(209)
  set queryRelationshipsRequest($27.QueryRelationshipsRequest v) {
    setField(209, v);
  }

  @$pb.TagNumber(209)
  $core.bool hasQueryRelationshipsRequest() => $_has(28);
  @$pb.TagNumber(209)
  void clearQueryRelationshipsRequest() => clearField(209);
  @$pb.TagNumber(209)
  $27.QueryRelationshipsRequest ensureQueryRelationshipsRequest() =>
      $_ensure(28);

  @$pb.TagNumber(210)
  $28.UpdateFriendRequestRequest get updateFriendRequestRequest => $_getN(29);
  @$pb.TagNumber(210)
  set updateFriendRequestRequest($28.UpdateFriendRequestRequest v) {
    setField(210, v);
  }

  @$pb.TagNumber(210)
  $core.bool hasUpdateFriendRequestRequest() => $_has(29);
  @$pb.TagNumber(210)
  void clearUpdateFriendRequestRequest() => clearField(210);
  @$pb.TagNumber(210)
  $28.UpdateFriendRequestRequest ensureUpdateFriendRequestRequest() =>
      $_ensure(29);

  @$pb.TagNumber(211)
  $29.UpdateRelationshipGroupRequest get updateRelationshipGroupRequest =>
      $_getN(30);
  @$pb.TagNumber(211)
  set updateRelationshipGroupRequest($29.UpdateRelationshipGroupRequest v) {
    setField(211, v);
  }

  @$pb.TagNumber(211)
  $core.bool hasUpdateRelationshipGroupRequest() => $_has(30);
  @$pb.TagNumber(211)
  void clearUpdateRelationshipGroupRequest() => clearField(211);
  @$pb.TagNumber(211)
  $29.UpdateRelationshipGroupRequest ensureUpdateRelationshipGroupRequest() =>
      $_ensure(30);

  @$pb.TagNumber(212)
  $30.UpdateRelationshipRequest get updateRelationshipRequest => $_getN(31);
  @$pb.TagNumber(212)
  set updateRelationshipRequest($30.UpdateRelationshipRequest v) {
    setField(212, v);
  }

  @$pb.TagNumber(212)
  $core.bool hasUpdateRelationshipRequest() => $_has(31);
  @$pb.TagNumber(212)
  void clearUpdateRelationshipRequest() => clearField(212);
  @$pb.TagNumber(212)
  $30.UpdateRelationshipRequest ensureUpdateRelationshipRequest() =>
      $_ensure(31);

  /// Group
  @$pb.TagNumber(300)
  $31.CreateGroupRequest get createGroupRequest => $_getN(32);
  @$pb.TagNumber(300)
  set createGroupRequest($31.CreateGroupRequest v) {
    setField(300, v);
  }

  @$pb.TagNumber(300)
  $core.bool hasCreateGroupRequest() => $_has(32);
  @$pb.TagNumber(300)
  void clearCreateGroupRequest() => clearField(300);
  @$pb.TagNumber(300)
  $31.CreateGroupRequest ensureCreateGroupRequest() => $_ensure(32);

  @$pb.TagNumber(301)
  $32.DeleteGroupRequest get deleteGroupRequest => $_getN(33);
  @$pb.TagNumber(301)
  set deleteGroupRequest($32.DeleteGroupRequest v) {
    setField(301, v);
  }

  @$pb.TagNumber(301)
  $core.bool hasDeleteGroupRequest() => $_has(33);
  @$pb.TagNumber(301)
  void clearDeleteGroupRequest() => clearField(301);
  @$pb.TagNumber(301)
  $32.DeleteGroupRequest ensureDeleteGroupRequest() => $_ensure(33);

  @$pb.TagNumber(302)
  $33.QueryGroupsRequest get queryGroupsRequest => $_getN(34);
  @$pb.TagNumber(302)
  set queryGroupsRequest($33.QueryGroupsRequest v) {
    setField(302, v);
  }

  @$pb.TagNumber(302)
  $core.bool hasQueryGroupsRequest() => $_has(34);
  @$pb.TagNumber(302)
  void clearQueryGroupsRequest() => clearField(302);
  @$pb.TagNumber(302)
  $33.QueryGroupsRequest ensureQueryGroupsRequest() => $_ensure(34);

  @$pb.TagNumber(303)
  $34.QueryJoinedGroupIdsRequest get queryJoinedGroupIdsRequest => $_getN(35);
  @$pb.TagNumber(303)
  set queryJoinedGroupIdsRequest($34.QueryJoinedGroupIdsRequest v) {
    setField(303, v);
  }

  @$pb.TagNumber(303)
  $core.bool hasQueryJoinedGroupIdsRequest() => $_has(35);
  @$pb.TagNumber(303)
  void clearQueryJoinedGroupIdsRequest() => clearField(303);
  @$pb.TagNumber(303)
  $34.QueryJoinedGroupIdsRequest ensureQueryJoinedGroupIdsRequest() =>
      $_ensure(35);

  @$pb.TagNumber(304)
  $35.QueryJoinedGroupInfosRequest get queryJoinedGroupInfosRequest =>
      $_getN(36);
  @$pb.TagNumber(304)
  set queryJoinedGroupInfosRequest($35.QueryJoinedGroupInfosRequest v) {
    setField(304, v);
  }

  @$pb.TagNumber(304)
  $core.bool hasQueryJoinedGroupInfosRequest() => $_has(36);
  @$pb.TagNumber(304)
  void clearQueryJoinedGroupInfosRequest() => clearField(304);
  @$pb.TagNumber(304)
  $35.QueryJoinedGroupInfosRequest ensureQueryJoinedGroupInfosRequest() =>
      $_ensure(36);

  @$pb.TagNumber(305)
  $36.UpdateGroupRequest get updateGroupRequest => $_getN(37);
  @$pb.TagNumber(305)
  set updateGroupRequest($36.UpdateGroupRequest v) {
    setField(305, v);
  }

  @$pb.TagNumber(305)
  $core.bool hasUpdateGroupRequest() => $_has(37);
  @$pb.TagNumber(305)
  void clearUpdateGroupRequest() => clearField(305);
  @$pb.TagNumber(305)
  $36.UpdateGroupRequest ensureUpdateGroupRequest() => $_ensure(37);

  /// Group Blocklist
  @$pb.TagNumber(400)
  $37.CreateGroupBlockedUserRequest get createGroupBlockedUserRequest =>
      $_getN(38);
  @$pb.TagNumber(400)
  set createGroupBlockedUserRequest($37.CreateGroupBlockedUserRequest v) {
    setField(400, v);
  }

  @$pb.TagNumber(400)
  $core.bool hasCreateGroupBlockedUserRequest() => $_has(38);
  @$pb.TagNumber(400)
  void clearCreateGroupBlockedUserRequest() => clearField(400);
  @$pb.TagNumber(400)
  $37.CreateGroupBlockedUserRequest ensureCreateGroupBlockedUserRequest() =>
      $_ensure(38);

  @$pb.TagNumber(401)
  $38.DeleteGroupBlockedUserRequest get deleteGroupBlockedUserRequest =>
      $_getN(39);
  @$pb.TagNumber(401)
  set deleteGroupBlockedUserRequest($38.DeleteGroupBlockedUserRequest v) {
    setField(401, v);
  }

  @$pb.TagNumber(401)
  $core.bool hasDeleteGroupBlockedUserRequest() => $_has(39);
  @$pb.TagNumber(401)
  void clearDeleteGroupBlockedUserRequest() => clearField(401);
  @$pb.TagNumber(401)
  $38.DeleteGroupBlockedUserRequest ensureDeleteGroupBlockedUserRequest() =>
      $_ensure(39);

  @$pb.TagNumber(402)
  $39.QueryGroupBlockedUserIdsRequest get queryGroupBlockedUserIdsRequest =>
      $_getN(40);
  @$pb.TagNumber(402)
  set queryGroupBlockedUserIdsRequest($39.QueryGroupBlockedUserIdsRequest v) {
    setField(402, v);
  }

  @$pb.TagNumber(402)
  $core.bool hasQueryGroupBlockedUserIdsRequest() => $_has(40);
  @$pb.TagNumber(402)
  void clearQueryGroupBlockedUserIdsRequest() => clearField(402);
  @$pb.TagNumber(402)
  $39.QueryGroupBlockedUserIdsRequest ensureQueryGroupBlockedUserIdsRequest() =>
      $_ensure(40);

  @$pb.TagNumber(403)
  $40.QueryGroupBlockedUserInfosRequest get queryGroupBlockedUserInfosRequest =>
      $_getN(41);
  @$pb.TagNumber(403)
  set queryGroupBlockedUserInfosRequest(
      $40.QueryGroupBlockedUserInfosRequest v) {
    setField(403, v);
  }

  @$pb.TagNumber(403)
  $core.bool hasQueryGroupBlockedUserInfosRequest() => $_has(41);
  @$pb.TagNumber(403)
  void clearQueryGroupBlockedUserInfosRequest() => clearField(403);
  @$pb.TagNumber(403)
  $40.QueryGroupBlockedUserInfosRequest
      ensureQueryGroupBlockedUserInfosRequest() => $_ensure(41);

  /// Group Enrollment
  @$pb.TagNumber(500)
  $41.CheckGroupJoinQuestionsAnswersRequest
      get checkGroupJoinQuestionsAnswersRequest => $_getN(42);
  @$pb.TagNumber(500)
  set checkGroupJoinQuestionsAnswersRequest(
      $41.CheckGroupJoinQuestionsAnswersRequest v) {
    setField(500, v);
  }

  @$pb.TagNumber(500)
  $core.bool hasCheckGroupJoinQuestionsAnswersRequest() => $_has(42);
  @$pb.TagNumber(500)
  void clearCheckGroupJoinQuestionsAnswersRequest() => clearField(500);
  @$pb.TagNumber(500)
  $41.CheckGroupJoinQuestionsAnswersRequest
      ensureCheckGroupJoinQuestionsAnswersRequest() => $_ensure(42);

  @$pb.TagNumber(501)
  $42.CreateGroupInvitationRequest get createGroupInvitationRequest =>
      $_getN(43);
  @$pb.TagNumber(501)
  set createGroupInvitationRequest($42.CreateGroupInvitationRequest v) {
    setField(501, v);
  }

  @$pb.TagNumber(501)
  $core.bool hasCreateGroupInvitationRequest() => $_has(43);
  @$pb.TagNumber(501)
  void clearCreateGroupInvitationRequest() => clearField(501);
  @$pb.TagNumber(501)
  $42.CreateGroupInvitationRequest ensureCreateGroupInvitationRequest() =>
      $_ensure(43);

  @$pb.TagNumber(502)
  $43.CreateGroupJoinRequestRequest get createGroupJoinRequestRequest =>
      $_getN(44);
  @$pb.TagNumber(502)
  set createGroupJoinRequestRequest($43.CreateGroupJoinRequestRequest v) {
    setField(502, v);
  }

  @$pb.TagNumber(502)
  $core.bool hasCreateGroupJoinRequestRequest() => $_has(44);
  @$pb.TagNumber(502)
  void clearCreateGroupJoinRequestRequest() => clearField(502);
  @$pb.TagNumber(502)
  $43.CreateGroupJoinRequestRequest ensureCreateGroupJoinRequestRequest() =>
      $_ensure(44);

  @$pb.TagNumber(503)
  $44.CreateGroupJoinQuestionsRequest get createGroupJoinQuestionsRequest =>
      $_getN(45);
  @$pb.TagNumber(503)
  set createGroupJoinQuestionsRequest($44.CreateGroupJoinQuestionsRequest v) {
    setField(503, v);
  }

  @$pb.TagNumber(503)
  $core.bool hasCreateGroupJoinQuestionsRequest() => $_has(45);
  @$pb.TagNumber(503)
  void clearCreateGroupJoinQuestionsRequest() => clearField(503);
  @$pb.TagNumber(503)
  $44.CreateGroupJoinQuestionsRequest ensureCreateGroupJoinQuestionsRequest() =>
      $_ensure(45);

  @$pb.TagNumber(504)
  $45.DeleteGroupInvitationRequest get deleteGroupInvitationRequest =>
      $_getN(46);
  @$pb.TagNumber(504)
  set deleteGroupInvitationRequest($45.DeleteGroupInvitationRequest v) {
    setField(504, v);
  }

  @$pb.TagNumber(504)
  $core.bool hasDeleteGroupInvitationRequest() => $_has(46);
  @$pb.TagNumber(504)
  void clearDeleteGroupInvitationRequest() => clearField(504);
  @$pb.TagNumber(504)
  $45.DeleteGroupInvitationRequest ensureDeleteGroupInvitationRequest() =>
      $_ensure(46);

  @$pb.TagNumber(505)
  $46.DeleteGroupJoinRequestRequest get deleteGroupJoinRequestRequest =>
      $_getN(47);
  @$pb.TagNumber(505)
  set deleteGroupJoinRequestRequest($46.DeleteGroupJoinRequestRequest v) {
    setField(505, v);
  }

  @$pb.TagNumber(505)
  $core.bool hasDeleteGroupJoinRequestRequest() => $_has(47);
  @$pb.TagNumber(505)
  void clearDeleteGroupJoinRequestRequest() => clearField(505);
  @$pb.TagNumber(505)
  $46.DeleteGroupJoinRequestRequest ensureDeleteGroupJoinRequestRequest() =>
      $_ensure(47);

  @$pb.TagNumber(506)
  $47.DeleteGroupJoinQuestionsRequest get deleteGroupJoinQuestionsRequest =>
      $_getN(48);
  @$pb.TagNumber(506)
  set deleteGroupJoinQuestionsRequest($47.DeleteGroupJoinQuestionsRequest v) {
    setField(506, v);
  }

  @$pb.TagNumber(506)
  $core.bool hasDeleteGroupJoinQuestionsRequest() => $_has(48);
  @$pb.TagNumber(506)
  void clearDeleteGroupJoinQuestionsRequest() => clearField(506);
  @$pb.TagNumber(506)
  $47.DeleteGroupJoinQuestionsRequest ensureDeleteGroupJoinQuestionsRequest() =>
      $_ensure(48);

  @$pb.TagNumber(507)
  $48.QueryGroupInvitationsRequest get queryGroupInvitationsRequest =>
      $_getN(49);
  @$pb.TagNumber(507)
  set queryGroupInvitationsRequest($48.QueryGroupInvitationsRequest v) {
    setField(507, v);
  }

  @$pb.TagNumber(507)
  $core.bool hasQueryGroupInvitationsRequest() => $_has(49);
  @$pb.TagNumber(507)
  void clearQueryGroupInvitationsRequest() => clearField(507);
  @$pb.TagNumber(507)
  $48.QueryGroupInvitationsRequest ensureQueryGroupInvitationsRequest() =>
      $_ensure(49);

  @$pb.TagNumber(508)
  $49.QueryGroupJoinRequestsRequest get queryGroupJoinRequestsRequest =>
      $_getN(50);
  @$pb.TagNumber(508)
  set queryGroupJoinRequestsRequest($49.QueryGroupJoinRequestsRequest v) {
    setField(508, v);
  }

  @$pb.TagNumber(508)
  $core.bool hasQueryGroupJoinRequestsRequest() => $_has(50);
  @$pb.TagNumber(508)
  void clearQueryGroupJoinRequestsRequest() => clearField(508);
  @$pb.TagNumber(508)
  $49.QueryGroupJoinRequestsRequest ensureQueryGroupJoinRequestsRequest() =>
      $_ensure(50);

  @$pb.TagNumber(509)
  $50.QueryGroupJoinQuestionsRequest get queryGroupJoinQuestionsRequest =>
      $_getN(51);
  @$pb.TagNumber(509)
  set queryGroupJoinQuestionsRequest($50.QueryGroupJoinQuestionsRequest v) {
    setField(509, v);
  }

  @$pb.TagNumber(509)
  $core.bool hasQueryGroupJoinQuestionsRequest() => $_has(51);
  @$pb.TagNumber(509)
  void clearQueryGroupJoinQuestionsRequest() => clearField(509);
  @$pb.TagNumber(509)
  $50.QueryGroupJoinQuestionsRequest ensureQueryGroupJoinQuestionsRequest() =>
      $_ensure(51);

  @$pb.TagNumber(510)
  $51.UpdateGroupInvitationRequest get updateGroupInvitationRequest =>
      $_getN(52);
  @$pb.TagNumber(510)
  set updateGroupInvitationRequest($51.UpdateGroupInvitationRequest v) {
    setField(510, v);
  }

  @$pb.TagNumber(510)
  $core.bool hasUpdateGroupInvitationRequest() => $_has(52);
  @$pb.TagNumber(510)
  void clearUpdateGroupInvitationRequest() => clearField(510);
  @$pb.TagNumber(510)
  $51.UpdateGroupInvitationRequest ensureUpdateGroupInvitationRequest() =>
      $_ensure(52);

  @$pb.TagNumber(511)
  $52.UpdateGroupJoinQuestionRequest get updateGroupJoinQuestionRequest =>
      $_getN(53);
  @$pb.TagNumber(511)
  set updateGroupJoinQuestionRequest($52.UpdateGroupJoinQuestionRequest v) {
    setField(511, v);
  }

  @$pb.TagNumber(511)
  $core.bool hasUpdateGroupJoinQuestionRequest() => $_has(53);
  @$pb.TagNumber(511)
  void clearUpdateGroupJoinQuestionRequest() => clearField(511);
  @$pb.TagNumber(511)
  $52.UpdateGroupJoinQuestionRequest ensureUpdateGroupJoinQuestionRequest() =>
      $_ensure(53);

  @$pb.TagNumber(512)
  $53.UpdateGroupJoinRequestRequest get updateGroupJoinRequestRequest =>
      $_getN(54);
  @$pb.TagNumber(512)
  set updateGroupJoinRequestRequest($53.UpdateGroupJoinRequestRequest v) {
    setField(512, v);
  }

  @$pb.TagNumber(512)
  $core.bool hasUpdateGroupJoinRequestRequest() => $_has(54);
  @$pb.TagNumber(512)
  void clearUpdateGroupJoinRequestRequest() => clearField(512);
  @$pb.TagNumber(512)
  $53.UpdateGroupJoinRequestRequest ensureUpdateGroupJoinRequestRequest() =>
      $_ensure(54);

  /// Storage
  @$pb.TagNumber(1000)
  $54.DeleteResourceRequest get deleteResourceRequest => $_getN(55);
  @$pb.TagNumber(1000)
  set deleteResourceRequest($54.DeleteResourceRequest v) {
    setField(1000, v);
  }

  @$pb.TagNumber(1000)
  $core.bool hasDeleteResourceRequest() => $_has(55);
  @$pb.TagNumber(1000)
  void clearDeleteResourceRequest() => clearField(1000);
  @$pb.TagNumber(1000)
  $54.DeleteResourceRequest ensureDeleteResourceRequest() => $_ensure(55);

  @$pb.TagNumber(1001)
  $55.QueryResourceDownloadInfoRequest get queryResourceDownloadInfoRequest =>
      $_getN(56);
  @$pb.TagNumber(1001)
  set queryResourceDownloadInfoRequest($55.QueryResourceDownloadInfoRequest v) {
    setField(1001, v);
  }

  @$pb.TagNumber(1001)
  $core.bool hasQueryResourceDownloadInfoRequest() => $_has(56);
  @$pb.TagNumber(1001)
  void clearQueryResourceDownloadInfoRequest() => clearField(1001);
  @$pb.TagNumber(1001)
  $55.QueryResourceDownloadInfoRequest
      ensureQueryResourceDownloadInfoRequest() => $_ensure(56);

  @$pb.TagNumber(1002)
  $56.QueryResourceUploadInfoRequest get queryResourceUploadInfoRequest =>
      $_getN(57);
  @$pb.TagNumber(1002)
  set queryResourceUploadInfoRequest($56.QueryResourceUploadInfoRequest v) {
    setField(1002, v);
  }

  @$pb.TagNumber(1002)
  $core.bool hasQueryResourceUploadInfoRequest() => $_has(57);
  @$pb.TagNumber(1002)
  void clearQueryResourceUploadInfoRequest() => clearField(1002);
  @$pb.TagNumber(1002)
  $56.QueryResourceUploadInfoRequest ensureQueryResourceUploadInfoRequest() =>
      $_ensure(57);

  @$pb.TagNumber(1003)
  $57.QueryMessageAttachmentInfosRequest
      get queryMessageAttachmentInfosRequest => $_getN(58);
  @$pb.TagNumber(1003)
  set queryMessageAttachmentInfosRequest(
      $57.QueryMessageAttachmentInfosRequest v) {
    setField(1003, v);
  }

  @$pb.TagNumber(1003)
  $core.bool hasQueryMessageAttachmentInfosRequest() => $_has(58);
  @$pb.TagNumber(1003)
  void clearQueryMessageAttachmentInfosRequest() => clearField(1003);
  @$pb.TagNumber(1003)
  $57.QueryMessageAttachmentInfosRequest
      ensureQueryMessageAttachmentInfosRequest() => $_ensure(58);

  @$pb.TagNumber(1004)
  $58.UpdateMessageAttachmentInfoRequest
      get updateMessageAttachmentInfoRequest => $_getN(59);
  @$pb.TagNumber(1004)
  set updateMessageAttachmentInfoRequest(
      $58.UpdateMessageAttachmentInfoRequest v) {
    setField(1004, v);
  }

  @$pb.TagNumber(1004)
  $core.bool hasUpdateMessageAttachmentInfoRequest() => $_has(59);
  @$pb.TagNumber(1004)
  void clearUpdateMessageAttachmentInfoRequest() => clearField(1004);
  @$pb.TagNumber(1004)
  $58.UpdateMessageAttachmentInfoRequest
      ensureUpdateMessageAttachmentInfoRequest() => $_ensure(59);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
