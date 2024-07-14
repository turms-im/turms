//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_related_user_ids_request.proto
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

class QueryRelatedUserIdsRequest extends $pb.GeneratedMessage {
  factory QueryRelatedUserIdsRequest({
    $core.bool? blocked,
    $core.Iterable<$core.int>? groupIndexes,
    $fixnum.Int64? lastUpdatedDate,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
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
  QueryRelatedUserIdsRequest._() : super();
  factory QueryRelatedUserIdsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryRelatedUserIdsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryRelatedUserIdsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'blocked')
    ..p<$core.int>(2, _omitFieldNames ? '' : 'groupIndexes', $pb.PbFieldType.K3)
    ..aInt64(3, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryRelatedUserIdsRequest clone() =>
      QueryRelatedUserIdsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryRelatedUserIdsRequest copyWith(
          void Function(QueryRelatedUserIdsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryRelatedUserIdsRequest))
          as QueryRelatedUserIdsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryRelatedUserIdsRequest create() => QueryRelatedUserIdsRequest._();
  QueryRelatedUserIdsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryRelatedUserIdsRequest> createRepeated() =>
      $pb.PbList<QueryRelatedUserIdsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryRelatedUserIdsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryRelatedUserIdsRequest>(create);
  static QueryRelatedUserIdsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get blocked => $_getBF(0);
  @$pb.TagNumber(1)
  set blocked($core.bool v) {
    $_setBool(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasBlocked() => $_has(0);
  @$pb.TagNumber(1)
  void clearBlocked() => clearField(1);

  @$pb.TagNumber(2)
  $core.List<$core.int> get groupIndexes => $_getList(1);

  @$pb.TagNumber(3)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(2);
  @$pb.TagNumber(3)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasLastUpdatedDate() => $_has(2);
  @$pb.TagNumber(3)
  void clearLastUpdatedDate() => clearField(3);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
