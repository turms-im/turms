//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationship_groups_request.proto
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

class QueryRelationshipGroupsRequest extends $pb.GeneratedMessage {
  factory QueryRelationshipGroupsRequest({
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
  QueryRelationshipGroupsRequest._() : super();
  factory QueryRelationshipGroupsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryRelationshipGroupsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryRelationshipGroupsRequest',
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
  QueryRelationshipGroupsRequest clone() =>
      QueryRelationshipGroupsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryRelationshipGroupsRequest copyWith(
          void Function(QueryRelationshipGroupsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryRelationshipGroupsRequest))
          as QueryRelationshipGroupsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryRelationshipGroupsRequest create() =>
      QueryRelationshipGroupsRequest._();
  QueryRelationshipGroupsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryRelationshipGroupsRequest> createRepeated() =>
      $pb.PbList<QueryRelationshipGroupsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryRelationshipGroupsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryRelationshipGroupsRequest>(create);
  static QueryRelationshipGroupsRequest? _defaultInstance;

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
