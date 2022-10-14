///
//  Generated code. Do not modify.
//  source: notification/turms_notification.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use turmsNotificationDescriptor instead')
const TurmsNotification$json = const {
  '1': 'TurmsNotification',
  '2': const [
    const {'1': 'timestamp', '3': 1, '4': 1, '5': 3, '10': 'timestamp'},
    const {'1': 'request_id', '3': 4, '4': 1, '5': 3, '9': 0, '10': 'requestId', '17': true},
    const {'1': 'code', '3': 5, '4': 1, '5': 5, '9': 1, '10': 'code', '17': true},
    const {'1': 'reason', '3': 6, '4': 1, '5': 9, '9': 2, '10': 'reason', '17': true},
    const {'1': 'data', '3': 7, '4': 1, '5': 11, '6': '.im.turms.proto.TurmsNotification.Data', '10': 'data'},
    const {'1': 'requester_id', '3': 10, '4': 1, '5': 3, '9': 3, '10': 'requesterId', '17': true},
    const {'1': 'close_status', '3': 11, '4': 1, '5': 5, '9': 4, '10': 'closeStatus', '17': true},
    const {'1': 'relayed_request', '3': 12, '4': 1, '5': 11, '6': '.im.turms.proto.TurmsRequest', '10': 'relayedRequest'},
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
    const {'1': 'ids', '3': 1, '4': 1, '5': 11, '6': '.im.turms.proto.Int64Values', '9': 0, '10': 'ids'},
    const {'1': 'ids_with_version', '3': 2, '4': 1, '5': 11, '6': '.im.turms.proto.Int64ValuesWithVersion', '9': 0, '10': 'idsWithVersion'},
    const {'1': 'url', '3': 3, '4': 1, '5': 9, '9': 0, '10': 'url'},
    const {'1': 'conversations', '3': 4, '4': 1, '5': 11, '6': '.im.turms.proto.Conversations', '9': 0, '10': 'conversations'},
    const {'1': 'messages', '3': 5, '4': 1, '5': 11, '6': '.im.turms.proto.Messages', '9': 0, '10': 'messages'},
    const {'1': 'messages_with_total_list', '3': 6, '4': 1, '5': 11, '6': '.im.turms.proto.MessagesWithTotalList', '9': 0, '10': 'messagesWithTotalList'},
    const {'1': 'user_session', '3': 7, '4': 1, '5': 11, '6': '.im.turms.proto.UserSession', '9': 0, '10': 'userSession'},
    const {'1': 'user_infos_with_version', '3': 8, '4': 1, '5': 11, '6': '.im.turms.proto.UserInfosWithVersion', '9': 0, '10': 'userInfosWithVersion'},
    const {'1': 'user_online_statuses', '3': 9, '4': 1, '5': 11, '6': '.im.turms.proto.UserOnlineStatuses', '9': 0, '10': 'userOnlineStatuses'},
    const {'1': 'user_friend_requests_with_version', '3': 10, '4': 1, '5': 11, '6': '.im.turms.proto.UserFriendRequestsWithVersion', '9': 0, '10': 'userFriendRequestsWithVersion'},
    const {'1': 'user_relationship_groups_with_version', '3': 11, '4': 1, '5': 11, '6': '.im.turms.proto.UserRelationshipGroupsWithVersion', '9': 0, '10': 'userRelationshipGroupsWithVersion'},
    const {'1': 'user_relationships_with_version', '3': 12, '4': 1, '5': 11, '6': '.im.turms.proto.UserRelationshipsWithVersion', '9': 0, '10': 'userRelationshipsWithVersion'},
    const {'1': 'nearby_users', '3': 13, '4': 1, '5': 11, '6': '.im.turms.proto.NearbyUsers', '9': 0, '10': 'nearbyUsers'},
    const {'1': 'group_invitations_with_version', '3': 14, '4': 1, '5': 11, '6': '.im.turms.proto.GroupInvitationsWithVersion', '9': 0, '10': 'groupInvitationsWithVersion'},
    const {'1': 'group_join_question_answer_result', '3': 15, '4': 1, '5': 11, '6': '.im.turms.proto.GroupJoinQuestionsAnswerResult', '9': 0, '10': 'groupJoinQuestionAnswerResult'},
    const {'1': 'group_join_requests_with_version', '3': 16, '4': 1, '5': 11, '6': '.im.turms.proto.GroupJoinRequestsWithVersion', '9': 0, '10': 'groupJoinRequestsWithVersion'},
    const {'1': 'group_join_questions_with_version', '3': 17, '4': 1, '5': 11, '6': '.im.turms.proto.GroupJoinQuestionsWithVersion', '9': 0, '10': 'groupJoinQuestionsWithVersion'},
    const {'1': 'group_members_with_version', '3': 18, '4': 1, '5': 11, '6': '.im.turms.proto.GroupMembersWithVersion', '9': 0, '10': 'groupMembersWithVersion'},
    const {'1': 'groups_with_version', '3': 19, '4': 1, '5': 11, '6': '.im.turms.proto.GroupsWithVersion', '9': 0, '10': 'groupsWithVersion'},
  ],
  '8': const [
    const {'1': 'kind'},
  ],
};

