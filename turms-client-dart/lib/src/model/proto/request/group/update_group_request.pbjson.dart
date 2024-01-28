//
//  Generated code. Do not modify.
//  source: request/group/update_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupRequestDescriptor instead')
const UpdateGroupRequest$json = {
  '1': 'UpdateGroupRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {
      '1': 'quit_after_transfer',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'quitAfterTransfer',
      '17': true
    },
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'intro', '3': 4, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    {
      '1': 'announcement',
      '3': 5,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'announcement',
      '17': true
    },
    {
      '1': 'min_score',
      '3': 6,
      '4': 1,
      '5': 5,
      '9': 4,
      '10': 'minScore',
      '17': true
    },
    {
      '1': 'type_id',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'typeId',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'muteEndDate',
      '17': true
    },
    {
      '1': 'successor_id',
      '3': 9,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'successorId',
      '17': true
    },
  ],
  '8': [
    {'1': '_quit_after_transfer'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_announcement'},
    {'1': '_min_score'},
    {'1': '_type_id'},
    {'1': '_mute_end_date'},
    {'1': '_successor_id'},
  ],
};

/// Descriptor for `UpdateGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupRequestDescriptor = $convert.base64Decode(
    'ChJVcGRhdGVHcm91cFJlcXVlc3QSGQoIZ3JvdXBfaWQYASABKANSB2dyb3VwSWQSMwoTcXVpdF'
    '9hZnRlcl90cmFuc2ZlchgCIAEoCEgAUhFxdWl0QWZ0ZXJUcmFuc2ZlcogBARIXCgRuYW1lGAMg'
    'ASgJSAFSBG5hbWWIAQESGQoFaW50cm8YBCABKAlIAlIFaW50cm+IAQESJwoMYW5ub3VuY2VtZW'
    '50GAUgASgJSANSDGFubm91bmNlbWVudIgBARIgCgltaW5fc2NvcmUYBiABKAVIBFIIbWluU2Nv'
    'cmWIAQESHAoHdHlwZV9pZBgHIAEoA0gFUgZ0eXBlSWSIAQESJwoNbXV0ZV9lbmRfZGF0ZRgIIA'
    'EoA0gGUgttdXRlRW5kRGF0ZYgBARImCgxzdWNjZXNzb3JfaWQYCSABKANIB1ILc3VjY2Vzc29y'
    'SWSIAQFCFgoUX3F1aXRfYWZ0ZXJfdHJhbnNmZXJCBwoFX25hbWVCCAoGX2ludHJvQg8KDV9hbm'
    '5vdW5jZW1lbnRCDAoKX21pbl9zY29yZUIKCghfdHlwZV9pZEIQCg5fbXV0ZV9lbmRfZGF0ZUIP'
    'Cg1fc3VjY2Vzc29yX2lk');
