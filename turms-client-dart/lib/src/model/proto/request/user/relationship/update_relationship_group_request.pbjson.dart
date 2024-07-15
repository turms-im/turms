//
//  Generated code. Do not modify.
//  source: request/user/relationship/update_relationship_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateRelationshipGroupRequestDescriptor instead')
const UpdateRelationshipGroupRequest$json = {
  '1': 'UpdateRelationshipGroupRequest',
  '2': [
    {'1': 'group_index', '3': 1, '4': 1, '5': 5, '10': 'groupIndex'},
    {'1': 'new_name', '3': 2, '4': 1, '5': 9, '10': 'newName'},
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

/// Descriptor for `UpdateRelationshipGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateRelationshipGroupRequestDescriptor =
    $convert.base64Decode(
        'Ch5VcGRhdGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3QSHwoLZ3JvdXBfaW5kZXgYASABKAVSCm'
        'dyb3VwSW5kZXgSGQoIbmV3X25hbWUYAiABKAlSB25ld05hbWUSQgoRY3VzdG9tX2F0dHJpYnV0'
        'ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlcw==');
