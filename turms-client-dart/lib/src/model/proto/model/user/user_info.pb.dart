//
//  Generated code. Do not modify.
//  source: model/user/user_info.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/profile_access_strategy.pbenum.dart' as $1;
import '../common/value.pb.dart' as $0;

class UserInfo extends $pb.GeneratedMessage {
  factory UserInfo({
    $fixnum.Int64? id,
    $core.String? name,
    $core.String? intro,
    $core.String? profilePicture,
    $1.ProfileAccessStrategy? profileAccessStrategy,
    $fixnum.Int64? registrationDate,
    $fixnum.Int64? lastUpdatedDate,
    $core.bool? active,
    $core.Map<$core.String, $0.Value>? userDefinedAttributes,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (id != null) {
      $result.id = id;
    }
    if (name != null) {
      $result.name = name;
    }
    if (intro != null) {
      $result.intro = intro;
    }
    if (profilePicture != null) {
      $result.profilePicture = profilePicture;
    }
    if (profileAccessStrategy != null) {
      $result.profileAccessStrategy = profileAccessStrategy;
    }
    if (registrationDate != null) {
      $result.registrationDate = registrationDate;
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    if (active != null) {
      $result.active = active;
    }
    if (userDefinedAttributes != null) {
      $result.userDefinedAttributes.addAll(userDefinedAttributes);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UserInfo._() : super();
  factory UserInfo.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UserInfo.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UserInfo',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'id')
    ..aOS(2, _omitFieldNames ? '' : 'name')
    ..aOS(3, _omitFieldNames ? '' : 'intro')
    ..aOS(4, _omitFieldNames ? '' : 'profilePicture')
    ..e<$1.ProfileAccessStrategy>(
        5, _omitFieldNames ? '' : 'profileAccessStrategy', $pb.PbFieldType.OE,
        defaultOrMaker: $1.ProfileAccessStrategy.ALL,
        valueOf: $1.ProfileAccessStrategy.valueOf,
        enumValues: $1.ProfileAccessStrategy.values)
    ..aInt64(6, _omitFieldNames ? '' : 'registrationDate')
    ..aInt64(7, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..aOB(8, _omitFieldNames ? '' : 'active')
    ..m<$core.String, $0.Value>(
        9, _omitFieldNames ? '' : 'userDefinedAttributes',
        entryClassName: 'UserInfo.UserDefinedAttributesEntry',
        keyFieldType: $pb.PbFieldType.OS,
        valueFieldType: $pb.PbFieldType.OM,
        valueCreator: $0.Value.create,
        valueDefaultOrMaker: $0.Value.getDefault,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UserInfo clone() => UserInfo()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UserInfo copyWith(void Function(UserInfo) updates) =>
      super.copyWith((message) => updates(message as UserInfo)) as UserInfo;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UserInfo create() => UserInfo._();
  UserInfo createEmptyInstance() => create();
  static $pb.PbList<UserInfo> createRepeated() => $pb.PbList<UserInfo>();
  @$core.pragma('dart2js:noInline')
  static UserInfo getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UserInfo>(create);
  static UserInfo? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get id => $_getI64(0);
  @$pb.TagNumber(1)
  set id($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasId() => $_has(0);
  @$pb.TagNumber(1)
  void clearId() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get name => $_getSZ(1);
  @$pb.TagNumber(2)
  set name($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasName() => $_has(1);
  @$pb.TagNumber(2)
  void clearName() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get intro => $_getSZ(2);
  @$pb.TagNumber(3)
  set intro($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasIntro() => $_has(2);
  @$pb.TagNumber(3)
  void clearIntro() => clearField(3);

  @$pb.TagNumber(4)
  $core.String get profilePicture => $_getSZ(3);
  @$pb.TagNumber(4)
  set profilePicture($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasProfilePicture() => $_has(3);
  @$pb.TagNumber(4)
  void clearProfilePicture() => clearField(4);

  @$pb.TagNumber(5)
  $1.ProfileAccessStrategy get profileAccessStrategy => $_getN(4);
  @$pb.TagNumber(5)
  set profileAccessStrategy($1.ProfileAccessStrategy v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasProfileAccessStrategy() => $_has(4);
  @$pb.TagNumber(5)
  void clearProfileAccessStrategy() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get registrationDate => $_getI64(5);
  @$pb.TagNumber(6)
  set registrationDate($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasRegistrationDate() => $_has(5);
  @$pb.TagNumber(6)
  void clearRegistrationDate() => clearField(6);

  @$pb.TagNumber(7)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(6);
  @$pb.TagNumber(7)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasLastUpdatedDate() => $_has(6);
  @$pb.TagNumber(7)
  void clearLastUpdatedDate() => clearField(7);

  @$pb.TagNumber(8)
  $core.bool get active => $_getBF(7);
  @$pb.TagNumber(8)
  set active($core.bool v) {
    $_setBool(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasActive() => $_has(7);
  @$pb.TagNumber(8)
  void clearActive() => clearField(8);

  @$pb.TagNumber(9)
  $core.Map<$core.String, $0.Value> get userDefinedAttributes => $_getMap(8);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(9);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
