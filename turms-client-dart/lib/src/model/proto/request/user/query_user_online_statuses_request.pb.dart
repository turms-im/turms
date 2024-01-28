//
//  Generated code. Do not modify.
//  source: request/user/query_user_online_statuses_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryUserOnlineStatusesRequest extends $pb.GeneratedMessage {
  factory QueryUserOnlineStatusesRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
  }) {
    final $result = create();
    if (userIds != null) {
      $result.userIds.addAll(userIds);
    }
    return $result;
  }
  QueryUserOnlineStatusesRequest._() : super();
  factory QueryUserOnlineStatusesRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryUserOnlineStatusesRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryUserOnlineStatusesRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryUserOnlineStatusesRequest clone() =>
      QueryUserOnlineStatusesRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryUserOnlineStatusesRequest copyWith(
          void Function(QueryUserOnlineStatusesRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryUserOnlineStatusesRequest))
          as QueryUserOnlineStatusesRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryUserOnlineStatusesRequest create() =>
      QueryUserOnlineStatusesRequest._();
  QueryUserOnlineStatusesRequest createEmptyInstance() => create();
  static $pb.PbList<QueryUserOnlineStatusesRequest> createRepeated() =>
      $pb.PbList<QueryUserOnlineStatusesRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryUserOnlineStatusesRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryUserOnlineStatusesRequest>(create);
  static QueryUserOnlineStatusesRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get userIds => $_getList(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
