//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationships_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../model/common/value.pb.dart' as $0;

class QueryRelationshipsRequest extends $pb.GeneratedMessage {
  factory QueryRelationshipsRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.bool? blocked,
    $core.Iterable<$core.int>? groupIndexes,
    $fixnum.Int64? lastUpdatedDate,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (userIds != null) {
      $result.userIds.addAll(userIds);
    }
    if (blocked != null) {
      $result.blocked = blocked;
    }
    if (groupIndexes != null) {
      $result.groupIndexes.addAll(groupIndexes);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  QueryRelationshipsRequest._() : super();
  factory QueryRelationshipsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryRelationshipsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryRelationshipsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
    ..aOB(2, _omitFieldNames ? '' : 'blocked')
    ..p<$core.int>(3, _omitFieldNames ? '' : 'groupIndexes', $pb.PbFieldType.K3)
    ..aInt64(4, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryRelationshipsRequest clone() =>
      QueryRelationshipsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryRelationshipsRequest copyWith(
          void Function(QueryRelationshipsRequest) updates) =>
      super.copyWith((message) => updates(message as QueryRelationshipsRequest))
          as QueryRelationshipsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryRelationshipsRequest create() => QueryRelationshipsRequest._();
  QueryRelationshipsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryRelationshipsRequest> createRepeated() =>
      $pb.PbList<QueryRelationshipsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryRelationshipsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryRelationshipsRequest>(create);
  static QueryRelationshipsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get userIds => $_getList(0);

  @$pb.TagNumber(2)
  $core.bool get blocked => $_getBF(1);
  @$pb.TagNumber(2)
  set blocked($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasBlocked() => $_has(1);
  @$pb.TagNumber(2)
  void clearBlocked() => clearField(2);

  @$pb.TagNumber(3)
  $core.List<$core.int> get groupIndexes => $_getList(2);

  @$pb.TagNumber(4)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(3);
  @$pb.TagNumber(4)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasLastUpdatedDate() => $_has(3);
  @$pb.TagNumber(4)
  void clearLastUpdatedDate() => clearField(4);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(4);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
