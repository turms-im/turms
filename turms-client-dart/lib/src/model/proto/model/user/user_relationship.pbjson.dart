///
//  Generated code. Do not modify.
//  source: model/user/user_relationship.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userRelationshipDescriptor instead')
const UserRelationship$json = const {
  '1': 'UserRelationship',
  '2': const [
    const {
      '1': 'owner_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'ownerId',
      '17': true
    },
    const {
      '1': 'related_user_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'relatedUserId',
      '17': true
    },
    const {
      '1': 'block_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'blockDate',
      '17': true
    },
    const {
      '1': 'group_index',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'groupIndex',
      '17': true
    },
    const {
      '1': 'establishment_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'establishmentDate',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_owner_id'},
    const {'1': '_related_user_id'},
    const {'1': '_block_date'},
    const {'1': '_group_index'},
    const {'1': '_establishment_date'},
  ],
};

/// Descriptor for `UserRelationship`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userRelationshipDescriptor = $convert.base64Decode(
    'ChBVc2VyUmVsYXRpb25zaGlwEh4KCG93bmVyX2lkGAEgASgDSABSB293bmVySWSIAQESKwoPcmVsYXRlZF91c2VyX2lkGAIgASgDSAFSDXJlbGF0ZWRVc2VySWSIAQESIgoKYmxvY2tfZGF0ZRgDIAEoA0gCUglibG9ja0RhdGWIAQESJAoLZ3JvdXBfaW5kZXgYBCABKANIA1IKZ3JvdXBJbmRleIgBARIyChJlc3RhYmxpc2htZW50X2RhdGUYBSABKANIBFIRZXN0YWJsaXNobWVudERhdGWIAQFCCwoJX293bmVyX2lkQhIKEF9yZWxhdGVkX3VzZXJfaWRCDQoLX2Jsb2NrX2RhdGVCDgoMX2dyb3VwX2luZGV4QhUKE19lc3RhYmxpc2htZW50X2RhdGU=');
