///
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationships_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryRelationshipsRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryRelationshipsRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userIds',
        $pb.PbFieldType.K6)
    ..aOB(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'blocked')
    ..p<$core.int>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIndexes',
        $pb.PbFieldType.K3)
    ..aInt64(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  QueryRelationshipsRequest._() : super();
  factory QueryRelationshipsRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.bool? blocked,
    $core.Iterable<$core.int>? groupIndexes,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (userIds != null) {
      _result.userIds.addAll(userIds);
    }
    if (blocked != null) {
      _result.blocked = blocked;
    }
    if (groupIndexes != null) {
      _result.groupIndexes.addAll(groupIndexes);
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory QueryRelationshipsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryRelationshipsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as QueryRelationshipsRequest; // ignore: deprecated_member_use
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
}
