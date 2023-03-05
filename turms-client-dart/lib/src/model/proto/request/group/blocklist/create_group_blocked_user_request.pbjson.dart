///
//  Generated code. Do not modify.
//  source: request/group/blocklist/create_group_blocked_user_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupBlockedUserRequestDescriptor instead')
const CreateGroupBlockedUserRequest$json = const {
  '1': 'CreateGroupBlockedUserRequest',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {'1': 'user_id', '3': 2, '4': 1, '5': 3, '10': 'userId'},
  ],
};

/// Descriptor for `CreateGroupBlockedUserRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupBlockedUserRequestDescriptor =
    $convert.base64Decode(
        'Ch1DcmVhdGVHcm91cEJsb2NrZWRVc2VyUmVxdWVzdBIZCghncm91cF9pZBgBIAEoA1IHZ3JvdXBJZBIXCgd1c2VyX2lkGAIgASgDUgZ1c2VySWQ=');
