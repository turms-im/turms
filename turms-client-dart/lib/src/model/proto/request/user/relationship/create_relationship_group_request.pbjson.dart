//
//  Generated code. Do not modify.
//  source: request/user/relationship/create_relationship_group_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use createRelationshipGroupRequestDescriptor instead')
const CreateRelationshipGroupRequest$json = {
  '1': 'CreateRelationshipGroupRequest',
  '2': [
    {'1': 'name', '3': 1, '4': 1, '5': 9, '10': 'name'},
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

/// Descriptor for `CreateRelationshipGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List createRelationshipGroupRequestDescriptor =
    $convert.base64Decode(
        'Ch5DcmVhdGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3QSEgoEbmFtZRgBIAEoCVIEbmFtZRJCCh'
        'FjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVlUhBjdXN0b21B'
        'dHRyaWJ1dGVz');
