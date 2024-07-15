//
//  Generated code. Do not modify.
//  source: model/group/group_member.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/device_type.pbenum.dart' as $3;
import '../../constant/group_member_role.pbenum.dart' as $1;
import '../../constant/user_status.pbenum.dart' as $2;
import '../common/value.pb.dart' as $0;

class GroupMember extends $pb.GeneratedMessage {
  factory GroupMember({
    $fixnum.Int64? groupId,
    $fixnum.Int64? userId,
    $core.String? name,
    $1.GroupMemberRole? role,
    $fixnum.Int64? joinDate,
    $fixnum.Int64? muteEndDate,
    $2.UserStatus? userStatus,
    $core.Iterable<$3.DeviceType>? usingDeviceTypes,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (userId != null) {
      $result.userId = userId;
    }
    if (name != null) {
      $result.name = name;
    }
    if (role != null) {
      $result.role = role;
    }
    if (joinDate != null) {
      $result.joinDate = joinDate;
    }
    if (muteEndDate != null) {
      $result.muteEndDate = muteEndDate;
    }
    if (userStatus != null) {
      $result.userStatus = userStatus;
    }
    if (usingDeviceTypes != null) {
      $result.usingDeviceTypes.addAll(usingDeviceTypes);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  GroupMember._() : super();
  factory GroupMember.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupMember.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'GroupMember',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..aInt64(2, _omitFieldNames ? '' : 'userId')
    ..aOS(3, _omitFieldNames ? '' : 'name')
    ..e<$1.GroupMemberRole>(
        4, _omitFieldNames ? '' : 'role', $pb.PbFieldType.OE,
        defaultOrMaker: $1.GroupMemberRole.OWNER,
        valueOf: $1.GroupMemberRole.valueOf,
        enumValues: $1.GroupMemberRole.values)
    ..aInt64(5, _omitFieldNames ? '' : 'joinDate')
    ..aInt64(6, _omitFieldNames ? '' : 'muteEndDate')
    ..e<$2.UserStatus>(
        7, _omitFieldNames ? '' : 'userStatus', $pb.PbFieldType.OE,
        defaultOrMaker: $2.UserStatus.AVAILABLE,
        valueOf: $2.UserStatus.valueOf,
        enumValues: $2.UserStatus.values)
    ..pc<$3.DeviceType>(
        8, _omitFieldNames ? '' : 'usingDeviceTypes', $pb.PbFieldType.KE,
        valueOf: $3.DeviceType.valueOf,
        enumValues: $3.DeviceType.values,
        defaultEnumValue: $3.DeviceType.DESKTOP)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupMember clone() => GroupMember()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupMember copyWith(void Function(GroupMember) updates) =>
      super.copyWith((message) => updates(message as GroupMember))
          as GroupMember;

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
  $1.GroupMemberRole get role => $_getN(3);
  @$pb.TagNumber(4)
  set role($1.GroupMemberRole v) {
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
  $2.UserStatus get userStatus => $_getN(6);
  @$pb.TagNumber(7)
  set userStatus($2.UserStatus v) {
    setField(7, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasUserStatus() => $_has(6);
  @$pb.TagNumber(7)
  void clearUserStatus() => clearField(7);

  @$pb.TagNumber(8)
  $core.List<$3.DeviceType> get usingDeviceTypes => $_getList(7);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(8);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
