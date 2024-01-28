//
//  Generated code. Do not modify.
//  source: model/user/user_infos_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_info.pb.dart' as $0;

class UserInfosWithVersion extends $pb.GeneratedMessage {
  factory UserInfosWithVersion({
    $core.Iterable<$0.UserInfo>? userInfos,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (userInfos != null) {
      $result.userInfos.addAll(userInfos);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  UserInfosWithVersion._() : super();
  factory UserInfosWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserInfosWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UserInfosWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.UserInfo>(1, _omitFieldNames ? '' : 'userInfos', $pb.PbFieldType.PM,
        subBuilder: $0.UserInfo.create)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserInfosWithVersion clone() =>
      UserInfosWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserInfosWithVersion copyWith(void Function(UserInfosWithVersion) updates) =>
      super.copyWith((message) => updates(message as UserInfosWithVersion))
          as UserInfosWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UserInfosWithVersion create() => UserInfosWithVersion._();
  UserInfosWithVersion createEmptyInstance() => create();
  static $pb.PbList<UserInfosWithVersion> createRepeated() =>
      $pb.PbList<UserInfosWithVersion>();
  @$core.pragma('dart2js:noInline')
  static UserInfosWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UserInfosWithVersion>(create);
  static UserInfosWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserInfo> get userInfos => $_getList(0);

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
