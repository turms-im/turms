//
//  Generated code. Do not modify.
//  source: request/group/create_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupRequestDescriptor instead')
const CreateGroupRequest$json = {
  '1': 'CreateGroupRequest',
  '2': [
    {'1': 'name', '3': 1, '4': 1, '5': 9, '10': 'name'},
    {'1': 'intro', '3': 2, '4': 1, '5': 9, '9': 0, '10': 'intro', '17': true},
    {
      '1': 'announcement',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'announcement',
      '17': true
    },
    {
      '1': 'min_score',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'minScore',
      '17': true
    },
    {
      '1': 'type_id',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'typeId',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'muteEndDate',
      '17': true
    },
  ],
  '8': [
    {'1': '_intro'},
    {'1': '_announcement'},
    {'1': '_min_score'},
    {'1': '_type_id'},
    {'1': '_mute_end_date'},
  ],
};

/// Descriptor for `CreateGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupRequestDescriptor = $convert.base64Decode(
    'ChJDcmVhdGVHcm91cFJlcXVlc3QSEgoEbmFtZRgBIAEoCVIEbmFtZRIZCgVpbnRybxgCIAEoCU'
    'gAUgVpbnRyb4gBARInCgxhbm5vdW5jZW1lbnQYAyABKAlIAVIMYW5ub3VuY2VtZW50iAEBEiAK'
    'CW1pbl9zY29yZRgEIAEoBUgCUghtaW5TY29yZYgBARIcCgd0eXBlX2lkGAUgASgDSANSBnR5cG'
    'VJZIgBARInCg1tdXRlX2VuZF9kYXRlGAYgASgDSARSC211dGVFbmREYXRliAEBQggKBl9pbnRy'
    'b0IPCg1fYW5ub3VuY2VtZW50QgwKCl9taW5fc2NvcmVCCgoIX3R5cGVfaWRCEAoOX211dGVfZW'
    '5kX2RhdGU=');
