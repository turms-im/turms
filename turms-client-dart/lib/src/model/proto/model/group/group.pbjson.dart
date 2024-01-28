//
//  Generated code. Do not modify.
//  source: model/group/group.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupDescriptor instead')
const Group$json = {
  '1': 'Group',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {
      '1': 'type_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'typeId',
      '17': true
    },
    {
      '1': 'creator_id',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'creatorId',
      '17': true
    },
    {
      '1': 'owner_id',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'ownerId',
      '17': true
    },
    {'1': 'name', '3': 5, '4': 1, '5': 9, '9': 4, '10': 'name', '17': true},
    {'1': 'intro', '3': 6, '4': 1, '5': 9, '9': 5, '10': 'intro', '17': true},
    {
      '1': 'announcement',
      '3': 7,
      '4': 1,
      '5': 9,
      '9': 6,
      '10': 'announcement',
      '17': true
    },
    {
      '1': 'creation_date',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'creationDate',
      '17': true
    },
    {
      '1': 'last_updated_date',
      '3': 9,
      '4': 1,
      '5': 3,
      '9': 8,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 10,
      '4': 1,
      '5': 3,
      '9': 9,
      '10': 'muteEndDate',
      '17': true
    },
    {
      '1': 'active',
      '3': 11,
      '4': 1,
      '5': 8,
      '9': 10,
      '10': 'active',
      '17': true
    },
  ],
  '8': [
    {'1': '_id'},
    {'1': '_type_id'},
    {'1': '_creator_id'},
    {'1': '_owner_id'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_announcement'},
    {'1': '_creation_date'},
    {'1': '_last_updated_date'},
    {'1': '_mute_end_date'},
    {'1': '_active'},
  ],
};

/// Descriptor for `Group`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupDescriptor = $convert.base64Decode(
    'CgVHcm91cBITCgJpZBgBIAEoA0gAUgJpZIgBARIcCgd0eXBlX2lkGAIgASgDSAFSBnR5cGVJZI'
    'gBARIiCgpjcmVhdG9yX2lkGAMgASgDSAJSCWNyZWF0b3JJZIgBARIeCghvd25lcl9pZBgEIAEo'
    'A0gDUgdvd25lcklkiAEBEhcKBG5hbWUYBSABKAlIBFIEbmFtZYgBARIZCgVpbnRybxgGIAEoCU'
    'gFUgVpbnRyb4gBARInCgxhbm5vdW5jZW1lbnQYByABKAlIBlIMYW5ub3VuY2VtZW50iAEBEigK'
    'DWNyZWF0aW9uX2RhdGUYCCABKANIB1IMY3JlYXRpb25EYXRliAEBEi8KEWxhc3RfdXBkYXRlZF'
    '9kYXRlGAkgASgDSAhSD2xhc3RVcGRhdGVkRGF0ZYgBARInCg1tdXRlX2VuZF9kYXRlGAogASgD'
    'SAlSC211dGVFbmREYXRliAEBEhsKBmFjdGl2ZRgLIAEoCEgKUgZhY3RpdmWIAQFCBQoDX2lkQg'
    'oKCF90eXBlX2lkQg0KC19jcmVhdG9yX2lkQgsKCV9vd25lcl9pZEIHCgVfbmFtZUIICgZfaW50'
    'cm9CDwoNX2Fubm91bmNlbWVudEIQCg5fY3JlYXRpb25fZGF0ZUIUChJfbGFzdF91cGRhdGVkX2'
    'RhdGVCEAoOX211dGVfZW5kX2RhdGVCCQoHX2FjdGl2ZQ==');
