///
//  Generated code. Do not modify.
//  source: request/turms_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use turmsRequestDescriptor instead')
const TurmsRequest$json = const {
  '1': 'TurmsRequest',
  '2': const [
    const {
      '1': 'request_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'requestId',
      '17': true
    },
    const {
      '1': 'create_session_request',
      '3': 3,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateSessionRequest',
      '9': 0,
      '10': 'createSessionRequest'
    },
    const {
      '1': 'delete_session_request',
      '3': 4,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteSessionRequest',
      '9': 0,
      '10': 'deleteSessionRequest'
    },
    const {
      '1': 'query_conversations_request',
      '3': 5,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryConversationsRequest',
      '9': 0,
      '10': 'queryConversationsRequest'
    },
    const {
      '1': 'update_conversation_request',
      '3': 6,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateConversationRequest',
      '9': 0,
      '10': 'updateConversationRequest'
    },
    const {
      '1': 'update_typing_status_request',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateTypingStatusRequest',
      '9': 0,
      '10': 'updateTypingStatusRequest'
    },
    const {
      '1': 'create_message_request',
      '3': 8,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateMessageRequest',
      '9': 0,
      '10': 'createMessageRequest'
    },
    const {
      '1': 'query_messages_request',
      '3': 9,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryMessagesRequest',
      '9': 0,
      '10': 'queryMessagesRequest'
    },
    const {
      '1': 'update_message_request',
      '3': 10,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateMessageRequest',
      '9': 0,
      '10': 'updateMessageRequest'
    },
    const {
      '1': 'create_group_members_request',
      '3': 11,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupMembersRequest',
      '9': 0,
      '10': 'createGroupMembersRequest'
    },
    const {
      '1': 'delete_group_members_request',
      '3': 12,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupMembersRequest',
      '9': 0,
      '10': 'deleteGroupMembersRequest'
    },
    const {
      '1': 'query_group_members_request',
      '3': 13,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupMembersRequest',
      '9': 0,
      '10': 'queryGroupMembersRequest'
    },
    const {
      '1': 'update_group_member_request',
      '3': 14,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupMemberRequest',
      '9': 0,
      '10': 'updateGroupMemberRequest'
    },
    const {
      '1': 'query_user_profiles_request',
      '3': 100,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryUserProfilesRequest',
      '9': 0,
      '10': 'queryUserProfilesRequest'
    },
    const {
      '1': 'query_nearby_users_request',
      '3': 101,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryNearbyUsersRequest',
      '9': 0,
      '10': 'queryNearbyUsersRequest'
    },
    const {
      '1': 'query_user_online_statuses_request',
      '3': 102,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryUserOnlineStatusesRequest',
      '9': 0,
      '10': 'queryUserOnlineStatusesRequest'
    },
    const {
      '1': 'update_user_location_request',
      '3': 103,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserLocationRequest',
      '9': 0,
      '10': 'updateUserLocationRequest'
    },
    const {
      '1': 'update_user_online_status_request',
      '3': 104,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserOnlineStatusRequest',
      '9': 0,
      '10': 'updateUserOnlineStatusRequest'
    },
    const {
      '1': 'update_user_request',
      '3': 105,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateUserRequest',
      '9': 0,
      '10': 'updateUserRequest'
    },
    const {
      '1': 'create_friend_request_request',
      '3': 200,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateFriendRequestRequest',
      '9': 0,
      '10': 'createFriendRequestRequest'
    },
    const {
      '1': 'create_relationship_group_request',
      '3': 201,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateRelationshipGroupRequest',
      '9': 0,
      '10': 'createRelationshipGroupRequest'
    },
    const {
      '1': 'create_relationship_request',
      '3': 202,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateRelationshipRequest',
      '9': 0,
      '10': 'createRelationshipRequest'
    },
    const {
      '1': 'delete_relationship_group_request',
      '3': 203,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteRelationshipGroupRequest',
      '9': 0,
      '10': 'deleteRelationshipGroupRequest'
    },
    const {
      '1': 'delete_relationship_request',
      '3': 204,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteRelationshipRequest',
      '9': 0,
      '10': 'deleteRelationshipRequest'
    },
    const {
      '1': 'query_friend_requests_request',
      '3': 205,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryFriendRequestsRequest',
      '9': 0,
      '10': 'queryFriendRequestsRequest'
    },
    const {
      '1': 'query_related_user_ids_request',
      '3': 206,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryRelatedUserIdsRequest',
      '9': 0,
      '10': 'queryRelatedUserIdsRequest'
    },
    const {
      '1': 'query_relationship_groups_request',
      '3': 207,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryRelationshipGroupsRequest',
      '9': 0,
      '10': 'queryRelationshipGroupsRequest'
    },
    const {
      '1': 'query_relationships_request',
      '3': 208,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryRelationshipsRequest',
      '9': 0,
      '10': 'queryRelationshipsRequest'
    },
    const {
      '1': 'update_friend_request_request',
      '3': 209,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateFriendRequestRequest',
      '9': 0,
      '10': 'updateFriendRequestRequest'
    },
    const {
      '1': 'update_relationship_group_request',
      '3': 210,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateRelationshipGroupRequest',
      '9': 0,
      '10': 'updateRelationshipGroupRequest'
    },
    const {
      '1': 'update_relationship_request',
      '3': 211,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateRelationshipRequest',
      '9': 0,
      '10': 'updateRelationshipRequest'
    },
    const {
      '1': 'create_group_request',
      '3': 300,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupRequest',
      '9': 0,
      '10': 'createGroupRequest'
    },
    const {
      '1': 'delete_group_request',
      '3': 301,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupRequest',
      '9': 0,
      '10': 'deleteGroupRequest'
    },
    const {
      '1': 'query_groups_request',
      '3': 302,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupsRequest',
      '9': 0,
      '10': 'queryGroupsRequest'
    },
    const {
      '1': 'query_joined_group_ids_request',
      '3': 303,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryJoinedGroupIdsRequest',
      '9': 0,
      '10': 'queryJoinedGroupIdsRequest'
    },
    const {
      '1': 'query_joined_group_infos_request',
      '3': 304,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryJoinedGroupInfosRequest',
      '9': 0,
      '10': 'queryJoinedGroupInfosRequest'
    },
    const {
      '1': 'update_group_request',
      '3': 305,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupRequest',
      '9': 0,
      '10': 'updateGroupRequest'
    },
    const {
      '1': 'create_group_blocked_user_request',
      '3': 400,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupBlockedUserRequest',
      '9': 0,
      '10': 'createGroupBlockedUserRequest'
    },
    const {
      '1': 'delete_group_blocked_user_request',
      '3': 401,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupBlockedUserRequest',
      '9': 0,
      '10': 'deleteGroupBlockedUserRequest'
    },
    const {
      '1': 'query_group_blocked_user_ids_request',
      '3': 402,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupBlockedUserIdsRequest',
      '9': 0,
      '10': 'queryGroupBlockedUserIdsRequest'
    },
    const {
      '1': 'query_group_blocked_user_infos_request',
      '3': 403,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupBlockedUserInfosRequest',
      '9': 0,
      '10': 'queryGroupBlockedUserInfosRequest'
    },
    const {
      '1': 'check_group_join_questions_answers_request',
      '3': 500,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest',
      '9': 0,
      '10': 'checkGroupJoinQuestionsAnswersRequest'
    },
    const {
      '1': 'create_group_invitation_request',
      '3': 501,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupInvitationRequest',
      '9': 0,
      '10': 'createGroupInvitationRequest'
    },
    const {
      '1': 'create_group_join_request_request',
      '3': 502,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupJoinRequestRequest',
      '9': 0,
      '10': 'createGroupJoinRequestRequest'
    },
    const {
      '1': 'create_group_join_questions_request',
      '3': 503,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupJoinQuestionsRequest',
      '9': 0,
      '10': 'createGroupJoinQuestionsRequest'
    },
    const {
      '1': 'delete_group_invitation_request',
      '3': 504,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupInvitationRequest',
      '9': 0,
      '10': 'deleteGroupInvitationRequest'
    },
    const {
      '1': 'delete_group_join_request_request',
      '3': 505,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupJoinRequestRequest',
      '9': 0,
      '10': 'deleteGroupJoinRequestRequest'
    },
    const {
      '1': 'delete_group_join_questions_request',
      '3': 506,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteGroupJoinQuestionsRequest',
      '9': 0,
      '10': 'deleteGroupJoinQuestionsRequest'
    },
    const {
      '1': 'query_group_invitations_request',
      '3': 507,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupInvitationsRequest',
      '9': 0,
      '10': 'queryGroupInvitationsRequest'
    },
    const {
      '1': 'query_group_join_requests_request',
      '3': 508,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupJoinRequestsRequest',
      '9': 0,
      '10': 'queryGroupJoinRequestsRequest'
    },
    const {
      '1': 'query_group_join_questions_request',
      '3': 509,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryGroupJoinQuestionsRequest',
      '9': 0,
      '10': 'queryGroupJoinQuestionsRequest'
    },
    const {
      '1': 'update_group_join_question_request',
      '3': 510,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateGroupJoinQuestionRequest',
      '9': 0,
      '10': 'updateGroupJoinQuestionRequest'
    },
    const {
      '1': 'delete_resource_request',
      '3': 1000,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.DeleteResourceRequest',
      '9': 0,
      '10': 'deleteResourceRequest'
    },
    const {
      '1': 'query_resource_download_info_request',
      '3': 1001,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryResourceDownloadInfoRequest',
      '9': 0,
      '10': 'queryResourceDownloadInfoRequest'
    },
    const {
      '1': 'query_resource_upload_info_request',
      '3': 1002,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryResourceUploadInfoRequest',
      '9': 0,
      '10': 'queryResourceUploadInfoRequest'
    },
    const {
      '1': 'query_message_attachment_infos_request',
      '3': 1003,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.QueryMessageAttachmentInfosRequest',
      '9': 0,
      '10': 'queryMessageAttachmentInfosRequest'
    },
    const {
      '1': 'update_message_attachment_info_request',
      '3': 1004,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UpdateMessageAttachmentInfoRequest',
      '9': 0,
      '10': 'updateMessageAttachmentInfoRequest'
    },
  ],
  '8': const [
    const {'1': 'kind'},
    const {'1': '_request_id'},
  ],
};

