///
//  Generated code. Do not modify.
//  source: request/group/enrollment/query_group_join_questions_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryGroupJoinQuestionsRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'QueryGroupJoinQuestionsRequest',
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
    ..aOB(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'withAnswers')
    ..aInt64(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  QueryGroupJoinQuestionsRequest._() : super();
  factory QueryGroupJoinQuestionsRequest({
    $fixnum.Int64? groupId,
    $core.bool? withAnswers,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final _result = create();
    if (groupId != null) {
      _result.groupId = groupId;
    }
    if (withAnswers != null) {
      _result.withAnswers = withAnswers;
    }
    if (lastUpdatedDate != null) {
      _result.lastUpdatedDate = lastUpdatedDate;
    }
    return _result;
  }
  factory QueryGroupJoinQuestionsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryGroupJoinQuestionsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  QueryGroupJoinQuestionsRequest clone() =>
      QueryGroupJoinQuestionsRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  QueryGroupJoinQuestionsRequest copyWith(
          void Function(QueryGroupJoinQuestionsRequest) updates) =>
      super.copyWith(
              (message) => updates(message as QueryGroupJoinQuestionsRequest))
          as QueryGroupJoinQuestionsRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static QueryGroupJoinQuestionsRequest create() =>
      QueryGroupJoinQuestionsRequest._();
  QueryGroupJoinQuestionsRequest createEmptyInstance() => create();
  static $pb.PbList<QueryGroupJoinQuestionsRequest> createRepeated() =>
      $pb.PbList<QueryGroupJoinQuestionsRequest>();
  @$core.pragma('dart2js:noInline')
  static QueryGroupJoinQuestionsRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<QueryGroupJoinQuestionsRequest>(create);
  static QueryGroupJoinQuestionsRequest? _defaultInstance;

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
  $core.bool get withAnswers => $_getBF(1);
  @$pb.TagNumber(2)
  set withAnswers($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasWithAnswers() => $_has(1);
  @$pb.TagNumber(2)
  void clearWithAnswers() => clearField(2);

  @$pb.TagNumber(3)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(2);
  @$pb.TagNumber(3)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasLastUpdatedDate() => $_has(2);
  @$pb.TagNumber(3)
  void clearLastUpdatedDate() => clearField(3);
}
