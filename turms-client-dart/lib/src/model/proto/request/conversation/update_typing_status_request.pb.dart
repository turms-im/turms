///
//  Generated code. Do not modify.
//  source: request/conversation/update_typing_status_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateTypingStatusRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UpdateTypingStatusRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOB(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'isGroupMessage')
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'toId')
    ..hasRequiredFields = false;

  UpdateTypingStatusRequest._() : super();
  factory UpdateTypingStatusRequest({
    $core.bool? isGroupMessage,
    $fixnum.Int64? toId,
  }) {
    final _result = create();
    if (isGroupMessage != null) {
      _result.isGroupMessage = isGroupMessage;
    }
    if (toId != null) {
      _result.toId = toId;
    }
    return _result;
  }
  factory UpdateTypingStatusRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateTypingStatusRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateTypingStatusRequest clone() =>
      UpdateTypingStatusRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateTypingStatusRequest copyWith(
          void Function(UpdateTypingStatusRequest) updates) =>
      super.copyWith((message) => updates(message as UpdateTypingStatusRequest))
          as UpdateTypingStatusRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UpdateTypingStatusRequest create() => UpdateTypingStatusRequest._();
  UpdateTypingStatusRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateTypingStatusRequest> createRepeated() =>
      $pb.PbList<UpdateTypingStatusRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateTypingStatusRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateTypingStatusRequest>(create);
  static UpdateTypingStatusRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.bool get isGroupMessage => $_getBF(0);
  @$pb.TagNumber(1)
  set isGroupMessage($core.bool v) {
    $_setBool(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasIsGroupMessage() => $_has(0);
  @$pb.TagNumber(1)
  void clearIsGroupMessage() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get toId => $_getI64(1);
  @$pb.TagNumber(2)
  set toId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasToId() => $_has(1);
  @$pb.TagNumber(2)
  void clearToId() => clearField(2);
}
