//
//  Generated code. Do not modify.
//  source: model/group/group_join_question.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../common/value.pb.dart' as $0;

class GroupJoinQuestion extends $pb.GeneratedMessage {
  factory GroupJoinQuestion({
    $fixnum.Int64? id,
    $fixnum.Int64? groupId,
    $core.String? question,
    $core.Iterable<$core.String>? answers,
    $core.int? score,
    $core.Iterable<$0.Value>? customAttributes,
  }) {
    final $result = create();
    if (id != null) {
      $result.id = id;
    }
    if (groupId != null) {
      $result.groupId = groupId;
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
    if (customAttributes != null) {
      $result.customAttributes.addAll(customAttributes);
    }
    return $result;
  }
  GroupJoinQuestion._() : super();
  factory GroupJoinQuestion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory GroupJoinQuestion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'GroupJoinQuestion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'id')
    ..aInt64(2, _omitFieldNames ? '' : 'groupId')
    ..aOS(3, _omitFieldNames ? '' : 'question')
    ..pPS(4, _omitFieldNames ? '' : 'answers')
    ..a<$core.int>(5, _omitFieldNames ? '' : 'score', $pb.PbFieldType.O3)
    ..pc<$0.Value>(
        15, _omitFieldNames ? '' : 'customAttributes', $pb.PbFieldType.PM,
        subBuilder: $0.Value.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  GroupJoinQuestion clone() => GroupJoinQuestion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  GroupJoinQuestion copyWith(void Function(GroupJoinQuestion) updates) =>
      super.copyWith((message) => updates(message as GroupJoinQuestion))
          as GroupJoinQuestion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static GroupJoinQuestion create() => GroupJoinQuestion._();
  GroupJoinQuestion createEmptyInstance() => create();
  static $pb.PbList<GroupJoinQuestion> createRepeated() =>
      $pb.PbList<GroupJoinQuestion>();
  @$core.pragma('dart2js:noInline')
  static GroupJoinQuestion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<GroupJoinQuestion>(create);
  static GroupJoinQuestion? _defaultInstance;

  @$pb.TagNumber(1)
  $fixnum.Int64 get id => $_getI64(0);
  @$pb.TagNumber(1)
  set id($fixnum.Int64 v) {
    $_setInt64(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasId() => $_has(0);
  @$pb.TagNumber(1)
  void clearId() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get groupId => $_getI64(1);
  @$pb.TagNumber(2)
  set groupId($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasGroupId() => $_has(1);
  @$pb.TagNumber(2)
  void clearGroupId() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get question => $_getSZ(2);
  @$pb.TagNumber(3)
  set question($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasQuestion() => $_has(2);
  @$pb.TagNumber(3)
  void clearQuestion() => clearField(3);

  @$pb.TagNumber(4)
  $core.List<$core.String> get answers => $_getList(3);

  @$pb.TagNumber(5)
  $core.int get score => $_getIZ(4);
  @$pb.TagNumber(5)
  set score($core.int v) {
    $_setSignedInt32(4, v);
  }

  @$pb.TagNumber(5)
  $core.bool hasScore() => $_has(4);
  @$pb.TagNumber(5)
  void clearScore() => clearField(5);

  @$pb.TagNumber(15)
  $core.List<$0.Value> get customAttributes => $_getList(5);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
