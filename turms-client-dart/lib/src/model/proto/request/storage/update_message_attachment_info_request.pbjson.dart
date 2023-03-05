///
//  Generated code. Do not modify.
//  source: request/storage/update_message_attachment_info_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateMessageAttachmentInfoRequestDescriptor instead')
const UpdateMessageAttachmentInfoRequest$json = const {
  '1': 'UpdateMessageAttachmentInfoRequest',
  '2': const [
    const {
      '1': 'attachment_id_num',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'attachmentIdNum',
      '17': true
    },
    const {
      '1': 'attachment_id_str',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'attachmentIdStr',
      '17': true
    },
    const {
      '1': 'user_id_to_share_with',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'userIdToShareWith',
      '17': true
    },
    const {
      '1': 'user_id_to_unshare_with',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'userIdToUnshareWith',
      '17': true
    },
    const {
      '1': 'group_id_to_share_with',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'groupIdToShareWith',
      '17': true
    },
    const {
      '1': 'group_id_to_unshare_with',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'groupIdToUnshareWith',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_attachment_id_num'},
    const {'1': '_attachment_id_str'},
    const {'1': '_user_id_to_share_with'},
    const {'1': '_user_id_to_unshare_with'},
    const {'1': '_group_id_to_share_with'},
    const {'1': '_group_id_to_unshare_with'},
  ],
};

/// Descriptor for `UpdateMessageAttachmentInfoRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateMessageAttachmentInfoRequestDescriptor =
    $convert.base64Decode(
        'CiJVcGRhdGVNZXNzYWdlQXR0YWNobWVudEluZm9SZXF1ZXN0Ei8KEWF0dGFjaG1lbnRfaWRfbnVtGAEgASgDSABSD2F0dGFjaG1lbnRJZE51bYgBARIvChFhdHRhY2htZW50X2lkX3N0chgCIAEoCUgBUg9hdHRhY2htZW50SWRTdHKIAQESNQoVdXNlcl9pZF90b19zaGFyZV93aXRoGAMgASgDSAJSEXVzZXJJZFRvU2hhcmVXaXRoiAEBEjkKF3VzZXJfaWRfdG9fdW5zaGFyZV93aXRoGAQgASgDSANSE3VzZXJJZFRvVW5zaGFyZVdpdGiIAQESNwoWZ3JvdXBfaWRfdG9fc2hhcmVfd2l0aBgFIAEoA0gEUhJncm91cElkVG9TaGFyZVdpdGiIAQESOwoYZ3JvdXBfaWRfdG9fdW5zaGFyZV93aXRoGAYgASgDSAVSFGdyb3VwSWRUb1Vuc2hhcmVXaXRoiAEBQhQKEl9hdHRhY2htZW50X2lkX251bUIUChJfYXR0YWNobWVudF9pZF9zdHJCGAoWX3VzZXJfaWRfdG9fc2hhcmVfd2l0aEIaChhfdXNlcl9pZF90b191bnNoYXJlX3dpdGhCGQoXX2dyb3VwX2lkX3RvX3NoYXJlX3dpdGhCGwoZX2dyb3VwX2lkX3RvX3Vuc2hhcmVfd2l0aA==');
