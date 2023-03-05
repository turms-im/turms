///
//  Generated code. Do not modify.
//  source: model/conversation/private_conversation.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use privateConversationDescriptor instead')
const PrivateConversation$json = const {
  '1': 'PrivateConversation',
  '2': const [
    const {'1': 'owner_id', '3': 1, '4': 1, '5': 3, '10': 'ownerId'},
    const {'1': 'target_id', '3': 2, '4': 1, '5': 3, '10': 'targetId'},
    const {'1': 'read_date', '3': 3, '4': 1, '5': 3, '10': 'readDate'},
  ],
};

/// Descriptor for `PrivateConversation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List privateConversationDescriptor = $convert.base64Decode(
    'ChNQcml2YXRlQ29udmVyc2F0aW9uEhkKCG93bmVyX2lkGAEgASgDUgdvd25lcklkEhsKCXRhcmdldF9pZBgCIAEoA1IIdGFyZ2V0SWQSGwoJcmVhZF9kYXRlGAMgASgDUghyZWFkRGF0ZQ==');
