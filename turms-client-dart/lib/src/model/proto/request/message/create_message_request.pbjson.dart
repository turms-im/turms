//
//  Generated code. Do not modify.
//  source: request/message/create_message_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createMessageRequestDescriptor instead')
const CreateMessageRequest$json = {
  '1': 'CreateMessageRequest',
  '2': [
    {
      '1': 'message_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'messageId',
      '17': true
    },
    {
      '1': 'is_system_message',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'isSystemMessage',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'recipient_id',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'recipientId',
      '17': true
    },
    {
      '1': 'delivery_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'deliveryDate',
      '17': true
    },
    {'1': 'text', '3': 6, '4': 1, '5': 9, '9': 5, '10': 'text', '17': true},
    {'1': 'records', '3': 7, '4': 3, '5': 12, '10': 'records'},
    {
      '1': 'burn_after',
      '3': 8,
      '4': 1,
      '5': 5,
      '9': 6,
      '10': 'burnAfter',
      '17': true
    },
    {
      '1': 'pre_message_id',
      '3': 9,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'preMessageId',
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
    {'1': '_message_id'},
    {'1': '_is_system_message'},
    {'1': '_group_id'},
    {'1': '_recipient_id'},
    {'1': '_delivery_date'},
    {'1': '_text'},
    {'1': '_burn_after'},
    {'1': '_pre_message_id'},
  ],
};

/// Descriptor for `CreateMessageRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createMessageRequestDescriptor = $convert.base64Decode(
    'ChRDcmVhdGVNZXNzYWdlUmVxdWVzdBIiCgptZXNzYWdlX2lkGAEgASgDSABSCW1lc3NhZ2VJZI'
    'gBARIvChFpc19zeXN0ZW1fbWVzc2FnZRgCIAEoCEgBUg9pc1N5c3RlbU1lc3NhZ2WIAQESHgoI'
    'Z3JvdXBfaWQYAyABKANIAlIHZ3JvdXBJZIgBARImCgxyZWNpcGllbnRfaWQYBCABKANIA1ILcm'
    'VjaXBpZW50SWSIAQESKAoNZGVsaXZlcnlfZGF0ZRgFIAEoA0gEUgxkZWxpdmVyeURhdGWIAQES'
    'FwoEdGV4dBgGIAEoCUgFUgR0ZXh0iAEBEhgKB3JlY29yZHMYByADKAxSB3JlY29yZHMSIgoKYn'
    'Vybl9hZnRlchgIIAEoBUgGUglidXJuQWZ0ZXKIAQESKQoOcHJlX21lc3NhZ2VfaWQYCSABKANI'
    'B1IMcHJlTWVzc2FnZUlkiAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybX'
    'MucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXNCDQoLX21lc3NhZ2VfaWRCFAoSX2lzX3N5'
    'c3RlbV9tZXNzYWdlQgsKCV9ncm91cF9pZEIPCg1fcmVjaXBpZW50X2lkQhAKDl9kZWxpdmVyeV'
    '9kYXRlQgcKBV90ZXh0Qg0KC19idXJuX2FmdGVyQhEKD19wcmVfbWVzc2FnZV9pZA==');
