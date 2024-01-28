//
//  Generated code. Do not modify.
//  source: request/group/blocklist/delete_group_blocked_user_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use deleteGroupBlockedUserRequestDescriptor instead')
const DeleteGroupBlockedUserRequest$json = {
  '1': 'DeleteGroupBlockedUserRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'user_id', '3': 2, '4': 1, '5': 3, '10': 'userId'},
  ],
};

/// Descriptor for `DeleteGroupBlockedUserRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List deleteGroupBlockedUserRequestDescriptor =
    $convert.base64Decode(
        'Ch1EZWxldGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdBIZCghncm91cF9pZBgBIAEoA1IHZ3JvdX'
        'BJZBIXCgd1c2VyX2lkGAIgASgDUgZ1c2VySWQ=');
