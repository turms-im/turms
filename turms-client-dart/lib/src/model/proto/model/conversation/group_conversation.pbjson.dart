///
//  Generated code. Do not modify.
//  source: model/conversation/group_conversation.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupConversationDescriptor instead')
const GroupConversation$json = const {
  '1': 'GroupConversation',
  '2': const [
    const {'1': 'group_id', '3': 1, '4': 1, '5': 3, '10': 'groupId'},
    const {
      '1': 'member_id_to_read_date',
      '3': 2,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.GroupConversation.MemberIdToReadDateEntry',
      '10': 'memberIdToReadDate'
    },
  ],
  '3': const [GroupConversation_MemberIdToReadDateEntry$json],
};

@$core.Deprecated('Use groupConversationDescriptor instead')
const GroupConversation_MemberIdToReadDateEntry$json = const {
  '1': 'MemberIdToReadDateEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 3, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 3, '10': 'value'},
  ],
  '7': const {'7': true},
};

/// Descriptor for `GroupConversation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupConversationDescriptor = $convert.base64Decode(
    'ChFHcm91cENvbnZlcnNhdGlvbhIZCghncm91cF9pZBgBIAEoA1IHZ3JvdXBJZBJtChZtZW1iZXJfaWRfdG9fcmVhZF9kYXRlGAIgAygLMjkuaW0udHVybXMucHJvdG8uR3JvdXBDb252ZXJzYXRpb24uTWVtYmVySWRUb1JlYWREYXRlRW50cnlSEm1lbWJlcklkVG9SZWFkRGF0ZRpFChdNZW1iZXJJZFRvUmVhZERhdGVFbnRyeRIQCgNrZXkYASABKANSA2tleRIUCgV2YWx1ZRgCIAEoA1IFdmFsdWU6AjgB');
