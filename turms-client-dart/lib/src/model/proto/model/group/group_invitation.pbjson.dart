//
//  Generated code. Do not modify.
//  source: model/group/group_invitation.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use groupInvitationDescriptor instead')
const GroupInvitation$json = {
  '1': 'GroupInvitation',
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
      '1': 'inviter_id',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'inviterId',
      '17': true
    },
    {
      '1': 'invitee_id',
      '3': 8,
      '4': 1,
      '5': 3,
      '9': 7,
      '10': 'inviteeId',
      '17': true
    },
  ],
  '8': [
    {'1': '_id'},
    {'1': '_creation_date'},
    {'1': '_content'},
    {'1': '_status'},
    {'1': '_expiration_date'},
    {'1': '_group_id'},
    {'1': '_inviter_id'},
    {'1': '_invitee_id'},
  ],
};

/// Descriptor for `GroupInvitation`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List groupInvitationDescriptor = $convert.base64Decode(
    'Cg9Hcm91cEludml0YXRpb24SEwoCaWQYASABKANIAFICaWSIAQESKAoNY3JlYXRpb25fZGF0ZR'
    'gCIAEoA0gBUgxjcmVhdGlvbkRhdGWIAQESHQoHY29udGVudBgDIAEoCUgCUgdjb250ZW50iAEB'
    'EjoKBnN0YXR1cxgEIAEoDjIdLmltLnR1cm1zLnByb3RvLlJlcXVlc3RTdGF0dXNIA1IGc3RhdH'
    'VziAEBEiwKD2V4cGlyYXRpb25fZGF0ZRgFIAEoA0gEUg5leHBpcmF0aW9uRGF0ZYgBARIeCghn'
    'cm91cF9pZBgGIAEoA0gFUgdncm91cElkiAEBEiIKCmludml0ZXJfaWQYByABKANIBlIJaW52aX'
    'RlcklkiAEBEiIKCmludml0ZWVfaWQYCCABKANIB1IJaW52aXRlZUlkiAEBQgUKA19pZEIQCg5f'
    'Y3JlYXRpb25fZGF0ZUIKCghfY29udGVudEIJCgdfc3RhdHVzQhIKEF9leHBpcmF0aW9uX2RhdG'
    'VCCwoJX2dyb3VwX2lkQg0KC19pbnZpdGVyX2lkQg0KC19pbnZpdGVlX2lk');
