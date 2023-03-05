///
//  Generated code. Do not modify.
//  source: request/storage/delete_resource_request.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

import '../../constant/storage_resource_type.pbenum.dart' as $0;

class DeleteResourceRequest extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'DeleteResourceRequest',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..e<$0.StorageResourceType>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'type',
        $pb.PbFieldType.OE,
        defaultOrMaker: $0.StorageResourceType.USER_PROFILE_PICTURE,
        valueOf: $0.StorageResourceType.valueOf,
        enumValues: $0.StorageResourceType.values)
    ..aInt64(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'idNum')
    ..aOS(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'idStr')
    ..m<$core.String, $core.String>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'extra',
        entryClassName: 'DeleteResourceRequest.ExtraEntry',
        keyFieldType: $pb.PbFieldType.OS,
        valueFieldType: $pb.PbFieldType.OS,
        packageName: const $pb.PackageName('im.turms.proto'))
    ..hasRequiredFields = false;

  DeleteResourceRequest._() : super();
  factory DeleteResourceRequest({
    $0.StorageResourceType? type,
    $fixnum.Int64? idNum,
    $core.String? idStr,
    $core.Map<$core.String, $core.String>? extra,
  }) {
    final _result = create();
    if (type != null) {
      _result.type = type;
    }
    if (idNum != null) {
      _result.idNum = idNum;
    }
    if (idStr != null) {
      _result.idStr = idStr;
    }
    if (extra != null) {
      _result.extra.addAll(extra);
    }
    return _result;
  }
  factory DeleteResourceRequest.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory DeleteResourceRequest.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  DeleteResourceRequest clone() =>
      DeleteResourceRequest()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  DeleteResourceRequest copyWith(
          void Function(DeleteResourceRequest) updates) =>
      super.copyWith((message) => updates(message as DeleteResourceRequest))
          as DeleteResourceRequest; // ignore: deprecated_member_use
  $pb.BuilderInfo get info_ => _i;
  @$core.pragma('dart2js:noInline')
  static DeleteResourceRequest create() => DeleteResourceRequest._();
  DeleteResourceRequest createEmptyInstance() => create();
  static $pb.PbList<DeleteResourceRequest> createRepeated() =>
      $pb.PbList<DeleteResourceRequest>();
  @$core.pragma('dart2js:noInline')
  static DeleteResourceRequest getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<DeleteResourceRequest>(create);
  static DeleteResourceRequest? _defaultInstance;

  @$pb.TagNumber(1)
  $0.StorageResourceType get type => $_getN(0);
  @$pb.TagNumber(1)
  set type($0.StorageResourceType v) {
    setField(1, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasType() => $_has(0);
  @$pb.TagNumber(1)
  void clearType() => clearField(1);

  @$pb.TagNumber(2)
  $fixnum.Int64 get idNum => $_getI64(1);
  @$pb.TagNumber(2)
  set idNum($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasIdNum() => $_has(1);
  @$pb.TagNumber(2)
  void clearIdNum() => clearField(2);

  @$pb.TagNumber(3)
  $core.String get idStr => $_getSZ(2);
  @$pb.TagNumber(3)
  set idStr($core.String v) {
    $_setString(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasIdStr() => $_has(2);
  @$pb.TagNumber(3)
  void clearIdStr() => clearField(3);

  @$pb.TagNumber(4)
  $core.Map<$core.String, $core.String> get extra => $_getMap(3);
}
