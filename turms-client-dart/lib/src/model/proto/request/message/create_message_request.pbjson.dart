///
//  Generated code. Do not modify.
//  source: request/message/create_message_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createMessageRequestDescriptor instead')
const CreateMessageRequest$json = const {
  '1': 'CreateMessageRequest',
  '2': const [
    const {
      '1': 'message_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'messageId',
      '17': true
    },
    const {
      '1': 'is_system_message',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'isSystemMessage',
      '17': true
    },
    const {
      '1': 'group_id',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'groupId',
      '17': true
    },
    const {
      '1': 'recipient_id',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'recipientId',
      '17': true
    },
    const {
      '1': 'delivery_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'deliveryDate',
      '17': true
    },
    const {
      '1': 'text',
      '3': 6,
      '4': 1,
      '5': 9,
      '9': 5,
      '10': 'text',
      '17': true
    },
    const {'1': 'records', '3': 7, '4': 3, '5': 12, '10': 'records'},
    const {
      '1': 'burn_after',
      '3': 8,
      '4': 1,
      '5': 5,
      '9': 6,
      '10': 'burnAfter',
      '17': true
    },
    const {
      '1': 'pre_message_id',
      '3': 9,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'preMessageId',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_message_id'},
    const {'1': '_is_system_message'},
    const {'1': '_group_id'},
    const {'1': '_recipient_id'},
    const {'1': '_delivery_date'},
    const {'1': '_text'},
    const {'1': '_burn_after'},
    const {'1': '_pre_message_id'},
  ],
};

/// Descriptor for `CreateMessageRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createMessageRequestDescriptor = $convert.base64Decode(
    'ChRDcmVhdGVNZXNzYWdlUmVxdWVzdBIiCgptZXNzYWdlX2lkGAEgASgDSABSCW1lc3NhZ2VJZIgBARIvChFpc19zeXN0ZW1fbWVzc2FnZRgCIAEoCEgBUg9pc1N5c3RlbU1lc3NhZ2WIAQESHgoIZ3JvdXBfaWQYAyABKANIAlIHZ3JvdXBJZIgBARImCgxyZWNpcGllbnRfaWQYBCABKANIA1ILcmVjaXBpZW50SWSIAQESKAoNZGVsaXZlcnlfZGF0ZRgFIAEoA0gEUgxkZWxpdmVyeURhdGWIAQESFwoEdGV4dBgGIAEoCUgFUgR0ZXh0iAEBEhgKB3JlY29yZHMYByADKAxSB3JlY29yZHMSIgoKYnVybl9hZnRlchgIIAEoBUgGUglidXJuQWZ0ZXKIAQESKQoOcHJlX21lc3NhZ2VfaWQYCSABKANIB1IMcHJlTWVzc2FnZUlkiAEBQg0KC19tZXNzYWdlX2lkQhQKEl9pc19zeXN0ZW1fbWVzc2FnZUILCglfZ3JvdXBfaWRCDwoNX3JlY2lwaWVudF9pZEIQCg5fZGVsaXZlcnlfZGF0ZUIHCgVfdGV4dEINCgtfYnVybl9hZnRlckIRCg9fcHJlX21lc3NhZ2VfaWQ=');
