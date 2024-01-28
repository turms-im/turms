//
//  Generated code. Do not modify.
//  source: request/group/enrollment/check_group_join_questions_answers_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class CheckGroupJoinQuestionsAnswersRequest extends $pb.GeneratedMessage {
  factory CheckGroupJoinQuestionsAnswersRequest({
    $core.Map<$fixnum.Int64, $core.String>? questionIdToAnswer,
  }) {
    final $result = create();
    if (questionIdToAnswer != null) {
      $result.questionIdToAnswer.addAll(questionIdToAnswer);
    }
    return $result;
  }
  CheckGroupJoinQuestionsAnswersRequest._() : super();
  factory CheckGroupJoinQuestionsAnswersRequest.fromBuffer(
          $core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CheckGroupJoinQuestionsAnswersRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'CheckGroupJoinQuestionsAnswersRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..m<$fixnum.Int64, $core.String>(
        1, _omitFieldNames ? '' : 'questionIdToAnswer',
        entryClassName:
            'CheckGroupJoinQuestionsAnswersRequest.QuestionIdToAnswerEntry',
        keyFieldType: $pb.PbFieldType.O6,
        valueFieldType: $pb.PbFieldType.OS,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CheckGroupJoinQuestionsAnswersRequest clone() =>
      CheckGroupJoinQuestionsAnswersRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CheckGroupJoinQuestionsAnswersRequest copyWith(
          void Function(CheckGroupJoinQuestionsAnswersRequest) updates) =>
      super.copyWith((message) =>
              updates(message as CheckGroupJoinQuestionsAnswersRequest))
          as CheckGroupJoinQuestionsAnswersRequest;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static CheckGroupJoinQuestionsAnswersRequest create() =>
      CheckGroupJoinQuestionsAnswersRequest._();
  CheckGroupJoinQuestionsAnswersRequest createEmptyInstance() => create();
  static $pb.PbList<CheckGroupJoinQuestionsAnswersRequest> createRepeated() =>
      $pb.PbList<CheckGroupJoinQuestionsAnswersRequest>();
  @$core.pragma('dart2js:noInline')
  static CheckGroupJoinQuestionsAnswersRequest getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<
          CheckGroupJoinQuestionsAnswersRequest>(create);
  static CheckGroupJoinQuestionsAnswersRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $core.Map<$fixnum.Int64, $core.String> get questionIdToAnswer => $_getMap(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
