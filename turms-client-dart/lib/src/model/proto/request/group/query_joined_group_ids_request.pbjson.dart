//
//  Generated code. Do not modify.
//  source: request/group/query_joined_group_ids_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryJoinedGroupIdsRequestDescriptor instead')
const QueryJoinedGroupIdsRequest$json = {
  '1': 'QueryJoinedGroupIdsRequest',
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

/// Descriptor for `QueryJoinedGroupIdsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryJoinedGroupIdsRequestDescriptor = $convert.base64Decode(
    'ChpRdWVyeUpvaW5lZEdyb3VwSWRzUmVxdWVzdBIvChFsYXN0X3VwZGF0ZWRfZGF0ZRgBIAEoA0'
    'gAUg9sYXN0VXBkYXRlZERhdGWIAQESQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50'
    'dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlc0IUChJfbGFzdF91cGRhdGVkX2RhdG'
    'U=');
