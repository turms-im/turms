//
//  Generated code. Do not modify.
//  source: request/group/create_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class CreateGroupRequest extends $pb.GeneratedMessage {
  factory CreateGroupRequest({
    $core.String? name,
    $core.String? intro,
    $core.String? announcement,
    $core.int? minScore,
    $fixnum.Int64? typeId,
    $fixnum.Int64? muteEndDate,
  }) {
    final $result = create();
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
    return $result;
  }
  CreateGroupRequest._() : super();
  factory CreateGroupRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateGroupRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateGroupRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOS(1, _omitFieldNames ? '' : 'name')
    ..aOS(2, _omitFieldNames ? '' : 'intro')
    ..aOS(3, _omitFieldNames ? '' : 'announcement')
    ..a<$core.int>(4, _omitFieldNames ? '' : 'minScore', $pb.PbFieldType.O3)
    ..aInt64(5, _omitFieldNames ? '' : 'typeId')
    ..aInt64(6, _omitFieldNames ? '' : 'muteEndDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateGroupRequest clone() => CreateGroupRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateGroupRequest copyWith(void Function(CreateGroupRequest) updates) =>
      super.copyWith((message) => updates(message as CreateGroupRequest))
          as CreateGroupRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateGroupRequest create() => CreateGroupRequest._();
  CreateGroupRequest createEmptyInstance() => create();
  static $pb.PbList<CreateGroupRequest> createRepeated() =>
      $pb.PbList<CreateGroupRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateGroupRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateGroupRequest>(create);
  static CreateGroupRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get name => $_getSZ(0);
  @$pb.TagNumber(1)
  set name($core.String v) {
    $_setString(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasName() => $_has(0);
  @$pb.TagNumber(1)
  void clearName() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get intro => $_getSZ(1);
  @$pb.TagNumber(2)
  set intro($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasIntro() => $_has(1);
  @$pb.TagNumber(2)
  void clearIntro() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get announcement => $_getSZ(2);
  @$pb.TagNumber(3)
  set announcement($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasAnnouncement() => $_has(2);
  @$pb.TagNumber(3)
  void clearAnnouncement() => clearField(3);

  @$pb.TagNumber(4)
  $core.int get minScore => $_getIZ(3);
  @$pb.TagNumber(4)
  set minScore($core.int v) {
    $_setSignedInt32(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasMinScore() => $_has(3);
  @$pb.TagNumber(4)
  void clearMinScore() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get typeId => $_getI64(4);
  @$pb.TagNumber(5)
  set typeId($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasTypeId() => $_has(4);
  @$pb.TagNumber(5)
  void clearTypeId() => clearField(5);

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
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
