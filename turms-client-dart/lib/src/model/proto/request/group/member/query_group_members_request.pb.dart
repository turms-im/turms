///
//  Generated code. Do not modify.
//  source: request/group/member/query_group_members_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryGroupMembersRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryGroupMembersRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupId')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..p<$fixnum.Int64>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'memberIds',
        $pb.PbFieldType.K6)
    ..aOB(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'withStatus')
    ..hasRequiredFields = false;

  QueryGroupMembersRequest._() : super();
  factory QueryGroupMembersRequest({
    $fixnum.Int64? groupId,
    $fixnum.Int64? lastUpdatedDate,
    $core.Iterable<$fixnum.Int64>? memberIds,
    $core.bool? withStatus,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    if (memberIds != null) {
      _result.memberIds.addAll(memberIds);
    }
    if (withStatus != null) {
      _result.withStatus = withStatus;
    }
    return _result;
  }
  factory QueryGroupMembersRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryGroupMembersRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as QueryGroupMembersRequest; // ignore: deprecated_member_use
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
}
