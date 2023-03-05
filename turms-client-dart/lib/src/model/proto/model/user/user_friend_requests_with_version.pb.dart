///
//  Generated code. Do not modify.
//  source: model/user/user_friend_requests_with_version.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_friend_request.pb.dart' as $0;

class UserFriendRequestsWithVersion extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UserFriendRequestsWithVersion',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.UserFriendRequest>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userFriendRequests',
        $pb.PbFieldType.PM,
        subBuilder: $0.UserFriendRequest.create)
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  UserFriendRequestsWithVersion._() : super();
  factory UserFriendRequestsWithVersion({
    $core.Iterable<$0.UserFriendRequest>? userFriendRequests,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (userFriendRequests != null) {
      _result.userFriendRequests.addAll(userFriendRequests);
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory UserFriendRequestsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserFriendRequestsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as UserFriendRequestsWithVersion; // ignore: deprecated_member_use
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