/// Descriptor for `TurmsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsRequestDescriptor = $convert.base64Decode(
    'CgxUdXJtc1JlcXVlc3QSIgoKcmVxdWVzdF9pZBgBIAEoA0gBUglyZXF1ZXN0SWSIAQESXAoWY3JlYXRlX3Nlc3Npb25fcmVxdWVzdBgDIAEoCzIkLmltLnR1cm1zLnByb3RvLkNyZWF0ZVNlc3Npb25SZXF1ZXN0SABSFGNyZWF0ZVNlc3Npb25SZXF1ZXN0ElwKFmRlbGV0ZV9zZXNzaW9uX3JlcXVlc3QYBCABKAsyJC5pbS50dXJtcy5wcm90by5EZWxldGVTZXNzaW9uUmVxdWVzdEgAUhRkZWxldGVTZXNzaW9uUmVxdWVzdBJrChtxdWVyeV9jb252ZXJzYXRpb25zX3JlcXVlc3QYBSABKAsyKS5pbS50dXJtcy5wcm90by5RdWVyeUNvbnZlcnNhdGlvbnNSZXF1ZXN0SABSGXF1ZXJ5Q29udmVyc2F0aW9uc1JlcXVlc3QSawobdXBkYXRlX2NvbnZlcnNhdGlvbl9yZXF1ZXN0GAYgASgLMikuaW0udHVybXMucHJvdG8uVXBkYXRlQ29udmVyc2F0aW9uUmVxdWVzdEgAUhl1cGRhdGVDb252ZXJzYXRpb25SZXF1ZXN0EmwKHHVwZGF0ZV90eXBpbmdfc3RhdHVzX3JlcXVlc3QYByABKAsyKS5pbS50dXJtcy5wcm90by5VcGRhdGVUeXBpbmdTdGF0dXNSZXF1ZXN0SABSGXVwZGF0ZVR5cGluZ1N0YXR1c1JlcXVlc3QSXAoWY3JlYXRlX21lc3NhZ2VfcmVxdWVzdBgIIAEoCzIkLmltLnR1cm1zLnByb3RvLkNyZWF0ZU1lc3NhZ2VSZXF1ZXN0SABSFGNyZWF0ZU1lc3NhZ2VSZXF1ZXN0ElwKFnF1ZXJ5X21lc3NhZ2VzX3JlcXVlc3QYCSABKAsyJC5pbS50dXJtcy5wcm90by5RdWVyeU1lc3NhZ2VzUmVxdWVzdEgAUhRxdWVyeU1lc3NhZ2VzUmVxdWVzdBJcChZ1cGRhdGVfbWVzc2FnZV9yZXF1ZXN0GAogASgLMiQuaW0udHVybXMucHJvdG8uVXBkYXRlTWVzc2FnZVJlcXVlc3RIAFIUdXBkYXRlTWVzc2FnZVJlcXVlc3QSbAocY3JlYXRlX2dyb3VwX21lbWJlcnNfcmVxdWVzdBgLIAEoCzIpLmltLnR1cm1zLnByb3RvLkNyZWF0ZUdyb3VwTWVtYmVyc1JlcXVlc3RIAFIZY3JlYXRlR3JvdXBNZW1iZXJzUmVxdWVzdBJsChxkZWxldGVfZ3JvdXBfbWVtYmVyc19yZXF1ZXN0GAwgASgLMikuaW0udHVybXMucHJvdG8uRGVsZXRlR3JvdXBNZW1iZXJzUmVxdWVzdEgAUhlkZWxldGVHcm91cE1lbWJlcnNSZXF1ZXN0EmkKG3F1ZXJ5X2dyb3VwX21lbWJlcnNfcmVxdWVzdBgNIAEoCzIoLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBNZW1iZXJzUmVxdWVzdEgAUhhxdWVyeUdyb3VwTWVtYmVyc1JlcXVlc3QSaQobdXBkYXRlX2dyb3VwX21lbWJlcl9yZXF1ZXN0GA4gASgLMiguaW0udHVybXMucHJvdG8uVXBkYXRlR3JvdXBNZW1iZXJSZXF1ZXN0SABSGHVwZGF0ZUdyb3VwTWVtYmVyUmVxdWVzdBJpChtxdWVyeV91c2VyX3Byb2ZpbGVzX3JlcXVlc3QYZCABKAsyKC5pbS50dXJtcy5wcm90by5RdWVyeVVzZXJQcm9maWxlc1JlcXVlc3RIAFIYcXVlcnlVc2VyUHJvZmlsZXNSZXF1ZXN0EmYKGnF1ZXJ5X25lYXJieV91c2Vyc19yZXF1ZXN0GGUgASgLMicuaW0udHVybXMucHJvdG8uUXVlcnlOZWFyYnlVc2Vyc1JlcXVlc3RIAFIXcXVlcnlOZWFyYnlVc2Vyc1JlcXVlc3QSfAoicXVlcnlfdXNlcl9vbmxpbmVfc3RhdHVzZXNfcmVxdWVzdBhmIAEoCzIuLmltLnR1cm1zLnByb3RvLlF1ZXJ5VXNlck9ubGluZVN0YXR1c2VzUmVxdWVzdEgAUh5xdWVyeVVzZXJPbmxpbmVTdGF0dXNlc1JlcXVlc3QSbAocdXBkYXRlX3VzZXJfbG9jYXRpb25fcmVxdWVzdBhnIAEoCzIpLmltLnR1cm1zLnByb3RvLlVwZGF0ZVVzZXJMb2NhdGlvblJlcXVlc3RIAFIZdXBkYXRlVXNlckxvY2F0aW9uUmVxdWVzdBJ5CiF1cGRhdGVfdXNlcl9vbmxpbmVfc3RhdHVzX3JlcXVlc3QYaCABKAsyLS5pbS50dXJtcy5wcm90by5VcGRhdGVVc2VyT25saW5lU3RhdHVzUmVxdWVzdEgAUh11cGRhdGVVc2VyT25saW5lU3RhdHVzUmVxdWVzdBJTChN1cGRhdGVfdXNlcl9yZXF1ZXN0GGkgASgLMiEuaW0udHVybXMucHJvdG8uVXBkYXRlVXNlclJlcXVlc3RIAFIRdXBkYXRlVXNlclJlcXVlc3QScAodY3JlYXRlX2ZyaWVuZF9yZXF1ZXN0X3JlcXVlc3QYyAEgASgLMiouaW0udHVybXMucHJvdG8uQ3JlYXRlRnJpZW5kUmVxdWVzdFJlcXVlc3RIAFIaY3JlYXRlRnJpZW5kUmVxdWVzdFJlcXVlc3QSfAohY3JlYXRlX3JlbGF0aW9uc2hpcF9ncm91cF9yZXF1ZXN0GMkBIAEoCzIuLmltLnR1cm1zLnByb3RvLkNyZWF0ZVJlbGF0aW9uc2hpcEdyb3VwUmVxdWVzdEgAUh5jcmVhdGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3QSbAobY3JlYXRlX3JlbGF0aW9uc2hpcF9yZXF1ZXN0GMoBIAEoCzIpLmltLnR1cm1zLnByb3RvLkNyZWF0ZVJlbGF0aW9uc2hpcFJlcXVlc3RIAFIZY3JlYXRlUmVsYXRpb25zaGlwUmVxdWVzdBJ8CiFkZWxldGVfcmVsYXRpb25zaGlwX2dyb3VwX3JlcXVlc3QYywEgASgLMi4uaW0udHVybXMucHJvdG8uRGVsZXRlUmVsYXRpb25zaGlwR3JvdXBSZXF1ZXN0SABSHmRlbGV0ZVJlbGF0aW9uc2hpcEdyb3VwUmVxdWVzdBJsChtkZWxldGVfcmVsYXRpb25zaGlwX3JlcXVlc3QYzAEgASgLMikuaW0udHVybXMucHJvdG8uRGVsZXRlUmVsYXRpb25zaGlwUmVxdWVzdEgAUhlkZWxldGVSZWxhdGlvbnNoaXBSZXF1ZXN0EnAKHXF1ZXJ5X2ZyaWVuZF9yZXF1ZXN0c19yZXF1ZXN0GM0BIAEoCzIqLmltLnR1cm1zLnByb3RvLlF1ZXJ5RnJpZW5kUmVxdWVzdHNSZXF1ZXN0SABSGnF1ZXJ5RnJpZW5kUmVxdWVzdHNSZXF1ZXN0EnEKHnF1ZXJ5X3JlbGF0ZWRfdXNlcl9pZHNfcmVxdWVzdBjOASABKAsyKi5pbS50dXJtcy5wcm90by5RdWVyeVJlbGF0ZWRVc2VySWRzUmVxdWVzdEgAUhpxdWVyeVJlbGF0ZWRVc2VySWRzUmVxdWVzdBJ8CiFxdWVyeV9yZWxhdGlvbnNoaXBfZ3JvdXBzX3JlcXVlc3QYzwEgASgLMi4uaW0udHVybXMucHJvdG8uUXVlcnlSZWxhdGlvbnNoaXBHcm91cHNSZXF1ZXN0SABSHnF1ZXJ5UmVsYXRpb25zaGlwR3JvdXBzUmVxdWVzdBJsChtxdWVyeV9yZWxhdGlvbnNoaXBzX3JlcXVlc3QY0AEgASgLMikuaW0udHVybXMucHJvdG8uUXVlcnlSZWxhdGlvbnNoaXBzUmVxdWVzdEgAUhlxdWVyeVJlbGF0aW9uc2hpcHNSZXF1ZXN0EnAKHXVwZGF0ZV9mcmllbmRfcmVxdWVzdF9yZXF1ZXN0GNEBIAEoCzIqLmltLnR1cm1zLnByb3RvLlVwZGF0ZUZyaWVuZFJlcXVlc3RSZXF1ZXN0SABSGnVwZGF0ZUZyaWVuZFJlcXVlc3RSZXF1ZXN0EnwKIXVwZGF0ZV9yZWxhdGlvbnNoaXBfZ3JvdXBfcmVxdWVzdBjSASABKAsyLi5pbS50dXJtcy5wcm90by5VcGRhdGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3RIAFIedXBkYXRlUmVsYXRpb25zaGlwR3JvdXBSZXF1ZXN0EmwKG3VwZGF0ZV9yZWxhdGlvbnNoaXBfcmVxdWVzdBjTASABKAsyKS5pbS50dXJtcy5wcm90by5VcGRhdGVSZWxhdGlvbnNoaXBSZXF1ZXN0SABSGXVwZGF0ZVJlbGF0aW9uc2hpcFJlcXVlc3QSVwoUY3JlYXRlX2dyb3VwX3JlcXVlc3QYrAIgASgLMiIuaW0udHVybXMucHJvdG8uQ3JlYXRlR3JvdXBSZXF1ZXN0SABSEmNyZWF0ZUdyb3VwUmVxdWVzdBJXChRkZWxldGVfZ3JvdXBfcmVxdWVzdBitAiABKAsyIi5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cFJlcXVlc3RIAFISZGVsZXRlR3JvdXBSZXF1ZXN0ElcKFHF1ZXJ5X2dyb3Vwc19yZXF1ZXN0GK4CIAEoCzIiLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBzUmVxdWVzdEgAUhJxdWVyeUdyb3Vwc1JlcXVlc3QScQoecXVlcnlfam9pbmVkX2dyb3VwX2lkc19yZXF1ZXN0GK8CIAEoCzIqLmltLnR1cm1zLnByb3RvLlF1ZXJ5Sm9pbmVkR3JvdXBJZHNSZXF1ZXN0SABSGnF1ZXJ5Sm9pbmVkR3JvdXBJZHNSZXF1ZXN0EncKIHF1ZXJ5X2pvaW5lZF9ncm91cF9pbmZvc19yZXF1ZXN0GLACIAEoCzIsLmltLnR1cm1zLnByb3RvLlF1ZXJ5Sm9pbmVkR3JvdXBJbmZvc1JlcXVlc3RIAFIccXVlcnlKb2luZWRHcm91cEluZm9zUmVxdWVzdBJXChR1cGRhdGVfZ3JvdXBfcmVxdWVzdBixAiABKAsyIi5pbS50dXJtcy5wcm90by5VcGRhdGVHcm91cFJlcXVlc3RIAFISdXBkYXRlR3JvdXBSZXF1ZXN0EnoKIWNyZWF0ZV9ncm91cF9ibG9ja2VkX3VzZXJfcmVxdWVzdBiQAyABKAsyLS5pbS50dXJtcy5wcm90by5DcmVhdGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdEgAUh1jcmVhdGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdBJ6CiFkZWxldGVfZ3JvdXBfYmxvY2tlZF91c2VyX3JlcXVlc3QYkQMgASgLMi0uaW0udHVybXMucHJvdG8uRGVsZXRlR3JvdXBCbG9ja2VkVXNlclJlcXVlc3RIAFIdZGVsZXRlR3JvdXBCbG9ja2VkVXNlclJlcXVlc3QSgQEKJHF1ZXJ5X2dyb3VwX2Jsb2NrZWRfdXNlcl9pZHNfcmVxdWVzdBiSAyABKAsyLy5pbS50dXJtcy5wcm90by5RdWVyeUdyb3VwQmxvY2tlZFVzZXJJZHNSZXF1ZXN0SABSH3F1ZXJ5R3JvdXBCbG9ja2VkVXNlcklkc1JlcXVlc3QShwEKJnF1ZXJ5X2dyb3VwX2Jsb2NrZWRfdXNlcl9pbmZvc19yZXF1ZXN0GJMDIAEoCzIxLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBCbG9ja2VkVXNlckluZm9zUmVxdWVzdEgAUiFxdWVyeUdyb3VwQmxvY2tlZFVzZXJJbmZvc1JlcXVlc3QSkwEKKmNoZWNrX2dyb3VwX2pvaW5fcXVlc3Rpb25zX2Fuc3dlcnNfcmVxdWVzdBj0AyABKAsyNS5pbS50dXJtcy5wcm90by5DaGVja0dyb3VwSm9pblF1ZXN0aW9uc0Fuc3dlcnNSZXF1ZXN0SABSJWNoZWNrR3JvdXBKb2luUXVlc3Rpb25zQW5zd2Vyc1JlcXVlc3QSdgofY3JlYXRlX2dyb3VwX2ludml0YXRpb25fcmVxdWVzdBj1AyABKAsyLC5pbS50dXJtcy5wcm90by5DcmVhdGVHcm91cEludml0YXRpb25SZXF1ZXN0SABSHGNyZWF0ZUdyb3VwSW52aXRhdGlvblJlcXVlc3QSegohY3JlYXRlX2dyb3VwX2pvaW5fcmVxdWVzdF9yZXF1ZXN0GPYDIAEoCzItLmltLnR1cm1zLnByb3RvLkNyZWF0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0SABSHWNyZWF0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0EoABCiNjcmVhdGVfZ3JvdXBfam9pbl9xdWVzdGlvbnNfcmVxdWVzdBj3AyABKAsyLy5pbS50dXJtcy5wcm90by5DcmVhdGVHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0SABSH2NyZWF0ZUdyb3VwSm9pblF1ZXN0aW9uc1JlcXVlc3QSdgofZGVsZXRlX2dyb3VwX2ludml0YXRpb25fcmVxdWVzdBj4AyABKAsyLC5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cEludml0YXRpb25SZXF1ZXN0SABSHGRlbGV0ZUdyb3VwSW52aXRhdGlvblJlcXVlc3QSegohZGVsZXRlX2dyb3VwX2pvaW5fcmVxdWVzdF9yZXF1ZXN0GPkDIAEoCzItLmltLnR1cm1zLnByb3RvLkRlbGV0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0SABSHWRlbGV0ZUdyb3VwSm9pblJlcXVlc3RSZXF1ZXN0EoABCiNkZWxldGVfZ3JvdXBfam9pbl9xdWVzdGlvbnNfcmVxdWVzdBj6AyABKAsyLy5pbS50dXJtcy5wcm90by5EZWxldGVHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0SABSH2RlbGV0ZUdyb3VwSm9pblF1ZXN0aW9uc1JlcXVlc3QSdgofcXVlcnlfZ3JvdXBfaW52aXRhdGlvbnNfcmVxdWVzdBj7AyABKAsyLC5pbS50dXJtcy5wcm90by5RdWVyeUdyb3VwSW52aXRhdGlvbnNSZXF1ZXN0SABSHHF1ZXJ5R3JvdXBJbnZpdGF0aW9uc1JlcXVlc3QSegohcXVlcnlfZ3JvdXBfam9pbl9yZXF1ZXN0c19yZXF1ZXN0GPwDIAEoCzItLmltLnR1cm1zLnByb3RvLlF1ZXJ5R3JvdXBKb2luUmVxdWVzdHNSZXF1ZXN0SABSHXF1ZXJ5R3JvdXBKb2luUmVxdWVzdHNSZXF1ZXN0En0KInF1ZXJ5X2dyb3VwX2pvaW5fcXVlc3Rpb25zX3JlcXVlc3QY/QMgASgLMi4uaW0udHVybXMucHJvdG8uUXVlcnlHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0SABSHnF1ZXJ5R3JvdXBKb2luUXVlc3Rpb25zUmVxdWVzdBJ9CiJ1cGRhdGVfZ3JvdXBfam9pbl9xdWVzdGlvbl9yZXF1ZXN0GP4DIAEoCzIuLmltLnR1cm1zLnByb3RvLlVwZGF0ZUdyb3VwSm9pblF1ZXN0aW9uUmVxdWVzdEgAUh51cGRhdGVHcm91cEpvaW5RdWVzdGlvblJlcXVlc3QSYAoXZGVsZXRlX3Jlc291cmNlX3JlcXVlc3QY6AcgASgLMiUuaW0udHVybXMucHJvdG8uRGVsZXRlUmVzb3VyY2VSZXF1ZXN0SABSFWRlbGV0ZVJlc291cmNlUmVxdWVzdBKDAQokcXVlcnlfcmVzb3VyY2VfZG93bmxvYWRfaW5mb19yZXF1ZXN0GOkHIAEoCzIwLmltLnR1cm1zLnByb3RvLlF1ZXJ5UmVzb3VyY2VEb3dubG9hZEluZm9SZXF1ZXN0SABSIHF1ZXJ5UmVzb3VyY2VEb3dubG9hZEluZm9SZXF1ZXN0En0KInF1ZXJ5X3Jlc291cmNlX3VwbG9hZF9pbmZvX3JlcXVlc3QY6gcgASgLMi4uaW0udHVybXMucHJvdG8uUXVlcnlSZXNvdXJjZVVwbG9hZEluZm9SZXF1ZXN0SABSHnF1ZXJ5UmVzb3VyY2VVcGxvYWRJbmZvUmVxdWVzdBKJAQomcXVlcnlfbWVzc2FnZV9hdHRhY2htZW50X2luZm9zX3JlcXVlc3QY6wcgASgLMjIuaW0udHVybXMucHJvdG8uUXVlcnlNZXNzYWdlQXR0YWNobWVudEluZm9zUmVxdWVzdEgAUiJxdWVyeU1lc3NhZ2VBdHRhY2htZW50SW5mb3NSZXF1ZXN0EokBCiZ1cGRhdGVfbWVzc2FnZV9hdHRhY2htZW50X2luZm9fcmVxdWVzdBjsByABKAsyMi5pbS50dXJtcy5wcm90by5VcGRhdGVNZXNzYWdlQXR0YWNobWVudEluZm9SZXF1ZXN0SABSInVwZGF0ZU1lc3NhZ2VBdHRhY2htZW50SW5mb1JlcXVlc3RCBgoEa2luZEINCgtfcmVxdWVzdF9pZA==');
