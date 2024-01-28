//
//  Generated code. Do not modify.
//  source: notification/turms_notification.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../model/common/longs_with_version.pb.dart' as $1;
import '../model/common/strings_with_version.pb.dart' as $2;
import '../model/conversation/conversations.pb.dart' as $3;
import '../model/group/group_invitations_with_version.pb.dart' as $13;
import '../model/group/group_join_questions_answer_result.pb.dart' as $14;
import '../model/group/group_join_questions_with_version.pb.dart' as $16;
import '../model/group/group_join_requests_with_version.pb.dart' as $15;
import '../model/group/group_members_with_version.pb.dart' as $17;
import '../model/group/groups_with_version.pb.dart' as $18;
import '../model/message/messages.pb.dart' as $4;
import '../model/message/messages_with_total_list.pb.dart' as $5;
import '../model/storage/storage_resource_infos.pb.dart' as $19;
import '../model/user/nearby_users.pb.dart' as $12;
import '../model/user/user_friend_requests_with_version.pb.dart' as $9;
import '../model/user/user_infos_with_version.pb.dart' as $7;
import '../model/user/user_online_statuses.pb.dart' as $8;
import '../model/user/user_relationship_groups_with_version.pb.dart' as $10;
import '../model/user/user_relationships_with_version.pb.dart' as $11;
import '../model/user/user_session.pb.dart' as $6;
import '../request/turms_request.pb.dart' as $0;

enum TurmsNotification_Data_Kind {
  long,
  string,
  longsWithVersion,
  stringsWithVersion,
  conversations,
  messages,
  messagesWithTotalList,
  userSession,
  userInfosWithVersion,
  userOnlineStatuses,
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
  storageResourceInfos,
  notSet
}

