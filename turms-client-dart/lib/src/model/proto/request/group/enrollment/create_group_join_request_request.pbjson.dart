//
//  Generated code. Do not modify.
//  source: request/group/enrollment/create_group_join_request_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupJoinRequestRequestDescriptor instead')
const CreateGroupJoinRequestRequest$json = {
  '1': 'CreateGroupJoinRequestRequest',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {'1': 'content', '3': 2, '4': 1, '5': 9, '10': 'content'},
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

/// Descriptor for `CreateGroupJoinRequestRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupJoinRequestRequestDescriptor =
    $convert.base64Decode(
        'Ch1DcmVhdGVHcm91cEpvaW5SZXF1ZXN0UmVxdWVzdBIZCghncm91cF9pZBgBIAEoA1IHZ3JvdX'
        'BJZBIYCgdjb250ZW50GAIgASgJUgdjb250ZW50EkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygL'
        'MhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXM=');
