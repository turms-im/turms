import 'package:fixnum/fixnum.dart';

class StorageUploadResult {
  final Uri uri;
  final Map<String, String> metadata;
  final String data;
  final Int64? resourceIdNum;
  final String? resourceIdStr;

  StorageUploadResult(this.uri, this.metadata, this.data, this.resourceIdNum,
      this.resourceIdStr);

  @override
  String toString() =>
      'StorageUploadResult{uri: $uri, metadata: $metadata, data: $data, resourceIdNum: $resourceIdNum, resourceIdStr: $resourceIdStr}';
}
