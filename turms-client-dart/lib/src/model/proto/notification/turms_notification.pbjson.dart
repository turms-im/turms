///
//  Generated code. Do not modify.
//  source: notification/turms_notification.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use turmsNotificationDescriptor instead')
const TurmsNotification$json = const {
  '1': 'TurmsNotification',
  '2': const [
    const {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
    const {
      '1': 'request_id',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'requestId',
      '17': true
    },
    const {
      '1': 'code',
      '3': 5,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'code',
      '17': true
    },
    const {
      '1': 'reason',
      '3': 6,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'reason',
      '17': true
    },
    const {
      '1': 'data',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.TurmsNotification.Data',
      '10': 'data'
    },
    const {
      '1': 'requester_id',
      '3': 10,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'requesterId',
      '17': true
    },
    const {
      '1': 'close_status',
      '3': 11,
      '4': 1,
      '5': 5,
      '9': 4,
      '10': 'closeStatus',
      '17': true
    },
    const {
      '1': 'relayed_request',
      '3': 12,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.TurmsRequest',
      '10': 'relayedRequest'
    },
  ],
  '3': const [TurmsNotification_Data$json],
  '8': const [
    const {'1': '_request_id'},
    const {'1': '_code'},
    const {'1': '_reason'},
    const {'1': '_requester_id'},
    const {'1': '_close_status'},
  ],
};

@$core.Deprecated('Use turmsNotificationDescriptor instead')
const TurmsNotification_Data$json = const {
  '1': 'Data',
  '2': const [
    const {'1': 'long', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'long'},
    const {'1': 'string', '3': 2, '4': 1, '5': 9, '9': 0, '10': 'string'},
    const {
      '1': 'longs_with_version',
      '3': 3,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.LongsWithVersion',
      '9': 0,
      '10': 'longsWithVersion'
    },
    const {
      '1': 'strings_with_version',
      '3': 4,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.StringsWithVersion',
      '9': 0,
      '10': 'stringsWithVersion'
    },
    const {
      '1': 'conversations',
      '3': 5,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Conversations',
      '9': 0,
      '10': 'conversations'
    },
    const {
      '1': 'messages',
      '3': 6,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Messages',
      '9': 0,
      '10': 'messages'
    },
    const {
      '1': 'messages_with_total_list',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.MessagesWithTotalList',
      '9': 0,
      '10': 'messagesWithTotalList'
    },
    const {
      '1': 'user_session',
      '3': 8,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserSession',
      '9': 0,
      '10': 'userSession'
    },
    const {
      '1': 'user_infos_with_version',
      '3': 9,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserInfosWithVersion',
      '9': 0,
      '10': 'userInfosWithVersion'
    },
    const {
      '1': 'user_online_statuses',
      '3': 10,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserOnlineStatuses',
      '9': 0,
      '10': 'userOnlineStatuses'
    },
    const {
      '1': 'user_friend_requests_with_version',
      '3': 11,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserFriendRequestsWithVersion',
      '9': 0,
      '10': 'userFriendRequestsWithVersion'
    },
    const {
      '1': 'user_relationship_groups_with_version',
      '3': 12,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserRelationshipGroupsWithVersion',
      '9': 0,
      '10': 'userRelationshipGroupsWithVersion'
    },
    const {
      '1': 'user_relationships_with_version',
      '3': 13,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserRelationshipsWithVersion',
      '9': 0,
      '10': 'userRelationshipsWithVersion'
    },
    const {
      '1': 'nearby_users',
      '3': 14,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.NearbyUsers',
      '9': 0,
      '10': 'nearbyUsers'
    },
    const {
      '1': 'group_invitations_with_version',
      '3': 15,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupInvitationsWithVersion',
      '9': 0,
      '10': 'groupInvitationsWithVersion'
    },
    const {
      '1': 'group_join_question_answer_result',
      '3': 16,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinQuestionsAnswerResult',
      '9': 0,
      '10': 'groupJoinQuestionAnswerResult'
    },
    const {
      '1': 'group_join_requests_with_version',
      '3': 17,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinRequestsWithVersion',
      '9': 0,
      '10': 'groupJoinRequestsWithVersion'
    },
    const {
      '1': 'group_join_questions_with_version',
      '3': 18,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinQuestionsWithVersion',
      '9': 0,
      '10': 'groupJoinQuestionsWithVersion'
    },
    const {
      '1': 'group_members_with_version',
      '3': 19,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupMembersWithVersion',
      '9': 0,
      '10': 'groupMembersWithVersion'
    },
    const {
      '1': 'groups_with_version',
      '3': 20,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupsWithVersion',
      '9': 0,
      '10': 'groupsWithVersion'
    },
    const {
      '1': 'storage_resource_infos',
      '3': 50,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.StorageResourceInfos',
      '9': 0,
      '10': 'storageResourceInfos'
    },
  ],
  '8': const [
    const {'1': 'kind'},
  ],
};

/// Descriptor for `TurmsNotification`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsNotificationDescriptor = $convert.base64Decode(
    'ChFUdXJtc05vdGlmaWNhdGlvbhIcCgl0aW1lc3RhbXAYASABKANSCXRpbWVzdGFtcBIiCgpyZXF1ZXN0X2lkGAQgASgDSABSCXJlcXVlc3RJZIgBARIXCgRjb2RlGAUgASgFSAFSBGNvZGWIAQESGwoGcmVhc29uGAYgASgJSAJSBnJlYXNvbogBARI6CgRkYXRhGAcgASgLMiYuaW0udHVybXMucHJvdG8uVHVybXNOb3RpZmljYXRpb24uRGF0YVIEZGF0YRImCgxyZXF1ZXN0ZXJfaWQYCiABKANIA1ILcmVxdWVzdGVySWSIAQESJgoMY2xvc2Vfc3RhdHVzGAsgASgFSARSC2Nsb3NlU3RhdHVziAEBEkUKD3JlbGF5ZWRfcmVxdWVzdBgMIAEoCzIcLmltLnR1cm1zLnByb3RvLlR1cm1zUmVxdWVzdFIOcmVsYXllZFJlcXVlc3Qa/A4KBERhdGESFAoEbG9uZxgBIAEoA0gAUgRsb25nEhgKBnN0cmluZxgCIAEoCUgAUgZzdHJpbmcSUAoSbG9uZ3Nfd2l0aF92ZXJzaW9uGAMgASgLMiAuaW0udHVybXMucHJvdG8uTG9uZ3NXaXRoVmVyc2lvbkgAUhBsb25nc1dpdGhWZXJzaW9uElYKFHN0cmluZ3Nfd2l0aF92ZXJzaW9uGAQgASgLMiIuaW0udHVybXMucHJvdG8uU3RyaW5nc1dpdGhWZXJzaW9uSABSEnN0cmluZ3NXaXRoVmVyc2lvbhJFCg1jb252ZXJzYXRpb25zGAUgASgLMh0uaW0udHVybXMucHJvdG8uQ29udmVyc2F0aW9uc0gAUg1jb252ZXJzYXRpb25zEjYKCG1lc3NhZ2VzGAYgASgLMhguaW0udHVybXMucHJvdG8uTWVzc2FnZXNIAFIIbWVzc2FnZXMSYAoYbWVzc2FnZXNfd2l0aF90b3RhbF9saXN0GAcgASgLMiUuaW0udHVybXMucHJvdG8uTWVzc2FnZXNXaXRoVG90YWxMaXN0SABSFW1lc3NhZ2VzV2l0aFRvdGFsTGlzdBJACgx1c2VyX3Nlc3Npb24YCCABKAsyGy5pbS50dXJtcy5wcm90by5Vc2VyU2Vzc2lvbkgAUgt1c2VyU2Vzc2lvbhJdChd1c2VyX2luZm9zX3dpdGhfdmVyc2lvbhgJIAEoCzIkLmltLnR1cm1zLnByb3RvLlVzZXJJbmZvc1dpdGhWZXJzaW9uSABSFHVzZXJJbmZvc1dpdGhWZXJzaW9uElYKFHVzZXJfb25saW5lX3N0YXR1c2VzGAogASgLMiIuaW0udHVybXMucHJvdG8uVXNlck9ubGluZVN0YXR1c2VzSABSEnVzZXJPbmxpbmVTdGF0dXNlcxJ5CiF1c2VyX2ZyaWVuZF9yZXF1ZXN0c193aXRoX3ZlcnNpb24YCyABKAsyLS5pbS50dXJtcy5wcm90by5Vc2VyRnJpZW5kUmVxdWVzdHNXaXRoVmVyc2lvbkgAUh11c2VyRnJpZW5kUmVxdWVzdHNXaXRoVmVyc2lvbhKFAQoldXNlcl9yZWxhdGlvbnNoaXBfZ3JvdXBzX3dpdGhfdmVyc2lvbhgMIAEoCzIxLmltLnR1cm1zLnByb3RvLlVzZXJSZWxhdGlvbnNoaXBHcm91cHNXaXRoVmVyc2lvbkgAUiF1c2VyUmVsYXRpb25zaGlwR3JvdXBzV2l0aFZlcnNpb24SdQofdXNlcl9yZWxhdGlvbnNoaXBzX3dpdGhfdmVyc2lvbhgNIAEoCzIsLmltLnR1cm1zLnByb3RvLlVzZXJSZWxhdGlvbnNoaXBzV2l0aFZlcnNpb25IAFIcdXNlclJlbGF0aW9uc2hpcHNXaXRoVmVyc2lvbhJACgxuZWFyYnlfdXNlcnMYDiABKAsyGy5pbS50dXJtcy5wcm90by5OZWFyYnlVc2Vyc0gAUgtuZWFyYnlVc2VycxJyCh5ncm91cF9pbnZpdGF0aW9uc193aXRoX3ZlcnNpb24YDyABKAsyKy5pbS50dXJtcy5wcm90by5Hcm91cEludml0YXRpb25zV2l0aFZlcnNpb25IAFIbZ3JvdXBJbnZpdGF0aW9uc1dpdGhWZXJzaW9uEnoKIWdyb3VwX2pvaW5fcXVlc3Rpb25fYW5zd2VyX3Jlc3VsdBgQIAEoCzIuLmltLnR1cm1zLnByb3RvLkdyb3VwSm9pblF1ZXN0aW9uc0Fuc3dlclJlc3VsdEgAUh1ncm91cEpvaW5RdWVzdGlvbkFuc3dlclJlc3VsdBJ2CiBncm91cF9qb2luX3JlcXVlc3RzX3dpdGhfdmVyc2lvbhgRIAEoCzIsLmltLnR1cm1zLnByb3RvLkdyb3VwSm9pblJlcXVlc3RzV2l0aFZlcnNpb25IAFIcZ3JvdXBKb2luUmVxdWVzdHNXaXRoVmVyc2lvbhJ5CiFncm91cF9qb2luX3F1ZXN0aW9uc193aXRoX3ZlcnNpb24YEiABKAsyLS5pbS50dXJtcy5wcm90by5Hcm91cEpvaW5RdWVzdGlvbnNXaXRoVmVyc2lvbkgAUh1ncm91cEpvaW5RdWVzdGlvbnNXaXRoVmVyc2lvbhJmChpncm91cF9tZW1iZXJzX3dpdGhfdmVyc2lvbhgTIAEoCzInLmltLnR1cm1zLnByb3RvLkdyb3VwTWVtYmVyc1dpdGhWZXJzaW9uSABSF2dyb3VwTWVtYmVyc1dpdGhWZXJzaW9uElMKE2dyb3Vwc193aXRoX3ZlcnNpb24YFCABKAsyIS5pbS50dXJtcy5wcm90by5Hcm91cHNXaXRoVmVyc2lvbkgAUhFncm91cHNXaXRoVmVyc2lvbhJcChZzdG9yYWdlX3Jlc291cmNlX2luZm9zGDIgASgLMiQuaW0udHVybXMucHJvdG8uU3RvcmFnZVJlc291cmNlSW5mb3NIAFIUc3RvcmFnZVJlc291cmNlSW5mb3NCBgoEa2luZEINCgtfcmVxdWVzdF9pZEIHCgVfY29kZUIJCgdfcmVhc29uQg8KDV9yZXF1ZXN0ZXJfaWRCDwoNX2Nsb3NlX3N0YXR1cw==');
