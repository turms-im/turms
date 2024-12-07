import 'image.dart';

class GiphyImages {
  GiphyImages({
    required this.fixedHeightStill,
    required this.originalStill,
    required this.fixedWidth,
    this.fixedHeightSmallStill,
    this.fixedHeightDownsampled,
    this.preview,
    this.fixedHeightSmall,
    this.downsizedStill,
    this.downsized,
    this.downsizedLarge,
    this.fixedWidthSmallStill,
    this.previewWebp,
    this.fixedWidthStill,
    this.fixedWidthSmall,
    this.downsizedSmall,
    this.fixedWidthDownsampled,
    this.downsizedMedium,
    this.original,
    this.fixedHeight,
    this.hd,
    this.looping,
    this.originalMp4,
    this.previewGif,
    this.w480Still,
  });

  factory GiphyImages.fromJson(Map<String, dynamic> json) => GiphyImages(
        fixedHeightStill: GiphyStillImage.fromJson(
            json['fixed_height_still'] as Map<String, dynamic>),
        originalStill: GiphyStillImage.fromJson(
            json['original_still'] as Map<String, dynamic>),
        fixedWidth: GiphyFullImage.fromJson(
            json['fixed_width'] as Map<String, dynamic>),
        fixedHeightSmallStill: json['fixed_height_small_still'] == null ||
                (json['fixed_height_small_still'] as Map<String, dynamic>)
                    .isEmpty
            ? null
            : GiphyStillImage.fromJson(
                json['fixed_height_small_still'] as Map<String, dynamic>),
        fixedHeightDownsampled: json['fixed_height_downsampled'] == null ||
                (json['fixed_height_downsampled'] as Map<String, dynamic>)
                    .isEmpty
            ? null
            : GiphyDownsampledImage.fromJson(
                json['fixed_height_downsampled'] as Map<String, dynamic>),
        preview: json['preview'] == null ||
                (json['preview'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyPreviewImage.fromJson(
                json['preview'] as Map<String, dynamic>),
        fixedHeightSmall: json['fixed_height_small'] == null ||
                (json['fixed_height_small'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyFullImage.fromJson(
                json['fixed_height_small'] as Map<String, dynamic>),
        downsizedStill: json['downsized_still'] == null ||
                (json['downsized_still'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyStillImage.fromJson(
                json['downsized_still'] as Map<String, dynamic>),
        downsized: json['downsized'] == null ||
                (json['downsized'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyDownsizedImage.fromJson(
                json['downsized'] as Map<String, dynamic>),
        downsizedLarge: json['downsized_large'] == null ||
                (json['downsized_large'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyDownsizedImage.fromJson(
                json['downsized_large'] as Map<String, dynamic>),
        fixedWidthSmallStill: json['fixed_width_small_still'] == null ||
                (json['fixed_width_small_still'] as Map<String, dynamic>)
                    .isEmpty
            ? null
            : GiphyStillImage.fromJson(
                json['fixed_width_small_still'] as Map<String, dynamic>),
        previewWebp: json['preview_webp'] == null ||
                (json['preview_webp'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyWebPImage.fromJson(
                json['preview_webp'] as Map<String, dynamic>),
        fixedWidthStill: json['fixed_width_still'] == null ||
                (json['fixed_width_still'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyStillImage.fromJson(
                json['fixed_width_still'] as Map<String, dynamic>),
        fixedWidthSmall: json['fixed_width_small'] == null ||
                (json['fixed_width_small'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyFullImage.fromJson(
                json['fixed_width_small'] as Map<String, dynamic>),
        downsizedSmall: json['downsized_small'] == null ||
                (json['downsized_small'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyPreviewImage.fromJson(
                json['downsized_small'] as Map<String, dynamic>),
        fixedWidthDownsampled: json['fixed_width_downsampled'] == null ||
                (json['fixed_width_downsampled'] as Map<String, dynamic>)
                    .isEmpty
            ? null
            : GiphyDownsampledImage.fromJson(
                json['fixed_width_downsampled'] as Map<String, dynamic>),
        downsizedMedium: json['downsized_medium'] == null ||
                (json['downsized_medium'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyPreviewImage.fromJson(
                json['downsized_medium'] as Map<String, dynamic>),
        original: json['original'] == null ||
                (json['original'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyOriginalImage.fromJson(
                json['original'] as Map<String, dynamic>),
        fixedHeight: json['fixed_height'] == null ||
                (json['fixed_height'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyFullImage.fromJson(
                json['fixed_height'] as Map<String, dynamic>),
        hd: json['hd'] == null
            ? null
            : GiphyPreviewImage.fromJson(json['hd'] as Map<String, dynamic>),
        looping: json['looping'] == null ||
                (json['looping'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyLoopingImage.fromJson(
                json['looping'] as Map<String, dynamic>),
        originalMp4: json['original_mp4'] == null ||
                (json['original_mp4'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyPreviewImage.fromJson(
                json['original_mp4'] as Map<String, dynamic>),
        previewGif: json['preview_gif'] == null ||
                (json['preview_gif'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyDownsizedImage.fromJson(
                json['preview_gif'] as Map<String, dynamic>),
        w480Still: json['480w_still'] == null ||
                (json['480w_still'] as Map<String, dynamic>).isEmpty
            ? null
            : GiphyStillImage.fromJson(
                json['480w_still'] as Map<String, dynamic>),
      );

  GiphyStillImage fixedHeightStill;
  GiphyStillImage originalStill;
  GiphyFullImage fixedWidth;
  GiphyStillImage? fixedHeightSmallStill;
  GiphyDownsampledImage? fixedHeightDownsampled;
  GiphyPreviewImage? preview;
  GiphyFullImage? fixedHeightSmall;
  GiphyStillImage? downsizedStill;
  GiphyDownsizedImage? downsized;
  GiphyDownsizedImage? downsizedLarge;
  GiphyStillImage? fixedWidthSmallStill;
  GiphyWebPImage? previewWebp;
  GiphyStillImage? fixedWidthStill;
  GiphyFullImage? fixedWidthSmall;
  GiphyPreviewImage? downsizedSmall;
  GiphyDownsampledImage? fixedWidthDownsampled;
  GiphyPreviewImage? downsizedMedium;
  GiphyOriginalImage? original;
  GiphyFullImage? fixedHeight;
  GiphyPreviewImage? hd;
  GiphyLoopingImage? looping;
  GiphyPreviewImage? originalMp4;
  GiphyDownsizedImage? previewGif;
  GiphyStillImage? w480Still;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'fixed_height_still': fixedHeightStill.toJson(),
        'original_still': originalStill.toJson(),
        'fixed_width': fixedWidth.toJson(),
        'fixed_height_small_still': fixedHeightSmallStill?.toJson(),
        'fixed_height_downsampled': fixedHeightDownsampled?.toJson(),
        'preview': preview?.toJson(),
        'fixed_height_small': fixedHeightSmall?.toJson(),
        'downsized_still': downsizedStill?.toJson(),
        'downsized': downsized?.toJson(),
        'downsized_large': downsizedLarge?.toJson(),
        'fixed_width_small_still': fixedWidthSmallStill?.toJson(),
        'preview_webp': previewWebp?.toJson(),
        'fixed_width_still': fixedWidthStill?.toJson(),
        'fixed_width_small': fixedWidthSmall?.toJson(),
        'downsized_small': downsizedSmall?.toJson(),
        'fixed_width_downsampled': fixedWidthDownsampled?.toJson(),
        'downsized_medium': downsizedMedium?.toJson(),
        'original': original?.toJson(),
        'fixed_height': fixedHeight?.toJson(),
        'hd': hd?.toJson(),
        'looping': looping?.toJson(),
        'original_mp4': originalMp4?.toJson(),
        'preview_gif': previewGif?.toJson(),
        '480w_still': w480Still?.toJson()
      };

  @override
  String toString() =>
      'GiphyImages{fixedHeightStill: $fixedHeightStill, originalStill: $originalStill, fixedWidth: $fixedWidth, fixedHeightSmallStill: $fixedHeightSmallStill, fixedHeightDownsampled: $fixedHeightDownsampled, preview: $preview, fixedHeightSmall: $fixedHeightSmall, downsizedStill: $downsizedStill, downsized: $downsized, downsizedLarge: $downsizedLarge, fixedWidthSmallStill: $fixedWidthSmallStill, previewWebp: $previewWebp, fixedWidthStill: $fixedWidthStill, fixedWidthSmall: $fixedWidthSmall, downsizedSmall: $downsizedSmall, fixedWidthDownsampled: $fixedWidthDownsampled, downsizedMedium: $downsizedMedium, original: $original, fixedHeight: $fixedHeight, hd: $hd, looping: $looping, originalMp4: $originalMp4, previewGif: $previewGif, w480Still: $w480Still}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyImages &&
          runtimeType == other.runtimeType &&
          fixedHeightStill == other.fixedHeightStill &&
          originalStill == other.originalStill &&
          fixedWidth == other.fixedWidth &&
          fixedHeightSmallStill == other.fixedHeightSmallStill &&
          fixedHeightDownsampled == other.fixedHeightDownsampled &&
          preview == other.preview &&
          fixedHeightSmall == other.fixedHeightSmall &&
          downsizedStill == other.downsizedStill &&
          downsized == other.downsized &&
          downsizedLarge == other.downsizedLarge &&
          fixedWidthSmallStill == other.fixedWidthSmallStill &&
          previewWebp == other.previewWebp &&
          fixedWidthStill == other.fixedWidthStill &&
          fixedWidthSmall == other.fixedWidthSmall &&
          downsizedSmall == other.downsizedSmall &&
          fixedWidthDownsampled == other.fixedWidthDownsampled &&
          downsizedMedium == other.downsizedMedium &&
          original == other.original &&
          fixedHeight == other.fixedHeight &&
          hd == other.hd &&
          looping == other.looping &&
          originalMp4 == other.originalMp4 &&
          previewGif == other.previewGif &&
          w480Still == other.w480Still;

  @override
  int get hashCode =>
      fixedHeightStill.hashCode ^
      originalStill.hashCode ^
      fixedWidth.hashCode ^
      fixedHeightSmallStill.hashCode ^
      fixedHeightDownsampled.hashCode ^
      preview.hashCode ^
      fixedHeightSmall.hashCode ^
      downsizedStill.hashCode ^
      downsized.hashCode ^
      downsizedLarge.hashCode ^
      fixedWidthSmallStill.hashCode ^
      previewWebp.hashCode ^
      fixedWidthStill.hashCode ^
      fixedWidthSmall.hashCode ^
      downsizedSmall.hashCode ^
      fixedWidthDownsampled.hashCode ^
      downsizedMedium.hashCode ^
      original.hashCode ^
      fixedHeight.hashCode ^
      hd.hashCode ^
      looping.hashCode ^
      originalMp4.hashCode ^
      previewGif.hashCode ^
      w480Still.hashCode;
}
