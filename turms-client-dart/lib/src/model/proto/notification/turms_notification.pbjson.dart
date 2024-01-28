//
//  Generated code. Do not modify.
//  source: notification/turms_notification.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use turmsNotificationDescriptor instead')
const TurmsNotification$json = {
  '1': 'TurmsNotification',
  '2': [
    {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
    {
      '1': 'request_id',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'requestId',
      '17': true
    },
    {'1': 'code', '3': 5, '4': 1, '5': 5, '9': 1, '10': 'code', '17': true},
    {'1': 'reason', '3': 6, '4': 1, '5': 9, '9': 2, '10': 'reason', '17': true},
    {
      '1': 'data',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.TurmsNotification.Data',
      '10': 'data'
    },
    {
      '1': 'requester_id',
      '3': 10,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'requesterId',
      '17': true
    },
    {
      '1': 'close_status',
      '3': 11,
      '4': 1,
      '5': 5,
      '9': 4,
      '10': 'closeStatus',
      '17': true
    },
    {
      '1': 'relayed_request',
      '3': 12,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.TurmsRequest',
      '10': 'relayedRequest'
    },
  ],
  '3': [TurmsNotification_Data$json],
  '8': [
    {'1': '_request_id'},
    {'1': '_code'},
    {'1': '_reason'},
    {'1': '_requester_id'},
    {'1': '_close_status'},
  ],
};

@$core.Deprecated('Use turmsNotificationDescriptor instead')
const TurmsNotification_Data$json = {
  '1': 'Data',
  '2': [
    {'1': 'long', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'long'},
    {'1': 'string', '3': 2, '4': 1, '5': 9, '9': 0, '10': 'string'},
    {
      '1': 'longs_with_version',
      '3': 3,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.LongsWithVersion',
      '9': 0,
      '10': 'longsWithVersion'
    },
    {
      '1': 'strings_with_version',
      '3': 4,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.StringsWithVersion',
      '9': 0,
      '10': 'stringsWithVersion'
    },
    {
      '1': 'conversations',
      '3': 5,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Conversations',
      '9': 0,
      '10': 'conversations'
    },
    {
      '1': 'messages',
      '3': 6,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Messages',
      '9': 0,
      '10': 'messages'
    },
    {
      '1': 'messages_with_total_list',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.MessagesWithTotalList',
      '9': 0,
      '10': 'messagesWithTotalList'
    },
    {
      '1': 'user_session',
      '3': 8,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserSession',
      '9': 0,
      '10': 'userSession'
    },
    {
      '1': 'user_infos_with_version',
      '3': 9,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserInfosWithVersion',
      '9': 0,
      '10': 'userInfosWithVersion'
    },
    {
      '1': 'user_online_statuses',
      '3': 10,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserOnlineStatuses',
      '9': 0,
      '10': 'userOnlineStatuses'
    },
    {
      '1': 'user_friend_requests_with_version',
      '3': 11,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserFriendRequestsWithVersion',
      '9': 0,
      '10': 'userFriendRequestsWithVersion'
    },
    {
      '1': 'user_relationship_groups_with_version',
      '3': 12,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserRelationshipGroupsWithVersion',
      '9': 0,
      '10': 'userRelationshipGroupsWithVersion'
    },
    {
      '1': 'user_relationships_with_version',
      '3': 13,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserRelationshipsWithVersion',
      '9': 0,
      '10': 'userRelationshipsWithVersion'
    },
    {
      '1': 'nearby_users',
      '3': 14,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.NearbyUsers',
      '9': 0,
      '10': 'nearbyUsers'
    },
    {
      '1': 'group_invitations_with_version',
      '3': 15,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupInvitationsWithVersion',
      '9': 0,
      '10': 'groupInvitationsWithVersion'
    },
    {
      '1': 'group_join_question_answer_result',
      '3': 16,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinQuestionsAnswerResult',
      '9': 0,
      '10': 'groupJoinQuestionAnswerResult'
    },
    {
      '1': 'group_join_requests_with_version',
      '3': 17,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinRequestsWithVersion',
      '9': 0,
      '10': 'groupJoinRequestsWithVersion'
    },
    {
      '1': 'group_join_questions_with_version',
      '3': 18,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinQuestionsWithVersion',
      '9': 0,
      '10': 'groupJoinQuestionsWithVersion'
    },
    {
      '1': 'group_members_with_version',
      '3': 19,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupMembersWithVersion',
      '9': 0,
      '10': 'groupMembersWithVersion'
    },
    {
      '1': 'groups_with_version',
      '3': 20,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.GroupsWithVersion',
      '9': 0,
      '10': 'groupsWithVersion'
    },
    {
      '1': 'storage_resource_infos',
      '3': 50,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.StorageResourceInfos',
      '9': 0,
      '10': 'storageResourceInfos'
    },
  ],
  '8': [
    {'1': 'kind'},
  ],
};

/// Descriptor for `TurmsNotification`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsNotificationDescriptor = $convert.base64Decode(
    'ChFUdXJtc05vdGlmaWNhdGlvbhIcCgl0aW1lc3RhbXAYASABKANSCXRpbWVzdGFtcBIiCgpyZX'
    'F1ZXN0X2lkGAQgASgDSABSCXJlcXVlc3RJZIgBARIXCgRjb2RlGAUgASgFSAFSBGNvZGWIAQES'
    'GwoGcmVhc29uGAYgASgJSAJSBnJlYXNvbogBARI6CgRkYXRhGAcgASgLMiYuaW0udHVybXMucH'
    'JvdG8uVHVybXNOb3RpZmljYXRpb24uRGF0YVIEZGF0YRImCgxyZXF1ZXN0ZXJfaWQYCiABKANI'
    'A1ILcmVxdWVzdGVySWSIAQESJgoMY2xvc2Vfc3RhdHVzGAsgASgFSARSC2Nsb3NlU3RhdHVziA'
    'EBEkUKD3JlbGF5ZWRfcmVxdWVzdBgMIAEoCzIcLmltLnR1cm1zLnByb3RvLlR1cm1zUmVxdWVz'
    'dFIOcmVsYXllZFJlcXVlc3Qa/A4KBERhdGESFAoEbG9uZxgBIAEoA0gAUgRsb25nEhgKBnN0cm'
    'luZxgCIAEoCUgAUgZzdHJpbmcSUAoSbG9uZ3Nfd2l0aF92ZXJzaW9uGAMgASgLMiAuaW0udHVy'
    'bXMucHJvdG8uTG9uZ3NXaXRoVmVyc2lvbkgAUhBsb25nc1dpdGhWZXJzaW9uElYKFHN0cmluZ3'
    'Nfd2l0aF92ZXJzaW9uGAQgASgLMiIuaW0udHVybXMucHJvdG8uU3RyaW5nc1dpdGhWZXJzaW9u'
    'SABSEnN0cmluZ3NXaXRoVmVyc2lvbhJFCg1jb252ZXJzYXRpb25zGAUgASgLMh0uaW0udHVybX'
    'MucHJvdG8uQ29udmVyc2F0aW9uc0gAUg1jb252ZXJzYXRpb25zEjYKCG1lc3NhZ2VzGAYgASgL'
    'MhguaW0udHVybXMucHJvdG8uTWVzc2FnZXNIAFIIbWVzc2FnZXMSYAoYbWVzc2FnZXNfd2l0aF'
    '90b3RhbF9saXN0GAcgASgLMiUuaW0udHVybXMucHJvdG8uTWVzc2FnZXNXaXRoVG90YWxMaXN0'
    'SABSFW1lc3NhZ2VzV2l0aFRvdGFsTGlzdBJACgx1c2VyX3Nlc3Npb24YCCABKAsyGy5pbS50dX'
    'Jtcy5wcm90by5Vc2VyU2Vzc2lvbkgAUgt1c2VyU2Vzc2lvbhJdChd1c2VyX2luZm9zX3dpdGhf'
    'dmVyc2lvbhgJIAEoCzIkLmltLnR1cm1zLnByb3RvLlVzZXJJbmZvc1dpdGhWZXJzaW9uSABSFH'
    'VzZXJJbmZvc1dpdGhWZXJzaW9uElYKFHVzZXJfb25saW5lX3N0YXR1c2VzGAogASgLMiIuaW0u'
    'dHVybXMucHJvdG8uVXNlck9ubGluZVN0YXR1c2VzSABSEnVzZXJPbmxpbmVTdGF0dXNlcxJ5Ci'
    'F1c2VyX2ZyaWVuZF9yZXF1ZXN0c193aXRoX3ZlcnNpb24YCyABKAsyLS5pbS50dXJtcy5wcm90'
    'by5Vc2VyRnJpZW5kUmVxdWVzdHNXaXRoVmVyc2lvbkgAUh11c2VyRnJpZW5kUmVxdWVzdHNXaX'
    'RoVmVyc2lvbhKFAQoldXNlcl9yZWxhdGlvbnNoaXBfZ3JvdXBzX3dpdGhfdmVyc2lvbhgMIAEo'
    'CzIxLmltLnR1cm1zLnByb3RvLlVzZXJSZWxhdGlvbnNoaXBHcm91cHNXaXRoVmVyc2lvbkgAUi'
    'F1c2VyUmVsYXRpb25zaGlwR3JvdXBzV2l0aFZlcnNpb24SdQofdXNlcl9yZWxhdGlvbnNoaXBz'
    'X3dpdGhfdmVyc2lvbhgNIAEoCzIsLmltLnR1cm1zLnByb3RvLlVzZXJSZWxhdGlvbnNoaXBzV2'
    'l0aFZlcnNpb25IAFIcdXNlclJlbGF0aW9uc2hpcHNXaXRoVmVyc2lvbhJACgxuZWFyYnlfdXNl'
    'cnMYDiABKAsyGy5pbS50dXJtcy5wcm90by5OZWFyYnlVc2Vyc0gAUgtuZWFyYnlVc2VycxJyCh'
    '5ncm91cF9pbnZpdGF0aW9uc193aXRoX3ZlcnNpb24YDyABKAsyKy5pbS50dXJtcy5wcm90by5H'
    'cm91cEludml0YXRpb25zV2l0aFZlcnNpb25IAFIbZ3JvdXBJbnZpdGF0aW9uc1dpdGhWZXJzaW'
    '9uEnoKIWdyb3VwX2pvaW5fcXVlc3Rpb25fYW5zd2VyX3Jlc3VsdBgQIAEoCzIuLmltLnR1cm1z'
    'LnByb3RvLkdyb3VwSm9pblF1ZXN0aW9uc0Fuc3dlclJlc3VsdEgAUh1ncm91cEpvaW5RdWVzdG'
    'lvbkFuc3dlclJlc3VsdBJ2CiBncm91cF9qb2luX3JlcXVlc3RzX3dpdGhfdmVyc2lvbhgRIAEo'
    'CzIsLmltLnR1cm1zLnByb3RvLkdyb3VwSm9pblJlcXVlc3RzV2l0aFZlcnNpb25IAFIcZ3JvdX'
    'BKb2luUmVxdWVzdHNXaXRoVmVyc2lvbhJ5CiFncm91cF9qb2luX3F1ZXN0aW9uc193aXRoX3Zl'
    'cnNpb24YEiABKAsyLS5pbS50dXJtcy5wcm90by5Hcm91cEpvaW5RdWVzdGlvbnNXaXRoVmVyc2'
    'lvbkgAUh1ncm91cEpvaW5RdWVzdGlvbnNXaXRoVmVyc2lvbhJmChpncm91cF9tZW1iZXJzX3dp'
    'dGhfdmVyc2lvbhgTIAEoCzInLmltLnR1cm1zLnByb3RvLkdyb3VwTWVtYmVyc1dpdGhWZXJzaW'
    '9uSABSF2dyb3VwTWVtYmVyc1dpdGhWZXJzaW9uElMKE2dyb3Vwc193aXRoX3ZlcnNpb24YFCAB'
    'KAsyIS5pbS50dXJtcy5wcm90by5Hcm91cHNXaXRoVmVyc2lvbkgAUhFncm91cHNXaXRoVmVyc2'
    'lvbhJcChZzdG9yYWdlX3Jlc291cmNlX2luZm9zGDIgASgLMiQuaW0udHVybXMucHJvdG8uU3Rv'
    'cmFnZVJlc291cmNlSW5mb3NIAFIUc3RvcmFnZVJlc291cmNlSW5mb3NCBgoEa2luZEINCgtfcm'
    'VxdWVzdF9pZEIHCgVfY29kZUIJCgdfcmVhc29uQg8KDV9yZXF1ZXN0ZXJfaWRCDwoNX2Nsb3Nl'
    'X3N0YXR1cw==');
