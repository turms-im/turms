//
//  Generated code. Do not modify.
//  source: request/user/relationship/delete_relationship_request.proto
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

class DeleteRelationshipRequest extends $pb.GeneratedMessage {
  factory DeleteRelationshipRequest({
    $fixnum.Int64? userId,
    $core.int? groupIndex,
    $core.int? targetGroupIndex,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (userId != null) {
      $result.userId = userId;
    }
    if (groupIndex != null) {
      $result.groupIndex = groupIndex;
    }
    if (targetGroupIndex != null) {
      $result.targetGroupIndex = targetGroupIndex;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  DeleteRelationshipRequest._() : super();
  factory DeleteRelationshipRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteRelationshipRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteRelationshipRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'userId')
    ..a<$core.int>(2, _omitFieldNames ? '' : 'groupIndex', $pb.PbFieldType.O3)
    ..a<$core.int>(
        3, _omitFieldNames ? '' : 'targetGroupIndex', $pb.PbFieldType.O3)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteRelationshipRequest clone() =>
      DeleteRelationshipRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteRelationshipRequest copyWith(
          void Function(DeleteRelationshipRequest) updates) =>
      super.copyWith((message) => updates(message as DeleteRelationshipRequest))
          as DeleteRelationshipRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteRelationshipRequest create() => DeleteRelationshipRequest._();
  DeleteRelationshipRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteRelationshipRequest> createRepeated() =>
      $pb.PbList<DeleteRelationshipRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteRelationshipRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteRelationshipRequest>(create);
  static DeleteRelationshipRequest? _defaultInstance;

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

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(3);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
