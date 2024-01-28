//
//  Generated code. Do not modify.
//  source: request/group/enrollment/query_group_join_requests_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupJoinRequestsRequestDescriptor instead')
const QueryGroupJoinRequestsRequest$json = {
  '1': 'QueryGroupJoinRequestsRequest',
  '2': [
    {
      '1': 'group_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'last_updated_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'lastUpdatedDate',
      '17': true
    },
  ],
  '8': [
    {'1': '_group_id'},
    {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryGroupJoinRequestsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupJoinRequestsRequestDescriptor =
    $convert.base64Decode(
        'Ch1RdWVyeUdyb3VwSm9pblJlcXVlc3RzUmVxdWVzdBIeCghncm91cF9pZBgBIAEoA0gAUgdncm'
        '91cElkiAEBEi8KEWxhc3RfdXBkYXRlZF9kYXRlGAIgASgDSAFSD2xhc3RVcGRhdGVkRGF0ZYgB'
        'AUILCglfZ3JvdXBfaWRCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
