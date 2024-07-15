//
//  Generated code. Do not modify.
//  source: model/message/message_reaction_group.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use messageReactionGroupDescriptor instead')
const MessageReactionGroup$json = {
  '1': 'MessageReactionGroup',
  '2': [
    {'1': 'reaction_type', '3': 1, '4': 1, '5': 5, '10': 'reactionType'},
    {'1': 'reaction_count', '3': 2, '4': 1, '5': 5, '10': 'reactionCount'},
    {
      '1': 'user_infos',
      '3': 3,
      '4': 3,
      '5': 11,
      '6': '.im.turms.proto.UserInfo',
      '10': 'userInfos'
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
};

/// Descriptor for `MessageReactionGroup`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List messageReactionGroupDescriptor = $convert.base64Decode(
    'ChRNZXNzYWdlUmVhY3Rpb25Hcm91cBIjCg1yZWFjdGlvbl90eXBlGAEgASgFUgxyZWFjdGlvbl'
    'R5cGUSJQoOcmVhY3Rpb25fY291bnQYAiABKAVSDXJlYWN0aW9uQ291bnQSNwoKdXNlcl9pbmZv'
    'cxgDIAMoCzIYLmltLnR1cm1zLnByb3RvLlVzZXJJbmZvUgl1c2VySW5mb3MSQgoRY3VzdG9tX2'
    'F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmlidXRl'
    'cw==');
