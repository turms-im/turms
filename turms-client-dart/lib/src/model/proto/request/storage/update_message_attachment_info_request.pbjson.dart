//
//  Generated code. Do not modify.
//  source: request/storage/update_message_attachment_info_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateMessageAttachmentInfoRequestDescriptor instead')
const UpdateMessageAttachmentInfoRequest$json = {
  '1': 'UpdateMessageAttachmentInfoRequest',
  '2': [
    {
      '1': 'attachment_id_num',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'attachmentIdNum',
      '17': true
    },
    {
      '1': 'attachment_id_str',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'attachmentIdStr',
      '17': true
    },
    {
      '1': 'user_id_to_share_with',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'userIdToShareWith',
      '17': true
    },
    {
      '1': 'user_id_to_unshare_with',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'userIdToUnshareWith',
      '17': true
    },
    {
      '1': 'group_id_to_share_with',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'groupIdToShareWith',
      '17': true
    },
    {
      '1': 'group_id_to_unshare_with',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'groupIdToUnshareWith',
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
    {'1': '_attachment_id_num'},
    {'1': '_attachment_id_str'},
    {'1': '_user_id_to_share_with'},
    {'1': '_user_id_to_unshare_with'},
    {'1': '_group_id_to_share_with'},
    {'1': '_group_id_to_unshare_with'},
  ],
};

/// Descriptor for `UpdateMessageAttachmentInfoRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateMessageAttachmentInfoRequestDescriptor = $convert.base64Decode(
    'CiJVcGRhdGVNZXNzYWdlQXR0YWNobWVudEluZm9SZXF1ZXN0Ei8KEWF0dGFjaG1lbnRfaWRfbn'
    'VtGAEgASgDSABSD2F0dGFjaG1lbnRJZE51bYgBARIvChFhdHRhY2htZW50X2lkX3N0chgCIAEo'
    'CUgBUg9hdHRhY2htZW50SWRTdHKIAQESNQoVdXNlcl9pZF90b19zaGFyZV93aXRoGAMgASgDSA'
    'JSEXVzZXJJZFRvU2hhcmVXaXRoiAEBEjkKF3VzZXJfaWRfdG9fdW5zaGFyZV93aXRoGAQgASgD'
    'SANSE3VzZXJJZFRvVW5zaGFyZVdpdGiIAQESNwoWZ3JvdXBfaWRfdG9fc2hhcmVfd2l0aBgFIA'
    'EoA0gEUhJncm91cElkVG9TaGFyZVdpdGiIAQESOwoYZ3JvdXBfaWRfdG9fdW5zaGFyZV93aXRo'
    'GAYgASgDSAVSFGdyb3VwSWRUb1Vuc2hhcmVXaXRoiAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA'
    '8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXNCFAoSX2F0dGFj'
    'aG1lbnRfaWRfbnVtQhQKEl9hdHRhY2htZW50X2lkX3N0ckIYChZfdXNlcl9pZF90b19zaGFyZV'
    '93aXRoQhoKGF91c2VyX2lkX3RvX3Vuc2hhcmVfd2l0aEIZChdfZ3JvdXBfaWRfdG9fc2hhcmVf'
    'd2l0aEIbChlfZ3JvdXBfaWRfdG9fdW5zaGFyZV93aXRo');
