//
//  Generated code. Do not modify.
//  source: model/conversation/group_conversation.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupConversationDescriptor instead')
const GroupConversation$json = {
  '1': 'GroupConversation',
  '2': [
    {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    {
      '1': 'member_id_to_read_date',
      '3': 2,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.GroupConversation.MemberIdToReadDateEntry',
      '10': 'memberIdToReadDate'
    },
  ],
  '3': [GroupConversation_MemberIdToReadDateEntry$json],
};

@$core.Deprecated('Use groupConversationDescriptor instead')
const GroupConversation_MemberIdToReadDateEntry$json = {
  '1': 'MemberIdToReadDateEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 3, '10': 'key'},
    {'1': 'value', '3': 2, '4': 1, '5': 3, '10': 'value'},
  ],
  '7': {'7': true},
};

/// Descriptor for `GroupConversation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupConversationDescriptor = $convert.base64Decode(
    'ChFHcm91cENvbnZlcnNhdGlvbhIZCghncm91cF9pZBgBIAEoA1IHZ3JvdXBJZBJtChZtZW1iZX'
    'JfaWRfdG9fcmVhZF9kYXRlGAIgAygLMjkuaW0udHVybXMucHJvdG8uR3JvdXBDb252ZXJzYXRp'
    'b24uTWVtYmVySWRUb1JlYWREYXRlRW50cnlSEm1lbWJlcklkVG9SZWFkRGF0ZRpFChdNZW1iZX'
    'JJZFRvUmVhZERhdGVFbnRyeRIQCgNrZXkYASABKANSA2tleRIUCgV2YWx1ZRgCIAEoA1IFdmFs'
    'dWU6AjgB');
