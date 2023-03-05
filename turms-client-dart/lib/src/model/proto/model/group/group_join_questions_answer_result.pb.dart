///
//  Generated code. Do not modify.
//  source: model/group/group_join_questions_answer_result.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class GroupJoinQuestionsAnswerResult extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'GroupJoinQuestionsAnswerResult',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..a<$core.int>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'score',
        $pb.PbFieldType.O3)
    ..p<$fixnum.Int64>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'questionIds',
        $pb.PbFieldType.K6)
    ..aOB(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'joined')
    ..hasRequiredFields = false;

  GroupJoinQuestionsAnswerResult._() : super();
  factory GroupJoinQuestionsAnswerResult({
    $core.int? score,
    $core.Iterable<$fixnum.Int64>? questionIds,
    $core.bool? joined,
  }) {
    final _result = create();
    if (score != null) {
      _result.score = score;
    }
    if (questionIds != null) {
      _result.questionIds.addAll(questionIds);
    }
    if (joined != null) {
      _result.joined = joined;
    }
    return _result;
  }
  factory GroupJoinQuestionsAnswerResult.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupJoinQuestionsAnswerResult.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupJoinQuestionsAnswerResult clone() =>
      GroupJoinQuestionsAnswerResult()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupJoinQuestionsAnswerResult copyWith(
          void Function(GroupJoinQuestionsAnswerResult) updates) =>
      super.copyWith(
              (message) => updates(message as GroupJoinQuestionsAnswerResult))
          as GroupJoinQuestionsAnswerResult; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static GroupJoinQuestionsAnswerResult create() =>
      GroupJoinQuestionsAnswerResult._();
  GroupJoinQuestionsAnswerResult createEmptyInstance() => create();
  static $pb.PbList<GroupJoinQuestionsAnswerResult> createRepeated() =>
      $pb.PbList<GroupJoinQuestionsAnswerResult>();
  @$core.pragma('dart2js:noInline')
  static GroupJoinQuestionsAnswerResult getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupJoinQuestionsAnswerResult>(create);
  static GroupJoinQuestionsAnswerResult? _defaultInstance;

  @$pb.TagNumber(1)
  $core.int get score => $_getIZ(0);
  @$pb.TagNumber(1)
  set score($core.int v) {
    $_setSignedInt32(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasScore() => $_has(0);
  @$pb.TagNumber(1)
  void clearScore() => clearField(1);

  @$pb.TagNumber(2)
  $core.List<$fixnum.Int64> get questionIds => $_getList(1);

  @$pb.TagNumber(3)
  $core.bool get joined => $_getBF(2);
  @$pb.TagNumber(3)
  set joined($core.bool v) {
    $_setBool(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasJoined() => $_has(2);
  @$pb.TagNumber(3)
  void clearJoined() => clearField(3);
}
