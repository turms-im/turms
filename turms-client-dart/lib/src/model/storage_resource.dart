import 'dart:typed_data';

class StorageResource {
  final Uri uri;
  final Map<String, String> metadata;
  final Uint8List data;

  StorageResource(this.uri, this.metadata, this.data);

  @override
  String toString() =>
      'StorageResource{uri: $uri, metadata: $metadata, data: $data}';
}
