//
//  Generated code. Do not modify.
//  source: request/conference/query_meetings_request.proto
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

class QueryMeetingsRequest extends $pb.GeneratedMessage {
  factory QueryMeetingsRequest({
    $core.Iterable<$fixnum.Int64>? ids,
    $core.Iterable<$fixnum.Int64>? creatorIds,
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.Iterable<$fixnum.Int64>? groupIds,
    $fixnum.Int64? creationDateStart,
    $fixnum.Int64? creationDateEnd,
    $core.int? skip,
    $core.int? limit,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (ids != null) {
      $result.ids.addAll(ids);
    }
    if (creatorIds != null) {
      $result.creatorIds.addAll(creatorIds);
    }
    if (userIds != null) {
      $result.userIds.addAll(userIds);
    }
    if (groupIds != null) {
      $result.groupIds.addAll(groupIds);
    }
    if (creationDateStart != null) {
      $result.creationDateStart = creationDateStart;
    }
    if (creationDateEnd != null) {
      $result.creationDateEnd = creationDateEnd;
    }
    if (skip != null) {
      $result.skip = skip;
    }
    if (limit != null) {
      $result.limit = limit;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  QueryMeetingsRequest._() : super();
  factory QueryMeetingsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryMeetingsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryMeetingsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'ids', $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(
        2, _omitFieldNames ? '' : 'creatorIds', $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(3, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(4, _omitFieldNames ? '' : 'groupIds', $pb.PbFieldType.K6)
    ..aInt64(5, _omitFieldNames ? '' : 'creationDateStart')
    ..aInt64(6, _omitFieldNames ? '' : 'creationDateEnd')
    ..a<$core.int>(10, _omitFieldNames ? '' : 'skip', $pb.PbFieldType.O3)
    ..a<$core.int>(11, _omitFieldNames ? '' : 'limit', $pb.PbFieldType.O3)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryMeetingsRequest clone() =>
      QueryMeetingsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryMeetingsRequest copyWith(void Function(QueryMeetingsRequest) updates) =>
      super.copyWith((message) => updates(message as QueryMeetingsRequest))
          as QueryMeetingsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryMeetingsRequest create() => QueryMeetingsRequest._();
  QueryMeetingsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryMeetingsRequest> createRepeated() =>
      $pb.PbList<QueryMeetingsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryMeetingsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryMeetingsRequest>(create);
  static QueryMeetingsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get ids => $_getList(0);

  @$pb.TagNumber(2)
  $core.List<$fixnum.Int64> get creatorIds => $_getList(1);

  @$pb.TagNumber(3)
  $core.List<$fixnum.Int64> get userIds => $_getList(2);

  @$pb.TagNumber(4)
  $core.List<$fixnum.Int64> get groupIds => $_getList(3);

  @$pb.TagNumber(5)
  $fixnum.Int64 get creationDateStart => $_getI64(4);
  @$pb.TagNumber(5)
  set creationDateStart($fixnum.Int64 v) {
    $_setInt64(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasCreationDateStart() => $_has(4);
  @$pb.TagNumber(5)
  void clearCreationDateStart() => clearField(5);

  @$pb.TagNumber(6)
  $fixnum.Int64 get creationDateEnd => $_getI64(5);
  @$pb.TagNumber(6)
  set creationDateEnd($fixnum.Int64 v) {
    $_setInt64(5, v);
  }

  @$pb.TagNumber(6)
  $core.bool hasCreationDateEnd() => $_has(5);
  @$pb.TagNumber(6)
  void clearCreationDateEnd() => clearField(6);

  @$pb.TagNumber(10)
  $core.int get skip => $_getIZ(6);
  @$pb.TagNumber(10)
  set skip($core.int v) {
    $_setSignedInt32(6, v);
  }

  @$pb.TagNumber(10)
  $core.bool hasSkip() => $_has(6);
  @$pb.TagNumber(10)
  void clearSkip() => clearField(10);

  @$pb.TagNumber(11)
  $core.int get limit => $_getIZ(7);
  @$pb.TagNumber(11)
  set limit($core.int v) {
    $_setSignedInt32(7, v);
  }

  @$pb.TagNumber(11)
  $core.bool hasLimit() => $_has(7);
  @$pb.TagNumber(11)
  void clearLimit() => clearField(11);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(8);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
