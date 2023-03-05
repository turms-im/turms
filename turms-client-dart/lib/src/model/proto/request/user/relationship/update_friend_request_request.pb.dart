///
//  Generated code. Do not modify.
//  source: request/user/relationship/update_friend_request_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../constant/response_action.pbenum.dart' as $0;

class UpdateFriendRequestRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UpdateFriendRequestRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'requestId')
    ..e<$0.ResponseAction>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'responseAction',
        $pb.PbFieldType.OE,
        defaultOrMaker: $0.ResponseAction.ACCEPT,
        valueOf: $0.ResponseAction.valueOf,
        enumValues: $0.ResponseAction.values)
    ..aOS(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'reason')
    ..hasRequiredFields = false;

  UpdateFriendRequestRequest._() : super();
  factory UpdateFriendRequestRequest({
    $fixnum.Int64? requestId,
    $0.ResponseAction? responseAction,
    $core.String? reason,
  }) {
    final _result = create();
    if (requestId != null) {
      _result.requestId = requestId;
    }
    if (responseAction != null) {
      _result.responseAction = responseAction;
    }
    if (reason != null) {
      _result.reason = reason;
    }
    return _result;
  }
  factory UpdateFriendRequestRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateFriendRequestRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as UpdateFriendRequestRequest; // ignore: deprecated_member_use
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
