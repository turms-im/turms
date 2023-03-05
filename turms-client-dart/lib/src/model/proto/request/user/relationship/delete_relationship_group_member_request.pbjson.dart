///
//  Generated code. Do not modify.
//  source: request/user/relationship/delete_relationship_group_member_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteRelationshipGroupMemberRequestDescriptor instead')
const DeleteRelationshipGroupMemberRequest$json = const {
  '1': 'DeleteRelationshipGroupMemberRequest',
  '2': const [
    const {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    const {'1': 'group_index', '3': 2, '4': 1, '5': 5, '10': 'groupIndex'},
    const {
      '1': 'target_group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'targetGroupIndex',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_target_group_index'},
  ],
};

/// Descriptor for `DeleteRelationshipGroupMemberRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteRelationshipGroupMemberRequestDescriptor =
    $convert.base64Decode(
        'CiREZWxldGVSZWxhdGlvbnNoaXBHcm91cE1lbWJlclJlcXVlc3QSFwoHdXNlcl9pZBgBIAEoA1IGdXNlcklkEh8KC2dyb3VwX2luZGV4GAIgASgFUgpncm91cEluZGV4EjEKEnRhcmdldF9ncm91cF9pbmRleBgDIAEoBUgAUhB0YXJnZXRHcm91cEluZGV4iAEBQhUKE190YXJnZXRfZ3JvdXBfaW5kZXg=');
