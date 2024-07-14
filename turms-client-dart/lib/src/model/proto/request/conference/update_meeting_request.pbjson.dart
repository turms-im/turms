//
//  Generated code. Do not modify.
//  source: request/conference/update_meeting_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateMeetingRequestDescriptor instead')
const UpdateMeetingRequest$json = {
  '1': 'UpdateMeetingRequest',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'intro', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    {
      '1': 'password',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'password',
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
    {'1': '_id'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_password'},
  ],
};

/// Descriptor for `UpdateMeetingRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateMeetingRequestDescriptor = $convert.base64Decode(
    'ChRVcGRhdGVNZWV0aW5nUmVxdWVzdBITCgJpZBgBIAEoA0gAUgJpZIgBARIXCgRuYW1lGAIgAS'
    'gJSAFSBG5hbWWIAQESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESHwoIcGFzc3dvcmQYBCAB'
    'KAlIA1IIcGFzc3dvcmSIAQESQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy'
    '5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlc0IFCgNfaWRCBwoFX25hbWVCCAoGX2ludHJv'
    'QgsKCV9wYXNzd29yZA==');
