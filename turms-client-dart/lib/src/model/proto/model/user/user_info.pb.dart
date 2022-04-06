///
//  Generated code. Do not modify.
//  source: model/user/user_info.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/profile_access_strategy.pbenum.dart' as $0;

class UserInfo extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'UserInfo', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'id')
    ..aOS(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'name')
    ..aOS(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'intro')
    ..aInt64(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'registrationDate')
    ..aOB(5, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'active')
    ..e<$0.ProfileAccessStrategy>(6, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'profileAccessStrategy', $pb.PbFieldType.OE, defaultOrMaker: $0.ProfileAccessStrategy.ALL, valueOf: $0.ProfileAccessStrategy.valueOf, enumValues: $0.ProfileAccessStrategy.values)
    ..hasRequiredFields = false
  ;

  UserInfo._() : super();
  factory UserInfo({
    $fixnum.Int64? id,
    $core.String? name,
    $core.String? intro,
    $fixnum.Int64? registrationDate,
    $core.bool? active,
    $0.ProfileAccessStrategy? profileAccessStrategy,
  }) {
    final _result = create();
    if (id != null) {
      _result.id = id;
    }
    if (name != null) {
      _result.name = name;
    }
    if (intro != null) {
      _result.intro = intro;
    }
    if (registrationDate != null) {
      _result.registrationDate = registrationDate;
    }
    if (active != null) {
      _result.active = active;
    }
    if (profileAccessStrategy != null) {
      _result.profileAccessStrategy = profileAccessStrategy;
    }
    return _result;
  }
  factory UserInfo.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory UserInfo.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  UserInfo clone() => UserInfo()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  UserInfo copyWith(void Function(UserInfo) updates) => super.copyWith((message) => updates(message as UserInfo)) as UserInfo; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UserInfo create() => UserInfo._();
  UserInfo createEmptyInstance() => create();
  static $pb.PbList<UserInfo> createRepeated() => $pb.PbList<UserInfo>();
  @$core.pragma('dart2js:noInline')
  static UserInfo getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UserInfo>(create);
  static UserInfo? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get id => $_getI64(0);
  @$pb.TagNumber(1)
  set id($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasId() => $_has(0);
  @$pb.TagNumber(1)
  void clearId() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get name => $_getSZ(1);
  @$pb.TagNumber(2)
  set name($core.String v) { $_setString(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasName() => $_has(1);
  @$pb.TagNumber(2)
  void clearName() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get intro => $_getSZ(2);
  @$pb.TagNumber(3)
  set intro($core.String v) { $_setString(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasIntro() => $_has(2);
  @$pb.TagNumber(3)
  void clearIntro() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get registrationDate => $_getI64(3);
  @$pb.TagNumber(4)
  set registrationDate($fixnum.Int64 v) { $_setInt64(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasRegistrationDate() => $_has(3);
  @$pb.TagNumber(4)
  void clearRegistrationDate() => clearField(4);

  @$pb.TagNumber(5)
  $core.bool get active => $_getBF(4);
  @$pb.TagNumber(5)
  set active($core.bool v) { $_setBool(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasActive() => $_has(4);
  @$pb.TagNumber(5)
  void clearActive() => clearField(5);

  @$pb.TagNumber(6)
  $0.ProfileAccessStrategy get profileAccessStrategy => $_getN(5);
  @$pb.TagNumber(6)
  set profileAccessStrategy($0.ProfileAccessStrategy v) { setField(6, v); }
  @$pb.TagNumber(6)
  $core.bool hasProfileAccessStrategy() => $_has(5);
  @$pb.TagNumber(6)
  void clearProfileAccessStrategy() => clearField(6);
}

