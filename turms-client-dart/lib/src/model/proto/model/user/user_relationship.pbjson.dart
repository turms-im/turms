//
//  Generated code. Do not modify.
//  source: model/user/user_relationship.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userRelationshipDescriptor instead')
const UserRelationship$json = {
  '1': 'UserRelationship',
  '2': [
    {
      '1': 'owner_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'ownerId',
      '17': true
    },
    {
      '1': 'related_user_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'relatedUserId',
      '17': true
    },
    {
      '1': 'block_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'blockDate',
      '17': true
    },
    {
      '1': 'group_index',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'groupIndex',
      '17': true
    },
    {
      '1': 'establishment_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'establishmentDate',
      '17': true
    },
    {'1': 'name', '3': 6, '4': 1, '5': 9, '9': 5, '10': 'name', '17': true},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
  '8': [
    {'1': '_owner_id'},
    {'1': '_related_user_id'},
    {'1': '_block_date'},
    {'1': '_group_index'},
    {'1': '_establishment_date'},
    {'1': '_name'},
  ],
};

/// Descriptor for `UserRelationship`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userRelationshipDescriptor = $convert.base64Decode(
    'ChBVc2VyUmVsYXRpb25zaGlwEh4KCG93bmVyX2lkGAEgASgDSABSB293bmVySWSIAQESKwoPcm'
    'VsYXRlZF91c2VyX2lkGAIgASgDSAFSDXJlbGF0ZWRVc2VySWSIAQESIgoKYmxvY2tfZGF0ZRgD'
    'IAEoA0gCUglibG9ja0RhdGWIAQESJAoLZ3JvdXBfaW5kZXgYBCABKANIA1IKZ3JvdXBJbmRleI'
    'gBARIyChJlc3RhYmxpc2htZW50X2RhdGUYBSABKANIBFIRZXN0YWJsaXNobWVudERhdGWIAQES'
    'FwoEbmFtZRgGIAEoCUgFUgRuYW1liAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW'
    '0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXNCCwoJX293bmVyX2lkQhIKEF9y'
    'ZWxhdGVkX3VzZXJfaWRCDQoLX2Jsb2NrX2RhdGVCDgoMX2dyb3VwX2luZGV4QhUKE19lc3RhYm'
    'xpc2htZW50X2RhdGVCBwoFX25hbWU=');
