///
//  Generated code. Do not modify.
//  source: request/storage/query_message_attachment_infos_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryMessageAttachmentInfosRequestDescriptor instead')
const QueryMessageAttachmentInfosRequest$json = {
  '1': 'QueryMessageAttachmentInfosRequest',
  '2': [
    {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
    {
      '1': 'are_shared_by_me',
      '3': 3,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'areSharedByMe',
      '17': true
    },
    {
      '1': 'creation_date_start',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'creationDateStart',
      '17': true
    },
    {
      '1': 'creation_date_end',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'creationDateEnd',
      '17': true
    },
  ],
  '8': [
    {'1': '_are_shared_by_me'},
    {'1': '_creation_date_start'},
    {'1': '_creation_date_end'},
  ],
};

/// Descriptor for `QueryMessageAttachmentInfosRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryMessageAttachmentInfosRequestDescriptor =
    $convert.base64Decode(
        'CiJRdWVyeU1lc3NhZ2VBdHRhY2htZW50SW5mb3NSZXF1ZXN0EhkKCHVzZXJfaWRzGAEgAygDUgd1c2VySWRzEhsKCWdyb3VwX2lkcxgCIAMoA1IIZ3JvdXBJZHMSLAoQYXJlX3NoYXJlZF9ieV9tZRgDIAEoCEgAUg1hcmVTaGFyZWRCeU1liAEBEjMKE2NyZWF0aW9uX2RhdGVfc3RhcnQYBCABKANIAVIRY3JlYXRpb25EYXRlU3RhcnSIAQESLwoRY3JlYXRpb25fZGF0ZV9lbmQYBSABKANIAlIPY3JlYXRpb25EYXRlRW5kiAEBQhMKEV9hcmVfc2hhcmVkX2J5X21lQhYKFF9jcmVhdGlvbl9kYXRlX3N0YXJ0QhQKEl9jcmVhdGlvbl9kYXRlX2VuZA==');
