//
//  Generated code. Do not modify.
//  source: model/conversation/conversations.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use conversationsDescriptor instead')
const Conversations$json = {
  '1': 'Conversations',
  '2': [
    {
      '1': 'private_conversations',
      '3': 1,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.PrivateConversation',
      '10': 'privateConversations'
    },
    {
      '1': 'group_conversations',
      '3': 2,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.GroupConversation',
      '10': 'groupConversations'
    },
  ],
};

/// Descriptor for `Conversations`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List conversationsDescriptor = $convert.base64Decode(
    'Cg1Db252ZXJzYXRpb25zElgKFXByaXZhdGVfY29udmVyc2F0aW9ucxgBIAMoCzIjLmltLnR1cm'
    '1zLnByb3RvLlByaXZhdGVDb252ZXJzYXRpb25SFHByaXZhdGVDb252ZXJzYXRpb25zElIKE2dy'
    'b3VwX2NvbnZlcnNhdGlvbnMYAiADKAsyIS5pbS50dXJtcy5wcm90by5Hcm91cENvbnZlcnNhdG'
    'lvblISZ3JvdXBDb252ZXJzYXRpb25z');
