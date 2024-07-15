//
//  Generated code. Do not modify.
//  source: model/conversation/private_conversation.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use privateConversationDescriptor instead')
const PrivateConversation$json = {
  '1': 'PrivateConversation',
  '2': [
    {'1': 'owner_id', '3': 1, '4': 1, '5': 3, '10': 'ownerId'},
    {'1': 'target_id', '3': 2, '4': 1, '5': 3, '10': 'targetId'},
    {'1': 'read_date', '3': 3, '4': 1, '5': 3, '10': 'readDate'},
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

/// Descriptor for `PrivateConversation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List privateConversationDescriptor = $convert.base64Decode(
    'ChNQcml2YXRlQ29udmVyc2F0aW9uEhkKCG93bmVyX2lkGAEgASgDUgdvd25lcklkEhsKCXRhcm'
    'dldF9pZBgCIAEoA1IIdGFyZ2V0SWQSGwoJcmVhZF9kYXRlGAMgASgDUghyZWFkRGF0ZRJCChFj'
    'dXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdH'
    'RyaWJ1dGVz');
