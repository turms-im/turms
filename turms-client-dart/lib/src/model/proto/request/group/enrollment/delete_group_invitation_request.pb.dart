///
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_invitation_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class DeleteGroupInvitationRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'DeleteGroupInvitationRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'invitationId')
    ..hasRequiredFields = false;

  DeleteGroupInvitationRequest._() : super();
  factory DeleteGroupInvitationRequest({
    $fixnum.Int64? invitationId,
  }) {
    final _result = create();
    if (invitationId != null) {
      _result.invitationId = invitationId;
    }
    return _result;
  }
  factory DeleteGroupInvitationRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteGroupInvitationRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as DeleteGroupInvitationRequest; // ignore: deprecated_member_use
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
}
