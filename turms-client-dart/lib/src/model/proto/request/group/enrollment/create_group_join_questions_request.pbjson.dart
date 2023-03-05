///
//  Generated code. Do not modify.
//  source: request/group/enrollment/create_group_join_questions_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupJoinQuestionsRequestDescriptor instead')
const CreateGroupJoinQuestionsRequest$json = const {
  '1': 'CreateGroupJoinQuestionsRequest',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {
      '1': 'questions',
      '3': 2,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.GroupJoinQuestion',
      '10': 'questions'
    },
  ],
};

/// Descriptor for `CreateGroupJoinQuestionsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupJoinQuestionsRequestDescriptor =
    $convert.base64Decode(
        'Ch9DcmVhdGVHcm91cEpvaW5RdWVzdGlvbnNSZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm91cElkEj8KCXF1ZXN0aW9ucxgCIAMoCzIhLmltLnR1cm1zLnByb3RvLkdyb3VwSm9pblF1ZXN0aW9uUglxdWVzdGlvbnM=');
