//
//  Generated code. Do not modify.
//  source: model/common/longs_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class LongsWithVersion extends $pb.GeneratedMessage {
  factory LongsWithVersion({
    $core.Iterable<$fixnum.Int64>? longs,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (longs != null) {
      $result.longs.addAll(longs);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  LongsWithVersion._() : super();
  factory LongsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory LongsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'LongsWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..p<$fixnum.Int64>(1, _omitFieldNames ? '' : 'longs', $pb.PbFieldType.K6)
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  LongsWithVersion clone() => LongsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  LongsWithVersion copyWith(void Function(LongsWithVersion) updates) =>
      super.copyWith((message) => updates(message as LongsWithVersion))
          as LongsWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static LongsWithVersion create() => LongsWithVersion._();
  LongsWithVersion createEmptyInstance() => create();
  static $pb.PbList<LongsWithVersion> createRepeated() =>
      $pb.PbList<LongsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static LongsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<LongsWithVersion>(create);
  static LongsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$fixnum.Int64> get longs => $_getList(0);

  @$pb.TagNumber(2)
  $fixnum.Int64 get lastUpdatedDate => $_getI64(1);
  @$pb.TagNumber(2)
  set lastUpdatedDate($fixnum.Int64 v) {
    $_setInt64(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasLastUpdatedDate() => $_has(1);
  @$pb.TagNumber(2)
  void clearLastUpdatedDate() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
