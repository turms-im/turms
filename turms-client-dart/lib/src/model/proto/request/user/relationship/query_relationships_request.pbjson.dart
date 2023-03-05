///
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationships_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryRelationshipsRequestDescriptor instead')
const QueryRelationshipsRequest$json = const {
  '1': 'QueryRelationshipsRequest',
  '2': const [
    const {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    const {
      '1': 'blocked',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'blocked',
      '17': true
    },
    const {'1': 'group_indexes', '3': 3, '4': 3, '5': 5, '10': 'groupIndexes'},
    const {
      '1': 'last_updated_date',
      '3': 4,
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

/// Descriptor for `QueryRelationshipsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryRelationshipsRequestDescriptor =
    $convert.base64Decode(
        'ChlRdWVyeVJlbGF0aW9uc2hpcHNSZXF1ZXN0EhkKCHVzZXJfaWRzGAEgAygDUgd1c2VySWRzEh0KB2Jsb2NrZWQYAiABKAhIAFIHYmxvY2tlZIgBARIjCg1ncm91cF9pbmRleGVzGAMgAygFUgxncm91cEluZGV4ZXMSLwoRbGFzdF91cGRhdGVkX2RhdGUYBCABKANIAVIPbGFzdFVwZGF0ZWREYXRliAEBQgoKCF9ibG9ja2VkQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZQ==');
