//
//  Generated code. Do not modify.
//  source: model/user/user_friend_request.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userFriendRequestDescriptor instead')
const UserFriendRequest$json = {
  '1': 'UserFriendRequest',
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
      '1': 'request_status',
      '3': 4,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.RequestStatus',
      '9': 3,
      '10': 'requestStatus',
      '17': true
    },
    {'1': 'reason', '3': 5, '4': 1, '5': 9, '9': 4, '10': 'reason', '17': true},
    {
      '1': 'expiration_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'expirationDate',
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
      '1': 'recipient_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'recipientId',
      '17': true
    },
  ],
  '8': [
    {'1': '_id'},
    {'1': '_creation_date'},
    {'1': '_content'},
    {'1': '_request_status'},
    {'1': '_reason'},
    {'1': '_expiration_date'},
    {'1': '_requester_id'},
    {'1': '_recipient_id'},
  ],
};

/// Descriptor for `UserFriendRequest`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userFriendRequestDescriptor = $convert.base64Decode(
    'ChFVc2VyRnJpZW5kUmVxdWVzdBITCgJpZBgBIAEoA0gAUgJpZIgBARIoCg1jcmVhdGlvbl9kYX'
    'RlGAIgASgDSAFSDGNyZWF0aW9uRGF0ZYgBARIdCgdjb250ZW50GAMgASgJSAJSB2NvbnRlbnSI'
    'AQESSQoOcmVxdWVzdF9zdGF0dXMYBCABKA4yHS5pbS50dXJtcy5wcm90by5SZXF1ZXN0U3RhdH'
    'VzSANSDXJlcXVlc3RTdGF0dXOIAQESGwoGcmVhc29uGAUgASgJSARSBnJlYXNvbogBARIsCg9l'
    'eHBpcmF0aW9uX2RhdGUYBiABKANIBVIOZXhwaXJhdGlvbkRhdGWIAQESJgoMcmVxdWVzdGVyX2'
    'lkGAcgASgDSAZSC3JlcXVlc3RlcklkiAEBEiYKDHJlY2lwaWVudF9pZBgIIAEoA0gHUgtyZWNp'
    'cGllbnRJZIgBAUIFCgNfaWRCEAoOX2NyZWF0aW9uX2RhdGVCCgoIX2NvbnRlbnRCEQoPX3JlcX'
    'Vlc3Rfc3RhdHVzQgkKB19yZWFzb25CEgoQX2V4cGlyYXRpb25fZGF0ZUIPCg1fcmVxdWVzdGVy'
    'X2lkQg8KDV9yZWNpcGllbnRfaWQ=');
