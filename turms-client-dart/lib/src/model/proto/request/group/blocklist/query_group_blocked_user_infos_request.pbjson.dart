//
//  Generated code. Do not modify.
//  source: request/group/blocklist/query_group_blocked_user_infos_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupBlockedUserInfosRequestDescriptor instead')
const QueryGroupBlockedUserInfosRequest$json = {
  '1': 'QueryGroupBlockedUserInfosRequest',
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
  ],
};

/// Descriptor for `QueryGroupBlockedUserInfosRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupBlockedUserInfosRequestDescriptor =
    $convert.base64Decode(
        'CiFRdWVyeUdyb3VwQmxvY2tlZFVzZXJJbmZvc1JlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2'
        'dyb3VwSWQSLwoRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEB'
        'EkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3'
        'RvbUF0dHJpYnV0ZXNCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
