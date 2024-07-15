//
//  Generated code. Do not modify.
//  source: request/message/create_message_reactions_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createMessageReactionsRequestDescriptor instead')
const CreateMessageReactionsRequest$json = {
  '1': 'CreateMessageReactionsRequest',
  '2': [
    {'1': 'message_id', '3': 1, '4': 1, '5': 3, '10': 'messageId'},
    {'1': 'reaction_types', '3': 2, '4': 3, '5': 5, '10': 'reactionTypes'},
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

/// Descriptor for `CreateMessageReactionsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createMessageReactionsRequestDescriptor = $convert.base64Decode(
    'Ch1DcmVhdGVNZXNzYWdlUmVhY3Rpb25zUmVxdWVzdBIdCgptZXNzYWdlX2lkGAEgASgDUgltZX'
    'NzYWdlSWQSJQoOcmVhY3Rpb25fdHlwZXMYAiADKAVSDXJlYWN0aW9uVHlwZXMSQgoRY3VzdG9t'
    'X2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidX'
    'Rlcw==');
