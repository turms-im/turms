//
//  Generated code. Do not modify.
//  source: model/user/user_session.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userSessionDescriptor instead')
const UserSession$json = {
  '1': 'UserSession',
  '2': [
    {'1': 'session_id', '3': 1, '4': 1, '5': 9, '10': 'sessionId'},
    {'1': 'server_id', '3': 2, '4': 1, '5': 9, '10': 'serverId'},
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

/// Descriptor for `UserSession`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userSessionDescriptor = $convert.base64Decode(
    'CgtVc2VyU2Vzc2lvbhIdCgpzZXNzaW9uX2lkGAEgASgJUglzZXNzaW9uSWQSGwoJc2VydmVyX2'
    'lkGAIgASgJUghzZXJ2ZXJJZBJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1z'
    'LnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVz');
