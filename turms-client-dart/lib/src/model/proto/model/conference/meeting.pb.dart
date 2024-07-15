//
//  Generated code. Do not modify.
//  source: model/conference/meeting.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../common/value.pb.dart' as $0;

class Meeting extends $pb.GeneratedMessage {
  factory Meeting({
    $fixnum.Int64? id,
    $fixnum.Int64? userId,
    $fixnum.Int64? groupId,
    $fixnum.Int64? creatorId,
    $core.String? accessToken,
    $core.String? name,
    $core.String? intro,
    $core.String? password,
    $fixnum.Int64? startDate,
    $fixnum.Int64? endDate,
    $fixnum.Int64? cancelDate,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (id != null) {
      $result.id = id;
    }
    if (userId != null) {
      $result.userId = userId;
    }
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (creatorId != null) {
      $result.creatorId = creatorId;
    }
    if (accessToken != null) {
      $result.accessToken = accessToken;
    }
    if (name != null) {
      $result.name = name;
    }
    if (intro != null) {
      $result.intro = intro;
    }
    if (password != null) {
      $result.password = password;
    }
    if (startDate != null) {
      $result.startDate = startDate;
    }
    if (endDate != null) {
      $result.endDate = endDate;
    }
    if (cancelDate != null) {
      $result.cancelDate = cancelDate;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  Meeting._() : super();
  factory Meeting.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory Meeting.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'Meeting',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'id')
    ..aInt64(2, _omitFieldNames ? '' : 'userId')
    ..aInt64(3, _omitFieldNames ? '' : 'groupId')
    ..aInt64(4, _omitFieldNames ? '' : 'creatorId')
    ..aOS(5, _omitFieldNames ? '' : 'accessToken')
    ..aOS(6, _omitFieldNames ? '' : 'name')
    ..aOS(7, _omitFieldNames ? '' : 'intro')
    ..aOS(8, _omitFieldNames ? '' : 'password')
    ..aInt64(9, _omitFieldNames ? '' : 'startDate')
    ..aInt64(10, _omitFieldNames ? '' : 'endDate')
    ..aInt64(11, _omitFieldNames ? '' : 'cancelDate')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  Meeting clone() => Meeting()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  Meeting copyWith(void Function(Meeting) updates) =>
      super.copyWith((message) => updates(message as Meeting)) as Meeting;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static Meeting create() => Meeting._();
  Meeting createEmptyInstance() => create();
  static $pb.PbList<Meeting> createRepeated() => $pb.PbList<Meeting>();
  @$core.pragma('dart2js:noInline')
  static Meeting getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<Meeting>(create);
  static Meeting? _defaultInstance;

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
  $fixnum.Int64 get groupId => $_getI64(2);
  @$pb.TagNumber(3)
  set groupId($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasGroupId() => $_has(2);
  @$pb.TagNumber(3)
  void clearGroupId() => clearField(3);

  @$pb.TagNumber(4)
  $fixnum.Int64 get creatorId => $_getI64(3);
  @$pb.TagNumber(4)
  set creatorId($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasCreatorId() => $_has(3);
  @$pb.TagNumber(4)
  void clearCreatorId() => clearField(4);

  @$pb.TagNumber(5)
  $core.String get accessToken => $_getSZ(4);
  @$pb.TagNumber(5)
  set accessToken($core.String v) {
    $_setString(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasAccessToken() => $_has(4);
  @$pb.TagNumber(5)
  void clearAccessToken() => clearField(5);

  @$pb.TagNumber(6)
  $core.String get name => $_getSZ(5);
  @$pb.TagNumber(6)
  set name($core.String v) {
    $_setString(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasName() => $_has(5);
  @$pb.TagNumber(6)
  void clearName() => clearField(6);

  @$pb.TagNumber(7)
  $core.String get intro => $_getSZ(6);
  @$pb.TagNumber(7)
  set intro($core.String v) {
    $_setString(6, v);
  }

  @$pb.TagNumber(7)
  $core.bool hasIntro() => $_has(6);
  @$pb.TagNumber(7)
  void clearIntro() => clearField(7);

  @$pb.TagNumber(8)
  $core.String get password => $_getSZ(7);
  @$pb.TagNumber(8)
  set password($core.String v) {
    $_setString(7, v);
  }

  @$pb.TagNumber(8)
  $core.bool hasPassword() => $_has(7);
  @$pb.TagNumber(8)
  void clearPassword() => clearField(8);

  @$pb.TagNumber(9)
  $fixnum.Int64 get startDate => $_getI64(8);
  @$pb.TagNumber(9)
  set startDate($fixnum.Int64 v) {
    $_setInt64(8, v);
  }

  @$pb.TagNumber(9)
  $core.bool hasStartDate() => $_has(8);
  @$pb.TagNumber(9)
  void clearStartDate() => clearField(9);

  @$pb.TagNumber(10)
  $fixnum.Int64 get endDate => $_getI64(9);
  @$pb.TagNumber(10)
  set endDate($fixnum.Int64 v) {
    $_setInt64(9, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasEndDate() => $_has(9);
  @$pb.TagNumber(10)
  void clearEndDate() => clearField(10);

  @$pb.TagNumber(11)
  $fixnum.Int64 get cancelDate => $_getI64(10);
  @$pb.TagNumber(11)
  set cancelDate($fixnum.Int64 v) {
    $_setInt64(10, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasCancelDate() => $_has(10);
  @$pb.TagNumber(11)
  void clearCancelDate() => clearField(11);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(11);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
