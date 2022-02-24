///
//  Generated code. Do not modify.
//  source: notification/turms_notification.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../request/turms_request.pb.dart' as $0;
import '../model/common/int64_values.pb.dart' as $1;
import '../model/common/int64_values_with_version.pb.dart' as $2;
import '../model/conversation/conversations.pb.dart' as $3;
import '../model/message/messages.pb.dart' as $4;
import '../model/message/messages_with_total_list.pb.dart' as $5;
import '../model/user/user_session.pb.dart' as $6;
import '../model/user/users_infos_with_version.pb.dart' as $7;
import '../model/user/users_online_statuses.pb.dart' as $8;
import '../model/user/user_friend_requests_with_version.pb.dart' as $9;
import '../model/user/user_relationship_groups_with_version.pb.dart' as $10;
import '../model/user/user_relationships_with_version.pb.dart' as $11;
import '../model/user/nearby_users.pb.dart' as $12;
import '../model/group/group_invitations_with_version.pb.dart' as $13;
import '../model/group/group_join_questions_answer_result.pb.dart' as $14;
import '../model/group/group_join_requests_with_version.pb.dart' as $15;
import '../model/group/group_join_questions_with_version.pb.dart' as $16;
import '../model/group/group_members_with_version.pb.dart' as $17;
import '../model/group/groups_with_version.pb.dart' as $18;

enum TurmsNotification_Data_Kind {
  ids, 
  idsWithVersion, 
  url, 
  conversations, 
  messages, 
  messagesWithTotalList, 
  userSession, 
  usersInfosWithVersion, 
  usersOnlineStatuses, 
  userFriendRequestsWithVersion, 
  userRelationshipGroupsWithVersion, 
  userRelationshipsWithVersion, 
  nearbyUsers, 
  groupInvitationsWithVersion, 
  groupJoinQuestionAnswerResult, 
  groupJoinRequestsWithVersion, 
  groupJoinQuestionsWithVersion, 
  groupMembersWithVersion, 
  groupsWithVersion, 
  notSet
}

