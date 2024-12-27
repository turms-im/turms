class GiphyFullImage {
  factory GiphyFullImage.fromJson(Map<String, dynamic> json) => GiphyFullImage(
        url: json['url'] as String,
        width: json['width'] as String,
        height: json['height'] as String,
        size: json['size'] as String,
        mp4: json['mp4'] as String?,
        mp4Size: json['mp4_size'] as String?,
        webp: json['webp'] as String?,
        webpSize: json['webp_size'] as String?,
      );

  GiphyFullImage({
    required this.url,
    required this.width,
    required this.height,
    required this.size,
    required this.mp4,
    required this.mp4Size,
    required this.webp,
    required this.webpSize,
  });

  final String url;
  final String width;
  final String height;
  final String size;
  final String? mp4;
  final String? mp4Size;
  final String? webp;
  final String? webpSize;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'url': url,
        'width': width,
        'height': height,
        'size': size,
        'mp4': mp4,
        'mp4_size': mp4Size,
        'webp': webp,
        'webp_size': webpSize
      };

  @override
  String toString() =>
      'GiphyFullImage{url: $url, width: $width, height: $height, size: $size, mp4: $mp4, mp4Size: $mp4Size, webp: $webp, webpSize: $webpSize}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyFullImage &&
          runtimeType == other.runtimeType &&
          url == other.url &&
          width == other.width &&
          height == other.height &&
          size == other.size &&
          mp4 == other.mp4 &&
          mp4Size == other.mp4Size &&
          webp == other.webp &&
          webpSize == other.webpSize;

  @override
  int get hashCode =>
      url.hashCode ^
      width.hashCode ^
      height.hashCode ^
      size.hashCode ^
      mp4.hashCode ^
      mp4Size.hashCode ^
      webp.hashCode ^
      webpSize.hashCode;
}

class GiphyOriginalImage {
  GiphyOriginalImage({
    required this.url,
    required this.width,
    required this.height,
    required this.size,
    required this.frames,
    required this.mp4,
    required this.mp4Size,
    required this.webp,
    required this.webpSize,
    required this.hash,
  });

  factory GiphyOriginalImage.fromJson(Map<String, dynamic> json) =>
      GiphyOriginalImage(
        url: json['url'] as String,
        width: json['width'] as String,
        height: json['height'] as String,
        size: json['size'] as String,
        frames: json['frames'] as String,
        mp4: json['mp4'] as String,
        mp4Size: json['mp4_size'] as String,
        webp: json['webp'] as String?,
        webpSize: json['webp_size'] as String?,
        hash: json['hash'] as String,
      );
  final String url;
  final String width;
  final String height;
  final String size;
  final String frames;
  final String mp4;
  final String mp4Size;
  final String? webp;
  final String? webpSize;
  final String hash;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'url': url,
        'width': width,
        'height': height,
        'size': size,
        'frames': frames,
        'mp4': mp4,
        'mp4_size': mp4Size,
        'webp': webp,
        'webp_size': webpSize,
        'hash': hash
      };

  @override
  String toString() =>
      'GiphyOriginalImage{url: $url, width: $width, height: $height, size: $size, frames: $frames, mp4: $mp4, mp4Size: $mp4Size, webp: $webp, webpSize: $webpSize, hash: $hash}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyOriginalImage &&
          runtimeType == other.runtimeType &&
          url == other.url &&
          width == other.width &&
          height == other.height &&
          size == other.size &&
          frames == other.frames &&
          mp4 == other.mp4 &&
          mp4Size == other.mp4Size &&
          webp == other.webp &&
          webpSize == other.webpSize &&
          hash == other.hash;

  @override
  int get hashCode =>
      url.hashCode ^
      width.hashCode ^
      height.hashCode ^
      size.hashCode ^
      frames.hashCode ^
      mp4.hashCode ^
      mp4Size.hashCode ^
      webp.hashCode ^
      webpSize.hashCode ^
      hash.hashCode;
}

class GiphyStillImage {
  GiphyStillImage({
    required this.url,
    required this.width,
    required this.height,
    required this.size,
  });

  factory GiphyStillImage.fromJson(Map<String, dynamic> json) =>
      GiphyStillImage(
        url: json['url'] as String,
        width: json['width'] as String,
        height: json['height'] as String,
        size: json['size'] as String? ?? '',
      );
  final String url;
  final String width;
  final String height;
  final String size;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'url': url,
        'width': width,
        'height': height,
        'size': size
      };

  @override
  String toString() =>
      'GiphyStillImage{url: $url, width: $width, height: $height, size: $size}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyStillImage &&
          runtimeType == other.runtimeType &&
          url == other.url &&
          width == other.width &&
          height == other.height &&
          size == other.size;

  @override
  int get hashCode =>
      url.hashCode ^ width.hashCode ^ height.hashCode ^ size.hashCode;
}

class GiphyDownsampledImage {
  GiphyDownsampledImage({
    required this.url,
    required this.width,
    required this.height,
    required this.size,
    required this.webp,
    required this.webpSize,
  });

