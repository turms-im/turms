class GithubAsset {
  GithubAsset({
    this.url,
    this.id,
    this.nodeId,
    this.name,
    this.label,
    this.contentType,
    this.state,
    this.size,
    this.downloadCount,
    this.createdAt,
    this.updatedAt,
    this.browserDownloadUrl,
  });

  GithubAsset.fromJson(Map<String, dynamic> json)
      : url = json['url'] as String?,
        id = json['id'] as int?,
        nodeId = json['node_id'] as String?,
        name = json['name'] as String?,
        label = json['label'] as String?,
        contentType = json['content_type'] as String?,
        state = json['state'] as String?,
        size = json['size'] as int?,
        downloadCount = json['download_count'] as int?,
        createdAt = json['created_at'] as String?,
        updatedAt = json['updated_at'] as String?,
        browserDownloadUrl = json['browser_download_url'] as String?;

  final String? url;
  final int? id;
  final String? nodeId;
  final String? name;
  final String? label;
  final String? contentType;
  final String? state;
  final int? size;
  final int? downloadCount;
  final String? createdAt;
  final String? updatedAt;
  final String? browserDownloadUrl;
}
