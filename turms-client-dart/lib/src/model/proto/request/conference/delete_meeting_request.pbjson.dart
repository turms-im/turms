//
//  Generated code. Do not modify.
//  source: request/conference/delete_meeting_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteMeetingRequestDescriptor instead')
const DeleteMeetingRequest$json = {
  '1': 'DeleteMeetingRequest',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
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
  ],
};

/// Descriptor for `DeleteMeetingRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteMeetingRequestDescriptor = $convert.base64Decode(
    'ChREZWxldGVNZWV0aW5nUmVxdWVzdBITCgJpZBgBIAEoA0gAUgJpZIgBARJCChFjdXN0b21fYX'
    'R0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVz'
    'QgUKA19pZA==');
