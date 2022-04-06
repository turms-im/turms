///
//  Generated code. Do not modify.
//  source: request/group/update_group_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateGroupRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'UpdateGroupRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupId')
    ..aOS(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupName')
    ..aOS(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'intro')
    ..aOS(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'announcement')
    ..a<$core.int>(5, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'minimumScore', $pb.PbFieldType.O3)
    ..aInt64(6, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupTypeId')
    ..aInt64(7, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'muteEndDate')
    ..aInt64(8, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'successorId')
    ..aOB(9, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'quitAfterTransfer')
    ..hasRequiredFields = false
  ;

  UpdateGroupRequest._() : super();
  factory UpdateGroupRequest({
    $fixnum.Int64? groupId,
    $core.String? groupName,
    $core.String? intro,
    $core.String? announcement,
    $core.int? minimumScore,
    $fixnum.Int64? groupTypeId,
    $fixnum.Int64? muteEndDate,
    $fixnum.Int64? successorId,
    $core.bool? quitAfterTransfer,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (groupName != null) {
      _result.groupName = groupName;
    }
    if (intro != null) {
      _result.intro = intro;
    }
    if (announcement != null) {
      _result.announcement = announcement;
    }
    if (minimumScore != null) {
      _result.minimumScore = minimumScore;
    }
    if (groupTypeId != null) {
      _result.groupTypeId = groupTypeId;
    }
    if (muteEndDate != null) {
      _result.muteEndDate = muteEndDate;
    }
    if (successorId != null) {
      _result.successorId = successorId;
    }
    if (quitAfterTransfer != null) {
      _result.quitAfterTransfer = quitAfterTransfer;
    }
    return _result;
  }
  factory UpdateGroupRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory UpdateGroupRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  UpdateGroupRequest clone() => UpdateGroupRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  UpdateGroupRequest copyWith(void Function(UpdateGroupRequest) updates) => super.copyWith((message) => updates(message as UpdateGroupRequest)) as UpdateGroupRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UpdateGroupRequest create() => UpdateGroupRequest._();
  UpdateGroupRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateGroupRequest> createRepeated() => $pb.PbList<UpdateGroupRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateGroupRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<UpdateGroupRequest>(create);
  static UpdateGroupRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get groupName => $_getSZ(1);
  @$pb.TagNumber(2)
  set groupName($core.String v) { $_setString(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasGroupName() => $_has(1);
  @$pb.TagNumber(2)
  void clearGroupName() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get intro => $_getSZ(2);
  @$pb.TagNumber(3)
  set intro($core.String v) { $_setString(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasIntro() => $_has(2);
  @$pb.TagNumber(3)
  void clearIntro() => clearField(3);

  @$pb.TagNumber(4)
  $core.String get announcement => $_getSZ(3);
  @$pb.TagNumber(4)
  set announcement($core.String v) { $_setString(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasAnnouncement() => $_has(3);
  @$pb.TagNumber(4)
  void clearAnnouncement() => clearField(4);

  @$pb.TagNumber(5)
  $core.int get minimumScore => $_getIZ(4);
  @$pb.TagNumber(5)
  set minimumScore($core.int v) { $_setSignedInt32(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasMinimumScore() => $_has(4);
  @$pb.TagNumber(5)
  void clearMinimumScore() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get groupTypeId => $_getI64(5);
  @$pb.TagNumber(6)
  set groupTypeId($fixnum.Int64 v) { $_setInt64(5, v); }
  @$pb.TagNumber(6)
  $core.bool hasGroupTypeId() => $_has(5);
  @$pb.TagNumber(6)
  void clearGroupTypeId() => clearField(6);

  @$pb.TagNumber(7)
  $fixnum.Int64 get muteEndDate => $_getI64(6);
  @$pb.TagNumber(7)
  set muteEndDate($fixnum.Int64 v) { $_setInt64(6, v); }
  @$pb.TagNumber(7)
  $core.bool hasMuteEndDate() => $_has(6);
  @$pb.TagNumber(7)
  void clearMuteEndDate() => clearField(7);

  @$pb.TagNumber(8)
  $fixnum.Int64 get successorId => $_getI64(7);
  @$pb.TagNumber(8)
  set successorId($fixnum.Int64 v) { $_setInt64(7, v); }
  @$pb.TagNumber(8)
  $core.bool hasSuccessorId() => $_has(7);
  @$pb.TagNumber(8)
  void clearSuccessorId() => clearField(8);

  @$pb.TagNumber(9)
  $core.bool get quitAfterTransfer => $_getBF(8);
  @$pb.TagNumber(9)
  set quitAfterTransfer($core.bool v) { $_setBool(8, v); }
  @$pb.TagNumber(9)
  $core.bool hasQuitAfterTransfer() => $_has(8);
  @$pb.TagNumber(9)
  void clearQuitAfterTransfer() => clearField(9);
}

