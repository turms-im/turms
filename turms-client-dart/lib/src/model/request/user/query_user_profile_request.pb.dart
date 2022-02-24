///
//  Generated code. Do not modify.
//  source: request/user/query_user_profile_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryUserProfileRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'QueryUserProfileRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'userId')
    ..aInt64(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false
  ;

  QueryUserProfileRequest._() : super();
  factory QueryUserProfileRequest({
    $fixnum.Int64? userId,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (userId != null) {
      _result.userId = userId;
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory QueryUserProfileRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory QueryUserProfileRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  QueryUserProfileRequest clone() => QueryUserProfileRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  QueryUserProfileRequest copyWith(void Function(QueryUserProfileRequest) updates) => super.copyWith((message) => updates(message as QueryUserProfileRequest)) as QueryUserProfileRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static QueryUserProfileRequest create() => QueryUserProfileRequest._();
  QueryUserProfileRequest createEmptyInstance() => create();
  static $pb.PbList<QueryUserProfileRequest> createRepeated() => $pb.PbList<QueryUserProfileRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryUserProfileRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<QueryUserProfileRequest>(create);
  static QueryUserProfileRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get userId => $_getI64(0);
  @$pb.TagNumber(1)
  set userId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasUserId() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) { $_setInt64(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}

