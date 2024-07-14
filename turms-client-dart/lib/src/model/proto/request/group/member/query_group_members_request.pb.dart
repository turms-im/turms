//
//  Generated code. Do not modify.
//  source: request/group/member/query_group_members_request.proto
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

class QueryGroupMembersRequest extends $pb.GeneratedMessage {
  factory QueryGroupMembersRequest({
    $fixnum.Int64? groupId,
    $fixnum.Int64? lastUpdatedDate,
    $core.Iterable<$fixnum.Int64>? memberIds,
    $core.bool? withStatus,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    if (memberIds != null) {
      $result.memberIds.addAll(memberIds);
    }
    if (withStatus != null) {
      $result.withStatus = withStatus;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  QueryGroupMembersRequest._() : super();
  factory QueryGroupMembersRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryGroupMembersRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryGroupMembersRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..p<$fixnum.Int64>(
        3, _omitFieldNames ? '' : 'memberIds', $pb.PbFieldType.K6)
    ..aOB(4, _omitFieldNames ? '' : 'withStatus')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryGroupMembersRequest clone() =>
      QueryGroupMembersRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryGroupMembersRequest copyWith(
          void Function(QueryGroupMembersRequest) updates) =>
      super.copyWith((message) => updates(message as QueryGroupMembersRequest))
          as QueryGroupMembersRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryGroupMembersRequest create() => QueryGroupMembersRequest._();
  QueryGroupMembersRequest createEmptyInstance() => create();
  static $pb.PbList<QueryGroupMembersRequest> createRepeated() =>
      $pb.PbList<QueryGroupMembersRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryGroupMembersRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryGroupMembersRequest>(create);
  static QueryGroupMembersRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

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
  $core.List<$fixnum.Int64> get memberIds => $_getList(2);

  @$pb.TagNumber(4)
  $core.bool get withStatus => $_getBF(3);
  @$pb.TagNumber(4)
  set withStatus($core.bool v) {
    $_setBool(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasWithStatus() => $_has(3);
  @$pb.TagNumber(4)
  void clearWithStatus() => clearField(4);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(4);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
