//
//  Generated code. Do not modify.
//  source: request/user/relationship/create_friend_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createFriendRequestRequestDescriptor instead')
const CreateFriendRequestRequest$json = {
  '1': 'CreateFriendRequestRequest',
  '2': [
    {'1': 'recipient_id', '3': 1, '4': 1, '5': 3, '10': 'recipientId'},
    {'1': 'content', '3': 2, '4': 1, '5': 9, '10': 'content'},
  ],
};

/// Descriptor for `CreateFriendRequestRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createFriendRequestRequestDescriptor =
    $convert.base64Decode(
        'ChpDcmVhdGVGcmllbmRSZXF1ZXN0UmVxdWVzdBIhCgxyZWNpcGllbnRfaWQYASABKANSC3JlY2'
        'lwaWVudElkEhgKB2NvbnRlbnQYAiABKAlSB2NvbnRlbnQ=');
