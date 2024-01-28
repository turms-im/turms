//
//  Generated code. Do not modify.
//  source: request/user/relationship/delete_relationship_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteRelationshipGroupRequestDescriptor instead')
const DeleteRelationshipGroupRequest$json = {
  '1': 'DeleteRelationshipGroupRequest',
  '2': [
    {'1': 'group_index', '3': 1, '4': 1, '5': 5, '10': 'groupIndex'},
    {
      '1': 'target_group_index',
      '3': 2,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'targetGroupIndex',
      '17': true
    },
  ],
  '8': [
    {'1': '_target_group_index'},
  ],
};

/// Descriptor for `DeleteRelationshipGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteRelationshipGroupRequestDescriptor =
    $convert.base64Decode(
        'Ch5EZWxldGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3QSHwoLZ3JvdXBfaW5kZXgYASABKAVSCm'
        'dyb3VwSW5kZXgSMQoSdGFyZ2V0X2dyb3VwX2luZGV4GAIgASgFSABSEHRhcmdldEdyb3VwSW5k'
        'ZXiIAQFCFQoTX3RhcmdldF9ncm91cF9pbmRleA==');
