//
//  Generated code. Do not modify.
//  source: request/conversation/query_conversation_settings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryConversationSettingsRequestDescriptor instead')
const QueryConversationSettingsRequest$json = {
  '1': 'QueryConversationSettingsRequest',
  '2': [
    {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
    {'1': 'names', '3': 3, '4': 3, '5': 9, '10': 'names'},
    {
      '1': 'last_updated_date_start',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDateStart',
      '17': true
    },
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
    {'1': '_last_updated_date_start'},
  ],
};

/// Descriptor for `QueryConversationSettingsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryConversationSettingsRequestDescriptor = $convert.base64Decode(
    'CiBRdWVyeUNvbnZlcnNhdGlvblNldHRpbmdzUmVxdWVzdBIZCgh1c2VyX2lkcxgBIAMoA1IHdX'
    'NlcklkcxIbCglncm91cF9pZHMYAiADKANSCGdyb3VwSWRzEhQKBW5hbWVzGAMgAygJUgVuYW1l'
    'cxI6ChdsYXN0X3VwZGF0ZWRfZGF0ZV9zdGFydBgEIAEoA0gAUhRsYXN0VXBkYXRlZERhdGVTdG'
    'FydIgBARJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVl'
    'UhBjdXN0b21BdHRyaWJ1dGVzQhoKGF9sYXN0X3VwZGF0ZWRfZGF0ZV9zdGFydA==');
