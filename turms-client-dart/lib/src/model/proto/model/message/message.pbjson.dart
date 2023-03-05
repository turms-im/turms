///
//  Generated code. Do not modify.
//  source: model/message/message.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use messageDescriptor instead')
const Message$json = const {
  '1': 'Message',
  '2': const [
    const {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    const {
      '1': 'delivery_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'deliveryDate',
      '17': true
    },
    const {
      '1': 'modification_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'modificationDate',
      '17': true
    },
    const {
      '1': 'text',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'text',
      '17': true
    },
    const {
      '1': 'sender_id',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'senderId',
      '17': true
    },
    const {
      '1': 'group_id',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'groupId',
      '17': true
    },
    const {
      '1': 'is_system_message',
      '3': 7,
      '4': 1,
      '5': 8,
      '9': 6,
      '10': 'isSystemMessage',
      '17': true
    },
    const {
      '1': 'recipient_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'recipientId',
      '17': true
    },
    const {'1': 'records', '3': 9, '4': 3, '5': 12, '10': 'records'},
    const {
      '1': 'sequence_id',
      '3': 10,
      '4': 1,
      '5': 5,
      '9': 8,
      '10': 'sequenceId',
      '17': true
    },
    const {
      '1': 'pre_message_id',
      '3': 11,
      '4': 1,
      '5': 3,
      '9': 9,
      '10': 'preMessageId',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_id'},
    const {'1': '_delivery_date'},
    const {'1': '_modification_date'},
    const {'1': '_text'},
    const {'1': '_sender_id'},
    const {'1': '_group_id'},
    const {'1': '_is_system_message'},
    const {'1': '_recipient_id'},
    const {'1': '_sequence_id'},
    const {'1': '_pre_message_id'},
  ],
};

/// Descriptor for `Message`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List messageDescriptor = $convert.base64Decode(
    'CgdNZXNzYWdlEhMKAmlkGAEgASgDSABSAmlkiAEBEigKDWRlbGl2ZXJ5X2RhdGUYAiABKANIAVIMZGVsaXZlcnlEYXRliAEBEjAKEW1vZGlmaWNhdGlvbl9kYXRlGAMgASgDSAJSEG1vZGlmaWNhdGlvbkRhdGWIAQESFwoEdGV4dBgEIAEoCUgDUgR0ZXh0iAEBEiAKCXNlbmRlcl9pZBgFIAEoA0gEUghzZW5kZXJJZIgBARIeCghncm91cF9pZBgGIAEoA0gFUgdncm91cElkiAEBEi8KEWlzX3N5c3RlbV9tZXNzYWdlGAcgASgISAZSD2lzU3lzdGVtTWVzc2FnZYgBARImCgxyZWNpcGllbnRfaWQYCCABKANIB1ILcmVjaXBpZW50SWSIAQESGAoHcmVjb3JkcxgJIAMoDFIHcmVjb3JkcxIkCgtzZXF1ZW5jZV9pZBgKIAEoBUgIUgpzZXF1ZW5jZUlkiAEBEikKDnByZV9tZXNzYWdlX2lkGAsgASgDSAlSDHByZU1lc3NhZ2VJZIgBAUIFCgNfaWRCEAoOX2RlbGl2ZXJ5X2RhdGVCFAoSX21vZGlmaWNhdGlvbl9kYXRlQgcKBV90ZXh0QgwKCl9zZW5kZXJfaWRCCwoJX2dyb3VwX2lkQhQKEl9pc19zeXN0ZW1fbWVzc2FnZUIPCg1fcmVjaXBpZW50X2lkQg4KDF9zZXF1ZW5jZV9pZEIRCg9fcHJlX21lc3NhZ2VfaWQ=');
