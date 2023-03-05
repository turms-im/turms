///
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationship_groups_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryRelationshipGroupsRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryRelationshipGroupsRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  QueryRelationshipGroupsRequest._() : super();
  factory QueryRelationshipGroupsRequest({
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory QueryRelationshipGroupsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryRelationshipGroupsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as QueryRelationshipGroupsRequest; // ignore: deprecated_member_use
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
}
