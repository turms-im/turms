//
//  Generated code. Do not modify.
//  source: request/conference/query_meetings_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryMeetingsRequestDescriptor instead')
const QueryMeetingsRequest$json = {
  '1': 'QueryMeetingsRequest',
  '2': [
    {'1': 'ids', '3': 1, '4': 3, '5': 3, '10': 'ids'},
    {'1': 'creator_ids', '3': 2, '4': 3, '5': 3, '10': 'creatorIds'},
    {'1': 'user_ids', '3': 3, '4': 3, '5': 3, '10': 'userIds'},
    {'1': 'group_ids', '3': 4, '4': 3, '5': 3, '10': 'groupIds'},
    {
      '1': 'creation_date_start',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'creationDateStart',
      '17': true
    },
    {
      '1': 'creation_date_end',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'creationDateEnd',
      '17': true
    },
    {'1': 'skip', '3': 10, '4': 1, '5': 5, '9': 2, '10': 'skip', '17': true},
    {'1': 'limit', '3': 11, '4': 1, '5': 5, '9': 3, '10': 'limit', '17': true},
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
    {'1': '_creation_date_start'},
    {'1': '_creation_date_end'},
    {'1': '_skip'},
    {'1': '_limit'},
  ],
};

/// Descriptor for `QueryMeetingsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryMeetingsRequestDescriptor = $convert.base64Decode(
    'ChRRdWVyeU1lZXRpbmdzUmVxdWVzdBIQCgNpZHMYASADKANSA2lkcxIfCgtjcmVhdG9yX2lkcx'
    'gCIAMoA1IKY3JlYXRvcklkcxIZCgh1c2VyX2lkcxgDIAMoA1IHdXNlcklkcxIbCglncm91cF9p'
    'ZHMYBCADKANSCGdyb3VwSWRzEjMKE2NyZWF0aW9uX2RhdGVfc3RhcnQYBSABKANIAFIRY3JlYX'
    'Rpb25EYXRlU3RhcnSIAQESLwoRY3JlYXRpb25fZGF0ZV9lbmQYBiABKANIAVIPY3JlYXRpb25E'
    'YXRlRW5kiAEBEhcKBHNraXAYCiABKAVIAlIEc2tpcIgBARIZCgVsaW1pdBgLIAEoBUgDUgVsaW'
    '1pdIgBARJCChFjdXN0b21fYXR0cmlidXRlcxgPIAMoCzIVLmltLnR1cm1zLnByb3RvLlZhbHVl'
    'UhBjdXN0b21BdHRyaWJ1dGVzQhYKFF9jcmVhdGlvbl9kYXRlX3N0YXJ0QhQKEl9jcmVhdGlvbl'
    '9kYXRlX2VuZEIHCgVfc2tpcEIICgZfbGltaXQ=');
