//
//  Generated code. Do not modify.
//  source: request/conversation/update_typing_status_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateTypingStatusRequest extends $pb.GeneratedMessage {
  factory UpdateTypingStatusRequest({
    $core.bool? isGroupMessage,
    $fixnum.Int64? toId,
  }) {
    final $result = create();
    if (isGroupMessage != null) {
      $result.isGroupMessage = isGroupMessage;
    }
    if (toId != null) {
      $result.toId = toId;
    }
    return $result;
  }
  UpdateTypingStatusRequest._() : super();
  factory UpdateTypingStatusRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateTypingStatusRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateTypingStatusRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOB(1, _omitFieldNames ? '' : 'isGroupMessage')
    ..aInt64(2, _omitFieldNames ? '' : 'toId')
    ..hasRequiredFields = false;

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
          as UpdateTypingStatusRequest;

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

  /// Query filter
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

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