  factory GiphyDownsampledImage.fromJson(Map<String, dynamic> json) =>
      GiphyDownsampledImage(
        url: json['url'] as String,
        width: json['width'] as String,
        height: json['height'] as String,
        size: json['size'] as String,
        webp: json['webp'] as String?,
        webpSize: json['webp_size'] as String?,
      );
  final String url;
  final String width;
  final String height;
  final String size;
  final String? webp;
  final String? webpSize;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'url': url,
        'width': width,
        'height': height,
        'size': size,
        'webp': webp,
        'webp_size': webpSize
      };

  @override
  String toString() =>
      'GiphyDownsampledImage{url: $url, width: $width, height: $height, size: $size, webp: $webp, webpSize: $webpSize}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyDownsampledImage &&
          runtimeType == other.runtimeType &&
          url == other.url &&
          width == other.width &&
          height == other.height &&
          size == other.size &&
          webp == other.webp &&
          webpSize == other.webpSize;

  @override
  int get hashCode =>
      url.hashCode ^
      width.hashCode ^
      height.hashCode ^
      size.hashCode ^
      webp.hashCode ^
      webpSize.hashCode;
}

class GiphyLoopingImage {
  GiphyLoopingImage({
    required this.mp4,
    required this.mp4Size,
  });

  factory GiphyLoopingImage.fromJson(
    Map<String, dynamic> json,
  ) =>
      GiphyLoopingImage(
        mp4: json['mp4'] as String,
        mp4Size: json['mp4_size'] as String,
      );
  final String mp4;
  final String mp4Size;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'mp4': mp4,
        'mp4_size': mp4Size,
      };

  @override
  String toString() => 'GiphyLoopingImage{mp4: $mp4, mp4Size: $mp4Size}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyLoopingImage &&
          runtimeType == other.runtimeType &&
          mp4 == other.mp4 &&
          mp4Size == other.mp4Size;

  @override
  int get hashCode => mp4.hashCode ^ mp4Size.hashCode;
}

class GiphyPreviewImage {
  GiphyPreviewImage({
    required this.width,
    required this.height,
    required this.mp4,
    required this.mp4Size,
  });

  factory GiphyPreviewImage.fromJson(Map<String, dynamic> json) =>
      GiphyPreviewImage(
        width: json['width'] as String,
        height: json['height'] as String,
        mp4: json['mp4'] as String? ?? '',
        mp4Size: json['mp4_size'] as String? ?? '',
      );
  final String width;
  final String height;
  final String mp4;
  final String mp4Size;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'width': width,
        'height': height,
        'mp4': mp4,
        'mp4_size': mp4Size
      };

  @override
  String toString() =>
      'GiphyPreviewImage{width: $width, height: $height, mp4: $mp4, mp4Size: $mp4Size}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyPreviewImage &&
          runtimeType == other.runtimeType &&
          width == other.width &&
          height == other.height &&
          mp4 == other.mp4 &&
          mp4Size == other.mp4Size;

  @override
  int get hashCode =>
      width.hashCode ^ height.hashCode ^ mp4.hashCode ^ mp4Size.hashCode;
}

class GiphyDownsizedImage {
  GiphyDownsizedImage({
    required this.url,
    required this.width,
    required this.height,
    required this.size,
  });

  factory GiphyDownsizedImage.fromJson(Map<String, dynamic> json) =>
      GiphyDownsizedImage(
        url: json['url'] as String,
        width: json['width'] as String,
        height: json['height'] as String,
        size: json['size'] as String,
      );
  final String url;
  final String width;
  final String height;
  final String size;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'url': url,
        'width': width,
        'height': height,
        'size': size
      };

  @override
  String toString() =>
      'GiphyDownsizedImage{url: $url, width: $width, height: $height, size: $size}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyDownsizedImage &&
          runtimeType == other.runtimeType &&
          url == other.url &&
          width == other.width &&
          height == other.height &&
          size == other.size;

  @override
  int get hashCode =>
      url.hashCode ^ width.hashCode ^ height.hashCode ^ size.hashCode;
}

class GiphyWebPImage {
  GiphyWebPImage({
    required this.url,
    required this.width,
    required this.height,
    required this.size,
  });

  factory GiphyWebPImage.fromJson(Map<String, dynamic> json) => GiphyWebPImage(
        url: json['url'] as String,
        width: json['width'] as String,
        height: json['height'] as String,
        size: json['size'] as String,
      );
  final String url;
  final String width;
  final String height;
  final String size;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'url': url,
        'width': width,
        'height': height,
        'size': size
      };

  @override
  String toString() =>
      'GiphyWebPImage{url: $url, width: $width, height: $height, size: $size}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyWebPImage &&
          runtimeType == other.runtimeType &&
          url == other.url &&
          width == other.width &&
          height == other.height &&
          size == other.size;

  @override
  int get hashCode =>
      url.hashCode ^ width.hashCode ^ height.hashCode ^ size.hashCode;
}