class TurmsNotification_Data extends $pb.GeneratedMessage {
  factory TurmsNotification_Data({
    $fixnum.Int64? long,
    $core.String? string,
    $1.LongsWithVersion? longsWithVersion,
    $2.StringsWithVersion? stringsWithVersion,
    $3.Conversations? conversations,
    $4.Messages? messages,
    $5.MessagesWithTotalList? messagesWithTotalList,
    $6.UserSession? userSession,
    $7.UserInfosWithVersion? userInfosWithVersion,
    $8.UserOnlineStatuses? userOnlineStatuses,
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
    $19.StorageResourceInfos? storageResourceInfos,
  }) {
    final $result = create();
    if (long != null) {
      $result.long = long;
    }
    if (string != null) {
      $result.string = string;
    }
    if (longsWithVersion != null) {
      $result.longsWithVersion = longsWithVersion;
    }
    if (stringsWithVersion != null) {
      $result.stringsWithVersion = stringsWithVersion;
    }
    if (conversations != null) {
      $result.conversations = conversations;
    }
    if (messages != null) {
      $result.messages = messages;
    }
    if (messagesWithTotalList != null) {
      $result.messagesWithTotalList = messagesWithTotalList;
    }
    if (userSession != null) {
      $result.userSession = userSession;
    }
    if (userInfosWithVersion != null) {
      $result.userInfosWithVersion = userInfosWithVersion;
    }
    if (userOnlineStatuses != null) {
      $result.userOnlineStatuses = userOnlineStatuses;
    }
    if (userFriendRequestsWithVersion != null) {
      $result.userFriendRequestsWithVersion = userFriendRequestsWithVersion;
    }
    if (userRelationshipGroupsWithVersion != null) {
      $result.userRelationshipGroupsWithVersion =
          userRelationshipGroupsWithVersion;
    }
    if (userRelationshipsWithVersion != null) {
      $result.userRelationshipsWithVersion = userRelationshipsWithVersion;
    }
    if (nearbyUsers != null) {
      $result.nearbyUsers = nearbyUsers;
    }
    if (groupInvitationsWithVersion != null) {
      $result.groupInvitationsWithVersion = groupInvitationsWithVersion;
    }
    if (groupJoinQuestionAnswerResult != null) {
      $result.groupJoinQuestionAnswerResult = groupJoinQuestionAnswerResult;
    }
    if (groupJoinRequestsWithVersion != null) {
      $result.groupJoinRequestsWithVersion = groupJoinRequestsWithVersion;
    }
    if (groupJoinQuestionsWithVersion != null) {
      $result.groupJoinQuestionsWithVersion = groupJoinQuestionsWithVersion;
    }
    if (groupMembersWithVersion != null) {
      $result.groupMembersWithVersion = groupMembersWithVersion;
    }
    if (groupsWithVersion != null) {
      $result.groupsWithVersion = groupsWithVersion;
    }
    if (storageResourceInfos != null) {
      $result.storageResourceInfos = storageResourceInfos;
    }
    return $result;
  }
  TurmsNotification_Data._() : super();
  factory TurmsNotification_Data.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory TurmsNotification_Data.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static const $core.Map<$core.int, TurmsNotification_Data_Kind>
      _TurmsNotification_Data_KindByTag = {
    1: TurmsNotification_Data_Kind.long,
    2: TurmsNotification_Data_Kind.string,
    3: TurmsNotification_Data_Kind.longsWithVersion,
    4: TurmsNotification_Data_Kind.stringsWithVersion,
    5: TurmsNotification_Data_Kind.conversations,
    6: TurmsNotification_Data_Kind.messages,
    7: TurmsNotification_Data_Kind.messagesWithTotalList,
    8: TurmsNotification_Data_Kind.userSession,
    9: TurmsNotification_Data_Kind.userInfosWithVersion,
    10: TurmsNotification_Data_Kind.userOnlineStatuses,
    11: TurmsNotification_Data_Kind.userFriendRequestsWithVersion,
    12: TurmsNotification_Data_Kind.userRelationshipGroupsWithVersion,
    13: TurmsNotification_Data_Kind.userRelationshipsWithVersion,
    14: TurmsNotification_Data_Kind.nearbyUsers,
    15: TurmsNotification_Data_Kind.groupInvitationsWithVersion,
    16: TurmsNotification_Data_Kind.groupJoinQuestionAnswerResult,
    17: TurmsNotification_Data_Kind.groupJoinRequestsWithVersion,
    18: TurmsNotification_Data_Kind.groupJoinQuestionsWithVersion,
    19: TurmsNotification_Data_Kind.groupMembersWithVersion,
    20: TurmsNotification_Data_Kind.groupsWithVersion,
    50: TurmsNotification_Data_Kind.storageResourceInfos,
    0: TurmsNotification_Data_Kind.notSet
  };
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'TurmsNotification.Data',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..oo(0, [
      1,
      2,
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
      15,
      16,
      17,
      18,
      19,
      20,
      50
    ])
    ..aInt64(1, _omitFieldNames ? '' : 'long')
    ..aOS(2, _omitFieldNames ? '' : 'string')
    ..aOM<$1.LongsWithVersion>(3, _omitFieldNames ? '' : 'longsWithVersion',
        subBuilder: $1.LongsWithVersion.create)
    ..aOM<$2.StringsWithVersion>(4, _omitFieldNames ? '' : 'stringsWithVersion',
        subBuilder: $2.StringsWithVersion.create)
    ..aOM<$3.Conversations>(5, _omitFieldNames ? '' : 'conversations',
        subBuilder: $3.Conversations.create)
    ..aOM<$4.Messages>(6, _omitFieldNames ? '' : 'messages',
        subBuilder: $4.Messages.create)
    ..aOM<$5.MessagesWithTotalList>(
        7, _omitFieldNames ? '' : 'messagesWithTotalList',
        subBuilder: $5.MessagesWithTotalList.create)
    ..aOM<$6.UserSession>(8, _omitFieldNames ? '' : 'userSession',
        subBuilder: $6.UserSession.create)
    ..aOM<$7.UserInfosWithVersion>(
        9, _omitFieldNames ? '' : 'userInfosWithVersion',
        subBuilder: $7.UserInfosWithVersion.create)
    ..aOM<$8.UserOnlineStatuses>(
        10, _omitFieldNames ? '' : 'userOnlineStatuses',
        subBuilder: $8.UserOnlineStatuses.create)
    ..aOM<$9.UserFriendRequestsWithVersion>(
        11, _omitFieldNames ? '' : 'userFriendRequestsWithVersion',
        subBuilder: $9.UserFriendRequestsWithVersion.create)
    ..aOM<$10.UserRelationshipGroupsWithVersion>(
        12, _omitFieldNames ? '' : 'userRelationshipGroupsWithVersion',
        subBuilder: $10.UserRelationshipGroupsWithVersion.create)
    ..aOM<$11.UserRelationshipsWithVersion>(
        13, _omitFieldNames ? '' : 'userRelationshipsWithVersion',
        subBuilder: $11.UserRelationshipsWithVersion.create)
    ..aOM<$12.NearbyUsers>(14, _omitFieldNames ? '' : 'nearbyUsers',
        subBuilder: $12.NearbyUsers.create)
    ..aOM<$13.GroupInvitationsWithVersion>(
        15, _omitFieldNames ? '' : 'groupInvitationsWithVersion',
        subBuilder: $13.GroupInvitationsWithVersion.create)
    ..aOM<$14.GroupJoinQuestionsAnswerResult>(
        16, _omitFieldNames ? '' : 'groupJoinQuestionAnswerResult',
        subBuilder: $14.GroupJoinQuestionsAnswerResult.create)
    ..aOM<$15.GroupJoinRequestsWithVersion>(
        17, _omitFieldNames ? '' : 'groupJoinRequestsWithVersion',
        subBuilder: $15.GroupJoinRequestsWithVersion.create)
    ..aOM<$16.GroupJoinQuestionsWithVersion>(
        18, _omitFieldNames ? '' : 'groupJoinQuestionsWithVersion',
        subBuilder: $16.GroupJoinQuestionsWithVersion.create)
    ..aOM<$17.GroupMembersWithVersion>(
        19, _omitFieldNames ? '' : 'groupMembersWithVersion',
        subBuilder: $17.GroupMembersWithVersion.create)
    ..aOM<$18.GroupsWithVersion>(20, _omitFieldNames ? '' : 'groupsWithVersion',
        subBuilder: $18.GroupsWithVersion.create)
    ..aOM<$19.StorageResourceInfos>(
        50, _omitFieldNames ? '' : 'storageResourceInfos',
        subBuilder: $19.StorageResourceInfos.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  TurmsNotification_Data clone() =>
      TurmsNotification_Data()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  TurmsNotification_Data copyWith(
          void Function(TurmsNotification_Data) updates) =>
      super.copyWith((message) => updates(message as TurmsNotification_Data))
          as TurmsNotification_Data;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static TurmsNotification_Data create() => TurmsNotification_Data._();
  TurmsNotification_Data createEmptyInstance() => create();
  static $pb.PbList<TurmsNotification_Data> createRepeated() =>
      $pb.PbList<TurmsNotification_Data>();
  @$core.pragma('dart2js:noInline')
  static TurmsNotification_Data getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<TurmsNotification_Data>(create);
  static TurmsNotification_Data? _defaultInstance;

  TurmsNotification_Data_Kind whichKind() =>
      _TurmsNotification_Data_KindByTag[$_whichOneof(0)]!;
  void clearKind() => clearField($_whichOneof(0));

  /// Common
  @$pb.TagNumber(1)
  $fixnum.Int64 get long => $_getI64(0);
  @$pb.TagNumber(1)
  set long($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasLong() => $_has(0);
  @$pb.TagNumber(1)
  void clearLong() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get string => $_getSZ(1);
  @$pb.TagNumber(2)
  set string($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasString() => $_has(1);
  @$pb.TagNumber(2)
  void clearString() => clearField(2);

  @$pb.TagNumber(3)
  $1.LongsWithVersion get longsWithVersion => $_getN(2);
  @$pb.TagNumber(3)
  set longsWithVersion($1.LongsWithVersion v) {
    setField(3, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasLongsWithVersion() => $_has(2);
  @$pb.TagNumber(3)
  void clearLongsWithVersion() => clearField(3);
  @$pb.TagNumber(3)
  $1.LongsWithVersion ensureLongsWithVersion() => $_ensure(2);

  @$pb.TagNumber(4)
  $2.StringsWithVersion get stringsWithVersion => $_getN(3);
  @$pb.TagNumber(4)
  set stringsWithVersion($2.StringsWithVersion v) {
    setField(4, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasStringsWithVersion() => $_has(3);
  @$pb.TagNumber(4)
  void clearStringsWithVersion() => clearField(4);
  @$pb.TagNumber(4)
  $2.StringsWithVersion ensureStringsWithVersion() => $_ensure(3);

  /// Conversation
  @$pb.TagNumber(5)
  $3.Conversations get conversations => $_getN(4);
  @$pb.TagNumber(5)
  set conversations($3.Conversations v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasConversations() => $_has(4);
  @$pb.TagNumber(5)
  void clearConversations() => clearField(5);
  @$pb.TagNumber(5)
  $3.Conversations ensureConversations() => $_ensure(4);

  /// Message
  @$pb.TagNumber(6)
  $4.Messages get messages => $_getN(5);
  @$pb.TagNumber(6)
  set messages($4.Messages v) {
    setField(6, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasMessages() => $_has(5);
  @$pb.TagNumber(6)
  void clearMessages() => clearField(6);
  @$pb.TagNumber(6)
  $4.Messages ensureMessages() => $_ensure(5);

  @$pb.TagNumber(7)
  $5.MessagesWithTotalList get messagesWithTotalList => $_getN(6);
  @$pb.TagNumber(7)
  set messagesWithTotalList($5.MessagesWithTotalList v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasMessagesWithTotalList() => $_has(6);
  @$pb.TagNumber(7)
  void clearMessagesWithTotalList() => clearField(7);
  @$pb.TagNumber(7)
  $5.MessagesWithTotalList ensureMessagesWithTotalList() => $_ensure(6);

  /// User
  @$pb.TagNumber(8)
  $6.UserSession get userSession => $_getN(7);
  @$pb.TagNumber(8)
  set userSession($6.UserSession v) {
    setField(8, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasUserSession() => $_has(7);
  @$pb.TagNumber(8)
  void clearUserSession() => clearField(8);
  @$pb.TagNumber(8)
  $6.UserSession ensureUserSession() => $_ensure(7);

  @$pb.TagNumber(9)
  $7.UserInfosWithVersion get userInfosWithVersion => $_getN(8);
  @$pb.TagNumber(9)
  set userInfosWithVersion($7.UserInfosWithVersion v) {
    setField(9, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasUserInfosWithVersion() => $_has(8);
  @$pb.TagNumber(9)
  void clearUserInfosWithVersion() => clearField(9);
  @$pb.TagNumber(9)
  $7.UserInfosWithVersion ensureUserInfosWithVersion() => $_ensure(8);

  @$pb.TagNumber(10)
  $8.UserOnlineStatuses get userOnlineStatuses => $_getN(9);
  @$pb.TagNumber(10)
  set userOnlineStatuses($8.UserOnlineStatuses v) {
    setField(10, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasUserOnlineStatuses() => $_has(9);
  @$pb.TagNumber(10)
  void clearUserOnlineStatuses() => clearField(10);
  @$pb.TagNumber(10)
  $8.UserOnlineStatuses ensureUserOnlineStatuses() => $_ensure(9);

  @$pb.TagNumber(11)
  $9.UserFriendRequestsWithVersion get userFriendRequestsWithVersion =>
      $_getN(10);
  @$pb.TagNumber(11)
  set userFriendRequestsWithVersion($9.UserFriendRequestsWithVersion v) {
    setField(11, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasUserFriendRequestsWithVersion() => $_has(10);
  @$pb.TagNumber(11)
  void clearUserFriendRequestsWithVersion() => clearField(11);
  @$pb.TagNumber(11)
  $9.UserFriendRequestsWithVersion ensureUserFriendRequestsWithVersion() =>
      $_ensure(10);

  @$pb.TagNumber(12)
  $10.UserRelationshipGroupsWithVersion get userRelationshipGroupsWithVersion =>
      $_getN(11);
  @$pb.TagNumber(12)
  set userRelationshipGroupsWithVersion(
      $10.UserRelationshipGroupsWithVersion v) {
    setField(12, v);
  }

  @$pb.TagNumber(12)
  $core.bool hasUserRelationshipGroupsWithVersion() => $_has(11);
  @$pb.TagNumber(12)
  void clearUserRelationshipGroupsWithVersion() => clearField(12);
  @$pb.TagNumber(12)
  $10.UserRelationshipGroupsWithVersion
      ensureUserRelationshipGroupsWithVersion() => $_ensure(11);

  @$pb.TagNumber(13)
  $11.UserRelationshipsWithVersion get userRelationshipsWithVersion =>
      $_getN(12);
  @$pb.TagNumber(13)
  set userRelationshipsWithVersion($11.UserRelationshipsWithVersion v) {
    setField(13, v);
  }

  @$pb.TagNumber(13)
  $core.bool hasUserRelationshipsWithVersion() => $_has(12);
  @$pb.TagNumber(13)
  void clearUserRelationshipsWithVersion() => clearField(13);
  @$pb.TagNumber(13)
  $11.UserRelationshipsWithVersion ensureUserRelationshipsWithVersion() =>
      $_ensure(12);

  @$pb.TagNumber(14)
  $12.NearbyUsers get nearbyUsers => $_getN(13);
  @$pb.TagNumber(14)
  set nearbyUsers($12.NearbyUsers v) {
    setField(14, v);
  }

  @$pb.TagNumber(14)
  $core.bool hasNearbyUsers() => $_has(13);
  @$pb.TagNumber(14)
  void clearNearbyUsers() => clearField(14);
  @$pb.TagNumber(14)
  $12.NearbyUsers ensureNearbyUsers() => $_ensure(13);

  /// Group
  @$pb.TagNumber(15)
  $13.GroupInvitationsWithVersion get groupInvitationsWithVersion => $_getN(14);
  @$pb.TagNumber(15)
  set groupInvitationsWithVersion($13.GroupInvitationsWithVersion v) {
    setField(15, v);
  }

  @$pb.TagNumber(15)
  $core.bool hasGroupInvitationsWithVersion() => $_has(14);
  @$pb.TagNumber(15)
  void clearGroupInvitationsWithVersion() => clearField(15);
  @$pb.TagNumber(15)
  $13.GroupInvitationsWithVersion ensureGroupInvitationsWithVersion() =>
      $_ensure(14);

  @$pb.TagNumber(16)
  $14.GroupJoinQuestionsAnswerResult get groupJoinQuestionAnswerResult =>
      $_getN(15);
  @$pb.TagNumber(16)
  set groupJoinQuestionAnswerResult($14.GroupJoinQuestionsAnswerResult v) {
    setField(16, v);
  }

  @$pb.TagNumber(16)
  $core.bool hasGroupJoinQuestionAnswerResult() => $_has(15);
  @$pb.TagNumber(16)
  void clearGroupJoinQuestionAnswerResult() => clearField(16);
  @$pb.TagNumber(16)
  $14.GroupJoinQuestionsAnswerResult ensureGroupJoinQuestionAnswerResult() =>
      $_ensure(15);

  @$pb.TagNumber(17)
  $15.GroupJoinRequestsWithVersion get groupJoinRequestsWithVersion =>
      $_getN(16);
  @$pb.TagNumber(17)
  set groupJoinRequestsWithVersion($15.GroupJoinRequestsWithVersion v) {
    setField(17, v);
  }

  @$pb.TagNumber(17)
  $core.bool hasGroupJoinRequestsWithVersion() => $_has(16);
  @$pb.TagNumber(17)
  void clearGroupJoinRequestsWithVersion() => clearField(17);
  @$pb.TagNumber(17)
  $15.GroupJoinRequestsWithVersion ensureGroupJoinRequestsWithVersion() =>
      $_ensure(16);

  @$pb.TagNumber(18)
  $16.GroupJoinQuestionsWithVersion get groupJoinQuestionsWithVersion =>
      $_getN(17);
  @$pb.TagNumber(18)
  set groupJoinQuestionsWithVersion($16.GroupJoinQuestionsWithVersion v) {
    setField(18, v);
  }

  @$pb.TagNumber(18)
  $core.bool hasGroupJoinQuestionsWithVersion() => $_has(17);
  @$pb.TagNumber(18)
  void clearGroupJoinQuestionsWithVersion() => clearField(18);
  @$pb.TagNumber(18)
  $16.GroupJoinQuestionsWithVersion ensureGroupJoinQuestionsWithVersion() =>
      $_ensure(17);

  @$pb.TagNumber(19)
  $17.GroupMembersWithVersion get groupMembersWithVersion => $_getN(18);
  @$pb.TagNumber(19)
  set groupMembersWithVersion($17.GroupMembersWithVersion v) {
    setField(19, v);
  }

  @$pb.TagNumber(19)
  $core.bool hasGroupMembersWithVersion() => $_has(18);
  @$pb.TagNumber(19)
  void clearGroupMembersWithVersion() => clearField(19);
  @$pb.TagNumber(19)
  $17.GroupMembersWithVersion ensureGroupMembersWithVersion() => $_ensure(18);

  @$pb.TagNumber(20)
  $18.GroupsWithVersion get groupsWithVersion => $_getN(19);
  @$pb.TagNumber(20)
  set groupsWithVersion($18.GroupsWithVersion v) {
    setField(20, v);
  }

  @$pb.TagNumber(20)
  $core.bool hasGroupsWithVersion() => $_has(19);
  @$pb.TagNumber(20)
  void clearGroupsWithVersion() => clearField(20);
  @$pb.TagNumber(20)
  $18.GroupsWithVersion ensureGroupsWithVersion() => $_ensure(19);

  /// Storage
  @$pb.TagNumber(50)
  $19.StorageResourceInfos get storageResourceInfos => $_getN(20);
  @$pb.TagNumber(50)
  set storageResourceInfos($19.StorageResourceInfos v) {
    setField(50, v);
  }

  @$pb.TagNumber(50)
  $core.bool hasStorageResourceInfos() => $_has(20);
  @$pb.TagNumber(50)
  void clearStorageResourceInfos() => clearField(50);
  @$pb.TagNumber(50)
  $19.StorageResourceInfos ensureStorageResourceInfos() => $_ensure(20);
}

class TurmsNotification extends $pb.GeneratedMessage {
  factory TurmsNotification({
    $fixnum.Int64? timestamp,
    $fixnum.Int64? requestId,
    $core.int? code,
    $core.String? reason,
    TurmsNotification_Data? data,
    $fixnum.Int64? requesterId,
    $core.int? closeStatus,
    $0.TurmsRequest? relayedRequest,
  }) {
    final $result = create();
    if (timestamp != null) {
      $result.timestamp = timestamp;
    }
    if (requestId != null) {
      $result.requestId = requestId;
    }
    if (code != null) {
      $result.code = code;
    }
    if (reason != null) {
      $result.reason = reason;
    }
    if (data != null) {
      $result.data = data;
    }
    if (requesterId != null) {
      $result.requesterId = requesterId;
    }
    if (closeStatus != null) {
      $result.closeStatus = closeStatus;
    }
    if (relayedRequest != null) {
      $result.relayedRequest = relayedRequest;
    }
    return $result;
  }
  TurmsNotification._() : super();
  factory TurmsNotification.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory TurmsNotification.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'TurmsNotification',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'timestamp')
    ..aInt64(4, _omitFieldNames ? '' : 'requestId')
    ..a<$core.int>(5, _omitFieldNames ? '' : 'code', $pb.PbFieldType.O3)
    ..aOS(6, _omitFieldNames ? '' : 'reason')
    ..aOM<TurmsNotification_Data>(7, _omitFieldNames ? '' : 'data',
        subBuilder: TurmsNotification_Data.create)
    ..aInt64(10, _omitFieldNames ? '' : 'requesterId')
    ..a<$core.int>(11, _omitFieldNames ? '' : 'closeStatus', $pb.PbFieldType.O3)
    ..aOM<$0.TurmsRequest>(12, _omitFieldNames ? '' : 'relayedRequest',
        subBuilder: $0.TurmsRequest.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  TurmsNotification clone() => TurmsNotification()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  TurmsNotification copyWith(void Function(TurmsNotification) updates) =>
      super.copyWith((message) => updates(message as TurmsNotification))
          as TurmsNotification;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static TurmsNotification create() => TurmsNotification._();
  TurmsNotification createEmptyInstance() => create();
  static $pb.PbList<TurmsNotification> createRepeated() =>
      $pb.PbList<TurmsNotification>();
  @$core.pragma('dart2js:noInline')
  static TurmsNotification getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<TurmsNotification>(create);
  static TurmsNotification? _defaultInstance;

  /// Common => [1, 3]
  @$pb.TagNumber(1)
  $fixnum.Int64 get timestamp => $_getI64(0);
  @$pb.TagNumber(1)
  set timestamp($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasTimestamp() => $_has(0);
  @$pb.TagNumber(1)
  void clearTimestamp() => clearField(1);

  /// Response => [4, 9]
  /// "request_id" is used to tell the client that
  /// this notification is a response to the specific request
  @$pb.TagNumber(4)
  $fixnum.Int64 get requestId => $_getI64(1);
  @$pb.TagNumber(4)
  set requestId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasRequestId() => $_has(1);
  @$pb.TagNumber(4)
  void clearRequestId() => clearField(4);

  @$pb.TagNumber(5)
  $core.int get code => $_getIZ(2);
  @$pb.TagNumber(5)
  set code($core.int v) {
    $_setSignedInt32(2, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasCode() => $_has(2);
  @$pb.TagNumber(5)
  void clearCode() => clearField(5);

  @$pb.TagNumber(6)
  $core.String get reason => $_getSZ(3);
  @$pb.TagNumber(6)
  set reason($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasReason() => $_has(3);
  @$pb.TagNumber(6)
  void clearReason() => clearField(6);

  @$pb.TagNumber(7)
  TurmsNotification_Data get data => $_getN(4);
  @$pb.TagNumber(7)
  set data(TurmsNotification_Data v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasData() => $_has(4);
  @$pb.TagNumber(7)
  void clearData() => clearField(7);
  @$pb.TagNumber(7)
  TurmsNotification_Data ensureData() => $_ensure(4);

  /// Notification => [10, 15]
  /// "requester_id" only exists when a requester triggers a notification to its recipients
  /// Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
  /// a new TurmsNotification when recipients need "requester_id".
  @$pb.TagNumber(10)
  $fixnum.Int64 get requesterId => $_getI64(5);
  @$pb.TagNumber(10)
  set requesterId($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasRequesterId() => $_has(5);
  @$pb.TagNumber(10)
  void clearRequesterId() => clearField(10);

  @$pb.TagNumber(11)
  $core.int get closeStatus => $_getIZ(6);
  @$pb.TagNumber(11)
  set closeStatus($core.int v) {
    $_setSignedInt32(6, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasCloseStatus() => $_has(6);
  @$pb.TagNumber(11)
  void clearCloseStatus() => clearField(11);

  @$pb.TagNumber(12)
  $0.TurmsRequest get relayedRequest => $_getN(7);
  @$pb.TagNumber(12)
  set relayedRequest($0.TurmsRequest v) {
    setField(12, v);
  }

  @$pb.TagNumber(12)
  $core.bool hasRelayedRequest() => $_has(7);
  @$pb.TagNumber(12)
  void clearRelayedRequest() => clearField(12);
  @$pb.TagNumber(12)
  $0.TurmsRequest ensureRelayedRequest() => $_ensure(7);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
