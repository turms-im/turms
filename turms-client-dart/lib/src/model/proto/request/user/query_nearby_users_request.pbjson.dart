//
//  Generated code. Do not modify.
//  source: request/user/query_nearby_users_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryNearbyUsersRequestDescriptor instead')
const QueryNearbyUsersRequest$json = {
  '1': 'QueryNearbyUsersRequest',
  '2': [
    {'1': 'latitude', '3': 1, '4': 1, '5': 2, '10': 'latitude'},
    {'1': 'longitude', '3': 2, '4': 1, '5': 2, '10': 'longitude'},
    {
      '1': 'max_count',
      '3': 3,
      '4': 1,
      '5': 5,
      '9': 0,
      '10': 'maxCount',
      '17': true
    },
    {
      '1': 'max_distance',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 1,
      '10': 'maxDistance',
      '17': true
    },
    {
      '1': 'with_coordinates',
      '3': 5,
      '4': 1,
      '5': 8,
      '9': 2,
      '10': 'withCoordinates',
      '17': true
    },
    {
      '1': 'with_distance',
      '3': 6,
      '4': 1,
      '5': 8,
      '9': 3,
      '10': 'withDistance',
      '17': true
    },
    {
      '1': 'with_user_info',
      '3': 7,
      '4': 1,
      '5': 8,
      '9': 4,
      '10': 'withUserInfo',
      '17': true
    },
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
  '8': [
    {'1': '_max_count'},
    {'1': '_max_distance'},
    {'1': '_with_coordinates'},
    {'1': '_with_distance'},
    {'1': '_with_user_info'},
  ],
};

/// Descriptor for `QueryNearbyUsersRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryNearbyUsersRequestDescriptor = $convert.base64Decode(
    'ChdRdWVyeU5lYXJieVVzZXJzUmVxdWVzdBIaCghsYXRpdHVkZRgBIAEoAlIIbGF0aXR1ZGUSHA'
    'oJbG9uZ2l0dWRlGAIgASgCUglsb25naXR1ZGUSIAoJbWF4X2NvdW50GAMgASgFSABSCG1heENv'
    'dW50iAEBEiYKDG1heF9kaXN0YW5jZRgEIAEoBUgBUgttYXhEaXN0YW5jZYgBARIuChB3aXRoX2'
    'Nvb3JkaW5hdGVzGAUgASgISAJSD3dpdGhDb29yZGluYXRlc4gBARIoCg13aXRoX2Rpc3RhbmNl'
    'GAYgASgISANSDHdpdGhEaXN0YW5jZYgBARIpCg53aXRoX3VzZXJfaW5mbxgHIAEoCEgEUgx3aX'
    'RoVXNlckluZm+IAQESQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90'
    'by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlc0IMCgpfbWF4X2NvdW50Qg8KDV9tYXhfZGlzdGFuY2'
    'VCEwoRX3dpdGhfY29vcmRpbmF0ZXNCEAoOX3dpdGhfZGlzdGFuY2VCEQoPX3dpdGhfdXNlcl9p'
    'bmZv');
