///
//  Generated code. Do not modify.
//  source: request/user/relationship/update_relationship_group_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,deprecated_member_use_from_same_package,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;
import 'dart:convert' as $convert;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use updateRelationshipGroupRequestDescriptor instead')
const UpdateRelationshipGroupRequest$json = const {
  '1': 'UpdateRelationshipGroupRequest',
  '2': const [
    const {'1': 'group_index', '3': 1, '4': 1, '5': 5, '10': 'groupIndex'},
    const {'1': 'new_name', '3': 2, '4': 1, '5': 9, '10': 'newName'},
  ],
};

/// Descriptor for `UpdateRelationshipGroupRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List updateRelationshipGroupRequestDescriptor =
    $convert.base64Decode(
        'Ch5VcGRhdGVSZWxhdGlvbnNoaXBHcm91cFJlcXVlc3QSHwoLZ3JvdXBfaW5kZXgYASABKAVSCmdyb3VwSW5kZXgSGQoIbmV3X25hbWUYAiABKAlSB25ld05hbWU=');
