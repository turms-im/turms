//
//  Generated code. Do not modify.
//  source: model/storage/storage_resource_infos.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

import 'storage_resource_info.pb.dart' as $0;

class StorageResourceInfos extends $pb.GeneratedMessage {
  factory StorageResourceInfos({
    $core.Iterable<$0.StorageResourceInfo>? infos,
  }) {
    final $result = create();
    if (infos != null) {
      $result.infos.addAll(infos);
    }
    return $result;
  }
  StorageResourceInfos._() : super();
  factory StorageResourceInfos.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory StorageResourceInfos.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'StorageResourceInfos',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pc<$0.StorageResourceInfo>(
        1, _omitFieldNames ? '' : 'infos', $pb.PbFieldType.PM,
        subBuilder: $0.StorageResourceInfo.create)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  StorageResourceInfos clone() =>
      StorageResourceInfos()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  StorageResourceInfos copyWith(void Function(StorageResourceInfos) updates) =>
      super.copyWith((message) => updates(message as StorageResourceInfos))
          as StorageResourceInfos;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static StorageResourceInfos create() => StorageResourceInfos._();
  StorageResourceInfos createEmptyInstance() => create();
  static $pb.PbList<StorageResourceInfos> createRepeated() =>
      $pb.PbList<StorageResourceInfos>();
  @$core.pragma('dart2js:noInline')
  static StorageResourceInfos getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<StorageResourceInfos>(create);
  static StorageResourceInfos? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$0.StorageResourceInfo> get infos => $_getList(0);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
