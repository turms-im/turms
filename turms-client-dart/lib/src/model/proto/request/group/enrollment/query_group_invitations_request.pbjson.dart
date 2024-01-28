//
//  Generated code. Do not modify.
//  source: request/group/enrollment/query_group_invitations_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupInvitationsRequestDescriptor instead')
const QueryGroupInvitationsRequest$json = {
  '1': 'QueryGroupInvitationsRequest',
  '2': [
    {
      '1': 'group_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'are_sent_by_me',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'areSentByMe',
      '17': true
    },
    {
      '1': 'last_updated_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'lastUpdatedDate',
      '17': true
    },
  ],
  '8': [
    {'1': '_group_id'},
    {'1': '_are_sent_by_me'},
    {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryGroupInvitationsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupInvitationsRequestDescriptor = $convert.base64Decode(
    'ChxRdWVyeUdyb3VwSW52aXRhdGlvbnNSZXF1ZXN0Eh4KCGdyb3VwX2lkGAEgASgDSABSB2dyb3'
    'VwSWSIAQESKAoOYXJlX3NlbnRfYnlfbWUYAiABKAhIAVILYXJlU2VudEJ5TWWIAQESLwoRbGFz'
    'dF91cGRhdGVkX2RhdGUYAyABKANIAlIPbGFzdFVwZGF0ZWREYXRliAEBQgsKCV9ncm91cF9pZE'
    'IRCg9fYXJlX3NlbnRfYnlfbWVCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
