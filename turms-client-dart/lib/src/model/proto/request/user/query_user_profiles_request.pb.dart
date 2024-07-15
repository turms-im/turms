//
//  Generated code. Do not modify.
//  source: request/user/query_user_profiles_request.proto
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

class QueryUserProfilesRequest extends $pb.GeneratedMessage {
  factory QueryUserProfilesRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
    $fixnum.Int64? lastUpdatedDate,
    $core.String? name,
    $core.int? skip,
    $core.int? limit,
    $core.Iterable<$core.int>? fieldsToHighlight,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (userIds != null) {
      $result.userIds.addAll(userIds);
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
  QueryUserProfilesRequest._() : super();
  factory QueryUserProfilesRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryUserProfilesRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryUserProfilesRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
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
  QueryUserProfilesRequest clone() =>
      QueryUserProfilesRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryUserProfilesRequest copyWith(
          void Function(QueryUserProfilesRequest) updates) =>
      super.copyWith((message) => updates(message as QueryUserProfilesRequest))
          as QueryUserProfilesRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryUserProfilesRequest create() => QueryUserProfilesRequest._();
  QueryUserProfilesRequest createEmptyInstance() => create();
  static $pb.PbList<QueryUserProfilesRequest> createRepeated() =>
      $pb.PbList<QueryUserProfilesRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryUserProfilesRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryUserProfilesRequest>(create);
  static QueryUserProfilesRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get userIds => $_getList(0);

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