/// Descriptor for `TurmsNotification`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List turmsNotificationDescriptor = $convert.base64Decode('ChFUdXJtc05vdGlmaWNhdGlvbhIcCgl0aW1lc3RhbXAYASABKANSCXRpbWVzdGFtcBIiCgpyZXF1ZXN0X2lkGAQgASgDSABSCXJlcXVlc3RJZIgBARIXCgRjb2RlGAUgASgFSAFSBGNvZGWIAQESGwoGcmVhc29uGAYgASgJSAJSBnJlYXNvbogBARI6CgRkYXRhGAcgASgLMiYuaW0udHVybXMucHJvdG8uVHVybXNOb3RpZmljYXRpb24uRGF0YVIEZGF0YRImCgxyZXF1ZXN0ZXJfaWQYCiABKANIA1ILcmVxdWVzdGVySWSIAQESJgoMY2xvc2Vfc3RhdHVzGAsgASgFSARSC2Nsb3NlU3RhdHVziAEBEkUKD3JlbGF5ZWRfcmVxdWVzdBgMIAEoCzIcLmltLnR1cm1zLnByb3RvLlR1cm1zUmVxdWVzdFIOcmVsYXllZFJlcXVlc3Qa3Q0KBERhdGESLwoDaWRzGAEgASgLMhsuaW0udHVybXMucHJvdG8uSW50NjRWYWx1ZXNIAFIDaWRzElIKEGlkc193aXRoX3ZlcnNpb24YAiABKAsyJi5pbS50dXJtcy5wcm90by5JbnQ2NFZhbHVlc1dpdGhWZXJzaW9uSABSDmlkc1dpdGhWZXJzaW9uEhIKA3VybBgDIAEoCUgAUgN1cmwSRQoNY29udmVyc2F0aW9ucxgEIAEoCzIdLmltLnR1cm1zLnByb3RvLkNvbnZlcnNhdGlvbnNIAFINY29udmVyc2F0aW9ucxI2CghtZXNzYWdlcxgFIAEoCzIYLmltLnR1cm1zLnByb3RvLk1lc3NhZ2VzSABSCG1lc3NhZ2VzEmAKGG1lc3NhZ2VzX3dpdGhfdG90YWxfbGlzdBgGIAEoCzIlLmltLnR1cm1zLnByb3RvLk1lc3NhZ2VzV2l0aFRvdGFsTGlzdEgAUhVtZXNzYWdlc1dpdGhUb3RhbExpc3QSQAoMdXNlcl9zZXNzaW9uGAcgASgLMhsuaW0udHVybXMucHJvdG8uVXNlclNlc3Npb25IAFILdXNlclNlc3Npb24SXQoXdXNlcl9pbmZvc193aXRoX3ZlcnNpb24YCCABKAsyJC5pbS50dXJtcy5wcm90by5Vc2VySW5mb3NXaXRoVmVyc2lvbkgAUhR1c2VySW5mb3NXaXRoVmVyc2lvbhJWChR1c2VyX29ubGluZV9zdGF0dXNlcxgJIAEoCzIiLmltLnR1cm1zLnByb3RvLlVzZXJPbmxpbmVTdGF0dXNlc0gAUhJ1c2VyT25saW5lU3RhdHVzZXMSeQohdXNlcl9mcmllbmRfcmVxdWVzdHNfd2l0aF92ZXJzaW9uGAogASgLMi0uaW0udHVybXMucHJvdG8uVXNlckZyaWVuZFJlcXVlc3RzV2l0aFZlcnNpb25IAFIddXNlckZyaWVuZFJlcXVlc3RzV2l0aFZlcnNpb24ShQEKJXVzZXJfcmVsYXRpb25zaGlwX2dyb3Vwc193aXRoX3ZlcnNpb24YCyABKAsyMS5pbS50dXJtcy5wcm90by5Vc2VyUmVsYXRpb25zaGlwR3JvdXBzV2l0aFZlcnNpb25IAFIhdXNlclJlbGF0aW9uc2hpcEdyb3Vwc1dpdGhWZXJzaW9uEnUKH3VzZXJfcmVsYXRpb25zaGlwc193aXRoX3ZlcnNpb24YDCABKAsyLC5pbS50dXJtcy5wcm90by5Vc2VyUmVsYXRpb25zaGlwc1dpdGhWZXJzaW9uSABSHHVzZXJSZWxhdGlvbnNoaXBzV2l0aFZlcnNpb24SQAoMbmVhcmJ5X3VzZXJzGA0gASgLMhsuaW0udHVybXMucHJvdG8uTmVhcmJ5VXNlcnNIAFILbmVhcmJ5VXNlcnMScgoeZ3JvdXBfaW52aXRhdGlvbnNfd2l0aF92ZXJzaW9uGA4gASgLMisuaW0udHVybXMucHJvdG8uR3JvdXBJbnZpdGF0aW9uc1dpdGhWZXJzaW9uSABSG2dyb3VwSW52aXRhdGlvbnNXaXRoVmVyc2lvbhJ6CiFncm91cF9qb2luX3F1ZXN0aW9uX2Fuc3dlcl9yZXN1bHQYDyABKAsyLi5pbS50dXJtcy5wcm90by5Hcm91cEpvaW5RdWVzdGlvbnNBbnN3ZXJSZXN1bHRIAFIdZ3JvdXBKb2luUXVlc3Rpb25BbnN3ZXJSZXN1bHQSdgogZ3JvdXBfam9pbl9yZXF1ZXN0c193aXRoX3ZlcnNpb24YECABKAsyLC5pbS50dXJtcy5wcm90by5Hcm91cEpvaW5SZXF1ZXN0c1dpdGhWZXJzaW9uSABSHGdyb3VwSm9pblJlcXVlc3RzV2l0aFZlcnNpb24SeQohZ3JvdXBfam9pbl9xdWVzdGlvbnNfd2l0aF92ZXJzaW9uGBEgASgLMi0uaW0udHVybXMucHJvdG8uR3JvdXBKb2luUXVlc3Rpb25zV2l0aFZlcnNpb25IAFIdZ3JvdXBKb2luUXVlc3Rpb25zV2l0aFZlcnNpb24SZgoaZ3JvdXBfbWVtYmVyc193aXRoX3ZlcnNpb24YEiABKAsyJy5pbS50dXJtcy5wcm90by5Hcm91cE1lbWJlcnNXaXRoVmVyc2lvbkgAUhdncm91cE1lbWJlcnNXaXRoVmVyc2lvbhJTChNncm91cHNfd2l0aF92ZXJzaW9uGBMgASgLMiEuaW0udHVybXMucHJvdG8uR3JvdXBzV2l0aFZlcnNpb25IAFIRZ3JvdXBzV2l0aFZlcnNpb25CBgoEa2luZEINCgtfcmVxdWVzdF9pZEIHCgVfY29kZUIJCgdfcmVhc29uQg8KDV9yZXF1ZXN0ZXJfaWRCDwoNX2Nsb3NlX3N0YXR1cw==');
