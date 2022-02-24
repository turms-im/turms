///
//  Generated code. Do not modify.
//  source: model/user/users_infos_with_version.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import 'user_info.pb.dart' as $0;

class UsersInfosWithVersion extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'UsersInfosWithVersion', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..pc<$0.UserInfo>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userInfos', $pb.PbFieldType.PM, subBuilder: $0.UserInfo.create)
    ..aInt64(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false
  ;

  UsersInfosWithVersion._() : super();
  factory UsersInfosWithVersion({
    $core.Iterable<$0.UserInfo>? userInfos,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (userInfos != null) {
      _result.userInfos.addAll(userInfos);
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory UsersInfosWithVersion.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory UsersInfosWithVersion.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  UsersInfosWithVersion clone() => UsersInfosWithVersion()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  UsersInfosWithVersion copyWith(void Function(UsersInfosWithVersion) updates) => super.copyWith((message) => updates(message as UsersInfosWithVersion)) as UsersInfosWithVersion; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UsersInfosWithVersion create() => UsersInfosWithVersion._();
  UsersInfosWithVersion createEmptyInstance() => create();
  static $pb.PbList<UsersInfosWithVersion> createRepeated() => $pb.PbList<UsersInfosWithVersion>();
  @$core.pragma('dart2js:noInline')
  static UsersInfosWithVersion getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UsersInfosWithVersion>(create);
  static UsersInfosWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.UserInfo> get userInfos => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) { $_setInt64(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}

