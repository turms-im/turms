//
//  Generated code. Do not modify.
//  source: model/file/image_file.proto
//
// @dart = 2.12

// ignore_for_file: annotate_overrides, camel_case_types, comment_references
// ignore_for_file: constant_identifier_names, library_prefixes
// ignore_for_file: non_constant_identifier_names, prefer_final_fields
// ignore_for_file: unnecessary_import, unnecessary_this, unused_import

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class ImageFile_Description extends $pb.GeneratedMessage {
  factory ImageFile_Description({
    $core.String? url,
    $core.bool? original,
    $core.int? imageSize,
    $core.int? fileSize,
  }) {
    final $result = create();
    if (url != null) {
      $result.url = url;
    }
    if (original != null) {
      $result.original = original;
    }
    if (imageSize != null) {
      $result.imageSize = imageSize;
    }
    if (fileSize != null) {
      $result.fileSize = fileSize;
    }
    return $result;
  }
  ImageFile_Description._() : super();
  factory ImageFile_Description.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory ImageFile_Description.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'ImageFile.Description',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOS(1, _omitFieldNames ? '' : 'url')
    ..aOB(2, _omitFieldNames ? '' : 'original')
    ..a<$core.int>(3, _omitFieldNames ? '' : 'imageSize', $pb.PbFieldType.O3)
    ..a<$core.int>(4, _omitFieldNames ? '' : 'fileSize', $pb.PbFieldType.O3)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  ImageFile_Description clone() =>
      ImageFile_Description()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  ImageFile_Description copyWith(
          void Function(ImageFile_Description) updates) =>
      super.copyWith((message) => updates(message as ImageFile_Description))
          as ImageFile_Description;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static ImageFile_Description create() => ImageFile_Description._();
  ImageFile_Description createEmptyInstance() => create();
  static $pb.PbList<ImageFile_Description> createRepeated() =>
      $pb.PbList<ImageFile_Description>();
  @$core.pragma('dart2js:noInline')
  static ImageFile_Description getDefault() => _defaultInstance ??=
      $pb.GeneratedMessage.$_defaultFor<ImageFile_Description>(create);
  static ImageFile_Description? _defaultInstance;

  @$pb.TagNumber(1)
  $core.String get url => $_getSZ(0);
  @$pb.TagNumber(1)
  set url($core.String v) {
    $_setString(0, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasUrl() => $_has(0);
  @$pb.TagNumber(1)
  void clearUrl() => clearField(1);

  @$pb.TagNumber(2)
  $core.bool get original => $_getBF(1);
  @$pb.TagNumber(2)
  set original($core.bool v) {
    $_setBool(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasOriginal() => $_has(1);
  @$pb.TagNumber(2)
  void clearOriginal() => clearField(2);

  @$pb.TagNumber(3)
  $core.int get imageSize => $_getIZ(2);
  @$pb.TagNumber(3)
  set imageSize($core.int v) {
    $_setSignedInt32(2, v);
  }

  @$pb.TagNumber(3)
  $core.bool hasImageSize() => $_has(2);
  @$pb.TagNumber(3)
  void clearImageSize() => clearField(3);

  @$pb.TagNumber(4)
  $core.int get fileSize => $_getIZ(3);
  @$pb.TagNumber(4)
  set fileSize($core.int v) {
    $_setSignedInt32(3, v);
  }

  @$pb.TagNumber(4)
  $core.bool hasFileSize() => $_has(3);
  @$pb.TagNumber(4)
  void clearFileSize() => clearField(4);
}

class ImageFile extends $pb.GeneratedMessage {
  factory ImageFile({
    ImageFile_Description? description,
    $core.List<$core.int>? data,
  }) {
    final $result = create();
    if (description != null) {
      $result.description = description;
    }
    if (data != null) {
      $result.data = data;
    }
    return $result;
  }
  ImageFile._() : super();
  factory ImageFile.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory ImageFile.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);

  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      _omitMessageNames ? '' : 'ImageFile',
      package: const $pb.PackageName(_omitMessageNames ? '' : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOM<ImageFile_Description>(1, _omitFieldNames ? '' : 'description',
        subBuilder: ImageFile_Description.create)
    ..a<$core.List<$core.int>>(
        2, _omitFieldNames ? '' : 'data', $pb.PbFieldType.OY)
    ..hasRequiredFields = false;

  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  ImageFile clone() => ImageFile()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  ImageFile copyWith(void Function(ImageFile) updates) =>
      super.copyWith((message) => updates(message as ImageFile)) as ImageFile;

  $pb.BuilderInfo get info_ => _i;

  @$core.pragma('dart2js:noInline')
  static ImageFile create() => ImageFile._();
  ImageFile createEmptyInstance() => create();
  static $pb.PbList<ImageFile> createRepeated() => $pb.PbList<ImageFile>();
  @$core.pragma('dart2js:noInline')
  static ImageFile getDefault() =>
      _defaultInstance ??= $pb.GeneratedMessage.$_defaultFor<ImageFile>(create);
  static ImageFile? _defaultInstance;

  @$pb.TagNumber(1)
  ImageFile_Description get description => $_getN(0);
  @$pb.TagNumber(1)
  set description(ImageFile_Description v) {
    setField(1, v);
  }

  @$pb.TagNumber(1)
  $core.bool hasDescription() => $_has(0);
  @$pb.TagNumber(1)
  void clearDescription() => clearField(1);
  @$pb.TagNumber(1)
  ImageFile_Description ensureDescription() => $_ensure(0);

  @$pb.TagNumber(2)
  $core.List<$core.int> get data => $_getN(1);
  @$pb.TagNumber(2)
  set data($core.List<$core.int> v) {
    $_setBytes(1, v);
  }

  @$pb.TagNumber(2)
  $core.bool hasData() => $_has(1);
  @$pb.TagNumber(2)
  void clearData() => clearField(2);
}

const _omitFieldNames = $core.bool.fromEnvironment('protobuf.omit_field_names');
const _omitMessageNames =
    $core.bool.fromEnvironment('protobuf.omit_message_names');
