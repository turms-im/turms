///
//  Generated code. Do not modify.
//  source: request/conversation/query_conversations_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryConversationsRequestDescriptor instead')
const QueryConversationsRequest$json = const {
  '1': 'QueryConversationsRequest',
  '2': const [
    const {'1': 'target_ids', '3': 1, '4': 3, '5': 3, '10': 'targetIds'},
    const {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
  ],
};

/// Descriptor for `QueryConversationsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryConversationsRequestDescriptor =
    $convert.base64Decode(
        'ChlRdWVyeUNvbnZlcnNhdGlvbnNSZXF1ZXN0Eh0KCnRhcmdldF9pZHMYASADKANSCXRhcmdldElkcxIbCglncm91cF9pZHMYAiADKANSCGdyb3VwSWRz');
