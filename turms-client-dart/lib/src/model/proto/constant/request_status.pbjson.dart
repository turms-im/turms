//
//  Generated code. Do not modify.
//  source: constant/request_status.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use requestStatusDescriptor instead')
const RequestStatus$json = {
  '1': 'RequestStatus',
  '2': [
    {'1': 'PENDING', '2': 0},
    {'1': 'ACCEPTED', '2': 1},
    {'1': 'ACCEPTED_WITHOUT_CONFIRM', '2': 2},
    {'1': 'DECLINED', '2': 3},
    {'1': 'IGNORED', '2': 4},
    {'1': 'EXPIRED', '2': 5},
    {'1': 'CANCELED', '2': 6},
  ],
};

/// Descriptor for `RequestStatus`. Decode as a `google.protobuf.EnumDescriptorProto`.
final $typed_data.Uint8List requestStatusDescriptor = $convert.base64Decode(
    'Cg1SZXF1ZXN0U3RhdHVzEgsKB1BFTkRJTkcQABIMCghBQ0NFUFRFRBABEhwKGEFDQ0VQVEVEX1'
    'dJVEhPVVRfQ09ORklSTRACEgwKCERFQ0xJTkVEEAMSCwoHSUdOT1JFRBAEEgsKB0VYUElSRUQQ'
    'BRIMCghDQU5DRUxFRBAG');
