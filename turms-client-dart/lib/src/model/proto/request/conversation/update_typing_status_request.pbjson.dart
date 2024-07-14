//
//  Generated code. Do not modify.
//  source: request/conversation/update_typing_status_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateTypingStatusRequestDescriptor instead')
const UpdateTypingStatusRequest$json = {
  '1': 'UpdateTypingStatusRequest',
  '2': [
    {'1': 'is_group_message', '3': 1, '4': 1, '5': 8, '10': 'isGroupMessage'},
    {'1': 'to_id', '3': 2, '4': 1, '5': 3, '10': 'toId'},
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

/// Descriptor for `UpdateTypingStatusRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateTypingStatusRequestDescriptor = $convert.base64Decode(
    'ChlVcGRhdGVUeXBpbmdTdGF0dXNSZXF1ZXN0EigKEGlzX2dyb3VwX21lc3NhZ2UYASABKAhSDm'
    'lzR3JvdXBNZXNzYWdlEhMKBXRvX2lkGAIgASgDUgR0b0lkEkIKEWN1c3RvbV9hdHRyaWJ1dGVz'
    'GA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXM=');
