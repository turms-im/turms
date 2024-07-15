//
//  Generated code. Do not modify.
//  source: request/group/enrollment/delete_group_invitation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteGroupInvitationRequestDescriptor instead')
const DeleteGroupInvitationRequest$json = {
  '1': 'DeleteGroupInvitationRequest',
  '2': [
    {'1': 'invitation_id', '3': 1, '4': 1, '5': 3, '10': 'invitationId'},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
};

/// Descriptor for `DeleteGroupInvitationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteGroupInvitationRequestDescriptor =
    $convert.base64Decode(
        'ChxEZWxldGVHcm91cEludml0YXRpb25SZXF1ZXN0EiMKDWludml0YXRpb25faWQYASABKANSDG'
        'ludml0YXRpb25JZBJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3Rv'
        'LlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVz');
