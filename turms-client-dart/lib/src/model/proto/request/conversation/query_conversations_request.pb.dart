//
//  Generated code. Do not modify.
//  source: request/conversation/query_conversations_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryConversationsRequest extends $pb.GeneratedMessage {
  factory QueryConversationsRequest({
    $core.Iterable<$fixnum.Int64>? targetIds,
    $core.Iterable<$fixnum.Int64>? groupIds,
  }) {
    final $result = create();
    if (targetIds != null) {
      $result.targetIds.addAll(targetIds);
    }
    if (groupIds != null) {
      $result.groupIds.addAll(groupIds);
    }
    return $result;
  }
  QueryConversationsRequest._() : super();
  factory QueryConversationsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryConversationsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryConversationsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(
        1, _omitFieldNames ? '' : 'targetIds', $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(2, _omitFieldNames ? '' : 'groupIds', $pb.PbFieldType.K6)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryConversationsRequest clone() =>
      QueryConversationsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryConversationsRequest copyWith(
          void Function(QueryConversationsRequest) updates) =>
      super.copyWith((message) => updates(message as QueryConversationsRequest))
          as QueryConversationsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryConversationsRequest create() => QueryConversationsRequest._();
  QueryConversationsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryConversationsRequest> createRepeated() =>
      $pb.PbList<QueryConversationsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryConversationsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryConversationsRequest>(create);
  static QueryConversationsRequest? _defaultInstance;

  /// Private conversations
  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get targetIds => $_getList(0);

  /// Group conversations
  @$pb.TagNumber(2)
  $core.List<$fixnum.Int64> get groupIds => $_getList(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
