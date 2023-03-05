///
//  Generated code. Do not modify.
//  source: request/group/blocklist/query_group_blocked_user_infos_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupBlockedUserInfosRequestDescriptor instead')
const QueryGroupBlockedUserInfosRequest$json = const {
  '1': 'QueryGroupBlockedUserInfosRequest',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
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

/// Descriptor for `QueryGroupBlockedUserInfosRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupBlockedUserInfosRequestDescriptor =
    $convert.base64Decode(
        'CiFRdWVyeUdyb3VwQmxvY2tlZFVzZXJJbmZvc1JlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSLwoRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEBQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZQ==');
