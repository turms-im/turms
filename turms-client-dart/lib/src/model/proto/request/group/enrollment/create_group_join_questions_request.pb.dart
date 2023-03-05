///
//  Generated code. Do not modify.
//  source: request/group/enrollment/create_group_join_questions_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../../model/group/group_join_question.pb.dart' as $0;

class CreateGroupJoinQuestionsRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'CreateGroupJoinQuestionsRequest',
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
    ..pc<$0.GroupJoinQuestion>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'questions',
        $pb.PbFieldType.PM,
        subBuilder: $0.GroupJoinQuestion.create)
    ..hasRequiredFields = false;

  CreateGroupJoinQuestionsRequest._() : super();
  factory CreateGroupJoinQuestionsRequest({
    $fixnum.Int64? groupId,
    $core.Iterable<$0.GroupJoinQuestion>? questions,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (questions != null) {
      _result.questions.addAll(questions);
    }
    return _result;
  }
  factory CreateGroupJoinQuestionsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory CreateGroupJoinQuestionsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  CreateGroupJoinQuestionsRequest clone() =>
      CreateGroupJoinQuestionsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  CreateGroupJoinQuestionsRequest copyWith(
          void Function(CreateGroupJoinQuestionsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as CreateGroupJoinQuestionsRequest))
          as CreateGroupJoinQuestionsRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static CreateGroupJoinQuestionsRequest create() =>
      CreateGroupJoinQuestionsRequest._();
  CreateGroupJoinQuestionsRequest createEmptyInstance() => create();
  static $pb.PbList<CreateGroupJoinQuestionsRequest> createRepeated() =>
      $pb.PbList<CreateGroupJoinQuestionsRequest>();
  @$core.pragma('dart2js:noInline')
  static CreateGroupJoinQuestionsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<CreateGroupJoinQuestionsRequest>(
          create);
  static CreateGroupJoinQuestionsRequest? _defaultInstance;

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
  $core.List<$0.GroupJoinQuestion> get questions => $_getList(1);
}
