//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationships_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryRelationshipsRequestDescriptor instead')
const QueryRelationshipsRequest$json = {
  '1': 'QueryRelationshipsRequest',
  '2': [
    {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    {
      '1': 'blocked',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'blocked',
      '17': true
    },
    {'1': 'group_indexes', '3': 3, '4': 3, '5': 5, '10': 'groupIndexes'},
    {
      '1': 'last_updated_date',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {'1': 'name', '3': 5, '4': 1, '5': 9, '9': 2, '10': 'name', '17': true},
  ],
  '8': [
    {'1': '_blocked'},
    {'1': '_last_updated_date'},
    {'1': '_name'},
  ],
};

/// Descriptor for `QueryRelationshipsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryRelationshipsRequestDescriptor = $convert.base64Decode(
    'ChlRdWVyeVJlbGF0aW9uc2hpcHNSZXF1ZXN0EhkKCHVzZXJfaWRzGAEgAygDUgd1c2VySWRzEh'
    '0KB2Jsb2NrZWQYAiABKAhIAFIHYmxvY2tlZIgBARIjCg1ncm91cF9pbmRleGVzGAMgAygFUgxn'
    'cm91cEluZGV4ZXMSLwoRbGFzdF91cGRhdGVkX2RhdGUYBCABKANIAVIPbGFzdFVwZGF0ZWREYX'
    'RliAEBEhcKBG5hbWUYBSABKAlIAlIEbmFtZYgBAUIKCghfYmxvY2tlZEIUChJfbGFzdF91cGRh'
    'dGVkX2RhdGVCBwoFX25hbWU=');
