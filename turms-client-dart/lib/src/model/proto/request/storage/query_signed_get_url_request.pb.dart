///
//  Generated code. Do not modify.
//  source: request/storage/query_signed_get_url_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/content_type.pbenum.dart' as $0;

class QuerySignedGetUrlRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'QuerySignedGetUrlRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..e<$0.ContentType>(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'contentType', $pb.PbFieldType.OE, defaultOrMaker: $0.ContentType.PROFILE, valueOf: $0.ContentType.valueOf, enumValues: $0.ContentType.values)
    ..aOS(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'keyStr')
    ..aInt64(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'keyNum')
    ..hasRequiredFields = false
  ;

  QuerySignedGetUrlRequest._() : super();
  factory QuerySignedGetUrlRequest({
    $0.ContentType? contentType,
    $core.String? keyStr,
    $fixnum.Int64? keyNum,
  }) {
    final _result = create();
    if (contentType != null) {
      _result.contentType = contentType;
    }
    if (keyStr != null) {
      _result.keyStr = keyStr;
    }
    if (keyNum != null) {
      _result.keyNum = keyNum;
    }
    return _result;
  }
  factory QuerySignedGetUrlRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory QuerySignedGetUrlRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  QuerySignedGetUrlRequest clone() => QuerySignedGetUrlRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  QuerySignedGetUrlRequest copyWith(void Function(QuerySignedGetUrlRequest) updates) => super.copyWith((message) => updates(message as QuerySignedGetUrlRequest)) as QuerySignedGetUrlRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static QuerySignedGetUrlRequest create() => QuerySignedGetUrlRequest._();
  QuerySignedGetUrlRequest createEmptyInstance() => create();
  static $pb.PbList<QuerySignedGetUrlRequest> createRepeated() => $pb.PbList<QuerySignedGetUrlRequest>();
  @$core.pragma('dart2js:noInline')
  static QuerySignedGetUrlRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<QuerySignedGetUrlRequest>(create);
  static QuerySignedGetUrlRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $0.ContentType get contentType => $_getN(0);
  @$pb.TagNumber(1)
  set contentType($0.ContentType v) { setField(1, v); }
  @$pb.TagNumber(1)
  $core.bool hasContentType() => $_has(0);
  @$pb.TagNumber(1)
  void clearContentType() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get keyStr => $_getSZ(1);
  @$pb.TagNumber(2)
  set keyStr($core.String v) { $_setString(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasKeyStr() => $_has(1);
  @$pb.TagNumber(2)
  void clearKeyStr() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get keyNum => $_getI64(2);
  @$pb.TagNumber(3)
  set keyNum($fixnum.Int64 v) { $_setInt64(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasKeyNum() => $_has(2);
  @$pb.TagNumber(3)
  void clearKeyNum() => clearField(3);
}

