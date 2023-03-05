///
//  Generated code. Do not modify.
//  source: model/group/group_member.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/group_member_role.pbenum.dart' as $0;
import '../../constant/user_status.pbenum.dart' as $1;
import '../../constant/device_type.pbenum.dart' as $2;

class GroupMember extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'GroupMember',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupId')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userId')
    ..aOS(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'name')
    ..e<$0.GroupMemberRole>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'role',
        $pb.PbFieldType.OE,
        defaultOrMaker: $0.GroupMemberRole.OWNER,
        valueOf: $0.GroupMemberRole.valueOf,
        enumValues: $0.GroupMemberRole.values)
    ..aInt64(
        5,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'joinDate')
    ..aInt64(
        6,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'muteEndDate')
    ..e<$1.UserStatus>(
        7,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userStatus',
        $pb.PbFieldType.OE,
        defaultOrMaker: $1.UserStatus.AVAILABLE,
        valueOf: $1.UserStatus.valueOf,
        enumValues: $1.UserStatus.values)
    ..pc<$2.DeviceType>(
        8,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'usingDeviceTypes',
        $pb.PbFieldType.KE,
        valueOf: $2.DeviceType.valueOf,
        enumValues: $2.DeviceType.values,
        defaultEnumValue: $2.DeviceType.DESKTOP)
    ..hasRequiredFields = false;

  GroupMember._() : super();
  factory GroupMember({
    $fixnum.Int64? groupId,
    $fixnum.Int64? userId,
    $core.String? name,
    $0.GroupMemberRole? role,
    $fixnum.Int64? joinDate,
    $fixnum.Int64? muteEndDate,
    $1.UserStatus? userStatus,
    $core.Iterable<$2.DeviceType>? usingDeviceTypes,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (userId != null) {
      _result.userId = userId;
    }
    if (name != null) {
      _result.name = name;
    }
    if (role != null) {
      _result.role = role;
    }
    if (joinDate != null) {
      _result.joinDate = joinDate;
    }
    if (muteEndDate != null) {
      _result.muteEndDate = muteEndDate;
    }
    if (userStatus != null) {
      _result.userStatus = userStatus;
    }
    if (usingDeviceTypes != null) {
      _result.usingDeviceTypes.addAll(usingDeviceTypes);
    }
    return _result;
  }
  factory GroupMember.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupMember.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupMember clone() => GroupMember()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupMember copyWith(void Function(GroupMember) updates) =>
      super.copyWith((message) => updates(message as GroupMember))
          as GroupMember; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static GroupMember create() => GroupMember._();
  GroupMember createEmptyInstance() => create();
  static $pb.PbList<GroupMember> createRepeated() => $pb.PbList<GroupMember>();
  @$core.pragma('dart2js:noInline')
  static GroupMember getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupMember>(create);
  static GroupMember? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get userId => $_getI64(1);
  @$pb.TagNumber(2)
  set userId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasUserId() => $_has(1);
  @$pb.TagNumber(2)
  void clearUserId() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get name => $_getSZ(2);
  @$pb.TagNumber(3)
  set name($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasName() => $_has(2);
  @$pb.TagNumber(3)
  void clearName() => clearField(3);

  @$pb.TagNumber(4)
  $0.GroupMemberRole get role => $_getN(3);
  @$pb.TagNumber(4)
  set role($0.GroupMemberRole v) {
    setField(4, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasRole() => $_has(3);
  @$pb.TagNumber(4)
  void clearRole() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get joinDate => $_getI64(4);
  @$pb.TagNumber(5)
  set joinDate($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasJoinDate() => $_has(4);
  @$pb.TagNumber(5)
  void clearJoinDate() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get muteEndDate => $_getI64(5);
  @$pb.TagNumber(6)
  set muteEndDate($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasMuteEndDate() => $_has(5);
  @$pb.TagNumber(6)
  void clearMuteEndDate() => clearField(6);

  @$pb.TagNumber(7)
  $1.UserStatus get userStatus => $_getN(6);
  @$pb.TagNumber(7)
  set userStatus($1.UserStatus v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasUserStatus() => $_has(6);
  @$pb.TagNumber(7)
  void clearUserStatus() => clearField(7);

  @$pb.TagNumber(8)
  $core.List<$2.DeviceType> get usingDeviceTypes => $_getList(7);
}
