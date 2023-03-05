///
//  Generated code. Do not modify.
//  source: request/user/query_nearby_users_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryNearbyUsersRequestDescriptor instead')
const QueryNearbyUsersRequest$json = const {
  '1': 'QueryNearbyUsersRequest',
  '2': const [
    const {'1': 'latitude', '3': 1, '4': 1, '5': 2, '10': 'latitude'},
    const {'1': 'longitude', '3': 2, '4': 1, '5': 2, '10': 'longitude'},
    const {
      '1': 'max_count',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'maxCount',
      '17': true
    },
    const {
      '1': 'max_distance',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'maxDistance',
      '17': true
    },
    const {
      '1': 'with_coordinates',
      '3': 5,
      '4': 1,
      '5': 8,
      '9': 2,
      '10': 'withCoordinates',
      '17': true
    },
    const {
      '1': 'with_distance',
      '3': 6,
      '4': 1,
      '5': 8,
      '9': 3,
      '10': 'withDistance',
      '17': true
    },
    const {
      '1': 'with_user_info',
      '3': 7,
      '4': 1,
      '5': 8,
      '9': 4,
      '10': 'withUserInfo',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_max_count'},
    const {'1': '_max_distance'},
    const {'1': '_with_coordinates'},
    const {'1': '_with_distance'},
    const {'1': '_with_user_info'},
  ],
};

/// Descriptor for `QueryNearbyUsersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryNearbyUsersRequestDescriptor =
    $convert.base64Decode(
        'ChdRdWVyeU5lYXJieVVzZXJzUmVxdWVzdBIaCghsYXRpdHVkZRgBIAEoAlIIbGF0aXR1ZGUSHAoJbG9uZ2l0dWRlGAIgASgCUglsb25naXR1ZGUSIAoJbWF4X2NvdW50GAMgASgFSABSCG1heENvdW50iAEBEiYKDG1heF9kaXN0YW5jZRgEIAEoBUgBUgttYXhEaXN0YW5jZYgBARIuChB3aXRoX2Nvb3JkaW5hdGVzGAUgASgISAJSD3dpdGhDb29yZGluYXRlc4gBARIoCg13aXRoX2Rpc3RhbmNlGAYgASgISANSDHdpdGhEaXN0YW5jZYgBARIpCg53aXRoX3VzZXJfaW5mbxgHIAEoCEgEUgx3aXRoVXNlckluZm+IAQFCDAoKX21heF9jb3VudEIPCg1fbWF4X2Rpc3RhbmNlQhMKEV93aXRoX2Nvb3JkaW5hdGVzQhAKDl93aXRoX2Rpc3RhbmNlQhEKD193aXRoX3VzZXJfaW5mbw==');
