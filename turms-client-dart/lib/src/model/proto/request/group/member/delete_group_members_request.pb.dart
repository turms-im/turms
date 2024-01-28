//
//  Generated code. Do not modify.
//  source: request/group/member/delete_group_members_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class DeleteGroupMembersRequest extends $pb.GeneratedMessage {
  factory DeleteGroupMembersRequest({
    $fixnum.Int64? groupId,
    $core.Iterable<$fixnum.Int64>? memberIds,
    $fixnum.Int64? successorId,
    $core.bool? quitAfterTransfer,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (memberIds != null) {
      $result.memberIds.addAll(memberIds);
    }
    if (successorId != null) {
      $result.successorId = successorId;
    }
    if (quitAfterTransfer != null) {
      $result.quitAfterTransfer = quitAfterTransfer;
    }
    return $result;
  }
  DeleteGroupMembersRequest._() : super();
  factory DeleteGroupMembersRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteGroupMembersRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteGroupMembersRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..p<$fixnum.Int64>(
        2, _omitFieldNames ? '' : 'memberIds', $pb.PbFieldType.K6)
    ..aInt64(3, _omitFieldNames ? '' : 'successorId')
    ..aOB(4, _omitFieldNames ? '' : 'quitAfterTransfer')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteGroupMembersRequest clone() =>
      DeleteGroupMembersRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteGroupMembersRequest copyWith(
          void Function(DeleteGroupMembersRequest) updates) =>
      super.copyWith((message) => updates(message as DeleteGroupMembersRequest))
          as DeleteGroupMembersRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteGroupMembersRequest create() => DeleteGroupMembersRequest._();
  DeleteGroupMembersRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteGroupMembersRequest> createRepeated() =>
      $pb.PbList<DeleteGroupMembersRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteGroupMembersRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteGroupMembersRequest>(create);
  static DeleteGroupMembersRequest? _defaultInstance;

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
  $core.List<$fixnum.Int64> get memberIds => $_getList(1);

  @$pb.TagNumber(3)
  $fixnum.Int64 get successorId => $_getI64(2);
  @$pb.TagNumber(3)
  set successorId($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasSuccessorId() => $_has(2);
  @$pb.TagNumber(3)
  void clearSuccessorId() => clearField(3);

  @$pb.TagNumber(4)
  $core.bool get quitAfterTransfer => $_getBF(3);
  @$pb.TagNumber(4)
  set quitAfterTransfer($core.bool v) {
    $_setBool(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasQuitAfterTransfer() => $_has(3);
  @$pb.TagNumber(4)
  void clearQuitAfterTransfer() => clearField(4);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
