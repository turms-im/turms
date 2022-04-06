///
//  Generated code. Do not modify.
//  source: request/user/query_nearby_users_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,unnecessary_const,non_constant_identifier_names,library_prefixes,unused_import,unused_shown_name,return_of_invalid_type,unnecessary_this,prefer_final_fields,deprecated_member_use_from_same_package

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;
@$core.Deprecated('Use queryNearbyUsersRequestDescriptor instead')
const QueryNearbyUsersRequest$json = const {
  '1': 'QueryNearbyUsersRequest',
  '2': const [
    const {'1': 'latitude', '3': 1, '4': 1, '5': 2, '10': 'latitude'},
    const {'1': 'longitude', '3': 2, '4': 1, '5': 2, '10': 'longitude'},
    const {'1': 'distance', '3': 3, '4': 1, '5': 5, '9': 0, '10': 'distance', '17': true},
    const {'1': 'max_number', '3': 4, '4': 1, '5': 5, '9': 1, '10': 'maxNumber', '17': true},
    const {'1': 'with_coordinates', '3': 5, '4': 1, '5': 8, '9': 2, '10': 'withCoordinates', '17': true},
    const {'1': 'with_distance', '3': 6, '4': 1, '5': 8, '9': 3, '10': 'withDistance', '17': true},
    const {'1': 'with_info', '3': 7, '4': 1, '5': 8, '9': 4, '10': 'withInfo', '17': true},
  ],
  '8': const [
    const {'1': '_distance'},
    const {'1': '_max_number'},
    const {'1': '_with_coordinates'},
    const {'1': '_with_distance'},
    const {'1': '_with_info'},
  ],
};

/// Descriptor for `QueryNearbyUsersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryNearbyUsersRequestDescriptor = $convert.base64Decode('ChdRdWVyeU5lYXJieVVzZXJzUmVxdWVzdBIaCghsYXRpdHVkZRgBIAEoAlIIbGF0aXR1ZGUSHAoJbG9uZ2l0dWRlGAIgASgCUglsb25naXR1ZGUSHwoIZGlzdGFuY2UYAyABKAVIAFIIZGlzdGFuY2WIAQESIgoKbWF4X251bWJlchgEIAEoBUgBUgltYXhOdW1iZXKIAQESLgoQd2l0aF9jb29yZGluYXRlcxgFIAEoCEgCUg93aXRoQ29vcmRpbmF0ZXOIAQESKAoNd2l0aF9kaXN0YW5jZRgGIAEoCEgDUgx3aXRoRGlzdGFuY2WIAQESIAoJd2l0aF9pbmZvGAcgASgISARSCHdpdGhJbmZviAEBQgsKCV9kaXN0YW5jZUINCgtfbWF4X251bWJlckITChFfd2l0aF9jb29yZGluYXRlc0IQCg5fd2l0aF9kaXN0YW5jZUIMCgpfd2l0aF9pbmZv');
