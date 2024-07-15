//
//  Generated code. Do not modify.
//  source: request/conference/create_meeting_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createMeetingRequestDescriptor instead')
const CreateMeetingRequest$json = {
  '1': 'CreateMeetingRequest',
  '2': [
    {
      '1': 'user_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'userId',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'name', '17': true},
    {'1': 'intro', '3': 4, '4': 1, '5': 9, '9': 3, '10': 'intro', '17': true},
    {
      '1': 'password',
      '3': 5,
      '4': 1,
      '5': 9,
      '9': 4,
      '10': 'password',
      '17': true
    },
    {
      '1': 'start_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'startDate',
      '17': true
    },
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
    {'1': '_user_id'},
    {'1': '_group_id'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_password'},
    {'1': '_start_date'},
  ],
};

/// Descriptor for `CreateMeetingRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createMeetingRequestDescriptor = $convert.base64Decode(
    'ChRDcmVhdGVNZWV0aW5nUmVxdWVzdBIcCgd1c2VyX2lkGAEgASgDSABSBnVzZXJJZIgBARIeCg'
    'hncm91cF9pZBgCIAEoA0gBUgdncm91cElkiAEBEhcKBG5hbWUYAyABKAlIAlIEbmFtZYgBARIZ'
    'CgVpbnRybxgEIAEoCUgDUgVpbnRyb4gBARIfCghwYXNzd29yZBgFIAEoCUgEUghwYXNzd29yZI'
    'gBARIiCgpzdGFydF9kYXRlGAYgASgDSAVSCXN0YXJ0RGF0ZYgBARJCChFjdXN0b21fYXR0cmli'
    'dXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzQgoKCF'
    '91c2VyX2lkQgsKCV9ncm91cF9pZEIHCgVfbmFtZUIICgZfaW50cm9CCwoJX3Bhc3N3b3JkQg0K'
    'C19zdGFydF9kYXRl');
