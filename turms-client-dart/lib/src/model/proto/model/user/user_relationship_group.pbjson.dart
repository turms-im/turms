//
//  Generated code. Do not modify.
//  source: model/user/user_relationship_group.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userRelationshipGroupDescriptor instead')
const UserRelationshipGroup$json = {
  '1': 'UserRelationshipGroup',
  '2': [
    {'1': 'index', '3': 1, '4': 1, '5': 5, '10': 'index'},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '10': 'name'},
    {
      '1': 'custom_attributes',
      '3': 15,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.Value',
      '10': 'customAttributes'
    },
  ],
};

/// Descriptor for `UserRelationshipGroup`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userRelationshipGroupDescriptor = $convert.base64Decode(
    'ChVVc2VyUmVsYXRpb25zaGlwR3JvdXASFAoFaW5kZXgYASABKAVSBWluZGV4EhIKBG5hbWUYAi'
    'ABKAlSBG5hbWUSQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5W'
    'YWx1ZVIQY3VzdG9tQXR0cmlidXRlcw==');
