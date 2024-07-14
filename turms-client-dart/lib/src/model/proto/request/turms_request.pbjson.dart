//
//  Generated code. Do not modify.
//  source: request/turms_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use turmsRequestDescriptor instead')
const TurmsRequest$json = {
  '1': 'TurmsRequest',
  '2': [
    {
      '1': 'request_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'requestId',
      '17': true
    },
    {
      '1': 'custom_attributes',
      '3': 2,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
    {
      '1': 'create_session_request',
      '3': 3,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateSessionRequest',
      '9': 0,
      '10': 'createSessionRequest'
    },
    {
      '1': 'delete_session_request',
      '3': 4,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteSessionRequest',
      '9': 0,
      '10': 'deleteSessionRequest'
    },
    {
      '1': 'query_conversations_request',
      '3': 5,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryConversationsRequest',
      '9': 0,
      '10': 'queryConversationsRequest'
    },
    {
      '1': 'update_conversation_request',
      '3': 6,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateConversationRequest',
      '9': 0,
      '10': 'updateConversationRequest'
    },
    {
      '1': 'update_typing_status_request',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateTypingStatusRequest',
      '9': 0,
      '10': 'updateTypingStatusRequest'
    },
    {
      '1': 'create_message_request',
      '3': 8,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateMessageRequest',
      '9': 0,
      '10': 'createMessageRequest'
    },
    {
      '1': 'query_messages_request',
      '3': 9,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryMessagesRequest',
      '9': 0,
      '10': 'queryMessagesRequest'
    },
    {
      '1': 'update_message_request',
      '3': 10,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateMessageRequest',
      '9': 0,
      '10': 'updateMessageRequest'
    },
    {
      '1': 'create_group_members_request',
      '3': 11,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupMembersRequest',
      '9': 0,
      '10': 'createGroupMembersRequest'
    },
    {
      '1': 'delete_group_members_request',
      '3': 12,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupMembersRequest',
      '9': 0,
      '10': 'deleteGroupMembersRequest'
    },
    {
      '1': 'query_group_members_request',
      '3': 13,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupMembersRequest',
      '9': 0,
      '10': 'queryGroupMembersRequest'
    },
    {
      '1': 'update_group_member_request',
      '3': 14,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupMemberRequest',
      '9': 0,
      '10': 'updateGroupMemberRequest'
    },
    {
      '1': 'query_user_profiles_request',
      '3': 100,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryUserProfilesRequest',
      '9': 0,
      '10': 'queryUserProfilesRequest'
    },
    {
      '1': 'query_nearby_users_request',
      '3': 101,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryNearbyUsersRequest',
      '9': 0,
      '10': 'queryNearbyUsersRequest'
    },
    {
      '1': 'query_user_online_statuses_request',
      '3': 102,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryUserOnlineStatusesRequest',
      '9': 0,
      '10': 'queryUserOnlineStatusesRequest'
    },
    {
      '1': 'update_user_location_request',
      '3': 103,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserLocationRequest',
      '9': 0,
      '10': 'updateUserLocationRequest'
    },
    {
      '1': 'update_user_online_status_request',
      '3': 104,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserOnlineStatusRequest',
      '9': 0,
      '10': 'updateUserOnlineStatusRequest'
    },
    {
      '1': 'update_user_request',
      '3': 105,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserRequest',
      '9': 0,
      '10': 'updateUserRequest'
    },
    {
      '1': 'update_user_settings_request',
      '3': 106,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserSettingsRequest',
      '9': 0,
      '10': 'updateUserSettingsRequest'
    },
    {
      '1': 'delete_user_settings_request',
      '3': 107,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteUserSettingsRequest',
      '9': 0,
      '10': 'deleteUserSettingsRequest'
    },
    {
      '1': 'query_user_settings_request',
      '3': 108,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryUserSettingsRequest',
      '9': 0,
      '10': 'queryUserSettingsRequest'
    },
    {
      '1': 'create_friend_request_request',
      '3': 200,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateFriendRequestRequest',
      '9': 0,
      '10': 'createFriendRequestRequest'
    },
    {
      '1': 'create_relationship_group_request',
      '3': 201,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateRelationshipGroupRequest',
      '9': 0,
      '10': 'createRelationshipGroupRequest'
    },
    {
      '1': 'create_relationship_request',
      '3': 202,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateRelationshipRequest',
      '9': 0,
      '10': 'createRelationshipRequest'
    },
    {
      '1': 'delete_friend_request_request',
      '3': 203,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteFriendRequestRequest',
      '9': 0,
      '10': 'deleteFriendRequestRequest'
    },
    {
      '1': 'delete_relationship_group_request',
      '3': 204,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteRelationshipGroupRequest',
      '9': 0,
      '10': 'deleteRelationshipGroupRequest'
    },
    {
      '1': 'delete_relationship_request',
      '3': 205,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteRelationshipRequest',
      '9': 0,
      '10': 'deleteRelationshipRequest'
    },
    {
      '1': 'query_friend_requests_request',
      '3': 206,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryFriendRequestsRequest',
      '9': 0,
      '10': 'queryFriendRequestsRequest'
    },
    {
      '1': 'query_related_user_ids_request',
      '3': 207,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryRelatedUserIdsRequest',
      '9': 0,
      '10': 'queryRelatedUserIdsRequest'
    },
    {
      '1': 'query_relationship_groups_request',
      '3': 208,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryRelationshipGroupsRequest',
      '9': 0,
      '10': 'queryRelationshipGroupsRequest'
    },
    {
      '1': 'query_relationships_request',
      '3': 209,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryRelationshipsRequest',
      '9': 0,
      '10': 'queryRelationshipsRequest'
    },
    {
      '1': 'update_friend_request_request',
      '3': 210,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateFriendRequestRequest',
      '9': 0,
      '10': 'updateFriendRequestRequest'
    },
    {
      '1': 'update_relationship_group_request',
      '3': 211,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateRelationshipGroupRequest',
      '9': 0,
      '10': 'updateRelationshipGroupRequest'
    },
    {
      '1': 'update_relationship_request',
      '3': 212,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateRelationshipRequest',
      '9': 0,
      '10': 'updateRelationshipRequest'
    },
    {
      '1': 'create_group_request',
      '3': 300,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupRequest',
      '9': 0,
      '10': 'createGroupRequest'
    },
    {
      '1': 'delete_group_request',
      '3': 301,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupRequest',
      '9': 0,
      '10': 'deleteGroupRequest'
    },
    {
      '1': 'query_groups_request',
      '3': 302,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupsRequest',
      '9': 0,
      '10': 'queryGroupsRequest'
    },
    {
      '1': 'query_joined_group_ids_request',
      '3': 303,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryJoinedGroupIdsRequest',
      '9': 0,
      '10': 'queryJoinedGroupIdsRequest'
    },
    {
      '1': 'query_joined_group_infos_request',
      '3': 304,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryJoinedGroupInfosRequest',
      '9': 0,
      '10': 'queryJoinedGroupInfosRequest'
    },
    {
      '1': 'update_group_request',
      '3': 305,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupRequest',
      '9': 0,
      '10': 'updateGroupRequest'
    },
    {
      '1': 'create_group_blocked_user_request',
      '3': 400,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupBlockedUserRequest',
      '9': 0,
      '10': 'createGroupBlockedUserRequest'
    },
    {
      '1': 'delete_group_blocked_user_request',
      '3': 401,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupBlockedUserRequest',
      '9': 0,
      '10': 'deleteGroupBlockedUserRequest'
    },
    {
      '1': 'query_group_blocked_user_ids_request',
      '3': 402,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupBlockedUserIdsRequest',
      '9': 0,
      '10': 'queryGroupBlockedUserIdsRequest'
    },
    {
      '1': 'query_group_blocked_user_infos_request',
      '3': 403,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupBlockedUserInfosRequest',
      '9': 0,
      '10': 'queryGroupBlockedUserInfosRequest'
    },
    {
      '1': 'check_group_join_questions_answers_request',
      '3': 500,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest',
      '9': 0,
      '10': 'checkGroupJoinQuestionsAnswersRequest'
    },
    {
      '1': 'create_group_invitation_request',
      '3': 501,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupInvitationRequest',
      '9': 0,
      '10': 'createGroupInvitationRequest'
    },
    {
      '1': 'create_group_join_request_request',
      '3': 502,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupJoinRequestRequest',
      '9': 0,
      '10': 'createGroupJoinRequestRequest'
    },
    {
      '1': 'create_group_join_questions_request',
      '3': 503,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupJoinQuestionsRequest',
      '9': 0,
      '10': 'createGroupJoinQuestionsRequest'
    },
    {
      '1': 'delete_group_invitation_request',
      '3': 504,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupInvitationRequest',
      '9': 0,
      '10': 'deleteGroupInvitationRequest'
    },
    {
      '1': 'delete_group_join_request_request',
      '3': 505,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupJoinRequestRequest',
      '9': 0,
      '10': 'deleteGroupJoinRequestRequest'
    },
    {
      '1': 'delete_group_join_questions_request',
      '3': 506,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupJoinQuestionsRequest',
      '9': 0,
      '10': 'deleteGroupJoinQuestionsRequest'
    },
    {
      '1': 'query_group_invitations_request',
      '3': 507,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupInvitationsRequest',
      '9': 0,
      '10': 'queryGroupInvitationsRequest'
    },
    {
      '1': 'query_group_join_requests_request',
      '3': 508,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupJoinRequestsRequest',
      '9': 0,
      '10': 'queryGroupJoinRequestsRequest'
    },
    {
      '1': 'query_group_join_questions_request',
      '3': 509,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupJoinQuestionsRequest',
      '9': 0,
      '10': 'queryGroupJoinQuestionsRequest'
    },
    {
      '1': 'update_group_invitation_request',
      '3': 510,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupInvitationRequest',
      '9': 0,
      '10': 'updateGroupInvitationRequest'
    },
    {
      '1': 'update_group_join_question_request',
      '3': 511,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupJoinQuestionRequest',
      '9': 0,
      '10': 'updateGroupJoinQuestionRequest'
    },
    {
      '1': 'update_group_join_request_request',
      '3': 512,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupJoinRequestRequest',
      '9': 0,
      '10': 'updateGroupJoinRequestRequest'
    },
    {
      '1': 'create_meeting_request',
      '3': 900,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateMeetingRequest',
      '9': 0,
      '10': 'createMeetingRequest'
    },
    {
      '1': 'delete_meeting_request',
      '3': 901,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteMeetingRequest',
      '9': 0,
      '10': 'deleteMeetingRequest'
    },
    {
      '1': 'query_meetings_request',
      '3': 902,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryMeetingsRequest',
      '9': 0,
      '10': 'queryMeetingsRequest'
    },
    {
      '1': 'update_meeting_request',
      '3': 903,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateMeetingRequest',
      '9': 0,
      '10': 'updateMeetingRequest'
    },
    {
      '1': 'update_meeting_invitation_request',
      '3': 904,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateMeetingInvitationRequest',
      '9': 0,
      '10': 'updateMeetingInvitationRequest'
    },
    {
      '1': 'delete_resource_request',
      '3': 1000,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteResourceRequest',
      '9': 0,
      '10': 'deleteResourceRequest'
    },
    {
      '1': 'query_resource_download_info_request',
      '3': 1001,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryResourceDownloadInfoRequest',
      '9': 0,
      '10': 'queryResourceDownloadInfoRequest'
    },
    {
      '1': 'query_resource_upload_info_request',
      '3': 1002,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryResourceUploadInfoRequest',
      '9': 0,
      '10': 'queryResourceUploadInfoRequest'
    },
    {
      '1': 'query_message_attachment_infos_request',
      '3': 1003,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryMessageAttachmentInfosRequest',
      '9': 0,
      '10': 'queryMessageAttachmentInfosRequest'
    },
    {
      '1': 'update_message_attachment_info_request',
      '3': 1004,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateMessageAttachmentInfoRequest',
      '9': 0,
      '10': 'updateMessageAttachmentInfoRequest'
    },
    {
      '1': 'delete_conversation_settings_request',
      '3': 1100,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteConversationSettingsRequest',
      '9': 0,
      '10': 'deleteConversationSettingsRequest'
    },
    {
      '1': 'query_conversation_settings_request',
      '3': 1101,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryConversationSettingsRequest',
      '9': 0,
      '10': 'queryConversationSettingsRequest'
    },
    {
      '1': 'update_conversation_settings_request',
      '3': 1102,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateConversationSettingsRequest',
      '9': 0,
      '10': 'updateConversationSettingsRequest'
    },
    {
      '1': 'create_message_reactions_request',
      '3': 1200,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateMessageReactionsRequest',
      '9': 0,
      '10': 'createMessageReactionsRequest'
    },
    {
      '1': 'delete_message_reactions_request',
      '3': 1201,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteMessageReactionsRequest',
      '9': 0,
      '10': 'deleteMessageReactionsRequest'
    },
  ],
  '8': [
    {'1': 'kind'},
    {'1': '_request_id'},
  ],
};

/// Descriptor for `TurmsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsRequestDescriptor = $convert.base64Decode(
    'CgxUdXJtc1JlcXVlc3QSIgoKcmVxdWVzdF9pZBgBIAEoA0gBUglyZXF1ZXN0SWSIAQESQgoRY3'
    'VzdG9tX2F0dHJpYnV0ZXMYAiADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0'
    'cmlidXRlcxJcChZjcmVhdGVfc2Vzc2lvbl9yZXF1ZXN0GAMgASgLMiQuaW0udHVybXMucHJvdG'
    '8uQ3JlYXRlU2Vzc2lvblJlcXVlc3RIAFIUY3JlYXRlU2Vzc2lvblJlcXVlc3QSXAoWZGVsZXRl'
    'X3Nlc3Npb25fcmVxdWVzdBgEIAEoCzIkLmltLnR1cm1zLnByb3RvLkRlbGV0ZVNlc3Npb25SZX'
    'F1ZXN0SABSFGRlbGV0ZVNlc3Npb25SZXF1ZXN0EmsKG3F1ZXJ5X2NvbnZlcnNhdGlvbnNfcmVx'
    'dWVzdBgFIAEoCzIpLmltLnR1cm1zLnByb3RvLlF1ZXJ5Q29udmVyc2F0aW9uc1JlcXVlc3RIAF'
    'IZcXVlcnlDb252ZXJzYXRpb25zUmVxdWVzdBJrCht1cGRhdGVfY29udmVyc2F0aW9uX3JlcXVl'
    'c3QYBiABKAsyKS5pbS50dXJtcy5wcm90by5VcGRhdGVDb252ZXJzYXRpb25SZXF1ZXN0SABSGX'
    'VwZGF0ZUNvbnZlcnNhdGlvblJlcXVlc3QSbAocdXBkYXRlX3R5cGluZ19zdGF0dXNfcmVxdWVz'
    'dBgHIAEoCzIpLmltLnR1cm1zLnByb3RvLlVwZGF0ZVR5cGluZ1N0YXR1c1JlcXVlc3RIAFIZdX'
    'BkYXRlVHlwaW5nU3RhdHVzUmVxdWVzdBJcChZjcmVhdGVfbWVzc2FnZV9yZXF1ZXN0GAggASgL'
    'MiQuaW0udHVybXMucHJvdG8uQ3JlYXRlTWVzc2FnZVJlcXVlc3RIAFIUY3JlYXRlTWVzc2FnZV'
    'JlcXVlc3QSXAoWcXVlcnlfbWVzc2FnZXNfcmVxdWVzdBgJIAEoCzIkLmltLnR1cm1zLnByb3Rv'
    'LlF1ZXJ5TWVzc2FnZXNSZXF1ZXN0SABSFHF1ZXJ5TWVzc2FnZXNSZXF1ZXN0ElwKFnVwZGF0ZV'
    '9tZXNzYWdlX3JlcXVlc3QYCiABKAsyJC5pbS50dXJtcy5wcm90by5VcGRhdGVNZXNzYWdlUmVx'
    'dWVzdEgAUhR1cGRhdGVNZXNzYWdlUmVxdWVzdBJsChxjcmVhdGVfZ3JvdXBfbWVtYmVyc19yZX'
    'F1ZXN0GAsgASgLMikuaW0udHVybXMucHJvdG8uQ3JlYXRlR3JvdXBNZW1iZXJzUmVxdWVzdEgA'
    'UhljcmVhdGVHcm91cE1lbWJlcnNSZXF1ZXN0EmwKHGRlbGV0ZV9ncm91cF9tZW1iZXJzX3JlcX'
    'Vlc3QYDCABKAsyKS5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cE1lbWJlcnNSZXF1ZXN0SABS'
    'GWRlbGV0ZUdyb3VwTWVtYmVyc1JlcXVlc3QSaQobcXVlcnlfZ3JvdXBfbWVtYmVyc19yZXF1ZX'
    'N0GA0gASgLMiguaW0udHVybXMucHJvdG8uUXVlcnlHcm91cE1lbWJlcnNSZXF1ZXN0SABSGHF1'
    'ZXJ5R3JvdXBNZW1iZXJzUmVxdWVzdBJpCht1cGRhdGVfZ3JvdXBfbWVtYmVyX3JlcXVlc3QYDi'
    'ABKAsyKC5pbS50dXJtcy5wcm90by5VcGRhdGVHcm91cE1lbWJlclJlcXVlc3RIAFIYdXBkYXRl'
    'R3JvdXBNZW1iZXJSZXF1ZXN0EmkKG3F1ZXJ5X3VzZXJfcHJvZmlsZXNfcmVxdWVzdBhkIAEoCz'
    'IoLmltLnR1cm1zLnByb3RvLlF1ZXJ5VXNlclByb2ZpbGVzUmVxdWVzdEgAUhhxdWVyeVVzZXJQ'
    'cm9maWxlc1JlcXVlc3QSZgoacXVlcnlfbmVhcmJ5X3VzZXJzX3JlcXVlc3QYZSABKAsyJy5pbS'
    '50dXJtcy5wcm90by5RdWVyeU5lYXJieVVzZXJzUmVxdWVzdEgAUhdxdWVyeU5lYXJieVVzZXJz'
    'UmVxdWVzdBJ8CiJxdWVyeV91c2VyX29ubGluZV9zdGF0dXNlc19yZXF1ZXN0GGYgASgLMi4uaW'
    '0udHVybXMucHJvdG8uUXVlcnlVc2VyT25saW5lU3RhdHVzZXNSZXF1ZXN0SABSHnF1ZXJ5VXNl'
    'ck9ubGluZVN0YXR1c2VzUmVxdWVzdBJsChx1cGRhdGVfdXNlcl9sb2NhdGlvbl9yZXF1ZXN0GG'
    'cgASgLMikuaW0udHVybXMucHJvdG8uVXBkYXRlVXNlckxvY2F0aW9uUmVxdWVzdEgAUhl1cGRh'
    'dGVVc2VyTG9jYXRpb25SZXF1ZXN0EnkKIXVwZGF0ZV91c2VyX29ubGluZV9zdGF0dXNfcmVxdW'
    'VzdBhoIAEoCzItLmltLnR1cm1zLnByb3RvLlVwZGF0ZVVzZXJPbmxpbmVTdGF0dXNSZXF1ZXN0'
    'SABSHXVwZGF0ZVVzZXJPbmxpbmVTdGF0dXNSZXF1ZXN0ElMKE3VwZGF0ZV91c2VyX3JlcXVlc3'
    'QYaSABKAsyIS5pbS50dXJtcy5wcm90by5VcGRhdGVVc2VyUmVxdWVzdEgAUhF1cGRhdGVVc2Vy'
    'UmVxdWVzdBJsChx1cGRhdGVfdXNlcl9zZXR0aW5nc19yZXF1ZXN0GGogASgLMikuaW0udHVybX'
    'MucHJvdG8uVXBkYXRlVXNlclNldHRpbmdzUmVxdWVzdEgAUhl1cGRhdGVVc2VyU2V0dGluZ3NS'
    'ZXF1ZXN0EmwKHGRlbGV0ZV91c2VyX3NldHRpbmdzX3JlcXVlc3QYayABKAsyKS5pbS50dXJtcy'
    '5wcm90by5EZWxldGVVc2VyU2V0dGluZ3NSZXF1ZXN0SABSGWRlbGV0ZVVzZXJTZXR0aW5nc1Jl'
    'cXVlc3QSaQobcXVlcnlfdXNlcl9zZXR0aW5nc19yZXF1ZXN0GGwgASgLMiguaW0udHVybXMucH'
    'JvdG8uUXVlcnlVc2VyU2V0dGluZ3NSZXF1ZXN0SABSGHF1ZXJ5VXNlclNldHRpbmdzUmVxdWVz'
    'dBJwCh1jcmVhdGVfZnJpZW5kX3JlcXVlc3RfcmVxdWVzdBjIASABKAsyKi5pbS50dXJtcy5wcm'
    '90by5DcmVhdGVGcmllbmRSZXF1ZXN0UmVxdWVzdEgAUhpjcmVhdGVGcmllbmRSZXF1ZXN0UmVx'
    'dWVzdBJ8CiFjcmVhdGVfcmVsYXRpb25zaGlwX2dyb3VwX3JlcXVlc3QYyQEgASgLMi4uaW0udH'
    'VybXMucHJvdG8uQ3JlYXRlUmVsYXRpb25zaGlwR3JvdXBSZXF1ZXN0SABSHmNyZWF0ZVJlbGF0'
    'aW9uc2hpcEdyb3VwUmVxdWVzdBJsChtjcmVhdGVfcmVsYXRpb25zaGlwX3JlcXVlc3QYygEgAS'
    'gLMikuaW0udHVybXMucHJvdG8uQ3JlYXRlUmVsYXRpb25zaGlwUmVxdWVzdEgAUhljcmVhdGVS'
    'ZWxhdGlvbnNoaXBSZXF1ZXN0EnAKHWRlbGV0ZV9mcmllbmRfcmVxdWVzdF9yZXF1ZXN0GMsBIA'
    'EoCzIqLmltLnR1cm1zLnByb3RvLkRlbGV0ZUZyaWVuZFJlcXVlc3RSZXF1ZXN0SABSGmRlbGV0'
    'ZUZyaWVuZFJlcXVlc3RSZXF1ZXN0EnwKIWRlbGV0ZV9yZWxhdGlvbnNoaXBfZ3JvdXBfcmVxdW'
    'VzdBjMASABKAsyLi5pbS50dXJtcy5wcm90by5EZWxldGVSZWxhdGlvbnNoaXBHcm91cFJlcXVl'
    'c3RIAFIeZGVsZXRlUmVsYXRpb25zaGlwR3JvdXBSZXF1ZXN0EmwKG2RlbGV0ZV9yZWxhdGlvbn'
    'NoaXBfcmVxdWVzdBjNASABKAsyKS5pbS50dXJtcy5wcm90by5EZWxldGVSZWxhdGlvbnNoaXBS'
    'ZXF1ZXN0SABSGWRlbGV0ZVJlbGF0aW9uc2hpcFJlcXVlc3QScAodcXVlcnlfZnJpZW5kX3JlcX'
    'Vlc3RzX3JlcXVlc3QYzgEgASgLMiouaW0udHVybXMucHJvdG8uUXVlcnlGcmllbmRSZXF1ZXN0'
    'c1JlcXVlc3RIAFIacXVlcnlGcmllbmRSZXF1ZXN0c1JlcXVlc3QScQoecXVlcnlfcmVsYXRlZF'
    '91c2VyX2lkc19yZXF1ZXN0GM8BIAEoCzIqLmltLnR1cm1zLnByb3RvLlF1ZXJ5UmVsYXRlZFVz'
    'ZXJJZHNSZXF1ZXN0SABSGnF1ZXJ5UmVsYXRlZFVzZXJJZHNSZXF1ZXN0EnwKIXF1ZXJ5X3JlbG'
    'F0aW9uc2hpcF9ncm91cHNfcmVxdWVzdBjQASABKAsyLi5pbS50dXJtcy5wcm90by5RdWVyeVJl'
    'bGF0aW9uc2hpcEdyb3Vwc1JlcXVlc3RIAFIecXVlcnlSZWxhdGlvbnNoaXBHcm91cHNSZXF1ZX'
    'N0EmwKG3F1ZXJ5X3JlbGF0aW9uc2hpcHNfcmVxdWVzdBjRASABKAsyKS5pbS50dXJtcy5wcm90'
    'by5RdWVyeVJlbGF0aW9uc2hpcHNSZXF1ZXN0SABSGXF1ZXJ5UmVsYXRpb25zaGlwc1JlcXVlc3'
    'QScAoddXBkYXRlX2ZyaWVuZF9yZXF1ZXN0X3JlcXVlc3QY0gEgASgLMiouaW0udHVybXMucHJv'
    'dG8uVXBkYXRlRnJpZW5kUmVxdWVzdFJlcXVlc3RIAFIadXBkYXRlRnJpZW5kUmVxdWVzdFJlcX'
    'Vlc3QSfAohdXBkYXRlX3JlbGF0aW9uc2hpcF9ncm91cF9yZXF1ZXN0GNMBIAEoCzIuLmltLnR1'
    'cm1zLnByb3RvLlVwZGF0ZVJlbGF0aW9uc2hpcEdyb3VwUmVxdWVzdEgAUh51cGRhdGVSZWxhdG'
    'lvbnNoaXBHcm91cFJlcXVlc3QSbAobdXBkYXRlX3JlbGF0aW9uc2hpcF9yZXF1ZXN0GNQBIAEo'
    'CzIpLmltLnR1cm1zLnByb3RvLlVwZGF0ZVJlbGF0aW9uc2hpcFJlcXVlc3RIAFIZdXBkYXRlUm'
    'VsYXRpb25zaGlwUmVxdWVzdBJXChRjcmVhdGVfZ3JvdXBfcmVxdWVzdBisAiABKAsyIi5pbS50'
    'dXJtcy5wcm90by5DcmVhdGVHcm91cFJlcXVlc3RIAFISY3JlYXRlR3JvdXBSZXF1ZXN0ElcKFG'
    'RlbGV0ZV9ncm91cF9yZXF1ZXN0GK0CIAEoCzIiLmltLnR1cm1zLnByb3RvLkRlbGV0ZUdyb3Vw'
    'UmVxdWVzdEgAUhJkZWxldGVHcm91cFJlcXVlc3QSVwoUcXVlcnlfZ3JvdXBzX3JlcXVlc3QYrg'
    'IgASgLMiIuaW0udHVybXMucHJvdG8uUXVlcnlHcm91cHNSZXF1ZXN0SABSEnF1ZXJ5R3JvdXBz'
    'UmVxdWVzdBJxCh5xdWVyeV9qb2luZWRfZ3JvdXBfaWRzX3JlcXVlc3QYrwIgASgLMiouaW0udH'
    'VybXMucHJvdG8uUXVlcnlKb2luZWRHcm91cElkc1JlcXVlc3RIAFIacXVlcnlKb2luZWRHcm91'
    'cElkc1JlcXVlc3QSdwogcXVlcnlfam9pbmVkX2dyb3VwX2luZm9zX3JlcXVlc3QYsAIgASgLMi'
    'wuaW0udHVybXMucHJvdG8uUXVlcnlKb2luZWRHcm91cEluZm9zUmVxdWVzdEgAUhxxdWVyeUpv'
    'aW5lZEdyb3VwSW5mb3NSZXF1ZXN0ElcKFHVwZGF0ZV9ncm91cF9yZXF1ZXN0GLECIAEoCzIiLm'
    'ltLnR1cm1zLnByb3RvLlVwZGF0ZUdyb3VwUmVxdWVzdEgAUhJ1cGRhdGVHcm91cFJlcXVlc3QS'
    'egohY3JlYXRlX2dyb3VwX2Jsb2NrZWRfdXNlcl9yZXF1ZXN0GJADIAEoCzItLmltLnR1cm1zLn'
    'Byb3RvLkNyZWF0ZUdyb3VwQmxvY2tlZFVzZXJSZXF1ZXN0SABSHWNyZWF0ZUdyb3VwQmxvY2tl'
    'ZFVzZXJSZXF1ZXN0EnoKIWRlbGV0ZV9ncm91cF9ibG9ja2VkX3VzZXJfcmVxdWVzdBiRAyABKA'
    'syLS5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdEgAUh1kZWxl'
    'dGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdBKBAQokcXVlcnlfZ3JvdXBfYmxvY2tlZF91c2VyX2'
    'lkc19yZXF1ZXN0GJIDIAEoCzIvLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBCbG9ja2VkVXNl'
    'cklkc1JlcXVlc3RIAFIfcXVlcnlHcm91cEJsb2NrZWRVc2VySWRzUmVxdWVzdBKHAQomcXVlcn'
    'lfZ3JvdXBfYmxvY2tlZF91c2VyX2luZm9zX3JlcXVlc3QYkwMgASgLMjEuaW0udHVybXMucHJv'
    'dG8uUXVlcnlHcm91cEJsb2NrZWRVc2VySW5mb3NSZXF1ZXN0SABSIXF1ZXJ5R3JvdXBCbG9ja2'
    'VkVXNlckluZm9zUmVxdWVzdBKTAQoqY2hlY2tfZ3JvdXBfam9pbl9xdWVzdGlvbnNfYW5zd2Vy'
    'c19yZXF1ZXN0GPQDIAEoCzI1LmltLnR1cm1zLnByb3RvLkNoZWNrR3JvdXBKb2luUXVlc3Rpb2'
    '5zQW5zd2Vyc1JlcXVlc3RIAFIlY2hlY2tHcm91cEpvaW5RdWVzdGlvbnNBbnN3ZXJzUmVxdWVz'
    'dBJ2Ch9jcmVhdGVfZ3JvdXBfaW52aXRhdGlvbl9yZXF1ZXN0GPUDIAEoCzIsLmltLnR1cm1zLn'
    'Byb3RvLkNyZWF0ZUdyb3VwSW52aXRhdGlvblJlcXVlc3RIAFIcY3JlYXRlR3JvdXBJbnZpdGF0'
    'aW9uUmVxdWVzdBJ6CiFjcmVhdGVfZ3JvdXBfam9pbl9yZXF1ZXN0X3JlcXVlc3QY9gMgASgLMi'
    '0uaW0udHVybXMucHJvdG8uQ3JlYXRlR3JvdXBKb2luUmVxdWVzdFJlcXVlc3RIAFIdY3JlYXRl'
    'R3JvdXBKb2luUmVxdWVzdFJlcXVlc3QSgAEKI2NyZWF0ZV9ncm91cF9qb2luX3F1ZXN0aW9uc1'
    '9yZXF1ZXN0GPcDIAEoCzIvLmltLnR1cm1zLnByb3RvLkNyZWF0ZUdyb3VwSm9pblF1ZXN0aW9u'
    'c1JlcXVlc3RIAFIfY3JlYXRlR3JvdXBKb2luUXVlc3Rpb25zUmVxdWVzdBJ2Ch9kZWxldGVfZ3'
    'JvdXBfaW52aXRhdGlvbl9yZXF1ZXN0GPgDIAEoCzIsLmltLnR1cm1zLnByb3RvLkRlbGV0ZUdy'
    'b3VwSW52aXRhdGlvblJlcXVlc3RIAFIcZGVsZXRlR3JvdXBJbnZpdGF0aW9uUmVxdWVzdBJ6Ci'
    'FkZWxldGVfZ3JvdXBfam9pbl9yZXF1ZXN0X3JlcXVlc3QY+QMgASgLMi0uaW0udHVybXMucHJv'
    'dG8uRGVsZXRlR3JvdXBKb2luUmVxdWVzdFJlcXVlc3RIAFIdZGVsZXRlR3JvdXBKb2luUmVxdW'
    'VzdFJlcXVlc3QSgAEKI2RlbGV0ZV9ncm91cF9qb2luX3F1ZXN0aW9uc19yZXF1ZXN0GPoDIAEo'
    'CzIvLmltLnR1cm1zLnByb3RvLkRlbGV0ZUdyb3VwSm9pblF1ZXN0aW9uc1JlcXVlc3RIAFIfZG'
    'VsZXRlR3JvdXBKb2luUXVlc3Rpb25zUmVxdWVzdBJ2Ch9xdWVyeV9ncm91cF9pbnZpdGF0aW9u'
    'c19yZXF1ZXN0GPsDIAEoCzIsLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBJbnZpdGF0aW9uc1'
    'JlcXVlc3RIAFIccXVlcnlHcm91cEludml0YXRpb25zUmVxdWVzdBJ6CiFxdWVyeV9ncm91cF9q'
    'b2luX3JlcXVlc3RzX3JlcXVlc3QY/AMgASgLMi0uaW0udHVybXMucHJvdG8uUXVlcnlHcm91cE'
    'pvaW5SZXF1ZXN0c1JlcXVlc3RIAFIdcXVlcnlHcm91cEpvaW5SZXF1ZXN0c1JlcXVlc3QSfQoi'
    'cXVlcnlfZ3JvdXBfam9pbl9xdWVzdGlvbnNfcmVxdWVzdBj9AyABKAsyLi5pbS50dXJtcy5wcm'
    '90by5RdWVyeUdyb3VwSm9pblF1ZXN0aW9uc1JlcXVlc3RIAFIecXVlcnlHcm91cEpvaW5RdWVz'
    'dGlvbnNSZXF1ZXN0EnYKH3VwZGF0ZV9ncm91cF9pbnZpdGF0aW9uX3JlcXVlc3QY/gMgASgLMi'
    'wuaW0udHVybXMucHJvdG8uVXBkYXRlR3JvdXBJbnZpdGF0aW9uUmVxdWVzdEgAUhx1cGRhdGVH'
    'cm91cEludml0YXRpb25SZXF1ZXN0En0KInVwZGF0ZV9ncm91cF9qb2luX3F1ZXN0aW9uX3JlcX'
    'Vlc3QY/wMgASgLMi4uaW0udHVybXMucHJvdG8uVXBkYXRlR3JvdXBKb2luUXVlc3Rpb25SZXF1'
    'ZXN0SABSHnVwZGF0ZUdyb3VwSm9pblF1ZXN0aW9uUmVxdWVzdBJ6CiF1cGRhdGVfZ3JvdXBfam'
    '9pbl9yZXF1ZXN0X3JlcXVlc3QYgAQgASgLMi0uaW0udHVybXMucHJvdG8uVXBkYXRlR3JvdXBK'
    'b2luUmVxdWVzdFJlcXVlc3RIAFIddXBkYXRlR3JvdXBKb2luUmVxdWVzdFJlcXVlc3QSXQoWY3'
    'JlYXRlX21lZXRpbmdfcmVxdWVzdBiEByABKAsyJC5pbS50dXJtcy5wcm90by5DcmVhdGVNZWV0'
    'aW5nUmVxdWVzdEgAUhRjcmVhdGVNZWV0aW5nUmVxdWVzdBJdChZkZWxldGVfbWVldGluZ19yZX'
    'F1ZXN0GIUHIAEoCzIkLmltLnR1cm1zLnByb3RvLkRlbGV0ZU1lZXRpbmdSZXF1ZXN0SABSFGRl'
    'bGV0ZU1lZXRpbmdSZXF1ZXN0El0KFnF1ZXJ5X21lZXRpbmdzX3JlcXVlc3QYhgcgASgLMiQuaW'
    '0udHVybXMucHJvdG8uUXVlcnlNZWV0aW5nc1JlcXVlc3RIAFIUcXVlcnlNZWV0aW5nc1JlcXVl'
    'c3QSXQoWdXBkYXRlX21lZXRpbmdfcmVxdWVzdBiHByABKAsyJC5pbS50dXJtcy5wcm90by5VcG'
    'RhdGVNZWV0aW5nUmVxdWVzdEgAUhR1cGRhdGVNZWV0aW5nUmVxdWVzdBJ8CiF1cGRhdGVfbWVl'
    'dGluZ19pbnZpdGF0aW9uX3JlcXVlc3QYiAcgASgLMi4uaW0udHVybXMucHJvdG8uVXBkYXRlTW'
    'VldGluZ0ludml0YXRpb25SZXF1ZXN0SABSHnVwZGF0ZU1lZXRpbmdJbnZpdGF0aW9uUmVxdWVz'
    'dBJgChdkZWxldGVfcmVzb3VyY2VfcmVxdWVzdBjoByABKAsyJS5pbS50dXJtcy5wcm90by5EZW'
    'xldGVSZXNvdXJjZVJlcXVlc3RIAFIVZGVsZXRlUmVzb3VyY2VSZXF1ZXN0EoMBCiRxdWVyeV9y'
    'ZXNvdXJjZV9kb3dubG9hZF9pbmZvX3JlcXVlc3QY6QcgASgLMjAuaW0udHVybXMucHJvdG8uUX'
    'VlcnlSZXNvdXJjZURvd25sb2FkSW5mb1JlcXVlc3RIAFIgcXVlcnlSZXNvdXJjZURvd25sb2Fk'
    'SW5mb1JlcXVlc3QSfQoicXVlcnlfcmVzb3VyY2VfdXBsb2FkX2luZm9fcmVxdWVzdBjqByABKA'
    'syLi5pbS50dXJtcy5wcm90by5RdWVyeVJlc291cmNlVXBsb2FkSW5mb1JlcXVlc3RIAFIecXVl'
    'cnlSZXNvdXJjZVVwbG9hZEluZm9SZXF1ZXN0EokBCiZxdWVyeV9tZXNzYWdlX2F0dGFjaG1lbn'
    'RfaW5mb3NfcmVxdWVzdBjrByABKAsyMi5pbS50dXJtcy5wcm90by5RdWVyeU1lc3NhZ2VBdHRh'
    'Y2htZW50SW5mb3NSZXF1ZXN0SABSInF1ZXJ5TWVzc2FnZUF0dGFjaG1lbnRJbmZvc1JlcXVlc3'
    'QSiQEKJnVwZGF0ZV9tZXNzYWdlX2F0dGFjaG1lbnRfaW5mb19yZXF1ZXN0GOwHIAEoCzIyLmlt'
    'LnR1cm1zLnByb3RvLlVwZGF0ZU1lc3NhZ2VBdHRhY2htZW50SW5mb1JlcXVlc3RIAFIidXBkYX'
    'RlTWVzc2FnZUF0dGFjaG1lbnRJbmZvUmVxdWVzdBKFAQokZGVsZXRlX2NvbnZlcnNhdGlvbl9z'
    'ZXR0aW5nc19yZXF1ZXN0GMwIIAEoCzIxLmltLnR1cm1zLnByb3RvLkRlbGV0ZUNvbnZlcnNhdG'
    'lvblNldHRpbmdzUmVxdWVzdEgAUiFkZWxldGVDb252ZXJzYXRpb25TZXR0aW5nc1JlcXVlc3QS'
    'ggEKI3F1ZXJ5X2NvbnZlcnNhdGlvbl9zZXR0aW5nc19yZXF1ZXN0GM0IIAEoCzIwLmltLnR1cm'
    '1zLnByb3RvLlF1ZXJ5Q29udmVyc2F0aW9uU2V0dGluZ3NSZXF1ZXN0SABSIHF1ZXJ5Q29udmVy'
    'c2F0aW9uU2V0dGluZ3NSZXF1ZXN0EoUBCiR1cGRhdGVfY29udmVyc2F0aW9uX3NldHRpbmdzX3'
    'JlcXVlc3QYzgggASgLMjEuaW0udHVybXMucHJvdG8uVXBkYXRlQ29udmVyc2F0aW9uU2V0dGlu'
    'Z3NSZXF1ZXN0SABSIXVwZGF0ZUNvbnZlcnNhdGlvblNldHRpbmdzUmVxdWVzdBJ5CiBjcmVhdG'
    'VfbWVzc2FnZV9yZWFjdGlvbnNfcmVxdWVzdBiwCSABKAsyLS5pbS50dXJtcy5wcm90by5DcmVh'
    'dGVNZXNzYWdlUmVhY3Rpb25zUmVxdWVzdEgAUh1jcmVhdGVNZXNzYWdlUmVhY3Rpb25zUmVxdW'
    'VzdBJ5CiBkZWxldGVfbWVzc2FnZV9yZWFjdGlvbnNfcmVxdWVzdBixCSABKAsyLS5pbS50dXJt'
    'cy5wcm90by5EZWxldGVNZXNzYWdlUmVhY3Rpb25zUmVxdWVzdEgAUh1kZWxldGVNZXNzYWdlUm'
    'VhY3Rpb25zUmVxdWVzdEIGCgRraW5kQg0KC19yZXF1ZXN0X2lk');
