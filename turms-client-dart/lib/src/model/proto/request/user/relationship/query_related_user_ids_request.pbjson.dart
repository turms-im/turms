//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_related_user_ids_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryRelatedUserIdsRequestDescriptor instead')
const QueryRelatedUserIdsRequest$json = {
  '1': 'QueryRelatedUserIdsRequest',
  '2': [
    {
      '1': 'blocked',
      '3': 1,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'blocked',
      '17': true
    },
    {'1': 'group_indexes', '3': 2, '4': 3, '5': 5, '10': 'groupIndexes'},
    {
      '1': 'last_updated_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 1,
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
    {'1': '_blocked'},
    {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryRelatedUserIdsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryRelatedUserIdsRequestDescriptor = $convert.base64Decode(
    'ChpRdWVyeVJlbGF0ZWRVc2VySWRzUmVxdWVzdBIdCgdibG9ja2VkGAEgASgISABSB2Jsb2NrZW'
    'SIAQESIwoNZ3JvdXBfaW5kZXhlcxgCIAMoBVIMZ3JvdXBJbmRleGVzEi8KEWxhc3RfdXBkYXRl'
    'ZF9kYXRlGAMgASgDSAFSD2xhc3RVcGRhdGVkRGF0ZYgBARJCChFjdXN0b21fYXR0cmlidXRlcx'
    'gPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzQgoKCF9ibG9j'
    'a2VkQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZQ==');
