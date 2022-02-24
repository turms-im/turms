///
//  Generated code. Do not modify.
//  source: request/group/member/create_group_member_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../constant/group_member_role.pbenum.dart' as $0;

class CreateGroupMemberRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'CreateGroupMemberRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupId')
    ..aInt64(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userId')
    ..aOS(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'name')
    ..e<$0.GroupMemberRole>(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'role', $pb.PbFieldType.OE, defaultOrMaker: $0.GroupMemberRole.OWNER, valueOf: $0.GroupMemberRole.valueOf, enumValues: $0.GroupMemberRole.values)
    ..aInt64(5, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'muteEndDate')
    ..hasRequiredFields = false
  ;

  CreateGroupMemberRequest._() : super();
  factory CreateGroupMemberRequest({
    $fixnum.Int64? groupId,
    $fixnum.Int64? userId,
    $core.String? name,
    $0.GroupMemberRole? role,
    $fixnum.Int64? muteEndDate,
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
    if (muteEndDate != null) {
      _result.muteEndDate = muteEndDate;
    }
    return _result;
  }
  factory CreateGroupMemberRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory CreateGroupMemberRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  CreateGroupMemberRequest clone() => CreateGroupMemberRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  CreateGroupMemberRequest copyWith(void Function(CreateGroupMemberRequest) updates) => super.copyWith((message) => updates(message as CreateGroupMemberRequest)) as CreateGroupMemberRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static CreateGroupMemberRequest create() => CreateGroupMemberRequest._();
  CreateGroupMemberRequest createEmptyInstance() => create();
  static $pb.PbList<CreateGroupMemberRequest> createRepeated() => $pb.PbList<CreateGroupMemberRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateGroupMemberRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<CreateGroupMemberRequest>(create);
  static CreateGroupMemberRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get userId => $_getI64(1);
  @$pb.TagNumber(2)
  set userId($fixnum.Int64 v) { $_setInt64(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasUserId() => $_has(1);
  @$pb.TagNumber(2)
  void clearUserId() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get name => $_getSZ(2);
  @$pb.TagNumber(3)
  set name($core.String v) { $_setString(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasName() => $_has(2);
  @$pb.TagNumber(3)
  void clearName() => clearField(3);

  @$pb.TagNumber(4)
  $0.GroupMemberRole get role => $_getN(3);
  @$pb.TagNumber(4)
  set role($0.GroupMemberRole v) { setField(4, v); }
  @$pb.TagNumber(4)
  $core.bool hasRole() => $_has(3);
  @$pb.TagNumber(4)
  void clearRole() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get muteEndDate => $_getI64(4);
  @$pb.TagNumber(5)
  set muteEndDate($fixnum.Int64 v) { $_setInt64(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasMuteEndDate() => $_has(4);
  @$pb.TagNumber(5)
  void clearMuteEndDate() => clearField(5);
}

