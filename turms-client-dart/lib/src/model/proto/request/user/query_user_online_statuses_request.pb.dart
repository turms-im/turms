///
//  Generated code. Do not modify.
//  source: request/user/query_user_online_statuses_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryUserOnlineStatusesRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryUserOnlineStatusesRequest',
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
    ..hasRequiredFields = false;

  QueryUserOnlineStatusesRequest._() : super();
  factory QueryUserOnlineStatusesRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
  }) {
    final _result = create();
    if (userIds != null) {
      _result.userIds.addAll(userIds);
    }
    return _result;
  }
  factory QueryUserOnlineStatusesRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryUserOnlineStatusesRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as QueryUserOnlineStatusesRequest; // ignore: deprecated_member_use
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
