//
//  Generated code. Do not modify.
//  source: model/conference/meeting.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use meetingDescriptor instead')
const Meeting$json = {
  '1': 'Meeting',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '10': 'id'},
    {
      '1': 'user_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'userId',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
    {'1': 'creator_id', '3': 4, '4': 1, '5': 3, '10': 'creatorId'},
    {
      '1': 'access_token',
      '3': 5,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'accessToken',
      '17': true
    },
    {'1': 'name', '3': 6, '4': 1, '5': 9, '9': 3, '10': 'name', '17': true},
    {'1': 'intro', '3': 7, '4': 1, '5': 9, '9': 4, '10': 'intro', '17': true},
    {
      '1': 'password',
      '3': 8,
      '4': 1,
      '5': 9,
      '9': 5,
      '10': 'password',
      '17': true
    },
    {'1': 'start_date', '3': 9, '4': 1, '5': 3, '10': 'startDate'},
    {
      '1': 'end_date',
      '3': 10,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'endDate',
      '17': true
    },
    {
      '1': 'cancel_date',
      '3': 11,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'cancelDate',
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
    {'1': '_access_token'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_password'},
    {'1': '_end_date'},
    {'1': '_cancel_date'},
  ],
};

/// Descriptor for `Meeting`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List meetingDescriptor = $convert.base64Decode(
    'CgdNZWV0aW5nEg4KAmlkGAEgASgDUgJpZBIcCgd1c2VyX2lkGAIgASgDSABSBnVzZXJJZIgBAR'
    'IeCghncm91cF9pZBgDIAEoA0gBUgdncm91cElkiAEBEh0KCmNyZWF0b3JfaWQYBCABKANSCWNy'
    'ZWF0b3JJZBImCgxhY2Nlc3NfdG9rZW4YBSABKAlIAlILYWNjZXNzVG9rZW6IAQESFwoEbmFtZR'
    'gGIAEoCUgDUgRuYW1liAEBEhkKBWludHJvGAcgASgJSARSBWludHJviAEBEh8KCHBhc3N3b3Jk'
    'GAggASgJSAVSCHBhc3N3b3JkiAEBEh0KCnN0YXJ0X2RhdGUYCSABKANSCXN0YXJ0RGF0ZRIeCg'
    'hlbmRfZGF0ZRgKIAEoA0gGUgdlbmREYXRliAEBEiQKC2NhbmNlbF9kYXRlGAsgASgDSAdSCmNh'
    'bmNlbERhdGWIAQESQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by'
    '5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlc0IKCghfdXNlcl9pZEILCglfZ3JvdXBfaWRCDwoNX2Fj'
    'Y2Vzc190b2tlbkIHCgVfbmFtZUIICgZfaW50cm9CCwoJX3Bhc3N3b3JkQgsKCV9lbmRfZGF0ZU'
    'IOCgxfY2FuY2VsX2RhdGU=');
