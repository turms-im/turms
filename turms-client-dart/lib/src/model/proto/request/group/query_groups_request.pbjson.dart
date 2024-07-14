//
//  Generated code. Do not modify.
//  source: request/group/query_groups_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupsRequestDescriptor instead')
const QueryGroupsRequest$json = {
  '1': 'QueryGroupsRequest',
  '2': [
    {'1': 'group_ids', '3': 1, '4': 3, '5': 3, '10': 'groupIds'},
    {
      '1': 'last_updated_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'skip', '3': 10, '4': 1, '5': 5, '9': 2, '10': 'skip', '17': true},
    {'1': 'limit', '3': 11, '4': 1, '5': 5, '9': 3, '10': 'limit', '17': true},
    {
      '1': 'fields_to_highlight',
      '3': 12,
      '4': 3,
      '5': 5,
      '10': 'fieldsToHighlight'
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
    {'1': '_name'},
    {'1': '_skip'},
    {'1': '_limit'},
  ],
};

/// Descriptor for `QueryGroupsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupsRequestDescriptor = $convert.base64Decode(
    'ChJRdWVyeUdyb3Vwc1JlcXVlc3QSGwoJZ3JvdXBfaWRzGAEgAygDUghncm91cElkcxIvChFsYX'
    'N0X3VwZGF0ZWRfZGF0ZRgCIAEoA0gAUg9sYXN0VXBkYXRlZERhdGWIAQESFwoEbmFtZRgDIAEo'
    'CUgBUgRuYW1liAEBEhcKBHNraXAYCiABKAVIAlIEc2tpcIgBARIZCgVsaW1pdBgLIAEoBUgDUg'
    'VsaW1pdIgBARIuChNmaWVsZHNfdG9faGlnaGxpZ2h0GAwgAygFUhFmaWVsZHNUb0hpZ2hsaWdo'
    'dBJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdX'
    'N0b21BdHRyaWJ1dGVzQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZUIHCgVfbmFtZUIHCgVfc2tpcEII'
    'CgZfbGltaXQ=');
