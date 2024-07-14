//
//  Generated code. Do not modify.
//  source: request/conference/update_meeting_invitation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/response_action.pbenum.dart' as $1;
import '../../model/common/value.pb.dart' as $0;

class UpdateMeetingInvitationRequest extends $pb.GeneratedMessage {
  factory UpdateMeetingInvitationRequest({
    $fixnum.Int64? meetingId,
    $core.String? password,
    $1.ResponseAction? responseAction,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (meetingId != null) {
      $result.meetingId = meetingId;
    }
    if (password != null) {
      $result.password = password;
    }
    if (responseAction != null) {
      $result.responseAction = responseAction;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  UpdateMeetingInvitationRequest._() : super();
  factory UpdateMeetingInvitationRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateMeetingInvitationRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateMeetingInvitationRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'meetingId')
    ..aOS(2, _omitFieldNames ? '' : 'password')
    ..e<$1.ResponseAction>(
        5, _omitFieldNames ? '' : 'responseAction', $pb.PbFieldType.OE,
        defaultOrMaker: $1.ResponseAction.ACCEPT,
        valueOf: $1.ResponseAction.valueOf,
        enumValues: $1.ResponseAction.values)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateMeetingInvitationRequest clone() =>
      UpdateMeetingInvitationRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateMeetingInvitationRequest copyWith(
          void Function(UpdateMeetingInvitationRequest) updates) =>
      super.copyWith(
              (message) => updates(message as UpdateMeetingInvitationRequest))
          as UpdateMeetingInvitationRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static UpdateMeetingInvitationRequest create() =>
      UpdateMeetingInvitationRequest._();
  UpdateMeetingInvitationRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateMeetingInvitationRequest> createRepeated() =>
      $pb.PbList<UpdateMeetingInvitationRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateMeetingInvitationRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateMeetingInvitationRequest>(create);
  static UpdateMeetingInvitationRequest? _defaultInstance;

  /// Query filter
  @$pb.TagNumber(1)
  $fixnum.Int64 get meetingId => $_getI64(0);
  @$pb.TagNumber(1)
  set meetingId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasMeetingId() => $_has(0);
  @$pb.TagNumber(1)
  void clearMeetingId() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get password => $_getSZ(1);
  @$pb.TagNumber(2)
  set password($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasPassword() => $_has(1);
  @$pb.TagNumber(2)
  void clearPassword() => clearField(2);

  /// Update
  @$pb.TagNumber(5)
  $1.ResponseAction get responseAction => $_getN(2);
  @$pb.TagNumber(5)
  set responseAction($1.ResponseAction v) {
    setField(5, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasResponseAction() => $_has(2);
  @$pb.TagNumber(5)
  void clearResponseAction() => clearField(5);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
