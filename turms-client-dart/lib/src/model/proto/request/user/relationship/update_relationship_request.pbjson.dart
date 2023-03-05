///
//  Generated code. Do not modify.
//  source: request/user/relationship/update_relationship_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateRelationshipRequestDescriptor instead')
const UpdateRelationshipRequest$json = const {
  '1': 'UpdateRelationshipRequest',
  '2': const [
    const {'1': 'user_id', '3': 1, '4': 1, '5': 3, '10': 'userId'},
    const {
      '1': 'blocked',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'blocked',
      '17': true
    },
    const {
      '1': 'new_group_index',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'newGroupIndex',
      '17': true
    },
    const {
      '1': 'delete_group_index',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'deleteGroupIndex',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_blocked'},
    const {'1': '_new_group_index'},
    const {'1': '_delete_group_index'},
  ],
};

/// Descriptor for `UpdateRelationshipRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateRelationshipRequestDescriptor =
    $convert.base64Decode(
        'ChlVcGRhdGVSZWxhdGlvbnNoaXBSZXF1ZXN0EhcKB3VzZXJfaWQYASABKANSBnVzZXJJZBIdCgdibG9ja2VkGAIgASgISABSB2Jsb2NrZWSIAQESKwoPbmV3X2dyb3VwX2luZGV4GAMgASgFSAFSDW5ld0dyb3VwSW5kZXiIAQESMQoSZGVsZXRlX2dyb3VwX2luZGV4GAQgASgFSAJSEGRlbGV0ZUdyb3VwSW5kZXiIAQFCCgoIX2Jsb2NrZWRCEgoQX25ld19ncm91cF9pbmRleEIVChNfZGVsZXRlX2dyb3VwX2luZGV4');
