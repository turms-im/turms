//
//  Generated code. Do not modify.
//  source: request/user/relationship/create_relationship_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createRelationshipRequestDescriptor instead')
const CreateRelationshipRequest$json = {
  '1': 'CreateRelationshipRequest',
  '2': [
    {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    {'1': 'blocked', '3': 2, '4': 1, '5': 8, '10': 'blocked'},
    {
      '1': 'group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'groupIndex',
      '17': true
    },
    {'1': 'name', '3': 4, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
  ],
  '8': [
    {'1': '_group_index'},
    {'1': '_name'},
  ],
};

/// Descriptor for `CreateRelationshipRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createRelationshipRequestDescriptor = $convert.base64Decode(
    'ChlDcmVhdGVSZWxhdGlvbnNoaXBSZXF1ZXN0EhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBIYCg'
    'dibG9ja2VkGAIgASgIUgdibG9ja2VkEiQKC2dyb3VwX2luZGV4GAMgASgFSABSCmdyb3VwSW5k'
    'ZXiIAQESFwoEbmFtZRgEIAEoCUgBUgRuYW1liAEBQg4KDF9ncm91cF9pbmRleEIHCgVfbmFtZQ'
    '==');
