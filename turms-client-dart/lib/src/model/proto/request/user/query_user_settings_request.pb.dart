//
//  Generated code. Do not modify.
//  source: request/user/query_user_settings_request.proto
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

class QueryUserSettingsRequest extends $pb.GeneratedMessage {
  factory QueryUserSettingsRequest({
    $core.Iterable<$core.String>? names,
    $fixnum.Int64? lastUpdatedDateStart,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
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
  QueryUserSettingsRequest._() : super();
  factory QueryUserSettingsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryUserSettingsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryUserSettingsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pPS(1, _omitFieldNames ? '' : 'names')
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDateStart')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryUserSettingsRequest clone() =>
      QueryUserSettingsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryUserSettingsRequest copyWith(
          void Function(QueryUserSettingsRequest) updates) =>
      super.copyWith((message) => updates(message as QueryUserSettingsRequest))
          as QueryUserSettingsRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static QueryUserSettingsRequest create() => QueryUserSettingsRequest._();
  QueryUserSettingsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryUserSettingsRequest> createRepeated() =>
      $pb.PbList<QueryUserSettingsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryUserSettingsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryUserSettingsRequest>(create);
  static QueryUserSettingsRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$core.String> get names => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDateStart => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDateStart($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDateStart() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDateStart() => clearField(2);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
