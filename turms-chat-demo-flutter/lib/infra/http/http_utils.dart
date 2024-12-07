import 'dart:async';
import 'dart:io';

import 'package:http/http.dart' as http;

import '../io/file_utils.dart';
import 'downloaded_file.dart';
import 'file_too_large_exception.dart';

class HttpUtils {
  HttpUtils._();

  static final _pendingTaskIdToDownloadedFile =
      <String, Future<DownloadedFile?>>{};

  static Future<DownloadedFile?> downloadFileIfNotExists(
      {String? taskId,
      String method = 'GET',
      required Uri uri,
      required String filePath,
      int maxBytes = (1 << 32) - 1,
      void Function(double progress)? onProgress}) async {
    final file = File(filePath);
    final exists = await file.exists();
    if (exists) {
      return DownloadedFile(file: file);
    }
    return downloadFile(
        taskId: taskId,
        method: method,
        uri: uri,
        filePath: filePath,
        maxBytes: maxBytes,
        onProgress: onProgress);
  }

  static Future<DownloadedFile?> downloadFile(
      {String? taskId,
      String method = 'GET',
      required Uri uri,
      required String filePath,
      int maxBytes = (1 << 32) - 1,
      void Function(double progress)? onProgress}) async {
    if (taskId == null) {
      return _downloadFile(
        method: method,
        uri: uri,
        filePath: filePath,
        maxBytes: maxBytes,
        onProgress: onProgress,
      );
    }
    final downloadedFile = _pendingTaskIdToDownloadedFile[taskId];
    if (downloadedFile != null) {
      return downloadedFile;
    }
    final downloadFile = _downloadFile(
      method: method,
      uri: uri,
      filePath: filePath,
      maxBytes: maxBytes,
      onProgress: onProgress,
    );
    _pendingTaskIdToDownloadedFile[taskId] = downloadFile;
    return downloadFile.whenComplete(
        () => unawaited(_pendingTaskIdToDownloadedFile.remove(taskId)));
  }

  static Future<DownloadedFile?> _downloadFile(
      {String method = 'GET',
      required Uri uri,
      required String filePath,
      int maxBytes = (1 << 32) - 1,
      void Function(double progress)? onProgress}) async {
    final response = await http.Client().send(http.Request(method, uri));
    final contentLength = response.contentLength;
    if (contentLength != null && contentLength > maxBytes) {
      throw FileTooLargeException(maxBytes, contentLength);
    }
    var received = 0;
    final bytes = <int>[];
    final completer = Completer<DownloadedFile?>();
    response.stream.listen(
        (value) {
          bytes.addAll(value);
          received += value.length;
          // The "contentLength" header is not always the real size,
          // so we need to calculate size.
          if (received > maxBytes) {
            throw FileTooLargeException(maxBytes, received);
          }
          if (contentLength != null) {
            onProgress?.call(received / contentLength);
          }
        },
        onError: completer.completeError,
        onDone: () async {
          if (bytes.isEmpty) {
            completer.complete();
            return;
          }
          final file = await FileUtils.writeAsBytes(filePath, bytes);
          completer.complete(DownloadedFile(file: file, bytes: bytes));
        });
    return completer.future;
  }
}
