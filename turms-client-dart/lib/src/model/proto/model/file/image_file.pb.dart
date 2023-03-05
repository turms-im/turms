///
//  Generated code. Do not modify.
//  source: model/file/image_file.proto
//
// @dart = 2.12
// ignore_for_file: annotate_overrides,camel_case_types,constant_identifier_names,directives_ordering,library_prefixes,non_constant_identifier_names,prefer_final_fields,return_of_invalid_type,unnecessary_const,unnecessary_import,unnecessary_this,unused_import,unused_shown_name

import 'dart:core' as $core;

import 'package:protobuf/protobuf.dart' as $pb;

class ImageFile_Description extends $pb.GeneratedMessage {
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'ImageFile.Description',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOS(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'url')
    ..aOB(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'original')
    ..a<$core.int>(
        3,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'imageSize',
        $pb.PbFieldType.O3)
    ..a<$core.int>(
        4,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'fileSize',
        $pb.PbFieldType.O3)
    ..hasRequiredFields = false;

  ImageFile_Description._() : super();
  factory ImageFile_Description({
    $core.String? url,
    $core.bool? original,
    $core.int? imageSize,
    $core.int? fileSize,
  }) {
    final _result = create();
    if (url != null) {
      _result.url = url;
    }
    if (original != null) {
      _result.original = original;
    }
    if (imageSize != null) {
      _result.imageSize = imageSize;
    }
    if (fileSize != null) {
      _result.fileSize = fileSize;
    }
    return _result;
  }
  factory ImageFile_Description.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory ImageFile_Description.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
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
          as ImageFile_Description; // ignore: deprecated_member_use
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
  static final $pb.BuilderInfo _i = $pb.BuilderInfo(
      const $core.bool.fromEnvironment('protobuf.omit_message_names')
          ? ''
          : 'ImageFile',
      package: const $pb.PackageName(
          const $core.bool.fromEnvironment('protobuf.omit_message_names')
              ? ''
              : 'im.turms.proto'),
      createEmptyInstance: create)
    ..aOM<ImageFile_Description>(
        1,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'description',
        subBuilder: ImageFile_Description.create)
    ..a<$core.List<$core.int>>(
        2,
        const $core.bool.fromEnvironment('protobuf.omit_field_names')
            ? ''
            : 'data',
        $pb.PbFieldType.OY)
    ..hasRequiredFields = false;

  ImageFile._() : super();
  factory ImageFile({
    ImageFile_Description? description,
    $core.List<$core.int>? data,
  }) {
    final _result = create();
    if (description != null) {
      _result.description = description;
    }
    if (data != null) {
      _result.data = data;
    }
    return _result;
  }
  factory ImageFile.fromBuffer($core.List<$core.int> i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromBuffer(i, r);
  factory ImageFile.fromJson($core.String i,
          [$pb.ExtensionRegistry r = $pb.ExtensionRegistry.EMPTY]) =>
      create()..mergeFromJson(i, r);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.deepCopy] instead. '
      'Will be removed in next major version')
  ImageFile clone() => ImageFile()..mergeFromMessage(this);
  @$core.Deprecated('Using this can add significant overhead to your binary. '
      'Use [GeneratedMessageGenericExtensions.rebuild] instead. '
      'Will be removed in next major version')
  ImageFile copyWith(void Function(ImageFile) updates) =>
      super.copyWith((message) => updates(message as ImageFile))
          as ImageFile; // ignore: deprecated_member_use
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
