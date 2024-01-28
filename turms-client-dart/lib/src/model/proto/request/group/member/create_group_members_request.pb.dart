//
//  Generated code. Do not modify.
//  source: request/group/member/create_group_members_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../constant/group_member_role.pbenum.dart' as $0;

class CreateGroupMembersRequest extends $pb.GeneratedMessage {
  factory CreateGroupMembersRequest({
    $fixnum.Int64? groupId,
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.String? name,
    $0.GroupMemberRole? role,
    $fixnum.Int64? muteEndDate,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (userIds != null) {
      $result.userIds.addAll(userIds);
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
    return $result;
  }
  CreateGroupMembersRequest._() : super();
  factory CreateGroupMembersRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateGroupMembersRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateGroupMembersRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..p<$fixnum.Int64>(2, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
    ..aOS(3, _omitFieldNames ? '' : 'name')
    ..e<$0.GroupMemberRole>(
        4, _omitFieldNames ? '' : 'role', $pb.PbFieldType.OE,
        defaultOrMaker: $0.GroupMemberRole.OWNER,
        valueOf: $0.GroupMemberRole.valueOf,
        enumValues: $0.GroupMemberRole.values)
    ..aInt64(5, _omitFieldNames ? '' : 'muteEndDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateGroupMembersRequest clone() =>
      CreateGroupMembersRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateGroupMembersRequest copyWith(
          void Function(CreateGroupMembersRequest) updates) =>
      super.copyWith((message) => updates(message as CreateGroupMembersRequest))
          as CreateGroupMembersRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateGroupMembersRequest create() => CreateGroupMembersRequest._();
  CreateGroupMembersRequest createEmptyInstance() => create();
  static $pb.PbList<CreateGroupMembersRequest> createRepeated() =>
      $pb.PbList<CreateGroupMembersRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateGroupMembersRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateGroupMembersRequest>(create);
  static CreateGroupMembersRequest? _defaultInstance;

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
  $core.List<$fixnum.Int64> get userIds => $_getList(1);

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
  $fixnum.Int64 get muteEndDate => $_getI64(4);
  @$pb.TagNumber(5)
  set muteEndDate($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasMuteEndDate() => $_has(4);
  @$pb.TagNumber(5)
  void clearMuteEndDate() => clearField(5);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
