//
//  Generated code. Do not modify.
//  source: request/group/enrollment/update_group_invitation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateGroupInvitationRequestDescriptor instead')
const UpdateGroupInvitationRequest$json = {
  '1': 'UpdateGroupInvitationRequest',
  '2': [
    {'1': 'invitation_id', '3': 1, '4': 1, '5': 3, '10': 'invitationId'},
    {
      '1': 'response_action',
      '3': 2,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.ResponseAction',
      '10': 'responseAction'
    },
    {'1': 'reason', '3': 3, '4': 1, '5': 9, '9': 0, '10': 'reason', '17': true},
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
    {'1': '_reason'},
  ],
};

/// Descriptor for `UpdateGroupInvitationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateGroupInvitationRequestDescriptor = $convert.base64Decode(
    'ChxVcGRhdGVHcm91cEludml0YXRpb25SZXF1ZXN0EiMKDWludml0YXRpb25faWQYASABKANSDG'
    'ludml0YXRpb25JZBJHCg9yZXNwb25zZV9hY3Rpb24YAiABKA4yHi5pbS50dXJtcy5wcm90by5S'
    'ZXNwb25zZUFjdGlvblIOcmVzcG9uc2VBY3Rpb24SGwoGcmVhc29uGAMgASgJSABSBnJlYXNvbo'
    'gBARJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBj'
    'dXN0b21BdHRyaWJ1dGVzQgkKB19yZWFzb24=');
