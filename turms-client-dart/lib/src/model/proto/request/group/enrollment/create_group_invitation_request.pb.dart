//
//  Generated code. Do not modify.
//  source: request/group/enrollment/create_group_invitation_request.proto
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

class CreateGroupInvitationRequest extends $pb.GeneratedMessage {
  factory CreateGroupInvitationRequest({
    $fixnum.Int64? groupId,
    $fixnum.Int64? inviteeId,
    $core.String? content,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (inviteeId != null) {
      $result.inviteeId = inviteeId;
    }
    if (content != null) {
      $result.content = content;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  CreateGroupInvitationRequest._() : super();
  factory CreateGroupInvitationRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateGroupInvitationRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CreateGroupInvitationRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..aInt64(2, _omitFieldNames ? '' : 'inviteeId')
    ..aOS(3, _omitFieldNames ? '' : 'content')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateGroupInvitationRequest clone() =>
      CreateGroupInvitationRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateGroupInvitationRequest copyWith(
          void Function(CreateGroupInvitationRequest) updates) =>
      super.copyWith(
              (message) => updates(message as CreateGroupInvitationRequest))
          as CreateGroupInvitationRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CreateGroupInvitationRequest create() =>
      CreateGroupInvitationRequest._();
  CreateGroupInvitationRequest createEmptyInstance() => create();
  static $pb.PbList<CreateGroupInvitationRequest> createRepeated() =>
      $pb.PbList<CreateGroupInvitationRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateGroupInvitationRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateGroupInvitationRequest>(create);
  static CreateGroupInvitationRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get inviteeId => $_getI64(1);
  @$pb.TagNumber(2)
  set inviteeId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasInviteeId() => $_has(1);
  @$pb.TagNumber(2)
  void clearInviteeId() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get content => $_getSZ(2);
  @$pb.TagNumber(3)
  set content($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasContent() => $_has(2);
  @$pb.TagNumber(3)
  void clearContent() => clearField(3);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
