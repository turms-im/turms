import 'gif.dart';

class GiphyResponse {
  GiphyResponse(
      {required this.data, required this.pagination, required this.meta});

  factory GiphyResponse.fromJson(Map<String, dynamic> json) {
    final pagination = json['pagination'];
    final meta = json['meta'];
    return GiphyResponse(
        data: (json['data'] as List?)
                ?.whereType<Map<String, dynamic>>()
                .map(GiphyGif.fromJson)
                .toList(
                  growable: false,
                ) ??
            List<GiphyGif>.empty(),
        pagination: pagination == null
            ? null
            : GiphyPagination.fromJson(
                pagination as Map<String, dynamic>,
              ),
        meta: meta == null
            ? null
            : GiphyMeta.fromJson(meta as Map<String, dynamic>));
  }

  final List<GiphyGif> data;
  final GiphyPagination? pagination;
  final GiphyMeta? meta;

  Map<String, dynamic> toJson() =>
      <String, dynamic>{'data': data, 'pagination': pagination, 'meta': meta};

  @override
  String toString() =>
      'GiphyCollection{data: $data, pagination: $pagination, meta: $meta}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyResponse &&
          runtimeType == other.runtimeType &&
          data == other.data &&
          pagination == other.pagination &&
          meta == other.meta;

  @override
  int get hashCode => data.hashCode ^ pagination.hashCode ^ meta.hashCode;
}

class GiphyPagination {
  factory GiphyPagination.fromJson(Map<String, dynamic> json) =>
      GiphyPagination(
        totalCount: json['total_count'] as int? ?? 0,
        count: json['count'] as int? ?? 0,
        offset: json['offset'] as int? ?? 0,
      );

  GiphyPagination(
      {required this.totalCount, required this.count, required this.offset});

  final int totalCount;
  final int count;
  final int offset;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'total_count': totalCount,
        'count': count,
        'offset': offset
      };

  @override
  String toString() =>
      'GiphyPagination{totalCount: $totalCount, count: $count, offset: $offset}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyPagination &&
          runtimeType == other.runtimeType &&
          totalCount == other.totalCount &&
          count == other.count &&
          offset == other.offset;

  @override
  int get hashCode => totalCount.hashCode ^ count.hashCode ^ offset.hashCode;
}

class GiphyMeta {
  GiphyMeta(
      {required this.status, required this.message, required this.responseId});

  factory GiphyMeta.fromJson(Map<String, dynamic> json) => GiphyMeta(
        status: json['status'] as int? ?? 0,
        message: json['msg'] as String? ?? '',
        responseId: json['response_id'] as String? ?? '',
      );

  final int status;
  final String message;
  final String responseId;

  Map<String, dynamic> toJson() => <String, dynamic>{
        'status': status,
        'msg': message,
        'response_id': responseId
      };

  @override
  String toString() =>
      'GiphyMeta{status: $status, msg: $message, responseId: $responseId}';

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GiphyMeta &&
          runtimeType == other.runtimeType &&
          status == other.status &&
          message == other.message &&
          responseId == other.responseId;

  @override
  int get hashCode => status.hashCode ^ message.hashCode ^ responseId.hashCode;
}
