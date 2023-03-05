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
const QueryMessageAttachmentInfosRequest$json = const {
  '1': 'QueryMessageAttachmentInfosRequest',
  '2': const [
    const {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    const {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
    const {
      '1': 'creation_date_start',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'creationDateStart',
      '17': true
    },
    const {
      '1': 'creation_date_end',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'creationDateEnd',
      '17': true
    },
    const {
      '1': 'in_private_conversation',
      '3': 5,
      '4': 1,
      '5': 8,
      '9': 2,
      '10': 'inPrivateConversation',
      '17': true
    },
    const {
      '1': 'are_shared_by_me',
      '3': 6,
      '4': 1,
      '5': 8,
      '9': 3,
      '10': 'areSharedByMe',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_creation_date_start'},
    const {'1': '_creation_date_end'},
    const {'1': '_in_private_conversation'},
    const {'1': '_are_shared_by_me'},
  ],
};

/// Descriptor for `QueryMessageAttachmentInfosRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryMessageAttachmentInfosRequestDescriptor =
    $convert.base64Decode(
        'CiJRdWVyeU1lc3NhZ2VBdHRhY2htZW50SW5mb3NSZXF1ZXN0EhkKCHVzZXJfaWRzGAEgAygDUgd1c2VySWRzEhsKCWdyb3VwX2lkcxgCIAMoA1IIZ3JvdXBJZHMSMwoTY3JlYXRpb25fZGF0ZV9zdGFydBgDIAEoA0gAUhFjcmVhdGlvbkRhdGVTdGFydIgBARIvChFjcmVhdGlvbl9kYXRlX2VuZBgEIAEoA0gBUg9jcmVhdGlvbkRhdGVFbmSIAQESOwoXaW5fcHJpdmF0ZV9jb252ZXJzYXRpb24YBSABKAhIAlIVaW5Qcml2YXRlQ29udmVyc2F0aW9uiAEBEiwKEGFyZV9zaGFyZWRfYnlfbWUYBiABKAhIA1INYXJlU2hhcmVkQnlNZYgBAUIWChRfY3JlYXRpb25fZGF0ZV9zdGFydEIUChJfY3JlYXRpb25fZGF0ZV9lbmRCGgoYX2luX3ByaXZhdGVfY29udmVyc2F0aW9uQhMKEV9hcmVfc2hhcmVkX2J5X21l');
