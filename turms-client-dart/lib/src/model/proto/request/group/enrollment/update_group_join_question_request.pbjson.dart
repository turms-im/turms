///
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_join_question_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupJoinQuestionRequestDescriptor instead')
const UpdateGroupJoinQuestionRequest$json = const {
  '1': 'UpdateGroupJoinQuestionRequest',
  '2': const [
    const {'1': 'question_id', '3': 1, '4': 1, '5': 3, '10': 'questionId'},
    const {
      '1': 'question',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'question',
      '17': true
    },
    const {'1': 'answers', '3': 3, '4': 3, '5': 9, '10': 'answers'},
    const {
      '1': 'score',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'score',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_question'},
    const {'1': '_score'},
  ],
};

/// Descriptor for `UpdateGroupJoinQuestionRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupJoinQuestionRequestDescriptor =
    $convert.base64Decode(
        'Ch5VcGRhdGVHcm91cEpvaW5RdWVzdGlvblJlcXVlc3QSHwoLcXVlc3Rpb25faWQYASABKANSCnF1ZXN0aW9uSWQSHwoIcXVlc3Rpb24YAiABKAlIAFIIcXVlc3Rpb26IAQESGAoHYW5zd2VycxgDIAMoCVIHYW5zd2VycxIZCgVzY29yZRgEIAEoBUgBUgVzY29yZYgBAUILCglfcXVlc3Rpb25CCAoGX3Njb3Jl');
