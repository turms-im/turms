///
//  Generated code. Do not modify.
//  source: request/user/relationship/create_relationship_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createRelationshipRequestDescriptor instead')
const CreateRelationshipRequest$json = const {
  '1': 'CreateRelationshipRequest',
  '2': const [
    const {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    const {'1': 'blocked', '3': 2, '4': 1, '5': 8, '10': 'blocked'},
    const {
      '1': 'group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'groupIndex',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_group_index'},
  ],
};

/// Descriptor for `CreateRelationshipRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createRelationshipRequestDescriptor =
    $convert.base64Decode(
        'ChlDcmVhdGVSZWxhdGlvbnNoaXBSZXF1ZXN0EhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBIYCgdibG9ja2VkGAIgASgIUgdibG9ja2VkEiQKC2dyb3VwX2luZGV4GAMgASgFSABSCmdyb3VwSW5kZXiIAQFCDgoMX2dyb3VwX2luZGV4');
