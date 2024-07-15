//
//  Generated code. Do not modify.
//  source: request/user/relationship/delete_relationship_group_member_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteRelationshipGroupMemberRequestDescriptor instead')
const DeleteRelationshipGroupMemberRequest$json = {
  '1': 'DeleteRelationshipGroupMemberRequest',
  '2': [
    {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    {'1': 'group_index', '3': 2, '4': 1, '5': 5, '10': 'groupIndex'},
    {
      '1': 'target_group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'targetGroupIndex',
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
    {'1': '_target_group_index'},
  ],
};

/// Descriptor for `DeleteRelationshipGroupMemberRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteRelationshipGroupMemberRequestDescriptor =
    $convert.base64Decode(
        'CiREZWxldGVSZWxhdGlvbnNoaXBHcm91cE1lbWJlclJlcXVlc3QSFwoHdXNlcl9pZBgBIAEoA1'
        'IGdXNlcklkEh8KC2dyb3VwX2luZGV4GAIgASgFUgpncm91cEluZGV4EjEKEnRhcmdldF9ncm91'
        'cF9pbmRleBgDIAEoBUgAUhB0YXJnZXRHcm91cEluZGV4iAEBEkIKEWN1c3RvbV9hdHRyaWJ1dG'
        'VzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXNCFQoTX3Rh'
        'cmdldF9ncm91cF9pbmRleA==');
