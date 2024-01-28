//
//  Generated code. Do not modify.
//  source: request/group/enrollment/check_group_join_questions_answers_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use checkGroupJoinQuestionsAnswersRequestDescriptor instead')
const CheckGroupJoinQuestionsAnswersRequest$json = {
  '1': 'CheckGroupJoinQuestionsAnswersRequest',
  '2': [
    {
      '1': 'question_id_to_answer',
      '3': 1,
      '4': 3,
      '5': 11,
      '6':
          '.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest.QuestionIdToAnswerEntry',
      '10': 'questionIdToAnswer'
    },
  ],
  '3': [CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry$json],
};

@$core.Deprecated('Use checkGroupJoinQuestionsAnswersRequestDescriptor instead')
const CheckGroupJoinQuestionsAnswersRequest_QuestionIdToAnswerEntry$json = {
  '1': 'QuestionIdToAnswerEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 3, '10': 'key'},
    {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': {'7': true},
};

/// Descriptor for `CheckGroupJoinQuestionsAnswersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List checkGroupJoinQuestionsAnswersRequestDescriptor =
    $convert.base64Decode(
        'CiVDaGVja0dyb3VwSm9pblF1ZXN0aW9uc0Fuc3dlcnNSZXF1ZXN0EoABChVxdWVzdGlvbl9pZF'
        '90b19hbnN3ZXIYASADKAsyTS5pbS50dXJtcy5wcm90by5DaGVja0dyb3VwSm9pblF1ZXN0aW9u'
        'c0Fuc3dlcnNSZXF1ZXN0LlF1ZXN0aW9uSWRUb0Fuc3dlckVudHJ5UhJxdWVzdGlvbklkVG9Bbn'
        'N3ZXIaRQoXUXVlc3Rpb25JZFRvQW5zd2VyRW50cnkSEAoDa2V5GAEgASgDUgNrZXkSFAoFdmFs'
        'dWUYAiABKAlSBXZhbHVlOgI4AQ==');
