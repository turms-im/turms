//
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_join_questions_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteGroupJoinQuestionsRequestDescriptor instead')
const DeleteGroupJoinQuestionsRequest$json = {
  '1': 'DeleteGroupJoinQuestionsRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'question_ids', '3': 2, '4': 3, '5': 3, '10': 'questionIds'},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
};

/// Descriptor for `DeleteGroupJoinQuestionsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteGroupJoinQuestionsRequestDescriptor =
    $convert.base64Decode(
        'Ch9EZWxldGVHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm'
        '91cElkEiEKDHF1ZXN0aW9uX2lkcxgCIAMoA1ILcXVlc3Rpb25JZHMSQgoRY3VzdG9tX2F0dHJp'
        'YnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlcw==');
