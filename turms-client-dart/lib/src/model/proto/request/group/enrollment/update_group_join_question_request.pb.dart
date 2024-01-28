//
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_join_question_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class UpdateGroupJoinQuestionRequest extends $pb.GeneratedMessage {
  factory UpdateGroupJoinQuestionRequest({
    $fixnum.Int64? questionId,
    $core.String? question,
    $core.Iterable<$core.String>? answers,
    $core.int? score,
  }) {
    final $result = create();
    if (questionId != null) {
      $result.questionId = questionId;
    }
    if (question != null) {
      $result.question = question;
    }
    if (answers != null) {
      $result.answers.addAll(answers);
    }
    if (score != null) {
      $result.score = score;
    }
    return $result;
  }
  UpdateGroupJoinQuestionRequest._() : super();
  factory UpdateGroupJoinQuestionRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory UpdateGroupJoinQuestionRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'UpdateGroupJoinQuestionRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'questionId')
    ..aOS(2, _omitFieldNames ? '' : 'question')
    ..pPS(3, _omitFieldNames ? '' : 'answers')
    ..a<$core.int>(4, _omitFieldNames ? '' : 'score', $pb.PbFieldType.O3)
    ..hasRequiredFields = false;

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
          as UpdateGroupJoinQuestionRequest;

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

  /// Query filter
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

  /// Update
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

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
