///
//  Generated code. Do not modify.
//  source: model/conversation/conversations.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use conversationsDescriptor instead')
const Conversations$json = const {
  '1': 'Conversations',
  '2': const [
    const {
      '1': 'private_conversations',
      '3': 1,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.PrivateConversation',
      '10': 'privateConversations'
    },
    const {
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
    'Cg1Db252ZXJzYXRpb25zElgKFXByaXZhdGVfY29udmVyc2F0aW9ucxgBIAMoCzIjLmltLnR1cm1zLnByb3RvLlByaXZhdGVDb252ZXJzYXRpb25SFHByaXZhdGVDb252ZXJzYXRpb25zElIKE2dyb3VwX2NvbnZlcnNhdGlvbnMYAiADKAsyIS5pbS50dXJtcy5wcm90by5Hcm91cENvbnZlcnNhdGlvblISZ3JvdXBDb252ZXJzYXRpb25z');
