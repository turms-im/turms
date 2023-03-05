///
//  Generated code. Do not modify.
//  source: request/user/create_session_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createSessionRequestDescriptor instead')
const CreateSessionRequest$json = const {
  '1': 'CreateSessionRequest',
  '2': const [
    const {'1': 'version', '3': 1, '4': 1, '5': 5, '10': 'version'},
    const {'1': 'user_id', '3': 2, '4': 1, '5': 3, '10': 'userId'},
    const {
      '1': 'password',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 0,
      '10': 'password',
      '17': true
    },
    const {
      '1': 'user_status',
      '3': 4,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.UserStatus',
      '9': 1,
      '10': 'userStatus',
      '17': true
    },
    const {
      '1': 'device_type',
      '3': 5,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.DeviceType',
      '10': 'deviceType'
    },
    const {
      '1': 'device_details',
      '3': 6,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.CreateSessionRequest.DeviceDetailsEntry',
      '10': 'deviceDetails'
    },
    const {
      '1': 'location',
      '3': 7,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.UserLocation',
      '9': 2,
      '10': 'location',
      '17': true
    },
  ],
  '3': const [CreateSessionRequest_DeviceDetailsEntry$json],
  '8': const [
    const {'1': '_password'},
    const {'1': '_user_status'},
    const {'1': '_location'},
  ],
};

@$core.Deprecated('Use createSessionRequestDescriptor instead')
const CreateSessionRequest_DeviceDetailsEntry$json = const {
  '1': 'DeviceDetailsEntry',
  '2': const [
    const {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    const {'1': 'value', '3': 2, '4': 1, '5': 9, '10': 'value'},
  ],
  '7': const {'7': true},
};

/// Descriptor for `CreateSessionRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createSessionRequestDescriptor = $convert.base64Decode(
    'ChRDcmVhdGVTZXNzaW9uUmVxdWVzdBIYCgd2ZXJzaW9uGAEgASgFUgd2ZXJzaW9uEhcKB3VzZXJfaWQYAiABKANSBnVzZXJJZBIfCghwYXNzd29yZBgDIAEoCUgAUghwYXNzd29yZIgBARJACgt1c2VyX3N0YXR1cxgEIAEoDjIaLmltLnR1cm1zLnByb3RvLlVzZXJTdGF0dXNIAVIKdXNlclN0YXR1c4gBARI7CgtkZXZpY2VfdHlwZRgFIAEoDjIaLmltLnR1cm1zLnByb3RvLkRldmljZVR5cGVSCmRldmljZVR5cGUSXgoOZGV2aWNlX2RldGFpbHMYBiADKAsyNy5pbS50dXJtcy5wcm90by5DcmVhdGVTZXNzaW9uUmVxdWVzdC5EZXZpY2VEZXRhaWxzRW50cnlSDWRldmljZURldGFpbHMSPQoIbG9jYXRpb24YByABKAsyHC5pbS50dXJtcy5wcm90by5Vc2VyTG9jYXRpb25IAlIIbG9jYXRpb26IAQEaQAoSRGV2aWNlRGV0YWlsc0VudHJ5EhAKA2tleRgBIAEoCVIDa2V5EhQKBXZhbHVlGAIgASgJUgV2YWx1ZToCOAFCCwoJX3Bhc3N3b3JkQg4KDF91c2VyX3N0YXR1c0ILCglfbG9jYXRpb24=');
