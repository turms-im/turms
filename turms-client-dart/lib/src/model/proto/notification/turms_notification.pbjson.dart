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
      '1': 'custom_attributes',
      '3': 2,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
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
      '1': 'meetings',
      '3': 40,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Meetings',
      '9': 0,
      '10': 'meetings'
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
    {
      '1': 'conversation_settings_list',
      '3': 200,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.ConversationSettingsList',
      '9': 0,
      '10': 'conversationSettingsList'
    },
    {
      '1': 'user_settings',
      '3': 400,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserSettings',
      '9': 0,
      '10': 'userSettings'
    },
  ],
  '8': [
    {'1': 'kind'},
  ],
};

/// Descriptor for `TurmsNotification`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsNotificationDescriptor = $convert.base64Decode(
    'ChFUdXJtc05vdGlmaWNhdGlvbhIcCgl0aW1lc3RhbXAYASABKANSCXRpbWVzdGFtcBJCChFjdX'
    'N0b21fYXR0cmlidXRlcxgCIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRy'
    'aWJ1dGVzEiIKCnJlcXVlc3RfaWQYBCABKANIAFIJcmVxdWVzdElkiAEBEhcKBGNvZGUYBSABKA'
    'VIAVIEY29kZYgBARIbCgZyZWFzb24YBiABKAlIAlIGcmVhc29uiAEBEjoKBGRhdGEYByABKAsy'
    'Ji5pbS50dXJtcy5wcm90by5UdXJtc05vdGlmaWNhdGlvbi5EYXRhUgRkYXRhEiYKDHJlcXVlc3'
    'Rlcl9pZBgKIAEoA0gDUgtyZXF1ZXN0ZXJJZIgBARImCgxjbG9zZV9zdGF0dXMYCyABKAVIBFIL'
    'Y2xvc2VTdGF0dXOIAQESRQoPcmVsYXllZF9yZXF1ZXN0GAwgASgLMhwuaW0udHVybXMucHJvdG'
    '8uVHVybXNSZXF1ZXN0Ug5yZWxheWVkUmVxdWVzdBrlEAoERGF0YRIUCgRsb25nGAEgASgDSABS'
    'BGxvbmcSGAoGc3RyaW5nGAIgASgJSABSBnN0cmluZxJQChJsb25nc193aXRoX3ZlcnNpb24YAy'
    'ABKAsyIC5pbS50dXJtcy5wcm90by5Mb25nc1dpdGhWZXJzaW9uSABSEGxvbmdzV2l0aFZlcnNp'
    'b24SVgoUc3RyaW5nc193aXRoX3ZlcnNpb24YBCABKAsyIi5pbS50dXJtcy5wcm90by5TdHJpbm'
    'dzV2l0aFZlcnNpb25IAFISc3RyaW5nc1dpdGhWZXJzaW9uEkUKDWNvbnZlcnNhdGlvbnMYBSAB'
    'KAsyHS5pbS50dXJtcy5wcm90by5Db252ZXJzYXRpb25zSABSDWNvbnZlcnNhdGlvbnMSNgoIbW'
    'Vzc2FnZXMYBiABKAsyGC5pbS50dXJtcy5wcm90by5NZXNzYWdlc0gAUghtZXNzYWdlcxJgChht'
    'ZXNzYWdlc193aXRoX3RvdGFsX2xpc3QYByABKAsyJS5pbS50dXJtcy5wcm90by5NZXNzYWdlc1'
    'dpdGhUb3RhbExpc3RIAFIVbWVzc2FnZXNXaXRoVG90YWxMaXN0EkAKDHVzZXJfc2Vzc2lvbhgI'
    'IAEoCzIbLmltLnR1cm1zLnByb3RvLlVzZXJTZXNzaW9uSABSC3VzZXJTZXNzaW9uEl0KF3VzZX'
    'JfaW5mb3Nfd2l0aF92ZXJzaW9uGAkgASgLMiQuaW0udHVybXMucHJvdG8uVXNlckluZm9zV2l0'
    'aFZlcnNpb25IAFIUdXNlckluZm9zV2l0aFZlcnNpb24SVgoUdXNlcl9vbmxpbmVfc3RhdHVzZX'
    'MYCiABKAsyIi5pbS50dXJtcy5wcm90by5Vc2VyT25saW5lU3RhdHVzZXNIAFISdXNlck9ubGlu'
    'ZVN0YXR1c2VzEnkKIXVzZXJfZnJpZW5kX3JlcXVlc3RzX3dpdGhfdmVyc2lvbhgLIAEoCzItLm'
    'ltLnR1cm1zLnByb3RvLlVzZXJGcmllbmRSZXF1ZXN0c1dpdGhWZXJzaW9uSABSHXVzZXJGcmll'
    'bmRSZXF1ZXN0c1dpdGhWZXJzaW9uEoUBCiV1c2VyX3JlbGF0aW9uc2hpcF9ncm91cHNfd2l0aF'
    '92ZXJzaW9uGAwgASgLMjEuaW0udHVybXMucHJvdG8uVXNlclJlbGF0aW9uc2hpcEdyb3Vwc1dp'
    'dGhWZXJzaW9uSABSIXVzZXJSZWxhdGlvbnNoaXBHcm91cHNXaXRoVmVyc2lvbhJ1Ch91c2VyX3'
    'JlbGF0aW9uc2hpcHNfd2l0aF92ZXJzaW9uGA0gASgLMiwuaW0udHVybXMucHJvdG8uVXNlclJl'
    'bGF0aW9uc2hpcHNXaXRoVmVyc2lvbkgAUhx1c2VyUmVsYXRpb25zaGlwc1dpdGhWZXJzaW9uEk'
    'AKDG5lYXJieV91c2VycxgOIAEoCzIbLmltLnR1cm1zLnByb3RvLk5lYXJieVVzZXJzSABSC25l'
    'YXJieVVzZXJzEnIKHmdyb3VwX2ludml0YXRpb25zX3dpdGhfdmVyc2lvbhgPIAEoCzIrLmltLn'
    'R1cm1zLnByb3RvLkdyb3VwSW52aXRhdGlvbnNXaXRoVmVyc2lvbkgAUhtncm91cEludml0YXRp'
    'b25zV2l0aFZlcnNpb24SegohZ3JvdXBfam9pbl9xdWVzdGlvbl9hbnN3ZXJfcmVzdWx0GBAgAS'
    'gLMi4uaW0udHVybXMucHJvdG8uR3JvdXBKb2luUXVlc3Rpb25zQW5zd2VyUmVzdWx0SABSHWdy'
    'b3VwSm9pblF1ZXN0aW9uQW5zd2VyUmVzdWx0EnYKIGdyb3VwX2pvaW5fcmVxdWVzdHNfd2l0aF'
    '92ZXJzaW9uGBEgASgLMiwuaW0udHVybXMucHJvdG8uR3JvdXBKb2luUmVxdWVzdHNXaXRoVmVy'
    'c2lvbkgAUhxncm91cEpvaW5SZXF1ZXN0c1dpdGhWZXJzaW9uEnkKIWdyb3VwX2pvaW5fcXVlc3'
    'Rpb25zX3dpdGhfdmVyc2lvbhgSIAEoCzItLmltLnR1cm1zLnByb3RvLkdyb3VwSm9pblF1ZXN0'
    'aW9uc1dpdGhWZXJzaW9uSABSHWdyb3VwSm9pblF1ZXN0aW9uc1dpdGhWZXJzaW9uEmYKGmdyb3'
    'VwX21lbWJlcnNfd2l0aF92ZXJzaW9uGBMgASgLMicuaW0udHVybXMucHJvdG8uR3JvdXBNZW1i'
    'ZXJzV2l0aFZlcnNpb25IAFIXZ3JvdXBNZW1iZXJzV2l0aFZlcnNpb24SUwoTZ3JvdXBzX3dpdG'
    'hfdmVyc2lvbhgUIAEoCzIhLmltLnR1cm1zLnByb3RvLkdyb3Vwc1dpdGhWZXJzaW9uSABSEWdy'
    'b3Vwc1dpdGhWZXJzaW9uEjYKCG1lZXRpbmdzGCggASgLMhguaW0udHVybXMucHJvdG8uTWVldG'
    'luZ3NIAFIIbWVldGluZ3MSXAoWc3RvcmFnZV9yZXNvdXJjZV9pbmZvcxgyIAEoCzIkLmltLnR1'
    'cm1zLnByb3RvLlN0b3JhZ2VSZXNvdXJjZUluZm9zSABSFHN0b3JhZ2VSZXNvdXJjZUluZm9zEm'
    'kKGmNvbnZlcnNhdGlvbl9zZXR0aW5nc19saXN0GMgBIAEoCzIoLmltLnR1cm1zLnByb3RvLkNv'
    'bnZlcnNhdGlvblNldHRpbmdzTGlzdEgAUhhjb252ZXJzYXRpb25TZXR0aW5nc0xpc3QSRAoNdX'
    'Nlcl9zZXR0aW5ncxiQAyABKAsyHC5pbS50dXJtcy5wcm90by5Vc2VyU2V0dGluZ3NIAFIMdXNl'
    'clNldHRpbmdzQgYKBGtpbmRCDQoLX3JlcXVlc3RfaWRCBwoFX2NvZGVCCQoHX3JlYXNvbkIPCg'
    '1fcmVxdWVzdGVyX2lkQg8KDV9jbG9zZV9zdGF0dXM=');
