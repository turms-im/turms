//
//  Generated code. Do not modify.
//  source: request/group/enrollment/create_group_invitation_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupInvitationRequestDescriptor instead')
const CreateGroupInvitationRequest$json = {
  '1': 'CreateGroupInvitationRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'invitee_id', '3': 2, '4': 1, '5': 3, '10': 'inviteeId'},
    {'1': 'content', '3': 3, '4': 1, '5': 9, '10': 'content'},
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

/// Descriptor for `CreateGroupInvitationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupInvitationRequestDescriptor = $convert.base64Decode(
    'ChxDcmVhdGVHcm91cEludml0YXRpb25SZXF1ZXN0EhkKCGdyb3VwX2lkGAEgASgDUgdncm91cE'
    'lkEh0KCmludml0ZWVfaWQYAiABKANSCWludml0ZWVJZBIYCgdjb250ZW50GAMgASgJUgdjb250'
    'ZW50EkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEG'
    'N1c3RvbUF0dHJpYnV0ZXM=');
