///
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_join_question_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class DeleteGroupJoinQuestionRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'DeleteGroupJoinQuestionRequest', package: const $pb.PackageName(const $core.bool.fromEnvironment('protobuf.omit_message_names') ? '' : 'im.turms.proto'), createEmptyInstance: create)
    ..aInt64(1, const $core.bool.fromEnvironment('protobuf.omit_field_names') ? '' : 'questionId')
    ..hasRequiredFields = false
  ;

  DeleteGroupJoinQuestionRequest._() : super();
  factory DeleteGroupJoinQuestionRequest({
    $fixnum.Int64? questionId,
  }) {
    final _result = create();
    if (questionId != null) {
      _result.questionId = questionId;
    }
    return _result;
  }
  factory DeleteGroupJoinQuestionRequest.fromBuffer($core.List<$core.int> i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromBuffer(i, r);
  factory DeleteGroupJoinQuestionRequest.fromJson($core.String i, [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) => create()..mergeFromJson(i, r);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
  'Will be removed in next major version')
  DeleteGroupJoinQuestionRequest clone() => DeleteGroupJoinQuestionRequest()..mergeFromMessage(this);
  @$core.Deprecated(
  'Using this can add significant overhead to your binary. '
  'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
  'Will be removed in next major version')
  DeleteGroupJoinQuestionRequest copyWith(void Function(DeleteGroupJoinQuestionRequest) updates) => super.copyWith((message) => updates(message as DeleteGroupJoinQuestionRequest)) as DeleteGroupJoinQuestionRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static DeleteGroupJoinQuestionRequest create() => DeleteGroupJoinQuestionRequest._();
  DeleteGroupJoinQuestionRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteGroupJoinQuestionRequest> createRepeated() => $pb.PbList<DeleteGroupJoinQuestionRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteGroupJoinQuestionRequest getDefault() => _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<DeleteGroupJoinQuestionRequest>(create);
  static DeleteGroupJoinQuestionRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get questionId => $_getI64(0);
  @$pb.TagNumber(1)
  set questionId($fixnum.Int64 v) { $_setInt64(0, v); }
  @$pb.TagNumber(1)
  $core.bool hasQuestionId() => $_has(0);
  @$pb.TagNumber(1)
  void clearQuestionId() => clearField(1);
}

