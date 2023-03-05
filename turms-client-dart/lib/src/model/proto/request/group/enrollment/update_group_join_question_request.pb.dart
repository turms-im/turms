///
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_join_question_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateGroupJoinQuestionRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'UpdateGroupJoinQuestionRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'questionId')
    ..aOS(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'question')
    ..pPS(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'answers')
    ..a<$core.int>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'score',
        $pb.PbFieldType.O3)
    ..hasRequiredFields = false;

  UpdateGroupJoinQuestionRequest._() : super();
  factory UpdateGroupJoinQuestionRequest({
    $fixnum.Int64? questionId,
    $core.String? question,
    $core.Iterable<$core.String>? answers,
    $core.int? score,
  }) {
    final _result = create();
    if (questionId != null) {
      _result.questionId = questionId;
    }
    if (question != null) {
      _result.question = question;
    }
    if (answers != null) {
      _result.answers.addAll(answers);
    }
    if (score != null) {
      _result.score = score;
    }
    return _result;
  }
  factory UpdateGroupJoinQuestionRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateGroupJoinQuestionRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  UpdateGroupJoinQuestionRequest clone() =>
      UpdateGroupJoinQuestionRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  UpdateGroupJoinQuestionRequest copyWith(
          void Function(UpdateGroupJoinQuestionRequest) updates) =>
      super.copyWith(
              (message) => updates(message as UpdateGroupJoinQuestionRequest))
          as UpdateGroupJoinQuestionRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static UpdateGroupJoinQuestionRequest create() =>
      UpdateGroupJoinQuestionRequest._();
  UpdateGroupJoinQuestionRequest createEmptyInstance() => create();
  static $pb.PbList<UpdateGroupJoinQuestionRequest> createRepeated() =>
      $pb.PbList<UpdateGroupJoinQuestionRequest>();
  @$core.pragma('dart2js:noInline')
  static UpdateGroupJoinQuestionRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<UpdateGroupJoinQuestionRequest>(create);
  static UpdateGroupJoinQuestionRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get questionId => $_getI64(0);
  @$pb.TagNumber(1)
  set questionId($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasQuestionId() => $_has(0);
  @$pb.TagNumber(1)
  void clearQuestionId() => clearField(1);

  @$pb.TagNumber(2)
  $core.String get question => $_getSZ(1);
  @$pb.TagNumber(2)
  set question($core.String v) {
    $_setString(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasQuestion() => $_has(1);
  @$pb.TagNumber(2)
  void clearQuestion() => clearField(2);

  @$pb.TagNumber(3)
  $core.List<$core.String> get answers => $_getList(2);

  @$pb.TagNumber(4)
  $core.int get score => $_getIZ(3);
  @$pb.TagNumber(4)
  set score($core.int v) {
    $_setSignedInt32(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasScore() => $_has(3);
  @$pb.TagNumber(4)
  void clearScore() => clearField(4);
}
