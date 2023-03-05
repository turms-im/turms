///
//  Generated code. Do not modify.
//  source: request/user/relationship/delete_relationship_group_member_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class DeleteRelationshipGroupMemberRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'DeleteRelationshipGroupMemberRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'userId')
    ..a<$core.int>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupIndex',
        $pb.PbFieldType.O3)
    ..a<$core.int>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'targetGroupIndex',
        $pb.PbFieldType.O3)
    ..hasRequiredFields = false;

  DeleteRelationshipGroupMemberRequest._() : super();
  factory DeleteRelationshipGroupMemberRequest({
    $fixnum.Int64? userId,
    $core.int? groupIndex,
    $core.int? targetGroupIndex,
  }) {
    final _result = create();
    if (userId != null) {
      _result.userId = userId;
    }
    if (groupIndex != null) {
      _result.groupIndex = groupIndex;
    }
    if (targetGroupIndex != null) {
      _result.targetGroupIndex = targetGroupIndex;
    }
    return _result;
  }
  factory DeleteRelationshipGroupMemberRequest.fromBuffer(
          $core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteRelationshipGroupMemberRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteRelationshipGroupMemberRequest clone() =>
      DeleteRelationshipGroupMemberRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteRelationshipGroupMemberRequest copyWith(
          void Function(DeleteRelationshipGroupMemberRequest) updates) =>
      super.copyWith((message) =>
              updates(message as DeleteRelationshipGroupMemberRequest))
          as DeleteRelationshipGroupMemberRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static DeleteRelationshipGroupMemberRequest create() =>
      DeleteRelationshipGroupMemberRequest._();
  DeleteRelationshipGroupMemberRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteRelationshipGroupMemberRequest> createRepeated() =>
      $pb.PbList<DeleteRelationshipGroupMemberRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteRelationshipGroupMemberRequest getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<
          DeleteRelationshipGroupMemberRequest>(create);
  static DeleteRelationshipGroupMemberRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get userId => $_getI64(0);
  @$pb.TagNumber(1)
  set userId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasUserId() => $_has(0);
  @$pb.TagNumber(1)
  void clearUserId() => clearField(1);

  @$pb.TagNumber(2)
  $core.int get groupIndex => $_getIZ(1);
  @$pb.TagNumber(2)
  set groupIndex($core.int v) {
    $_setSignedInt32(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasGroupIndex() => $_has(1);
  @$pb.TagNumber(2)
  void clearGroupIndex() => clearField(2);

  @$pb.TagNumber(3)
  $core.int get targetGroupIndex => $_getIZ(2);
  @$pb.TagNumber(3)
  set targetGroupIndex($core.int v) {
    $_setSignedInt32(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasTargetGroupIndex() => $_has(2);
  @$pb.TagNumber(3)
  void clearTargetGroupIndex() => clearField(3);
}
