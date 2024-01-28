//
//  Generated code. Do not modify.
//  source: model/storage/storage_resource_info.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class StorageResourceInfo extends $pb.GeneratedMessage {
  factory StorageResourceInfo({
    $fixnum.Int64? idNum,
    $core.String? idStr,
    $core.String? name,
    $core.String? mediaType,
    $fixnum.Int64? uploaderId,
    $fixnum.Int64? creationDate,
  }) {
    final $result = create();
    if (idNum != null) {
      $result.idNum = idNum;
    }
    if (idStr != null) {
      $result.idStr = idStr;
    }
    if (name != null) {
      $result.name = name;
    }
    if (mediaType != null) {
      $result.mediaType = mediaType;
    }
    if (uploaderId != null) {
      $result.uploaderId = uploaderId;
    }
    if (creationDate != null) {
      $result.creationDate = creationDate;
    }
    return $result;
  }
  StorageResourceInfo._() : super();
  factory StorageResourceInfo.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory StorageResourceInfo.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'StorageResourceInfo',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'idNum')
    ..aOS(2, _omitFieldNames ? '' : 'idStr')
    ..aOS(3, _omitFieldNames ? '' : 'name')
    ..aOS(4, _omitFieldNames ? '' : 'mediaType')
    ..aInt64(5, _omitFieldNames ? '' : 'uploaderId')
    ..aInt64(6, _omitFieldNames ? '' : 'creationDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  StorageResourceInfo clone() => StorageResourceInfo()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  StorageResourceInfo copyWith(void Function(StorageResourceInfo) updates) =>
      super.copyWith((message) => updates(message as StorageResourceInfo))
          as StorageResourceInfo;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static StorageResourceInfo create() => StorageResourceInfo._();
  StorageResourceInfo createEmptyInstance() => create();
  static $pb.PbList<StorageResourceInfo> createRepeated() =>
      $pb.PbList<StorageResourceInfo>();
  @$core.pragma('dart2js:noInline')
  static StorageResourceInfo getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<StorageResourceInfo>(create);
  static StorageResourceInfo? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get idNum => $_getI64(0);
  @$pb.TagNumber(1)
  set idNum($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasIdNum() => $_has(0);
  @$pb.TagNumber(1)
  void clearIdNum() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get idStr => $_getSZ(1);
  @$pb.TagNumber(2)
  set idStr($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasIdStr() => $_has(1);
  @$pb.TagNumber(2)
  void clearIdStr() => clearField(2);

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
  $core.String get mediaType => $_getSZ(3);
  @$pb.TagNumber(4)
  set mediaType($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasMediaType() => $_has(3);
  @$pb.TagNumber(4)
  void clearMediaType() => clearField(4);

  @$pb.TagNumber(5)
  $fixnum.Int64 get uploaderId => $_getI64(4);
  @$pb.TagNumber(5)
  set uploaderId($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasUploaderId() => $_has(4);
  @$pb.TagNumber(5)
  void clearUploaderId() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get creationDate => $_getI64(5);
  @$pb.TagNumber(6)
  set creationDate($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasCreationDate() => $_has(5);
  @$pb.TagNumber(6)
  void clearCreationDate() => clearField(6);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
