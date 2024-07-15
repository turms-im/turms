//
//  Generated code. Do not modify.
//  source: request/conversation/update_conversation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateConversationRequestDescriptor instead')
const UpdateConversationRequest$json = {
  '1': 'UpdateConversationRequest',
  '2': [
    {
      '1': 'user_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'userId',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
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
  '8': [
    {'1': '_user_id'},
    {'1': '_group_id'},
  ],
};

/// Descriptor for `UpdateConversationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateConversationRequestDescriptor = $convert.base64Decode(
    'ChlVcGRhdGVDb252ZXJzYXRpb25SZXF1ZXN0EhwKB3VzZXJfaWQYASABKANIAFIGdXNlcklkiA'
    'EBEh4KCGdyb3VwX2lkGAIgASgDSAFSB2dyb3VwSWSIAQESGwoJcmVhZF9kYXRlGAMgASgDUghy'
    'ZWFkRGF0ZRJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbH'
    'VlUhBjdXN0b21BdHRyaWJ1dGVzQgoKCF91c2VyX2lkQgsKCV9ncm91cF9pZA==');
