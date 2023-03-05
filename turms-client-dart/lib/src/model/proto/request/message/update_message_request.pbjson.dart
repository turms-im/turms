///
//  Generated code. Do not modify.
//  source: request/message/update_message_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateMessageRequestDescriptor instead')
const UpdateMessageRequest$json = const {
  '1': 'UpdateMessageRequest',
  '2': const [
    const {'1': 'message_id', '3': 1, '4': 1, '5': 3, '10': 'messageId'},
    const {
      '1': 'text',
      '3': 2,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'text',
      '17': true
    },
    const {'1': 'records', '3': 3, '4': 3, '5': 12, '10': 'records'},
    const {
      '1': 'recall_date',
      '3': 4,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'recallDate',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_text'},
    const {'1': '_recall_date'},
  ],
};

/// Descriptor for `UpdateMessageRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateMessageRequestDescriptor = $convert.base64Decode(
    'ChRVcGRhdGVNZXNzYWdlUmVxdWVzdBIdCgptZXNzYWdlX2lkGAEgASgDUgltZXNzYWdlSWQSFwoEdGV4dBgCIAEoCUgAUgR0ZXh0iAEBEhgKB3JlY29yZHMYAyADKAxSB3JlY29yZHMSJAoLcmVjYWxsX2RhdGUYBCABKANIAVIKcmVjYWxsRGF0ZYgBAUIHCgVfdGV4dEIOCgxfcmVjYWxsX2RhdGU=');
