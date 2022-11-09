///
//  Generated code. Do not modify.
//  source: request/group/create_group_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupRequestDescriptor instead')
const CreateGroupRequest$json = const {
  '1': 'CreateGroupRequest',
  '2': const [
    const {'1': 'name', '3': 1, '4': 1, '5': 9, '10': 'name'},
    const {
      '1': 'intro',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'intro',
      '17': true
    },
    const {
      '1': 'announcement',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'announcement',
      '17': true
    },
    const {
      '1': 'minimum_score',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'minimumScore',
      '17': true
    },
    const {
      '1': 'group_type_id',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'groupTypeId',
      '17': true
    },
    const {
      '1': 'mute_end_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'muteEndDate',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_intro'},
    const {'1': '_announcement'},
    const {'1': '_minimum_score'},
    const {'1': '_group_type_id'},
    const {'1': '_mute_end_date'},
  ],
};

/// Descriptor for `CreateGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupRequestDescriptor = $convert.base64Decode(
    'ChJDcmVhdGVHcm91cFJlcXVlc3QSEgoEbmFtZRgBIAEoCVIEbmFtZRIZCgVpbnRybxgCIAEoCUgAUgVpbnRyb4gBARInCgxhbm5vdW5jZW1lbnQYAyABKAlIAVIMYW5ub3VuY2VtZW50iAEBEigKDW1pbmltdW1fc2NvcmUYBCABKAVIAlIMbWluaW11bVNjb3JliAEBEicKDWdyb3VwX3R5cGVfaWQYBSABKANIA1ILZ3JvdXBUeXBlSWSIAQESJwoNbXV0ZV9lbmRfZGF0ZRgGIAEoA0gEUgttdXRlRW5kRGF0ZYgBAUIICgZfaW50cm9CDwoNX2Fubm91bmNlbWVudEIQCg5fbWluaW11bV9zY29yZUIQCg5fZ3JvdXBfdHlwZV9pZEIQCg5fbXV0ZV9lbmRfZGF0ZQ==');
