import 'dart:io';
import 'dart:typed_data';

import 'package:file_picker/file_picker.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';

import '../app/app_config.dart';

final pathSeparator = Platform.pathSeparator;

class FileUtils {
  FileUtils._();

  static bool _isPickingFile = false;

  static Future<String?> getFileDownloadPath(String name, String? ext) async {
    final downloadsDir = await getDownloadsDirectory();
    if (downloadsDir == null) {
      return null;
    }
    ext = ext == null
        ? ''
        : ext.startsWith('.')
            ? ext
            : '.$ext';
    var path =
        '${downloadsDir.path}$pathSeparator${AppConfig.packageInfo.packageName}$pathSeparator$name$ext';
    var num = 1;
    while (await File(path).exists()) {
      path =
          '${downloadsDir.path}$pathSeparator${AppConfig.packageInfo.packageName}$pathSeparator$name (${num++})$ext';
    }
    return path;
  }

  static Future<void> saveFile(String path, Uint8List content) async {
    final file = File(path);
    await file.writeAsBytes(content);
  }

  static Future<File> writeAsBytes(String filePath, List<int> bytes) async {
    final dir = Directory(dirname(filePath));
    if (!await dir.exists()) {
      await dir.create(recursive: true);
    }
    final file = File(filePath);
    await file.writeAsBytes(bytes);
    return file;
  }

  static Future<FilePickerResult?> pickFile(
      {List<String>? allowedExtensions, bool withReadStream = false}) async {
    if (_isPickingFile) {
      return null;
    }
    _isPickingFile = true;
    try {
      return await FilePicker.platform.pickFiles(
        type: allowedExtensions == null ? FileType.any : FileType.custom,
        // TODO: translate filters
        allowedExtensions: allowedExtensions,
        withReadStream: withReadStream,
      );
    } finally {
      _isPickingFile = false;
    }
  }
}
