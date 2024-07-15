//
//  Generated code. Do not modify.
//  source: request/conversation/query_conversation_settings_request.proto
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

class QueryConversationSettingsRequest extends $pb.GeneratedMessage {
  factory QueryConversationSettingsRequest({
    $core.Iterable<$fixnum.Int64>? userIds,
    $core.Iterable<$fixnum.Int64>? groupIds,
    $core.Iterable<$core.String>? names,
    $fixnum.Int64? lastUpdatedDateStart,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (userIds != null) {
      $result.userIds.addAll(userIds);
    }
    if (groupIds != null) {
      $result.groupIds.addAll(groupIds);
    }
    if (names != null) {
      $result.names.addAll(names);
    }
    if (lastUpdatedDateStart != null) {
      $result.lastUpdatedDateStart = lastUpdatedDateStart;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  QueryConversationSettingsRequest._() : super();
  factory QueryConversationSettingsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryConversationSettingsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryConversationSettingsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'userIds', $pb.PbFieldType.K6)
    ..p<$fixnum.Int64>(2, _omitFieldNames ? '' : 'groupIds', $pb.PbFieldType.K6)
    ..pPS(3, _omitFieldNames ? '' : 'names')
    ..aInt64(4, _omitFieldNames ? '' : 'lastUpdatedDateStart')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryConversationSettingsRequest clone() =>
      QueryConversationSettingsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryConversationSettingsRequest copyWith(
          void Function(QueryConversationSettingsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryConversationSettingsRequest))
          as QueryConversationSettingsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryConversationSettingsRequest create() =>
      QueryConversationSettingsRequest._();
  QueryConversationSettingsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryConversationSettingsRequest> createRepeated() =>
      $pb.PbList<QueryConversationSettingsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryConversationSettingsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryConversationSettingsRequest>(
          create);
  static QueryConversationSettingsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get userIds => $_getList(0);

  @$pb.TagNumber(2)
  $core.List<$fixnum.Int64> get groupIds => $_getList(1);

  @$pb.TagNumber(3)
  $core.List<$core.String> get names => $_getList(2);

  @$pb.TagNumber(4)
  $fixnum.Int64 get lastUpdatedDateStart => $_getI64(3);
  @$pb.TagNumber(4)
  set lastUpdatedDateStart($fixnum.Int64 v) {
    $_setInt64(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasLastUpdatedDateStart() => $_has(3);
  @$pb.TagNumber(4)
  void clearLastUpdatedDateStart() => clearField(4);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(4);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
