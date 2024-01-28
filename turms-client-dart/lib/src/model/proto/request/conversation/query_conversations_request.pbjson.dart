//
//  Generated code. Do not modify.
//  source: request/conversation/query_conversations_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryConversationsRequestDescriptor instead')
const QueryConversationsRequest$json = {
  '1': 'QueryConversationsRequest',
  '2': [
    {'1': 'target_ids', '3': 1, '4': 3, '5': 3, '10': 'targetIds'},
    {'1': 'group_ids', '3': 2, '4': 3, '5': 3, '10': 'groupIds'},
  ],
};

/// Descriptor for `QueryConversationsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryConversationsRequestDescriptor =
    $convert.base64Decode(
        'ChlRdWVyeUNvbnZlcnNhdGlvbnNSZXF1ZXN0Eh0KCnRhcmdldF9pZHMYASADKANSCXRhcmdldE'
        'lkcxIbCglncm91cF9pZHMYAiADKANSCGdyb3VwSWRz');
