///
//  Generated code. Do not modify.
//  source: request/group/enrollment/check_group_join_questions_answers_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use checkGroupJoinQuestionsAnswersRequestDescriptor instead')
const CheckGroupJoinQuestionsAnswersRequest$json = const {
  '1': 'CheckGroupJoinQuestionsAnswersRequest',
  '2': const [
    const {'1': 'question_id_and_answer', '3': 1, '4': 3, '5': 11, '6': '.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest.QuestionIdAndAnswerEntry', '10': 'questionIdAndAnswer'},
  ],
  '3': const [CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry$json],
};

@$core.Deprecated('Use checkGroupJoinQuestionsAnswersRequestDescriptor instead')
const CheckGroupJoinQuestionsAnswersRequest_QuestionIdAndAnswerEntry$json = const {
  '1': 'QuestionIdAndAnswerEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 3, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': const {'7': true},
};

/// Descriptor for `CheckGroupJoinQuestionsAnswersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List checkGroupJoinQuestionsAnswersRequestDescriptor = $convert.base64Decode('CiVDaGVja0dyb3VwSm9pblF1ZXN0aW9uc0Fuc3dlcnNSZXF1ZXN0EoMBChZxdWVzdGlvbl9pZF9hbmRfYW5zd2VyGAEgAygLMk4uaW0udHVybXMucHJvdG8uQ2hlY2tHcm91cEpvaW5RdWVzdGlvbnNBbnN3ZXJzUmVxdWVzdC5RdWVzdGlvbklkQW5kQW5zd2VyRW50cnlSE3F1ZXN0aW9uSWRBbmRBbnN3ZXIaRgoYUXVlc3Rpb25JZEFuZEFuc3dlckVudHJ5EhAKA2tleRgBIAEoA1IDa2V5EhQKBXZhbHVlGAIgASgJUgV2YWx1ZToCOAE=');
