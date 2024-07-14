//
//  Generated code. Do not modify.
//  source: request/user/relationship/update_friend_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../constant/response_action.pbenum.dart' as $1;
import '../../../model/common/value.pb.dart' as $0;

class UpdateFriendRequestRequest extends $pb.GeneratedMessage {
  factory UpdateFriendRequestRequest({
    $fixnum.Int64? requestId,
    $1.ResponseAction? responseAction,
    $core.String? reason,
    $core.Iterable<$0.Value>? customAttributes,
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
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UpdateFriendRequestRequest._() : super();
  factory UpdateFriendRequestRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateFriendRequestRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateFriendRequestRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'requestId')
    ..e<$1.ResponseAction>(
        2, _omitFieldNames ? '' : 'responseAction', $pb.PbFieldType.OE,
        defaultOrMaker: $1.ResponseAction.ACCEPT,
        valueOf: $1.ResponseAction.valueOf,
        enumValues: $1.ResponseAction.values)
    ..aOS(3, _omitFieldNames ? '' : 'reason')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateFriendRequestRequest clone() =>
      UpdateFriendRequestRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateFriendRequestRequest copyWith(
          void Function(UpdateFriendRequestRequest) updates) =>
      super.copyWith(
              (message) => updates(message as UpdateFriendRequestRequest))
          as UpdateFriendRequestRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateFriendRequestRequest create() => UpdateFriendRequestRequest._();
  UpdateFriendRequestRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateFriendRequestRequest> createRepeated() =>
      $pb.PbList<UpdateFriendRequestRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateFriendRequestRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateFriendRequestRequest>(create);
  static UpdateFriendRequestRequest? _defaultInstance;

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
  $1.ResponseAction get responseAction => $_getN(1);
  @$pb.TagNumber(2)
  set responseAction($1.ResponseAction v) {
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

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
