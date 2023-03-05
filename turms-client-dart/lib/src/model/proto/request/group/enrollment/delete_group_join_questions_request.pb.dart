///
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_join_questions_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class DeleteGroupJoinQuestionsRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'DeleteGroupJoinQuestionsRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'groupId')
    ..p<$fixnum.Int64>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'questionIds',
        $pb.PbFieldType.K6)
    ..hasRequiredFields = false;

  DeleteGroupJoinQuestionsRequest._() : super();
  factory DeleteGroupJoinQuestionsRequest({
    $fixnum.Int64? groupId,
    $core.Iterable<$fixnum.Int64>? questionIds,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (questionIds != null) {
      _result.questionIds.addAll(questionIds);
    }
    return _result;
  }
  factory DeleteGroupJoinQuestionsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteGroupJoinQuestionsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteGroupJoinQuestionsRequest clone() =>
      DeleteGroupJoinQuestionsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteGroupJoinQuestionsRequest copyWith(
          void Function(DeleteGroupJoinQuestionsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as DeleteGroupJoinQuestionsRequest))
          as DeleteGroupJoinQuestionsRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static DeleteGroupJoinQuestionsRequest create() =>
      DeleteGroupJoinQuestionsRequest._();
  DeleteGroupJoinQuestionsRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteGroupJoinQuestionsRequest> createRepeated() =>
      $pb.PbList<DeleteGroupJoinQuestionsRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteGroupJoinQuestionsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteGroupJoinQuestionsRequest>(
          create);
  static DeleteGroupJoinQuestionsRequest? _defaultInstance;

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
  $core.List<$fixnum.Int64> get questionIds => $_getList(1);
}
