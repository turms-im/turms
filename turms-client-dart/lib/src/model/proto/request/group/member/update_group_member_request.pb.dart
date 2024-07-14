//
//  Generated code. Do not modify.
//  source: request/group/member/update_group_member_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../constant/group_member_role.pbenum.dart' as $1;
import '../../../model/common/value.pb.dart' as $0;

class UpdateGroupMemberRequest extends $pb.GeneratedMessage {
  factory UpdateGroupMemberRequest({
    $fixnum.Int64? groupId,
    $fixnum.Int64? memberId,
    $core.String? name,
    $1.GroupMemberRole? role,
    $fixnum.Int64? muteEndDate,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (memberId != null) {
      $result.memberId = memberId;
    }
    if (name != null) {
      $result.name = name;
    }
    if (role != null) {
      $result.role = role;
    }
    if (muteEndDate != null) {
      $result.muteEndDate = muteEndDate;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UpdateGroupMemberRequest._() : super();
  factory UpdateGroupMemberRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateGroupMemberRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateGroupMemberRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..aInt64(2, _omitFieldNames ? '' : 'memberId')
    ..aOS(3, _omitFieldNames ? '' : 'name')
    ..e<$1.GroupMemberRole>(
        4, _omitFieldNames ? '' : 'role', $pb.PbFieldType.OE,
        defaultOrMaker: $1.GroupMemberRole.OWNER,
        valueOf: $1.GroupMemberRole.valueOf,
        enumValues: $1.GroupMemberRole.values)
    ..aInt64(5, _omitFieldNames ? '' : 'muteEndDate')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateGroupMemberRequest clone() =>
      UpdateGroupMemberRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateGroupMemberRequest copyWith(
          void Function(UpdateGroupMemberRequest) updates) =>
      super.copyWith((message) => updates(message as UpdateGroupMemberRequest))
          as UpdateGroupMemberRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateGroupMemberRequest create() => UpdateGroupMemberRequest._();
  UpdateGroupMemberRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateGroupMemberRequest> createRepeated() =>
      $pb.PbList<UpdateGroupMemberRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateGroupMemberRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateGroupMemberRequest>(create);
  static UpdateGroupMemberRequest? _defaultInstance;

  /// Query filter
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
  $fixnum.Int64 get memberId => $_getI64(1);
  @$pb.TagNumber(2)
  set memberId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasMemberId() => $_has(1);
  @$pb.TagNumber(2)
  void clearMemberId() => clearField(2);

  /// Update
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
  $fixnum.Int64 get muteEndDate => $_getI64(4);
  @$pb.TagNumber(5)
  set muteEndDate($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasMuteEndDate() => $_has(4);
  @$pb.TagNumber(5)
  void clearMuteEndDate() => clearField(5);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(5);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
