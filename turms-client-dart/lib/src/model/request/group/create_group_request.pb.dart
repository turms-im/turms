///
//  Generated code. Do not modify.
//  source: request/group/create_group_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class CreateGroupRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'CreateGroupRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aOS(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'name')
    ..aOS(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'intro')
    ..aOS(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'announcement')
    ..a<$core.int>(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'minimumScore', $pb.PbFieldType.O3)
    ..aInt64(5, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupTypeId')
    ..aInt64(6, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'muteEndDate')
    ..hasRequiredFields = false
  ;

  CreateGroupRequest._() : super();
  factory CreateGroupRequest({
    $core.String? name,
    $core.String? intro,
    $core.String? announcement,
    $core.int? minimumScore,
    $fixnum.Int64? groupTypeId,
    $fixnum.Int64? muteEndDate,
  }) {
    final _result = create();
    if (name != null) {
      _result.name = name;
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
    return _result;
  }
  factory CreateGroupRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory CreateGroupRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  CreateGroupRequest clone() => CreateGroupRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  CreateGroupRequest copyWith(void Function(CreateGroupRequest) updates) => super.copyWith((message) => updates(message as CreateGroupRequest)) as CreateGroupRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static CreateGroupRequest create() => CreateGroupRequest._();
  CreateGroupRequest createEmptyInstance() => create();
  static $pb.PbList<CreateGroupRequest> createRepeated() => $pb.PbList<CreateGroupRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateGroupRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<CreateGroupRequest>(create);
  static CreateGroupRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get name => $_getSZ(0);
  @$pb.TagNumber(1)
  set name($core.String v) { $_setString(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasName() => $_has(0);
  @$pb.TagNumber(1)
  void clearName() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get intro => $_getSZ(1);
  @$pb.TagNumber(2)
  set intro($core.String v) { $_setString(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasIntro() => $_has(1);
  @$pb.TagNumber(2)
  void clearIntro() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get announcement => $_getSZ(2);
  @$pb.TagNumber(3)
  set announcement($core.String v) { $_setString(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasAnnouncement() => $_has(2);
  @$pb.TagNumber(3)
  void clearAnnouncement() => clearField(3);

  @$pb.TagNumber(4)
  $core.int get minimumScore => $_getIZ(3);
  @$pb.TagNumber(4)
  set minimumScore($core.int v) { $_setSignedInt32(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasMinimumScore() => $_has(3);
  @$pb.TagNumber(4)
  void clearMinimumScore() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get groupTypeId => $_getI64(4);
  @$pb.TagNumber(5)
  set groupTypeId($fixnum.Int64 v) { $_setInt64(4, v); }
  @$pb.TagNumber(5)
  $core.bool hasGroupTypeId() => $_has(4);
  @$pb.TagNumber(5)
  void clearGroupTypeId() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get muteEndDate => $_getI64(5);
  @$pb.TagNumber(6)
  set muteEndDate($fixnum.Int64 v) { $_setInt64(5, v); }
  @$pb.TagNumber(6)
  $core.bool hasMuteEndDate() => $_has(5);
  @$pb.TagNumber(6)
  void clearMuteEndDate() => clearField(6);
}

