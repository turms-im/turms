//
//  Generated code. Do not modify.
//  source: model/user/user_info.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:convert' as $convert;
import 'dart:core' as $core;
import 'dart:typed_data' as $typed_data;

@$core.Deprecated('Use userInfoDescriptor instead')
const UserInfo$json = {
  '1': 'UserInfo',
  '2': [
    {'1': 'id', '3': 1, '4': 1, '5': 3, '9': 0, '10': 'id', '17': true},
    {'1': 'name', '3': 2, '4': 1, '5': 9, '9': 1, '10': 'name', '17': true},
    {'1': 'intro', '3': 3, '4': 1, '5': 9, '9': 2, '10': 'intro', '17': true},
    {
      '1': 'profile_picture',
      '3': 4,
      '4': 1,
      '5': 9,
      '9': 3,
      '10': 'profilePicture',
      '17': true
    },
    {
      '1': 'profile_access_strategy',
      '3': 5,
      '4': 1,
      '5': 14,
      '6': '.im.turms.proto.ProfileAccessStrategy',
      '9': 4,
      '10': 'profileAccessStrategy',
      '17': true
    },
    {
      '1': 'registration_date',
      '3': 6,
      '4': 1,
      '5': 3,
      '9': 5,
      '10': 'registrationDate',
      '17': true
    },
    {
      '1': 'last_updated_date',
      '3': 7,
      '4': 1,
      '5': 3,
      '9': 6,
      '10': 'lastUpdatedDate',
      '17': true
    },
    {'1': 'active', '3': 8, '4': 1, '5': 8, '9': 7, '10': 'active', '17': true},
  ],
  '8': [
    {'1': '_id'},
    {'1': '_name'},
    {'1': '_intro'},
    {'1': '_profile_picture'},
    {'1': '_profile_access_strategy'},
    {'1': '_registration_date'},
    {'1': '_last_updated_date'},
    {'1': '_active'},
  ],
};

/// Descriptor for `UserInfo`. Decode as a `google.protobuf.DescriptorProto`.
final $typed_data.Uint8List userInfoDescriptor = $convert.base64Decode(
    'CghVc2VySW5mbxITCgJpZBgBIAEoA0gAUgJpZIgBARIXCgRuYW1lGAIgASgJSAFSBG5hbWWIAQ'
    'ESGQoFaW50cm8YAyABKAlIAlIFaW50cm+IAQESLAoPcHJvZmlsZV9waWN0dXJlGAQgASgJSANS'
    'DnByb2ZpbGVQaWN0dXJliAEBEmIKF3Byb2ZpbGVfYWNjZXNzX3N0cmF0ZWd5GAUgASgOMiUuaW'
    '0udHVybXMucHJvdG8uUHJvZmlsZUFjY2Vzc1N0cmF0ZWd5SARSFXByb2ZpbGVBY2Nlc3NTdHJh'
    'dGVneYgBARIwChFyZWdpc3RyYXRpb25fZGF0ZRgGIAEoA0gFUhByZWdpc3RyYXRpb25EYXRliA'
    'EBEi8KEWxhc3RfdXBkYXRlZF9kYXRlGAcgASgDSAZSD2xhc3RVcGRhdGVkRGF0ZYgBARIbCgZh'
    'Y3RpdmUYCCABKAhIB1IGYWN0aXZliAEBQgUKA19pZEIHCgVfbmFtZUIICgZfaW50cm9CEgoQX3'
    'Byb2ZpbGVfcGljdHVyZUIaChhfcHJvZmlsZV9hY2Nlc3Nfc3RyYXRlZ3lCFAoSX3JlZ2lzdHJh'
    'dGlvbl9kYXRlQhQKEl9sYXN0X3VwZGF0ZWRfZGF0ZUIJCgdfYWN0aXZl');
