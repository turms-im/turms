///
//  Generated code. Do not modify.
//  source: request/message/query_messages_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryMessagesRequestDescriptor instead')
const QueryMessagesRequest$json = const {
  '1': 'QueryMessagesRequest',
  '2': const [
    const {'1': 'ids', '3': 1, '4': 3, '5': 3, '10': 'ids'},
    const {
      '1': 'are_group_messages',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 0,
      '10': 'areGroupMessages',
      '17': true
    },
    const {
      '1': 'are_system_messages',
      '3': 3,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'areSystemMessages',
      '17': true
    },
    const {'1': 'from_ids', '3': 4, '4': 3, '5': 3, '10': 'fromIds'},
    const {
      '1': 'delivery_date_start',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'deliveryDateStart',
      '17': true
    },
    const {
      '1': 'delivery_date_end',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'deliveryDateEnd',
      '17': true
    },
    const {
      '1': 'max_count',
      '3': 7,
      '4': 1,
      '5': 5,
      '9': 4,
      '10': 'maxCount',
      '17': true
    },
    const {'1': 'with_total', '3': 8, '4': 1, '5': 8, '10': 'withTotal'},
    const {
      '1': 'descending',
      '3': 9,
      '4': 1,
      '5': 8,
      '9': 5,
      '10': 'descending',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_are_group_messages'},
    const {'1': '_are_system_messages'},
    const {'1': '_delivery_date_start'},
    const {'1': '_delivery_date_end'},
    const {'1': '_max_count'},
    const {'1': '_descending'},
  ],
};

/// Descriptor for `QueryMessagesRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryMessagesRequestDescriptor = $convert.base64Decode(
    'ChRRdWVyeU1lc3NhZ2VzUmVxdWVzdBIQCgNpZHMYASADKANSA2lkcxIxChJhcmVfZ3JvdXBfbWVzc2FnZXMYAiABKAhIAFIQYXJlR3JvdXBNZXNzYWdlc4gBARIzChNhcmVfc3lzdGVtX21lc3NhZ2VzGAMgASgISAFSEWFyZVN5c3RlbU1lc3NhZ2VziAEBEhkKCGZyb21faWRzGAQgAygDUgdmcm9tSWRzEjMKE2RlbGl2ZXJ5X2RhdGVfc3RhcnQYBSABKANIAlIRZGVsaXZlcnlEYXRlU3RhcnSIAQESLwoRZGVsaXZlcnlfZGF0ZV9lbmQYBiABKANIA1IPZGVsaXZlcnlEYXRlRW5kiAEBEiAKCW1heF9jb3VudBgHIAEoBUgEUghtYXhDb3VudIgBARIdCgp3aXRoX3RvdGFsGAggASgIUgl3aXRoVG90YWwSIwoKZGVzY2VuZGluZxgJIAEoCEgFUgpkZXNjZW5kaW5niAEBQhUKE19hcmVfZ3JvdXBfbWVzc2FnZXNCFgoUX2FyZV9zeXN0ZW1fbWVzc2FnZXNCFgoUX2RlbGl2ZXJ5X2RhdGVfc3RhcnRCFAoSX2RlbGl2ZXJ5X2RhdGVfZW5kQgwKCl9tYXhfY291bnRCDQoLX2Rlc2NlbmRpbmc=');
