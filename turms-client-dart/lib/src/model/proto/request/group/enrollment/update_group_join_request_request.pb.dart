//
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_join_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../constant/response_action.pbenum.dart' as $0;

class UpdateGroupJoinRequestRequest extends $pb.GeneratedMessage {
  factory UpdateGroupJoinRequestRequest({
    $fixnum.Int64? requestId,
    $0.ResponseAction? responseAction,
    $core.String? reason,
  }) {
    final $result = create();
    if (requestId != null) {
      $result.requestId = requestId;
    }
    if (responseAction != null) {
      $result.responseAction = responseAction;
    }
    if (reason != null) {
      $result.reason = reason;
    }
    return $result;
  }
  UpdateGroupJoinRequestRequest._() : super();
  factory UpdateGroupJoinRequestRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateGroupJoinRequestRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateGroupJoinRequestRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'requestId')
    ..e<$0.ResponseAction>(
        2, _omitFieldNames ? '' : 'responseAction', $pb.PbFieldType.OE,
        defaultOrMaker: $0.ResponseAction.ACCEPT,
        valueOf: $0.ResponseAction.valueOf,
        enumValues: $0.ResponseAction.values)
    ..aOS(3, _omitFieldNames ? '' : 'reason')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateGroupJoinRequestRequest clone() =>
      UpdateGroupJoinRequestRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateGroupJoinRequestRequest copyWith(
          void Function(UpdateGroupJoinRequestRequest) updates) =>
      super.copyWith(
              (message) => updates(message as UpdateGroupJoinRequestRequest))
          as UpdateGroupJoinRequestRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateGroupJoinRequestRequest create() =>
      UpdateGroupJoinRequestRequest._();
  UpdateGroupJoinRequestRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateGroupJoinRequestRequest> createRepeated() =>
      $pb.PbList<UpdateGroupJoinRequestRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateGroupJoinRequestRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateGroupJoinRequestRequest>(create);
  static UpdateGroupJoinRequestRequest? _defaultInstance;

  /// Query filter
  @$pb.TagNumber(1)
  $fixnum.Int64 get requestId => $_getI64(0);
  @$pb.TagNumber(1)
  set requestId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasRequestId() => $_has(0);
  @$pb.TagNumber(1)
  void clearRequestId() => clearField(1);

  /// Update
  @$pb.TagNumber(2)
  $0.ResponseAction get responseAction => $_getN(1);
  @$pb.TagNumber(2)
  set responseAction($0.ResponseAction v) {
    setField(2, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasResponseAction() => $_has(1);
  @$pb.TagNumber(2)
  void clearResponseAction() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get reason => $_getSZ(2);
  @$pb.TagNumber(3)
  set reason($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasReason() => $_has(2);
  @$pb.TagNumber(3)
  void clearReason() => clearField(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
