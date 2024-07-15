//
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_join_question_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupJoinQuestionRequestDescriptor instead')
const UpdateGroupJoinQuestionRequest$json = {
  '1': 'UpdateGroupJoinQuestionRequest',
  '2': [
    {'1': 'question_id', '3': 1, '4': 1, '5': 3, '10': 'questionId'},
    {
      '1': 'question',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'question',
      '17': true
    },
    {'1': 'answers', '3': 3, '4': 3, '5': 9, '10': 'answers'},
    {'1': 'score', '3': 4, '4': 1, '5': 5, '9': 1, '10': 'score', '17': true},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
  '8': [
    {'1': '_question'},
    {'1': '_score'},
  ],
};

/// Descriptor for `UpdateGroupJoinQuestionRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupJoinQuestionRequestDescriptor = $convert.base64Decode(
    'Ch5VcGRhdGVHcm91cEpvaW5RdWVzdGlvblJlcXVlc3QSHwoLcXVlc3Rpb25faWQYASABKANSCn'
    'F1ZXN0aW9uSWQSHwoIcXVlc3Rpb24YAiABKAlIAFIIcXVlc3Rpb26IAQESGAoHYW5zd2VycxgD'
    'IAMoCVIHYW5zd2VycxIZCgVzY29yZRgEIAEoBUgBUgVzY29yZYgBARJCChFjdXN0b21fYXR0cm'
    'lidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzQgsK'
    'CV9xdWVzdGlvbkIICgZfc2NvcmU=');
