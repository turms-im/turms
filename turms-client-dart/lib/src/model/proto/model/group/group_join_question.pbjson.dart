//
//  Generated code. Do not modify.
//  source: model/group/group_join_question.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupJoinQuestionDescriptor instead')
const GroupJoinQuestion$json = {
  '1': 'GroupJoinQuestion',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {
      '1': 'group_id',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'question',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'question',
      '17': true
    },
    {'1': 'answers', '3': 4, '4': 3, '5': 9, '10': 'answers'},
    {'1': 'score', '3': 5, '4': 1, '5': 5, '9': 3, '10': 'score', '17': true},
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
    {'1': '_id'},
    {'1': '_group_id'},
    {'1': '_question'},
    {'1': '_score'},
  ],
};

/// Descriptor for `GroupJoinQuestion`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupJoinQuestionDescriptor = $convert.base64Decode(
    'ChFHcm91cEpvaW5RdWVzdGlvbhITCgJpZBgBIAEoA0gAUgJpZIgBARIeCghncm91cF9pZBgCIA'
    'EoA0gBUgdncm91cElkiAEBEh8KCHF1ZXN0aW9uGAMgASgJSAJSCHF1ZXN0aW9uiAEBEhgKB2Fu'
    'c3dlcnMYBCADKAlSB2Fuc3dlcnMSGQoFc2NvcmUYBSABKAVIA1IFc2NvcmWIAQESQgoRY3VzdG'
    '9tX2F0dHJpYnV0ZXMYDyADKAsyFS5pbS50dXJtcy5wcm90by5WYWx1ZVIQY3VzdG9tQXR0cmli'
    'dXRlc0IFCgNfaWRCCwoJX2dyb3VwX2lkQgsKCV9xdWVzdGlvbkIICgZfc2NvcmU=');
