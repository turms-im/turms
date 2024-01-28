//
//  Generated code. Do not modify.
//  source: model/user/user_friend_requests_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_friend_request.pb.dart' as $0;

class UserFriendRequestsWithVersion extends $pb.GeneratedMessage {
  factory UserFriendRequestsWithVersion({
    $core.Iterable<$0.UserFriendRequest>? userFriendRequests,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (userFriendRequests != null) {
      $result.userFriendRequests.addAll(userFriendRequests);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  UserFriendRequestsWithVersion._() : super();
  factory UserFriendRequestsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserFriendRequestsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UserFriendRequestsWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.UserFriendRequest>(
        1, _omitFieldNames ? '' : 'userFriendRequests', $pb.PbFieldType.PM,
        subBuilder: $0.UserFriendRequest.create)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserFriendRequestsWithVersion clone() =>
      UserFriendRequestsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserFriendRequestsWithVersion copyWith(
          void Function(UserFriendRequestsWithVersion) updates) =>
      super.copyWith(
              (message) => updates(message as UserFriendRequestsWithVersion))
          as UserFriendRequestsWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UserFriendRequestsWithVersion create() =>
      UserFriendRequestsWithVersion._();
  UserFriendRequestsWithVersion createEmptyInstance() => create();
  static $pb.PbList<UserFriendRequestsWithVersion> createRepeated() =>
      $pb.PbList<UserFriendRequestsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static UserFriendRequestsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserFriendRequestsWithVersion>(create);
  static UserFriendRequestsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserFriendRequest> get userFriendRequests => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
