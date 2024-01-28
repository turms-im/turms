//
//  Generated code. Do not modify.
//  source: constant/storage_resource_type.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class StorageResourceType extends $pb.ProtobufEnum {
  static const StorageResourceType USER_PROFILE_PICTURE =
      StorageResourceType._(0, _omitEnumNames ? '' : 'USER_PROFILE_PICTURE');
  static const StorageResourceType GROUP_PROFILE_PICTURE =
      StorageResourceType._(1, _omitEnumNames ? '' : 'GROUP_PROFILE_PICTURE');
  static const StorageResourceType MESSAGE_ATTACHMENT =
      StorageResourceType._(2, _omitEnumNames ? '' : 'MESSAGE_ATTACHMENT');

  static const $core.List<StorageResourceType> values = <StorageResourceType>[
    USER_PROFILE_PICTURE,
    GROUP_PROFILE_PICTURE,
    MESSAGE_ATTACHMENT,
  ];

  static final $core.Map<$core.int, StorageResourceType> _byValue =
      $pb.ProtobufEnum.initByValue(values);
  static StorageResourceType? valueOf($core.int value) => _byValue[value];

  const StorageResourceType._($core.int v, $core.String n) : super(v, n);
}

const _omitEnumNames = $core.bool.fromEnvironment('protobuf.omit_enum_names');
