//
//  Generated code. Do not modify.
//  source: model/common/strings_with_version.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:fixnum/fixnum.dart' as $fixnum;
import 'package:protobuf/protobuf.dart' as $pb;

class StringsWithVersion extends $pb.GeneratedMessage {
  factory StringsWithVersion({
    $core.Iterable<$core.String>? strings,
    $fixnum.Int64? lastUpdatedDate,
  }) {
    final $result = create();
    if (strings != null) {
      $result.strings.addAll(strings);
    }
    if (lastUpdatedDate != null) {
      $result.lastUpdatedDate = lastUpdatedDate;
    }
    return $result;
  }
  StringsWithVersion._() : super();
  factory StringsWithVersion.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory StringsWithVersion.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'StringsWithVersion',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..pPS(1, _omitFieldNames ? '' : 'strings')
    ..aInt64(2, _omitFieldNames ? '' : 'lastUpdatedDate')
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  StringsWithVersion clone() => StringsWithVersion()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  StringsWithVersion copyWith(void Function(StringsWithVersion) updates) =>
      super.copyWith((message) => updates(message as StringsWithVersion))
          as StringsWithVersion;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static StringsWithVersion create() => StringsWithVersion._();
  StringsWithVersion createEmptyInstance() => create();
  static $pb.PbList<StringsWithVersion> createRepeated() =>
      $pb.PbList<StringsWithVersion>();
  @$core.pragma('dart2js:noInline')
  static StringsWithVersion getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<StringsWithVersion>(create);
  static StringsWithVersion? _defaultInstance;

  @$pb.TagNumber(1)
  $core.List<$core.String> get strings => $_getList(0);

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
