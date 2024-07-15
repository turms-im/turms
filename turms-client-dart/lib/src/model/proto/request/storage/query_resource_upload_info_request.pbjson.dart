//
//  Generated code. Do not modify.
//  source: request/storage/query_resource_upload_info_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryResourceUploadInfoRequestDescriptor instead')
const QueryResourceUploadInfoRequest$json = {
  '1': 'QueryResourceUploadInfoRequest',
  '2': [
    {
      '1': 'type',
      '3': 1,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.StorageResourceType',
      '10': 'type'
    },
    {'1': 'id_num', '3': 2, '4': 1, '5': 3, '9': 0, '10': 'idNum', '17': true},
    {'1': 'id_str', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'idStr', '17': true},
    {'1': 'name', '3': 4, '4': 1, '5': 9, '9': 2, '10': 'name', '17': true},
    {
      '1': 'media_type',
      '3': 5,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'mediaType',
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
    {'1': '_id_num'},
    {'1': '_id_str'},
    {'1': '_name'},
    {'1': '_media_type'},
  ],
};

/// Descriptor for `QueryResourceUploadInfoRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryResourceUploadInfoRequestDescriptor = $convert.base64Decode(
    'Ch5RdWVyeVJlc291cmNlVXBsb2FkSW5mb1JlcXVlc3QSNwoEdHlwZRgBIAEoDjIjLmltLnR1cm'
    '1zLnByb3RvLlN0b3JhZ2VSZXNvdXJjZVR5cGVSBHR5cGUSGgoGaWRfbnVtGAIgASgDSABSBWlk'
    'TnVtiAEBEhoKBmlkX3N0chgDIAEoCUgBUgVpZFN0cogBARIXCgRuYW1lGAQgASgJSAJSBG5hbW'
    'WIAQESIgoKbWVkaWFfdHlwZRgFIAEoCUgDUgltZWRpYVR5cGWIAQESQgoRY3VzdG9tX2F0dHJp'
    'YnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRlc0IJCg'
    'dfaWRfbnVtQgkKB19pZF9zdHJCBwoFX25hbWVCDQoLX21lZGlhX3R5cGU=');
