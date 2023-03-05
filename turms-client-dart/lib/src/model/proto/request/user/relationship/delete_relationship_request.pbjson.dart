///
//  Generated code. Do not modify.
//  source: request/user/relationship/delete_relationship_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteRelationshipRequestDescriptor instead')
const DeleteRelationshipRequest$json = const {
  '1': 'DeleteRelationshipRequest',
  '2': const [
    const {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    const {
      '1': 'group_index',
      '3': 2,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'groupIndex',
      '17': true
    },
    const {
      '1': 'target_group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'targetGroupIndex',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_group_index'},
    const {'1': '_target_group_index'},
  ],
};

/// Descriptor for `DeleteRelationshipRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteRelationshipRequestDescriptor =
    $convert.base64Decode(
        'ChlEZWxldGVSZWxhdGlvbnNoaXBSZXF1ZXN0EhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBIkCgtncm91cF9pbmRleBgCIAEoBUgAUgpncm91cEluZGV4iAEBEjEKEnRhcmdldF9ncm91cF9pbmRleBgDIAEoBUgBUhB0YXJnZXRHcm91cEluZGV4iAEBQg4KDF9ncm91cF9pbmRleEIVChNfdGFyZ2V0X2dyb3VwX2luZGV4');
