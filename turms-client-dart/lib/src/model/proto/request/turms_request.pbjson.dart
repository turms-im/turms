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
  ],
  '8': [
    {'1': 'kind'},
    {'1': '_request_id'},
  ],
};

/// Descriptor for `TurmsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsRequestDescriptor = $convert.base64Decode(
    'CgxUdXJtc1JlcXVlc3QSIgoKcmVxdWVzdF9pZBgBIAEoA0gBUglyZXF1ZXN0SWSIAQESXAoWY3'
    'JlYXRlX3Nlc3Npb25fcmVxdWVzdBgDIAEoCzIkLmltLnR1cm1zLnByb3RvLkNyZWF0ZVNlc3Np'
    'b25SZXF1ZXN0SABSFGNyZWF0ZVNlc3Npb25SZXF1ZXN0ElwKFmRlbGV0ZV9zZXNzaW9uX3JlcX'
    'Vlc3QYBCABKAsyJC5pbS50dXJtcy5wcm90by5EZWxldGVTZXNzaW9uUmVxdWVzdEgAUhRkZWxl'
    'dGVTZXNzaW9uUmVxdWVzdBJrChtxdWVyeV9jb252ZXJzYXRpb25zX3JlcXVlc3QYBSABKAsyKS'
    '5pbS50dXJtcy5wcm90by5RdWVyeUNvbnZlcnNhdGlvbnNSZXF1ZXN0SABSGXF1ZXJ5Q29udmVy'
    'c2F0aW9uc1JlcXVlc3QSawobdXBkYXRlX2NvbnZlcnNhdGlvbl9yZXF1ZXN0GAYgASgLMikuaW'
    '0udHVybXMucHJvdG8uVXBkYXRlQ29udmVyc2F0aW9uUmVxdWVzdEgAUhl1cGRhdGVDb252ZXJz'
    'YXRpb25SZXF1ZXN0EmwKHHVwZGF0ZV90eXBpbmdfc3RhdHVzX3JlcXVlc3QYByABKAsyKS5pbS'
    '50dXJtcy5wcm90by5VcGRhdGVUeXBpbmdTdGF0dXNSZXF1ZXN0SABSGXVwZGF0ZVR5cGluZ1N0'
    'YXR1c1JlcXVlc3QSXAoWY3JlYXRlX21lc3NhZ2VfcmVxdWVzdBgIIAEoCzIkLmltLnR1cm1zLn'
    'Byb3RvLkNyZWF0ZU1lc3NhZ2VSZXF1ZXN0SABSFGNyZWF0ZU1lc3NhZ2VSZXF1ZXN0ElwKFnF1'
    'ZXJ5X21lc3NhZ2VzX3JlcXVlc3QYCSABKAsyJC5pbS50dXJtcy5wcm90by5RdWVyeU1lc3NhZ2'
    'VzUmVxdWVzdEgAUhRxdWVyeU1lc3NhZ2VzUmVxdWVzdBJcChZ1cGRhdGVfbWVzc2FnZV9yZXF1'
    'ZXN0GAogASgLMiQuaW0udHVybXMucHJvdG8uVXBkYXRlTWVzc2FnZVJlcXVlc3RIAFIUdXBkYX'
    'RlTWVzc2FnZVJlcXVlc3QSbAocY3JlYXRlX2dyb3VwX21lbWJlcnNfcmVxdWVzdBgLIAEoCzIp'
    'LmltLnR1cm1zLnByb3RvLkNyZWF0ZUdyb3VwTWVtYmVyc1JlcXVlc3RIAFIZY3JlYXRlR3JvdX'
    'BNZW1iZXJzUmVxdWVzdBJsChxkZWxldGVfZ3JvdXBfbWVtYmVyc19yZXF1ZXN0GAwgASgLMiku'
    'aW0udHVybXMucHJvdG8uRGVsZXRlR3JvdXBNZW1iZXJzUmVxdWVzdEgAUhlkZWxldGVHcm91cE'
    '1lbWJlcnNSZXF1ZXN0EmkKG3F1ZXJ5X2dyb3VwX21lbWJlcnNfcmVxdWVzdBgNIAEoCzIoLmlt'
    'LnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBNZW1iZXJzUmVxdWVzdEgAUhhxdWVyeUdyb3VwTWVtYm'
    'Vyc1JlcXVlc3QSaQobdXBkYXRlX2dyb3VwX21lbWJlcl9yZXF1ZXN0GA4gASgLMiguaW0udHVy'
    'bXMucHJvdG8uVXBkYXRlR3JvdXBNZW1iZXJSZXF1ZXN0SABSGHVwZGF0ZUdyb3VwTWVtYmVyUm'
    'VxdWVzdBJpChtxdWVyeV91c2VyX3Byb2ZpbGVzX3JlcXVlc3QYZCABKAsyKC5pbS50dXJtcy5w'
    'cm90by5RdWVyeVVzZXJQcm9maWxlc1JlcXVlc3RIAFIYcXVlcnlVc2VyUHJvZmlsZXNSZXF1ZX'
    'N0EmYKGnF1ZXJ5X25lYXJieV91c2Vyc19yZXF1ZXN0GGUgASgLMicuaW0udHVybXMucHJvdG8u'
    'UXVlcnlOZWFyYnlVc2Vyc1JlcXVlc3RIAFIXcXVlcnlOZWFyYnlVc2Vyc1JlcXVlc3QSfAoicX'
    'VlcnlfdXNlcl9vbmxpbmVfc3RhdHVzZXNfcmVxdWVzdBhmIAEoCzIuLmltLnR1cm1zLnByb3Rv'
    'LlF1ZXJ5VXNlck9ubGluZVN0YXR1c2VzUmVxdWVzdEgAUh5xdWVyeVVzZXJPbmxpbmVTdGF0dX'
    'Nlc1JlcXVlc3QSbAocdXBkYXRlX3VzZXJfbG9jYXRpb25fcmVxdWVzdBhnIAEoCzIpLmltLnR1'
    'cm1zLnByb3RvLlVwZGF0ZVVzZXJMb2NhdGlvblJlcXVlc3RIAFIZdXBkYXRlVXNlckxvY2F0aW'
    '9uUmVxdWVzdBJ5CiF1cGRhdGVfdXNlcl9vbmxpbmVfc3RhdHVzX3JlcXVlc3QYaCABKAsyLS5p'
    'bS50dXJtcy5wcm90by5VcGRhdGVVc2VyT25saW5lU3RhdHVzUmVxdWVzdEgAUh11cGRhdGVVc2'
    'VyT25saW5lU3RhdHVzUmVxdWVzdBJTChN1cGRhdGVfdXNlcl9yZXF1ZXN0GGkgASgLMiEuaW0u'
    'dHVybXMucHJvdG8uVXBkYXRlVXNlclJlcXVlc3RIAFIRdXBkYXRlVXNlclJlcXVlc3QScAodY3'
    'JlYXRlX2ZyaWVuZF9yZXF1ZXN0X3JlcXVlc3QYyAEgASgLMiouaW0udHVybXMucHJvdG8uQ3Jl'
    'YXRlRnJpZW5kUmVxdWVzdFJlcXVlc3RIAFIaY3JlYXRlRnJpZW5kUmVxdWVzdFJlcXVlc3QSfA'
    'ohY3JlYXRlX3JlbGF0aW9uc2hpcF9ncm91cF9yZXF1ZXN0GMkBIAEoCzIuLmltLnR1cm1zLnBy'
    'b3RvLkNyZWF0ZVJlbGF0aW9uc2hpcEdyb3VwUmVxdWVzdEgAUh5jcmVhdGVSZWxhdGlvbnNoaX'
    'BHcm91cFJlcXVlc3QSbAobY3JlYXRlX3JlbGF0aW9uc2hpcF9yZXF1ZXN0GMoBIAEoCzIpLmlt'
    'LnR1cm1zLnByb3RvLkNyZWF0ZVJlbGF0aW9uc2hpcFJlcXVlc3RIAFIZY3JlYXRlUmVsYXRpb2'
    '5zaGlwUmVxdWVzdBJwCh1kZWxldGVfZnJpZW5kX3JlcXVlc3RfcmVxdWVzdBjLASABKAsyKi5p'
    'bS50dXJtcy5wcm90by5EZWxldGVGcmllbmRSZXF1ZXN0UmVxdWVzdEgAUhpkZWxldGVGcmllbm'
    'RSZXF1ZXN0UmVxdWVzdBJ8CiFkZWxldGVfcmVsYXRpb25zaGlwX2dyb3VwX3JlcXVlc3QYzAEg'
    'ASgLMi4uaW0udHVybXMucHJvdG8uRGVsZXRlUmVsYXRpb25zaGlwR3JvdXBSZXF1ZXN0SABSHm'
    'RlbGV0ZVJlbGF0aW9uc2hpcEdyb3VwUmVxdWVzdBJsChtkZWxldGVfcmVsYXRpb25zaGlwX3Jl'
    'cXVlc3QYzQEgASgLMikuaW0udHVybXMucHJvdG8uRGVsZXRlUmVsYXRpb25zaGlwUmVxdWVzdE'
    'gAUhlkZWxldGVSZWxhdGlvbnNoaXBSZXF1ZXN0EnAKHXF1ZXJ5X2ZyaWVuZF9yZXF1ZXN0c19y'
    'ZXF1ZXN0GM4BIAEoCzIqLmltLnR1cm1zLnByb3RvLlF1ZXJ5RnJpZW5kUmVxdWVzdHNSZXF1ZX'
    'N0SABSGnF1ZXJ5RnJpZW5kUmVxdWVzdHNSZXF1ZXN0EnEKHnF1ZXJ5X3JlbGF0ZWRfdXNlcl9p'
    'ZHNfcmVxdWVzdBjPASABKAsyKi5pbS50dXJtcy5wcm90by5RdWVyeVJlbGF0ZWRVc2VySWRzUm'
    'VxdWVzdEgAUhpxdWVyeVJlbGF0ZWRVc2VySWRzUmVxdWVzdBJ8CiFxdWVyeV9yZWxhdGlvbnNo'
    'aXBfZ3JvdXBzX3JlcXVlc3QY0AEgASgLMi4uaW0udHVybXMucHJvdG8uUXVlcnlSZWxhdGlvbn'
    'NoaXBHcm91cHNSZXF1ZXN0SABSHnF1ZXJ5UmVsYXRpb25zaGlwR3JvdXBzUmVxdWVzdBJsChtx'
    'dWVyeV9yZWxhdGlvbnNoaXBzX3JlcXVlc3QY0QEgASgLMikuaW0udHVybXMucHJvdG8uUXVlcn'
    'lSZWxhdGlvbnNoaXBzUmVxdWVzdEgAUhlxdWVyeVJlbGF0aW9uc2hpcHNSZXF1ZXN0EnAKHXVw'
    'ZGF0ZV9mcmllbmRfcmVxdWVzdF9yZXF1ZXN0GNIBIAEoCzIqLmltLnR1cm1zLnByb3RvLlVwZG'
    'F0ZUZyaWVuZFJlcXVlc3RSZXF1ZXN0SABSGnVwZGF0ZUZyaWVuZFJlcXVlc3RSZXF1ZXN0EnwK'
    'IXVwZGF0ZV9yZWxhdGlvbnNoaXBfZ3JvdXBfcmVxdWVzdBjTASABKAsyLi5pbS50dXJtcy5wcm'
    '90by5VcGRhdGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3RIAFIedXBkYXRlUmVsYXRpb25zaGlw'
    'R3JvdXBSZXF1ZXN0EmwKG3VwZGF0ZV9yZWxhdGlvbnNoaXBfcmVxdWVzdBjUASABKAsyKS5pbS'
    '50dXJtcy5wcm90by5VcGRhdGVSZWxhdGlvbnNoaXBSZXF1ZXN0SABSGXVwZGF0ZVJlbGF0aW9u'
    'c2hpcFJlcXVlc3QSVwoUY3JlYXRlX2dyb3VwX3JlcXVlc3QYrAIgASgLMiIuaW0udHVybXMucH'
    'JvdG8uQ3JlYXRlR3JvdXBSZXF1ZXN0SABSEmNyZWF0ZUdyb3VwUmVxdWVzdBJXChRkZWxldGVf'
    'Z3JvdXBfcmVxdWVzdBitAiABKAsyIi5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cFJlcXVlc3'
    'RIAFISZGVsZXRlR3JvdXBSZXF1ZXN0ElcKFHF1ZXJ5X2dyb3Vwc19yZXF1ZXN0GK4CIAEoCzIi'
    'LmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBzUmVxdWVzdEgAUhJxdWVyeUdyb3Vwc1JlcXVlc3'
    'QScQoecXVlcnlfam9pbmVkX2dyb3VwX2lkc19yZXF1ZXN0GK8CIAEoCzIqLmltLnR1cm1zLnBy'
    'b3RvLlF1ZXJ5Sm9pbmVkR3JvdXBJZHNSZXF1ZXN0SABSGnF1ZXJ5Sm9pbmVkR3JvdXBJZHNSZX'
    'F1ZXN0EncKIHF1ZXJ5X2pvaW5lZF9ncm91cF9pbmZvc19yZXF1ZXN0GLACIAEoCzIsLmltLnR1'
    'cm1zLnByb3RvLlF1ZXJ5Sm9pbmVkR3JvdXBJbmZvc1JlcXVlc3RIAFIccXVlcnlKb2luZWRHcm'
    '91cEluZm9zUmVxdWVzdBJXChR1cGRhdGVfZ3JvdXBfcmVxdWVzdBixAiABKAsyIi5pbS50dXJt'
    'cy5wcm90by5VcGRhdGVHcm91cFJlcXVlc3RIAFISdXBkYXRlR3JvdXBSZXF1ZXN0EnoKIWNyZW'
    'F0ZV9ncm91cF9ibG9ja2VkX3VzZXJfcmVxdWVzdBiQAyABKAsyLS5pbS50dXJtcy5wcm90by5D'
    'cmVhdGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdEgAUh1jcmVhdGVHcm91cEJsb2NrZWRVc2VyUm'
    'VxdWVzdBJ6CiFkZWxldGVfZ3JvdXBfYmxvY2tlZF91c2VyX3JlcXVlc3QYkQMgASgLMi0uaW0u'
    'dHVybXMucHJvdG8uRGVsZXRlR3JvdXBCbG9ja2VkVXNlclJlcXVlc3RIAFIdZGVsZXRlR3JvdX'
    'BCbG9ja2VkVXNlclJlcXVlc3QSgQEKJHF1ZXJ5X2dyb3VwX2Jsb2NrZWRfdXNlcl9pZHNfcmVx'
    'dWVzdBiSAyABKAsyLy5pbS50dXJtcy5wcm90by5RdWVyeUdyb3VwQmxvY2tlZFVzZXJJZHNSZX'
    'F1ZXN0SABSH3F1ZXJ5R3JvdXBCbG9ja2VkVXNlcklkc1JlcXVlc3QShwEKJnF1ZXJ5X2dyb3Vw'
    'X2Jsb2NrZWRfdXNlcl9pbmZvc19yZXF1ZXN0GJMDIAEoCzIxLmltLnR1cm1zLnByb3RvLlF1ZX'
    'J5R3JvdXBCbG9ja2VkVXNlckluZm9zUmVxdWVzdEgAUiFxdWVyeUdyb3VwQmxvY2tlZFVzZXJJ'
    'bmZvc1JlcXVlc3QSkwEKKmNoZWNrX2dyb3VwX2pvaW5fcXVlc3Rpb25zX2Fuc3dlcnNfcmVxdW'
    'VzdBj0AyABKAsyNS5pbS50dXJtcy5wcm90by5DaGVja0dyb3VwSm9pblF1ZXN0aW9uc0Fuc3dl'
    'cnNSZXF1ZXN0SABSJWNoZWNrR3JvdXBKb2luUXVlc3Rpb25zQW5zd2Vyc1JlcXVlc3QSdgofY3'
    'JlYXRlX2dyb3VwX2ludml0YXRpb25fcmVxdWVzdBj1AyABKAsyLC5pbS50dXJtcy5wcm90by5D'
    'cmVhdGVHcm91cEludml0YXRpb25SZXF1ZXN0SABSHGNyZWF0ZUdyb3VwSW52aXRhdGlvblJlcX'
    'Vlc3QSegohY3JlYXRlX2dyb3VwX2pvaW5fcmVxdWVzdF9yZXF1ZXN0GPYDIAEoCzItLmltLnR1'
    'cm1zLnByb3RvLkNyZWF0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0SABSHWNyZWF0ZUdyb3VwSm'
    '9pblJlcXVlc3RSZXF1ZXN0EoABCiNjcmVhdGVfZ3JvdXBfam9pbl9xdWVzdGlvbnNfcmVxdWVz'
    'dBj3AyABKAsyLy5pbS50dXJtcy5wcm90by5DcmVhdGVHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZX'
    'N0SABSH2NyZWF0ZUdyb3VwSm9pblF1ZXN0aW9uc1JlcXVlc3QSdgofZGVsZXRlX2dyb3VwX2lu'
    'dml0YXRpb25fcmVxdWVzdBj4AyABKAsyLC5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cEludm'
    'l0YXRpb25SZXF1ZXN0SABSHGRlbGV0ZUdyb3VwSW52aXRhdGlvblJlcXVlc3QSegohZGVsZXRl'
    'X2dyb3VwX2pvaW5fcmVxdWVzdF9yZXF1ZXN0GPkDIAEoCzItLmltLnR1cm1zLnByb3RvLkRlbG'
    'V0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0SABSHWRlbGV0ZUdyb3VwSm9pblJlcXVlc3RSZXF1'
    'ZXN0EoABCiNkZWxldGVfZ3JvdXBfam9pbl9xdWVzdGlvbnNfcmVxdWVzdBj6AyABKAsyLy5pbS'
    '50dXJtcy5wcm90by5EZWxldGVHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0SABSH2RlbGV0ZUdy'
    'b3VwSm9pblF1ZXN0aW9uc1JlcXVlc3QSdgofcXVlcnlfZ3JvdXBfaW52aXRhdGlvbnNfcmVxdW'
    'VzdBj7AyABKAsyLC5pbS50dXJtcy5wcm90by5RdWVyeUdyb3VwSW52aXRhdGlvbnNSZXF1ZXN0'
    'SABSHHF1ZXJ5R3JvdXBJbnZpdGF0aW9uc1JlcXVlc3QSegohcXVlcnlfZ3JvdXBfam9pbl9yZX'
    'F1ZXN0c19yZXF1ZXN0GPwDIAEoCzItLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBKb2luUmVx'
    'dWVzdHNSZXF1ZXN0SABSHXF1ZXJ5R3JvdXBKb2luUmVxdWVzdHNSZXF1ZXN0En0KInF1ZXJ5X2'
    'dyb3VwX2pvaW5fcXVlc3Rpb25zX3JlcXVlc3QY/QMgASgLMi4uaW0udHVybXMucHJvdG8uUXVl'
    'cnlHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0SABSHnF1ZXJ5R3JvdXBKb2luUXVlc3Rpb25zUm'
    'VxdWVzdBJ2Ch91cGRhdGVfZ3JvdXBfaW52aXRhdGlvbl9yZXF1ZXN0GP4DIAEoCzIsLmltLnR1'
    'cm1zLnByb3RvLlVwZGF0ZUdyb3VwSW52aXRhdGlvblJlcXVlc3RIAFIcdXBkYXRlR3JvdXBJbn'
    'ZpdGF0aW9uUmVxdWVzdBJ9CiJ1cGRhdGVfZ3JvdXBfam9pbl9xdWVzdGlvbl9yZXF1ZXN0GP8D'
    'IAEoCzIuLmltLnR1cm1zLnByb3RvLlVwZGF0ZUdyb3VwSm9pblF1ZXN0aW9uUmVxdWVzdEgAUh'
    '51cGRhdGVHcm91cEpvaW5RdWVzdGlvblJlcXVlc3QSegohdXBkYXRlX2dyb3VwX2pvaW5fcmVx'
    'dWVzdF9yZXF1ZXN0GIAEIAEoCzItLmltLnR1cm1zLnByb3RvLlVwZGF0ZUdyb3VwSm9pblJlcX'
    'Vlc3RSZXF1ZXN0SABSHXVwZGF0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0EmAKF2RlbGV0ZV9y'
    'ZXNvdXJjZV9yZXF1ZXN0GOgHIAEoCzIlLmltLnR1cm1zLnByb3RvLkRlbGV0ZVJlc291cmNlUm'
    'VxdWVzdEgAUhVkZWxldGVSZXNvdXJjZVJlcXVlc3QSgwEKJHF1ZXJ5X3Jlc291cmNlX2Rvd25s'
    'b2FkX2luZm9fcmVxdWVzdBjpByABKAsyMC5pbS50dXJtcy5wcm90by5RdWVyeVJlc291cmNlRG'
    '93bmxvYWRJbmZvUmVxdWVzdEgAUiBxdWVyeVJlc291cmNlRG93bmxvYWRJbmZvUmVxdWVzdBJ9'
    'CiJxdWVyeV9yZXNvdXJjZV91cGxvYWRfaW5mb19yZXF1ZXN0GOoHIAEoCzIuLmltLnR1cm1zLn'
    'Byb3RvLlF1ZXJ5UmVzb3VyY2VVcGxvYWRJbmZvUmVxdWVzdEgAUh5xdWVyeVJlc291cmNlVXBs'
    'b2FkSW5mb1JlcXVlc3QSiQEKJnF1ZXJ5X21lc3NhZ2VfYXR0YWNobWVudF9pbmZvc19yZXF1ZX'
    'N0GOsHIAEoCzIyLmltLnR1cm1zLnByb3RvLlF1ZXJ5TWVzc2FnZUF0dGFjaG1lbnRJbmZvc1Jl'
    'cXVlc3RIAFIicXVlcnlNZXNzYWdlQXR0YWNobWVudEluZm9zUmVxdWVzdBKJAQomdXBkYXRlX2'
    '1lc3NhZ2VfYXR0YWNobWVudF9pbmZvX3JlcXVlc3QY7AcgASgLMjIuaW0udHVybXMucHJvdG8u'
    'VXBkYXRlTWVzc2FnZUF0dGFjaG1lbnRJbmZvUmVxdWVzdEgAUiJ1cGRhdGVNZXNzYWdlQXR0YW'
    'NobWVudEluZm9SZXF1ZXN0QgYKBGtpbmRCDQoLX3JlcXVlc3RfaWQ=');
