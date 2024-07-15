//
//  Generated code. Do not modify.
//  source: request/user/relationship/update_relationship_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateRelationshipRequestDescriptor instead')
const UpdateRelationshipRequest$json = {
  '1': 'UpdateRelationshipRequest',
  '2': [
    {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    {
      '1': 'blocked',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'blocked',
      '17': true
    },
    {
      '1': 'new_group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'newGroupIndex',
      '17': true
    },
    {
      '1': 'delete_group_index',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'deleteGroupIndex',
      '17': true
    },
    {'1': 'name', '3': 5, '4': 1, '5': 9, '9': 3, '10': 'name', '17': true},
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
    {'1': '_blocked'},
    {'1': '_new_group_index'},
    {'1': '_delete_group_index'},
    {'1': '_name'},
  ],
};

/// Descriptor for `UpdateRelationshipRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateRelationshipRequestDescriptor = $convert.base64Decode(
    'ChlVcGRhdGVSZWxhdGlvbnNoaXBSZXF1ZXN0EhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBIdCg'
    'dibG9ja2VkGAIgASgISABSB2Jsb2NrZWSIAQESKwoPbmV3X2dyb3VwX2luZGV4GAMgASgFSAFS'
    'DW5ld0dyb3VwSW5kZXiIAQESMQoSZGVsZXRlX2dyb3VwX2luZGV4GAQgASgFSAJSEGRlbGV0ZU'
    'dyb3VwSW5kZXiIAQESFwoEbmFtZRgFIAEoCUgDUgRuYW1liAEBEkIKEWN1c3RvbV9hdHRyaWJ1'
    'dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXNCCgoIX2'
    'Jsb2NrZWRCEgoQX25ld19ncm91cF9pbmRleEIVChNfZGVsZXRlX2dyb3VwX2luZGV4QgcKBV9u'
    'YW1l');
