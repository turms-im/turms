import 'dart:io';
import 'dart:typed_data';

class DownloadedFile {
  DownloadedFile({required this.file, List<int>? bytes}) : _bytes = bytes;

  final File file;
  final List<int>? _bytes;

  Future<Uint8List> get bytes {
    if (_bytes case final bytes?) {
      return Future.value(Uint8List.fromList(bytes));
    }
    return file.readAsBytes();
  }
}
