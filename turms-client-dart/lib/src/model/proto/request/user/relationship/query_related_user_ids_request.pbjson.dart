///
//  Generated code. Do not modify.
//  source: request/user/relationship/query_related_user_ids_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryRelatedUserIdsRequestDescriptor instead')
const QueryRelatedUserIdsRequest$json = const {
  '1': 'QueryRelatedUserIdsRequest',
  '2': const [
    const {
      '1': 'blocked',
      '3': 1,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'blocked',
      '17': true
    },
    const {'1': 'group_indexes', '3': 2, '4': 3, '5': 5, '10': 'groupIndexes'},
    const {
      '1': 'last_updated_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'lastUpdatedDate',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_blocked'},
    const {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryRelatedUserIdsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryRelatedUserIdsRequestDescriptor =
    $convert.base64Decode(
        'ChpRdWVyeVJlbGF0ZWRVc2VySWRzUmVxdWVzdBIdCgdibG9ja2VkGAEgASgISABSB2Jsb2NrZWSIAQESIwoNZ3JvdXBfaW5kZXhlcxgCIAMoBVIMZ3JvdXBJbmRleGVzEi8KEWxhc3RfdXBkYXRlZF9kYXRlGAMgASgDSAFSD2xhc3RVcGRhdGVkRGF0ZYgBAUIKCghfYmxvY2tlZEIUChJfbGFzdF91cGRhdGVkX2RhdGU=');
