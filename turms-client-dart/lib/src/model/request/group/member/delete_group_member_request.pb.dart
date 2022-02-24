///
//  Generated code. Do not modify.
//  source: request/group/member/delete_group_member_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class DeleteGroupMemberRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'DeleteGroupMemberRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'groupId')
    ..aInt64(2, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'memberId')
    ..aInt64(3, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'successorId')
    ..aOB(4, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'quitAfterTransfer')
    ..hasRequiredFields = false
  ;

  DeleteGroupMemberRequest._() : super();
  factory DeleteGroupMemberRequest({
    $fixnum.Int64? groupId,
    $fixnum.Int64? memberId,
    $fixnum.Int64? successorId,
    $core.bool? quitAfterTransfer,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (memberId != null) {
      _result.memberId = memberId;
    }
    if (successorId != null) {
      _result.successorId = successorId;
    }
    if (quitAfterTransfer != null) {
      _result.quitAfterTransfer = quitAfterTransfer;
    }
    return _result;
  }
  factory DeleteGroupMemberRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory DeleteGroupMemberRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  DeleteGroupMemberRequest clone() => DeleteGroupMemberRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  DeleteGroupMemberRequest copyWith(void Function(DeleteGroupMemberRequest) updates) => super.copyWith((message) => updates(message as DeleteGroupMemberRequest)) as DeleteGroupMemberRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static DeleteGroupMemberRequest create() => DeleteGroupMemberRequest._();
  DeleteGroupMemberRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteGroupMemberRequest> createRepeated() => $pb.PbList<DeleteGroupMemberRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteGroupMemberRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<DeleteGroupMemberRequest>(create);
  static DeleteGroupMemberRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get groupId => $_getI64(0);
  @$pb.TagNumber(1)
  set groupId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasGroupId() => $_has(0);
  @$pb.TagNumber(1)
  void clearGroupId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get memberId => $_getI64(1);
  @$pb.TagNumber(2)
  set memberId($fixnum.Int64 v) { $_setInt64(1, v); }
  @$pb.TagNumber(2)
  $core.bool hasMemberId() => $_has(1);
  @$pb.TagNumber(2)
  void clearMemberId() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get successorId => $_getI64(2);
  @$pb.TagNumber(3)
  set successorId($fixnum.Int64 v) { $_setInt64(2, v); }
  @$pb.TagNumber(3)
  $core.bool hasSuccessorId() => $_has(2);
  @$pb.TagNumber(3)
  void clearSuccessorId() => clearField(3);

  @$pb.TagNumber(4)
  $core.bool get quitAfterTransfer => $_getBF(3);
  @$pb.TagNumber(4)
  set quitAfterTransfer($core.bool v) { $_setBool(3, v); }
  @$pb.TagNumber(4)
  $core.bool hasQuitAfterTransfer() => $_has(3);
  @$pb.TagNumber(4)
  void clearQuitAfterTransfer() => clearField(4);
}

