//
//  Generated code. Do not modify.
//  source: request/group/blocklist/query_group_blocked_user_ids_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupBlockedUserIdsRequestDescriptor instead')
const QueryGroupBlockedUserIdsRequest$json = {
  '1': 'QueryGroupBlockedUserIdsRequest',
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
  ],
  '8': [
    {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryGroupBlockedUserIdsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupBlockedUserIdsRequestDescriptor =
    $convert.base64Decode(
        'Ch9RdWVyeUdyb3VwQmxvY2tlZFVzZXJJZHNSZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm'
        '91cElkEi8KEWxhc3RfdXBkYXRlZF9kYXRlGAIgASgDSABSD2xhc3RVcGRhdGVkRGF0ZYgBAUIU'
        'ChJfbGFzdF91cGRhdGVkX2RhdGU=');
