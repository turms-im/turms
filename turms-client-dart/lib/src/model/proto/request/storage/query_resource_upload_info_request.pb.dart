//
//  Generated code. Do not modify.
//  source: request/storage/query_resource_upload_info_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/storage_resource_type.pbenum.dart' as $0;

class QueryResourceUploadInfoRequest extends $pb.GeneratedMessage {
  factory QueryResourceUploadInfoRequest({
    $0.StorageResourceType? type,
    $fixnum.Int64? idNum,
    $core.String? idStr,
    $core.String? name,
    $core.String? mediaType,
    $core.Map<$core.String, $core.String>? extra,
  }) {
    final $result = create();
    if (type != null) {
      $result.type = type;
    }
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
    if (extra != null) {
      $result.extra.addAll(extra);
    }
    return $result;
  }
  QueryResourceUploadInfoRequest._() : super();
  factory QueryResourceUploadInfoRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryResourceUploadInfoRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryResourceUploadInfoRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..e<$0.StorageResourceType>(
        1, _omitFieldNames ? '' : 'type', $pb.PbFieldType.OE,
        defaultOrMaker: $0.StorageResourceType.USER_PROFILE_PICTURE,
        valueOf: $0.StorageResourceType.valueOf,
        enumValues: $0.StorageResourceType.values)
    ..aInt64(2, _omitFieldNames ? '' : 'idNum')
    ..aOS(3, _omitFieldNames ? '' : 'idStr')
    ..aOS(4, _omitFieldNames ? '' : 'name')
    ..aOS(5, _omitFieldNames ? '' : 'mediaType')
    ..m<$core.String, $core.String>(6, _omitFieldNames ? '' : 'extra',
        entryClassName: 'QueryResourceUploadInfoRequest.ExtraEntry',
        keyFieldType: $pb.PbFieldType.OS,
        valueFieldType: $pb.PbFieldType.OS,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryResourceUploadInfoRequest clone() =>
      QueryResourceUploadInfoRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryResourceUploadInfoRequest copyWith(
          void Function(QueryResourceUploadInfoRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryResourceUploadInfoRequest))
          as QueryResourceUploadInfoRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryResourceUploadInfoRequest create() =>
      QueryResourceUploadInfoRequest._();
  QueryResourceUploadInfoRequest createEmptyInstance() => create();
  static $pb.PbList<QueryResourceUploadInfoRequest> createRepeated() =>
      $pb.PbList<QueryResourceUploadInfoRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryResourceUploadInfoRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryResourceUploadInfoRequest>(create);
  static QueryResourceUploadInfoRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $0.StorageResourceType get type => $_getN(0);
  @$pb.TagNumber(1)
  set type($0.StorageResourceType v) {
    setField(1, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasType() => $_has(0);
  @$pb.TagNumber(1)
  void clearType() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get idNum => $_getI64(1);
  @$pb.TagNumber(2)
  set idNum($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasIdNum() => $_has(1);
  @$pb.TagNumber(2)
  void clearIdNum() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get idStr => $_getSZ(2);
  @$pb.TagNumber(3)
  set idStr($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasIdStr() => $_has(2);
  @$pb.TagNumber(3)
  void clearIdStr() => clearField(3);

  @$pb.TagNumber(4)
  $core.String get name => $_getSZ(3);
  @$pb.TagNumber(4)
  set name($core.String v) {
    $_setString(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasName() => $_has(3);
  @$pb.TagNumber(4)
  void clearName() => clearField(4);

  @$pb.TagNumber(5)
  $core.String get mediaType => $_getSZ(4);
  @$pb.TagNumber(5)
  set mediaType($core.String v) {
    $_setString(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasMediaType() => $_has(4);
  @$pb.TagNumber(5)
  void clearMediaType() => clearField(5);

  @$pb.TagNumber(6)
  $core.Map<$core.String, $core.String> get extra => $_getMap(5);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
