//
//  Generated code. Do not modify.
//  source: request/group/query_joined_group_ids_request.proto
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

class QueryJoinedGroupIdsRequest extends $pb.GeneratedMessage {
  factory QueryJoinedGroupIdsRequest({
    $fixnum.Int64? lastUpdatedDate,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  QueryJoinedGroupIdsRequest._() : super();
  factory QueryJoinedGroupIdsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryJoinedGroupIdsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryJoinedGroupIdsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryJoinedGroupIdsRequest clone() =>
      QueryJoinedGroupIdsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryJoinedGroupIdsRequest copyWith(
          void Function(QueryJoinedGroupIdsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryJoinedGroupIdsRequest))
          as QueryJoinedGroupIdsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryJoinedGroupIdsRequest create() => QueryJoinedGroupIdsRequest._();
  QueryJoinedGroupIdsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryJoinedGroupIdsRequest> createRepeated() =>
      $pb.PbList<QueryJoinedGroupIdsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryJoinedGroupIdsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryJoinedGroupIdsRequest>(create);
  static QueryJoinedGroupIdsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(0);
  @$pb.TagNumber(1)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasLastUpdatedDate() => $_has(0);
  @$pb.TagNumber(1)
  void clearLastUpdatedDate() => clearField(1);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
