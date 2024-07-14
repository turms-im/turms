//
//  Generated code. Do not modify.
//  source: request/group/create_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createGroupRequestDescriptor instead')
const CreateGroupRequest$json = {
  '1': 'CreateGroupRequest',
  '2': [
    {'1': 'name', '3': 1, '4': 1, '5': 9, '10': 'name'},
    {'1': 'intro', '3': 2, '4': 1, '5': 9, '9': 0, '10': 'intro', '17': true},
    {
      '1': 'announcement',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 1,
      '10': 'announcement',
      '17': true
    },
    {
      '1': 'min_score',
      '3': 4,
      '4': 1,
      '5': 5,
      '9': 2,
      '10': 'minScore',
      '17': true
    },
    {
      '1': 'type_id',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 3,
      '10': 'typeId',
      '17': true
    },
    {
      '1': 'mute_end_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'muteEndDate',
      '17': true
    },
    {
      '1': 'user_defined_attributes',
      '3': 7,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.CreateGroupRequest.UserDefinedAttributesEntry',
      '10': 'userDefinedAttributes'
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
  '3': [CreateGroupRequest_UserDefinedAttributesEntry$json],
  '8': [
    {'1': '_intro'},
    {'1': '_announcement'},
    {'1': '_min_score'},
    {'1': '_type_id'},
    {'1': '_mute_end_date'},
  ],
};

@$core.Deprecated('Use createGroupRequestDescriptor instead')
const CreateGroupRequest_UserDefinedAttributesEntry$json = {
  '1': 'UserDefinedAttributesEntry',
  '2': [
    {'1': 'key', '3': 1, '4': 1, '5': 9, '10': 'key'},
    {
      '1': 'value',
      '3': 2,
      '4': 1,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'value'
    },
  ],
  '7': {'7': true},
};

/// Descriptor for `CreateGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createGroupRequestDescriptor = $convert.base64Decode(
    'ChJDcmVhdGVHcm91cFJlcXVlc3QSEgoEbmFtZRgBIAEoCVIEbmFtZRIZCgVpbnRybxgCIAEoCU'
    'gAUgVpbnRyb4gBARInCgxhbm5vdW5jZW1lbnQYAyABKAlIAVIMYW5ub3VuY2VtZW50iAEBEiAK'
    'CW1pbl9zY29yZRgEIAEoBUgCUghtaW5TY29yZYgBARIcCgd0eXBlX2lkGAUgASgDSANSBnR5cG'
    'VJZIgBARInCg1tdXRlX2VuZF9kYXRlGAYgASgDSARSC211dGVFbmREYXRliAEBEnUKF3VzZXJf'
    'ZGVmaW5lZF9hdHRyaWJ1dGVzGAcgAygLMj0uaW0udHVybXMucHJvdG8uQ3JlYXRlR3JvdXBSZX'
    'F1ZXN0LlVzZXJEZWZpbmVkQXR0cmlidXRlc0VudHJ5UhV1c2VyRGVmaW5lZEF0dHJpYnV0ZXMS'
    'QgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG'
    '9tQXR0cmlidXRlcxpfChpVc2VyRGVmaW5lZEF0dHJpYnV0ZXNFbnRyeRIQCgNrZXkYASABKAlS'
    'A2tleRIrCgV2YWx1ZRgCIAEoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUgV2YWx1ZToCOAFCCA'
    'oGX2ludHJvQg8KDV9hbm5vdW5jZW1lbnRCDAoKX21pbl9zY29yZUIKCghfdHlwZV9pZEIQCg5f'
    'bXV0ZV9lbmRfZGF0ZQ==');
