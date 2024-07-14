//
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_join_request_request.proto
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

class DeleteGroupJoinRequestRequest extends $pb.GeneratedMessage {
  factory DeleteGroupJoinRequestRequest({
    $fixnum.Int64? requestId,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (requestId != null) {
      $result.requestId = requestId;
    }
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  DeleteGroupJoinRequestRequest._() : super();
  factory DeleteGroupJoinRequestRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteGroupJoinRequestRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'DeleteGroupJoinRequestRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'requestId')
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteGroupJoinRequestRequest clone() =>
      DeleteGroupJoinRequestRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteGroupJoinRequestRequest copyWith(
          void Function(DeleteGroupJoinRequestRequest) updates) =>
      super.copyWith(
              (message) => updates(message as DeleteGroupJoinRequestRequest))
          as DeleteGroupJoinRequestRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static DeleteGroupJoinRequestRequest create() =>
      DeleteGroupJoinRequestRequest._();
  DeleteGroupJoinRequestRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteGroupJoinRequestRequest> createRepeated() =>
      $pb.PbList<DeleteGroupJoinRequestRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteGroupJoinRequestRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteGroupJoinRequestRequest>(create);
  static DeleteGroupJoinRequestRequest? _defaultInstance;

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

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(1);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
