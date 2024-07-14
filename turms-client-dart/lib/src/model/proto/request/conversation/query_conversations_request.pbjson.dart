//
//  Generated code. Do not modify.
//  source: request/conversation/query_conversations_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryConversationsRequestDescriptor instead')
const QueryConversationsRequest$json = {
  '1': 'QueryConversationsRequest',
  '2': [
    {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
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

/// Descriptor for `QueryConversationsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryConversationsRequestDescriptor = $convert.base64Decode(
    'ChlRdWVyeUNvbnZlcnNhdGlvbnNSZXF1ZXN0EhkKCHVzZXJfaWRzGAEgAygDUgd1c2VySWRzEh'
    'sKCWdyb3VwX2lkcxgCIAMoA1IIZ3JvdXBJZHMSQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsy'
    'FS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlcw==');
