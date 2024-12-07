import 'dart:async';
import 'dart:typed_data';

extension IoStreamExtensions on Stream<List<int>> {
  Future<Uint8List> toFuture() {
    final completer = Completer<Uint8List>();
    final list = <int>[];
    listen(list.addAll, onDone: () {
      completer.complete(Uint8List.fromList(list));
    });
    return completer.future;
  }
}
