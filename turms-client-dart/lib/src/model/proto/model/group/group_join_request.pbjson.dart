//
//  Generated code. Do not modify.
//  source: model/group/group_join_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupJoinRequestDescriptor instead')
const GroupJoinRequest$json = {
  '1': 'GroupJoinRequest',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {
      '1': 'creation_date',
      '3': 2,
      '4': 1,
      '5': 3,
      '9': 1,
      '10': 'creationDate',
      '17': true
    },
    {
      '1': 'content',
      '3': 3,
      '4': 1,
      '5': 9,
      '9': 2,
      '10': 'content',
      '17': true
    },
    {
      '1': 'status',
      '3': 4,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.RequestStatus',
      '9': 3,
      '10': 'status',
      '17': true
    },
    {
      '1': 'expiration_date',
      '3': 5,
      '4': 1,
      '5': 3,
      '9': 4,
      '10': 'expirationDate',
      '17': true
    },
    {
      '1': 'group_id',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'groupId',
      '17': true
    },
    {
      '1': 'requester_id',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'requesterId',
      '17': true
    },
    {
      '1': 'responder_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'responderId',
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
    {'1': '_id'},
    {'1': '_creation_date'},
    {'1': '_content'},
    {'1': '_status'},
    {'1': '_expiration_date'},
    {'1': '_group_id'},
    {'1': '_requester_id'},
    {'1': '_responder_id'},
  ],
};

/// Descriptor for `GroupJoinRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupJoinRequestDescriptor = $convert.base64Decode(
    'ChBHcm91cEpvaW5SZXF1ZXN0EhMKAmlkGAEgASgDSABSAmlkiAEBEigKDWNyZWF0aW9uX2RhdG'
    'UYAiABKANIAVIMY3JlYXRpb25EYXRliAEBEh0KB2NvbnRlbnQYAyABKAlIAlIHY29udGVudIgB'
    'ARI6CgZzdGF0dXMYBCABKA4yHS5pbS50dXJtcy5wcm90by5SZXF1ZXN0U3RhdHVzSANSBnN0YX'
    'R1c4gBARIsCg9leHBpcmF0aW9uX2RhdGUYBSABKANIBFIOZXhwaXJhdGlvbkRhdGWIAQESHgoI'
    'Z3JvdXBfaWQYBiABKANIBVIHZ3JvdXBJZIgBARImCgxyZXF1ZXN0ZXJfaWQYByABKANIBlILcm'
    'VxdWVzdGVySWSIAQESJgoMcmVzcG9uZGVyX2lkGAggASgDSAdSC3Jlc3BvbmRlcklkiAEBEkIK'
    'EWN1c3RvbV9hdHRyaWJ1dGVzGA8gAygLMhUuaW0udHVybXMucHJvdG8uVmFsdWVSEGN1c3RvbU'
    'F0dHJpYnV0ZXNCBQoDX2lkQhAKDl9jcmVhdGlvbl9kYXRlQgoKCF9jb250ZW50QgkKB19zdGF0'
    'dXNCEgoQX2V4cGlyYXRpb25fZGF0ZUILCglfZ3JvdXBfaWRCDwoNX3JlcXVlc3Rlcl9pZEIPCg'
    '1fcmVzcG9uZGVyX2lk');
