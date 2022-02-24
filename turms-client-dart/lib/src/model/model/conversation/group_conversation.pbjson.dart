///
//  Generated code. Do not modify.
//  source: model/conversation/group_conversation.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use groupConversationDescriptor instead')
const GroupConversation$json = const {
  '1': 'GroupConversation',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {'1': 'member_id_and_read_date', '3': 2, '4': 3, '5': 11, '6': '.im.turms.proto.GroupConversation.MemberIdAndReadDateEntry', '10': 'memberIdAndReadDate'},
  ],
  '3': const [GroupConversation_MemberIdAndReadDateEntry$json],
};

@$core.Deprecated('Use groupConversationDescriptor instead')
const GroupConversation_MemberIdAndReadDateEntry$json = const {
  '1': 'MemberIdAndReadDateEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 3, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 3, '10': 'value'},
  ],
  '7': const {'7': true},
};

/// Descriptor for `GroupConversation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupConversationDescriptor = $convert.base64Decode('ChFHcm91cENvbnZlcnNhdGlvbhIZCghncm91cF9pZBgBIAEoA1IHZ3JvdXBJZBJwChdtZW1iZXJfaWRfYW5kX3JlYWRfZGF0ZRgCIAMoCzI6LmltLnR1cm1zLnByb3RvLkdyb3VwQ29udmVyc2F0aW9uLk1lbWJlcklkQW5kUmVhZERhdGVFbnRyeVITbWVtYmVySWRBbmRSZWFkRGF0ZRpGChhNZW1iZXJJZEFuZFJlYWREYXRlRW50cnkSEAoDa2V5GAEgASgDUgNrZXkSFAoFdmFsdWUYAiABKANSBXZhbHVlOgI4AQ==');
