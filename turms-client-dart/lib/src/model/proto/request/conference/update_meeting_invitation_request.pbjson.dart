//
//  Generated code. Do not modify.
//  source: request/conference/update_meeting_invitation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateMeetingInvitationRequestDescriptor instead')
const UpdateMeetingInvitationRequest$json = {
  '1': 'UpdateMeetingInvitationRequest',
  '2': [
    {'1': 'meeting_id', '3': 1, '4': 1, '5': 3, '10': 'meetingId'},
    {
      '1': 'password',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'password',
      '17': true
    },
    {
      '1': 'response_action',
      '3': 5,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.ResponseAction',
      '10': 'responseAction'
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
    {'1': '_password'},
  ],
};

/// Descriptor for `UpdateMeetingInvitationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateMeetingInvitationRequestDescriptor = $convert.base64Decode(
    'Ch5VcGRhdGVNZWV0aW5nSW52aXRhdGlvblJlcXVlc3QSHQoKbWVldGluZ19pZBgBIAEoA1IJbW'
    'VldGluZ0lkEh8KCHBhc3N3b3JkGAIgASgJSABSCHBhc3N3b3JkiAEBEkcKD3Jlc3BvbnNlX2Fj'
    'dGlvbhgFIAEoDjIeLmltLnR1cm1zLnByb3RvLlJlc3BvbnNlQWN0aW9uUg5yZXNwb25zZUFjdG'
    'lvbhJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBj'
    'dXN0b21BdHRyaWJ1dGVzQgsKCV9wYXNzd29yZA==');
