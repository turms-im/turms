///
//  Generated code. Do not modify.
//  source: request/group/member/query_group_members_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupMembersRequestDescriptor instead')
const QueryGroupMembersRequest$json = const {
  '1': 'QueryGroupMembersRequest',
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
    const {'1': 'member_ids', '3': 3, '4': 3, '5': 3, '10': 'memberIds'},
    const {
      '1': 'with_status',
      '3': 4,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'withStatus',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_last_updated_date'},
    const {'1': '_with_status'},
  ],
};

/// Descriptor for `QueryGroupMembersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupMembersRequestDescriptor =
    $convert.base64Decode(
        'ChhRdWVyeUdyb3VwTWVtYmVyc1JlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSLwoRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEBEh0KCm1lbWJlcl9pZHMYAyADKANSCW1lbWJlcklkcxIkCgt3aXRoX3N0YXR1cxgEIAEoCEgBUgp3aXRoU3RhdHVziAEBQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZUIOCgxfd2l0aF9zdGF0dXM=');
