//
//  Generated code. Do not modify.
//  source: request/group/query_groups_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../model/common/value.pb.dart' as $0;

class QueryGroupsRequest extends $pb.GeneratedMessage {
  factory QueryGroupsRequest({
    $core.Iterable<$fixnum.Int64>? groupIds,
    $fixnum.Int64? lastUpdatedDate,
    $core.String? name,
    $core.int? skip,
    $core.int? limit,
    $core.Iterable<$core.int>? fieldsToHighlight,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (groupIds != null) {
      $result.groupIds.addAll(groupIds);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    if (name != null) {
      $result.name = name;
    }
    if (skip != null) {
      $result.skip = skip;
    }
    if (limit != null) {
      $result.limit = limit;
    }
    if (fieldsToHighlight != null) {
      $result.fieldsToHighlight.addAll(fieldsToHighlight);
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  QueryGroupsRequest._() : super();
  factory QueryGroupsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryGroupsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryGroupsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'groupIds', $pb.PbFieldType.K6)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..aOS(3, _omitFieldNames ? '' : 'name')
    ..a<$core.int>(10, _omitFieldNames ? '' : 'skip', $pb.PbFieldType.O3)
    ..a<$core.int>(11, _omitFieldNames ? '' : 'limit', $pb.PbFieldType.O3)
    ..p<$core.int>(
        12, _omitFieldNames ? '' : 'fieldsToHighlight', $pb.PbFieldType.K3)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryGroupsRequest clone() => QueryGroupsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryGroupsRequest copyWith(void Function(QueryGroupsRequest) updates) =>
      super.copyWith((message) => updates(message as QueryGroupsRequest))
          as QueryGroupsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryGroupsRequest create() => QueryGroupsRequest._();
  QueryGroupsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryGroupsRequest> createRepeated() =>
      $pb.PbList<QueryGroupsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryGroupsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryGroupsRequest>(create);
  static QueryGroupsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get groupIds => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);

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

  @$pb.TagNumber(10)
  $core.int get skip => $_getIZ(3);
  @$pb.TagNumber(10)
  set skip($core.int v) {
    $_setSignedInt32(3, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasSkip() => $_has(3);
  @$pb.TagNumber(10)
  void clearSkip() => clearField(10);

  @$pb.TagNumber(11)
  $core.int get limit => $_getIZ(4);
  @$pb.TagNumber(11)
  set limit($core.int v) {
    $_setSignedInt32(4, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasLimit() => $_has(4);
  @$pb.TagNumber(11)
  void clearLimit() => clearField(11);

  @$pb.TagNumber(12)
  $core.List<$core.int> get fieldsToHighlight => $_getList(5);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(6);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
