//
//  Generated code. Do not modify.
//  source: request/storage/query_message_attachment_infos_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryMessageAttachmentInfosRequestDescriptor instead')
const QueryMessageAttachmentInfosRequest$json = {
  '1': 'QueryMessageAttachmentInfosRequest',
  '2': [
    {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
    {
      '1': 'creation_date_start',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'creationDateStart',
      '17': true
    },
    {
      '1': 'creation_date_end',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'creationDateEnd',
      '17': true
    },
    {
      '1': 'in_private_conversation',
      '3': 5,
      '4': 1,
      '5': 8,
      '9': 2,
      '10': 'inPrivateConversation',
      '17': true
    },
    {
      '1': 'are_shared_by_me',
      '3': 6,
      '4': 1,
      '5': 8,
      '9': 3,
      '10': 'areSharedByMe',
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
    {'1': '_creation_date_start'},
    {'1': '_creation_date_end'},
    {'1': '_in_private_conversation'},
    {'1': '_are_shared_by_me'},
  ],
};

/// Descriptor for `QueryMessageAttachmentInfosRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryMessageAttachmentInfosRequestDescriptor = $convert.base64Decode(
    'CiJRdWVyeU1lc3NhZ2VBdHRhY2htZW50SW5mb3NSZXF1ZXN0EhkKCHVzZXJfaWRzGAEgAygDUg'
    'd1c2VySWRzEhsKCWdyb3VwX2lkcxgCIAMoA1IIZ3JvdXBJZHMSMwoTY3JlYXRpb25fZGF0ZV9z'
    'dGFydBgDIAEoA0gAUhFjcmVhdGlvbkRhdGVTdGFydIgBARIvChFjcmVhdGlvbl9kYXRlX2VuZB'
    'gEIAEoA0gBUg9jcmVhdGlvbkRhdGVFbmSIAQESOwoXaW5fcHJpdmF0ZV9jb252ZXJzYXRpb24Y'
    'BSABKAhIAlIVaW5Qcml2YXRlQ29udmVyc2F0aW9uiAEBEiwKEGFyZV9zaGFyZWRfYnlfbWUYBi'
    'ABKAhIA1INYXJlU2hhcmVkQnlNZYgBARJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmlt'
    'LnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21BdHRyaWJ1dGVzQhYKFF9jcmVhdGlvbl9kYXRlX3'
    'N0YXJ0QhQKEl9jcmVhdGlvbl9kYXRlX2VuZEIaChhfaW5fcHJpdmF0ZV9jb252ZXJzYXRpb25C'
    'EwoRX2FyZV9zaGFyZWRfYnlfbWU=');
