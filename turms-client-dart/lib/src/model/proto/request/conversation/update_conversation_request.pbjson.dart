///
//  Generated code. Do not modify.
//  source: request/conversation/update_conversation_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateConversationRequestDescriptor instead')
const UpdateConversationRequest$json = const {
  '1': 'UpdateConversationRequest',
  '2': const [
    const {
      '1': 'target_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'targetId',
      '17': true
    },
    const {
      '1': 'group_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
    const {'1': 'read_date', '3': 3, '4': 1, '5': 3, '10': 'readDate'},
  ],
  '8': const [
    const {'1': '_target_id'},
    const {'1': '_group_id'},
  ],
};

/// Descriptor for `UpdateConversationRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateConversationRequestDescriptor =
    $convert.base64Decode(
        'ChlVcGRhdGVDb252ZXJzYXRpb25SZXF1ZXN0EiAKCXRhcmdldF9pZBgBIAEoA0gAUgh0YXJnZXRJZIgBARIeCghncm91cF9pZBgCIAEoA0gBUgdncm91cElkiAEBEhsKCXJlYWRfZGF0ZRgDIAEoA1IIcmVhZERhdGVCDAoKX3RhcmdldF9pZEILCglfZ3JvdXBfaWQ=');
