import 'dart:io';
import 'dart:typed_data';

import 'package:super_clipboard/super_clipboard.dart';

class DataReaderFileValueAdapter extends DataReaderFile {
  DataReaderFileValueAdapter(this.file);

  final File file;

  @override
  void close() {
    // do nothing.
  }

  @override
  String? get fileName => file.path;

  @override
  int? get fileSize => file.statSync().size;

  @override
  Stream<Uint8List> getStream() {
    final stream = file.openRead();
    return stream.map(Uint8List.fromList);
  }

  @override
  Future<Uint8List> readAll() => file.readAsBytes();
}
