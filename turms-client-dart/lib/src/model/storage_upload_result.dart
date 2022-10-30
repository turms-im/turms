class StorageUploadResult {
  final Uri uri;
  final Map<String, String> metadata;
  final String data;

  StorageUploadResult(this.uri, this.metadata, this.data);

  @override
  String toString() =>
      'StorageUploadResult{uri: $uri, metadata: $metadata, data: $data}';
}
