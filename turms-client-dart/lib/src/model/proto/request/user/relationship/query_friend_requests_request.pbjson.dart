///
//  Generated code. Do not modify.
//  source: request/user/relationship/query_friend_requests_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryFriendRequestsRequestDescriptor instead')
const QueryFriendRequestsRequest$json = const {
  '1': 'QueryFriendRequestsRequest',
  '2': const [
    const {'1': 'are_sent_by_me', '3': 1, '4': 1, '5': 8, '10': 'areSentByMe'},
    const {
      '1': 'last_updated_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDate',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryFriendRequestsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryFriendRequestsRequestDescriptor =
    $convert.base64Decode(
        'ChpRdWVyeUZyaWVuZFJlcXVlc3RzUmVxdWVzdBIjCg5hcmVfc2VudF9ieV9tZRgBIAEoCFILYXJlU2VudEJ5TWUSLwoRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEBQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZQ==');