class TurmsNotification_Data extends $pb.GeneratedMessage {
  static const $core.Map<$core.int, TurmsNotification_Data_Kind> _TurmsNotification_Data_KindByTag = {
    1 : TurmsNotification_Data_Kind.ids,
    2 : TurmsNotification_Data_Kind.idsWithVersion,
    3 : TurmsNotification_Data_Kind.url,
    4 : TurmsNotification_Data_Kind.conversations,
    5 : TurmsNotification_Data_Kind.messages,
    6 : TurmsNotification_Data_Kind.messagesWithTotalList,
    7 : TurmsNotification_Data_Kind.userSession,
    8 : TurmsNotification_Data_Kind.usersInfosWithVersion,
    9 : TurmsNotification_Data_Kind.usersOnlineStatuses,
    10 : TurmsNotification_Data_Kind.userFriendRequestsWithVersion,
    11 : TurmsNotification_Data_Kind.userRelationshipGroupsWithVersion,
    12 : TurmsNotification_Data_Kind.userRelationshipsWithVersion,
    13 : TurmsNotification_Data_Kind.nearbyUsers,
    14 : TurmsNotification_Data_Kind.groupInvitationsWithVersion,
    15 : TurmsNotification_Data_Kind.groupJoinQuestionAnswerResult,
    16 : TurmsNotification_Data_Kind.groupJoinRequestsWithVersion,
    17 : TurmsNotification_Data_Kind.groupJoinQuestionsWithVersion,
    18 : TurmsNotification_Data_Kind.groupMembersWithVersion,
    19 : TurmsNotification_Data_Kind.groupsWithVersion,
    0 : TurmsNotification_Data_Kind.notSet
  };
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'TurmsNotification.Data', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..oo(0, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19])
    ..aOM<$1.Int64Values>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'ids', subBuilder: $1.Int64Values.create)
    ..aOM<$2.Int64ValuesWithVersion>(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'idsWithVersion', subBuilder: $2.Int64ValuesWithVersion.create)
    ..aOS(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'url')
    ..aOM<$3.Conversations>(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'conversations', subBuilder: $3.Conversations.create)
    ..aOM<$4.Messages>(5, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'messages', subBuilder: $4.Messages.create)
    ..aOM<$5.MessagesWithTotalList>(6, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'messagesWithTotalList', subBuilder: $5.MessagesWithTotalList.create)
    ..aOM<$6.UserSession>(7, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userSession', subBuilder: $6.UserSession.create)
    ..aOM<$7.UsersInfosWithVersion>(8, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'usersInfosWithVersion', subBuilder: $7.UsersInfosWithVersion.create)
    ..aOM<$8.UsersOnlineStatuses>(9, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'usersOnlineStatuses', subBuilder: $8.UsersOnlineStatuses.create)
    ..aOM<$9.UserFriendRequestsWithVersion>(10, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userFriendRequestsWithVersion', subBuilder: $9.UserFriendRequestsWithVersion.create)
    ..aOM<$10.UserRelationshipGroupsWithVersion>(11, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userRelationshipGroupsWithVersion', subBuilder: $10.UserRelationshipGroupsWithVersion.create)
    ..aOM<$11.UserRelationshipsWithVersion>(12, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userRelationshipsWithVersion', subBuilder: $11.UserRelationshipsWithVersion.create)
    ..aOM<$12.NearbyUsers>(13, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'nearbyUsers', subBuilder: $12.NearbyUsers.create)
    ..aOM<$13.GroupInvitationsWithVersion>(14, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupInvitationsWithVersion', subBuilder: $13.GroupInvitationsWithVersion.create)
    ..aOM<$14.GroupJoinQuestionsAnswerResult>(15, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupJoinQuestionAnswerResult', subBuilder: $14.GroupJoinQuestionsAnswerResult.create)
    ..aOM<$15.GroupJoinRequestsWithVersion>(16, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupJoinRequestsWithVersion', subBuilder: $15.GroupJoinRequestsWithVersion.create)
    ..aOM<$16.GroupJoinQuestionsWithVersion>(17, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupJoinQuestionsWithVersion', subBuilder: $16.GroupJoinQuestionsWithVersion.create)
    ..aOM<$17.GroupMembersWithVersion>(18, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupMembersWithVersion', subBuilder: $17.GroupMembersWithVersion.create)
    ..aOM<$18.GroupsWithVersion>(19, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupsWithVersion', subBuilder: $18.GroupsWithVersion.create)
    ..hasRequiredFields = false
  ;

  TurmsNotification_Data._() : super();
  factory TurmsNotification_Data({
    $1.Int64Values? ids,
    $2.Int64ValuesWithVersion? idsWithVersion,
    $core.String? url,
    $3.Conversations? conversations,
    $4.Messages? messages,
    $5.MessagesWithTotalList? messagesWithTotalList,
    $6.UserSession? userSession,
    $7.UsersInfosWithVersion? usersInfosWithVersion,
    $8.UsersOnlineStatuses? usersOnlineStatuses,
    $9.UserFriendRequestsWithVersion? userFriendRequestsWithVersion,
    $10.UserRelationshipGroupsWithVersion? userRelationshipGroupsWithVersion,
    $11.UserRelationshipsWithVersion? userRelationshipsWithVersion,
    $12.NearbyUsers? nearbyUsers,
    $13.GroupInvitationsWithVersion? groupInvitationsWithVersion,
    $14.GroupJoinQuestionsAnswerResult? groupJoinQuestionAnswerResult,
    $15.GroupJoinRequestsWithVersion? groupJoinRequestsWithVersion,
    $16.GroupJoinQuestionsWithVersion? groupJoinQuestionsWithVersion,
    $17.GroupMembersWithVersion? groupMembersWithVersion,
    $18.GroupsWithVersion? groupsWithVersion,
  }) {
    final _result = create();
    if (ids != null) {
      _result.ids = ids;
    }
    if (idsWithVersion != null) {
      _result.idsWithVersion = idsWithVersion;
    }
    if (url != null) {
      _result.url = url;
    }
    if (conversations != null) {
      _result.conversations = conversations;
    }
    if (messages != null) {
      _result.messages = messages;
    }
    if (messagesWithTotalList != null) {
      _result.messagesWithTotalList = messagesWithTotalList;
    }
    if (userSession != null) {
      _result.userSession = userSession;
    }
    if (usersInfosWithVersion != null) {
      _result.usersInfosWithVersion = usersInfosWithVersion;
    }
    if (usersOnlineStatuses != null) {
      _result.usersOnlineStatuses = usersOnlineStatuses;
    }
    if (userFriendRequestsWithVersion != null) {
      _result.userFriendRequestsWithVersion = userFriendRequestsWithVersion;
    }
    if (userRelationshipGroupsWithVersion != null) {
      _result.userRelationshipGroupsWithVersion = userRelationshipGroupsWithVersion;
    }
    if (userRelationshipsWithVersion != null) {
      _result.userRelationshipsWithVersion = userRelationshipsWithVersion;
    }
    if (nearbyUsers != null) {
      _result.nearbyUsers = nearbyUsers;
    }
    if (groupInvitationsWithVersion != null) {
      _result.groupInvitationsWithVersion = groupInvitationsWithVersion;
    }
    if (groupJoinQuestionAnswerResult != null) {
      _result.groupJoinQuestionAnswerResult = groupJoinQuestionAnswerResult;
    }
    if (groupJoinRequestsWithVersion != null) {
      _result.groupJoinRequestsWithVersion = groupJoinRequestsWithVersion;
    }
    if (groupJoinQuestionsWithVersion != null) {
      _result.groupJoinQuestionsWithVersion = groupJoinQuestionsWithVersion;
    }
    if (groupMembersWithVersion != null) {
      _result.groupMembersWithVersion = groupMembersWithVersion;
    }
    if (groupsWithVersion != null) {
      _result.groupsWithVersion = groupsWithVersion;
    }
    return _result;
  }
  factory TurmsNotification_Data.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory TurmsNotification_Data.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  TurmsNotification_Data clone() => TurmsNotification_Data()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  TurmsNotification_Data copyWith(void Function(TurmsNotification_Data) updates) => super.copyWith((message) => updates(message as TurmsNotification_Data)) as TurmsNotification_Data; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static TurmsNotification_Data create() => TurmsNotification_Data._();
  TurmsNotification_Data createEmptyInstance() => create();
  static $pb.PbList<TurmsNotification_Data> createRepeated() => $pb.PbList<TurmsNotification_Data>();
  @$core.pragma('dart2js:noInline')
  static TurmsNotification_Data getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<TurmsNotification_Data>(create);
  static TurmsNotification_Data? _defaultInstance;

  TurmsNotification_Data_Kind whichKind() => _TurmsNotification_Data_KindByTag[$_whichOneof(0)]!;
  void clearKind() => clearField($_whichOneof(0));

  @$pb.TagNumber(1)
  $1.Int64Values get ids => $_getN(0);
  @$pb.TagNumber(1)
  set ids($1.Int64Values v) { setField(1, v); }
  @$pb.TagNumber(1)
  $core.bool hasIds() => $_has(0);
  @$pb.TagNumber(1)
  void clearIds() => clearField(1);
  @$pb.TagNumber(1)
  $1.Int64Values ensureIds() => $_ensure(0);

  @$pb.TagNumber(2)
  $2.Int64ValuesWithVersion get idsWithVersion => $_getN(1);
  @$pb.TagNumber(2)
  set idsWithVersion($2.Int64ValuesWithVersion v) { setField(2, v); }
  @$pb.TagNumber(2)
  $core.bool hasIdsWithVersion() => $_has(1);
  @$pb.TagNumber(2)
  void clearIdsWithVersion() => clearField(2);
  @$pb.TagNumber(2)
  $2.Int64ValuesWithVersion ensureIdsWithVersion() => $_ensure(1);

  @$pb.TagNumber(3)
  $core.String get url => $_getSZ(2);
  @$pb.TagNumber(3)
  set url($core.String v) { $_setString(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasUrl() => $_has(2);
  @$pb.TagNumber(3)
  void clearUrl() => clearField(3);

  @$pb.TagNumber(4)
  $3.Conversations get conversations => $_getN(3);
  @$pb.TagNumber(4)
  set conversations($3.Conversations v) { setField(4, v); }
  @$pb.TagNumber(4)
  $core.bool hasConversations() => $_has(3);
  @$pb.TagNumber(4)
  void clearConversations() => clearField(4);
  @$pb.TagNumber(4)
  $3.Conversations ensureConversations() => $_ensure(3);

  @$pb.TagNumber(5)
  $4.Messages get messages => $_getN(4);
  @$pb.TagNumber(5)
  set messages($4.Messages v) { setField(5, v); }
  @$pb.TagNumber(5)
  $core.bool hasMessages() => $_has(4);
  @$pb.TagNumber(5)
  void clearMessages() => clearField(5);
  @$pb.TagNumber(5)
  $4.Messages ensureMessages() => $_ensure(4);

  @$pb.TagNumber(6)
  $5.MessagesWithTotalList get messagesWithTotalList => $_getN(5);
  @$pb.TagNumber(6)
  set messagesWithTotalList($5.MessagesWithTotalList v) { setField(6, v); }
  @$pb.TagNumber(6)
  $core.bool hasMessagesWithTotalList() => $_has(5);
  @$pb.TagNumber(6)
  void clearMessagesWithTotalList() => clearField(6);
  @$pb.TagNumber(6)
  $5.MessagesWithTotalList ensureMessagesWithTotalList() => $_ensure(5);

  @$pb.TagNumber(7)
  $6.UserSession get userSession => $_getN(6);
  @$pb.TagNumber(7)
  set userSession($6.UserSession v) { setField(7, v); }
  @$pb.TagNumber(7)
  $core.bool hasUserSession() => $_has(6);
  @$pb.TagNumber(7)
  void clearUserSession() => clearField(7);
  @$pb.TagNumber(7)
  $6.UserSession ensureUserSession() => $_ensure(6);

  @$pb.TagNumber(8)
  $7.UsersInfosWithVersion get usersInfosWithVersion => $_getN(7);
  @$pb.TagNumber(8)
  set usersInfosWithVersion($7.UsersInfosWithVersion v) { setField(8, v); }
  @$pb.TagNumber(8)
  $core.bool hasUsersInfosWithVersion() => $_has(7);
  @$pb.TagNumber(8)
  void clearUsersInfosWithVersion() => clearField(8);
  @$pb.TagNumber(8)
  $7.UsersInfosWithVersion ensureUsersInfosWithVersion() => $_ensure(7);

  @$pb.TagNumber(9)
  $8.UsersOnlineStatuses get usersOnlineStatuses => $_getN(8);
  @$pb.TagNumber(9)
  set usersOnlineStatuses($8.UsersOnlineStatuses v) { setField(9, v); }
  @$pb.TagNumber(9)
  $core.bool hasUsersOnlineStatuses() => $_has(8);
  @$pb.TagNumber(9)
  void clearUsersOnlineStatuses() => clearField(9);
  @$pb.TagNumber(9)
  $8.UsersOnlineStatuses ensureUsersOnlineStatuses() => $_ensure(8);

  @$pb.TagNumber(10)
  $9.UserFriendRequestsWithVersion get userFriendRequestsWithVersion => $_getN(9);
  @$pb.TagNumber(10)
  set userFriendRequestsWithVersion($9.UserFriendRequestsWithVersion v) { setField(10, v); }
  @$pb.TagNumber(10)
  $core.bool hasUserFriendRequestsWithVersion() => $_has(9);
  @$pb.TagNumber(10)
  void clearUserFriendRequestsWithVersion() => clearField(10);
  @$pb.TagNumber(10)
  $9.UserFriendRequestsWithVersion ensureUserFriendRequestsWithVersion() => $_ensure(9);

  @$pb.TagNumber(11)
  $10.UserRelationshipGroupsWithVersion get userRelationshipGroupsWithVersion => $_getN(10);
  @$pb.TagNumber(11)
  set userRelationshipGroupsWithVersion($10.UserRelationshipGroupsWithVersion v) { setField(11, v); }
  @$pb.TagNumber(11)
  $core.bool hasUserRelationshipGroupsWithVersion() => $_has(10);
  @$pb.TagNumber(11)
  void clearUserRelationshipGroupsWithVersion() => clearField(11);
  @$pb.TagNumber(11)
  $10.UserRelationshipGroupsWithVersion ensureUserRelationshipGroupsWithVersion() => $_ensure(10);

  @$pb.TagNumber(12)
  $11.UserRelationshipsWithVersion get userRelationshipsWithVersion => $_getN(11);
  @$pb.TagNumber(12)
  set userRelationshipsWithVersion($11.UserRelationshipsWithVersion v) { setField(12, v); }
  @$pb.TagNumber(12)
  $core.bool hasUserRelationshipsWithVersion() => $_has(11);
  @$pb.TagNumber(12)
  void clearUserRelationshipsWithVersion() => clearField(12);
  @$pb.TagNumber(12)
  $11.UserRelationshipsWithVersion ensureUserRelationshipsWithVersion() => $_ensure(11);

  @$pb.TagNumber(13)
  $12.NearbyUsers get nearbyUsers => $_getN(12);
  @$pb.TagNumber(13)
  set nearbyUsers($12.NearbyUsers v) { setField(13, v); }
  @$pb.TagNumber(13)
  $core.bool hasNearbyUsers() => $_has(12);
  @$pb.TagNumber(13)
  void clearNearbyUsers() => clearField(13);
  @$pb.TagNumber(13)
  $12.NearbyUsers ensureNearbyUsers() => $_ensure(12);

  @$pb.TagNumber(14)
  $13.GroupInvitationsWithVersion get groupInvitationsWithVersion => $_getN(13);
  @$pb.TagNumber(14)
  set groupInvitationsWithVersion($13.GroupInvitationsWithVersion v) { setField(14, v); }
  @$pb.TagNumber(14)
  $core.bool hasGroupInvitationsWithVersion() => $_has(13);
  @$pb.TagNumber(14)
  void clearGroupInvitationsWithVersion() => clearField(14);
  @$pb.TagNumber(14)
  $13.GroupInvitationsWithVersion ensureGroupInvitationsWithVersion() => $_ensure(13);

  @$pb.TagNumber(15)
  $14.GroupJoinQuestionsAnswerResult get groupJoinQuestionAnswerResult => $_getN(14);
  @$pb.TagNumber(15)
  set groupJoinQuestionAnswerResult($14.GroupJoinQuestionsAnswerResult v) { setField(15, v); }
  @$pb.TagNumber(15)
  $core.bool hasGroupJoinQuestionAnswerResult() => $_has(14);
  @$pb.TagNumber(15)
  void clearGroupJoinQuestionAnswerResult() => clearField(15);
  @$pb.TagNumber(15)
  $14.GroupJoinQuestionsAnswerResult ensureGroupJoinQuestionAnswerResult() => $_ensure(14);

  @$pb.TagNumber(16)
  $15.GroupJoinRequestsWithVersion get groupJoinRequestsWithVersion => $_getN(15);
  @$pb.TagNumber(16)
  set groupJoinRequestsWithVersion($15.GroupJoinRequestsWithVersion v) { setField(16, v); }
  @$pb.TagNumber(16)
  $core.bool hasGroupJoinRequestsWithVersion() => $_has(15);
  @$pb.TagNumber(16)
  void clearGroupJoinRequestsWithVersion() => clearField(16);
  @$pb.TagNumber(16)
  $15.GroupJoinRequestsWithVersion ensureGroupJoinRequestsWithVersion() => $_ensure(15);

  @$pb.TagNumber(17)
  $16.GroupJoinQuestionsWithVersion get groupJoinQuestionsWithVersion => $_getN(16);
  @$pb.TagNumber(17)
  set groupJoinQuestionsWithVersion($16.GroupJoinQuestionsWithVersion v) { setField(17, v); }
  @$pb.TagNumber(17)
  $core.bool hasGroupJoinQuestionsWithVersion() => $_has(16);
  @$pb.TagNumber(17)
  void clearGroupJoinQuestionsWithVersion() => clearField(17);
  @$pb.TagNumber(17)
  $16.GroupJoinQuestionsWithVersion ensureGroupJoinQuestionsWithVersion() => $_ensure(16);

  @$pb.TagNumber(18)
  $17.GroupMembersWithVersion get groupMembersWithVersion => $_getN(17);
  @$pb.TagNumber(18)
  set groupMembersWithVersion($17.GroupMembersWithVersion v) { setField(18, v); }
  @$pb.TagNumber(18)
  $core.bool hasGroupMembersWithVersion() => $_has(17);
  @$pb.TagNumber(18)
  void clearGroupMembersWithVersion() => clearField(18);
  @$pb.TagNumber(18)
  $17.GroupMembersWithVersion ensureGroupMembersWithVersion() => $_ensure(17);

  @$pb.TagNumber(19)
  $18.GroupsWithVersion get groupsWithVersion => $_getN(18);
  @$pb.TagNumber(19)
  set groupsWithVersion($18.GroupsWithVersion v) { setField(19, v); }
  @$pb.TagNumber(19)
  $core.bool hasGroupsWithVersion() => $_has(18);
  @$pb.TagNumber(19)
  void clearGroupsWithVersion() => clearField(19);
  @$pb.TagNumber(19)
  $18.GroupsWithVersion ensureGroupsWithVersion() => $_ensure(18);
}

class TurmsNotification extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'TurmsNotification', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'requestId')
    ..a<$core.int>(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'code', $pb.PbFieldType.O3)
    ..aOS(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'reason')
    ..aOM<TurmsNotification_Data>(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'data', subBuilder: TurmsNotification_Data.create)
    ..aInt64(5, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'requesterId')
    ..a<$core.int>(6, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'closeStatus', $pb.PbFieldType.O3)
    ..aOM<$0.TurmsRequest>(7, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'relayedRequest', subBuilder: $0.TurmsRequest.create)
    ..hasRequiredFields = false
  ;

  TurmsNotification._() : super();
  factory TurmsNotification({
    $fixnum.Int64? requestId,
    $core.int? code,
    $core.String? reason,
    TurmsNotification_Data? data,
    $fixnum.Int64? requesterId,
    $core.int? closeStatus,
    $0.TurmsRequest? relayedRequest,
  }) {
    final _result = create();
    if (requestId != null) {
      _result.requestId = requestId;
    }
    if (code != null) {
      _result.code = code;
    }
    if (reason != null) {
      _result.reason = reason;
    }
    if (data != null) {
      _result.data = data;
    }
    if (requesterId != null) {
      _result.requesterId = requesterId;
    }
    if (closeStatus != null) {
      _result.closeStatus = closeStatus;
    }
    if (relayedRequest != null) {
      _result.relayedRequest = relayedRequest;
    }
    return _result;
  }
  factory TurmsNotification.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory TurmsNotification.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  TurmsNotification clone() => TurmsNotification()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  TurmsNotification copyWith(void Function(TurmsNotification) updates) => super.copyWith((message) => updates(message as TurmsNotification)) as TurmsNotification; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static TurmsNotification create() => TurmsNotification._();
  TurmsNotification createEmptyInstance() => create();
  static $pb.PbList<TurmsNotification> createRepeated() => $pb.PbList<TurmsNotification>();
  @$core.pragma('dart2js:noInline')
  static TurmsNotification getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<TurmsNotification>(create);
  static TurmsNotification? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get requestId => $_getI64(0);
  @$pb.TagNumber(1)
  set requestId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasRequestId() => $_has(0);
  @$pb.TagNumber(1)
  void clearRequestId() => clearField(1);

  @$pb.TagNumber(2)
  $core.int get code => $_getIZ(1);
  @$pb.TagNumber(2)
  set code($core.int v) { $_setSignedInt32(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasCode() => $_has(1);
  @$pb.TagNumber(2)
  void clearCode() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get reason => $_getSZ(2);
  @$pb.TagNumber(3)
  set reason($core.String v) { $_setString(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasReason() => $_has(2);
  @$pb.TagNumber(3)
  void clearReason() => clearField(3);

  @$pb.TagNumber(4)
  TurmsNotification_Data get data => $_getN(3);
  @$pb.TagNumber(4)
  set data(TurmsNotification_Data v) { setField(4, v); }
  @$pb.TagNumber(4)
  $core.bool hasData() => $_has(3);
  @$pb.TagNumber(4)
  void clearData() => clearField(4);
  @$pb.TagNumber(4)
  TurmsNotification_Data ensureData() => $_ensure(3);

  @$pb.TagNumber(5)
  $fixnum.Int64 get requesterId => $_getI64(4);
  @$pb.TagNumber(5)
  set requesterId($fixnum.Int64 v) { $_setInt64(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasRequesterId() => $_has(4);
  @$pb.TagNumber(5)
  void clearRequesterId() => clearField(5);

  @$pb.TagNumber(6)
  $core.int get closeStatus => $_getIZ(5);
  @$pb.TagNumber(6)
  set closeStatus($core.int v) { $_setSignedInt32(5, v); }
  @$pb.TagNumber(6)
  $core.bool hasCloseStatus() => $_has(5);
  @$pb.TagNumber(6)
  void clearCloseStatus() => clearField(6);

  @$pb.TagNumber(7)
  $0.TurmsRequest get relayedRequest => $_getN(6);
  @$pb.TagNumber(7)
  set relayedRequest($0.TurmsRequest v) { setField(7, v); }
  @$pb.TagNumber(7)
  $core.bool hasRelayedRequest() => $_has(6);
  @$pb.TagNumber(7)
  void clearRelayedRequest() => clearField(7);
  @$pb.TagNumber(7)
  $0.TurmsRequest ensureRelayedRequest() => $_ensure(6);
}

