//
//  Generated code. Do not modify.
//  source: request/group/enrollment/query_group_join_questions_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class QueryGroupJoinQuestionsRequest extends $pb.GeneratedMessage {
  factory QueryGroupJoinQuestionsRequest({
    $fixnum.Int64? groupId,
    $core.bool? withAnswers,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (groupId != null) {
      $result.groupId = groupId;
    }
    if (withAnswers != null) {
      $result.withAnswers = withAnswers;
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  QueryGroupJoinQuestionsRequest._() : super();
  factory QueryGroupJoinQuestionsRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory QueryGroupJoinQuestionsRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'QueryGroupJoinQuestionsRequest',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aInt64(1, _omitFieldNames ? '' : 'groupId')
    ..aOB(2, _omitFieldNames ? '' : 'withAnswers')
    ..aInt64(3, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

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
          as QueryGroupJoinQuestionsRequest;

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

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
