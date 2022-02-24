///
//  Generated code. Do not modify.
//  source: request/message/query_messages_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use queryMessagesRequestDescriptor instead')
const QueryMessagesRequest$json = const {
  '1': 'QueryMessagesRequest',
  '2': const [
    const {'1': 'ids', '3': 1, '4': 3, '5': 3, '10': 'ids'},
    const {'1': 'size', '3': 2, '4': 1, '5': 5, '9': 0, '10': 'size', '17': true},
    const {'1': 'are_group_messages', '3': 3, '4': 1, '5': 8, '9': 1, '10': 'areGroupMessages', '17': true},
    const {'1': 'are_system_messages', '3': 4, '4': 1, '5': 8, '9': 2, '10': 'areSystemMessages', '17': true},
    const {'1': 'from_id', '3': 5, '4': 1, '5': 3, '9': 3, '10': 'fromId', '17': true},
    const {'1': 'delivery_date_after', '3': 6, '4': 1, '5': 3, '9': 4, '10': 'deliveryDateAfter', '17': true},
    const {'1': 'delivery_date_before', '3': 7, '4': 1, '5': 3, '9': 5, '10': 'deliveryDateBefore', '17': true},
    const {'1': 'with_total', '3': 8, '4': 1, '5': 8, '10': 'withTotal'},
  ],
  '8': const [
    const {'1': '_size'},
    const {'1': '_are_group_messages'},
    const {'1': '_are_system_messages'},
    const {'1': '_from_id'},
    const {'1': '_delivery_date_after'},
    const {'1': '_delivery_date_before'},
  ],
};

/// Descriptor for `QueryMessagesRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryMessagesRequestDescriptor = $convert.base64Decode('ChRRdWVyeU1lc3NhZ2VzUmVxdWVzdBIQCgNpZHMYASADKANSA2lkcxIXCgRzaXplGAIgASgFSABSBHNpemWIAQESMQoSYXJlX2dyb3VwX21lc3NhZ2VzGAMgASgISAFSEGFyZUdyb3VwTWVzc2FnZXOIAQESMwoTYXJlX3N5c3RlbV9tZXNzYWdlcxgEIAEoCEgCUhFhcmVTeXN0ZW1NZXNzYWdlc4gBARIcCgdmcm9tX2lkGAUgASgDSANSBmZyb21JZIgBARIzChNkZWxpdmVyeV9kYXRlX2FmdGVyGAYgASgDSARSEWRlbGl2ZXJ5RGF0ZUFmdGVyiAEBEjUKFGRlbGl2ZXJ5X2RhdGVfYmVmb3JlGAcgASgDSAVSEmRlbGl2ZXJ5RGF0ZUJlZm9yZYgBARIdCgp3aXRoX3RvdGFsGAggASgIUgl3aXRoVG90YWxCBwoFX3NpemVCFQoTX2FyZV9ncm91cF9tZXNzYWdlc0IWChRfYXJlX3N5c3RlbV9tZXNzYWdlc0IKCghfZnJvbV9pZEIWChRfZGVsaXZlcnlfZGF0ZV9hZnRlckIXChVfZGVsaXZlcnlfZGF0ZV9iZWZvcmU=');
