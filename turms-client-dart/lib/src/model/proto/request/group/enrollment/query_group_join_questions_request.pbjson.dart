//
//  Generated code. Do not modify.
//  source: request/group/enrollment/query_group_join_questions_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupJoinQuestionsRequestDescriptor instead')
const QueryGroupJoinQuestionsRequest$json = {
  '1': 'QueryGroupJoinQuestionsRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'with_answers', '3': 2, '4': 1, '5': 8, '10': 'withAnswers'},
    {
      '1': 'last_updated_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDate',
      '17': true
    },
  ],
  '8': [
    {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryGroupJoinQuestionsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupJoinQuestionsRequestDescriptor =
    $convert.base64Decode(
        'Ch5RdWVyeUdyb3VwSm9pblF1ZXN0aW9uc1JlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3'
        'VwSWQSIQoMd2l0aF9hbnN3ZXJzGAIgASgIUgt3aXRoQW5zd2VycxIvChFsYXN0X3VwZGF0ZWRf'
        'ZGF0ZRgDIAEoA0gAUg9sYXN0VXBkYXRlZERhdGWIAQFCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
