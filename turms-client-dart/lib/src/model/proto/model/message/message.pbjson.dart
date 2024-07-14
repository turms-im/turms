//
//  Generated code. Do not modify.
//  source: model/message/message.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use messageDescriptor instead')
const Message$json = {
  '1': 'Message',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {
      '1': 'delivery_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'deliveryDate',
      '17': true
    },
    {
      '1': 'modification_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'modificationDate',
      '17': true
    },
    {'1': 'text', '3': 4, '4': 1, '5': 9, '9': 3, '10': 'text', '17': true},
    {
      '1': 'sender_id',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'senderId',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'is_system_message',
      '3': 7,
      '4': 1,
      '5': 8,
      '9': 6,
      '10': 'isSystemMessage',
      '17': true
    },
    {
      '1': 'recipient_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'recipientId',
      '17': true
    },
    {'1': 'records', '3': 9, '4': 3, '5': 12, '10': 'records'},
    {
      '1': 'sequence_id',
      '3': 10,
      '4': 1,
      '5': 5,
      '9': 8,
      '10': 'sequenceId',
      '17': true
    },
    {
      '1': 'pre_message_id',
      '3': 11,
      '4': 1,
      '5': 3,
      '9': 9,
      '10': 'preMessageId',
      '17': true
    },
    {
      '1': 'reaction_groups',
      '3': 12,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.MessageReactionGroup',
      '10': 'reactionGroups'
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
    {'1': '_id'},
    {'1': '_delivery_date'},
    {'1': '_modification_date'},
    {'1': '_text'},
    {'1': '_sender_id'},
    {'1': '_group_id'},
    {'1': '_is_system_message'},
    {'1': '_recipient_id'},
    {'1': '_sequence_id'},
    {'1': '_pre_message_id'},
  ],
};

/// Descriptor for `Message`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List messageDescriptor = $convert.base64Decode(
    'CgdNZXNzYWdlEhMKAmlkGAEgASgDSABSAmlkiAEBEigKDWRlbGl2ZXJ5X2RhdGUYAiABKANIAV'
    'IMZGVsaXZlcnlEYXRliAEBEjAKEW1vZGlmaWNhdGlvbl9kYXRlGAMgASgDSAJSEG1vZGlmaWNh'
    'dGlvbkRhdGWIAQESFwoEdGV4dBgEIAEoCUgDUgR0ZXh0iAEBEiAKCXNlbmRlcl9pZBgFIAEoA0'
    'gEUghzZW5kZXJJZIgBARIeCghncm91cF9pZBgGIAEoA0gFUgdncm91cElkiAEBEi8KEWlzX3N5'
    'c3RlbV9tZXNzYWdlGAcgASgISAZSD2lzU3lzdGVtTWVzc2FnZYgBARImCgxyZWNpcGllbnRfaW'
    'QYCCABKANIB1ILcmVjaXBpZW50SWSIAQESGAoHcmVjb3JkcxgJIAMoDFIHcmVjb3JkcxIkCgtz'
    'ZXF1ZW5jZV9pZBgKIAEoBUgIUgpzZXF1ZW5jZUlkiAEBEikKDnByZV9tZXNzYWdlX2lkGAsgAS'
    'gDSAlSDHByZU1lc3NhZ2VJZIgBARJNCg9yZWFjdGlvbl9ncm91cHMYDCADKAsyJC5pbS50dXJt'
    'cy5wcm90by5NZXNzYWdlUmVhY3Rpb25Hcm91cFIOcmVhY3Rpb25Hcm91cHMSQgoRY3VzdG9tX2'
    'F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRl'
    'c0IFCgNfaWRCEAoOX2RlbGl2ZXJ5X2RhdGVCFAoSX21vZGlmaWNhdGlvbl9kYXRlQgcKBV90ZX'
    'h0QgwKCl9zZW5kZXJfaWRCCwoJX2dyb3VwX2lkQhQKEl9pc19zeXN0ZW1fbWVzc2FnZUIPCg1f'
    'cmVjaXBpZW50X2lkQg4KDF9zZXF1ZW5jZV9pZEIRCg9fcHJlX21lc3NhZ2VfaWQ=');
