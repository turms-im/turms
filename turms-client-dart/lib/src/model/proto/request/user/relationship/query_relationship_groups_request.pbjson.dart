//
//  Generated code. Do not modify.
//  source: request/user/relationship/query_relationship_groups_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryRelationshipGroupsRequestDescriptor instead')
const QueryRelationshipGroupsRequest$json = {
  '1': 'QueryRelationshipGroupsRequest',
  '2': [
    {
      '1': 'last_updated_date',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDate',
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
    {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryRelationshipGroupsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryRelationshipGroupsRequestDescriptor =
    $convert.base64Decode(
        'Ch5RdWVyeVJlbGF0aW9uc2hpcEdyb3Vwc1JlcXVlc3QSLwoRbGFzdF91cGRhdGVkX2RhdGUYAS'
        'ABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEBEkIKEWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUu'
        'aW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbUF0dHJpYnV0ZXNCFAoSX2xhc3RfdXBkYXRlZF'
        '9kYXRl');
