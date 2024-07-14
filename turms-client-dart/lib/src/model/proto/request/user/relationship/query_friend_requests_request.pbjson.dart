//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_friend_requests_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryFriendRequestsRequestDescriptor instead')
const QueryFriendRequestsRequest$json = {
  '1': 'QueryFriendRequestsRequest',
  '2': [
    {'1': 'are_sent_by_me', '3': 1, '4': 1, '5': 8, '10': 'areSentByMe'},
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

/// Descriptor for `QueryFriendRequestsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryFriendRequestsRequestDescriptor = $convert.base64Decode(
    'ChpRdWVyeUZyaWVuZFJlcXVlc3RzUmVxdWVzdBIjCg5hcmVfc2VudF9ieV9tZRgBIAEoCFILYX'
    'JlU2VudEJ5TWUSLwoRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRl'
    'iAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEG'
    'N1c3RvbUF0dHJpYnV0ZXNCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
