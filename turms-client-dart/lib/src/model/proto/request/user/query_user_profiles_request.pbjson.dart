//
//  Generated code. Do not modify.
//  source: request/user/query_user_profiles_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryUserProfilesRequestDescriptor instead')
const QueryUserProfilesRequest$json = {
  '1': 'QueryUserProfilesRequest',
  '2': [
    {'1': 'user_ids', '3': 1, '4': 3, '5': 3, '10': 'userIds'},
    {
      '1': 'last_updated_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {'1': 'name', '3': 3, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'skip', '3': 10, '4': 1, '5': 5, '9': 2, '10': 'skip', '17': true},
    {'1': 'limit', '3': 11, '4': 1, '5': 5, '9': 3, '10': 'limit', '17': true},
    {
      '1': 'fields_to_highlight',
      '3': 12,
      '4': 3,
      '5': 5,
      '10': 'fieldsToHighlight'
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
    {'1': '_name'},
    {'1': '_skip'},
    {'1': '_limit'},
  ],
};

/// Descriptor for `QueryUserProfilesRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryUserProfilesRequestDescriptor = $convert.base64Decode(
    'ChhRdWVyeVVzZXJQcm9maWxlc1JlcXVlc3QSGQoIdXNlcl9pZHMYASADKANSB3VzZXJJZHMSLw'
    'oRbGFzdF91cGRhdGVkX2RhdGUYAiABKANIAFIPbGFzdFVwZGF0ZWREYXRliAEBEhcKBG5hbWUY'
    'AyABKAlIAVIEbmFtZYgBARIXCgRza2lwGAogASgFSAJSBHNraXCIAQESGQoFbGltaXQYCyABKA'
    'VIA1IFbGltaXSIAQESLgoTZmllbGRzX3RvX2hpZ2hsaWdodBgMIAMoBVIRZmllbGRzVG9IaWdo'
    'bGlnaHQSQgoRY3VzdG9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZV'
    'IQY3VzdG9tQXR0cmlidXRlc0IUChJfbGFzdF91cGRhdGVkX2RhdGVCBwoFX25hbWVCBwoFX3Nr'
    'aXBCCAoGX2xpbWl0');
