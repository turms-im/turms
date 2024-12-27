import 'images.dart';

class GiphyGif {
  GiphyGif({
    required this.title,
    required this.type,
    required this.id,
    required this.slug,
    required this.url,
    required this.bitlyGifUrl,
    required this.bitlyUrl,
    required this.embedUrl,
    required this.username,
    required this.source,
    required this.rating,
    required this.contentUrl,
    required this.sourceTld,
    required this.sourcePostUrl,
    required this.isSticker,
    required this.importDatetime,
    required this.trendingDatetime,
    required this.images,
  });

  factory GiphyGif.fromJson(Map<String, dynamic> json) => GiphyGif(
        title: json['title'] as String?,
        type: json['type'] as String?,
        id: json['id'] as String?,
        slug: json['slug'] as String?,
        url: json['url'] as String?,
        bitlyGifUrl: json['bitly_gif_url'] as String?,
        bitlyUrl: json['bitly_url'] as String?,
        embedUrl: json['embed_url'] as String?,
        username: json['username'] as String?,
        source: json['source'] as String?,
        rating: json['rating'] as String?,
        contentUrl: json['content_url'] as String?,
        sourceTld: json['source_tld'] as String?,
        sourcePostUrl: json['source_post_url'] as String?,
        isSticker: json['is_sticker'] as int?,
        importDatetime: json['import_datetime'] == null
            ? null
            : DateTime.parse(json['import_datetime'] as String),
        trendingDatetime: json['trending_datetime'] == null
            ? null
            : DateTime.parse(json['trending_datetime'] as String),
        images: GiphyImages.fromJson(
          json['images'] as Map<String, dynamic>,
        ),
      );
  String? title;
  String? type;
  String? id;
  String? slug;
  String? url;
  String? bitlyGifUrl;
  String? bitlyUrl;
  String? embedUrl;
  String? username;
  String? source;
  String? rating;
  String? contentUrl;
  String? sourceTld;
  String? sourcePostUrl;
  int? isSticker;
  DateTime? importDatetime;
  DateTime? trendingDatetime;
  GiphyImages? images;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'title': title,
        'type': type,
        'id': id,
        'slug': slug,
        'url': url,
        'bitly_gif_url': bitlyGifUrl,
        'bitly_url': bitlyUrl,
        'embed_url': embedUrl,
        'username': username,
        'source': source,
        'rating': rating,
        'content_url': contentUrl,
        'source_tld': sourceTld,
        'source_post_url': sourcePostUrl,
        'is_sticker': isSticker,
        'import_datetime': importDatetime?.toIso8601String(),
        'trending_datetime': trendingDatetime?.toIso8601String(),
        'images': images?.toJson()
      };

  @override
  String toString() =>
      'GiphyGif{title: $title, type: $type, id: $id, slug: $slug, url: $url, bitlyGifUrl: $bitlyGifUrl, bitlyUrl: $bitlyUrl, embedUrl: $embedUrl, username: $username, source: $source, rating: $rating, contentUrl: $contentUrl, sourceTld: $sourceTld, sourcePostUrl: $sourcePostUrl, isSticker: $isSticker, importDatetime: $importDatetime, trendingDatetime: $trendingDatetime, images: $images}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyGif &&
          runtimeType == other.runtimeType &&
          title == other.title &&
          type == other.type &&
          id == other.id &&
          slug == other.slug &&
          url == other.url &&
          bitlyGifUrl == other.bitlyGifUrl &&
          bitlyUrl == other.bitlyUrl &&
          embedUrl == other.embedUrl &&
          username == other.username &&
          source == other.source &&
          rating == other.rating &&
          contentUrl == other.contentUrl &&
          sourceTld == other.sourceTld &&
          sourcePostUrl == other.sourcePostUrl &&
          isSticker == other.isSticker &&
          importDatetime == other.importDatetime &&
          trendingDatetime == other.trendingDatetime &&
          images == other.images;

  @override
  int get hashCode =>
      title.hashCode ^
      type.hashCode ^
      id.hashCode ^
      slug.hashCode ^
      url.hashCode ^
      bitlyGifUrl.hashCode ^
      bitlyUrl.hashCode ^
      embedUrl.hashCode ^
      username.hashCode ^
      source.hashCode ^
      rating.hashCode ^
      contentUrl.hashCode ^
      sourceTld.hashCode ^
      sourcePostUrl.hashCode ^
      isSticker.hashCode ^
      importDatetime.hashCode ^
      trendingDatetime.hashCode ^
      images.hashCode;
}
