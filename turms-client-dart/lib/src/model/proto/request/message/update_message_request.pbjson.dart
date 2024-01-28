//
//  Generated code. Do not modify.
//  source: request/message/update_message_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateMessageRequestDescriptor instead')
const UpdateMessageRequest$json = {
  '1': 'UpdateMessageRequest',
  '2': [
    {'1': 'message_id', '3': 1, '4': 1, '5': 3, '10': 'messageId'},
    {'1': 'text', '3': 2, '4': 1, '5': 9, '9': 0, '10': 'text', '17': true},
    {'1': 'records', '3': 3, '4': 3, '5': 12, '10': 'records'},
    {
      '1': 'recall_date',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'recallDate',
      '17': true
    },
  ],
  '8': [
    {'1': '_text'},
    {'1': '_recall_date'},
  ],
};

/// Descriptor for `UpdateMessageRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateMessageRequestDescriptor = $convert.base64Decode(
    'ChRVcGRhdGVNZXNzYWdlUmVxdWVzdBIdCgptZXNzYWdlX2lkGAEgASgDUgltZXNzYWdlSWQSFw'
    'oEdGV4dBgCIAEoCUgAUgR0ZXh0iAEBEhgKB3JlY29yZHMYAyADKAxSB3JlY29yZHMSJAoLcmVj'
    'YWxsX2RhdGUYBCABKANIAVIKcmVjYWxsRGF0ZYgBAUIHCgVfdGV4dEIOCgxfcmVjYWxsX2RhdG'
    'U=');
