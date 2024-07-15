//
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_invitation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../model/common/value.pb.dart' as $0;

class DeleteGroupInvitationRequest extends $pb.GeneratedMessage {
  factory DeleteGroupInvitationRequest({
    $fixnum.Int64? invitationId,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (invitationId != null) {
      $result.invitationId = invitationId;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  DeleteGroupInvitationRequest._() : super();
  factory DeleteGroupInvitationRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteGroupInvitationRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteGroupInvitationRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'invitationId')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteGroupInvitationRequest clone() =>
      DeleteGroupInvitationRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteGroupInvitationRequest copyWith(
          void Function(DeleteGroupInvitationRequest) updates) =>
      super.copyWith(
              (message) => updates(message as DeleteGroupInvitationRequest))
          as DeleteGroupInvitationRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteGroupInvitationRequest create() =>
      DeleteGroupInvitationRequest._();
  DeleteGroupInvitationRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteGroupInvitationRequest> createRepeated() =>
      $pb.PbList<DeleteGroupInvitationRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteGroupInvitationRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteGroupInvitationRequest>(create);
  static DeleteGroupInvitationRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get invitationId => $_getI64(0);
  @$pb.TagNumber(1)
  set invitationId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasInvitationId() => $_has(0);
  @$pb.TagNumber(1)
  void clearInvitationId() => clearField(1);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
