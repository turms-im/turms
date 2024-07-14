//
//  Generated code. Do not modify.
//  source: request/group/member/query_group_members_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupMembersRequestDescriptor instead')
const QueryGroupMembersRequest$json = {
  '1': 'QueryGroupMembersRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {
      '1': 'last_updated_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {'1': 'member_ids', '3': 3, '4': 3, '5': 3, '10': 'memberIds'},
    {
      '1': 'with_status',
      '3': 4,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'withStatus',
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
    {'1': '_last_updated_date'},
    {'1': '_with_status'},
  ],
};

/// Descriptor for `QueryGroupMembersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupMembersRequestDescriptor = $convert.base64Decode(
    'ChhRdWVyeUdyb3VwTWVtYmVyc1JlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSLw'
    'oRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEBEh0KCm1lbWJl'
    'cl9pZHMYAyADKANSCW1lbWJlcklkcxIkCgt3aXRoX3N0YXR1cxgEIAEoCEgBUgp3aXRoU3RhdH'
    'VziAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVS'
    'EGN1c3RvbUF0dHJpYnV0ZXNCFAoSX2xhc3RfdXBkYXRlZF9kYXRlQg4KDF93aXRoX3N0YXR1cw'
    '==');
