//
//  Generated code. Do not modify.
//  source: request/group/update_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateGroupRequest extends $pb.GeneratedMessage {
  factory UpdateGroupRequest({
    $fixnum.Int64? groupId,
    $core.bool? quitAfterTransfer,
    $core.String? name,
    $core.String? intro,
    $core.String? announcement,
    $core.int? minScore,
    $fixnum.Int64? typeId,
    $fixnum.Int64? muteEndDate,
    $fixnum.Int64? successorId,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (quitAfterTransfer != null) {
      $result.quitAfterTransfer = quitAfterTransfer;
    }
    if (name != null) {
      $result.name = name;
    }
    if (intro != null) {
      $result.intro = intro;
    }
    if (announcement != null) {
      $result.announcement = announcement;
    }
    if (minScore != null) {
      $result.minScore = minScore;
    }
    if (typeId != null) {
      $result.typeId = typeId;
    }
    if (muteEndDate != null) {
      $result.muteEndDate = muteEndDate;
    }
    if (successorId != null) {
      $result.successorId = successorId;
    }
    return $result;
  }
  UpdateGroupRequest._() : super();
  factory UpdateGroupRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateGroupRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateGroupRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..aOB(2, _omitFieldNames ? '' : 'quitAfterTransfer')
    ..aOS(3, _omitFieldNames ? '' : 'name')
    ..aOS(4, _omitFieldNames ? '' : 'intro')
    ..aOS(5, _omitFieldNames ? '' : 'announcement')
    ..a<$core.int>(6, _omitFieldNames ? '' : 'minScore', $pb.PbFieldType.O3)
    ..aInt64(7, _omitFieldNames ? '' : 'typeId')
    ..aInt64(8, _omitFieldNames ? '' : 'muteEndDate')
    ..aInt64(9, _omitFieldNames ? '' : 'successorId')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateGroupRequest clone() => UpdateGroupRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateGroupRequest copyWith(void Function(UpdateGroupRequest) updates) =>
      super.copyWith((message) => updates(message as UpdateGroupRequest))
          as UpdateGroupRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateGroupRequest create() => UpdateGroupRequest._();
  UpdateGroupRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateGroupRequest> createRepeated() =>
      $pb.PbList<UpdateGroupRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateGroupRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateGroupRequest>(create);
  static UpdateGroupRequest? _defaultInstance;

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

  /// Update options
  @$pb.TagNumber(2)
  $core.bool get quitAfterTransfer => $_getBF(1);
  @$pb.TagNumber(2)
  set quitAfterTransfer($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasQuitAfterTransfer() => $_has(1);
  @$pb.TagNumber(2)
  void clearQuitAfterTransfer() => clearField(2);

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
  $core.String get intro => $_getSZ(3);
  @$pb.TagNumber(4)
  set intro($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasIntro() => $_has(3);
  @$pb.TagNumber(4)
  void clearIntro() => clearField(4);

  @$pb.TagNumber(5)
  $core.String get announcement => $_getSZ(4);
  @$pb.TagNumber(5)
  set announcement($core.String v) {
    $_setString(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasAnnouncement() => $_has(4);
  @$pb.TagNumber(5)
  void clearAnnouncement() => clearField(5);

  @$pb.TagNumber(6)
  $core.int get minScore => $_getIZ(5);
  @$pb.TagNumber(6)
  set minScore($core.int v) {
    $_setSignedInt32(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasMinScore() => $_has(5);
  @$pb.TagNumber(6)
  void clearMinScore() => clearField(6);

  @$pb.TagNumber(7)
  $fixnum.Int64 get typeId => $_getI64(6);
  @$pb.TagNumber(7)
  set typeId($fixnum.Int64 v) {
    $_setInt64(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasTypeId() => $_has(6);
  @$pb.TagNumber(7)
  void clearTypeId() => clearField(7);

  @$pb.TagNumber(8)
  $fixnum.Int64 get muteEndDate => $_getI64(7);
  @$pb.TagNumber(8)
  set muteEndDate($fixnum.Int64 v) {
    $_setInt64(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasMuteEndDate() => $_has(7);
  @$pb.TagNumber(8)
  void clearMuteEndDate() => clearField(8);

  @$pb.TagNumber(9)
  $fixnum.Int64 get successorId => $_getI64(8);
  @$pb.TagNumber(9)
  set successorId($fixnum.Int64 v) {
    $_setInt64(8, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasSuccessorId() => $_has(8);
  @$pb.TagNumber(9)
  void clearSuccessorId() => clearField(9);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
