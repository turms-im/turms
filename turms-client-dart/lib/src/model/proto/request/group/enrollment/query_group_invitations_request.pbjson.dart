///
//  Generated code. Do not modify.
//  source: request/group/enrollment/query_group_invitations_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use queryGroupInvitationsRequestDescriptor instead')
const QueryGroupInvitationsRequest$json = const {
  '1': 'QueryGroupInvitationsRequest',
  '2': const [
    const {
      '1': 'group_id',
      '3': 1,
      '4': 1,
      '5': 3,
      '9': 0,
      '10': 'groupId',
      '17': true
    },
    const {
      '1': 'are_sent_by_me',
      '3': 2,
      '4': 1,
      '5': 8,
      '9': 1,
      '10': 'areSentByMe',
      '17': true
    },
    const {
      '1': 'last_updated_date',
      '3': 3,
      '4': 1,
      '5': 3,
      '9': 2,
      '10': 'lastUpdatedDate',
      '17': true
    },
  ],
  '8': const [
    const {'1': '_group_id'},
    const {'1': '_are_sent_by_me'},
    const {'1': '_last_updated_date'},
  ],
};

/// Descriptor for `QueryGroupInvitationsRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List queryGroupInvitationsRequestDescriptor =
    $convert.base64Decode(
        'ChxRdWVyeUdyb3VwSW52aXRhdGlvbnNSZXF1ZXN0Eh4KCGdyb3VwX2lkGAEgASgDSABSB2dyb3VwSWSIAQESKAoOYXJlX3NlbnRfYnlfbWUYAiABKAhIAVILYXJlU2VudEJ5TWWIAQESLwoRbGFzdF91cGRhdGVkX2RhdGUYAyABKANIAlIPbGFzdFVwZGF0ZWREYXRliAEBQgsKCV9ncm91cF9pZEIRCg9fYXJlX3NlbnRfYnlfbWVCFAoSX2xhc3RfdXBkYXRlZF9kYXRl');
